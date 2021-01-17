package uned.java2016.apitwitter.services;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import uned.java2016.apitwitter.services.rs.jaxb.Neighbours;  

@WebService  
public interface NeighbourService {  
  
 @WebMethod
 public Neighbours getNeighbours(  @WebParam (name="hashtag") String hashtag);

 @WebMethod
 public String echo(); 
}  