package modelo;

class Horario
{
	private Time saida;
	private boolean feriado;

	public Horario(Time saida, boolean feriado)
	{
		this.saida = saida;
		this.feriado = feriado;
		
	}

	public Time getSaida()
	{
		return (saida);
	}

	public boolean getFeriado()
	{
		return (feriado);
	}



}