
	// Se escribe "xxx" en el input de texto y se comprueba que el combo de sugerencias está vacío:
	QUnit.asyncTest( "Combo vacío para 'xxx'", function( assert ) {
		 
		var texto = "xxx";
		JRecurso.sqlBuscadorRecursos(texto, buscarCombo);
		
		var combo = document.getElementById("combo");
	
		setTimeout(function() {
			assert.equal( 0, combo.getElementsByTagName("td").length, "Para 'xxx' el combo de sugerencias está vacío" );
			QUnit.start();
		}, 1000);
		 
	});
	
	
	// Se escribe "sAl" en el input de texto y se comprueba que el combo de sugerencias está vacío:
	QUnit.asyncTest( "Combo lleno para 'sAl'", function( assert ) {
		 
		var texto = "sAl";
		JRecurso.sqlBuscadorRecursos(texto, buscarCombo);
		
		var combo = document.getElementById("combo");
		 
		setTimeout(function() {
			assert.ok(combo.getElementsByTagName("td").length > 0, "Para 'sAl' el combo de sugerencias NO está vacío" );
			QUnit.start();
		}, 1000);
		   
	});
	
	
	//Se escribe "xxx" en el input de texto y se pulsa "buscar". Tabla de resultados vacía:
	QUnit.asyncTest( "Tabla de resultados vacía para 'xxx'", function( assert ) { 
		
		var texto = "xxx";
		JRecurso.sqlBuscadorRecursos(texto, buscarResultado);
		
		var tabla = document.getElementById("tabla");
	
		setTimeout(function() {
			assert.equal( 0, tabla.getElementsByTagName("td").length, "Para 'xxx' la tabla de resultados está vacía" );
			QUnit.start();
		}, 1000);
		 
	});
	
	
	//Se escribe "pHo" en el input de texto y se pulsa "buscar". Tabla de resultados NO vacía:
	QUnit.asyncTest( "Tabla de resultados llena para 'pHo'", function( assert ) { 
		
		var texto = "pHo";
		JRecurso.sqlBuscadorRecursos(texto, buscarResultado);
		
		var tabla = document.getElementById("tabla");
	
		setTimeout(function() {
			assert.ok( tabla.getElementsByTagName("td").length > 0, "Para 'pHo' la tabla de resultados NO está vacía" );
			QUnit.start();
		}, 1000);
		 
	});
	
	
	// Se pulsa sobre un link del combo, y se comprueba que el campo "texto" coincide
	QUnit.asyncTest( "Campo de texto al pulsar link", function( assert ) { 
		
		var link = "Sala reunión 4-6 personas";	
		linkCombo(link);
		
		var texto = document.getElementById("texto");

		setTimeout(function() {
			assert.ok( link==texto.value, "Al pulsar un link de sugerencia, el texto coincide" );
			QUnit.start();
		}, 1000);
		 
	});

