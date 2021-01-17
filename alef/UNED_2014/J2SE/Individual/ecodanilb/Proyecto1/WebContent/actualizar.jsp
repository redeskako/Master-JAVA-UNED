<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
    <%@ page import="java.util.ArrayList,java.util.Iterator,org.BBDD.*, org.Otros.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>ACTUALIZAR</title>
</head>
<body>

<%
	int id = Integer.valueOf(request.getParameter("id")) ;

Recursos xx = new Recursos(id,"");
ArrayList<Recursos> ConjuntoRecursos = new ArrayList<Recursos>();
ConjuntoRecursos = xx.listadoRecursos("Select * from recursos Where idRecurso='"+id+"'"); 
Recursos unRecurso;
Iterator it= ConjuntoRecursos.iterator();
String idRecurso="";
while (it.hasNext()){
	unRecurso =(Recursos)it.next();
	idRecurso=String.valueOf(unRecurso.getCodigo());// OBTENEMOS EL Nº DE ID
	String nombreRecurso=String.valueOf(unRecurso.getNombre());
	



%>


   <center> <form align="center" name="Act" action="./Actualizar" method="get">
            <table align="center">
                <tr>
                    <td style="color:red;">Id:</td>
                    <td><input type="TEXT" name="identificador" size="10" value="<%= idRecurso%>" disabled> </td>
                </tr>			
                <tr>
                    <td style="color:red;">Clave:</td>
                    <td><input type="TEXT" name="recurso" size="30" value="<%= nombreRecurso%>"> </td>
                </tr>
                <tr>
                    <td><input type="submit" name="enviar" value="Aceptar"> </td>

                </tr>
            </table>		
    </form></center>

<%} 
HttpSession sesion= request.getSession();
sesion.setAttribute("idRec",idRecurso);//GRABAMOS EN SESION EL NUMERO DE ID
%>
</body>
</html>