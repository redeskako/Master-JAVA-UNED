/**
 * Gestión básica de una biblioteca.
 * @author Antonio Dorado
 * @version 1.0 - 07/01/2016
 */

package com.csc.biblioteca.util;

import java.awt.Color;
import java.awt.Component;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.sql.Date;
import javax.swing.DefaultCellEditor;
import javax.swing.JFormattedTextField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.text.DateFormatter;

/** Clase para la edición de las fechas de una tabla. */
public class FechaCellEditor extends DefaultCellEditor {
    private final static SimpleDateFormat format = new SimpleDateFormat(Constantes.FORMATO_FECHA);
    private String valorInicial;
    
    /** Constructor del CellEditor. */
    public FechaCellEditor() {
        super(new JFormattedTextField(new DateFormatter(format)));
        format.setLenient(false);
        this.valorInicial = "";
    }

    /**
     * Obtener el valor de la celda que va a ser editada.
     * @return Valor de la celda.
     */
    @Override
    public Object getCellEditorValue() {
        try {
            Date valor = new Date(format.parse(((JTextField)getComponent()).getText()).getTime());
            return valor;
        } catch (ParseException ex) {
            return null;
        }
    }

    /**
     * Obtener el componente que se utiliza para la edición de la celda.
     * @param table Tabla a la que pertenece la celda a editar.
     * @param value Valor de la celda.
     * @param isSelected Si está seleccionada (TRUE) o no (FALSE).
     * @param row Fila de la celda.
     * @param column Columna de la celda.
     * @return Componente que se utiliza para la edición de la celda.
     */
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, 
            boolean isSelected, int row, int column) {
        JTextField componente = ((JTextField)getComponent());
        componente.setBorder(new LineBorder(Color.BLACK));
        try {
            this.valorInicial = format.format(value);
        } catch (Exception ex) {
            this.valorInicial = "";
        }
        componente.setText(this.valorInicial);
        return componente;
    }
    
    /**
     * Validar si la cadena de entrada tiene una fecha válida según el formato
     * de la aplicación (Constantes.FORMATO_FECHA).
     * @param valor Cadena de texto a validar.
     * @return Si es válida (TRUE) o no (FALSE).
     */
    private boolean validarFecha(String valor) {
        ParsePosition posicion = new ParsePosition(0);
        format.parse(valor, posicion);
        
        return (posicion.getIndex() == valor.length());
    }

    /**
     * Método para gestionar la finalización de la edición y permitirla o no.
     * También se valida la correción del formato del nuevo valor de la celda.
     * @return Indica si permite la finalización de la edición (TRUE) o no (FALSE).
     */
    @Override
    public boolean stopCellEditing() {
        String valor = ((JTextField)getComponent()).getText();
        
        if (valor.equals(this.valorInicial)) {
            // Control del mismo valor inicial para que no avise de un cambio de valor
            cancelCellEditing();
        } else if (valor.equals("") || !validarFecha(valor)) {
            ((JTextField)getComponent()).setBorder(new LineBorder(Color.RED));
            return false;
        }
        return super.stopCellEditing();
    }
}
