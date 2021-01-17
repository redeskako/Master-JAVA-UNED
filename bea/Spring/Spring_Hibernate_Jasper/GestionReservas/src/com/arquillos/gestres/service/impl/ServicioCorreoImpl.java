/*
 * 
 * 
 */

package com.arquillos.gestres.service.impl;

import com.arquillos.gestres.data.Reserva;
import com.arquillos.gestres.data.Usuario;
import com.arquillos.gestres.service.ServicioCorreo;
import java.text.MessageFormat;
import java.util.Date;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

/**
 *
 * @author Antonio Jesús Arquillos Álvarez
 */
public class ServicioCorreoImpl implements ServicioCorreo {  
	private MailSender mailSender;
	private String from;
	private String homeUrl;
	private String titulo;

	public void enviarCorreoPrueba(String from, String to) {
		SimpleMailMessage mensaje = new SimpleMailMessage();

		mensaje.setFrom(from);
		mensaje.setTo(to);
		mensaje.setReplyTo(from);
		mensaje.setSentDate(new Date());
		mensaje.setSubject("Prueba correo");
		mensaje.setText("¡Correo de prueba enviado correctamente!");

		mailSender.send(mensaje);
	}

	public MailSender getMailSender() {
		return mailSender;
	}

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void notifReservaAprobada(Reserva reserva) {
		final SimpleMailMessage mensaje = new SimpleMailMessage();
		mensaje.setFrom(from);
		mensaje.setTo(reserva.getSolicitante().getEmail());        
		mensaje.setSentDate(new Date());
		mensaje.setSubject( 
		  MessageFormat.format(
			  "{0} ha aprobado su reserva de {1}.",
				reserva.getAutorizador().getNombre(),
				reserva.getRecurso().getNombre()
			)
		);
		mensaje.setText( 
			MessageFormat.format(
				"Hola, {0}!\n\n"
				+ "{1} ha aprobado su reserva de {2} desde {3} hasta {4}.\n\n"        
				+ "Un saludo,\n{5}\n{6}",
				reserva.getSolicitante().getNombre(),
				reserva.getAutorizador().getNombre(),
				reserva.getRecurso().getNombre(),
				reserva.getInicio(),
				reserva.getFin(),
				titulo,
				homeUrl
			)
		);
		new Thread() {
			@Override
			public void run() {        
				mailSender.send(mensaje);
			}
    }.start();
	}

	public void notifReservaCreada( Reserva reserva ) {
		// notificamos a los autorizadores       
		Usuario[] usuarios = { 
			reserva.getSolicitante().getAutorizador(), 
			reserva.getRecurso().getAutorizador() 
		};    
		// no se debe notificar dos veces a un mismo autorizador
		if (usuarios[0] == usuarios[1])
			usuarios[1] = null;
 
		for (int i = 0; i < usuarios.length; i++) {
			Usuario usuario = usuarios[i];
			if (usuario != null && !reserva.getSolicitante().equals(usuario)) {
				final SimpleMailMessage mensaje = new SimpleMailMessage();
				mensaje.setFrom(from);
				mensaje.setTo(usuario.getEmail());        
				mensaje.setSentDate(new Date());
				mensaje.setSubject( 
					MessageFormat.format(
						"{0} ha reservado {1} y necesita su aprobación.",
						reserva.getSolicitante().getNombre(),
						reserva.getRecurso().getNombre() 
					)
				);
				mensaje.setText( 					
					MessageFormat.format(
						"Hola, {0}!\n\n" +
						"{1} ha solicitado {2} desde {3} hasta {4}.\n\n" +
						"Por favor, proceda a su aprobación o denegación enviándola a:" +
						"\n{5}\n" +
						"Un saludo,\n{6}",
						usuario.getNombre(),
						reserva.getSolicitante().getNombre(),
						reserva.getRecurso().getNombre(),
						reserva.getInicio(),
						reserva.getFin(),
						homeUrl,
						titulo 
					)
				);        
				new Thread() {
					@Override
					public void run() {        
						mailSender.send(mensaje);
					}
				}.start();
			}
		}    
	}

	public void notifReservaDenegada(Reserva reserva) {
		final SimpleMailMessage mensaje = new SimpleMailMessage();
		mensaje.setFrom(from);
		mensaje.setTo(reserva.getSolicitante().getEmail());        
		mensaje.setSentDate(new Date());
		mensaje.setSubject( 
			MessageFormat.format(
				"Su reserva de {1} ha sido denegada por {0}.",
				reserva.getAutorizador().getNombre(),
				reserva.getRecurso().getNombre() 
			) 
		);
		mensaje.setText( 
			MessageFormat.format(
				"Hola, {0}!\n\n" +
				"{1} ha denegado su solicitud de {2} desde {3} hasta {4}.\n\n" +        
				"Un saludo,\n{5}\n{6}",
				reserva.getSolicitante().getNombre(),
				reserva.getAutorizador().getNombre(),
				reserva.getRecurso().getNombre(),
				reserva.getInicio(),
				reserva.getFin(),
				titulo,
				homeUrl 
			)
		);
		new Thread() {
			@Override
			public void run() {        
				mailSender.send(mensaje);
			}
    }.start();
	}

	public String getFrom() {
		return from;
	}

	public void setFrom( String from ) {
		this.from = from;
	}

	public String getHomeUrl() {
		return homeUrl;
	}

	public void setHomeUrl( String homeUrl ) {
		this.homeUrl = homeUrl;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo( String titulo ) {
		this.titulo = titulo;
	}
}