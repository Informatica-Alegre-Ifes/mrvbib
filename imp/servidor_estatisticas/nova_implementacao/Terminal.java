import java.util.Time;
import java.util.List;
import java.util.ArrayList;

class Terminal extends Parada
{
	private Time saida;

	public Terminal(String descricao, Coordenada coordenada, List<Linha> linhas, Time saida)
	{
		this.saida = saida;
		super(descricao, coordenada, linhas);
	}

	//Getters

	public Time getSaida()
	{
		return (saida);
	}
}