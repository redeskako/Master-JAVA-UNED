package es.uned.master.java.oca.drivers;



import es.uned.master.java.oca.cliente.ClienteGui;


/**
 * @author	Alef UNED 2014
 * @version	Oca 3.0
 * @fecha 	Mayo 2014
 * Clase Driver Cliente Traspaso a Clase Cliente GUI
 */


public class DriverCliente {

	public static void main(String[] args) {
		// Dani - Se envían las Medidas de la Ventana a la Clase Gui
        ClienteGui cliente1 = new ClienteGui(30,80,700,500);
	}
}