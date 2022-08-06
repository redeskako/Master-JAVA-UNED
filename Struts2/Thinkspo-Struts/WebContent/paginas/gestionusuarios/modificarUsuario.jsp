<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
	<title>Modificación de _Usuario</title>
	<link rel="stylesheet" href="/J2EEThinspo/css/estilos.css" type="text/css" />
</head>
<body class="thinspo">
    <s:if test="hasActionErrors()">
   		<div class="errors">
      		<s:actionerror/>
   		</div>
	</s:if>
	<div class="crear">
      <legend>Introduzca Datos Usuario a Modificar</legend>
      <s:form method="post" action="modificarUsuario.action" target="content">
            <s:textfield name="login" label="Login" />
            <s:password name="password" label="Password"/>
            <s:textfield name="nombre" label="Nombre"/>
            <s:textfield name="apellidos" label="Apellidos"/>
            <s:submit value="Modificar"/>
      </s:form>
	</div>
</body>
</html>