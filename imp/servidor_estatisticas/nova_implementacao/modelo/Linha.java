package modelo;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

class Linha
{
	private int id;
	private String origem;
	private String destino;
	private Date dataRegistro;

	private List<Parada> parada;

	public Linha()
	{
		parada = new ArrayList<Parada>();
	}

	public Linha(String origem, String destino, List<Parada> parada)
	{
		this.origem = origem;
		this.destino = destino;
		this.parada = parada;
	}

	//Getters

	public int getId()
	{
		return (id);
	}

	public String getOrigem()
	{
		return (origem);
	}

	public String getDestino()
	{
		return (destino);
	}

	public List<Parada> getParada()
	{
		return(parada);
	}

	public Date getDataRegistro()
	{
		return (dataRegistro);
	}

	//Setters

	public void setId(int id)
	{
		this.id = id;
	}

	public void setOrigem(String origem)
	{
		this.origem = origem;
	}

	public void setDestino(String destino)
	{
		this.destino = destino;
	}

	public void setDataRegistro(Date dataRegistro)
	{
		this.dataRegistro = dataRegistro
	}

	public static List<Linha> obter(String codigo)
	{
		List<Linha> linhas = new ArrayList<Linha>();
		Linha linha = null;
		List<String> objetos = new ArrayList<String>();
		String sqlConsulta;
		int quantidadeColunas = 0;

		sqlConsulta = "SELECT ";
		sqlConsulta += "LINH_ID_LINHA, ";
		quantidadeColunas++;
		sqlConsulta += "LINH_DT_REGISTRO, ";
		quantidadeColunas++;		
		sqlConsulta += "LINH_NM_ORIGEM, ";
		quantidadeColunas++;
		sqlConsulta += "LINH_NM_DESTINO ";
		quantidadeColunas++;
		sqlConsulta += "FROM LINHA ";
		sqlConsulta += "WHERE LINH_ID_LINHA = " + codigo + ";";

		objetos = persistencia.listar(sqlConsulta);

		for (int i = 0; i < objetos.size(); ++i)
		{
			if (i % quantidadeColunas == 0)
			{
				linha = new Linha();
				linhas.add(linha);
			}

			switch (i % quantidadeColunas)
			{
				case 0:
					linha.setId(Integer.parseInt(objetos.get(i)));
					break;
				case 1:
					linha.dataRegistro = Util.construirData(objetos.get(i));
					break;
				case 2:
					linha.origem = objetos.get(i);
					break;
				case 3:
					linha.destino = objetos.get(i);
					break;
			}
		}

		return (linhas);
	}

	public static List<Linha> listar()
	{
		List<Linha> linhas = new ArrayList<Linha>();
		Linha linha = null;
		List<String> objetos = new ArrayList<String>();
		String sqlConsulta;
		int quantidadeColunas = 0;

		sqlConsulta = "SELECT ";
		sqlConsulta += "LINH_ID_LINHA, ";
		quantidadeColunas++;
		sqlConsulta += "LINH_DT_REGISTRO, ";
		quantidadeColunas++;		
		sqlConsulta += "LINH_NM_ORIGEM, ";
		quantidadeColunas++;
		sqlConsulta += "LINH_NM_DESTINO ";
		quantidadeColunas++;
		sqlConsulta += "FROM LINHA";

		objetos = persistencia.listar(sqlConsulta);

		for (int i = 0; i < objetos.size(); ++i)
		{
			if (i % quantidadeColunas == 0)
			{
				linha = new Linha();
				linhas.add(linha);
			}

			switch (i % quantidadeColunas)
			{
				case 0:
					linha.setId(integer.parseInt(objetos.get(i)));
					break;
				case 1:
					linha.setDataRegistro(Util.construirData(objetos.get(i)));
					break;
				case 2:
					linha.setOrigem(objetos.get(i));
					break;
				case 3:
					linha.setDestino(objetos.get(i));
					break;

					
			}
		}

		return (linhas);
	}
}