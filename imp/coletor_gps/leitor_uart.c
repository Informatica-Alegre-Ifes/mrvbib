#include <stdio.h>
#include <stdlib.h>
#include <termios.h>
#include <fcntl.h>
#include <unistd.h>


int main()
{
	int uart0_file = -1;
	
	uart0_file = open("/dev/ttyAMA0", O_RDWR | O_NOCTTY | O_NDELAY);

	if (uart0_file == -1)
	{
		printf("ERRO!");
		exit(1);
	}
	
	struct termios options;
	tcgetattr(uart0_file, &options);
	options.c_cflag = B9600 | CS8 | CLOCAL | CREAD;
	options.c_iflag = IGNPAR;
	options.c_oflag = 0;
	options.c_lflag = 0;

	tcflush(uart0_file, TCIFLUSH);
	tcsetattr(uart0_file, TCSANOW, &options); 
			
	if (uart0_file != -1)
	{
		unsigned char rx_buffer[256];
		int rx_length = -1;
		while (1)
		{
			rx_length = read(uart0_file, (void *) rx_buffer, 256);
			
			if (rx_length < 0)
			{
				sleep(1);
			}
			else if (rx_length == 0)
			{
				printf("Sem data para exibicao");	
			}
			else
			{
				printf("%s\n", rx_buffer);
			}
		}
	}

	 
	
	
	return (0);
}
