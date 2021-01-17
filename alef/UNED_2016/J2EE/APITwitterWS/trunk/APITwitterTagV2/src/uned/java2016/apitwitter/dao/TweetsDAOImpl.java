package uned.java2016.apitwitter.dao;

import java.util.ArrayList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

public class TweetsDAOImpl implements TweetsDAO {
	
	private Connection conn;
	private Statement stm;
	private Statement stm2;
	private Statement stmContar;
	private String errorenPrimero;
	private String quedanMas;
	
	/** Constructor por defecto, inicializa un objeto de tipo TweetsDAOImpl totalmente vacío
	  *	Recoge la conexión hecha por el controlador 
	  * @ in Connection conn con la conexión del controlador
	  * @ out no procede
	  * @ error   
	  */
	public 	TweetsDAOImpl(Connection conn){
		this.conn=conn;
	}
	
	public void TweetDAOImpl(Connection conn){
		
		this.conn = conn;
		
	}
	/** Métodos de acceso I/O a los campos de la clase */
	/** El método getErrorenPrimero lee el campo errorenPrimero
	 * @ in 
	 * @ out String con errorenPrimero
	 * @ error   
	 */
	public String getErrorenPrimero(){
		return this.errorenPrimero;
	}
	/** El método getQuedanMas lee el campo quedanMas
	 * @ in 
	 * @ out String con quedanMas
	 * @ error   
	 */
	public String getQuedanMas(){
		return this.quedanMas;
	}
	/** El método leerTweetsconHashtagDeterminado devuelve los objetos de tipo tweet que 
	 *  contengan un deteminado hashtag en su interior mediante una colección de los mismos
	 * @ in 
	 * 		String hashtag
	 * 		int primero
	 * 		int cuantos
	 * @ out ArrayList<Tweet> con la lista de objetos tweet que tienen el hashtag adecuado
	 * @ error SQLException e
	 */
	@Override
	public ArrayList<Tweet> leerTweetsconHashtagDeterminado(String hashtag,int primero,int cuantos){
		//Buscamos que tweets contienen el hashtag que ha elegido el usuario y los representamos con su
		// tweet_id
		this.errorenPrimero="No";
		this.quedanMas="No";
		int totalRegistros=0;
		try {
			this.stmContar = conn.createStatement();
			ResultSet rsContar;
			String sqlContar="Select * from hashtags_tweet where hashtag_id='"+hashtag+"'";
			rsContar=stmContar.executeQuery(sqlContar);
			while (rsContar.next()){
				totalRegistros++;
			}
			rsContar.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(primero>totalRegistros){
			primero=1;
			this.errorenPrimero="Si";
		}
		else{
			primero=primero;
		}
		if(totalRegistros>=(primero+cuantos)){
			this.quedanMas="Si";
		}
		System.out.println("TOTAL: "+totalRegistros+"   "+(primero+cuantos));
		System.out.println("CUANTOS: "+cuantos);
		ArrayList<Long> listaIDTweets=new ArrayList<Long>();
		try{
			this.stm = conn.createStatement();
			ResultSet rs;
			String sql="Select * from hashtags_tweet where hashtag_id='"+hashtag+"' ORDER BY tweet_id LIMIT "+cuantos+" OFFSET "+(primero-1);
			rs=stm.executeQuery(sql);
			while (rs.next()){
				listaIDTweets.add(rs.getLong("tweet_id"));
			}
			rs.close();
		}catch (SQLException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Cargamos aquellos tweets cuyo identificador es el que se ha obtenido previamente
		//Cargamos aquellas urls de tweets cuyo identificador es el que se ha obtenido previamente
		ArrayList<Tweet> listaTweets=new ArrayList<Tweet>();
		for(int i=0;i<listaIDTweets.size();++i){
			try{
				String urlTemp=null;
				//Urls
				this.stm2 = conn.createStatement();
				ResultSet rs2;
				String sql2="Select url from urls_tweet where tweet_id="+listaIDTweets.get(i);
				rs2=stm.executeQuery(sql2);
				while (rs2.next()){
					urlTemp=rs2.getString("url");
				}
				rs2.close();

				//Tweets
				this.stm = conn.createStatement();
				ResultSet rs;
				String sql="Select * from tweets where tweet_id="+listaIDTweets.get(i);
				rs=stm.executeQuery(sql);
				while (rs.next()){
					listaTweets.add(new Tweet(rs.getLong("tweet_id"),rs.getString("text"),rs.getString("user_name"),urlTemp));
				}
				rs.close();
			}catch (SQLException e){
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//Vamos a sacar los datos en consola a ver que pasa, vale funciona
		/*for(int i=0;i<listaTweets.size();++i){
			System.out.print(i+"  "+listaTweets.get(i).getId());
			System.out.print("  "+listaTweets.get(i).getText());
			System.out.print("  "+listaTweets.get(i).getUser().getName());
			System.out.println("  "+listaTweets.get(i).getUrl().getUrl());
		}*/
		return listaTweets;
	}
	
	
	
	
	@Override
	//Inserta la información del DTO Tweet en las distintas tablas.
	public void insertTweet(Tweet tweet) {
		// TODO Auto-generated method stub
		Statement stm = null;
		int rs = 0;
		
		//Si no existe el tweet lo insertamos en las tablas tweets, hashtags_tweet, urls_tweet 
		if (!(this.exists(tweet.getId()))){
	    //Insertamos en tweets la información de usuario Twitter.
		    try {
        	    stm = this.conn.createStatement();
        	    StringBuilder sql = new StringBuilder("INSERT INTO tweets (tweet_id, text, user_id, user_name, followers_count, friends_count,profile_image_url_https)");
        	    sql.append("VALUES ( '"+tweet.getId()+"','"+tweet.getText().replace("'", "")+"','"+tweet.getUser().getId()+"','"+tweet.getUser().getName()+"','"+tweet.getUser().getFollowersCount()+"','"+tweet.getUser().getFriendsCount()+"','"+tweet.getUser().getProfuleImageUrl()+"')");
        	    //sql.append("VALUES ( '"+tweet.getId()+"','"+"PRUEBA"+"','"+tweet.getUser().getId()+"','"+"PRUEBA"+"','"+tweet.getUser().getFollowersCount()+"','"+tweet.getUser().getFriendsCount()+"' )");
        	    rs = stm.executeUpdate(sql.toString());
        	    System.out.println(sql);
        	
        //Insertamos los hashtags del tweet en la tabla hashtags_tweet	
        	for (int i=0; i< tweet.getEntities().hashtags.length; i++) {
			    sql = new StringBuilder("INSERT INTO hashtags_tweet (tweet_id, hashtag_id) VALUES ( '"+tweet.getId()+"','"+tweet.getEntities().getHashtags()[i].getText()+"' )");
			    System.out.println(sql);
			    rs = stm.executeUpdate(sql.toString());
		        }
        	
        //Insertamos las URLs del tweet en la tabla urls_tweet	
        	if ((tweet.getEntities().urls)!=null){
        	for (int j=0; j< tweet.getEntities().urls.size(); j++) {
			    sql = new StringBuilder("INSERT INTO urls_tweet (tweet_id, url) VALUES ( '"+tweet.getId()+"','"+tweet.getEntities().getUrls().get(j).getUrl()+"' )");
			    System.out.println(sql);
			    rs = stm.executeUpdate(sql.toString());
		        }
        	}
        	
				stm.close();
		    } catch (SQLException e) {
			e.printStackTrace();
		}
		}	
	}

	@Override
	public void insertTweets(ArrayList<Tweet> tweets) {
		// TODO Auto-generated method stub
		
	}

	//@Override
	public Tweet selectTweetOld(long tweetid) {
		// TODO Auto-generated method stub
		Tweet devuelveTweet = new Tweet();
		User usuarioTweet = new User();
		Entities conjuntos = new Entities();
		Hashtag[] hashtagsAsociados = null;
		ArrayList<Url> urlsAsociadas = null;
		Statement stm = null;
		int rsCont = 0;
		ResultSet rs = null;
		
		//Si no existe el tweet devolvemos directamente null. 
		if (this.exists(tweetid)){
			
			devuelveTweet.setId(tweetid);
			
	    //Leemos de la tabla tweets la información de usuario Twitter.
		    try {
        	    stm = this.conn.createStatement();
        	    StringBuilder sql = new StringBuilder("SELECT tweet_id, text, user_id, user_name, followers_count, friends_count FROM tweets WHERE tweet_id='" + tweetid + "';");
        	    System.out.println(sql);
        	    rs = stm.executeQuery(sql.toString());
        	    if(rs.next()){
        		    devuelveTweet.setText(rs.getString(2));
        		    usuarioTweet.setId(rs.getLong(3));
        		    usuarioTweet.setName(rs.getString(4));
        		    usuarioTweet.setFollowersCount(rs.getLong(5));
        		    usuarioTweet.setFriendsCount(rs.getInt(6));
        		    devuelveTweet.setUser(usuarioTweet);
        		    
        		}
        	
        //Leemos los hashtags del tweet de la tabla hashtags_tweet
        	 
        	    sql = new StringBuilder("SELECT hashtag_id FROM hashtags_tweet WHERE tweet_id='" + tweetid + "';");
			    System.out.println(sql);
			    rs = stm.executeQuery(sql.toString());
			    int cuenta=0;
			    while (rs.next()) {
			    	hashtagsAsociados[cuenta]=new Hashtag(rs.getString(1));
			        cuenta++;
			    }

			    if (hashtagsAsociados.length>0)  conjuntos.setHashtags(hashtagsAsociados); 
		
		//Leemos las URLs del tweet de la tabla urls_tweet
	        	 
			    sql = new StringBuilder("SELECT url FROM urls_tweet WHERE tweet_id='" + tweetid + "';");
			    System.out.println(sql);
			    rs = stm.executeQuery(sql.toString());
			    cuenta=0;
			    while (rs.next()) {urlsAsociadas=new ArrayList<Url>();
			    	urlsAsociadas.add(new Url());
			    	urlsAsociadas.get(cuenta).setUrl(rs.getString(1));
			        cuenta++;
			    }
			    if (urlsAsociadas.size()>0)  conjuntos.setUrls(urlsAsociadas); 
		
			    
	    // Actualizamos el objeto Tweet a devolver.
			    devuelveTweet.setEntities(conjuntos);
			    
        	
				stm.close();
		    } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		}
		return null;
	}

	@Override
	public Tweet selectTweet(long tweetid) {
		// TODO Auto-generated method stub
		Tweet devuelveTweet = new Tweet();
		User usuarioTweet = new User();
		Entities conjuntos = new Entities();
		Hashtag[] hashtagsAsociados = null;
		ArrayList<Url> urlsAsociadas = null;
		Statement stm = null;
		int rsCont = 0;
		ResultSet rs = null;
		
		//Si no existe el tweet devolvemos directamente null. 
		if (this.exists(tweetid)){
			
			devuelveTweet.setId(tweetid);
			
	    //Leemos de la tabla tweets_index la información de usuario Twitter.
		    try {
        	    stm = this.conn.createStatement();
        	    StringBuilder sql = new StringBuilder("SELECT tweet_id, text, user_id, user_name, followers_count, friends_count FROM tweets WHERE tweet_id='" + tweetid + "';");
    		    System.out.println(sql);
        	    rs = stm.executeQuery(sql.toString());
        	    if(rs.next()){
        		    devuelveTweet.setText(rs.getString(2));
        		    usuarioTweet.setId(rs.getLong(3));
        		    usuarioTweet.setName(rs.getString(4));
        		    usuarioTweet.setFollowersCount(rs.getLong(5));
        		    usuarioTweet.setFriendsCount(rs.getInt(6));
        		    devuelveTweet.setUser(usuarioTweet);
        		    
        		}
        	
        //Leemos los hashtags del tweet de la tabla hashtags_tweet_index
			    sql = new StringBuilder("SELECT hashtag_id FROM hashtags_tweet WHERE tweet_id='" + tweetid + "';");
			    System.out.println(sql);
			    rs = stm.executeQuery(sql.toString());   
			    int cuenta= getRowCount(rs);
        	    hashtagsAsociados = new  Hashtag[getRowCount(rs)];
			    cuenta =0;
			   while (rs.next()) {
			    	hashtagsAsociados[cuenta]=new Hashtag(rs.getString(1));
			        cuenta++;
			    }
			    if (hashtagsAsociados.length>0)  conjuntos.setHashtags(hashtagsAsociados);
		
		//Leemos las URLs del tweet de la tabla urls_tweet_index
	        	 
			    sql = new StringBuilder("SELECT url FROM urls_tweet WHERE tweet_id='" + tweetid + "';");
			    System.out.println(sql);
			    rs = stm.executeQuery(sql.toString());
			    cuenta=0;
			    urlsAsociadas=new ArrayList<Url>();
			   while (rs.next()) {				   
			    	urlsAsociadas.add(new Url());
			    	urlsAsociadas.get(cuenta).setUrl(rs.getString(1));
			        cuenta++;
			    }
			    if (urlsAsociadas.size()>0)  conjuntos.setUrls(urlsAsociadas); 
		
			    
	    // Actualizamos el objeto Tweet a devolver.
			    devuelveTweet.setEntities(conjuntos);
			    
        	
				stm.close();
		    } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		}
		return devuelveTweet;
	}

	private int getRowCount(ResultSet resultSet) {
	    if (resultSet == null) {
	        return 0;
	    }
	    try {
	        resultSet.last();
	        return resultSet.getRow();
	    } catch (SQLException exp) {
	        exp.printStackTrace();
	    } finally {
	        try {
	            resultSet.beforeFirst();
	        } catch (SQLException exp) {
	            exp.printStackTrace();
	        }
	    }
	    return 0;
	}

	
	
	@Override
	public ArrayList<Tweet> selectTweets(String hashtag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Tweet> selectTweets(String hashtag, int minimo, int maximo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int contadorTweets(String hashtag) {
		Statement stm = null;
		ResultSet rs = null;
		// TODO Auto-generated method stub
		try {
			stm = this.conn.createStatement();
        	rs = stm.executeQuery("select count(*) from hashtags_tweet where hashtag_id='" + hashtag + "';");
			if (rs.next()){
				return(rs.getInt(1));
			}
			rs.close();
			stm.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
	}

	@Override
	public void deleteTweets(String hashtag) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean exists(long tweetid) {
		Statement stm = null;
		ResultSet rs = null;
		// TODO Auto-generated method stub
		try {
			stm = this.conn.createStatement();
        	rs = stm.executeQuery("select count(*) from tweets where tweet_id='" + tweetid + "';");
			if (rs.next()){
				if (rs.getInt(1) > 0){return(true);}
			}
			rs.close();
			stm.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

    @Override   
    public long selectMaxId(){
      	Statement stm = null;
		ResultSet rs = null;
		try {
			stm = this.conn.createStatement();
        	rs = stm.executeQuery("select max_id from poll;");
			if (rs.next()){
				return(rs.getLong(1));
			}
			rs.close();
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (-1);
	}

    @Override
    public void insertMaxId(long maxid){
    	Statement stm = null;
		int rs = 0;
		
		//Borramos cualquier registro de la tabla, soo queremos uno.
		//Insertamos el máximo id tweet.
		try {
        	stm = this.conn.createStatement();
        	StringBuilder sql = new StringBuilder("Delete from poll;");
        	rs = stm.executeUpdate(sql.toString());
        	    
			sql = new StringBuilder("INSERT INTO poll (max_id) VALUES ( '"+maxid+"' )");
			System.out.println(sql);
			rs = stm.executeUpdate(sql.toString());
		     
			stm.close();
		    } catch (SQLException e) {
			    e.printStackTrace();
		}	
    }
	

}
