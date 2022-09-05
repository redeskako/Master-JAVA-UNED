<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "java.util.ArrayList"%>
<%@ page import = "uned.java2016.apitwitter.dao.Tweet"%>
<%@ page import = "uned.java2016.apitwitter.dao.ClinicalStudy"%>
<%@ taglib uri="WEB-INF/ViewIndexTag.tld" prefix="usuario" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
		<link href="Resources/css/ViewIndex.css" rel="stylesheet" type="text/css" />
		<link href="Resources/css/stylesHeaderFooter.css" rel="stylesheet" type="text/css" />

		<!--  soporte JQuery  y JQueryUI-->		
		<link href="js/lib/jquery-ui-1.12.0/jquery-ui.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="js/lib/jquery-3.1.0.min.js"></script>
		<script type="text/javascript" src="js/lib/jquery-ui-1.12.0/jquery-ui.min.js"></script>

		<!--  soporte DWR para componente HashtagSearchDWR -->
		<script type='text/javascript' src='/APITwitterWeb/services/dwr/engine.js'></script>
  		<script type='text/javascript' src='/APITwitterWeb/services/dwr/interface/HashtagSearchDWR.js'></script>
  		<script type='text/javascript' src='/APITwitterWeb/services/dwr/util.js'></script>		
		
		<script type="text/javascript" src="js/ViewIndex.js"></script>
		
		<title>Vista Clientes</title>
	</head>
	<body>
		<usuario:inicializar_valores/>
		<div id="faja">
				<%@ include file="Resources/html/encabezado.html" %>
				<div id="centro">
					<div id="capa_buscar" class="misZonas">
						<div id="zonaApiTwitter">
							<img src="imagenes/zona_buscar/ApiTwitter.png" alt="ApiTwitter" />
						</div>
						<div id="zonaHashtag">
							<h5>Hashtag:</h5>
							<input id="asistenteHashtag"/>
							<select name="buscadorHastags" id="buscadorHashtags" class="select_busqueda">
								<option value="" disabled="disabled" selected="selected" class="option_inicial_busqueda">
									Introduce aquí tu búsqueda
								</option>
								<usuario:bucle_hashtags/>
								<c:if test="${listaH!=null}">
									<c:forEach var="i" begin="1" end="${listaH.size()}">
										<c:choose>
										    <c:when test="${elHashtag!=null}">
										    	<c:choose>
										    		<c:when test="${elHashtag==listaH.get(i-1)}">
										    			<option value="${listaH.get(i-1)}" selected="selected"><c:out value="${listaH.get(i-1)}"/></option>
										    		</c:when>
										    		<c:otherwise>
										    			<option value="${listaH.get(i-1)}"><c:out value="${listaH.get(i-1)}"/></option>
										    		</c:otherwise>
										       	</c:choose>
										    </c:when>
										    <c:otherwise>
										        <option value="${listaH.get(i-1)}"><c:out value="${listaH.get(i-1)}"/></option>
										    </c:otherwise>
										</c:choose>
									</c:forEach>
								</c:if>								
							</select>
						</div>
						<div id="zonaUsuario">
							<h5>Usuario: &#160;</h5>
							&#160;&#160;&#160;&#160;
							<p>
								<c:out value="${elUsuario}" />
							</p>
							&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
							&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
							<a href="APITwitterLogin?operacion=logout" target="_self">Cerrar sesión</a>
						</div>
						<c:if test="${elHashtag!=null}">
							<script type="text/javascript">crearEnlaces();</script>
						</c:if>
						</div>
						
						<usuario:crear_tweets/>
						<c:if test="${losTweets!=null}">
							<script type="text/javascript">borrarTweetsAnteriores();</script>
							<c:if test="${ErrorPrimero=='Si'}">
								<div id="capa_errores1" class="misZonas">
									<p>ERROR: El primer Registro ha mostrar es superior al número total de registos</p>
									<p>Se muestran los registros desde el primero</p>
								</div>
							</c:if>
							<div id="capa_principal" class="misZonas">
								<div id="zonaResultadosTitulo">
									<div id="zonaResultadosTituloTexto">
										<h5>RESULTADOS</h5>
									</div>
									<div id="zonaResultadosTituloSelect">
										<p>Primer Tweet</p>&#160;
											<select id="selectPrimero">
												<c:forEach var="i" begin="1" end="${laListaPrimeros.size()}">
													<c:choose>
														<c:when test="${elPrimero==laListaPrimeros.get(i-1)}">
															<option value="${laListaPrimeros.get(i-1)}" selected="selected"><c:out value="${laListaPrimeros.get(i-1)}"/></option>
														</c:when>
														<c:otherwise>
															<option value="${laListaPrimeros.get(i-1)}"><c:out value="${laListaPrimeros.get(i-1)}"/></option>
														</c:otherwise>
													</c:choose>
												</c:forEach>
											</select>
											&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
											<p>Número Tweets</p>
											&#160;
											<select id="selectNumeroTweets">
												<c:forEach var="i" begin="1" end="${laListaCuantos.size()}">
													<c:choose>
														<c:when test="${elCuantos==laListaCuantos.get(i-1)}">
															<option value="${laListaCuantos.get(i-1)}" selected="selected"><c:out value="${laListaCuantos.get(i-1)}"/></option>
														</c:when>
														<c:otherwise>
															<option value="${laListaCuantos.get(i-1)}"><c:out value="${laListaCuantos.get(i-1)}"/></option>
														</c:otherwise>
													</c:choose>
												</c:forEach>
											</select>
										</div>
									</div>
									<div id='zonaResultadosGeneral'>
									<c:set var="inicio" value="${laPagina*20}"/>
										<c:forEach var="i" begin="${inicio}" end="${inicio+20}">
											<c:if test="${i<losTweets.size()}">
												<div id="zonaResultadoParticular${i}" class="zonaResultadoParticular">
													<div id="zonaID${i}" class="zonasID">
														<h5>TweetID:&#160;&#160;</h5>
														<p>${losTweets.get(i).getId()}</p>
													</div>
													<div id="zonaAutor${i}" class="zonasAutor">
														<h5>Autor:&#160;&#160;</h5>
														<p>${losTweets.get(i).getUser().getName()}</p>
													</div>
													<div id="zonaUrl${i}" class="zonasUrl">
														<h5>URL:&#160;&#160;</h5>
														<a href="${losTweets.get(i).getUrl().getUrl()}" target="_blank">${losTweets.get(i).getUrl().getUrl()}</a>
													</div>
													<div id="zonaTexto${i}" class="zonasTexto">
														<h5>Texto:&#160;&#160;</h5>
														<p>${losTweets.get(i).getText()}</p>
													</div>
												</div>
											</c:if>
										</c:forEach>
										<script type="text/javascript">crearPaginacion();</script>
									</div>
							</div>	
						</c:if>
						
						<usuario:crear_vecinos/>
						<c:if test="${losVecinos!=null}">
							<script type="text/javascript">borrarVecinosAnteriores();</script>
							<div id="capa_principal" class="misZonas2">
								<div id="zonaResultadosTitulo">
									<h5>RESULTADOS</h5>
								</div>
								<div id="zonaResultadosGeneral">
									<c:forEach var="i" begin="1" end="${losVecinos.size()}">
										<div id="zonaResultadoParticular${i}" class="zonaResultadoParticularEnlaces">
											<div id="zonaID${i}" class="zonasID">
												<h5>Neighbor:&#160;&#160;</h5>
												<a href="APITwitterView?enlace=Tweets&queHashtag=${losVecinos.get(i-1)}">${losVecinos.get(i-1)}</a>
											</div>
										</div>
									</c:forEach>
								</div>
							</div>
						</c:if>
						
						<usuario:crear_estudios/>
						<c:if test="${losEstudios!=null}">
						<script type="text/javascript">borrarTweetsAnteriores();</script>
							<div id="capa_principal" class="misZonas">
								<div id="zonaResultadosTitulo">
									<div id="zonaResultadosTituloTexto">
										<h5>RESULTADOS</h5>
									</div>
									<div id="zonaResultadosTituloSelect">
										<p>Primer Estudio</p>&#160;
											<select id="selectPrimeroEstudio">
												<c:forEach var="i" begin="1" end="${laListaPrimeros.size()}">
													<c:choose>
														<c:when test="${elPrimero==laListaPrimeros.get(i-1)}">
															<option value="${laListaPrimeros.get(i-1)}" selected="selected"><c:out value="${laListaPrimeros.get(i-1)}"/></option>
														</c:when>
														<c:otherwise>
															<option value="${laListaPrimeros.get(i-1)}"><c:out value="${laListaPrimeros.get(i-1)}"/></option>
														</c:otherwise>
													</c:choose>
												</c:forEach>
											</select>
											&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
											<p>Número Estudios</p>
											&#160;
											<select id="selectNumeroEstudios">
												<c:forEach var="i" begin="1" end="${laListaCuantos.size()}">
													<c:choose>
														<c:when test="${elCuantos==laListaCuantos.get(i-1)}">
															<option value="${laListaCuantos.get(i-1)}" selected="selected"><c:out value="${laListaCuantos.get(i-1)}"/></option>
														</c:when>
														<c:otherwise>
															<option value="${laListaCuantos.get(i-1)}"><c:out value="${laListaCuantos.get(i-1)}"/></option>
														</c:otherwise>
													</c:choose>
												</c:forEach>
											</select>
										</div>
									</div>
									<div id='zonaResultadosGeneral'>
									<c:set var="inicio" value="${laPagina*20}"/>
										<c:forEach var="i" begin="${inicio}" end="${inicio+20}">
											<c:if test="${i<losEstudios.size()}">
												<div id="zonaResultadoParticular${i}" class="zonaResultadoParticularEstudios">
													<div id="zonaNTCID${i}" class="zonasNTCID">
														<h5>NTC_ID:&#160;&#160;</h5>
														<a href="APITwitterOBJ?queEstudio=${losEstudios.get(i).getNctId()}">${losEstudios.get(i).getNctId()}</a>
													</div>
													<div id="zonaTituloInforme${i}" class="zonasTituloInforme">
														<h5>Título:&#160;&#160;</h5>
														<p>${losEstudios.get(i).getOfficialTitle()}</p>
													</div>
													<div id="zonaResumen${i}" class="zonasResumen">
														<h5>Resumen:&#160;&#160;</h5>
														<p>${losEstudios.get(i).getBriefSummary()}</p>
													</div>
													<div id="zonaFecha${i}" class="zonasFecha">
														<h5>Fecha:&#160;&#160;</h5>
														<p>${losEstudios.get(i).getLastChangedDate()}</p>
													</div>
													<div id="zonaOrganizacion${i}" class="zonasOrganizacion">
														<h5>Organización:&#160;&#160;</h5>
														<p>${losEstudios.get(i).getOrganization()}</p>
													</div>
												</div>
											</c:if>
										</c:forEach>
										<script type="text/javascript">crearPaginacionEstudios();</script>
									</div>
							</div>	
						</c:if>
						
					</div>
			<%@ include file="Resources/html/footer.html" %>			
		</div>
	</body>
</html>