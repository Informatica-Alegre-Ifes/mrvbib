package coletor_gps;

import java.io.IOException;
import java.io.OutputStream;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.Enumeration;
import java.util.List;
import java.util.ArrayList;

import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;

class ComunicacaoMovel implements IStatusProdutor
{
	private String porta;
	private SerialPort portaSerial;
	private Status status;

	public ComunicacaoMovel(String porta, Status status)
	{
		this.porta = porta;
		this.status = status;
		portaSerial = obterPortaSerial();
	}

	private SerialPort obterPortaSerial()
	{
		SerialPort portaSerial = null;

		try
		{
			CommPortIdentifier portaComm = obterPortaCommSerial();
			portaSerial = (SerialPort) portaComm.open(getClass().getName(), 2000);
			portaSerial.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE); 
		}
		catch (PortInUseException excecao)
		{
			Erro.registrar(excecao);
			statusMudou(Status.Semaforo.Vermelho);
		}
		catch (UnsupportedCommOperationException excecao)
		{
			Erro.registrar(excecao);
			statusMudou(Status.Semaforo.Vermelho);
		}
		finally
		{
			return (portaSerial);
		}
	}

	private CommPortIdentifier obterPortaCommSerial()
	{
		Enumeration portasComm = CommPortIdentifier.getPortIdentifiers();

		while (portasComm.hasMoreElements())
		{
			CommPortIdentifier portaComm = (CommPortIdentifier) portasComm.nextElement();

			if (portaComm.getPortType() == CommPortIdentifier.PORT_SERIAL && portaComm.getName().equals(porta))
				return (portaComm);
		}

		return (null);
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
			if (portaSerial != null)
			{
				OutputStream streamSaida = portaSerial.getOutputStream();

				//Led.acenderLedRoxo();
				for (int i = 0; i < mensagensSIM.size(); ++i)
				{
					streamSaida.write((mensagensSIM.get(i)).getBytes());
					streamSaida.flush();
					Thread.sleep(1000);
				}

				statusMudou(Status.Semaforo.Verde);
			}
			else
			{
				statusMudou(Status.Semaforo.Amarelo);
				Erro.registrar(new Exception("Porta serial (UART) em uso."));
			}
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