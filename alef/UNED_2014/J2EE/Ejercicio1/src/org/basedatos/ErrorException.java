package org.basedatos;
//Gestión de los errores del Ejercicio
public class ErrorException extends Exception {

	private static final long serialVersionUID = 1L;
	public ErrorException(){
	super();
	}
	public ErrorException(String err){
		super("\nError en recurso:\n\t"+err);
	}
}
