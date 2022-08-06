<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
    <title>Upload Canales Thinspo</title>
     <link rel="stylesheet" href="/J2EEThinspo/css/estilos.css" type="text/css" />
</head>
<body class="thinspo">
    <s:if test="hasActionErrors()">
   		<div class="errors">
      		<s:actionerror/>
   		</div>
	</s:if>
	<s:if test="hasFieldErrors()">
   		<div class="errorsFields">
      		<s:fielderror/>
   		</div>
	</s:if>
    <div class="login">
    <s:form action="uploadCanales" method="POST" enctype="multipart/form-data">
        <legend>Seleccione Archivo de Canales</legend>
        <s:file name="archivo"/>
        <s:submit value="Enviar" />
    </s:form>
    </div>
</body>
</html>