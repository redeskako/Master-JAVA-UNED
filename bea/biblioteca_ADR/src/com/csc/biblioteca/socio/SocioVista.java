/**
 * Gestión básica de una biblioteca.
 * @author Antonio Dorado
 * @version 1.0 - 09/12/2015
 */

package com.csc.biblioteca.socio;

import com.csc.biblioteca.util.Constantes;
import com.csc.biblioteca.util.FechaCellRenderer;
import com.csc.biblioteca.util.Messages;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.sql.Date;
import javax.swing.*;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

/** Ventana de mantenimiento de socios. */
public class SocioVista extends JInternalFrame {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor de la ventana.
     * @param titulo Título de la ventana.
     */
    public SocioVista(String titulo) {
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
        this.tSocios.addMouseListener(controlador);
    }
    
    /**
     * Establecer el modelo del listado de la ventana (MVC).
     * @param modelo Modelo del listado.
     */
    @SuppressWarnings("unchecked")
    public void setModeloListado(SocioModelo modelo) {
        int ancho;
        
        this.tSocios.setModel(modelo);
        this.tSocios.setRowSorter(new TableRowSorter(modelo));
        
        // Establecemos los anchos de las columnas según indique el modelo
        TableColumnModel columnModel = this.tSocios.getColumnModel();
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            ancho = modelo.getColumnWidth(i);
            if (ancho > 0) {
                columnModel.getColumn(i).setMinWidth(ancho);
                columnModel.getColumn(i).setMaxWidth(ancho);
            }
        }
        
        // Cambiamos el renderer de las fechas de la tabla
        this.tSocios.setDefaultRenderer(Date.class, new FechaCellRenderer());
    }
    
    /**
     * Obtener el índice del modelo de la fila seleccionada en la tabla
     * @return Fila seleccionada o -1 si no hay
     */
    public int getFilaSeleccionada() {
        int fila = this.tSocios.getSelectedRow();
        
        // Por la ordenación hay que convertir el índice de la fila al índice del modelo
        if (fila >= 0) fila = this.tSocios.convertRowIndexToModel(fila); else fila = -1;
        
        return fila;
    }
    
    /**
     * Establecer la fila seleccionada en el listado de la ventana.
     * @param index Número de fila del modelo que hay que seleccionar.
     */
    public void setFilaSeleccionada(int index) {
        if (this.tSocios.getRowCount() > 0) {
            // Por la ordenación hay que convertir el índice del modelo al índice de la fila
            int fila = this.tSocios.convertRowIndexToView(index);
            this.tSocios.setRowSelectionInterval(fila, fila);
        }
    }
    
    /**
     * Establecer los datos del socio indicado en los campos de la ficha de edición.
     * @param socio Socio con los datos a establecer en la ficha.
     */
    public void setSocio(Socio socio) {
        this.lblIdSocio.setText(String.valueOf(socio.getIdSocio()));
        this.txtApellidos.setText(socio.getApellidos());
        this.txtDNI.setText(socio.getDni());
        this.txtDireccion.setText(socio.getDireccion());
        this.txtNombre.setText(socio.getNombre());
        this.dtcFechaAlta.setDate(socio.getFechaAlta());
    }

    /**
     * Obtener el socio con los datos que hay en la ficha de edición.
     * @return Socio con los datos de la ficha.
     */
    public Socio getSocio() {
        java.util.Date fechaAlta = this.dtcFechaAlta.getDate();
        String idSocio = this.lblIdSocio.getText();
        Socio socio = new Socio();
        
        if (!idSocio.isEmpty()) socio.setIdSocio(Integer.parseInt(idSocio));
        socio.setApellidos(this.txtApellidos.getText());
        socio.setDni(this.txtDNI.getText());
        socio.setDireccion(this.txtDireccion.getText());
        socio.setNombre(this.txtNombre.getText());
        if (fechaAlta != null) socio.setFechaAlta(new java.sql.Date(fechaAlta.getTime()));
        
        return socio;
    }
    
    /**
     * Método para activar o desactivar los campos de la ficha de edición de los
     * datos del socio.
     * @param activar Indica si hay que habilitar los campos (TRUE) o no (FALSE).
     */
    public void activarFicha(boolean activar) {
        this.txtApellidos.setEnabled(activar);
        this.txtDNI.setEnabled(activar);
        this.txtDireccion.setEnabled(activar);
        this.txtNombre.setEnabled(activar);
        this.dtcFechaAlta.setEnabled(activar);
        this.btnAceptar.setEnabled(activar);
        this.btnCancelar.setEnabled(activar);
    }
    
    /** Método para limpiar los datos de la ficha de edición del socio. */
    public void limpiarFicha() {
        this.lblIdSocio.setText("");
        this.txtApellidos.setText("");
        this.txtDNI.setText("");
        this.txtDireccion.setText("");
        this.txtNombre.setText("");
        this.dtcFechaAlta.setDate(null);
    }
    
    /**
     * Método para establecer el estado en el que se encuentran los componentes
     * de la ventana de mantenimiento según el estado de edición del socio.
     * @param estado Estado a establecer.
     */
    public void setEstado(SocioControlador.Estado estado) {
        boolean haySeleccionada = (this.tSocios.getSelectedRowCount() > 0);
                
        switch (estado) {
            case CREACION:
                this.dtcFechaAlta.setDate(new java.util.Date());
            case EDICION:
                // Habilitar la ficha (Aceptar, Cancelar) y deshabilitar tabla (Insertar, Editar, Eliminar)
                this.activarFicha(true);
                this.btnInsertar.setEnabled(false);
                this.btnImprimir.setEnabled(false);
                this.btnEditar.setEnabled(false);
                this.btnBorrar.setEnabled(false);
                this.tSocios.setEnabled(false);
                this.txtDNI.requestFocus();
                break;
                
            case LECTURA:
                // Deshabilitar la ficha (Aceptar, Cancelar) y habilitar tabla (Insertar, Editar)
                this.activarFicha(false);
                this.btnInsertar.setEnabled(true);
                this.btnImprimir.setEnabled(true);
                this.tSocios.setEnabled(true);
                this.tSocios.requestFocus();
                this.btnEditar.setEnabled(haySeleccionada);
                this.btnBorrar.setEnabled(false);
                break;
                
            case MARCADO:
                // Deshabilitar la ficha (Aceptar, Cancelar) y habilitar tabla (Insertar, Editar, Eliminar)
                this.activarFicha(false);
                this.btnInsertar.setEnabled(true);
                this.btnImprimir.setEnabled(true);
                this.tSocios.setEnabled(true);
                this.tSocios.requestFocus();
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
        tSocios = new javax.swing.JTable();
        btnInsertar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnBorrar = new javax.swing.JButton();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        lblId = new javax.swing.JLabel();
        lblIdSocio = new javax.swing.JLabel();
        lblDNI = new javax.swing.JLabel();
        txtDNI = new javax.swing.JTextField();
        lblNombre = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        lblApellidos = new javax.swing.JLabel();
        txtApellidos = new javax.swing.JTextField();
        lblDireccion = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        lblFechaAlta = new javax.swing.JLabel();
        dtcFechaAlta = new com.toedter.calendar.JDateChooser();
        panNulo = new javax.swing.JPanel();
        btnImprimir = new javax.swing.JButton();

        getContentPane().setLayout(new java.awt.GridBagLayout());

        tSocios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sel", "Id", "DNI", "Nombre", "Apellidos", "Dirección", "Alta"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tSocios.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tSocios);

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

        lblIdSocio.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblIdSocio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIdSocio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        lblIdSocio.setMaximumSize(new java.awt.Dimension(60, 20));
        lblIdSocio.setMinimumSize(new java.awt.Dimension(20, 20));
        lblIdSocio.setPreferredSize(new java.awt.Dimension(60, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 4, 2, 2);
        getContentPane().add(lblIdSocio, gridBagConstraints);

        lblDNI.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDNI.setText(Messages.getString("ETQ_DNI")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        getContentPane().add(lblDNI, gridBagConstraints);

        txtDNI.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtDNI.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        getContentPane().add(txtDNI, gridBagConstraints);

        lblNombre.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNombre.setText(Messages.getString("ETQ_NOMBRE")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        getContentPane().add(lblNombre, gridBagConstraints);

        txtNombre.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtNombre.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        getContentPane().add(txtNombre, gridBagConstraints);

        lblApellidos.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblApellidos.setText(Messages.getString("ETQ_APELLIDOS")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        getContentPane().add(lblApellidos, gridBagConstraints);

        txtApellidos.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtApellidos.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        getContentPane().add(txtApellidos, gridBagConstraints);

        lblDireccion.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDireccion.setText(Messages.getString("ETQ_DIRECCION")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        getContentPane().add(lblDireccion, gridBagConstraints);

        txtDireccion.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtDireccion.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        getContentPane().add(txtDireccion, gridBagConstraints);

        lblFechaAlta.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblFechaAlta.setText(Messages.getString("ETQ_FECHA_ALTA")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        getContentPane().add(lblFechaAlta, gridBagConstraints);

        dtcFechaAlta.setDateFormatString(Constantes.FORMATO_FECHA);
        dtcFechaAlta.setEnabled(false);
        dtcFechaAlta.setMaximumSize(new java.awt.Dimension(130, 25));
        dtcFechaAlta.setMinimumSize(new java.awt.Dimension(130, 25));
        dtcFechaAlta.setPreferredSize(new java.awt.Dimension(130, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        getContentPane().add(dtcFechaAlta, gridBagConstraints);

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
    private com.toedter.calendar.JDateChooser dtcFechaAlta;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblApellidos;
    private javax.swing.JLabel lblDNI;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblFechaAlta;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblIdSocio;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JPanel panNulo;
    private javax.swing.JTable tSocios;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtDNI;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
