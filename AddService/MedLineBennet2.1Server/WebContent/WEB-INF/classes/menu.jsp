<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"  errorPage="error.jsp"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>    
    
<t:genericpage_access>
    <jsp:attribute name="header">
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
    <jsp:body>
        <form name="elegir" action="./menu"   method="get">
			<table id="menu" align ="center">
				<tr>
					<td colspan="2">Por favor, seleccione la una opcion:</td>
				</tr>
				<!--  
				<tr>
					<td>Tipo:</td>
					<td>
						<select name="tipoDB" onchange= "">
							<option value="importar">Importar BBDD</option>
							<option value="health">Healthcare database</option>
							<option value="bennett">Bennet database</option>
							<option value="hebe">Healthcare and Bennett database</option>
						</select>
					</td>
				</tr>
				-->
				<tr>
					<td align="center"><input type="submit" name="importar" value="Importar" align="middle"  /></td>
					<td align="center"><input type="submit" name="health"  value="MedLine" align="middle" /></td>
				</tr>
					<td align="center"><span style= 'font-size:12px; color:blue;'>Importa el listado <br/>actualizado <br/> de ambas listas</span></td>
					<td align="center"><span style= 'font-size:12px; color:blue;'>Busqueda en BBDD <br/> MedLine                      </span></td>
				<tr>
					<td align="center"><input type="submit" name="bennett"  value="Bennett" align="middle" /></td>
					<td align="center"><input type="submit" name="hebe"     value="Med-Ben" align="middle" /></td>
				</tr>
					<td align="center"><span style= 'font-size:12px; color:blue;'>Busqueda en BBDD <br/> Ed Bennett     </span></td>
					<td align="center"><span style= 'font-size:12px; color:blue;'>Busqueda en BBDD <br/> MedLine-Bennett</span></td>
				
				<tr>
					<td>&nbsp;</td>
				</tr>
				<!-- anulo este codigo 
				<tr>
					<td colspan="2">
						<input type="submit" value="OK">
					</td>
				</tr>
				-->
			</table>	
		</form>
    </jsp:body>
</t:genericpage_access>
<!--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="estilos.css" />
<title>Insert title here</title>
</head>
<body>
	<%@ include file="verifyAccess.jsp" %>
	<div>
		<a id="cerrarSesion" href="./cerrarSesion" target="_self"><span>Cerrar sesión</span></a>
	</div>
	<div id="cabecera">
		<img src="images/medline_logo.jpg"/>
	</div>
	
	<form name="elegir" action="./menu"   method="get">
	<table id="menu" align ="center">
		<tr>
			<td colspan="2">Por favor, seleccione la una opcion:</td>
		</tr>
		<!--  
		<tr>
			<td>Tipo:</td>
			<td>
				<select name="tipoDB" onchange= "">
					<option value="importar">Importar BBDD</option>
					<option value="health">Healthcare database</option>
					<option value="bennett">Bennet database</option>
					<option value="hebe">Healthcare and Bennett database</option>
				</select>
			</td>
		</tr>
		-->
		<!--<tr>
			<td align="center"><input type="submit" name="importar" value="Importar" align="middle"  /></td>
			<td align="center"><input type="submit" name="health"  value="MedLine" align="middle" /></td>
		</tr>
			<td align="center"><span style= 'font-size:12px; color:blue;'>Importa el listado <br/>actualizado <br/> de ambas listas</span></td>
			<td align="center"><span style= 'font-size:12px; color:blue;'>Busqueda en BBDD <br/> MedLine                      </span></td>
		<tr>
			<td align="center"><input type="submit" name="bennett"  value="Bennett" align="middle" /></td>
			<td align="center"><input type="submit" name="hebe"     value="Med-Ben" align="middle" /></td>
		</tr>
			<td align="center"><span style= 'font-size:12px; color:blue;'>Busqueda en BBDD <br/> Ed Bennett     </span></td>
			<td align="center"><span style= 'font-size:12px; color:blue;'>Busqueda en BBDD <br/> MedLine-Bennett</span></td>
		
		<tr>
			<td>&nbsp;</td>
		</tr>-->
		<!-- anulo este codigo 
		<tr>
			<td colspan="2">
				<input type="submit" value="OK">
			</td>
		</tr>
		-->
	<!--</table>	
	</form>

	
</body>
</html>-->