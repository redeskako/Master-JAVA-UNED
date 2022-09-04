 //CAPA DE NEGOCIO DE USUARIOS
//DEFINE UN CONJUNTO DE TIPO TREESET DE USUARIOS
//Los usuarios solo se consultan (no se a�aden ni eliminan)

package org.aprende.java.bbdd;
import java.util.*;

public class Disconformidades extends TreeSet{
	
	private BBDD miBd;
	
	public Disconformidades(){
		super();
		miBd = new BBDD();
	}

	//Metodo que devuelve una lista de usuarios
	//Si nos llegan errores de los m�todos abrirConexion y cerrarConexion se lanzan a la clase DisconformidadException
	public Disconformidades listadoDisconformidades() throws DisconformidadException{
		miBd.abrirConexion(); //No hacemos tratamiento de errores porque ya se ha hecho en el m�todo abrirConexi�n.		
		this.addAll( miBd.listadoDisconformidades("Select * from disconformidad"));
		miBd.cerrarConexion();//No hacemos tratamiento de errores porque ya se ha hecho en el m�todo abrirConexi�n.
		return this;		
	}
	public Disconformidades addDisconformidades(Disconformidad d) throws DisconformidadException{
		miBd.abrirConexion(); //No hacemos tratamiento de errores porque ya se ha hecho en el m�todo abrirConexi�n.		
		miBd.ejecutarDisconformidad("insert into disconformidad(numero, fecha,docs, servicio,idusuario,devolucion,motivo,comentario) " +     "values(" + d.numero() + "," + d.fecha() + "," + d.docs() + "," + d.servicio() + "," +d.usuario() + "," + d.devolucion() +  d.motivo() +d.comentario() +")");
		miBd.cerrarConexion();//No hacemos tratamiento de errores porque ya se ha hecho en el m�todo abrirConexi�n.
		this.add(d); //A�adimos a la lista de disconformidades la disconformidad nueva
		return this;		
	}
	public Disconformidades deleteDisconformidades(Disconformidad d) throws DisconformidadException{
		miBd.abrirConexion(); //No hacemos tratamiento de errores porque ya se ha hecho en el m�todo abrirConexi�n.		
		miBd.ejecutarDisconformidad("delete * from disconformidad where numero = " + d.numero());
		miBd.cerrarConexion();//No hacemos tratamiento de errores porque ya se ha hecho en el m�todo abrirConexi�n.
		this.remove(d);
		return this;		
	}
	
}
