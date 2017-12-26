package coletor_gps;

class Coletor
{
	static
	{
		Runtime.getRuntime().addShutdownHook(new Thread()
		{
			public void run()
			{
				Led.pararTodos();
			}
		});
	}

	public static void main(String[] args) throws InterruptedException
	{
		GerenteStatus gerenteStatus = GerenteStatus.obterInstancia();

		Serial serial = new Serial("/dev/ttyS0", "$GPRMC", new Status(gerenteStatus));
		Persistencia persistencia = new Persistencia(new Status(gerenteStatus));
		Util util = new Util(new Status(gerenteStatus));
		// Rede rede = new Rede(ssids, new Status(gerenteStatus));
		
		gerenteStatus.adicionar(serial);
		gerenteStatus.adicionar(persistencia);
		gerenteStatus.adicionar(util);
		// gerenteStatus.adicionar(rede);
	
		// while (true)
		// {
		// 	Dado dado = new Dado(serial.obterMensagemGPS(), persistencia, util);
		// 	if (dado != null && dado.ehValido())
		// 	{
		// 		dado.salvar();
		// 		Thread.sleep(27000);
		// 	}
		// }

		Dado dado = new Dado(serial.obterMensagemGPS(), persistencia, util);
		for (Dado _dado : dado.listar())
		{
			_dado.imprimir();
			System.out.println("\n-------------------------------------\n");
		}
	}
}
