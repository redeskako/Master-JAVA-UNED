/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.csc.biblioteca.loans.dao;

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
public class LoansDAOImplOracle extends LoansDAOImpl{
    public LoansDAOImplOracle() {
    }
    
    public LoansDAOImplOracle(Connection connection) {
        this.iConnection = connection;
    }

 @Override
    public int insert(LoanDTO dto) throws LoansDAOException {
        
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        final boolean isConnSupplied = (iConnection != null);
        
        try {
            // Obtenemos una conexión a la base de datos
            connection = isConnSupplied ? iConnection : ConnectionsManager.getConnection();
            // Obtenemos la secuencia            
            dto.setIdPrestamo(UtilsOracle.generaSecuencia(connection, "SEQ_PRESTAMO"));
            // Preparamos la sentencia INSERT
            stmt = getInsertStatement(connection, dto);
            // Ejecutamos la sentencia SQL
            stmt.executeUpdate();

            reset(dto);
        } catch (SQLException ex) {
            throw new LoansDAOException(ex.toString());
        } finally {
            ConnectionsManager.close(rs);
            ConnectionsManager.close(stmt);
            if (!isConnSupplied) {
                ConnectionsManager.close(connection);
            }
        }
        
        return dto.getIdPrestamo();
        
    }    
    
  protected PreparedStatement getInsertStatement(Connection conn, LoanDTO dto) throws IllegalStateException, SQLException {
        int modifiedCount = 0;
        StringBuilder sql = new StringBuilder();
        StringBuilder values = new StringBuilder();
        PreparedStatement stmt = null;
        
        String sqlIdentifier = "select SEQ_PRESTAMO.NEXTVAL from dual";
        PreparedStatement pst = conn.prepareStatement(sqlIdentifier);
        
        ResultSet rs = pst.executeQuery();
            if(rs.next())
                dto.setIdPrestamo(rs.getInt(1));            
        
        // Construimos la cadena con la sentencia INSERT
        sql.append( "INSERT INTO " + getTableName() + " (" );
        if (dto.isIdPrestamoModified()) {
            if (modifiedCount > 0) {
                sql.append(", ");
                values.append(", ");
            }
            sql.append("idprestamo");
            values.append("?");
            modifiedCount++;
        }
        if (dto.isIdLibroModified()) {
            if (modifiedCount > 0) {
                sql.append(", ");
                values.append(", ");
            }
            sql.append("idlibro");
            values.append("?");
            modifiedCount++;
        }
        if (dto.isIdSocioModified()) {
            if (modifiedCount > 0) {
                sql.append(", ");
                values.append(", ");
            }
            sql.append("idsocio");
            values.append("?");
            modifiedCount++;
        }
        if (dto.isFechaInicioModified()) {
            if (modifiedCount > 0) {
                sql.append(", ");
                values.append(", ");
            }
            sql.append("fechainicio");
            values.append("?");
            modifiedCount++;
        }
        if (dto.isFechaFinModified()) {
            if (modifiedCount > 0) {
                sql.append(", ");
                values.append(", ");
            }
            sql.append("fechafin");
            values.append("?");
            modifiedCount++;
        }
        if (modifiedCount == 0) {
            // Nada que insertar
            throw new IllegalStateException("Nada que insertar");
        }
        sql.append(") VALUES (");
        sql.append(values);
        sql.append(")");
        // Construimos el objeto PreparedStatement
        int index = 1;
        stmt = conn.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
        if (dto.isIdPrestamoModified()) {
            stmt.setInt(index++, dto.getIdPrestamo());
        }
        if (dto.isIdLibroModified()) {
            stmt.setInt(index++, dto.getBook().getIdLibro());
        }
        if (dto.isIdSocioModified()) {
            stmt.setInt(index++, dto.getCustomer().getIdSocio());
        }
        if (dto.isFechaInicioModified()) {
            stmt.setDate(index++, dto.getFechaInicio());
        }
        if (dto.isFechaFinModified()) {
            stmt.setDate(index++, dto.getFechaFin());
        }
        // Devolvemos el objeto PreparedStatement
        return stmt;
    }    

  
}
