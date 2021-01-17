package ws.rest;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

import beans.Bennett;
import beans.HealthTopics;
import beans.HealthTopics.HealthTopic;

@Path("/search")
public class WSRest {


	@Context
	 ServletContext context;
	
    @GET    
    @Produces("text/xml")
    public String getClichedMessage( 
    		@QueryParam("catalogo") String catalogo,
    		@QueryParam("palabra") String palabra,
    		 @QueryParam("campo") String campo) {

    	String out = "";
    	
    	if ("Bennett".equalsIgnoreCase(catalogo)){
	    	int pag = 1;
	    	int filas = 10;
	    	
	    	List<Bennett> bList = new ArrayList<Bennett>();
	    	
	    			try{
	    			bList =Bennett.listadoBennett(campo, palabra, pag, filas);
	    			}catch(Exception e){
	    				e.printStackTrace();
	    			}
	    	
	    	out = toXML(bList);
    	}else{
    		if("MedLine".equalsIgnoreCase(catalogo)){
    			String ruta = context.getRealPath(context.getInitParameter("dirUploadFiles"))
 						+ File.separator + "medlinecompleta.xml";//"C:\\Users\\David\\medlinecompleta.xml";
    			HealthTopics hts = new HealthTopics();
    			ArrayList<HealthTopic> listadoCompleto = new ArrayList<HealthTopic>();
    			listadoCompleto = hts.BusquedaHealthTopicXML(ruta, campo, palabra);
    			
    			out = toXML2(listadoCompleto);
    		}
    	}
    	
        return out;
    }
    
    private String toXML(List<Bennett> list){
    	StringBuffer sb = new StringBuffer("<?xml version='1.0'?>");
    	
    	//Cabecera.
    	sb.append("<Search>\n");
    	
    	//Cuerpo
    	for(Bennett item: list){
    		sb.append("<title>" + item.getOrganization()+"</title>\n");
    		sb.append("<url>" + item.getFullOrgURL()+"</url>\n");
    	}
    	
    	//Cola
    	sb.append("</Search>");
    	
    	String out = sb.toString();
    	
    	return out;
    }
    
    private String toXML2(List<HealthTopic> list){
    	StringBuffer sb = new StringBuffer("<?xml version='1.0'?>");
    	
    	//Cabecera.
    	sb.append("<Search>\n");
    	
    	//Cuerpo
    	for(HealthTopic item: list){
    		sb.append("<title>" + item.getTitle()+"</title>\n");
    		sb.append("<url>" + item.getUrl()+"</url>\n");
    	}
    	
    	//Cola
    	sb.append("</Search>");
    	
    	String out = sb.toString();
    	
    	return out;
    }
}
