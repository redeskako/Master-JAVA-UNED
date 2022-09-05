<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
	<title>Creacion de _Usuario</title>
	 <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
	 <link rel="stylesheet" href="/J2EEThinspo/css/estilos.css" type="text/css" />
</head>
<body class="thinspo">
    <s:if test="hasActionErrors()">
   		<div class="errors">
      		<s:actionerror/>
   		</div>
	</s:if>
	<div class="crear">
      <legend>Introduzca Datos de Nuevo Usuario</legend>
      <s:form method="post" action="crearUsuario.action" target="content">
            <s:textfield name="nombre" label="Nombre" />
            <s:textfield name="apellidos" label="Apellidos" />
            <s:textfield name="login" label="Login" />
            <s:password name="password" label="Password"/>
            <s:submit value="Crear"/>
            <s:reset value="Reset" />
      </s:form>
	</div>
</body>
</html>