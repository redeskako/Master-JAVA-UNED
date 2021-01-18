/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.csc.biblioteca.customers.dao;

import es.csc.biblioteca.jdbc.ConnectionsManager;
import es.csc.biblioteca.jdbc.UtilsOracle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author alber
 */
public class CustomersDAOImplOracle extends CustomersDAOImpl {
    public CustomersDAOImplOracle() {
    }

    public CustomersDAOImplOracle(Connection connection) {
        this.iConnection = connection;
    }    

    /**
     * Inserta el socio indicado en la base de datos.
     * 
     * @param dto Datos del socio.
     * @return Devuelve la clave primaria asociada al socio.
     * @throws CustomersDAOException 
     */
    @Override
    public int insert(CustomerDTO dto) throws CustomersDAOException {

        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        final boolean isConnSupplied = (iConnection != null);
        
        try {
            // Obtenemos una conexión a la base de datos
            connection = isConnSupplied ? iConnection : ConnectionsManager.getConnection();
            
            // Obtenemos la secuencia            
            dto.setIdSocio(UtilsOracle.generaSecuencia(connection, "SEQ_SOCIO"));
            // Preparamos la sentencia INSERT
            stmt = getInsertStatement(connection, dto);
            // Ejecutamos la sentencia SQL
            stmt.executeUpdate();
           
            reset(dto);
        } catch (SQLException ex) {
            throw new CustomersDAOException(ex.toString());
        } finally {
            ConnectionsManager.close(rs);
            ConnectionsManager.close(stmt);
            if (!isConnSupplied) {
                ConnectionsManager.close(connection);
            }
        }
        
        return dto.getIdSocio();
        
    }    
   

  
}
