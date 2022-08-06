		

		function mostrarCombo(ev){
        	
        	// Se comprueba que la última tecla presionada es una letra, número, space o borrar:
        	if((ev.keyCode>=48 && ev.keyCode<=57) || (ev.keyCode>=65 && ev.keyCode<=90) || (ev.keyCode=8)){
	        	
        		var texto = dwr.util.getValue("texto");
	        	
        		// El combo se genera cuando se han introducido al menos tres letras:
	        	if(texto.length >= 3){
	        		// Se genera el SQL para la búsqueda de recursos que contengan el texto introducido:
	        		JRecurso.sqlBuscadorRecursos(texto, buscarCombo);
	            } else {
	            	dwr.util.removeAllRows("combo"); 
	            }
        	} 
        }
        
        function buscarCombo(sql){
        	// Con el SQL de la búsqueda, se realiza la misma y se obtiene el array con los resultados:
        	JRecurso.recursosBuscar(0, sql, generarCombo);	
        }
        
        function generarCombo (array){
        	// Cuando se muestra el combo, se borra el resultado de anteriores búsquedas:
        	dwr.util.setValue("tablaTitulo", "");
        	dwr.util.removeAllRows("tabla_encabezado"); 
        	dwr.util.removeAllRows("tabla_cuerpo");
        	dwr.util.removeAllRows("combo"); 
        	
        	// Si hay resultados, se muestran en la tabla con id="combo":
        	if(array.length > 0){
            	
            	// Cuerpo:
            	var funcCombo = [
            	                 function(data) { return "<a href='javascript:void(0)' onclick='linkCombo(\"" 
            	                		 + data.descripcion + "\")' >" + data.descripcion + "</a>" ;}
            	               ];
        	
            	dwr.util.addRows("combo", array, funcCombo, { escapeHtml:false });
        	}
        }
        
        function botonBuscar(){
        	// Si el usuario presiona el botón "buscar" se realiza una búsqueda con el contenido del input 
        	// con id="texto":
        	var texto = dwr.util.getValue("texto");
        	
        	// Se genera el SQL para la búsqueda de recursos que contengan el texto introducido:
        	JRecurso.sqlBuscadorRecursos(texto, buscarResultado);
        }
        
        function buscarResultado(sql){
        	// Con el SQL de la búsqueda, se realiza la misma y se obtiene el array con los resultados:
        	JRecurso.recursosBuscar(0, sql, generarTabla);
        }
        
        function generarTabla (array){
        	// Cuando se muestra la tabla de resultado, se borra el resultado del combo:
        	dwr.util.removeAllRows("combo"); 
        	
        	// Se muestra el título de la tabla:
        	dwr.util.setValue("tablaTitulo", "<h3>N&uacute;mero de resultados obtenidos: " + array.length + "</h3>", 
        			{ escapeHtml:false });
        	
        	// Se elimina el contenido de posibles búsquedas previas:
        	dwr.util.removeAllRows("tabla_encabezado"); 
        	dwr.util.removeAllRows("tabla_cuerpo"); 
        	
        	// Si hay resultados, se muestran en la tabla con id="tabla"
        	if(array.length > 0){
        		
        		// Encabezado:
            	var arrayEncabezado = ["encabezado"];
            	var funcEncabezado = [
            	                 function(data) { return "ID Recurso"; },
            	                 function(data) { return "Descripción"; }
            	               ];
            	dwr.util.addRows("tabla_encabezado", arrayEncabezado, funcEncabezado);
            	
            	// Cuerpo:
            	var funcCasillas = [
               	                 function(data) { return data.idRecurso; },
               	                 function(data) { return data.descripcion; }
               	               ];
               	dwr.util.addRows("tabla_cuerpo", array, funcCasillas);
        	}
        }
        
        function linkCombo(texto){
        	// Si activa algún link del combo de sugerencias, se establece el valor del link al input
        	// con id="texto", se eliminan las opciones del combo y se realiza la búsqueda
        	dwr.util.setValue("texto", texto);
        	dwr.util.removeAllRows("combo"); 
        	
        	botonBuscar();
        }