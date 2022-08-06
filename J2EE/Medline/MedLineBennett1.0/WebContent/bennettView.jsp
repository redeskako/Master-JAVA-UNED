<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"  errorPage="error.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>





<!-- 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<link rel="stylesheet" type="text/css" href="estilos.css" />
<title>B&uacutesqueda Bennett</title>
</head>
<body>
	<%@ include file="verifyAccess.jsp" %>
	<!-- añado estas lineas para cerrar sesión 
	<a id ="cerrarSesion" href="./cerrarSesion">Cerrar sesi&oacuten</a>
	<!-- fin de lo añadido para cerrar sesión 
	<div id="top">
		<img src="./images/bennet_logo.jpg"/>
	</div>
	
	-->
	<t:genericpage_access>
    <jsp:attribute name="header">
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
    <jsp:body>
	
	<div id="filtro">
	<form id="formBennettSearch" name="formBennettSearch" action="./bennettSearch.do" method="get">
		<table>
			<tr>
				<td>
					<label for="txtPalabra">Buscar en Bennett:</label>
				</td>
				<td>
					<input type="text" id="txtPalabra" name="txtPalabra" value="${requestScope.palabra}" size="20" />
				</td>
				<td>
					<label for="txtCampo">por:</label>
				</td>
				<td>
					<select id="txtCampo" name="txtCampo">
						<option value="*" <c:if test="${(empty requestScope.campo) or (requestScope.campo eq '*')}">selected="selected"</c:if>>
							cualquier campo
						</option>
						<option value="Organization" <c:if test="${requestScope.campo eq 'Organization'}">selected="selected"</c:if>>
							organizaci&oacuten
						</option>
						<option value="State" <c:if test="${requestScope.campo eq 'State'}">selected="selected"</c:if>>
							estado
						</option>
						<option value="City" <c:if test="${requestScope.campo eq 'City'}">selected="selected"</c:if>>
							ciudad
						</option>
					</select>
				</td>
				<td>
					<input type="submit" value="Ir">
				</td>
			</tr>
			<tr>
				<td></td>
				<td>
					<a href="./menu.jsp">Ir a Men&uacute</a>
				</td>
				<td></td>
				
				<td>
					<a href="./bennettView.jsp">Borrar B&uacutesqueda</a>
				</td>
			</tr>
		</table>		
	</form>
	</div>
	
	<div id="clear">
	<hr>
	</div>
    
  	<div id="content">
	<%--Si vamos a visualizar alguna página--%>
	<c:if test="${!empty requestScope.pag}">
		<%--Si hay resultados en la búsqueda--%>
		<c:choose>
			<c:when test="${requestScope.paginas gt 0}">
				<%--Si existe más de una página, mostramos enlaces.--%>
				<c:if test="${requestScope.paginas gt 1}">
					<%--Calculamos el limite de paginacion mostrada, solo se muestra si la pagina no esta vacia--%>
				  	<c:choose>
				  		<c:when test="${requestScope.pag gt 5}">
				  			<c:set var="firstPageDisplayed" value="${requestScope.pag - 5}" scope="page"/>
				  		</c:when>
				  		<c:otherwise>
				  			<c:set var="firstPageDisplayed" value="1" scope="page"/>
				  		</c:otherwise>
				  	</c:choose>
				  	<c:set var="lastPageDisplayed" value="${pageScope.firstPageDisplayed + 10}" scope="page"/>
				  	<c:if test="${requestScope.paginas lt pageScope.lastPageDisplayed}">
				  		<c:set var="lastPageDisplayed" value="${requestScope.paginas}" />
				  	</c:if>	  
					<table>
						<tr>
							<%--Para mostrar el enlace a la página anterior excepto en la primera página--%>
							<c:if test="${requestScope.pag gt 1}">
								<td><a href="./bennettSearch.do?pag=${requestScope.pag - 1}&txtCampo=${requestScope.campo}&txtPalabra=${requestScope.palabra}">&laquo--Anterior</a></td>
							</c:if>
					 
					 		<%--Si existe más de una página, muestra enlaces con los números de página. 
					 		La condición 'when' hace que no se muestre como enlace la página actual--%>
						  	<c:forEach var="i" begin="${pageScope.firstPageDisplayed}" end="${pageScope.lastPageDisplayed}">
						    	<c:choose>
						    		<c:when test="${requestScope.pag eq i}">
						    			<td><b>${i}</b></td>
						    		</c:when>
						    		<c:otherwise>
						    			<td><a href="./bennettSearch.do?pag=${i}&txtCampo=${requestScope.campo}&txtPalabra=${requestScope.palabra}">${i}</a></td>
						    		</c:otherwise>
						    	</c:choose>
						    </c:forEach>
					    	
					    	<%--Para mostrar el enlace a la página siguiente excepto en la última página--%>
					    	<c:if test="${requestScope.pag lt requestScope.paginas}">
					    		<td><a href="./bennettSearch.do?pag=${requestScope.pag + 1}&txtCampo=${requestScope.campo}&txtPalabra=${requestScope.palabra}">Siguiente--&raquo</a></td>
					    	</c:if>
					   </tr>
					</table>
				</c:if>
				<table>
					<tr>
            		<th>Organizaci&oacuten</th>
            		<th>Camas</th>
            		<th>Blog</th>
            		<th>Ciudad</th>
            		<th>Estado</th>
            		<th>URL</th>
            		<th>Tipo</th>
            		<th>Org. Par.</th>
        			</tr>
		
					<c:forEach var="bn" items="${requestScope.bennettList}">
            		<tr>
                		<td>${pageScope.bn.organization}</td>
                		<td>${pageScope.bn.beds}</td>
                		<c:choose>
	                		<c:when test="${pageScope.bn.blog ne 'none'}">
	                			<td><a href="${pageScope.bn.blog}" target="_blank">${pageScope.bn.blog}</a></td>
	                		</c:when>
	                		<c:otherwise>
	                			<td>${pageScope.bn.blog}</td>
	                		</c:otherwise>
                		</c:choose>
                		<td>${pageScope.bn.city}</td>
                		<td>${pageScope.bn.state}</td>
                		<td><a href="${pageScope.bn.fullOrgURL}" target="_blank">${pageScope.bn.fullOrgURL}</a></td>
                		<td>${pageScope.bn.orgType}</td>
                		<td>${pageScope.bn.parOrg}</td>
            		</tr>
        			</c:forEach>
				</table>
			</c:when>
			<c:otherwise>
				<h2>No hay resultados.</h2>
			</c:otherwise>
		</c:choose>
	</c:if>	
	</div>
</jsp:body>
</t:genericpage_access>
	
	
	<!-- 
</body>
</html>
-->