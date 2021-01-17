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
<br><br><br><br><br>
<form onsubmit="return validacioneditacita(this)" action="ServletCita?accion=modificaCita" method="post">
<center>
<table class="mod1">
<tr>
<th class="mod1">EDITAR CITA</th>
</tr>
<tr>
<td class="mod1">tipoServicio</td>
<td class="mod1"><input type="text" name="tipoServicio" size="30" maxlength"50" /></td>
</tr>

<tr>
<td class="mod1">Turno:</td>
<th><select name="turno">
<option value="Turno1">Turno1</option>
<option value="Turno2">Turno2</option>
<option value="Turno3">Turno3</option>
<option value="Turno4">Turno4</option>
<option value="Turno5">Turno5</option>
</select></td>
</tr>
</table>
<br>
<input type="image" src="template/enviar.jpg" >
<button type="reset" name="limpiar" ><img src="template/limpiar.jpg" alt="borrar" /></button>

</center>

</form>
</center>
</div>
<%@ include file="template/cerrarsesion.html" %>

</html>