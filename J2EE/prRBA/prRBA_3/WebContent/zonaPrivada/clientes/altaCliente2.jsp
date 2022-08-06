<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="/error.jsp"%>
<%@ page import="java.lang.*" %>
<%@ page import="rba.clientes.AccionCliente" %>
<%@ page import="rba.bbdd.*" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%String path = request.getContextPath();%>
<!-- Copyright 2005 Macromedia, Inc. All rights reserved. -->
<title>ALTA CLIENTE</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="<%=path%>/css/mm_restaurant1.css" type="text/css" />


</head>

<%
String idioma = (String) session.getAttribute("idioma");
String nombre;
String apellido1;
String apellido2;
String telefono;
String correo;
String pass;
String cp;

try{
	 nombre = (String) request.getParameter("nombre");
	 if (nombre == null) {
		 nombre=" ";
		 }	 
}catch(Exception e){
	 nombre = " ";
}

try{
	 apellido1 = (String) request.getParameter("apellido1");
	 if (apellido1 == null) {
		 apellido1=" ";
		 }
}catch(Exception e){
	 apellido1 = " ";
}

try{
	 apellido2 = (String) request.getParameter("apellido2");
	 if (apellido2 == null) {
		 apellido2=" ";
		 }
}catch(Exception e){
	 apellido2 = " ";
}

try{
	 telefono = (String) request.getParameter("telefono");
	 if (telefono == null) {
		 telefono=" ";
		 }
}catch(Exception e){
	 telefono = " ";
}

try{
	correo = (String) request.getParameter("correo");
	 if (correo == null) {
		 correo=" ";
		 }
}catch(Exception e){
	 correo = " ";
}

try{
	 pass = (String) request.getParameter("pass");
	 if (pass == null) {
		 pass=" ";
		 }
}catch(Exception e){
	 pass = " ";
}

try{
	 cp = (String) request.getParameter("cp");
	 if (cp == null) {
		 cp=" ";
		 }
}catch(Exception e){
	 cp = " ";
}



%>

<fmt:setLocale value="<%=idioma%>"/>
<fmt:setBundle basename="RbaEtiquetas" />

<body bgcolor="#0066cc">

<table width="100%" height="101" border="0" cellpadding="0" cellspacing="0">
<tr bgcolor="#99ccff">
		<td width="15" nowrap="nowrap" >
			<img src="<%=path%>/css/mm_spacer.gif" alt="" width="15" height="1" border="0" />
		</td>
		<td height="60" colspan="3" class="logo" nowrap="nowrap"><br />
			<fmt:message key="membrete" bundle="${base}" /><span class="tagline">| <fmt:message key="membrete_leyenda" bundle="${base}" /></span>
		</td>
		<td width="40">&nbsp;</td>
		<td width="100%">&nbsp;</td>
	</tr>

		<tr bgcolor="#003399">
		<td width="15" nowrap="nowrap">&nbsp;</td>
		<td height="36" colspan="3" id="navigation" nowrap="nowrap" class="navText">
			<a href="<%=path%>/zonaPrivada/menu.jsp"><fmt:message key="menu_Inicio" bundle="${base}" /></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="<%=path%>/zonaPrivada/clientes/cliente.jsp"><fmt:message key="menu_Clientes" bundle="${base}"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      		<a href="<%=path%>/zonaPrivada/mesas/mesa.jsp"><fmt:message key="menu_Mesas" bundle="${base}"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      		<a href="<%=path%>/zonaPrivada/reservas/reserva.jsp"><fmt:message key="menu_Reservas" bundle="${base}"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="<%=path %>/index.jsp?valido=0"><fmt:message key="menu_CerrarS" bundle="${base}"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      	</td>
	 
	</tr>
	
	<tr bgcolor="#ffffff">
		<td colspan="6">
			<img src="<%=path%>/css/mm_spacer.gif" alt="" width="1" height="1" border="0" />
		</td>
	</tr>


	<form action="<%=path%>/AccionCliente?accion=4" method="post">

	<table width="200" border="1" align="center">
          <caption>
            <fmt:message key="FormularioAltaCliente" bundle="${base}" />
          </caption>
          <tr>
            <th scope="col"><fmt:message key="Campos" bundle="${base}" /></th>
            <th scope="col"><fmt:message key="Valores" bundle="${base}" /></th>
          </tr>

          <tr>
            <th scope="row"><fmt:message key="Nombre" bundle="${base}" />: </th>
            <td><input name="txtNombreCliente" type="text" value="<%=nombre %>"><label>* <fmt:message key="datoimprescindible" bundle="${base}" /></label></td>
          </tr>
          <tr>
            <th scope="row"><fmt:message key="Apellido1" bundle="${base}" />: </th>
            <td><input name="txtApellido1Cliente" type="text" value="<%=apellido1 %>"></td>
          </tr>
          <tr>
            <th scope="row"><fmt:message key="Apellido2" bundle="${base}" />: </th>
            <td><input name="txtApellido2Cliente" type="text"  value="<%=apellido2 %>"></td>
          </tr>
          <tr>
            <th scope="row"><fmt:message key="Telefono" bundle="${base}"  />: </th>
            <td><input name="txtTelefono" type="text" value="<%=telefono %>"></td>
          </tr>
          <tr>
            <th scope="row"><fmt:message key="Correo" bundle="${base}" />: </th>
            <td><input name="txtCorreo" type="text" value="<%=correo %>"><label>* <fmt:message key="datoimprescindible" bundle="${base}" /></label></td>
          </tr>
          <tr>
            <th scope="row"><fmt:message key="password" bundle="${base}" />: </th>
            <td><input name="txtPass" type="text" value="<%=pass %>"><label>* <fmt:message key="datoimprescindible" bundle="${base}" /></label></td>
          </tr>
          <tr>
            <th scope="row"><fmt:message key="Cp" bundle="${base}" />: </th>
            <td><input name="txtCp" type="text" value="<%=cp %>"></td>
          </tr>
        </table>
        <p align="center"><input name="txtAceptar" type="submit" value="<fmt:message key="btnEnviar" bundle="${base}" />">
        </p>
        

    </form>


</table>

</body>
</html>