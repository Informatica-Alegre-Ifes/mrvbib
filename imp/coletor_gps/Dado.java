package coletor_gpsa;

import java.text.SimpleDateFormat;

class Dado
{
	private SimpleDateFormat data;
	private double latitude;
	private	char orientacaoLatitude;
	private double longitude;
	private char orientacaoLongitude;

	public Dado(String mensagemGPS)
	{
		construir(mensagemGPS);
	}

	public SimpleDateFormat getData()
	{
		return (data);
	}

	public void setData(SimpleDateFormat data)
	{
		this.data = data;
	}

	public double getLatitude()
	{
		return (latitude);
	}

	public void setLatitude(double latitude)
	{
		this.latitude = latitude;
	}

	public char getOrientacaoLatitude()
	{
		return (orientacaoLatitude);
	}

	public void setOrientacaoLatitude()
	{
		this.orientacaoLatitude = orientacaoLatitude;
	}

	public double getLongitude()
	{
		return (longitude);
	}

	public void setLongitude(double longitude)
	{
		this.longitude = longitude;
	}

	public char getOrientacaoLongitude()
	{
		return (orientacaoLongitude);
	}

	public void setOrientacaoLongitude()
	{
		this.orientacaoLongitude = orientacaoLongitude;
	}

	private void construir(String mensagemGPS)
	{
		String[] mensagemSegregada = mensagemGPS.split(",");

		for (int i = 0; i < mensagemSegregada.length; ++i)
			switch (i)
			{
				case 1:
					
					break;
				case 2:
					
					break;
				case 3:
					
					break;
				case 4:
					
					break;
				case 5:
					
					break;
				case 6:
					
					break;
				case 7:
					
					break;
			}
	}
}
