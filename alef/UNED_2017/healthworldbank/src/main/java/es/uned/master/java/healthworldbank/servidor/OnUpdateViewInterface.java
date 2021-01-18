package es.uned.master.java.healthworldbank.servidor;

import java.awt.event.ActionListener;


/**
 * Interface para comunicar con la vista
 * @author jbelo
 */
public interface OnUpdateViewInterface {

    /**
     * Identificador del botón PARAR servidor
     */
    String BUTTON_STOP_SERVER = "stop_server";

    /**
     * Identificador del botón ARRANCAR servidor
     */
    String BUTTON_START_SERVER = "start_server";

    /**
     * Asigan el ActionListener que escuchará los botones de la vista
     * @param actionListener el ActionListener que escuchará los botones
     */
    void setActionListener(ActionListener actionListener);

    /**
     * Empaqueta y hace visible la vista
     */
    void start();

    /**
     * Establece el estado de la vista actualizando sus componentes
     * @param running indica si el servisor esstá corriendo o no
     * @param notification mensaje para mostrar al usuario
     */
    void setStatus(boolean running, String notification );

    /**
     * Muestra mensaje al usuario
     * @param message mesaje para el usuario
     */
    void displayMessage(String message);

}
