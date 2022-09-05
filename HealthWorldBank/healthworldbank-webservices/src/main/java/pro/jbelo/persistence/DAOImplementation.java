package pro.jbelo.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import pro.jbelo.model.DataBean;
import pro.jbelo.utils.ServerRunningException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.criteria.*;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jbelo
 * @date 2017 06.
 */
@Named("dao")
@ApplicationScoped
public class DAOImplementation implements Serializable, DAO {


    public DAOImplementation() {

    }

    @Override
    public void createCountry(CountryEntity countryEntity) throws ServerRunningException{
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            // TODO: review this code, move create update to a single method
            //session.save(countryEntity);
            session.saveOrUpdate(countryEntity);
            transaction.commit();
        } catch (Exception e){
            if(transaction != null) transaction.rollback();
            throw new ServerRunningException(e.toString());
        } finally {
            session.close();
        }

    }


    @Override
    public CountryEntity readCountry(String countryId) throws ServerRunningException {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        CountryEntity countryEntity = session.get(CountryEntity.class,countryId);

        return countryEntity;
    }

    @Override
    public List<CountryEntity> readCountries() throws ServerRunningException {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<CountryEntity> criteriaQuery = criteriaBuilder.createQuery(CountryEntity.class);
        Root<CountryEntity> root = criteriaQuery.from(CountryEntity.class);
        criteriaQuery.select(root);

        // TODO: comprobar si se retorna algún valor, en caso de n encontrar ningún valor se devuelve null
        List<CountryEntity> countriesList = session.
                createQuery(criteriaQuery).getResultList();


        session.close();

        return countriesList;
    }

    @Override
    public List<CountryEntity> readCountries(int recordsPage, int offset) throws ServerRunningException {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<CountryEntity> criteriaQuery = criteriaBuilder.createQuery(CountryEntity.class);
        Root<CountryEntity> root = criteriaQuery.from(CountryEntity.class);
        criteriaQuery.select(root);

        // TODO: comprobar si se retorna algún valor, en caso de n encontrar ningún valor se devuelve null
        List<CountryEntity> countriesList = session.
                createQuery(criteriaQuery).
                setFirstResult(offset).
                setMaxResults(recordsPage).getResultList();

        session.close();

        return countriesList;
    }

    @Override
    public void updateCountry(CountryEntity countryEntity) throws ServerRunningException {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            //session.update(countryEntity);
            // TODO: review como funciona esto
            session.saveOrUpdate(countryEntity);

            transaction.commit();
        } catch (Exception e){
            if(transaction != null) transaction.rollback();
            throw new ServerRunningException(e.toString());
        } finally {
            session.close();
        }
    }

    @Override
    public List<CountryEntity> deleteCountry(String countryCode) throws ServerRunningException {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            CountryEntity countryEntity = session.get(CountryEntity.class,countryCode);
            session.delete(countryEntity);

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<CountryEntity> criteriaQuery = criteriaBuilder.createQuery(CountryEntity.class);
            Root<CountryEntity> root = criteriaQuery.from(CountryEntity.class);
            criteriaQuery.select(root);

            // TODO: comprobar si se retorna algún valor, en caso de n encontrar ningún valor se devuelve null
            List<CountryEntity> countriesList = session.
                    createQuery(criteriaQuery).getResultList();

            transaction.commit();
            return countriesList;
        } catch (Exception e) {
            if(transaction != null) transaction.rollback();
            throw new ServerRunningException(e.toString());
        } finally {
            session.close();
        }
    }


    @Override
    public void createHealthIndicator(HealthIndicatorEntity healthIndicatorEntity) throws ServerRunningException{
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(healthIndicatorEntity);
            transaction.commit();
        } catch (Exception e){
            if(transaction != null) transaction.rollback();
            throw new ServerRunningException(e.toString());
        } finally {
            session.close();
        }

    }

    @Override
    public HealthIndicatorEntity readHealthIndicator(String indicatorId) throws ServerRunningException {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        HealthIndicatorEntity healthIndicatorEntity = session.get(HealthIndicatorEntity.class,indicatorId);

        return healthIndicatorEntity;
    }

    @Override
    public List<HealthIndicatorEntity> readHealthIndicators() throws ServerRunningException {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<HealthIndicatorEntity> criteriaQuery = criteriaBuilder.createQuery(HealthIndicatorEntity.class);
        Root<HealthIndicatorEntity> root = criteriaQuery.from(HealthIndicatorEntity.class);

        criteriaQuery.select(root);
        criteriaQuery.orderBy(criteriaBuilder.asc(root.get("indicatorName")));

        List<HealthIndicatorEntity> healthIndicatorList = session.
                createQuery(criteriaQuery).getResultList();

        session.close();

        return healthIndicatorList;
    }

    @Override
    public List<HealthIndicatorEntity> readHealthIndicators(int recordsPage, int offset) throws ServerRunningException {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<HealthIndicatorEntity> criteriaQuery = criteriaBuilder.createQuery(HealthIndicatorEntity.class);
        Root<HealthIndicatorEntity> root = criteriaQuery.from(HealthIndicatorEntity.class);

        criteriaQuery.select(root);
        criteriaQuery.orderBy(criteriaBuilder.asc(root.get("indicatorName")));

        List<HealthIndicatorEntity> healthIndicatorList = session.
                createQuery(criteriaQuery).
                setMaxResults(recordsPage).
                setFirstResult(offset).
                getResultList();

        session.close();

        return healthIndicatorList;
    }

    @Override
    public void updateHealthIndicator(HealthIndicatorEntity healthIndicatorEntity) throws ServerRunningException {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.update(healthIndicatorEntity);
            transaction.commit();
        } catch (Exception e){
            if(transaction != null) transaction.rollback();
            throw new ServerRunningException(e.toString());
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteHealthIndicator(String indicatorCode) throws ServerRunningException {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            HealthIndicatorEntity healthIndicatorEntity = session.get(HealthIndicatorEntity.class,indicatorCode);
            session.delete(healthIndicatorEntity);
            transaction.commit();
        } catch (Exception e) {
            if(transaction != null) transaction.rollback();
            throw new ServerRunningException(e.toString());
        } finally {
            session.close();
        }
    }

    @Override
    public void createData(DataEntity dataEntity) throws ServerRunningException{
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(dataEntity);
            transaction.commit();
        } catch (Exception e){
            if(transaction != null) transaction.rollback();
            throw new ServerRunningException(e.toString());
        } finally {
            session.close();
        }

    }

    @Override
    public List<DataEntity> readData() throws ServerRunningException {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<DataEntity> criteriaQuery = criteriaBuilder.createQuery(DataEntity.class);
        Root<DataEntity> root = criteriaQuery.from(DataEntity.class);
        criteriaQuery.select(root);

        // TODO: comprobar si se retorna algún valor, en caso de n encontrar ningún valor se devuelve null
        List<DataEntity> countriesList = session.
                createQuery(criteriaQuery).getResultList();


        session.close();

        return countriesList;
    }

    @Override
    public DataEntity readData(String indicatorCode, String countryCode, int yearParam) throws ServerRunningException{
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<DataEntity> criteriaQuery = criteriaBuilder.createQuery(DataEntity.class);
        Root<DataEntity> root = criteriaQuery.from(DataEntity.class);
        criteriaQuery.select(root);

        criteriaQuery.where(
                criteriaBuilder.and(
                        criteriaBuilder.equal(root.get("dataId").get("indicatorCode"),indicatorCode),
                        criteriaBuilder.equal(root.get("dataId").get("countryCode"),countryCode),
                        criteriaBuilder.equal(root.get("dataId").get("year"),yearParam)
                )
        );

        DataEntity dataEntity = session.createQuery(criteriaQuery).uniqueResult();
        return dataEntity;
    }


    @Override
    public List<DataEntity> readData(String countryCode,
                                     String indicatorCode,
                                     int offset,
                                     int recordsPage) throws ServerRunningException {

        StringBuilder sql = new StringBuilder("SELECT INDICATOR_CODE, COUNTRY_CODE, YEAR, PERCENTAGE FROM DATA WHERE INDICATOR_CODE = '");
        sql.append(indicatorCode);
        sql.append("' AND COUNTRY_CODE = '");
        sql.append(countryCode);
        sql.append("' ORDER BY YEAR LIMIT ");
        sql.append(recordsPage);
        sql.append(" OFFSET ");
        sql.append(offset);
        sql.append(";");

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        List<DataEntity> dataList = session.createNativeQuery(sql.toString(),DataEntity.class).getResultList();

        session.close();

        return dataList;
    }

    @Override
    public void updateData(DataEntity dataEntity) throws ServerRunningException {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.update(dataEntity);
            transaction.commit();
        } catch (Exception e){
            if(transaction != null) transaction.rollback();
            throw new ServerRunningException(e.toString());
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteData(String indicatorCode, String countryCode, int yearParam) throws ServerRunningException {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;


        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<DataEntity> criteriaQuery = criteriaBuilder.createQuery(DataEntity.class);
        Root<DataEntity> root = criteriaQuery.from(DataEntity.class);
        criteriaQuery.select(root);

        criteriaQuery.where(
                criteriaBuilder.and(
                        criteriaBuilder.equal(root.get("dataId").get("indicatorCode"),indicatorCode),
                        criteriaBuilder.equal(root.get("dataId").get("countryCode"),countryCode),
                        criteriaBuilder.equal(root.get("dataId").get("year"),yearParam)
                )
        );



        try {
            transaction = session.beginTransaction();
            //HealthIndicatorEntity healthIndicatorEntity = session.get(HealthIndicatorEntity.class,indicatorCode);
            DataEntity dataEntity = session.createQuery(criteriaQuery).uniqueResult();
            session.delete(dataEntity);
            transaction.commit();
        } catch (Exception e) {
            if(transaction != null) transaction.rollback();
            throw new ServerRunningException(e.toString());
        } finally {
            session.close();
        }
    }

    @Override
    public List<YearEntity> requestYears() throws ServerRunningException {

        StringBuilder sql = new StringBuilder("SELECT DISTINCT YEAR FROM DATA ORDER BY YEAR ASC;");

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        List<Object> dataList = session.createNativeQuery(sql.toString()).getResultList();


        List<YearEntity> mList = new ArrayList<>();

        for(Object year:dataList) {
          System.out.println("" + year.toString());
          YearEntity reg = new YearEntity();
          reg.setYear((Integer)year);
          mList.add(reg);
        }

        session.close();

        return mList;

    }

    @Override
    public List<DataMapWrapper> requestGeoStatistics(String indicator, String year) throws ServerRunningException {

        StringBuilder sql = new StringBuilder("SELECT country.COUNTRY_NAME, data.PERCENTAGE " +
                "FROM COUNTRY INNER JOIN DATA ON country.COUNTRY_CODE = data.COUNTRY_CODE WHERE ");
        sql.append("data.INDICATOR_CODE = '");
        sql.append(indicator);
        sql.append("'  AND YEAR = '");
        sql.append(year);
        sql.append( "';");

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        List<Object[]> dataList = session.createNativeQuery(sql.toString()).getResultList();

        List<DataMapWrapper> mList = new ArrayList<>();

        for(Object[] object:dataList){
            DataMapWrapper reg = new DataMapWrapper();
            reg.setCountry((String)object[0]);
            reg.setPercentage((Double)object[1]);
            mList.add(reg);
        }

        session.close();

        return mList;

    }


    /**
     * Calcula el total de registros para una sola tabla
     * @param tables_name nombre de la tabla
     * @return total de registros
     * @throws SQLException
     */
    @Override
    public int calculateTotalRecordsSingleTable( DataBean.TABLES_NAME tables_name) throws ServerRunningException {

        StringBuilder sql = new StringBuilder("SELECT COUNT(*) as total_records FROM ");
        sql.append(tables_name.toString());
        sql.append(";");

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        Object data = session.createNativeQuery(sql.toString()).getSingleResult();

        session.close();

        return Integer.parseInt(data.toString());


    }

    /**
     * Calcula el total de registros para un indicador y un país dado
     * @param indicatorCode código de indicador
     * @param countryCode código de país
     * @return totalde registros para los valores dados
     * @throws ServerRunningException
     */
    public int calculateTotalStadistics(String indicatorCode, String countryCode) throws ServerRunningException {

        StringBuilder sql = new StringBuilder("SELECT COUNT(*) as totals FROM DATA WHERE INDICATOR_CODE = '");
        sql.append(indicatorCode);
        sql.append("' AND COUNTRY_CODE = '");
        sql.append(countryCode);
        sql.append("';");

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        Object data = session.createNativeQuery(sql.toString()).getSingleResult();

        session.close();

        return Integer.parseInt(data.toString());


    }


}
