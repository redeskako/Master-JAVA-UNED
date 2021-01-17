package es.csc.biblioteca.customers.dao;

import java.util.List;

/**
 *
 * @author David Atencia
 * @version 0.1
 */
public interface ICustomersDAO {
    
    /**
     * Inserta el socio indicado en la base de datos.
     * 
     * @param dto Datos del socio.
     * @return Devuelve la clave primaria asociada al socio.
     * @throws CustomersDAOException 
     */
    public int insert(CustomerDTO dto) throws CustomersDAOException;

    /**
     * Actualiza el socio indicado por su clave primaria en la base de datos
     * con los datos especificados.
     * 
     * @param idSocio Identificador único del socio a actualizar.
     * @param dto Datos del socio.
     * @throws CustomersDAOException 
     */
    public void update(int idSocio, CustomerDTO dto) throws CustomersDAOException;
    
    /**
     * Elimina de la base de datos el socio indicado por su clave primaria.
     * 
     * @param idSocio Identificador único del socio.
     * @throws CustomersDAOException 
     */
    public void delete(int idSocio) throws CustomersDAOException;
    
    /**
     * Recupera de la base de datos el socio indicado por su clave primaria.
     * 
     * @param idSocio Identificador único del socio.
     * @return Devuelve los datos del socio encontrado.
     * @throws CustomersDAOException 
     */
    public CustomerDTO findByPrimaryKey(int idSocio) throws CustomersDAOException;
    
    /**
     * Recupera de la base de datos todos los socios.
     * 
     * @return Devuelve un array con cada uno de los socios de la base de datos.
     * @throws CustomersDAOException 
     */
    public List<CustomerDTO> findAll() throws CustomersDAOException;
    
}
