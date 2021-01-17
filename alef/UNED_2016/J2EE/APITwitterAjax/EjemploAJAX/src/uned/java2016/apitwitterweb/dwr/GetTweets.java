package uned.java2016.apitwitterweb.dwr;

import java.util.ArrayList;
import uned.java2016.apitwitter.dao.ConnDAOImpl;
import uned.java2016.apitwitter.dao.Tweet;
import uned.java2016.apitwitter.dao.TweetsDAOImpl;

public class GetTweets
{
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Tweet> misTweets;
	private TweetsDAOImpl miTweetsDAOImpl;
	
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
	 * @ out ArrayList<ArrayList<String>> con la lista de tweets
	 * @ error 
	 */
	public ArrayList<ArrayList<String>> cargarTweets(String queHashtag,int primero,int cuantos){

		this.misTweets=new ArrayList<Tweet>();

		ConnDAOImpl miBd = new ConnDAOImpl();
		miBd.abrirConexion(); 
		
		miTweetsDAOImpl=new TweetsDAOImpl(miBd.getConnection());
		misTweets=miTweetsDAOImpl.leerTweetsconHashtagDeterminado(queHashtag,primero,cuantos);
		
		miBd.cerrarConexion();
		
		 ArrayList<ArrayList<String>> listaTweets=new ArrayList<>();
		 
	     for(int i=0;i<misTweets.size();++i){
	    	 ArrayList<String> lista1Fila=new ArrayList<>();
	    	 listaTweets.add(i,lista1Fila);
	    	 lista1Fila.add(0,Long.toString(misTweets.get(i).getId()));
	    	 lista1Fila.add(1,misTweets.get(i).getText());
	    	 lista1Fila.add(2,misTweets.get(i).getUser().getName());
	    	 lista1Fila.add(3,misTweets.get(i).getUrl().getUrl());
	     }
	     /*for(int i=0;i<misTweets.size();++i){
	    	 for(int j=0;j<4;++j){
	    		 System.out.println(listaTweets.get(i).get(j));
	    	 }
	     }*/
		
		return listaTweets;
	}
}