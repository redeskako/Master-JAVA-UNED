package es.uned.master.java.springboot.controlador;

import es.uned.master.java.springboot.service.ReunionesService;
import es.uned.master.java.springboot.model.Reunion;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reuniones") //Para no colisionar con la llamada web de reuniones
public class ReunionesRestController {
	private ReunionesService reunionesService;
	
	public ReunionesRestController(ReunionesService reunionesService) {
		super ();
		this.reunionesService = reunionesService;
	}
	// Esta vez usando el mismo servicio, ahora toca crear el getAllReuniones
	@GetMapping
	public List<Reunion> getAllReuniones(){
		return this.reunionesService.getAllReuniones();
	}
	// Normalmente puedes usar la herramienta de Chrome de depuración en consola
	// Pero te recomiendo que instales Postman y probemos la petición
	
}
