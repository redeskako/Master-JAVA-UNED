<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"  errorPage="error.jsp" import="beans.*" import="java.io.File"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
    // This is a scriptlet.  Notice that the "date"
    // variable we declare here is available in the
    // embedded expression later on.
    File f = new File(getServletContext().getRealPath("medlinecompleta.xml"));
    //File f = new File(getServletContext().getRealPath("medlinereducida.xml"));
	System.out.println("La ruta del servidor es " + getServletContext().getContextPath());
    System.out.println( "Evaluating date now" );
    java.util.Date date = new java.util.Date();
    HealthTopics ht = new HealthTopics();
    //File file = new File("uploads/medlinereducida.xml");
    HealthTopics dos = ht.inicio(f);
    System.out.println("La fecha generada es " + dos.getDateGenerated());
    //String uno = ht.getTotal().toString();
    
%>

El número total de elementos es <%= dos.getTotal().toString() %>
<br/>
<% for(int i=0; i<10; i++){ %>
El nombre del healthtopic es <%= dos.getHealthTopic().get(i).getTitle().toString() %>
<br/>
y su resumen es <%= dos.getHealthTopic().get(i).getFullSummary().toString() %>
<% } %>
<br/>
</body>
</html>