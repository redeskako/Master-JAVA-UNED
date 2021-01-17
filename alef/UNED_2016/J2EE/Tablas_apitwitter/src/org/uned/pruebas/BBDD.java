package org.uned.pruebas;
import java.sql.*;
import java.util.*;


public class  BBDD{
	//Atributos
	private Connection conn;
	private Statement stm;
	private boolean conectado;
	
	//Constructor por defecto
	public BBDD(){		
		inicializa();
	}
	//Métodos
	private void inicializa(){
		this.conn = null;
		this.conectado = false;
	}
	public boolean abrirConexion() throws SQLException{
		this.conectado=false;
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.conn = DriverManager.getConnection("jdbc:mysql://localhost/apitwitter?user=uned&password=uned");
            this.stm = conn.createStatement();
            this.conectado=true;
        return conectado;
	}
	
	public void cerrarConexion() throws SQLException{
			if (this.stm != null){
				this.stm.close();
			}
			if (this.conn != null){
				this.conn.close();
			}
			inicializa();
	}

	public ArrayList <Tweets_index> listadoTweetsIndex(String sql) throws SQLException{
		ArrayList <Tweets_index> lista = new ArrayList<Tweets_index>();
		ResultSet rs;
			rs = stm.executeQuery(sql);
			while (rs.next()){
				lista.add(new Tweets_index(rs.getString("tweet_id"), rs.getString("text"),
											 rs.getString("user_id"), rs.getString("user_name"),
											 rs.getInt("followers_count"), rs.getInt("friends_count"),
											 rs.getString("profile_image_url_https")));
			}
			rs.close();
			return lista;
	}
	
	public ArrayList <Poll_index> listadoPollIndex(String sql) throws SQLException{
		ArrayList <Poll_index> lista = new ArrayList<Poll_index>();
		ResultSet rs;
			rs = stm.executeQuery(sql);
			while (rs.next()){
				lista.add(new Poll_index(rs.getDate("fecha_hora"), rs.getString("max_id")));
			}
			rs.close();
			return lista;
	}
	
	public ArrayList <Hashtags_adm_index> listadoHashtagsAdmIndex(String sql) throws SQLException{
		ArrayList <Hashtags_adm_index> lista = new ArrayList<Hashtags_adm_index>();
		ResultSet rs;
			rs = stm.executeQuery(sql);
			while (rs.next()){
				lista.add(new Hashtags_adm_index(rs.getString("hashtag_id"), rs.getString("origen"), rs.getDate("fecha_entrada")));
			}
			rs.close();
			return lista;
	}
	
	public ArrayList <Hashtags_tweet_index> listadoHashtagsTweetIndex(String sql) throws SQLException{
		ArrayList <Hashtags_tweet_index> lista = new ArrayList<Hashtags_tweet_index>();
		ResultSet rs;
			rs = stm.executeQuery(sql);
			while (rs.next()){
				lista.add(new Hashtags_tweet_index(rs.getInt("ID"), rs.getString("tweet_id"), rs.getString("hashtag_id")));
			}
			rs.close();
			return lista;
	}
	
	public ArrayList <Hashtag_neighbors_index> listadoHashtagNeighborsIndex(String sql) throws SQLException{
		ArrayList <Hashtag_neighbors_index> lista = new ArrayList<Hashtag_neighbors_index>();
		ResultSet rs;
			rs = stm.executeQuery(sql);
			while (rs.next()){
				lista.add(new Hashtag_neighbors_index(rs.getInt("ID"), rs.getString("hashtag_id"), rs.getString("neighbor")));
			}
			rs.close();
			return lista;
	}
	
	public ArrayList <Urls_tweet_index> listadoUrlsTweetIndex(String sql) throws SQLException{
		ArrayList <Urls_tweet_index> lista = new ArrayList<Urls_tweet_index>();
		ResultSet rs;
			rs = stm.executeQuery(sql);
			while (rs.next()){
				lista.add(new Urls_tweet_index(rs.getInt("ID"), rs.getString("tweet_id"), rs.getString("url")));
			}
			rs.close();
			return lista;
	}
}
