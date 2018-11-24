package coletor_gps;

import java.util.List;
import java.util.ArrayList;

class Principal
{
	private static List<Conexao> conexoes;
	private static String porta;
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

	public static void main(String[] args) throws InterruptedException
	{
		GerenteStatus gerenteStatus = GerenteStatus.obterInstancia();

		Serial serial = Serial.obterInstancia(porta, new Status(gerenteStatus));
		Persistencia persistencia = new Persistencia(new Status(gerenteStatus));
		Util util = new Util(new Status(gerenteStatus));
		Rede rede = new Rede(conexoes, util, new Status(gerenteStatus));
		ColetorWebClient coletorWebClient = new ColetorWebClient(new Status(gerenteStatus));
		ColetorGPS coletorGPS = new ColetorGPS(serial, sentencaNMEA, new Status(gerenteStatus));
		ComunicacaoMovel comunicacaoMovel = new ComunicacaoMovel(serial, new Status(gerenteStatus));
		
		gerenteStatus.adicionar(serial);
		gerenteStatus.adicionar(persistencia);
		gerenteStatus.adicionar(util);
		gerenteStatus.adicionar(rede);
		gerenteStatus.adicionar(coletorWebClient);
		gerenteStatus.adicionar(coletorGPS);
		gerenteStatus.adicionar(comunicacaoMovel);
	
		while (true)
		{
			Dado dado = new Dado(coletorGPS.obterMensagem(), persistencia, util);
			if (dado != null && dado.ehValido())
			{
				dado.salvar();
				rede.setDadoReferencia(dado);
				if (dado.calcularDistanciaGeografica2D(rede.getDadoReferencia()) < minimaDistanciaCoordenadas && rede.conectar())
				{
					coletorWebClient.carregar(dado.listar());
					rede.desconectar();
				}
				else
					comunicacaoMovel.enviarMensagemHTTP("zap.vivo.com.br", "201.140.233.5", 8080, "cadastrodadogps.php", dado.gerarHTTPQueryString());
				
				Thread.sleep(intervaloMedicao);
			}
		}
	}

	private static void inicializar()
	{
		conexoes = new ArrayList<Conexao>();
		conexoes.add(new Conexao("NUCLEO", "nucleo#123."));
		conexoes.add(new Conexao("CAYO", "abc@123."));

		porta = "/dev/ttyS0";
		sentencaNMEA = "$GPRMC";
		intervaloMedicao = 27000;
		minimaDistanciaCoordenadas = 200;
	}
}