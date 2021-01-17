package es.uned2013.parchis.test;

import static org.junit.Assert.*;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Locale;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import es.uned2013.parchis.Parchis;
import es.uned2013.parchis.Colores;
import es.uned2013.parchis.Jugador;
import es.uned2013.parchis.Tablero;
import es.uned2013.parchis.Casilla;
import es.uned2013.parchis.Ficha;

import es.uned2013.parchis.ui.ParchisUIMode;

public class JugadorTest {
	
	private Jugador jug0, jug1; // Jugadores para la prueba
	private Tablero tab; // Tablero para la prueba.
	// ArrayList con todas las fichas para la prueba,
	// en total 8 fichas (4 por cada jugador).
	private ArrayList<Ficha> fichas = new ArrayList<Ficha>();
	
	/** Antes de iniciar el test se crea la interfaz
	 * de usuario de la clase Parchis, las fichas, 
	 * el tablero, y los jugadores
	 */
	@Before
	public void setUP() {
		
		Parchis parchis = new Parchis(); // Objeto Parchis vacío para 
		                                 // inicializar la interfaz
		                                 // de usuario
		Colores color; // Variable auxiliar  de tipo Colores
		Ficha fic; // Variable auxiliar de tipo Ficha
		int numCasillaActual; // Número de la casilla actual de la ficha
		
		// Antes de iniciar el test se crea la
		// interfaz de usuario de la clase Parchis desde
		// un objeto de tipo Parchis vacío, ya que para
		// probar la clase Jugador no es necesario crear
		// el parchís completo.
		parchis.setUI(ParchisUIMode.CONSOLE, new Locale("es"));
		
		// Creamos 8 objetos de tipo Ficha y los 
		// añadimos al array 'fichas' (4 de color AMARILLO
		// y 4 de color AZUL).
		for (int i = 0; i < 8; i++) {
			color = Colores.getColor(i / 4);
			if ((i % 4) == 0)
			   // La primera ficha de cada color en la salida
				numCasillaActual = color.getCasillaSalida();
			else
			   // La 2ª, 3ª y 4ª ficha de cada color en casa
				numCasillaActual = color.getCasillaCasa();
			fic = new Ficha(color, i + 1, numCasillaActual,
					color.getCasillaSalida(), color.getCasillaCasa(),
					color.getCasillaFinal());
			fichas.add(fic);
		}
		
		// Creamos el tablero, y le pasamos las 8 fichas
		// como parámetro
		tab = new Tablero(fichas);
		
		// Creamos un jugador AMARILLO y uno AZUL, 
		// pasándoles como parámetro el tablero y las fichas
		// (reciben todas, e internamente asignan solo las de su color. 
		jug0 = new Jugador(tab, Colores.AMARILLO.toString(), 
				Colores.AMARILLO, fichas);
		jug1 = new Jugador(tab, Colores.AZUL.toString(),
				Colores.AZUL, fichas);
		
		// Come estamos en modo LOCAL, asociamos a los jugadores el UI 
		// del parchís.
		jug0.setUI(Parchis.getUI());
		jug1.setUI(Parchis.getUI());
	};
	
	/** Método que coloca todas las fichas
	 * en sus casillas de casa correspondientes
	 * para reiniciar las pruebas.
	 * @throws RemoteException 
	 */
	@Ignore
	public void colocarFichasEnCasa() throws RemoteException {
		// Variables de tipo ArrayList para referenciar
		// a las fichas del jugador amarillo y azul
		ArrayList<Ficha> fichas0 = jug0.getFichas();
		ArrayList<Ficha> fichas1 = jug1.getFichas();
		
		Ficha fic;
		
		System.out.println("\nColocando fichas en casa:");
		// Mostramos un mensaje y Recorremos las 4 fichas 
		// de cada color. Si alguna de ellas no está en la 
		// casilla de casa, la mandamos allí.
		for (int i = 0; i < 4; i++) {
			fic = fichas0.get(i);
			if (fic.getCasillaActual() != fic.getCasillaCasa())
				tab.fichaComida(fic);
			fic = fichas1.get(i);
			if (fic.getCasillaActual() != fic.getCasillaCasa())
				tab.fichaComida(fic);
		}
	}
	
	/** Método para mostrar un encabezado indicando el número
	 * de prueba y un texto explicando brevemente las
	 * comprobaciones que realiza.
	 * @param numPrueba: int -> Número de la prueba
	 * @param texto: String -> Explicación de la misma
	 */
	@Ignore
	public void mostrarEncabezadoPrueba(int numPrueba, String texto) {
		System.out.println("\n<<<<< PRUEBA " + numPrueba + " >>>>>" + 
				"\n" + texto + "\n------------------------------------" +
				"----------------");
	}
	
	/** Método para mostrar por la consola la
	 * posición de una de las fichas dentro de
	 * la prueba unitaria.
	 * @param fic: Ficha
	 */
	@Ignore
	public void mostrarPosicionFicha(Ficha fic) {
		System.out.println("\nFicha " + fic.getIdentificador() + 
				" de color " + fic.getColor().toString() + 
				" en casilla " + fic.getCasillaActual());
	}
	
	/** Método para mostrar por la consola el
	 * resultado de la tirada del jugador
	 * dentro de la prueba unitaria.
	 * @param jug: Jugador (jug0 o jug1)
	 */
	@Ignore
	public void mostrarTirada(Jugador jug) {
		System.out.println("\nEl jugador " + jug.getNombre() +
				" ha sacado un " + jug.getTirada());
	}

	/** Método que testea el método 'mover()' de la clase Jugador
	 * con distintas disposiciones de las fichas y puntuaciones
	 * obtenidas en la tirada del dado.
	 * @throws RemoteException 
	 */
	@Test
	public final void testMover() throws RemoteException {
		// Variables de tipo ArrayList para referenciar
		// a las fichas del jugador amarillo y azul
		// por separado.
		ArrayList<Ficha> fichas0 = jug0.getFichas();
		ArrayList<Ficha> fichas1 = jug1.getFichas();
		
		// Números de las casillas de casa y salida
		// del color amarillo y azul.
		int numCasillaCasa0 = Colores.AMARILLO.getCasillaCasa(),
			numCasillaSalida0 = Colores.AMARILLO.getCasillaSalida(),   
			numCasillaCasa1 = Colores.AZUL.getCasillaCasa(),
			numCasillaSalida1 = Colores.AZUL.getCasillaSalida();
		
		Casilla cas; // Variable auxiliar de tipo Casilla;
		Ficha fic;   // Variable auxiliar de tipo Ficha.
		
		/* PRUEBA 1 */
		
		mostrarEncabezadoPrueba(1, 
				"Se comprueba que, si ya hay 2 fichas del color del\n" +
				"jugador formando barrera en su casilla de salida, no\n" +
				"se puede sacar una tercera aunque se obtenga un 5 con\n" +
				"la tirada del dado, teniendo que avanzar en su lugar\n" + 
				"5 posiciones con alguna de las fichas movibles.");
		
		// Seleccionamos la primera ficha amarilla, 
		// que inicialmente debe estar en la casilla de
		// salida de su color. Lo comprobamos.
		fic = fichas0.get(0);
		mostrarPosicionFicha(fic);
		assertEquals(fic.getCasillaActual(), numCasillaSalida0);
		
		// El jugador AMARILLO obtiene un 5 con el dado.
		// Llamamos a mover() y comprobamos que la segunda 
		// ficha amarilla se ha movido a su casilla de salida, 
		// habiendo ahora 2 fichas en esa casilla y formándose 
		// una barrera.
		jug0.setTirada(5);
		mostrarTirada(jug0);
		jug0.mover();
		fic = fichas0.get(1);
		mostrarPosicionFicha(fic);
		assertEquals(fic.getCasillaActual(), numCasillaSalida0);
		cas = tab.getCasilla(numCasillaSalida0);
		assertEquals(cas.getTieneFicha(), 2);
		assertTrue(cas.isEsBarrera());
		
		// Tratamos de sacar la tercera ficha amarilla
		// con la misma tirada (un 5), comprobando que
		// no la moverá de la casilla de casa al haber
		// ya 2 fichas en la casilla de salida, solicitando
		// en su lugar al jugador que mueva alguna de ellas,
		// ya que son las únicas que puede mover.
		mostrarTirada(jug0);
		jug0.mover();
		fic = fichas0.get(2);
		mostrarPosicionFicha(fic);
		assertEquals(fic.getCasillaActual(), numCasillaCasa0);
		cas = tab.getCasilla(numCasillaSalida0);
		assertEquals(cas.getTieneFicha(), 1);
		assertTrue(!(cas.isEsBarrera()));
		// Seleccionamos la casilla cuyo identificador se obtiene
		// sumando 5 al de la casilla de salida del color amarillo,
		// (casilla nº 10) y comprobamos que ahí también hay una ficha.
		cas = tab.getCasilla(numCasillaSalida0 + 5);
		assertEquals(cas.getTieneFicha(), 1);
		
		// Reiniciamos la prueba colocando todas las fichas en casa.
		colocarFichasEnCasa();
		
		/* PRUEBA 2 */
		
		mostrarEncabezadoPrueba(2,
				"Se comprueba que, habiendo 2 fichas en la casilla de\n" +
				"salida de un color y siendo al menos una de ellas de\n" +
				"color distinto al suyo, si el jugador obtiene un 5 en\n" +
				"la tirada del dado, sacará ficha capturando la última\n" +
				"distinta que ocupó la casilla de salida, y enviándola\n" + 
				"a la casilla de casa del rival correspondiente.");
		
		// El jugagor AZUL obtiene un 5 con el dado y 
		// saca la primera ficha azul.
		jug1.setTirada(5);
		mostrarTirada(jug1);
		jug1.mover();
		fic = fichas1.get(0);
		mostrarPosicionFicha(fic);
		assertEquals(fic.getCasillaActual(), numCasillaSalida1);
		
		// Se establecen 51 avances y la mueve a la casilla 
		// de salida del color AMARILLO (casilla nº 5).
		jug1.setTirada(51);
		mostrarTirada(jug1);
		jug1.mover();
		mostrarPosicionFicha(fic);
		assertEquals(fic.getCasillaActual(), numCasillaSalida0);
		
		// Vuelve a sacar un 5 y saca la segunda ficha azul.
		jug1.setTirada(5);
		mostrarTirada(jug1);
		jug1.mover();
		fic = fichas1.get(1);
		assertEquals(fic.getCasillaActual(), numCasillaSalida1);
		
		// Se establecen otros 51 avances para que también
		// ocupe la casilla de salida del color AMARILLO,
		// donde habrán 2 fichas azules formando barrera.
		// Esta vez se llama al método 'mueve' de tablero
		// para forzar el movimiento de esa ficha.
		jug1.setTirada(51);
		mostrarTirada(jug1);
		tab.mueve(jug1, fic);
		mostrarPosicionFicha(fic);
		assertEquals(fic.getCasillaActual(), numCasillaSalida0);
		
		// El jugador AMARILLO obtiene un 5 con el dado. Como hay 2 
		// fichas azules en su casilla de salida, captura a la última
		// en llegar (la ficha 6) enviándola a su casa, y saca la 
		// primera ficha amarilla, quedando una ficha de cada color
		// en la casilla de salida del color amarillo.
		jug0.setTirada(5);
		mostrarTirada(jug0);
		jug0.mover();
		// Seleccionamos la segunda ficha azul,
		// y comprobamos que está en su casilla de casa
		fic = fichas1.get(1);
		mostrarPosicionFicha(fic);
		assertEquals(fic.getCasillaActual(), numCasillaCasa1);
		// Seleccionamos la primera ficha amarilla,
		// y comprobamos que está en su casilla de salida.
		fic = fichas0.get(0);
		mostrarPosicionFicha(fic);
		assertEquals(fic.getCasillaActual(), numCasillaSalida0);
		// Comprobamos que la casilla de salida del color
		// AMARILLO tiene 2 fichas, siendo la primera en la
		// casilla de color azul y la segunda de color amarillo.
		// Son, respectivamente, la 5 y la 1.
		cas = tab.getCasilla(numCasillaSalida0);
		assertEquals(cas.getTieneFicha(), 2);
		fic = cas.getFicha1();
		assertEquals(fic.getColor(), Colores.AZUL);
		assertEquals(fic.getIdentificador(), 5);
		fic = cas.getFicha2();
		assertEquals(fic.getColor(), Colores.AMARILLO);
		assertEquals(fic.getIdentificador(), 1);
		
		// Reiniciamos la prueba colocando todas las fichas en casa.
		colocarFichasEnCasa();
			
		/* PRUEBA 3 */
		
		mostrarEncabezadoPrueba(3, 
				"Se comprueba que, si 2 fichas movibles del mismo color\n" +
				"del jugador están formando barrera en alguna casilla, en\n" +
				"caso de obtener el jugador un 6 (o un 7) con la tirada del\n" +
				"dado se le obligará siempre a romper esa barrera aunque\n" +
				"existan otras fichas movibles.");
		
		// El jugagor AMARILLO obtiene un 5 con el dado y 
		// saca la primera ficha amarilla.
		jug0.setTirada(5);
		mostrarTirada(jug0);
		jug0.mover();
		fic = fichas0.get(0);
		mostrarPosicionFicha(fic);
		assertEquals(fic.getCasillaActual(), numCasillaSalida0);
		
		// Se establecen 10 avances y la mueve a la casilla nº 15
		// (que es una casilla ordinaria).
		jug0.setTirada(10);
		mostrarTirada(jug0);
		jug0.mover();
		mostrarPosicionFicha(fic);
		assertEquals(fic.getCasillaActual(), numCasillaSalida0 + 10);
		
		// Vuelve a obtener un 5 y saca la segunda ficha amarilla.
		jug0.setTirada(5);
		mostrarTirada(jug0);
		jug0.mover();
		fic = fichas0.get(1);
		mostrarPosicionFicha(fic);
		assertEquals(fic.getCasillaActual(), numCasillaSalida0);
		
		// Se coloca también en la casilla nº 15 (llamando 
		// directamente al método 'mueve' del tablero para
		// forzar el movimiento de esa ficha), consiguiendo 
		// formar una barrera de 2 fichas amarillas.
		jug0.setTirada(10);
		mostrarTirada(jug0);
		tab.mueve(jug0, fic);
		mostrarPosicionFicha(fic);
		assertEquals(fic.getCasillaActual(), numCasillaSalida0 + 10);
		// Seleccionamos la casilla nº 15, comprobando que hay
		// 2 fichas y forman barrera
		cas = tab.getCasilla(numCasillaSalida0 + 10);
		assertEquals(cas.getTieneFicha(), 2);
		assertTrue(cas.isEsBarrera());
		
		// Vuelve a obtener un 5 y saca la tercera ficha amarilla.
		jug0.setTirada(5);
		mostrarTirada(jug0);
		jug0.mover();
		fic = fichas0.get(2);
		mostrarPosicionFicha(fic);
		assertEquals(fic.getCasillaActual(), numCasillaSalida0);
		
		// Finalmente, obtiene un 6, obligando al jugador a 
		// romper la barrera de la casilla nº 15, sin posibilidad 
		// de seleccionar la tercera ficha amarilla, que se quedará 
		// en la casilla de salida.
		jug0.setTirada(6);
		mostrarTirada(jug0);
		jug0.mover();
		fic = fichas0.get(2);
		mostrarPosicionFicha(fic);
		assertEquals(fic.getCasillaActual(), numCasillaSalida0);
		// Seleccionamos la casilla nº 15, comprobando
		// que ahí solo queda una ficha, y ya no hay barrera.
		cas = tab.getCasilla(numCasillaSalida0 + 10);
		assertEquals(cas.getTieneFicha(), 1);
		assertTrue(!(cas.isEsBarrera()));
		// Seleccionamos la casilla nº 21 y comprobamos que 
		// ahí también hay una ficha (la que se ha movido
		// desde la barrera).
		cas = tab.getCasilla(cas.getNumero() + 6);
		assertEquals(cas.getTieneFicha(), 1);
		
		// Reiniciamos la prueba colocando todas las fichas en casa.
		colocarFichasEnCasa();
		
		/* PRUEBA 4 */
		
		mostrarEncabezadoPrueba(4, 
				"Se comprueba que, si 2 fichas del mismo color del jugador\n" +
				"que no se pueden mover están formando barrera en alguna\n" +
				"casilla, en caso de obtener el jugador un 7 (o un 6) con\n" +
				"la tirada del dado y no ser posible romper esa barrera,\n" +
				"tendrá que mover, si existe, otra ficha que sí sea movible.");
		
		// El jugador AMARILLO obtiene un 5 con el dado y
		// saca la primera ficha amarilla.
		jug0.setTirada(5);
		mostrarTirada(jug0);
		jug0.mover();
		fic = fichas0.get(0);
		mostrarPosicionFicha(fic);
		assertEquals(fic.getCasillaActual(), numCasillaSalida0);
		
		// Se establecen 12 avances y la mueve a la casilla nº 17
		// (casilla de conexión con el pasillo del color AZUL).
		jug0.setTirada(12);
		mostrarTirada(jug0);
		jug0.mover();
		mostrarPosicionFicha(fic);
		assertEquals(fic.getCasillaActual(), 
				Colores.AZUL.getCasillaConexionPasillo());
		
		// El jugagor AZUL obtiene un 5 con el dado y 
		// saca la primera ficha azul.
		jug1.setTirada(5);
		mostrarTirada(jug1);
		jug1.mover();
		fic = fichas1.get(0);
		mostrarPosicionFicha(fic);
		assertEquals(fic.getCasillaActual(), numCasillaSalida1);
		
		// Se establecen 62 avances y la mueve a la casilla nº 16
		// (11 casillas por delante de la casilla de salida del 
		// color AMARILLO).
		jug1.setTirada(62);
		mostrarTirada(jug1);
		jug1.mover();
		mostrarPosicionFicha(fic);
		assertEquals(fic.getCasillaActual(), numCasillaSalida0 + 11);
		
		// Vuelve a obtener un 5 y saca la segunda ficha azul.
		jug1.setTirada(5);
		mostrarTirada(jug1);
		jug1.mover();
		fic = fichas1.get(1);
		mostrarPosicionFicha(fic);
		assertEquals(fic.getCasillaActual(), numCasillaSalida1);
		
		// Se coloca también en la casilla nº 16 (llamando 
		// directamente al método 'mueve' del tablero para
		// forzar el movimiento de esa ficha), consiguiendo 
		// formar una barrera de 2 fichas azules.
		jug1.setTirada(62);
		mostrarTirada(jug1);
		tab.mueve(jug1, fic);
		mostrarPosicionFicha(fic);
		assertEquals(fic.getCasillaActual(), numCasillaSalida0 + 11);
		// Seleccionamos la casilla nº 16, comprobando que hay
		// 2 fichas y forman barrera
		cas = tab.getCasilla(numCasillaSalida0 + 11);
		assertEquals(cas.getTieneFicha(), 2);
		assertTrue(cas.isEsBarrera());
		
		// El jugador AMARILLO obtiene un 5 con el dado y
		// saca la segunda ficha amarilla.
		jug0.setTirada(5);
		mostrarTirada(jug0);
		jug0.mover();
		fic = fichas0.get(1);
		mostrarPosicionFicha(fic);
		assertEquals(fic.getCasillaActual(), numCasillaSalida0);
		
		// Se establecen 4 avances y la mueve a la casilla nº 9
		// (llamando directamente al método 'mueve' del tablero).
		jug0.setTirada(4);
		mostrarTirada(jug0);
		tab.mueve(jug0, fic);
		mostrarPosicionFicha(fic);
		assertEquals(fic.getCasillaActual(), numCasillaSalida0 + 4);
		
		// Vuelve a obtener un 5 y saca la tercera ficha amarilla.
		jug0.setTirada(5);
		mostrarTirada(jug0);
		jug0.mover();
		fic = fichas0.get(2);
		mostrarPosicionFicha(fic);
		assertEquals(fic.getCasillaActual(), numCasillaSalida0);
		
		// Se coloca también en la casilla nº 9 (llamando 
		// directamente al método 'mueve' del tablero), 
		// consiguiendo formar una barrera de 2 fichas amarillas.
		jug0.setTirada(4);
		mostrarTirada(jug0);
		tab.mueve(jug0, fic);
		mostrarPosicionFicha(fic);
		assertEquals(fic.getCasillaActual(), numCasillaSalida0 + 4);
		// Seleccionamos la casilla nº 9, comprobando que hay
		// 2 fichas y forman barrera.
		cas = tab.getCasilla(numCasillaSalida0 + 4);
		assertEquals(cas.getTieneFicha(), 2);
		assertTrue(cas.isEsBarrera());
		
		// Finalmente, el jugador AMARILLO obtiene un 7, 
		// teniendo que mover la ficha amarilla situada en 
		// la casilla nº 17 por imposibilidad de romper la 
		// barrera formada en la casilla nº 9, ya que existe 
		// otra barrera de fichas azules que se lo impide.
		jug0.setTirada(7);
		mostrarTirada(jug0);
		jug0.mover();
		// Comprobamos que se ha movido la primera ficha
		// amarilla a la casilla nº 24.
		fic = fichas0.get(0);
		mostrarPosicionFicha(fic);
		assertEquals(fic.getCasillaActual(),
				Colores.AZUL.getCasillaConexionPasillo() + 7);
		// Seleccionamos la casilla nº 9, comprobando
		// que sigue existiendo la barrera de fichas
		// amarillas.
		cas = tab.getCasilla(numCasillaSalida0 + 4);
		assertEquals(cas.getTieneFicha(), 2);
		assertTrue(cas.isEsBarrera());
	}
}
