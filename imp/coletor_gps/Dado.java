package coletor_gps;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

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

	public Dado()
	{		
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

	public List<Dado> listar()
	{
		List<Dado> dados = new ArrayList<Dado>();
		Dado dado = null;
		List<String> objetos;
		String sqlConsulta;
		int quantidadeColunas = 0;

		sqlConsulta = "SELECT ";
		sqlConsulta += "DADO_VL_LATITUDE, ";
		quantidadeColunas++;
		sqlConsulta += "DADO_SG_ORIENTACAO_LATITUDE, ";
		quantidadeColunas++;
		sqlConsulta += "DADO_VL_LONGITUDE, ";
		quantidadeColunas++;
		sqlConsulta += "DADO_SG_ORIENTACAO_LONGITUDE, ";
		quantidadeColunas++;
		sqlConsulta += "DADO_VL_VELOCIDADE, ";
		quantidadeColunas++;
		sqlConsulta += "DADO_DT_CAPTURA, ";
		quantidadeColunas++;
		sqlConsulta += "DADO_DT_REGISTRO ";
		quantidadeColunas++;
		sqlConsulta += "FROM DADO_GPS ";

		objetos = persistencia.listar(sqlConsulta);

		System.out.println("Passei!");

		for (int i = 0; i < objetos.size(); ++i)
		{
			if (i % (quantidadeColunas - 1) == 0)
			{
				dados.add(dado);
				dado = new Dado();
			}

			switch (i)
			{
				case 0:
					dado.latitude = util.alterarTipoParaDouble(objetos.get(i));
					break;
				case 1:
					dado.orientacaoLatitude = util.obterCaractereDeTexto(objetos.get(i), 0);
					break;
				case 2:
					dado.longitude = util.alterarTipoParaDouble(objetos.get(i));
					break;
				case 3:
					dado.orientacaoLongitude = util.obterCaractereDeTexto(objetos.get(i), 0);
					break;
				case 4:
					dado.velocidade = util.alterarTipoParaDouble(objetos.get(i));
					break;
				case 5:
					dado.data = util.construirData(objetos.get(i));
					break;
				case 6:					
					break;
			}
		}

		return (dados);
	}

	public double calcularDistanciaGeografica2D(Dado dado, char unidade)
	{
		double theta;
		double distancia;

		theta = longitude - dado.longitude;
		distancia = Math.sin(util.converterGrausEmRadianos(latitude)) * Math.sin(util.converterGrausEmRadianos(dado.latitude)) + Math.cos(util.converterGrausEmRadianos(latitude)) * Math.cos(util.converterGrausEmRadianos(dado.latitude)) * Math.cos(util.converterGrausEmRadianos(theta));
		distancia = Math.acos(distancia);
		distancia = util.converterRadianosEmGraus(distancia);
		distancia = distancia * 60 * 1.1515d;
		
		if (unidade == 'K')
			return (distancia *= 1.609344d);
		else if (unidade == 'M')
			return (distancia *= 1609.344d);
		else if (unidade == 'N')
			return (distancia *= 0.8684d);

		return (distancia);
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
