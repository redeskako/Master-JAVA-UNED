package org.Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.BBDD.BBDDException;


/**
 * Servlet implementation class Buscador
 */
@WebServlet("/Buscador")
public class Buscador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Buscador() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doServicioBusc(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doServicioBusc(request, response);
	}
	
	protected void doServicioBusc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String id=request.getParameter("identificador");
		String recurso=request.getParameter("recurso");
		HttpSession sesion= request.getSession();
		String sql;

		
		// VERIFICAMOS QUE EL ID A BUSCAR SEA UN NUMERO EN CASO DE SERLO LO PASAMOS AL LISTADO DE RECURSOS CON LA SENTENCIA SQL A EMPLEAR
		if (id.matches("[0-9]*"))
		  
		{
				if (id.equals("") && recurso.equals(""))
				{
					
					response.sendRedirect("buscador.jsp");
					
				}
				if (id.equals("") && recurso!="")
				{
					sql="Select * from recursos where Descripcion like '%"+recurso+"%'";
					response.sendRedirect("recursos.jsp?consulta="+sql);
					sesion.setAttribute("consulta",sql);
				}
				if (id !="" && recurso.equals(""))
				{
					sql="Select * from recursos where idRecurso="+id;
					response.sendRedirect("recursos.jsp?consulta="+sql);
					sesion.setAttribute("consulta",sql);
				}
				if (id !="" && recurso!="")
				{
					sql="Select * from recursos where idRecurso where id="+id+"and Descripcion='"+recurso+"'";
					response.sendRedirect("recursos.jsp?consulta="+sql);
					sesion.setAttribute("consulta",sql);
				}
	
		
		}
		else
		{
			
			//VERIFICAMOS SI HAY UN NUMERO Y LO PASAMOS COMO PARAMETRO
			try {

			response.sendRedirect("buscador.jsp?noNumero=verdadero");
			}
			catch (Exception e)
			{
				throw new BBDDException("Error al en Clase Buscador-Espera: " + e.getMessage() );
			}
			
			
		}
		
		
	}
	

}
