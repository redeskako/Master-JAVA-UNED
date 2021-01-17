package org.aprende.java;
import java.util.regex.*;
import java.io.*;
public class Chequear3 implements Serializable  {
	
	private String codigo;
	
	Pattern Patron=Pattern.compile("[0-9]");

	
	public Chequear3(){
		this.codigo="27";
	}
	
	public boolean comprobarCodigo(String codigo){
		 boolean exito ;
		 Matcher encaja = Patron.matcher(codigo);
		
		if (encaja.matches()) {
	        exito=true;
	     } else {
	    	 exito=false;
	     }
		 return exito; 
	}
}
