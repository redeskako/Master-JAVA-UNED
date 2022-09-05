<%@page import="es.uned.clasesCliente.UsuarioCliente"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="es.uned.clasesCliente.*, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<%
	UsuarioCliente u;
	ArrayList<UsuarioCliente> array = (ArrayList<UsuarioCliente>) request.getAttribute("uListado");
	Iterator<UsuarioCliente> it = array.iterator();
	%>

	<table>
		<tr>
			<th scope="col">IdUsuario</th>
			<th scope="col">Nombre Usuario</th>
			<th scope="col">Nombre</th>
			<th scope="col">Apellidos</th>
			<th scope="col">Rol</th>
		</tr>
		
		<%
		// Se dibuja la tabla mientras exitan valores en la base de datos
		while (it.hasNext()) {
			u = (UsuarioCliente) it.next();
		%>
		<tr>
			<td><%=u.getIdUsuario()%></td>
			<td><%=u.getNombreUsuario()%></td>
			<td><%=u.getNombre()%></td>
			<td><%=u.getApellidos()%></td>
			<td><%=u.getRol()%></td>
		</tr>
		<%
		}
		%>	
	</table>
	
</body>
</html>