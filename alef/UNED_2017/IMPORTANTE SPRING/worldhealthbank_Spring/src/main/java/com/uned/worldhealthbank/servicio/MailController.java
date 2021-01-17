package com.uned.worldhealthbank.servicio;

import javax.mail.MessagingException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uned.worldhealthbank.domain.User;

@RestController
public class MailController {

	private MailSender mailSender;
	
	
	private String tema = "Complete el registro de su  cuenta";
	
	public MailController(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	@RequestMapping("/mail")
	public String sendMail(User user) throws MessagingException {
		
		String urlActivate = "http://localhost:8080/worldhealthbank/activarUser/"+user.getId();
		
		String cuerpo = "<strong>Bienvenido " + user.getLogin() +", a la práctica "
				+ "worldhealthbank de Spring</strong>. <br><br>Para"
				+ " activar su cuenta debe pulsar en el siguiente "+ "<a href='"+ urlActivate +"'>enlace</a><br>"
				+ " "+urlActivate+" " 
				+ "<br><br>link<br><br><br><br> Atentamente el <i><b>Grupo "
				+ "de la práctica.</b></i> <br>";
		
		
		mailSender.send(user.getEmail(), tema, cuerpo);
		return "Mail enviado";
	}
}

