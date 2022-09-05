package es.uned.servidor;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import es.uned.clasesServidor.Usuario;

/**
 * @author	Cristina Morales
 * @version	Pruebas 1.0
 * @since	Septiembre 2014
 */

//Se indica el Path para acceder al SW a través de la URI:
@Path("/numeroUsuario")
public class NumeroUsuario {
	
	// Método a ejecutar si se pide String a través de un formulario (POST):
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	public String numeroUsuarioPostHtml(@FormParam("num") int i){
		
		Usuario u = new Usuario();
		String sql = "WHERE usu.IdUsuario=" + i ;
		Usuario uEncontrado = u.usuariosBuscar(1, sql).get(0);
		
		String nombreUsuario = uEncontrado.getNombreUsuario();
			
		return "<html> <body><h1> El num. de usuario " + i + " corresponde a <u>" + nombreUsuario + "</u></h1></body>";
		
	}

}
