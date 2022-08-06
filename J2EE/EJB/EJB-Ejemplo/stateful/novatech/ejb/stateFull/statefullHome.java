package novatech.ejb.stateFull;

import javax.ejb.EJBHome;
import javax.ejb.CreateException ;
import java.rmi.RemoteException ; 

public interface statefullHome extends EJBHome {
	public statefull create() throws CreateException, RemoteException ;
	public statefull create (String sNombre) throws CreateException, RemoteException ;
}
