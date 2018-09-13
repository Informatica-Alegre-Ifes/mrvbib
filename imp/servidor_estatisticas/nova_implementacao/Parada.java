import java.util.List;
import java.util.ArrayList;

class Parada
{
	private String descricao;
	private Coordenada coordenada;
	private List<Linha> linhas;

	public Parada(String descricao, Coordenada coordenada, List<Linha> linhas)
	{
		this.descricao = descricao;
		this.coordenada = coordenada;
		this.linhas = linhas;
	}

	//Getters

	public String getDescricao()
	{
		return (descricao);
	}

	public Coordenada getCoordenada()
	{
		return (coordenada);
	}

	public List<linha> getLinha()
	{
		return(linha);
	}

	//Methods

	public List<Parada> listar()
	{

	}
}