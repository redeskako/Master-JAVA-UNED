package es.uned.master.java.springboot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import es.uned.master.java.springboot.model.Persona;
import es.uned.master.java.springboot.repositorios.PersonasRepository;

@Service
public class PersonasService {
	private PersonasRepository personasRepository;

	// Creamos un constructor para inyectarle a personasRepository
	public PersonasService(PersonasRepository personasRepository) {
		this.personasRepository = personasRepository;
	}
	
	public List<Persona> getAllPersonas(){
		// Debo adecuar este método al que nos incorpora JpaRepository
		return this.personasRepository.findAll();
	}
}