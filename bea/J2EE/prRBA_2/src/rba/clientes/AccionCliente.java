package rba.clientes;



import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.sql.*;
import java.lang.*;

import rba.bbdd.Cliente;





public class AccionCliente extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet
{

	// creamos bd como de la clase AccesoDatos
	private Cliente bd;

	// funcion que cuando carga el proceso abre base de datos
	public void init(ServletConfig cfg) throws ServletException
	{
		bd=new Cliente();
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

		String dni = null;
		String nombre = null;
		String apellido1 = null;
		String apellido2 = null;
		String telefono = null;
		String correo = null;
		String pass = null;
		Integer cp = null;

		// cargamos los datos de accion (accion a realizar alba, baja, modificacion)
		// como del codigo del registro en la que se va a realizar la accion


		accion =Integer.parseInt(req.getParameter("accion"));
		codigo =Integer.parseInt(req.getParameter("codigo"));

		switch (accion)
		{
		case 1:
			// baja cliente: se manda a formulario para confirmar eliminacion
			res.sendRedirect (res.encodeRedirectURL("/zonaPrivada/clientes/bajaCliente.jsp?codigo=" + codigo));
			break;
		case 2:
			// modificacion cliente: se manda a formulario para que se introduzcan los datos y confirme su actualizacion
			res.sendRedirect (res.encodeRedirectURL("/zonaPrivada/clientes/modificarCliente.jsp?codigo=" + codigo));
			break;
		case 3:
			// alta cliente: nos lleva al formulario de captura de datos
			res.sendRedirect (res.encodeRedirectURL("/zonaPrivada/clientes/altaCliente2.jsp"));
			break;
		case 4:
			// alta de cliente capturamos los datos introducidos el altacliente.jsp
			dni = (String) req.getParameter("txtDniCliente");
			nombre = (String) req.getParameter("txtNombreCliente");
			apellido1 = (String) req.getParameter("txtApellido1Cliente");
			apellido2 = (String) req.getParameter("txtApellido2Cliente");
			telefono = (String) req.getParameter("txtTelefono");
			correo = (String) req.getParameter("txtCorreo");
			pass = (String) req.getParameter("txtPass");
			cp = Integer.parseInt(req.getParameter("txtCp"));
			// pasamos los campos capturados al modelo para su grabacion
			bd.InsertarCliente (dni,nombre,apellido1,apellido2,telefono,correo,pass,cp);
			// volvemos al principio de cliente
			res.sendRedirect (res.encodeRedirectURL("/zonaPrivada/clientes/cliente.jsp"));
			break;
		case 5:
			// baja de cliente
			bd.EliminarCliente (codigo);
			// volvemos al principio de cliente
			res.sendRedirect (res.encodeRedirectURL("/zonaPrivada/clientes/cliente.jsp"));
			break;
		case 6:
			// modificacion cliente: han confirmado datos y
			// o modificado
			dni = (String) req.getParameter("txtDniCliente");
			nombre = (String) req.getParameter("txtNombreCliente");
			apellido1 = (String) req.getParameter("txtApellido1Cliente");
			apellido2 = (String) req.getParameter("txtApellido2Cliente");
			telefono = (String) req.getParameter("txtTelefono");
			correo = (String) req.getParameter("txtCorreo");
			pass = (String) req.getParameter("txtPass");
			cp = Integer.parseInt(req.getParameter("txtCp"));
			// pasamos a modelo para la grabacion de los datos introducidos
			bd.ActualizarCliente (codigo,dni,nombre,apellido1,apellido2,telefono,correo,pass,cp);
			break;
		case 7:
			// accion de busqueda que viene de la eleccion del campo de busqueda por
			// un combo y una caja de texto en cliente.jsp
			res.sendRedirect (res.encodeRedirectURL("/zonaPrivada/clientes/cliente.jsp?campo=" + req.getParameter("CmbCampos") + ",texto=" + req.getParameter("txtbuscar")));
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
