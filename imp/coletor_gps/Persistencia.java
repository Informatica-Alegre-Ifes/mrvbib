package coletor_gps;

import java.util.Properties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

class Persistencia
{
	public static boolean salvar(String strTransacao)
	{
		Connection conexao = null;
		Properties propriedades = new Properties();

		propriedades.put("user", "coletor");
		propriedades.put("password", "ifes2017");

		try
		{
			Class classe = Class.forName("com.mysql.jdbc.Driver");
			classe.newInstance();

			conexao = DriverManager.getConnection("jdbc:mysql://127.0.0.1/MRVBIB?useSSL=false", propriedades);
			Statement transacao = conexao.createStatement();

			transacao.execute(strTransacao);
			
			return (true);
		}
		catch (SQLException excecao)
		{
			System.out.println(excecao);
			return (false);
		}
		catch (ClassNotFoundException excecao)
		{
			System.out.println(excecao);
			return (false);
		}
		catch (InstantiationException excecao)
		{
			System.out.println(excecao);			
			return (false);
		}
		catch (IllegalAccessException excecao)
		{
			System.out.println(excecao);
			return (false);
		}
	}
}