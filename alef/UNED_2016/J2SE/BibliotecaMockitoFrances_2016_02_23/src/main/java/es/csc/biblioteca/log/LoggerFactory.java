package es.csc.biblioteca.log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;
import org.apache.log4j.Appender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author David Atencia
 * @version 0.1
 */
public class LoggerFactory {
    
    /** Ruta donde se encuentra el fichero de propiedades. */
    private static final String PATH_PROPERTIES = "log4j.properties";
    
    /** Propiedades para el uso de los Logs. */
    private static Properties properties = null;
    
    /** Log de la aplicación. */
    private static Logger logger = null;

    
    /**
     * <b>getLogger</b>
     * Devuelve un Logger
     * @return 
     */
    public static Logger getLogger() {
        // Iniciamos el fichero de propiedades.
        if (properties == null) {
            InputStream is;
            File f = new File(PATH_PROPERTIES);
            try {
                if (!f.exists()) {
                    is = LoggerFactory.class.getResourceAsStream(PATH_PROPERTIES);
                } else {
                    is = new FileInputStream(PATH_PROPERTIES);
                }
                properties = new Properties();
                properties.load(is);
                PropertyConfigurator.configure(properties);
            } catch (IOException ex) {
                properties = null;
            }
        }
        // Obtenemos el logger
        if (properties != null) {
            if (logger == null) {
                logger = Logger.getLogger("");
            }
        }
        return logger;
    }
    
    /**
     * Activa el log de la aplicación
     */
    public static boolean activate() {
        boolean resultado;
        // Activa el log
        Logger.getRootLogger().setLevel(Level.ALL);
        // Establece el valor en el fichero properties
        String valor = "ALL" + getAppenders();
        properties.setProperty("log4j.rootLogger", valor);
        // Guarda el fichero log4j.properties
        resultado = save(true);
        return resultado;
    }
    
    /**
     * Desactiva el log de la aplicación
     */
    public static boolean deactivate() {
        boolean resultado;
        // Desactiva el log
        Logger.getRootLogger().setLevel(Level.OFF);
        // Establece el valor en el fichero properties
        String valor = "OFF" + getAppenders();
        properties.setProperty("log4j.rootLogger", valor);
        // Guarda el fichero log4j.properties
        resultado = save(false);
        return resultado;
    }
    
    /**
     * Obtiene una lista de appenders separadas por comas (,)
     * @return 
     */
    private static String getAppenders() {
        String appenders = "";
        Enumeration e = Logger.getRootLogger().getAllAppenders();
        while (e.hasMoreElements()) {
          Appender app = (Appender) e.nextElement();
          appenders += ", " + app.getName();
        }
        return appenders;
    }
    
    /**
     * Guarda el fichero log4j.properties
     * @param enabled
     * @return 
     */
    private static boolean save(boolean enabled) {
        boolean resultado = true;
        try {
            properties.store(new FileOutputStream(PATH_PROPERTIES), null);
        } catch (FileNotFoundException ex) {
            resultado = false;
            if (logger.isDebugEnabled()) {
                logger.debug("El fichero de configuración no existe.");
            }
        } catch (IOException ex) {
            resultado = false;
            logger.error( ex.getMessage(), ex );
        }
        return resultado;
    }
    
}