package novatech.ejb.entity;

import java.rmi.RemoteException;
import javax.ejb.EJBException;
import javax.ejb.EntityBean;
import javax.ejb.EntityContext;
import javax.ejb.RemoveException;

public abstract class clientBean implements EntityBean {

	public abstract void setClientId(Integer id);
	public abstract Integer getClientId();

	public abstract void setName (String name);
	public abstract String getName();
	
	public abstract void setAddress (String addr);
	public abstract String getAddress();
		
	public abstract void setTown (String town);
	public abstract String getTown();

	
	public void ejbActivate() throws EJBException, RemoteException {
	}

	public void ejbLoad() throws EJBException, RemoteException {}

	public void ejbPassivate() throws EJBException, RemoteException {}

	public void ejbRemove() throws RemoveException, EJBException, RemoteException {}

	public void ejbStore() throws EJBException, RemoteException {}

	public void setEntityContext(EntityContext arg0) throws EJBException, RemoteException {}

	public void unsetEntityContext() throws EJBException, RemoteException {}
	 
	public Integer ejbCreate (String name, String address, String town) {
		this.setName (name) ;
		this.setAddress (address) ;
		this.setTown (town) ;
		return null ;				
	}

	public void ejbPostCreate (String name, String address, String town) {}

}
