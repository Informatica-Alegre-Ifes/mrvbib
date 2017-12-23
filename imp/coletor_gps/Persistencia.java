package coletor_gps;

import java.util.List;
import java.util.ArrayList;
import java.util.Properties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

class Persistencia implements IStatusProdutor
{
	private Status status;

	public Persistencia(Status status)
	{
		this.status = status;
	}
	
	public boolean salvar(String strTransacao)
	{
		boolean estahSalvo = false;
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
			
			estahSalvo = true;
			statusMudou(Status.Semaforo.Verde);
			Led.piscarLedAzul();
		}
		catch (SQLException excecao)
		{
			statusMudou(Status.Semaforo.Vermelho);
			Erro.registrar(excecao);
		}
		catch (ClassNotFoundException excecao)
		{
			statusMudou(Status.Semaforo.Vermelho);
			Erro.registrar(excecao);
		}
		catch (InstantiationException excecao)
		{
			statusMudou(Status.Semaforo.Vermelho);
			Erro.registrar(excecao);
		}
		catch (IllegalAccessException excecao)
		{
			statusMudou(Status.Semaforo.Vermelho);
			Erro.registrar(excecao);
		}
		finally
		{
			return (estahSalvo);
		}
	}

	public List<String> listar(String sqlConsulta)
	{

		Connection conexao = null;
		Properties propriedades = new Properties();
		List<String> objetos = new ArrayList<String>();

		propriedades.put("user", "coletor");
		propriedades.put("password", "ifes2017");

		try
		{
			Class classe = Class.forName("com.mysql.jdbc.Driver");
			classe.newInstance();

			conexao = DriverManager.getConnection("jdbc:mysql://127.0.0.1/MRVBIB?useSSL=false", propriedades);
			Statement consulta = conexao.createStatement();
			ResultSet conjuntoDados = consulta.executeQuery(sqlConsulta);

			while (conjuntoDados.next())
				for (int i = 1; i <= conjuntoDados.getMetaData().getColumnCount(); ++i)
					objetos.add(conjuntoDados.getString(i));

			statusMudou(Status.Semaforo.Verde);
		}
		catch (SQLException excecao)
		{
			statusMudou(Status.Semaforo.Vermelho);
			Erro.registrar(excecao);
		}
		catch (ClassNotFoundException excecao)
		{
			statusMudou(Status.Semaforo.Vermelho);
			Erro.registrar(excecao);
		}
		catch (InstantiationException excecao)
		{
			statusMudou(Status.Semaforo.Vermelho);
			Erro.registrar(excecao);
		}
		catch (IllegalAccessException excecao)
		{
			statusMudou(Status.Semaforo.Vermelho);
			Erro.registrar(excecao);
		}
		finally
		{
			return (objetos);
		}
	}

	public Status getStatus()
	{
		return (status);
	}

	public void statusMudou(Status.Semaforo semaforoStatus)
	{
		status.setSemaforo(semaforoStatus);
		status.notificarGerente(this);
	}
}
