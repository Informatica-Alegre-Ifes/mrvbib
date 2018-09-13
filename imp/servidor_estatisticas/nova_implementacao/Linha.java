import java.util.List;
import java.util.ArrayList;

class Linha
{
	private String origem;
	private String destino;
	private List<Parada> parada; 

	public Linha(String origem, String destino, List<Parada> parada)
	{
		this.origem = origem;
		this.destino = destino;
		this.parada = parada;
	}

	//Getters

	public String getOrigem()
	{
		return (origem);
	}

	public String getDestino()
	{
		return (destino);
	}

	public List<Parada> getParada()
	{
		return(parada);
	}
}