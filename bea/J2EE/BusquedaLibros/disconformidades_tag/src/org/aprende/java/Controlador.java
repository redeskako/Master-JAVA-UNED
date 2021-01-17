package org.aprende.java;

import org.aprende.java.bbdd.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class Controlador {
	Usuario user;
	
	public Controlador(){
		this.user= new Usuario();
	}
	public void setUser(Usuario user){
		this.user= user;
	}
	public void logarse(HttpServletRequest request){
		this.user= this.user.validarUsuario(request.getParameter("txtUsuario"),request.getParameter("txtPasswd"));
		HttpSession ses= request.getSession();
		// Establecer la sesion si ha validado.
		
		if (user!=null) {
			ses.setAttribute("IdUsuario",this.user.Id());
		}
		
	}
}
