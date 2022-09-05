package es.uned.servidor;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * @author	Cristina Morales
 * @version	Pruebas 1.0
 * @since	Septiembre 2014
 */

//Se indica el Path para acceder al SW a trav�s de la URI:
@Path("/saludo2")
public class Saludo2 {

	// M�todo a ejecutar si se pide String a trav�s de un GET.
	// Con @QueryParam se recuperan los par�metros incrustados en la URL
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String saludar2(@QueryParam("nombre") String s){

		return ("Hola, " + s + ". Bienvenido!");
	}
}
