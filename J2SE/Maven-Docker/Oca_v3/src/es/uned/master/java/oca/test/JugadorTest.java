package es.uned.master.java.oca.test;

import es.uned.master.java.oca.excepciones.*;
import es.uned.master.java.oca.jugador.*;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Se define una clase JugadorTest para realizar las pruebas unitarias sobre los m�dulos de la clase Jugador.
 * @author	Alef UNED 2014
 * @version	Oca 2.0
 * @fecha 	Abril 2014
 */

public class JugadorTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	
	/**
	 * Pruebas sobre el m�todo constructor Jugador.
	 * Al crear un objeto Jugador, �ste debe tener castigo = 0.
	 * Se prueba que es as�
	 */
	@Test
	public void testJugadorCastigo() {
		// Se crea un Jugador de prueba con valor no v�lido para la variable castigo:
		Jugador j = new Jugador (ColorJugador.AZUL);
		assertTrue(j.getCastigo() == 0);
	}

	
	
	
	/**
	 * Pruebas sobre el m�todo getColor.
	 * No se han desarrollado porque no hace ninguna comprobaci�n l�gica.
	 * 
	 */
	@Test
	public void testGetColor() {
	}
	
	/**
	 * Pruebas sobre el m�todo setColor.
	 * Se asigna un valor a la variable color y se comprueba si devuelve el valor bien.
	 */
	@Test
	public void testSetColor() {
		// Se crea un Jugador de prueba:
		Jugador j = new Jugador (ColorJugador.AZUL);
		// Se asigna un valor a color:
		j.setColor(ColorJugador.VERDE); 
		
		// Se comprueba el valor de color:
		assertTrue(j.getColor() == ColorJugador.VERDE);
	}
	
	/**
	 * Pruebas sobre el m�todo getAliasJugador.
	 * No se han desarrollado porque no hace ninguna comprobaci�n l�gica.
	 */
	@Test
	public void testGetAliasJugador() {
	}
		
	/**
	 * Pruebas sobre el m�todo getCastigo.
	 * No se han desarrollado porque no hace ninguna comprobaci�n l�gica.
	 */
	@Test
	public void testGetCastigo() {
	}		
	
	/**
	 * Pruebas sobre el m�todo setCastigo, que recibe como par�metro 
	 * un valor entero que representa el castigo de turnos del jugador.
	 * El valor del castigo debe estar comprendido entre 0 y 3.
	 * Se prueba que en caso de introducir un valor para el castigo fuera de rango,
	 * se lanza un error.
	 */
	@Test(expected=ClaseError.class)
	public void testSetCastigo() {
		// Se crea un Jugador de prueba:
		Jugador j = new Jugador (ColorJugador.AZUL);
		// Se intenta asignar un valor incorrecto a castigo:
		j.setCastigo(4); 
	}

	/**
	 * Pruebas sobre el m�todo getTiraOtraVez.
	 * No se han desarrollado porque no hace ninguna comprobaci�n l�gica.
	 */
	@Test
	public void testGetTiraOtraVez() {
	}
	
	/**
	 * Pruebas sobre el m�todo setTiraOtraVez.
	 * Se asigna un valor a la variable tiraOtraVez y se comprueba si devuelve el valor bien.
	 */
	@Test
	public void testSetTiraOtraVez() {
		// Se crea un Jugador de prueba:
		Jugador j = new Jugador (ColorJugador.AZUL);
		// Se asigna un valor a tiraOtraVez:
		j.setTiraOtraVez(true); 
		
		// Se comprueba el valor de tiraOtraVez:
		assertTrue(j.getTiraOtraVez());
		
		// Se cambia el valor a tiraOtraVez:
		j.setTiraOtraVez(false); 
		
		// Se comprueba el valor de tiraOtraVez:
		assertFalse(j.getTiraOtraVez());
	}
	

	
	
	/**
	 * Pruebas sobre el m�todo hashCode.
	 * En el juego no se permite que varios jugadores tengan el mismo colorJugador. Se va a 
	 * comprobar que el hashCode de dos jugadores con el mismo color es igual.
	 * Asimismo, se comprueba que el hashCode de dos jugadores con diferente color es diferente.
	 */
	@Test
	public void testHashCode(){
		// Se crean dos objetos Jugador de prueba con el mismo color:
		Jugador j1 = new Jugador (ColorJugador.AZUL);		
		Jugador j2 = new Jugador (ColorJugador.AZUL);
		// Se crea un tercer jugador con otro color:
		Jugador j3 = new Jugador (ColorJugador.VERDE);
		
		// Se comprueba el valor de hashCode:
		assertTrue(j1.hashCode() == j2.hashCode());
		assertTrue(j1.hashCode() != j3.hashCode());
	}
	
	/**
	 * Pruebas sobre el m�todo equals.
	 * En el juego no se permite que varios jugadores tengan el mismo colorJugador. Se va a 
	 * comprobar que el m�todo equals de dos jugadores con el mismo color son iguales.
	 * Asimismo, se comprueba que dos jugadores con diferentes colores, son diferentes.
	 */
	@Test
	public void testEquals(){
		// Se crean dos objetos Jugador de prueba con el mismo color:
		Jugador j1 = new Jugador (ColorJugador.AZUL);		
		Jugador j2 = new Jugador (ColorJugador.AZUL);
		// Se crea un tercer jugador con otro color:
		Jugador j3 = new Jugador (ColorJugador.VERDE);
		
		// Se comprueba el valor de equals:
		assertTrue(j1.equals(j2));
		assertFalse(j1.equals(j3));
	}
	
	/**
	 * Pruebas sobre el m�todo compareTo.
	 * En el juego no se permite que varios jugadores tengan el mismo colorJugador. Se va a 
	 * comprobar que dos jugadores con el mismo color son iguales para este m�todo.
	 * Asimismo, se comprueba que dos jugadores con diferente color son diferentes para este m�todo.
	 */
	@Test
	public void testCompareTo() {
		// Se crean dos objetos Jugador de prueba con el mismo color:
		Jugador j1 = new Jugador (ColorJugador.AZUL);		
		Jugador j2 = new Jugador (ColorJugador.AZUL);
		// Se crea un tercer jugador con otro color:
		Jugador j3 = new Jugador (ColorJugador.VERDE);
		
		// Se comprueba el valor de hashCode:
		assertTrue(j1.compareTo(j2) == 0);
		assertTrue(j1.compareTo(j3) != 0);	
	}

	/**
	 * Pruebas sobre el m�todo toString.
	 * No se han desarrollado
	 */
	@Test
	public void testToString() {	
	}

}
