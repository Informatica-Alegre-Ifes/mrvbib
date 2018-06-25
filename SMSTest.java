import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

class SMSTest
{
	public static void main(String[] args)
	{
		FileWriter arquivoEscrita = null;
		BufferedWriter escritorDados = null;

		try
		{
			arquivoEscrita = new FileWriter("/dev/ttyS0");
			escritorDados = new BufferedWriter(arquivoEscrita);
			escritorDados.write("AT" + "\r\n");
			escritorDados.write("ATE0" + "\r\n");
			escritorDados.write("AT+CMGF=1" + "\r\n");
			escritorDados.write("AT+CMGS=\"+5527999150088\"" + "\r\n");
			escritorDados.write("MRVBIB Test" + "\r\n");
			escritorDados.flush();
		}
		catch (IOException excecao)
		{
			excecao.printStackTrace();
		}
		finally
		{
			try
			{
				if (arquivoEscrita != null)
					arquivoEscrita.close();
				if (escritorDados != null)
					escritorDados.close();
			}
			catch (IOException excecao)
			{
				excecao.printStackTrace();
			}
		}
	}
}