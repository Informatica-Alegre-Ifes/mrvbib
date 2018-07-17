package coletor_gps_new;

import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

import java.io.IOException;

final class Erro
{
	private static Logger registrador;
	private static FileHandler manipuladorArquivo;
	private static SimpleFormatter formatador = new SimpleFormatter();
	
	static
	{
		registrador = Logger.getLogger("Log de Erros");
		formatador = new SimpleFormatter();

		try
		{
			manipuladorArquivo = new FileHandler("log.log");
		}
		catch (IOException excecao)
		{
			System.out.println(excecao);
		}
		
		manipuladorArquivo.setFormatter(formatador);
		registrador.addHandler(manipuladorArquivo);
	}

	private Erro()
	{
	}
	
	public static void registrar(Exception excecao)
	{
		String mensagem;
	
		mensagem = excecao.getClass().getSimpleName() + "\n";
		mensagem += "Causa: " + excecao.getMessage() + "\n";
		mensagem += "Curso:\n" + excecao.toString();

		registrador.info(mensagem);
	}
}
