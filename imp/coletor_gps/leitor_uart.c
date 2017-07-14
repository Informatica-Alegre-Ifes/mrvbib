#include <stdio.h>
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
	}
	printf("%d\n", uart0_file);
	
	struct termios options;
	tcgetattr(uart0_file, &options);
	options.c_cflag = B9600 | CS8 | CLOCAL | CREAD;
	options.c_iflag = IGNPAR;
	options.c_oflag = 0;
	options.c_lflag = 0;

	tcflush(uart0_file, TCIFLUSH);
	tcsetattr(uart0_file, TCSANOW, &options); 
	
	unsigned char tx_buffer[20];
	unsigned char *p_tx_buffer;
	
	p_tx_buffer = &tx_buffer[0];
	*p_tx_buffer++ = 'O';
	*p_tx_buffer++ = 'i';
	
	if (uart0_file != -1)
	{
		int contador = write(uart0_file, &tx_buffer[0], (p_tx_buffer-&tx_buffer[0]));
		
		if (contador < 0)
		{
			printf("ERROU\n");
		}	
	}

	 
	
	
	return (0);
}
