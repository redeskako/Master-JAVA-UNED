package es.csc.biblioteca.customers.dao;

import es.csc.biblioteca.exceptions.DAOException;

/**
 *
 * @author David Atencia
 * @version 0.1
 */
public class CustomersDAOException extends DAOException {

    public CustomersDAOException() {
    }

    public CustomersDAOException(String message) {
        super(message);
    }

    public CustomersDAOException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
