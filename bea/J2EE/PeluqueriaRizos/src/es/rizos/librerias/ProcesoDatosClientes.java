package es.rizos.librerias;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.rizos.beansClientes.Clientes;
import es.rizos.beansClientes.DatosClientes;

/**
 * Servlet implementation class for Servlet: ProcesoDatosClientes
 *
 */
 public class ProcesoDatosClientes extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {

	public ProcesoDatosClientes() {
		super();
	}
	private void doService(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			HttpSession sesion = request.getSession();
			RequestDispatcher vista = null;
			String action = request.getParameter("action");
			DatosClientes datoscliente= new DatosClientes();
   if(action!=null){
			if (action.equalsIgnoreCase("obtenerCliente")){

			     int campo=Integer.parseInt( request.getParameter("campo"));
				 DatosClientes clienteeditar=new DatosClientes();
				 clienteeditar=datoscliente.obtenerCliente(campo);
				 vista = request.getRequestDispatcher(response.encodeURL("EditarCliente.jsp"));
				 request.setAttribute("clienteeditar",clienteeditar);
				 vista.forward(request, response);
			}

			if(action.equalsIgnoreCase("editar")){
				int id = Integer.parseInt(request.getParameter("id"));
			    String nombre = (request.getParameter("nombre"));
			    String apellido1 = (request.getParameter("apellido1"));
			    String apellido2 = (request.getParameter("apellido2"));
			    String dni = (request.getParameter("dni"));
			    String direccion= (request.getParameter("direccion"));
			    int codigopostal = Integer.parseInt(request.getParameter("codigopostal"));
			    String localidad = (request.getParameter("localidad"));
			    String provincia = (request.getParameter("provincia"));
			    String telefono = (request.getParameter("telefono"));
			    String observaciones = (request.getParameter("observaciones"));
			    String tipoUsuario = (request.getParameter("tipoUsuario"));
			    String usuario = (request.getParameter("usuario"));
			    String pass= (request.getParameter("pass"));
			    System.out.println(apellido1+"editar");


			    DatosClientes cliente = new DatosClientes();
			    cliente.setId(id);
			    cliente.setNombre(nombre);
			    cliente.setApellido1(apellido1);
			    cliente.setApellido2(apellido2);
			    cliente.setDni(dni);
			    cliente.setCodigopostal(codigopostal);
			    cliente.setDireccion(direccion);
			    cliente.setLocalidad(localidad);
			    cliente.setProvincia(provincia);
			    cliente.setTelefono(telefono);
			    cliente.setObservaciones(observaciones);
			    cliente.setTipoUsuario(tipoUsuario);
			    cliente.setUsuario(usuario);
			    cliente.setPass(pass);

			    DatosClientes auxcliente=new DatosClientes();
			    auxcliente.editarCliente(cliente);
			    Clientes clientes= new Clientes();
			    ArrayList<Clientes> arrclientes = new ArrayList();
				arrclientes=clientes.ejecutaLista();
				vista = request.getRequestDispatcher(response.encodeURL("MantenimientoUsuarios.jsp"));
				request.setAttribute("cliente",arrclientes);
				vista.forward(request, response);

}
			if(action.equalsIgnoreCase("insertar")){


		        String nombre = (request.getParameter("nombre"));
		        String apellido1 = (request.getParameter("apellido1"));
		        String apellido2 = (request.getParameter("apellido2"));
		        String dni = (request.getParameter("dni"));
		        String direccion= (request.getParameter("direccion"));
		        int codigopostal = Integer.parseInt(request.getParameter("codigopostal"));
		        String localidad = (request.getParameter("localidad"));
		        String provincia = (request.getParameter("provincia"));
		        String telefono = (request.getParameter("telefono"));
		        String observaciones = (request.getParameter("observaciones"));
		        String tipoUsuario = (request.getParameter("tipoUsuario"));
		        String usuario = (request.getParameter("usuario"));
		        String pass= (request.getParameter("pass"));



		        DatosClientes cliente = new DatosClientes();
		        cliente.setNombre(nombre);
		        cliente.setApellido1(apellido1);
		        cliente.setApellido2(apellido2);
		        cliente.setDni(dni);
		        cliente.setCodigopostal(codigopostal);
		        cliente.setDireccion(direccion);
		        cliente.setLocalidad(localidad);
		        cliente.setProvincia(provincia);
		        cliente.setTelefono(telefono);
		        cliente.setObservaciones(observaciones);
		        cliente.setTipoUsuario(tipoUsuario);
		        cliente.setUsuario(usuario);
		        cliente.setPass(pass);

		        DatosClientes auxcliente=new DatosClientes();
		        boolean existeDNI=false;
		        existeDNI=auxcliente.existeDNI(cliente);

		      if(!existeDNI){
		        auxcliente.insertarCliente(cliente);
		        Clientes clientes= new Clientes();
		        ArrayList<Clientes> arrclientes = new ArrayList();

		    	arrclientes=clientes.ejecutaLista();
		    	vista = request.getRequestDispatcher(response.encodeURL("MantenimientoUsuarios.jsp"));
				request.setAttribute("cliente",arrclientes);
				vista.forward(request, response);
		       } else {
		        	vista = request.getRequestDispatcher(response.encodeURL("Bienvenida.jsp"));
					vista.forward(request, response);
		       }
		}
   }	else{
			// Si NO OK Envio una página por out de ERROR con un link a la index.html
			response.sendRedirect("GestionaAdmin.jsp");
   }
		} catch (ErrorRizosBd e){
			response.sendRedirect("error.jsp?error=Error en la conexion a la Base Datos");

		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doService(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doService(request,response);
	}
}