package es.uned.servidor;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * @author	Cristina Morales
 * @version	Pruebas 1.0
 * @since	Septiembre 2014
 */

// Se indica el Path para acceder al SW a trav�s de la URI:
@Path("/calculadora")
public class Calculadora {
	
	// M�todo a ejecutar si se pide String a trav�s de un GET:
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sumarGet(@QueryParam("a") int a, @QueryParam("b") int b){
				
		int suma = a+b;
		return ("El resultado de sumar " + a + " + " + b + " es " + suma);
	}
	
	// M�todo a ejecutar si se pide String a trav�s de un formulario (POST):
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	public String sumarPost(@FormParam("suma_a") int a, @FormParam("suma_b") int b, @FormParam("suma_c") int c, @FormParam("suma_d") int d){
		int suma = a+b+c+d;
		
		return "<html> <body><h1> El resultado de sumar " + a + " + " + b + " + " + c + " + " + d + " es " + suma + "</h1></body>";
		
	}

}
