package es.uned.master.java.healthworldbank.servidor;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * Interfaz gr�fica del servidor
 * 
 * @author grupo alef (Juan S�nchez)
 *
 */
public class ServidorGUI implements ReceptorActualizacionServidor {
	// Declara todos los componentes de la interfaz
	private JFrame frmVentana;			// ventana 
	private JPanel pnlGeneral;			// panel general
	private JPanel pnlBotones;			// panel de botones
	private JLabel lblTitulo;			// t�tulo
									
	private JTextArea taNotificaciones;		// �rea de notificaciones
	private JScrollPane spNotificaciones;	// panel de scroll de notificaciones
	private JButton btnIniciar;				// bot�n iniciar
	private JButton btnParar;				// bot�n parar

	private Servidor servidor;				// servidor
	private ServidorGUI gui;				// referencia al GUI (para clases internas an�nimas)
	
	/**
	 * Constructor de clase. Crea todos los componentes y los a�ade al marco mediante 
	 * la combinaci�n de BorderLayout (panel general) y GridBagLayout (paneles de las pesta�as) 
	 */
	ServidorGUI() {
		gui = this;
		/*
		 * Crea el panel general
		 */
		pnlGeneral = new JPanel();
		pnlGeneral.setLayout(new BorderLayout()); 			// define su administrador de contenido 
		
		/* 
		 * Crea el r�tulo del t�tulo y lo ubica en la zona
		 */
		lblTitulo = new JLabel("Servidor World Health Bank");
        lblTitulo.setBackground(new Color(255,204,0));				// color de fondo
        lblTitulo.setFont(new Font("Tahoma", 1, 14)); 				// fuente y tama�o
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);	// alineaci�n
        lblTitulo.setOpaque(true);									// opacidad
		lblTitulo.setPreferredSize(new Dimension(500,25));			// dimensiones preferidas

		pnlGeneral.add(lblTitulo, BorderLayout.NORTH);				// a�ade el r�tulo a la zona superior

		taNotificaciones = new JTextArea();							// �rea de texto notificaciones
		taNotificaciones.setLineWrap(true);							// para que parta las l�neas que no caben en el control
		taNotificaciones.setEditable(false);						// no es editable por el usuario
        spNotificaciones = new JScrollPane(taNotificaciones);		// le a�ade un scroll
        /* 
		 * Se A�ade un mensaje inicial al panel del servidor
		 */
        setNotificaciones("Pulsar \"Inciar servidor\" para comenzar...");

		spNotificaciones.setPreferredSize(new Dimension(100,140));	// dimensiones preferidas
		spNotificaciones.setMinimumSize(new Dimension(100,140));	// dimensiones m�nimas
		pnlGeneral.add(spNotificaciones, BorderLayout.CENTER);

		
		pnlBotones = new JPanel(new GridLayout(1, 2, 10, 10));		// panel de los botones
        btnIniciar = new JButton("Iniciar servidor");				// bot�n iniciar servidor
        btnIniciar.setHorizontalTextPosition(SwingConstants.LEFT);

		btnIniciar.addActionListener( 								// escuchador del bot�n iniciar servidor
			new ActionListener() {									// clase ActionListener
				public void actionPerformed(ActionEvent e) {		// m�todo actionPerformed
					
					if (servidor == null) {							// no se ha arrancado nunca, 
						servidor = new Servidor(gui);				// lo crea
					}
					servidor.arrancarServidor(); 					// ahora lo arranca
					System.out.println("Se ha solicitado el arranque del servidor");
					btnIniciar.setEnabled(false);					// deshabilita el bot�n de iniciar
					btnParar.setEnabled(true);						// habilita el bot�n de parar
				} // fin de m�todo actioPerformed
			} // fin de clase interna an�nima
		); // fin de llamada a addActionListener
		pnlBotones.add(btnIniciar);									// a�ade el bot�n al panel

		btnParar = new JButton("Parar servidor");					// bot�n parar servidor
        btnParar.setHorizontalTextPosition(SwingConstants.CENTER);
        btnParar.setEnabled(false);									// el bot�n est� inhabilitado de entrada
        
		btnParar.addActionListener( 								// se le a�ade un escuchador
			new ActionListener() {									// nueva clase
				public void actionPerformed(ActionEvent e) {		// m�todo actionPerformed
					if (servidor != null) {							// si el servidor existe
						servidor.pararServidor();					// lo para
					}
					btnIniciar.setEnabled(true);					// habilita el bot�n de arrancar
					btnParar.setEnabled(false);						// inhabilita el de parar
				} // fin de m�todo actionPerformed
			} // fin de clase interna an�nima
		); // fin de llamada a addActionListener
		pnlBotones.add(btnParar);									// a�ade el bot�n al panel

		pnlGeneral.add(pnlBotones, BorderLayout.SOUTH);				// a�ade el panel de botones al panel general 

		/**
		 * Crea el marco, define su panel de contenido y muestra todo
		 */
		frmVentana = new JFrame("Servidor WHB");					// marco de la ventana
		frmVentana.addWindowListener(								// listener de ventana para que se cierre la aplicaci�n
				new WindowAdapter() {		 						// crea clase
				public void windowClosing(WindowEvent e) {			// al cerrar la ventana
					if (servidor != null) {							// si existe el servidor
						servidor.pararServidor();					// lo para
					}
					System.out.println("Fin de programa");
					System.exit(0);									// sale de la aplicaci�n
				} // fin de m�todo windowClosing
			} // fin de clase interna an�nima
		); // fin de llamada a addWindowListener
		
		frmVentana.setSize(500,500);					// tama�o de la ventana
		frmVentana.setContentPane(pnlGeneral);			// asignaci�n del contenido
		frmVentana.setVisible(true);					// muestra la ventana		
	} // fin de constructor
	

	/**
	 * punto de entrada al programa
	 * 
	 * @param args 	no se utilizan
	 */
	public static void main(String[] args) {
		new ServidorGUI();				// crea una instancia del servidor
	}

	/**
	 * setter para �rea de notificaciones
	 * 
	 * @param texto a mostrar
	 */
	public void setNotificaciones(String texto) {
		taNotificaciones.setText(texto);
	}
	
	/**
	 * m�todo sobreescrito del interface que actualiza la informaci�n eviada desde el servidor
	 * 
	 * @params servidor 
	 */
	@Override
	public void estadoServidorActualizado(Servidor servidor) {
		final String BR = (taNotificaciones.getText().equals("")) ? "" : "\n";					// por si hay que insertar un retorno de carro		
		if (servidor.servidorArrancado()) {														// si est� arrancado
			taNotificaciones.setText(taNotificaciones.getText() + BR + "\n******\nServidor arrancado\n******");
		} else {																				// ... y si no
			taNotificaciones.setText(taNotificaciones.getText() + BR + "\n******\nServidor detenido\n******");				
		}
        taNotificaciones.setCaretPosition(taNotificaciones.getText().length());					// asegura de que se vea en el �re de texto el �ltimo mensaje
	}
	
	public void estadoServidorActualizado(String mensajeServer) {
		final String BR = (taNotificaciones.getText().equals("")) ? "" : "\n";					// por si hay que insertar un retorno de carro		
		taNotificaciones.setText(taNotificaciones.getText() + BR + mensajeServer);
		taNotificaciones.setCaretPosition(taNotificaciones.getText().length());					// asegura de que se vea en el �re de texto el �ltimo mensaje
	}
}
