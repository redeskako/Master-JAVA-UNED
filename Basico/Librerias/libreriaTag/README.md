## Primer ejemplo de Librería de Tag

Este ejemplo mostramos como crear nuestras propias etiquetas de Tags, a parte de las propias de [JSP 2.0](https://www.tutorialspoint.com/jsp/jsp_standard_tag_library.htm) y [JSTL](http://www.softwaresummit.com/2005/speakers/BergmanJSP2.0TagFiles.pdf)
Para ello importo y utilizo un descriptor xml del fichero `WEB-INF/ejemploTags.tld` en el que contemplamos las etiquetas personalizadas para utilizarlas en nuetra vista

```
<taglib>
	<tlib-version>1.0</tlib-version>
	<jsp-version>1.2</jsp-version>
	<short-name>ejemploTags</short-name>
	<description>Un conjunto de tags.</description>
	<tag>
		<name>tiempo</name>
		<tag-class>com.prueba.jsp.TimeTag</tag-class>
	</tag>
	<tag>
		<name>itera</name>
		<tag-class>com.prueba.jsp.IterateTag</tag-class>
	</tag>
</taglib>
```

En la misma tenemos dos etiquetas `Tiempo` que hace llamada de la clase `com.prueba.jsp.TimeTag de TagSupport`.

```
<tag>
	<name>tiempo</name>
	<tag-class>com.prueba.jsp.TimeTag</tag-class>
</tag>
```

También `itera` que hace uso de la clase `com.prueba.jsp.IterateTag`.

```
<tag>
		<name>itera</name>
		<tag-class>com.prueba.jsp.IterateTag</tag-class>
</tag>
```

Luego podremos ver las clases respectivas `com.prueba.jsp.TimeTag`: En este caso activamos el método `doEndTag()`que nos habilita el uso de acciones al cierre de la etiqueta tag.

```
public class TimeTag extends TagSupport{
	public int doEndTag() throws JspException{
		SimpleDateFormat sdf;
		sdf= new SimpleDateFormat("HH:mm:ss");
		String time= sdf.format(new java.util.Date());
		try{
			this.pageContext.getOut().print(time);
		}catch(Exception e){
			throw new JspException(e.toString());
		}
		return this.EVAL_PAGE;
	}
}
```
También `com.prueba.jsp.IterateTag`: que nos habilita la acción de iteración con el método `doAfterBody()` aunque en el `doStartTag()` configuramos las respectivas propiedades `cuenta` y `cadenas`.

```
public class IterateTag extends TagSupport{
	private int cuenta = 0;
	private String[] cadenas = null;

	public int doStartTag(){
		this.cadenas = (String []) this.pageContext.getAttribute("cadenas");
		return super.EVAL_BODY_INCLUDE;
	}

	public int doAfterBody(){
		try{
			this.pageContext.getOut().print(" "+ this.cadenas[this.cuenta]+ "<BR/>");
		}catch(Exception e){
			throw new JspException(e.toString());
		}
		this.cuenta ++;
		if (this.cuenta >= this.cadenas.length){
			return this.SKIP_BODY;
		}
		return this.EVAL_BODY_AGAIN;
	}
}
```

Al final desde nuestro `jsp` podríamos usar la etiquetas respectiva pero antes importando el fichero `WEB-INF/ejemploTags.tld`.

```
...
<%@ taglib uri="WEB-INF/ejemploTags.tld" prefix="ejemplo" %>
...
ejemplo:itera>
	La cadena es: 
</ejemplo:itera>
<hr/>
Son las : <ejemplo:tiempo/>
```

