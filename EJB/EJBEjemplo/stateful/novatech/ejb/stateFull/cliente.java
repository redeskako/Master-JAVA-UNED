package novatech.ejb.stateFull;

import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;


public class cliente {
	
	public static void main (String [] as) {
		try {
			Hashtable h =  new Hashtable() ;
			h.put (Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory") ;
			h.put (Context.PROVIDER_URL, "t3://localhost:7001") ;
			InitialContext ic = new InitialContext (h) ;
			statefullHome home = (statefullHome)
				PortableRemoteObject.narrow (
						ic.lookup ("EjbStatefull"), statefullHome.class) ;
			
			statefull remote = (statefull)
			PortableRemoteObject.narrow (
					home.create(), statefull.class) ;				
			
			remote.cambioNombre ("pepe") ;
			
		}
		catch (Exception e) {}
	}

}
