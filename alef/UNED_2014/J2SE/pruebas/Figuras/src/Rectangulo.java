
public class Rectangulo extends Poligonos {
	int ancho;
	int alto;
	
	public Rectangulo(String color, int ancho, int alto) {
		super(color);
		this.ancho=ancho;
		this.alto=alto;
	}
	
	public double area(){
		return ancho*alto;
	}

	public double getAncho() {
		return ancho;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	public double getAlto() {
		return alto;
	}

	public void setAlto(int alto) {
		this.alto = alto;
	}

	
}
