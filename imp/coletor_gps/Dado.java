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

	private Persistencia persistencia;
	private Util util;

	public Dado(String mensagemGPS, Persistencia persistencia, Util util)
	{
		this.persistencia = persistencia;
		this.util = util;
		this.status = 'V';

		construir(mensagemGPS);
	}

	public boolean ehValido()
	{
		return status == 'A' ? (true) : (false);
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
		strTransacao += "DADO_DT_CAPTURA, ";
		strTransacao += "DADO_DT_REGISTRO";
		strTransacao += ") ";
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
		strTransacao += "'" + util.obterTexto(data) + "'";
		strTransacao += ", ";
		strTransacao += "NOW()";
		strTransacao += ")";
		strTransacao += ";";

		return (persistencia.salvar(strTransacao));
	}

	public void imprimir()
	{
		String strDado;

		strDado = "Data/Hora: " + util.obterTexto(data) + "\n";
		strDado += "Latitude: " + latitude + "\n";
		strDado += "Orientação latitude: " + orientacaoLatitude + "\n";
		strDado += "Longitude: " + longitude + "\n";
		strDado += "Orientação longitude: " + orientacaoLongitude + "\n";
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
					data = util.construirData(mensagemSegregada[i + 8], mensagemSegregada[i]);
					break;
				case 2:
					status = util.obterCaractereDeTexto(mensagemSegregada[i], 0);
					break;
				case 3:
					latitude = util.alterarTipoParaDouble(mensagemSegregada[i]);
					break;
				case 4:
					orientacaoLatitude = util.obterCaractereDeTexto(mensagemSegregada[i], 0);
					break;
				case 5:
					longitude = util.alterarTipoParaDouble(mensagemSegregada[i]);
					break;
				case 6:
					orientacaoLongitude = util.obterCaractereDeTexto(mensagemSegregada[i], 0);
					break;
				case 7:
					velocidade = util.alterarTipoParaDouble(mensagemSegregada[i]);
					break;
			}
	}
}
