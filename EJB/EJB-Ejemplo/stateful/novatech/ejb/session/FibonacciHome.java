package novatech.ejb.session;

import javax.ejb.CreateException ;
import java.rmi.RemoteException ;
import javax.ejb.EJBHome ;

public interface FibonacciHome extends EJBHome {

	public Fibonacci create() throws RemoteException, CreateException ;

}
