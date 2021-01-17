package org.Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.BBDD.BBDD;
import org.BBDD.BBDDException;

/**
 * Servlet implementation class Eliminador
 */
@WebServlet("/Eliminador")
public class Eliminador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Eliminador() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doServicioElim(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doServicioElim(request, response);
	}
	
	protected void doServicioElim(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {

			// RECOJEMOS EL Nº DE REGISTROS MEDIANTE SESION
			HttpSession sesion= request.getSession();
			int radio=(Integer) sesion.getAttribute("radio");
			
			String control;
			int i=0;// INDICARA LA POSICION DEL Nº DE FILA DONDE SE ENCUENTRA EL REGISTRO
			int j=1;// INDICARA EL ID A ELIMINAR

			Boolean verificar=false;
			System.out.println(radio);
			
			while(i<radio)
			{
				
				control=String.valueOf(request.getParameter("radioact"+i));

			
				System.out.println(control);
				if (control.equals("on"))
				{



					BBDD mibd=new BBDD();
					 System.out.println("Select * from recursos limit " + (i-1) + ",1");
					 System.out.println("es iiiii  "+i);

					
		
					 // Devolver nº de id
					 

						
					j=mibd.obtenerId(i);
							
					sesion.setAttribute("idAct", j);
							
					System.out.println("El registro a ELIMINAR es el numero: "+ j);
					verificar=true;
					
					break;
					
				}//fin if
				
				i++;
				
			}// fin while
				

				// SI HAY UN RADIOBUTTOM MARCADO PROCEDEREMOS A ELIMINAR EL REGISTRO
				if (verificar==false)
					{response.sendRedirect("eliminador.jsp");}
				else
					{

					BBDD mibd=new BBDD();
					
					mibd.actualizarRecursos("Delete from recursos where idRecurso="+j);

					response.sendRedirect("actualizador.jsp");
					
					}

			}//fin try
		
		
		catch(Exception e)
		{
			throw new BBDDException("Error al en Metodo DoServicioAct: " + e.getMessage() );
		}//fin catch
		
	}
	

}
