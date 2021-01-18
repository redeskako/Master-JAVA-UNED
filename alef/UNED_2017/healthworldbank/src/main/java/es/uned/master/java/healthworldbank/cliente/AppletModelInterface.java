package es.uned.master.java.healthworldbank.cliente;

import es.uned.master.java.healthworldbank.comunicacion.Pregunta;
import es.uned.master.java.healthworldbank.comunicacion.Respuesta;

/**
 * Interface del modelo
 * @jbelo
 */
public interface AppletModelInterface {

    /**
     * Establece la referencia al controlador
     * @param appletControllerInterface controlador
     */
    void setController(AppletControllerInterface appletControllerInterface);

    /**
     * Establece la referencia a la vista
     * @param appletViewInterface vista
     */
    void setView(AppletViewInterface appletViewInterface);

    /**
     * Procesa la solicitud de información al servidor.
     * @param pregunta información que se solicita
     * @return información devuelta
     * @throws ServerConnectionException excepción lanzada en caso de haber algún problema.
     */
    Respuesta processRequest(Pregunta pregunta) throws ServerConnectionException;


}
