package pilas;

public class Point {
    // variables de instancia

	private int x;
	private int y;
	private int z;


	//	 constructor , la variable this contiene una referencia del objeto con la que se invoco el metodo

	public Point(int x, int y, int z){
		this.x = x;
		this.y = y;
		this.z = z;
	}

	// junta las variables en una cadena de tipo string

	public String toString(){
		return "("+x+","+y+","+z+")";
	}

}
