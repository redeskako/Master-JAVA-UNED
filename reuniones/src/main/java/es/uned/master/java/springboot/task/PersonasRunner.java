package es.uned.master.java.springboot.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import es.uned.master.java.springboot.service.PersonasService;

/*
 * Vamos a crear proceso batch en lotes que nos ayude a trabar en consola
 * Para ello tenemos dos interfaces CommandLineRunner y ApplicationRunner pero en este caso para consola
 * El interfaz obliga a un método 'run' que lo pueda getionar
 * Veamos una Agenda de contactos (personas)
 */
@Component
@Order(1)
public class PersonasRunner implements CommandLineRunner{
	private static final Logger LOG = LoggerFactory.getLogger(PersonasRunner.class);
	
	// Vamos a incluir el servicio Personas para mostrar en consola la agenda
	@Autowired
	private PersonasService personaService;
	
	@Override
	public void run(String... args) throws Exception {
		LOG.info("Agenda de direcciones");
		this.personaService.getAllPersonas().forEach(persona ->{
			LOG.info(persona.toString());
		});
		LOG.info("Fin agenda direcciones");
	}

}
