<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp"%>
<%@ page import = "org.aprende.java.bbdd.*, java.util.Iterator, java.text.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>LISTADO DE DISCONFORMIDADES</title>
</head>
<body>

	<h2  align="center"> LISTADO DE DISCONFORMIDADES</h2>
	
	
	<table  align="center" border="1">
		<tr  align="center">
			<td>NUMERO</td>
			<td>FECHA</td>
			<td>DOCUMIENTOS</td>
			<td>MOTIVO</td>
			<td>COMENTARIOS</td>
			<td>USUARIO</td>
			<td>SERVICIO</td>
		</tr>
		
		
		<%! String sNull(String valor){
				return (valor!=null ? valor: "");				
			}; 
		%>
		
		
		
	
		<% 
		SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		String lafecha= sdf.format(new java.util.Date());
		out.println(lafecha.toString());
		
		Disconformidad unaDisconformidad = new Disconformidad(1,new java.util.Date(),"Documentos",1,2,true,"Motivo","Comentario"); 
		
		//Disconformidades treeSetDisconformidades = new Disconformidades().listadoDisconformidades();
		//Iterator it= treeSetDisconformidades.iterator();		
		//while (it.hasNext()){
		//	unaDisconformidad = (Disconformidad)it.next();
	
		%>
			<tr>
				<td align="center"> <%out.println(unaDisconformidad.numero());%> </td>
				<td align="center"> <%out.println(unaDisconformidad.fecha());%> </td>
				<td align="center"> <%out.println(sNull(unaDisconformidad.docs()));%> </td>
				<td align="center"> <%out.println(sNull(unaDisconformidad.motivo()));%> </td>
				<td align="center"> <%out.println(sNull(unaDisconformidad.comentario()));%> </td>
				
			
				
			<!--	//	Usuario usu = new Usuarios().getUsuario(unaDisconformidad.usuario()); -->
				
				
																							
			
			<!-- 	<td align="center"> <%out.println(unaDisconformidad.servicio());%> </td> -->
			</tr>
		
		
	</table>
	
	<%session.setAttribute("modo","editar");%>
	<% session.setAttribute("dis",unaDisconformidad); %>
	
	<a href="modificaciones.jsp">editar </a>
	<a href="modificaciones.jsp">eliminar</a>
	<a href="modificaciones.jsp">nuevo </a>

</body>
</html>