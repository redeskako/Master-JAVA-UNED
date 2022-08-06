package uned.java2016.apitwitter.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import uned.java2016.apitwitter.dao.ClinicalStudy;
import uned.java2016.apitwitter.dao.ClinicalStudyDAO;
import uned.java2016.apitwitter.dao.ClinicalStudyDAOImpl;
import uned.java2016.apitwitter.dao.ConnDAOImpl;
import uned.java2016.apitwitter.dao.NeighborsDAO;
import uned.java2016.apitwitter.dao.NeighborsDAOImpl;
import uned.java2016.apitwitter.services.rs.jaxb.Clinicals;

@WebService(endpointInterface="uned.java2016.apitwitter.services.ClinicalService")  
public class ClinicalServiceImpl implements ClinicalService {

    @Resource
    WebServiceContext wsctx;
    
	@Override
	public Clinicals getClinicals(String hashtag) {
		// TODO Auto-generated method stub
		
		Clinicals ret=null;
		// abrimos conexion
		ConnDAOImpl conn = null;
		try{
			conn=new ConnDAOImpl();
			conn.abrirConexion();
			ClinicalStudyDAO ndao=new ClinicalStudyDAOImpl(conn.getConnection());
			ArrayList<ClinicalStudy> list=ndao.selectClinicalStudies(hashtag);
			ret=new Clinicals(list);					
		
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