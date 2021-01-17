package uned.java2016.apitwitter.services.rs.filters;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

import uned.java2016.apitwitter.dao.*;

/**
 * Implementa un sencillo mecanismo de autorizacion. Este filtro espera una cabecera de nombre
 * 'Authorization' con las credenciales segun el esquema <user>:<password>
 * Accede a la BBDD y comprueba si el usuario tiene perfil 'adm'. En caso contrario,
 * aborta la ejecucion de la llamada al WS y devuelve un error al llamante 401 UNAUTHORIZED
 * @author José Barba Martínez (jbarba63)
 *
 */
@Provider
public class AuthorizationFilter implements ContainerRequestFilter {

	/**
	 * Abortamos la ejecución en caso de que el usuario con las credenciales en la cabecera
	 * Authorization NO tenga perfil 'adm' en la base de datos.
	 */
	@Override
	public void filter(ContainerRequestContext req)
	   throws IOException {
		  String authorizationHeader=req.getHeaderString("Authorization");		  
		  ConnDAOImpl con=null;
		  try{
			// el formato es <user:password>
			String user=authorizationHeader.split(":")[0];
			String password=authorizationHeader.split(":")[1];			  
			con = new ConnDAOImpl();
			con.abrirConexion();
			UsuariosDAOImpl u = new UsuariosDAOImpl(con.getConnection());
			String rol=u.getRol(user,password);
			if(rol==null || !rol.equals("adm"))
			{
				throw new RuntimeException("Usuario no autorizado o no presente");
			}
		  }catch(Exception e){
			  req.abortWith(Response.status(Response.Status.UNAUTHORIZED)
					                .entity("Usuario NO autorizado")
					                .build()
					  );
		  }finally{
			  if(con!=null) 
				 con.cerrarConexion();
		  }
		  
	}
}
