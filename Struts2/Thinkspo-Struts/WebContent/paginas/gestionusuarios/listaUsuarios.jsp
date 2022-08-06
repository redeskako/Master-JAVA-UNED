<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <sj:head jqueryui="true" jquerytheme="redmond" />
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
     <link rel="stylesheet" href="/J2EEThinspo/css/estilos.css" type="text/css" />
    <title>Usuarios Thinspo</title>
    <script type="text/javascript">
        //En caso de detectar un error en la carga de la tabla(Ej Perdida de Conexion con la BBDD) saca mensaje y redirige al login
	    $.subscribe('errorCarga', function(event,data) {
    	       alert('Ha ocurrido un error durante la carga de la tabla de Usuarios.Consulte con el Administrador.Mas detalle en el log');  	
    	       top.location='login.jsp';
   		 });
    </script>
</head>
<body class="thinspo">
<s:if test="hasActionMessages()">
   <div class="errors">
      <s:actionmessage/>
   </div>
</s:if>
 <s:url id="remoteurl" action="listarUsuarios"/>
    <sjg:grid
        id="gridtable"
        caption="Tabla de Usuarios de Thinspo"
        dataType="json"
        href="%{remoteurl}"
        pager="true"
        gridModel="gridModel"
        rowList="10,15,20,25"
        rowNum="10"
        rownumbers="true"
        onErrorTopics="errorCarga"
    >
        <sjg:gridColumn name="login" index="login" title="Login"/>
        <sjg:gridColumn name="password" index="password" title="Password"/>
        <sjg:gridColumn name="nombre" index="nombre" title="Nombre"/>
        <sjg:gridColumn name="apellidos" index="apellidos" title="Apellidos"/>
    </sjg:grid>
</body>
</html>