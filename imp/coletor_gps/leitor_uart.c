#include <stdio.h>
#include <stdlib.h>
#include <termios.h>
#include <fcntl.h>
#include <unistd.h>
#include <string.h>

int inicializar_leitura(void);
void definir_configuracoes(int);
unsigned char *obter_dados(int);
void construir_gps_data(unsigned char *);

struct horario
{
	short int hora;
	short int minuto;
	short int segundo;
};
typedef struct horario horario_t;

struct data
{
	short int dia;
	short int mes;
	short int ano;
};
typedef struct data data_t;

struct gps_data
{
	horario_t horario;
	float latitude;
	float longitude;
	float velocidade;
	float angulo;
	data_t data;	
};
typedef struct gps_data gps_data_t;

int
main(int argc, char **args)
{
	unsigned char *dados_gps;
	// gps_data_t gps_data;
	int leitor_uart0;

	leitor_uart0 = inicializar_leitura();
	definir_configuracoes(leitor_uart0);

	while (1)
	{
	 	dados_gps = obter_dados(leitor_uart0);
		construir_gps_data(dados_gps);
	}
		 
	return (0);
}

int
inicializar_leitura(void)
{
	int uart0_file;
	
	uart0_file = open("/dev/ttyAMA0", O_RDWR | O_NOCTTY | O_NDELAY);

	if (uart0_file == -1)
		exit(1);

	return (uart0_file);
}

void
definir_configuracoes(int uart0_file)
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

unsigned char *
obter_dados(int uart0_file)
{	
	unsigned char *ptr_buffer;
	int rx_length;
	char serial_data;
	int i;

	if (uart0_file != -1)
	{
		ptr_buffer = (unsigned char *) malloc(256 * sizeof(unsigned char));
	
		if (!ptr_buffer)
			exit(1);

		for (i = 0; ; i++)
		{
			rx_length = read(uart0_file, (void *) &serial_data, 1);
			
			if (rx_length < 0)
				sleep(1);
			else
			{
				if (serial_data == '\n')
				{
					ptr_buffer[i] = '\0';
					break;
				}
				ptr_buffer[i] = serial_data;
			}
		}
		
		printf("%s\n\n", ptr_buffer);
	} 

	return (ptr_buffer);
}

void
construir_gps_data(unsigned char *mensagem_gps)
{
	unsigned char *token;
	unsigned char *token2;
	unsigned char *delimitador = (unsigned char *) "$GP";
	token = strtok(mensagem_gps, "RMC");
	token2 = strtok(NULL, "RMC");
	
	if (token == delimitador && token2 != NULL)
	{
		printf("Aqui esta o token, boa tarde! %s Um bom projeto a vcs\n", token);	
		printf("token2! %s Boa noite, renoncio falando\n", token2);
	}

	free(mensagem_gps);
}
