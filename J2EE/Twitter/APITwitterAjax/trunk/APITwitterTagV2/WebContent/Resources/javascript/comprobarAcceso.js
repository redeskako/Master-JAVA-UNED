//-----------------------------------------------------------------------------
// ACCION: registra los manejadores de eventos y establece valores por defecto.
//-----------------------------------------------------------------------------
window.onload = function()
	{
		var Twitterform = document.getElementById("Twitterform");

		with (Twitterform)
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

		var Twitterform = document.getElementById("Twitterform");

		with (Twitterform)
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