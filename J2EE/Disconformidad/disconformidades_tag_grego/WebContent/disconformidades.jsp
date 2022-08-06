<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  %>
<%@ taglib uri="WEB-INF/disconformidades.tld" prefix="disconformidad" %>
<%@ page import = "org.aprende.java.bbdd.*, java.util.Iterator, java.util.*"%>
<%@ page import = "org.aprende.java.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>LISTADO DE DISCONFORMIDADES</title>
</head>
<body>

   <% 
   
   if (session.getAttribute("Estado")==null) {
	   response.sendRedirect("index.jsp");
 
   }else if ( ((Integer) session.getAttribute("Estado")).intValue()==0){
	   session.invalidate();
	   response.sendRedirect("index.jsp");
   }else if (((Integer) session.getAttribute("Estado")).intValue()==1 ){
	  	if ((request.getParameter("salir")!=null) && (request.getParameter("salir").equals("0"))){
		   		session.invalidate();
		   		response.sendRedirect("index.jsp");
	   	}else{
%>
<form method=post action="nuevo.jsp">
	
	
	<h2  align="center"> LISTADO DE DISCONFORMIDADES</h2>
	
	
	<table  align="center" border="1">

		<tr  align="center">
			<th>NUMERO</th>
			<th>FECHA</th>
			<th>DOCUMIENTOS</th>
			<th>MOTIVO</th>
			<th>COMENTARIOS</th>
			<th>USUARIO</th>
			<th>SERVICIO</th>
			<%if (((Usuario)session.getAttribute("usuario")).getGestion()== 1) { //administrador
			%>
			
			<th colspan="15%"> ACCION</th>
				
		
			<%
			}
			%>
		</tr>

		<disconformidad:crear/>
		<disconformidad:itera/> 
	
	</table>
	<br/>
	<center><input type="submit" name="cmdNuevo" value="Nuevo"/></center>
	<br/>
	<a href="disconformidades.jsp?salir=0">Salir</a>
</form>
<%	}
}
%>
</body>
</html>

