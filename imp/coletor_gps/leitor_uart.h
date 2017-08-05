#include <stdio.h>
#include <stdlib.h>
#include <termios.h>
#include <fcntl.h>
#include <unistd.h>
#include <string.h>

#define STATUS_GPRMC "A"

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
