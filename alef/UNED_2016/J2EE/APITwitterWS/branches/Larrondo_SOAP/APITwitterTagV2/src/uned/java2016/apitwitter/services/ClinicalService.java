package uned.java2016.apitwitter.services;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import uned.java2016.apitwitter.services.rs.jaxb.Clinicals;  

@WebService  
public interface ClinicalService {  
  
 @WebMethod
 public Clinicals getClinicals(  @WebParam (name="hashtag") String hashtag);

 @WebMethod
 public String echo(); 
}  