<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet" href="css/modelo1.css" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="template/cabecera.html" %>
<%@ include file="template/informacion.html" %> 
<div id="main">
<br><br><br><br>
<center>
<head><text3>Gesti&oacute;n Adminstrador</text3></head>
</center>
<br><br>
<center>
<table class="mod1">
<tr>
<td>
<a href="ProcesoClientes?action=ejecuta"><font size=5 color="yellow">Gestion Clientes</font></a>
</td>
</tr>
<br/>
<tr>
<td>
<a href="Calendario/January.jsp"><font size=5 color="yellow">Gestion Citas</font></a>
</td>
</tr>

</table>

</center>
</div>
<%@ include file="template/cerrarsesion.html" %>

</html>

