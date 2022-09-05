package es.uned.servidor;

import java.io.File;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import es.uned.clasesServidor.Usuario;
import es.uned.clasesServidor.UsuarioConjunto;

/**
 * @author	Cristina Morales
 * @version	Pruebas 1.0
 * @since	Septiembre 2014
 */

//Se indica el Path para acceder al SW a través de la URI:
@Path("/usuariosListado1")
public class UsuariosListado1 {
	
	// Método a ejecutar si se pide String a través de un GET:
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public UsuarioConjunto usuariosListadoXml(){
		
		// Se encuentra el listado:
		Usuario u = new Usuario();
		ArrayList<Usuario> array = u.usuariosListado(1);
		
		// Se almacena el listado en un objeto UsuarioConjunto, que contiene el array
		UsuarioConjunto uConjunto = new UsuarioConjunto();
		uConjunto.setListado(array);

			
		// Se convierte Objeto --> XML:
		try {
			JAXBContext context = JAXBContext.newInstance(UsuarioConjunto.class);
			Marshaller m = context.createMarshaller();
			File file = new File("uConjunto.xml");
			m.marshal(uConjunto, file);
			System.out.println(file.getAbsolutePath());
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return uConjunto;	
		
	}
}
