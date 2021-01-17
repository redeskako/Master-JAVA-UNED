package uned.java2016.apitwitter.dao;

import java.util.ArrayList;

public class Entities {
	/** Atributos */
		/** Constantes */
		/** Variables */
	protected Hashtag[] hashtags=null;
	protected ArrayList<Url> urls;
 
	/** Constructor por defecto, inicializa un objeto de tipo hashtag totalmente vacío
  	 * @ in 
  	 * @ out no procede
  	 * @ error   
  	 */
	public Entities(){
		this.urls=null;
	}
	/** Constructor que carga cada entidad con los valores de la BBDD
	 * @ in 
	 * 		ArrayList<Url> lista con la lista de las URLs
	 * @ out no procede
	 * @ error   
	 */ 
	public Entities(ArrayList<Url> lista){
		this.urls=new ArrayList<Url>();
		this.urls=lista;
	}
	/** Métodos de acceso I/O a los campos de la clase */
	/** El método getUrls lee la lista de urls
	 * @ in 
	 * @ out String con url
	 * @ error   
	 */ 
	public ArrayList<Url> getUrls(){
		return this.urls;
	}
	/** El método setUrls escribe la lista de urls
	  * @ in String a con la url
	  * @ out void
	  * @ error   
	  */
	public void setUrls(ArrayList<Url> u){
		this.urls=u;
	}
	
	public void setUrls(Url[] u){
		if (u!=null && u.length>0){
		    ArrayList<Url> lasurls =new ArrayList<Url>();
		    for (int i=0;i<u.length;i++){
			    lasurls.add(u[i]);
		    }
		    this.setUrls(lasurls);
		}
	}
	
	public void setHashtags(Hashtag[] h){this.hashtags=h;}
	public Hashtag[] getHashtags(){return this.hashtags;}
}
