class Monitor
{
	private Thread thread;
	private IStatusProdutor produtor;

	public Monitor(IStatusProdutor produtor)
	{
		this.produtor = produtor;
	}

	public void monitorar()
	{
		thread = new Thread(() -> 
		{
			try
			{
				Thread.sleep(2000);
				produtor.statusMudou(Status.Semaforo.Vermelho);
			}
			catch (InterruptedException excecao)
			{
				produtor.statusMudou(Status.Semaforo.Vermelho);
				Erro.registrar(excecao);
			}
		});
		thread.start();
	}

	public void finalizar()
	{
		try
		{
			thread.join();
		}
		catch (InterruptedException excecao)
		{
			produtor.statusMudou(Status.Semaforo.Vermelho);
			Erro.registrar(excecao);
		}
	}
}