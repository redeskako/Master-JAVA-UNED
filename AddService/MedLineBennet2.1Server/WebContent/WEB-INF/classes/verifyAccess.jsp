
<%
	/*
	*Se incluye en cada .jsp para verificar que la sesi�n se ha iniciado
	*correctamente y el acceso a la aplicaci�n web es correcto.
	*Si no es correcto se redirecciona a la p�gina de inicio.
	*/
	String usuario = (String) session.getAttribute("IdUsuario");

	if (usuario == null){
		response.sendRedirect("./index.jsp?estado=ilegal");
	}
%>
