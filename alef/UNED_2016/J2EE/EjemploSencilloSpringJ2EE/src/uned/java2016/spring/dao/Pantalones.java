package uned.java2016.spring.dao;

public class Pantalones implements Prendas {
	private String color;
	private int talla;
	private float precio;

	@Override
	public String mensajeColorPrenda() {
		return ("Los pantalones son de color "+this.color);
	}

	@Override
	public String mensajeTallaPrenda() {
		return ("Los pantalones son de la talla "+this.talla);
	}

	@Override
	public String mensajePrecioPrenda() {
		return ("Los pantalones valen "+this.precio+" euros");
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getTalla() {
		return talla;
	}

	public void setTalla(int talla) {
		this.talla = talla;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}
}
