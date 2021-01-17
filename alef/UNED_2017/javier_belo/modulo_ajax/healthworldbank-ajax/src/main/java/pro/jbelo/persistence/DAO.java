package pro.jbelo.persistence;

import pro.jbelo.model.DataBean;
import pro.jbelo.utils.ServerRunningException;

import java.util.List;

/**
 * @author jbelo
 * @date 2017 05.
 * Interface para la implemmentación de un Data Access Object. Por falta de tiempo no se ha implementado
 */
public interface DAO {


    /**
     * Crea nuevo país
     * @param countryEntity pais para crear
     * @throws ServerRunningException
     */
    void createCountry(CountryEntity countryEntity) throws ServerRunningException;

    /**
     * Lee pais
     * @param countryId identificador único del país que se quiere
     * @return País
     * @throws ServerRunningException
     */
    CountryEntity readCountry(String countryId) throws ServerRunningException;

    /**
     * Devuelve lista de paises
     * @return lista de países
     * @throws ServerRunningException en caso de haber problemas en la solicitud
     */
    List<CountryEntity> readCountries() throws ServerRunningException;

    /**
     * Devuelve lista de países
     * @param recordsPage cantidad de registros por página, esto será el máximo de registros devuelto
     * @param offset el offset teniendo en cuenta el número de página en el que se encuentra
     * @return lista de países
     * @throws ServerRunningException exception en caso de problemas con la operación
     */
    List<CountryEntity> readCountries(int recordsPage, int offset) throws ServerRunningException;

    /**
     * Actualiza país
     * @param countryEntity país para actualizar
     * @throws ServerRunningException
     */
    void updateCountry(CountryEntity countryEntity) throws ServerRunningException;

    /**
     * Borrar país
     * @param countryCode
     * @return devuelve lista de paises actualizada
     * @throws ServerRunningException
     */
    List<CountryEntity> deleteCountry(String countryCode) throws ServerRunningException;

    /**
     * Crea nuevo indicador de salud
     * @param healthIndicatorEntity nuevo indicador de salud para ser creado
     * @throws ServerRunningException
     */
    void createHealthIndicator(HealthIndicatorEntity healthIndicatorEntity) throws ServerRunningException;

    /**
     * Devuelve el indicador de salud para los parámetros pasados
     * @param indicatorId identificador del indicador de salud
     * @return indicador de salud
     * @throws ServerRunningException
     */
    HealthIndicatorEntity readHealthIndicator(String indicatorId) throws ServerRunningException;

    /**
     * Deveulve el listado de indicadores de salud
     * @return lsitado de indicadores
     * @throws ServerRunningException exception en caso de haber problemas con la operación
     */
    List<HealthIndicatorEntity> readHealthIndicators() throws ServerRunningException;

    /**
     * Devuelve indicadores de salud
     * @param recordsPage cantidad de registros devueltos máximo
     * @param offset offset en función del numero de página
     * @return lista de indicadores
     * @throws ServerRunningException exception en caso de problemas en la operación
     */
    List<HealthIndicatorEntity> readHealthIndicators(int recordsPage, int offset) throws ServerRunningException;

    /**
     * Actualiza el indicador de salud
     * @param healthIndicatorEntity
     * @throws ServerRunningException
     */
    void updateHealthIndicator(HealthIndicatorEntity healthIndicatorEntity) throws ServerRunningException;

    /**
     * Borra el indicador de salud
     * @param indicatorCode identificador del indicador de salud
     * @throws ServerRunningException
     */
    void deleteHealthIndicator(String indicatorCode) throws ServerRunningException;

    /**
     * Crea dato sobre indicador de salud
     * @param dataEntity
     * @throws ServerRunningException
     */
    void createData(DataEntity dataEntity) throws ServerRunningException;

    /**
     * Listado de indicadores de salud
     * @return
     * @throws ServerRunningException
     */
    List<DataEntity> readData() throws ServerRunningException;

    /**
     * Devuelve indicador
     * @param indicatorCode identificador del indicador
     * @param countryCode identificador del país
     * @param year año
     * @return datos para los parametros pasados
     * @throws ServerRunningException
     */
    DataEntity readData(String indicatorCode, String countryCode, int year) throws ServerRunningException;

    /**
     * Devuelve datos de inidcaodres
     * @param countryCode código del país para el que se solicitan los datos
     * @param indicatorCOde código del indicador para el que se soliciatan los datos
     * @param offset offset de datos en función de la página solicitada
     * @param recordsPage cantidad máxima de registros devueltos
     * @return listado de datos
     * @throws ServerRunningException exception en caso de problemas en la operación
     */
    List<DataEntity> readData(String countryCode, String indicatorCOde, int offset, int recordsPage) throws ServerRunningException;

    /**
     * Actualiza datos
     * @param dataEntity datos pasado
     * @throws ServerRunningException
     */
    void updateData(DataEntity dataEntity) throws ServerRunningException;

    /**
     * Borra datos
     * @param indicatorCode identificador del indicador
     * @param countryCode identificador del país
     * @param yearParam año
     * @throws ServerRunningException
     */
    void deleteData(String indicatorCode, String countryCode, int yearParam) throws ServerRunningException;

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
     * Listado de años
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
    List<DataMapWrapper> requestGeoStatistics(String indicator, String year) throws ServerRunningException;


}
