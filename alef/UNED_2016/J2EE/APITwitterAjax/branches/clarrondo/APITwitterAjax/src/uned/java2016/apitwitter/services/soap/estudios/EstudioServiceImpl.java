package uned.java2016.apitwitter.services.soap.estudios;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

//import javax.xml.bind.*;
//import javax.xml.namespace.*;
//import java.io.*;
//import java.util.*;
import uned.java2016.apitwitter.dao.*;
import uned.java2016.apitwitter.services.rs.jaxb.*;

@WebService(endpointInterface="uned.java2016.apitwitter.services.soap.estudios.EstudioService")  
public class EstudioServiceImpl implements EstudioService {

    @Resource
    WebServiceContext wsctx;
    
	@Override
	public Estudios getByHashtag(String ht, int count) {
		// TODO Auto-generated method stub
		
		//Response ret=null;
		Estudios t=null;
		// abrimos conexion
		ConnDAOImpl conn = null;
		try{
		conn=new ConnDAOImpl();
		conn.abrirConexion();
		ClinicalStudyDAO tdao=new ClinicalStudyDAOImpl(conn.getConnection());
		List<ClinicalStudy> list=tdao.selectClinicalStudies(ht,1,count);
		t=new Estudios(list);					
		//ret=Response.ok(new JAXBElement(new QName("","estudios"),Estudios.class,t), MediaType.APPLICATION_XML).build();
		}catch(Exception e){
			System.out.println("Error accediendo a datos");
		}
		finally{
			try{conn.getConnection().close();}catch(Exception ce){}finally{conn=null;}
		}
		return t;

	}

	public ClinicalStudy getByNCT(String nct) {

				//Response ret=null;
		ClinicalStudy study=null;
		// abrimos conexion
		ConnDAOImpl conn = null;
		try{
		conn=new ConnDAOImpl();
		conn.abrirConexion();
		ClinicalStudyDAO cdao=new ClinicalStudyDAOImpl(conn.getConnection());
		study = cdao.selectClinicalStudy(nct);					
		//ret=Response.ok(new JAXBElement(new QName("","estudio"),ClinicalStudy.class,study), MediaType.APPLICATION_XML).build();		
		}catch(Exception e){
			System.out.println("Error accediendo a datos");
		}
		finally{
			try{conn.getConnection().close();}catch(Exception ce){}finally{conn=null;}
		}
		return study;
	}


	@Override
	public String echo() {
		// TODO Auto-generated method stub
		return "Hola Estudio";
	}

}