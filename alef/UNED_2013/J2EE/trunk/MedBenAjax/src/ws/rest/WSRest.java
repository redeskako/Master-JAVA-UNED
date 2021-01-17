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

import java.util.logging.*;

@Path("/v1")
public class WSRest {


	private static Logger log = Logger.getLogger(WSRest.class.getCanonicalName());
	
	public WSRest(){
		log.setLevel(Level.FINEST);
		Logger.getGlobal().setLevel(Level.FINEST);
		try{
			String ruta =   "%h/application.log";
			FileHandler fh= new FileHandler(ruta,true);
			fh.setFormatter(new SimpleFormatter());			
			log.addHandler(fh);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Context
	 ServletContext context;
	
    @GET    
    @Produces("text/xml")
    @Path("/search")
    public String search( 
    		@QueryParam("catalogo") String catalogo,
    		@QueryParam("palabra") String palabra,
    		 @QueryParam("campo") String campo) {

    	String out = "";
    	
    	
    	log.info("Enter in search with catalogo='"+ catalogo +"' palabra='"+palabra+"' campo'"+campo+"'");
    	
    	if ("Bennett".equalsIgnoreCase(catalogo)){
	    	int pag = 1;
	    	int filas = 100;
	    	
	    	log.finest("Search in Bennett");
	    	
	    	ArrayList<Bennett> bList = new ArrayList<Bennett>(); //cambio List<Bennett>bList por ArrayList
	    	
	    			try{
	    			bList =Bennett.listadoBennett(campo, palabra, pag, filas);
	    			}catch(Exception e){
	    				e.printStackTrace();
	    			}
	    	
	    	log.finest("Writing in the output " + bList.size() + " resultados");
	    	
	    	out = toXML(bList);
	    	
    	}else{
    		if("MedLine".equalsIgnoreCase(catalogo)){
    			
    			log.info("Search in MedLine");
    			
    			String ruta = context.getRealPath(context.getInitParameter("dirUploadFiles"))
 						+ File.separator + "medlinecompleta.xml";
    			
    			log.info("Database file: "+ ruta);
    			
    			HealthTopics hts = new HealthTopics();
    			ArrayList<HealthTopic> listadoCompleto = new ArrayList<HealthTopic>();
    			listadoCompleto = hts.BusquedaHealthTopicXML(ruta, campo, palabra);
    			
    			log.finest("Writing in the output" + listadoCompleto.size() + " resultados");
    			out = toXML2(listadoCompleto);
    		}
    	}
    	
        return out;
    }
    
    private String toWordResultXML(ArrayList<String> list){ 
    	StringBuffer sb = new StringBuffer("<?xml version='1.0'?>");
    	
    	//Cabecera.
    	sb.append("<Search>\n");
    	
    	//Cuerpo
    	for(String item: list){
    		sb.append("<Result>" + item+"</Result>\n");
    	}
    	
    	//Cola
    	sb.append("</Search>");
    	
    	String out = sb.toString();
    	
    	return out;
    }
    
    private String toXML(ArrayList<Bennett> list){ //Cambio List<Bennett> list por ArrayList<Bennett> list
    	//StringBuffer sb = new StringBuffer("<?xml version='1.0'?>");
    	StringBuffer sb = new StringBuffer();
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
    
    private String toXML2(ArrayList<Bennett> list){ //Cambio List<Bennett> list por ArrayList<Bennett> list
    	//StringBuffer sb = new StringBuffer("<?xml version='1.0'?>");
    	StringBuffer sb = new StringBuffer();
    	//Cabecera.
    	sb.append("<Search>\n");
    	
    	//Cuerpo
    	for(Bennett item: list){
    		sb.append("<title>" + item.getCity()+"</title>\n");
    		sb.append("<url>" + item.getFullOrgURL()+"</url>\n");
    	}
    	
    	//Cola
    	sb.append("</Search>");
    	
    	String out = sb.toString();
    	
    	return out;
    }
    private String toXML2(List<HealthTopic> list){
    	//StringBuffer sb = new StringBuffer("<?xml version='1.0'?>");
    	StringBuffer sb = new StringBuffer();
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
    
    private String toXML3(List<String> pals){
    	//StringBuffer sb = new StringBuffer("<?xml version='1.0'?>");
    	StringBuffer sb = new StringBuffer();
    	//Cabecera.
    	sb.append("<Search>\n");
    	
    	//Cuerpo
    	for(String item: pals){
    		sb.append("<title>" + item +"</title>\n");
    		sb.append("<url>nulo</url>\n");
    	}
    	
    	//Cola
    	sb.append("</Search>");
    	
    	String out = sb.toString();
    	
    	return out;
    }
    
    @GET    
    @Produces("text/xml")
    @Path("/startWith")
    public String startWith(@QueryParam("catalogo") String catalogo,
    						@QueryParam("palabra") String palabra, 
    						@QueryParam("limite") int limite){

    	String out = "";
    	String palabra_buscada = "";
    	
    	log.info("Enter in startWith with catalogo='"+catalogo+"' palabla='"+palabra+"' limite='"+limite+"'");
    	if ("Bennett".equalsIgnoreCase(catalogo)){
    		
    		log.info("Search in Bennett");
    		
    		ArrayList<Bennett> bList = new ArrayList<Bennett>(); //cambio List<Bennett> blist por ArrayList<Bennett>
	    	
			try{
			bList =Bennett.listadoStartBennett(palabra, limite);
			}catch(Exception e){
				e.printStackTrace();
			}
	
			log.finest("Return with "+ bList.size() + " resultados");
			
//			ArrayList<String> cities = new ArrayList<String>();
//			for(Bennett bennett: bList){
//				cities.add(bennett.getCity());
//			}
//			
//			out = toWordResultXML(cities);
			out = toXML2(bList);
    	}else{
    		if ("MedLine".equalsIgnoreCase(catalogo)){
    			
    			log.info("Search in MedLine");
    			
    			String ruta = context.getRealPath(context.getInitParameter("dirUploadFiles"))
 						+ File.separator + "medlinecompleta.xml";
    			
    			log.info("Database path: " + ruta);
    			HealthTopics hts = new HealthTopics();    			
    			hts = hts.inicio(new File(ruta));
    			
    			ArrayList<HealthTopic> listadoCompleto = new ArrayList<HealthTopic>();
    			ArrayList<String> palabrasBuscadas = new ArrayList<String>();
    			/*
    			 * Se buscan palabras que contenga el título del HealthTopic que comiencen con
    			 * la palabra pasada como parámetro, hasta el límite especificado.
    			 */
    			for(int i=0,encontrados=0; i < hts.getTotal() && encontrados < limite; i++){
    				HealthTopic topic = hts.getHealthTopic().get(i);
    				if(topic.getTitle().contains(palabra)){// Si el título contiene las letras pasadas...
    					System.out.println("----->> " + topic.getTitle());
    					if (topic.getTitle().indexOf(" ") == -1){
    						// Si el título solo tiene una palabra se añade
    						palabra_buscada = topic.getTitle();
    					}
    					else{
    						// Si el título tiene más de una palabra, se extrae la palabra que comienza
    						// con las letras pasadas como parámetro del método
    						
    						// Si la palabra se encuentra al final (no hay espacio detrás)
    						if (topic.getTitle().indexOf(" ", topic.getTitle().indexOf(palabra)) == -1){
    							palabra_buscada = topic.getTitle().substring(topic.getTitle().indexOf(palabra),
    							topic.getTitle().length());
    						}
    						// Si se encuentra en una zona intermedia se "lee" hasta el siguiente espacio
    						else{
    							palabra_buscada = topic.getTitle().substring(topic.getTitle().indexOf(palabra),
    	    							topic.getTitle().indexOf(" ", topic.getTitle().indexOf(palabra)));
    						}
    						
    					}
    					
    					//System.out.println("Palabra buscada: " + palabra_buscada);
    					
    					if (!palabrasBuscadas.contains(palabra_buscada)){
    						// Si la palabra no se encuentra todavía en el listado, se añade a las palabras
    						// buscadas.
    						palabrasBuscadas.add(palabra_buscada);
    						listadoCompleto.add(topic);
    						//System.out.println("Palabra añadida: " + palabra_buscada);
    						encontrados++;
    					}
    				}
    			}
    			
    			log.finest("Writing output with " + palabrasBuscadas.size() + " encontradas");
    			
//    			ArrayList<String> titles = new ArrayList<String>();
//    			for(HealthTopic bennett: listadoCompleto){
//    				titles.add(bennett.getTitle());
//    			}
//    			
//    			out = toWordResultXML(titles);
    			out = toXML3(palabrasBuscadas);
    		}
    	}
    	
    	return out;
    } 
}
