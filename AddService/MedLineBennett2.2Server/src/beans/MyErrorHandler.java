package beans;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
/**
 * Gestiona los errores producidos al parsear el XML. Hay tres tipos de errores
 * 'Warning', 'Error' y 'FatalError'
 * @author alef
 */

public class MyErrorHandler implements ErrorHandler {
	
	private int errores;//Controla el número de errores totales
	 
    public void warning(SAXParseException exception) throws SAXException {
    	errores++;
        System.out.println("\nWARNING");
        exception.printStackTrace();
    }
 
    public void error(SAXParseException exception) throws SAXException {
    	errores++;
        System.out.println("\nERROR");
        exception.printStackTrace();
    }
 
    public void fatalError(SAXParseException exception) throws SAXException {
    	errores++;
        System.out.println("\nFATAL ERROR");
        exception.printStackTrace();
    }
    
    public int getErrores(){
    	return this.errores;
    }
 
}