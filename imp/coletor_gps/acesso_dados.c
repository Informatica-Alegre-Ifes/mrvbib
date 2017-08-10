#include <mysql.h>
#include <my_global.h>


int main(void)
{
	MYSQL mysql;
	printf("MYSQL client: %s\n", mysql_get_client_info());

	return (0);
}
