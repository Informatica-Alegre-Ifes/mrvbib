#include <stdio.h>
#include <mysql.h>
#include <my_global.h>
#include <mysql/mysql.h>

int main(void)
{
	MYSQL conexao;
	
	mysql_init(&conexao);
	if(mysql_real_connect(&conexao, "localhost", "root", "123456", "mrvbib", 0, NULL, 0))
	{
		printf("conectado com sucesso\n");
		mysql_close(&conexao);
	}
	return (0);
}
