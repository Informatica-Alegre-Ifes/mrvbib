package coletor_gps;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;

class Dado
{
	private Date data;
	private char status;
	private double latitude;
	private	char orientacaoLatitude;
	private double longitude;
	private char orientacaoLongitude;
	private double velocidade;

	private SimpleDateFormat dataFormat;


	public Dado(String mensagemGPS)
	{
		dataFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		construir(mensagemGPS);
	}

	public Date getData()
	{
		return (data);
	}
	
	public char getStatus()
	{
		return (status);
	}

	public double getLatitude()
	{
		return (latitude);
	}

	public char getOrientacaoLatitude()
	{
		return (orientacaoLatitude);
	}

	public double getLongitude()
	{
		return (longitude);
	}

	public char getOrientacaoLongitude()
	{
		return (orientacaoLongitude);
	}

	public double getVelocidade()
	{
		return (velocidade);
	}

	public void imprimir()
	{
		String strDado = "";
		strDado += "Data/Hora: " + dataFormat.format(data) + "\n";
		strDado += "Status: " + status + "\n";
		strDado += "Latitude: " + latitude + "\n";
		strDado += "Orinetaçãõ latitude: " + orientacaoLatitude + "\n";
		strDado += "Longitude: " + longitude + "\n";
		strDado += "Orinetaçãõ longitude: " + orientacaoLongitude + "\n";
		strDado += "Velocidade: " + velocidade;
		System.out.println(strDado);
	}

	private void construir(String mensagemGPS)
	{
		String[] mensagemSegregada = mensagemGPS.split(",");

		for (int i = 0; i < mensagemSegregada.length; ++i)
			switch (i)
			{
				case 1:
					try
					{
						construirData(mensagemSegregada[i + 8], mensagemSegregada[i]);
					}
					catch (ParseException excecao)
					{
					}
					break;
				case 2:
					status = mensagemSegregada[i].charAt(0);
					break;
				case 3:
					latitude = Double.parseDouble(mensagemSegregada[i]);
					break;
				case 4:
					orientacaoLatitude = mensagemSegregada[i].charAt(0);
					break;
				case 5:
					longitude = Double.parseDouble(mensagemSegregada[i]);
					break;
				case 6:
					orientacaoLongitude = mensagemSegregada[i].charAt(0);
					break;
				case 7:
					velocidade = Double.parseDouble(mensagemSegregada[i]);
					break;
			}
	}

	private void construirData(String strData, String strHora) throws ParseException
	{
		String dia, mes, ano, hora, minuto, segundo;
	
		dia = strData.substring(0, 2);
		mes = strData.substring(2, 4);
		ano = strData.substring(4, 6);
		hora = strHora.substring(0, 2);
		minuto = strHora.substring(2, 4);
		segundo = strHora.substring(4, 6);
		data = dataFormat.parse(dia + "/" + mes + "/20" + ano + " " + hora + ":" + minuto + ":" + segundo);
	}
}
