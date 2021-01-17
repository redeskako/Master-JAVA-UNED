package pro.jbelo.persistence;

import pro.jbelo.beans.DataBean;
import pro.jbelo.utils.ServerRunningException;

import java.util.List;

/**
 * @author jbelo
 * @date 2017 05.
 * Interface para la implemmentación de un Data Access Object. Por falta de tiempo no se ha implementado
 */
public interface DAO {

    /**
     * Devuelve lista de paises
     * @return lista de países
     * @throws ServerRunningException en caso de haber problemas en la solicitud
     */
    List<CountryEntity> requestCountries() throws ServerRunningException;

    /**
     * Devuelve lista de países
     * @param recordsPage cantidad de registros por página, esto será el máximo de registros devuelto
     * @param offset el offset teniendo en cuenta el número de página en el que se encuentra
     * @return lista de países
     * @throws ServerRunningException exception en caso de problemas con la operación
     */
    List<CountryEntity> requestCountries(int recordsPage, int offset) throws ServerRunningException;

    /**
     * Deveulve el listado de indicadores de salud
     * @return lsitado de indicadores
     * @throws ServerRunningException exception en caso de haber problemas con la operación
     */
    List<HealthIndicatorEntity> requestIndicators() throws ServerRunningException;

    /**
     * Devuelve indicadores de salud
     * @param recordsPage cantidad de registros devueltos máximo
     * @param offset offset en función del numero de página
     * @return lista de indicadores
     * @throws ServerRunningException exception en caso de problemas en la operación
     */
    List<HealthIndicatorEntity> requestIndicators(int recordsPage, int offset) throws ServerRunningException;

    /**
     * Devuelve datos de inidcaodres
     * @param countryCode código del país para el que se solicitan los datos
     * @param indicatorCOde código del indicador para el que se soliciatan los datos
     * @param offset offset de datos en función de la página solicitada
     * @param recordsPage cantidad máxima de registros devueltos
     * @return listado de datos
     * @throws ServerRunningException exception en caso de problemas en la operación
     */
    List<DataEntity> requestStatistics(String countryCode, String indicatorCOde, int offset, int recordsPage) throws ServerRunningException;

    /**
     * Devuelve el número total de estadísticas
     * @param indicatorCode código del indicador para el cual se quieren los datos
     * @param countryCode código del país para el cual se solicitan los datos
     * @return cantidad de datos
     * @throws ServerRunningException exception en caso de problemas en la solicitud
     */
    int calculateTotalStadistics(String indicatorCode, String countryCode) throws ServerRunningException;

    /**
     * Cantidad de registros presentes en una tabla
     * @param tables_name nombre de la tbala
     * @return candidad de registros
     * @throws ServerRunningException exception en caso de problemas en la solicitud
     */
    int calculateTotalRecordsSingleTable( DataBean.TABLES_NAME tables_name) throws ServerRunningException;

    /**
     * Listado de ños
     * @return lista con años
     * @throws ServerRunningException exception en caso de problemas en la operación
     */
    List<YearEntity> requestYears() throws ServerRunningException;

    /**
     * Listado de datos par aun indicador y un año
     * @param indicator indicador para el que se solicitan los datos
     * @param year año para el que se solicitanl os datos
     * @return listado conteniendo el pais el índice del indicador
     * @throws ServerRunningException exception en caso de problemas durante la operación
     */
    List<DataMapEntity> requestGeoStatistics(String indicator, String year) throws ServerRunningException;


}
