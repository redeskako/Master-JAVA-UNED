<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*"%>
<%@page import="java.io.*"%>
<%@page import="org.w3c.dom.*"%>
<%@page import="javax.xml.transform.*"%>
<%@page import="javax.xml.transform.stream.StreamResult"%>
<%@page import="javax.xml.transform.stream.StreamSource"%>
<%@page import="org.apache.xalan.processor.*"%>
<%@page import="javax.xml.transform.dom.DOMSource"%>
<%@page import="com.prueba.CD" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CD</title>
</head>
<body>
<%
   String xslFicheroName = new String("/cdmayor1995ordenadodes.xsl");
   try{
	 File xslFichero = new File(xslFicheroName);
	 StreamSource xslt = new StreamSource(xslFichero);
	 //Crear la factory
     TransformerFactory tFactory = new TransformerFactoryImpl();
	 //Crear un Transformador asociado al Source xslt de cd.xsl
     // Generar el motor de Transformación
     Transformer transformer = tFactory.newTransformer(xslt);
         	
     /********2 – Crear el objeto OrdenCliente para extraer sus datos***********/
     CD cd = new CD();

     /********3 – Extrayendo el xml usando la clase OrdenCliente***********/
     Document xmlCliente = cd.devuelveCD();
     DOMSource domSource = new DOMSource(xmlCliente);
     // Valida la transformación xsl con el dom domSource
     transformer.transform(domSource, new StreamResult(out));
   }catch(Exception e){
       out.write("Error en JSP:" + e.toString());
   }
%>
</body>
</html>