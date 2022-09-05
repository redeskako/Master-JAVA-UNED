package es.uned.sw;

import javax.jws.WebService;

/**
 * @author	Cristina Morales
 * @version	Pruebas 1.0
 * @since	Septiembre 2014
 */
@WebService(targetNamespace = "http://sw.uned.es/", portName = "CuentasPort", serviceName = "CuentasService")
public class Cuentas {
	
	public String sumar(int a, int b){
		
		int c = a+b;
		String s = a + " + " + b + " = " + c;
		
		System.out.println("SERVIDOR: El resultado es " + s);
		
		return s;
	}

}
