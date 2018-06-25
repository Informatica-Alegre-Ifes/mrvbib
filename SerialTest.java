import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 INSTALAR: sudo apt-get install librxtx-java
 COMPILAR: javac SerialTest.java -classpath .:/usr/share/java/RXTXcomm.jar
 EXECUTAR: sudo java -Djava.library.path=/usr/lib/jni -cp .:/usr/share/java/RXTXcomm.jar SerialTest
 *
 */
public class SerialTest
{
    public SerialTest()
    {
        super();
    }
    
    public void conectar(String portName) throws Exception
    {
        CommPortIdentifier identificadorPorta = CommPortIdentifier.getPortIdentifier(portName);

        if (identificadorPorta.isCurrentlyOwned())
        {
            System.out.println("Porta em uso.");
        }
        else
        {
            CommPort portaComunicacao = identificadorPorta.open(this.getClass().getName(), 2000);
            
            if (portaComunicacao instanceof SerialPort)
            {
                SerialPort portaSerial = (SerialPort) portaComunicacao;
                portaSerial.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
                
                InputStream streamEntrada = portaSerial.getInputStream();
                portaSerial.addEventListener(new SerialReader(streamEntrada));
                portaSerial.notifyOnDataAvailable(true);

                OutputStream streamSaida = portaSerial.getOutputStream();
                String conteudo;
                byte[] bytes;

                conteudo = "AT" + "\r";
                streamSaida.write(conteudo.getBytes());
		streamSaida.flush();
                Thread.sleep(1000);
                conteudo = "ATE0" + "\r";
                streamSaida.write(conteudo.getBytes());
		streamSaida.flush();
                Thread.sleep(1000);
		//conteudo = "AT+CPIN=\"7078\"" + "\r\n";
                //streamSaida.write(conteudo.getBytes());
                //Thread.sleep(1000);
                conteudo = "AT+CMGF=1" + "\r";
                streamSaida.write(conteudo.getBytes());
		streamSaida.flush();
                Thread.sleep(1000);
                conteudo = "AT+CMGS=\"+5527999150088\"" + "\r";
                streamSaida.write(conteudo.getBytes());
		streamSaida.flush();
                Thread.sleep(1000);
                conteudo = "MRVBIB Test" + "\r";
                streamSaida.write(conteudo.getBytes());
		streamSaida.flush();                               
                //(new Thread(new SerialWriter(streamSaida))).start();
                portaSerial.close();
            }
            else
            {
                System.out.println("Apenas portas seriais são tratadas.");
            }
        }     
    }
    
    /**
     * Trata a entrada vinda da porta serial. No final de cada bloco é adicionado o caractere de nova linha.
     */
    public static class SerialReader implements SerialPortEventListener 
    {
        private InputStream streamEntrada;
        private byte[] memoria = new byte[1024];
        
        public SerialReader(InputStream streamEntrada)
        {
            this.streamEntrada = streamEntrada;
        }
        
        public void serialEvent(SerialPortEvent arg0)
        {
            int dado;
          
            try
            {
                int tam = 0;
                while ((dado = streamEntrada.read()) > -1)
                {
                    if (dado == '\n')
                        break;
                    memoria[tam++] = (byte) dado;
                }
                System.out.println(new String(memoria, 0, tam));
            }
            catch ( IOException e )
            {
                e.printStackTrace();
                System.exit(-1);
            }             
        }

    }

    /** */
    public static class SerialWriter implements Runnable 
    {
        OutputStream streamSaida;
        
        public SerialWriter(OutputStream streamSaida)
        {
            this.streamSaida = streamSaida;
        }
        
        public void run()
        {
            try
            {                
                int letra = 0;
                while ((letra = System.in.read()) > -1)
                    streamSaida.write(letra);   
            }
            catch ( IOException e )
            {
                e.printStackTrace();
                System.exit(-1);
            }            
        }
    }
    
    public static void main(String[] args)
    {
        try
        {
            (new SerialTest()).conectar("/dev/ttyS0");
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
    }


}
