package es.uned.master.java.springboot.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class PersonaTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@Test
	void test() {
		Persona persona = new Persona();
		
		persona.setId(1);
		persona.setNombre("Carlos");
		persona.setApellidos("Ruiz");
		assertTrue(persona.getApellidos().equals("Ruiz") && persona.getNombre().equals("Carlos"));
		System.out.println(persona);
	}

}
