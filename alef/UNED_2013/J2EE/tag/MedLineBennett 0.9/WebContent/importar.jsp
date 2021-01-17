<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"  errorPage="error.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="estilos.css" media="screen" />
<title>Seleccione el archivo a importar</title>
<script type="text/javascript">
/*
 * Cambia la extensión del cuadro de diálogo de selección de archivos para mostrar solo
 * aquellos que sean del mismo tipo que el seleccionado en el cuadro de diálogo. En este
 * caso los tipos de archivos permitidos son ".xml" o ".csv".
 */
function acceptedFile(){
	//Selecciona el índice de la opción seleccionada
	var ext =document.getElementById("mySelect").selectedIndex;
	//Establece el valor del atributo 'accept' para que sólo se muestren los archivos que tengan esa extensión
	document.getElementsByTagName("input")[0].setAttribute("accept", document.getElementsByTagName("option")[ext].value);
}
</script>
</head>
<body>
<%@ include file="verifyAccess.jsp" %> 
<!-- añado estas lineas para cerrar sesión -->
	<div>
		<a id ="cerrarSesion" href="./cerrarSesion" target="_self">Cerrar sesión</a>
	</div>
	<!-- fin de lo añadido para cerrar sesión -->
	<div id="cabecera">
		<img src="images/medline_logo.jpg"/>
	</div>
	<form method="POST" enctype="multipart/form-data" action="./UploadFile">
		
		<table id="menu" align="center">
			<tr>
				<td colspan="2"><span>Por favor, seleccione el archivo a importar:</span></td>
			</tr>
			<tr>
				<td><span>Tipo:</span></td>
			<td>
				<select name="tipoDB" id="mySelect">
					<option value=".xml">Medline XML</option>
					<option value=".csv">Bennet CSV</option>
<!-- 					<option value="hebe">Healthcare and Bennett database</option> -->
				</select>
			</td>

		</tr>
		<tr>
			<td>Archivo:</td>
			<td><input type="file" name="fichero" onclick="acceptedFile()"></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="Importar">
			</td>
			<th><a href="menu.jsp">Go to Menu</a></th>
		</tr>
			</table>
		</form>
	
		<div id="cargaArchivo">
		
			<c:if test="<%=request.getAttribute(\"nombre\") != null %>">
				<p><span>Nombre:  <%=request.getAttribute("nombre") %></span></p>
				<p><span>Tipo <%=request.getAttribute("tipo") %></span></p>
				<p><span>Extensi&oacuten: <%=request.getAttribute("extension") %></span></p>
				<p><span>Nombre del archivo: <%=request.getAttribute("myArchivo") %></span></p>
			</c:if>	
		
			<c:if test="<%=request.getAttribute(\"guardado\") != null %>">
				<p><span>Guardado en:  <%=request.getAttribute("guardado") %></span></p>
				<p><span>Guardado correctamente: <%=request.getAttribute("guardado_correctamente") %></span></p>
			</c:if>
		
			<c:if test="<%=request.getAttribute(\"fallo_al_guardar\") != null %>">
				<p><span>Fallo al guardar:  <%=request.getAttribute("fallo_al_guardar") %></span></p>
			</c:if>
		
			<c:if test="<%=request.getAttribute(\"XML_correcto\") != null %>">
				<p><span>XML correcto:  <%=request.getAttribute("XML_correcto") %></span></p>
			</c:if>
		
			<c:if test="<%=request.getAttribute(\"XML_novalido\") != null %>">
				<p><span>XML incorrecto:  <%=request.getAttribute("XML_novalido") %></span></p>
			</c:if>
		
			<c:if test="<%=request.getAttribute(\"XML_eliminado\") != null %>">
				<p><span>XML eliminado:  <%=request.getAttribute("XML_eliminado") %></span></p>
			</c:if>
		
			<c:if test="<%=request.getAttribute(\"csv_cargado\") != null %>">
				<p><span>CSV cargado:  <%=request.getAttribute("csv_cargado") %></span></p>
			</c:if>
			
		
			
	
		</div>
	
</body>
</html>