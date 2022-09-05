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


// String modo=(String)session.getAttribute("modo");
//Disconformidad dis=(Disconformidad)session.getAttribute("dis");
SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
//String lafecha= sdf.format(new java.util.Date());
//out.println(lafecha.toString());
//out.println(sdf.format(dis.fecha()));

	String id=request.getParameter("id");
%>
		<jsp:useBean id="elcontrolador"  class="org.aprende.java.Controlador"></jsp:useBean>
<% 
	Disconformidad dis=elcontrolador.getDisconformidad(Integer.parseInt(id));

%>		
	   
<title>Modo Eliminacion</title>
</head>
<body>
	<h1  align="center"> Proyecto Alef J2EE</h1>
	<h2  align="center"> Modo Eliminacion	</h2>
	<h5  align="center">(Estas conectado como usuario: <%out.println(((Usuario)session.getAttribute("usuario")).nombre());%>)</h5>
	
  	<form align="center" name="formmodificar" action="modosout.jsp" method="get">
		<table aling="center">
			<tr>
				<td>Numero: </td>
				<td><%out.println(dis.numero());%></td>
 			</tr>			
				
			<tr>
				<td>Devolucion: </td>
				<td>
				<% 	out.println(dis.devolucion());  %> 

				</td>
			</tr>
			<tr>
				<td>Fecha: </td>
				<td>
				<% out.println(sdf.format(dis.fecha()));  %>
			   </td>
			</tr>	
			<tr>
				<td>Servicio: </td>
				<td>
					<% 
						//Servicio s = new Servicio();
						//s.getServicio(dis.servicio());
						//out.println(s.nombre());
						
						out.println(new Servicio().getServicio(dis.servicio()).nombre());
						
						
				    %> 
				</td>
			</tr>
			
			<tr>
				<td>Motivo: </td>
				<td>
				<% 	out.println(dis.motivo());     %> 
			   </td>
			</tr>	
			
			<tr>
				<td>Documentos: </td>
				<td>
					<% out.println(dis.docs());  %> 
			   </td>
			</tr>				
					
			<tr>
				<td>Comentario: </td>
				<td>
				<%  out.println(dis.comentario());     %> 
			   </td>
			</tr>				
		
			<tr></tr>
			<tr>
				<%
				session.setAttribute("modo","eliminar");
				session.setAttribute("dis",dis);
				%>
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