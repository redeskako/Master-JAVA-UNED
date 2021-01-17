/**
 * Gestión básica de una biblioteca.
 * @author Antonio Dorado
 * @version 1.0 - 11/12/2015
 */

package com.csc.biblioteca.util;

import java.util.ArrayList;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/** Modelo genérico para usar en un desplegable a partir de un listado (ArrayList) */
public class ArrayListComboBoxModel extends AbstractListModel implements ComboBoxModel {
    private Object selectedItem;
    private final ArrayList listado;

    /**
     * Constructor del modelo.
     * @param listado Listado que contendrá el modelo.
     */
    public ArrayListComboBoxModel(ArrayList listado) {
        this.listado = listado;
    }

    /**
     * Obtener el tamaño del listado.
     * @return Número de elementos del listado.
     */
    @Override
    public int getSize() {
        return this.listado.size();
    }

    /**
     * Obtener el elemento del modelo dada su posición.
     * @param index Índice del elemento.
     * @return Elemento en la posición indicada.
     */
    @Override
    public Object getElementAt(int index) {
        return this.listado.get(index);
    }

    /**
     * Establecer el elemento seleccionado en el desplegable.
     * @param anItem Elemento seleccionado.
     */
    @Override
    public void setSelectedItem(Object anItem) {
        this.selectedItem = anItem;
    }
    
    /**
     * Obtener el elemento seleccionado en el desplegable.
     * @return Elemento seleccionado.
     */
    @Override
    public Object getSelectedItem() {
        return this.selectedItem;
    }
}
