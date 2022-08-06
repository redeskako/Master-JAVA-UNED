<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="java.util.*" %>
<%@ page import="java.lang.Exception" %>
<%@ page import="es.rizos.librerias.*"%>
<%@ page import="es.rizos.beansClientes.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet" href="css/modelo1.css" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="template/cabecera.html" %>
<%@ include file="template/informacion.html" %> 
<div id="main">
<%
if(request.getAttribute("clienteeditar")!=null){
   DatosClientes pclientes= (DatosClientes)request.getAttribute("clienteeditar");

%>

<form  onsubmit="return validacion(this)" action="ProcesoDatosClientes?action=editar" method="post" >

<table class="mod1" align="left">
 <tr>
  <th width=25% class="mod1">Datos Personales</th>
 </tr>
 <tr>
  <td width=25% class="mod1">
    Nombre:
    <input type="text" name="nombre" value="<%=pclientes.getNombre()%>"/>
  </td>
  <td width=25% class="mod1">
    Apellido1:
    <input type="text" name="apellido1" value="<%=pclientes.getApellido1()%>"/>
  </td>
  <td width=25% class="mod1">
  Apellido2:
  <input type="text" name="apellido2" value="<%=pclientes.getApellido2()%>"/>
  </td>
 </tr>
 <tr>
  <td width=25% class="mod1">
   DNI:
   <input type="text" name="dni" value="<%=pclientes.getDni()%>"/>
  </td>
  <td width=25% class="mod1">
   Telefono:
   <input type="text" name="telefono" value="<%=pclientes.getTelefono()%>"/>
  </td>
 </tr>
</table>


<table class="mod1" align="left" >
<tr>
<th class="mod1" >Datos Direccion</th>
</tr>
<tr>
<td class="mod1" width=25% >
Direccion:
<input type="text" name="direccion" value="<%=pclientes.getDireccion()%>"/>
</td>
<td class="mod1" width=25%  >
Codigo Postal:
<input type="text" name="codigopostal" onkeypress="return validar(event)" value="<%=pclientes.getCodigopostal()%>"/>
</td>
</tr>
<tr>
<td class="mod1" width=25% >
Localidad:
<input type="text" name="localidad" value="<%=pclientes.getLocalidad()%>"/>
</td>
<td class="mod1" width=25%>
Provincia:
<input type="text" name="provincia" value="<%=pclientes.getProvincia()%>"/>
</td>
</tr>
</table>


<table class="mod1" align="left" >
<th class="mod1" >Otros:</th>
<tr>
<td class="mod1" width=25% >
Observaciones:
<textarea name="observaciones" rows=5 cols=35 ><%=pclientes.getObservaciones()%></textarea>
</td>
<td class="mod1" width=25% class="mod1">
Id:
<input type="text" name="id" onkeypress="return validar(event)" value="<%=pclientes.getId()%>" />
</td>
</tr>
<tr>
<td class="mod1" width=25% >
Usuario:
<input type="text" name="usuario" value="<%=pclientes.getUsuario()%>"/>
</td>
<td class="mod1" width=25% >
Pass:
<input type="text" name="pass" value="<%=pclientes.getPass()%>"/>
</td>
</tr>
</table>
<br>
<tr>
<td>
<input  type="image" src="template/enviar.jpg" name="submit" value="editar"/>
</td>
</tr>



</form>
<%} %>
</div>
<%@ include file="template/cerrarsesion.html" %>


</html>