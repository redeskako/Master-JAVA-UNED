package es.uned2014.oca.test;

import es.uned2014.oca.jugador.*;
import es.uned2014.oca.excepciones.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JugadorTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	
	/**
	 * Pruebas sobre el método constructor Jugador.
	 * Al crear un objeto Jugador, éste debe tener castigo = 0.
	 * Se prueba que en caso de introducir un valor para el castigo que no sea 0,
	 * se lanza un error.
	 */
	@Test(expected=ClaseError.class)
	public void testJugadorCastigo() {
		// Se crea un Jugador de prueba con valor no válido para la variable castigo:
		Jugador j = new Jugador (Color.AZUL, null,  5);
	}
	
	/**
	 * Pruebas sobre el método constructor Jugador.
	 * El valor del String que se aligna a la variable aliasJugador debe tener una longitud 
	 * entre 3 y 15.
	 * Se prueba que en caso de introducir un alias con longitud incorrecta, se lanza un error.
	 */
	@Test(expected=ClaseError.class)
	public void testJugadorAlias() {
		// Se crea un Jugador de prueba con valor no válido para la variable alias:
		Jugador j = new Jugador (Color.AZUL, "UnStringMuyLargo",  0);
	}
	
	
	
	
	/**
	 * Pruebas sobre el método getColor.
	 * Se asigna un valor a la variable color y se comprueba si devuelve el valor bien.
	 */
	@Test
	public void testGetColor() {
		// Se crea un Jugador de prueba:
		Jugador j = new Jugador (Color.AZUL, "Juan", 0);
		// Se asigna un valor a color:
		j.setColor(Color.VERDE); 
		
		// Se comprueba el valor de color:
		assertTrue(j.getColor() == Color.VERDE);
	}
	
	/**
	 * Pruebas sobre el método setColor.
	 * No se han desarrollado
	 */
	@Test
	public void testSetColor() {
	}
	
	/**
	 * Pruebas sobre el método getAliasJugador.
	 * Se asigna un valor a la variable aliasJugador y se comprueba si devuelve el valor bien.
	 */
	@Test
	public void testGetAliasJugador() {
		// Se crea un Jugador de prueba:
		Jugador j = new Jugador (Color.AZUL, "Ana", 0);
		
		// Se comprueba el valor del alias:
		assertTrue(j.getAliasJugador() == "Ana");
	}
	
	/**
	 * Pruebas sobre el método setAliasJugador, que recibe como parámetro 
	 * un valor String que representa el alias del jugador.
	 * La longitud del String debe estar comprendido entre 3 y 15.
	 * Se prueba que en caso de introducir un valor para el alias fuera de rango,
	 * se lanza un error.
	 */
	@Test(expected=ClaseError.class)
	public void testSetAliasJugador() {
		// Se crea un Jugador de prueba:
		Jugador j = new Jugador (Color.AZUL, "Juan", 0);
		// Se intenta asignar un valor incorrecto a alias:
		j.setAliasJugador("UnStringMuyLargo"); 
	}
		
	/**
	 * Pruebas sobre el método getCastigo.
	 * Se asigna un valor a la variable castigo y se comprueba si devuelve el valor bien.
	 */
	@Test
	public void testGetCastigo() {
		// Se crea un Jugador de prueba:
		Jugador j = new Jugador (Color.AZUL, "Ana", 0);
		// Se asigna un valor a castigo:
		j.setCastigo(3); 
		
		// Se comprueba el valor de castigo:
		assertTrue(j.getCastigo() == 3);
	}		
	
	/**
	 * Pruebas sobre el método setCastigo, que recibe como parámetro 
	 * un valor entero que representa el castigo de turnos del jugador.
	 * El valor del castigo debe estar comprendido entre 0 y 3.
	 * Se prueba que en caso de introducir un valor para el castigo fuera de rango,
	 * se lanza un error.
	 */
	@Test(expected=ClaseError.class)
	public void testSetCastigo() {
		// Se crea un Jugador de prueba:
		Jugador j = new Jugador (Color.AZUL, "Ana", 0);
		// Se intenta asignar un valor incorrecto a castigo:
		j.setCastigo(4); 
	}

	/**
	 * Pruebas sobre el método getTiraOtraVez.
	 * Se asigna un valor a la variable tiraOtraVez y se comprueba si devuelve el valor bien.
	 */
	@Test
	public void testGetTiraOtraVez() {
		// Se crea un Jugador de prueba:
		Jugador j = new Jugador (Color.AZUL, "María José", 0);
		// Se asigna un valor a tiraOtraVez:
		j.setTiraOtraVez(true); 
		
		// Se comprueba el valor de castigo:
		assertTrue(j.getTiraOtraVez());
	}
	
	/**
	 * Pruebas sobre el método setTiraOtraVez.
	 * No se han desarrollado
	 */
	@Test
	public void testSetTiraOtraVez() {
	}
	

	
	
	/**
	 * Pruebas sobre el método hashCode.
	 * En el juego no se permite que varios jugadores tengan el mismo colorJugador. Se va a 
	 * comprobar que el hashCode de dos jugadores con el mismo color es igual.
	 * Asimismo, se comprueba que el hashCode de dos jugadores con diferente color es diferente.
	 */
	@Test
	public void testHashCode(){
		// Se crean dos objetos Jugador de prueba con el mismo color:
		Jugador j1 = new Jugador (Color.AZUL, "Ana",  0);		
		Jugador j2 = new Jugador (Color.AZUL, "Pedro", 0);
		// Se crea un tercer jugador con otro color:
		Jugador j3 = new Jugador (Color.VERDE, "Ana",  0);
		
		// Se comprueba el valor de hashCode:
		assertTrue(j1.hashCode() == j2.hashCode());
		assertTrue(j1.hashCode() != j3.hashCode());
	}
	
	/**
	 * Pruebas sobre el método equals.
	 * En el juego no se permite que varios jugadores tengan el mismo colorJugador. Se va a 
	 * comprobar que el método equals de dos jugadores con el mismo color son iguales.
	 * Asimismo, se comprueba que dos jugadores con diferentes colores, son diferentes.
	 */
	@Test
	public void testEquals(){
		// Se crean dos objetos Jugador de prueba con el mismo color:
		Jugador j1 = new Jugador (Color.AZUL, "Ana",  0);		
		Jugador j2 = new Jugador (Color.AZUL, "Pedro", 0);
		// Se crea un tercer jugador con otro color:
		Jugador j3 = new Jugador (Color.VERDE, "Ana",  0);
		
		// Se comprueba el valor de equals:
		assertTrue(j1.equals(j2));
		assertFalse(j1.equals(j3));
	}
	
	/**
	 * Pruebas sobre el método compareTo.
	 * En el juego no se permite que varios jugadores tengan el mismo colorJugador. Se va a 
	 * comprobar que dos jugadores con el mismo color son iguales para este método.
	 * Asimismo, se comprueba que dos jugadores con diferente color son diferentes para este método.
	 */
	@Test
	public void testCompareTo() {
		// Se crean dos objetos Jugador de prueba con el mismo color:
		Jugador j1 = new Jugador (Color.AZUL, "Ana",  0);		
		Jugador j2 = new Jugador (Color.AZUL, "Pedro", 0);
		// Se crea un tercer jugador con otro color:
		Jugador j3 = new Jugador (Color.VERDE, "Ana",  0);
		
		// Se comprueba el valor de hashCode:
		assertTrue(j1.compareTo(j2) == 0);
		assertTrue(j1.compareTo(j3) != 0);	
	}

	/**
	 * Pruebas sobre el método toString.
	 * No se han desarrollado
	 */
	@Test
	public void testToString() {	
	}

}
