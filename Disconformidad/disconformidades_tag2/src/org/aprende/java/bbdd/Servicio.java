package org.aprende.java.bbdd;


import java.util.*;

public class Servicio implements Comparable{

	private int id ;
	private String nombre;
	
	
	//constructor
	public Servicio(int id, String nombre){
		this.id=id;
		this.nombre=nombre;
		
	}

	public Servicio(){
		this.id=0;
		this.nombre="";
		
		
		
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
	public void nombre(String ser){
		this.nombre= ser;
	}
	
	//metodos
	public String toString(){
		//return " (Servicio "+this.id + ":" + this.nombre+") ";
		return  this.nombre;
	}	
	
	
	
	public int compareTo(Object o){
		Servicio s = (Servicio) o;
		return this.nombre.compareToIgnoreCase(s.nombre); //Ordenar� por nombre
	}
	public boolean equals(Object o){
		Servicio s = (Servicio) o;
		return this.compareTo(s)==0; //Dos usuarios son iguales si tienen el mismo nombre
	}
	
	public Servicio getServicio(int idservi){		
		BBDD miBd = new BBDD();
		miBd.abrirConexion(); //No hacemos tratamiento de errores porque ya se ha hecho en el m�todo abrirConexi�n.		
		Servicio servi=miBd.getServicio("Select * from Servicios where id=" + idservi);
		miBd.cerrarConexion();//No hacemos tratamiento de errores porque ya se ha hecho en el m�todo abrirConexi�n.
		return servi;	
	
			
	}
	
	
}
