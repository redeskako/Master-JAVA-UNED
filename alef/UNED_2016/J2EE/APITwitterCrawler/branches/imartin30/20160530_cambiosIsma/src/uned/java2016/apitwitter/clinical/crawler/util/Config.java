package uned.java2016.apitwitter.clinical.crawler.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;



public class Config {

    private static Config _instance = null;
    private Properties properties = null;

    public final static String CONFIG_FILE_NAME = "conf\\crawler-clinical.properties";
    public final static String DIRECTORY_UNZIP = "directory_unzip";
    public final static String RESULT_FILE = "result_file";
    

    protected static final Logger logger = LogManager.getLogger(Config.class);
    
    private Config() {
        this.properties = new Properties();        
        try {
            properties.load(new FileInputStream(CONFIG_FILE_NAME));
        } catch (FileNotFoundException ex) {
            if (logger.isDebugEnabled()) {
                logger.debug("Archivo de confiuracion no encontrado.");
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


    public String getProperty(String key) {
        return this.properties.getProperty(key);
    }
    

    public String getProperty(String key, String defaultValue) {
        return this.properties.getProperty(key, defaultValue);
    }
 
    public void setProperty(String key, String value) {
        this.properties.setProperty(key, value);
    }

    public void save() {
        try {
            properties.store(new FileOutputStream(CONFIG_FILE_NAME), null);
        } catch (FileNotFoundException ex) {
            if (logger.isDebugEnabled()) {
                logger.debug("Archivo de confiuracion no encontrado.");
            }
            logger.error( ex.getMessage(), ex );
        } catch (IOException ex) {
            logger.error( ex.getMessage(), ex );
        }
    }

}