package coletor_gps;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.RaspiPin;

class Coletor
{
	public static void main(String[] args)
	{
		GerenteStatus gerenteStatus = GerenteStatus.obterInstancia();	
		Serial serial = new Serial("/dev/ttyAMA0", "$GPRMC", new Status(gerenteStatus));
		Persistencia persistencia = new Persistencia(new Status(gerenteStatus));
		Util util = new Util(new Status(gerenteStatus));
		
		gerenteStatus.adicionar(serial);
		gerenteStatus.adicionar(persistencia);
		gerenteStatus.adicionar(util);
	
		final GpioController gpio = GpioFactory.getInstance();
		final GpioPinDigitalOutput ledPin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02);
	
		while (true)
		{
			Dado dado = new Dado(serial.obterMensagemGPS(28000), persistencia, util);
			if (dado != null && dado.ehValido())
			{
				dado.salvar();
				ledPin.blink(1000, 3500);
			}
		}
	}
}
