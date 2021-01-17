/*
 * tests.js
 * 
 * Pruebas de unidad de javascript y ajax para la aplicación MedBenAjax
 * Autor: Grupo Alef
 * Fecha: 28/11/2013
 * 
 * Basado en la información de :
 * http://coding.smashingmagazine.com/2012/06/27/introduction-to-javascript-unit-testing/
 */	
	// documento XML de prueba
	var docXmlPrueba = '<?xml version="1.0" encoding="UTF-8"?><Search><title>Uned</title><url>http://www.uned.es</url><title>Google</title><url>http://www.google.es</url><title>w3schools javascript while loop</title><url>http://www.w3schools.com/js/js_loop_while.asp</url></Search>';
	var resultados = {	// almacena los resultados
			total: 0,
			mal: 0
	};
	
	/*
	* Convierte una cadena de texto a documento XML
	*/
	function stringToXml(text){
		var doc = null;
	    if (window.ActiveXObject){
	      doc=new ActiveXObject('Microsoft.XMLDOM');
	      doc.async='false';
	      doc.loadXML(text);
	    } else {
	      var parser=new DOMParser();
	      doc=parser.parseFromString(text,'text/xml');
	    }
	    return doc;
	}
	
	/*
	* Compara si dos arrays unidimensionales de texto so idénticos
	*/
	function arraysIdentical(a, b) {
	    var i = a.length;
	    if (i != b.length) return false;
	    while (i--) {
	        if (a[i] !== b[i]) return false;
	    }
	    return true;
	};

	/*
	* Prueba la función parseDocXML
	* Le pasa como parámetro un documento XML
	* Comprueba si el contenido de las variables titulos y urls coincide con el esperado
	*/
	function testParseDocXml() {
		var resultTitles=['Uned', 'Google', 'w3schools javascript while loop'];
		var resultUrls=['http://www.uned.es', 'http://www.google.es', 'http://www.w3schools.com/js/js_loop_while.asp'];
		parseDocXML(stringToXml(docXmlPrueba));
		resultados.total++;
		if (!arraysIdentical(resultTitles,titulos)) {	// comprueba los títulos
			resultados.mal++;
			console.log("=> parseDocXML\nSe esperaba: ");
			console.log(resultTitles);
			console.log("pero el resultado ha sido: ");
			console.log(titulos);
		}
		resultados.total++;
		if (!arraysIdentical(resultTitles, titulos)) {	// comprueba las urls
			resultados.mal++;
			console.log("Se esperaba: ");
			console.log(resultUrls);
			console.log("pero el resultado ha sido: ");
			console.log(urls);
		}
	}

	/*
	 * Convierte un documento xml a cadena de texto
	 */
	function xmlToString(node) {
		   if (typeof(XMLSerializer) !== 'undefined') {
		      var serializer = new XMLSerializer();
		      return serializer.serializeToString(node);
		   } else if (node.xml) {
		      return node.xml;
		   }
		}

	/*
	 * Prueba la función rellenaTabla()
	 * Llama a la función rellenaTabla() y comprueba que el contenido de la tabla coincide con el esperado 
	 */
	function testRellenaTabla() {
		var resultTabla='<table id="tMenu"><tbody><tr><th>Healthtopic</th><th>URL</th></tr><tr><td>Uned</td><td><a href="http://www.uned.es" target="_blank">http://www.uned.es</a></td></tr><tr><td>Google</td><td><a href="http://www.google.es" target="_blank">http://www.google.es</a></td></tr><tr><td>w3schools javascript while loop</td><td><a href="http://www.w3schools.com/js/js_loop_while.asp" target="_blank">http://www.w3schools.com/js/js_l...</a></td></tr><tr><td> </td><td><a href="" target="_blank"> </a></td></tr><tr><td> </td><td><a href="" target="_blank"> </a></td></tr><tr><td> </td><td><a href="" target="_blank"> </a></td></tr><tr><td> </td><td><a href="" target="_blank"> </a></td></tr><tr><td> </td><td><a href="" target="_blank"> </a></td></tr><tr><td> </td><td><a href="" target="_blank"> </a></td></tr><tr><td> </td><td><a href="" target="_blank"> </a></td></tr></tbody></table>';
		parseDocXML(stringToXml(docXmlPrueba));
		rellenaTabla();
		var contenidoTabla = xmlToString(document.getElementById("tMenu"));	// obtiene el contenido HTML de la tabla
		resultados.total++;
		if (resultTabla != contenidoTabla) {								// lo compara con el resultado deseado
			resultados++;
			console.log("Se esperaba: ");
			console.log(resultTabla);
			console.log("pero el resultado ha sido: ");
			console.log(contenidoTabla);
		}
		
	}
	/*
	 * Aquí ejecuta los tests
	 */
	function ejecutaTests() {
		testParseDocXml();
		testRellenaTabla();
		console.log("De " + resultados.total + " pruebas, " + resultados.mal + " han sido erróneas y "
				+ (resultados.total - resultados.mal) + " correctas.");
	}

