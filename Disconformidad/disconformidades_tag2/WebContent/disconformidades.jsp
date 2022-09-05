<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import = "org.aprende.java.bbdd.*, java.util.Iterator, java.util.*"%>
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
			<th>NUMERO</th>
			<th>FECHA</th>
			<th>DOCUMIENTOS</th>
			<th>MOTIVO</th>
			<th>COMENTARIOS</th>
			<th>USUARIO</th>
			<th>SERVICIO</th>
			<%if (((Usuario)session.getAttribute("usuario")).getGestion()== 1) { //administrador
			%>
				<th>MODIFICAR</th>
				<th>ELIMINAR</th>	
			
			<% 
				}
			%>
		</tr>
		
		
		<%! String sNull(String valor){
				return (valor!=null ? valor: "");				
			}; 
		%>
	
		<jsp:useBean id="elcontrolador" class="org.aprende.java.Controlador"></jsp:useBean>
		
		<%  
			
		Iterator it= elcontrolador.listarDisconformidades(((Usuario)session.getAttribute("usuario")));
		while (it.hasNext()){
			Disconformidad unaDisconformidad = (Disconformidad)it.next();
		
		%>
		
		<tr>
				<td align="center"> <%out.println(unaDisconformidad.numero());%> </td>
				<td align="center"> <%out.println(unaDisconformidad.fecha());%> </td>
				<td align="center"> <%out.println(sNull(unaDisconformidad.docs()));%> </td>
				<td align="center"> <%out.println(sNull(unaDisconformidad.motivo()));%> </td>
				<td align="center"> <%out.println(sNull(unaDisconformidad.comentario()));%> </td>
				
				<%
					//devuelve el nombre del usuario
					String nombreusuario = elcontrolador.getUsuario(unaDisconformidad.usuario()).nombre();
				%>
																							
				<td align="center"> <%out.println(nombreusuario);%> </td>
				<%
					//devuelve el nombre del servicio
					String nombreservicio = elcontrolador.getServicio(unaDisconformidad.servicio()).nombre();
				%>
				<td align="center"> <%out.println(nombreservicio);%> </td>
				
				
				<%
				 if (((Usuario)session.getAttribute("usuario")).getGestion() ==1) { //administrador
					 // <td><IMG SRC="/imagenes/imagen1.gif" ALT="Modificar" ></td> 
				%>
					<td><A HREF="modificar.jsp?id=<%=unaDisconformidad.numero()%>">Modificar</A></td>
					<td><A HREF="eliminar.jsp?id=<%=unaDisconformidad.numero()%>" >Eliminar</A></td>					
				<%
				}
				%>
			</tr>			
		<%
		}
		%>
		
	</table>
	<BR>
	
	<% 
	if (((Usuario)session.getAttribute("usuario")).getGestion() == 1) { //administrador
	%>
	<center>
		<form name="formNuevo" action="nuevo.jsp" method="get">
			<input type="submit" name="cmdNuevo" value="Nuevo">
		</form>
	</center>
	<% 
	}
		
 
	%>

</body>
</html>