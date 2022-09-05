import es.csc.biblioteca.gui.main.DesktopMDIController;
import es.csc.biblioteca.i18n.LocaleManager;
import es.csc.biblioteca.log.LoggerFactory;
import es.csc.biblioteca.util.Config;
import java.sql.SQLException;
import java.util.Locale;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.apache.log4j.Logger;

/**
 *
 * @author David Atencia
 * @version 0.1
 */
public class Driver {

    protected static final Config config = Config.getInstance();
    protected static final Logger logger = LoggerFactory.getLogger();
    
    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException {
        if (logger.isDebugEnabled()) {
            logger.debug("Application start");
            logger.debug("Biblioteca v1.0.0");
            logger.debug(System.getProperties().toString());
        }
        setLookAndFeel();
        setLanguage();
        new DesktopMDIController();
    }
    
    private static void setLookAndFeel() {
        // Establecemos el aspecto "look and feel" de la aplicación
        String lookAndFeel = config.getProperty("lookandfeel", UIManager.getSystemLookAndFeelClassName());
        try {
            UIManager.setLookAndFeel(lookAndFeel);
        } catch (ClassNotFoundException ex) {
            logger.error( ex.getMessage(), ex );
        } catch (InstantiationException ex) {
            logger.error( ex.getMessage(), ex );
        } catch (IllegalAccessException ex) {
            logger.error( ex.getMessage(), ex );
        } catch (UnsupportedLookAndFeelException ex) {
            logger.error( ex.getMessage(), ex );
        } 
    }
    
    
    private static void setLanguage() {
        Locale locale;
        String language = config.getProperty("language", "en_US");
        switch (language) {
            case "es_ES":
                locale = new Locale("es","ES");
                break;
            case "en_US":
                locale = new Locale("en","US");
                break;
            default:
                locale = new Locale("en","US");
                break;
        }
        LocaleManager.getInstance().setLocale(locale);
        //Locale.setDefault(locale);
        //JFileChooser.setDefaultLocale(locale);
        //DateFormat df = DateFormat.getDateInstance(DateFormat.LONG, locale);
    }
    
}
