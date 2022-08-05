/**
 * Gestión básica de una biblioteca.
 * @author Antonio Dorado
 * @version 1.0 - 12/12/2015
 */

package com.csc.biblioteca.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** Clase base de la que deben heredar todas las clases DAO de la aplicación. */
public class DAOBase {
    protected Connection conexion;

    /**
     * Obtener la estructura PreparedStatement de la sentencia SQL indicada.
     * @param sql Sentencia SQL a procesar.
     * @return Estructura con la sentencia SQL procesada.
     * @throws SQLException Error de SQL.
     */
    protected PreparedStatement obtenerStatement(String sql) throws SQLException {
        return this.conexion.prepareStatement(sql);
    }
    
    /**
     * Obtener la estructura PreparedStatement de la sentencia SQL y opciones indicadas.
     * @param sql Sentencia SQL a procesar.
     * @param opcion Opciones a utilizar en el procesado.
     * @return Estructura con la sentencia SQL procesada.
     * @throws SQLException Error de SQL.
     */
    protected PreparedStatement obtenerStatement(String sql, int opcion) throws SQLException {
        return this.conexion.prepareStatement(sql, opcion);
    }
    
    /**
     * Método para cerrar una estructura con una sentencia SQL procesada.
     * @param stmt Estructura con la sentencia SQL procesada que hay que cerrar.
     */
    protected void cerrarStatement(PreparedStatement stmt) {
        try {
            stmt.close();
        } catch (SQLException ex) {}
    }

    /**
     * Ejecutar la sentencia SQL que contiene la estructura indicada.
     * @param stmt Estructura con la sentencia SQL procesada que hay que ejecutar.
     * @return Estructura de resultados de la consulta a base de datos.
     * @throws SQLException Error de SQL.
     */
    protected ResultSet obtenerResultSet(PreparedStatement stmt) throws SQLException {
        return stmt.executeQuery();
    }
    
    /**
     * Método para cerrar una estructura de resultados.
     * @param rst Estructura de resultados que hay que cerrar.
     */
    protected void cerrarResultSet(ResultSet rst) {
        try {
            rst.close();
        } catch (SQLException ex) {}
    }
}
