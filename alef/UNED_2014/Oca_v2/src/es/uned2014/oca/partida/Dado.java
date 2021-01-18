package es.uned2014.oca.partida;

import java.util.Random;

import es.uned2014.oca.excepciones.ClaseError;


/**
 * Se define una clase Dadocuyo único objeto es generar un número entero aleatorio entre 1 y 6.
 * @author	Alef UNED 2014
 * @version	Oca 2.0
 * @fecha 	Abril 2014
 */
public class Dado {

	/**
	 * Se genera un número entero aleatorio entre 1 y 6 con Random.
	 * @param null
	 * @return valor entero entre 1 y 6
	 * @throws null
	 */
	public int tirarDado(){
		// Se crea una instancia de Random (java.util)
		Random dado = new Random();
		// Se solicita un número entero aleatorio entre 1 y 6
		return dado.nextInt(6)+1;
	}
}
