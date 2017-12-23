package coletor_gps;

import java.text.SimpleDateFormat;
import java.text.ParseException;

import java.util.Date;

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
			mes = strMySQLDataHora.substring(4, 6);
			dia = strMySQLDataHora.substring(6, 8);			
			
			hora = strMySQLDataHora.substring(9, 11);
			minuto = strMySQLDataHora.substring(11, 13);
			segundo = strMySQLDataHora.substring(13, 15);

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

	public double converterGrausEmRadianos(double graus)
	{
		return (graus * Math.PI / 180.0);
	}

	public double converterRadianosEmGraus(double radianos)
	{
		return (radianos * 180 / Math.PI);
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
