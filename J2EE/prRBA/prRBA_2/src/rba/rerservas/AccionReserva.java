package rba.rerservas;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.sql.*;
import java.lang.*;
import rba.bbdd.Reserva;





public class AccionReserva extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet
{

	// creamos bd como de la clase AccesoDatos
	//private AcReserva bd;

	// funcion que cuando carga el proceso abre base de datos
	public void init(ServletConfig cfg) throws ServletException
	{
		 bd =new rba.bbdd.Reserva();
		
		//bd.abrirConexion();

	}
    // funcion que captura todos los datos que vienen de un jsp o servlet para realizar distintas funciones
	// en concreto con la variable ACCION, lo que se pretende es que segun el valor que tenga que realize
	// una accion alta, baja, modificacion
	public void doPost (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		HttpSession s = req.getSession(true);

		// creacion de variables tanto de uso entre paginas

		Integer accion = null;
		Integer codigo = null;

		// como de los campos, para trabajar en la funcion

		String CodReserva = null;
		String Fecha = null;
		String Hora = null;
		String Turno = null;
		String Usuario = null;
		String Cliente  = null;
		String Mesa = null;


		// cargamos los datos de accion (accion a realizar alba, baja, modificacion)
		// como del codigo del registro en la que se va a realizar la accion


		accion = Integer.parseInt(req.getParameter("accion"));
		codigo = Integer.parseInt(req.getParameter("codigo"));

		switch (accion)
		{
		case 1:
			// baja cliente: se manda a formulario para confirmar eliminacion
			res.sendRedirect (res.encodeRedirectURL("/zonaPrivada/reservas/bajaReserva.jsp?codigo=" + codigo));
			break;
		case 2:
			// modificacion cliente: se manda a formulario para que se introduzcan los datos y confirme su actualizacion
			res.sendRedirect (res.encodeRedirectURL("/zonaPrivada/reservas/modificarReserva.jsp?codigo=" + codigo));
			break;
		case 3:
			// alta cliente: nos lleva al formulario de captura de datos
			res.sendRedirect (res.encodeRedirectURL("/zonaPrivada/reservas/altaReserva.jsp"));
			break;
		case 4:
			// alta de cliente capturamos los datos introducidos el altacliente.jsp
			Fecha = (String) req.getParameter("txtFecha");
			Hora = (String) req.getParameter("txtHora");
			Turno = (String) req.getParameter("txtTurno");
			Usuario = (String) req.getParameter("txtUsuario");
			Cliente = (String) req.getParameter("txtCliente");
			Mesa = (String) req.getParameter("txtMesa");
			// pasamos los campos capturados al modelo para su grabacion
			bd.InsertarReserva (Fecha,Hora,Turno,Usuario,Cliente,Mesa);
			// volvemos al principio de cliente
			res.sendRedirect (res.encodeRedirectURL("/zonaPrivada/reservas/reserva.jsp"));
			break;
		case 5:
			// baja de cliente
			bd.EliminarReserva (codigo);
			// volvemos al principio de cliente
			res.sendRedirect (res.encodeRedirectURL("/zonaPrivada/reservas/reserva.jsp"));
			break;
		case 6:
			// modificacion cliente: han confirmado datos y
			// o modificado
			Fecha = (String) req.getParameter("txtFecha");
			Hora = (String) req.getParameter("txtHora");
			Turno = (String) req.getParameter("txtTurno");
			Usuario = (String) req.getParameter("txtUsuario");
			Cliente = (String) req.getParameter("txtCliente");
			Mesa = (String) req.getParameter("txtMesa");
			// pasamos a modelo para la grabacion de los datos introducidos
			bd.ActualizarReserva (Fecha,Hora,Turno,Usuario,Cliente,Mesa);
			break;
		case 7:
			// accion de busqueda que viene de la eleccion del campo de busqueda por
			// un combo y una caja de texto en cliente.jsp
			res.sendRedirect (res.encodeRedirectURL("/zonaPrivada/reservas/reserva.jsp?campo=" + req.getParameter("CmbCampos") + ",texto=" + req.getParameter("txtbuscar")));
			break;
		}

	}
	public void doGet (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		try{
			doPost(req,res);

		}catch (Exception e){}

		//public void destroy(){
			//bd.cerrarConexion;
			//super.destroy();

		//}
	}

}
