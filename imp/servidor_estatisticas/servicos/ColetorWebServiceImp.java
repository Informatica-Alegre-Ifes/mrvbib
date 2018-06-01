package servidor_estatisticas.servicos;

import servidor_estatisticas.modelo.Dado;

import javax.jws.WebService;

@WebService(endpointInterface = "servidor_estatisticas.servicos.IColetorWebService")
public class ColetorWebServiceImp implements IColetorWebService
{
	public boolean carregar(Dado[] dados)
	{
		try
		{
			for (Dado dado : dados)
				dado.imprimir();

			return (true);
		}
		catch (Exception excecao)
		{
			// TRATAMENTO DE ERRO
			return (false);
		}
	}
}