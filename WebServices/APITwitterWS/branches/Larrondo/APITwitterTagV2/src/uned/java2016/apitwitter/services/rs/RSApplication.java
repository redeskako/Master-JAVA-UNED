package uned.java2016.apitwitter.services.rs;

import javax.ws.rs.core.Application;
import java.util.*;

import uned.java2016.apitwitter.services.rs.filters.*;

/**
 * Devuelve las clases que implementan servicios web.
 * @author Jos� Barba Mart�nez (jbarba63)
 *
 */
public class RSApplication extends Application{
	/**
	 * Devuelve un Set con todas las clases con m�todos que queremos exponer como 
	 * operaciones de WS.
	 * A�adido ret.add(EstudiosService.class);
	 */
   @Override
   public Set<Class<?>> getClasses(){
	   Set<Class<?>> ret=new HashSet<Class<?>>();
	   ret.add(TweetsService.class);
	   ret.add(EstudiosService.class);
	   ret.add(AuthorizationFilter.class);
	   return ret;
   }
}
