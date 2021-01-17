package PaqueteLibros;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;


/**
 * Servlet implementation class for Servlet: session
 *
 */
 public class session extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */

	public session() {
		super();
	}



	protected void doService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion = request.getSession(true);
		String submit_aqui=request.getParameter("cerrar");
		if (submit_aqui !=null){
			try{

				if (submit_aqui.equals("cerrar_session")){
					sesion.invalidate();
					response.sendRedirect(response.encodeRedirectURL(request.getContextPath () + "/index.jsp"));
				}

			}catch(ClassCastException err){
				sesion.setAttribute("EstadoSesion","Error:Sesion cerrada incorrectamente");
				response.sendRedirect(response.encodeRedirectURL(request.getContextPath () + "/index.jsp"));
			}
		}
	}






	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doService(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doService(request, response);
	}

}