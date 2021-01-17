<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="java.util.*" %>
<%@ page import="java.lang.Exception" %>
<%@ page import="es.rizos.librerias.*"%>
<%@ page import="es.rizos.beansClientes.*" %>
<link rel="stylesheet" href="css/template.css" type="text/css" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="template/cabecera.html" %>
<head>
<title>Editar Cliente</title>
</head>

<center><h1>Editar al cliente</h1></center>
<%
if(request.getAttribute("clienteeditar")!=null){
   DatosClientes pclientes= (DatosClientes)request.getAttribute("clienteeditar");

%>

<form action="ProcesoDatosClientes?action=editar" method="post">
<center>
<table BORDER="3" WIDTH="60%">
<tr>
<th BGCOLOR="#6D8FFF">Datos Personales</th>
</tr>
<tr>
<td class="des">
Nombre:
<input type="text" name="nombre" value="<%=pclientes.getNombre()%>"/>
</td>
<td>
Apellido1:
<input type="text" name="apellido1" value="<%=pclientes.getApellido1()%>"/>
</td>
<td>
Apellido2:
<input type="text" name="apellido2" value="<%=pclientes.getApellido2()%>"/>
</td>
</tr>
<tr>
<td>
DNI:
<input type="text" name="dni" value="<%=pclientes.getDni()%>"/>
</td>
<td>
Telefono:
<input type="text" name="telefono" value="<%=pclientes.getTelefono()%>"/>
</td>
</tr>
</table>
<table BORDER="3" WIDTH="60%" >
<tr>
<th BGCOLOR="#6D8FFF" >Datos Direccion</th>
</tr>
<tr>
<td>
Direccion:
<input type="text" name="direccion" value="<%=pclientes.getDireccion()%>"/>
</td>
<td>
Coddigo Postal:
<input type="text" name="codigopostal" value="<%=pclientes.getCodigopostal()%>"/>
</td>
</tr>
<tr>
<td>
Localidad:
<input type="text" name="localidad" value="<%=pclientes.getLocalidad()%>"/>
</td>
<td>
Provincia:
<input type="text" name="provincia" value="<%=pclientes.getProvincia()%>"/>
</td>
</tr>
</table>
<table BORDER="3" WIDTH="60%" >

<th BGCOLOR="#6D8FFF" >Otros:</th>
<tr>
<td>
Observaciones:
<textarea name="observaciones" rows=5 cols=35 ><%=pclientes.getObservaciones()%></textarea>
</td>
<td>
Id:
<input type="text" name="id" value="<%=pclientes.getId()%>" DISABLED />
</td>
</tr>
<tr>
<td>
Usuario:
<input type="text" name="usuario" value="<%=pclientes.getUsuario()%>"/>
</td>
<td>
Pass:
<input type="text" name="pass" value="<%=pclientes.getPass()%>"/>
</td>


</tr>
</table>
<tr>
<td>
<input type="submit" name="submit" value="editar"/>
</td>
</tr>

</center>
</form>
<%} %>
<%@ include file="template/cerrarsesion.html" %>
<%@ include file="template/pie.html" %>
</html>