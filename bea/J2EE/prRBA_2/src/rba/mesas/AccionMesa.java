package rba.mesas;


import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.sql.*;
import java.lang.*;





public class AccionMesa extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet
{

	// creamos bd como de la clase AccesoDatos
	private AccionMesa bd;

	// funcion que cuando carga el proceso abre base de datos
	public void init(ServletConfig cfg) throws ServletException
	{
		bd=new AccionMesa();
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

		Integer codigomesa = null;
		Integer numsillas = null;

		// cargamos los datos de accion (accion a realizar alba, baja, modificacion)
		// como del codigo del registro en la que se va a realizar la accion


		accion = Integer.parseInt(req.getParameter("accion"));
		codigo = Integer.parseInt(req.getParameter("codigo"));

		switch (accion)
		{
		case 1:
			// baja mesa: se manda a formulario para confirmar eliminacion
			res.sendRedirect (res.encodeRedirectURL("/zonaPrivada/mesas/bajaMesa.jsp?codigo=" + codigo));
			break;
		case 2:
			// modificacion mesa: se manda a formulario para que se introduzcan los datos y confirme su actualizacion
			res.sendRedirect (res.encodeRedirectURL("/zonaPrivada/mesas/modificarMesa.jsp?codigo=" + codigo));
			break;
		case 3:
			// alta mesa: nos lleva al formulario de captura de datos
			res.sendRedirect (res.encodeRedirectURL("/zonaPrivada/mesas/altaMesa.jsp"));
			break;
		case 4:
			// alta de mesa capturamos los datos introducidos el altacliente.jsp
			codigomesa  = Integer.parseInt(req.getParameter("txtCodigo"));
			numsillas = Integer.parseInt(req.getParameter("txtNumSillas"));

			// pasamos los campos capturados al modelo para su grabacion
			bd.InsertarMesa (codigomesa,numsillas);
			// volvemos al principio de cliente
			res.sendRedirect (res.encodeRedirectURL("/zonaPrivada/mesas/mesa.jsp"));
			break;
		case 5:
			// baja de mesa
			bd.EliminarMesa (codigo);
			// volvemos al principio de mesa
			res.sendRedirect (res.encodeRedirectURL("/zonaPrivada/mesas/mesa.jsp"));
			break;
		case 6:
			// modificacion cliente: han confirmado datos y
			// o modificado
//			 alta de cliente capturamos los datos introducidos el altamesa.jsp
			codigomesa  = Integer.parseInt( req.getParameter("txtCodigo"));
			numsillas = Integer.parseInt(req.getParameter("txtNumSillas"));
			// pasamos a modelo para la grabacion de los datos introducidos
			bd.ActualizarMesa (codigomesa,numsillas);
			break;
		case 7:
			// accion de busqueda que viene de la eleccion del campo de busqueda por
			// un combo y una caja de texto en mesa.jsp
			res.sendRedirect (res.encodeRedirectURL("/zonaPrivada/mesas/mesa.jsp?campo=" + req.getParameter("CmbCampos")+ ", texto=" + req.getParameter("txtbuscar")));
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
