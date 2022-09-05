/**
 * Gestión básica de una biblioteca.
 * @author Antonio Dorado
 * @version 1.0 - 09/12/2015
 */

package com.csc.biblioteca.prestamo;

import com.csc.biblioteca.libro.Libro;
import com.csc.biblioteca.socio.Socio;
import com.csc.biblioteca.util.Constantes;
import com.csc.biblioteca.util.FechaCellEditor;
import com.csc.biblioteca.util.FechaCellRenderer;
import com.csc.biblioteca.util.Messages;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.sql.Date;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

/** Ventana de mantenimiento de prestamos. */
public class PrestamoVista extends JInternalFrame {

    /**
     * Constructor de la ventana.
     * @param titulo Título de la ventana.
     */
    public PrestamoVista(String titulo) {
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
        this.tPrestamos.addMouseListener(controlador);
    }
    
    /**
     * Establecer el modelo del listado de la ventana (MVC).
     * @param modelo Modelo del listado.
     */
    @SuppressWarnings("unchecked")
    public void setModeloListado(PrestamoModelo modelo) {
        int ancho;
        
        this.tPrestamos.setModel(modelo);
        this.tPrestamos.setRowSorter(new TableRowSorter(modelo));
        
        // Establecemos los anchos de las columnas según indique el modelo
        TableColumnModel columnModel = this.tPrestamos.getColumnModel();
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            ancho = modelo.getColumnWidth(i);
            if (ancho > 0) {
                columnModel.getColumn(i).setMinWidth(ancho);
                columnModel.getColumn(i).setMaxWidth(ancho);
            }
        }
        
        // Cambiamos el renderer y el editor de las fechas de la tabla
        this.tPrestamos.setDefaultRenderer(Date.class, new FechaCellRenderer());
        this.tPrestamos.setDefaultEditor(Date.class, new FechaCellEditor());
    }
    
    /**
     * Establecer el modelo y renderer asociado al campo desplegable de socios.
     * @param modelo Modelo del desplegable.
     * @param renderer Renderer del desplegable.
     */
    @SuppressWarnings("unchecked")
    public void setComboBoxSocios(ComboBoxModel modelo, ListCellRenderer renderer) {
        this.cmbSocio.setModel(modelo);
        this.cmbSocio.setRenderer(renderer);
    }
    
    /**
     * Establecer el modelo y renderer asociado al campo desplegable de libros.
     * @param modelo Modelo del desplegable.
     * @param renderer Renderer del desplegable.
     */
    @SuppressWarnings("unchecked")
    public void setComboBoxLibros(ComboBoxModel modelo, ListCellRenderer renderer) {
        this.cmbLibro.setModel(modelo);
        this.cmbLibro.setRenderer(renderer);
    }
    
    /**
     * Obtener el índice del modelo de la fila seleccionada en la tabla
     * @return Fila seleccionada o -1 si no hay
     */
    public int getFilaSeleccionada() {
        int fila = this.tPrestamos.getSelectedRow();
        
        // Por la ordenación hay que convertir el índice de la fila al índice del modelo
        if (fila >= 0) fila = this.tPrestamos.convertRowIndexToModel(fila); else fila = -1;
        
        return fila;
    }
    
    /**
     * Establecer la fila seleccionada en el listado de la ventana.
     * @param index Número de fila del modelo que hay que seleccionar.
     */
    public void setFilaSeleccionada(int index) {
        if (this.tPrestamos.getRowCount() > 0) {
            // Por la ordenación hay que convertir el índice del modelo al índice de la fila
            int fila = this.tPrestamos.convertRowIndexToView(index);
            this.tPrestamos.setRowSelectionInterval(fila, fila);
        }
    }
    
    /**
     * Establecer los datos del préstamo indicado en los campos de la ficha de edición.
     * @param prestamo Préstamo con los datos a establecer en la ficha.
     */
    public void setPrestamo(Prestamo prestamo) {
        this.lblIdPrestamo.setText(String.valueOf(prestamo.getIdPrestamo()));
        this.cmbSocio.setSelectedItem(prestamo.getSocio());
        this.cmbLibro.setSelectedItem(prestamo.getLibro());
        this.dtcFechaInicio.setDate(prestamo.getFechaInicio());
        this.dtcFechaFin.setDate(prestamo.getFechaFin());
    }

    /**
     * Obtener el préstamo con los datos que hay en la ficha de edición.
     * @return Préstamo con los datos de la ficha.
     */
    public Prestamo getPrestamo() {
        java.util.Date fechaInicio = this.dtcFechaInicio.getDate();
        java.util.Date fechaFin = this.dtcFechaFin.getDate();
        String idPrestamo = this.lblIdPrestamo.getText();
        Prestamo prestamo = new Prestamo();
        
        if (!idPrestamo.isEmpty()) prestamo.setIdPrestamo(Integer.parseInt(idPrestamo));
        prestamo.setSocio((Socio)this.cmbSocio.getSelectedItem());
        prestamo.setLibro((Libro)this.cmbLibro.getSelectedItem());
        if (fechaInicio != null) prestamo.setFechaInicio(new java.sql.Date(fechaInicio.getTime()));
        if (fechaFin != null) prestamo.setFechaFin(new java.sql.Date(fechaFin.getTime()));
        
        return prestamo;
    }
    
    /**
     * Método para activar o desactivar los campos de la ficha de edición de los
     * datos del préstamo.
     * @param activar Indica si hay que habilitar los campos (TRUE) o no (FALSE).
     */
    public void activarFicha(boolean activar) {
        this.cmbSocio.setEnabled(activar);
        this.cmbLibro.setEnabled(activar);
        this.dtcFechaInicio.setEnabled(activar);
        this.dtcFechaFin.setEnabled(activar);
        this.btnAceptar.setEnabled(activar);
        this.btnCancelar.setEnabled(activar);
    }
    
    /** Método para limpiar los datos de la ficha de edición del préstamo. */
    public void limpiarFicha() {
        this.lblIdPrestamo.setText("");
        this.cmbSocio.setSelectedItem(null);
        this.cmbLibro.setSelectedItem(null);
        this.dtcFechaInicio.setDate(null);
        this.dtcFechaFin.setDate(null);
    }
    
    /**
     * Método para establecer el estado en el que se encuentran los componentes
     * de la ventana de mantenimiento según el estado de edición del préstamo.
     * @param estado Estado a establecer.
     */
    public void setEstado(PrestamoControlador.Estado estado) {
        boolean haySeleccionada = (this.tPrestamos.getSelectedRowCount() > 0);
                
        switch (estado) {
            case CREACION:
                this.dtcFechaInicio.setDate(new java.util.Date());
            case EDICION:
                // Habilitar la ficha (Aceptar, Cancelar) y deshabilitar tabla (Insertar, Editar, Eliminar)
                this.activarFicha(true);
                this.btnInsertar.setEnabled(false);
                this.btnImprimir.setEnabled(false);
                this.btnEditar.setEnabled(false);
                this.btnBorrar.setEnabled(false);
                this.tPrestamos.setEnabled(false);
                break;
                
            case LECTURA:
                // Deshabilitar la ficha (Aceptar, Cancelar) y habilitar tabla (Insertar, Editar)
                this.activarFicha(false);
                this.btnInsertar.setEnabled(true);
                this.btnImprimir.setEnabled(true);
                this.tPrestamos.setEnabled(true);
                this.tPrestamos.requestFocus();
                this.btnEditar.setEnabled(haySeleccionada);
                this.btnBorrar.setEnabled(false);
                break;
                
            case MARCADO:
                // Deshabilitar la ficha (Aceptar, Cancelar) y habilitar tabla (Insertar, Editar, Eliminar)
                this.activarFicha(false);
                this.btnInsertar.setEnabled(true);
                this.btnImprimir.setEnabled(true);
                this.tPrestamos.setEnabled(true);
                this.tPrestamos.requestFocus();
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
        tPrestamos = new javax.swing.JTable() {
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                if (!isRowSelected(row)) {
                    int modelRow = convertRowIndexToModel(row);
                    PrestamoModelo modelo = (PrestamoModelo)getModel();
                    c.setBackground(modelo.getFilaColor(modelRow));
                }
                return c;
            }
        };
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        lblId = new javax.swing.JLabel();
        lblIdPrestamo = new javax.swing.JLabel();
        lblFechaInicio = new javax.swing.JLabel();
        lblSocio = new javax.swing.JLabel();
        lblLibro = new javax.swing.JLabel();
        lblFechaFin = new javax.swing.JLabel();
        dtcFechaInicio = new com.toedter.calendar.JDateChooser();
        dtcFechaFin = new com.toedter.calendar.JDateChooser();
        cmbSocio = new javax.swing.JComboBox();
        cmbLibro = new javax.swing.JComboBox();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        jpn_botonera = new javax.swing.JPanel();
        btnInsertar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnBorrar = new javax.swing.JButton();
        btnImprimir = new javax.swing.JButton();

        getContentPane().setLayout(new java.awt.GridBagLayout());

        tPrestamos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sel", "Id", "Socio", "Libro", "Inicio", "Fin"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tPrestamos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tPrestamos);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        getContentPane().add(jScrollPane1, gridBagConstraints);

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
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(2, 6, 2, 6);
        getContentPane().add(lblId, gridBagConstraints);

        lblIdPrestamo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblIdPrestamo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIdPrestamo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        lblIdPrestamo.setMaximumSize(new java.awt.Dimension(60, 20));
        lblIdPrestamo.setMinimumSize(new java.awt.Dimension(60, 20));
        lblIdPrestamo.setPreferredSize(new java.awt.Dimension(60, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(2, 4, 2, 2);
        getContentPane().add(lblIdPrestamo, gridBagConstraints);

        lblFechaInicio.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblFechaInicio.setText(Messages.getString("ETQ_FECHA_INICIO")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        getContentPane().add(lblFechaInicio, gridBagConstraints);

        lblSocio.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSocio.setText(Messages.getString("ETQ_SOCIO")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(2, 6, 2, 6);
        getContentPane().add(lblSocio, gridBagConstraints);

        lblLibro.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblLibro.setText(Messages.getString("ETQ_LIBRO")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(2, 6, 2, 6);
        getContentPane().add(lblLibro, gridBagConstraints);

        lblFechaFin.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblFechaFin.setText(Messages.getString("ETQ_FECHA_FIN")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        getContentPane().add(lblFechaFin, gridBagConstraints);

        dtcFechaInicio.setDateFormatString(Constantes.FORMATO_FECHA);
        dtcFechaInicio.setEnabled(false);
        dtcFechaInicio.setMaximumSize(new java.awt.Dimension(130, 25));
        dtcFechaInicio.setMinimumSize(new java.awt.Dimension(130, 25));
        dtcFechaInicio.setPreferredSize(new java.awt.Dimension(130, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        getContentPane().add(dtcFechaInicio, gridBagConstraints);

        dtcFechaFin.setDateFormatString(Constantes.FORMATO_FECHA);
        dtcFechaFin.setEnabled(false);
        dtcFechaFin.setMaximumSize(new java.awt.Dimension(130, 25));
        dtcFechaFin.setMinimumSize(new java.awt.Dimension(130, 25));
        dtcFechaFin.setPreferredSize(new java.awt.Dimension(130, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        getContentPane().add(dtcFechaFin, gridBagConstraints);

        cmbSocio.setBorder(null);
        cmbSocio.setEnabled(false);
        cmbSocio.setMaximumSize(new java.awt.Dimension(1000, 25));
        cmbSocio.setMinimumSize(new java.awt.Dimension(28, 25));
        cmbSocio.setPreferredSize(new java.awt.Dimension(28, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        getContentPane().add(cmbSocio, gridBagConstraints);

        cmbLibro.setEnabled(false);
        cmbLibro.setLightWeightPopupEnabled(false);
        cmbLibro.setMaximumSize(new java.awt.Dimension(1000, 25));
        cmbLibro.setMinimumSize(new java.awt.Dimension(28, 25));
        cmbLibro.setPreferredSize(new java.awt.Dimension(28, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        getContentPane().add(cmbLibro, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(filler1, gridBagConstraints);

        jpn_botonera.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        btnInsertar.setText(Messages.getString("BOTON_INSERTAR")); // NOI18N
        btnInsertar.setActionCommand(Constantes.INSERTAR);
        jpn_botonera.add(btnInsertar);

        btnEditar.setText(Messages.getString("BOTON_EDITAR")); // NOI18N
        btnEditar.setActionCommand(Constantes.EDITAR);
        btnEditar.setEnabled(false);
        jpn_botonera.add(btnEditar);

        btnBorrar.setText(Messages.getString("BOTON_ELIMINAR")); // NOI18N
        btnBorrar.setActionCommand(Constantes.BORRAR);
        btnBorrar.setEnabled(false);
        jpn_botonera.add(btnBorrar);

        btnImprimir.setText(Messages.getString("BOTON_IMPRIMIR")); // NOI18N
        btnImprimir.setActionCommand(Constantes.IMPRIMIR);
        jpn_botonera.add(btnImprimir);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        getContentPane().add(jpn_botonera, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnInsertar;
    private javax.swing.JComboBox cmbLibro;
    private javax.swing.JComboBox cmbSocio;
    private com.toedter.calendar.JDateChooser dtcFechaFin;
    private com.toedter.calendar.JDateChooser dtcFechaInicio;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel jpn_botonera;
    private javax.swing.JLabel lblFechaFin;
    private javax.swing.JLabel lblFechaInicio;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblIdPrestamo;
    private javax.swing.JLabel lblLibro;
    private javax.swing.JLabel lblSocio;
    private javax.swing.JTable tPrestamos;
    // End of variables declaration//GEN-END:variables
}
