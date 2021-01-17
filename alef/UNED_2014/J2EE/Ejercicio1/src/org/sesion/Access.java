package org.sesion;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;

import org.basedatos.Usuario;


public class Access extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public Access(){
	super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		this.doService (request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		this.doService (request,response);
	}
	//Recoge el usuario y la password de index.jsp
	protected void doService(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{

		String sUser = request.getParameter("usuario");
		String sPassw = request.getParameter("password");
		if (sUser != null && sPassw!=null){
			Usuario usu = new Usuario().validarUser(sUser, sPassw);

			if(usu != null){
				System.out.println(usu.getNameUser(0));
				//Se crea cookie
				Cookie miCookie = new Cookie("nick", sUser);
				miCookie.setMaxAge(15+60);
				response.addCookie(miCookie);
				HttpSession session = request.getSession();
				session.setAttribute("iduser", usu.getNameUser(0));
				//Se reenvía a la página recursos.jsp para que saque el listado
				response.sendRedirect("recursos.jsp");
				System.out.println("Se ha creado la cookie y la variable de sesion");
				//Si no escribe mensaje de que no existe el usario y 
				//reenvía a la página index indicando que es un usuario inválido
			}else{
				System.out.println("El usuario no existe");				
				response.sendRedirect("index.jsp?estado=invalido");
				}
			}
		}
}
