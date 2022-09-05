package org.aprende.java;

import org.aprende.java.bbdd.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class login_action
 */
public class Login_action extends HttpServlet{
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login_action() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		this.doService(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		this.doService(request, response);
	}

	protected void doService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String sUsuario = request.getParameter("txtUsuario");
		String sPwd = request.getParameter("txtPwd");

		if (sUsuario != null && sPwd != null){ //No han metido usuarios 'vacios'
			// ¡¡¡Falta acceso a la b.d. para consultar el login
			//Suponemos que se valida la entrada con la base de datos
			Usuario usu = new Usuario().validarUsuario(sUsuario, sPwd); //El controlador llama a un javabean para que gestione el usuario.
			// Si usuario es null es que no se ha validado....
			if (usu != null){
				//El usuario es un usuario válido
				System.out.println(usu.nombre()); //Imprimo en consola el usuario
				//COOKIE
				Cookie miCookie = new Cookie("nick",sUsuario); //Creo la cookie
				miCookie.setMaxAge(15*60);
				response.addCookie(miCookie);
				
				HttpSession session = request.getSession(); //Genero la sesion. Importante para mantener el control de usuario en la web
				session.setAttribute("IdUsuario",usu.nombre()); //VARIABLE DE SESION el usuario
				//El atributo lo usaré a posteriori para gestionar que existe sesión.
				response.sendRedirect("disconformidades.jsp"); // Cuando se ha validado y todo ok paso a redirigir la vista disconformidades.jsp.
				System.out.println("creada la cookie y la variable de sesion"); //Mando mensaje a consola de que todo ok
			}else{
				System.out.println("El usuario no existe"); //No se ha validado usuario 
				response.sendRedirect("index.jsp?estado=invalido"); //Redirijo a index.jsp con el estado 'invalido' para que index lo procese.
			}
		}
	}
