package org.Servlet;
import org.BBDD.*;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Servlet implementation class Controlador
 */
@WebServlet("/Controlador")
public class Controlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controlador() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		servicio(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		servicio(request, response);
	}

	protected void servicio(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try
		{
			
			
			String usu;
			String clave;
			String nombre;
			usu=request.getParameter("usuario");
			clave=request.getParameter("clave");

			// DETECTAMOS SI LOS CAMPOS USUARIO Y CLAVE ESTAN VACIOS
			
			
			if (usu!=null && clave!=null)
			{
				Usuario usuario=new Usuario(usu,clave);
				usuario=usuario.validarUsuario();
			
				//COMPROBAMOS SI HAY REGISTRO EN BBDD CON EL NOMBRE DE USUARIO Y CLAVE
				
				if(usuario!=null)
				{
					nombre=usuario.nombre();

					//RECOGEMOS LA HORA DE ENTRADA DEL USUARIO
					
					Date horaInicio=new Date();
					long hInicio=horaInicio.getTime();
					String horaEntrada=String.valueOf(hInicio);
					
					//CREAMOS SESION
					
					HttpSession session=request.getSession(true);
					
					session.setAttribute("nombre", usuario.nombre());
					session.setAttribute("horainicio", horaEntrada);
					
		
			    	// REDIRIGIMOS A LA PAGINA MENU
	
				   response.sendRedirect("bienvenido.jsp"); // Cuando se ha validado y todo ok paso a redirigir la vista disconformidades.jsp.

			   }//fin if
				
			   else{
	
				   // CASO EN QUE EL USUARIO NO SEA CORRECTO
				   
					System.out.println("El usuario no existe"); //No se ha validado usuario 
					response.sendRedirect("index.jsp?estado=invalido"); //Redirijo a index.jsp co
			   }//fin else
			
			}//fin if
		
			else {
				System.out.println("El usuario y/o clave están vacíos"); //No se ha validado usuario 
				response.sendRedirect("index.jsp?estado=ilegal"); //Redirijo a index.jsp co
			}
		}//fin try
		catch(Exception e)
		{
			throw new BBDDException("Error al Cerrar la Conexion: " + e.getMessage() );
		}//fin catch
	
		
	}//fin metodo servicio
			
	
}//fin clase
