import java.io.IOException;
import java.io.OutputStream;

import java.util.Enumeration;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;

class SMS
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

	public void enviar(String mensagem)
	{
		CommPortIdentifier portaComm = obterPortaCommSerial();

		String messageString1 = "AT";
		String messageString2 = "AT+CPIN=\"7078\"";
		String messageString3 = "AT+CMGF=1"; 
		String messageString4 = "AT+CMGS=\"+27999150088\"";
		char enter = 13;
		char CTRLZ = 26;

		try
		{
			SerialPort portaSerial = (SerialPort) portaComm.open("/dev/ttyS0", 2000);
			portaSerial.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE); 

			OutputStream streamSaida = portaSerial.getOutputStream();

			streamSaida.write((messageString1 + enter).getBytes());
			Thread.sleep(100); 
			streamSaida.flush();
			streamSaida.write((messageString2 + enter).getBytes()); 
			Thread.sleep(100); 
			streamSaida.flush();
			streamSaida.write((messageString3 + enter).getBytes());
			Thread.sleep(100); 
			streamSaida.flush(); 
			streamSaida.write((messageString4 + enter).getBytes()); 
			Thread.sleep(100);  
			streamSaida.flush();
			streamSaida.write((mensagem + CTRLZ).getBytes());  
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

	public static void main(String[] args) throws InterruptedException
	{
		SMS sms = new SMS();
		sms.enviar("MRVBIB Test");
	}
}