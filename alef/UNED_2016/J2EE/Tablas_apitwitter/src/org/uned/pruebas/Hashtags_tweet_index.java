package org.uned.pruebas;

import java.util.*;
import java.sql.SQLException;

public class Hashtags_tweet_index{
	//Atributos
	private int ID ;
	private String tweet_id;
	private String hashtag_id;
	
	//Constructor por defecto
	public Hashtags_tweet_index(){
		this.ID=0;
		this.tweet_id=null;
		this.hashtag_id=null;
	}
	//Constructor con los campos leidos de la tabla de la BBDD
	public Hashtags_tweet_index(int ID,String tweet_id,String hashtag_id){
		this.ID=ID;
		this.tweet_id=tweet_id;
		this.hashtag_id=hashtag_id;
	}
	//Lectura de los campos de los atributos de la clase
	public int getID() {
		return this.ID;
	}
	public String getTweet_id() {
		return this.tweet_id;
	}
	public String getHashtag_id() {
		return this.hashtag_id;
	}
	//Métodos
	public ArrayList<Hashtags_tweet_index> listadoHashtagsTweetIndex() throws SQLException{
		ArrayList<Hashtags_tweet_index> miListaHashtagsTweetIndex;
		BBDD miBd = new BBDD();
		miBd.abrirConexion(); 
		miListaHashtagsTweetIndex=miBd.listadoHashtagsTweetIndex("Select * from hashtags_tweet_index");
		miBd.cerrarConexion();
		return miListaHashtagsTweetIndex;		
	}
}

