var totReg = 0; 		// Total de registros devueltos por la consulta.
var titulos = new Array();
var urls = new Array();
var propuestas = new Array();	// array de propuestas de texto predictivo
var pag = 1; 			// Página actual.
var LONGITUD_TEXTO = 35; // para la longitud del texto de la url (ajustar al tamaño óptimo)
var LPP = 10;			// líneas por página para paginación de resultados

function inicializarJSP() {

	dwr.util.setValue("selectMenu","MedLine");
	actualizarCampos();
	actualizarTextoPaginacion();

}

/*
 * Devuelve la cadena pasada eliminando los espacios anteriores y posteriores
 */
function trim (str) {
	return str.replace(/^\s\s*/, '').replace(/\s\s*$/, '');
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
	valor_tabla = trim(valor_tabla);
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
	dwr.util.setValue("textPalabra",valor_tabla);
	var campoDivEmergente = document.getElementById('divEmergente');
	campoDivEmergente.style.display = "none";
}

/*
 * Equivalencia del método parseDocXML() que 
 * se utiliza como retrollamada en DWR para recuperar 
 * los datos de la búsqueda básica (títulos y urls) 
 * en el WSREST desde el cliente.
 */

function parseDWR(busqueda) {
	titulos.length = 0;
	urls.length = 0;
	totReg = 0;

	totReg = busqueda.length;

	for(var i = 0; i < totReg ; i++){ 
		titulos.push(busqueda[i][0]); // añade título
		urls.push(busqueda[i][1]);	  // añade url
	}

	rellenaTabla();
	actualizarTextoPaginacion();
	gestPaginacion();
}

/*
 * Requisito RQ5 de la práctica
 * Rellena la tabla con el contenido de las variables globales titulos y urls
 * sustituyendo el contenido de todas las filas menos la primera (fila de títulos)
 */
function rellenaTabla() {

	//limpio la tabla para empezar a rellenar de cero
	dwr.util.removeAllRows("cuerpoTabla");

	for (var i = 1; i <= LPP; i++) {			// las LPP líneas de la tabla, empezando por la 1 (la 0 son los títulos)
		var indiceArray = (LPP * (pag  - 1)) + i - 1; // calcula el índice del array, según la página en la que está
		if (indiceArray >= totReg) {			// celdas vacías	
			dwr.util.addRows("cuerpoTabla", [""], [function(data) { return data; },
			                                       function(data) { return data; }],
			                                       { escapeHtml:false });
		} else {
			dwr.util.addRows("cuerpoTabla", [""], [function(data) { return titulos[indiceArray]; },
			                                       function(data) { return "<a href="+ urls[indiceArray] + " target=_blank>"+recortar(urls[indiceArray])+"</a>"; }],
			                                       { escapeHtml:false });
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
 * Equivalencia del método parseDocXMLPredictivo() que
 * se utiliza como retrollamada en DWR para recuperar 
 * los datos de la búsqueda predictiva (títulos o ciudades) 
 * en el WSREST desde el cliente.
 */
function parseDWRPredictivo(busqueda) {

	var campo = dwr.util.getValue("selectCampo");

	//El símbolo * no se puede utilizar en DWR, por eso se utiliza All y aquí se cambia si se necesita
	if (campo == "All") campo = "*";

	propuestas.splice(0, propuestas.length); 							// vacía el array de propuestas

	for(var i = 0; i < busqueda.length; i++){ 

		propuestas.push(busqueda[i]);		// añade propuesta
	}

	escribir_tEmergente(); 
}


//Función que modifica los campos de búsqueda que se pueden 
//seleccionar según el catálogo, y también el nombre de la primera
//columna de la tabla de resultados ("Healthtopic" u "Organización").
function actualizarCampos() {

	var col_title = dwr.util.byId("tMenu").getElementsByTagName("th")[0].firstChild;

	dwr.util.removeAllOptions("selectCampo");

	if (dwr.util.getValue("selectMenu") == "MedLine") {
		dwr.util.addOptions( "selectCampo", { All:'cualquier campo', 
			Title: 'título',
			FullSummary: 'descripción',
			Site: 'site',
			Organization: 'organización'});
		col_title.nodeValue = "Healthtopic";
	}
	else {
		dwr.util.addOptions( "selectCampo", { All:'cualquier campo', 
			Organization: 'organización',
			State: 'estado',
			City: 'ciudad',
			Organization: 'organización'});
		col_title.nodeValue = "Organización";
	}
}

//Función que modifica el texto que muestra
//el estado de la paginación, para conocer en
//todo momento el rango de resultados
//mostrados y el total de los mismos.
function actualizarTextoPaginacion() {
	// Almacenamos el campo de texto que debe mostrar el estado de la
	// paginación en una variable.
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

	dwr.util.setValue("txtBuscaPalabra",cad);
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

	var base = dwr.util.getValue("selectMenu");						//valor del selector de base de datos
	var palabra = dwr.util.getValue("textPalabra");

	//comprobación del valor de la palabra
	if ((palabra == "") ||(palabra == "Palabra a buscar")){
		alert("No ha introducido ninguna palabra");
	}
	else {
		var campo = dwr.util.getValue("selectCampo"); //valor del selector de campo

		//El símbolo * no se puede utilizar en DWR, por eso se utiliza All y aquí se cambia si se necesita
		if (campo == "All") campo = "*";

		//los espacios en blanco no estan permitidos en una URL
		palabra = palabra.replace(" ", "");

		// Con DWR, conseguimos sustituir todo el código anterior para 
		// recuperación del documento XML asíncrono por una única línea.
		// La función de retrollamada es 'parseDWR'.
		Dwrwsrest.busquedaBasica(base, palabra, campo, parseDWR);
	}
}

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
	var campo = dwr.util.byId("selectCampo");
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
	var palabra = dwr.util.getValue("textPalabra");


	//convierte la letra incial en mayuscula
	var letraInicial = "";
	if ((palabra.charAt(0)>="a") & (palabra.charAt(0)<="z")){
		letraInicial = palabra.charAt(0).toUpperCase() + palabra.substring(1);
		palabra = letraInicial;
	}

	// Base de datos en la que se está buscando
	var bbdd = dwr.util.getValue("selectMenu");

	//El límite lo ponemos a 6.
	var limite = 6;


	// Nos cercioramos de que no hayan espacios en la URL
	palabra = palabra.replace(" ", "");

	// Con DWR, conseguimos sustituir todo el código anterior para 
	// recuperación del documento XML asíncrono por una única línea.
	Dwrwsrest.busquedaPredictiva(bbdd, palabra, limite, parseDWRPredictivo);		
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
	var campoDivEmergente = dwr.util.byId("divEmergente");

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




