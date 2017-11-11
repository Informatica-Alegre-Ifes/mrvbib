package coletor_gps;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;

class Serial implements IStatusProdutor
{
	private String mensagemNMEA;
	private String enderecoArquivo;
	private BufferedReader leitor;
	private Status status;

	public Serial(String enderecoArquivo, String mensagemNMEA, Status status)
	{
		this.mensagemNMEA = mensagemNMEA;
		this.enderecoArquivo = enderecoArquivo;
		this.status = status;
	}
	
	public String obterMensagemGPS(int periodo)
	{
		String linha;
		
		linha = "";
		try
		{
			inicializar();
			do
				linha = leitor.readLine();
			while (linha == null || linha.length() == 0 || !linha.startsWith(mensagemNMEA));
			finalizar();
			Thread.sleep(periodo);
			statusMudou(Status.Semaforo.Verde);
		}
		catch (IOException excecao)
		{
			statusMudou(Status.Semaforo.Vermelho);
			Erro.registrar(excecao);
		}
		catch (InterruptedException excecao)
		{
			statusMudou(Status.Semaforo.Vermelho);
			Erro.registrar(excecao);
		}
		finally
		{
			return (linha);
		}
	}

	private void inicializar()
	{
		try
		{
			leitor = new BufferedReader(new FileReader(enderecoArquivo));
		}
		catch (FileNotFoundException excecao)
		{
			statusMudou(Status.Semaforo.Vermelho);
			Erro.registrar(excecao);
		}
	}

	private void finalizar()
	{
		try
		{
			if (leitor != null)
			{
				leitor.close();
				leitor = null;
			}
		}
		catch (IOException excecao)
		{
			statusMudou(Status.Semaforo.Vermelho);
			Erro.registrar(excecao);
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
