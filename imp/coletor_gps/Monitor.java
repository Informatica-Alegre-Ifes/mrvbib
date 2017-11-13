package coletor_gps;

class Monitor
{
	private volatile boolean emEspera;
	private Thread thread;
	private IStatusProdutor produtor;

	public Monitor(IStatusProdutor produtor)
	{
		this.produtor = produtor;
		emEspera = false;
	}

	public void monitorar()
	{
		thread = new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					Thread.sleep(5000);
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
		thread.start();
	}

	public void finalizar()
	{
		try
		{
			emEspera = false;
			thread.join();
		}
		catch (InterruptedException excecao)
		{
			produtor.statusMudou(Status.Semaforo.Vermelho);
			Erro.registrar(excecao);
		}
	}
}
