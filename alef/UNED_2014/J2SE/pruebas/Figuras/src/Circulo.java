
public class Circulo extends Poligonos {
	/* En esta clase además del concepto de herencia utilizamos
	 * el método estático PI de la clase Math.
	 * Recordad lo que dijo Carlos en la última sesión. Los método estaticos 
	 * se asocian a la clase y no al objeto. Por tanto no hace falta crear un objeto
	 * de una determinada clase para su utilización. Se utilizan con la clase directamente.
	 * Como la clase Math pertenece al paquete java lang no es necesario hacer ningun import
	 * al comienzo. 
	 * 
	 */
	private int radio;
	
	public Circulo(int radio, String color){
		   super(color);
		   this.radio=radio;
		}
	
	public double area(){
		return Math.PI*radio*radio;
	}
	
	
	public int getRadio() {
		return radio;
	}

	public void setRadio(int radio) {
		this.radio = radio;
	}
	
}
