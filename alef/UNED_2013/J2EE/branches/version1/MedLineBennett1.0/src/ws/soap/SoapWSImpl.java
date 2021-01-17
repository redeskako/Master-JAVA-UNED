package ws.soap;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import utils.Limiter;
import beans.HealthTopics;
import beans.HealthTopics.HealthTopic;

@WebService(endpointInterface = "ws.soap.SoapWSImpl")
public class SoapWSImpl implements SoapWS{

	//Averiguar como sacar una ruta del servidor en un ws. ¿?	
	private static final String ruta = "ruta";
	
	@WebMethod 
	public SearchResult search(String campo,String valor,int page,int elementsByPage){
		SearchResult out = new SearchResult();
		
		out.setElementsByPage(elementsByPage);
		out.setPage(page);
		
		HealthTopics ht = new HealthTopics();
		List<HealthTopic> list = ht.BusquedaMixtaMedlineBennett(ruta, campo, valor);
		
		List<HealthTopic> limitedList = Limiter.limit(list,page,elementsByPage);
		
		out.setMaxResults(list.size());
		out.setResults(limitedList);
		
		return out;		
	
	}
}
