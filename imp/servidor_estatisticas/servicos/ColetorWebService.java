package servidor_estatisticas.servicos;

import servidor_estatisticas.servicos.IColetorWebService;
import servidor_estatisticas.servicos.ColetorWebServiceImp;

import javax.xml.ws.Endpoint;

public class ColetorWebService
{
	public static void main(String[] args)
	{
		IColetorWebService coletorWebService = new ColetorWebServiceImp();

		Endpoint.publish("http://192.168.0.102:8080/servidor_estatisticas/servicos", coletorWebService);
	}
}