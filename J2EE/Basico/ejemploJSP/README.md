## Ejemplo de etiquetas JSP 1

Este ejemplo muestra el uso de etiquetas JSP para reducir el uso de scriptlets

En el siguiente código mostramos la definición del servlet en el descriptor

Para ello importo y utilizo un bean `es.uned.master.java.Libro` donde tenemos el POJO de la estructura de una entidad `Libro`.

```
<%@ page import="com.prueba.jsp.Libro" %>
```

Para usar los métodos del bean utilizo la siguiente etiqueta JSP:

```
<jsp:useBean id="miLibro" class="com.prueba.jsp.Libro"/>
```

Luego podemos definir valores a las propiedades del `Libro` con set y get property:

```
	<jsp:setProperty name="miLibro" property="titulo" value="Iniciandonse en JSP"/>
	Titulo del libro: 
	<jsp:getProperty name="miLibro" property="titulo"/>
```

