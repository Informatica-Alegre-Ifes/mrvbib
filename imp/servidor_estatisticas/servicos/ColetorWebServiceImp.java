package servidor_estatisticas.servicos;

import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "servidor_estatisticas.IColetorWebService")
public class ColetorWebServiceImp implements IColetorWebService
{
	public boolean carregar(List<Dado> dados)
	{

	}
}