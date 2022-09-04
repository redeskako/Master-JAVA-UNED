package es.uned2014.oca.test;

import es.uned2014.oca.tablero.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.uned2014.oca.excepciones.ClaseError;
import es.uned2014.oca.jugador.ColorJugador;
import es.uned2014.oca.jugador.Jugador;

/**
 * Se define una clase CasillaTest para realizar las pruebas unitarias sobre los m�dulos de la clase Casilla.
 * @author	Alef UNED 2014
 * @version	Oca 2.0
 * @fecha 	Abril 2014
 */

public class CasillaTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	
	/**
	 * Pruebas sobre el m�todo constructor Casilla, que recibe como par�metro un valor entero.
	 * El valor entero se asigna a la posici�n de la Casilla: debe estar comprendido entre 1 y 63.
	 * Se prueba que en caso de introducir un valor int fuera de rango, se lanza un error.
	 */
	@Test(expected=ClaseError.class)
	public void testCasilla() {
		// Se crea una Casilla de prueba con valor no v�lido para la variable posicion:
		Casilla c = new Casilla (0);
	}

	
	
	
	/**
	 * Pruebas sobre el m�todo getPosicion.
	 * No se han desarrollado porque no hace ninguna comprobaci�n l�gica.
	 */
	@Test
	public void testGetPosicion() {
	}
	
	/**
	 * Pruebas sobre el m�todo setPosicion, que recibe como par�metro un valor entero 
	 * que representa la posici�n de la Casilla en el Tablero.
	 * El valor de posicion debe estar comprendido entre 1 y 63.
	 * Se prueba que al introducir un valor para posicion fuera de rango, se lanza un error.
	 */
	@Test(expected=ClaseError.class)
	public void testSetPosicion() {
		// Se crea una Casilla de prueba:
		Casilla c = new Casilla (8);
		
		// Se le intenta asignar un valor incorrecto de posicion:
		c.setPosicion(80);
	}
	
	/**
	 * Pruebas sobre el m�todo getTipo.
	 * No se han desarrollado porque no hace ninguna comprobaci�n l�gica.
	 */
	@Test
	public void testGetTipo() {
	}

	/**
	 * Pruebas sobre el m�todo toString.
	 * No se han desarrollado
	 */
	@Test
	public void testToString() {
	}

	/**
	 * Pruebas sobre el m�todo equals.
	 * Dos casillas son iguales ti est�n en la misma posici�n.
	 */
	@Test
	public void testEquals(){
		// Se crean dos objetos Casila de prueba:
		Casilla c1 = new Casilla (22);		
		Casilla c2 = new Casilla (22);
		// Se crea un tercer jugador con otro color:
		Casilla c3 = new Casilla (8);
		
		// Se comprueba el valor de equals:
		assertTrue(c1.equals(c2));
		assertFalse(c1.equals(c3));
	}
}
