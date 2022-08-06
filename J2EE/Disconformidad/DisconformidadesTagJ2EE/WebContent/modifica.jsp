<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp"%>
<%@ page import = "org.aprende.java.bbdd.*,  java.text.*"%>
<%@ taglib uri="WEB-INF/Servicios.tld" prefix="Servicios" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% 
	if (session.getAttribute("Estado")==null) {
		   response.sendRedirect("index.jsp");
	}else if ( ((Integer) session.getAttribute("Estado")).intValue()==0){
		   session.invalidate();
		   response.sendRedirect("index.jsp");
	}else if (((Integer) session.getAttribute("Estado")).intValue()==1 ){
 
%>
<html>
<head>
 <body background="images/IMGP1485.JPG"> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<% 	String id=request.getParameter("id"); %>
		<jsp:useBean id="elcontrolador"  class="org.aprende.java.Controlador"></jsp:useBean>
<% 
	Disconformidad dis=elcontrolador.getDisconformidad(Integer.parseInt(id.trim()));
	session.setAttribute("servicio",dis.servicio());
	SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
	
	//String lafecha= sdf.format(new java.util.Date());
	//out.println(lafecha.toString());
	//out.println(sdf.format(dis.fecha()));
%>		
	

<title>Modo Edicion</title>
</head>
<body>
	<h1  align="center"> Proyecto Alef J2EE</h1>
	<h2  align="center"> Modo Edicion </h2>
	<h5  align="center">(Estas conectado como usuario: <%out.println(((Usuario)session.getAttribute("usuario")).nombre());%>)</h5>
	
  	<form align="center" name="formmodifica" action="modosout.jsp" method="get">
		<table aling="center">
			<tr>
				<td>Numero: </td>
				<td><%=dis.numero()%></td>
 			</tr>			
		
		
			<tr>
				<td>Devolucion: </td>
				<td>
					<input type="CHECKBOX" name="chkDevolucion" 
					<%if ( dis.devolucion()) {%> CHECKED> <% } %>/>
				</td>
			</tr>
			<tr>
				<td>Fecha: </td>
				<td>
					<input type="TEXT" name="txtFecha" size="30" value=<%=sdf.format(dis.fecha())%> />   
				</td>
			</tr>	
			<tr>
				<td>Servicio: </td>
				<td>
					<SELECT NAME="Servicio">
					<!--	<Servicios:crear/> -->
	    				<Servicios:itera>    
	    					<% if (numservicio!=null) {%>
		    					<option <% if (seleccionado){ %> selected <% } %>value=<%= numservicio  %> ><%= nomservicio %></option>    					
		    				<% } %>
	    				</Servicios:itera>
					</SELECT> 
				</td>
			</tr>
			
			<tr>
				<td>Motivo: </td>
				<td>
					<input type="TEXT" name="txtMotivo" size="50"  value=<%=dis.motivo()%>   >   
			   </td>
			</tr>	
			
			<tr>
				<td>Documentos: </td>
				<td>
					<input type="TEXT" name="txtDocumentos" size="50" value=<%=dis.docs()%>   >   
			   </td>
			</tr>				
					
			<tr>
				<td>Comentario: </td>
				<td>
					<input type="TEXT" name="txtComentario" size="50" value=<%=dis.comentario()%>   >   
			   </td>
			</tr>				
		
			<tr></tr>
			<tr>
				<%
				 session.setAttribute("modo","editar");
				 session.setAttribute("dis",dis);
				%>
				<td><input type="submit" name="cmdOpcion" value="Aceptar"> </td> 
				<td><input type="submit" name="cmdOpcion" value="Cancelar"> </td> 
			</tr>
		</table>		
	</form>

</body>
</html>
<% 
}
%>