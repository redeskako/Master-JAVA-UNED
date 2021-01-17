package org.uned.pruebas;

import java.util.*;
import java.sql.SQLException;

public class Hashtag_neighbors_index{
	//Atributos
	private int ID ;
	private String hashtag_id;
	private String neighbor;
	
	//Constructor por defecto
	public Hashtag_neighbors_index(){
		this.ID=0;
		this.hashtag_id=null;
		this.neighbor=null;
	}
	//Constructor con los campos leidos de la tabla de la BBDD
	public Hashtag_neighbors_index(int ID,String hashtag_id,String neighbor){
		this.ID=ID;
		this.hashtag_id=hashtag_id;
		this.neighbor=neighbor;
	}
	//Lectura de los campos de los atributos de la clase
	public int getID() {
		return this.ID;
	}
	public String getHashtag_id() {
		return this.hashtag_id;
	}
	public String getNeighbor() {
		return this.neighbor;
	}
	//Métodos
	public ArrayList<Hashtag_neighbors_index> listadoHashtagNeighborsIndex() throws SQLException{
		ArrayList<Hashtag_neighbors_index> miListaHashtagNeighborsIndex;
		BBDD miBd = new BBDD();
		miBd.abrirConexion(); 
		miListaHashtagNeighborsIndex=miBd.listadoHashtagNeighborsIndex("Select * from hashtag_neighbors_index");
		miBd.cerrarConexion();
		return miListaHashtagNeighborsIndex;		
	}
}

