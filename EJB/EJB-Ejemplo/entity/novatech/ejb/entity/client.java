package novatech.ejb.entity;

import java.rmi.Remote;
import javax.ejb.EJBObject;
import java.rmi.RemoteException;

public interface client extends EJBObject, Remote {

	public Integer getClientId() throws RemoteException ;
	public void setClientId (Integer i) throws RemoteException ;
	
	public String getName() throws RemoteException ;
	public void setName (String name) throws RemoteException ;
	
	public String getAddress() throws RemoteException ;
	public void setAddress (String name) throws RemoteException ;
		
	public String getTown() throws RemoteException ;
	public void setTown (String name) throws RemoteException ;
		
}
