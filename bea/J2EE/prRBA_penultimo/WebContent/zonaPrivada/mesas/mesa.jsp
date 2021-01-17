<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" errorPage="../../error.jsp"%>
<%@ page import="java.lang.*"%>
<%@ page import="rba.bbdd.*" %>
<%@ page import="java.util.*" %>
<%@ page import="rba.mesas.AccionMesa" %>


<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <!-- DW6 -->
    <head>        
        <!-- Copyright 2005 Macromedia, Inc. All rights reserved. -->
        <title>Restaurante - Texto</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <link rel="stylesheet" href="../../css/estiloMenu.css" type="text/css" />
        <link rel="stylesheet" href="../../css/mm_restaurant1.css" type="text/css" />
        <link rel="stylesheet" href="../../css/estiloGeneral.css" type="text/css" />
    </head>
    <!-- Idioma -->
        <c:set var="idioma" value="${sessionScope.idiomaPagina}"></c:set>
        <c:if test="${idioma == null}">
            <c:set var="idioma" value="en_EN"></c:set>
        </c:if>
        <fmt:setLocale value="${idioma}"/>
        <fmt:setBundle basename="RbaEtiquetas" /> 
   
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

        <c:set var="mesas" value="${requestScope.ejecutarMesas}" />
        <div id="fondoGeneral">
            
            
                 <div class="tituloSubmenu">
                    <div id="submenu" onclick="${path}/AccionMesa?accion=<%=3%>" >
                      <div>
                          <a class="center" href="${path}/AccionMesa?accion=<%=3%>" title="<fmt:message key="AnadirMesa" bundle="${base}" />" ><fmt:message key="AnadirMesa" bundle="${base}" /></a>
                      </div>
                   </div>
                    <div class="tituloPagina">
                        <fmt:message key="GestionMesas" bundle="${base}" />
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
                    
            <form id="form1" name="form1" method="post" action="${path}/AccionMesa?accion=${7}">
                
                <div class="intro_datos">
                    
                    <span class="fuente"><fmt:message key="Campos" />:</span>
                    <select  name="CmbCampos" id="CmbCampos">
                        
                        <option value="Codigo" <c:if test="${campoBusqueda == 'Codigo'}"> selected</c:if> ><fmt:message key="Codigo" bundle="${base}" /></option>
                        <option value="NumSillas" <c:if test="${campoBusqueda == 'NumSillas'}"> selected</c:if> ><fmt:message key="NumSillas" bundle="${base}" /></option>
                        
                    </select>       
                    <span class="fuente"><fmt:message key="Textoabuscar" />:</span>
                    <input name="txtbuscar" type="text" value="${busqueda}"/>
                    <input type="submit" name="submit" id="BtnBuscar" value="<fmt:message key="Buscar" />" />
                    
                </div>
                <br />
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
            
            <table id="tablaGeneral">
                
                <tr>
                    <th><a href="${path}/AccionMesa?campo=codigo&accion=8"><fmt:message key="Codigo" bundle="${base}" /></a></th>
                    <th></th>
                    <th><a href="${path}/AccionMesa?campo=numSillas&accion=8"><fmt:message key="NumSillas" bundle="${base}" /></a></th>
                </tr>
                <c:forEach var="mesa" items="${mesas}">   
                    
                    <tr>
                        <td>${mesa.codigo}</td>
                        <td><img src="template/mesa.png"/></td>
                        <td>
                            <c:forEach begin="1" end="${mesa.sillas}">  
                                <img src="template/silla.png"/>
                            </c:forEach>                          
                        </td>
                        
                        <td><div><a href="${path}/AccionMesa?accion=<%=1%>&codigo=${mesa.codigo}" title="Eliminar Mesa"><fmt:message key="Eliminar" bundle="${base}" /></a></div></td>
                        <td><div><a href="${path}/AccionMesa?accion=<%=2%>&codigo=${mesa.codigo}" title="Modificar Mesa"><fmt:message key="Modificar" bundle="${base}" /></a></div></td>
                    </tr>
                    
                </c:forEach>
                </table>
                <!------------------------------------>
             <c:set var="numeroPaginas" value="${requestScope.totalpaginas}" />
            <span class="paginacion">       
                <c:forEach begin="0" end="${numeroPaginas}" step="1" var="pagina">
                    <c:choose>
                        <c:when test="${pagina != inicio}">
                        
                            <a href="${path}/AccionMesa?accion=7&paginado=${pagina}&CmbCampos=${campo}&txtbuscar=${textoabuscar}" target="_self">${pagina}</a>                     </c:when>
                        <c:otherwise>
                            
                            ${pagina}                    </c:otherwise>
                    </c:choose>
                    <c:if test="${pagina != numeroPaginas}">
                    -                      </c:if>              
                </c:forEach>
            </span>
            <!------------------------------------>
            <span class="rightGeneral">Gracias por utilizar RBA 1.0</span>
        </div>
        <div class="lineaAzul"></div>
    </body>
</html>

