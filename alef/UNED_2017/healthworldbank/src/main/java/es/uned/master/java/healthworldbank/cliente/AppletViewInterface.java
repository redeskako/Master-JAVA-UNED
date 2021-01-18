package es.uned.master.java.healthworldbank.cliente;

import es.uned.master.java.healthworldbank.datos.Registro;
import org.geotools.map.FeatureLayer;
import org.geotools.swing.JMapPane;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by javierbelogarcia on 27/02/2017.
 */
public interface AppletViewInterface {

    /**
     * Idntificaor de las tablas presentes en la vista
     */
    enum TABLE_ID {COUNTRIES,INDICATOR_GRPAH,INDICATORS_MAP, STATISTICS,YEARS};

    /**
     * Dimensión del contenedor
     */
    int CONTAINER_W = 900;

    /**
     * Dimensión del contenedor
     */
    int CONTAINER_H = 700;

    /**
     * Asigna una referencia controlador
     * @param appletControllerInterface controlador
     */
    void setController(AppletControllerInterface appletControllerInterface);

    /**
     * Actualiza las tabalas
     * @param tableId identificador de la tabla
     * @param mList Datos para la actualización de la tabla
     * @param filter filtro
     * @param totalRecords cantidad total de records en la base de datos
     * @param page página que se va a mostrar
     * @param offset offset utilizado
     */
    void updateTables(TABLE_ID tableId, List<Registro> mList, String filter, int totalRecords, String page, int offset);

    /**
     * Añade los layes al mapa
     * @param featureLayers lista de layes para incluir
     */
    void addMapLayer(ArrayList<FeatureLayer> featureLayers);

    /**
     * Getter del mappane
     * @return
     */
    JMapPane getMapPane();

    /**
     * Envío de mensaje de erros
     * @param error tipo de error
     * @param mensaje mensaje
     */
    void showErrorMessage(String error, String mensaje);









    }
