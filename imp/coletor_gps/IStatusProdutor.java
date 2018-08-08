package coletor_gps;

interface IStatusProdutor
{
	Status getStatus();

	void statusMudou(Status.Semaforo semaforoStatus);
}
