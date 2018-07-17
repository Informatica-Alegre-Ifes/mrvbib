package coletor_gps_new;

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
		List<String> mensagensSIM = new ArrayList<String>();

		mensagensSIM.add("AT" + "\r");
		mensagensSIM.add("ATE0" + "\r\n");
		mensagensSIM.add("AT+CMGF=1" + "\r\n");
		mensagensSIM.add("AT+CMGS=\"" + numeroCelular + "\"" + ",145\r\n");
		mensagensSIM.add(mensagem + "\u001a");

		try
		{
			OutputStream streamSaida = serial.obterPortaSerial().getOutputStream();

			//Led.acenderLedRoxo();
			for (int i = 0; i < mensagensSIM.size(); ++i)
			{
				streamSaida.write((mensagensSIM.get(i)).getBytes());
				streamSaida.flush();
				Thread.sleep(1000);
			}
			streamSaida.close();

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
	}

	public void enviarMensagemHTTP(String mensagem)
	{		
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