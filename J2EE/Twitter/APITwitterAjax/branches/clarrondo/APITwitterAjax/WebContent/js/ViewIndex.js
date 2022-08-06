/** Js para dar dinamismo a la página
 * @ autor equipo UNED 2016
 * @ version 1.0
 * @ fecha 2016/05/21
 * @ licencia
 */
/** window.onload espera a que se cargue la página antes de hacer nada
	 * @ in 
	 * @ out 
	 * @ error
	 */
window.onload=function(){
	eventos();
}

/** eventos crea los listeners para los tres combos
 * @ in 
 * @ out 
 * @ error
 */
function eventos(){
	var miSelect=document.getElementById("buscadorHashtags");
	miSelect.addEventListener("change",crearEnlaces);

	if(document.getElementById("selectPrimeroEstudio")){
		var miSelectPrimerEstudio=document.getElementById("selectPrimeroEstudio");
		miSelectPrimerEstudio.addEventListener("change",cambiarVisionEstudio);
		var miSelectCuantosEstudios=document.getElementById("selectNumeroEstudios");
		miSelectCuantosEstudios.addEventListener("change",cambiarVisionEstudio);
	}
}

/** crearEnlaces crea la zona de enlaces de manera dinámica
 * @ in 
 * @ out 
 * @ error
 */
function crearEnlaces(){
	if (document.getElementById("capa_enlaces"))
	{
		var nodeA=document.getElementById("capa_enlaces");
		nodeA.parentNode.removeChild(nodeA);
	}
	
	var miSelect=document.getElementById("buscadorHashtags");
	var queHashtag=miSelect.options[miSelect.selectedIndex].value;
	
	var centro=document.getElementById("centro");
	var capa_enlaces=document.createElement("div");
	centro.appendChild(capa_enlaces);
	capa_enlaces.setAttribute("id","capa_enlaces");
	capa_enlaces.setAttribute("class","misZonas");
	
	var zonaTitulo=document.createElement("div");
	capa_enlaces.appendChild(zonaTitulo);
	zonaTitulo.setAttribute("id","zonaTitulo");
	var zonaTituloTitulo=document.createElement("h5");
	zonaTitulo.appendChild(zonaTituloTitulo);
	zonaTituloTitulo.innerHTML="HASHTAG: ";
	var zonaTituloTexto=document.createElement("p");
	zonaTitulo.appendChild(zonaTituloTexto);
	zonaTituloTexto.setAttribute("id","mensajeBuscando");
	zonaTituloTexto.innerHTML="&#160;&#160;"+queHashtag;

	
	var zonaEnlaces=document.createElement("div");
	capa_enlaces.appendChild(zonaEnlaces);
	zonaEnlaces.setAttribute("id","zonaEnlaces");
	
	var zonaClinicos=document.createElement("div");
	zonaEnlaces.appendChild(zonaClinicos);
	zonaClinicos.setAttribute("id","zonaClinicos");
	zonaClinicos.setAttribute("class","enlaces");
	var miEnlaceClinicos=document.createElement("a");
	zonaClinicos.appendChild(miEnlaceClinicos);
	miEnlaceClinicos.setAttribute("id","miEnlaceClinicos");
	miEnlaceClinicos.setAttribute("href","APITwitterView?enlace=Estudios&primero=1&cuantos=5&queHashtag="+queHashtag);
	miEnlaceClinicos.innerHTML="Estudios Cl&#237;nicos";
	
	var zonaTweets=document.createElement("div");
	zonaEnlaces.appendChild(zonaTweets);
	zonaTweets.setAttribute("id","zonaTweets");
	zonaTweets.setAttribute("class","enlaces");
	var botonTweets=document.createElement("BUTTON");
	zonaTweets.appendChild(botonTweets);
	botonTweets.setAttribute("id","botonTweets");
	var t=document.createTextNode("TWEETS");
	botonTweets.appendChild(t);
	botonTweets.setAttribute("onclick","buscarTweets()");
	
	var zonaOtros=document.createElement("div");
	zonaEnlaces.appendChild(zonaOtros);
	zonaOtros.setAttribute("id","zonaOtros");
	zonaOtros.setAttribute("class","enlaces");
	var miEnlaceOtros=document.createElement("a");
	zonaOtros.appendChild(miEnlaceOtros);
	miEnlaceOtros.setAttribute("id","miEnlaceOtros");
	miEnlaceOtros.setAttribute("href","APITwitterView?enlace=Vecinos&queHashtag="+queHashtag);
	miEnlaceOtros.innerHTML="Hashtags Relacionados";
	
	borrarTweetsAnteriores();
}

/** bucarTweets llama a la case dwr para buscar los tweets
 * @ in 
 * @ out 
 * @ error
 */
function buscarTweets(){
	var primero=1;
	var cuantos=5;
	var miSelect=document.getElementById("buscadorHashtags");
	var queHashtag=miSelect.options[miSelect.selectedIndex].value;
	
	GetTweets.cargarTweets(queHashtag,
			function(data){
		//alert("DATOS "+data.length);
				/*for(i=0;i<data.length;++i){
					var testObj=data[i];
				    //Probando
				    alert("ID: "+testObj.tweet_id);
				    alert("Autor: "+testObj.user.name);
				    alert("Text: "+testObj.text);
				    alert("Url: "+testObj.url.url);
				}*/
		});
	setTimeout("location.href='ViewIndex.jsp'",1000);	
}

/** borrarTweetsAnteriores elimina las anteriores búsquedas de tweets
 * @ in 
 * @ out 
 * @ error
 */
function borrarTweetsAnteriores(){
	if (document.getElementById("capa_principal"))
	{
		var nodeA=document.getElementById("capa_principal");
		nodeA.parentNode.removeChild(nodeA);
	}
}
/** borrarVecinosAnteriores elimina las anteriores búsquedas de vecinos
 * @ in 
 * @ out 
 * @ error
 */
function borrarVecinosAnteriores(){
	if (document.getElementById("capa_principal"))
	{
		var nodeA=document.getElementById("capa_principal");
		nodeA.parentNode.removeChild(nodeA);
	}
}

/** cambiarVision refresca la pantalla al cambiar los estudios
 * @ in 
 * @ out 
 * @ error
 */
function cambiarVisionEstudio(){
	var miSelect=document.getElementById("buscadorHashtags");
	var queHashtag=miSelect.options[miSelect.selectedIndex].value;
	var selectPrimeroEstudio=document.getElementById("selectPrimeroEstudio");
	var quePrimeroEstudio=selectPrimeroEstudio.options[selectPrimeroEstudio.selectedIndex].value;
	var miSelectCuantosEstudios=document.getElementById("selectNumeroEstudios");
	var queCuantosEstudios=miSelectCuantosEstudios.options[miSelectCuantosEstudios.selectedIndex].value;
	window.location.href="APITwitterView?enlace=Estudios&cuantos="+queCuantosEstudios+"&queHashtag="+queHashtag+"&primero="+quePrimeroEstudio;
	return false;
}

/** crearPaginacion crea los número para avanzar de página estudios
 * @ in 
 * @ out 
 * @ error
 */
function crearPaginacionEstudios(){
	var miSelect=document.getElementById("buscadorHashtags");
	var queHashtag=miSelect.options[miSelect.selectedIndex].value;
	var mizonaResultadosGeneral=document.getElementById("zonaResultadosGeneral");
	var mizonaPaginacion=document.createElement("div");
	mizonaResultadosGeneral.appendChild(mizonaPaginacion);
	mizonaPaginacion.setAttribute("id","zonaPaginacion");
	
	var selectPrimeroEstudio=document.getElementById("selectPrimeroEstudio");
	var quePrimero=selectPrimeroEstudio.options[selectPrimeroEstudio.selectedIndex].value;
	var miSelectCuantosEstudios=document.getElementById("selectNumeroEstudios");
	
	var queCuantos=miSelectCuantosEstudios.options[miSelectCuantosEstudios.selectedIndex].value;
	
	var numPaginas=Math.floor(queCuantos/20);
	var resto=queCuantos-(numPaginas*20);

	if(numPaginas!=0){
		var numeros=new Array();
		for(u=0;u<numPaginas;++u){
			numeros[u]=document.createElement("div");
			mizonaPaginacion.appendChild(numeros[u]);
			numeros[u].setAttribute("id","zonaPaginacion"+u);
			numeros[u].innerHTML="<a href='APITwitterView?enlace=Estudios&pagina="+u+"&cuantos="+queCuantos+"&queHashtag="+queHashtag+"&primero="+quePrimero+"' class='zonaNumeros'>"+(u+1)+"</a>";
		}
		if(resto!=0){
			numeros[u]=document.createElement("div");
			mizonaPaginacion.appendChild(numeros[u]);
			numeros[u].setAttribute("id","zonaPaginacion"+u);
			numeros[u].innerHTML="<a href='APITwitterView?enlace=Estudios&pagina="+u+"&cuantos="+queCuantos+"&queHashtag="+queHashtag+"&primero="+quePrimero+"' class='zonaNumeros'>"+(u+1)+"</a>";
		}
	}
}