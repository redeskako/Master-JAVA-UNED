//CAPA DE NEGOCIO DE SERVICIOS
//DEFINE UN CONJUNTO DE TIPO TREESET DE SERVICIOS
//Los servicios solo se consultan (no se añaden ni eliminan)

package org.aprende.java.bbdd;
import java.util.*;

public class Servicios extends TreeSet {
	
	private Bbdd miBd;
	
	public Servicios(){
		super();
		miBd = new Bbdd();
	}

	//Metodo que devuelve una lista de usuarios
	//Si nos llegan errores de los mï¿½todos abrirConexion y cerrarConexion se lanzan a la clase DisconformidadException
	public Servicios allServicios() throws DisconformidadException{
		/*
		 * lo comento porque no tengo conexion a bd
		miBd.abrirConexion(); //No hacemos tratamiento de errores porque ya se ha hecho en el mï¿½todo abrirConexiï¿½n.		
		this.addAll( miBd.listadoServicios("Select * from servicios"));
		miBd.cerrarConexion();//No hacemos tratamiento de errores porque ya se ha hecho en el mï¿½todo abrirConexiï¿½n.
		return this;
		*/
		
		this.add(new Servicio (1,"Servicio 1"));
		this.add(new Servicio (2,"Servicio 2"));
		this.add(new Servicio (3,"Servicio 3"));
		return this;		
	}
	public String toString(){		
		String cadena = new String();
		Iterator it= this.iterator();		
		while (it.hasNext()){
			cadena = cadena  + ((Servicio)it.next()).toString() + "  -  ";			
		}
		return cadena;
	}
	
	
}

