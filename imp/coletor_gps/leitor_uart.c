#include <stdio.h>
#include <stdlib.h>
#include <termios.h>
#include <fcntl.h>
#include <unistd.h>
#include <string.h>

int InicializarLeitura();
void InicializarTermios(int uart0_file);
unsigned char ObterDados(int uart0_file);
void QuebrarLinha(unsigned char protocolo);

struct horario
{
	short int hora;
	short int minuto;
	short int segundo;
};

struct data
{
	short int dia;
	short int mes;
	short int ano;
};

struct gps_data
{
	struct horario horario;
	float latitude;
	float longitude;
	float velocidade;
	float angulo;
	struct data data;	
};

int main()
{
	unsigned char protocolo;
	struct gps_data gps;
	int leitor_uart0 = InicializarLeitura();
	InicializarTermios(leitor_uart0);
	while(1)
	{
	 	protocolo = ObterDados(leitor_uart0);
		QuebrarLinha(protocolo);
	}
		 
	return (0);
}

int InicializarLeitura()
{
	int uart0_file = -1;
	
	uart0_file = open("/dev/ttyAMA0", O_RDWR | O_NOCTTY | O_NDELAY);

	if (uart0_file == -1)
	{
		printf("ERRO!");
		exit(1);
	}
	return uart0_file;
}

void InicializarTermios(int uart0_file)
{
	
	struct termios options;
	tcgetattr(uart0_file, &options);
	options.c_cflag = B9600 | CS8 | CLOCAL | CREAD;
	options.c_iflag = IGNPAR;
	options.c_oflag = 0;
	options.c_lflag = 0;

	tcflush(uart0_file, TCIFLUSH);
	tcsetattr(uart0_file, TCSANOW, &options); 

}


unsigned char ObterDados(int uart0_file)
{			
	if (uart0_file != -1)
	{		
	 		unsigned char rx_buffer[256];
			unsigned char *ptr_buffer = rx_buffer;
			int rx_length = -1;
			char serial_data;

			while (1)
			{
				rx_length = read(uart0_file, (void *) &serial_data, 1);
				
				if (rx_length < 0)
				{
					sleep(1);
				}
				else
				{
					if (serial_data == '\n')
					{
						*ptr_buffer++ = '\0';
						break;
					}
					*ptr_buffer++ = serial_data;
				}
			}
			
			printf("%s\n", rx_buffer);
			return rx_buffer;
	} 
}

void QuebrarLinha(unsigned char protocolo)
{
	unsigned char *token;
	unsigned char *coringa = ",";

	token = strtok(protocolo, coringa);
	
	token = strtok(NULL, coringa); 
}



/*
void ObterDados(int uart0_file)
{			
	if (uart0_file != -1)
	{
		while (1)
		{
			unsigned char rx_buffer[256];
			unsigned char *ptr_buffer = rx_buffer;
			int rx_length = -1;
			char serial_data;
			while (1)
			{
				rx_length = read(uart0_file, (void *) &serial_data, 1);
				
				if (rx_length < 0)
				{
					sleep(1);
				}
				else
				{
					if (serial_data == '\n')
					{
						*ptr_buffer++ = '\0';
						break;
					}
					*ptr_buffer++ = serial_data;
				}
			}
			printf("%s\n", rx_buffer);
		}
	}

}*/
