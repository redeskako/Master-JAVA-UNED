//CAPA DE NEGOCIO DE USUARIOS
//DEFINE UN CONJUNTO DE TIPO TREESET DE USUARIOS
//Los usuarios solo se consultan (no se a�aden ni eliminan)

package org.aprende.java.bbdd;
import java.util.*;

public class Usuarios extends TreeSet {
	
	private BBDD miBd;
	
	public Usuarios(){
		super();
		miBd = new BBDD();
	}

	//Metodo que devuelve una lista de usuarios
	//Si nos llegan errores de los m�todos abrirConexion y cerrarConexion se lanzan a la clase DisconformidadException
	public Usuarios AllUsuarios() throws DisconformidadException{
		miBd.abrirConexion(); //No hacemos tratamiento de errores porque ya se ha hecho en el m�todo abrirConexi�n.		
		this.addAll( miBd.listadoUsuarios("Select * from Usuarios"));
		miBd.cerrarConexion();//No hacemos tratamiento de errores porque ya se ha hecho en el m�todo abrirConexi�n.
		return this;		
	}
	
	public Usuario getUsuario (int id) throws DisconformidadException{
		Usuario u =new Usuario();
		Iterator it=this.iterator();
		
		while(it.hasNext() && u.Id()!=id )  {
				u=(Usuario) it.next();
		}
		if (u.Id()!=id ){
			u.Id(0);
			u.nombre("");
			u.clave("");
		}
		return u;
	}
	
	public int getIdUsuario (String nombre) throws DisconformidadException{
		Usuario u =new Usuario();
		Iterator it=this.iterator();
		
		while(it.hasNext() && u.nombre()!=nombre )  {
				u=(Usuario) it.next();
		}
		if (u.nombre()!=nombre ){
			u.Id(0);
			u.nombre("");
			u.clave("");
		}
		return u.Id();
	}
	
	
	public String toString(Object o){		
		String cadena = new String();
		Iterator it= this.iterator();		
		while (it.hasNext()){
			cadena = cadena  + ((Usuario)it.next()).toString() + "  -  ";			
		}
		return cadena;
	}
}
