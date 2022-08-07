<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!doctype html public "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="es">

<head>
	<title>Youtube CrawlerC</title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<link href="css/estilos.css" type="text/css" rel="stylesheet">
	
	<script language="javascript">

	//-----------------------------------------------------------------------------
	// ACCION: registra los manejadores de eventos y establece valores por defecto.
	//-----------------------------------------------------------------------------
	window.onload = function()
	{
		var frm = document.getElementById("frm");

		with (frm)
		{
			user.value = "";
			password.value = "";

			user.focus();

			btn.onclick = validar;
		}
	}
	
	//-------------------------------------------------------------------------
	// ACCION: Comprueba que se han cubierto todos los campos obligatorios y 
	//	 	que todos los datos introducidos son correctos.
	//-------------------------------------------------------------------------
	function validar()
	{
		var bCorrecto = false;

		var frm = document.getElementById("frm");

		with (frm)
		{
			// User.
			if (user.value == "")
			{
				alert ("Introduzca el nombre de usuario.");
				user.focus();
			}
			// Password.
			else if (password.value == "")
			{
				alert ("Introduzca la contraseña.");
				password.focus();
			}
			else
			{
				bCorrecto = true;
			}
		}

		// Valor de retorno.
		return bCorrecto;
	}	
	</script>
	
</head>

<body>
        
    <!-- Contenedor -->
    <div id="contenedor">

		<h1 class="login">YouTube Crawler</h1>
		
	    <!-- Mandamos un canal de prueba -->
		<form action="Controlador?operacion=login" method="post" id="frm">

			<div id="pnlClave">
			   <span>Usuario</span>
			   <input type="text" name="user" id="user" maxlength="10" size="10" />
			   <span>Clave</span>
			   <input type="password" name="password" id="password" maxlength="10" size="10" />
			   <input type="submit" name="btn" id="btn" value="Aceptar"/>
			</div>                 

		</form>
	
		<p class="pie">Master Java UNED - 2015.</p>
	
	</div> <!-- Contenedor -->

</body>

</html>