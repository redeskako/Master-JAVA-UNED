package uned.java2016.apitwitter.services.rs.jaxb;


import javax.xml.bind.annotation.*;

import uned.java2016.apitwitter.dao.ClinicalStudy;

import java.util.*;


public class Clinicals {
	/**
	 * Lista de Clinicals
	 */
	protected ArrayList<ClinicalStudy> clinicals=null;
	  public void setClinicals(ArrayList<ClinicalStudy> n){this.clinicals=n;}
	  
	  /**
	   * 
	   * @return
	   */
	  @XmlElement(name="clinical")
	  public ArrayList<ClinicalStudy> getClinicals(){return this.clinicals;}
	
	public Clinicals(){}  
	  
	public Clinicals(ArrayList<ClinicalStudy> n){
		this.setClinicals(n);
	}

}
