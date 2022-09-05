<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  errorPage="error.jsp"%><link rel="stylesheet" href="css/template.css" type="text/css" />
<%@ page import="es.rizos.beansClientes.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<link rel="stylesheet" href="css/modelo1.css" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="template/cabecera.html" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<c:if test="${(sessionScope.idioma==null)}">
	<jsp:forward page="Bienvenida.jsp?valido=-4">
		<jsp:param name="fecha" value="${fecha}" />
	</jsp:forward>
</c:if>
<c:if test="${(sessionScope.idioma== 'es_ES')}">
	<%@ include file="template/informacion.html"%>
	<fmt:setLocale value="es_ES" scope="session"/>
</c:if>

<c:if test="${(sessionScope.idioma== 'en_EN')}">
	<%@ include file="template/informacionen.html"%>
	<fmt:setLocale value="en_EN" scope="session" />
</c:if>

<fmt:setBundle basename="Etiquetas" var="lang" scope="session"/>

<div id="main">


<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Confirmaci&oacuten</title>
</head>
<br><br><br>
<body>

<center>
<text3><fmt:message key="citanorealizar" bundle="${lang}" />.</text3> <p>
<text3><fmt:message key="posiblescausas" bundle="${lang}"/></text3>
</br></br>

<text2><fmt:message key="continuar" bundle="${lang}"/>&nbsp &nbsp</text2>
<c:if test="${(sessionScope.AtributoTipoUsuario=='null')}">
	<fmt:message key="debesiniciarsesion" bundle="${lang}" />
<a href="Bienvenida.jsp"><img src="template/GLOBES_ORANGE.png" width=5% heigth=5% border="none"></a>
</c:if>
<c:set var="TipoUsuario" value="${sessionScope.AtributoTipoUsuario}" />
<c:if test="${(TipoUsuario =='admin')}">
	<a href="GestionaAdmin.jsp"><img src="template/GLOBES_ORANGE.png" width=5% heigth=5% border="none"></a>
</c:if>
<c:if test="${(TipoUsuario =='cliente')}">
	<a href="GestionaCli.jsp"><img src="template/GLOBES_ORANGE.png" width=5% heigth=5% border="none"></a>
</c:if>

</body>
</center>
</div>
<c:if test="${(sessionScope.idioma== 'es_ES')}">
	<%@ include file="template/cerrarsesion.html"%>
</c:if>

<c:if test="${(sessionScope.idioma== 'en_EN')}">
	<%@ include file="template/cerrarsesionen.html"%>
</c:if>

</html>