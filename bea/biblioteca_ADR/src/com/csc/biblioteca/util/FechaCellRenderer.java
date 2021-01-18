/**
 * Gestión básica de una biblioteca.
 * @author Antonio Dorado
 * @version 1.0 - 12/12/2015
 */

package com.csc.biblioteca.util;

import java.sql.Date;
import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * Clase para formatear las fechas de las tablas a "dd-mm-yyyy".
 * Con el método: setDefaultRenderer(Date.class, new FechaRenderer());
 */
public class FechaCellRenderer extends DefaultTableCellRenderer {
    /** Constructor del CellRenderer. */
    public FechaCellRenderer() {super();}
    
    /**
     * Establecer el valor a mostrar en la celda.
     * @param value Valor a mostrar.
     */
    @Override
    public void setValue(Object value) {
        SimpleDateFormat formato = new SimpleDateFormat(Constantes.FORMATO_FECHA);
        setText((value == null) ? "" : formato.format((Date)value));
    }
}
