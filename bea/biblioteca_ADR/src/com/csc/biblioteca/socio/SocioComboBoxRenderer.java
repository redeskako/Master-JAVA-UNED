/**
 * Gestión básica de una biblioteca.
 * @author Antonio Dorado
 * @version 1.0 - 15/12/2015
 */

package com.csc.biblioteca.socio;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/** Clase renderer para el combo desplegable de socios. */
public class SocioComboBoxRenderer extends JLabel implements ListCellRenderer {

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
            Socio socio = (Socio) value;
            this.setText(socio.getApellidos()+", "+socio.getNombre());
        } else {
            this.setText("");
        }
        
        return this;
    }
}
