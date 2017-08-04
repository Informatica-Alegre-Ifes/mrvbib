#include <stdio.h>
#include <stdlib.h>
#include <termios.h>
#include <fcntl.h>
#include <unistd.h>
#include <string.h>

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
	char *status;
	horario_t horario;
	float latitude;
	char *latitude_o;
	float longitude;
	char *longitude_o;
	float velocidade;
	data_t data;	
};
typedef struct gps_data gps_data_t;

int inicializar_leitura(void);
void definir_configuracoes(int);
char *obter_dados(int);
char *extrair_gprmc_dados(char *);
gps_data_t construir_gps_data(char *);

int
main(int argc, char **args)
{
	gps_data_t gps_data;
	char *dados_gps;
	char *d_gprmc;
	int leitor_uart0;
	
	d_gprmc = NULL;
	leitor_uart0 = inicializar_leitura();
	definir_configuracoes(leitor_uart0);

	while (1)
	{
	 	dados_gps = obter_dados(leitor_uart0);
		d_gprmc = extrair_gprmc_dados(dados_gps);

		if (d_gprmc)
			gps_data = construir_gps_data(d_gprmc);
	}
	free(d_gprmc);
		 
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

char *
obter_dados(int uart0_file)
{	
	char *ptr_buffer;
	int rx_length;
	char serial_data;
	int i;

	if (uart0_file != -1)
	{
		ptr_buffer = (char *) malloc(256 * sizeof(char));
	
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
	} 

	return (ptr_buffer);
}

char *
extrair_gprmc_dados(char *mensagem_gps)
{
	const char *delimitador;
	const char *id_gprmc;
	char *split_mensagem_gps;
	char *dado_gprmc;

	delimitador = (const char *) "$";
	id_gprmc = "GPRMC";
	dado_gprmc = NULL;

	for (split_mensagem_gps = strtok(mensagem_gps, delimitador); split_mensagem_gps != NULL; split_mensagem_gps = strtok(NULL, delimitador))
		if (strncmp(split_mensagem_gps, id_gprmc, strlen(id_gprmc)) == 0)
		{	
			dado_gprmc = (char *) malloc(strlen(split_mensagem_gps) * sizeof(char));
			strncat(dado_gprmc, split_mensagem_gps, strlen(split_mensagem_gps));
			break;
		}
	free(mensagem_gps);
	
	return (dado_gprmc);
}

gps_data_t
construir_gps_data(char *dado_gprmc)
{
	gps_data_t gps_data;
	const char *delimitador;
	char *split_dado_gprmc;
	int index;
	
	delimitador = (const char *) ",";

	for (split_dado_gprmc = strtok(dado_gprmc, delimitador), index = 0; split_dado_gprmc != NULL; split_dado_gprmc = strtok(NULL, delimitador), ++index)
		switch (index)
		{
			case 1:
				
				gps_data.horario.hora = (int) split_dado_gprmc;
				break;
			case 2:
				gps_data.status = (char *) split_dado_gprmc;
				break;
			case 3:
				gps_data.latitude = (float) strtof(split_dado_gprmc, NULL);
				break;
			case 4:
				gps_data.latitude_o = (char *) split_dado_gprmc;
				break;
	 		case 5:
				break;
			case 6:
				break;
			case 7:
				break;
			case 8:
				break;

		}
	printf("%s %d %f %s\n", gps_data.status, gps_data.horario.hora, gps_data.latitude, gps_data.latitude_o);

	return (gps_data);
}
