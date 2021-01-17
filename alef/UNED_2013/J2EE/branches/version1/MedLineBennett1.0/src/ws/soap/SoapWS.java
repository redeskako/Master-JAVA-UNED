package ws.soap;

import javax.jws.WebMethod;
import javax.jws.WebService;
@WebService

public interface SoapWS {
@WebMethod 
SearchResult search(String campo,String valor,int page,int elementsByPage);

}