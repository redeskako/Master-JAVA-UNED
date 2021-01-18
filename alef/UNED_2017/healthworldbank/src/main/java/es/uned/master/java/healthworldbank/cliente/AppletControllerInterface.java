package es.uned.master.java.healthworldbank.cliente;

import es.uned.master.java.healthworldbank.comunicacion.ActionType;

/**
 * Interface del controlador para comunicación con modelo y vista
 * y arranque de la aplicación.
 * @author jbelo
 */
public interface AppletControllerInterface {

    /**
     * Estabelce la referecnia a la vista
     * @param appletViewInterface
     */
    void setView(AppletViewInterface appletViewInterface);

    /**
     * Establece la referencia al modelo
     * @param appletModelInterface
     */
    void setModel(AppletModelInterface appletModelInterface);

    /**
     * Carga inicial de los datos en las tablas
     */
    void loadInitialData();

    /**
     * Carga del mapa inicial
     */
    void loadBaseMap();

    /**
     * Petición de datos para los parametros indicados
     * @param actionType Indica la petición sobre la misma tabla en la paginación
     * @param offset offset actual considerado en la vista
     * @param country país sobre el que se solicitan datos
     * @param indicator indicador sobre el que se solicitan datos
     */
    void requestStatistics(ActionType actionType, int offset, String country, String indicator);

    /**
     * Petición para mostrar datos en mapa de los parámetros pasados como argumento
     * @param indicatorCode indicador sobre el que se solicitan datos
     * @param yearCode año sobre el que se soliciatn datos
     */
    void requestMapData(String indicatorCode, String yearCode);

    /**
     * Usaod para recibir solicitud de acciones por parte de loa botones adelante y atrás
     * @param tableID tabla que realiza la petición
     * @param actionType especifica acción, adelante, atrás o neutro
     * @param offset offset actual considerado en la vista
     */
    void requestDataSingleTable(AppletViewInterface.TABLE_ID tableID, ActionType actionType, int offset);
}
