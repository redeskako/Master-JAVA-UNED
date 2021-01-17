<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage="error.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<c:set var="estado" scope="page" value="${param['estado']}"/>

<t:genericpage>
    <jsp:attribute name="header">
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
    <jsp:body>
        <form name="formLogin" action="./Login_action" method="get">  
		<table align="center">
			<tr>
				<td>Usuario</td>
				<td><input type="TEXT" name="txtUsuario" size="10"> </td>
			</tr>			
			<tr>
				<td>Password</td>
				<td><input type="PASSWORD" name="txtPwd" size="10"> </td>
			</tr>
			<tr>
				<td><input type="submit" name="cmdAceptar" value="Aceptar"> </td>
				<td><input type="reset" name="cmdLimpiar" value="Limpiar"> </td>
			</tr>
		</table>		
	</form>
	<c:choose>
         <c:when test="${pageScope.estado eq 'invalido'}">
            <center><h1>Usuario invalido</h1></center>
         </c:when>
         <c:when test="${pageScope.estado eq 'salir'}">
            <center><h1>Has salido de la sesión</h1></center>
         </c:when>
         <c:when test="${pageScope.estado eq 'ilegal'}">
            <center><h1>Acceso ilegal, debe antes entrar en sesión</h1></center>
         </c:when>
     </c:choose>
    </jsp:body>
</t:genericpage>
<!--  <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<div id="cabecera">
		<img src="images/medline_logo.jpg"/>
	</div>-->
	<!-- lugar donde envia -->
  	<!--  <form name="formLogin" action="./Login_action" method="get">  
		<table align="center">
			<tr>
				<td>Usuario</td>
				<td><input type="TEXT" name="txtUsuario" size="10"> </td>
			</tr>			
			<tr>
				<td>Password</td>
				<td><input type="PASSWORD" name="txtPwd" size="10"> </td>
			</tr>
			<tr>
				<td><input type="submit" name="cmdAceptar" value="Aceptar"> </td>
				<td><input type="reset" name="cmdLimpiar" value="Limpiar"> </td>
			</tr>
		</table>		
	</form>
	<c:choose>
         <c:when test="${pageScope.estado eq 'invalido'}">
            <center><h1>Usuario invalido</h1></center>
         </c:when>
         <c:when test="${pageScope.estado eq 'salir'}">
            <center><h1>Has salido de la sesión</h1></center>
         </c:when>
         <c:when test="${pageScope.estado eq 'ilegal'}">
            <center><h1>Acceso ilegal, debe antes entrar en sesión</h1></center>
         </c:when>
     </c:choose>  	
</body>
</html>-->