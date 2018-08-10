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

	public void enviarMensagemHTTP(String apn, String endereco, int porta, String documento, String parametros)
	{
		CommPortIdentifier portaComm = obterPortaCommSerial();
		List<InstrucaoAT> mensagensSIM = new ArrayList<InstrucaoAT>();

		mensagensSIM.add(new InstrucaoAT("AT+CGATT=1" + "\r\n", 1000));
		mensagensSIM.add(new InstrucaoAT("AT+CGDCONT=1,\"IP\",\"" + apn + "\"" + "\r\n", 1000));
		mensagensSIM.add(new InstrucaoAT("AT+CIPSTART=\"TCP\",\"" + endereco + "\"," + porta + "\r\n", 5000));
		mensagensSIM.add(new InstrucaoAT("AT+CIPSEND" + "\r", 2000));
		mensagensSIM.add(new InstrucaoAT("GET " + documento + "?" + parametros + " HTTP/1.1" + "\r\n", 1000));
		mensagensSIM.add(new InstrucaoAT("HOST: " + endereco + "\r\n", 2000));
		mensagensSIM.add(new InstrucaoAT("\u001a", 3000));
		mensagensSIM.add(new InstrucaoAT("AT+CIPCLOSE" + "\r\n", 2000));
		mensagensSIM.add(new InstrucaoAT("AT+CIPSHUT" + "\r\n", 1000));

		try
		{
			SerialPort portaSerial = (SerialPort) portaComm.open(getClass().getName(), 2000);
			portaSerial.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE); 

			OutputStream streamSaida = portaSerial.getOutputStream();

			for (InstrucaoAT instrucaoAT : mensagensSIM)
			{
				streamSaida.write(instrucaoAT.getInstrucao().getBytes());
				streamSaida.flush();
				Thread.sleep(instrucaoAT.getPeriodo());
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
		//comunicacaoMovel.enviarMensagemSMS("+5527999150088", "MRVBIB Test");
		comunicacaoMovel.enviarMensagemHTTP("zap.vivo.com.br", "date.jsontest.com", 80, "/index.html", "teste");
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