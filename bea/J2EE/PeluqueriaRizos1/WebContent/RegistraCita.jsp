<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<link rel="stylesheet" href="css/modelo1.css" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="template/cabecera.html" %>
<%@ include file="template/informacion.html" %> 
<div id="main">
<center>
<form onsubmit="return validacioncita(this)" action="ServletCita?accion=registraCita" method="post">
<br><br><br><br><br>
<table class="mod1" >
<tr>
<th class="mod1" >REGISTRAR NUEVA CITA</th>
</tr>
<tr>
<td class="mod1" >dni</td>
<td class="mod1" ><input type="text" name="dni" /></td>
</tr>
<tr>
<td class="mod1" >Tipo de Servicio:</td>
<td class="mod1" ><select name="tipoServicio">
<option value="Corte">Corte</option>
<option value="Tinte">Tinte</option>
<option value="Mechas">Mechas</option>
<option value="Alisado japones">Alisado japones</option>
<option value="Extensiones">Extensiones</option>
</select></td>
</tr>

<tr>
<td class="mod1" >Turno:</td>
<td class="mod1" ><select name="turno">
<option value="Turno1">Turno1</option>
<option value="Turno2">Turno2</option>
<option value="Turno3">Turno3</option>
<option value="Turno4">Turno4</option>
<option value="Turno5">Turno5</option>
</select></td>
</tr>
</table>
<br>
<tr>
<td>
<input type="image" src="template/enviar.jpg" name="action" value="Registra la cita"> &nbsp
<button type="reset" name="limpiar" ><img src="template/limpiar.jpg" alt="borrar" /></button>
</td>
</tr>
</form>
</center>
<br><br><br><br>
<%@ include file="template/cerrarsesion.html" %>
</div>
</html>