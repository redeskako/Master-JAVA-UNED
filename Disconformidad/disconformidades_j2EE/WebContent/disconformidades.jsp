<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "org.aprende.java.bbdd.*, java.util.Iterator, java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>LISTADO DE DISCONFORMIDADES</title>
</head>
<body>
<%
	String usuario = (String) session.getAttribute("IdUsuario");

	if (usuario != null){
%>
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

		Disconformidad unaDisconformidad = new Disconformidad();
		ArrayList<Disconformidad> treeSetDisconformidades = new Disconformidad().listadoDisconformidades();
		Iterator it= treeSetDisconformidades.iterator();
		while (it.hasNext()){
			unaDisconformidad = (Disconformidad)it.next();
		%>
			<tr>
				<td align="center"> <%out.println(unaDisconformidad.numero());%> </td>
				<td align="center"> <%out.println(unaDisconformidad.fecha());%> </td>
				<td align="center"> <%out.println(sNull(unaDisconformidad.docs()));%> </td>
				<td align="center"> <%out.println(sNull(unaDisconformidad.motivo()));%> </td>
				<td align="center"> <%out.println(sNull(unaDisconformidad.comentario()));%> </td>
				<%
					String  nombreusu = new Usuario().getNombreUsuario(unaDisconformidad.usuario()); //devuelve el nombre de los usuarios
				%>

				<td align="center"> <%out.println(nombreusu);%> </td>
				<td align="center"> <%out.println(unaDisconformidad.servicio());%> </td>
			</tr>
		<%
		}
		%>
		<div align="right">
			<a href="./cerrarSesion" target="_self">Cerrar sesión</a>
		</div>
	</table>
<%
	}else{
		response.sendRedirect("./index.jsp?estado=ilegal");
	}
%>
</body>
</html>