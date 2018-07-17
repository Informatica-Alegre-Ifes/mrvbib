package coletor_gps_new;

interface IStatusConsumidor
{
	void adicionar(IStatusProdutor produtor);

	void notificar(Status.Semaforo semaforoStatus);
}
