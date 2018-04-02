package coletor_gps;

import java.util.List;
import java.util.ArrayList;

class Coletor
{
	private static List<Conexao> conexoes;
	private static String enderecoArquivo;
	private static String mensagemNMEA;
	private static int intervaloDados;
	private static double limiteDistanciaDados;
	private static char unidadeDistanciaDados;

	static
	{
		Runtime.getRuntime().addShutdownHook(new Thread()
		{
			public void run()
			{
				Led.pararTodos();
			}
		});
		inicializar();
	}

	private static void inicializar()
	{
		conexoes = new ArrayList<Conexao>();
		conexoes.add(new Conexao("nucleo", "nucleo#123."));
		conexoes.add(new Conexao("CAYO", "cayo220383"));

		enderecoArquivo = "/dev/ttyS0";
		mensagemNMEA = "$GPRMC";
		intervaloDados = 27000;
		limiteDistanciaDados = 0.5d;
		unidadeDistanciaDados = 'K';
	}

	public static void main(String[] args) throws InterruptedException
	{
		GerenteStatus gerenteStatus = GerenteStatus.obterInstancia();

		Serial serial = new Serial(enderecoArquivo, mensagemNMEA, new Status(gerenteStatus));
		Persistencia persistencia = new Persistencia(new Status(gerenteStatus));
		Util util = new Util(new Status(gerenteStatus));
		Rede rede = new Rede(conexoes, new Status(gerenteStatus));
		ColetorWebClient coletorWebClient = new ColetorWebClient(new Status(gerenteStatus));
		
		gerenteStatus.adicionar(serial);
		gerenteStatus.adicionar(persistencia);
		gerenteStatus.adicionar(util);
		gerenteStatus.adicionar(rede);
		gerenteStatus.adicionar(coletorWebClient);
	
		while (true)
		{
			Dado dado = new Dado(serial.obterMensagemGPS(), persistencia, util);
			if (dado != null && dado.ehValido())
			{
				dado.salvar();
				rede.setDadoReferencia(dado);
				if (dado.calcularDistanciaGeografica2D(rede.getDadoReferencia(), unidadeDistanciaDados) < limiteDistanciaDados && rede.conectar())
				{
					System.out.println("Descarregar dados para a base central!");
					Thread.sleep(2000);
					rede.desconectar();
				//	coletorWebClient.carregar(dado.listar());
				}
				Thread.sleep(intervaloDados);
			}
		}
	}
}
