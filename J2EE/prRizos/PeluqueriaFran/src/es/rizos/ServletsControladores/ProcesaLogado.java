package es.rizos.ServletsControladores;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.rizos.beansUsuario.*;
import es.rizos.librerias.*;

/**
 * Servlet implementation class for Servlet: ProcesaLogado
 *
 */
 public class ProcesaLogado extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ProcesaLogado() {
		super();
	}

	protected void doService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Primero Recuperar los datos del usuario
		//Del request
		// usuario/password/submit
		String user= request.getParameter("usuario");
		String pass= request.getParameter("password");

		//Comparar la password es perez y el usuario pepe
	    boolean logado=false;
		try{
		  logado= Usuario.logarse(user,pass);
			if (logado){
				//Si todo OK Enviamos una página por out de todo correcto con el IDSEsion creado.
				//Recupera la cookie de sesion para mostrarla.
					HttpSession sesion= request.getSession();
					sesion.setAttribute("AtributoSesionIniciada", sesion.getId());
                                       	if (Usuario.EsCliente(user, pass)){
						//los atributos serán,tipo de usuario y sesionIniciada
						sesion.setAttribute("AtributoTipoUsuario","cliente");
						response.sendRedirect("GestionaCliente.jsp");
					}
					else {
						sesion.setAttribute("AtributoTipoUsuario","admin");
						response.sendRedirect("GestionaAdmin.jsp");
					}
			}
			else{
				// Si NO OK Envio una página por out de ERROR con un link a la index.html
				response.sendRedirect("Bienvenida.jsp?valido=-1");
			}
		}catch(ErrorRizosBd e){
			response.sendRedirect("error.jsp?error=Error en la conexion a la Base Datos");
			logado=false;
		}
	}

	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doService(request,response);
	}

	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doService(request,response);
	}
}