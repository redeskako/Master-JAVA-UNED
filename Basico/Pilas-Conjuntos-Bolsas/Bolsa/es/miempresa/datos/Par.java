package es.miempresa.datos;


public class Par implements Comparable<Par> {
	private int x;
	private int y;


	public int hashCode(){
		return (new Integer(x)).hashCode()*(new Integer(y)).hashCode();
	}

	public boolean equals(Object o){

		boolean ok=false;
		try{
			Par p = (Par) o;
			ok= ((p.getX()==this.x) && (p.getY()==this.y));
		}catch(ClassCastException e){
			throw new MiErrorBonitoException("Error de casting\n"+e.toString());
		}
		return ok;
	}

	public int compareTo(Par p){
		if (this.x>p.getX()){
			return 1;
		}else if (this.x<p.getX()){
			return -1;
		}else{
			if (this.y>p.getY()){
				return 1;
			}else if (this.y<p.getY()){
				return -1;
			}else{
				return 0;
			}
		}
	}
	public Par(int x,int y){
		this.x=x;
		this.y=y;
	}
	public Par(){
		this.x=this.y=0;
	}

	public int getX(){
		return this.x;
	}
	public void setX(int x){
		this.x=x;
	}

	public int getY(){
		return this.y;
	}
	public void setY(int y){
		this.y=y;
	}

	public boolean equals(Par p){
		return ((x==p.getX()) && (y==p.getY()));
	}


	public String toString(){
		return "("+this.x+","+this.y+")";
	}
}
