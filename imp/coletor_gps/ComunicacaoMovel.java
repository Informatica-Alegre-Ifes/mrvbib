package coletor_gps;

import java.io.IOException;
import java.io.OutputStream;

import java.util.List;
import java.util.ArrayList;

class ComunicacaoMovel implements IStatusProdutor
{
	private String porta;
	private Serial serial;
	private Status status;

	public ComunicacaoMovel(Serial serial, Status status)
	{
		this.serial = serial;
		this.status = status;
	}

	public void enviarMensagemSMS(String numeroCelular, String mensagem)
	{
		List<String> instrucoesAT = new ArrayList<String>();

		instrucoesAT.add("AT" + "\r");
		instrucoesAT.add("ATE0" + "\r\n");
		instrucoesAT.add("AT+CMGF=1" + "\r\n");
		instrucoesAT.add("AT+CMGS=\"" + numeroCelular + "\"" + ",145\r\n");
		instrucoesAT.add(mensagem + "\u001a");

		try
		{
			OutputStream streamSaida = serial.obterPortaSerial().getOutputStream();

			Led.acenderLedRoxo();
			for (int i = 0; i < instrucoesAT.size(); ++i)
			{
				streamSaida.write((instrucoesAT.get(i)).getBytes());
				streamSaida.flush();
				Thread.sleep(1000);
			}
			streamSaida.close();

			statusMudou(Status.Semaforo.Verde);
			Led.apagarLedRoxo();
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
	}

	public void enviarMensagemHTTP(String apn, String endereco, int porta, String documento, String parametros)
	{
		List<InstrucaoAT> instrucoesAT = new ArrayList<InstrucaoAT>();

		instrucoesAT.add(new InstrucaoAT("AT+CGATT=1" + "\r\n", 1000));
		instrucoesAT.add(new InstrucaoAT("AT+CGDCONT=1,\"IP\",\"" + apn + "\"" + "\r\n", 1000));
		instrucoesAT.add(new InstrucaoAT("AT+CIPSTART=\"TCP\",\"" + endereco + "\"," + porta + "\r\n", 5000));
		instrucoesAT.add(new InstrucaoAT("AT+CIPSEND" + "\r", 2000));
		instrucoesAT.add(new InstrucaoAT("GET /" + documento + "?" + parametros + " HTTP/1.1" + "\r\n", 1000));
		instrucoesAT.add(new InstrucaoAT("HOST: " + endereco + "\r\n\r\n", 2000));
		instrucoesAT.add(new InstrucaoAT("\u001a", 3000));
		instrucoesAT.add(new InstrucaoAT("AT+CIPCLOSE" + "\r\n", 2000));
		instrucoesAT.add(new InstrucaoAT("AT+CIPSHUT" + "\r\n", 1000));

		try
		{
			OutputStream streamSaida = serial.obterPortaSerial().getOutputStream();

			Led.acenderLedRoxo();
			for (InstrucaoAT instrucaoAT : instrucoesAT)
			{
				streamSaida.write(instrucaoAT.getInstrucao().getBytes());
				streamSaida.flush();
				Thread.sleep(instrucaoAT.getPeriodo());
			}
			streamSaida.close();

			statusMudou(Status.Semaforo.Verde);
			Led.apagarLedRoxo();
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

	private class InstrucaoAT
	{
		private String instrucao;
		private int periodo;

		public InstrucaoAT(String instrucao, int periodo)
		{
			this.instrucao = instrucao;
			this.periodo = periodo;
		}

		public String getInstrucao()
		{
			return (instrucao);
		}

		public int getPeriodo()
		{
			return (periodo);
		}
	}
}