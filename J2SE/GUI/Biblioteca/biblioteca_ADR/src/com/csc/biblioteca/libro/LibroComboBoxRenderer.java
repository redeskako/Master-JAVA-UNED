/**
 * Gestión básica de una biblioteca.
 * @author Antonio Dorado
 * @version 1.0 - 15/12/2015
 */

package com.csc.biblioteca.libro;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/** Clase renderer para el combo desplegable de libros. */
public class LibroComboBoxRenderer extends JLabel implements ListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(Color.WHITE);
            setForeground(list.getForeground());
        }
        
        if (value != null) {
            Libro libro = (Libro) value;
            this.setText(libro.getTitulo());
        } else {
            this.setText("");
        }
        
        return this;
    }
}
