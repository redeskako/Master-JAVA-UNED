/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import comercio.ComercioTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

/**
 *
 * @author adorado
 */
public class VentanaComercio implements ActionListener, TableModelListener{
    private JFrame  frame;
    private JPanel  panel;
	private JButton mibotonera[];
    private JTable  mitabla;
    private JScrollPane stabla;

    private ComercioTableModel  comercioTableModel;

    private String  txtBoton[] = {"Nuevo", "Aceptar", "Cancelar"};
    public final static int NUEVO = 0;
    public final static int ACEPTAR = 1;
    public final static int CANCELAR = 2;

	public final static String accion[] = {"NUEVO", "ACEPTAR", "CANCELAR"};
    public final static Boolean activo[] = {true, false, true};

    public VentanaComercio(String titulo) {
        // Contenedor superior
        this.frame = new JFrame(titulo);
        
        // Contenedor intermedio (Java por defecto crea un contenedor intermedio de tipo JPanel
        this.panel = (JPanel) this.frame.getContentPane();

        // Layout para distribuir los componentes en condiciones
        this.panel.setLayout(new BorderLayout());

        // Creamos componentes visuales
		this.mibotonera = new JButton[txtBoton.length];
		for (int i= 0; i < txtBoton.length;i++){
			this.mibotonera[i] = new JButton(txtBoton[i]);
            this.mibotonera[i].setEnabled(VentanaComercio.activo[i]);
			this.mibotonera[i].setActionCommand(VentanaComercio.accion[i]);
			this.mibotonera[i].addActionListener(this);
		}
        this.comercioTableModel = new ComercioTableModel("localhost", "comercio", "usuario", "clave");

		this.mitabla= new JTable(this.comercioTableModel);
		this.mitabla.getModel().addTableModelListener(this);
		this.stabla= new JScrollPane(this.mitabla);

        // Asociamos los componentes al contenedor intermedio, indicando el Layout para q se vean en condiciones los componentes
		FlowLayout layout = new FlowLayout(this.mibotonera.length);
        layout.setAlignment(FlowLayout.RIGHT);
        JPanel p1 = new JPanel(layout);
		for (int i=0; i<this.mibotonera.length; i++){
			p1.add(this.mibotonera[i]);
		}
		panel.add(p1, BorderLayout.SOUTH);
		panel.add(stabla, BorderLayout.CENTER);

        // Dimensiones
        this.frame.setSize(new Dimension(600,400));
        this.frame.setLocation(100, 100);
        
        // Hago visible el contenedor superior
        this.frame.setVisible(true);

        // Establecer la operación por defecto en caso de cerrar la ventana
        // Lo ponemos porque por defecto queda invisible
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Encaja en la ventana según los componentes que hemos añadido
        //this.frame.pack();
    }

    public void cargarDatos() {
        ComercioTableModel modelo = (ComercioTableModel) this.mitabla.getModel();
        modelo.cargarDatos();
    }

    public void actionPerformed(ActionEvent e) {
        String str = e.getActionCommand();

        //System.out.println(str);
        if (str.equals(VentanaComercio.accion[VentanaComercio.NUEVO])) {
            this.comercioTableModel.nuevo();
        } else if (str.equals(VentanaComercio.accion[VentanaComercio.ACEPTAR])) {
            this.comercioTableModel.aceptar();
        } else if (str.equals(VentanaComercio.accion[VentanaComercio.CANCELAR])) {
            this.comercioTableModel.cancelar();
        }
    }

    public void tableChanged(TableModelEvent e) {
        // Activar el botón de Aceptar solo si hay cambios pendientes
        this.mibotonera[VentanaComercio.ACEPTAR].setEnabled(this.comercioTableModel.hayCambios());
    }
}
