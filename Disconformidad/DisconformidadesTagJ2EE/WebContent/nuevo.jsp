<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp"%>
<%@ page import = "org.aprende.java.bbdd.*,  java.text.*"%>
<%@ taglib uri="WEB-INF/Servicios.tld" prefix="Servicios" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
int elestado= ((Integer)session.getAttribute("Estado")).intValue();
System.out.println("Vble Estado: "+ elestado);
   //if ((int)session.getAttribute("Estado")==1) {	
  if ( elestado==1 ) {
  //if (session.getAttribute("usuario")!=null){
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%

//String modo=(String)session.getAttribute("modo");
//Disconformidad dis=(Disconformidad)session.getAttribute("dis");
SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
String lafecha= sdf.format(new java.util.Date());
session.setAttribute("servicio",-1);
//out.println(lafecha.toString());
//out.println(sdf.format(dis.fecha()));
%>		
	

<title>Modo Insercion</title>
</head>
 <body background="images/IMGP1485.JPG"> 

	<h1  align="center"> Proyecto Alef J2EE</h1>
	<h2  align="center"> Modo Insercion </h2>
	<h5  align="center">(Estas conectado como usuario: <%out.println(((Usuario)session.getAttribute("usuario")).nombre());%>)</h5>
	
  	<form align="center" name="formmodifica" action="modosout.jsp" method="get">
		<table aling="center">
				
				
			<tr>
				<td>Devolucion: </td>
				<td>
					<input type="CHECKBOX" name="chkDevolucion"/>
				</td>
			</tr>
			<tr>
				<td>Fecha: </td>
				<td>
				 	<input type="TEXT" name="txtFecha" size="30" value=<%out.println(lafecha);%> /> 
				</td>
			</tr>	
			<tr>
				<td>Servicio: </td>
				<td>
	    			<SELECT NAME="Servicio">
						<Servicios:itera>    
	    					<% if (numservicio!=null) {%>
		    					<option value=<%= numservicio  %> ><%= nomservicio %></option>    					
		    				<% } %>
	    				</Servicios:itera>
					</SELECT> 
				</td>
			</tr>
			
			<tr>
				<td>Motivo: </td>
				<td>
					<input type="TEXT" name="txtMotivo" size="50"  >   
			   </td>
			</tr>	
			
			<tr>
				<td>Documentos: </td>
				<td>
					<input type="TEXT" name="txtDocumentos" size="50" >   
			   </td>
			</tr>				
					
			<tr>
				<td>Comentario: </td>
				<td>
					<input type="TEXT" name="txtComentario" size="50" >   
			   </td>
			</tr>				
		
			<tr></tr>
			<tr>
				<%session.setAttribute("modo","nuevo");%>
				
				<td><input type="submit" name="cmdOpcion" value="Aceptar"> </td> 
				<td><input type="submit" name="cmdOpcion" value="Cancelar"> </td> 
			</tr>
		</table>		
	</form>

</body>
</html>

<% }else
	 {
		 response.sendRedirect("index.jsp");
	 } 
%>