package es.csc.biblioteca.books.dao;

import es.csc.biblioteca.jdbc.ConnectionsManager;
import es.csc.biblioteca.jdbc.UtilsOracle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author David Atencia
 * @version 0.1
 */
public class BooksDAOImplOracle extends BooksDAOImpl {
    

    
    public BooksDAOImplOracle() {
    }

    public BooksDAOImplOracle(Connection connection) {
        this.iConnection = connection;
    }
    
    @Override
    public int insert(BookDTO dto) throws BooksDAOException {
        
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        final boolean isConnSupplied = (iConnection != null);
        
        try {
            // Obtenemos una conexión a la base de datos
            connection = isConnSupplied ? iConnection : ConnectionsManager.getConnection();
            // Obtenemos la secuencia
            dto.setIdLibro(UtilsOracle.generaSecuencia(connection, "SEQ_LIBRO"));

            // Preparamos la sentencia INSERT
            stmt = getInsertStatement(connection, dto);
            // Ejecutamos la sentencia SQL
            stmt.executeUpdate();
            
            reset(dto);
        } catch (SQLException ex) {
            throw new BooksDAOException(ex.toString());
        } finally {
            ConnectionsManager.close(rs);
            ConnectionsManager.close(stmt);
            if (!isConnSupplied) {
                ConnectionsManager.close(connection);
            }
        }
        
        return dto.getIdLibro();
        
    }   
        
}

