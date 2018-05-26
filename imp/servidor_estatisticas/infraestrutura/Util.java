package servidor_estatisticas.infraestrutura;

import java.text.SimpleDateFormat;
import java.text.ParseException;

import java.util.Date;

public class Util
{
	private SimpleDateFormat formatoData;

	public Util()
	{
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
		}
		catch (ParseException excecao)
		{
			// TRATAMENTO DE ERRO
		}
		catch (StringIndexOutOfBoundsException excecao)
		{
			// TRATAMENTO DE ERRO
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
		}
		catch (ParseException excecao)
		{
			// TRATAMENTO DE ERRO
		}
		catch (StringIndexOutOfBoundsException excecao)
		{
			// TRATAMENTO DE ERRO
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
		}
		catch (NumberFormatException excecao)
		{
			// TRATAMENTO DE ERRO
		}
		catch (IllegalArgumentException excecao)
		{
			// TRATAMENTO DE ERRO
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
			// TRATAMENTO DE ERRO
		}
		finally
		{
			return (caractere);
		}
	}
}
