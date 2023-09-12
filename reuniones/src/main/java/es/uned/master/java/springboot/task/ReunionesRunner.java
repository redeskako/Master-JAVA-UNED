package es.uned.master.java.springboot.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import es.uned.master.java.springboot.service.ReunionesService;

@Component
public class ReunionesRunner implements ApplicationRunner {
	private static final Logger LOG = LoggerFactory.getLogger(ReunionesRunner.class);

	@Autowired
	private ReunionesService reunionesService;

	public ReunionesRunner(ReunionesService reunionesService) {
		this.reunionesService = reunionesService;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		LOG.info("Agenda de reuniones");
		this.reunionesService.getAllReuniones().forEach(reunion -> {
			LOG.info(reunion.toString());
		});
		LOG.info("No hay más eventos");
	}

}
