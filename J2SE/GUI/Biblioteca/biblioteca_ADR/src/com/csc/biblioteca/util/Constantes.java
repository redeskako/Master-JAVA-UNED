/**
 * Gestión básica de una biblioteca.
 * @author Antonio Dorado
 * @version 1.1 - 07/01/2016
 */
package com.csc.biblioteca.util;

/** Clase de constantes de la aplicación (opciones de menú, acciones, etc). */
public class Constantes {
    /** Versión de la aplicación. */
    public final static String VERSION = "v1.0";
    /** Formato de fecha que se utiliza en la aplicación. */
    public final static String FORMATO_FECHA = "dd-MM-yyyy";
    /** Nombre del fichero de configuración. */
    public final static String CONFIG_FILE = "Biblioteca.properties";
    /** Ruta del directorio en donde se ubican los ficheros de los informes (.jasper). */
    public final static String REPORTS_PATH = "./reports";
    /** Ruta de localización de los recursos de idiomas de la aplicación. */
    public final static String BUNDLE_NAME = "com.csc.biblioteca.i18n.idioma";
    
    /* ActionComands de las opciones de menú */
    public final static String MENU_ACERCADE = "ACERCADE";
    public final static String MENU_SOCIOS = "SOCIOS";
    public final static String MENU_LIBROS = "LIBROS";
    public final static String MENU_PRESTAMOS = "PRESTAMOS";
    public final static String MENU_PRESTAMOS_SOCIO = "INFORMEPRESTAMOSSOCIO";
    public final static String MENU_PRESTAMOS_LIBRO = "INFORMEPRESTAMOSLIBRO";
    public final static String MENU_SALIR = "SALIR";
    public final static String MENU_SPANISH = "SPANISH";
    public final static String MENU_ENGLISH = "ENGLISH";
    
    /* ActionCommands de las ventanas de mantenimiento */
    public final static String INSERTAR = "INSERTAR";
    public final static String EDITAR = "EDITAR";
    public final static String BORRAR = "BORRAR";
    public final static String IMPRIMIR = "IMPRIMIR";
    public final static String ACEPTAR = "ACEPTAR";
    public final static String CANCELAR = "CANCELAR";
}
