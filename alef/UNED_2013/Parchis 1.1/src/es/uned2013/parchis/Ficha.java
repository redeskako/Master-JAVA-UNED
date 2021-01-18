package es.uned2013.parchis;

import java.io.Serializable;



/**
 * Contiene las características de la ficha e
 * informa del su estado en el juego
 */
public class Ficha implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Colores color; // Color de la ficha.
	private int identificador; // Identificador único de la ficha.
	private int casillaActual; // Indica el número de la casilla en la que se encuentra la ficha.
	private int casillaSalida; // Indica el número de la casilla de salida (la siguiente a casa) de la ficha.
	private int casillaCasa; // Indica el número de la casilla casa de la ficha.
	private int casillaFinal; // Indica el número de la casilla final de recorrido de la ficha. 
	
	/**
	 * Constructor. Establece la ficha con sus respectivas características.
	 * @param color color de la ficha
	 * @param identificador identificador de ficha
	 * @param casillaActual casilla en la que se encuentra la ficha en una tirada
	 * @param casillaSalida casilla de partida de una tirada
	 * @param casillaCasa casilla inicial depediente del color
	 * @param casillaFinal casilla final dependiente del color
	 */
	public Ficha(Colores color, int identificador, int casillaActual,
			int casillaSalida, int casillaCasa, int casillaFinal) {
		this.color = color;
		this.identificador = identificador;
		this.casillaActual = casillaActual;
		this.casillaSalida = casillaSalida;
		this.casillaCasa = casillaCasa;
		this.casillaFinal = casillaFinal;		
	}

	/**
	 * Metodo getColor() que devuelte el color de la ficha
	 * @return color
	 */
	public Colores getColor() {
		return color;
	}

	/**
	 * Metodo setColor(color) que a�ade el color a la ficha
	 * @param color
	 */
	public void setColor(Colores color) {
		this.color = color;
	}

	/**
	 * Método getIdentificador() que devuelve el valor integer 
	 * correspondiente al identificador único de la ficha
	 * @return identificador
	 */
	public int getIdentificador() {
		return identificador;
	}

	/**
	 * Método setIdentificador(identificador) que añade el valor
	 * integer correspondiente al identificador único de la ficha
	 * @param identificador
	 */
	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}

	/**
	 * Método getCasillaActual() que devuelve el valor el numero
	 * de la casilla dode se encuentra la ficha
	 * @return casillaActual
	 */
	public int getCasillaActual() {
		return casillaActual;
	}

	/**
	 * Método setCasillaActual que añade el valor de la casilla actual
	 * a la ficha
	 * @param casillaActual
	 */
	public void setCasillaActual(int casillaActual) {
		this.casillaActual = casillaActual;
	}

	/**
	 * Metodo getCasillaSalida() que devuelve el valor de la casilla de salida
	 * para una ficha de ese color
	 * @return
	 */
	public int getCasillaSalida() {
		return casillaSalida;
	}

	/**
	 * Método setCasillaSalida(casillaSalida) que añade el valor de la
	 * casilla de salida para una ficha
	 * @param casillaSalida
	 */
	public void setCasillaSalida(int casillaSalida) {
		this.casillaSalida = casillaSalida;
	}

	/**
	 * Método getCasillaCasa() que devuelve el valor de la casilla casa
	 * para esa ficha
	 * @return
	 */
	public int getCasillaCasa() {
		return casillaCasa;
	}

	/**
	 * Método setCasillaCasa que introduce el valor de la casilla casa 
	 * para esa ficha
	 * @param casillaCasa
	 */
	public void setCasillaCasa(int casillaCasa) {
		this.casillaCasa = casillaCasa;
	}

	/**
	 * Método getCasillaFinal() que devuelve la casilla Final 
	 * para esa ficha
	 * @return
	 */
	public int getCasillaFinal() {
		return casillaFinal;
	}

	/**
	 * Método setCasillaFinal que introduce el valor de la casilla Final
	 * para esa ficha
	 * @param casillaFinal
	 */
	public void setCasillaFinal(int casillaFinal) {
		this.casillaFinal = casillaFinal;
	}

	
	
	
}
