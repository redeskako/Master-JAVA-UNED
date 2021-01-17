package es.csc.biblioteca.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;

/**
 *
 * @author David Atencia
 * @version 0.1
 */
public class Config {

    private static Config _instance = null;
    private Properties properties = null;

    /** Nombre del fichero de configuración */
    public final static String CONFIG_FILE_NAME = "config.properties";
 
    /** Tipo de base de datos mysql u oracle*/
    public final static String DATABASE_DBMS = "database_dbms";
    
    /** Servidor de base de datos */
    public final static String DATABASE_SERVER = "database_server";

    /** Puerto de conexión a la base de datos */
    public final static String DATABASE_PORT = "database_port";

    /** Para base de datos oracle, tipo de conexion sid o servicename */
    public final static String DATABASE_ORACLE_TIPO = "database_oracle_tipo";

    /** Datos de conexion del parametro anterior en caso de ser base de datos Oracle */
    public final static String DATABASE_ORACLE_SERVICIO = "database_oracle_servicio";
    
    
    /** Nombre de la base de datos */
    public final static String DATABASE_CATALOG = "database_catalog";

    /** Usuario de la base de datos */
    public final static String DATABASE_USER = "database_user";

    /** Contraseña del usuario de la base de datos */
    public final static String DATABASE_PSWD = "database_password";
    
    protected static final Logger logger = Logger.getLogger(Config.class);
    
    
    private Config() {
        this.properties = new Properties();        
        try {
            properties.load(new FileInputStream(CONFIG_FILE_NAME));
        } catch (FileNotFoundException ex) {
            if (logger.isDebugEnabled()) {
                logger.debug("Configuration file not found.");
            }
            logger.error( ex.getMessage(), ex );
        } catch (IOException ex) {
            logger.error( ex.getMessage(), ex );
        }
    }
    
    public synchronized static Config getInstance() {
        if (_instance == null) {
            _instance = new Config();
        }
        return _instance;
    }

    /**
     * Devuelve el parámetro de configuración indicado
     *
     * @param key Parámetro de configuración
     * @return String
     */
    public String getProperty(String key) {
        return this.properties.getProperty(key);
    }
    
    /**
     * Devuelve el parámetro de configuración indicado, si no existe devuelve
     * el valor por defecto
     * 
     * @param key Parámetro de configuración
     * @param defaultValue Valor por defecto
     * @return String
     */
    public String getProperty(String key, String defaultValue) {
        return this.properties.getProperty(key, defaultValue);
    }
    
    /**
     * Establece el valor del parámetro de configuración indicado
     * 
     * @param key Parámetro de configuración
     * @param value Valor
     */
    public void setProperty(String key, String value) {
        this.properties.setProperty(key, value);
    }
    
    /**
     * Guarda las opciones de configuración en el fichero properties
     */
    public void save() {
        try {
            properties.store(new FileOutputStream(CONFIG_FILE_NAME), null);
        } catch (FileNotFoundException ex) {
            if (logger.isDebugEnabled()) {
                logger.debug("Configuration file not found.");
            }
            logger.error( ex.getMessage(), ex );
        } catch (IOException ex) {
            logger.error( ex.getMessage(), ex );
        }
    }

}