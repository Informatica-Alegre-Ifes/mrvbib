package coletor_gps;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.RaspiPin;

final class Led
{
	private static GpioController controlador;
	private static GpioPinDigitalOutput ledVerde;

	static
	{
		controlador = GpioFactory.getInstance();
		ledVerde = controlador.provisionDigitalOutputPin(RaspiPin.GPIO_02);
	}

	private Led()
	{		
	}

	public static void notificar(Status.Semaforo semaforoStatus)
	{
		switch (semaforoStatus)
		{
			case Verde:
				piscar(1000, 3500);
				break;
			case Amarelo:
				break;
			case Vermelho:
				break;
		}
	}

	private static void piscar(int duracao, int periodo)
	{
		ledVerde.blink(duracao, periodo);
	}
}
