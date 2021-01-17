package org.uned.pruebas;

import java.util.*;
import java.sql.SQLException;

public class Urls_tweet_index{
	//Atributos
	private int ID ;
	private String tweet_id;
	private String url;
	
	//Constructor por defecto
	public Urls_tweet_index(){
		this.ID=0;
		this.tweet_id=null;
		this.url=null;
	}
	//Constructor con los campos leidos de la tabla de la BBDD
	public Urls_tweet_index(int ID,String tweet_id,String url){
		this.ID=ID;
		this.tweet_id=tweet_id;
		this.url=url;
	}
	//Lectura de los campos de los atributos de la clase
	public int getID() {
		return this.ID;
	}
	public String getTweet_id() {
		return this.tweet_id;
	}
	public String getUrl() {
		return this.url;
	}
	//Métodos
	public ArrayList<Urls_tweet_index> listadoUrlsTweetIndex() throws SQLException{
		ArrayList<Urls_tweet_index> miListaUrlsTweetIndex;
		BBDD miBd = new BBDD();
		miBd.abrirConexion(); 
		miListaUrlsTweetIndex=miBd.listadoUrlsTweetIndex("Select * from urls_tweet_index");
		miBd.cerrarConexion();
		return miListaUrlsTweetIndex;		
	}
}


