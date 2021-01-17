/**
 * Aplicación que ejecuta el Juego de la Oca, creando un nuevo objeto Oca y usando los
 * métodos creados para ello.
 * 
 * @author	Alef UNED 2014
 * @version	Oca 2.0
 * @fecha 	Abril 2014
 *
 */
// import es.uned2014.oca.partida.*; // importa todo el package es.uned2014.oca.partida

public class Driver {
	// Incio método main
	public static void main( String[] args) {
		// Crea objeto Oca
		Oca oca = new Oca();
		
		// Muestra el mensaje de bienvenida
		oca.bienvenidoOca();
		
		/*
		 * Pienso que antes de ejecutar el método configurarOca habría que haber creado el 
		 * socket del servidor. Ya que todas las preguntas a los jugadores se realizan a través de la 
		 * red. Lo que me hace dudar es como se realiza la primera petición desde el cliente ya que se necesita
		 * un servidor a la escucha. 
		 * 
		 */
		
		/*
		 * Para crear el socket del servidor se necesita crear un objeto de la clase Servidor y una vez creado
		 * ejecuar el método ejecutarServidor de dicha clase.
		 * 
		 */
		
		 /*
		  * Ya tenemos levantado un servidor con lo que ya las estaciones cliente pueden realizar peticiones.
		  *  El servidor se queda a la escucha.
		  * 
		  */
		
		
		// Configura la partida para el juego de la oca
		oca.configurarOca();
		
		// Inicia la partida
		oca.jugarOca();
		
		// Fin de la partida, con opción a jugar de nuevo
		oca.finOca();
		
	} // fin main

} // fin clase Driver
