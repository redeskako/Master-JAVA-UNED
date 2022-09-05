package novatech.ejb.stateFull;

import java.rmi.RemoteException;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.naming.InitialContext;

public class statefullBean implements SessionBean {
 
	String sNombre ; 
	
	public void ejbActivate() throws EJBException, RemoteException {
	}

	public void ejbCreate() throws EJBException, RemoteException {
		try {
			InitialContext initialcontext = new InitialContext();
			sNombre = (String)initialcontext.lookup ("java:/comp/env/valorInicial");
System.out.println ("Valor inicial: " + sNombre) ;			
		}
		catch (Exception e) {}
	}
	
	public void ejbCreate (String sNombre) throws EJBException, RemoteException {
		this.sNombre = sNombre ;
	}
	
	public void ejbPassivate() throws EJBException, RemoteException {
	}

	public void ejbRemove() throws EJBException, RemoteException {
	}

	public void setSessionContext(SessionContext arg0) throws EJBException,
			RemoteException {
	}
	
	public void cambioNombre (String sNombre) throws RemoteException {
		System.out.println ("Valor Anterior: " + sNombre) ;
		this.sNombre = sNombre ;
		System.out.println ("Valor Anterior: " + sNombre) ;
	}

}
