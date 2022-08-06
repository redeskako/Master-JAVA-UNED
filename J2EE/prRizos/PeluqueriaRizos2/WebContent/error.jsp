<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isErrorPage="true"%>
<%@ page import="java.lang.*"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="template/cabecera.html"%>
<c:if test="${(sessionScope.idioma== 'es_ES')}">
	<%@ include file="template/informacion.html"%>
	<fmt:setLocale value="es_ES" scope="session" />
</c:if>

<c:if test="${(sessionScope.idioma== 'en_EN')}">
	<%@ include file="template/informacionen.html"%>
	<fmt:setLocale value="en_EN" scope="session" />
</c:if>

<fmt:setBundle basename="Etiquetas" var="lang" scope="session"/>

<br />
<br />
<br />
<br />
<br />
<br />
<h1>
<center><fmt:message key="resolviendoproblema" bundle="${lang}"/></center>
</h1>
<%
	String str =request.getParameter("error");
	if (str!=null){
%>
<br />
<h3>
<center><fmt:message key="detalleserror" bundle="${lang}"/>: <%=str%></center>
</h3>
<%
	}
%>
<c:if test="${(sessionScope.idioma== 'es_ES')}">
	<%@ include file="template/cerrarsesion.html"%>
</c:if>

<c:if test="${(sessionScope.idioma== 'en_EN')}">
	<%@ include file="template/cerrarsesionen.html"%>
</c:if>
</html>