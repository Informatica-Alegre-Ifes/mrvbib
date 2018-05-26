package coletor_gps;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC)
public interface IColetorWebClient
{
	@WebMethod boolean carregar(Dado[] dados);
}