package es.csc.biblioteca.books.dao;

import es.csc.biblioteca.exceptions.DAOException;

/**
 *
 * @author David Atencia
 * @version 0.1
 */
public class BooksDAOException extends DAOException {

    public BooksDAOException() {
    }

    public BooksDAOException(String message) {
        super(message);
    }

    public BooksDAOException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
