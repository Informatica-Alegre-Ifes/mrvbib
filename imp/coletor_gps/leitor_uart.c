#include "leitor_uart.h"

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
		{
			printf("%s\n", d_gprmc);
			gps_data = construir_gps_data(d_gprmc);
			if (strncmp(gps_data.status, STATUS_GPRMC, strlen(STATUS_GPRMC)) == 0)
				printf("Hora: %d:%d:%d\t\tLatitude: %lf %s\t\tLongitude: %lf %s\tVelocidade: %f\t\tData: %d/%d/%d\n", gps_data.horario.hora, gps_data.horario.minuto, gps_data.horario.segundo, gps_data.latitude, gps_data.latitude_o, gps_data.longitude, gps_data.longitude_o, gps_data.velocidade, gps_data.data.dia, gps_data.data.mes, gps_data.data.ano);
		}
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
				{
					char substring[2];

					strncpy(substring, &split_dado_gprmc[0], 2);
					gps_data.horario.hora = atoi(substring);
					strncpy(substring, &split_dado_gprmc[2], 2);
					gps_data.horario.minuto = atoi(substring);
					strncpy(substring, &split_dado_gprmc[4], 2);
					gps_data.horario.segundo = atoi(substring);
				}
				break;
			case 2:
				gps_data.status = split_dado_gprmc;
				break;
			case 3:
				gps_data.latitude = (float) strtof(split_dado_gprmc, NULL);
				break;
			case 4:
				gps_data.latitude_o = split_dado_gprmc;
				break;
	 		case 5:
				gps_data.longitude = (float) strtof(split_dado_gprmc, NULL);
				break;
			case 6:
				gps_data.longitude_o = split_dado_gprmc; 
				break;
			case 7:
				gps_data.velocidade = (float) strtof(split_dado_gprmc, NULL);
				break;
			case 8:
				{
					char substring[2];

					strncpy(substring, &split_dado_gprmc[0], 2);
					gps_data.data.dia = atoi(substring);
					strncpy(substring, &split_dado_gprmc[2], 2);
					gps_data.data.mes = atoi(substring);
					strncpy(substring, &split_dado_gprmc[4], 2);
					gps_data.data.ano = atoi(substring);
				}
				break;
		}

	return (gps_data);
}
