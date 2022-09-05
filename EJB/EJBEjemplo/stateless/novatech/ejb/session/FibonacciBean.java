package novatech.ejb.session;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class FibonacciBean implements SessionBean { 
	
	private SessionContext sessCtx ;

	public void ejbActivate() throws EJBException, RemoteException {
		System.out.println ("FibonacciBean: Método Activate") ;		
	}

	public void ejbPassivate() throws EJBException, RemoteException {
		System.out.println ("FibonacciBean: Método Passivate") ;
	}

	public void ejbRemove() throws EJBException, RemoteException {
		System.out.println ("FibonacciBean: Método Remove") ;
	}

	public void setSessionContext(SessionContext arg0) throws EJBException, RemoteException {
		this.sessCtx = arg0 ;
		System.out.println ("FibonacciBean: setSessionContext") ;
	}
	
	public int getFibonacciNumber (int n) {
		if (n <= 0) 
			return 0 ;
		
		if (n == 1)
			return 1 ;
		
		int prev2 = 0, prev1 = 1, fib = 0 ;
		
		for (int i = 2; i <= n; i ++) {
			fib = prev1 + prev2 ;
			prev2 = prev1 ;
			prev1 = fib ;
		}
		return fib ;					
	}
	
	public void ejbCreate() throws CreateException {
		try {
			InitialContext initialcontext = new InitialContext();
			Integer integer = (Integer)initialcontext.lookup ("java:/comp/env/valor");
			System.out.println ("El valor es: " + integer.intValue()) ;
		}
		catch (NamingException namingexception) {
			throw new CreateException("Failed to find environment value " + namingexception);
		}
	}
}
