package es.csc.biblioteca.loans.dao;

import es.csc.biblioteca.exceptions.DAOException;

/**
 *
 * @author David Atencia
 * @version 0.1
 */
public class LoansDAOException extends DAOException {

    public LoansDAOException() {
    }

    public LoansDAOException(String message) {
        super(message);
    }

    public LoansDAOException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
