
<%
	/*
	*Se incluye en cada .jsp para verificar que la sesión se ha iniciado
	*correctamente y el acceso a la aplicación web es correcto.
	*Si no es correcto se redirecciona a la página de inicio.
	*/
	String usuario = (String) session.getAttribute("IdUsuario");

	if (usuario == null){
		response.sendRedirect("./index.jsp?estado=ilegal");
	}
%>
