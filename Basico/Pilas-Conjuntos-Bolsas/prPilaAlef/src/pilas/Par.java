package pilas;



import pilas.MiPilaException;
import pilas.Par;

public class Par {
	//propiedades

	private int x;
	private int y;

	/**
	 * redefinimos el método equals. Lo que hace es ver si son iguales las componentes de mi mismo
	 * y de el objeto Par que me viene como argumento
	 */

	public boolean equals(Object o){
		boolean ok=false;
		try{
			Par p = (Par) o; //instanciamos la clase Par
			ok= ((p.getX()==this.x) && (p.getY()==this.y));
		}catch(ClassCastException e){
			throw new MiPilaException("Error de casting\n"+e.toString());
		}
		return ok;
	}
    //constructor Par, que me inicializa las componentes de mi clase por las pasadas como parámetros
	public Par(int x,int y){
		this.x=x;
		this.y=y;
	}
	//constructor Par sin argumentos, que me inicializa, tanto la x como la y al valor 0
	public Par(){
		this.x=this.y=0;
	}
    //método que me devuelve el valor de la x
	public int getX(){
		return this.x;
	}
	//método que me actualiza el valor de la x
	public void setX(int x){
		this.x=x;
	}

	//método que me devuelve el valor de la y
	public int getY(){
		return this.y;
	}

	//método que me actualiza el valor de la y
	public void setY(int y){
		this.y=y;
	}

	//método equals REDEFINIDO, me compara dos pares, el de mi mismo y el que me pasa como parámetro
	//componente a componente.
	//Me devolverá true si son iguales, y false en caso contrario
	public boolean equals(Par p){
		return ((this.x == p.getX()) && (this.y == p.getY()));
	}

    //método que me imprime las componentes x e y del par
	public String toString(){
		return "("+this.x+","+this.y+")";
	}
}
