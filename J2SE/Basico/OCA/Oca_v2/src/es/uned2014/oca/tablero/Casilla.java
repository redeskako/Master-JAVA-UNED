package es.uned2014.oca.tablero;

import es.uned2014.oca.excepciones.*;

/**
 * Se define una clase Casilla que representa cada una de las casillas que forman el tablero de la oca.
 * Sus variables son un valor entero que indica la posici�n de la casilla (de 1 a 63) y un tipo enum que
 * representa el tipo de casilla.
 * @author	Alef UNED 2014
 * @version	Oca 2.0
 * @fecha 	Abril 2014
 */

public class Casilla {
	
	// Variable de valor entero que representa la posici�n de la Casilla en el Tablero
	private int posicion;
	// Variable enum que representa el tipo de Casilla: NORMAL, OCA, PUENTE, DADOS, POSADA, POZO, CARCEL, LABERINTO, CALAVERA;
	private TipoCasilla tipo;

	/**
	 * M�todo constructor. Recibe como par�metro un valor entero que se asignar� a la posici�n 
	 * que tiene la Casilla en el Tablero. 
	 * Se deduce el tipo de casilla que es en funci�n de la posici�n de la casilla.
	 * Se comprueba si la posici�n est� entre 1 y 63. Si no es as�, se lanza error.
	 * @param Valor entero que se asigna a la variable posici�n si se encuentra entre 1 y 63.
	 * @return null
	 * @throws Se lanza error si la posici�n de la casilla no es v�lida.
	 */
	public Casilla (int i){
		// Precondici�n: que el valor que se va a asignar a la variable posicion est� entre 1 y 63:
		if(i<1 || i>63){
			throw new ClaseError("Posici�n de la casilla no comprendida entre 1 y 63");
		} else {
			posicion = i;
		}
		
		// C�lculo del tipo de casilla en funci�n de la posici�n.
		switch (posicion) {
		case 5:
		case 9:
		case 14:
		case 18:
		case 23:
		case 27:
		case 32:
		case 36:
		case 41:
		case 45:
		case 50:
		case 54:
		case 59:
		case 63:
			tipo = TipoCasilla.OCA;
			break;
		case 6:
		case 12:
			tipo = TipoCasilla.PUENTE;
			break;
		case 26:
		case 53:
			tipo = TipoCasilla.DADOS;
			break;
		case 19:
			tipo = TipoCasilla.POSADA;
			break;
		case 31:
			tipo = TipoCasilla.POZO;
			break;
		case 52:
			tipo = TipoCasilla.CARCEL;
			break;
		case 42:
			tipo = TipoCasilla.LABERINTO;
			break;
		case 58:
			tipo = TipoCasilla.CALAVERA;
			break;
		default:
			tipo = TipoCasilla.NORMAL;	
		}	
	}
	
	/**
	 * Getter de la variable posici�n.
	 * @param null
	 * @return Devuelve el valor entero de la variable posicion
	 * @throws null
	 */
	public int getPosicion() {
		return posicion;
	}
	
	/**
	 * Establece un nuevo valor entero para la variable posicion.
	 * @param valor entero i que se asignar� a la variable posicion
	 * @return null
	 * @throws ClaseError en caso de que el valor no est� comprendido entre 1 y 63
	 */
	public void setPosicion(int i) {
		// Antes de asignar el valor a la variable posicion, se comprueba que est� en el rango 1-63.
		if (i<1 || i>63){
			throw new ClaseError("El valor de la posici�n de Casilla tiene que estar comprendido entre 1 y 63."); 
		}else{
			this.posicion = i;
		}
	}
	
	/**
	 * Getter de la variable tipo (TipoCasilla).
	 * @param null
	 * @return Devuelve el valor TipoCasilla de la variable tipo
	 * @throws Se lanza error si la posici�n de la casilla no es v�lida.
	 */
	public TipoCasilla getTipo() {
		return tipo;
	}


	/**
	 * M�todo toString que devuelve una cadena de texto que representa al objeto Casilla.
	 * Este String tendr� el formato [5, tipo OCA].
	 * @param null
	 * @return String que representa una Casilla [posicion, tipo TipoCasilla]
	 * @throws null
	 */
	public String toString(){
		return "[" + posicion + ", tipo " + tipo + "]";
	}
	
	/**
	 * M�todo equals para poder comprobar que dos casillas son iguales (pruebas unitarias).
	 * @param Casilla de comparaci�n
	 * @return valor booleano. True si dos objetos son iguales. False si son diferentes.
	 * @throws null
	 */
	public boolean equals(Casilla c){
		return (posicion == c.getPosicion());
	}
	
}
