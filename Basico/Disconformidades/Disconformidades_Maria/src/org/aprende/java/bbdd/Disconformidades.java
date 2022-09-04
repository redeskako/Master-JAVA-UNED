//CAPA DE NEGOCIO DE USUARIOS
//DEFINE UN CONJUNTO DE TIPO TREESET DE USUARIOS
//Los usuarios solo se consultan (no se a�aden ni eliminan)

package org.aprende.java.bbdd;
import java.util.*;

public class Disconformidades extends TreeSet{
	
	private Bbdd miBd;
	
	public Disconformidades(){
		super();
		miBd = new Bbdd();
	}

	//Metodo que devuelve una lista de disconformidades
	//Si nos llegan errores de los metodos abrirConexion y cerrarConexion se lanzan a la clase DisconformidadException
	public Disconformidades listadoDisconformidades() throws DisconformidadException{
		
		
		miBd.abrirConexion(); //No hacemos tratamiento de errores porque ya se ha hecho en el m�todo abrirConexi�n.		
		this.addAll( miBd.listadoDisconformidades("Select * from Disconformidad"));
		miBd.cerrarConexion();//No hacemos tratamiento de errores porque ya se ha hecho en el m�todo abrirConexi�n.
		return this;
		
		
		/*/esta linea es para probar datos
		this.add(new Disconformidad(1,"30-4-07","doc",1,1,true,"esta mal hecho","esto es un lio"));
		this.add(new Disconformidad(2,"20-4-07","doc2",1,2,false ,"esta bien hecho","esto es un lio2"));
		this.add(new Disconformidad(3,"10-4-07","doc2",2,2,false ,"es el tercer elemento","esto es un lio3"));
		
		return this;
		*/
		
	}
	public Disconformidades addDisconformidades(Disconformidad d) throws DisconformidadException{
		/*
		miBd.abrirConexion(); //No hacemos tratamiento de errores porque ya se ha hecho en el m�todo abrirConexi�n.		
		miBd.ejecutarDisconformidad("insert into disconformidad(numero, fecha,docs, servicio,idusuario,devolucion,motivo,comentario) " +     "values(" + d.numero() + "," + d.fecha() + "," + d.docs() + "," + d.servicio() + "," +d.usuario() + "," + d.devolucion() +  d.motivo() +d.comentario() +")");
		miBd.cerrarConexion();//No hacemos tratamiento de errores porque ya se ha hecho en el metodo abrirConexion.
		this.add(d); //A�adimos a la lista de disconformidades la disconformidad nueva
		*/
		this.add(d);
		return this;		
	}
	
	public Disconformidades updateDisconformidades(Disconformidad dAnterior,Disconformidad d) throws DisconformidadException{
		/*
		miBd.abrirConexion(); //No hacemos tratamiento de errores porque ya se ha hecho en el m�todo abrirConexi�n.		
		miBd.ejecutarDisconformidad("update Disconformidad set ..... where numero = " + d.numero());
		miBd.cerrarConexion();//No hacemos tratamiento de errores porque ya se ha hecho en el m�todo abrirConexi�n.
		this.(d);
		*/
		this.remove(dAnterior);
		this.add(d);
		
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
