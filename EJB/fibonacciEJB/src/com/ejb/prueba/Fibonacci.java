package com.ejb.prueba;

import javax.ejb.EJBObject ;
import java.rmi.RemoteException ;

public interface Fibonacci extends EJBObject {

	public int getFibonacciNumber (int n) throws RemoteException ;

}
