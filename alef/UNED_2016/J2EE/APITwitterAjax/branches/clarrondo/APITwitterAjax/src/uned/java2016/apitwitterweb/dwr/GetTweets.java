package uned.java2016.apitwitterweb.dwr;

import java.util.ArrayList;

import uned.java2016.apitwitter.dao.ConnDAOImpl;
import uned.java2016.apitwitter.dao.Tweet;
import uned.java2016.apitwitter.dao.TweetsDAOImpl;

public class GetTweets
{
	private static final long serialVersionUID = 1L;
	
	private static ArrayList<Tweet> misTweets;
	private TweetsDAOImpl miTweetsDAOImpl;
	
	private static String hashtag;
	
	/** Constructor por defecto
	 * 
	 */
	public GetTweets(){
		
	}
	
	/** El método cargarTweets se encarga de cargar los Tweets que contengan el hashtag elegido 
	 *  por el usuario
	 *  @ in
	 * 		String queHashtag
	 * 		int primero
	 * 		int cuantos
	 * @throws IOException 
	 * @throws ServletException 
	 * @ out ArrayList<Tweet>
	 * @ error 
	 */
	public ArrayList<Tweet> cargarTweets(String queHashtag){

		GetTweets.misTweets=new ArrayList<Tweet>();
		GetTweets.hashtag=queHashtag;

		ConnDAOImpl miBd = new ConnDAOImpl();
		miBd.abrirConexion(); 
		
		miTweetsDAOImpl=new TweetsDAOImpl(miBd.getConnection());
		misTweets=miTweetsDAOImpl.leerTweetsconHashtagDeterminadoAjax(queHashtag);
		
		miBd.cerrarConexion();
		
		return misTweets;
	}
	/* Getter */
	public ArrayList<Tweet> leerTweets(){
		return misTweets;
	}
	public String getHashtag(){
		return hashtag;
	}
}