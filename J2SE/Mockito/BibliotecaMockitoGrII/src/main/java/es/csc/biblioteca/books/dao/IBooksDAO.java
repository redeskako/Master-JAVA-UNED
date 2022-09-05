package es.csc.biblioteca.books.dao;

import java.util.List;

/**
 *
 * @author David Atencia
 * @version 0.1
 */
public interface IBooksDAO {

    /**
     * Inserta el libro indicado en la base de datos.
     * 
     * @param dto Datos del libro.
     * @return Devuelve la clave primaria asociada al libro.
     * @throws BooksDAOException 
     */
    public int insert(BookDTO dto) throws BooksDAOException;

    /**
     * Actualiza el libro indicado por su clave primaria en la base de datos
     * con los datos especificados.
     * 
     * @param idLibro Clave primaria del libro a actualizar.
     * @param dto Datos del libro.
     * @throws BooksDAOException 
     */
    public void update(int idLibro, BookDTO dto) throws BooksDAOException;

    /**
     * Elimina de la base de datos el libro indicado por su clave primaria.
     * 
     * @param idLibro Clave primaria del libro a eliminar.
     * @throws BooksDAOException 
     */
    public void delete(int idLibro) throws BooksDAOException;

    /**
     * Recupera de la base de datos el libro indicado por su clave primaria.
     * 
     * @param idLibro Clave primaria del libro.
     * @return Devuelve los datos del libro encontrado.
     * @throws BooksDAOException 
     */
    public BookDTO findByPrimaryKey(int idLibro) throws BooksDAOException;

    /**
     * Recupera de la base de datos todos los libros.
     * 
     * @return Devuelve una lista todos los libros de la base de datos.
     * @throws BooksDAOException 
     */
    public List<BookDTO> findAll() throws BooksDAOException;

}
