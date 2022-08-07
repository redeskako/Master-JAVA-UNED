<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "java.util.ArrayList"%>
<%@ page import = "uned.java2016.apitwitter.dao.Tweet"%>
<%@ page import = "uned.java2016.apitwitter.dao.ClinicalStudy"%>
<%@ taglib uri="WEB-INF/ViewIndexTag.tld" prefix="usuario" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
		<link href="css/ViewIndex.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="js/ViewIndex.js"></script>
		<title>Vista Clientes</title>
	</head>
	<body>
		<usuario:texto_prueba/>
		<usuario:inicializar_valores/>
	<% 
	String usuario = (String) session.getAttribute("idSesion");
	//System.out.println("EL USUARIO ES: "+usuario);
	if (usuario != null){
		//Crear la lista con los hashtag de la tabla y ver cual ha sido elegido por el usuario
		ArrayList<String> listaHashtags=(ArrayList<String>)request.getAttribute("miListaHashtagsHTML");
		String miHashtag=(String)request.getAttribute("queHashtagElegido");
		
		//Crear los combo para elegir el primer tweet a presentar y cuantos en cada página.
		ArrayList<Integer> listaPrimeros=new ArrayList<Integer>();
		listaPrimeros.add(0,1);
		listaPrimeros.add(1,40);
		listaPrimeros.add(2,80);
		listaPrimeros.add(3,120);
		listaPrimeros.add(4,160);
		listaPrimeros.add(5,200);
		ArrayList<Integer> listaCuantos=new ArrayList<Integer>();
		listaCuantos.add(0,5);
		listaCuantos.add(1,25);
		listaCuantos.add(2,50);
		listaCuantos.add(3,75);
		listaCuantos.add(4,100);
		//Si no hay hashtags, porque es la primera vez que se carga la página se cargan de la BBDD
		if(listaHashtags==null){ %>
			<script type='text/javascript'>window.location='APITwitterView';</script>
		<% }
		else{//Si ya los tenemos empezamos a crear la página
		%>
			<!--  <div id="faja">
				<header>
				</header>
				<div id="centro">
					<div id="capa_buscar" class="misZonas">
						<div id="zonaApiTwitter">
							<img src="imagenes/zona_buscar/ApiTwitter.png" alt="ApiTwitter" />
						</div>
						<div id="zonaHashtag">
								<h5>Hashtag:</h5>
								<select name="buscadorHastags" id="buscadorHashtags" class="select_busqueda">
									<option value="" disabled="disabled" selected="selected" class="option_inicial_busqueda">
										Introduce aquí tu búsqueda
									</option>-->
										<% /*for(int i=0;i<listaHashtags.size();++i) {
												if(miHashtag!=null){
													if(miHashtag.equals(listaHashtags.get(i))){
														out.println("<option value="+listaHashtags.get(i)+" selected='selected'>"+listaHashtags.get(i)+"</option>");
													}
													else{
														out.println("<option value="+listaHashtags.get(i)+">"+listaHashtags.get(i)+"</option>");
													}
												}
												else{
													out.println("<option value="+listaHashtags.get(i)+">"+listaHashtags.get(i)+"</option>");
												}
											}
											out.println("</select>");*/
										%>
						<!--  </div>
						<div id="zonaUsuario">
							<h5>Usuario: &#160;</h5>
							&#160;&#160;&#160;&#160;-->
							<% 
								//out.println("<p>"+usuario+"</p>");
							%>
							<!--  &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
							&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
							<a href="APITwitterLogin?operacion=logout" target="_self">Cerrar sesión</a>
						</div>-->
					</div>
					<%	//Si ya tenemos un hashtag elegido creamos la zona de los enlaces
						/*if(miHashtag!=null){*/%>
							<script type='text/javascript'>crearEnlaces();</script>
						<%
						//}
						//ArrayList<Tweet> listaTweets = (ArrayList<Tweet>)request.getAttribute("miListaTweets");
						
						//if(listaTweets!=null){%>
							<script type='text/javascript'>borrarTweetsAnteriores();</script>
						<% 
							//String ErrorenPrimero=(String)request.getAttribute("hayErrorenPrimero");
							//Si hay error de que buscamos el primer tweet que no tenemos
							/*if(ErrorenPrimero.equals("Si")){
								out.println("<div id='capa_errores1' class='misZonas'>");
									out.println("<p>ERROR: El primer Registro ha mostrar es superior al número total de registos</p>");
									out.println("<p>Se muestran los registros desde el primero</p>");
								out.println("</div>");
							}*/
							
							//Crear los combo para elegir el primer tweet a escribir y cuantos en cada página
							/*String myfirst=(String)request.getAttribute("quePrimeroElegido");
							int miPrimero=Integer.parseInt(myfirst);
							String myHowMany=(String)request.getAttribute("queCuantosElegido");
							int miCuantos=Integer.parseInt(myHowMany);
							int miPagina=0;
							String myPagina=(String)request.getAttribute("quePagina");
							if(myPagina!=null){
								miPagina=Integer.parseInt(myPagina);
							}
							out.println("<div id='capa_principal' class='misZonas'>");
						
								out.println("<div id='zonaResultadosTitulo'>");
									out.println("<div id='zonaResultadosTituloTexto'><h5>RESULTADOS</h5></div>");
									out.println("<div id='zonaResultadosTituloSelect'>");
										out.println("<p>Primer Tweet</p>&#160;");
										out.println("<select id='selectPrimero'>");
											for(int i=0;i<listaPrimeros.size();++i){
												if(miPrimero==listaPrimeros.get(i)){
													out.println("<option value='"+listaPrimeros.get(i)+"' selected='selected'>"+listaPrimeros.get(i)+"</option>");
												}
												else{
													out.println("<option value='"+listaPrimeros.get(i)+"'>"+listaPrimeros.get(i)+"</option>");
												}
											}										
										out.println("</select>");
										out.println("&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;<p>Número Tweets</p>&#160;");
										out.println("<select id='selectNumeroTweets'>");
											for(int i=0;i<listaCuantos.size();++i){
												if(miCuantos==listaCuantos.get(i)){
													out.println("<option value='"+listaCuantos.get(i)+"' selected='selected'>"+listaCuantos.get(i)+"</option>");
												}
												else{
													out.println("<option value='"+listaCuantos.get(i)+"'>"+listaCuantos.get(i)+"</option>");
												}
											}	
										out.println("</select>");
									out.println("</div>");
								out.println("</div>");
								//Poner los Tweets en pantalla
									out.println("<div id='zonaResultadosGeneral'>");
										for(int i=miPagina*20;i<(miPagina+1)*20;++i) {
											if(i<listaTweets.size()){
												out.println("<div id='zonaResultadoParticular"+i+"' class='zonaResultadoParticular'>");
										    	out.println("<div id='zonaID"+i+"' class='zonasID'><h5>TweetID:&#160;&#160;</h5><p>"+listaTweets.get(i).getId()+"</p></div>");
										    	out.println("<div id='zonaAutor"+i+"' class='zonasAutor'><h5>Autor:&#160;&#160;</h5><p>"+listaTweets.get(i).getUser().getName()+"</p></div>");
										    	out.println("<div id='zonaUrl"+i+"' class='zonasUrl'><h5>URL:&#160;&#160;</h5><a href='"+listaTweets.get(i).getUrl().getUrl()+"' target='_blank'>"+listaTweets.get(i).getUrl().getUrl()+"</a></div>");
										    	out.println("<div id='zonaTexto"+i+"' class='zonasTexto'><h5>Texto:&#160;&#160;</h5><p>"+listaTweets.get(i).getText()+"</p></div>");
										    	out.println("</div>");
											}
										}*/
									//Paginación%>
										<script type='text/javascript'>crearPaginacion();</script>
									<%
									/*out.println("</div>");
							out.println("</div>");
						}*/
						//Poner los vecinos en pantalla
						/*ArrayList<String> misNeighbors = (ArrayList<String>)request.getAttribute("Neighbors");
						if(misNeighbors!=null){*/%>
							<script type='text/javascript'>borrarVecinosAnteriores();</script>
						<% 
							/*out.println("<div id='capa_principal' class='misZonas'>");
								out.println("<div id='zonaResultadosTitulo'><h5>RESULTADOS</h5></div>");
									out.println("<div id='zonaResultadosGeneral'>");
									for(int i=0;i<misNeighbors.size();++i) {
										out.println("<div id='zonaResultadoParticular"+i+"' class='zonaResultadoParticularEnlaces'>");
										out.println("<div id='zonaID"+i+" class='zonasID'><h5>Neighbor:&#160;&#160;</h5><a href='APITwitterView?enlace=Tweets&queHashtag="+misNeighbors.get(i)+"'>"+misNeighbors.get(i)+"</a></div>");
								    	out.println("</div>");
									}
									out.println("</div>");
								out.println("</div>");
						}*/
						%>
						<%//Poniendo los estudios en pantalla
						ArrayList<ClinicalStudy> listaEstudios = (ArrayList<ClinicalStudy>)request.getAttribute("Estudios");
						
						if(listaEstudios!=null){%>
							<!-- <script type='text/javascript'>borrarTweetsAnteriores();</script> -->
						<% 
							//Crear los combo para elegir el primer tweet a escribir y cuantos en cada página
							String myfirst=(String)request.getAttribute("quePrimeroElegido");
							int miPrimero=Integer.parseInt(myfirst);
							String myHowMany=(String)request.getAttribute("queCuantosElegido");
							int miCuantos=Integer.parseInt(myHowMany);
							int miPagina=0;
							String myPagina=(String)request.getAttribute("quePagina");
							if(myPagina!=null){
								miPagina=Integer.parseInt(myPagina);
							}
							out.println("<div id='capa_principal' class='misZonas'>");
								out.println("<div id='zonaResultadosTitulo'>");
									out.println("<div id='zonaResultadosTituloTexto'><h5>RESULTADOS</h5></div>");
									out.println("<div id='zonaResultadosTituloSelect'>");
									out.println("<p>Primer Estudio</p>&#160;");
									out.println("<select id='selectPrimeroEstudio'>");
										for(int i=0;i<listaPrimeros.size();++i){
											if(miPrimero==listaPrimeros.get(i)){
												out.println("<option value='"+listaPrimeros.get(i)+"' selected='selected'>"+listaPrimeros.get(i)+"</option>");
											}
											else{
												out.println("<option value='"+listaPrimeros.get(i)+"'>"+listaPrimeros.get(i)+"</option>");
											}
										}										
									out.println("</select>");
									out.println("&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;<p>Número Estudios</p>&#160;");
									out.println("<select id='selectNumeroEstudios'>");
										for(int i=0;i<listaCuantos.size();++i){
											if(miCuantos==listaCuantos.get(i)){
												out.println("<option value='"+listaCuantos.get(i)+"' selected='selected'>"+listaCuantos.get(i)+"</option>");
											}
											else{
												out.println("<option value='"+listaCuantos.get(i)+"'>"+listaCuantos.get(i)+"</option>");
											}
										}	
									out.println("</select>");
								out.println("</div>");
							out.println("</div>");
									//Poniendo los estudios en pantalla
									System.out.println("mipagina "+miPagina);
									out.println("<div id='zonaResultadosGeneral'>");
									for(int i=miPagina*20;i<(miPagina+1)*20;++i) {
										if(i<listaEstudios.size()){
											out.println("<div id='zonaResultadoParticular"+i+"' class='zonaResultadoParticularEstudios'>");
										    out.println("<div id='zonaNTCID"+i+"' class='zonasNTCID'><h5>NTC_ID:&#160;&#160;</h5><a href='APITwitterOBJ?queEstudio="+listaEstudios.get(i).getNctId()+"'>"+listaEstudios.get(i).getNctId()+"</a></div>");
										    out.println("<div id='zonaTituloInforme"+i+"' class='zonasTituloInforme'><h5>Título:&#160;&#160;</h5><p>"+listaEstudios.get(i).getOfficialTitle()+"</p></div>");
										    out.println("<div id='zonaResumen"+i+"' class='zonasResumen'><h5>Resumen:&#160;&#160;</h5><p>"+listaEstudios.get(i).getBriefSummary()+"</p></div>");
										    out.println("<div id='zonaFecha"+i+"' class='zonasFecha'><h5>Fecha:&#160;&#160;</h5><p>"+listaEstudios.get(i).getLastChangedDate()+"</p></div>");
										    out.println("<div id='zonaOrganizacion"+i+"' class='zonasOrganizacion'><h5>Organización:&#160;&#160;</h5><p>"+listaEstudios.get(i).getOrganization()+"</p></div>");
										    out.println("</div>");
										}
									}
									%>
										<script type='text/javascript'>crearPaginacionEstudios();</script>
									<%
									out.println("</div>");
							out.println("</div>");
					}%>
				</div>
				<footer>
				</footer>
			</div>
		<% }
	}
	else{
		response.sendRedirect("./index.jsp?estado=ilegal");
	}
		%>
	</body>
</html>