<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*,java.util.*" %>
<%@ page import="javax.servlet.*,java.text.*" %>

<%@ page import="org.w3c.dom.*" %>
<%@ page import="javax.xml.parsers.DocumentBuilder" %>
<%@ page import="javax.xml.parsers.DocumentBuilderFactory" %>
<%
    DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
    DocumentBuilder db=dbf.newDocumentBuilder();
    Document mixml=db.parse("F:/Uned/JAVA/J2EE/Programas/WebContent/Biblioteca.xml");
    NodeList nodos=mixml.getElementsByTagName("libro");
    
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mi primera vez con jsp</title>
</head>
<body>
	Hello World!<br/>
	<%
		Date dNow = new Date();
	   	SimpleDateFormat ft = new SimpleDateFormat ("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
	   	out.print( "<h2 align=\"center\">" + ft.format(dNow) + "</h2>");
	%>
	<br/>
	<br/>
	<%
	for(int i=0;i<nodos.getLength();i++)
	{
  		NodeList titulos=mixml.getElementsByTagName("titulo");
  		Element tituloElemento=(Element)titulos.item(i);
  		String titulo_libro=tituloElemento.getChildNodes().item(0).getNodeValue();
  
  		NodeList autores=mixml.getElementsByTagName("autor");
  		Element autorElemento=(Element)autores.item(i);
  		String autor_libro=autorElemento.getChildNodes().item(0).getNodeValue();
  		
  		out.println("Título del libro :"+titulo_libro+"<br>");    
  		out.println("Autor del libro :"+autor_libro+"<br><br>");    
	}
	%>	
	
</body>
</html>