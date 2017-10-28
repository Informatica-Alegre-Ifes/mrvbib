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

	public Dado(String mensagemGPS)
	{
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

	public static boolean salvar(Dado dado)
	{
		String strTransacao;

		strTransacao = "INSERT INTO DADO_GPS ";
		strTransacao += "(";
		strTransacao += "DADO_VL_LATITUDE, ";
		strTransacao += "DADO_SG_ORIENTACAO_LATITUDE, ";
		strTransacao += "DADO_VL_LONGITUDE, ";
		strTransacao += "DADO_SG_ORIENTACAO_LONGITUDE, ";
		strTransacao += "DADO_VL_VELOCIDADE, ";
		strTransacao += "DADO_DT_CAPTURA";
		strTransacao += ")";
		strTransacao += "VALUES ";
		strTransacao += "(";
		strTransacao += dado.latitude;
		strTransacao += ", ";
		strTransacao += "'" + dado.orientacaoLatitude + "'";
		strTransacao += ", ";
		strTransacao += dado.longitude;
		strTransacao += ", ";
		strTransacao += "'" + dado.orientacaoLatitude + "'";
		strTransacao += ", ";
		strTransacao += dado.velocidade;
		strTransacao += ", ";
		strTransacao += "'" + dado.data + "'";
		strTransacao += ")";
		strTransacao += ";";

		return (Persistencia.salvar(strTransacao));
	}

	public void imprimir()
	{
		String strDado;
		SimpleDateFormat dataFormat;
	
		dataFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		strDado = "Data/Hora: " + dataFormat.format(data) + "\n";
		strDado += "Status: " + status + "\n";
		strDado += "Latitude: " + latitude + "\n";
		strDado += "Orinetação latitude: " + orientacaoLatitude + "\n";
		strDado += "Longitude: " + longitude + "\n";
		strDado += "Orinetação longitude: " + orientacaoLongitude + "\n";
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
						data = construirData(mensagemSegregada[i + 8], mensagemSegregada[i]);
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

	private static Date construirData(String strData, String strHora) throws ParseException
	{
		SimpleDateFormat dataFormat;
		String dia, mes, ano, hora, minuto, segundo;
	
		dataFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		dia = strData.substring(0, 2);
		mes = strData.substring(2, 4);
		ano = strData.substring(4, 6);
		hora = strHora.substring(0, 2);
		minuto = strHora.substring(2, 4);
		segundo = strHora.substring(4, 6);
		
		return (dataFormat.parse(dia + "/" + mes + "/20" + ano + " " + hora + ":" + minuto + ":" + segundo));
	}
}
