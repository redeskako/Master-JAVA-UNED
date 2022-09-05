package org.basedatos;

import java.util.*;

import org.basedatos.BBDD;
//Gestión del listado de los recursos
public class ListadoRecursos extends TreeSet<Recurso>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Declaración de la base de datos
	private BBDD miBd;

	public ListadoRecursos(){

		super();
		miBd = new BBDD();
	}
	//Selecciona los recursos del listado de la base de datos
	public ListadoRecursos listadoRecursos() throws ErrorException {

		miBd.abrirConexion();
		this.addAll(miBd.listadoRecursos("select * from recursos"));
		miBd.cerrarConexion();
		return this;
	}
	//añade recursos a la base de datos 
	public ListadoRecursos addRecursos(Recurso r) throws ErrorException{

		miBd.abrirConexion();
		miBd.ejecutarRecurso("insert into recursos (IdRecurso, Descripcion)" + r.getIdRecurso() + "'," + r.getDescripcion() + "'");
		miBd.cerrarConexion();
		this.add(r);
		return this;
	}
	//borra recursos de la base de datos
	public ListadoRecursos deleteRecursos (Recurso r) throws ErrorException{

		miBd.abrirConexion();
		miBd.ejecutarRecurso("delete from recursos where IdRecurso =" +r.getIdRecurso());
		miBd.cerrarConexion();
		this.remove(r);
		return this;
		}
	
	/*public ListadoRecursos editarRecursos (Recurso r) throws ErrorException{

		miBd.abrirConexion();
		miBd.ejecutarRecurso("update recursos set values ("+ r.getIdRecurso() + r.getDescripcion()");
		miBd.cerrarConexion();
		this.remove(r);
		return this;
		}
	*/
	
	//Ejecuta las acciones sobre la base de datos
public Object ejecutarRecurso() throws ErrorException{
		
		BBDD miBdd = new BBDD();
		miBdd.abrirConexion();
		Recurso addRecursos;
		addRecursos= miBdd.ejecutarRecurso ("insert into recursos (IdRecurso, Descripcion)");
		miBdd.cerrarConexion();
		return addRecursos;
	}
}
