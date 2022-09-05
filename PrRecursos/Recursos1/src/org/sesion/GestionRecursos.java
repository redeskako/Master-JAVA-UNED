package org.sesion;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Marta
 *
 */
public class GestionRecursos extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public GestionRecursos(){
	super();

	
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
        //Recupero todos los parámetros
        String action = request.getParameter("action");
        int IdRecurso = Integer.parseInt(request.getParameter("IdRecurso"));
        
        //Verifico el tipo de acción.
        if (action.equalsIgnoreCase("delete")){
        	// Si elimino, redirección a una vista para que confirme la eliminación.
        	response.sendRedirect("borrarRecursos.jsp?IdRecurso=" + IdRecurso);
        	
        	/* Dejo la anterior como propuesta.
            int IdUsuario = Integer.parseInt(request.getParameter("IdRecurso"));
            r.deleteRecursos(IdRecurso);
            forward = listadoRecursos;
            request.setAttribute("recursos", r.getAllUsers());
            */
           } else if (action.equalsIgnoreCase("edit")){
        	// Si edito, redirecciónno a una vista para que cambie lo que quiera.
        	response.sendRedirect("editarRecursos.jsp?IdRecurso=" + IdRecurso);
        	
        	/* Dejo la anterior como propuesta
            forward = INSERT_OR_EDIT;
            int IdRecurso = Integer.parseInt(request.getParameter("IdRecurso"));
            Recursos recurso = r.getUserById(IdRecurso);
            request.setAttribute("recurso", recurso);
            */
       } else if (action.equalsIgnoreCase("insert")){
        	// Si añado no necesito id lo redirecciono a otra vista.
        	response.sendRedirect("addRecursos.jsp?IdRecurso=" + IdRecurso);

        	/* Dejo la anterior como propuesta
        	forward = listadoRecursos;
            request.setAttribute("recursos", r.getAllUsers());
            */
        	
        } else if (action.equalsIgnoreCase("listadoRecursos")){
            	// Si añado no necesito id lo redirecciono a otra vista.
            	response.sendRedirect("recursos.jsp?IdRecurso=" + IdRecurso);

            	/* Dejo la anterior como propuesta
            	forward = listadoRecursos;
                request.setAttribute("recursos", r.getAllUsers());
                */
       } else {
        	// En otro caso paso a recursos.jsp (muestro el listado)
        	response.sendRedirect("recursos.jsp?IdRecurso=" + IdRecurso);
            /*
        	forward = INSERT_OR_EDIT;
        	*/
        }

        /*
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
        */
   }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		this.doGet(request, response);
		}
}




