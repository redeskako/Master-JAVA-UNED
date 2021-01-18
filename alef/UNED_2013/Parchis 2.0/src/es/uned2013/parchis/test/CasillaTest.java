package es.uned2013.parchis.test;

import static org.junit.Assert.*;

import org.junit.Test;
import es.uned2013.parchis.Ficha;
import es.uned2013.parchis.Colores;
import es.uned2013.parchis.Casilla;
/**
 * Para crear la clase de Test desde Eclipse sobre una clase determinada
 * 1 Se seleciona la clase
 * 2 Desplegar menu con boton derecho mouse
 * 3 New -->Other-->JUnit-->Junit Test Case
 * 4 Si es la primera vez pedira si queremos realizar una descarga, decimos que si
 * 5 Nos creara una clase y aparecera un menu donde podemos seleccionar los metodos a probar
 * @author Paco
 *
 */
public class CasillaTest {
	Colores color;
	int i= 0;
	Ficha ficha1 = new Ficha(Colores.AMARILLO , 1, 1, 5, 100, 108);
	Ficha ficha2 = new Ficha(Colores.VERDE, i, 1, 5, 100, 108);
	/*
	 * (Colores color, int identificador, int casillaActual,
		int casillaSalida, int casillaCasa, int casillaFinal)
	 */
	
	Casilla casilla = new Casilla(color, 1, false, false, true, false, false, false, 1, ficha1 );
	
	@Test
	public void testGetTieneFicha() {
		int expResult=0;
		int resultado = casilla.getTieneFicha();
		System.out.println("casilla.getTieneFicha() = " + resultado);
		assertNotSame(expResult, resultado);
		//fail("Not yet implemented");
	}

	@Test
	public void testSetTieneFicha() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetFicha1() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetFicha1() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetFicha2() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetFicha2() {
		fail("Not yet implemented");
	}

	@Test
	public void testColocarFicha() {
		fail("Not yet implemented");
	}

	@Test
	public void testQuitarFicha() {
		fail("Not yet implemented");
	}

}
