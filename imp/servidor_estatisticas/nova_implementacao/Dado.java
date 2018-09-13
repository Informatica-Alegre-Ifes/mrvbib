import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import java.lang.IllegalArgumentException;

class Dado
{
	private String codigoMACDispositivo;
	private Date data;
	private double velocidade;
	private Coordenada coordenada;

	private Util util;
	private Persistencia persistencia;

	public Dado()
	{
		coordenada = new Coordenada();
		util = new Util();
		persistencia = new Persistencia();
	}

	public Dado(String codigoMACDispositivo, Date data, double velocidade, Coordenada coordenada)
	{
		this.codigoMACDispositivo = codigoMACDispositivo;
		this.data = data;
		this.velocidade = velocidade;
		this.coordenada = coordenada;

		util = new Util();
		persistencia = new Persistencia();
	}

	//Getters

	public String getCodigoMACDispositivo()
	{
		return (codigoMACDispositivo);
	}

	public Date getData()
	{
		return (data);
	}

	public double getVelocidade()
	{
		return (velocidade);
	}

	public Coordenada getCoordenada()
	{
		return (coordenada);
	}

	//Setters

	public void setCodigoMACDispositivo(String codigoMACDispositivo)
	{
		this.codigoMACDispositivo = codigoMACDispositivo;
	}

	public void setData(Date data)
	{
		this.data = data;
	}

	public void setVelocidade(double velocidade)
	{
		if (velocidade >= 0)
			this.velocidade = velocidade;
		else
			throw new IllegalArgumentException("velocidade deve ser maior que 0");
	}

	public void setCoordenada(Coordenada coordenada)
	{
		if (coordenada != null)
			this.coordenada = coordenada;
		else
			throw new IllegalArgumentException("coordenada não pode ser nula");
	}

	//Methods

	public boolean salvar()
	{
		String strTransacao;

		strTransacao = "INSERT INTO DADO_GPS ";
		strTransacao += "(";
		strTransacao += "DADO_CD_MAC_DISPOSITIVO, ";
		strTransacao += "DADO_VL_LATITUDE, ";
		strTransacao += "DADO_SG_ORIENTACAO_LATITUDE, ";
		strTransacao += "DADO_VL_LONGITUDE, ";
		strTransacao += "DADO_SG_ORIENTACAO_LONGITUDE, ";
		strTransacao += "DADO_VL_VELOCIDADE, ";
		strTransacao += "DADO_DT_CAPTURA, ";
		strTransacao += "DADO_DT_REGISTRO";
		strTransacao += ") ";
		strTransacao += "VALUES ";
		strTransacao += "('" + codigoMACDispositivo + "'";
		strTransacao += ", ";
		strTransacao += coordenada.getLatitude();
		strTransacao += ", ";
		strTransacao += "'" +coordenada.getOrientacaoLatitude() + "'";
		strTransacao += ", ";
		strTransacao += coordenada.getLongitude();
		strTransacao += ", ";
		strTransacao += "'" + coordenada.getOrientacaoLongitude() + "'";
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

	public static List<Dado> listar()
	{
		List<Dado> dados = new ArrayList<Dado>();
		Dado dado = null;
		List<String> objetos = new ArrayList<String>();
		String sqlConsulta;
		int quantidadeColunas = 0;

		sqlConsulta = "SELECT ";
		sqlConsulta += "DADO_CD_MAC_DISPOSITIVO, ";
		quantidadeColunas++;
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

		for (int i = 0; i < objetos.size(); ++i)
		{
			if (i % quantidadeColunas == 0)
			{
				dado = new Dado();
				dados.add(dado);
			}

			switch (i % quantidadeColunas)
			{
				case 0:
					dado.setCodigoMACDispositivo(objetos.get(i));
					break;
				case 1:
					dado.getCoordenada().setLatitude(util.alterarTipoParaDouble(objetos.get(i)));
					break;
				case 2:
					dado.getCoordenada().setOrientacaoLatitude(util.obterCaractereDeTexto(objetos.get(i), 0));
					break;
				case 3:
					dado.getCoordenada().setLongitude(util.alterarTipoParaDouble(objetos.get(i)));
					break;
				case 4:
					dado.getCoordenada().setOrientacaoLongitude(util.obterCaractereDeTexto(objetos.get(i), 0));
					break;
				case 5:
					dado.setVelocidade(util.alterarTipoParaDouble(objetos.get(i)));
					break;
				case 6:
					dado.setData(util.construirData(objetos.get(i)));
					break;
				case 7:					
					break;
			}
		}

		return (dados);
	}

	public void imprimir()
	{

	}
}