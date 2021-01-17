package es.uned.coordenadas;

/*
 * Se define la clase Par, que implementan la interfaz Comparable de java.lang.
 * Sus variables son dos valores enteros denominados x e y que representan la ordenada y la 
 * abscisa de un punto en un plano.
 * @autor	Varios UNED 2014
 * @version
 * @fecha 	2014
 */

public class Par implements Comparable<Par>{
	
	// Definición de variables:
	// Variable de instancia x: es un valor entero que representa la ordenada de un punto.
	private int x;
	// Variable de instancia y: es un valor entero que representa la abscisa de un punto.
	private int y;
	
	/*
	 * Método constructor: se construye un Par mediante dos valores enteros que asignará a las variables x e y.
	 * @in 	número entero x que se asignará a la variable x.
	 * 		número entero y que se asignará a la variable y.
	 * @out	null
	 * @err	null
	 */
	public Par(int x,int y){
		this.x=x;
		this.y=y;
	}
	
	/*
	 * Método constructor: se construye un Par mediante un valores entero que asignará a la variable x, asignando 0 
	 * a la variable y.
	 * @in 	número entero x que se asignará a la variable x.
	 * @out	null
	 * @err	null
	 */
	public Par(int x){
	this.x=x;
	this.y=0;
	}
	
	/*
	 * Se obtiene el valor de la variable x.
	 * @in	null
	 * @out	Devuelve el valor entero de la variable x.
	 * @err	null
	 */
	public int getX(){
		return x;
	}
		/*
	 * Se establece un nuevo valor entero y se asigna a la variable x.
	 * @in	Valor entero que se asignará a la variable x.
	 * @out	null
	 * @err	null
	 */
	public void setX(int x){
		this.x=x;
	}
	
	/*
	 * Se obtiene el valor de la variable y.
	 * @in	null
	 * @out	Devuelve el valor entero de la variable y.
	 * @err	null
	 */
	public int getY(){
		return this.y;
	}
	
	/*
	 * Se establece un nuevo valor entero y se asigna a la variable y.
	 * @in	Valor entero que se asignará a la variable y.
	 * @out	null
	 * @err	null
	 */
	public void setY(int y){
		this.y=y;
	}
	
	/*
	 * Se establece el criterio de igualdad (necesario para definir la relación de 
	 * equivalencia) para dos objetos de tipo Par. 
	 * El método devolverá el booleano true si los dos objetos Par tienen 
	 * el mismo valor entero para x y el mismo valor entero para y. 
	 * En cualquier otro caso, devolverá false.
	 * @in	Parámetro de tipo Object con el que comparar el Par.
	 * @out Booleano true si los dos objetos son iguales, false si no lo son.
	 * @err	null
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
		public boolean equals(Object o){
		// Gestión de errores (se verifica si o es un Par).
		try{
			// Se hace el casting
			Par p = (Par) o;
			
			// Se envuelven los enteros (x) en la clase Integer.
			Integer intThis = new Integer(this.x);
			Integer intP = new Integer(p.getX());
			
			// Se obtiene la relación de igualdad de x.
			boolean equalsX = intThis.equals(intP);
			
			// Se envuelven los enteros (y) en la clase Integer.
			intThis = new Integer(this.y);
			intP = new Integer(p.getY());
			
			// Se obtiene la relación de igualdad de y.
			boolean equalsY = intThis.equals(intP);
			
			// Se obtiene la relación de igualdad del Par.
			return  (equalsX && equalsY);
		}catch(ClassCastException e){
			// En caso de error se considera que NO son iguales 
			// (Pepino no es un Par --> no son iguales)
			// No se lanza error.
			return false;
		}
		// (3,1) equals "hola" --> false
		// (3,1) equals (1,3) --> false
		// (3,1) equals (3,3) --> false
		// (3,1) equals (3,1) --> true
		
	}

	/*public boolean equals(Object o){
		try{
			Par p1= (Par) o;
		 	return ((this.x==p1.getX()) && (this.y==p1.getY()));
		}catch(ClassCastException e){
			return false;
		}
	 }*/
	
	
	/*
	 * Se define un identificador de tipo hash (necesario para definir la relación de 
	 * equivalencia), que devuelve un identificador de tipo entero como resultado de la 
	 * suma de x e y para una instancia de la clase Par.
	 * @in	null
	 * @out	Identificador hash de tipo entero para una instancia de la clase Par.
	 * @err null
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode(){
		return (new Integer(x+y)).hashCode();
		//return ((new Integer(x)).toString()+(new Integer(y).toString())).hashCode();
	}
	
	/*
	 * Como Par implementa la interfaz comparable, se define el método compareTo que 
	 * define el criterio de orden dentro de un conjunto de elementos.
	 * Para la comparación de Pares:
	 * 		1º Se comparan los valores de x.
	 * 		2º Si las x son iguales, se devuelve la comparación entre y.
	 * 		3º Si las x son diferentes, se devuelve la comparación entre x.
	 * @in	El parámetro será un objeto tipo Par con el que comparar.
	 * @out	Valor entero que representa si el Par a comparar es mayor (+1),
	 * 		igual (0) o menor -1) que el parámetro.
	 * @err	null
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
		public int compareTo(Par p){
		
		// Se envuelven los valores enteros (x) en la clase Integer.
		Integer intThis = new Integer(this.x);
		Integer intP = new Integer(p.getX());
		
		// Se obtiene la comparación de los valores x.
		int compareX = intThis.compareTo(intP);
		
		// Se envuelven los valores enteros (y) en la clase Integer.
		intThis = new Integer(this.y);
		intP = new Integer(p.getY());
		
		// Se obtiene la comparación de los valores x.
		int compareY = intThis.compareTo(intP);
		
		// Se obtiene la comparación de Par.
		switch (compareX){
			// Si los valores de x son iguales, se devuelve la comparación de y.
			case 0:
				return compareY;
			// Si los valores de x NO son iguales, se devuelve la comparación de x.
			default:
				return compareX;
		}
		// (3,1) compareTo (1,3) --> +1
		// (3,1) compareTo (3,3) --> 0
		// (3,3) compareTo (3,1) --> -1
		// (1,3) compareTo (3,1) --> -1
	}
	
	/*
	 * Se define un String que representa al Par con el formato (x,y).
	 * @in	null
	 * @out	String que representa al Par.
	 * @err	null
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return "("+this.x+","+y+")";
	}

}