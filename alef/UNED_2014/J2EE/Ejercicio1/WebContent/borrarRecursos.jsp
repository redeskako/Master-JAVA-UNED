<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div style="width: 100%; height: 60px; background-color: #db9542; padding: 30px;">
<h1>Gestión de Recursos</h1>
</div>
<div style="height:20px;"></div>
<form action="AccionRecursos.jsp" method="post">
<table>
<tr>
<td>IdRecurso</td>
<td>Descripción</td>
</tr>
<tr>
<td><input type="text" name="IdRecurso"></input></td>
<td><input type="text" name="Descripcion"></input>
<input type="hidden" name="ac" value="adicionar"></input>
</td>

</tr>
</table>
<div style="height:50px;"></div>

<input type="submit" value="Borrar"></input>
<a href="recursos.jsp">Cancelar</a>
</form>
</body>
</html>