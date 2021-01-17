package PaqueteLibros;


//importar clases
import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.*;

/**
 * Servlet implementation class for Servlet: Libreria2
 *
 */
 public class Libreria2 extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	 private Vector<Libro> itemLibreria=null;
		private Libro producto=null;
		private String submit=null;


	public Libreria2() {
		this.itemLibreria= new Vector();
	}
	public Vector<Libro> getProducto() {
		return this.itemLibreria;
	}

	public Vector<Libro> consultaLibreria()
	{
		this.itemLibreria=Libro.listaLibro();
		return this.itemLibreria;
	}

	public void setProducto(String producto) {
		this.producto.setLibro(producto);
	}

	public void setSubmit(String submit) {
		this.submit = submit;
	}
	public void addProducto(Libro producto){
		System.out.println(producto);
		this.itemLibreria.add(producto);
		//Libro.insertar(producto);
	}

	protected void doService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion = request.getSession(true);
		String submit_aqui=request.getParameter("accion");
		//String id=request.getParameter("id");
		if (submit_aqui !=null){
			try{

				if (submit_aqui.equals("borrar")){

					String id=request.getParameter("id");
					Libro.borrar(id);
					response.sendRedirect(response.encodeRedirectURL(request.getContextPath () + "/libros/listaLibros.jsp"));
				}

				if (submit_aqui.equals("anadir")){
					//Libro l= new Libro(request.getParameter("id"), request.getParameter("nombre"));
					//response.sendRedirect(response.encodeRedirectURL(request.getContextPath () + "/listaLibros.jsp"));
				}

			}catch(ClassCastException err){
				sesion.setAttribute("EstadoSesion","Error:Sesion cerrada incorrectamente");
				response.sendRedirect(response.encodeRedirectURL(request.getContextPath () + "/index.jsp"));
			}
		}
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doService(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doService(request, response);
	}


	public void reset(){
		this.submit= null;
		this.producto= null;
	}
}