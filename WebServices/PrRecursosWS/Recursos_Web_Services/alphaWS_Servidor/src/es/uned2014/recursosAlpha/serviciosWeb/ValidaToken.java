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

/**
 * Servicio Web para comprobar tokens de usuario del servidor cliente
 * 
 * @author Alpha UNED 2014
 * @version Recursos Servicio Web 1.0
 * @since November 2016
 */
//Se indica el Path para acceder al SW a trav�s de la URI:
@Path("/validaToken")
public class ValidaToken {
	
	/**
	 * Function obtiene usuario basadandose en el token
	 * 
	 * @param t - token
	 * @return archivo XML Auth, contiene usuario si existe en la BDD
	 * @throws JAXBException
	 */
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Auth getAuthorization(@QueryParam("t") String token) {
		Auth auth = new Auth();
		// Buscar obtener auth basados en el token
		if(token != null){
			auth = Auth.getAuthByToken(token);
		}
		
		// Se convierte el Conjunto --> XML
		buildAuthXMLFile(auth);
		return auth;
	}
	
	/***
	 * Metodo para crear fichero XML basado en la instancia Auth
	 * @param auth
	 */
	private void buildAuthXMLFile(Auth auth){
		// Se convierte el Conjunto --> XML
		try {
			JAXBContext context = JAXBContext.newInstance(Conjunto.class);
			Marshaller m = context.createMarshaller();
			File file = new File("auth.xml"); //  fichero XML
			m.marshal(auth, file);	
		} catch (JAXBException e1) {
			e1.printStackTrace();
		}
	}

}
