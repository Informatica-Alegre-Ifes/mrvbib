import java.io.IOException;
import java.io.OutputStream;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.Enumeration;

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
	private boolean fim;

	public ComunicacaoMovel()
	{
		fim = false;
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

	public void enviarMensagemSMS(String mensagem)
	{
		CommPortIdentifier portaComm = obterPortaCommSerial();

		String mensagem1 = "AT";
		String mensagem2 = "AT+CPIN=\"7078\"";
		String mensagem3 = "AT+CMGF=1"; 
		String mensagem4 = "AT+CMGS=\"+27999150088\"";
		char enter = 13;
		char ctrlz = 26;

		try
		{
			SerialPort portaSerial = (SerialPort) portaComm.open("/dev/ttyS0", 2000);
			portaSerial.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE); 

			OutputStream streamSaida = portaSerial.getOutputStream();

			streamSaida.write((mensagem1 + enter).getBytes());
			Thread.sleep(100); 
			streamSaida.flush();
			streamSaida.write((mensagem2 + enter).getBytes()); 
			Thread.sleep(100); 
			streamSaida.flush();
			streamSaida.write((mensagem3 + enter).getBytes());
			Thread.sleep(100); 
			streamSaida.flush(); 
			streamSaida.write((mensagem4 + enter).getBytes()); 
			Thread.sleep(100);  
			streamSaida.flush();
			streamSaida.write((mensagem + ctrlz).getBytes());  
			streamSaida.flush(); 
			Thread.sleep(500); 
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

	public void enviarMensagemHTTP(String mensagem)
	{
		CommPortIdentifier portaComm = obterPortaCommSerial();
		String mensagem1 = "AT";
		String mensagem2 = "AT+CGATT=1";
		String mensagem3 = "AT+CGDCONT=1,\"IP\",\"zap.vivo.com.br\"";
		String mensagem4 = "AT+CSTT=\"zap.vivo.com.br\",\"vivo\",\"vivo\"";
		String mensagem5 = "AT+CIICR";
		String mensagem6 = "AT+CIFSR";
		String mensagem7 = "AT+CIPSTATUS";
		String mensagem8 = "AT+CIPSTART=\"TCP\",\"uproc.com.br\",\"80\"";
		String mensagem9 = "AT+CIPCLOSE";
		String mensagem10 = "AT+CIPSHUT";
		char enter = 13;
		char ctrlz = 26;

		try
		{
			SerialPort portaSerial = (SerialPort) portaComm.open("/dev/ttyS0", 2000);
			portaSerial.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE); 

			OutputStream streamSaida = portaSerial.getOutputStream();

			final Thread threadLeituraStream = new Thread()
			{
				@Override
				public void run()
				{
					try {
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

			streamSaida.write((mensagem1 + enter).getBytes());
			Thread.sleep(2000);
			streamSaida.flush();
			streamSaida.write((mensagem2 + enter).getBytes());
			Thread.sleep(2000);
			streamSaida.flush();
			streamSaida.write((mensagem3 + enter).getBytes());
			Thread.sleep(2000);
			streamSaida.flush();
			streamSaida.write((mensagem4 + enter).getBytes());
			Thread.sleep(2000);
			streamSaida.flush();
			streamSaida.write((mensagem5 + enter).getBytes());
			Thread.sleep(2000);
			streamSaida.flush();
			streamSaida.write((mensagem6 + enter).getBytes());
			Thread.sleep(2000);
			streamSaida.flush();
			streamSaida.write((mensagem7 + enter).getBytes());
			Thread.sleep(2000);
			streamSaida.flush();
			streamSaida.write((mensagem8 + enter).getBytes());
			Thread.sleep(2000);
			streamSaida.flush();
			streamSaida.write((mensagem9 + enter).getBytes());
			Thread.sleep(2000);
			streamSaida.flush();
			streamSaida.write((mensagem10 + ctrlz).getBytes());
			Thread.sleep(2000);
			streamSaida.flush();
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

	public void enviarMensagemHTTP()
	{
		CommPortIdentifier portaComm = obterPortaCommSerial();
		String mensagem1 = "AT+CREG?";
		String mensagem2 = "AT+CGATT?";
		String mensagem3 = "AT+CSTT=\"zap.vivo.com.br\",\"vivo\",\"vivo\"";
		String mensagem4 = "AT+CSQ";
		String mensagem5 = "AT+CIICR";
		String mensagem6 = "AT+CIPSHUT";
		String mensagem7 = "AT+SAPBR=3,1,\"APN\",\"CMNET\"";
		String mensagem8 = "AT+SAPBR=3,1,\"Contype\",\"GPRS\"";
		String mensagem9 = "AT+SAPBR=1,1";
		String mensagem10 = "AT+HTTPINIT=?";
		String mensagem11 = "AT+HTTPPARA=\"URL\",\"www.uproc.com.br\"";
		String mensagem12 = "AT+HTTPACTION=0";
		String mensagem13 = "AT+HTTPREAD";
		char enter = 13;
		char ctrlz = 26;

		try
		{
			SerialPort portaSerial = (SerialPort) portaComm.open("/dev/ttyS0", 2000);
			portaSerial.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE); 

			OutputStream streamSaida = portaSerial.getOutputStream();

			final Thread threadLeituraStream = new Thread()
			{
				@Override
				public void run()
				{
					try {
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

			streamSaida.write((mensagem1 + enter).getBytes());
			Thread.sleep(2000);
			streamSaida.flush();
			streamSaida.write((mensagem2 + enter).getBytes());
			Thread.sleep(2000);
			streamSaida.flush();
			streamSaida.write((mensagem3 + enter).getBytes());
			Thread.sleep(2000);
			streamSaida.flush();
			streamSaida.write((mensagem4 + enter).getBytes());
			Thread.sleep(2000);
			streamSaida.flush();
			streamSaida.write((mensagem5 + enter).getBytes());
			Thread.sleep(2000);
			streamSaida.flush();
			streamSaida.write((mensagem6 + enter).getBytes());
			Thread.sleep(2000);
			streamSaida.flush();
			streamSaida.write((mensagem7 + enter).getBytes());
			Thread.sleep(2000);
			streamSaida.flush();
			streamSaida.write((mensagem8 + enter).getBytes());
			Thread.sleep(2000);
			streamSaida.flush();
			streamSaida.write((mensagem9 + enter).getBytes());
			Thread.sleep(2000);
			streamSaida.flush();
			streamSaida.write((mensagem10 + enter).getBytes());
			Thread.sleep(2000);
			streamSaida.flush();
			streamSaida.write((mensagem11 + enter).getBytes());
			Thread.sleep(2000);
			streamSaida.flush();
			streamSaida.write((mensagem12 + ctrlz).getBytes());
			Thread.sleep(2000);
			streamSaida.flush();
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
		ComunicacaoMovel comunicacaoMovel = new ComunicacaoMovel();
		comunicacaoMovel.enviarMensagemHTTP();
	}
}
