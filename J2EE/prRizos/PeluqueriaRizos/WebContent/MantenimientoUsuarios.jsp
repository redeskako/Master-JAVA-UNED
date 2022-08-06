<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp" %>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.Exception" %>
<link rel="stylesheet" href="css/template.css" type="text/css" />
<%@ page import="es.rizos.beansClientes.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="template/cabecera.html" %>

<style type="text/css">
a{text-decoration:none}
a:hover{text-decoration: underline ; color:black;}
</style>
<center><h1>Clientes Guays</h1></center>
<%
if(request.getAttribute("cliente")!=null){
    //Cogemos el atributo que nos manda el controlador
    ArrayList clientes = (ArrayList) request.getAttribute("cliente");

%>
<form action="ProcesoClientes" method="post"><P>
Busqueda <INPUT type="text" name="textobusqueda">
Campo
<SELECT NAME="campobusqueda" size="1">
	<option value="nombre" >Nombre </option>
	<option value="Apellido1">Apellido1 </option>
	<option value="Apellido2">Apellido2 </option>
	<option value="DNI">DNI </option>

</SELECT>
<INPUT TYPE="submit" name="action" value="buscar">
</form>
<center>
<table cellpadding="0" cellspacing="0" class="tabla">
<caption class="caption">
<strong><font size=4 color="green">  Listado de usuarios</font></strong></caption>
<tr>
<th >
IDcliente
</th>
<th >
<a href="ProcesoClientes?action=ordenar&campo=nombre">
Nombre </a>
</th>
<th >
<a href="ProcesoClientes?action=ordenar&campo=apellido1">
Apellido1
</a>
</th>
<th >
<a href="ProcesoClientes?action=ordenar&campo=apellido2">
Apellido2
</a>
</th>
<th >
<a href="ProcesoClientes?action=ordenar&campo=dni">
DNI
</a>
</th>
<th >
EDITAR
</th>
<th >

ELIMINAR

</th>
</tr>
<%

	Iterator<Clientes> it = clientes.iterator();
	while (it.hasNext()){
		Clientes cl = it.next();
%>
<tr>
<td class="tit">
		<%=cl.getId()%>
</td>
<td class="tit">
		<%=cl.getNombre()%>
</td>
<td class="tit">
		<%=cl.getApellido1() %>
</td>
<td class="tit">
		<%=cl.getApellido2() %>
</td>
<td class="des">
		<%=cl.getDni() %>
</td>
<td class="des">
 <a href="ProcesoDatosClientes?action=obtenerCliente&campo=<%=cl.getId()%>">
  <img src="template/shapes060.gif" alt="editar">
 </a>
</td>
<td class="des">
 <a href="ProcesoClientes?action=eliminar&campo=<%=cl.getId()%>">
 <img src="template/shapes062.gif" alt="eliminar">
 </a>
</td>
<%}
	}%>
</tr>
</table>

</center></br>
<tr class="boton">
<td>
Añadir Cliente
<a href="AltaCliente.jsp"> <img src="template/shapes060.gif" alt="insertar"></a>
</td>
</tr>

<%@ include file="template/cerrarsesion.html" %>
<%@ include file="template/pie.html" %>
</html>