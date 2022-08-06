<%@tag description="Template general" pageEncoding="UTF-8"%>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" href="estilos.css" />
	<title>MedLine Bennett database search</title>
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
    <%@ include file="../../verifyAccess.jsp" %>
    <div id="pageheader">
    	<div>
			<a id="cerrarSesion" href="./cerrarSesion" target="_self"><span>Cerrar sesión</span></a>
		</div>
      	<img src="images/medline_logo.jpg" />
      <jsp:invoke fragment="header"/>
    </div>
    <div id="body">
      <jsp:doBody/>
    </div>
    <div id="pagefooter">
    	<p id="copyright">Copyright Alef, UNED 2013.</p>
      	<jsp:invoke fragment="footer"/>
    </div>
  </body>
</html>