package es.uned.master.java.springboot.controlador;

import es.uned.master.java.springboot.service.ReunionesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reuniones")
// Le indico a SpringBoot que es una clase controladora
// Aporto la llamada /reuniones para que pueda hacer la llamada
public class ReunionesController {
	@Autowired //Para que lo inyecte al servicio respectivo
			   // Esto asocia al servicio respectivo
	ReunionesService reunionesService;
	// Vamos a crear esta vez un Servicio que nos gestione los datos de manera más independiente
	// Para ello tendremos la clase ReunionesService con un método getAllReuniones
	// Lo inyectamos directamente al modelo.

	@GetMapping
	public String getAllReuniones(Model model){
		model.addAttribute("reuniones", reunionesService.getAllReuniones());
		return "reuniones";
	}
}