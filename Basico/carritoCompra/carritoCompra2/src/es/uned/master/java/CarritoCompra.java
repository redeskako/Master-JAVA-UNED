package es.uned.master.java;

import java.util.Vector;
import javax.servlet.http.*;

public class CarritoCompra {
	private Vector<Libro> carrito=null;
	private Libro producto=null;
	
	private String submit=null;
	
	public CarritoCompra(){
		this.carrito= new Vector();
	}

	public Vector<Libro> getProducto() {
		return this.carrito;
	}

	public void setProducto(String producto) {
		this.producto.setLibro(producto);
	}

	public void setSubmit(String submit) {
		this.submit = submit;
	}
	public void addProducto(Libro producto){
		System.out.println(producto);
		this.carrito.add(producto);
	}
	public void eliminaProducto(Libro producto){
		this.carrito.remove(producto);
	}
	public void processRequest(HttpServletRequest req){
		if (submit !=null){
			try{
				this.producto= Libro.libro(Integer.parseInt(req.getParameter("producto")));
				if (submit.equals("sumar")){
					this.addProducto(this.producto);
				}else{
					this.eliminaProducto(this.producto);
				}
				this.reset();
			}catch(ClassCastException err){
				//throw new LibreriaException("Dato incorrecto."+err.toString());
				throw new LibreriaException("Dato incorrecto.");
			}
		}
	}
	public void reset(){
		this.submit= null;
		this.producto= null;
	}
}
