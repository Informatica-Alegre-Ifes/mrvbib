package coletor_gps;

import java.util.List;
import java.util.ArrayList;

class Coletor
{
	private static List<Conexao> conexoes;
	private static String portaSerial;
	private static String sentencaNMEA;
	private static int intervaloMedicao;
	private static int minimaDistanciaCoordenadas;

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
		conexoes.add(new Conexao("NUCLEO", "nucleo#123."));
		conexoes.add(new Conexao("CAYO", "abc@123."));

		portaSerial = "/dev/ttyS0";
		sentencaNMEA = "$GPRMC";
		intervaloMedicao = 27000;
		minimaDistanciaCoordenadas = 300;
	}

	public static void main(String[] args) throws InterruptedException
	{
		GerenteStatus gerenteStatus = GerenteStatus.obterInstancia();

		Serial serial = new Serial(portaSerial, sentencaNMEA, new Status(gerenteStatus));
		Persistencia persistencia = new Persistencia(new Status(gerenteStatus));
		Util util = new Util(new Status(gerenteStatus));
		Rede rede = new Rede(conexoes, new Status(gerenteStatus));
		ColetorWebClient coletorWebClient = new ColetorWebClient(new Status(gerenteStatus));
		ComunicacaoMovel comunicacaoMovel = new ComunicacaoMovel(new Status(gerenteStatus));
		
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
				if (dado.calcularDistanciaGeografica2D(rede.getDadoReferencia()) < minimaDistanciaCoordenadas && rede.conectar())
				{
					coletorWebClient.carregar(dado.listar());
					rede.desconectar();
					comunicacaoMovel.enviarMensagemSMS("+5527999150088", "MRVBIB - Teste");
				}
				Thread.sleep(intervaloMedicao);
			}
		}
	}
}