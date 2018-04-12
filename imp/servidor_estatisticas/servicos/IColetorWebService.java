package servidor_estatisticas.servicos;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC)
public interface IColetorWebService
{
	@WebMethod
	boolean carregar(List<Dado> dados);
}