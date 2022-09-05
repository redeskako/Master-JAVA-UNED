package es.uned.sw;

/**
 * @author	Cristina Morales
 * @version	Pruebas 1.0
 * @since	Septiembre 2014
 */

public class Cuentas {
	
	public String sumar(int a, int b){
		
		int c = a+b;
		String s = a + " + " + b + " = " + c;
		
		System.out.println("SERVIDOR: El resultado es " + s);
		
		return s;
	}

}
