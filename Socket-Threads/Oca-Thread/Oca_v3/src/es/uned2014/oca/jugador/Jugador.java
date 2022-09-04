package es.uned2014.oca.jugador;

import es.uned2014.oca.excepciones.*;

/**
 * Se define una clase Jugador que representa cada participante del juego de la Oca.
 * Como en la clase Oca se van a almacenar varios objetos de tipo Jugador en un TreeSet, Jugador hereda
 * de Comparable, e implementa el m�todo compareTo(Jugador j).
 * Asimismo, como se van a utilizar elementos Jugador como Key en un HashMap, se implementan los m�todos
 * hashCode() y equals(Jugador j).
 * Sus variables son un valor del enum Color, que identifica el color del jugador, un valor entero que 
 * indica su el jugador tiene penalizaci�n de turnos, y un valor booleano que indica si repite turno.
 * @author	Alef UNED 2014
 * @version	Oca 2.0
 * @fecha 	Abril 2014
 */

public class Jugador implements Comparable<Jugador>{
	
	// Variable de tipo enum Color, que representa el color que identifica al jugador.
	private ColorJugador colorJugador;
	// Variable de tipo String, que representa el alias del jugador.
	private String aliasJugador;	
	// Variable de tipo entero que indica el n�mero de turnos que debe pasar sin participar el jugador.
	private int castigo;
	// Variable booleana que indica si el jugador repite turno (true, repite turno; false, no repite turno).
	private boolean tiraOtraVez;

	/**
	 * M�todo constructor. Recibe como argumentos un objeto Color que representa el color del jugador y
	 * un valor entero que representa el castigo en turno que tiene ese jugador.
	 * @param ColorJugador c, que pertenece al enum Color.
	 * @param valor entero i que se asignar� a la variable castigo
	 * @return null
	 * @throws ClaseError en caso de que el valor no sea 0
	 */
	public Jugador(ColorJugador c) {
		this.colorJugador = c;
		this.castigo = 0;
		this.tiraOtraVez =  false;
	}
	
	/**
	 * Obtiene el valor de la variable colorJugador, que es de tipo enum Color.
	 * @param null
	 * @return valor de la variable colorJugador, que es de tipo enum Color
	 * @throws null
	 */
	public ColorJugador getColor() {
		return colorJugador;
	}
	
	/**
	 * Establece un nuevo Color para la variable colorJugador.
	 * @param valor enum Color c que se asignar� a la variable colorJugador
	 * @return null
	 * @throws null
	 */
	public void setColor(ColorJugador c) {
		this.colorJugador = c;
	}
	
	/**
	 * Obtiene el valor String de la variable aliasJugador.
	 * @param null
	 * @return valor String de la variable aliasJugador
	 * @throws null
	 */
	public String getAliasJugador() {
		return aliasJugador;
	}
	
	/**
	 * Establece un nuevo valor String para la variable aliasJugador.
	 * @param valor String que se asignar� a la variable aliasJugador
	 * @return null
	 * @throws ClaseError en caso de que el String tenga una longitud entre 3 y 15 caracteres
	 */
	public void setAliasJugador(String s) {
		// Antes de asignar el valor a la variable castigo, se comprueba que est� en el rango 3-15.
		if (s.length()<3 || s.length()>15){
			throw new ClaseError("La longitud del nombre del jugador no es correcta (3-15 caracteres)"); 
		}else{
			this.aliasJugador = s;
		}
	}
	
	/**
	 * Obtiene el valor entero de la variable castigo.
	 * @param null
	 * @return valor entero i de la variable castigo
	 * @throws null
	 */
	public int getCastigo() {
		return castigo;
	}
	
	/**
	 * Establece un nuevo valor entero para la variable castigo.
	 * @param valor entero i que se asignar� a la variable castigo
	 * @return null
	 * @throws ClaseError en caso de que el valor no est� comprendido entre 0 y 3
	 */
	public void setCastigo(int i) {
		// Antes de asignar el valor a la variable castigo, se comprueba que est� en el rango 0-3.
		if (i<0 || i>3){
			throw new ClaseError("El valor del castigo tiene que estar comprendido entre 0 y 3."); 
		}else{
			this.castigo = i;
		}
	}
	
	/**
	 * Se obtiene el valor boolean de la variable tiraOtraVez.
	 * @param null
	 * @return valor boolean b de tirarOtravez
	 * @throws null
	 */
	public boolean getTiraOtraVez() {
		return tiraOtraVez;
	}
	
	/**
	 * Establece un nuevo valor boolean para la variable tiraOtraVez.
	 * @param valor boolean b que se asignar� a la variable tiraOtraVez
	 * @return null
	 * @throws null
	 */
	public void setTiraOtraVez(boolean b) {
		this.tiraOtraVez = b;
	}

	/**
	 * Establece un c�digo hash, que identifica a cada Jugador.
	 * Se utiliza el criterio de usar el hashCode del enum de la variable colorJugador.
	 * @param null
	 * @return valor entero que identifica de manera �nica a cada Jugador
	 * @throws null
	 */
	public int hashCode() {
		// retorna un identificador unico del objeto. 
		return this.getColor().hashCode();
		} 
	
	/**
	 * M�todo equals, que establece el criterio mediante el cual dos jugadores son iguales.
	 * Se utiliza el criterio de usar el equals del enum de la variable colorJugador.
	 * @param objeto Jugador con el que se compara el Jugador actural (this).
	 * @return valor booleano. True si dos objetos son iguales. False si son diferentes.
	 * @throws null
	 */
	public boolean equals(Jugador j) {
		return this.colorJugador.equals(j.getColor());
		// Jugador AZUL "Juan" es igual a Jugador AZUL "Ana"
		// Jugador AZUL "Juan" no es igual a Jugador VERDE "Juan"
		} 

	/**
	 * M�todo compareTo, que establece el criterio mediante el cual un Jugador es igual,
	 * mayor o menor que otro Jugador.
	 * Se utiliza el criterio de usar el compareTo del enum de la variable colorJugador.
	 * @param 	objeto Jugador con el que se compara el Jugador actural (this).
	 * @return 	valor entero. Positivo si el jugador a comparar es mayor que this, negativo si
	 * 			es menor, cero si son iguales.
	 * @throws 	null
	 */
	public int compareTo(Jugador j){
		return this.colorJugador.compareTo(j.getColor());
		// Jugador AZUL "Juan" es igual a Jugador AZUL "Ana"
		// Jugador AZUL "Juan" va antes que VERDE "Juan"
	}

	/**
	 * M�todo toString que devuelve una cadena de texto que representa al objeto Jugador.
	 * Este String tendr� el formato "Jugador AZUL: Pedro".
	 * @param null
	 * @return String que representa un Jugador "Jugador colorJugador: aliasJugador"
	 * @throws null
	 */
	public String toString(){
		return "Jugador " + this.colorJugador;
	}
}
