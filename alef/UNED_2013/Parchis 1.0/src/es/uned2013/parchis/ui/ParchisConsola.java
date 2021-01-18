package es.uned2013.parchis.ui;

import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;

import es.uned2013.parchis.Casilla;
import es.uned2013.parchis.Ficha;
import es.uned2013.parchis.Jugador;
import es.uned2013.parchis.Parchis;
import es.uned2013.parchis.Tablero;

/**
 * Gestiona la presentación de mensajes por consola e interacción con los jugadores
 * Al implementar de ParchisUI, por la propia estructura, es necesario que todos los métodos
 * estén definidos, incluso cuando no se utilicen.
 */
public class ParchisConsola implements ParchisUI {

	 private LineNumberReader lnReader = new LineNumberReader(new InputStreamReader(System.in));
	 private Parchis parchis= null;
	 
	 /**
	  * Constructor de la clase. Inicializa parchis
	  * @param parchis
	  */
	 public ParchisConsola(Parchis parchis){
		 this.parchis = parchis;
	 }
	
	 /*
	  * Todos los override en métodos en esta clase sobrecargan a los métodos de la clase
	  * ParchisUI.
	  */
	 /**
	  * Se encarga de mostrar el mensaje de entrada.
	  * @param mensaje Mensaje a mostrar
	  */
	@Override
	public void mostrarMensaje(String mensaje) {
		System.out.println(mensaje);

	}
	
	/**
	  * Se encarga de mostrar el mensaje de entrada y una cadena de entrada aparte.
	  * Este método se crea por la necesidad de independizar el interfaz ParchisUI de la 
	  * presentación final. Independizar el mensaje a mostrar con la forma en que se presenta.
	  * @param mensaje Mensaje a mostrar
	  * @param cadena cadena de caracteres a añadir al mensaje
	  */
	@Override
	public void mostrarMensajeString(String mensaje, String cadena){
		System.out.println(mensaje + cadena);
	}
	
	/**
	  * Se encarga de mostrar el mensaje de entrada y un entero aparte.
	  * Este método se crea por la necesidad de independizar el interfaz ParchisUI de la 
	  * presentación final. Independizar el mensaje a mostrar con la forma en que se presenta.
	  * @param mensaje Mensaje a mostrar
	  * @param numero entero a añadir al mensaje
	  */
	@Override
	public void mostrarMensajeInteger(String mensaje, int numero){
		System.out.println(mensaje + numero);
	}
	
	/**
	  * Gestiona la recepción de valores enteros por pantalla y posibles excepciones en la
	  * entrada.
	  * @param mensaje Mensaje a mostrar para pedir valor a introducir
	  * @return entero resultado válido de la comprobación
	  */
	@Override
	public int solicitarEntero(String mensaje) {
		int out = -1;
		
		System.out.print(mensaje);
		
		boolean valido = false;
		
		while(!valido){
			try{
			String leido = lnReader.readLine();
			out = Integer.parseInt(leido);
			valido = true;
			}catch(Exception e){
				mostrarMensaje(mensaje);
			}
		}
		
		return out;
	}
	
	/**
	  * Gestiona la recepción de cadenas por pantalla. Se utilizaría en el caso de solicitar
	  * el nombre de los jugadores por pantalla, por ejemplo.
	  * @param mensaje Mensaje a leer del usuario
	  * @return String resultado válido de la comprobación.
	  */
	public String solicitarCadena(String mensaje){
		String out= "";
		
		System.out.println(mensaje);
		
		try{
			out = lnReader.readLine();
		}
		catch(Exception e){
			//TODO:Mostrar error.
			mostrarMensaje("Error al leer.");
		}
		
		return out;
	}
	
	/**
	  * Muestra la tirada de cada jugador por pantalla.
	  * @param nombre Nombre del jugador
	  * @param tirada Valor de la tirada
	  */
	@Override
	public void mostrarTirada(String nombre, int tirada){
		mostrarMensaje(nombre + tirada);
	}
	
	/**
	  * Muestra la situación del tablero para todos los jugadores de forma tabulada.
	  * @param Tablero tablero de juego
	  */
	@Override
	public void mostrarTablero(Tablero tablero) {
		
		
		ArrayList<Jugador> jugadores = parchis.getJugadores();
		String cad=""; //cad almacena gradualmente la situación del tablero por jugador y ficha.
		
		cad+="\n";
		//Imprimo los nombres de los jugadores
		for(Jugador jugador: jugadores){
			cad += String.format("%11s", jugador.getNombre()) + "\t";
		}
		
		cad += "\n";
		//Imprimo un separador para cada jugador
		for(Jugador jugador: jugadores){
			cad=cad + "-----------" + "\t";
		}
		cad += "\n";
		
		//imprimo para cada jugador y ficha, los valores actuales de casilla
		for(int i=0;i<4;i++){
			for(Jugador jugador: jugadores){
				/*
				 * Como las casillas especiales de cada jugador son múltiplos de 100 o mayores que
				 * múltiplos de 100, utilizo división y resto para conocer la posición y 
				 * cambiar la definición a presentar:
				 * Para el jugador AMARILLO: casa=100; final=108, pasillo=101-107
				 * Si la división entre 100 es mayor que 1, esto quiere decir que hablamos de una
				 * casilla especial, con lo cual utilizo el resto para saber qué casilla 
				 * exactamente. Si el resto es cero, es casa; si el resto es 8, es final, cualquier
				 * otra es pasillo.
				 */
				if(jugador.getFichas().get(i).getCasillaActual() / 100 > 0){
					switch(jugador.getFichas().get(i).getCasillaActual() % 100){
					case 0:
						//saco por pantalla que es casilla casa
						cad+=String.format("%3d   %5s",	
								jugador.getFichas().get(i).getIdentificador(), "casa") + "\t";
						break;
					case 1: case 2: case 3: case 4: case 5: case 6: case 7:
						//saco por pantalla que es casilla pasillo
						cad+=String.format("%3d   %5s",	
								jugador.getFichas().get(i).getIdentificador(), "p"+jugador.getFichas().get(i).getCasillaActual() % 100) + "\t";
						break;
					case 8:
						//saco por pantalla que es casilla final
						cad+=String.format("%3d   %5s",	
								jugador.getFichas().get(i).getIdentificador(), "final") + "\t";
						break;
					}
				}
				else{
					//es una casilla normal, no especial
					cad+=String.format("%3d   %5s",	
							jugador.getFichas().get(i).getIdentificador(), jugador.getFichas().get(i).getCasillaActual())+ "\t";
				}
			}
			cad+="\n";
		}
		//añado leyenda
		cad+="\n(* p=pasillo/square, casa=home)";
		//presento resultado total por pantalla
		mostrarMensaje(cad);

	}
	
	/**
	  * Muestra la información de la ficha de entrada.
	  * @param Ficha ficha a sacar por pantalla
	  */
	@Override
	public void mostrarFicha(Ficha ficha) {
		mostrarMensaje(String.format("\n%3d   %5d",	ficha.getIdentificador(), ficha.getCasillaActual()));

	}

	/**
	 * Presenta un mensaje informando de cómo se mueve una ficha.
	 * @param Casilla_old destino
	 * @param Ficha ficha
	 */
	@Override
	public void moverFicha(Casilla destino, Ficha ficha) {
		// TODO Auto-generated method stub

	}
	/**
	 * Presenta un mensaje informando de la eliminación de una ficha por otro jugador.
	 * @param Ficha ficha
	 */
	@Override
	public void comerFicha(Ficha ficha) {
		// TODO Auto-generated method stub		

	}

}
