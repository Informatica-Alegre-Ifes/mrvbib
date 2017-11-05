package coletor_gps;

interface IStatusConsumidor
{
	void adicionar(IStatusProdutor produtor);

	void notificar(Status status);
}
