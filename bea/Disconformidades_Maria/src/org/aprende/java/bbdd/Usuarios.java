//CAPA DE NEGOCIO DE USUARIOS
//DEFINE UN CONJUNTO DE TIPO TREESET DE USUARIOS
//Los usuarios solo se consultan (no se a�aden ni eliminan)

package org.aprende.java.bbdd;
import java.util.*;

public class Usuarios extends TreeSet {
	
	private Bbdd miBd;
	
	public Usuarios(){
		super();
		miBd = new Bbdd();
	}

	//Metodo que devuelve una lista de usuarios
	//Si nos llegan errores de los m�todos abrirConexion y cerrarConexion se lanzan a la clase DisconformidadException
	public Usuarios allUsuarios() throws DisconformidadException{
		/*
		 * lo comento porque no tengo conexion a bd
		miBd.abrirConexion(); //No hacemos tratamiento de errores porque ya se ha hecho en el m�todo abrirConexi�n.		
		this.addAll( miBd.listadoUsuarios("Select * from usuarios"));
		miBd.cerrarConexion();//No hacemos tratamiento de errores porque ya se ha hecho en el m�todo abrirConexi�n.
		return this;
		*/
		
		this.add(new Usuario (1,"Maria","Maria"));
		this.add(new Usuario (2,"Juan","Juan"));
		this.add(new Usuario (3,"Pepe","Pepe"));
		return this;		
	}
	public String toString(){		
		String cadena = new String();
		Iterator it= this.iterator();		
		while (it.hasNext()){
			cadena = cadena  + ((Usuario)it.next()).toString() + "  -  ";			
		}
		return cadena;
	}
	
	
}
