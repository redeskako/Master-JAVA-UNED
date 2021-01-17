package uned.java2016.apitwitter.services.rs.jaxb;

import javax.xml.bind.*;
import javax.xml.bind.annotation.*;

import java.util.*;
import uned.java2016.apitwitter.dao.*;

/**
 * Clase simple que permite guardar un grupo de tweets para su uso con jaxb
 * @author José Barba Martínez (jbarba63)
 *
 */
public class Tweets {
	/**
	 * Lista de Tweets
	 */
	protected List<Tweet> tweets=null;
	  public void setTweets(List<Tweet> t){this.tweets=t;}
	  
	  /**
	   * Este metodo es el q, con la anotacion correspondiente, nos permite deserializar la lista de tweets
	   * @return
	   */
	  @XmlElement(name="tweet")
	  public List<Tweet> getTweets(){return this.tweets;}
	
	public Tweets(){}  
	  
	public Tweets(List<Tweet> t){
		this.setTweets(t);
	}
	
	
	
	
}
