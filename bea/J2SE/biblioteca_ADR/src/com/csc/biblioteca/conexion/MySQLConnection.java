/**
 * Gestión básica de una biblioteca.
 * @author Antonio Dorado
 * @version 1.0 - 24/11/2015
 */
package com.csc.biblioteca.conexion;

/**
 * Constantes de la conexión al SGBD MySQL.
 */
public final class MySQLConnection {
    /** Error al intentar borrar un registro referenciado en otra tabla (FK). */
    public final static int MYSQL_ERROR_FK_REFERENCE = 1451;
    /** Error al intentar insertar un valor repetido en un campo con clave única. */
    public final static int MYSQL_ERROR_UNIQUE_KEY = 1062;
    
    /** Nombre del driver de conexión a MySQL. */
    public final static String DRIVER_MYSQL = "com.mysql.jdbc.Driver";
    /** Parámetro de conexión para considerar las fechas 00-00-0000 como valor nulo. */
    public final static String MYSQL_DATE_NULL = "zeroDateTimeBehavior=convertToNull";

    private MySQLConnection() {}
}
