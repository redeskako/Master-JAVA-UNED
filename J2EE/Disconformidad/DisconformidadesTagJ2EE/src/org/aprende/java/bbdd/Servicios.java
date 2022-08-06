//CAPA DE NEGOCIO DE USUARIOS
//DEFINE UN CONJUNTO DE TIPO VECTOR DE USUARIOS
//Los usuarios solo se consultan (no se a�aden ni eliminan)

package org.aprende.java.bbdd;
import java.util.*;

public class Servicios extends Vector<Servicio> {

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

	public Servicio getServicio (int id) throws DisconformidadException{
		Servicio s =new Servicio();
		Iterator it=this.iterator();

		while(it.hasNext() && s.Id()!=id )  {
				s=(Servicio) it.next();
		}
		if (s.Id()!=id ){
			s.Id(0);
			s.nombre("");
		}
		return s;
	}

	public int getIdServicio (String nombre) throws DisconformidadException{
		Servicio s =new Servicio();
		Iterator it=this.iterator();

		while(it.hasNext() && s.nombre()!=nombre )  {
				s=(Servicio) it.next();
		}
		if (s.nombre()!=nombre ){
			s.Id(0);
			s.nombre("");
		}
		return s.Id();
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
