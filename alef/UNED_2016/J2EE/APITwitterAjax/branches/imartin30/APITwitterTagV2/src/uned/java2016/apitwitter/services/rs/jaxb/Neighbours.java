package uned.java2016.apitwitter.services.rs.jaxb;


import javax.xml.bind.annotation.*;
import java.util.*;


public class Neighbours {
	/**
	 * Lista de Vecinos
	 */
	protected List<String> neighbours=null;
	  public void setNeighbours(List<String> n){this.neighbours=n;}
	  
	  /**
	   * Este metodo es el q, con la anotacion correspondiente, nos permite deserializar la lista de vecinos
	   * @return
	   */
	  @XmlElement(name="neighbour")
	  public List<String> getNeighbours(){return this.neighbours;}
	
	public Neighbours(){}  
	  
	public Neighbours(List<String> n){
		this.setNeighbours(n);
	}

}
