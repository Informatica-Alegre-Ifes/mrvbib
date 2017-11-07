package coletor_gps;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.RaspiPin;

final class Led
{
	private static GpioController controlador;
	private static GpioPinDigitalOutput ledAzul;
	private static GpioPinDigitalOutput ledVerde;
	private static GpioPinDigitalOutput ledAmarelo;
	private static GpioPinDigitalOutput ledVermelho;

	static
	{
		controlador = GpioFactory.getInstance();
		ledAzul = controlador.provisionDigitalOutputPin(RaspiPin.GPIO_02);
		ledVerde = controlador.provisionDigitalOutputPin(RaspiPin.GPIO_04);
		ledAmarelo = controlador.provisionDigitalOutputPin(RaspiPin.GPIO_05);
		ledVermelho = controlador.provisionDigitalOutputPin(RaspiPin.GPIO_06);
	}

	private Led()
	{		
	}

	public static void notificar(Status.Semaforo semaforoStatus)
	{
		ledVerde.low();
		ledAmarelo.low();
		ledVermelho.low();

		switch (semaforoStatus)
		{
			case Verde:
				ledVerde.high();
				break;
			case Amarelo:
				ledAmarelo.blink(1000, 60500);
				break;
			case Vermelho:
				ledVermelho.blink(500, 60500);
				break;
		}
	}

	// Método temporário utilizado para notificar testes de inserção de dados
	public static void piscarLedAzul()
	{
		ledAzul.blink(1000, 1500);
	}
}
