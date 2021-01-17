package uned.java2016.apitwitter.services.rs.jaxb;

import javax.xml.bind.annotation.*;

import uned.java2016.apitwitter.dao.ClinicalStudy;

import java.util.*;

/**
 * Clase simple que permite guardar un grupo de estudios para su uso con jaxb
 * @author Uned 2016
 *
 */
public class Estudios {
	/**
	 * Lista de Estudios
	 */
	protected List<ClinicalStudy> estudios=null;
	public void setEstudios(List<ClinicalStudy> t){this.estudios=t;}
	  
	  /**
	   * Este metodo es el q, con la anotacion correspondiente, nos permite deserializar la lista de estudios
	   * @return
	   */
	  @XmlElement(name="estudio")
	  public List<ClinicalStudy> getEstudios(){return this.estudios;}
	
	public Estudios(){}  
	  
	public Estudios(List<ClinicalStudy> list){
		this.setEstudios(list);
	}
	
	
	
	
}
