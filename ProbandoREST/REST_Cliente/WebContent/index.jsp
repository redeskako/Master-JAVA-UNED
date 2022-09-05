<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cliente REST</title>
</head>
<body>
		<h3>Saludo sin parámetros (GET):</h3>
		<form name="formularioSaludar" action="AccionSaludar" method="get">
			<input type="submit" name="Saludar" value="Saludar">
		</form>

		<br/>
		<h3>Saludo con parámetros (GET):</h3>
		<form name="formularioSaludar" action="AccionSaludar2" method="get">
			<input type="text" name="nombre" size="10">
			<input type="submit" name="Saludar" value="Saludar">
		</form>
		
		<br/>
		<h3>Suma de dos números (GET):</h3>		
		<form name="formularioSumar" action="AccionSumarGet" method="get">
			<label>Sumar: </label>
			<input type="text" name="suma_a" size="3">
			<label> + </label>
			<input type="text" name="suma_b" size="3">
			<input class="logBtn" type="submit" name="Sumar" value="Sumar">
		</form>
		
		<br/>
		<h3>Suma de varios números (POST):</h3>		
		<form name="formularioSumar" action="http://localhost:8080/REST_Servidor/rest/calculadora" method="post">
			<label>Sumar: </label>
			<input type="text" name="suma_a" size="3">
			<label> + </label>
			<input type="text" name="suma_b" size="3">
			<label> + </label>
			<input type="text" name="suma_c" size="3">
			<label> + </label>
			<input type="text" name="suma_d" size="3">
			<input class="logBtn" type="submit" name="Sumar" value="Sumar">
		</form>
		
		<br/>		
		<h3>Nombre de usuario (POST + BBDD):</h3>		
		<form name="formularioEmpleado1" action="http://localhost:8080/REST_Servidor/rest/numeroUsuario" method="post">
			<label>Introduzca un número del 1 al 3: </label>
			<input type="text" name="num" size="3">
			<input class="logBtn" type="submit" name="numUsuario" value="Num.Usuario">
		</form>
		
		<br/>				
		<h3>Nombre de usuario (GET + BBDD + XML para envío de UN objeto):</h3>		
		<form name="formularioEmpleado2" action="AccionNumeroUsuarioXml" method="post">
			<label>Introduzca un número del 1 al 3: </label>
			<input type="text" name="num" size="3">
			<input class="logBtn" type="submit" name="numUsuario" value="Num.Usuario">
		</form>
		
		<br/>
		<h3><font color=red>Nombre de usuario (GET + BBDD + JSON para envío de UN objeto): - - - NO FUNCIONA - - -</font></h3>	
		<form name="formularioEmpleado3" action="AccionNumeroUsuarioJson" method="post">
			<label>Introduzca un número del 1 al 3: </label>
			<input type="text" name="num" size="3">
			<input class="logBtn" type="submit" name="numUsuario" value="Num.Usuario">
		</form>
		
		<br/>				
		<h3>Listado de usuarios (GET + BBDD + XML para envío de UN ARRAY):</h3>		
		<form name="formularioEmpleado4" action="AccionUsuariosXml" method="post">
			<label>Listado de usuarios: </label>
			<input class="logBtn" type="submit" name="ver" value="ver">
		</form>
</body>
</html>