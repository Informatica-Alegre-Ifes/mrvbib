package servidor_estatisticas.servicos;

import javax.jws.WebService;

@WebService(endpointInterface = "servidor_estatisticas.IColetorWebService")
public class ColetorWebServiceImp implements IColetorWebService
{
	public boolean carregar(List<Dado> dados)
	{

	}
}