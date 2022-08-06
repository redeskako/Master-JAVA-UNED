package uned.java2016.apitwitter.services;

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

@WebService(endpointInterface="uned.java2016.apitwitter.services.NeighbourService")  
public class NeighbourServiceImpl implements NeighbourService {

    @Resource
    WebServiceContext wsctx;
    
	@Override
	public Neighbours getNeighbours(String hashtag) {
		// TODO Auto-generated method stub

		/*MessageContext mctx = wsctx.getMessageContext();

		//get detail from request headers
	        Map http_headers = (Map) mctx.get(MessageContext.HTTP_REQUEST_HEADERS);
	        List userList = (List) http_headers.get("Username");
	        List passList = (List) http_headers.get("Password");

	        String username = "";
	        String password = "";

	        if(userList!=null){
	        	//get username
	        	username = userList.get(0).toString();
	        }

	        if(passList!=null){
	        	//get password
	        	password = passList.get(0).toString();
	        }		
		*/
		
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