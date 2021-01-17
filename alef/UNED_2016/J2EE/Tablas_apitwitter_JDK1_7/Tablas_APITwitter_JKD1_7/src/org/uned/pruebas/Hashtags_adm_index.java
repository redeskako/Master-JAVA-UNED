package org.uned.pruebas;

import java.util.*;
import java.sql.SQLException;

public class Hashtags_adm_index{
	//Atributos
	private String hashtag_id;
	private String origen;
	private Date fecha_entrada;
	
	//Constructor por defecto
	public Hashtags_adm_index(){
		this.hashtag_id=null;
		this.origen=null;
		this.fecha_entrada=null;
	}
	//Constructor con los campos leidos de la tabla de la BBDD
	public Hashtags_adm_index(String hashtag_id,String origen,Date fecha_entrada){
		this.hashtag_id=hashtag_id;
		this.origen=origen;
		this.fecha_entrada=fecha_entrada;
	}
	//Lectura de los campos de los atributos de la clase
	public String getHashtag_id() {
		return this.hashtag_id;
	}
	public String getOrigen() {
		return this.origen;
	}
	public Date getFecha_entrada() {
		return this.fecha_entrada;
	}
	//Métodos
	public ArrayList<Hashtags_adm_index> listadoHashtagsAdmIndex() throws SQLException{
		ArrayList<Hashtags_adm_index> miListaHashtagsAdmIndex;
		BBDD miBd = new BBDD();
		miBd.abrirConexion(); 
		miListaHashtagsAdmIndex=miBd.listadoHashtagsAdmIndex("Select * from hashtags_adm_index");
		miBd.cerrarConexion();
		return miListaHashtagsAdmIndex;		
	}
}

