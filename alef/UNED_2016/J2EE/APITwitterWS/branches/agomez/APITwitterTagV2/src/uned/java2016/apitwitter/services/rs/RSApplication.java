package uned.java2016.apitwitter.services.rs;

import javax.ws.rs.core.Application;
import java.util.*;

import uned.java2016.apitwitter.services.rs.filters.*;

/**
 * Devuelve las clases que implementan servicios web.
 * @author José Barba Martínez (jbarba63)
 *
 */
public class RSApplication extends Application{
	/**
	 * Devuelve un Set con todas las clases con métodos que queremos exponer como 
	 * operaciones de WS.
	 */
   @Override
   public Set<Class<?>> getClasses(){
	   Set<Class<?>> ret=new HashSet<Class<?>>();
	   ret.add(TweetsService.class);
	   ret.add(NeighbourService.class);
	   ret.add(AuthorizationFilter.class);
	   return ret;
   }
}
