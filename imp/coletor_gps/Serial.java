package coletor_gps;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;

class Serial
{
	private String mensagemNMEA;
	private BufferedReader leitor;
	
	public Serial(String arquivo, String mensagemNMEA)
	{
		this.mensagemNMEA = mensagemNMEA;

		inicializar(arquivo);
	}
	
	public Dado obterDadoGPS()
	{
		try
		{
			String linha;

			do
			{
				linha = leitor.readLine();
			} while (linha == null || linha == "" || !linha.startsWith(mensagemNMEA));

			return (new Dado(linha));
		}
		catch (IOException excecao)
		{
			System.out.println(excecao);
			return (null);
		}
	}
	
	private void inicializar(String arquivo)
	{
		try
		{
			leitor = new BufferedReader(new FileReader(arquivo));
		}
		catch (FileNotFoundException excecao)
		{
			System.out.println(excecao);
		}
	}
}
