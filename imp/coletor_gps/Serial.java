package coletor_gps;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;

class Serial
{
	private String mensagemNMEA;
	private String enderecoArquivo;
	private BufferedReader leitor;
	
	public Serial(String enderecoArquivo, String mensagemNMEA)
	{
		this.mensagemNMEA = mensagemNMEA;
		this.enderecoArquivo = enderecoArquivo;
	}
	
	public Dado obterDadoGPS(int periodo)
	{
		try
		{
			String linha;

			linha = "";
			inicializar();
			do
				linha = leitor.readLine();
			while (linha == null || !linha.startsWith(mensagemNMEA));
			Thread.sleep(periodo);
			finalizar();

			return (new Dado(linha));
		}
		catch (IOException excecao)
		{
			System.out.println(excecao);
			return (null);
		}
		catch (InterruptedException excecao)
		{
			System.out.println(excecao);
			return (null);
		}
	}

	private void inicializar()
	{
		try
		{
			leitor = new BufferedReader(new FileReader(enderecoArquivo));
		}
		catch (FileNotFoundException excecao)
		{
			System.out.println(excecao);
		}
	}

	private void finalizar()
	{
		try
		{
			leitor.close();
			leitor = null;
		}
		catch (IOException excecao)
		{
			System.out.println(excecao);
		}
	}
}

