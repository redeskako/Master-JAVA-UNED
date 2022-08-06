<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="org.basedatos.*, org.sesion.* "%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Acceso Usuario</title>
</head>
<body>
<%
String sUser = request.getParameter("usuario");
String sPassw = request.getParameter("password");

if (sUser != null && sPassw!=null){
	Usuario usu = new Usuario().validarUser(sUser, sPassw);

	if(usu != null){
		out.println(usu.NombreUsuario());
		Cookie miCookie = new Cookie("nick", sUser);
		miCookie.setMaxAge(15+60);
		response.addCookie(miCookie);
		session.setAttribute("iduser", usu.IdUsuario());
		response.sendRedirect("recursos.jsp");
		out.println("Se ha creado la cookie y la variable de sesion");
	}else{
		out.println("El usuario no existe");
		response.sendRedirect("index.jsp?estado=invalido");
		out.println("Usuario invalido");
	}
}
%>

<jsp:forward page="./Recursos?action=listadoRecursos" />
</body>
</html>