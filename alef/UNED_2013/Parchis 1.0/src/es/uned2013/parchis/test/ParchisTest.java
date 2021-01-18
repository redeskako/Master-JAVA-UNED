package es.uned2013.parchis.test;

import static org.junit.Assert.*;

import java.util.Locale;

import org.junit.Before;
import org.junit.Test;

import es.uned2013.parchis.Modo;
import es.uned2013.parchis.Parchis;
import es.uned2013.parchis.ui.ParchisUIMode;

/**
 * En el Test se presupone que juegan cuatro jugadores y el orden de 
 * turno es AMARILLO->AZUL->ROJO->VERDE. De manera predeterminada el
 * programa da este orden a los jugadores.
 */
public class ParchisTest {
	
	public Parchis parchis = new Parchis();

	@Before
	public void setUp() throws Exception {
		/** 
         * El metodo precedido por la etiqueta @Before 
         * es para indicar a JUnit que debe ejecutarlo 
         * antes de ejecutar los Tests que figuran en 
         * esta clase. 
         */
		Parchis.setModo(Modo.PRUEBA);// Modo Test
		parchis.setUI(ParchisUIMode.CONSOLE,/*null*/new Locale("en"));// Interfaz consola
		Parchis.jugadaLimitePrueba = 132;// Jugadas totales de la partida Test
		parchis.inicioJuego(4);// Se prueba con todos los jugadores
		
	}
	
	@Test
	public void testJugar() {
		/**
		*  Al final de la ejecución del programa se comprueba que cada una de 
		*  las fichas ocupan la posición final de la partida testada.
		*  En la partida Test se fuerzan todas las situaciones recogidas en 
		*  los requisitos al menos una vez.
		*  Se guardan posiciones de jugadas intermedias en previsión.
		*  Más información en la wiki.
		*/
		assertEquals(108,(parchis.getFichasJuego()).get(0).getCasillaActual());
		assertEquals(108,(parchis.getFichasJuego()).get(1).getCasillaActual());
		assertEquals(108,(parchis.getFichasJuego()).get(2).getCasillaActual());
		assertEquals(108,(parchis.getFichasJuego()).get(3).getCasillaActual());
		assertEquals(207,(parchis.getFichasJuego()).get(4).getCasillaActual());
		assertEquals(25,(parchis.getFichasJuego()).get(5).getCasillaActual());
		assertEquals(22,(parchis.getFichasJuego()).get(6).getCasillaActual());
		assertEquals(22,(parchis.getFichasJuego()).get(7).getCasillaActual());
		assertEquals(49,(parchis.getFichasJuego()).get(8).getCasillaActual());
		assertEquals(308,(parchis.getFichasJuego()).get(9).getCasillaActual());
		assertEquals(45,(parchis.getFichasJuego()).get(10).getCasillaActual());
		assertEquals(39,(parchis.getFichasJuego()).get(11).getCasillaActual());
		assertEquals(56,(parchis.getFichasJuego()).get(12).getCasillaActual());
		assertEquals(408,(parchis.getFichasJuego()).get(13).getCasillaActual());
		assertEquals(56,(parchis.getFichasJuego()).get(14).getCasillaActual());
		assertEquals(62,(parchis.getFichasJuego()).get(15).getCasillaActual());
		
		//Parchis.jugadaLimitePrueba = 103;
		/*assertEquals(108,(parchis.getFichasJuego()).get(0).getCasillaActual());
		assertEquals(44,(parchis.getFichasJuego()).get(1).getCasillaActual());
		assertEquals(62,(parchis.getFichasJuego()).get(2).getCasillaActual());
		assertEquals(39,(parchis.getFichasJuego()).get(3).getCasillaActual());
		assertEquals(50,(parchis.getFichasJuego()).get(4).getCasillaActual());
		assertEquals(22,(parchis.getFichasJuego()).get(5).getCasillaActual());
		assertEquals(200,(parchis.getFichasJuego()).get(6).getCasillaActual());
		assertEquals(200,(parchis.getFichasJuego()).get(7).getCasillaActual());
		assertEquals(41,(parchis.getFichasJuego()).get(8).getCasillaActual());
		assertEquals(18,(parchis.getFichasJuego()).get(9).getCasillaActual());
		assertEquals(45,(parchis.getFichasJuego()).get(10).getCasillaActual());
		assertEquals(300,(parchis.getFichasJuego()).get(11).getCasillaActual());
		assertEquals(20,(parchis.getFichasJuego()).get(12).getCasillaActual());
		assertEquals(408,(parchis.getFichasJuego()).get(13).getCasillaActual());
		assertEquals(5,(parchis.getFichasJuego()).get(14).getCasillaActual());
		assertEquals(56,(parchis.getFichasJuego()).get(15).getCasillaActual());
		
		//Parchis.jugadaLimitePrueba = 52; Comprobado: 
		//1. Cuando se saca ficha se come una y la otra es del mismo color, se forma barrera.
		//2. Si una ficha finaliza en un casilla con dos fichas que no es barrera, no es movible.
		/*assertEquals(100,(parchis.getFichasJuego()).get(0).getCasillaActual());
		assertEquals(11,(parchis.getFichasJuego()).get(1).getCasillaActual());
		assertEquals(34,(parchis.getFichasJuego()).get(2).getCasillaActual());
		assertEquals(25,(parchis.getFichasJuego()).get(3).getCasillaActual());
		assertEquals(38,(parchis.getFichasJuego()).get(4).getCasillaActual());
		assertEquals(30,(parchis.getFichasJuego()).get(5).getCasillaActual());
		assertEquals(200,(parchis.getFichasJuego()).get(6).getCasillaActual());
		assertEquals(200,(parchis.getFichasJuego()).get(7).getCasillaActual());
		assertEquals(300,(parchis.getFichasJuego()).get(8).getCasillaActual());
		assertEquals(5,(parchis.getFichasJuego()).get(9).getCasillaActual());
		assertEquals(64,(parchis.getFichasJuego()).get(10).getCasillaActual());
		assertEquals(43,(parchis.getFichasJuego()).get(11).getCasillaActual());
		assertEquals(56,(parchis.getFichasJuego()).get(12).getCasillaActual());
		assertEquals(17,(parchis.getFichasJuego()).get(13).getCasillaActual());
		assertEquals(56,(parchis.getFichasJuego()).get(14).getCasillaActual());
		assertEquals(400,(parchis.getFichasJuego()).get(15).getCasillaActual());
		
		//Parchis.jugadaLimitePrueba = 41; Comprobado: "no se come ficha en casilla casa" 
		// "cuando se come a otra ficha actualiza posición"
		/*assertEquals(100,(parchis.getFichasJuego()).get(0).getCasillaActual());
		assertEquals(11,(parchis.getFichasJuego()).get(1).getCasillaActual());
		assertEquals(15,(parchis.getFichasJuego()).get(2).getCasillaActual());
		assertEquals(5,(parchis.getFichasJuego()).get(3).getCasillaActual());
		assertEquals(30,(parchis.getFichasJuego()).get(4).getCasillaActual());
		assertEquals(30,(parchis.getFichasJuego()).get(5).getCasillaActual());
		assertEquals(25,(parchis.getFichasJuego()).get(6).getCasillaActual());
		assertEquals(43,(parchis.getFichasJuego()).get(7).getCasillaActual());
		assertEquals(56,(parchis.getFichasJuego()).get(8).getCasillaActual());
		assertEquals(44,(parchis.getFichasJuego()).get(9).getCasillaActual());
		assertEquals(44,(parchis.getFichasJuego()).get(10).getCasillaActual());
		assertEquals(39,(parchis.getFichasJuego()).get(11).getCasillaActual());
		assertEquals(64,(parchis.getFichasJuego()).get(12).getCasillaActual());
		assertEquals(12,(parchis.getFichasJuego()).get(13).getCasillaActual());
		assertEquals(56,(parchis.getFichasJuego()).get(14).getCasillaActual());
		assertEquals(400,(parchis.getFichasJuego()).get(15).getCasillaActual());*/
		
		/*Parchis.jugadaLimitePrueba = 33;
		 * assertEquals(23,(parchis.getFichasJuego()).get(0).getCasillaActual());
		assertEquals(11,(parchis.getFichasJuego()).get(1).getCasillaActual());
		assertEquals(11,(parchis.getFichasJuego()).get(2).getCasillaActual());
		assertEquals(100,(parchis.getFichasJuego()).get(3).getCasillaActual());
		assertEquals(30,(parchis.getFichasJuego()).get(4).getCasillaActual());
		assertEquals(26,(parchis.getFichasJuego()).get(5).getCasillaActual());
		assertEquals(25,(parchis.getFichasJuego()).get(6).getCasillaActual());
		assertEquals(22,(parchis.getFichasJuego()).get(7).getCasillaActual());
		assertEquals(51,(parchis.getFichasJuego()).get(8).getCasillaActual());
		assertEquals(44,(parchis.getFichasJuego()).get(9).getCasillaActual());
		assertEquals(44,(parchis.getFichasJuego()).get(10).getCasillaActual());
		assertEquals(39,(parchis.getFichasJuego()).get(11).getCasillaActual());
		assertEquals(63,(parchis.getFichasJuego()).get(12).getCasillaActual());
		assertEquals(9,(parchis.getFichasJuego()).get(13).getCasillaActual());
		assertEquals(56,(parchis.getFichasJuego()).get(14).getCasillaActual());
		assertEquals(400,(parchis.getFichasJuego()).get(15).getCasillaActual());
		 * */
		 
	}

}
