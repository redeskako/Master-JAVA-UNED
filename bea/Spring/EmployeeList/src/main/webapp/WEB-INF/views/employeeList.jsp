<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spring 4 MVC - Employee List</title>
</head>
<body>
<center>
	<h1>Listado de Empleados</h1>
	<c:forEach items="${employeeList}" var="employee">
		<c:out value="${employee.id}"/>: 
				<c:out value="${employee.lastName}"/>, 
				<c:out value="${employee.firstName}"/><br/>
	</c:forEach>
</center>
</body>
</html>