package es.uned2014.oca.tablero;

import es.uned2014.oca.jugador.*;
import es.uned2014.oca.partida.*;
import es.uned2014.oca.excepciones.*;

import java.util.*;

/**
 * Se define una clase Tablero que representa el conjunto de Casillas necesarias para jugar a la Oca.
 * Su �nica variable es un conjunto ArrayList de Casillas.
 * Contiene adem�s los m�todos necesarios para describir las acciones que se llevan a cabo en el 
 * transcurso de la partida de la Oca.
 * @author	Alef UNED 2014
 * @version	Oca 2.0
 * @fecha 	Abril 2014
 */

public class Tablero {
	
	// Variable de tipo colecci�n (ArrayList) que representa un conjunto de objetos Casilla.
	private ArrayList<Casilla> arrayCasillas;
	
	/**
	 * M�todo constructor: se asigna a arrayCasillas un ArrayList de elementos Jugador de 63 de longitud,
	 * ya que en la Oca hay 63 casillas.
	 * @param null
	 * @return null
	 * @throws null
	 */
	public Tablero(){
		arrayCasillas = new ArrayList<Casilla>(63);
	}
	
	/**
	 * Se inicializa la colecci�n arrayCasillas, colocando en el arrayCasillas 63 objetos Casilla, desde
	 * Casilla(1) hasta Casilla(63).
	 * @param null
	 * @return null
	 * @throws null
	 */	
	public ArrayList<Casilla> inicializarTablero(){
		// Mediante un bucle, se crean y a�aden al ArrayList las 63 Casillas
		for(int i=1; i<=63; i++){
			// Se crea Casilla
			Casilla c = new Casilla(i);
			// Se a�ade la Casilla a la colecci�n
			arrayCasillas.add(c);
		}	
		return arrayCasillas;
	}
	
	/**
	 * Se calcula la casilla destino, como resultado de sumar el valor de la tirada del dado a la
	 * casilla actual del jugador.
	 * Se comprueba si es la casilla final. Si es as�, gana la partida y se termina la misma. Si no es as�,
	 * se analiza la casilla destino.
	 * @param Jugador activo, la Casilla en la que se encuentra, y la puntuaci�n obtenida con los dados.
	 * @return Se devuelve el objeto Casilla resultante de la tirada.
	 * @throws Si la puntuaci�n obtenida con los dados no est� entre 1 y 6, se lanza ClaseError.
	 */	
	public Casilla analizarTirada(Jugador j, Casilla c, int n){
		// Precondici�n: comprobar que el valor entero "n" est� entre 1 y 6 (dado)
		if(n<1 || n>6){
			throw new ClaseError("Valor de la tirada del dado incorrecto.");
		} else {
			// Se calcula la posici�n de la casilla de destino con posici�n de la Casilla + tirada del dado:
			int posicionDestino = c.getPosicion() + n;

			// Se comprueba si hay rebote en la casilla 63, y se calcula la posicionDestino
			if(posicionDestino > 63){
				posicionDestino = 2*63 - posicionDestino;
			}
			// Una vez calculado el rebote (si lo hay), se crea la Casilla destino.		
			Casilla casillaDestino = new Casilla(posicionDestino);		
			
			// Se analiza la Casilla destino
			return analizarCasilla(j, casillaDestino);	
		}
	}
	
	/**
	 * Se analiza la casilla destino, haciendo que se ejecuten las acciones necesarias dependiendo del tipo
	 * de casilla destino.
	 * @param Jugador activo y Casilla destino.
	 * @return Se devuelve el objeto Casilla resultante de la tirada.
	 * @throws null
	 */	
	private Casilla analizarCasilla(Jugador j, Casilla casillaDestino){
		
		// Se ejecutan las acciones propias de cada tipo de Casilla, en funci�n al tipo de Casilla.
		switch (casillaDestino.getTipo()){
		case OCA:
			return accionesOca(j, casillaDestino);
		case PUENTE:
			return accionesPuente(j, casillaDestino);	
		case DADOS:
			return accionesDados(j, casillaDestino);
		case POSADA:
		case POZO:
			return accionesTurnos(j, casillaDestino, 2);
		case CARCEL:
			return accionesTurnos(j, casillaDestino, 3);
		case LABERINTO:
			return accionesLaberinto(j, casillaDestino);
		case CALAVERA:
			return accionesCalavera(j, casillaDestino);
		default:
			return accionesNormal(j, casillaDestino);
		}
	}
	
	/**
	 * Se definen las acciones necesarias cuando se cae en una casilla tipo OCA:
	 * 		1� Se comprueba si es la Oca 63: en ese caso se termina la partida
	 * 		2� Si no es la 63, se desplaza a la siguiente Oca y repite turno el jugador.
	 * @param 	Jugador activo y Casilla destino.
	 * @return 	Se devuelve el objeto Casilla resultante de la tirada.
	 * @throws 	null
	 */	
	private Casilla accionesOca(Jugador j, Casilla casillaDestino){
		
		// Se comprueba si la casillaDestino es 63. En ese caso, ha ganado la partida.
		if (casillaDestino.getPosicion() == 63){
			// Se general el mensaje para el jugador
			System.out.println("\n�Felicidades " + j.getAliasJugador() + "! �Has ganado la partida!");
						
			// Se finaliza la partida
			Oca.setJuegoTerminado(true);
			
			// Se modifica la variable tiraOtraVez del Jugador j a false para que NO repita turno
			j.setTiraOtraVez(false);
				
			// El m�todo devuelve la Casilla destino
			return casillaDestino;
			
		} else{
			// Se define un valor entero i que corresponde a la posici�n de la Casilla dentro del ArrayList
			int i = casillaDestino.getPosicion() - 1;
			
			// Bucle para buscar la siguiente casilla tipo OCA:
			while (arrayCasillas.get(i+1).getTipo() != TipoCasilla.OCA){
				i++;
			}
			
			// Se detecta la siguiente OCA
			Casilla nuevoDestino = arrayCasillas.get(i+1);
			
			// Se modifica la variable tiraOtraVez del Jugador j a true para que repita turno
			j.setTiraOtraVez(true);

			// Se general el mensaje para el jugador
			String s = " has ca�do en la casilla " + casillaDestino.getPosicion() + ": \nDe oca (" + casillaDestino.getPosicion() + ") a oca "
					+ "(" + nuevoDestino.getPosicion() + "), y tiro porque me toca.";
			mensaje(j, s);
			
			// El m�todo devuelve la Casilla del nuevo destino	
			return nuevoDestino;
		}
	}
	
	/**
	 * Se definen las acciones necesarias cuando se cae en una casilla tipo PUENTE:
	 * se desplaza a la otra Casilla tipo PUENTE y repite turno el jugador.
	 * @param 	Jugador activo y Casilla destino.
	 * @return 	Se devuelve el objeto Casilla resultante de la tirada.
	 * @throws 	null
	 */	
	private Casilla accionesPuente(Jugador j, Casilla casillaDestino){

		// Bucle para buscar la otra casilla tipo PUENTE: se repite mientras NO sea puente o sea la casilla actual
		int i = 0;
		while (arrayCasillas.get(i).getTipo() != TipoCasilla.PUENTE || arrayCasillas.get(i).getPosicion() == casillaDestino.getPosicion()){
			i++;
		}
		
		// Se detecta el otro PUENTE
		Casilla nuevoDestino = arrayCasillas.get(i);
		
		// Se modifica la variable tiraOtraVez del Jugador j a true para que repita turno
		j.setTiraOtraVez(true);
		
		// Se general el mensaje para el jugador
		String s = " has ca�do en la casilla " + casillaDestino.getPosicion() + ": \nDe puente (" + casillaDestino.getPosicion() 
				+ ") a puente " + "(" + nuevoDestino.getPosicion() + "), y tiro porque me lleva la corriente.";
		mensaje(j, s);	
		
		// El m�todo devuelve la Casilla del nuevo destino	
		return nuevoDestino;

	}
	
	/**
	 * Se definen las acciones necesarias cuando se cae en una casilla tipo DADOS:
	 * se desplaza a la otra Casilla tipo DADOS y repite turno el jugador.
	 * @param 	Jugador activo y Casilla destino.
	 * @return 	Se devuelve el objeto Casilla resultante de la tirada.
	 * @throws 	null
	 */	
	private Casilla accionesDados(Jugador j, Casilla casillaDestino){
		
		// Bucle para buscar la otra casilla tipo DADOS: se repite mientras NO sea puente o sea la casilla actual
		int i = 0;
		while (arrayCasillas.get(i).getTipo() != TipoCasilla.DADOS || arrayCasillas.get(i).getPosicion() == casillaDestino.getPosicion()){
			i++;
		}
		
		// Se detecta la otra Casilla tipo DADOS
		Casilla nuevoDestino = arrayCasillas.get(i);
		
		// Se modifica la variable tiraOtraVez del Jugador j a true para que repita turno
		j.setTiraOtraVez(true);
		
		// Se general el mensaje para el jugador
		String s = " has ca�do en la casilla " + casillaDestino.getPosicion() + ": \nDe dado (" + casillaDestino.getPosicion() 
				+ ") a dado " + "(" + nuevoDestino.getPosicion() + "), y tiro porque me ha tocado.";
		mensaje(j, s);	
							
		// El m�todo devuelve la Casilla del nuevo destino	
		return nuevoDestino;
	}
	
	/**
	 * Se definen las acciones necesarias cuando se cae en una casilla tipo POSADA, POZO o CARCEL:
	 * Penalizaci�n con 2 o 3 turnos, dependiendo del tipo de Casilla.
	 * @param 	Jugador activo, Casilla destino y n�mero de turnos penalizados.
	 * @return 	Se devuelve el objeto Casilla resultante de la tirada.
	 * @throws 	null
	 */	
	private Casilla accionesTurnos(Jugador j, Casilla casillaDestino, int i){
		
		// Se a�aden los turnos de castigo al jugador activo.
		j.setCastigo(i);
		
		// Se modifica la variable tiraOtraVez del Jugador j a false para que NO repita turno
		j.setTiraOtraVez(false);
		
		// Se general el mensaje para el jugador
		String s = " has ca�do en la casilla " + casillaDestino.getPosicion() + ": " + casillaDestino.getTipo() + 
				"\nPierdes " + i + " turnos.";
		mensaje(j, s);	
							
		// Se crea la Casilla y se devuelve				
		return casillaDestino;
	}
	
	/**
	 * Se definen las acciones necesarias cuando se cae en una casilla tipo LABERINTO:
	 * Se desplaza a la Casilla 30.
	 * @param 	Jugador activo y Casilla destino.
	 * @return 	Se devuelve el objeto Casilla resultante de la tirada.
	 * @throws 	null
	 */	
	private Casilla accionesLaberinto(Jugador j, Casilla casillaDestino){
		
		Casilla nuevoDestino = arrayCasillas.get(29);
		
		// Se modifica la variable tiraOtraVez del Jugador j a false para que NO repita turno
		j.setTiraOtraVez(false);
		
		// Se general el mensaje para el jugador
		String s = " has ca�do en la casilla " + casillaDestino.getPosicion() + ": " + casillaDestino.getTipo() + 
				"\nDel laberinto al 30.";
		mensaje(j, s);			
		
		// El m�todo devuelve la Casilla del nuevo destino	
		return nuevoDestino;
	}
	
	/**
	 * Se definen las acciones necesarias cuando se cae en una casilla tipo CALAVERA:
	 * Se desplaza a la Casilla 1.
	 * @param 	Jugador activo y Casilla destino.
	 * @return 	Se devuelve el objeto Casilla resultante de la tirada.
	 * @throws 	null
	 */	
	private Casilla accionesCalavera(Jugador j, Casilla casillaDestino){
		
		Casilla nuevoDestino = arrayCasillas.get(0);
		
		// Se modifica la variable tiraOtraVez del Jugador j a false para que NO repita turno
		j.setTiraOtraVez(false);
		
		// Se general el mensaje para el jugador
		String s = " has ca�do en la casilla " + casillaDestino.getPosicion() + ": " + casillaDestino.getTipo() + 
				"\nVuelves a la casilla de inicio.";
		mensaje(j, s);		
		
		// El m�todo devuelve la Casilla del nuevo destino	
		return nuevoDestino;		
	}
	
	/**
	 * Se definen las acciones necesarias cuando se cae en una casilla tipo NORMAL.
	 * Simplemente, el turno termina sin acciones especiales.
	 * @param 	Jugador activo y Casilla destino.
	 * @return 	Se devuelve el objeto Casilla resultante de la tirada.
	 * @throws 	null
	 */	
	private Casilla accionesNormal(Jugador j, Casilla casillaDestino){	
		
		// Se modifica la variable tiraOtraVez del Jugador j a false para que NO repita turno
		j.setTiraOtraVez(false);
		
		// Se general el mensaje para el jugador
		String s = " has ca�do en la casilla " + casillaDestino.getPosicion() + ".";
		mensaje(j, s);	
		
		// No hay acciones especiales. Se decuelve la Casilla
		return casillaDestino;
	}
	
	/**
	 * M�todo que muestra en consola informaci�n para el jugador activo.
	 * @param Jugador activo y la cadena de texto que se quiere mostrar en consola.
	 * @return null
	 * @throws null
	 */	
	private void mensaje (Jugador j, String s){
		System.out.println(j.toString() + s);
	}
	
	/**
	 * M�todo toString que devuelve una cadena de texto que representa al objeto Tablero.
	 * Este String tendr� el formato "Tablero: [1, tipo NORMAL][2, tipo NORMAL]..." .
	 * @param null
	 * @return String que representa un Tablero
	 * @throws null
	 */	
	public String toString(){
		String s = "Tablero: \n";
		
		for (int i=0; i<63; i++){
			s += arrayCasillas.get(i).toString();
		}
		return s;
	}
}
