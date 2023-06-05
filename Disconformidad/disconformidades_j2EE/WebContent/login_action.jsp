<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="org.aprende.java.bbdd.Usuario"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
		String sUsuario = request.getParameter("txtUsuario");
		String sPwd = request.getParameter("txtPwd");

		if (sUsuario!=null && sPwd!=null){
			// ¡¡¡Falta acceso a la b.d. para consultar el login
			//Suponemos que se valida la entrada con la base de datos
			Usuario usu = new Usuario().validarUsuario(sUsuario,sPwd);

			if (usu!=null){
				out.println(usu.nombre());

				session.setAttribute("IdUsuario",usu.Id()); //VARIABLE DE SESION con la clave el usuario

				response.sendRedirect("disconformidades.jsp");
				out.println("creada la cookie y la variable de sesion");
			}else{
				out.println("El usuario no existe");
				response.sendRedirect("index.jsp");
				out.println("Usuario invalido");
			}
		}
	%>
</body>
</html>