package PaqueteLibros;
//importar clases
import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.*;

//definir la clase libreria
public class Libreria {
	//crear el vector Libro, producto y submit
	private Vector<Libro> itemLibreria=null;
	private Libro producto=null;
	private String submit=null;

	public Libreria(){
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

	/*
	public void processRequest(HttpServletRequest req){
		if (submit !=null){
			try{
				this.producto= new Libro(0,req.getParameter("producto"));
				if (submit.equals("enviar")){
					//llamar a anadir un producto
					this.addProducto(this.producto);
				}
				this.reset();
			}catch(ClassCastException err){
				//throw new LibreriaException("Dato incorrecto."+err.toString());
				throw new LibreriaException("Dato incorrecto.");
			}
		}
	}
	*/

/*
	protected void doService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion = request.getSession(true);
		String submit_aqui=request.getParameter("accion");
		//String id=request.getParameter("id");
		if (submit_aqui !=null){
			try{

				if (submit_aqui.equals("borrar")){

					//Libro.borrar(id);
					response.sendRedirect(response.encodeRedirectURL(request.getContextPath () + "/listaLibros.jsp"));
				}

				if (submit_aqui.equals("anadir")){
					response.sendRedirect(response.encodeRedirectURL(request.getContextPath () + "/listaLibros.jsp"));
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

*/
	public void reset(){
		this.submit= null;
		this.producto= null;
	}
}
