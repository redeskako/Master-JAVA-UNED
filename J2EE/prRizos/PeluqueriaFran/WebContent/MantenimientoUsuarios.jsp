<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="template/cabecera.html" %>


<form action="Usuario.java" method="post">
 <P>
    <LABEL for="busqueda">Busqueda </LABEL>
<INPUT type="text" id="busqueda">
<SELECT NAME="urldestino">
<option selected value="datos">
<option value="inicio.html" >Nombre
<option value="seccion1.html">Apellido1
<option value="seccion2.html">Apellido2
<option value="seccion2.html">DNI

</SELECT>
<input type="button" value="BUSCAR" onClick="location=this.form.urldestino.value" name="button">
</form>

<table cellpadding="0" cellspacing="0" class="tabla" border="2" >
<caption class="caption">Clientes</caption>
<tr>
<th class="link">
NOMBRE
</th>
<th class="link">
APELLIDO1
</th>
<th class="link">
APELLIDO2
</th>
<th class="link">
DNI
</th>
<th class="link">
borrar
</th>
<th class="link">
<IMG SRC="template/images.jpg" ALT="AQUI VA UNA IMAGEN">

</th>

</table>
<%@ include file="template/pie.html" %>
</html>