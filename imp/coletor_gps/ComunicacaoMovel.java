package coletor_gps;

import java.io.IOException;
import java.io.OutputStream;

import java.util.Enumeration;
import java.util.List;
import java.util.ArrayList;

import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;

class ComunicacaoMovel implements IStatusProdutor
{
	private static final char enter = 13;
	private static final char ctrlz = 26;
	private Status status;

	public ComunicacaoMovel(Status status)
	{
		this.status = status;
	}

	private CommPortIdentifier obterPortaCommSerial()
	{
		Enumeration portasComm = CommPortIdentifier.getPortIdentifiers();

		while (portasComm.hasMoreElements())
		{
			CommPortIdentifier portaComm = (CommPortIdentifier) portasComm.nextElement();

			if (portaComm.getPortType() == CommPortIdentifier.PORT_SERIAL && portaComm.getName().equals("/dev/ttyS0"))
				return (portaComm);
		}

		return (null);
	}

	public void enviarMensagemSMS(String numeroCelular, String mensagem)
	{
		List<String> mensagensSIM = new ArrayList<String>();
		CommPortIdentifier portaComm;

		mensagensSIM.add("AT");
		mensagensSIM.add("ATE0");
		mensagensSIM.add("AT+CMGF=1");
		mensagensSIM.add("AT+CMGS=\"+" + numeroCelular + "\"");
		mensagensSIM.add(mensagem);

		try
		{
			portaComm = obterPortaCommSerial();
			SerialPort portaSerial = (SerialPort) portaComm.open("/dev/ttyS0", 2000);
			portaSerial.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE); 

			OutputStream streamSaida = portaSerial.getOutputStream();

			for (int i = 0; i < mensagensSIM.size(); ++i)
			{
				if (i < (mensagensSIM.size() - 1))
					streamSaida.write((mensagensSIM.get(i) + enter).getBytes());
				else
					streamSaida.write((mensagensSIM.get(i) + ctrlz).getBytes());
				Thread.sleep(1000);
				streamSaida.flush();
				//Led.acenderLedRoxo();
			}

			streamSaida.close();
			portaSerial.close();

			statusMudou(Status.Semaforo.Amarelo);
		}
		catch (PortInUseException excecao)
		{
			statusMudou(Status.Semaforo.Vermelho);
			Erro.registrar(excecao);
		}
		catch (IOException excecao)
		{
			statusMudou(Status.Semaforo.Vermelho);
			Erro.registrar(excecao);
		}
		catch (UnsupportedCommOperationException excecao)
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