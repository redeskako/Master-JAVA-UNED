<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<link rel="stylesheet" href="css/template.css" type="text/css" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="template/cabecera.html" %>
<head>
<title>Alta Cliente</title>
</head>



<form action="ProcesoDatosClientes" method="post">
<center>
<table BORDER="3" WIDTH="50%">
<tr>
<th BGCOLOR="#6D8FFF">Datos Personales</th>
</tr>
<tr>
<td class="des">
Nombre:
<input type="text" name="nombre" />
</td>
<td>
Apellido1:
<input type="text" name="apellido1" />
</td>
<td>
Apellido2:
<input type="text" name="apellido2" />
</td>
</tr>
<tr>
<td>
DNI:
<input type="text" name="dni" />
</td>
<td>
Telefono:
<input type="text" name="telefono" />
</td>
</tr>
</table>
<table BORDER="3" WIDTH="50%" >
<tr>
<th BGCOLOR="#6D8FFF" >Datos Direccion</th>
</tr>
<tr>
<td>
Direccion:
<input type="text" name="direccion" />
</td>
<td>
Coddigo Postal:
<input type="text" name="codigopostal" />
</td>
</tr>
<tr>
<td>
Localidad:
<input type="text" name="localidad" />
</td>
<td>
Provincia:
<input type="text" name="provincia" />
</td>
</tr>
</table>
<table BORDER="3" WIDTH="50%" >

<th BGCOLOR="#6D8FFF" >Otros:</th>
<tr>
<td>
Observaciones:
<textarea name="observaciones" rows=5 cols=35></textarea>
</td>
</tr>
<tr>
<td>
Usuario:
<input type="text" name="usuario" />
</td>
<td>
Pass:
<input type="text" name="pass"/>
</td>
<td>
Tipo Usuario:
<input type="text" name="tipousuario" />
</td>

</tr>
</table>
<tr>
<td>
<input type="submit" name="action" value="insertar"/>
</td>
</tr>

</center>
</form>
<%@ include file="template/cerrarsesion.html" %>
<%@ include file="template/pie.html" %>
</html>