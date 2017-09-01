#include <stdio.h>
#include <mysql.h>
#include <my_global.h>
#include <mysql/mysql.h>
#include "leitor_uart.h"

int main(void)
{
	//MYSQL conexao;
	gps_data_t gps_data;
	
	//mysql_init(&conexao);
	//if(mysql_real_connect(&conexao, "localhost", "root", "123456", "mrvbib", 0, NULL, 0))
	//{
		printf("conectado com sucesso\n");
		//mysql_close(&conexao);
		while (TRUE)
		{
			obter_dado_gps(&gps_data);
			printf("Hora: %d:%d:%d\t\tLatitude: %s %s\t\tLongitude: %s %s\tVelocidade: %lf\t\tData: %d/%d/%d\n", gps_data.horario.hora, gps_data.horario.minuto, gps_data.horario.segundo, gps_data.latitude, gps_data.latitude_o, gps_data.longitude, gps_data.longitude_o, gps_data.velocidade, gps_data.data.dia, gps_data.data.mes, gps_data.data.ano);

		}
		//sprintf(gps_data,"INSERT INTO dado_gps(DGPS_NU_LATITUDE, DGPS_DC_LATITUDE_ORIENTACAO, DGPS_NU_LONGITUDE, DGPS_DC_LONGITUDE, DGPS_NU_VELOCIDADE, DGPS_DT_DADO, DGPS_DT_REGISTRO) VALUES (%f,'%c', %f, '%c', %lf,'2015-02-18-00:00:00')", gps_data->latitude, gps_data->latitude_o, gps_data->longitude, gps_data->longitude_o, gps_data->velocidade);
		//mysql_query(conexao, gps_data);
	//}

	//conexao.execute("INSERT INTO dado_gps (DGPS_NU_LATITUDE, DGPS_DC_LATITUDE_ORIENTACAO, DGPS_NU_LONGITUDE, DGPS_DC_LONGITUDE, DGPS_NU_VELOCIDADE, DGPS_DT_DADO, DGPS_DT_REGISTRO) VALUES ("+ gps_data->latitude + "," + gps_data->latitude_o + "," + gps_data->longitude + "," + gps_data->longitude_o + "," + gps_data->velocidade + ", now(), now());");

	return (0);
}
