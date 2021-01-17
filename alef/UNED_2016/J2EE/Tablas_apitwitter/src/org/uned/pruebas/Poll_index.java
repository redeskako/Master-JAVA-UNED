package org.uned.pruebas;

import java.util.*;
import java.sql.SQLException;

public class Poll_index{
	//Atributos
	private Date fecha_hora ;
	private String max_id;
	
	//Constructor por defecto
	public Poll_index(){
		this.fecha_hora=null;
		this.max_id=null;
	}
	//Constructor con los campos leidos de la tabla de la BBDD
	public Poll_index(Date fecha_hora,String max_id){
		this.fecha_hora=fecha_hora;
		this.max_id=max_id;	
	}
	//Lectura de los campos de los atributos de la clase
	public Date getFecha_hora() {
		return this.fecha_hora;
	}
	public String getMax_id() {
		return this.max_id;
	}
	//Métodos
	public ArrayList<Poll_index> listadoPollIndex() throws SQLException{
		ArrayList<Poll_index> miListaPollIndex;
		BBDD miBd = new BBDD();
		miBd.abrirConexion(); 
		miListaPollIndex=miBd.listadoPollIndex("Select * from poll_index");
		miBd.cerrarConexion();
		return miListaPollIndex;		
	}
}

