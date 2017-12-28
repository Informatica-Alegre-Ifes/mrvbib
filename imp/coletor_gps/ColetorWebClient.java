package coletor_gps;

import java.util.List;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;
import java.net.MalformedURLException;

class ColetorWebClient implements IStatusProdutor, IColetorWebClient
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
			URL url = new URL("http://192.168.0.102:8080/servidor?wsdl");
			QName qnameServico = new QName("http://servidor/","carregarService");
			QName qnamePort = new QName("http://servidor/", "carregarPort");
			Service servico = Service.create(url, qnameServico);
			IColetorWebClient coletorWebClient = servico.getPort(qnamePort, IColetorWebClient.class);
			
			coletorWebClient.carregar(dados);

			carregou = true;
			statusMudou(Status.Semaforo.Verde);
		}
		catch (MalformedURLException excecao)
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
