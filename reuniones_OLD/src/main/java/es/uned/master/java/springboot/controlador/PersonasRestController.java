package es.uned.master.java.springboot.controlador;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.uned.master.java.springboot.model.Persona;
import es.uned.master.java.springboot.service.PersonasService;

@RestController
@RequestMapping("/api/personas")
public class PersonasRestController {
	private PersonasService personasService;
	public PersonasRestController(PersonasService personasService) {
		this.personasService = personasService;
	}
	
	@GetMapping
	public List<Persona> getAllPersonas(){
		return this.personasService.getAllPersonas();
	}
}
