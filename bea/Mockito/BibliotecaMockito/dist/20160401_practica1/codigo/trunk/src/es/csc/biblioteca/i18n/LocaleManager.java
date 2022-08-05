package es.csc.biblioteca.i18n;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Observable;
import java.util.ResourceBundle;
import org.apache.log4j.Logger;

/**
 *
 * @author David Atencia
 * @version 0.1
 */
public final class LocaleManager extends Observable {
    
    private Locale currentLocale;
    private ResourceBundle rb;
    
    private static final String BASENAME = "es.csc.biblioteca.i18n.idioma";
    
    protected static final Logger logger = Logger.getLogger(LocaleManager.class);
    
    /**
     * Inicializa el idioma a Ingles (Estados Unidos)
     */
    private LocaleManager() {
        Locale.setDefault(new Locale("en", "US"));
        this.rb = ResourceBundle.getBundle(BASENAME);
        this.currentLocale = new Locale("en", "US");
    }
    
    public static LocaleManager getInstance() {
        return LocaleManagerHolder.INSTANCE;
    }
    
    private static class LocaleManagerHolder {
        private static final LocaleManager INSTANCE = new LocaleManager();
    }
    
    /**
     * Establece el idioma indicado.
     * 
     * @param locale 
     */
    public void setLocale (Locale locale) {
      setLocale(locale.getLanguage(), locale.getCountry());
    }
    
    /**
     * Establece el idioma indicado.
     * 
     * @param language
     * @param country 
     */
    public void setLocale (String language, String country) {
        // Comprobamos que no estemos ya utilizando el idioma indicado
        if (currentLocale != null) {
            if (language.equals(this.currentLocale.getLanguage()) && country.equals(this.currentLocale.getCountry())) {
                return;
            }
        }
        // Establecemos el idioma
        Locale.setDefault(new Locale(language, country));
        this.currentLocale = new Locale(language, country);    
        this.rb = ResourceBundle.getBundle(BASENAME, this.currentLocale);
        // Notificamos a los observadores que se ha cambiado el idioma
        setChanged();
        notifyObservers();
    }
    
    /**
     * Devuelve el locale usado actualmente.
     * 
     * @return 
     */
    public Locale getLocale() {
        return this.currentLocale;
    }
    
    /**
     * Devuelve el texto asociado a la etiqueta indicada. Si la etiqueta no existe 
     * devuelve el nombre de la etiqueta entre admiraciones.
     * 
     * @param tag Nombre de la etiqueta.
     * @return 
     */
    public String getText(String tag) {
        return getText(tag, (Object) null);
    }
    
    /**
     * Devuelve el texto asociado a la etiqueta indicada. Si la etiqueta no existe 
     * devuelve el nombre de la etiqueta entre admiraciones
     * 
     * @param tag Nombre de la etiqueta.
     * @param params Listado de parámetros a sustituir en la etiqueta.
     * @return 
     */
    public String getText(String tag, Object... params) {
        String text;
        try {
            text = MessageFormat.format(this.rb.getString(tag), params);
        } catch (MissingResourceException ex) {
            text = '!' + tag + '!';
            if (logger.isDebugEnabled()) {
                logger.debug(ex.toString());
            }
        } catch (NullPointerException ex) {
            text = '!' + tag + '!';
            logger.error(ex.toString());
        }
        return text;
    }
    
}
