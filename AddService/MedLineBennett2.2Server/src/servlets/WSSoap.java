package servlets;

import java.io.File;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.ws.rs.core.Context;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

//import org.apache.axis2.context.MessageContext;
//import org.apache.axis2.transport.http.HTTPConstants;

import beans.BBDD;
import beans.Bennett;
import beans.HealthTopics;
import beans.HealthTopics.HealthTopic;
import beans.Search;




@WebService(targetNamespace = "http://servlets/", portName = "WSSoapPort", serviceName = "WSSoapService")

public class WSSoap  {
	@Resource
	private WebServiceContext context;
	@WebMethod(operationName = "SoapSearch", action = "urn:SoapSearch")
	@RequestWrapper(className = "servlets.jaxws.SoapSearch", localName = "SoapSearch", targetNamespace = "http://servlets/")
	@ResponseWrapper(className = "servlets.jaxws.SoapSearchResponse", localName = "SoapSearchResponse", targetNamespace = "http://servlets/")
	@WebResult(name = "return")
	
	
	public Search SoapSearch(@WebParam(name = "arg0") int pag, @WebParam(name = "arg1") String campo, @WebParam(name = "arg2") String palabra, @WebParam(name = "arg3") String catalogo ) {
		
		Search resultado = null;
		
		if (catalogo.equalsIgnoreCase("MedLine")){
			
			resultado = (new WSSoap()).MedLineSearch(pag, campo, palabra);
			
		}else if (catalogo.equalsIgnoreCase("Bennett")){
			
			resultado = (new WSSoap()).WSBennettSearch(pag, campo, palabra);
		}
		return resultado;
		
	}


	private Search MedLineSearch(int pag, String campo, String palabra ){ 
		
		HealthTopics hts = new HealthTopics();
 		ArrayList<HealthTopic> listadoCompleto = new ArrayList<HealthTopic>(), listadoPagina = new ArrayList<HealthTopic>();
 		
 		int regPorPag = 5, totReg, paginas;
 		String ruta;
 		
 		Search search = new Search();
 		
 		ArrayList<String> title = new ArrayList<String>(); 
 		ArrayList<String> uRL = new ArrayList<String>(); 
 		
 			 
 		// Sólo si se ha introducido alguna palabra iniciamos la búsqueda.
 		if (!palabra.equals("")) {
 			// Si no se había generado el listado completo anteriormente.
 			
 			//ruta = getServletContext().getRealPath(getServletContext().getInitParameter("dirUploadFiles"))
 			//		+ File.separator + "medlinecompleta.xml";
 			//ruta = "C:/medlinecompleta.xml";
 			
 			//introducimos la ruta
 			
 			//HttpServlet srv = (HttpServlet)MessageContext.getCurrentMessageContext().getProperty(HTTPConstants.MC_HTTP_SERVLET); 
 			 // ServletContext context = srv.getServletContext();
 			
 			  //ruta = System.getProperty("user.dir") + File.separator + "uploads" + File.separator + "medlinecompleta.xml";
 			ServletContext servletContext =
 				    (ServletContext) context.getMessageContext().get(MessageContext.SERVLET_CONTEXT);
 			ruta = servletContext.getRealPath(servletContext.getInitParameter("dirUploadFiles"))
					+ File.separator + "medlinecompleta.xml";
 			
 			  
 			  System.out.println("Esta es la ruta que coge: " + ruta);
 			
 			listadoCompleto = hts.BusquedaHealthTopicXML(ruta, campo, palabra);
 		}
 		
 		for (HealthTopic h: listadoCompleto){ //esto es un arrayList 
 			
 			uRL.add(h.getUrl());
 			//title.add(h.getId(), h.getTitle());
 			title.add(h.getTitle());
 						
 		}
 		
 		search.setTitle(title);
 		search.setURL(uRL);
 		
 		System.out.println("Se han encontrado tantos resultados" + listadoCompleto.size());
 		return search;
  	}




	private Search WSBennettSearch(int pag, String campo, String palabra ){
		
		BBDD bd = new BBDD();
		ArrayList<Bennett> listado = null;
		RequestDispatcher vista;
		int filasPorPagina = 12, filas, paginas;
		
		Search search = new Search();
		ArrayList<String> title = new ArrayList<String>();
		ArrayList<String> uRL = new ArrayList<String>();
	
		bd.abrirConexion();
		listado = bd.listadoBennett(campo, palabra, pag, filasPorPagina);
		filas = bd.getFilas();
		paginas = (int) Math.ceil(filas * 1.0 / filasPorPagina);
		bd.cerrarConexion();
	
		for (Bennett b:listado){
		
			title.add(b.getOrganization());
			uRL.add(b.getFullOrgURL());
		}
	
		search.setTitle(title);
		search.setURL(uRL);
	
		return search;
	}
	
	 
	

}
