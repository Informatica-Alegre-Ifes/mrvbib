package coletor_gps_new;

import java.util.Enumeration;

import java.io.InputStream;
import java.io.IOException;

import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;

class Serial implements IStatusProdutor
{
	private static Serial serial;
	private String porta;
	private Status status;
	private SerialPort portaSerial;

	private Serial(String porta, Status status)
	{
		this.porta = porta;
		this.status = status;
		portaSerial = null;
	}

	public static Serial obterInstancia(String porta, Status status)
	{
		if (serial == null)
			return (new Serial(porta, status));
		return (serial);
	}

	public SerialPort obterPortaSerial()
	{
		if (portaSerial == null)
			configurarPortaSerial();
		return (portaSerial);
	}

	public void limparBuffer()
	{
		byte[] dadosBuffer = new byte[1024];
		int tamanho = -1;

		try
		{
			InputStream streamEntrada = obterPortaSerial().getInputStream();

			while ((tamanho = streamEntrada.read(dadosBuffer)) > 200)
				System.out.print(new String(dadosBuffer, 0, tamanho));
			streamEntrada.close();
		}
		catch (IOException excecao)
		{
			Erro.registrar(excecao);
			statusMudou(Status.Semaforo.Vermelho);
		}
	}

	private void configurarPortaSerial()
	{
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