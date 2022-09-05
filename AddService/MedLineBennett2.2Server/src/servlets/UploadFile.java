package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;

import java.util.List;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import beans.BBDD;
import beans.Bennett;
import beans.HealthTopics;
import beans.MyErrorHandler;
import beans.ValidarXML;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.util.JAXBSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

/**
* Gestiona la subida de archivos seleccionados en el formulario que contiene 'importar.jsp'.
* Si el archivo es un XML lo valida, si es un CSV, lo carga tambi�n en la base de datos.
* Todos los archivos se guardan en la carpeta 'uploads', para modificar la carpeta donde se
* guardan los archivos hay que modificar el atributo 'uploadfile' en el 'web.xml'.
* @author alef
*/
public class UploadFile extends HttpServlet {

private static final long serialVersionUID = 2L;

private String dirUploadFiles; //directorio donde se guardara los archivos
private ValidarXML val = new ValidarXML(); //validador de los archivos XML


protected void processRequest(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
response.setContentType("text/html;charset=UTF-8");
//PrintWriter out = response.getWriter();


RequestDispatcher vista;
String guardado = null;
//String fin_parrafo = null;
String guardado_correctamente = null;

String fallo_al_guardar = null;
String XML_correcto = null;
String XML_novalido = null;
String XML_eliminado = null;
String csv_cargado = null;
String tamanio_archivo = null;
int i = 1;


// 1. obtengo el directorio donde guardare los archivos, desde un parametro de
// contexto en el archivo web.xml
dirUploadFiles = getServletContext().getRealPath( getServletContext().getInitParameter( "dirUploadFiles" ) );


// 2. Si la peticion es de tipo multi-part,
// static boolean isMultipartContent() devuelve true/false
if( ServletFileUpload.isMultipartContent( request ) ){

 // 3. crear el arhivo factory
 // DiskFileItemfactory es una implementacion de FileItemfactory
 // esta implementacion crea una instacia de FileItem que guarda su
 // contenido ya sea en la memoria, para elementos pequeños,
 // o en un archivo temporal en el disco, para los
 // elementos de mayor tamaño
 FileItemFactory factory = new DiskFileItemFactory();


 // 4. crear el servlet upload
 // es un API de alto nivel para procesar subida de archivos
 // Por defecto la instancia de ServletFileUpload tiene los siguientes valores:
 // * Size threshold = 10,240 bytes. Si el tamaño del archivo está por debajo del umbral,
 //   se almacenará en memoria. En otro caso se almacenara en un archivo temporal en disco.
 // * Tamaño Maximo del cuerpo de la request HTTP = -1.
 //   El servidor aceptará cuerpos de request de cualquier tamaño.
 // * Repository = Directorio que el sistema usa para archivos temporales.
 //   Se puede recuperar llamando a System.getProperty("java.io.tmpdir").
 ServletFileUpload upload = new ServletFileUpload( factory );
 /* 5. declaro listUploadFiles
  * Contendrá una lista de items de archivo que son instancias de FileItem
* Un item de archivo puede contener un archivo para upload o un
  * campo del formulario con la estructura simple nombre-valor
  * (ejemplo: <input name="text_field" type="text" />)
  *
  * Podemos cambiar las opciones mediante setSizeThreshold() y setRespository()
    de la clase DiskFileItemFactory y el
    método setSizeMax() de la clase ServletFileUpload, por ejemplo:

         DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
         diskFileItemFactory.setSizeThreshold(40960); // bytes

         File repositoryPath = new File("/temp");
         diskFileItemFactory.setRepository(repositoryPath);

         ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
         servletFileUpload.setSizeMax(81920); // bytes

  *
  */
 // limito a 300 Kb el humbral del tamaño del archivo a subir
 // Long.parseLong( getServletContext().getInitParameter( "maxFileSize" ) )
 upload.setSizeMax( new Long( getServletContext().getInitParameter( "maxFileSize" ) ).longValue() ); // 1024 x 300 = 307200 bytes = 300 Kb

 List listUploadFiles = null;
 FileItem item = null;

 try{
     // 6. adquiere la lista de FileItem asociados a la peticion
     listUploadFiles = upload.parseRequest( request );

     /* 7. Iterar para obtener todos los FileItem
      * vamos a trabajar con generalidad
      * programaremos como si quisieramos leer todos los
      * campos sean 'simples' o 'file'. Por ello iteramos
      * sobre todos los FileItem que recibimos:
      * Los parámetros simples los diferenciaremos de los parámetros 'file'
      * por medio del método isFormField()
      */
     Iterator it = listUploadFiles.iterator();
     while( it.hasNext() ){
         item = ( FileItem ) it.next();
         // 8. evaluamos si el campo es de tipo file, para subir al servidor
         if( !item.isFormField() ){
             //9. verificamos si el archivo es > 0
             if( item.getSize() > 0 ){
                 // 10. obtener el nombre del archivo
                 String nombre   = item.getName();                 
                 // 11. obtener el tipo de archivo
                 // e. .jpg = "image/jpeg", .txt = "text/plain"
                 String tipo     = item.getContentType();
                 // 12. obtener el tamaño del archivo
                 long tamanio    = item.getSize();
                 // 13. obtener la extension
                 String extension = nombre.substring( nombre.lastIndexOf( "." ) );
      
                 //out.println( "Nombre: " + nombre + "<br>");
                 //out.println( "Tipo: " + tipo + "<br>");
                 //out.println( "Extension: " + extension + "<br>");
                 //cambiar por request.getParameter("atributo") y request.setAttribute("atributo", atributo)
                 
                 
                 
                if (request.getParameter("nombre") != null)
                	 request.setAttribute("nombre" , nombre);
         			
                if (request.getParameter("tipo") != null)  
         			request.setAttribute("tipo", tipo);
         			
                 if (request.getParameter("extension") != null)
         			extension = request.getParameter("extension");
                 
                 // 14. determinar si el caracter slash es de linux, o windows
                 String slashType = ( nombre.lastIndexOf( "\\" ) > 0 ) ?  "\\" : "/"; // Windows o Linux
                 //a�ado estas dos l�neas para capturar el atributo
                 if (request.getParameter("slashType") != null)
         			slashType = request.getParameter("slashType");
                 
                 // 15. obtener la ultima posicion del slash en el nombre del archivo
                 int startIndex = nombre.lastIndexOf( slashType );
                 //a�ado estas dos l�neas para capturar el atributo
                 if (request.getParameter("startIndex") != null)
         			startIndex = Integer.parseInt(request.getParameter("startIndex"));
                 
                 // 16. obtener el nombre del archivo ignorando la ruta completa
                 String myArchivo = nombre.substring( startIndex + 1, nombre.length() );
                 //out.println( "Nombre del archivo: " + myArchivo + "<br>");
                 //a�ado estas dos l�neas para capturar el atributo
                 if (request.getParameter("myArchivo") != null)
         			myArchivo = request.getParameter("myArchivo");
                 
                 // 17. Guardo archivo del cliente en servidor, con un nombre 'fijo' y la
// extensión que me manda el cliente,
                 // Create new File object
                 File archivo = new File( dirUploadFiles, myArchivo );
                 String archi = archivo.getAbsolutePath().toString();
                 //String guardado = null;
                  
                 // 18. Write the uploaded file to the system
                 item.write( archivo );
                 //19. Valido el fichero (solo en el caso de XML)
                 if (extension.equals(".xml")){
                	 val.validar(archivo);
                 }
                 
                 
                 if ( archivo.exists() ){
                	 //out.println( "GUARDADO " + archivo.getAbsolutePath() + "</p>");
                	 //out.println( "El archivo se ha importado correctamente.");
                	// if (request.getParameter("guardado") != null)
                	 System.out.println("El archivo existe");
                	    guardado = archivo.getAbsolutePath();
                	    System.out.println(guardado + " es el valor de guardado");
                	 	//fin_parrafo = request.getParameter("</p>");
                	 	guardado_correctamente = "El archivo se ha importado correctamente";
                	 	//archi = request.getParameter(archi);
                 }else{
                     // nunca se llega a ejecutar
                     //out.println( "FALLO AL GUARDAR. NO EXISTE " + archivo.getAbsolutePath() + "</p>");
                        fallo_al_guardar = "FALLO AL GUARDAR. NO EXISTE " + archivo.getAbsolutePath();
                        //archi = request.getParameter(archi);
                        //fin_parrafo = request.getParameter("</p>");
                 }
             	 // 20. Devuelvo el resultado de la validaci�n si el archivo es XML.
                 if (extension.equals(".xml") && val.resultado() == 0){
                	 //out.println("<p>El XML es correcto</p>");
                	 	XML_correcto = "El XML es correcto";
                	 	
                	 	
                 }else if (extension.equals(".xml")){
                	 //out.println("<p><span style='color: red;'>El XML contiene " + val.resultado() + " errores. El archivo XML no es v�lido</span></p>");
                	 	XML_novalido = "<span style='color: red;'>El XML contiene " + val.resultado() + " errores. El archivo XML no es v�lido</span></p>";
                	 //Si el archivo no es v�lido no se guarda
                	 archivo.delete();
                	 //out.println("<p>El XML se ha eliminado del directorio del servidor</p>");
                	 	XML_eliminado = "El XML se ha eliminado del directorio del servidor";
                	 item.delete();
                 }
                 // 21. Si el archivo es CSV se carga en la base de datos
                 if (extension.equals(".csv")){
                	 BBDD bd = new BBDD();
                	 bd.abrirConexion();
                	 Bennett bennett = new Bennett();
                	 bennett.cargaCvs(archivo, bd);
                	 //out.println("<p><span style='color: green;'>El archivo CSV se ha cargado en la base de datos</span></p>");
                	 	csv_cargado = "<p><span style='color: green;'>El archivo CSV se ha cargado en la base de datos</span></p>";
                	 bd.cerrarConexion();
                	 
                	 
                 }
                 //los atributos se incluyen en las variables creadas
                 request.setAttribute("nombre", nombre);
          	   	 request.setAttribute("tipo", tipo);
          	   	 request.setAttribute("tamanio", tamanio);
          	   	 request.setAttribute("extension", extension);
          	   	 request.setAttribute("slashType", slashType);
          	   	 request.setAttribute("startIndex", startIndex);
          	   	 request.setAttribute("myArchivo", myArchivo);
          	   
          	     request.setAttribute("guardado", guardado);
          	     request.setAttribute("guardado_correctamente", guardado_correctamente);
          	     request.setAttribute("fallo_al_guardar", fallo_al_guardar);
          	     request.setAttribute("XML_correcto", XML_correcto);
          	     request.setAttribute("XML_novalido", XML_novalido);
          	     request.setAttribute("XML_eliminado", XML_eliminado);
          	     request.setAttribute("csv_cargado", csv_cargado);
          	     System.out.println("hola");
          	     System.out.println(nombre);
          	     
          	     //las variables se env�an al jsp importar.jsp
          	   	 vista = request.getRequestDispatcher("importar.jsp");
          	   	 vista.forward(request, response);
             }
             
             
         }
     }//end while

 }catch( FileUploadBase.SizeLimitExceededException e ){
     e.printStackTrace(); 
     //out.println("El tamaño del archivo es superior a 100 Mb"); //decidir cuál es el tamaño máximo, ahora 100 Mb
     	tamanio_archivo = request.getParameter("El tamaño del archivo es superior a 100 Mb");
 }catch( FileUploadException e ){
     e.printStackTrace(); 
 }catch (Exception e){
     // poner respuesta = false; si existe alguna problema
     e.printStackTrace();
 } 
 finally{
	// out.close();//los quito para que no escriba por consola
	 
	   
	   
	   
 }
}
}

protected void doGet(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
processRequest(request, response);


}

protected void doPost(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
processRequest(request, response);
}

}
