package org.Servlet;
import org.BBDD.*;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Cookie;


/**
 * Servlet implementation class CerrarSesion
 */
@WebServlet("/CerrarSesion")
public class CerrarSesion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CerrarSesion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doSalir(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doSalir(request, response);
	}
	
	protected void doSalir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// CERRAMOS LA CONEXION A LA BBDD
		
		
		BBDD miBBDD=new BBDD();
		miBBDD.cerrarConexion();
		
		
		
		//CREAMOS SESIONES
		
		HttpSession session=request.getSession(true);
		String horaEntrada=(String)session.getAttribute("horainicio");
		String nombre=(String)session.getAttribute("nombre");
		
		//RECOGEMOS HORA DE ENTRADA DE USUARIO
		
		long lngHoraEntrada=Long.parseLong(horaEntrada);
		
		Date horaFin=new Date();
		long hFinal=horaFin.getTime();
		
		long lngTiempo=hFinal/1000-lngHoraEntrada/1000;
		
		String strTiempo=String.valueOf(lngTiempo);
		
		//CREAMOS LA COOKIE CON EL NOMBRE USUARIO Y TIEMPO EN SEGUNDOS LOGEADO
		
		Cookie miCookie=new Cookie(nombre,strTiempo);
		miCookie.setMaxAge(1*365*24*60*60);
		response.addCookie(miCookie);
		
		// SALIMOS
		
		response.sendRedirect("./index.jsp?estado=salir");

		
	}
	

}
