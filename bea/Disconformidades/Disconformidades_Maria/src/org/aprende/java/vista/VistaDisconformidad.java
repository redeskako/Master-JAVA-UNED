
package org.aprende.java.vista;
import java.io.*;
import java.text.SimpleDateFormat;
import java.text.ParsePosition;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;

import javax.swing.JOptionPane;

import org.aprende.java.bbdd.*;
/**
 *
 * @ Autor: Jesus M. Tejado
 */
public class VistaDisconformidad extends javax.swing.JFrame {
    
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
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JTextField comentario;
    private javax.swing.JCheckBox devolucionsi_no;
    private javax.swing.JTextField documentosafectados;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
   // private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    
    private javax.swing.JLabel lblEstado;//etiqueta que indica en que estado se encuentra del formulario
    
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JTextField motivo;
    private javax.swing.JComboBox servicioorigen;
    private javax.swing.JComboBox usuario;
    
    private Servicios listaServicios;
    private Usuarios listaUsuarios;
    // final de declaraciones de variable    
    
    
    
    /** Constructor de la ventana */
    public VistaDisconformidad() {
        initComponents();
        this.setTitle("Editor de Disconformidades");
        this.setVisible(true);
    }
    //***************************
    
    
    //metodo que asocia un listener a cada boton
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
    	this.btnEliminar.setActionCommand("Eliminar");
    	this.btnAceptar.addActionListener(a);
    	this.btnAceptar.setActionCommand("Aceptar");
    	this.btnCancelar.addActionListener(a);
    	this.btnCancelar.setActionCommand("Cancelar");
    	this.servicioorigen.addActionListener(a);
    	this.servicioorigen.setActionCommand("Servicio");
    	this.usuario.addActionListener(a);
    	this.usuario.setActionCommand("Usuario");
    }
   
    //metodo que establece los botones visibles de acuerdo al estado que se le pasa como parametro
    public  void estableceBotonesVisibles(int estado){
		
		if (estado == org.aprende.java.controlador.Controlador.EDICION){
			this.btnInicio.setVisible(false);
			this.btnAnterior.setVisible(false);
			this.btnSiguiente.setVisible(false);
			this.btnUltimo.setVisible(false);
			this.btnEliminar.setVisible(false);
			this.btnNuevo.setVisible(false);
			this.btnEditar.setVisible(false);
			this.btnSalir.setVisible(false);
			
			this.btnAceptar.setVisible(true);
			this.btnCancelar.setVisible(true);
	
		}else {  //estado consulta INICIAL
			this.btnInicio.setVisible(true);
			this.btnAnterior.setVisible(true);
			this.btnSiguiente.setVisible(true);
			this.btnUltimo.setVisible(true);
			this.btnEliminar.setVisible(true);
			this.btnNuevo.setVisible(true);
			this.btnEditar.setVisible(true);
			this.btnSalir.setVisible(true);
			
			this.btnAceptar.setVisible(false);
			this.btnCancelar.setVisible(false);
		}
		
	}
    
    
    //metodo que establece los botones visibles de acuerdo al estado que se le pasa como parametro
    private void estableceCamposActivos(int estado){
		
		if (estado == org.aprende.java.controlador.Controlador.EDICION){
			this.Fecha_d.setEditable(true);
			this.motivo.setEditable(true);
			this.documentosafectados.setEditable(true);
			this.devolucionsi_no.setEnabled(true);
			this.servicioorigen.setEnabled(true);
			this.usuario.setEnabled(true);
			this.comentario.setEditable(true);
			this.lblEstado.setText("ESTADO: Edición");
			
		}else {  //estado consulta , no puedo escribir
			this.Fecha_d.setEditable(false);
			this.motivo.setEditable(false);
			this.documentosafectados.setEditable(false);
			this.devolucionsi_no.setEnabled(false);
			this.servicioorigen.setEditable(false);
			this.servicioorigen.setEnabled(false);
			this.usuario.setEnabled(false);
			this.usuario.setEditable(false);
			this.comentario.setEditable(false);
			this.lblEstado.setText("ESTADO: Consulta");
		}
		
	}
    
    //establece los componentes del formulario que están activos y los que no dependiendo del estado
    public void estableceComponentesActivados(int estado){
 		estableceBotonesVisibles(estado);
		estableceCamposActivos(estado);
		
    }	
    //establece los botones desactivados al no haber disconformidades que tratar
    public void sinDisconformidades(){
    	this.btnEditar.setEnabled(false);
    	this.btnEliminar.setEnabled(false);
    	this.btnInicio.setEnabled(false);
		this.btnAnterior.setEnabled(false);
		this.btnSiguiente.setEnabled(false);
		this.btnUltimo.setEnabled(false);
    }
    
    //método que carga el combo de servicios con los valores de la base de datos
    public  void llenaComboServicios(){
    	this.servicioorigen.removeAllItems() ; //elimino lo que habia por si hay otros elementos nuevos cogerlos ahora.
       	listaServicios=new Servicios().allServicios();
		Iterator iteraServi=listaServicios.iterator();  //creo un iterador para recorrer el TreeSet
		while (iteraServi.hasNext()){  // recorro el TreeSet de Servicios
			String nombre=((Servicio)iteraServi.next()).servicio();  //obtengo el nombre del servicio en el que estoy en el Treeset
			this.servicioorigen.addItem(nombre.toString());  //añado el servicio al combo de servicios
		}
		this.servicioorigen.setEnabled(true);
    }
    
    //método que carga el combo de usuarios con los valores de la base de datos
    public void llenaComboUsuarios(){
    	this.usuario.removeAllItems(); // elimino lo que habia por si hay otros elementos nuevos cogerlos ahora
    	listaUsuarios=new Usuarios().allUsuarios();
		Iterator iteraUsu=listaUsuarios.iterator();  //creo un iterador para recorrer el TreeSet
		while (iteraUsu.hasNext()){  // recorro el TreeSet de Usuarios
			String nombre=((Usuario)iteraUsu.next()).nombre();  //obtengo el nombre del usuario en el que estoy en el Treeset
			this.usuario.addItem(nombre.toString());  //añado el usuario al combo de usuarios
		}
		this.usuario.setEnabled(true);
    }
    
    //establece los campos de disconformidad a vacios
    private void disconformidadVacia(){
    	this.Fecha_d.setText("");
    	this.setEnabled(true);
    	this.documentosafectados.setText("");
    	this.servicioorigen.setSelectedIndex(-1);
    	this.usuario.setSelectedIndex(-1);
    	this.devolucionsi_no.setSelected(false);
    	this.motivo.setText("");
    	this.comentario.setText("");
    }
    

   
    //rellena el formulario de acuerdo a la disconformidad pasada como parametro
    public void rellenaVistaDisconformidad(Disconformidad disconformidad){
    	llenaComboUsuarios();
    	llenaComboServicios();
		this.Fecha_d.setText(disconformidad.fecha());
		this.motivo.setText(disconformidad.motivo());
		this.documentosafectados.setText(disconformidad.docs());
		this.devolucionsi_no.setSelected(disconformidad.devolucion());
		this.servicioorigen.setSelectedIndex(disconformidad.servicio()); 
		this.comentario.setText(disconformidad.comentario()); 
		
		
	}
    //establece el formulario con datos vacios  
    public void establecerNuevaDisconformidad(){
    	llenaComboUsuarios();
    	llenaComboServicios();
    	disconformidadVacia();
    	this.lblEstado.setText("ESTADO:Edicion");
    	
    	
    }
    
    //método que comprueba el formato de los datos introducidos
    private boolean compruebaFormatoDatos(String fecha,int servicio,int idusuario,boolean devolucion){
        if (fecha.length()==0){  //la fecha debe ser obligatoria
        	return false;
        }else {
        	if ((idusuario == -1) || (servicio == -1)) {
        		JOptionPane.showMessageDialog(null, "No existe usuario o servicio seleccionado");
        		return false;
        	}
        	try {
        		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yy");
        		Date mifecha= formatoFecha.parse(fecha,new ParsePosition(0));
        		if (mifecha!=null) {
        			return true;
        		}else {
        			JOptionPane.showMessageDialog(null, "Formato de fecha incorrecto");
        			return false;
        		}
        	}catch (NullPointerException e1){
        		JOptionPane.showMessageDialog(null, e1.toString());
        		return false;
        	}catch (IllegalArgumentException e2){
        		JOptionPane.showMessageDialog(null, e2.toString());
        		return false;
        	}
        }
        	
        
    }
    
    
    //metodo que recoge los datos del formulario en un objeto disconformidad
    public Disconformidad recogerDisconformidad(){
    	boolean encontrado=false;
    	String fecha=this.Fecha_d.getText();
    	int idusuario,servicio;
    	
    	if ((this.usuario.getSelectedIndex())>=0){
    		idusuario = (Integer)this.usuario.getSelectedIndex();
    	}else{
    		idusuario=-1;
    	}
    	if (this.servicioorigen.getSelectedIndex()>=0){
    		servicio = (Integer)this.servicioorigen.getSelectedIndex();
    	}else{
    		servicio=-1;
    	}
      	boolean devolucion = this.devolucionsi_no.isSelected();
    	String comentario = this.comentario.getText();
    	String motivo = this.motivo.getText();
    	String docs= this.documentosafectados.getText();
    	
    	Disconformidad dis;
    	//if ( compruebaFormatoDatos(fecha,servicio,idusuario,devolucion)){   //esto no me funciona
    		dis= new Disconformidad(fecha,docs,servicio,idusuario,devolucion,motivo,comentario);
    	//}
    	return dis;
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
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        documentosafectados = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        
        servicioorigen = new javax.swing.JComboBox();
        usuario= new javax.swing.JComboBox();   
        
        lblEstado = new javax.swing.JLabel();
        Fecha_d = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        motivo = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        comentario = new javax.swing.JTextField();
        devolucionsi_no = new javax.swing.JCheckBox();
        menuBar = new javax.swing.JMenuBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 204, 102));
        setFocusableWindowState(true);  //por defecto estaba a false pero hay que ponerlo a true para que sus componentes cojan el foco
        setForeground(new java.awt.Color(204, 255, 0));
        btnSalir.setText("Salir");
        btnSalir.getAccessibleContext().setAccessibleName("BSalir");

        //btnUltimo.setIcon(new javax.swing.ImageIcon("/home/user/prueba_svn/Prueba2/Iconos/ultimo.gif"));
        btnUltimo.setText(">>");
        btnUltimo.setActionCommand("Ultimo");
        btnUltimo.getAccessibleContext().setAccessibleName("Fin");

       // btnSiguiente.setIcon(new javax.swing.ImageIcon("/home/user/prueba_svn/Prueba2/Iconos/siguietne.gif"));
        
        btnSiguiente.setText(">");
        btnSiguiente.setActionCommand("Siguiente");
        btnSiguiente.getAccessibleContext().setAccessibleName("Siguiente");

        //btnInicio.setIcon(new javax.swing.ImageIcon("/home/user/prueba_svn/Prueba2/Iconos/primero.gif"));
        btnInicio.setText("<<");
        btnInicio.setActionCommand("Primero");
        btnInicio.getAccessibleContext().setAccessibleName("Inicio");
       

        //btnAnterior.setIcon(new javax.swing.ImageIcon("/home/user/prueba_svn/Prueba2/Iconos/anterior.gif"));
        btnAnterior.setText("<");
        btnAnterior.setActionCommand("Anterior");
        btnAnterior.getAccessibleContext().setAccessibleName("Anterior");
      

        btnEditar.setText("Editar");
        btnEditar.setName("Editar");

        btnNuevo.setText("Nuevo");

        btnEliminar.setText("Eliminar");
        
        btnAceptar.setText("Aceptar");
     
        btnCancelar.setText("Cancelar");

        jLabel2.setText("Documentos");

        jLabel3.setFont(new java.awt.Font("AlMothnna-Bold", 1, 36));
        jLabel3.setText("Alef-Work Group");

        jLabel1.setText("Fecha");

        jLabel4.setText("Ver: 1.0.0");

        servicioorigen.setForeground(new java.awt.Color(255, 255, 255));

        jLabel6.setText("Motivo");

        jLabel7.setText("Comentario");
        
        lblEstado.setText("ESTADO:");
        


        devolucionsi_no.setText("Devolucion");
        devolucionsi_no.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        devolucionsi_no.setMargin(new java.awt.Insets(0, 0, 0, 0));

      

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
                        .addComponent(btnAceptar, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGap(21, 21, 21)
                        .addComponent(btnInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUltimo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
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
                                                .addGap(28, 28, 28)
                                                .addComponent(usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addComponent(lblEstado)
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
                            .addComponent(lblEstado)
                            .addComponent(jLabel1)
                            .addComponent(Fecha_d, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            //.addComponent(jLabel1)
                            .addComponent(servicioorigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                           // .addComponent(jLabel1)
                            .addComponent(usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                            .addComponent(btnAceptar)
                            .addComponent(btnCancelar)
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
        
      
     //metodos get de los botones 
	/*public javax.swing.JButton getBtnAnterior() {
		return btnAnterior;
	}
	public javax.swing.JButton getBtnEditar() {
		return btnEditar;
	}
	public javax.swing.JButton getBtnInicio() {
		return btnInicio;
	}
	public javax.swing.JButton getBtnSiguiente() {
		return btnSiguiente;
	}
	public javax.swing.JButton getBtnUltimo() {
		return btnUltimo;
	}
	public javax.swing.JButton getBtnAceptar() {
		return btnAceptar;
	}
	public javax.swing.JButton getBtnCancelar() {
		return btnCancelar;
	}
	public javax.swing.JButton getBtnEliminar() {
		return btnEliminar;
	}
	public javax.swing.JButton getBtnSalir() {
		return btnSalir;
	}
	public javax.swing.JButton getBtnNuevo() {
		return btnNuevo;
	}
	
	//metodos get y set de los campos del formulario 
	public javax.swing.JCheckBox devolucionsi_no() {
		return devolucionsi_no;
	}
	public void devolucionsi_no(javax.swing.JCheckBox devolucionsi_no) {
		this.devolucionsi_no = devolucionsi_no;
	}
	public javax.swing.JTextField documentosafectados() {
		return documentosafectados;
	}
	public void documentosafectados(javax.swing.JTextField documentosafectados) {
		this.documentosafectados = documentosafectados;
	}
	public javax.swing.JTextField fecha_d() {
		return Fecha_d;
	}
	public void fecha_d(javax.swing.JTextField fecha_d) {
		Fecha_d = fecha_d;
	}
	public javax.swing.JTextField motivo() {
		return motivo;
	}
	public void setMotivo(javax.swing.JTextField motivo) {
		this.motivo = motivo;
	}
	
	public void servicioorigen(javax.swing.JComboBox servicioorigen) {
		this.servicioorigen = servicioorigen;
	}
	public javax.swing.JComboBox servicioorigen() {
		return servicioorigen;
	}
	public javax.swing.JComboBox usuario() {
		return usuario;
	}
*/

}
