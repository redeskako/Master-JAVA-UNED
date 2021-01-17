  /*
   * qTests.js
   * 
   * Pruebas de unidad de javascript y ajax para la aplicación MedBenAjax
   * utilizando QUnit.js http://qunitjs.com/
   * Autor: Grupo Alef
   * Fecha: 03/12/2013
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
  	 * Ejecuta los tests de unidad
  	 */
    function ejecutaTests() {
	  	var resultTitles=['Uned', 'Google', 'w3schools javascript while loop'];
		var resultUrls=['http://www.uned.es', 'http://www.google.es', 'http://www.w3schools.com/js/js_loop_while.asp'];
		var resultTabla='<table id="tMenu"><tbody><tr><th>Healthtopic</th><th>URL</th></tr><tr><td>Uned</td><td><a href="http://www.uned.es" target="_blank">http://www.uned.es</a></td></tr><tr><td>Google</td><td><a href="http://www.google.es" target="_blank">http://www.google.es</a></td></tr><tr><td>w3schools javascript while loop</td><td><a href="http://www.w3schools.com/js/js_loop_while.asp" target="_blank">http://www.w3schools.com/js/js_l...</a></td></tr><tr><td> </td><td><a href="" target="_blank"> </a></td></tr><tr><td> </td><td><a href="" target="_blank"> </a></td></tr><tr><td> </td><td><a href="" target="_blank"> </a></td></tr><tr><td> </td><td><a href="" target="_blank"> </a></td></tr><tr><td> </td><td><a href="" target="_blank"> </a></td></tr><tr><td> </td><td><a href="" target="_blank"> </a></td></tr><tr><td> </td><td><a href="" target="_blank"> </a></td></tr></tbody></table>';
		
		parseDocXML(stringToXml(docXmlPrueba));
		rellenaTabla();
		var contenidoTabla = xmlToString(document.getElementById("tMenu"));	
		
		test( "Prueba de parseDocXML() con títulos", function() {
    		// comprueba el array titulos
    		deepEqual( resultTitles, titulos, "parseDocXML: El valor esperado es ['Uned', 'Google', 'w3schools javascript while loop']" );
		});
      	  	
		test( "Prueba de parseDocXML() con urls", function() {
    		// comprueba el array urls
    		deepEqual( resultUrls, urls, "parseDocXML: El valor esperado es ['http://www.uned.es', 'http://www.google.es', 'http://www.w3schools.com/js/js_loop_while.asp']" );
		});
      	  	
		test( "Prueba de rellenaTabla()", function() {
    		// comprueba la tabla
    		deepEqual( resultTabla, contenidoTabla, "rellenaTabla(): El valor esperado es '<table id=\"tMenu\"><tbody><tr><th>Healthtopic</th><th>URL</th></tr><tr><td>Uned</td><td><a href=\"http://www.uned.es\" target=\"_blank\">http://www.uned.es</a></td></tr><tr><td>Google</td><td><a href=\"http://www.google.es\" target=\"_blank\">http://www.google.es</a></td></tr><tr><td>w3schools javascript while loop</td><td><a href=\"http://www.w3schools.com/js/js_loop_while.asp\" target=\"_blank\">http://www.w3schools.com/js/js_l...</a></td></tr><tr><td> </td><td><a href=\"\" target=\"_blank\"> </a></td></tr><tr><td> </td><td><a href=\"\" target=\"_blank\"> </a></td></tr><tr><td> </td><td><a href=\"\" target=\"_blank\"> </a></td></tr><tr><td> </td><td><a href=\"\" target=\"_blank\"> </a></td></tr><tr><td> </td><td><a href=\"\" target=\"_blank\"> </a></td></tr><tr><td> </td><td><a href=\"\" target=\"_blank\"> </a></td></tr><tr><td> </td><td><a href=\"\" target=\"_blank\"> </a></td></tr></tbody></table>'" );
		});
      	  	
    }
