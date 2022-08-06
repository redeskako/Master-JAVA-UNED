<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<link rel="stylesheet" href="css/template.css" type="text/css" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet" href="css/modelo1.css" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="template/cabecera.html" %>
<%@ include file="template/informacion.html" %> 

<div id="main">
<form onsubmit="return validacion(this)" action="ProcesoDatosClientes?action=insertar" method="post">
<center>
<table class="mod1" align="left">
<tr>
<th width=25% class="mod1">Datos Personales</th>
</tr>
<tr>
<td width=25% class="mod1">
Nombre:
<input type="text" name="nombre" />
</td>
<td width=25% class="mod1">
Apellido1:
<input type="text" name="apellido1" />
</td>
<td width=25% class="mod1">
Apellido2:
<input type="text" name="apellido2" />
</td>
</tr>
<tr>
<td width=25% class="mod1">
DNI:
<input type="text" name="dni" />
</td>
<td width=25% class="mod1">
Telefono:
<input type="text" name="telefono" />
</td>
</tr>
</table>
</center>
<center>
<table class="mod1" align="left" >
<tr>
<th th width=25% class="mod1">Datos Direccion</th>
</tr>
<tr>
<td width=25% class="mod1">
Direccion:
<input type="text" name="direccion" />
</td>
<td width=25% class="mod1">
Codigo Postal:
<input type="text" name="codigopostal" onkeypress="return validar(event)" />
</td>
</tr>
<tr>
<td width=25% class="mod1">
Localidad:
<input type="text" name="localidad" />
</td>
<td width=25% class="mod1">
Provincia:
<input type="text" name="provincia" />
</td>
</tr>
</table>
</center>
<table class="mod1" align="left">
<tr>
<th width=25% class="mod1" >Otros:</th>
</tr>
<tr>
<td width=25% class="mod1">
Observaciones:
<textarea name="observaciones" rows=5 cols=35></textarea>
</td>
<td width=25% class="mod1">
Tipo Usuario:
<SELECT NAME="tipoUsuario" size="1">
	<option value="cliente" >cliente </option>
	<option value="admin" DISABLED>administrador </option>
</SELECT>
</td>
</tr>
<tr>
<td width=25% class="mod1">
Usuario:
<input type="text" name="usuario" />
</td>
<td width=25% class="mod1">
Pass:
<input type="text" name="pass"/>
</td>


</tr>
</table>

<br>
<tr>
<td>
<input type="image" src="template/enviar.jpg"  name="action" value="insertar"/>
</td>
</tr>
</form>
</div>
<%@ include file="template/cerrarsesion.html" %>

</html>