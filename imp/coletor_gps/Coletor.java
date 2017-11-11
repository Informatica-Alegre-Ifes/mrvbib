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

	public static void main(String[] args)
	{
		GerenteStatus gerenteStatus = GerenteStatus.obterInstancia();

		Serial serial = new Serial("/dev/ttyAMA0", "$GPRMC", new Status(gerenteStatus));
		Persistencia persistencia = new Persistencia(new Status(gerenteStatus));
		Util util = new Util(new Status(gerenteStatus));
		
		gerenteStatus.adicionar(serial);
		gerenteStatus.adicionar(persistencia);
		gerenteStatus.adicionar(util);
	
		while (true)
		{
			Dado dado = new Dado(serial.obterMensagemGPS(28000), persistencia, util);
			if (dado != null && dado.ehValido())
				dado.salvar();
		}
	}
}
