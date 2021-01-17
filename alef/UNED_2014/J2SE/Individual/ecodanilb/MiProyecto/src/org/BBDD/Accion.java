package org.BBDD;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;



/**
 * Servlet implementation class Accion
 */
@WebServlet("/Accion")
public class Accion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Accion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//LLAMAMOS AL METODO SERVICIO CON ENVIO DE PETICIONES Y RESPUESTAS
		
		this.servicio(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	protected void servicio(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String Usuario = request.getParameter("usuario");
		String Clave = request.getParameter("clave");
		
		// SE COMPRUEBA QUE LOS CAMPOS USUARIO Y CLAVE NO ESTEN VACIOS EN ELFORMULARIO

		if (Usuario != null && Clave != null){ //No han metido usuarios 'vacios'
			BBDD bd = new BBDD();
		   Usuario usu=new Usuario(Usuario, Clave); // Creamos el usuario
			if (usu.validarUsuario(bd)){
				System.out.println(usu.nombre()); //Imprimo en consola el usuario
				//COOKIE
				Cookie miCookie = new Cookie("nick",Usuario); //Creo la cookie
				miCookie.setMaxAge(15*60);
				response.addCookie(miCookie);
				
				HttpSession session = request.getSession(); //Genero la sesion. Importante para mantener el control de usuario en la web
				session.setAttribute("IdUsuario",usu.nombre()); //VARIABLE DE SESION el usuario
				//El atributo lo usaré a posteriori para gestionar que existe sesión.
				response.sendRedirect("bienvenido.jsp"); // Cuando se ha validado y todo ok paso a redirigir la vista disconformidades.jsp.
				System.out.println("creada la cookie y la variable de sesion"); //Mando mensaje a consola de que todo ok
			}else{
				System.out.println("El usuario no existe"); //No se ha validado usuario 
				response.sendRedirect("index.jsp?estado=invalido"); //Redirijo a index.jsp co
			}
		}
	}

}
