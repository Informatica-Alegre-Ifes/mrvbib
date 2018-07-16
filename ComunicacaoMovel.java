import java.io.IOException;
import java.io.OutputStream;

import java.util.Enumeration;
import java.util.List;
import java.util.ArrayList;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;

class ComunicacaoMovel
{
	private String porta;

	public ComunicacaoMovel(String porta)
	{
		this.porta = porta;
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

			for (int i = 0; i < mensagensSIM.size(); ++i)
			{
				streamSaida.write((mensagensSIM.get(i)).getBytes());
				streamSaida.flush();
				Thread.sleep(1000);
			}

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