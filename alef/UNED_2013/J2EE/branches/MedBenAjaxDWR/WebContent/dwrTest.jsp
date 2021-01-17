<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<script type='text/javascript' src='dwr/util.js'></script>
<script type='text/javascript' src='dwr/engine.js'></script>
<script type='text/javascript' src='dwr/interface/Dwrwsrest.js'></script>
<script type='text/javascript'>

	function comunicacionServidor(){
		var catalogo = "MedLine";
		var palabra = "Pain";
		var pred = "Pa";
		var campo = "Title";
		var limite = 5;
		Dwrwsrest.busquedaBasica(catalogo, palabra, campo, function(res){
			var cadTitulos = "[";
			var cadURLs = "[";
			
			for (var i = 0; i < res.length; i++) {
				cadTitulos += "'" + res[i][0] + "'";
				cadURLs += res[i][1];
				if (i < res.length - 1) {
					cadTitulos += ", ";
					cadURLs += ", ";
				}
			}
			cadTitulos += "]";
			cadURLs += "]";
			$('mostrarMensaje1').value = cadTitulos;
			$('mostrarMensaje2').value = cadURLs;});
		Dwrwsrest.busquedaPredictiva(catalogo, pred, limite, function(res) {
			var cad = "[";
			
			for (var i = 0; i < res.length; i++) {
				cad += "'" + res[i] + "'";
				if (i < res.length - 1) cad += ", ";
			}
			cad += "]";
			$('mostrarMensaje3').value = cad;});
	}
</script>
<title>Insert title here</title>
</head>
<body>
<p> Prueba de DWR </p>
<button id="buscarMensaje" onclick="comunicacionServidor();">Comunicar con el servidor</button>
<table>
	<tr><td>
		<input id="mostrarMensaje1" type="text" size="100" style="background:lightyellow;" />
	</td></tr>
	<tr><td>
		<input id="mostrarMensaje2" type="text" size="100" style="background:lightyellow;" />
	</td></tr>
	<tr><td>
		<input id="mostrarMensaje3" type="text" size="100" style="background:lightyellow;" />
	</td></tr>
</table>
</body>
</html>