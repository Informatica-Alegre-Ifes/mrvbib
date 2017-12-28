
package coletor_gps;

import java.util.List;
import java.util.ArrayList;

class Conexao
{
	private String ssid;
	private String senha;
	private String modoOperacao;
	private int canal;
	private int sinal;
	private int taxa;
	private String unidadeTaxa;
	private String protecao;
	private boolean estahAtiva;

	public Conexao(String ssid, String Senha)
	{
		this();
		this.ssid = ssid;
		this.senha = senha;
	}

	public Conexao()
	{
		estahAtiva = false;
	}

	public String getSSID()
	{
		return (ssid);
	}

	public String getSenha()
	{
		return (senha);
	}

	public Conexao construir(String infoConexao)
	{
		Conexao conexao = new Conexao();
		String[] camposInfoConexao = infoConexao.split(":");
		int indice = 0;

		for (String campoInfoConexao : camposInfoConexao)
		{
			switch (indice)
			{
				case 0:
					conexao.ssid = campoInfoConexao;
					break;
				case 1:
					conexao.modoOperacao = campoInfoConexao;
					break;
				case 2:
					conexao.canal = Integer.parseInt(campoInfoConexao);
					break;
				case 3:
					String[] strTaxaUnidade = campoInfoConexao.split(" ");
					conexao.taxa = Integer.parseInt(strTaxaUnidade[0]);
					conexao.unidadeTaxa = strTaxaUnidade[1];
					break;
				case 4:
					conexao.sinal = Integer.parseInt(campoInfoConexao);
					break;
				case 5:
					conexao.protecao = campoInfoConexao;
					break;
				case 6:
					conexao.estahAtiva = campoInfoConexao.toLowerCase().trim().equals("yes") ? true : false;
					break;
			}
			++indice;
		}

		return (conexao);
	}
}