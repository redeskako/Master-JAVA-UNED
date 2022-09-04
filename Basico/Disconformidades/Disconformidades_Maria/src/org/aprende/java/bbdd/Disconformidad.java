package org.aprende.java.bbdd;
import java.util.*;

public class Disconformidad implements Comparable{

	private int numero ;
	private String fecha;
	private String motivo;
	private String docs;
	private int servicio;
	private int usuario;
	private boolean devolucion;
	private String comentario;
	
	//Constructores
	public Disconformidad( String fecha, String doc, int servicio, int usuario, boolean devolucion,String motivo,String comentario){
		this.fecha=fecha;
		this.docs=doc;
		this.servicio=servicio;
		this.usuario=usuario;
		this.devolucion=devolucion;
		this.motivo=motivo;
		this.comentario=comentario;
	}
	
	public Disconformidad(int numero, String fecha, String doc, int servicio, int usuario, boolean devolucion,String motivo,String comentario){
		this.numero=numero;
		this.fecha=fecha;
		this.docs=doc;
		this.servicio=servicio;
		this.usuario=usuario;
		this.devolucion=devolucion;
		this.motivo=motivo;
		this.comentario=comentario;
	}

	//get y set de las variables de clase
	public boolean devolucion() {
		return devolucion;
	}
	public void devolucion(boolean confirmacion) {
		this.devolucion = confirmacion;
	}
	public String docs() {
		return docs;
	}
	public void docs(String doc) {
		this.docs = doc;
	}
	public String fecha() {
		return fecha;
	}
	public void fecha(String fecha) {
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
	public String motivo() {
		return motivo;
	}
	public void motivo(String motivo) {
		this.motivo = motivo;
	}
	public String comentario() {
		return comentario;
	}
	public void comentario(String coment) {
		this.comentario = coment;
	}
	
	//metodos
	public String toString(){
		return " (Numero: " + this.numero + " - Fecha: " + this.fecha + " - Documentos afectados: " + this.docs + " - Servicio: " + this.servicio + " - usuario: " + this.usuario + " - Devolucion: " + this.devolucion + " - Motivo: " + this.motivo + " - Comentario: " + this.comentario +")";
	}
	public int compareTo(Object o){
		Disconformidad d= (Disconformidad) o;
		return this.fecha().compareTo(d.fecha()); //Ordenar por fecha		
	}
	public boolean equals(Object o){
		Disconformidad d= (Disconformidad) o;
		//Dos disconformidades son iguales si son iguales todos sus propiedades, excepto el numero por ser clave autonumerica
		//no comparamos el atributo numero porque al ser autonumerico no pueden haber dos disconformidades con el mismo numero
		return ( (this.fecha().equals(d.fecha()) && (this.docs().equals(d.docs())) && (this.servicio()== d.servicio()) && (this.usuario()==d.usuario())));
	}

	
}
