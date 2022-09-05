package rba.reservas;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.sql.*;
import java.lang.*;
import java.util.Date;
import java.text.DateFormat;


import rba.bbdd.Reserva;






public class AccionReserva extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet
{

	// creamos bd como de la clase AccesoDatos
	private Reserva bd;

	// funcion que cuando carga el proceso abre base de datos
	public void init(ServletConfig cfg) throws ServletException
	{
		bd = new Reserva();

	}
    // funcion que captura todos los datos que vienen de un jsp o servlet para realizar distintas funciones
	// en concreto con la variable ACCION, lo que se pretende es que segun el valor que tenga que realize
	// una accion alta, baja, modificacion
	public void doPost (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		HttpSession s = req.getSession(true);

		// como de los campos, para trabajar en la funcion

		String Fecha = bd.recFecha();
		String Hora = bd.recHora();
		String Turno = null;
		Integer Usuario = null;
		Integer Cliente  = null;
		Integer Mesa = null;
		String path = req.getContextPath();

		// cargamos los datos de accion (accion a realizar alba, baja, modificacion)
		// como del codigo del registro en la que se va a realizar la accion

		String codigo2 = (String) req.getParameter("codigo");
		String accion2 = (String) req.getParameter("accion");

		Integer accion = Integer.parseInt(accion2);
		Integer codigo;

			//Integer.parseInt(req.getParameter("codigo"));

		switch (accion)
		{
		case 1:
			// baja cliente: se manda a formulario para confirmar eliminacion
			codigo = Integer.parseInt(codigo2);
			res.sendRedirect ("zonaPrivada/reservas/bajaReserva.jsp?codigo="+codigo);
			break;
		case 2:
			// modificacion cliente: se manda a formulario para que se introduzcan los datos y confirme su actualizacion
			codigo = Integer.parseInt(codigo2);
			res.sendRedirect ("zonaPrivada/reservas/modificarReserva.jsp?codigo="+codigo);
			break;
		case 3:
			// alta cliente: nos lleva al formulario de captura de datos
			res.sendRedirect ("zonaPrivada/reservas/altaReserva.jsp");
			break;
		case 4:
			// alta de cliente capturamos los datos introducidos el altacliente.jsp
			try{
				Fecha = (String) req.getParameter("txtFecha");
			}catch(Exception e){
				Fecha = bd.getFecha();
			}

			//no puede ser null, ya que las comparaciones las hago con la hora.Deberia recogerse
			//automaticamente....hacer
			try{
				Hora = (String) req.getParameter("txtHora") ;
			}catch(Exception e){
				Hora = bd.getHora();
			}

			try{
				Turno = (String) req.getParameter("txtTurno");
			}catch(Exception e){
				Turno = "";
			}
			//usuario
			Usuario  = Integer.parseInt(req.getParameter("txtUsuario"));
			//cliente
			Cliente = Integer.parseInt(req.getParameter("CmbCliente"));
			//recuperamos el correo y con la función RetornarNumCliente recuperamos el código y lo insertamos

			//mesa
			Mesa = Integer.parseInt(req.getParameter("txtMesa")) ;
			//insertamos
			bd.InsertarReserva(Fecha,Hora,Turno,Usuario,Cliente,Mesa);




			// volvemos al principio de cliente
			res.sendRedirect ("zonaPrivada/reservas/reserva.jsp");
			break;
		case 5:
			// baja de cliente
			codigo = Integer.parseInt(codigo2);
			bd.EliminarReserva (codigo);
			// volvemos al principio de cliente
			res.sendRedirect ("zonaPrivada/reservas/reserva.jsp");
			break;
		case 6:
			// modificacion cliente: han confirmado datos y
			// o modificado
			codigo = Integer.parseInt(codigo2);

			try{
				Fecha = (String) req.getParameter("txtFecha");
			}catch(Exception e){
				Fecha = bd.getFecha();
			}

			//no puede ser null, ya que las comparaciones las hago con la hora.Deberia recogerse
			//automaticamente....hacer
			try{
				Hora = (String) req.getParameter("txtHora") ;
			}catch(Exception e){
				Hora = bd.getHora();
			}

			try{
				Turno = (String) req.getParameter("txtTurno");
			}catch(Exception e){
				Turno = "";
			}
			//usuario
			Usuario  = Integer.parseInt(req.getParameter("txtUsuario"));
			//cliente
			Cliente = Integer.parseInt(req.getParameter("CmbCliente"));
			//recuperamos el correo y con la función RetornarNumCliente recuperamos el código y lo insertamos

			//mesa
			Mesa = Integer.parseInt(req.getParameter("txtMesa")) ;
			/*
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy"); //Las M en mayúsculas o interpretará minutos!!

			try{
				Date fecha = sdf.parse(Fecha);
			}catch(Exception e){

			}*/


			// pasamos a modelo para la grabacion de los datos introducidos

			bd.ActualizarReserva(codigo,Fecha,Hora,Turno,Usuario,Cliente,Mesa);
			res.sendRedirect("zonaPrivada/reservas/reserva.jsp");
			break;
		case 7:
			// accion de busqueda que viene de la eleccion del campo de busqueda por
			// un combo y una caja de texto en cliente.jsp
			String campo = req.getParameter("CmbCampos");
			String texto = req.getParameter("txtbuscar");
			res.sendRedirect ("zonaPrivada/reservas/reserva.jsp?campo=" + campo + "&texto=" + texto);
			break;
		case 8:
			String columna = req.getParameter("campo");
			res.sendRedirect("zonaPrivada/reservas/reserva.jsp?campo=" + columna );
			break;
		}

	}
	public void doGet (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		try{
			doPost(req,res);

		}catch (Exception e){}


	}

}
