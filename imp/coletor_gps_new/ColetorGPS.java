package coletor_gps_new;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;

class ColetorGPS implements IStatusProdutor
{
	private String mensagemNMEA;
	private Serial serial;
	private BufferedReader leitor;
	private Status status;
	private Monitor monitor;

	public ColetorGPS(Serial serial, String mensagemNMEA, Status status)
	{
		this.mensagemNMEA = mensagemNMEA;
		this.serial = serial;
		this.status = status;
	}
	
	public String obterMensagem()
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
			statusMudou(Status.Semaforo.Verde);
		}
		catch (IOException excecao)
		{
			statusMudou(Status.Semaforo.Vermelho);
			Erro.registrar(excecao);
		}
		catch (Exception excecao)
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
			monitor = new Monitor(this);
			leitor = new BufferedReader(new InputStreamReader(serial.obterPortaSerial().getInputStream()));

			monitor.monitorar();
		}
		catch (FileNotFoundException excecao)
		{
			statusMudou(Status.Semaforo.Vermelho);
			Erro.registrar(excecao);
		}
		catch (IOException excecao)
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
			monitor.finalizar();
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