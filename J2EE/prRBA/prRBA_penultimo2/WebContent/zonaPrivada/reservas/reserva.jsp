<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" errorPage="error.jsp"%>
<%@ page import="java.lang.*"%>
<%@ page import="rba.bbdd.*" %>
<%@ page import="java.util.*" %>
<%@ page import="rba.reservas.AccionReserva" %>


<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- DW6 -->
<head>        
    <!-- Copyright 2005 Macromedia, Inc. All rights reserved. -->
    <title>Restaurante - Reserva</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" href="../../css/estiloMenu.css" type="text/css" />
    <link rel="stylesheet" href="../../css/mm_restaurant1.css" type="text/css" />
    <link rel="stylesheet" href="../../css/estiloGeneral.css" type="text/css" />
</head>
<!-- ------------------------------------------------------------------------ -->
    <!-- Idioma -->
        <c:set var="idioma" value="${sessionScope.idiomaPagina}"></c:set>
        <c:if test="${idioma == null}">
            <c:set var="idioma" value="en_EN"></c:set>
        </c:if>
        <fmt:setLocale value="${idioma}"/>
        <fmt:setBundle basename="RbaEtiquetas" />
        <!-- Manejo de Excepciones -->
<!-- ---------------------------------------------------------------------------- -->
<body>   
    <jsp:include page="${path}/plantillas/plantillaMenu.jsp" flush="true" />
    
    
    <!-- INICIALIZAMOS VARIABLES Y RECUPERAMOS ATRIBUTOS DEL REQUEST-->
    <c:set var="path" value="${sessionScope.ruta}" />    
    <!-- variable inicializada a cero, que nos indica la posicion en la select para su paginacion
    -->
         
         <c:set var="inicio" value="${0}" />
    
         <!--// variable inicializada a vacio, y es el valor que va a toma de la caja de texto de busqueda
         // si no se hace nada aunque se cambie el como de campo busqueda, no buscaria nada
         // ya que no hay ningun valor
         -->


         <c:set var="inicio" value="${requestScope.paginado}" />
        <c:if test="${inicio == null}"> 
            <c:set var="inicio" value="${0}" />
        </c:if>
               
         <!-- variable inicializada a CORREO que es el campo por el que se va a identificar al cliente
         // si no se toca el combo de eleccion de campo de busqueda siempre se ordenara por CORREO
         // si se tocara el desplegable este valor cambiaria al elegido
         -->
        <c:set var="campo" value="${requestScope.campoBuscado}" />
        <c:if test="${campo == null}"> 
            <c:set var="campo" value="codigo" />
        </c:if>
        <!--// variable inicializada a vacio, y es el valor que va a toma de la caja de texto de busqueda
         // si no se hace nada aunque se cambie el como de campo busqueda, no buscaria nada
         // ya que no hay ningun valor
         -->
        <c:set var="textoabuscar" value="${requestScope.textoBuscado}" />
        <c:if test="${textoabuscar == null}"> 
            <c:set var="textoabuscar" value="" />
        </c:if>

        <c:set var="reservas" value="${requestScope.ejecutarReservas}" />
    <div id="fondoGeneral">
    
        <div class="tituloSubmenu">
            <div id="submenu" onclick="${path}/AccionCliente?accion=<%=3%>" >
                <div>
                    <a class="center" href="${path}/AccionReserva?accion=<%=3%>" title="Nueva Reserva" ><fmt:message key="AnadirReserva" /></a>
                </div>
            </div>
            <div class="tituloPagina">
                <fmt:message key="GestionReservas" bundle="${base}" />
            </div>
        </div>  
    
    <!--Recuperamos el campo de busqueda para mostrarlo, si hemos realizado una busqueda-->
    <c:set var="busqueda" value="${requestScope.textoBuscado}" />
    <c:set var="campoBusqueda" value="${requestScope.campoBuscado}" />

    <c:if test="${busqueda == null}">
        <c:set var="busqueda" value="" />
    </c:if>
     <c:if test="${campoBusqueda == null}">
        <c:set var="campoBusqueda" value="${campo}" />
    </c:if>                          
                    
        <form id="form1" name="form1" method="post" action="${path}/AccionReserva?accion=${7}">

            <div class="intro_datos">
                <span class="fuente"><fmt:message key="Campos" />:</span>
                <select  name="CmbCampos" id="CmbCampos">

                    <option value="Codigo" <c:if test="${campoBusqueda == 'Codigo'}"> selected</c:if> ><fmt:message key="Codigo" /></option>
                    <option value="Fecha" <c:if test="${campoBusqueda == 'Fecha'}"> selected</c:if> ><fmt:message key="Fecha" /></option>
                    <option value="Hora" <c:if test="${campoBusqueda == 'Hora'}"> ${'selected'}</c:if> ><fmt:message key="Hora" /></option>
                    <option value="Turno" <c:if test="${campoBusqueda == 'Turno'}"> selected</c:if> ><fmt:message key="Turno" /></option>
                    <!--<option value="Usuario" <c:if test="${campoBusqueda == 'Usuario'}"> selected</c:if> ><fmt:message key="usuario" /></option>-->
                    <option value="Cliente" <c:if test="${campoBusqueda == 'Cliente'}"> selected</c:if> ><fmt:message key="Cliente" /></option>
                    <option value="Mesa" <c:if test="${campoBusqueda == 'Mesa'}"> selected</c:if> ><fmt:message key="Mesa" /></option>
                    

                </select>
                <span class="fuente"><fmt:message key="Textoabuscar" />:</span>
                <input name="txtbuscar" type="text" value="${busqueda}"/>
                 <input type="submit" name="submit" id="BtnBuscar" value="<fmt:message key="Buscar" />" />
          </div>
        </form>
            <br />
            <span class="resultado">
                <c:set var="resultado" value="${requestScope.operacion}" />
                    <c:if test="${resultado != null}">
                        <c:choose>
                            <c:when test="${resultado == 0}"><fmt:message key="insertadoCorrecto" /></c:when>
                            <c:when test="${resultado == 1}"><fmt:message key="eliminadoCorrecto" /></c:when>
                            <c:when test="${resultado == 2}"><fmt:message key="modificadoCorrecto" /></c:when>
                            <c:when test="${resultado == 3}"><fmt:message key="errorInsertar" /></c:when>
                            <c:when test="${resultado == 4}"><fmt:message key="errorEliminar" /></c:when>
                            <c:when test="${resultado == 5}"><fmt:message key="errorModificar" /></c:when>
                        </c:choose>
                    </c:if>
            </span>
            
        <!------------------------------------>
        <table id="tablaGeneral">

            <tr>
                <th><a href="${path}/AccionReserva?campo=codigo&accion=8"><fmt:message key="Codigo" /></a></th>
                <th><a href="${path}/AccionReserva?campo=fecha&accion=8"><fmt:message key="Fecha" /></a></th>
                <th><a href="${path}/AccionReserva?campo=hora&accion=8"><fmt:message key="Hora" /></a></th>
                <th><a href="${path}>/AccionReserva?campo=turno&accion=8"><fmt:message key="Turno" /></a></th>
                <!--<th><a href="${path}/AccionReserva?campo=usuario&accion=8"><fmt:message key="usuario" /></a></th>-->
                <th><a href="${path}/AccionReserva?campo=cliente&accion=8"><fmt:message key="Cliente" /></a></th>
                <th><a href="${path}/AccionReserva?campo=mesa&accion=8"><fmt:message key="Mesa" /></a></th>
            </tr>
            <c:forEach var="reserva" items="${reservas}">        
                <tr>
                    <td>${reserva.codigo}</td>
                    <td>${reserva.fecha}</td>
                    <td>${reserva.hora}</td>
                    <td>${reserva.turno}</td>
                    <!--<td>${reserva.usuario}</td>-->
                    <td>${reserva.cliente}</td>
                    <td>${reserva.mesa}</td>
                     
                    <td><div><a href="${path}/AccionReserva?accion=<%=1%>&codigo=${reserva.codigo}" title="Eliminar Reserva"><fmt:message key="Eliminar" /></a></div></td>
                    <td><div><a href="${path}/AccionReserva?accion=<%=2%>&codigo=${reserva.codigo}" title="Modificar Reserva"><fmt:message key="Modificar" /></a></div></td>
                </tr>

                <tr></c:forEach>
      </table>
            <!------------------------------------>
             <c:set var="numeroPaginas" value="${requestScope.totalpaginas}" />
             <span class="paginacion">       
                 <c:forEach begin="0" end="${numeroPaginas}" step="1" var="pagina">
                   <c:choose>
                     <c:when test="${pagina != inicio}">

                            <a href="${path}/AccionReserva?accion=7&paginado=${pagina}&CmbCampos=${campo}&txtbuscar=${textoabuscar}" target="_self">${pagina}</a>                     </c:when>
                    <c:otherwise>
                        
                            ${pagina}                    </c:otherwise>
                  </c:choose>
                      <c:if test="${pagina != numeroPaginas}">
                        -                      </c:if>              
                </c:forEach>
            </span>
			<div class="inscripcion"></div>       
        <!------------------------------------>
        <span class="rightGeneral"><fmt:message key="Graciasporutilizar" /></span>    </div>
        <div class="lineaAzul"></div>
</body>
</html>

