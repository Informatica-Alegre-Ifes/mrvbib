package coletor_gps;

import java.text.SimpleDateFormat;
import java.text.ParseException;

import java.util.Date;

class Util
{
	private static SimpleDateFormat formatoData;

	static 
	{
		formatoData = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}

	public static Date construirData(String strData, String strHora)
	{
		String dia, mes, ano, hora, minuto, segundo;

		dia = strData.substring(0, 2);
		mes = strData.substring(2, 4);
		ano = strData.substring(4, 6);
		hora = strHora.substring(0, 2);
		minuto = strHora.substring(2, 4);
		segundo = strHora.substring(4, 6);
		
		try
		{
			return (formatoData.parse(dia + "/" + mes + "/20" + ano + " " + hora + ":" + minuto + ":" + segundo));
		}
		catch (ParseException excecao)
		{
			return (null);
		}
	}

	public static String obterTextoData(Date data)
	{
		return (formatoData.format(data));
	}
}