package servidor_estatisticas.servicos;

import javax.xml.ws.Endpoint;

public class ColetorWebService
{

	public static void main(String[] args)
	{
		Endpoint.publish("http://192.168.0.102:8080/servidor_estatisticas", new ColetorWebServiceImp());
	}
}