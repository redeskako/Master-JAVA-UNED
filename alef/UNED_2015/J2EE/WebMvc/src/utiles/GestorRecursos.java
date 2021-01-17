package utiles;


import java.util.*;

public class GestorRecursos{
	
	
	private ResourceBundle rb ;
	
	
	public GestorRecursos() {		
		
	}    
	// Parametro de entrada: Clave del fichero de mensajes
	// Devuelve el mensanje correspondiente a la clave
	
	
	// Devuelve el valor de la clave solicitada del fichero  de propiedades
	public String getPropiedad(String clave){
		//El archivo .properties 
		//Est· en el paquete utiles		
		//formato: nombreDePaquete . nombreFicheroPropiedades 
		//no hay que incluir la extensiÛn .properties
		String ficheroPropiedades= "utiles.crawler";
		this.rb= ResourceBundle.getBundle(ficheroPropiedades);	
		//Para obtener el valor de una  clave se utiliza asi:	
		return rb.getString(clave);
	}		
}