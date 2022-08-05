/*
 * 
 */

package componente;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

/**
 *
 * @author kako
 */
public class MiTablaTuneada {
    private JFrame frame;
    private JPanel panel;





    private JTable tabla;
    private TableColumn tcolumna;

    public MiTablaTuneada(){
        this.frame= new JFrame ("Creando un Ejemeplo de Estilos en JTable");
        this.panel= new JPanel();
        // Formar los datos de forma chapucera de la JTable
        //Introducción de ejemplo
        String datos [][] = {
                                {"ACER", "Ordenador","3"},
                                {"VAIO", "Ordenador", "2"},
                                {"ASUS", "Placa Base", "4"},
                                {"SONY", "Monitor","5"}
                            };
        //Cabecera de la tabla
        String cabecera []= {"Componente","Material", "Cantidad"};

        DefaultTableModel modelo = new DefaultTableModel (datos,cabecera);
        this.tabla = new JTable(modelo);

        this.tcolumna = tabla.getColumnModel().getColumn(0);
        this.tcolumna.setCellRenderer(new MiTablaRenderer());

        this.tcolumna = tabla.getColumnModel().getColumn(1);
        this.tcolumna.setCellRenderer(new MiTablaRenderer());

        this.tcolumna = tabla.getColumnModel().getColumn(2);
        this.tcolumna.setCellRenderer(new MiTablaRenderer());

        JTableHeader cabeceraTabla= tabla.getTableHeader();
        cabeceraTabla.setBackground(Color.yellow);
        JScrollPane scroll= new JScrollPane(tabla);

        this.panel.add(scroll);
        this.frame.add(this.panel);
        this.frame.setSize(500,150);
        this.frame.setUndecorated(true);
        this.frame.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setVisible(true);
    }
}
