<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.Date" %>
<%@ page import="es.rizos.librerias.*" %>
<%@ page import="es.rizos.beansCitas.*" %>
<%@ page import="javax.servlet.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="template/cabecera.html" %>



<form action="ServletCita?accion=modificaCita" method="post">

<table cellpadding="0" cellspacing="0" class="tabla" border="2" >
<caption class="caption">EDITA CITA</caption>
<tr>
<th class="link">tipoServicio</th>
<th class="link"><input type="text" name="tipoServicio" size="30" maxlength"50" /></th>
</tr>

<tr>
<th class="link">Turno:</th>
<th class="link"><select name="turno">
<option value="Turno1">Turno1</option>
<option value="Turno2">Turno2</option>
<option value="Turno3">Turno3</option>
<option value="Turno4">Turno4</option>
<option value="Turno5">Turno5</option>
</select></th>
</tr>

<tr>
<th class="link"><input type="submit"></th>
<th class="link"><input type="reset"></th>
</tr>

</table>


<%@ include file="template/cerrarsesion.html" %>
<%@ include file="template/pie.html" %>
</html>