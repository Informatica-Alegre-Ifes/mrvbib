package servidor_estatisticas.servicos;

import javax.xml.ws.Endpoint;

public class ColetorWebService
{
	public static void main(String[] args)
	{
		IColetorWebService coletorWebService = new ColetorWebServiceImp();

		Endpoint.publish("http://192.168.0.102:3128/servidor_estatisticas", coletorWebService);
	}
}