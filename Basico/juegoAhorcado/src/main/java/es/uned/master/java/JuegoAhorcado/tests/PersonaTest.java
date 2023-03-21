package es.uned.master.java.JuegoAhorcado.tests;

import es.uned.master.java.juegoAhorcado.base.Persona;
import static org.junit.Assert.*;

import org.junit.Test;

public class PersonaTest {

	@Test
	public void test() {
		Persona p = new Persona("44", "Fernando", "Sanchez");
		assertEquals("44", p.getDni());

		p.setDni("33");
		p.setApellidos("Carlos");
		p.setNombre("Pepido");
		
		assertEquals("33", p.getDni());
		assertEquals("Carlos", p.getApellidos());
		assertEquals("Pepido", p.getNombre());
		

	}

}
