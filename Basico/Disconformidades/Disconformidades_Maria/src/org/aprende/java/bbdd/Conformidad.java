package org.aprende.java.bbdd;
import java.util.*;

public class Conformidad implements Comparable{

	private int numero ;
	private Date fecha;
	private String doc;
	private int servicio;
	private int usuario;
	private boolean confirmacion;
	
	//constructores
	public Conformidad(int numero, int servicio, int usuario, boolean confirmacion){
		this.numero=numero;
		this.fecha= new Date();
		this.doc="";
		this.servicio=servicio;
		this.usuario=usuario;
		this.confirmacion=false;
	}
	public Conformidad(int numero, Date fecha, String doc, int servicio, int usuario, boolean confirmacion){
		this.numero=numero;
		this.fecha=fecha;
		this.doc=doc;
		this.servicio=servicio;
		this.usuario=usuario;
		this.confirmacion=confirmacion;
	}

	//get y set de las variables de clase
	public boolean confirmacion() {
		return confirmacion;
	}
	public void confirmacion(boolean confirmacion) {
		this.confirmacion = confirmacion;
	}
	public String doc() {
		return doc;
	}
	public void doc(String doc) {
		this.doc = doc;
	}
	public Date fecha() {
		return fecha;
	}
	public void fecha(Date fecha) {
		this.fecha = fecha;
	}
	public int numero() {
		return numero;
	}
	public void numero(int numero) {
		this.numero = numero;
	}
	public int servicio() {
		return servicio;
	}
	public void servicio(int servicio) {
		this.servicio = servicio;
	}
	public int usuario() {
		return usuario;
	}
	public void usuario(int usuario) {
		this.usuario = usuario;
	}
	
	
	//metodos
	public String toString(){
		return " (Numero: " + this.numero + " - Fecha: " + this.fecha + " - Documentos afectados: " + this.doc + " - Servicio: " + this.servicio + " - usuario: " + this.usuario + " - Confirmacion: " + this.confirmacion + ")";
	}
	public boolean equals(Object o){
		Conformidad d= (Conformidad) o;
		//no comparamos el atributo numero porque al ser autonumerico no pueden haber dos disconformidades con el mismo numero
		return ( (this.fecha().equals(d.fecha()) && (this.doc().equals(d.doc())) && (this.servicio()== d.servicio()) && (this.usuario()==d.usuario())));
	}	
	public int compareTo(Object o){
		Conformidad d= (Conformidad) o;
		return this.fecha().compareTo(d.fecha());				
	}
	
}
