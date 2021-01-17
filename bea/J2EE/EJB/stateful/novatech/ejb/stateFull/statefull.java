package novatech.ejb.stateFull;

import javax.ejb.EJBObject;
import java.rmi.RemoteException ;

public interface statefull extends EJBObject {
	public void cambioNombre (String sNombre) throws RemoteException ;	
}
 