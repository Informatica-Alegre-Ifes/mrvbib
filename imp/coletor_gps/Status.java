package coletor_gps;

class Status
{
	public enum Semaforo
	{
		Verde(0),
		Amarelo(1),
		Vermelho(2);
		
		private final int codigoSemaforo;
		
		Semaforo(int codigoSemaforo)
		{
			this.codigoSemaforo = codigoSemaforo;
		}

		public int getCodigoSemaforo()
		{
			return (codigoSemaforo);
		}
	};

	private GerenteStatus gerenteStatus;
	private Semaforo semaforo;

	public Status(GerenteStatus gerenteStatus)
	{
		this.gerenteStatus = gerenteStatus;
	}

	public GerenteStatus getGerenteStatus()
	{
		return (gerenteStatus);
	}

	public Semaforo getSemaforo()
	{
		return (semaforo);
	}

	public void setSemaforo(Status.Semaforo semaforo)
	{
		this.semaforo = semaforo;
	}
	
	public void notificarGerente(IStatusProdutor produtor)
	{
		gerenteStatus.atualizarStatusGlobal(produtor);
	}
}
