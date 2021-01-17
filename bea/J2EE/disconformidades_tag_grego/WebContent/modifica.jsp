<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp"%>
<%@ page import = "org.aprende.java.bbdd.*,  java.text.*"%>
<%@ taglib uri="WEB-INF/Servicios.tld" prefix="Servicios" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% //int elestado=(int)session.getAttribute("Estado");
   //if ((int)session.getAttribute("Estado")==1) {	
  // if ( elestado==1 ) {
  if (session.getAttribute("usuario")!=null){
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%

//String modo=(String)session.getAttribute("modo");
//Disconformidad dis=(Disconformidad)session.getAttribute("dis");


	String id=request.getParameter("id");
%>
		<jsp:useBean id="elcontrolador"  class="org.aprende.java.Controlador"></jsp:useBean>
<% 
	Disconformidad dis=elcontrolador.getDisconformidad(Integer.parseInt(id));

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
				<td><%out.println(dis.numero());%></td>
 			</tr>			
		
		
			<tr>
				<td>Devolucion: </td>
				<td>
					<input type="CHECKBOX" name="chkDevolucion" 
					<%if ( dis.devolucion()) {%> CHECKED> <% } %>   
				</td>
			</tr>
			<tr>
				<td>Fecha: </td>
				<td>
					<input type="TEXT" name="txtFecha" size="30" value=<%out.println(sdf.format(dis.fecha()));%> >   
				</td>
			</tr>	
			<tr>
				<td>Servicio: </td>
				<td>
	    				<SELECT NAME="Servicio">
			<!--		<OPTION VALUE=1> Servicio 1 -->
			<!--		<OPTION SELECTED VALUE=2> Servicio 2 -->
			<!--		<OPTION VALUE=3> Servicio 3</OPTION> -->
						<Servicios:itera/>    
						
						<% 
							Servicios servis = new Servicios();
							servis=servis.AllServicios();
							for (int i=0; i<servis.size();i++) {
								if (dis.servicio()==servis.get(i).Id()){
								%> <OPTION SELECTED VALUE=<% servis.get(i).Id(); %> > <% servis.get(i).nombre(); %> </OPTION>
								<%
								}else
								{
								%> <OPTION VALUE=<% servis.get(i).Id(); %> > <% servis.get(i).nombre(); %> </OPTION>
								<%}
							}
							
														
						 %>

					</SELECT> 
				</td>
			</tr>
			
			<tr>
				<td>Motivo: </td>
				<td>
					<input type="TEXT" name="txtMotivo" size="50"  value=<%out.println(dis.motivo());%>   >   
			   </td>
			</tr>	
			
			<tr>
				<td>Documentos: </td>
				<td>
					<input type="TEXT" name="txtDocumentos" size="50" value=<%out.println(dis.docs());%>   >   
				 
			   </td>
			</tr>				
					
			<tr>
				<td>Comentario: </td>
				<td>
					<input type="TEXT" name="txtComentario" size="50" value=<%out.println(dis.comentario());%>   >   
				 
			   </td>
			</tr>				
		
			<tr></tr>
			<tr>
				<%session.setAttribute("modo","editar");%>
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