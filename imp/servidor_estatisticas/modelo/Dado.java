package servidor_estatisticas.modelo;

import servidor_estatisticas.infraestrutura.Util;
import servidor_estatisticas.persistencia.Persistencia;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="dado")
@XmlAccessorType(XmlAccessType.NONE)
public class Dado
{
	@XmlAttribute
	private String codigoMACDispositivo;
	@XmlAttribute
	private Date data;
	@XmlAttribute
	private char status;
	@XmlAttribute
	private double latitude;
	@XmlAttribute
	private	char orientacaoLatitude;
	@XmlAttribute
	private double longitude;
	@XmlAttribute
	private char orientacaoLongitude;
	@XmlAttribute
	private double velocidade;

	private Persistencia persistencia;
	private Util util;
	
	public Dado()
	{
		persistencia = new Persistencia();
		util = new Util();
	}

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
					dado.codigoMACDispositivo = objetos.get(i);
					break;
				case 1:
					dado.latitude = util.alterarTipoParaDouble(objetos.get(i));
					break;
				case 2:
					dado.orientacaoLatitude = util.obterCaractereDeTexto(objetos.get(i), 0);
					break;
				case 3:
					dado.longitude = util.alterarTipoParaDouble(objetos.get(i));
					break;
				case 4:
					dado.orientacaoLongitude = util.obterCaractereDeTexto(objetos.get(i), 0);
					break;
				case 5:
					dado.velocidade = util.alterarTipoParaDouble(objetos.get(i));
					break;
				case 6:
					dado.data = util.construirData(objetos.get(i));
					break;
				case 7:					
					break;
			}
		}

		return (dados);
	}

	public void imprimir()
	{
		String strDado;

		strDado = "Dispositivo: " + codigoMACDispositivo + "\n";
		strDado += "Data/Hora: " + util.obterTexto(data) + "\n";
		strDado += "Latitude: " + latitude + "\n";
		strDado += "Orientação latitude: " + orientacaoLatitude + "\n";
		strDado += "Longitude: " + longitude + "\n";
		strDado += "Orientação longitude: " + orientacaoLongitude + "\n";
		strDado += "Velocidade: " + velocidade;

		System.out.println(strDado);
	}
}