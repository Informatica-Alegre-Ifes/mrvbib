class Coordenada
{
	private double latitude;
	private char orientacaoLatitude;
	private double longitude;
	private char orientacaoLongitude;

	public Coordenada()
	{
	}

	public Coordenada(double latitude, char orientacaoLatitude, double longitude, char orientacaoLongitude)
	{
		this.latitude = latitude;
		this.orientacaoLatitude = orientacaoLatitude;
		this.longitude = longitude;
		this.orientacaoLongitude = orientacaoLongitude;
	}

	//Getters

	public double getLatitude()
	{
		return (latitude);
	}

	public char getOrientacaoLatitude()
	{
		return (orientacaoLatitude);
	}

	public double getLongitude()
	{
		return (longitude);
	}

	public char getOrientacaoLongitude()
	{
		return (orientacaoLongitude);
	}

	//Setters

	public void setLatitude(double latitude)
	{
		this.latitude = latitude;
	}

	public void setOrientacaoLatitude(char orientacaoLatitude)
	{
		this.orientacaoLatitude = orientacaoLatitude;
	}

	public void setLongitude(double longitude)
	{
		this.longitude = longitude;
	}

	public void setOrientacaoLongitude(char orientacaoLongitude)
	{
		this.orientacaoLongitude = orientacaoLongitude;
	}
}