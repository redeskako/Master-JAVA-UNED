package com.uned.springtube.configuration;

import java.util.Properties;
 
import javax.sql.DataSource;
 
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Configuraci�n de hibernate.
 * Crea un objeto dataSource y un properties (hibernateProperties) a partir del fichero resources/application.properties.
 * Con �stos y el modelo crea un objeto sessionFactory, el cual es asignado a un objeto transactionManager. 
 */

@Configuration // Indica que se trata de una clase de configuraci�n que va a definir managed beans.
@EnableTransactionManagement // Habilita el manejo de transacciones.
@ComponentScan({ "com.uned.springtube.configuration" }) // Paquete ra�z donde buscar los managed beans.
// Fichero del cual se obtienen las propiedades jdbc e hibernate.
@PropertySource(value = { "classpath:application.properties" })
public class HibernateConfiguration
{
    @Autowired // Resuelve mediante inyecci�n las dependencias del bean de Spring.
    private Environment environment; // Objeto que encapsula las propiedades jdbc e hibernate.

    @Bean // Managed bean.
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory s)
    {
       HibernateTransactionManager txManager = new HibernateTransactionManager();
       txManager.setSessionFactory(s); // Definido en este mismo fichero.
       return txManager;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory()
    {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource()); // definido en este mismo fichero.
        sessionFactory.setPackagesToScan(new String[] { "com.uned.springtube.model" });
        sessionFactory.setHibernateProperties(hibernateProperties()); // definido en este mismo fichero.
        return sessionFactory;
    }

    @Bean
    public DataSource dataSource()
    {
    	// Las propiedades se obtienen del fichero application.properties.
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
        return dataSource;
    }
    
    private Properties hibernateProperties()
    {
    	// Las propiedades se obtienen del fichero application.properties.
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        /*properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));*/
        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
        return properties;        
    }
}