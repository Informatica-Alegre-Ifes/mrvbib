package coletor_gps;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.net.NetworkInterface;
import java.net.SocketException;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;

class Util implements IStatusProdutor
{
	private final String FORMATO = "yyyy-MM-dd HH:mm:ss";

	private SimpleDateFormat formatoData;
	private Status status;

	public Util(Status status)
	{
		this.status = status;
		formatoData = new SimpleDateFormat(FORMATO);
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

	public String obterTexto(Date data, String formato)
	{
		String strData = "";

		try
		{
			formatoData.applyPattern(formato);
			strData = formatoData.format(data);
		}
		catch (Exception excecao)
		{
			Erro.registrar(excecao);
			statusMudou(Status.Semaforo.Amarelo);
		}
		finally
		{
			formatoData.applyPattern(FORMATO);
			return (strData);
		}
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

	public BufferedReader executarInstrucaoConsole(String instrucao)	
	{
		ProcessBuilder contrutorProcessos = new ProcessBuilder("bash", "-c", instrucao);
		contrutorProcessos.redirectErrorStream(true);
		BufferedReader leitorDados = null;

		try
		{
			Process processo = contrutorProcessos.start();
			leitorDados = new BufferedReader(new InputStreamReader(processo.getInputStream()));
			statusMudou(Status.Semaforo.Verde);
		}
		catch (SocketException excecao)
		{
			statusMudou(Status.Semaforo.Vermelho);
			Erro.registrar(excecao);
		}
		catch (IOException excecao)
		{
			statusMudou(Status.Semaforo.Vermelho);
			Erro.registrar(excecao);
		}
		
		return (leitorDados);
	}

	public String obterEnderecoMAC()
	{
		String enderecoMAC = null;
		BufferedReader leitorDados = executarInstrucaoConsole("/sbin/ifconfig | grep ether | awk '{print $2}'");		

		try
		{
			if ((enderecoMAC = leitorDados.readLine()) != null)
			{
				enderecoMAC.toUpperCase().replace(":", "-");
				statusMudou(Status.Semaforo.Verde);
			}
			else
				statusMudou(Status.Semaforo.Amarelo);
		}
		catch (IOException excecao)
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
