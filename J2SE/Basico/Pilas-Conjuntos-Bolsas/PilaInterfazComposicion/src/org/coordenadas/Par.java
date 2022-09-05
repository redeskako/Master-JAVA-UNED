package org.coordenadas;

import org.listas.PilaParException;
import java.util.Comparator;

/**
 * @author carlosl.sanchez
 *
 */
public class Par implements Comparator,Comparable{
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
	public int compareTo(Object o){
		return this.compare(this, o);
	}	
	public int compare(Object o1, Object o2){
		try{
			Par temp1= (Par) o1;
			Par temp2= (Par) o2;
			if (temp1.y()<temp2.y()){
				return -1;
			}else if(temp1.y()>temp2.y()){
				return 1;		
			}else{
				if (temp1.x<temp2.x()){
					return -1;
				}else if(temp1.x>temp2.x()){
					return 1;		
				}else{
					return 0;
				}
			}
		}catch (ClassCastException e){
			throw new PilaParException("No es un Par\t"+e);
		}
		//return i;
	}
}