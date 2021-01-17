package beans;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * La validación es el proceso de verificar que un documento XML cumple con todas las restricciones expresadas en
 * un esquema, ya sea XSD o DTD. En este caso el documento se verifica respecto al DTD que está incluido en el
 * propio documento, aunque también sería posible validarlo con un XSD.
 * Los errores que se producen en el 'parseo' son gestionados por una instancia de la clase 'MyErrorHandler'. 
 * @author alef
 */
public class ValidarXML {
	
	private MyErrorHandler meh = new MyErrorHandler();

	public void validar (File xml) throws Exception {
		
		// Creando la factoria e indicando que hay validacion  
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();  
		documentBuilderFactory.setNamespaceAware(true);  
		documentBuilderFactory.setValidating(true);  

		// Parseando el documento  
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		// Instanciando el gestor de errores
		documentBuilder.setErrorHandler(meh);

		// Se parsea el documento para confirmar que está bien formado  
		documentBuilder.parse(xml); 

		System.out.println("El proceso de validación ha finalizado");
		
    }
	/*
	 * Si el XML se ha parseado correctamente devolverá '0', en caso contrario devolverá el número
	 * de errores que contiene el XML.
	 * Si se llama antes de que el XML sea parseado devolverá '0'.
	 */
	public int resultado(){
		return meh.getErrores();
	}
}


