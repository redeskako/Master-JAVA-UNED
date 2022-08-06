package es.uned2014.recursosAlpha.serviciosWeb;

import java.io.File;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import es.uned2014.recursosAlpha.auth.Auth;
import es.uned2014.recursosAlpha.conjuntos.Conjunto;
import es.uned2014.recursosAlpha.usuario.Usuario;

/**
 * Servicio web para autorizar acceso a un usuario
 * 
 * @author Alpha UNED 2014
 * @version Recursos Servicio Web 1.0
 * @since November 2016
 */
//Se indica el Path para acceder al SW a trav�s de la URI:
@Path("/accesoUsuario")
public class AccesoUsuario {

	/**
	 * Funcion valida al usuario
	 * 
	 * @param u - nombre de usuario
	 * @param p - contraseña
	 * @return archivo XML Auth, contiene al usuario si existe en la BDD
	 * @throws JAXBException
	 */
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Auth validUser(@QueryParam("u") String username, @QueryParam("p") String password) {
		
		Usuario u = new Usuario();
		Auth auth = new Auth();
		
		// Buscar a un usuario basado (usuario contraseña)
		if(username != null && password != null){
			u = new Usuario().validarUsuario(username, password);
			auth.setUsuario(u);
			auth.generateToken();
			auth.save();
		}
		
		// Se convierte el Conjunto --> XML
		buildAuthXMLFile(auth);
		return auth;
	}
	
	/***
	 * Metodo para crear archivo XML a partir de isntancia Auth
	 * @param auth
	 */
	private void buildAuthXMLFile(Auth auth){
		// Se convierte el Conjunto --> XML
		try {
			JAXBContext context = JAXBContext.newInstance(Conjunto.class);
			Marshaller m = context.createMarshaller();
			File file = new File("auth.xml"); // fichero XML 
			m.marshal(auth, file);	
		} catch (JAXBException e1) {
			e1.printStackTrace();
		}
	}

}
