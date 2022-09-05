package com.uned.springtube.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Clase gen�rica base para todas las clases de implementaci�n DAO.
 * La clase EmployeeDaoImpl.java extiende de esta.
 * Puede omitirse si empleamos exclusivamente implementaciones DAO instanciando objetos Query.
 */

public abstract class AbstractDao<PK extends Serializable, T>
{     
    private final Class<T> persistentClass;

    @SuppressWarnings("unchecked")
    public AbstractDao()
    {
        this.persistentClass =(Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    // El objeto sessionFactory se crea en la clase HibernateConfiguration.java.
    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession()
    {
        return sessionFactory.getCurrentSession();
    }

    /**
     * Busca por clave.
     * @param key clave a buscar.
     * @return
     */
    @SuppressWarnings("unchecked")
    public T getByKey(PK key)
    {
        return (T) getSession().get(persistentClass, key);
    }

    /**
     * Alta/modificaci�n.
     * @param entity
     */
    public void persist(T entity)
    {
        getSession().persist(entity);
    }
 
    /**
     * Borrado.
     * @param entity
     */
    public void delete(T entity)
    {
        getSession().delete(entity);
    }
    
    /**
     * Criterios para las b�squedas.
     * @return
     */
    protected Criteria createEntityCriteria()
    {
        return getSession().createCriteria(persistentClass);
    }
    //Nuevo 
    @SuppressWarnings("unchecked")
    public List< T > findAll(){     	
    	return getSession().createQuery( "from " + persistentClass.getName()).list(); 
    	}
}