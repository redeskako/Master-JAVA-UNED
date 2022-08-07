package uned.java2016.apitwitter.clinical.crawler;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.logging.log4j.Logger;

import uned.java2016.apitwitter.dao.*;
//import uned.java2016.apitwitter.clinical.crawler.model.ClinicalStudy;
//import uned.java2016.apitwitter.clinical.crawler.model.Hashtag;

/**
 * @author Alberto Gomez Gonzalez
 * Esta clase es la encargada de realizar los sondeos y a su vez de generar el objeto
 * LinkedHashMap resultante del mismo
 */
public class Poll {

	protected Logger logger=null;
	  public void setLogger(Logger logger){this.logger=logger;}
	  public Logger getLogger(){return this.logger;}
	
	protected LinkedHashMap<Hashtag,HashSet<ClinicalStudy>> map=null;
	  public LinkedHashMap<Hashtag,HashSet<ClinicalStudy>> getMap(){return this.map;}
	  
	public Poll(){
		this.map=new LinkedHashMap<Hashtag,HashSet<ClinicalStudy>>();
	}
	
	/**
	 * Este metodo se encarga de añadir un nuevo Hashtag al map
	 * @param hash Objeto Hashtag a añadir al map
	 */
	public void addHashtag(Hashtag hash){
		if(!this.getMap().containsKey(hash))
		{
			this.getMap().put(hash, new HashSet<ClinicalStudy>());
		}
	}
	
	 /**
	 * Devuelve el set asociado a un Hashtag
	 * @param hash Hastag sobre el que se quiere obtener el Set
	 * @return Set asociado al hashtag pasado por parametro
	 */
	public HashSet<ClinicalStudy> getClinicalStudySet(Hashtag hash){
		HashSet<ClinicalStudy> ret=null;
		ret=this.getMap().get(hash);
		return ret;
	}
	
	/**
	 * Añade un estudio al map asociado a un hashtag
	 * @param hash Hastag al que que pertenece el estudio
	 * @param cs Estudio que se desea añadir
	 */
	public void addClinicalStudy(Hashtag hash,ClinicalStudy cs){
		HashSet<ClinicalStudy> set=null;
		if(!this.getMap().containsKey(hash))
		{
			set=new HashSet<ClinicalStudy>();
			this.getMap().put(hash,set);
		}
		else
			set=this.getMap().get(hash);
		
		set.add(cs);
	    		
		}		
	
	

	/**
	 * Añade un array de estudios al map asociado a un hashtag
	 * @param hash Hastag al que que pertenece el estudio
	 * @param tts Array de estudios que se desea añadir
	 */
	public void addClinicalStudy(Hashtag hash,ClinicalStudy[] tts){
	  if(tts!=null && tts.length>0)
	  {
		this.getLogger().info("Aniadiendo "+tts.length+" Estudios...");		
	    for(int i=0;i<tts.length;i++)
	    	this.addClinicalStudy(hash, tts[i]);
	  }
	}
	
	

	/**
	 * Devuelve el conjunto de Estudios asociados a un hashtag
	 * @param hash Hashtag que se desea buscar
	 * @return Array de estudios devueltos
	 */
	public ClinicalStudy[] getClinicalStudies(Hashtag hash){
		ClinicalStudy[] ret=null;
	  if(this.map.containsKey(hash))
	   {
		 HashSet<ClinicalStudy> set=this.map.get(hash);
		 
		 if(set.size()>0)
		 {
			 ret=new ClinicalStudy[set.size()];
			 Iterator<ClinicalStudy> it=set.iterator();
			 int i=0;
			 while(it.hasNext())
			 {
			   ret[i]=(ClinicalStudy) it.next();
			   i++; 
		     }		 
		 }
	   }
	  return ret;
	}
	
	
	public Iterator<Hashtag> getHashtagIterator(){
		return this.map.keySet().iterator();
	}
	
	public Iterator<Map.Entry<Hashtag, HashSet<ClinicalStudy>>> getEntryIterator(){
		return this.getMap().entrySet().iterator();			   
	}
}
