package coletor_gps_new;

interface IStatusProdutor
{
	Status getStatus();

	void statusMudou(Status.Semaforo semaforoStatus);
}
