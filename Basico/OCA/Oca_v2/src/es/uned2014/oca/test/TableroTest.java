package es.uned2014.oca.test;

import es.uned2014.oca.jugador.*;
import es.uned2014.oca.tablero.*;
import es.uned2014.oca.partida.*;
import es.uned2014.oca.excepciones.ClaseError;

import java.util.*;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TableroTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	/**
	 * Pruebas sobre el método constructor.
	 * No se han desarrollado porque no hace ninguna comprobación lógica.
	 */
	@Test
	public void testTablero() {
	}
	
	
	/**
	 * Pruebas sobre el método inicializarTablero.
	 * Se comprueba que el método devuelve un ArrayList con el contenido esperado.
	 */
	@Test
	public void testInicializarTablero() {
		// Se crea un Tablero de prueba
		Tablero tab = new Tablero();

		// Se ejecuta inicializartablero y se guarda el resultado:
		ArrayList<Casilla> resultado = tab.inicializarTablero();		
		
		// Se comprueba si cada valor del Array es igual que la casilla que le corresponde.
		// Array de 0 a 62, casillas de Casilla(1) a Casilla(63)
		for (int i = 0; i<63; i++){
			assertTrue(resultado.get(i).equals(new Casilla(i+1)));
		}
	}

	/**
	 * Pruebas sobre el método analizarTirada: Recibe un valor del dado fuera de rango.
	 */
	@Test(expected=ClaseError.class)
	public void testAnalizarTiradaDado() {
		// Se crea un Tablero de prueba y se inicializa
		Tablero tab = new Tablero();
		tab.inicializarTablero();
		
		// Se crea un Jugador de prueba
		Jugador j = new Jugador(Color.AZUL, "Juan", 0);
		
		// Se crea una Casilla origen
		Casilla c = new Casilla(15);
		
		// Se le pide al tablero que analice que un jugador esté en la casilla 15 y saque un 8
		Casilla resultado = tab.analizarTirada(j, c, 8);
	}
	
	/**
	 * Pruebas sobre el método analizarTirada.
	 * Se va a comprobar que se calcula bien la casilla de destino, siendo Casilla NORMAL.
	 */
	@Test
	public void testAnalizarTiradaNormal() {
		// Se crea un Tablero de prueba y se inicializa
		Tablero tab = new Tablero();
		tab.inicializarTablero();
		
		// Se crea un Jugador de prueba
		Jugador j = new Jugador(Color.AZUL, "Juan", 0);
		
		// Se crea una Casilla origen
		Casilla c = new Casilla(15);
		
		// Se le pide al tablero que analice que un jugador esté en la casilla 15 y saque un 2
		Casilla resultado = tab.analizarTirada(j, c, 2);
		
		// La casilla destino se calcula normal: 17
		Casilla expected = new Casilla(17);
		
		assertTrue(resultado.equals(expected));
		
		// Se comprueba si la variable tiraOtraVez del jugador es false.
		assertFalse(j.getTiraOtraVez());
		
		// Se comprueba que la variable castigo del jugador es 0
		assertTrue(j.getCastigo() == 0);
		
	}
	
	/**
	 * Pruebas sobre el método analizarTirada.
	 * Se va a comprobar que se calcula bien la casilla de destino, siendo Casilla OCA.
	 */
	@Test
	public void testAnalizarTiradaOca() {
		// Se crea un Tablero de prueba y se inicializa
		Tablero tab = new Tablero();
		tab.inicializarTablero();
		
		// Se crea un Jugador de prueba
		Jugador j = new Jugador(Color.AZUL, "Juan", 0);
		
		// Se crea una Casilla origen
		Casilla c = new Casilla(15);
		
		// Se le pide al tablero que analice que un jugador esté en la casilla 15 y saque un 3
		Casilla resultado = tab.analizarTirada(j, c, 3);
		
		// Como es una Oca, la casilla destino no debe ser la 18, sino la 23
		Casilla expected = new Casilla(23);

		assertTrue(resultado.equals(expected));
		
		// Se comprueba si la variable tiraOtraVez del jugador ha pasado a true.
		assertTrue(j.getTiraOtraVez());
		
		// Se comprueba que la variable castigo del jugador es 0
		assertTrue(j.getCastigo() == 0);
	}
	
	/**
	 * Pruebas sobre el método analizarTirada.
	 * Se va a comprobar que se calcula bien la casilla de destino, siendo Casilla PUENTE.
	 */
	@Test
	public void testAnalizarTiradaPuente() {
		// Se crea un Tablero de prueba y se inicializa
		Tablero tab = new Tablero();
		tab.inicializarTablero();
		
		// Se crea un Jugador de prueba
		Jugador j = new Jugador(Color.AZUL, "Juan", 0);
		
		// Se crea una Casilla origen
		Casilla c = new Casilla(11);
		
		// Se le pide al tablero que analice que un jugador esté en la casilla 11 y saque un 1
		Casilla resultado = tab.analizarTirada(j, c, 1);
		
		// Como es una casilla Puente, la casilla destino no debe ser la 12, sino la 6
		Casilla expected = new Casilla(6);

		assertTrue(resultado.equals(expected));
		
		// Se comprueba si la variable tiraOtraVez del jugador ha pasado a true.
		assertTrue(j.getTiraOtraVez());
		
		// Se comprueba que la variable castigo del jugador es 0
		assertTrue(j.getCastigo() == 0);
	}
	
	/**
	 * Pruebas sobre el método analizarTirada.
	 * Se va a comprobar que se calcula bien la casilla de destino, siendo Casilla DADOS.
	 */
	@Test
	public void testAnalizarTiradaDados() {
		// Se crea un Tablero de prueba y se inicializa
		Tablero tab = new Tablero();
		tab.inicializarTablero();
		
		// Se crea un Jugador de prueba
		Jugador j = new Jugador(Color.AZUL, "Juan", 0);
		
		// Se crea una Casilla origen
		Casilla c = new Casilla(52);
		
		// Se le pide al tablero que analice que un jugador esté en la casilla 52 y saque un 1
		Casilla resultado = tab.analizarTirada(j, c, 1);
		
		// Como es una casilla Dados, la casilla destino no debe ser la 12, sino la 6
		Casilla expected = new Casilla(26);

		assertTrue(resultado.equals(expected));
		
		// Se comprueba si la variable tiraOtraVez del jugador ha pasado a true.
		assertTrue(j.getTiraOtraVez());	
		
		// Se comprueba que la variable castigo del jugador es 0
		assertTrue(j.getCastigo() == 0);
	}
	
	/**
	 * Pruebas sobre el método analizarTirada.
	 * Se va a comprobar que se calcula bien la casilla de destino, siendo Casilla POSADA.
	 */
	@Test
	public void testAnalizarTiradaPosada() {
		// Se crea un Tablero de prueba y se inicializa
		Tablero tab = new Tablero();
		tab.inicializarTablero();
		
		// Se crea un Jugador de prueba
		Jugador j = new Jugador(Color.AZUL, "Juan", 0);
		
		// Se crea una Casilla origen
		Casilla c = new Casilla(17);
		
		// Se le pide al tablero que analice que un jugador esté en la casilla 17 y saque un 2
		Casilla resultado = tab.analizarTirada(j, c, 2);
		
		// Como es la Posada, la casilla destino se calcula normal: 19
		Casilla expected = new Casilla(19);

		assertTrue(resultado.equals(expected));
		
		// Se comprueba si la variable tiraOtraVez del jugador es false.
		assertFalse(j.getTiraOtraVez());
		
		// Se comprueba que la variable castigo del jugador es 2
		assertTrue(j.getCastigo() == 2);
	}	
	
	/**
	 * Pruebas sobre el método analizarTirada.
	 * Se va a comprobar que se calcula bien la casilla de destino, siendo Casilla POZO.
	 */
	@Test
	public void testAnalizarTiradaPozo() {
		// Se crea un Tablero de prueba y se inicializa
		Tablero tab = new Tablero();
		tab.inicializarTablero();
		
		// Se crea un Jugador de prueba
		Jugador j = new Jugador(Color.AZUL, "Juan", 0);
		
		// Se crea una Casilla origen
		Casilla c = new Casilla(29);
		
		// Se le pide al tablero que analice que un jugador esté en la casilla 29 y saque un 2
		Casilla resultado = tab.analizarTirada(j, c, 2);
		
		// Como es el Pozo, la casilla destino se calcula normal: 31
		Casilla expected = new Casilla(31);

		assertTrue(resultado.equals(expected));
		
		// Se comprueba si la variable tiraOtraVez del jugador es false.
		assertFalse(j.getTiraOtraVez());
		
		// Se comprueba que la variable castigo del jugador es 2
		assertTrue(j.getCastigo() == 2);
	}
	
	/**
	 * Pruebas sobre el método analizarTirada.
	 * Se va a comprobar que se calcula bien la casilla de destino, siendo Casilla CARCEL.
	 */
	@Test
	public void testAnalizarTiradaCarcel() {
		// Se crea un Tablero de prueba y se inicializa
		Tablero tab = new Tablero();
		tab.inicializarTablero();
		
		// Se crea un Jugador de prueba
		Jugador j = new Jugador(Color.AZUL, "Juan", 0);
		
		// Se crea una Casilla origen
		Casilla c = new Casilla(49);
		
		// Se le pide al tablero que analice que un jugador esté en la casilla 49 y saque un 3
		Casilla resultado = tab.analizarTirada(j, c, 3);
		
		// Como es la Carcel, la casilla destino se calcula normal: 52
		Casilla expected = new Casilla(52);

		assertTrue(resultado.equals(expected));
		
		// Se comprueba si la variable tiraOtraVez del jugador es false.
		assertFalse(j.getTiraOtraVez());
		
		// Se comprueba que la variable castigo del jugador es 3
		assertTrue(j.getCastigo() == 3);
	}
	
	/**
	 * Pruebas sobre el método analizarTirada.
	 * Se va a comprobar que se calcula bien la casilla de destino, siendo Casilla LABERINTO.
	 */
	@Test
	public void testAnalizarTiradaLaberinto() {
		// Se crea un Tablero de prueba y se inicializa
		Tablero tab = new Tablero();
		tab.inicializarTablero();
		
		// Se crea un Jugador de prueba
		Jugador j = new Jugador(Color.AZUL, "Juan", 0);
		
		// Se crea una Casilla origen
		Casilla c = new Casilla(40);
		
		// Se le pide al tablero que analice que un jugador esté en la casilla 40 y saque un 2
		Casilla resultado = tab.analizarTirada(j, c, 2);
		
		// Como es el Laberinto, la casilla destino es la 30
		Casilla expected = new Casilla(30);

		assertTrue(resultado.equals(expected));
		
		// Se comprueba si la variable tiraOtraVez del jugador es false.
		assertFalse(j.getTiraOtraVez());
		
		// Se comprueba que la variable castigo del jugador es 0
		assertTrue(j.getCastigo() == 0);
	}
	
	/**
	 * Pruebas sobre el método analizarTirada.
	 * Se va a comprobar que se calcula bien la casilla de destino, siendo Casilla CALAVERA.
	 */
	@Test
	public void testAnalizarTiradaCalavera() {
		// Se crea un Tablero de prueba y se inicializa
		Tablero tab = new Tablero();
		tab.inicializarTablero();
		
		// Se crea un Jugador de prueba
		Jugador j = new Jugador(Color.AZUL, "Juan", 0);
		
		// Se crea una Casilla origen
		Casilla c = new Casilla(57);
		
		// Se le pide al tablero que analice que un jugador esté en la casilla 57 y saque un 1
		Casilla resultado = tab.analizarTirada(j, c, 1);
		
		// Como es la Calavera, la casilla destino es la 1
		Casilla expected = new Casilla(1);

		assertTrue(resultado.equals(expected));
		
		// Se comprueba si la variable tiraOtraVez del jugador es false.
		assertFalse(j.getTiraOtraVez());
		
		// Se comprueba que la variable castigo del jugador es 0
		assertTrue(j.getCastigo() == 0);
	}		
	
	/**
	 * Pruebas sobre el método analizarTirada: Casilla 63: REBOTE.
	 */
	@Test
	public void testAnalizarTirada63Rebote() {
		// Se crea un Tablero de prueba y se inicializa
		Tablero tab = new Tablero();
		tab.inicializarTablero();
		
		// Se crea un Jugador de prueba
		Jugador j = new Jugador(Color.AZUL, "Juan", 0);
		
		// Se crea una Casilla origen
		Casilla c = new Casilla(60);
		
		// Se le pide al tablero que analice que un jugador esté en la casilla 60 y saque un 4
		Casilla resultado = tab.analizarTirada(j, c, 4);
		
		// Se calcula el rebote: la casilla destino no es 64, sino 62.
		Casilla expected = new Casilla(62);
		
		assertTrue(resultado.equals(expected));
		
		// Se comprueba que el juego NO ha terminado
		assertFalse(Oca.getJuegoTerminado());
	}
	
	/**
	 * Pruebas sobre el método analizarTirada: Casilla 63: desde Oca.
	 */
	@Test
	public void testAnalizarTirada63DesdeOca() {
		// Se crea un Tablero de prueba y se inicializa
		Tablero tab = new Tablero();
		tab.inicializarTablero();
		
		// Se crea un Jugador de prueba
		Jugador j = new Jugador(Color.AZUL, "Juan", 0);
		
		// Se crea una Casilla origen
		Casilla c = new Casilla(57);
		
		// Se le pide al tablero que analice que un jugador esté en la casilla 57 y saque un 2
		Casilla resultado = tab.analizarTirada(j, c, 2);
		
		// Como es Oca, la casilla destino no es 59, sino 63
		Casilla expected = new Casilla(63);
		
		assertTrue(resultado.equals(expected));
		
		// Se comprueba si la variable tiraOtraVez del jugador es true.
		assertTrue(j.getTiraOtraVez());
		
		// Se comprueba que la variable castigo del jugador es 0
		assertTrue(j.getCastigo() == 0);
		
		// Se comprueba que el juego NO ha terminado
		assertFalse(Oca.getJuegoTerminado());
	}
	
	
	/**
	 * Pruebas sobre el método analizarTirada: Casilla 63: FINAL DE PARTIDA.
	 */
	@Test
	public void testAnalizarTirada63Final() {
		// Se crea un Tablero de prueba y se inicializa
		Tablero tab = new Tablero();
		tab.inicializarTablero();
		
		// Se crea un Jugador de prueba
		Jugador j = new Jugador(Color.AZUL, "Juan", 0);
		
		// Se crea una Casilla origen
		Casilla c = new Casilla(57);
		
		// Se le pide al tablero que analice que un jugador esté en la casilla 57 y saque un 6
		Casilla resultado = tab.analizarTirada(j, c, 6);
		
		// Llega a la meta con su tirada:
		Casilla expected = new Casilla(63);
		
		assertTrue(resultado.equals(expected));
		
		// Se comprueba que el juego ha terminado
		assertTrue(Oca.getJuegoTerminado());
	}
	
	/**
	 * Pruebas sobre el método toString.
	 * No se han desarrollado
	 */
	@Test
	public void testToString() {

	}

}
