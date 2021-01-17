package rba.mesas;


import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.sql.*;
import java.lang.*;
import rba.bbdd.Mesa;





public class AccionMesa extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet
{

	// creamos bd como de la clase AccesoDatos
	private Mesa bd;

	// funcion que cuando carga el proceso abre base de datos
	public void init(ServletConfig cfg) throws ServletException
	{
		bd=new Mesa();
		//bd.abrirConexion();

	}
    // funcion que captura todos los datos que vienen de un jsp o servlet para realizar distintas funciones
	// en concreto con la variable ACCION, lo que se pretende es que segun el valor que tenga que realize
	// una accion alta, baja, modificacion
	public void doPost (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		HttpSession s = req.getSession(true);

		//Integer codigomesa = 0;
		Integer numsillas = null;
		String path = req.getContextPath();
		// cargamos los datos de accion (accion a realizar alta, baja, modificacion)
		// como del codigo del registro en la que se va a realizar la accion

		String codigo2 = (String) req.getParameter("codigo");
		String accion2 = (String) req.getParameter("accion");

		Integer accion = Integer.parseInt(accion2);
		Integer codigo;

		switch (accion)
		{
		case 1:
			// baja mesa: se manda a formulario para confirmar eliminacion
			codigo = Integer.parseInt(codigo2);
			res.sendRedirect ("zonaPrivada/mesas/bajaMesa.jsp?codigo=" + codigo);
			break;
		case 2:
			// modificacion mesa: se manda a formulario para que se introduzcan los datos y confirme su actualizacion
			codigo = Integer.parseInt(codigo2);
			res.sendRedirect ("zonaPrivada/mesas/modificarMesa.jsp?codigo=" + codigo);
			break;
		case 3:
			// alta mesa: nos lleva al formulario de captura de datos
			res.sendRedirect ("zonaPrivada/mesas/altaMesa.jsp");
			break;
		case 4:
			// alta de mesa capturamos los datos introducidos el altacliente.jsp

			try{
				numsillas = Integer.parseInt(req.getParameter("txtNumSillas"));
			}catch(Exception e){
				numsillas = 0;
			}

			// pasamos los campos capturados al modelo para su grabacion
			bd.InsertarMesa (numsillas);
			// volvemos al principio de mesas
			res.sendRedirect ("zonaPrivada/mesas/mesa.jsp");
			break;
		case 5:
			// baja de mesa
			codigo = Integer.parseInt(codigo2);
			bd.EliminarMesa (codigo);
			// volvemos al principio de mesa
			res.sendRedirect ("zonaPrivada/mesas/mesa.jsp");
			break;
		case 6:
			// modificacion cliente: han confirmado datos y
			// o modificado
//			 alta de cliente capturamos los datos introducidos el altamesa.jsp



			try{
				numsillas = Integer.parseInt(req.getParameter("txtNumSillas"));
			}catch(Exception e){
				numsillas = 0;
			}

			// pasamos a modelo para la grabacion de los datos introducidos
			codigo = Integer.parseInt(codigo2);
			bd.ActualizarMesa (codigo,numsillas);
			res.sendRedirect ("zonaPrivada/mesas/mesa.jsp");
			break;
		case 7:
			// accion de busqueda que viene de la eleccion del campo de busqueda por
			// un combo y una caja de texto en mesa.jsp
			String campo = req.getParameter("CmbCampos");
			String texto = req.getParameter("txtbuscar");
			res.sendRedirect ("zonaPrivada/mesas/mesa.jsp?campo=" + campo + "&texto=" + texto );
			break;
		case 8:
			String columna = req.getParameter("campo");
			res.sendRedirect("zonaPrivada/mesas/mesa.jsp?campo=" + columna );
			break;
		}

	}
	public void doGet (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		try{
			doPost(req,res);

		}catch (Exception e){}


	}

}
