package coletor_gps;

import java.util.List;
import java.util.ArrayList;
import java.util.Enumeration;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

class Rede implements IStatusProdutor
{
	private static final String MSG_CRIAR_SUCESSO = "created and activated on device";
	private static final String MSG_CONECTAR_SUCESSO = "successfully activated";
	private List<Conexao> conexoes;
	private Status status;
	private Dado dadoReferencia;

	public Rede(List<Conexao> conexoes, Status status)
	{
		this.conexoes = conexoes;
		this.status = status;
	}

	public boolean conectar()	
	{
		boolean conectou = false;
		List<Conexao> conexoesAtivas = listar();

		try
		{
			if (existeRedeSSIDs(conexoesAtivas))
			{
				if (estahConectado())
				{
					conectou = true;
					Led.acenderLedBranco();
				}
				else	
				{
					for (Conexao conexao : conexoes)
					{
						BufferedReader leitorDados = executarInstrucaoConsole("nmcli dev wifi con " + conexao.getSSID() + " password " + conexao.getSenha());

						String linhaDado;						
						if (((linhaDado = leitorDados.readLine()) != null) && (linhaDado.contains(MSG_CRIAR_SUCESSO) || linhaDado.contains(MSG_CONECTAR_SUCESSO)))
						{
							conectou = true;
							statusMudou(Status.Semaforo.Verde);
							Led.acenderLedBranco();
							break;
						}
					}
				}	
			}

			if (!conectou)
				statusMudou(Status.Semaforo.Amarelo);
		}
		catch (SocketException excecao)
		{
			statusMudou(Status.Semaforo.Vermelho);
			Erro.registrar(excecao);
		}
		catch (IOException excecao)
		{
			statusMudou(Status.Semaforo.Vermelho);
			Erro.registrar(excecao);
		}
		finally
		{
			return (conectou);
		}
	}

	public void desconectar()
	{
		BufferedReader leitorDados = executarInstrucaoConsole("nmcli --t --f \"CONNECTION\" dev");
		
		String linhaDado;

		try
		{
			if ((linhaDado = leitorDados.readLine()) != null)
			{
				executarInstrucaoConsole("nmcli con down id " + linhaDado);
				statusMudou(Status.Semaforo.Verde);
			}
		}
		catch (IOException excecao)
		{
			statusMudou(Status.Semaforo.Vermelho);
			Erro.registrar(excecao);
		}

		Led.apagarLedBranco();
	}

	public Dado getDadoReferencia()
	{
		return (dadoReferencia);
	}

	public void setDadoReferencia(Dado dado)
	{
		if (dadoReferencia == null)
			dadoReferencia = dado;
	}

	private boolean existeRedeSSIDs(List<Conexao> conexoesAtivas)
	{
		for (Conexao conexaoAtiva : conexoesAtivas)
			for (Conexao conexao : conexoes)
				if (conexaoAtiva.getSSID().equals(conexao.getSSID()))
					return (true);
		return (false);
	}

	private boolean estahConectado() throws IOException, SocketException
	{
		Enumeration<NetworkInterface> interfacesRede = NetworkInterface.getNetworkInterfaces();
		
		while (interfacesRede.hasMoreElements())
		{
			NetworkInterface interfaceRede = interfacesRede.nextElement();
			
			if (interfaceRede.isUp() && !interfaceRede.isLoopback())
				return (true);
		}

		return (false);
	}

	private List<Conexao> listar()
	{
		List<Conexao> conexoesAtivas = new ArrayList<Conexao>();
		BufferedReader leitorDados = executarInstrucaoConsole("nmcli --t --f \"SSID, MODE, CHAN, RATE, SIGNAL, SECURITY, ACTIVE\" dev wifi");
		
		String linhaDado;

		try
		{
			while ((linhaDado = leitorDados.readLine()) != null)
			{
				Conexao conexaoAtiva = new Conexao();
				conexaoAtiva.construir(linhaDado);
				conexoesAtivas.add(conexaoAtiva);
			}
			statusMudou(Status.Semaforo.Verde);
		}
		catch (NumberFormatException excecao)
		{
			statusMudou(Status.Semaforo.Amarelo);
			Erro.registrar(excecao);
		}
		catch (IOException excecao)
		{
			statusMudou(Status.Semaforo.Vermelho);
			Erro.registrar(excecao);
		}

		return (conexoesAtivas);
	}

	private BufferedReader executarInstrucaoConsole(String instrucao)	
	{
		ProcessBuilder contrutorProcessos = new ProcessBuilder("bash", "-c", instrucao);
		contrutorProcessos.redirectErrorStream(true);
		BufferedReader leitorDados = null;

		try
		{
			Process processo = contrutorProcessos.start();
			leitorDados = new BufferedReader(new InputStreamReader(processo.getInputStream()));
			statusMudou(Status.Semaforo.Verde);
		}
		catch (SocketException excecao)
		{
			statusMudou(Status.Semaforo.Vermelho);
			Erro.registrar(excecao);
		}
		catch (IOException excecao)
		{
			statusMudou(Status.Semaforo.Vermelho);
			Erro.registrar(excecao);
		}
		
		return (leitorDados);
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
