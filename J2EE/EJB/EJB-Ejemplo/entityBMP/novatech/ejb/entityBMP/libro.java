package novatech.ejb.entityBMP;

import javax.ejb.EJBObject;
import java.rmi.RemoteException;
import java.util.Date;

public interface libro extends EJBObject {
    public String getIsbn() throws RemoteException ;
    public void setIsbn (String isbn) throws RemoteException ;

    public String getTitulo() throws RemoteException ;
    public void setTitulo (String titulo) throws RemoteException ;

    public String getAutor() throws RemoteException ;
    public void setAutor (String autor) throws RemoteException ;

    public String getAnyo() throws RemoteException ;
    public void setAnyo (String anyo)  throws RemoteException ;

    public String getEdicion() throws RemoteException ;
    public void setEdicion (String edicion) throws RemoteException ;

    public String getEditorial() throws RemoteException ;
    public void setEditorial (String editorial) throws RemoteException ;
}
