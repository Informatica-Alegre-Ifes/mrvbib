package coletor_gps;

import java.io.IOException;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.RaspiPin;

class Coletor
{
	public static void main(String[] args)
	{
		Serial serial = new Serial("/dev/ttyAMA0", "$GPRMC");
	
		final GpioController gpio = GpioFactory.getInstance();
		final GpioPinDigitalOutput ledPin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02);
	
		//try
		//{
		//	Runtime.getRuntime().exec("gpio write 2 1");
		//}
		//catch (IOException excecao)
		//{
		//}

		while (true)
		{
			Dado dado = serial.obterDadoGPS(28000);
			if (dado != null && dado.ehValido())
			{
				dado.salvar();
				ledPin.blink(1000, 15000);
			}
		}
	}
}
