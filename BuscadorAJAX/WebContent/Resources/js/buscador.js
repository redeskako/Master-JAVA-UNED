// Visualiza el combo de búsqueda, si es necesario.
function muestraCombo(ev)
{	
	// Si la tecla pulsada es una letra, un número, un espacio o borrar.
	if((ev.keyCode>=48 && ev.keyCode<=57) || (ev.keyCode>=65 && ev.keyCode<=90) || (ev.keyCode=8))
	{	
		var titulo = dwr.util.getValue("titulo");
    	
		// Si se han especificado al menos 3 letras para el título, se buscan los videos
		// y se muestra el combo. En caso contrario el combo se oculta.
    	if(titulo.length >= 3)
    		JVideo.buscaVideos(titulo, generaCombo)
    	else
        	dwr.util.removeAllRows("combo");
	}
}

// Genera el contenido del combo a mostrar.
function generaCombo(lstVideos)
{
	// Se ocultan los resultados de posibles búsquedas anteriores.
	dwr.util.setValue("total_registros", "");
	dwr.util.removeAllRows("tabla_encabezado"); 
	dwr.util.removeAllRows("tabla_cuerpo");
	dwr.util.removeAllRows("combo"); 
	
	// Se carga el combo con el contenido a presentar.
	if(lstVideos.length > 0)
	{
    	var fCombo = [function(datos) { return "<a href='javascript:void(0)' onclick='linkCombo(\"" + datos.titulo + "\")' >" + datos.titulo + "</a>" ;}];
    	dwr.util.addRows("combo", lstVideos, fCombo, { escapeHtml:false });
	}
}

// Realiza una búsqueda con el texto introducido como título.
// Se invoca al pulsar el botón "Buscar" en el formulario.
function busca()
{
	var titulo = dwr.util.getValue("titulo");
	
	// Busca los videos a partir del título y luego genera la tabla.
	JVideo.buscaVideos(titulo, generaTabla)
}

// Genera el contenido de la tabla a mostrar.
function generaTabla(lstVideos)
{
	// Oculta el combo y elimina el resultado de posibles búsquedas previas.
	dwr.util.removeAllRows("combo"); 
	dwr.util.removeAllRows("tabla_encabezado"); 
	dwr.util.removeAllRows("tabla_cuerpo");
	
	// Muestra el número de registros obtenidos.
	dwr.util.setValue("total_registros", "<p><strong>Se han recuperado " + lstVideos.length + " registros.<strong></p>", { escapeHtml:false });

	// Carga la tabla con el contenido a presentar.
	if(lstVideos.length > 0)
	{
		// Encabezado.
    	var lstEncabezado = ["encabezado"];
    	var fEncabezado = [function(datos) { return "Id"; }, function(datos) { return "Titulo"; },function(datos) { return "Ver"; }];
    	dwr.util.addRows("tabla_encabezado", lstEncabezado, fEncabezado);

    	// Cuerpo.
    	var fCuerpo = [function(datos) { return datos.id; }, function(datos) { return datos.titulo; }, 
    	               function(datos) { return "<a href= https://www.youtube.com/watch?v=" + datos.id + ">Ver</a>"; }];
    	              //  function(datos) { return "<a href= /BuscadorAJAX/hola.jsp>Ver</a>";}];
	 

    	 dwr.util.addRows("tabla_cuerpo", lstVideos, fCuerpo, { escapeHtml:false });
	}
}

// Al pulsar un enlace del combo.
function linkCombo(titulo)
{
	// Si activa algún link del combo de sugerencias, se establece su valor 
	// en el input, se oculta el combo y se realiza la búsqueda.
	dwr.util.setValue("titulo", titulo);
	dwr.util.removeAllRows("combo");

	busca();
}