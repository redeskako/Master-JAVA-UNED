/**
 * Gestión básica de una biblioteca.
 * @author Antonio Dorado
 * @version 1.0 - 08/01/2016
 */

package com.csc.biblioteca.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/** Clase para inicializar y cargar los parámetros de configuración de la aplicación. */
public class Configuracion {

    /** Propiedad que almacena los valores preconfigurados. */
    private static Properties properties;

    static {
        inicializar();
    }

    /**
     * Retorna el valor de la propiedad almacenada en el fichero de propiedades.
     * @param clave Nombre de la propiedad solicitada.
     * @return El valor de la propiedad.
     */
    public static String getProperty(String clave) {
        return (String)properties.getProperty(clave);
    }
    
    /** Carga de los datos del fichero de configuración. */
    private static void inicializar() {
        FileInputStream fis = null;

        try {
            properties = new Properties();
            fis = new FileInputStream("./"+Constantes.CONFIG_FILE);
            properties.load(fis);
        } catch (FileNotFoundException e) {
            Messages.mensaje(Messages.Tipo.ERROR, "MSG_ERROR_FICHERO_NO_ENCONTRADO", Constantes.CONFIG_FILE);
        } catch (Exception e) {
            Messages.mensaje(Messages.Tipo.ERROR, "MSG_ERROR_CARGAR_FICHERO", Constantes.CONFIG_FILE);
        } finally {
            try {
                if (fis != null) fis.close();
            } catch (IOException ex) {}
        }    
    }
}
