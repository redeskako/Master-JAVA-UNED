/**
 * Gestión básica de una biblioteca.
 * @author Antonio Dorado
 * @version 1.0 - 21/12/2015
 */

package com.csc.biblioteca.util;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/** Clase base de los modelos (MVC). */
public abstract class ModeloBase extends AbstractTableModel {
    protected String nombreColumnas[];
    protected int anchoColumnas[];
    protected Class tipoColumnas[];
    
    /**
     * Obtener el listado de elementos que contiene el modelo.
     * @return Listado de elementos.
     */
    public abstract List getListado();
    
    /**
     * Obtener el número de columnas que contiene el listado del modelo.
     * @return Número de columnas del listado.
     */
    @Override
    public int getColumnCount() {
        return this.nombreColumnas.length;
    }

    /**
     * Obtener el nombre de la columna del listado en la posición indicada.
     * Tiene en cuenta el idioma establecido.
     * @param columnIndex Índice de la columna a obtener su nombre.
     * @return Nombre de la columna.
     */
    @Override
    public String getColumnName(int columnIndex) {
        String colname = this.nombreColumnas[columnIndex];
        if (!colname.isEmpty()) colname = Messages.getString(colname);
        return colname;
    }
    
    /**
     * Obtener el ancho de la columna indicada del modelo para dimensionar
     * la columna correspondiente del listado de la vista.
     * @param columnIndex Índice de la columna.
     * @return Ancho de la columna.
     */
    public int getColumnWidth(int columnIndex) {
        return this.anchoColumnas[columnIndex];
    }
    
    /**
     * Obtener el tipo de dato de la columna indicada del modelo.
     * @param columnIndex Índice de la columna.
     * @return Tipo de dato.
     */
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return this.tipoColumnas[columnIndex];
    }
}
