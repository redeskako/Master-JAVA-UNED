package novatech.ejb.entity;

import java.rmi.RemoteException;
import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.EJBHome;
import javax.ejb.FinderException;

public interface clientHome extends EJBHome {
	
	public client create (String name, String address, String town) 
			throws CreateException, RemoteException, EJBException ;
		  
	public client findByPrimaryKey (Integer id) 
			throws FinderException, RemoteException, EJBException ;
			
	public client findByName (java.lang.String name) 
				throws FinderException, RemoteException, EJBException ;			

}
