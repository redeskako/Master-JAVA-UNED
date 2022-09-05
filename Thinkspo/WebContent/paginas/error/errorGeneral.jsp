<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html xmlns="http://www.w3.org/1999/xhtml">
   <head>
      <title>Error General</title>
      <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
      <link rel="stylesheet" href="/J2EEThinspo/css/estilos.css" type="text/css" />
	</head>
	<body class="thinspo">
		<h2>Situacion Anomala</h4>
		<p>Se ha producido un error en la aplicacion.Contacte con el administrador.Detalles debajo</p> 
		<h4>Exception Name: <s:property value="exception" /> </h4>
		<h4>Exception Details: <s:property value="exceptionStack" /></h4> 
	</body>
	
</html>