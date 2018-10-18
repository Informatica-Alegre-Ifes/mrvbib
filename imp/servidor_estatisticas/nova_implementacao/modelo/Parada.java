package modelo;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

class Parada
{
	private int id;
	private String descricao;
	private Coordenada coordenada;
	private Date dataRegistro;

	private List<Linha> linhas;

	public Parada(String descricao, Coordenada coordenada, List<Linha> linhas)
	{
		this.descricao = descricao;
		this.coordenada = coordenada;
		this.linhas = linhas;
	}

	//Getters
	public int getId()
	{
		return (id);
	}

	public String getDescricao()
	{
		return (descricao);
	}

	public Coordenada getCoordenada()
	{
		return (coordenada);
	}

	public List<linha> getLinha()
	{
		return(linha);
	}

	public Date getDataRegistro()
	{
		return (dataRegistro);
	}

	//Methods

	public List<Parada> obter(Linha linha)
	{

	}

	public List<Parada> listar()
	{
		List<Parada> paradas = new ArrayList<Parada>();
		Parada parada = null;
		List<String> objetos = new ArrayList<String>();
		String sqlConsulta;
		int quantidadeColunas = 0;

		sqlConsulta = "SELECT ";
		sqlConsulta += "POPA_ID_PONTO_PARADA, ";
		quantidadeColunas++;
		sqlConsulta += "POPA_DC_PONTO_PARADA, ";
		quantidadeColunas++;
		sqlConsulta += "POPA_VL_LATITUDE, ";
		quantidadeColunas++;
		sqlConsulta += "POPA_VL_LONGITUDE, ";
		quantidadeColunas++;
		sqlConsulta += "POPA_DT_REGISTRO ";
		quantidadeColunas++;
		sqlConsulta += "FROM PONTO_PARADA;";

		objetos = persistencia.listar(sqlConsulta);

		for (int i = 0; i < objetos.size(); ++i)
		{
			if (i % quantidadeColunas == 0)
			{
				parada = new Parada();
				paradas.add(parada);
			}

			switch (i)
			{
				case 0:
					linha.setId(integer.parseInt(objetos.get(i)));
					break;
				case 1:
					linha.setDescricao(objetos.get(i));
					break;
				case 2:
					linha.setCoordenada(objetos.get(i));
					break;
				case 3:
					linha.setDataRegistro(Util.construirData(objetos.get(i)));
					break;
			}
		}


	}
}