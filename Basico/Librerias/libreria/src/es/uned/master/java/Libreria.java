package es.uned.master.java;
// Importar clases
import java.util.Vector;
import jakarta.servlet.http.HttpServletRequest;

// Definir la clase libreria
public class Libreria{
	// Crear el vector Libro, producto y submit
	private Vector<Libro> itemLibreria = null;
	private Libro producto = null;
	private String submit = null;

	public Libreria(){
		this.itemLibreria = new Vector();
	}

	public Vector<Libro> getProducto(){
		return this.itemLibreria;
	}

	public Vector<Libro> consultaLibreria(){
		this.itemLibreria = Libro.listaLibro();
		return this.itemLibreria;
	}

	public void setProducto(String producto){
		this.producto.setLibro(producto);
	}

	public void setSubmit(String submit){
		this.submit = submit;
	}

	public void addProducto(Libro producto){
		System.out.println(producto);
		this.itemLibreria.add(producto);
		Libro.insertar(producto);
	}

	public void processRequest(HttpServletRequest req){
		if (submit != null){
			try{
				this.producto = new Libro(0, req.getParameter("libro"));
				if (submit.equals("submit")){
					// Llamar a anadir un producto
					this.addProducto(this.producto);
				}
				this.reset();
			}catch(ClassCastException err){
				//throw new LibreriaException("Dato incorrecto."+err.toString());
				throw new LibreriaException("Dato incorrecto.");
			}
		}
	}

	public void reset(){
		this.submit = null;
		this.producto = null;
	}
}