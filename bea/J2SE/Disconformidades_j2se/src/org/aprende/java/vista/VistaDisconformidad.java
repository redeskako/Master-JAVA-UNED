
package org.aprende.java.vista;
import java.io.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.applet.*;
import org.aprende.java.bbdd.*;
/**
 *
 * @ Autor: Jesús M. Tejado
 */
public class VistaDisconformidad extends javax.swing.JFrame {
    
    /** Crea nuevo formaulario con PantallaDDBB()*/
    public VistaDisconformidad() {
        initComponents();
    }
    //***************************
    
    
    
    public void asocia(ActionListener a){
    	this.btnAnterior.addActionListener(a);
    	this.btnAnterior.setActionCommand("Anterior");
    	this.btnSalir.addActionListener(a);
    	this.btnSalir.setActionCommand("Salir");
    	this.btnUltimo.addActionListener(a);
    	this.btnUltimo.setActionCommand("Ultimo");
    	this.btnSiguiente.addActionListener(a);
    	this.btnSiguiente.setActionCommand("Siguiente");
    	this.btnInicio.addActionListener(a); 
    	this.btnInicio.setActionCommand("Inicio");
    	this.btnAnterior.addActionListener(a); 
    	this.btnAnterior.setActionCommand("Anterior");
    	this.btnEditar.addActionListener(a);
    	this.btnEditar.setActionCommand("Editar");
    	this.btnNuevo.addActionListener(a);
    	this.btnNuevo.setActionCommand("Nuevo");
    	this.btnEliminar.addActionListener(a);
    	this.btnEliminar.setActionCommand("eliminar");
    }

    
    
    public void rellenaUsuario(Usuarios usu){
    	this.Fecha_d.setText("");
    }
    
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">                          
    private void initComponents() {
        btnSalir = new javax.swing.JButton();
        btnUltimo = new javax.swing.JButton();
        btnSiguiente = new javax.swing.JButton();
        btnInicio = new javax.swing.JButton();
        btnAnterior = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        documentosafectados = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        servicioorigen = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        Fecha_d = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        motivo = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        comentario = new javax.swing.JTextField();
        devolucionsi_no = new javax.swing.JCheckBox();
        menuBar = new javax.swing.JMenuBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 204, 102));
        setFocusableWindowState(false);
        setForeground(new java.awt.Color(204, 255, 0));
        btnSalir.setText("Salir");
        btnSalir.getAccessibleContext().setAccessibleName("BSalir");

        btnUltimo.setIcon(new javax.swing.ImageIcon("/home/user/prueba_svn/Prueba2/Iconos/ultimo.gif"));
        btnUltimo.setActionCommand("Ultimo");
        btnUltimo.getAccessibleContext().setAccessibleName("Fin");

        btnSiguiente.setIcon(new javax.swing.ImageIcon("/home/user/prueba_svn/Prueba2/Iconos/siguietne.gif"));
        btnSiguiente.setActionCommand("Siguiente");
        btnSiguiente.getAccessibleContext().setAccessibleName("Siguiente");

        btnInicio.setIcon(new javax.swing.ImageIcon("/home/user/prueba_svn/Prueba2/Iconos/primero.gif"));
        btnInicio.setActionCommand("Primer");
        btnInicio.getAccessibleContext().setAccessibleName("Anterior");

        btnAnterior.setIcon(new javax.swing.ImageIcon("/home/user/prueba_svn/Prueba2/Iconos/anterior.gif"));
        btnAnterior.setActionCommand("Anterior");

        btnEditar.setText("Editar");
        btnEditar.setName("Editar");

        btnNuevo.setText("Nuevo");

        btnEliminar.setText("Eliminar");

        jLabel2.setText("Documentos");

        jLabel3.setFont(new java.awt.Font("AlMothnna-Bold", 1, 36));
        jLabel3.setText("Alef-Work Group");

        jLabel1.setText("Numero");

        jLabel4.setText("Ver: 1.0.0");

        servicioorigen.setForeground(new java.awt.Color(255, 255, 255));

        jLabel6.setText("Motivo");

        jLabel7.setText("Comentario");

        devolucionsi_no.setText("Devolucion");
        devolucionsi_no.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        devolucionsi_no.setMargin(new java.awt.Insets(0, 0, 0, 0));

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnEditar, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminar)
                        .addGap(21, 21, 21)
                        .addComponent(btnInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUltimo, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(btnSalir)
                        .addGap(8, 8, 8))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel1))
                                .addGap(69, 69, 69)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(419, 419, 419)
                                        .addComponent(jLabel4))
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(motivo, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addComponent(Fecha_d, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(28, 28, 28)
                                                .addComponent(servicioorigen, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(22, 22, 22)
                                                .addComponent(devolucionsi_no))
                                            .addComponent(documentosafectados, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 476, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(comentario, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addComponent(jLabel7))))
                .addGap(208, 208, 208))
            .addGroup(layout.createSequentialGroup()
                .addGap(180, 180, 180)
                .addComponent(jLabel5)
                .addContainerGap(642, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addContainerGap(482, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel1)
                            .addComponent(Fecha_d, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(servicioorigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(devolucionsi_no)))
                    .addComponent(jLabel4))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(motivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(documentosafectados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(comentario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnEditar)
                            .addComponent(btnNuevo)
                            .addComponent(btnEliminar)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnInicio)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnAnterior)
                                .addComponent(btnUltimo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnSiguiente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnSalir, javax.swing.GroupLayout.Alignment.TRAILING)))))
                .addGap(84, 84, 84))
        );
        pack();
    }// </editor-fold>                        
        
    //***********************

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaDisconformidad().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify                     
    private javax.swing.JTextField Fecha_d;
    private javax.swing.JButton btnAnterior;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnInicio;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnSiguiente;
    private javax.swing.JButton btnUltimo;
    private javax.swing.JTextField comentario;
    private javax.swing.JCheckBox devolucionsi_no;
    private javax.swing.JTextField documentosafectados;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JTextField motivo;
    private javax.swing.JComboBox servicioorigen;
    // final de declaraciones de variable                   
}
