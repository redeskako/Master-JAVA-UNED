package es.uned.servidor;

import java.io.File;
import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.uned.clasesServidor.Usuario;

/**
 * @author	Cristina Morales
 * @version	Pruebas 1.0
 * @since	Septiembre 2014
 */

//Se indica el Path para acceder al SW a través de la URI:
@Path("/numeroUsuario3")
public class NumeroUsuario3 {
	
	// Método a ejecutar si se pide String a través de un formulario (POST):
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Usuario numeroUsuarioPostXml(@QueryParam("num") int i){
		
		System.out.println("Se recibe parámetro en servidor: " + i);	
		
		// Se encuentra el empleado solicitado:
		Usuario u = new Usuario();
		String sql = "WHERE usu.IdUsuario=" + i ;
		Usuario uEncontrado = u.usuariosBuscar(1, sql).get(0);
		System.out.println("El servidor encuentra al usuario " + uEncontrado.getNombreUsuario());	
		
		// Se convierte Objeto --> JSON:
		ObjectMapper mapper = new ObjectMapper();
		try {
			File file = new File("uEncontrado.json");
			mapper.writeValue(file, uEncontrado);
			System.out.println(file.getAbsolutePath());
			
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		return uEncontrado;			
	}

}
