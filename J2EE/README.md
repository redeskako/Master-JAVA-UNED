# J2EE
Desarrollo y ejercicios en Java Enterprise Edition

## Lo básico de J2EE
La especificación JAVA EE define los siguientes componentes:

* Applets y aplicaciones de cliente son componentes del lado del cliente.
    * Clientes Webs.
    * Applets.
    * Aplicaciones de clientes.
* Componentes Java Servlet, JavaServer? Faces, and JavaServer? son componentes del lado del servidor.
* Componentes Enterprise JavaBeans? (enterprise beans) son componentes de negocio del lado del servidor.

![Image Principios J2SE](https://i.pinimg.com/originals/1d/58/df/1d58df27c0998ebb26906417f874ded0.jpg)

Nuestro objetivo radicará en centrarnos ahora en el lado del servidor. Escrutar la arquitectura que lo maneja y controlarla.


## J2EE
![Image J2EE](https://i.pinimg.com/originals/3d/40/5e/3d405ea2c7bd76cbe4f21e9ac941758c.jpg)

### Java EE
* Java EE incluye varias especificaciones de API, tales como JDBC, RMI, e-mail, JMS, Servicios Web, XML, etc, y define como coordinarlos.
* Java EE también configura algunas especificaciones Técnicas para Java EE para componentes. Estas incluyen Enterprise JavaBeans?, servlet, portlet (siguiendo la especificación de Portlets Java), JavaServer? Pages y varias tecnologías de servicios web.
    * Esto permite al desarrollador crear una Aplicación de Empresa? que es portable entre plataformas y escalable, mientras integramos con tecnologías de legado.
    * Otros beneficios añadidos son, por ejemplo, que el servidor de aplicaciones puede manejar las transacciones, seguridad, escalabilidad, concurrencia y gestión de los componentes que son desplegados, significando que los desarrolladores pueden concentrarse más en la lógica de negocio de los componentes en lugar de las tareas de mantenimiento de bajo nivel.
### Contenedor Web
En la Plataforma Java 2 Enterprise Edition, un contenedor web es la implementación que hace cumplimiento del contrato de componentes web de la arquitectura J2EE. Este contrato especifica un entorno de ejecución para componentes web que incluye Seguridad informática, concurrencia, gestión del ciclo de vida, procesamiento de transacciones, despliegue y otros servicios. Un contenedor web suministra los mismos servicios que el contenedor de JavaServer? Pages (JSP) asÃ­ como también una vista federada de las APIs de la plataforma J2EE. Un contenedor web se suministra incluido en un servidor web o J2EE.
* Ejemplos de contenedores web
    * Sun Java System Application.
    * Sun Java System Web Server.
    * Apache Tomcat para Java Web Services Development Pack.

## Protocolo HTTP
* Protocolo de transferencia es el sistema mediante el cual se envían las peticiones de acceso a una página y la respuesta con el contenido.
* **HTTP es un protocolo sin estado.**
* Al finalizar la transacción todos los datos se pierden. Por esto se popularizaron las cookies, que son pequeños ficheros guardados en el propio ordenador que puede leer un sitio web al establecer conexión con él, y de esta forma reconocer a un visitante que ya estuvo en ese sitio anteriormente.

```html
telnet 127.0.0.1 80
 Trying 127.0.1.1...
 Connected to pi-.
 Escape character is '^]'.
 GET index.html
 <!DOCTYPE HTML PUBLIC "-//IETF//DTD HTML 2.0//EN">
 <html><head>
 <title>400 Bad Request</title>
 </head><body>
 <h1>Bad Request</h1>
 <p>Your browser sent a request that this server could not understand.<br />
 </p>
 <hr>
 </body></html>
 Connection closed by foreign host.
```
## Servlet y JSP

[![Servelts, JSP y Java Beans](https://img.youtube.com/vi/pf7SOu2VIkk/0.jpg)](https://youtu.be/pf7SOu2VIkk "Servlets, JSP y Java Beans")

### Servlet
#### Introducción
* En este capítulo aprenderemos como se realiza la programación del lado del servidor usando la API Servlets 2.1. Como primer punto importante señalaremos que este paquete se trata de una extensión del API Core del JDK y por tanto ha de ser descargado de forma independiente de aquel. La podemos obtener ​aquí, donde podremos encontrar el JSDK 2.1 (Java Servlets Development Kit 2.1).
* Debido también a que se trata de una extensión, el paquete debe ser indicado en el CLASSPATH de la máquina a ejecutar, concretamente los archivos server.jar y servlet.jar.
### Descripcion exata de un Servlet
#### ¿Qué es un servlet?
* Un servlet de forma intuitiva se puede definir como un programa independiente de plataforma que aporta la misma funcionalidad a la programación en el lado del servidor que tradicionalmente han realizado la interfaz CGI. Con respecto a esta tecnología aporta numerosas ventajas que citaremos a continuación:
    * **Independencia de la plataforma**: (la tan anelada premisa del write once run everywhere aun no totalmente cons**eguida). Esto proporciona un menor esfuerzo de codificación con respecto a soluciones dependientes del servidor web y de la plataforma como ISAPI o NSAPI.
    * **Ejecución en paralelo de multiples peticiones por una sola instancia del servlet**: Tradicionalmente en los programas CGI se ejecuta un proceso distinto para cada petición lo que conlleva una gradual degradación del rendimiento y una necesidad de recursos muy elevada. En un servlet todas las peticiones se atienden en el mismo proceso por distintos hilos y una vez que se ha cargado el servlet este permanece en memoria hasta que se reinicie el servidor o hasta que se le diga lo contrario con lo cual las subsiguientes peticiones son mas rapidas al encontrarse el programa ya cargado en memoria.
* Un servlet puede ejecutarse (incido en esto puede no es necesario) en una sandbox o recinto de seguridad parecido al modelo que se se sigue con los applets. Debido a esto pueden colocarse servlets en servidores dedicados a hosting sin que la empresa tema por la integridad del servidor y la seguridad de las aplicaciones. Históricamente el rechazo al uso de los servlets se ha debido a la injustificada leyenda de la falta de velocidad de ejecución del lenguaje Java. Sin embargo, si observamos los lenguajes de script que tradicionalmente se han dado para escribir aplicaciones CGI´s (Perl, PHP, ...) nos encontramos con que también son interpretados lo que unido a la necesidad de lanzar un proceso por petición provocan un rendimiento considerablemente menor. Esto no pasa si el lenguaje de implementación del CGI: es uno compilado como puede ser C pero la obligatoriedad de cumplir la ecuación 1 petición = 1 proceso sitúan el rendimiento en niveles parecidos con un menor consumo de recursos del servlet.
#### ¿Dónde puedo ejecutar Servlets y qué necesito?
* En la actualidad la mayoría de servidores web, tanto comerciales como de licencia libre, tienen la capacidad de ejecutar servlets a través de plug-ins o módulos.
* Como dato adicional el JSDK 2.1 incluye una herramienta llamada servletrunner análoga a appletviewer para la ejecución y depuración de servlet con unas capacidades muy limitadas por lo que sólo se debe usar para comprobar la exactitud del servlet.
### Estructura de un Servlet
* El API Servlet consiste básicamente en dos paquetes:
    * **javax.servlet**: En este paquete se definen 6 interfaces y 3 clases para la implementación de servlets genericos, sin especificación de protocolo. Hoy en dia no tienen utilidad practica mas que para servir de base en la jerarquía de clases de los servlets. Conforme pase el tiempo se supone que constituiran la base para la implementación de otros protocolos distintos de http.
    * **javax.servlet.http**: Ofrece la implementación específica de servlets para el protocolo http. En estos paquetes se definen todas las clases e interfaces necesarias para la escritura de applets. De hecho se usan los servlets para gestionar conexiones http usaremos las clases del paquete javax.servlet.http.
* El ciclo de ejecución de un servlet es análogo al de un applet con ligeras diferencias. Inicialmente el servlet debe extender a la clase HttpServlet:
```java
import javax.servlet;
import javax.servlet.*;
import javax.servlet.http.*;
public class MiServlet extends HttpServlet{
   ....
}
```
* Para dotar de funcionalidad a un servlet se han de redefinir una seria de métodos que guardan una analogí­a con los métodos de funcionamiento de un applet (init(), start(), stop(), destroy()).
```java
  public void init(ServletConfig config)
```
* Cada vez que se inicia el servlet el servidor web llama a este metodo pasando un parámetro de la clase ServletConfig? que guarda información de la configuración del servlet y del contexto del servidor web en el que se ejecuta. A través de ServletConfig? se accede a los parámetros de inicialización del servlet que se establecieron al configurar el servlet y a través de la interfaz ServletContext? (obtenido a partir del método getServletContext() de ServletConfig?) se accede a la información del servidor web.
* A modo de ejemplo desarrollaremos un ejemplo simple de un servlet que escribe información en un fichero de registro (el formato,ubicación y nombre de este es dependiente del servidor web):
```java
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
public class MiServlet extends HttpServlet{
    public void init(ServletConfig config){
    config.getServletContext().log("Iniciado MiServlet a las "+new Date());
}
```
* En este método se han de realizar todas las operaciones unicas en el ciclo de vida del servlet tal como conexion a BD de forma persistente y otras tareas de inicialización. Dado que el servlet se carga en memoria al iniciar el sevidor web o al recibir la primera petición (dependiendo de la configuración) el metodo init() es llamado solo una vez, no cada vez que se realice una petición.
```java
public void destroy()
```
* Este método es análogo al método init() sólo que sera llamado por el servidor web cuando el servlet esta a punto de ser descargado de memoria (repito: no cuando termina una petición). En este método se han de realizar las tareas necesarias para conseguir una finalización apropiada como cerrar archivos y flujos de entrada de salida externos a la petición, cerrar conexiones persistentes a bases de datos.
* Un punto importante es que se puede llamar a este método cuando todaví­a esta ejecutándose alguna petición por lo que podría producirse un fallo del sistema y una inconsistencia de datos tanto en archivos como en BD. Por eso debe retrasarse la desaparición del servlet hasta que todas las peticiones hayan sido concluidas (con respecto a la peticiones que lleguen en ese intervalo el servidor web sencillamente las desestimará).
```java
public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException.
```
* En este método se encuentra la mayor parte de la funcionalidad del servlet. Cada vez que se realice una petición se llamará a este método pasándole dos parámetros que nos permite obtener información de la petición y un flujo de salida para escribir la respuesta. Análogamente tenemos otra serie de métodos que realizan la implementación de respuesta a metodos de comunicación del protocolo http 1.1 como son GET y POST. Estos son respectivamente:
```java
public void doGet(HttpServletRequest request,HttpServletResponse response)
public void doPost(HttpServletRequest request,HttpServletResponse response)
```
* Los dos parámetros que recibe service() son esenciales para el funcionamiento del servlet por lo que pasaremos a verlos con mas profundidad: son HttpservletRequest y HttpServletResponse.
* Esta interfaz derivada de ServletRequest? proporciona los métodos para recuperar la información de la petición del usuario así como del propio usuario. Señalaremos los mas importantes:
```java
public abstract String getRemoteHost()
```
* Devuelve el nombre del ordenador que realizó la petición.
```java
public abstract String getParameter(String parameter)
```
* Devuelve el valor del parámetro parameter o null si dicho parámetro no existe.
```java
public abstract String[] getParameterValues(String parameter)
```
* Devuelve un array con los valores del parametro especificado por parameter o null si dicho parametro no existe.
```java
public abstract Enumeration getParameterNames()
```
* Devuelve una Enumeration de los nombre de los parametros empleados en la petición.
```java
HttpServletResponse
```
* Se trata de un interfaz derivada de ServletResponse que proporciona los métodos para realizar la respuesta al cliente que originó la petición. Señalaremos los mas importantes:
```java
public abstract PrintWriter getWriter()
```
* Permite obtener un objeto PrintWriter para escribir la respuesta.
```java
public abstract setContentType(String)
```
* Permite establecer el tipo MIME de la respuesta
```java
public abstract setContentLenght(int)
```
* Permite especificar la longitud de la respuesta. Si no se indica el propio motor de servlets calculará la longitud de la respuesta. A efectos practicos el autor de este tutorial no encuentra otra utilidad a este método mas que permitir al navegador mostrar una barra de progreso que muestre el estado de descarga de la solicitud.

### Servlet con Docker-Compos
[![Servlet con Docker-Compose](https://img.youtube.com/vi/tiY5nKHeaBg/0.jpg)](https://youtu.be/tiY5nKHeaBg "Servlet con Docker-Compose")

### Configuraciones y variables de entorno J2EE
[![Configuraciones y variables de entorno J2EE](https://img.youtube.com/vi/IZmYDvFIz8Q/0.jpg)](https://youtu.be/IZmYDvFIz8Q "Configuraciones y variables de entorno J2EE")

## Proyectos

### ¿Cómo abordar un proyecto?

### Ejemplos

-