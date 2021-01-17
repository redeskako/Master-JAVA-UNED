package pro.jbelo.persistence;

import pro.jbelo.utils.ServerRunningException;

import java.util.ArrayList;

/**
 * @author jbelo
 * @date 2017 05.
 * Interface para la implemmentación de un Data Access Object. Por falta de tiempo no se ha implementado
 */
public interface DAO {

    void requestCountries(String action, int currentOffset, EntitiesContainer countries) throws ServerRunningException;

    void requestIndicators(String action, int currentOffset, ArrayList<HealthIndicatorEntity> indicators) throws ServerRunningException;

    void requestYears(ArrayList<YearEntity> years) throws ServerRunningException;

    void requestStatistics(String action, String countryCode, String indicatorCode, int currentOffset, ArrayList<DataEntity> dataEntities) throws ServerRunningException;


}
