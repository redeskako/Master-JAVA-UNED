package uned.java2016.apitwitter.services.soap.estudios;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import uned.java2016.apitwitter.services.rs.jaxb.Estudios; 
import uned.java2016.apitwitter.dao.ClinicalStudy;

@WebService  
public interface EstudioService {  
  
 @WebMethod
 public Estudios getByHashtag(  @WebParam (name="hashtag") String ht, @WebParam (name="count") int count);

 @WebMethod
 public ClinicalStudy getByNCT(  @WebParam (name="nct") String nct);

 @WebMethod
 public String echo(); 
}  