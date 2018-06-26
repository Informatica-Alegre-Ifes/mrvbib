import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;

import java.util.Enumeration;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;

class ComunicacaoMovel
{
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
		String mensagem2 = "AT+CPIN=\"7078\"";
		String mensagem3 = "AT+CGATT=1";
		String mensagem4 = "AT+CGDCONT=1,\"IP\",\"zap.vivo.com.br\"";
		String mensagem5 = "AT+CIICR";
		String mensagem6 = "AT+CIPSTATUS";
		char enter = 13;
		char ctrlz = 26;

		try
		{
			SerialPort portaSerial = (SerialPort) portaComm.open("/dev/ttyS0", 2000);
			portaSerial.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE); 

			OutputStream streamSaida = portaSerial.getOutputStream();
			InputStream streamEntrada = portaSerial.getInputStream();

			streamSaida.write((mensagem1 + enter).getBytes());
			Thread.sleep(500);
			streamEntrada.read();
			streamSaida.flush();
			streamSaida.write((mensagem2 + enter).getBytes());
			Thread.sleep(500);
			streamEntrada.read();
			streamSaida.flush();
			streamSaida.write((mensagem3 + enter).getBytes());
			Thread.sleep(500);
			streamEntrada.read();
			streamSaida.flush();
			streamSaida.write((mensagem4 + enter).getBytes());
			Thread.sleep(500);
			streamEntrada.read();
			streamSaida.flush();
			streamSaida.write((mensagem5 + enter).getBytes());
			Thread.sleep(500);
			streamEntrada.read();
			streamSaida.flush();
			streamSaida.write((mensagem6 + enter).getBytes());
			Thread.sleep(500);
			streamEntrada.read();
			streamSaida.flush();
			// streamSaida.write((mensagem + ctrlz).getBytes());  
			// streamSaida.flush(); 
			// Thread.sleep(500);
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
		comunicacaoMovel.enviarMensagemHTTP("MRVBIB Test");
	}
}