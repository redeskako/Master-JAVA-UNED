package novatech.ejb.entityBMP;

import javax.ejb.EJBHome;
import javax.ejb.CreateException;
import javax.ejb.FinderException;
import java.rmi.RemoteException;


public interface libroHome extends EJBHome {

    public libro create (String isbn, String titulo, String autor, String anyo,
                                String Edicion, String Editorial) throws RemoteException, CreateException ;
    public libro findByPrimaryKey (String isbn) throws RemoteException, FinderException ;
}
