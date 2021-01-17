<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage="error.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<!-- 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<link rel="stylesheet" type="text/css" href="estilos.css" />
<title>Detalle de: ${requestScope.ht.title}</title>
</head>

<body>
	<%@ include file="verifyAccess.jsp" %>
	
	<a id="cerrarSesion" href="./cerrarSesion">Cerrar sesi&oacuten</a>
	<a id="gotoMenu" href="./menu.jsp">Ir a Men&uacute</a>
	
	<div id="top">
		<img src="./images/medline_logo.jpg"/>
	</div>
	-->
	<t:genericpage_menu>
    <jsp:attribute name="header">
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
    <jsp:body>
	
	<a id="backToList" href="./${search}Search.do?pag=${requestScope.pag}&txtCampo=${requestScope.campo}&txtPalabra=${requestScope.palabra}">&laquo-- Volver a la Lista</a>
	
	<div id="clear">
	<hr>
	</div>
	
	<div id="content">
		<h2>${requestScope.ht.title}</h2>
		<table>
			<tr><th>Descripci&oacuten</th></tr>
			<tr><td>${requestScope.ht.fullSummary}</td></tr>
		</table>
		<c:if test="${!empty requestScope.ht.alsoCalled}">
			<br/>
			<table>
				<tr><th>Otras denominaciones</th></tr>
				<c:forEach var="ac" items="${requestScope.ht.alsoCalled}">
					<tr><td>${pageScope.ac}</td></tr>
				</c:forEach>
			</table>
		</c:if>
		<c:if test="${!empty requestScope.ht.relatedTopic}">
			<br/>
			<table>
				<tr><th>Temas relacionados</th></tr>
				<c:forEach var="rt" items="${requestScope.ht.relatedTopic}">
					<tr><td>${pageScope.rt.value}</td></tr>
				</c:forEach>
			</table>
		</c:if>
		<c:if test="${!empty requestScope.orgList}">
			<br/>
			<table>
				<tr>
					<th>Organizaciones</th>
				</tr>
				<c:forEach var="org" items="${requestScope.orgList}">
					<tr>
						<td>${pageScope.org}</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
		<c:if test="${!empty requestScope.ht.site}">
			<br/>
			<table>
				<tr>
					<th>Sites</th>
				</tr>
				<c:forEach var="st" items="${requestScope.ht.site}">
					<tr>
						<td><a href="${pageScope.st.url}" target="_blank">${pageScope.st.title}</a></td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	</div>
	    </jsp:body>
</t:genericpage_menu>
<!-- 
</body>
</html>
-->