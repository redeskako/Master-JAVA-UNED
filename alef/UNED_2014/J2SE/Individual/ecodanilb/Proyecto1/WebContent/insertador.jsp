<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
    <%@ page import="java.util.ArrayList,java.util.Iterator,org.BBDD.*, org.Otros.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>INSERTAR REGISTROS</title>
</head>
<body>


   <center> <form align="center" name="Act" action="./Insertador" method="get">
            <table align="center">		
                <tr>
                    <td style="color:red;">Recurso:</td>
                    <td><input type="TEXT" name="recurso" size="30"> </td>
                </tr>
                <tr>
                    <td><input type="submit" name="insertar" value="Insertar"> </td>

                </tr>
            </table>		
    </form></center>

</body>
</html>