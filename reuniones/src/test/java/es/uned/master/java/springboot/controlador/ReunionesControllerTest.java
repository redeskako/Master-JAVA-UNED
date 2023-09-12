package es.uned.master.java.springboot.controlador;

import static org.junit.jupiter.api.Assertions.*;

import java.time.ZonedDateTime;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import es.uned.master.java.springboot.model.Reunion;

class ReunionesControllerTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@Test
	void testCargaDatos() {
		// Lo he creado en protected para validar el método.
		Reunion reunion = new Reunion(Integer.toUnsignedLong(33), 
									  "Este es un asunto test",
									  ZonedDateTime.now());
		ReunionesController.añadeAsistentes(reunion, 3);
		System.out.println(reunion);
	}

}
