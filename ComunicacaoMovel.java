import java.io.IOException;
import java.io.OutputStream;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.Enumeration;
import java.util.List;
import java.util.ArrayList;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;

/*
*
	INSTALAR: sudo apt-get install librxtx-java
	COMPILAR: javac ComunicacaoMovel.java -classpath .:/usr/share/java/RXTXcomm.jar
	EXECUTAR: sudo java -Djava.library.path=/usr/lib/jni -cp .:/usr/share/java/RXTXcomm.jar ComunicacaoMovel
*
*/

class ComunicacaoMovel
{
	private String porta;
	private boolean fim;

	public ComunicacaoMovel(String porta)
	{
		this.porta = porta;
		fim = false;
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
		CommPortIdentifier portaComm = obterPortaCommSerial();
		List<String> mensagensSIM = new ArrayList<String>();

		mensagensSIM.add("AT" + "\r");
		mensagensSIM.add("ATE0" + "\r\n");
		mensagensSIM.add("AT+CMGF=1" + "\r\n");
		mensagensSIM.add("AT+CMGS=\"" + numeroCelular + "\"" + ",145\r\n");
		mensagensSIM.add(mensagem + "\u001a");

		try
		{
			SerialPort portaSerial = (SerialPort) portaComm.open(getClass().getName(), 2000);
			portaSerial.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE); 

			OutputStream streamSaida = portaSerial.getOutputStream();

			final Thread threadLeituraStream = new Thread()
			{
				@Override
				public void run()
				{
					try
					{
						final BufferedReader leitor = new BufferedReader(new InputStreamReader(portaSerial.getInputStream()));
						String linha = null;
						while ((linha = leitor.readLine()) != null && !fim)
							System.out.println(linha);
						leitor.close();
					}
					catch (final Exception excecao)
					{
						excecao.printStackTrace();
					}
				}
			};
			threadLeituraStream.start();

			for (int i = 0; i < mensagensSIM.size(); ++i)
			{
				streamSaida.write((mensagensSIM.get(i)).getBytes());
				streamSaida.flush();
				Thread.sleep(1000);
			}
			fim = !fim;

			streamSaida.close();
			portaSerial.close();
		}
		catch (PortInUseException excecao)
		{
			excecao.printStackTrace();
		}
		catch (IOException excecao)
		{
			excecao.printStackTrace();
		}
		catch (UnsupportedCommOperationException excecao)
		{
			excecao.printStackTrace();
		}
		catch (InterruptedException excecao)
		{
			excecao.printStackTrace();
		}
	}

	public static void main(String[] args)
	{
		ComunicacaoMovel comunicacaoMovel = new ComunicacaoMovel("/dev/ttyS0");
		comunicacaoMovel.enviarMensagemSMS("+5527999150088", "MRVBIB Test");
	}
}