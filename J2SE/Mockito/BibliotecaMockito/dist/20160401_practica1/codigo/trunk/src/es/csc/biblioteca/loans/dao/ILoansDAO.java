package es.csc.biblioteca.loans.dao;

import java.util.List;

/**
 *
 * @author David Atencia
 * @version 0.1
 */
public interface ILoansDAO {

    /**
     * Inserta el prestamo indicado en la base de datos.
     * 
     * @param dto Datos del prestamo.
     * @return Devuelve la clave primaria asociada al prestamo.
     * @throws LoansDAOException 
     */
    public int insert(LoanDTO dto) throws LoansDAOException;

    /**
     * Actualiza el prestamo indicado por su clave primaria en la base de datos
     * con los datos especificados.
     * 
     * @param idPrestamo Clave primaria del prestamo a actualizar.
     * @param dto Datos del prestamo.
     * @throws LoansDAOException 
     */
    public void update(int idPrestamo, LoanDTO dto) throws LoansDAOException;

    /**
     * Elimina de la base de datos el prestamo indicado por su clave primaria.
     * 
     * @param idPrestamo Clave primaria del prestamo.
     * @throws LoansDAOException 
     */
    public void delete(int idPrestamo) throws LoansDAOException;

    /**
     * Recupera de la base de datos el prestamo indicado por su clave primaria.
     * 
     * @param idPrestamo Clave primaria del prestamo.
     * @return Devuelve los datos del prestamo encontrado.
     * @throws LoansDAOException 
     */
    public LoanDTO findByPrimaryKey(int idPrestamo) throws LoansDAOException;

    /**
     * Recupera de la base de datos todos los prestamos.
     * 
     * @return Devuelve una lista con todos los prestamos de la base de datos.
     * @throws LoansDAOException 
     */
    public List<LoanDTO> findAll() throws LoansDAOException;

}
