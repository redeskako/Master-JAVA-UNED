//CAPA DE NEGOCIO DE USUARIOS
//DEFINE UN CONJUNTO DE TIPO TREESET DE USUARIOS
//Los usuarios solo se consultan (no se a�aden ni eliminan)

package org.aprende.java.bbdd;
import java.util.*;

public class Servicios extends TreeSet {
	
	private BBDD miBd;
	
	public Servicios(){
		super();
		miBd = new BBDD();
	}

	//Metodo que devuelve una lista de servicios
	//Si nos llegan errores de los m�todos abrirConexion y cerrarConexion se lanzan a la clase DisconformidadException
	public Servicios AllServicios() throws DisconformidadException{
		miBd.abrirConexion(); //No hacemos tratamiento de errores porque ya se ha hecho en el m�todo abrirConexi�n.		
		this.addAll( miBd.listadoServicios("Select * from Servicios"));
		miBd.cerrarConexion();//No hacemos tratamiento de errores porque ya se ha hecho en el m�todo abrirConexi�n.
		return this;		
	}
	public String toString(Object o){		
		String cadena = new String();
		Iterator it= this.iterator();		
		while (it.hasNext()){
			cadena = cadena  + ((Servicio)it.next()).toString() + "  -  ";			
		}
		return cadena;
	}
}
