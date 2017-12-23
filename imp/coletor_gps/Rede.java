import java.util.List;
import java.util.ArrayList;
import java.util.Enumeration;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

class Rede
{
	private String ssid;
	private String modoOperacao;
	private int canal;
	private int sinal;
	private int taxa;
	private String unidadeTaxa;
	private String protecao;
	private boolean estahAtiva;

	private static final String CONEXAO_SUCESSO = "successfully activated";
	private List<String> ssids;

	public Rede(List<String> ssids)
	{
		this.ssids = ssids;
	}

	public boolean conectar()
	{
		List<Rede> redes = listar();

		if (existeRedeSSIDs(redes))
		{
			if (!estahConectado())
			{
				BufferedReader leitorDados = executarInstrucaoConsole("nmcli dev wifi con XXXXXX password XXXXXX");

				String linhaDado;
				if (((linhaDado = leitorDados.readLine()) == null) || !linhaDado.contains(CONEXAO_SUCESSO))
					return (false);
			}

			return (true);			
		}

		return (false);
	}

	private boolean existeRedeSSIDs(List<Rede> redes)
	{
		for (Rede rede : redes)
			for (String ssid : ssids)
				if (rede.ssid.equals(ssid))
					return (true);
		return (false);
	}

	private boolean estahConectado()
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

	private List<Rede> listar() throws IOException
	{
		List<Rede> redes = new ArrayList<Rede>();
		BufferedReader leitorDados = executarInstrucaoConsole("nmcli --t --f \"SSID, MODE, CHAN, RATE, SIGNAL, SECURITY, ACTIVE\" dev wifi");
		
		String linhaDado;

		while ((linhaDado = leitorDados.readLine()) != null)
		{
			Rede rede = construir(linhaDado);
			redes.add(rede);
		}

		return (redes);
	}

	private Rede construir(String infoRede)
	{
		Rede rede = new Rede(ssids);
		String[] camposInfoRede = infoRede.split(":");
		int indice = 0;

		for (String campoInfoRede : camposInfoRede)
		{
			switch (indice)
			{
				case 0:
					rede.ssid = campoInfoRede;
					break;
				case 1:
					rede.modoOperacao = campoInfoRede;
					break;
				case 2:
					rede.canal = Integer.parseInt(campoInfoRede);
					break;
				case 3:
					String[] strTaxaUnidade = campoInfoRede.split(" ");
					rede.taxa = Integer.parseInt(strTaxaUnidade[0]);
					rede.unidadeTaxa = strTaxaUnidade[1];
					break;
				case 4:
					rede.sinal = Integer.parseInt(campoInfoRede);
					break;
				case 5:
					rede.protecao = campoInfoRede;
					break;
				case 6:
					rede.estahAtiva = campoInfoRede.toLowerCase().trim().equals("yes") ? true : false;
					break;
			}
			++indice;
		}

		return (rede);
	}

	private BufferedReader executarInstrucaoConsole(String instrucao) throws SocketException, IOException
	{
		ProcessBuilder contrutorProcessos = new ProcessBuilder("bash", "-c", instrucao);
		contrutorProcessos.redirectErrorStream(true);
		Process processo = contrutorProcessos.start();
		BufferedReader leitorDados = new BufferedReader(new InputStreamReader(processo.getInputStream()));
		
		return (leitorDados);
	}
}