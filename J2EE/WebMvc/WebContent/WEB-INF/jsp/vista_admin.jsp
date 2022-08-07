<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head> 
	<title>Area de administrador de canales</title>
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
			id.value = "";
			nombre.value = "";
			descripcion.value = "";

			id.focus();

			boton.onclick = validar;
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
			// Id.
			if (id.value == "")
			{
				alert ("Introduzca el identificador del canal.");
				id.focus();
			}
			// Nombre.
			else if (nombre.value == "")
			{
				alert ("Introduzca el nombre del canal.");
				nombre.focus();
			}
			// Descripción.
			else if (descripcion.value == "")
			{
				alert ("Introduzca la descripción del canal.");
				descripcion.focus();
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

		<!-- Salida sesión usuario -->
		<div><a class="cerrarSesion" href="Controlador?operacion=logout">Cerrar sesión</a></div>

	    <h1>Area de administrador de canales</h1> 
	    <p class="leyenda">Añade un nuevo canal a la BD.</p>  

	    <!-- Añadimos canal a BD -->
		<form id="frm" action="Controlador?operacion=anadir_canal" method="post">

			<div id="pnlCanal">
			
			   	<span>Identificador</span>
			   	<input type="text" name="id" id="id" maxlength="30" size="30" />

			   	<span>Nombre</span>
			   	<input type="text" name="nombre" id="nombre" maxlength="30" size="30" />

				<br/>
			   	<span>Descripción</span>
			   	<input type="text" id="descripcion" name="descripcion" id="descripcion" maxlength="100" size="77"/>

			   	<input type="submit" name="boton" id="boton" value="Añadir canal"/>
			   
			</div>
		</form>

	</div> <!-- Contenedor -->

</body>
</html>