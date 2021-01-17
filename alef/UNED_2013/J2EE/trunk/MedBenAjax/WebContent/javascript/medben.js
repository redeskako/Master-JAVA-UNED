var totReg = 0; 		// Total de registros devueltos por la consulta.
var titulos = new Array();
var urls = new Array();
var propuestas = new Array();	// array de propuestas de texto predictivo
var propuestas_doc = new Array();
var pag = 1; 			// Página actual.
var LONGITUD_TEXTO = 35; // para la longitud del texto de la url (ajustar al tamaño óptimo)
var LPP = 10;			// líneas por página para paginación de resultados
var xmlhttp;

function inicializarJSP() {

	document.getElementById("selectMenu").value = "MedLine";
	actualizarCampos();
	actualizarTextoPaginacion();

}

/*
 * Requisito RQ34 de la practica
 * Realizar sobre JavaScript una función que sea el listener de la tabla emergente
 *  y que ante el evento onclick sobre una de las filas haga desaparecer la tabla 
 *  y muestre el valor de la fila seleccionada en el campo de texto de b�squeda principal.
 */
function rq34(valor_tabla){
	//PARCHE PARA CORREGIR QUE EN BÚSQUEDAS PREDICTIVAS QUE DEVUELVEN CIUDADES CON
	//DOS PALABRAS, AL SELECCIONAR UNA DE ELLAS, NO LA BUSQUE POSTERIORMENTE,
	//PUESTO QUE EN LOS PARÁMETROS DE LA URL NO SE PERMITEN ESPACIOS.
	//Se toma la palabra buscada
	var campoBusqueda= document.getElementById('textPalabra');
	var txtInicial = campoBusqueda.value;
	//Se pasan a mayúsculas ambos strings para facilitar su manejo
	var valor_tabla_May = valor_tabla.toUpperCase();
	txtInicial = txtInicial.toUpperCase();
	//Se busca el índice inicial donde comienza la palabra
	var indicePalabra = valor_tabla_May.search(txtInicial);
	//Nos quedamos con la parte del string que va desde la palabra búscada hasta el final
	var txtModificado = valor_tabla_May.substring(indicePalabra, valor_tabla_May.length);

	if (txtModificado.indexOf(" ") == -1){
		//Si el resto no contiene espacios, solo tiene una palabra o si tiene
		//varias o ésta es la última, en ambos casos se toma la palabra desde
		//el índice hasta el final.
		valor_tabla = valor_tabla.substring(indicePalabra, valor_tabla.length);
	}
	else{
		//Si el resto tiene espacios, se toma la palabra que va desde el índice
		//hasta el primer espacion en blanco.
		valor_tabla = valor_tabla.substring(indicePalabra, txtModificado.indexOf(" "));
	}
	//FIN PARCHE
	campoBusqueda.value = valor_tabla;
	var campoDivEmergente = document.getElementById('divEmergente');
	campoDivEmergente.style.display = "none";
}



/* 
 * Requisito RQ4 de la práctica
 * parsea un documento XML pasado como parámetro y rellena dos
 * variables globales: titulos y urls
 * actualiza la variable global totReg con el total de resultados
 */ 
function parseDocXML(xmlDoc) {
	titulos.length = 0;
	urls.length = 0;
	totReg = 0;	
	pag = 1;
	/* Se asume que esta es la estructura del fichero XML:
		<Search>
			<title></title>
			<url></url>
			<title></title>
			<url></url>
			.../...
		</Search>
	 */
	totReg = xmlDoc.getElementsByTagName("title").length;
	var titulos_doc = xmlDoc.getElementsByTagName("title");
	var urls_doc = xmlDoc.getElementsByTagName("url");

	for(var i=0;i<totReg;i++){
		var tituloNode = titulos_doc[i].childNodes[0];
		titulos.push((tituloNode != null) ? tituloNode.nodeValue : "");		// añade título

		var urlNode = urls_doc[i].childNodes[0];
		urls.push((urlNode != null) ? urlNode.nodeValue : "");				// añade url

	}
}  

/*
 * Requisito RQ5 de la práctica
 * Rellena la tabla con el contenido de las variables globales titulos y urls
 * sustituyendo el contenido de todas las filas menos la primera (fila de títulos)
 */
function rellenaTabla() {
	var tabla = document.getElementById("tMenu");// obtiene una referencia a la tabla
	console.log(tabla);
	var trs = tabla.getElementsByTagName("tr");	// obtiene todos los tr

	// Modifica las filas de la tabla
	var elemTd1, elemTd2;
	var elemA;

	for (var i = 1; i <= LPP; i++) {			// las LPP líneas de la tabla, empezando por la 1 (la 0 son los títulos)
		var indiceArray = (LPP * (pag  - 1)) + i - 1; // calcula el índice del array, según la página en la que está
		if (indiceArray >= totReg) {			// celdas vacías
			elemTd1 = trs[i].getElementsByTagName("td")[0];
			elemTd1.childNodes[0].nodeValue = " ";				// pone un espacio en blanco en el valor del título
			elemTd2 = trs[i].getElementsByTagName("td")[1];
			elemA = elemTd2.getElementsByTagName("a")[0];
			elemA.setAttribute("href","");						// pone la url en blanco
			elemA.childNodes[0].nodeValue = " ";				//pone el texto de la url en blanco

		} else {
			elemTd1 = trs[i].getElementsByTagName("td")[0];
			elemTd1.childNodes[0].nodeValue = titulos[indiceArray];	// pone el título en su celda
			elemTd2 = trs[i].getElementsByTagName("td")[1];
			elemA = elemTd2.getElementsByTagName("a")[0];			
			elemA.setAttribute("href",urls[indiceArray]);			// asigna la url
			elemA.childNodes[0].nodeValue = recortar(urls[indiceArray]);	// pone el texto de la url
		}	
	}
}

/*
 * Función que toma un texto y lo devuelve recortado
 * al tamaño indicado en la variable global LOGITUD_TEXTO
 */
function recortar(texto) {
	if (texto.length <= LONGITUD_TEXTO) {
		return texto;
	} else {
		return ( texto.substring(0, (LONGITUD_TEXTO - 3)) + "..." );
	}
}

/*
 * Requisito RQ35 de la práctica 
 * Función que toma como parámetro un documento xml como el devuelto por el servicio web de búsqueda predictiva 
 * y lo parsea para cargar los datos en una variable glogal que almacene en un array los resultados.  
 */
function parseDocXmlPredictivo(xmlPredictivo) {
	var objCampo = document.getElementById("selectCampo");			
	var campo = objCampo[objCampo.selectedIndex].text;
	if (campo == "título"){
		campo = "Title";
	}else if (campo == "cualquier campo"){
		campo ="*";
	}else if (campo == "descripción"){
		campo ="FullSummary";
	}else if (campo == "organización"){
		campo ="Organization";
	}else if (campo == "ciudad"){
		campo ="city"; 
	}

	console.log(campo);
	console.log(xmlPredictivo);
	var propuestas_doc = xmlPredictivo.getElementsByTagName("title");	// extrae todos los elementos  

	propuestas.splice(0, propuestas.length); 							// vacía el array de propuestas

	for(var i = 0; i < propuestas_doc.length; i++){ 

		propuestas.push(propuestas_doc[i].childNodes[0].nodeValue);		// añade propuesta
	}

	escribir_tEmergente(); 
}

//Función que modifica los campos de búsqueda que se pueden 
//seleccionar según el catálogo, y también el nombre de la primera
//columna de la tabla de resultados ("Healthtopic" u "Organización").
function actualizarCampos() {

	var doc = document;
	// Array con los campos seleccionables en caso de
	// consulta sobre el XML de MedLine.
	var array_opciones_medline = new Array();
	// Array con los campos seleccionables en caso de
	// consulta sobre BD de Bennett.
	var array_opciones_bennett = new Array();
	// Variable array para almacenar uno u otro
	// seg�n el cat�logo seleccionado.
	var array_opciones;
	// Variable para almacenar el control de selecci�n del cat�logo.
	var catalogo = doc.getElementById("selectMenu");
	// Variable para almacenar el control de selecci�n del campo de
	// b�squeda.
	var campo = doc.getElementById("selectCampo");
	// Variable para almacenar el contenido del t�tulo 
	// de la primera columna de la tabla de resultados.
	var col_title = doc.getElementById("tMenu").
	getElementsByTagName("th")[0].firstChild;

	// Inicilizamos los arrays con los posibles
	// valores de los campos, seg�n hablemos de MedLine o
	// de Bennett.
	array_opciones_medline[0] = ["*", "cualquier campo"];
	array_opciones_medline[1] = ["Title", "título"];
	array_opciones_medline[2] = ["FullSummary", "descripción"];
	array_opciones_medline[3] = ["Site", "site"];
	array_opciones_medline[4] = ["Organization", "organización"];
	array_opciones_bennett[0] = ["*", "cualquier campo"];
	array_opciones_bennett[1] = ["Organization", "organización"];
	array_opciones_bennett[2] = ["State", "estado"];
	array_opciones_bennett[3] = ["City", "ciudad"];

	if (catalogo.value == "MedLine") {
		array_opciones = array_opciones_medline;
		col_title.nodeValue = "Healthtopic";
	}
	else {
		array_opciones = array_opciones_bennett;
		col_title.nodeValue = "Organización";
	}
	campo.innerHTML = "";

	// Creamos las opciones correspondientes en el control
	// de selección del campo de búsqueda.
	for (var i = 0; i < array_opciones.length; i++) {
		var opcion = doc.createElement("option");
		opcion.setAttribute("value", array_opciones[i][0]);
		var textoOpcion = doc.createTextNode(array_opciones[i][1]);
		opcion.appendChild(textoOpcion);
		campo.appendChild(opcion);
	}
}

//Función que modifica el texto que muestra
//el estado de la paginación, para conocer en
//todo momento el rango de resultados
//mostrados y el total de los mismos.
function actualizarTextoPaginacion() {
	// Almacenamos el campo de texto que debe mostrar el estado de la
	// paginación en una variable.
	var texto = document.getElementById("txtBuscaPalabra");
	var cad;

	if (totReg > 0) {
		// Si hay resultados de la búsqueda.
		var ini = (pag - 1) * 10 + 1;
		var fin = ini + 9;
		// Si estamos mostrando la última página
		if (fin > totReg) fin = totReg;
		cad = "Mostrando " + ini + " a " + fin + 
		" resultados de " + totReg;
	}
	else
		cad = "No hay resultados.";
	texto.value = cad;
}



/*esta funcion se encarga de gestionar el mostrado o no mostrado de los botones de paginacion
 */
function gestPaginacion(){

	/* supuesto 1, no se deben mostrar botones por que hay solo una pagina de datos
	 * es decir, el numero total de registros entre las paginas es menor o igual a 1, o no hay registros
	 */

	if ( (totReg == 0)  || ((totReg / LPP) <= 1) ){
		// se deshabilitan ambos botones.
		deshabilitarBoton10menos();		
		deshabilitarBoton10mas();

	}
	/*
	 * supuesto 2, es la pagina inicial y hay mas paginas (no se requiere al no haber cumplido supuesto 1), luego se muestra tan solo un boton
	 */
	else if(pag == 1) {
		deshabilitarBoton10menos();
		habilitarBoton10mas();
	}
	/*
	 * supuesto 3, es la ultima pagina
	 */
	else if(pag >= (totReg / LPP)) {
		deshabilitarBoton10mas();
		habilitarBoton10menos();
	}
	/*
	 * supuesto 4; es un punto intermedio
	 */
	else{
		habilitarBoton10menos();		
		habilitarBoton10mas();
	}
	actualizarTextoPaginacion();
}

function mas10paginacion(){
	// el elemento ini se supone que es el elemento inicial de la tabla actual, por lo tanto se le sumar� 10
	pag = pag + 1;
	gestPaginacion();
	rellenaTabla();

}

function menos10paginacion(){
	// el elemento ini se supone que es el elemento inicial de la tabla actual, por lo tanto se le sumar� 10
	pag = pag - 1;
	gestPaginacion();
	rellenaTabla();

}

function deshabilitarBoton10mas(){

	document.getElementById('btn10mas').disabled=true;

}

function habilitarBoton10mas(){

	document.getElementById('btn10mas').disabled=false;

}

function deshabilitarBoton10menos(){

	document.getElementById("btn10menos").disabled=true;

}

function habilitarBoton10menos(){

	document.getElementById("btn10menos").disabled=false;

}
/**
 * function localizaPalabra()
 * Contiene el código para localizar la palabra introducida
 */
function localizaPalabra(){

	var obj_menu = document.getElementById("selectMenu");
	var indice_menu = obj_menu.selectedIndex;
	var base = obj_menu.options[indice_menu].text;					//valor del selector de base de datos

	var palabra = document.getElementById("textPalabra").value; 

	if ((palabra == "") ||(palabra == "Palabra a buscar")){
		alert("No ha introducido ninguna palabra");
	}else{
		//comprobación del valor de la palabra
		var obj_campo = document.getElementById("selectCampo");			
		var indice_campo = obj_campo.selectedIndex;
		var campo = obj_campo.options[indice_campo].text; 				//valor del selector de campo

		if (campo == "título"){
			campo = "Title";
		}else if (campo == "cualquier campo"){
			campo ="*";
		}else if (campo == "descripción"){
			campo ="FullSummary";
		}else if (campo == "organización"){
			campo ="Organization";
		}else if (campo == "ciudad"){
			campo ="City";
		}

		//los espacios en blanco no estan permitidos en una URL
		palabra = palabra.replace(" ", "");


		//modificar la URL en funcion de la instalación de TOMCAT 
		var urltxt = "http://localhost:8080/MedLineBennettAjax/rs/v1/search?catalogo=" + base +
		"&palabra=" + palabra + "&campo=" + campo; 

		var xmlhttp;
		if (window.XMLHttpRequest) {
			// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp=new XMLHttpRequest();
		}
		else {
			// code for IE6, IE5
			xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange=function (oEvent) { 

			if (xmlhttp.readyState == 4) {
				if (xmlhttp.status == 200) {  
					xmlDoc=xmlhttp.responseXML;
					parseDocXML(xmlDoc);
					rellenaTabla();
					actualizarTextoPaginacion();
					gestPaginacion();
				} else {  
					console.log("Error", xmlhttp.statusText); 
					alert("Ha ocurrido un error durante la búsqueda. Consulte a su administrador");
					alert("error 1: " + xmlhttp.statusText);
					alert("error 2: " + xmlhttp.status);
					alert("error 3: " + xmlhttp.readyState);
				}  
			}  
		};
		xmlhttp.open("GET",urltxt,true);
		xmlhttp.send(null); 
	}//end if palabra || palabra
}//end function localizaPalabra();

function iniciarBusquedaPredictiva(ev) {
	var iniciar = false;
	// Obtenemos el código de carácter pulsado teniendo
	// en cuenta compatibilidad con todos los navegadores.
	var cod = ("which" in ev) ? ev.which : ev.keyCode;
	// Obtenemos el documento HTML
	var doc = document;
	// Obtenemos el control de selección del catálogo de búsqueda
	var catalogo = doc.getElementById("selectMenu");
	// Obtenemos el campo de texto donde se teclea la palabra
	// a buscar
	var palabra = doc.getElementById("textPalabra");

	// Obtenemos el control de selección del campo de búsqueda
	var campo = doc.getElementById("selectCampo");
	// Si el caracter pulsado es alfanumérico, el texto tecleado
	// tiene ya un mínimo de 4 caracteres, y el campo de búsqueda
	// seleccionado es "Title" de MedLine o "City" de Bennett,
	// podemos iniciar la búsqueda predictiva
	if (((cod >= "0".charCodeAt(0) && cod <= "9".charCodeAt(0)) ||
			(cod >= "A".charCodeAt(0) && cod <= "Z".charCodeAt(0)) ||
			(cod >= "a".charCodeAt(0) && cod <= "z".charCodeAt(0)) ||
			(cod == "ñ".charCodeAt(0)) || (cod == "Ñ".charCodeAt(0))) &&
			(palabra.value.length >= 4) &&
			((catalogo.value == "MedLine" && campo.value == "Title") ||
					(catalogo.value == "Bennett" && campo.value == "City"))) {
		iniciar = true;
		busquedaPredictiva();
	}
	return iniciar;
}

function busquedaPredictiva(){
	// Guardamos los datos que tenemos que pasarle al servidor
	// Palabra que se está buscando.
	var palabra = document.getElementById("textPalabra").value;

	//convierte la letra incial en mayuscula
	var letraInicial = "";
	if ((palabra.charAt(0)>="a") & (palabra.charAt(0)<="z")){
		letraInicial = palabra.charAt(0).toUpperCase() + palabra.substring(1);
		palabra = letraInicial;
	}

	var obj_menu = document.getElementById("selectMenu");
	var indice_menu = obj_menu.selectedIndex;
	// Base de datos en la que se está buscando
	var bbdd = obj_menu.options[indice_menu].text;

	//El límite lo ponemos a 6.
	var limite = 6;


	// Nos cercioramos de que no hayan espacios en la URL
	palabra = palabra.replace(" ", "");
	var urltxt = "rs/v1/startWith?catalogo=" + bbdd + "&palabra=" + palabra + "&limite=" + limite; 

	if (window.XMLHttpRequest){
		// Code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();
	}
	else{
		// Code for IE6, IE5
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}

	// Hacemos la petición.
	xmlhttp.open("GET", urltxt, true);

	xmlhttp.onreadystatechange= function(oEvent) { 
		// Completado
		if (xmlhttp.readyState == 4) {  
			// Ok
			if (xmlhttp.status == 200) {  
				console.log(xmlhttp.responseXML); 
				var xmlDoc=xmlhttp.responseXML;

				parseDocXmlPredictivo(xmlDoc); //propuesta de Juan 
			} 
			// Si ha habido algún error: lo mostramos
			// al usuario.
			else{  
				console.log("Error", xmlhttp.statusText); 
				alert("Ha ocurrido un error durante la búsqueda. Consulte a su administrador");
				//alert("Error 1: " + xmlhttp.statusText);
				//alert("Error 2: " + xmlhttp.status);
				//alert("Error 3: " + xmlhttp.readyState);
			}  
		}
	}; 
	xmlhttp.send();


}


/*
 * funcion de prueba para rellenar el array 
 * que carga la tabla de sugerencias en la 
 * búsqueda predictiva
 */
function rellena_Array(){
	var i = 0;
	for(i=0; i<6; i++){
		//alert("En rellena_Array");
		propuestas.push("Hola" + i);

	}
}

/*
 * Rq33
 * Realizar sobre JavaScript una funci�n que tome como valores 
 * los resultados almacenados en una variable global llamada
 * propuestas y los muestra en una tabla llamada "tEmergente"
 * justo debajo del campo texto de b�squeda principal
 * La tabla puede estar creada previamente y mediante css estar
 * oculta
 */

function escribir_tEmergente() {

	var i = 0;
	var filas = document.getElementsByTagName("span");
	var tope = 0;
	var tope_anterior = 6;

	var campoDivEmergente = document.getElementById("divEmergente");

	campoDivEmergente.style.display = "block"; //activa campo emergente

	if (propuestas.length > 6){
		tope = filas.length;
	} else{
		tope = propuestas.length;
	}

	//rellena la tabla emergente
	for (i=0; i<(tope); i++){
		filas[i].innerHTML = propuestas[i];
	}
	//si no hay sugerencias borra la tabla
	if ((tope < tope_anterior)){
		for (i=tope; i<6; i++){
			filas[i].innerHTML = "";
		}
	}
	tope_anterior = tope;
}




