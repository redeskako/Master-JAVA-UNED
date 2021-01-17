package pro.jbelo.persistence;

import pro.jbelo.beans.DataBean;
import pro.jbelo.utils.ServerRunningException;

import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.sql.DataSource;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jbelo
 * @date 2017 06.
 */
@Named("dao")
@SessionScoped
public class DAOImplementation implements Serializable, DAO {

    @Resource(lookup = "jdbc/hwbDS")
    DataSource dataSource;

    public DAOImplementation() {

    }

    @Override
    public List<CountryEntity> requestCountries() throws ServerRunningException {
        ResultSet resultSet;

        try(Connection connection = dataSource.getConnection()) {
            Statement statement =  connection.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            StringBuilder sql =  new StringBuilder("SELECT * FROM COUNTRY;");

            resultSet = statement.executeQuery(sql.toString());
            List<CountryEntity> mList = makeCountryList(resultSet);
            return mList;

        } catch (SQLException e) {
            throw new ServerRunningException();
        }
    }

    @Override
    public List<CountryEntity> requestCountries(int recordsPage, int offset) throws ServerRunningException {
        ResultSet resultSet = null;

        try(Connection connection = dataSource.getConnection()){

            Statement statement =  connection.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            StringBuilder sql =  new StringBuilder("SELECT * FROM COUNTRY LIMIT ");
            sql.append(recordsPage);
            sql.append(" OFFSET ");
            sql.append(offset);
            sql.append(";");
            resultSet = statement.executeQuery(sql.toString());

            List<CountryEntity> mList = makeCountryList(resultSet);

            return mList;
        }catch (SQLException e){
            throw new ServerRunningException(e.getLocalizedMessage());
        }
    }

    private List<CountryEntity> makeCountryList(ResultSet resultSet) throws SQLException{

        List<CountryEntity> mList = new ArrayList<>();

        while(resultSet.next()){
            CountryEntity reg = new CountryEntity();
            reg.setCountryCode(resultSet.getString("COUNTRY_CODE"));
            reg.setCountryName(resultSet.getString("COUNTRY_NAME"));
            mList.add(reg);
        }
        return mList;
    }

    @Override
    public List<HealthIndicatorEntity> requestIndicators() throws ServerRunningException {
        ResultSet resultSet = null;

        try(Connection connection = dataSource.getConnection()){
            Statement statement =  connection.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            StringBuilder sql = new StringBuilder("SELECT INDICATOR_CODE, INDICATOR_NAME, SOURCE_NOTE, SOURCE_ORGANIZATION FROM HEALTH_INDICATOR ORDER BY INDICATOR_NAME;");

            resultSet = statement.executeQuery(sql.toString());

            List<HealthIndicatorEntity> mList = makeIndicatorsList(resultSet);

            return mList;
        }catch(SQLException ex){
            throw new ServerRunningException(ex.getMessage());
        }
    }

    @Override
    public List<HealthIndicatorEntity> requestIndicators(int recordsPage, int offset) throws ServerRunningException {
        ResultSet resultSet = null;

        try(Connection connection = dataSource.getConnection()){
            Statement statement =  connection.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            StringBuilder sql = new StringBuilder("SELECT INDICATOR_CODE, INDICATOR_NAME, SOURCE_NOTE, SOURCE_ORGANIZATION FROM HEALTH_INDICATOR ORDER BY INDICATOR_NAME LIMIT ");
            sql.append(recordsPage);
            sql.append(" OFFSET ");
            sql.append(offset);
            sql.append(";");

            resultSet = statement.executeQuery(sql.toString());

            List<HealthIndicatorEntity> mList = makeIndicatorsList(resultSet);

            return mList;
        }catch(SQLException ex){
            throw new ServerRunningException(ex.getMessage());
        }

    }

    private List<HealthIndicatorEntity> makeIndicatorsList (ResultSet resultSet) throws SQLException {

        List<HealthIndicatorEntity> mList = new ArrayList<>();

        while(resultSet.next()){
            HealthIndicatorEntity reg = new HealthIndicatorEntity();
            reg.setIndicatorCode(resultSet.getString("INDICATOR_CODE"));
            reg.setIndicatorName(resultSet.getString("INDICATOR_NAME"));
            reg.setSourceNote(resultSet.getString("SOURCE_NOTE"));
            reg.setSourceOrganization(resultSet.getString("SOURCE_ORGANIZATION"));
            mList.add(reg);
        }
        return mList;
    }

    @Override
    public List<DataEntity> requestStatistics(String countryCode,
                                              String indicatorCode,
                                              int offset,
                                              int recordsPage) throws ServerRunningException {
        ResultSet resultSet = null;

        try(Connection connection = dataSource.getConnection()){
            Statement statement =  connection.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            StringBuilder sql = new StringBuilder("SELECT INDICATOR_CODE, COUNTRY_CODE, YEAR, PERCENTAGE FROM DATA WHERE INDICATOR_CODE = '");
            sql.append(indicatorCode);
            sql.append("' AND COUNTRY_CODE = '");
            sql.append(countryCode);
            sql.append("' ORDER BY YEAR LIMIT ");
            sql.append(recordsPage);
            sql.append(" OFFSET ");
            sql.append(offset);
            sql.append(";");

            resultSet = statement.executeQuery(sql.toString());

            List<DataEntity> mList = new ArrayList<>();

            while(resultSet.next()){
                DataEntity reg = new DataEntity();
                reg.setIndicatorCode(resultSet.getString("INDICATOR_CODE"));
                reg.setCountryCode(resultSet.getString("COUNTRY_CODE"));
                reg.setYear(resultSet.getInt("YEAR"));
                reg.setPercentage(resultSet.getDouble("PERCENTAGE"));
                mList.add(reg);
            }

            return mList;
        } catch(SQLException ex) {
            throw new ServerRunningException(ex.getMessage());
        }
    }

    @Override
    public List<YearEntity> requestYears() throws ServerRunningException {
        ResultSet resultSet = null;

        try(Connection connection = dataSource.getConnection()){

            Statement statement =  connection.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            StringBuilder sql = new StringBuilder("SELECT DISTINCT YEAR FROM DATA ORDER BY YEAR ASC;");

            resultSet = statement.executeQuery(sql.toString());

            List<YearEntity> mList = new ArrayList<>();

            while(resultSet.next()){
                YearEntity reg = new YearEntity();
                reg.setYear(resultSet.getInt("YEAR"));
                mList.add(reg);
            }

            return mList;

        }catch(SQLException ex){
            throw new ServerRunningException(ex.getMessage());
        }

    }

    @Override
    public List<DataMapEntity> requestGeoStatistics(String indicator, String year) throws ServerRunningException {
        ResultSet resultSet = null;

        try (Connection connection = dataSource.getConnection()){

            Statement statement =  connection.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            StringBuilder sql = new StringBuilder("SELECT country.COUNTRY_NAME, data.PERCENTAGE " +
                    "FROM COUNTRY INNER JOIN DATA ON country.COUNTRY_CODE = data.COUNTRY_CODE WHERE ");
            sql.append("data.INDICATOR_CODE = '");
            sql.append(indicator);
            sql.append("'  AND YEAR = '");
            sql.append(year);
            sql.append( "';");

            resultSet = statement.executeQuery(sql.toString());

            List<DataMapEntity> mList = new ArrayList<>();

            while(resultSet.next()){
                DataMapEntity reg = new DataMapEntity();
                reg.setCountry(resultSet.getString("COUNTRY_NAME"));
                reg.setPercentage(resultSet.getDouble("PERCENTAGE"));
                mList.add(reg);
            }

            return mList;

        }catch(SQLException ex){
            throw new ServerRunningException(ex.getMessage());
        }

    }


    /**
     * Calcula el total de registros para una sola tabla
     * @param tables_name nombre de la tabla
     * @return total de registros
     * @throws SQLException
     */
    @Override
    public int calculateTotalRecordsSingleTable( DataBean.TABLES_NAME tables_name) throws ServerRunningException {
        ResultSet resultSet = null;

        try(Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            StringBuilder sql = new StringBuilder("SELECT COUNT(*) as total_records FROM ");
            sql.append(tables_name.toString());
            sql.append(";");
            resultSet = statement.executeQuery(sql.toString());
            resultSet.next();
            return resultSet.getInt("total_records");
        } catch (SQLException e) {
            throw new ServerRunningException();
        }

    }

    /**
     * Calcula el total de registros para un indicador y un país dado
     * @param indicatorCode código de indicador
     * @param countryCode código de país
     * @return totalde registros para los valores dados
     * @throws ServerRunningException
     */
    public int calculateTotalStadistics(String indicatorCode, String countryCode) throws ServerRunningException {
        ResultSet resultSet = null;
        Statement statement = null;

        try(Connection connection = dataSource.getConnection()) {
            statement = connection.createStatement();
            StringBuilder sql = new StringBuilder("SELECT COUNT(*) as totals FROM DATA WHERE INDICATOR_CODE = '");
            sql.append(indicatorCode);
            sql.append("' AND COUNTRY_CODE = '");
            sql.append(countryCode);
            sql.append("';");
            resultSet = statement.executeQuery(sql.toString());
            resultSet.next();
            return resultSet.getInt("totals");
        }catch (SQLException e) {
            throw new ServerRunningException();
        }
    }


}
