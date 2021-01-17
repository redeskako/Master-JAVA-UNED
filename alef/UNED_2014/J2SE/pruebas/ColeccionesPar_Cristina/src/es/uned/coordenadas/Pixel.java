package es.uned.coordenadas;

/*
 * Se define la clase Pixel, que hereda de la clase Par.
 * Sus variables heredadas son dos valores enteros denominados x e y que representan la ordenada y la 
 * abscisa de un punto en un plano.
 * Añade la variable color de tipo String que representa el color del punto del plano.
 * @autor	Varios UNED 2014
 * @version
 * @fecha 	2014
 */
public class Pixel extends Par{
	// Definición de variables:
	// Propiedad de instancia color: String que representa el color de un punto.
	private String color;
	
	/*
	 * Método constructor: se construye un Pixel sobrecargando el método constructor de Par, 
	 * y añadiendo un valor String que se asigna a la variable color.
	 * @in	String que se asigna, en mayúsculas, a la variable color.
	 * 		Valor entero x que se utilizará como parámetro para el método constructor de Par.
	 * 		Valor entero y que se utilizará como parámetro para el método constructor de Par.
	 * @out	null
	 * @err	null
	 */
	public Pixel(String color, int x, int y){
		// Se llama al método constructor de Par
		super(x,y);
		
		// Se asigna el String en mayúsculas a la variable color.
		this.color=color.toUpperCase();
	}
	
	/*
	 * Método constructor: se construye un Pixel sobrecargando el método constructor de Par, 
	 * y añadiendo un valor String que se asigna a la variable color.
	 * @in	String que se asigna, en mayúsculas, a la variable color.
	 * 		Objeto Par, cuyas variable x e y se utilizarán como parámetros para el constructor de Par. 
	 * @out	null
	 * @err	null
	 */
	public Pixel(String color, Par p){
		// Se llama al método constructor de Par
		super(p.getX(),p.getY());
		
		// Se asigna el String en mayúsculas a la variable color.
		this.color=color.toUpperCase();		
	}
	
	/*
	 * Método constructor: se construye un Pixel sobrecargando el método constructor de Par, 
	 * y añadiendo un valor String que se asigna a la variable color.
	 * @in	String que se asigna, en mayúsculas, a la variable color.
	 * 		Valor entero x que se utilizará como parámetro para el método constructor de Par.
	 * @out	null
	 * @err	null
	 */
	public Pixel(String color, int x){
		// Se llama al método constructor de Par
		super(x);
		
		// Se asigna el String en mayúsculas a la variable color.
		this.color=color.toUpperCase();			
	}
	
	/*
	 * Método constructor: se construye un Pixel sobrecargando el método constructor de Par, 
	 * y asignando el valor "BLANCO" a la variable color.
	 * @in	Valor entero x que se utilizará como parámetro para el método constructor de Par.
	 * @out	null
	 * @err	null
	 */
	public Pixel(int x){
		// Se llama al método constructor de Par
		super(x);
		
		// Se asigna el valor "BLANCO" a la variable color.
		this.color="BLANCO";		
	}
	
	/*
	 * Se obtiene el valor de la variable color.
	 * @in	null
	 * @out	Devuelve el valor color, de tipo String.
	 * @err	null
	 */
	public String getColor(){
		return color;
	}
	
	/*
	 * Se establece la relación de igualdad entre dos Pixel:
	 * Dos Pixel son iguales si se cumple la condición de igualdad de Par y además sus 
	 * variables color son iguales (true).
	 * En cualquier otro caso, no son iguales (false)
	 * @in	Parámetro de tipo Object con el que comparar el Pixel.
	 * @out	Booleano true si los dos objetos son iguales, false si no lo son.
	 * @err	null
	 * @see es.uned.coordenadas.Par#equals(java.lang.Object)
	 */
	public boolean equals (Object o){
		try{
			// Se hace el casting a Pixel
			Pixel pi = (Pixel) o;
			
			// Se obtiene la relación de igualdad de Pixel (igualdad de Par + igualdad de color)
			return (super.equals(pi) && this.getColor().equals(pi.getColor()) );
			
		} catch(ClassCastException e){
			// En caso de error se considera que NO son iguales.
			// Si el parámetro (o) no es un Pixel, no pueden ser iguales.
			// No se lanza error.
			return false;
		}
		// [AZUL (1,-5)] equals "hola" --> false
		// [AZUL (1,-5)] equals [AZUL (0,2)] --> false
		// [AZUL (1,-5)] equals [VERDE (1,-5)] --> false
		// [AZUL (1,-5)] equals [AZUL (1,-5)] --> true
	}
	
	/*
	 * Se define un identificador de tipo hash: suma del hashCode de Par y el hashCode de la variable color.
	 * @in	null
	 * @out	Identificador hash de tipo entero para una instancia de la clase Pixel.
	 * @err null
	 * @see java.lang.Object#hashCode()
	 */	
	public int hashCode(){
		return super.hashCode() + this.getColor().hashCode();
	}
	
	/*
	 * Se define el criterio de orden dentro de un conjunto de elementos Pixel.
	 * Para la comparación de Pixeles:
	 * 		1º Se compara Par (variables x e y).
	 * 		2º Si los Pares son iguales, se devuelve la comparación entre color.
	 * 		3º Si los Pares son diferentes, se devuelve la comparación entre Pares.
	 * @in	El parámetro será un objeto tipo Pixel con el que comparar.
	 * @out	Valor entero que representa si el Pixel a comparar (this) es mayor (+1),
	 * 		igual (0) o menor (-1) que el parámetro.
	 * @err	null
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Pixel pi){
		// Se obtiene la comparación de Color.
		int compColor = this.getColor().compareTo(pi.getColor());
		
		switch (compColor){
			// Si los colores son iguales, se devuelve la comparación de par.
			case 0:
				return super.compareTo(pi);
			// Si los colores NO son iguales, se devuelve la comparación de color.
			default:
				return compColor;
		}
	}
	
	/*
	 * Se define un String que representa al Pixel con el formato [COLOR; Par(x,y)].
	 * @in	null
	 * @out	String que representa al Pixel.
	 * @err	null
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return "[" +this.getColor()+"; Par"+super.toString()+"]";
	}
}
