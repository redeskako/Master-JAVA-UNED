<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><link rel="stylesheet" href="css/template.css" type="text/css" />
<%@ page import="es.rizos.beansClientes.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<link rel="stylesheet" href="css/modelo1.css" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="template/cabecera.html" %>
<%@ include file="template/informacion.html" %> 
<div id="main">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Confirmaci&oacuten</title>
</head>
<body>

<text1>La cita no se pudo realizar.</text1> <p>
<text1>Posibles causas: Jornada Completa o Turno asignado</text1>
</br></br>
<text2>Continuar &nbsp &nbsp</text2>
<%String tipoUsuario= (String) session.getAttribute("AtributoTipoUsuario"); 
 
if(tipoUsuario.equals("admin")){
 %>
<a href="GestionaAdmin.jsp"><img src="template/GLOBES_ORANGE.png" width=5% heigth=5%></a>
<%} else if (tipoUsuario.equals("cliente")) {
	
%>
<a href="GestionaCliente.jsp"><img src="template/GLOBES_ORANGE.png" width=5% heigth=5%></a>
<%}else {
%>
Debes de iniciar sesion
<a href="Bienvenida.jsp"><img src="template/GLOBES_ORANGE.png" width=5% heigth=5%></a>
<%} %>
</body>
</div>
<%@ include file="template/cerrarsesion.html" %>

</html>