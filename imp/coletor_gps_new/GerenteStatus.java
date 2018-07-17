package coletor_gps_new;

import java.util.List;
import java.util.ArrayList;
import java.io.IOException;

class GerenteStatus implements IStatusConsumidor
{
	private static GerenteStatus gerenteStatus;
	private List<IStatusProdutor> produtores;
	private Status.Semaforo semaforoStatusGlobal;

	private GerenteStatus()
	{
		produtores = new ArrayList<IStatusProdutor>();
		semaforoStatusGlobal = Status.Semaforo.Amarelo;
		notificar(semaforoStatusGlobal);
	}

	public static GerenteStatus obterInstancia()
	{
		if (gerenteStatus == null)
			return (new GerenteStatus());
		return (gerenteStatus);
	}

	public void atualizarStatusGlobal(IStatusProdutor produtor)
	{
		Status.Semaforo semaforoAtual = semaforoStatusGlobal;

		if (produtor.getStatus().getSemaforo() != semaforoStatusGlobal)
			semaforoStatusGlobal = produtor.getStatus().getSemaforo();

		for (IStatusProdutor _produtor : produtores)
			if (_produtor != produtor && _produtor.getStatus().getSemaforo().getCodigoSemaforo() > semaforoStatusGlobal.getCodigoSemaforo())
				semaforoStatusGlobal = _produtor.getStatus().getSemaforo();
		
		if (semaforoAtual != semaforoStatusGlobal)
			notificar(semaforoStatusGlobal);
	}
	
	public void adicionar(IStatusProdutor produtor)
	{
		produtores.add(produtor);
	}

	public void notificar(Status.Semaforo semaforoStatus)
	{
		Led.notificar(semaforoStatus);
	}
}
