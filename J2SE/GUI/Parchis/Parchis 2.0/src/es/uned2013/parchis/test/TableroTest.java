package es.uned2013.parchis.test;

import static org.junit.Assert.*;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Vector;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import es.uned2013.parchis.Colores;
import es.uned2013.parchis.Ficha;
import es.uned2013.parchis.Jugador;
import es.uned2013.parchis.Modo;
import es.uned2013.parchis.Parchis;
import es.uned2013.parchis.Tablero;
import es.uned2013.parchis.ui.ParchisUIMode;

public class TableroTest {
	
	ArrayList<Ficha> fichasJuego;
	
	@Ignore public void inicializarArrayFichas (int numJugadores){
		fichasJuego = new ArrayList<Ficha>();
		//Creo las fichas del juego, supongamos que tenemos dos jugadores
		for (int i = 0; i < (numJugadores); i++) {
			for (int j = (4 * i); j < ((4 * i) + 4); j++) 
			{
				if (j == (4 * i))
				{ // La primera ficha de cada color en la casilla de salida
					fichasJuego.add(new Ficha(Colores.values()[i],j + 1,
							Colores.values()[i].getCasillaSalida(),
							Colores.values()[i].getCasillaSalida(),
							Colores.values()[i].getCasillaCasa(),
							Colores.values()[i].getCasillaFinal()));
				}
				else if (j == (4*i + 1))
				{ // ...una en casa
					fichasJuego.add(new Ficha(Colores.values()[i],j + 1,
							Colores.values()[i].getCasillaCasa(),
							Colores.values()[i].getCasillaSalida(),
							Colores.values()[i].getCasillaCasa(),
							Colores.values()[i].getCasillaFinal()));
				}
				else //las dos restantes cuatro casilla por delante de la de salida
				{
					fichasJuego.add(new Ficha(Colores.values()[i],j + 1,
							Colores.values()[i].getCasillaSalida()+4,
							Colores.values()[i].getCasillaSalida(),
							Colores.values()[i].getCasillaCasa(),
							Colores.values()[i].getCasillaFinal()));
				}
			}
		}
	}
	
	
	@Before
	public void setUp() throws Exception {
		/** 
         * El metodo precedido por la etiqueta @Before 
         * es para indicar a JUnit que debe ejecutarlo 
         * antes de ejecutar los Tests que figuran en 
         * esta clase. 
         */
	}

	@Test
	public void testTablero() {
		Vector<Tablero> vectorTablero = new Vector<Tablero>();
		int[] arrayCasillaSeguro={12, 17, 29, 34, 46, 51, 63, 68};
		int[] arrayCasillaSalida={5, 22, 39, 56};
		int aux;
		
		for (int i=1 ; i<=4 ; i++){
			inicializarArrayFichas(i);
			//Creamos el tablero
			vectorTablero.add(new Tablero(fichasJuego));
			//una vez creado el tablero, empezamos con las comprobaciones.
			
			//primera comprobación es verificar que están creadas todas las casillas, recorriendo una por una.
			
			for (int j=1; j<= 68; j++){
				//miramos si el codigo de la casilla en el hashmap mapea bien el valor de la casilla
				assertEquals("El codigo de la casilla no coincide con el del tablero", vectorTablero.firstElement().getCasilla(j).getNumero(),j);
				if (0 <= Arrays.binarySearch(arrayCasillaSeguro, j)){
					System.out.println(j);
					//revisamos que está configurado como casilla seguro
					assertTrue(vectorTablero.firstElement().getCasilla(j).isEsSeguro());
					assertFalse(vectorTablero.firstElement().getCasilla(j).isEsCasa());
					assertFalse(vectorTablero.firstElement().getCasilla(j).isEsBarrera());
					assertFalse(vectorTablero.firstElement().getCasilla(j).isEsPasillo());
					assertFalse(vectorTablero.firstElement().getCasilla(j).isEsSalida());
					assertFalse(vectorTablero.firstElement().getCasilla(j).isEsUltima());
				}else if (0 <= Arrays.binarySearch(arrayCasillaSalida, j)){
					//realizamos las comprobaciones de las casillas de salida
					System.out.println(j);
					assertTrue(vectorTablero.firstElement().getCasilla(j).isEsSeguro());
					assertFalse(vectorTablero.firstElement().getCasilla(j).isEsCasa());
					assertFalse(vectorTablero.firstElement().getCasilla(j).isEsBarrera());
					assertFalse(vectorTablero.firstElement().getCasilla(j).isEsPasillo());
					assertTrue(vectorTablero.firstElement().getCasilla(j).isEsSalida());
					assertFalse(vectorTablero.firstElement().getCasilla(j).isEsUltima());
				}else {
					//realizamos las comprobaciones de las casillas convencionales
					System.out.println(j);
					assertFalse(vectorTablero.firstElement().getCasilla(j).isEsSeguro());
					assertFalse(vectorTablero.firstElement().getCasilla(j).isEsCasa());
					assertFalse(vectorTablero.firstElement().getCasilla(j).isEsBarrera());
					assertFalse(vectorTablero.firstElement().getCasilla(j).isEsPasillo());
					assertFalse(vectorTablero.firstElement().getCasilla(j).isEsSalida());
					assertFalse(vectorTablero.firstElement().getCasilla(j).isEsUltima());
				}
			}
			
			
			for (int p=1; p<=i ; p++){
				//verificamos la casa
				aux=p*100;
				System.out.println(aux);
				assertEquals("El codigo de la casilla no coincide con el del tablero", vectorTablero.firstElement().getCasilla(aux).getNumero(),aux);
				assertFalse(vectorTablero.firstElement().getCasilla(aux).isEsSeguro());
				assertTrue(vectorTablero.firstElement().getCasilla(aux).isEsCasa());
				assertFalse(vectorTablero.firstElement().getCasilla(aux).isEsBarrera());
				assertFalse(vectorTablero.firstElement().getCasilla(aux).isEsPasillo());
				assertFalse(vectorTablero.firstElement().getCasilla(aux).isEsSalida());
				assertFalse(vectorTablero.firstElement().getCasilla(aux).isEsUltima());
				aux++;
				for (int j=1; j<=7; j++){
					//verificamos pasillo
					System.out.println(aux);
					assertEquals("El codigo de la casilla no coincide con el del tablero", vectorTablero.firstElement().getCasilla(aux).getNumero(),aux);
					assertFalse(vectorTablero.firstElement().getCasilla(aux).isEsSeguro());
					assertFalse(vectorTablero.firstElement().getCasilla(aux).isEsCasa());
					assertFalse(vectorTablero.firstElement().getCasilla(aux).isEsBarrera());
					assertTrue(vectorTablero.firstElement().getCasilla(aux).isEsPasillo());
					assertFalse(vectorTablero.firstElement().getCasilla(aux).isEsSalida());
					assertFalse(vectorTablero.firstElement().getCasilla(aux).isEsUltima());
					aux++;
				}
				
				System.out.println(aux);
				assertEquals("El codigo de la casilla no coincide con el del tablero", vectorTablero.firstElement().getCasilla(aux).getNumero(),aux);
				assertFalse(vectorTablero.firstElement().getCasilla(aux).isEsSeguro());
				assertFalse(vectorTablero.firstElement().getCasilla(aux).isEsCasa());
				assertFalse(vectorTablero.firstElement().getCasilla(aux).isEsBarrera());
				assertTrue(vectorTablero.firstElement().getCasilla(aux).isEsPasillo());
				assertFalse(vectorTablero.firstElement().getCasilla(aux).isEsSalida());
				assertTrue(vectorTablero.firstElement().getCasilla(aux).isEsUltima());

			}
			//eliminamos todos los elementos del tablero y de las fichas para que empiece de nuevo el bucle
			fichasJuego.clear();
			vectorTablero.clear();
		}
	}

	@Test
	public void testEsMovible() {
		inicializarArrayFichas(2);
		//Creamos el tablero
		Tablero tablero = new Tablero(fichasJuego);
		//Ahora comprobamos si se puede mover o no la ficha, sacando un 6 no se podrá mover
		boolean movible;
		//Intentamos sacar la ficha y en la casilla salida hay colocada dos fichas de otro color (barrera)
		tablero.getCasilla(fichasJuego.get(0).getCasillaSalida()).setFicha1(fichasJuego.get(7));
		tablero.getCasilla(fichasJuego.get(0).getCasillaActual()).setFicha2(fichasJuego.get(7));
		tablero.getCasilla(fichasJuego.get(0).getCasillaActual()).setEsBarrera(true);
		tablero.getCasilla(fichasJuego.get(7).getCasillaActual()).setTieneFicha(2);
		movible = tablero.esMovible(fichasJuego.get(1), 5);
		assertTrue(movible);
		//Intentamos sacar ficha y hay una barrera de otro color en la casilla de salida
		fichasJuego.get(1).setCasillaActual(fichasJuego.get(1).getCasillaCasa());
		tablero.getCasilla(fichasJuego.get(0).getCasillaSalida()).setFicha1(fichasJuego.get(3));
		tablero.getCasilla(fichasJuego.get(0).getCasillaSalida()).setFicha2(fichasJuego.get(3));
		tablero.getCasilla(fichasJuego.get(0).getCasillaSalida()).setEsBarrera(true);
		tablero.getCasilla(fichasJuego.get(7).getCasillaSalida()).setTieneFicha(2);
		movible = tablero.esMovible(fichasJuego.get(1), 5);
		assertFalse(movible);
		//Sacamos ficha y aunque no hay barrera hay dos fichas en la casilla salida
		tablero.getCasilla(fichasJuego.get(0).getCasillaSalida()).setFicha1(fichasJuego.get(7));
		tablero.getCasilla(fichasJuego.get(0).getCasillaSalida()).setFicha2(fichasJuego.get(3));
		tablero.getCasilla(fichasJuego.get(0).getCasillaSalida()).setEsBarrera(false);
		tablero.getCasilla(fichasJuego.get(0).getCasillaSalida()).setTieneFicha(2);
		movible = tablero.esMovible(fichasJuego.get(1), 5);
		assertTrue(movible);
		//Movemos ficha y acaba en una casilla con dos fichas (aunque no es una barrera)
		fichasJuego.get(1).setCasillaActual(5);
		tablero.getCasilla(8).setFicha1(fichasJuego.get(3));
		tablero.getCasilla(8).setFicha2(fichasJuego.get(7));
		tablero.getCasilla(8).setTieneFicha(2);
		movible = tablero.esMovible(fichasJuego.get(1), 3);
		assertFalse(movible);
		//Sólo hay una ficha en la casilla final del movimiento y me la zampo :)
		fichasJuego.get(1).setCasillaActual(5);
		tablero.getCasilla(8).setFicha1(fichasJuego.get(7));
		tablero.getCasilla(8).setTieneFicha(1);
		tablero.getCasilla(fichasJuego.get(1).getCasillaActual()+3).setEsSeguro(false);
		movible = tablero.esMovible(fichasJuego.get(1), 3);
		assertTrue(movible);
		//Sólo hay una ficha en la casilla final y comparto seguro :)
		fichasJuego.get(1).setCasillaActual(5);
		tablero.getCasilla(8).setFicha1(fichasJuego.get(7));
		fichasJuego.get(7).setCasillaActual(8);
		tablero.getCasilla(8).setTieneFicha(1);
		tablero.getCasilla(8).setEsSeguro(true);
		movible = tablero.esMovible(fichasJuego.get(1), 3);
		assertTrue(movible);
	}
	@Test
	public void testMueve() throws RemoteException, InterruptedException {
		inicializarArrayFichas(2);
		//Creamos el tablero
		Tablero tablero = new Tablero(fichasJuego);
		Parchis parchis = new Parchis();
		parchis.setUI(ParchisUIMode.GUI, new Locale("es"));//cambio modo GUI
		Jugador jug = new Jugador(tablero, "Jugador1", fichasJuego.get(0).getColor(), fichasJuego);
	
		//creamos una variable para manejar el hilo principal
		Thread t = Thread.currentThread();
		System.out.println("Current Thread: " + t);
		try{
			Thread.sleep(1000);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		
		//Intentamos sacar la ficha y en la casilla salida hay colocada dos fichas de otro color (barrera)
		fichasJuego.get(1).setCasillaActual(fichasJuego.get(1).getCasillaCasa());
		//vista.moverFicha(fichasJuego.get(1), );
		Thread.sleep(1000);
		tablero.getCasilla(fichasJuego.get(0).getCasillaSalida()).setFicha1(fichasJuego.get(7));
		Thread.sleep(1000);
		tablero.getCasilla(fichasJuego.get(0).getCasillaSalida()).setFicha2(fichasJuego.get(6));
		Thread.sleep(1000);
		fichasJuego.get(6).setCasillaActual(fichasJuego.get(0).getCasillaSalida());
		Thread.sleep(1000);
		fichasJuego.get(7).setCasillaActual(fichasJuego.get(0).getCasillaSalida());
		Thread.sleep(1000);
		tablero.getCasilla(fichasJuego.get(0).getCasillaSalida()).setEsBarrera(true);
		Thread.sleep(1000);
		tablero.getCasilla(fichasJuego.get(0).getCasillaSalida()).setTieneFicha(2);
		jug.setTirada(5);
		tablero.mueve(jug, fichasJuego.get(1));
		assertEquals(fichasJuego.get(1).getCasillaActual(), fichasJuego.get(1).getCasillaSalida());
		assertEquals(fichasJuego.get(6).getCasillaActual(), fichasJuego.get(6).getCasillaCasa());
		assertEquals(tablero.getCasilla(fichasJuego.get(1).getCasillaSalida()).getTieneFicha(), 2);
		//Sacamos ficha y aunque no hay barrera hay dos fichas en la casilla salida
		tablero.getCasilla(fichasJuego.get(0).getCasillaSalida()).setFicha1(fichasJuego.get(7));
		tablero.getCasilla(fichasJuego.get(0).getCasillaSalida()).setFicha2(fichasJuego.get(3));
		tablero.getCasilla(fichasJuego.get(0).getCasillaSalida()).setEsBarrera(false);
		tablero.getCasilla(fichasJuego.get(0).getCasillaSalida()).setTieneFicha(2);
		fichasJuego.get(7).setCasillaActual(fichasJuego.get(0).getCasillaSalida());
		fichasJuego.get(3).setCasillaActual(fichasJuego.get(0).getCasillaSalida());
		jug.setTirada(5);
		fichasJuego.get(1).setCasillaActual(fichasJuego.get(1).getCasillaCasa());
		tablero.getCasilla(fichasJuego.get(1).getCasillaCasa()).setTieneFicha(1);
		tablero.mueve(jug, fichasJuego.get(1));
		assertEquals(fichasJuego.get(1).getCasillaActual(), fichasJuego.get(1).getCasillaSalida());
		assertEquals(fichasJuego.get(7).getCasillaActual(), fichasJuego.get(7).getCasillaCasa());
		assertEquals(tablero.getCasilla(fichasJuego.get(0).getCasillaSalida()).getTieneFicha(), 2);
		assertEquals(tablero.getCasilla(fichasJuego.get(0).getCasillaSalida()).getFicha1(), fichasJuego.get(3));
		assertEquals(tablero.getCasilla(fichasJuego.get(0).getCasillaSalida()).getFicha2(), fichasJuego.get(1));
		//Sólo hay una ficha en la casilla final y me la zampo :)
		fichasJuego.get(1).setCasillaActual(5);
		tablero.getCasilla(5).setTieneFicha(1);
		fichasJuego.get(7).setCasillaActual(8);
		tablero.getCasilla(8).setFicha1(fichasJuego.get(7));
		tablero.getCasilla(8).setTieneFicha(1);
		tablero.getCasilla(fichasJuego.get(1).getCasillaActual()+3).setEsSeguro(false);
		jug.setTirada(3);
		tablero.mueve(jug, fichasJuego.get(1));
		assertEquals(fichasJuego.get(1).getCasillaActual(), 8);
		assertEquals(fichasJuego.get(7).getCasillaActual(), fichasJuego.get(7).getCasillaCasa());
		assertEquals(tablero.getCasilla(8).getTieneFicha(), 1);
		assertEquals(tablero.getCasilla(5).getTieneFicha(), 0);
		//Sólo hay una ficha en la casilla final y comparto seguro :)
		fichasJuego.get(1).setCasillaActual(5);
		tablero.getCasilla(5).setTieneFicha(1);
		tablero.getCasilla(8).setFicha1(fichasJuego.get(7));
		fichasJuego.get(7).setCasillaActual(8);
		tablero.getCasilla(8).setTieneFicha(1);
		tablero.getCasilla(8).setEsSeguro(true);
		jug.setTirada(3);
		tablero.mueve(jug, fichasJuego.get(1));
		assertEquals(fichasJuego.get(1).getCasillaActual(), 8);
		assertEquals(fichasJuego.get(7).getCasillaActual(), 8);
		assertEquals(tablero.getCasilla(8).isEsBarrera(), false);
		assertEquals(tablero.getCasilla(8).getTieneFicha(), 2);
		assertEquals(tablero.getCasilla(5).getTieneFicha(), 0);
		//Sólo hay una ficha en la casilla final y como es del mismo color formo barrera :)
		fichasJuego.get(1).setCasillaActual(5);
		tablero.getCasilla(5).setTieneFicha(1);
		tablero.getCasilla(8).setFicha1(fichasJuego.get(3));
		fichasJuego.get(3).setCasillaActual(8);
		tablero.getCasilla(8).setTieneFicha(1);
		tablero.getCasilla(8).setEsSeguro(false);
		jug.setTirada(3);
		tablero.mueve(jug, fichasJuego.get(1));
		assertEquals(fichasJuego.get(1).getCasillaActual(), 8);
		assertEquals(fichasJuego.get(3).getCasillaActual(), 8);
		assertEquals(tablero.getCasilla(8).isEsBarrera(), true);
		assertEquals(tablero.getCasilla(8).getTieneFicha(), 2);
		assertEquals(tablero.getCasilla(5).getTieneFicha(), 0);
	}
}
