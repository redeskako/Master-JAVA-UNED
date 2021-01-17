package org.coordenadas;

/**
 * @author carlosl.sanchez
 *
 */
public class Par implements Comparable<Par> {
	//Declaración de propiedades del objeto
	private int x;
	private int y;
	//Declaración de métodos

	public Par(int x,int y){
		this.x=x;
		this.y=y;
	}
	public Par(int x){
		this.x=x;
		this.y=0;
	}
	public int x(){
		return this.x;
	}
	public void x(int x){
		this.x=x;
	}
	public int y(){
		return this.y;
	}
	public void y(int y){
		this.y=y;
	}
	public boolean equals(Object o){
		Par p1= (Par) o;
		return ((this.x==p1.x()) && (this.y==p1.y()));
	}
	public String toString(){
		return "("+this.x+","+y+")";
	}
	@Override
	public int compareTo(Par arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
}


/*public int compareTo(Par p){
	if(this.x>p.getX()){
		return 1;
	}else if (this.x<p.getX()){
		return -1;
	}else{
		if(this.y>p.getY()){
			return 1;
		}else if (this.y<p.getY()){
			return -1;
	}
}
*/