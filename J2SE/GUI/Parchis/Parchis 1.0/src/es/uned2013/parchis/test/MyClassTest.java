package es.uned2013.parchis.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.uned2013.parchis.Parchis;

public class MyClassTest {
	
	public Parchis parchis = new Parchis();
	
	@Before  
    public void antesDelTest() {  
        /** 
         * El metodo precedido por la etiqueta @Before 
         * es para indicar a JUnit que debe ejecutarlo 
         * antes de ejecutar los Tests que figuran en 
         * esta clase. 
         */
		parchis.numJugadores(3);
		System.out.println("En prueba.............................");
		//assertEquals(3,parchis.getJugadores().size());
		System.out.println(" " + parchis.getJugadores().size());
		
		//fail("Not yet implemented");
          
    }  

	@Test
	public void testNumJugadores() {
		
	}

	@Test
	public void testElegirPrimero() {
		System.out.println("En prueba.............................");
		//assertEquals(3,parchis.getJugadores().size());
		assertTrue(parchis.getJugadores().size()<1);
		//fail("Not yet implemented");
	}

	@Test
	public void testElegirSiguiente() {
		fail("Not yet implemented");
	}
	
	@After
	public void despuesDelTest() {
		System.out.println("En prueba.............................");
		//assertEquals(3,parchis.getJugadores().size());
		assertTrue(parchis.getJugadores().size()>1);
	}

}
