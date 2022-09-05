package org.aprende.java.bbdd;

import java.util.*;


public class Servicio implements Comparable {
	
	private int id;
	private String servicio;
	
	public Servicio (int id, String servicio){
		this.id=id;
		this.servicio=servicio;	
	}
	
	
	public void id (int id){
		this.id=id;
	}	
	public int id (){
		return this.id;
	}
	public void servicio (String servicio){
		this.servicio=servicio;
	}
	public String servicio (){
		 return this.servicio;
	}
	
	
	public String toString(){
		return "(Servicio "+this.id+":"+this.servicio+")"; 
	}
	
	public boolean equals(Object o){
		Servicio p1= (Servicio) o;
		return (this.id==p1.id && this.servicio==p1.servicio);
	}
	public int compareTo(Object o ){
		Servicio p1= (Servicio) o;
		return this.servicio.compareTo(p1.servicio());
	}
	
}
