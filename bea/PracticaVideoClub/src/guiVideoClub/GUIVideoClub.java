/*
 * GUIVideoClub.java
 *
 * Created on 27 de marzo de 2007, 17:58
 */
package guiVideoClub;

import java.awt.*;
import java.awt.event.*;

public class GUIVideoClub extends javax.swing.JFrame {
    private javax.swing.JMenu clientes;
    private javax.swing.JMenuItem devolucion;
    private javax.swing.JMenuItem eliminarCliente;
    private javax.swing.JMenuItem eliminarPelicula;
    private javax.swing.JMenu gestionVideoclub;
    private javax.swing.JMenuItem infoClientes;
    private javax.swing.JMenuItem infoPeliculas;
    private javax.swing.JMenu informacion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JMenuBar menu;
    private javax.swing.JMenuItem nuevaPelicula;
    private javax.swing.JMenuItem nuevoCliente;
    private javax.swing.JMenu peliculas;
    private javax.swing.JMenuItem prestamo;
    private javax.swing.JMenuItem salir;
    //private javax.swing.JMenuItem nada;
   /// private javax.swing.JMenuItem nada1;
    //private javax.swing.JMenuItem nada2;
    //private javax.swing.JMenuItem nada3;
    /** Creates new form GUIVideoClub */
    public GUIVideoClub() {
        initComponents();
        //this.setVisible(true) ;
    }
    
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        menu = new javax.swing.JMenuBar();
        gestionVideoclub = new javax.swing.JMenu();
        //nada = new javax.swing.JMenuItem();
        prestamo = new javax.swing.JMenuItem();
        devolucion = new javax.swing.JMenuItem();
        salir = new javax.swing.JMenuItem();
        
        
        clientes = new javax.swing.JMenu();
        
        /*nada = new javax.swing.JMenuItem();
        nada1 = new javax.swing.JMenuItem();
        nada2 = new javax.swing.JMenuItem();
        nada3 = new javax.swing.JMenuItem();
         */        
        nuevoCliente = new javax.swing.JMenuItem();
        eliminarCliente = new javax.swing.JMenuItem();
        
        peliculas = new javax.swing.JMenu();
       
        nuevaPelicula = new javax.swing.JMenuItem();
        eliminarPelicula = new javax.swing.JMenuItem();
        
        informacion = new javax.swing.JMenu();
       
        infoClientes = new javax.swing.JMenuItem();
        infoPeliculas = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("VideoClub Grupo Pi");
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.setBackground(new java.awt.Color(2, 89, 117));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon("/home/user/workspace_antonio/PracticaVideoClub/src/imagenes/l2.jpg"));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(168, 168, 168)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(182, 182, 182))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(117, 117, 117)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(141, 141, 141))
        );

        menu.setBackground(new java.awt.Color(213, 223, 233));
        menu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        menu.setPreferredSize(new java.awt.Dimension(2, 30));
        
        gestionVideoclub.setBackground(new java.awt.Color(223, 223, 233));
        gestionVideoclub.setIcon(new javax.swing.ImageIcon("/home/user/workspace_antonio/PracticaVideoClub/src/imagenes/bricks.gif"));
        gestionVideoclub.setText("Gesti\u00f3n VideoClub");
        gestionVideoclub.setMargin(new java.awt.Insets(2, 20, 2, 20));
        
        prestamo.setBackground(new java.awt.Color(213, 223, 233));
        prestamo.setText("Pr\u00e9stamo");
        prestamo.setMargin(new java.awt.Insets(2, 20, 2, 20));
        prestamo.setPreferredSize(new java.awt.Dimension(130, 20));
        
        //gestionVideoclub.add(nada);
        gestionVideoclub.add(prestamo);

        devolucion.setBackground(new java.awt.Color(213, 223, 233));
        devolucion.setText("Devoluci\u00f3n");
        devolucion.setMargin(new java.awt.Insets(2, 20, 2, 20));
        devolucion.setPreferredSize(new java.awt.Dimension(100, 20));
        
        gestionVideoclub.add(devolucion);

        salir.setBackground(new java.awt.Color(213, 223, 233));
        salir.setText("Salir");
        salir.setMargin(new java.awt.Insets(2, 20, 2, 20));
        salir.setPreferredSize(new java.awt.Dimension(100, 20));
        //salir.addActionListener(new java.awt.event.ActionListener() {
        //    public void actionPerformed(java.awt.event.ActionEvent evt) {
        //        salir.ActionPerformed(evt);
        //    }
        //});

        gestionVideoclub.add(salir);

        menu.add(gestionVideoclub);

        clientes.setBackground(new java.awt.Color(213, 223, 233));
        clientes.setIcon(new javax.swing.ImageIcon("C:\\Documents and Settings\\sergio\\Escritorio\\VideoClub\\imagenes\\folder_user.gif"));
        clientes.setText("Clientes");
        clientes.setMargin(new java.awt.Insets(2, 20, 2, 20));
        nuevoCliente.setBackground(new java.awt.Color(213, 223, 233));
        nuevoCliente.setText("Nuevo Cliente");
       // clientes.add(nada1);
        clientes.add(nuevoCliente);

        eliminarCliente.setBackground(new java.awt.Color(213, 223, 233));
        eliminarCliente.setText("Eliminar Cliente");
        clientes.add(eliminarCliente);

        menu.add(clientes);

        peliculas.setBackground(new java.awt.Color(223, 223, 233));
        peliculas.setIcon(new javax.swing.ImageIcon("C:\\Documents and Settings\\sergio\\Escritorio\\VideoClub\\imagenes\\cd_add.gif"));
        peliculas.setText("Pel\u00edculas");
        peliculas.setMargin(new java.awt.Insets(2, 20, 2, 20));
        nuevaPelicula.setBackground(new java.awt.Color(213, 223, 233));
        nuevaPelicula.setText("Nueva Pelicula");
        //peliculas.add(nada2);
        peliculas.add(nuevaPelicula);

        eliminarPelicula.setBackground(new java.awt.Color(213, 223, 233));
        eliminarPelicula.setText("Eliminar Pelicula");
        peliculas.add(eliminarPelicula);

        menu.add(peliculas);

        informacion.setBackground(new java.awt.Color(213, 223, 233));
        informacion.setIcon(new javax.swing.ImageIcon("C:\\Documents and Settings\\sergio\\Escritorio\\VideoClub\\imagenes\\feed.gif"));
        informacion.setText("Informaci\u00f3n");
        informacion.setMargin(new java.awt.Insets(2, 20, 2, 20));
        infoClientes.setBackground(new java.awt.Color(223, 223, 233));
        infoClientes.setText("Informacion Clientes");
        //informacion.add(nada3);
        informacion.add(infoClientes);

        infoPeliculas.setBackground(new java.awt.Color(213, 223, 233));
        infoPeliculas.setText("Informacion Peliculas");
        informacion.add(infoPeliculas);

        menu.add(informacion);

        setJMenuBar(menu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pack();
    }
    
    
    public void controlador(ActionListener crt) {
    	prestamo.addActionListener(crt);
    	prestamo.setActionCommand("PRESTAMO");
	
    	devolucion.addActionListener(crt);
    	devolucion.setActionCommand("DEVOLUCION");
	
    	salir.addActionListener(crt);
    	salir.setActionCommand("SALIR");
        
    	nuevoCliente.addActionListener(crt);
    	nuevoCliente.setActionCommand("NUEVOCLIENTE");
	
    	eliminarCliente.addActionListener(crt);
    	eliminarCliente.setActionCommand("ELIMINARCLIENTE");
        
    	nuevaPelicula.addActionListener(crt);
    	nuevaPelicula.setActionCommand("NUEVAPELICULA");
	
    	eliminarPelicula.addActionListener(crt);
    	eliminarPelicula.setActionCommand("ELIMINARPELICULA");
        
    	infoClientes.addActionListener(crt);
    	infoClientes.setActionCommand("INFOCLIENTES");
	
    	infoPeliculas.addActionListener(crt);
    	infoPeliculas.setActionCommand("INFOPELICULAS");
    }
}