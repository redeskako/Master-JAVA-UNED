package es.uned.master.java.healthworldbank.cliente;

import es.uned.master.java.healthworldbank.comunicacion.ActionType;

/**
 * Interface para el listener de TablePaginationHandler
 * @jbelo
 */
public interface TablePaginationHandlerListener {

    /**
     * Método disparado cuando se hace acciones sobre la tabla
     * @param tableId
     */
    void tableEventRowSelected(AppletViewInterface.TABLE_ID tableId);

    /**
     * Método disparado cuando se hacen acciones sobre los botones
     * @param tableId identificador de la tabla
     * @param actionType tipo de acción , adelante- atrás
     * @param offset offset
     */
    void tableButtonsActionListener(AppletViewInterface.TABLE_ID tableId, ActionType actionType, int offset);

}
