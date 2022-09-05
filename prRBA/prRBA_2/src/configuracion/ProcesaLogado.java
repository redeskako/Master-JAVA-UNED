package configuracion;

import java.io.IOException;
import rba.bbdd.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		//Recuperamos los datos del usuario del request
		// usuario/password/submit
		String user= request.getParameter("usuario");
		String pass= request.getParameter("password");

		//En logado tendré si se ha logado correctamente.Utilizo el método
		//logarse de la clase Usuario que se encuentra en rba.bbdd
	    boolean logado=false;
		try{
		  logado= Usuario.logarse(user,pass);
			if (logado){
				//Si todo OK Enviamos una página por out de todo correcto con el IDSEsion creado.

					//Recupera la cookie de sesion para mostrarla.
					HttpSession sesion= request.getSession();
					sesion.setAttribute("comprobarSesion", sesion.getId());
					//enviaria a la carpeta zonaPrivada
					response.sendRedirect("zonaPrivada/menu.jsp");
					//response.sendRedirect("zonaPrivada/*.jsp");
			}else{
				// Si NO OK Envio una página por out de ERROR con un link a la index.html
					response.sendRedirect("index.jsp?valido=-1");

			}
		}catch(RbaException e){
			response.sendRedirect("error.jsp?error=problemas de servidor");
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