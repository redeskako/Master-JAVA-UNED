<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <sj:head jqueryui="true" jquerytheme="redmond" />
    <script type="text/javascript">
          $.subscribe('seleccionCombo', function(event,data) {
        	  var seleccion=$('#tiposGraficas').val();
       	      parent.obtenJFree(seleccion);
       });
       </script>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
    <title>Visualizacion Grafos JFree previa Seleccion de Grafica</title>
    <link rel="stylesheet" href="/J2EEThinspo/css/estilos.css" type="text/css" />
</head>
<body class="thinspo">
    <div class="graficas">
				<s:url id="obtenerTiposGraficasUrl" action="listarTiposGraficas"/> 
				<sj:select 
					href="%{obtenerTiposGraficasUrl}" 
					id="tiposGraficas" 
					name="tiposGraficas" 
					list="tiposGraficas" 
					listKey="tipoGraficaId" 
					listValue="tipoGraficaValor" 
					emptyOption="false" 
					headerKey="-1" 
					headerValue="Seleccionar un tipo de Grafica"
					onChangeTopics="seleccionCombo"
				/>
	</div>			
</body>
</html>