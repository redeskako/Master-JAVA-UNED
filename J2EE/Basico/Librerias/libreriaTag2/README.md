## Primer ejemplo de Librería de Tag 2

Este ejemplo mostramos como crear nuestras propias etiquetas de Tags, a parte de las propias de [JSP 2.0](https://www.tutorialspoint.com/jsp/jsp_standard_tag_library.htm) y [JSTL](http://www.softwaresummit.com/2005/speakers/BergmanJSP2.0TagFiles.pdf)
Para ello importo y utilizo un descriptor xml del fichero `WEB-INF/ejemploTags.tld` en el que contemplamos las etiquetas personalizadas para utilizarlas en nuetra vista

Esta segunda versión mostramos como eliminar parte del scriptlet que teníamos en index.jsp

```
	<%
		String[] cadenas= new String[]{"James Bond", "Loco de atar", "Omega"};
		pageContext.setAttribute ("cadenas",cadenas);
	%>
```

En nuestro caso utilizaremos una estiqueta nueva `<ejemplo:crear/>` siendo ejemplo el identificativo que apunte al fichero `liberiaTags.tld`.
Para ello creamos la clase `CrearArrayTag.java`que 

También `itera` que hace uso de la clase `com.prueba.jsp.IterateTag`.

```
	public int doStartTag(){
		String [] cadenas= new String[] {"Tome Lee Jones", "Samuel L. Jackson", "Will Smith"};
		this.pageContext.setAttribute("cadenas", cadenas);
		return this.SKIP_BODY;
	}
```

De esta manera tendremos la inclusión de estos elementos con el index en xml

```
<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="WEB-INF/ejemploTags.tld" prefix="ejemplo" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Prueba de iteracion</title>
	</head>
<body>
	<ejemplo:crear/>
	<hr />
	<ejemplo:itera> El valor ahora es: </ejemplo:itera>
</body>
</html>
```