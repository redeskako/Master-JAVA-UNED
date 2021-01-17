package org.sesion;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




public class CerrarSesion extends HttpServlet {

private static final long serialVersionUID = 1L;

public CerrarSesion(){
super();
}

//Cierra la Sesion y envía a la página index de nuevo para que se vuelva a introducir nombre y contraseña
protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
	HttpSession sesion = request.getSession();
	sesion.invalidate();
	response.sendRedirect("./index.jsp?estado=salir");
	}

protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	this.doGet(request, response);
}

}
