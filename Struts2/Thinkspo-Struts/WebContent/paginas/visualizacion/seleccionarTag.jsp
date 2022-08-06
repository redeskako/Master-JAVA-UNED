<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <sj:head jqueryui="true" jquerytheme="redmond" />
    <script type="text/javascript">
          $.subscribe('seleccionFila', function(event,data) {
        	  var grid = event.originalEvent.grid;
        	  var sel_id = grid.jqGrid('getGridParam', 'selrow'); 
        	  var valor = grid.jqGrid('getCell', sel_id, 'valor'); 
         	  parent.obtenJUNG(valor);
        	  
       });
       //En caso de detectar un error en la carga de la tabla(Ej Perdida de Conexion con la BBDD) saca mensaje y redirige al login
  	    $.subscribe('errorCarga', function(event,data) {
      	       alert('Ha ocurrido un error durante la carga de la tabla de Tags.Consulte con el Administrador.Mas detalle en el log');  	
      	       top.location='login.jsp';
     		 });

       </script>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
    <title>Visualizacion Grafos JUNG previa Seleccion de Categoria</title>
    <link rel="stylesheet" href="/J2EEThinspo/css/estilos.css" type="text/css" />
</head>
<body class="thinspo">
 <div id="tags">
 <s:url id="urlListarTags" action="listarTags.action"/>
    <sjg:grid
        id="tablaListarTags"
        caption="Tabla de Tags de Videos"
        dataType="json"
        href="%{urlListarTags}"
        pager="true"
        gridModel="modelotags"
        rowNum="25"
        onSelectRowTopics="seleccionFila"
        onErrorTopics="errorCarga"
    >
        <sjg:gridColumn name="clave" index="clave" title="Tags-Clave"/>
        <sjg:gridColumn name="valor" index="valor" title="Tags-Valor"/>
    </sjg:grid>
 </div>
</body>
</html>