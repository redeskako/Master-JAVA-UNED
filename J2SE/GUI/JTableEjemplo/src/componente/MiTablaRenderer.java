package componente;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kako
 */
public class MiTablaRenderer extends DefaultTableCellRenderer{
    public MiTablaRenderer(){
        super();
    }
    public Component getTableCellRendererComponent (JTable tabla,
                                                    Object obj,
                                                    boolean esSeleccionado,
                                                    boolean tieneFoco,
                                                    int fila,
                                                    int columna
                                                    ){
        Component celda = super.getTableCellRendererComponent(tabla,
                                                              obj,
                                                              esSeleccionado,
                                                              tieneFoco,
                                                              fila,
                                                              columna);
        if (esSeleccionado){
            celda.setBackground(Color.green);
        }else{
            if (fila % 2 == 0){
                celda.setBackground(Color.cyan);
            }else{
                celda.setBackground(Color.lightGray);
            }
        }
        return celda;
    }
}
