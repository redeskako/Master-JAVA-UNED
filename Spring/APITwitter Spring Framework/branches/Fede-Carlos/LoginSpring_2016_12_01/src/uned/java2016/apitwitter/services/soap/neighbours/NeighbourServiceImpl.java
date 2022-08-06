package uned.java2016.apitwitter.services.soap.neighbours;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import uned.java2016.apitwitter.dao.ConnDAOImpl;
import uned.java2016.apitwitter.dao.NeighborsDAO;
import uned.java2016.apitwitter.dao.NeighborsDAOImpl;
import uned.java2016.apitwitter.services.rs.jaxb.Neighbours;

@WebService(endpointInterface="uned.java2016.apitwitter.services.soap.neighbours.NeighbourService")  
public class NeighbourServiceImpl implements NeighbourService {

    @Resource
    WebServiceContext wsctx;
    
	@Override
	public Neighbours getNeighbours(String hashtag) {
		// TODO Auto-generated method stub
		
		Neighbours ret=null;
		// abrimos conexion
		ConnDAOImpl conn = null;
		try{
			conn=new ConnDAOImpl();
			conn.abrirConexion();
			NeighborsDAO ndao=new NeighborsDAOImpl(conn.getConnection());
			List<String> list=ndao.selectNeighbors(hashtag);
			ret=new Neighbours(list);					
		
		}catch(Exception e){

			System.out.println("Error accediendo a datos");
		}
		finally{
			try{conn.getConnection().close();}catch(Exception ce){}finally{conn=null;}
		}
		return ret;

	}

	@Override
	public String echo() {
		// TODO Auto-generated method stub
		return "Hola";
	}

}