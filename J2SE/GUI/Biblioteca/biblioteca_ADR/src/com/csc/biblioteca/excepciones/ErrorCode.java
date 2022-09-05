/**
 * Gestión básica de una biblioteca.
 * @author Antonio Dorado
 * @version 1.0 - 03/12/2015
 */

package com.csc.biblioteca.excepciones;

/**
 * Listado de códigos de error de la aplicación.
 */
public final class ErrorCode {
    public final static int ERROR_INDETERMINADO         = -1;
    
    public final static int ERROR_PARAMETROS            = -11;
    
    public final static int ERROR_CARGAR_SOCIOS         = -101;
    public final static int ERROR_CARGAR_SOCIO          = -102;
    public final static int ERROR_ELIMINAR_SOCIO        = -103;
    public final static int ERROR_ACTUALIZAR_SOCIO      = -104;
    public final static int ERROR_INSERTAR_SOCIO        = -105;
    
    public final static int ERROR_CARGAR_LIBROS         = -111;
    public final static int ERROR_CARGAR_LIBRO          = -112;
    public final static int ERROR_ELIMINAR_LIBRO        = -113;
    public final static int ERROR_ACTUALIZAR_LIBRO      = -114;
    public final static int ERROR_INSERTAR_LIBRO        = -115;

    public final static int ERROR_CARGAR_PRESTAMOS      = -111;
    public final static int ERROR_CARGAR_PRESTAMO       = -112;
    public final static int ERROR_ELIMINAR_PRESTAMO     = -113;
    public final static int ERROR_ACTUALIZAR_PRESTAMO   = -114;
    public final static int ERROR_INSERTAR_PRESTAMO     = -115;

    public final static int ERROR_DBMS_NOVALIDO         = -1001;
    public final static int ERROR_DRIVER_NOVALIDO       = -1002;
    public final static int ERROR_CONEXION_BD           = -1003;
    public final static int ERROR_FK_REFERENCE          = -1004;
    public final static int ERROR_UNIQUE_KEY            = -1005;
    
    private ErrorCode() {}
}
