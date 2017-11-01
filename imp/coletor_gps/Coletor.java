package coletor_gps;

class Coletor
{
	public static void main(String[] args)
	{
		Serial serial = new Serial("/dev/ttyAMA0", "$GPRMC");
		
		while (true)
		{
			Dado dado = serial.obterDadoGPS(28000);
			if (dado != null && dado.ehValido())
				dado.salvar();
		}
	}
}
