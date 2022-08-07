## Ejemplo de un Servlet simple

Este ejemplo muestra como llamar a un Servlet que hemos definido en `es.uned.master.java.MiPrimerServlet`

Para ello el fichero `web.xml` que es el descriptor de fichero web nos incluye la información necesaria 

En el siguiente código mostramos la definición del servlet en el descriptor

```
<servlet>
		<description>
		</description>
		<display-name>
		MiPrimerServlet</display-name>
		<servlet-name>MiPrimerServlet</servlet-name>
		<servlet-class>
		es.uned.master.java.MiPrimerServlet</servlet-class>
	</servlet>
```
Un Servlet puede asignarle un alias que permite proyectarse desde la web

```
<servlet-mapping>
		<servlet-name>MiPrimerServlet</servlet-name>
		<url-pattern>/MiPrimerServlet</url-pattern>
	</servlet-mapping>
```

En nuestro caso el `mapping` se lleva con `/MiPrimerServlet`.


