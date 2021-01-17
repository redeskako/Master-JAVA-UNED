package es.rizos.librerias;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.rizos.beansClientes.Clientes;


 public class ProcesoClientes extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {


	   public ArrayList<Clientes> clie= new ArrayList<Clientes>();

	   public ProcesoClientes() {
		super();
	}

	private void doService(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		//recogo la informaci�n de que operaci�n ha sido mandada

		try {
			HttpSession sesion = request.getSession();
			RequestDispatcher vista = null;
			String action = request.getParameter("action");
			Clientes pclientes= new Clientes();
       if (action!=null){
			if (action.equalsIgnoreCase("ejecuta")){
				clie=pclientes.ejecutaLista();
				vista = request.getRequestDispatcher(response.encodeURL("MantenimientoUsuarios.jsp"));
				request.setAttribute("cliente",clie);
				vista.forward(request, response);
			}

			if (action.equalsIgnoreCase("buscar")){
				String campobusqueda=(String) request.getParameter("campobusqueda");
				String textobusqueda=(String) request.getParameter("textobusqueda");
				clie=pclientes.buscar(campobusqueda, textobusqueda);
			  vista = request.getRequestDispatcher(response.encodeURL("MantenimientoUsuarios.jsp"));
			  request.setAttribute("cliente",clie);
			  vista.forward(request, response);
			}

			if(action.equalsIgnoreCase("ordenar")){
			  String campo=(String) request.getParameter("campo");
			  clie=pclientes.ordenar(campo);
			  vista = request.getRequestDispatcher(response.encodeURL("MantenimientoUsuarios.jsp"));
			  request.setAttribute("cliente",clie);
			  vista.forward(request, response);
			}

			if (action.equalsIgnoreCase("obtenerCliente")){
			     int campo=Integer.parseInt( request.getParameter("campo"));
				 Clientes clienteeditar=new Clientes();
				 clienteeditar=pclientes.obtenerCliente(campo);
				 vista = request.getRequestDispatcher(response.encodeURL("mostrar.jsp"));
				 request.setAttribute("clienteeditar",clienteeditar);
				 vista.forward(request, response);
			}
			if(action.equalsIgnoreCase("editar")){
			        int id = Integer.parseInt(request.getParameter("id"));
			        String nombre = (request.getParameter("nombre"));
			        String apellido1 = (request.getParameter("apellido1"));
			        String apellido2 = (request.getParameter("apellido2"));
			        String dni = (request.getParameter("dni"));

			        Clientes cliente = new Clientes();
			        cliente.setId(id);
			        cliente.setNombre(nombre);
			        cliente.setApellido1(apellido1);
			        cliente.setApellido2(apellido2);
			        Clientes auxcliente=new Clientes();
			        auxcliente.editarCliente(cliente);
			    	clie=pclientes.ejecutaLista();
			    	vista = request.getRequestDispatcher(response.encodeURL("MantenimientoUsuarios.jsp"));
					request.setAttribute("cliente",clie);
					vista.forward(request, response);

			}
			 if(action.equalsIgnoreCase("eliminar")){
				 int id = Integer.parseInt(request.getParameter("campo"));
				 pclientes.eliminarCliente(id);

			    clie=pclientes.ejecutaLista();

			 	vista = request.getRequestDispatcher(response.encodeURL("MantenimientoUsuarios.jsp"));
				request.setAttribute("cliente",clie);
				vista.forward(request, response);

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