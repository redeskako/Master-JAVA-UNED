/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.csc.biblioteca.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author alber
 */
public class UtilsOracle {
        public static int generaSecuencia(Connection conn, String secuencia) throws SQLException {
        String sentencia = "select "+ secuencia + ".NEXTVAL from dual";
        PreparedStatement pst = conn.prepareStatement(sentencia);
        ResultSet rs = pst.executeQuery();
            
        if(rs.next())
            return rs.getInt(1);
        return 0;
    }
    
    
}
