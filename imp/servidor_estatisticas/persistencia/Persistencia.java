package servidor_estatisticas.persistencia;

import java.util.List;
import java.util.ArrayList;
import java.util.Properties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class Persistencia
{
	private Connection conexao;
	private Properties propriedades;

	public Persistencia()
	{
		conexao = null;
		propriedades = new Properties();
		
		inicializar();
	}
	
	public boolean salvar(String strTransacao)
	{
		boolean estahSalvo = false;
		Connection conexao = null;

		propriedades.put("user", "coletor");
		propriedades.put("password", "ifes2017");

		try
		{
			conexao = DriverManager.getConnection("jdbc:mysql://127.0.0.1/MRVBIB?useSSL=false", propriedades);
			Statement transacao = conexao.createStatement();
			transacao.execute(strTransacao);
			
			estahSalvo = true;
		}
		catch (SQLException excecao)
		{
			// TRATAMENTO DE ERRO
		}
		finally
		{
			return (estahSalvo);
		}
	}

	public List<String> listar(String sqlConsulta)
	{
		List<String> objetos = new ArrayList<String>();
		Connection conexao = null;
		Properties propriedades = new Properties();

		try
		{
			conexao = DriverManager.getConnection("jdbc:mysql://127.0.0.1/MRVBIB?useSSL=false", propriedades);
			Statement consulta = conexao.createStatement();
			ResultSet conjuntoDados = consulta.executeQuery(sqlConsulta);

			while (conjuntoDados.next())
				for (int i = 1; i <= conjuntoDados.getMetaData().getColumnCount(); ++i)
					objetos.add(conjuntoDados.getString(i));
		}
		catch (SQLException excecao)
		{
			// TRATAMENTO DE ERRO
		}
		finally
		{
			return (objetos);
		}
	}

	private void inicializar()
	{
		propriedades.put("user", "servidor_estatisticas");
		propriedades.put("password", "mrvbib2018");

		try
		{
			Class classe = Class.forName("com.mysql.jdbc.Driver");
			classe.newInstance();
		}
		catch (ClassNotFoundException excecao)
		{
			// TRATAMENTO DE ERRO
		}
		catch (IllegalAccessException excecao)
		{
			// TRATAMENTO DE ERRO
		}
		catch (InstantiationException excecao)
		{
			// TRATAMENTO DE ERRO
		}
	}
}
