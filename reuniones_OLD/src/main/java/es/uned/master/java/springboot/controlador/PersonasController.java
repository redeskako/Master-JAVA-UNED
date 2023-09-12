package es.uned.master.java.springboot.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import es.uned.master.java.springboot.model.Persona;
import es.uned.master.java.springboot.service.PersonasService;

@Controller
@RequestMapping("/personas")
public class PersonasController {
	@Autowired
	private PersonasService personasService;

	@GetMapping
	public String getAllPersonas(Model model) {
		model.addAttribute("personas", personasService.getAllPersonas()); 
		//Llamamos al método del servicio público
		return "personas";
	}
}