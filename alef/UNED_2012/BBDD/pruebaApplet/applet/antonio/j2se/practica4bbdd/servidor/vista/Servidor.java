package antonio.j2se.practica4bbdd.servidor.vista;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import antonio.j2se.practica4bbdd.excepciones.ErrorComunicacionNegocioException;
import antonio.j2se.practica4bbdd.excepciones.SQLNegocioException;
import antonio.j2se.practica4bbdd.servidor.red.ServidorBBDD;

public class Servidor extends JFrame {
	private static final long serialVersionUID = 2213926710573235818L;
    private JTextArea consola;
    private JLabel etiquetaServidor;
    private JLabel etiquetaConsola;
    private JTextField puerto;
    private JButton botonArrancar;
    private JButton botonParar;
    private JScrollPane scrollTestAreaConsola;
    private ServidorBBDD server;


	

    public static void main(String[] args) {
    	SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Servidor();
			}
		});
	}
    
    public Servidor(){
    	inicializaGUI();
    	server=new ServidorBBDD(this);
    }
	
	private void inicializaGUI(){
		etiquetaServidor=new JLabel();
		etiquetaConsola=new JLabel();
		consola=new JTextArea(15,60);
		puerto=new JTextField(3);
		botonArrancar=new JButton();
		botonParar=new JButton();
		scrollTestAreaConsola=new JScrollPane();
		
		etiquetaServidor.setText("Introduzca Puerto de escucha :");
		etiquetaServidor.setFont(new Font("Times-Roman", Font.BOLD + Font.ITALIC, 12));
		GridBagConstraints gridConstraintsES = new GridBagConstraints();
		gridConstraintsES.fill=GridBagConstraints.HORIZONTAL;
		gridConstraintsES.gridwidth=1;
		gridConstraintsES.anchor=GridBagConstraints.EAST;
		gridConstraintsES.gridx=0;
		gridConstraintsES.gridy=0;

		puerto.setEditable(Boolean.TRUE);
		GridBagConstraints gridConstraintsTP = new GridBagConstraints();
		gridConstraintsTP.fill=GridBagConstraints.HORIZONTAL;
		gridConstraintsTP.gridwidth=GridBagConstraints.REMAINDER;
		gridConstraintsTP.anchor=GridBagConstraints.CENTER;
		gridConstraintsTP.gridx=1;
		gridConstraintsTP.gridy=0;
		
		etiquetaConsola.setText("Consola de Servidor :");
		etiquetaConsola.setFont(new Font("Times-Roman", Font.BOLD + Font.ITALIC, 12));
		GridBagConstraints gridConstraintsEC = new GridBagConstraints();
		gridConstraintsEC.fill=GridBagConstraints.HORIZONTAL;
		gridConstraintsEC.gridwidth=GridBagConstraints.REMAINDER;
		gridConstraintsEC.anchor=GridBagConstraints.EAST;
		gridConstraintsEC.gridx=0;
		gridConstraintsEC.gridy=1;
		
		consola.setEditable(Boolean.FALSE);
		consola.setText(" ");
		scrollTestAreaConsola.setViewportView(consola);
		scrollTestAreaConsola.setAutoscrolls(Boolean.TRUE);
		GridBagConstraints gridConstraintsTAC = new GridBagConstraints();
		gridConstraintsTAC.fill=GridBagConstraints.BOTH;
		gridConstraintsTAC.gridwidth=GridBagConstraints.REMAINDER;
		gridConstraintsTAC.gridheight=3;
		gridConstraintsTAC.anchor=GridBagConstraints.CENTER;
		gridConstraintsTAC.gridx=0;
		gridConstraintsTAC.gridy=2;

		botonArrancar.setText("Arrancar Servidor");
		botonArrancar.setToolTipText("Arranca el Servidor en el puerto introducido");
		botonArrancar.setMnemonic(KeyEvent.VK_A);
		GridBagConstraints gridConstraintsBA = new GridBagConstraints();
		gridConstraintsBA.fill=GridBagConstraints.BOTH;
		gridConstraintsBA.gridwidth=1;
		gridConstraintsBA.anchor=GridBagConstraints.EAST;
		gridConstraintsBA.gridx=0;
		gridConstraintsBA.gridy=6;

		botonParar.setText("Parar Servidor");
		botonParar.setToolTipText("Para el Servidor");
		botonParar.setMnemonic(KeyEvent.VK_P);
		GridBagConstraints gridConstraintsBP = new GridBagConstraints();
		gridConstraintsBP.fill=GridBagConstraints.BOTH;
		gridConstraintsBP.gridwidth=1;
		gridConstraintsBP.anchor=GridBagConstraints.WEST;
		gridConstraintsBP.gridx=2;
		gridConstraintsBP.gridy=6;

		getContentPane().setLayout(new GridBagLayout());
		getContentPane().add(etiquetaServidor,gridConstraintsES);
		getContentPane().add(puerto,gridConstraintsTP);
		getContentPane().add(etiquetaConsola,gridConstraintsEC);
		getContentPane().add(scrollTestAreaConsola,gridConstraintsTAC);
		getContentPane().add(botonArrancar,gridConstraintsBA);
		getContentPane().add(botonParar,gridConstraintsBP);
		
		this.setResizable(Boolean.FALSE);
		this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		this.setSize(800, 500);
        this.setTitle("Servidor BBDD");
        this.setLocationByPlatform(Boolean.TRUE);
        
        ActionListener listenerBotonArrancar=new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!puerto.getText().trim().equals("")){
					if(esNumero(puerto.getText().trim())){
					   //Arrancamos el servidor en el puerto indicado
					   try{	
						   if (!ServidorBBDD.isPuertoEnUso(Integer.parseInt(puerto.getText().trim()))){
							   server.iniciarServidorBBDD(Integer.parseInt(puerto.getText().trim()));
							   JOptionPane.showMessageDialog(null, "Servidor Iniciado","Mensaje",JOptionPane.INFORMATION_MESSAGE);
							   puerto.setEditable(Boolean.FALSE);
						   }else
							   JOptionPane.showMessageDialog(null, "Servidor Ya Arrancado en puerto indicado,parelo antes de volverlo a iniciar","ERROR",JOptionPane.ERROR_MESSAGE); 
					   }catch(NumberFormatException nfe){
						   JOptionPane.showMessageDialog(null, "El puerto introducido debe ser numerico","Error",JOptionPane.ERROR_MESSAGE);   
					   }
					}else{
						JOptionPane.showMessageDialog(null, "El puerto introducido debe ser numerico","Error",JOptionPane.ERROR_MESSAGE);
						puerto.setText("");
					}	
				}else //Mensaje de error indicando que introduzca un puerto
					JOptionPane.showMessageDialog(null, "Necesario Introducir un puerto","Error",JOptionPane.ERROR_MESSAGE); 
			}
		};
		ActionListener listenerBotonParar=new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			 
				if( !puerto.getText().trim().equals("") && ServidorBBDD.isPuertoEnUso(Integer.parseInt(puerto.getText().trim()))){
					server.noAtenderConexiones();
					puerto.setEditable(Boolean.TRUE);
					puerto.setText("");
					puerto.requestFocusInWindow();
					JOptionPane.showMessageDialog(null, "Servidor Parado","Mensaje",JOptionPane.INFORMATION_MESSAGE);
				}else
					JOptionPane.showMessageDialog(null, "Necesario tener arrancado el Servidor","Error",JOptionPane.ERROR_MESSAGE);
			  
			}
		};
        
		//Listener para capturar el intento de cierre de ventana,pedir confirmacion y en caso afirmativo liberar recursos
		WindowListener listenerCerrarVentana=new WindowListener() {
			public void windowOpened(WindowEvent arg0) {}
			public void windowIconified(WindowEvent arg0) {}
			public void windowDeiconified(WindowEvent arg0) {}
			public void windowDeactivated(WindowEvent arg0) {}
			public void windowClosing(WindowEvent arg0) {
			  try{	
				JOptionPane.showMessageDialog(null, "Se va a cerrar el Servidor","Aviso",JOptionPane.INFORMATION_MESSAGE);
				if(server.getPuerto()!=-99 && ServidorBBDD.isPuertoEnUso(server.getPuerto())){
					System.out.println("Antes de liberar recursos,paramos el servidor");
					server.noAtenderConexiones();
				}
				server.liberarRecursos();
				dispose();
				System.exit(0);
			  }catch(SQLNegocioException sqle){
				  sqle.printStackTrace();
				  JOptionPane.showMessageDialog(null, sqle.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			  }
			}
			public void windowClosed(WindowEvent arg0) {}
			public void windowActivated(WindowEvent arg0) {}
		};
		//Listener para detectar la pulsacion de teclas
		//Si se pulsa Enter se arranca el servidor
		KeyListener listenerTeclado=new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}
			public void keyReleased(KeyEvent e) {
			}
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==10)//Pulsado Enter
					botonArrancar.doClick();

			}
		};

		puerto.addKeyListener(listenerTeclado);
        botonArrancar.addActionListener(listenerBotonArrancar);
		botonParar.addActionListener(listenerBotonParar);
		this.addWindowListener(listenerCerrarVentana);
		pack();
		setVisible(Boolean.TRUE);
	}
	
	 /**
	  * Utilizado para mostrar por la consola del GUI el texto
	  * @param texto
	  */
	 public void setConsola(String texto){
		 SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		 long fechaActual=System.currentTimeMillis();
		 consola.setText(sdf.format(fechaActual)+"<---->"+texto.trim()+"\n"+consola.getText().trim());
     }
	 
	 /**
	  * Indica si un String es numerico o no
	  * @param dato
	  * @return
	  */
	 private Boolean esNumero(String dato){
		 Boolean retorno=Boolean.TRUE;
		 try{
			 Integer.parseInt(dato.trim());
			 retorno=Boolean.TRUE;
		 }catch (NumberFormatException nfe){
			 retorno=Boolean.FALSE;
		 }finally{
			 return retorno;
		 }
	 }
	 
}
