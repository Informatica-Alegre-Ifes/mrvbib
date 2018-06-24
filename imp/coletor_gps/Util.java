package coletor_gps;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.IOException;

import java.net.NetworkInterface;
import java.net.SocketException;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;

class Util implements IStatusProdutor
{
	private SimpleDateFormat formatoData;
	private Status status;

	public Util(Status status)
	{
		this.status = status;
		formatoData = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}

	public Date construirData(String strData, String strHora)
	{
		Date data;
		String dia, mes, ano, hora, minuto, segundo;

		data = null;
		try
		{
			dia = strData.substring(0, 2);
			mes = strData.substring(2, 4);
			ano = strData.substring(4, 6);
			hora = strHora.substring(0, 2);
			minuto = strHora.substring(2, 4);
			segundo = strHora.substring(4, 6);

			data = formatoData.parse("20" + ano + "-" + mes + "-" + dia + " " + hora + ":" + minuto + ":" + segundo);
			statusMudou(Status.Semaforo.Verde);
		}
		catch (ParseException excecao)
		{
			statusMudou(Status.Semaforo.Amarelo);
			Erro.registrar(excecao);
		}
		catch (StringIndexOutOfBoundsException excecao)
		{
			statusMudou(Status.Semaforo.Amarelo);
			Erro.registrar(excecao);
		}
		finally
		{
			return (data);
		}
	}

	public Date construirData(String strMySQLDataHora)
	{
		Date data;
		String dia, mes, ano, hora, minuto, segundo;

		data = null;
		try
		{
			ano = strMySQLDataHora.substring(0, 4);
			mes = strMySQLDataHora.substring(5, 7);
			dia = strMySQLDataHora.substring(8, 10);
			
			hora = strMySQLDataHora.substring(11, 13);
			minuto = strMySQLDataHora.substring(14, 16);
			segundo = strMySQLDataHora.substring(17, 19);

			data = formatoData.parse(ano + "-" + mes + "-" + dia + " " + hora + ":" + minuto + ":" + segundo);
			statusMudou(Status.Semaforo.Verde);
		}
		catch (ParseException excecao)
		{
			statusMudou(Status.Semaforo.Amarelo);
			Erro.registrar(excecao);
		}
		catch (StringIndexOutOfBoundsException excecao)
		{
			statusMudou(Status.Semaforo.Amarelo);
			Erro.registrar(excecao);
		}
		finally
		{
			return (data);
		}
	}

	public String obterTexto(Date data)
	{
		return (formatoData.format(data));
	}
	
	public double alterarTipoParaDouble(Object valor)
	{
		double valorDouble;
		
		valorDouble = 0.0;
		try
		{
			switch ((valor.getClass().getSimpleName()))
			{
				case "String":
					valorDouble = Double.parseDouble(valor.toString());
					break;
				default:
					throw new IllegalArgumentException("Tipo inválido para converter em Double.");
			}
			statusMudou(Status.Semaforo.Verde);
		}
		catch (NumberFormatException excecao)
		{
			statusMudou(Status.Semaforo.Amarelo);
			Erro.registrar(excecao);
		}
		catch (IllegalArgumentException excecao)
		{
			statusMudou(Status.Semaforo.Amarelo);
			Erro.registrar(excecao);
		}
		finally
		{
			return (valorDouble);
		}
	}

	public char obterCaractereDeTexto(String texto, int posicao)
	{
		char caractere = Character.MIN_VALUE;

		try
		{
			caractere = texto.charAt(posicao);
		}
		catch (StringIndexOutOfBoundsException excecao)
		{
			statusMudou(Status.Semaforo.Amarelo);
			Erro.registrar(excecao);
		}
		finally
		{
			return (caractere);
		}
	}

	public String obterPropriedade(String nomePropriedade)
	{
		String propriedade = null;		
		Properties propriedades = new Properties();

		try
		{
			InputStream streamEntrada = Util.class.getClassLoader().getResourceAsStream("coletor_gps/recursos/config.properties");
			propriedades.load(streamEntrada);
			propriedade = propriedades.getProperty(nomePropriedade);
		}
		catch (FileNotFoundException excecao)
		{
			statusMudou(Status.Semaforo.Amarelo);
			Erro.registrar(excecao);
		}
		catch (IOException excecao)
		{
			statusMudou(Status.Semaforo.Amarelo);
			Erro.registrar(excecao);
		}
		
		return (propriedade);
	}

	public double converterGrausEmRadianos(double graus)
	{
		return (graus * Math.PI / 180.0);
	}

	public double converterRadianosEmGraus(double radianos)
	{
		return (radianos * 180 / Math.PI);
	}

	public String obterEnderecoMAC()
	{
		String enderecoMAC = "";

		try
		{
			Enumeration<NetworkInterface> interfacesRede = NetworkInterface.getNetworkInterfaces();

			while (interfacesRede.hasMoreElements())
			{
				NetworkInterface rede = interfacesRede.nextElement();
				byte[] bytesEnderecoMAC = rede.getHardwareAddress();

				if (bytesEnderecoMAC != null)
					for (int i = 0; i < bytesEnderecoMAC.length; ++i)
						enderecoMAC += String.format("%02X%s", bytesEnderecoMAC[i], (i < bytesEnderecoMAC.length - 1) ? "-" : "");
			}

			statusMudou(Status.Semaforo.Verde);
		}
		catch (SocketException excecao)
		{
			statusMudou(Status.Semaforo.Vermelho);
			Erro.registrar(excecao);
		}
		finally
		{
			return (enderecoMAC);
		}
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
