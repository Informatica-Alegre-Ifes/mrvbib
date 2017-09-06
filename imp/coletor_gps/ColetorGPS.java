import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

class ColetorGPS
{
	public static void main(String[] args)
	{
		try
		{
			BufferedReader leitor = new BufferedReader(new FileReader("/dev/ttyAMA0"));
			String linha;
			while ((linha = leitor.readLine()) != null)
				if (linha.startsWith("$GPRMC"))
					System.out.println(linha);
		}
		catch (IOException excecao)
		{
			System.out.println(excecao);
		}
	}
}
