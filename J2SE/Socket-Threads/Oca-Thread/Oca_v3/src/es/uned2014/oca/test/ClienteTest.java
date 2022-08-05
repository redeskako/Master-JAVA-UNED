package es.uned2014.oca.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.uned2014.oca.cliente.*;

/**
 * Se define una clase ClienteTest para realizar las pruebas unitarias sobre los módulos de la clase cliente.
 * @author	Alef UNED 2014
 * @version	Oca 2.0
 * @fecha 	Mayo 2014
 */

public class ClienteTest {
	ClienteGui cliente = new ClienteGui(30,80,700,500);
	Cliente cliente2 = new Cliente(cliente);
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	/**
	 * Prueba sobre el boolean estado terminar.
	 */
	@Test
	public void testFin() {
		cliente.actualizarEstadoTerminar(true);
	}
	
	/**
	 * Prueba sobre escribir color del jugador. Hay 6 colores definidos y 
	 * debe cambiar el titulo y el area en el color asginado
	 */
	@Test
	public void testEscribirColor() {
		cliente.escribirColor("AZUL");
	}

	/**
	 * Prueba sobre escribir un mensaje en el textarea
	 */
	@Test
	public void testEscribirMensaje() {
		cliente.escribirMensaje("Mensaje para el cliente");
	}

	/**
	 * Prueba para cerrar el socket
	 */
	@Test
	public void testCerrarSocket(){
		cliente2.cerrarSocket();
	}
	
}
