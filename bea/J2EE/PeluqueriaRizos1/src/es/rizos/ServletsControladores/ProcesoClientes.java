package es.rizos.ServletsControladores;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.rizos.beansClientes.Clientes;
import es.rizos.librerias.ErrorRizosBd;


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
                        //Controlamos la pagina que tenemos que representar
                        //=================================================
                        int inicio=0;
                        try{
                                String cad= request.getParameter("paginado");
                                inicio= Integer.parseInt(cad);
                        }catch(Exception e){ inicio=0;}
                        //===================================================
			Clientes pclientes= new Clientes();
            if (action!=null){
			if (action.equalsIgnoreCase("ejecuta")){
                clie=pclientes.ejecutaLista(inicio);
				vista = request.getRequestDispatcher(response.encodeURL("MantenimientoUsuarios.jsp?action=ejecuta"));
				request.setAttribute("cliente",clie);
				vista.forward(request, response);
			}

			if (action.equalsIgnoreCase("buscar")){
				String campobusqueda=(String) request.getParameter("campobusqueda");
				String textobusqueda=(String) request.getParameter("textobusqueda");
				System.out.println(campobusqueda);
				System.out.println(inicio);
				clie=pclientes.buscar(campobusqueda, textobusqueda,inicio);
                vista = request.getRequestDispatcher(response.encodeURL("MantenimientoUsuarios.jsp?action=buscar"));
                request.setAttribute("cliente",clie);
                request.setAttribute("campobusqueda",campobusqueda);
                request.setAttribute("textobusqueda",textobusqueda);
                vista.forward(request, response);
			}

			if(action.equalsIgnoreCase("ordenar")){
                String campo=(String) request.getParameter("campo");
                clie=pclientes.ordenar(campo,inicio);
                vista = request.getRequestDispatcher(response.encodeURL("MantenimientoUsuarios.jsp?action=ordenar"));
                request.setAttribute("campo",campo);
                request.setAttribute("cliente",clie);
                vista.forward(request, response);
			}


            if(action.equalsIgnoreCase("eliminar")){
				 int id = Integer.parseInt(request.getParameter("campo"));
				 pclientes.eliminarCliente(id);
                 clie=pclientes.ejecutaLista(0);
                 vista = request.getRequestDispatcher(response.encodeURL("Confirmar.jsp"));
				 request.setAttribute("cliente",clie);
				 vista.forward(request, response);
			}
                }else{
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