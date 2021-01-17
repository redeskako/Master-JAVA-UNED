package pro.jbelo.beans;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import pro.jbelo.persistence.*;
import pro.jbelo.utils.Charts;
import pro.jbelo.utils.ServerRunningException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jbelo
 * @date 2017 04.
 */
@Named("dataBean")
@SessionScoped
public class DataBean implements Serializable{

    @Inject
    DAOImplementation daoImplementation;


    @PostConstruct
    public void setInitial(){
        try {
            retrieveCountries("neutral");
            retrieveIndicators("neutral");
            retrieveYears();
        }catch (ServerRunningException e){
            // Implement catch process
        }
        chart = new Charts();
        chart.changeDataSet((ArrayList<DataEntity>)(Object)statisticsContainer.getEntities());
        chartImage = chart.createImage();
    }

    @PreDestroy
    public void setClosing(){
        // Implement closing tasks here
    }

    /**
     * Cantidad máxima de registros por página para ser mostradas
     */
    private static final int RECORDS_PER_PAGE = 20;

    /**
     * Tablas presentes en la base de datos
     */
    public enum TABLES_NAME {COUNTRY, DATA, HEALTH_INDICATOR}

    /**
     * Instacia de Chart encargada de generar el gráfico
     */
    private Charts chart;

    /**
     * Array de bytes para almacenar la imagen generada por Chart
     */
    private byte[] chartImage;

    /**
     * Alamcena los paises que se muestran al usuario
     */
    private final EntitiesContainer countryContainer = new EntitiesContainer();

    /**
     * Alamacena los indicadores que se muestran al usuario
     */
    private final EntitiesContainer indicatorsContainer = new EntitiesContainer();

    /**
     * Alamcena las estadisticas que se han solicitado
     */
    private final EntitiesContainer statisticsContainer = new EntitiesContainer();

    /**
     * Almacena los años que se muestran al usuario
     */
    private final ArrayList<YearEntity> years = new ArrayList<>();

    /**
     * Almacena los datos para mostrar en el mapa
     */
    private final ArrayList<DataMapEntity> dataMapEntities = new ArrayList<>();

    /**
     * Constructor
     */
    public DataBean() {

    }

    /**
     * Recurpera el gráfico como um array de bytes para ser enviado a la vista
     * @return le gráfico como un array de bytes
     */
    public synchronized byte[] getChartImage() {
        chartImage = chart.createImage();
        return chartImage;
    }

    /**
     * Setter de chartImage
     * @param chartImage
     */
    public synchronized void setChartImage(byte[] chartImage) {
        this.chartImage = chartImage;
    }

    /**
     * Getter de countryContainer
     * @return countryContainer
     */
    public synchronized EntitiesContainer getCountryContainer() {
        return countryContainer;
    }

    /**
     * Getter indicatorContainer
     * @return indicatorContainer
     */
    public synchronized EntitiesContainer getIndicatorsContainer() {
        return indicatorsContainer;
    }

    /**
     * Getter de statisticsContainer
     * @return statisticsContainer
     */
    public synchronized EntitiesContainer getStatisticsContainer() {
        return statisticsContainer;
    }

    /**
     * Getter years
     * @return years
     */
    public synchronized ArrayList<YearEntity> getYears() {
        return years;
    }

    /**
     * Getter dataMapEntities
     * @return dataMapEntities
     */
    public synchronized ArrayList<DataMapEntity> getDataMapEntities() {
        return dataMapEntities;
    }

    /**
     * Recupera de la base de datos los paises teniendo en cuenta la acción de paginación indicada, adelante, atrás o neutral
     * @param action
     * @throws ServerRunningException
     */
    public synchronized void retrieveCountries(String action) throws ServerRunningException {
        int totalRecords = 0;
        int newOffset = 0;
        String page;

        try {
            totalRecords = daoImplementation.calculateTotalRecordsSingleTable(TABLES_NAME.COUNTRY);
            newOffset = calculateNewOffset(action, countryContainer.getOffset(), totalRecords);
            page = calculatePage(newOffset,totalRecords);

            List<CountryEntity> countryList = daoImplementation.requestCountries(RECORDS_PER_PAGE,newOffset);

            countryContainer.getEntities().clear();
            countryContainer.setOffset(newOffset);

            for(CountryEntity c : countryList){
                CountryEntity reg = new CountryEntity();
                reg.setCountryCode(c.getCountryCode());
                reg.setCountryName(c.getCountryName());
                countryContainer.getEntities().add(reg);
            }
        } catch (ServerRunningException e){
            throw new ServerRunningException(e.getLocalizedMessage());
        }
    }

    /**
     * Recupera los indicadores teniendo en cuenta la acción de paginación indicada, adelante, atrás o neutral
     * @param action acción de paginación
     * @throws ServerRunningException
     */
    public synchronized void retrieveIndicators(String  action) throws ServerRunningException {
        int totalRecords = 0;
        int newOffset = 0;
        String page;

        try {
            totalRecords = daoImplementation.calculateTotalRecordsSingleTable(TABLES_NAME.HEALTH_INDICATOR);
            newOffset = calculateNewOffset(action, indicatorsContainer.getOffset(), totalRecords);
            page = calculatePage(newOffset,totalRecords);

            List<HealthIndicatorEntity> mList = daoImplementation.requestIndicators(RECORDS_PER_PAGE,newOffset);

            indicatorsContainer.getEntities().clear();
            indicatorsContainer.setOffset(newOffset);

            for(HealthIndicatorEntity h: mList){
                HealthIndicatorEntity reg = new HealthIndicatorEntity();
                reg.setIndicatorCode(h.getIndicatorCode());
                reg.setIndicatorName(h.getIndicatorName());
                reg.setSourceNote(h.getSourceNote());
                reg.setSourceOrganization(h.getSourceOrganization());
                indicatorsContainer.getEntities().add(reg);
            }
        }catch(ServerRunningException ex){
            throw new ServerRunningException(ex.getMessage());
        }

    }

    /**
     * Recupera los datos para un indicador y pais dado
     * @param action acción de paginación
     * @param countryCode  código del país
     * @param indicatorCode código del indicador
     * @throws ServerRunningException
     */
    public synchronized void retrieveStatistics(String  action, String countryCode, String indicatorCode) throws ServerRunningException {
        ResultSet resultSet = null;
        int totalRecords = 0;
        int newOffset = 0;
        String page;

        try{
            totalRecords = daoImplementation.calculateTotalStadistics(indicatorCode,countryCode);
            newOffset = calculateNewOffset(action, statisticsContainer.getOffset(), totalRecords);
            page = calculatePage(newOffset,totalRecords);

            List<DataEntity> mList = daoImplementation.requestStatistics(countryCode,indicatorCode,newOffset,RECORDS_PER_PAGE);

            statisticsContainer.getEntities().clear();
            statisticsContainer.setOffset(newOffset);

            for(DataEntity d :mList){
                DataEntity reg = new DataEntity();
                reg.setIndicatorCode(d.getIndicatorCode());
                reg.setCountryCode(d.getCountryCode());
                reg.setYear(d.getYear());
                reg.setPercentage(d.getPercentage());
                statisticsContainer.getEntities().add(reg);
            }

            chart.changeDataSet((ArrayList<DataEntity>)(Object)statisticsContainer.getEntities());
        }catch(ServerRunningException e){
            throw new ServerRunningException(e.getMessage());
        }
    }

    /**
     * Recupera los años
     * @throws ServerRunningException
     */
    public synchronized void retrieveYears() throws ServerRunningException{
        ResultSet resultSet = null;

        try {

            List<YearEntity> mList = daoImplementation.requestYears();
            years.clear();

            for(YearEntity y: mList){
                YearEntity reg = new YearEntity();
                reg.setYear(y.getYear());
                years.add(reg);
            }

        }catch(ServerRunningException ex){
            throw new ServerRunningException(ex.getMessage());
        }
    }

    /**
     * Recupera las estadísticas para el mapa teniendo en cuenta  el indicador y el año
     * @param indicator
     * @param year
     * @throws ServerRunningException
     */
    public synchronized void retrieveGeoStatistic(String indicator, String year) throws ServerRunningException{
        ResultSet resultSet = null;

        try {
            List<DataMapEntity> mList = daoImplementation.requestGeoStatistics(indicator,year);

            dataMapEntities.clear();

            for (DataMapEntity d: mList){
                DataMapEntity reg = new DataMapEntity();
                reg.setCountry(d.getCountry());
                reg.setPercentage(d.getPercentage());
                dataMapEntities.add(reg);
            }

        }catch(ServerRunningException ex){
            throw new ServerRunningException(ex.getMessage());
        }

    }


    /**
     * Calcula el offset a aplicar según los criterios de acción de paginación, el offset actual y el número totalde records
     * @param action acción de paginación
     * @param currentOffset offset actual
     * @param totalRecords número total de records
     * @return offset calculado
     */
    private int calculateNewOffset(String action, int currentOffset, int totalRecords){
        int calculatedOffset = 0;
        if(action.equals("neutral")){
            calculatedOffset = currentOffset;
        }else if(action.equals("forward")){
            if(totalRecords > currentOffset + RECORDS_PER_PAGE){
                calculatedOffset = currentOffset + RECORDS_PER_PAGE;
            } else if (totalRecords <= currentOffset + RECORDS_PER_PAGE && totalRecords > currentOffset){
                calculatedOffset = currentOffset;
            } else if (totalRecords < currentOffset) {
                int latestRecords = totalRecords%RECORDS_PER_PAGE;
                calculatedOffset = totalRecords - latestRecords;
            }

        }else if(action.equals("backward")){

            if(currentOffset - RECORDS_PER_PAGE <= 0){
                calculatedOffset = 0;
            } else {
                calculatedOffset = currentOffset - RECORDS_PER_PAGE;
            }
        }
        return calculatedOffset;
    }


    /**
     * Calcula la página en la que se encentra teniendo en cuenta el offset y el número total de registros
     * @param offset offset
     * @param totalRecords número total de registros
     * @return la página en la que se encuentra
     */
    private String calculatePage(int offset, int totalRecords){
        String totalPages;
        String currrentPage;
        int current;

        int total = totalRecords%RECORDS_PER_PAGE > 0?totalRecords/RECORDS_PER_PAGE + 1:totalRecords/RECORDS_PER_PAGE;
        totalPages = String.valueOf(total);

        if(totalRecords > 0) {
            current = offset/RECORDS_PER_PAGE + 1;
        } else {
            current = 0;
        }
        currrentPage = String.valueOf(current);
        return currrentPage + "-" + totalPages;
    }

    /**
     * Método para responder a las peticiones AJAX y devolver los datos en formato JSON
     * @param object objeto para pasar a AJAX
     * @return JSON como string
     */
    public synchronized String objectToJson(Object object){
        String objectJson = "";
        ObjectMapper mapper = new ObjectMapper();
        try {
            objectJson = mapper.writeValueAsString(object);
            System.out.println(objectJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return objectJson;
    }


}

