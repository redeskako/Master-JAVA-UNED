/**
 * Gestión básica de una biblioteca.
 * @author Antonio Dorado (Original: Daniel Murygin (daniel.murygin@gmail.com))
 * @version 2.1 - 15/12/2015
 */

package com.csc.biblioteca.util;

import com.csc.biblioteca.excepciones.*;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Clase wrapper para el acceso a los recursos de idioma: etiquetas y frases.
 * Permite definir mensajes con número de parámetros variable. Por ejemplo:
 * 
 * <PRE> Messages.getString("ETIQUETA", user.getName(), item.getTitle(), group.getName());</PRE>
 * 
 * En idioma_en_EN.properties: ETIQUETA=User {0} added new item {1} to group {2}.<br>
 * En idioma_de_DE.properties: ETIQUETA=Das Objekt {1} wurde von Benutzer {0} zur Gruppe {2} hinzugef\u00FCgt.
 * 
 * Gestiona las ventanas de información, aviso, error y pregunta al usuario de la aplicación.
 */
public class Messages {
    public enum Tipo {INFORMATION, WARNING, ERROR, QUESTION}
    
    /** Idioma español de España. */
    public static final String SPANISH = "es_ES";

    /** Idioma inglés de Reino Unido. */
    public static final String ENGLISH = "en_GB";
    
    private static final String TITULO_AVISO = "TITULO_AVISO";
    private static final String TITULO_ERROR = "TITULO_ERROR";
    private static final String TITULO_ATENCION = "TITULO_ATENCION";
    private static final String TITULO_PREGUNTA = "TITULO_PREGUNTA";
    
    // Fichero de propiedades por defecto: com/csc/master/java/i18n/idioma.properties
    private static ResourceBundle idioma = ResourceBundle.getBundle(Constantes.BUNDLE_NAME);
    
    private static JFrame ventana;
    private static Locale locale;
    
    // Para que no se pueda instanciar un objeto de esta clase
    private Messages() {}

    /**
     * Establecer la ventana principal de la aplcicación que será padre de todos
     * los mensajes de aviso al usuario (aviso, alerta, error y pregunta).
     * @param padre Ventana principal de la aplicación.
     */
    public static void setVentana(JFrame padre) {
        ventana = padre;
    }
    
    /**
     * Establecer la localización indicada para la obtención de los recursos del idioma.
     * @param idioma Código de idioma a establecer (es-ES, en-EN, ...).
     */
    public static void setLocale(String idioma) {
        switch (idioma) {
            case ENGLISH:
                locale = new Locale("en", "GB");
                break;
            default:
                locale = new Locale("es", "ES");
                break;
        }
        Messages.idioma = ResourceBundle.getBundle(Constantes.BUNDLE_NAME, locale);
    }
    
    /**
     * Obtener la localización establecida en la aplicación.
     * @return Localización actualmente establecida.
     */
    public static Locale getLocale() {
        return locale;
    }
    
    /**
     * Mostrar un mensaje emergente del tipo indicado con la clave y parámetros indicados.
     * @param tipo Tipo de mensaje {INFORMATION, WARNING, ERROR, QUESTION}.
     * @param key Clave del recurso que contiene el mensaje a mostrar.
     * @param params Argumentos del mensaje (opcional y variable).
     * @return Respuesta del usuario en las preguntas (por defecto 0).
     */
    public static int mensaje(Tipo tipo, String key, Object... params) {
        int valor = 0;
        String mensaje = getString(key, params);
                
        switch (tipo) {
            case INFORMATION:
                JOptionPane.showMessageDialog(ventana, mensaje, Messages.getString(TITULO_AVISO), 
                        JOptionPane.INFORMATION_MESSAGE);
                break;
            case WARNING:
                JOptionPane.showMessageDialog(ventana, mensaje, Messages.getString(TITULO_ATENCION), 
                        JOptionPane.WARNING_MESSAGE);
                break;
            case ERROR:
                JOptionPane.showMessageDialog(ventana, mensaje, Messages.getString(TITULO_ERROR), 
                        JOptionPane.ERROR_MESSAGE);
                break;
            case QUESTION:
                valor = JOptionPane.showConfirmDialog(ventana, mensaje, Messages.getString(TITULO_PREGUNTA), 
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        }
        
        return valor;
    }
    
    /**
     * Mostrar un mensaje de error al usuario.
     * @param error Excepción con los datos del error producido.
     * @param key Clave del mensaje a mostrar.
     * @param params Parámetros variables del mensaje a mostrar.
     * @return Valor fijo a 0.
     */
    public static int mensaje(ErrorException error, String key, Object... params) {
        //MSG_ERROR_EXCEPTION={0}\nCódigo: {1}\n\nDetalle: {2}
        return mensaje(Tipo.ERROR, "MSG_ERROR_EXCEPTION", getString(key, params), error.getErrorCode(), error.getMessage());
    }

    /**
     * Mostrar un mensaje de atención al usuario.
     * @param info Excepción con los datos del evento producido.
     * @param key Clave del mensaje a mostrar.
     * @param params Parámetros variables del mensaje a mostrar.
     * @return Valor fijo a 0.
     */
    public static int mensaje(InfoException info, String key, Object... params) {
        return mensaje(Tipo.WARNING, "MSG_INFO_EXCEPTION", getString(key, params), info.getErrorCode(), info.getMessage());
    }
    
    /**
     * Acceso a una etiqueta del fichero de recursos.
     * Devuelve el nombre de la etiqueta entre admiraciones si no existe.
     * @param key Nombre de la etiqueta.
     * @return Valor de la etiqueta.
     */
    public static String getString(String key) {
        try {
            return idioma.getString(key);
        } catch (MissingResourceException e) {
            return '!' + key + '!';
        }
    }
	
    /**
     * Acceso a una frase con variables del fichero de recursos.
     * Devuelve el nombre de la etiqueta entre admiraciones si no existe.
     * @param key Nombre de la etiqueta.
     * @param params Listado de parámetros a sustituir en la frase.
     * @return Frase traducida al idioma establecido.
     */
    public static String getString(String key, Object... params) {
        try {
            return MessageFormat.format(idioma.getString(key), params);
        } catch (MissingResourceException e) {
            return '!' + key + '!';
        }
    }
}
