package rba.clientes;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.sql.*;
import java.lang.*;


import rba.bbdd.Cliente;
import rba.bbdd.Reserva;



public class AccionCliente extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet
{

	// creamos bd como de la clase AccesoDatos
	private Cliente bd;

	// funcion que cuando carga el proceso abre base de datos
	public void init(ServletConfig cfg) throws ServletException
	{
		bd=new Cliente();
	
	}
    // funcion que captura todos los datos que vienen de un jsp o servlet para realizar distintas funciones
	// en concreto con la variable ACCION, lo que se pretende es que segun el valor que tenga que realize
	// una accion alta, baja, modificacion
	public void doPost (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		HttpSession s = req.getSession(true);


		// como de los campos, para trabajar en la funcion

    	String nombre = "";
		String apellido1 = "";
		String apellido2 = "";
		String telefono = "";
		String correo = "";
		String pass = "";
		Integer cp = null;
		
		

		// cargamos los datos de accion (accion a realizar alba, baja, modificacion)
		// como del codigo del registro en la que se va a realizar la accion


		String codigo2 = (String) req.getParameter("codigo");
		String accion2 = (String) req.getParameter("accion");

		Integer accion = Integer.parseInt(accion2);
		Integer codigo;


		switch (accion)
		{
		case 1:
			// baja cliente: se manda a formulario para confirmar eliminacion
			 codigo = Integer.parseInt(codigo2);
			res.sendRedirect ("zonaPrivada/clientes/bajaCliente.jsp?codigo="+codigo);
			break;
		case 2:
			// modificacion cliente: se manda a formulario para que se introduzcan los datos y confirme su actualizacion
			codigo = Integer.parseInt(codigo2);
			res.sendRedirect ("zonaPrivada/clientes/modificarCliente.jsp?codigo="+codigo);
			break;
		case 3:
			// alta cliente: nos lleva al formulario de captura de datos
			res.sendRedirect ("zonaPrivada/clientes/altaCliente2.jsp");
			break;
		case 4:
			// alta de cliente capturamos los datos introducidos el altacliente.jsp
			nombre = (String) req.getParameter("txtNombreCliente");
			apellido1 = (String) req.getParameter("txtApellido1Cliente");
			apellido2 = (String) req.getParameter("txtApellido2Cliente");
			telefono = (String) req.getParameter("txtTelefono");
			correo = (String) req.getParameter("txtCorreo");
			pass = (String) req.getParameter("txtPass");
		
			
			if (((apellido1.trim()).compareTo(""))==0){
				apellido1= " ";				
			}
			
			if (((apellido2.trim()).compareTo(""))==0){
				apellido2= " ";				
			}
			
			if (((telefono.trim()).compareTo(""))==0){
				telefono= " ";				
			}
			 
				try{
					cp = Integer.parseInt(req.getParameter("txtCp"));
					 if (cp == null) {
						 cp=0;
						 }
				}catch(Exception e){
					cp=0;
				}
			 	
			// controlamos que sea solo numeros los datos que se introduzcan
			// con lo que si se introducen text dara error y nos metera en la bd un 0
		
	
			if (((nombre.trim()).compareTo(""))==0){
				nombre = " ";				
				res.sendRedirect ("zonaPrivada/clientes/altaCliente2.jsp?nombre="+nombre+"&apellido1="+apellido1+"&apellido2="+apellido2+"&telefono="+telefono+"&correo="+correo+"&pass="+pass+"&cp="+cp);				
				break;			
			}
			if (((correo.trim()).compareTo(""))==0){
				correo = " ";
				res.sendRedirect ("zonaPrivada/clientes/altaCliente2.jsp?nombre="+nombre+"&apellido1="+apellido1+"&apellido2="+apellido2+"&telefono="+telefono+"&correo="+correo+"&pass="+pass+"&cp="+cp);				
				break;			
			}
		
			if (((pass.trim()).compareTo(""))==0){
				pass = " ";
				res.sendRedirect ("zonaPrivada/clientes/altaCliente2.jsp?nombre="+nombre+"&apellido1="+apellido1+"&apellido2="+apellido2+"&telefono="+telefono+"&correo="+correo+"&pass="+pass+"&cp="+cp);
				
				break;
			}
			
			
				

			// pasamos los campos capturados al modelo para su grabacion
			bd.InsertarCliente (nombre,apellido1,apellido2,telefono,correo,pass,cp);
			// volvemos al principio de cliente
			res.sendRedirect ("zonaPrivada/clientes/cliente.jsp");
			break;
		case 5:
			// baja de cliente
			codigo = Integer.parseInt(codigo2);
			bd.EliminarCliente (codigo);
			// volvemos al principio de cliente
			res.sendRedirect ("zonaPrivada/clientes/cliente.jsp");
			break;
		case 6:
			// modificacion cliente: han confirmado datos y
			// o modificado
			codigo = Integer.parseInt(codigo2);

			nombre = (String) req.getParameter("txtNombreCliente");
			apellido1 = (String) req.getParameter("txtApellido1Cliente");
			apellido2 = (String) req.getParameter("txtApellido2Cliente");
			telefono = (String) req.getParameter("txtTelefono");
			correo = (String) req.getParameter("txtCorreo");
			pass = (String) req.getParameter("txtPass");
		
			
			if (((apellido1.trim()).compareTo(""))==0){
				apellido1= " ";				
			}
			
			if (((apellido2.trim()).compareTo(""))==0){
				apellido2= " ";				
			}
			
			if (((telefono.trim()).compareTo(""))==0){
				telefono= " ";				
			}
			 
				try{
					cp = Integer.parseInt(req.getParameter("txtCp"));
					 if (cp == null) {
						 cp=0;
						 }
				}catch(Exception e){
					cp=0;
				}
			 	
			// controlamos que sea solo numeros los datos que se introduzcan
			// con lo que si se introducen text dara error y nos metera en la bd un 0
		
	
			if (((nombre.trim()).compareTo(""))==0){
				nombre = " ";				
				res.sendRedirect ("zonaPrivada/clientes/modificarCliente.jsp?codigo="+codigo+"&nombre="+nombre+"&apellido1="+apellido1+"&apellido2="+apellido2+"&telefono="+telefono+"&correo="+correo+"&pass="+pass+"&cp="+cp);				
				break;			
			}
			if (((correo.trim()).compareTo(""))==0){
				correo = " ";
				res.sendRedirect ("zonaPrivada/clientes/modificarCliente.jsp?codigo="+codigo+"&nombre="+nombre+"&apellido1="+apellido1+"&apellido2="+apellido2+"&telefono="+telefono+"&correo="+correo+"&pass="+pass+"&cp="+cp);				
				break;			
			}
		
			if (((pass.trim()).compareTo(""))==0){
				pass = " ";
				res.sendRedirect ("zonaPrivada/clientes/modificarCliente.jsp?codigo="+codigo+"&nombre="+nombre+"&apellido1="+apellido1+"&apellido2="+apellido2+"&telefono="+telefono+"&correo="+correo+"&pass="+pass+"&cp="+cp);
				
				break;
			}
			
			
			// pasamos a modelo para la grabacion de los datos introducidos
			bd.ActualizarCliente (codigo,nombre,apellido1,apellido2,telefono,correo,pass,cp);

			res.sendRedirect ("zonaPrivada/clientes/cliente.jsp");
			break;
		case 7:
			// accion de busqueda que viene de la eleccion del campo de busqueda por
			// un combo y una caja de texto en cliente.jsp
			String campo = req.getParameter("CmbCampos");
			String texto = req.getParameter("txtbuscar");
			res.sendRedirect ("zonaPrivada/clientes/cliente.jsp?campo=" + campo  + "&texto=" + texto );
			break;
		case 8:
			String columna = req.getParameter("campo");
			res.sendRedirect("zonaPrivada/clientes/cliente.jsp?campo=" + columna );
			break;
		}




	}
	public void doGet (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		try{
			doPost(req,res);

		}catch (Exception e){


		}

		
	}




}

