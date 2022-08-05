//CLASE USUARIO
//Define cada usuario de la base de datos

package org.aprende.java.bbdd;
import java.util.*;

public class Usuario implements Comparable{

	private int id ;
	private String nombre;
	private String clave;
	
	//constructor
	public Usuario(int id, String nombre, String clave){
		this.id=id;
		this.nombre=nombre;
		this.clave=clave;
	}

	public String clave() {
		return clave;
	}

	public void clave(String clave) {
		this.clave = clave;
	}

	//get y set de las variables de clase
	public int Id() {
		return this.id;
	}
	public void Id(int id) {
		this.id = id;
	}
	public String nombre() {
		return this.nombre;
	}
	public void nombre(String usu){
		this.nombre= usu;
	}

	
	//metodos
	public String toString(){
		return " (Usuario "+this.id + ":" + this.nombre+":"+this.clave+") ";
	}	
	public int compareTo(Object o){
		Usuario u = (Usuario) o;
		return this.nombre.compareToIgnoreCase(u.nombre); //Ordenar� por nombre
	}
	public boolean equals(Object o){
		Usuario u = (Usuario) o;
		return this.compareTo(u)==0; //Dos usuarios son iguales si tienen el mismo nombre
	}
	
}
