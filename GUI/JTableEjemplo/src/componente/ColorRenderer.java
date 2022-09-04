package componente;


import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.TableCellRenderer;

/*
 *
 */

/**
 *
 * @author kako
 */


public class ColorRenderer extends JLabel implements TableCellRenderer{
    private Border bordeNoSeleccionado;
    private Border bordeSeleccionado;
    private boolean esBorde;

    public ColorRenderer(boolean esBorde){
        this.esBorde= esBorde;
        this.setOpaque(true); //Se debe realizar para mostrar el fondo
    }
    //Método que implementa TableCellRenderer y que se usa precisamente para poder cambiar su tonalidad
    public Component getTableCellRendererComponent(JTable table, Object color, boolean isSelected,
                                                    boolean hasFocus, int row, int column) {
        Color nuevoColor= (Color) color;
        this.setBackground(nuevoColor);
        if (this.esBorde){
            if (isSelected){
                if (this.bordeSeleccionado==null){
                    this.bordeSeleccionado = BorderFactory.createMatteBorder(2,5,2,5, table.getSelectionBackground());
                }
                this.setBorder(this.bordeSeleccionado);
            }else{
                if (this.bordeNoSeleccionado==null){
                    this.bordeNoSeleccionado= BorderFactory.createMatteBorder(2,5,2,5, table.getSelectionBackground());
                }
                this.setBorder(this.bordeNoSeleccionado);
            }
        }
        this.setToolTipText("RGB valor:" + nuevoColor.getRed() + ", "
                                         + nuevoColor.getGreen() + ", "+
                                         + nuevoColor.getBlue());
        return this;
    }
}
