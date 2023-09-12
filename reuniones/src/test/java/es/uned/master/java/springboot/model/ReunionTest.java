package es.uned.master.java.springboot.model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ReunionTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@Test
	void testLombokReunion() {
		//Probemos Lombok es ok
		Reunion reunion = new Reunion();

		reunion.setId(Integer.toUnsignedLong(33));
		reunion.setAsunto("Esta es la primera cita");
		reunion.setFecha(ZonedDateTime.now());
		//Probemos que todo ok
		// Esto es una chapuza, lo suyo es modularizar pero la idea es mostrarlo

		for (int i=1; i<5; i++) {
			reunion.addAsistente(new Persona(i, "Nombre " + i, "Apellidos " + i));
		}
		//Pruebo que funciona correctamente
		System.out.println("Reunion con asistentes usado ADD " + reunion);

		List<Persona> personas = new ArrayList<>();
		for (int i=5; i<10; i++) {
			personas.add(new Persona(i, "Nombre " + i, "Apellidos " + i));
		}
		reunion.setAsistentes(personas);
		System.out.println("Reunion con asistentes usando SET " + reunion);
	}
}