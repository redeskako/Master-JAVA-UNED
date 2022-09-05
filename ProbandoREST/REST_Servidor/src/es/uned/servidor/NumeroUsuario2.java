package es.uned.servidor;

import java.io.File;

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

import es.uned.clasesServidor.Usuario;

/**
 * @author	Cristina Morales
 * @version	Pruebas 1.0
 * @since	Septiembre 2014
 */

//Se indica el Path para acceder al SW a través de la URI:
@Path("/numeroUsuario2")
public class NumeroUsuario2 {
	
	// Método a ejecutar si se pide String a través de un GET:
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Usuario numeroUsuarioPostXml(@QueryParam("num") int i){
		
		// Se encuentra el empleado solicitado:
		Usuario u = new Usuario();
		String sql = "WHERE usu.IdUsuario=" + i ;
		Usuario uEncontrado = u.usuariosBuscar(1, sql).get(0);
			
		// Se convierte Objeto --> XML:
		try {
			JAXBContext context = JAXBContext.newInstance(Usuario.class);
			Marshaller m = context.createMarshaller();
			File file = new File("uEncontrado.xml");
			m.marshal(uEncontrado, file);
			System.out.println(file.getAbsolutePath());
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return uEncontrado;	
		
	}

}
