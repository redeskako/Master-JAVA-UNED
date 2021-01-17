<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp"%>
<%@ page import="java.lang.*" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- DW6 -->
<head>
<!-- Copyright 2005 Macromedia, Inc. All rights reserved. -->
<title>Restaurante - PÃ¡gina principal</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="css/mm_restaurant1.css" type="text/css" />
</head>
<body bgcolor="#0066cc">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr bgcolor="#99ccff">
	<td width="15" nowrap="nowrap" ><img src="css/mm_spacer.gif" alt="" width="15" height="1" border="0" /></td>
	<td height="60" colspan="3" class="logo" nowrap="nowrap"><br />
	Restaurante Bicicleta Atomica<span class="tagline">| Donde los sentidos revaran lo inimaginable</span></td>
	<td width="40">&nbsp;</td>
	<td width="100%">&nbsp;</td>
	</tr>

	<tr bgcolor="#003399">
	<td width="15" nowrap="nowrap">&nbsp;</td>
	<td height="36" colspan="3" id="navigation" nowrap="nowrap" class="navText"><a href="cocina.jsp">COCINA</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      <a href="localizacion.jsp">LOCALIZACION</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="carta.jsp">CARTA</a>&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp;
      <a href="eventos.jsp">EVENTOS ESPECIALES</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="contacto.jsp">&nbsp;PÃNGASE EN CONTACTO CON NOSOTROS&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></td>
	  <td width="40">&nbsp;</td>
	<td width="100%">&nbsp;</td>
	</tr>

	<tr bgcolor="#ffffff">
	<td colspan="6"><img src="css/mm_spacer.gif" alt="" width="1" height="1" border="0" /></td>
	</tr>

	<tr bgcolor="#ffffff">
	<td width="230" colspan="2" valign="top" bgcolor="#ffffcc"><img src="css/mm_restaurant_image.jpg" alt="Home Page Image" width="230" height="270" border="0" />
	<table border="0" cellspacing="0" cellpadding="0" width="245">
		<tr>
		<td width="10">&nbsp;</td>
		<td width="231" class="smallText" id="padding">
		<form id="form1" name="form1" method="post" action="ProcesaLogado">
          <p>&nbsp;</p>
          <p>
          <label>
            Usuario: .......
              <input type="text" name="usuario" id="usuario" />
             </label>


          </p>
          <p align="center">

            <label> Password: ..
            <input type="password" name="password" id="password" />
            </label>
          </p>
          <p align="center">
            <label>
            <input type="submit" name="submit" id="btnAcceso" value="Enviar" />
            </label>
          </p>
          <p align="center">&nbsp;</p>
		</form>
			<%
				Integer valido;
				String cad;
				try{
					cad= request.getParameter("valido");
				valido= new Integer(Integer.parseInt(cad));
				}catch(Exception e){ valido=new Integer(0);}

				String str= "";
				switch (valido.intValue()){
					case -1: str="Usuario o Password incorrecto";break;
					case -4: str="Debes iniciar sesion";break;
					case 0: session.invalidate();
				}
				if (valido.intValue()!=0){
			%>

					<center>Error:<%=valido.intValue()%><br/>
					<%=str %></center>
				<%
				}
				%>

		<br />		</td>
		<td width="10">&nbsp;</td>
		</tr>
		<tr>
		  <td>&nbsp;</td>
		  <td class="smallText" id="padding2">&nbsp;</td>
		  <td>&nbsp;</td>
	    </tr>
	</table>	</td>
	<td width="50" valign="top"><img src="css/mm_spacer.gif" alt="" width="50" height="1" border="0" /></td>
	<td width="440" valign="top"><br />
	&nbsp;<br />
	<table border="0" cellspacing="0" cellpadding="0" width="440">
		<tr>
		<td class="pageHeader">Bienvenidos al Imperio de los Sentidos</td>
		</tr>

		<tr>
		<td class="bodyText"><p>Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. </p>

		<p>Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi. </p>

		<br />		</td>
		</tr>
	</table>	</td>
	<td width="40">&nbsp;</td>
	<td width="100%">&nbsp;</td>
	</tr>

	<tr bgcolor="#ffffff">
	<td colspan="6"><img src="css/mm_spacer.gif" alt="" width="1" height="1" border="0" /></td>
	</tr>

	<tr>
	<td colspan="6">&nbsp;</td>
	</tr>


	<tr bgcolor="#003399">
	<td colspan="6"><img src="css/mm_spacer.gif" alt="" width="1" height="1" border="0" /></td>
	</tr>

	<tr>
	<td colspan="6"><img src="css/mm_spacer.gif" alt="" width="1" height="2" border="0" /></td>
	</tr>

	<tr bgcolor="#003399">
	<td colspan="6"><img src="css/mm_spacer.gif" alt="" width="1" height="1" border="0" /></td>
	</tr>


	<tr>
	<td width="15">&nbsp;</td>
	<td width="215">&nbsp;</td>
	<td width="50">&nbsp;</td>
	<td width="440">&nbsp;</td>
	<td width="40">&nbsp;</td>
	<td width="100%">&nbsp;</td>
	</tr>
</table>
</body>
</html>
