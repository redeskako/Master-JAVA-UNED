package es.uned.master.java;

import java.util.Vector;
import jakarta.servlet.http.HttpServletRequest;

public class CarritoCompra {
	private Vector<String> carrito = null;
	private String producto = null;
	private String lestado = null;

	public CarritoCompra(){
		this.carrito = new Vector<String>();
	}

	public Vector<String> getProducto() {
		return this.carrito;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public void setSubmit(String estado) {
		this.lestado = estado;
	}

	public void addProducto(String producto){
	//	System.out.println(producto);
		this.carrito.add(producto);
	}

	public void eliminaProducto(String producto){
		this.carrito.remove(producto);
	}

	public void processRequest(HttpServletRequest req){
		if (lestado != null){
			this.producto = req.getParameter("producto");
			if (lestado.equals("sumar")){
				this.addProducto(this.producto);
			}else{ //eliminar
				this.eliminaProducto(producto);
			}
			this.reset();
		}
	}

	public void reset(){
		this.lestado = null;
		this.producto = null;
	}
}