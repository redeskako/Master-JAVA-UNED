## Primer ejemplo de Librería de Tag 3
Este ejemplo mostramos como crear nuestras propias etiquetas de Tags, a parte de las propias de [JSP 2.0](https://www.tutorialspoint.com/jsp/jsp_standard_tag_library.htm) y [JSTL](http://www.softwaresummit.com/2005/speakers/BergmanJSP2.0TagFiles.pdf)
Para ello importo y utilizo un descriptor xml del fichero `WEB-INF/ejemploTags.tld` en el que contemplamos las etiquetas personalizadas para utilizarlas en nuetra vista

Esta tercera versión mostramos como incluir partes de etiquetas HTML para facilitar el código, en concreto hemos incluido `templates` con los ficheros `cabecera.html` y `pie.html`.

```
<!-- Fichero HTML cabecera.html -->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Prueba de variables</title>
</head>

```

```
<!-- Fichero HTML pie.html -->
<address>Adios</address>
```

En el caso del incluirse añadimos las directivas en el fichero index.jsp `<%@ include file="templates/cabecera.html" %>` y `<%@ include file="templates/pie.html" %>`.

Además hemos incorporado gestión de errores elementales usando para ello llamada al fichero jsp `error.jsp` que lo convocaría el fichero `com.prueba.jsp.JspException.java`.

En el código `com.prueba.jsp.IteraTag.java`.

```
	public int doAfterBody(){
		try{
			this.pageContext.getOut().print(" "+ this.cad[this.cuenta] + "<BR/>");
		}catch(Exception e){
			throw new JspException(e.toString());
		}
		this.cuenta ++;
		if (this.cuenta>= this.cad.length){
			return this.SKIP_BODY;
		}
		return this.EVAL_BODY_AGAIN;
	}
```
