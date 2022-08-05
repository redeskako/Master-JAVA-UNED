
/**
 * Gestión básica de una biblioteca.
 * @author Antonio Dorado
 * @version 1.1 - 12/11/2015
 */

package com.csc.biblioteca.conexion;

import com.csc.biblioteca.excepciones.ErrorCode;
import com.csc.biblioteca.excepciones.ErrorException;
import com.csc.biblioteca.excepciones.InfoException;
import java.sql.*;

/**
 * Clase que gestiona la conexión a la base de datos de la aplicación.
 * Por ahora, sólo contempla el motor de base de datos MySQL.
 */
public class ConexionBD {
    public enum DBMS {MYSQL, ORACLE}
    
    private Connection conexion = null;
    
    /**
     * Método para conectar a la base de datos.
     * @param tipo Tipo de conexión (DBMS: MYSQL / ORACLE).
     * @param servername Nombre del servidor de base de datos.
     * @param database Nombre de la base de datos.
     * @param user Nombre del usuario de conexión.
     * @param password Contraseña del usuario de conexión.
     * @throws InfoException Tipo de DBMS no soportado.
     * @throws ErrorException Error en los parámetros de conexión.
     */
    public void conectar(DBMS tipo, String servername, String database, String user, String password) throws InfoException, ErrorException {
        String driver;
        String cadena;
        String parametros[];
        
        if (servername == null || servername.isEmpty()) throw new ErrorException(ErrorCode.ERROR_PARAMETROS, "Servidor no válido.");
        if (database == null || database.isEmpty()) throw new ErrorException(ErrorCode.ERROR_PARAMETROS, "Base de datos no válida.");
        if (user == null || user.isEmpty()) throw new ErrorException(ErrorCode.ERROR_PARAMETROS, "Nombre de usuario no válido.");
        if (password == null || password.isEmpty()) throw new ErrorException(ErrorCode.ERROR_PARAMETROS, "Contraseña no válida.");
        
        // Registro del Driver de conexión a la BD
        switch (tipo) {
            case MYSQL:
                driver = MySQLConnection.DRIVER_MYSQL;
                cadena = "jdbc:mysql";
                parametros = new String[] {MySQLConnection.MYSQL_DATE_NULL};
                break;

            default:
                throw new InfoException(ErrorCode.ERROR_DBMS_NOVALIDO, tipo.toString());
        }
        
        try {
            Class.forName(driver).newInstance();
            
            cadena += "://"+servername+"/"+database+"?user="+user+"&password="+password;
            if (parametros.length > 0) {
                for (String param : parametros) {
                    cadena += "&"+param;
                }
            }
            conexion = DriverManager.getConnection(cadena);
            
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            throw new ErrorException(ErrorCode.ERROR_DRIVER_NOVALIDO, ex.getMessage());
        } catch (Exception ex) {
            throw new ErrorException(ErrorCode.ERROR_CONEXION_BD, ex.getMessage());
        }
    }
    
    /**
     * Desconecta la conexión establecida, previamente, con la base de datos.
     * @throws ErrorException Error general durante la desconexión.
     */
    public void desconectar() throws ErrorException {
        if (conexion != null) {
            try {
                conexion.close();
            } catch (Exception ex) {
                throw new ErrorException(ErrorCode.ERROR_INDETERMINADO, ex.getMessage());
            }
        }
    }

    /**
     * Obtener la conexión establecida a la base de datos.
     * @return Conexión a la base de datos.
     */
    public Connection getConexion() {
        return conexion;
    }
}
