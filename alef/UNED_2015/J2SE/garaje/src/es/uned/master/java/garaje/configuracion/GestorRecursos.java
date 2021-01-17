package es.uned.master.java.garaje.configuracion;

import java.util.*;

public class GestorRecursos{
	
	private String idioma;
	private ResourceBundle rb ;
	
	public GestorRecursos(String idioma) {		
		this.idioma = idioma;
	}
	public GestorRecursos() {		
		this.idioma = "es";
	}
	
	
	public String getIdioma() {
		return idioma;
	}


	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
    
	// Parametro de entrada: Clave del fichero de mensajes
	// Devuelve el mensanje correspondiente a la clave
	
	public String muestraMensaje(String clave){
		//El archivo .properties se llama  mensajes_XX.properties
		//XX= Idioma
		//Est· en el paquete es.uned.master.java.garaje.configuracion		
		//construimos el nombre de fichero de propiedades aÒadiendo el idioma
		//formato: nombreDePaquete . nombreFicheroMensajes 
		//no hay que incluir la extensiÛn .properties
		String ficheroMensajes= "es.uned.master.java.garaje.configuracion.mensajes_" + this.idioma;
		this.rb = ResourceBundle.getBundle(ficheroMensajes);		
		//Para obtener el valor de  una clave se utiliza asi:	
		return rb.getString(clave);
	}	
	// Devuelve el valor de la clave solicitada del fichero  de propiedades
	public String getPropiedad(String clave){
		//El archivo .properties 
		//Est· en el paquete es.uned.master.java.garaje.configuracion		
		//formato: nombreDePaquete . nombreFicheroPropiedades 
		//no hay que incluir la extensiÛn .properties
		String ficheroPropiedades= "es.uned.master.java.garaje.configuracion.garaje";
		this.rb= ResourceBundle.getBundle(ficheroPropiedades);	
		//Para obtener el valor de una  clave se utiliza asi:	
		return rb.getString(clave);
	}		
}
