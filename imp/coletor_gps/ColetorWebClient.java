package coletor_gps;

import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebServiceException;

import java.net.URL;
import java.net.MalformedURLException;

class ColetorWebClient implements IStatusProdutor
{
	private Status status;

	public ColetorWebClient(Status status)
	{
		this.status = status;
	}

	public boolean carregar(List<Dado> dados)
	{
		boolean carregou = false;

		try
		{
			URL url = new URL("http://192.168.1.103:3128/servidor_estatisticas?wsdl");
			QName qnameServico = new QName("http://servicos.servidor_estatisticas/","ColetorWebServiceImpService");
			QName qnamePort = new QName("http://servicos.servidor_estatisticas/", "ColetorWebServiceImpPort");
			Service servico = Service.create(url, qnameServico);
			IColetorWebClient coletorWebClient = servico.getPort(qnamePort, IColetorWebClient.class);
			
			if (coletorWebClient.carregar(dados.toArray(new Dado[dados.size()])))
			{
				carregou = !carregou;
				statusMudou(Status.Semaforo.Verde);				
			}
			else
				statusMudou(Status.Semaforo.Amarelo);
		}
		catch (MalformedURLException excecao)
		{
			statusMudou(Status.Semaforo.Vermelho);
			Erro.registrar(excecao);
		}
		catch (WebServiceException excecao)
		{
			statusMudou(Status.Semaforo.Vermelho);
			Erro.registrar(excecao);
		}
		finally
		{
			return (carregou);
		}
	}

	public Status getStatus()
	{
		return (status);
	}

	public void statusMudou(Status.Semaforo semaforoStatus)
	{
		status.setSemaforo(semaforoStatus);
		status.notificarGerente(this);
	}
}  