package coletor_gps;

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

	public boolean salvar()
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
		strTransacao += latitude;
		strTransacao += ", ";
		strTransacao += "'" + orientacaoLatitude + "'";
		strTransacao += ", ";
		strTransacao += longitude;
		strTransacao += ", ";
		strTransacao += "'" + orientacaoLongitude + "'";
		strTransacao += ", ";
		strTransacao += velocidade;
		strTransacao += ", ";
		strTransacao += "'" + Util.obterTexto(data) + "'";
		strTransacao += ")";
		strTransacao += ";";

		return (Persistencia.salvar(strTransacao));
	}

	public void imprimir()
	{
		String strDado;

		strDado = "Data/Hora: " + Util.obterTexto(data) + "\n";
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
					data = Util.construirData(mensagemSegregada[i + 8], mensagemSegregada[i]);
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
}
