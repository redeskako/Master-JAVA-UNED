package org.uned.pruebas;
import java.util.*;
import java.sql.SQLException;

public class Tweets_index{
	//Atributos
	private String tweet_id ;
	private String text;
	private String user_id;
	private String user_name;
	private int followers_count;
	private int friends_count;
	private String profile_image_url_https;
	
	//Constructor por defecto
	public Tweets_index(){
		this.tweet_id=null;
		this.text=null;
		this.user_id=null;
		this.user_name=null;
		this.followers_count=0;
		this.friends_count=0;
		this.profile_image_url_https=null;
	}
	//Constructor con los campos leidos de la tabla de la BBDD
	public Tweets_index(String tweet_id,String text,String user_id,String user_name,
						int followers_count,int friends_count,String profile_image_url_https){
		this.tweet_id=tweet_id;
		this.text=text;
		this.user_id=user_id;
		this.user_name=user_name;
		this.followers_count=followers_count;
		this.friends_count=friends_count;
		this.profile_image_url_https=profile_image_url_https;
	}
	//Lectura de los campos de los atributos de la clase
	public String getTweet_id() {
		return this.tweet_id;
	}
	public String getText() {
		return this.text;
	}
	public String getUser_id() {
		return this.user_id;
	}
	public String getUser_name() {
		return this.user_name;
	}
	public int getFollowers_count() {
		return this.followers_count;
	}
	public int getFriends_count() {
		return this.friends_count;
	}
	public String getProfile_image_url_https() {
		return this.profile_image_url_https;
	}
	//Métodos
	public ArrayList<Tweets_index> listadoTweetsIndex() throws SQLException{
		ArrayList<Tweets_index> miListaTweetsIndex;
		BBDD miBd = new BBDD();
		miBd.abrirConexion(); 
		miListaTweetsIndex=miBd.listadoTweetsIndex("Select * from tweets_index");
		miBd.cerrarConexion();
		return miListaTweetsIndex;		
	}
}

