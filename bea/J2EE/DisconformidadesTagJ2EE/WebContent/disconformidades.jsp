<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp" %>
<%@ taglib uri="WEB-INF/disconformidades.tld" prefix="disconformidad" %>
<%@ page import = "org.aprende.java.bbdd.*, java.util.Iterator, java.util.*"%>
<%@ page import = "org.aprende.java.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>LISTADO DE DISCONFORMIDADES</title>
</head>
 <body background="images/IMGP1485.JPG">

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
	   		
	   		int pag=1;
	   		if (request.getParameter("salir")!=null){
				pag=Integer.parseInt((request.getParameter("salir")).trim());
			}
			session.setAttribute("pag",pag);
			
			String orden="numero";
			if (request.getParameter("orden")!=null){
				orden=(request.getParameter("orden"));
			}
			session.setAttribute("orden",orden);			
			
%>



<form method=post action="nuevo.jsp">
	
	
	<h2  align="center"> LISTADO DE DISCONFORMIDADES</h2>
	
<%--	<disconformidad:inicializa/>  --%> 

	
	<table  align="center" border="1">

		<tr  align="center">
			<th><a href="disconformidades.jsp?salir= <%= pag %> &orden=numero">Numero </a></th>
			<th><a href="disconformidades.jsp?salir= <%= pag %> &orden=fecha">Fecha </a></th>
			<th><a href="disconformidades.jsp?salir= <%= pag %> &orden=docs">Documentos </a></th>
			<th><a href="disconformidades.jsp?salir= <%= pag %> &orden=motivo">Motivo </a></th>
			<th><a href="disconformidades.jsp?salir= <%= pag %> &orden=comentario">Comentarios </a></th>
			<th><a href="disconformidades.jsp?salir= <%= pag %> &orden=idusuario">Usuario </a></th>
			<th><a href="disconformidades.jsp?salir= <%= pag %> &orden=servicio">Servicio </a></th>
			<% 	if (((Usuario)session.getAttribute("usuario")).getGestion()== 1) { 	%>
					<th colspan="15%"> ACCION</th>
			<% } %>
		</tr>
		
	
		<disconformidad:itera>
		
		  <% if (numero!=null) { %>
			<tr> 
				<td align="center">  <%= numero %> </td>
				<td align="center">  <%= fecha %> </td>
				<td align="center">  <%= docs %> </td>
				<td align="center">  <%= motivo %> </td>
				<td align="center">  <%= comentario %> </td>
				<td align="center">  <%= nombreusuario %> </td>
				<td align="center">  <%= nombreservicio %> </td>

				
			<% 	if (((Usuario)session.getAttribute("usuario")).getGestion()== 1) { 	%>
				<td><a href="modifica.jsp?id= <%= numero %> ">Modificar</a></td>
				<td><a href="elimina.jsp?id=  <%= numero %> ">Eliminar</a></td>
			<% } %>
			</tr>
		  <% } %>	
		</disconformidad:itera> 
		
	</table>
	<br/>
	
	<table  align="center" border="0">

		<tr  align="center">
			<th><input type="submit" name="cmdNuevo" value="Nuevo"/></th>
			<th> &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp  </th>
			<th><a href="disconformidades.jsp?salir=0">Salir</a></th>
			<th> &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp  </th>
			
			<disconformidad:iterapaginas> 
			<th>
			<% if (pag != Integer.parseInt(contpag)) {%>
				<a href="disconformidades.jsp?salir= <%= contpag %> &orden= <%=orden%> "> <% out.println(contpag); %> </a>
			<% }else{ out.println(contpag); } %>	
			</th>
			</disconformidad:iterapaginas> 						
				
		</tr>
	</table>
	
</form>
<%	}
}
%>
</body>
</html>

