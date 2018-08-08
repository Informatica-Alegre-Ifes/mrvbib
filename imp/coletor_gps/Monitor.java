package coletor_gps;

class Monitor
{
	private volatile boolean emEspera;
	private Thread processoParalelo;
	private IStatusProdutor produtor;

	public Monitor(IStatusProdutor produtor)
	{
		this.produtor = produtor;
		emEspera = false;
	}

	public void monitorar()
	{
		processoParalelo = new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					Thread.sleep(3000);
					if (emEspera)
						produtor.statusMudou(Status.Semaforo.Vermelho);
				}
				catch (InterruptedException excecao)
				{
					produtor.statusMudou(Status.Semaforo.Vermelho);
					Erro.registrar(excecao);
				}
			}
		});
		emEspera = true;
		processoParalelo.start();
	}

	public void finalizar()
	{
		try
		{
			emEspera = false;
			processoParalelo.join();
		}
		catch (InterruptedException excecao)
		{
			produtor.statusMudou(Status.Semaforo.Vermelho);
			Erro.registrar(excecao);
		}
	}
}
