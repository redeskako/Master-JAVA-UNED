
public abstract class Poligonos {
	/* ¿Por qué se define la clase Poligonos como abstracta?. Simplemente porque 
	 * tiene un método que es abstracto. En este caso el método area. En una clase
	 * abstracta puede haber más de un método abstracto.Una clase es abstracta cuando tiene
	 * al menos un método abstracto. Si una clase tiene todos sus métodos abstractos se dice que
	 * es un interface, pero esto lo va a explicar Carlos.
	 * 
	 * ¿Cuando se dice que un método es abstracto?. Cuando solamente se define
	 * el nombre, los parametros que recibe para su ejecución y el tipo de valor que
	 * devuelve.NO HAY FUNCIONALIDAD.NO TIENE DEFINIDO EL CUERPO.
	 * 
	 * ¿Para que se definen los métodos abstractos. Por cuestiones de diseño. Al emplear un método 
	 * abstracto, el diseñador de la aplicación obliga al programador a definir la funcionalidad del
	 * método abstracto, pero le deja total libertad a como hacerlo. Solamente le obliga a utilizar un
	 * determinado nombre y a que devuelva un determinado tipo de valor.  
	 * 
	 * 
	 * De una clase abstracta no se pueden crear objetos. Es decir no se puede hacer
	 * Poligono obj = new Poligono();
	 */
	
	
	private String color;
	
	public Poligonos(String arg){
	  this.color=arg;
	}

	public abstract double area();

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
		
	
	
}
