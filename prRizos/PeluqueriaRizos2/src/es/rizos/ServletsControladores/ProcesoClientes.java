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
            	 int totalpaginas=Clientes.calculaTotalPaginas();
            	  sesion.setAttribute("paginasClientes", totalpaginas);

			if (action.equalsIgnoreCase("ejecuta")){
                clie=pclientes.ejecutaLista(inicio);
                sesion.setAttribute("cliente",clie);
                sesion.setAttribute("campobusqueda","nombre");
                sesion.setAttribute("textobusqueda", "vacio");
                sesion.setAttribute("campo", "nombre");
                response.sendRedirect("MantenimientoUsuarios2.jsp?action=ejecuta");
			}

			if (action.equalsIgnoreCase("buscar")){
				String campobusqueda=(String) request.getParameter("campobusqueda");
				String textobusqueda=(String) request.getParameter("textobusqueda");
				clie=pclientes.buscar(campobusqueda, textobusqueda,inicio);
				sesion.setAttribute("cliente",clie);
                sesion.setAttribute("campobusqueda",campobusqueda);
                sesion.setAttribute("textobusqueda",textobusqueda);
                response.sendRedirect("MantenimientoUsuarios2.jsp?action=buscar");
			}

			if(action.equalsIgnoreCase("ordenar")){
                String campo=(String) request.getParameter("campo");
                clie=pclientes.ordenar(campo,inicio);
                sesion.setAttribute("campo",campo);
                sesion.setAttribute("cliente",clie);
                response.sendRedirect("MantenimientoUsuarios2.jsp?action=ordenar");
            }
            if(action.equalsIgnoreCase("eliminar")){
				 int id = Integer.parseInt(request.getParameter("campo"));
				 pclientes.eliminarCliente(id);
                 clie=pclientes.ejecutaLista(0);
                 sesion.setAttribute("cliente",clie);
				 response.sendRedirect("Confirmar.jsp");

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