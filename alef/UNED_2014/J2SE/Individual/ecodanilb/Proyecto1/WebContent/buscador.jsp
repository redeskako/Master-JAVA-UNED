<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>BUSCADOR</title>
</head>
<body>

<%

	// VERIFICAMOS SI ANTES SE HA PUESTO UN DATO NO NUMERICO PARA MOSTRAR MENSAJE
	if (request.getParameter("noNumero")!=null){
		out.print("<center><h2 style='color:blue'> Ha Introducido Caracteres no Numericos en el Campo ID<h2></center>");
	}


%>

   <center> <form align="center" name="Act" action="./Buscador" method="get">
            <table align="center">
                <tr>
                    <td style="color:red;">Id:</td>
                    <td><input type="TEXT" name="identificador" size="10"> </td>
                </tr>			
                <tr>
                    <td style="color:red;">Clave:</td>
                    <td><input type="TEXT" name="recurso" size="30"> </td>
                </tr>
                <tr>
                    <td><input type="submit" name="buscar" value="Buscar"> </td>

                </tr>
            </table>		
    </form></center>


</body>
</html>