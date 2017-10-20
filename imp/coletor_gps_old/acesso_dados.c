#include <stdio.h>
#include <mysql.h>
#include <my_global.h>
#include <mysql/mysql.h>
#include "leitor_uart.h"

int main(void)
{
	MYSQL conexao;
	char *dados_gps = 0;	

	mysql_init(&conexao);
	if(mysql_real_connect(&conexao, "localhost", "root", "123456", "mrvbib", 0, NULL, 0))
	{
		printf("conectado com sucesso\n");
		while (TRUE)
		{
			gps_data_t gps_data;

			obter_dado_gps(&gps_data);
			printf("Hora: %d:%d:%d\t\tLatitude: %s %s\t\tLongitude: %s %s\tVelocidade: %lf\t\tData: %d/%d/%d\n", gps_data.horario.hora, gps_data.horario.minuto, gps_data.horario.segundo, gps_data.latitude, gps_data.latitude_o, gps_data.longitude, gps_data.longitude_o, gps_data.velocidade, gps_data.data.dia, gps_data.data.mes, gps_data.data.ano);
		
			sprintf(dados_gps,"INSERT INTO dado_gps(DGPS_NU_LATITUDE, DGPS_DC_LATITUDE_ORIENTACAO, DGPS_NU_LONGITUDE, DGPS_DC_LONGITUDE, DGPS_NU_VELOCIDADE, DGPS_DT_DADO, DGPS_DT_REGISTRO) VALUES ('%s','%s', '%s', '%s', '%lf', '%d-%d-%d %d:%d:%d', '%d-%d-%d %d:%d:%d');", gps_data.latitude, gps_data.latitude_o, gps_data.longitude, gps_data.longitude_o, gps_data.velocidade, gps_data.data.ano, gps_data.data.mes, gps_data.data.dia, gps_data.horario.hora, gps_data.horario.minuto, gps_data.horario.segundo, gps_data.data.ano, gps_data.data.mes, gps_data.data.dia, gps_data.horario.hora, gps_data.horario.minuto, gps_data.horario.segundo);
		}
	}

	return (0);
}
