/**
 * Gestión básica de una biblioteca.
 * @author Antonio Dorado
 * @version 1.0 - 09/12/2015
 */

package com.csc.biblioteca.libro;

import com.csc.biblioteca.util.Constantes;
import com.csc.biblioteca.util.FechaCellRenderer;
import com.csc.biblioteca.util.Messages;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.sql.Date;
import javax.swing.*;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

/** Ventana de mantenimiento de libros. */
public class LibroVista extends JInternalFrame {

    /**
     * Constructor de la ventana.
     * @param titulo Título de la ventana.
     */
    public LibroVista(String titulo) {
        super(titulo, true, true, true, false);   // title, resizable, closable, maximizable, iconifiable
        this.initComponents();
    }

    /**
     * Registrar el controlador que gestionará las acciones de la ventana.
     * @param controlador Controlador de la ventana.
     */
    public void registrarActionListener(ActionListener controlador) {
        this.btnInsertar.addActionListener(controlador);
        this.btnEditar.addActionListener(controlador);
        this.btnBorrar.addActionListener(controlador);
        this.btnAceptar.addActionListener(controlador);
        this.btnCancelar.addActionListener(controlador);
        this.btnImprimir.addActionListener(controlador);
    }
    
    /**
     * Registrar el controlador que gestionará las acciones del ratón sobre el listado de la ventana.
     * @param controlador Controlador de la ventana.
     */
    public void registraMouseListener(MouseListener controlador) {
        this.tLibros.addMouseListener(controlador);
    }
    
    /**
     * Establecer el modelo del listado de la ventana (MVC).
     * @param modelo Modelo del listado.
     */
    @SuppressWarnings("unchecked")
    public void setModeloListado(LibroModelo modelo) {
        int ancho;
        
        this.tLibros.setModel(modelo);
        this.tLibros.setRowSorter(new TableRowSorter(modelo));
        
        // Establecemos los anchos de las columnas según indique el modelo
        TableColumnModel columnModel = this.tLibros.getColumnModel();
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            ancho = modelo.getColumnWidth(i);
            if (ancho > 0) {
                columnModel.getColumn(i).setMinWidth(ancho);
                columnModel.getColumn(i).setMaxWidth(ancho);
            }
        }
        
        // Cambiamos el renderer de las fechas de la tabla
        this.tLibros.setDefaultRenderer(Date.class, new FechaCellRenderer());
    }
    
    /**
     * Obtener el índice del modelo de la fila seleccionada en la tabla
     * @return Fila seleccionada o -1 si no hay
     */
    public int getFilaSeleccionada() {
        int fila = this.tLibros.getSelectedRow();
        
        // Por la ordenación hay que convertir el índice de la fila al índice del modelo
        if (fila >= 0) fila = this.tLibros.convertRowIndexToModel(fila); else fila = -1;
        
        return fila;
    }
    
    /**
     * Establecer la fila seleccionada en el listado de la ventana.
     * @param index Número de fila del modelo que hay que seleccionar.
     */
    public void setFilaSeleccionada(int index) {
        if (this.tLibros.getRowCount() > 0) {
            // Por la ordenación hay que convertir el índice del modelo al índice de la fila
            int fila = this.tLibros.convertRowIndexToView(index);
            this.tLibros.setRowSelectionInterval(fila, fila);
        }
    }
    
    /**
     * Establecer los datos del libro indicado en los campos de la ficha de edición.
     * @param libro Libro con los datos a establecer en la ficha.
     */
    public void setLibro(Libro libro) {
        this.lblIdLibro.setText(String.valueOf(libro.getIdLibro()));
        this.txtAutor.setText(libro.getAutor());
        this.txtTema.setText(libro.getTema());
        this.txtTitulo.setText(libro.getTitulo());
    }

    /**
     * Obtener el libro con los datos que hay en la ficha de edición.
     * @return Libro con los datos de la ficha.
     */
    public Libro getLibro() {
        String idLibro = this.lblIdLibro.getText();
        Libro libro = new Libro();
        
        if (!idLibro.isEmpty()) libro.setIdLibro(Integer.parseInt(idLibro));
        libro.setAutor(this.txtAutor.getText());
        libro.setTema(this.txtTema.getText());
        libro.setTitulo(this.txtTitulo.getText());
        
        return libro;
    }
    
    /**
     * Método para activar o desactivar los campos de la ficha de edición de los
     * datos del libro.
     * @param activar Indica si hay que habilitar los campos (TRUE) o no (FALSE).
     */
    public void activarFicha(boolean activar) {
        this.txtAutor.setEnabled(activar);
        this.txtTema.setEnabled(activar);
        this.txtTitulo.setEnabled(activar);
        this.btnAceptar.setEnabled(activar);
        this.btnCancelar.setEnabled(activar);
    }
    
    /** Método para limpiar los datos de la ficha de edición del libro. */
    public void limpiarFicha() {
        this.lblIdLibro.setText("");
        this.txtAutor.setText("");
        this.txtTema.setText("");
        this.txtTitulo.setText("");
    }
    
    /**
     * Método para establecer el estado en el que se encuentran los componentes
     * de la ventana de mantenimiento según el estado de edición del libro.
     * @param estado Estado a establecer.
     */
    public void setEstado(LibroControlador.Estado estado) {
        boolean haySeleccionada = (this.tLibros.getSelectedRowCount() > 0);
                
        switch (estado) {
            case CREACION:
            case EDICION:
                // Habilitar la ficha (Aceptar, Cancelar) y deshabilitar tabla (Insertar, Editar, Eliminar)
                this.activarFicha(true);
                this.btnInsertar.setEnabled(false);
                this.btnImprimir.setEnabled(false);
                this.btnEditar.setEnabled(false);
                this.btnBorrar.setEnabled(false);
                this.tLibros.setEnabled(false);
                break;
                
            case LECTURA:
                // Deshabilitar la ficha (Aceptar, Cancelar) y habilitar tabla (Insertar, Editar)
                this.activarFicha(false);
                this.btnInsertar.setEnabled(true);
                this.btnImprimir.setEnabled(true);
                this.tLibros.setEnabled(true);
                this.tLibros.requestFocus();
                this.btnEditar.setEnabled(haySeleccionada);
                this.btnBorrar.setEnabled(false);
                break;
                
            case MARCADO:
                // Deshabilitar la ficha (Aceptar, Cancelar) y habilitar tabla (Insertar, Editar, Eliminar)
                this.activarFicha(false);
                this.btnInsertar.setEnabled(true);
                this.btnImprimir.setEnabled(true);
                this.tLibros.setEnabled(true);
                this.tLibros.requestFocus();
                this.btnEditar.setEnabled(haySeleccionada);
                this.btnBorrar.setEnabled(true);
                break;
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jScrollPane1 = new javax.swing.JScrollPane();
        tLibros = new javax.swing.JTable();
        btnInsertar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnBorrar = new javax.swing.JButton();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        lblId = new javax.swing.JLabel();
        lblIdLibro = new javax.swing.JLabel();
        lblTitulo = new javax.swing.JLabel();
        txtTitulo = new javax.swing.JTextField();
        lblAutor = new javax.swing.JLabel();
        txtAutor = new javax.swing.JTextField();
        lblTema = new javax.swing.JLabel();
        txtTema = new javax.swing.JTextField();
        panNulo = new javax.swing.JPanel();
        btnImprimir = new javax.swing.JButton();

        getContentPane().setLayout(new java.awt.GridBagLayout());

        tLibros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sel", "Id", "Nombre", "Autor", "Tema"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tLibros.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tLibros);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        getContentPane().add(jScrollPane1, gridBagConstraints);

        btnInsertar.setText(Messages.getString("BOTON_INSERTAR")); // NOI18N
        btnInsertar.setActionCommand(Constantes.INSERTAR);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        getContentPane().add(btnInsertar, gridBagConstraints);

        btnEditar.setText(Messages.getString("BOTON_EDITAR")); // NOI18N
        btnEditar.setActionCommand(Constantes.EDITAR);
        btnEditar.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        getContentPane().add(btnEditar, gridBagConstraints);

        btnBorrar.setText(Messages.getString("BOTON_ELIMINAR")); // NOI18N
        btnBorrar.setActionCommand(Constantes.BORRAR);
        btnBorrar.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        getContentPane().add(btnBorrar, gridBagConstraints);

        btnAceptar.setText(Messages.getString("BOTON_ACEPTAR")); // NOI18N
        btnAceptar.setActionCommand(Constantes.ACEPTAR);
        btnAceptar.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        getContentPane().add(btnAceptar, gridBagConstraints);

        btnCancelar.setText(Messages.getString("BOTON_CANCELAR")); // NOI18N
        btnCancelar.setActionCommand(Constantes.CANCELAR);
        btnCancelar.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        getContentPane().add(btnCancelar, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        getContentPane().add(jSeparator1, gridBagConstraints);

        lblId.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblId.setText(Messages.getString("ETQ_ID")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        getContentPane().add(lblId, gridBagConstraints);

        lblIdLibro.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblIdLibro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIdLibro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        lblIdLibro.setMaximumSize(new java.awt.Dimension(60, 20));
        lblIdLibro.setMinimumSize(new java.awt.Dimension(20, 20));
        lblIdLibro.setPreferredSize(new java.awt.Dimension(60, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 4, 2, 2);
        getContentPane().add(lblIdLibro, gridBagConstraints);

        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTitulo.setText(Messages.getString("ETQ_TITULO")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        getContentPane().add(lblTitulo, gridBagConstraints);

        txtTitulo.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtTitulo.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        getContentPane().add(txtTitulo, gridBagConstraints);

        lblAutor.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblAutor.setText(Messages.getString("ETQ_AUTOR")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        getContentPane().add(lblAutor, gridBagConstraints);

        txtAutor.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtAutor.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        getContentPane().add(txtAutor, gridBagConstraints);

        lblTema.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTema.setText(Messages.getString("ETQ_TEMA")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        getContentPane().add(lblTema, gridBagConstraints);

        txtTema.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtTema.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        getContentPane().add(txtTema, gridBagConstraints);

        javax.swing.GroupLayout panNuloLayout = new javax.swing.GroupLayout(panNulo);
        panNulo.setLayout(panNuloLayout);
        panNuloLayout.setHorizontalGroup(
            panNuloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panNuloLayout.setVerticalGroup(
            panNuloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.4;
        getContentPane().add(panNulo, gridBagConstraints);

        btnImprimir.setText(Messages.getString("BOTON_IMPRIMIR")); // NOI18N
        btnImprimir.setActionCommand(Constantes.IMPRIMIR);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        getContentPane().add(btnImprimir, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnInsertar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblAutor;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblIdLibro;
    private javax.swing.JLabel lblTema;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel panNulo;
    private javax.swing.JTable tLibros;
    private javax.swing.JTextField txtAutor;
    private javax.swing.JTextField txtTema;
    private javax.swing.JTextField txtTitulo;
    // End of variables declaration//GEN-END:variables
}
