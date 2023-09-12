package es.uned.master.java.springboot.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import es.uned.master.java.springboot.model.Persona;

/*
 * El respositorio es una interfaz intermedia entre el servicio y el DTO (modelo)
 * Ayuda a la abstracción de los datos y nos ayuda con métodos comunes (findall()) entre otros
 * Además de modelar las colecciones de datos
 */
public interface PersonasRepository extends JpaRepository<Persona, Long> {
	// Debemos tener la instanaciación a la clase Persona con su ID (LONG)
	// NO necesito de momento definer método propios, usaré los del interfaz JpaRepository
}
