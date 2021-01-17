package uned.java2016.apitwitter.services.rs.client.filters;

import java.io.IOException;
import java.util.*;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;

/**
 * Implementa un sencillo filtro, que inserta la cabecera 'Authorization' segun la espera el servicio expuesto
 * en APITwitterTagV2. Leemos las credenciales de la propiedad 'HEADER-Authorization' fijada por JAXRSClient
 * en los metodos 'invoke*'.
 * @author José Barba Martínez (jbarba63)
 *
 */

@Provider
public class AuthorizationFilter implements ClientRequestFilter {

	/**
	 * Fijamos la cabecera Authorization que espera el WS
	 */
	@Override
	public void filter(ClientRequestContext req) throws IOException {		
		MultivaluedMap<String,Object> map=req.getHeaders();
		String credentials= req.getProperty("HEADER-Authorization").toString();
		map.add("Authorization",credentials);
	}

}
