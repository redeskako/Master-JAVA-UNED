/**
 * Aplicación que ejecuta el Juego de la Oca, creando un nuevo objeto Oca y usando los
 * métodos creados para ello.
 * 
 * @author	Alef UNED 2014
 * @version	Oca 2.0
 * @fecha 	Abril 2014
 *
 */
import es.uned2014.oca.partida.*; // importa todo el package es.uned2014.oca.partida

public class Driver {
	// Incio método main
	public static void main( String[] args) {
		// Crea objeto Oca
		Oca oca = new Oca();
		
		// Muestra el mensaje de bienvenida
		oca.bienvenidoOca();
		
		// Configura la partida para el juego de la oca
		oca.configurarOca();
		
		// Inicia la partida
		oca.jugarOca();
		
		// Fin de la partida, con opción a jugar de nuevo
		oca.finOca();
		
	} // fin main

} // fin clase Driver
