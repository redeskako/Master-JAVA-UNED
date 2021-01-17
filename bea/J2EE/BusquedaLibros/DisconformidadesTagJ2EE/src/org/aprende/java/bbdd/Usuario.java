//CLASE USUARIO
//Define cada usuario de la base de datos

package org.aprende.java.bbdd;
import java.util.*;

public class Usuario implements Comparable{

	private int id ;
	private String nombre;
	private String clave;
	private int gestion; //1:administrador

	//constructor
	public Usuario(int id, String nombre, String clave,int gestion){
		this.id=id;
		this.nombre=nombre;
		this.clave=clave;
		this.gestion=gestion;
	}
	public Usuario(){
		this.id=0;
		this.nombre="";
		this.clave="";
		this.gestion=0;
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

	public int getGestion() {
		return gestion;
	}

	public void setGestion(int gestion) {
		this.gestion = gestion;
	}

	public Usuario getUsuario(int idusuario){
		BBDD miBd = new BBDD();
		miBd.abrirConexion(); //No hacemos tratamiento de errores porque ya se ha hecho en el m�todo abrirConexi�n.
		Usuario usu=miBd.getUsuario("Select * from Usuarios where id=" + idusuario);
		miBd.cerrarConexion();//No hacemos tratamiento de errores porque ya se ha hecho en el m�todo abrirConexi�n.
		return usu;


	}


	public Usuario validarUsuario(String usuario,String pwd){
		BBDD miBd = new BBDD();
		miBd.abrirConexion(); //No hacemos tratamiento de errores porque ya se ha hecho en el m�todo abrirConexi�n.
		Usuario usu=miBd.getUsuario("Select * from Usuarios where usuario='" + usuario +"' and clave=password('" + pwd + "')");
		//Usuario usu=miBd.getUsuario("Select * from Usuarios where usuario='" + usuario +"'");
		miBd.cerrarConexion();//No hacemos tratamiento de errores porque ya se ha hecho en el m�todo abrirConexi�n.
		return usu;
	}
}
