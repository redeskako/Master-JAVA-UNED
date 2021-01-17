package org.aprende.java;
import java.util.regex.*;
import java.io.*;

import javax.servlet.http.*;
import javax.servlet.*;

import org.apache.catalina.Session;
public class Chequear2 implements Serializable  {
	
	private String codigo;
	
	private HttpSession sesion;
	Pattern patron=Pattern.compile("[0-9]");
    
	public Chequear2(){
		
		
	}
	
	public void comprobarCodigo(HttpServletRequest request){
		
		 int codigoCorrecto ;
		 codigo = request.getParameter("codigoRecurso");
		 Matcher encaja = patron.matcher(codigo);
		 sesion =request.getSession();
		
		if (encaja.matches()) {
	        codigoCorrecto =1;
	     } else {
	    	codigoCorrecto =0;
	     }
		 sesion.setAttribute("codigoCorrecto", codigoCorrecto); 
	}
}
