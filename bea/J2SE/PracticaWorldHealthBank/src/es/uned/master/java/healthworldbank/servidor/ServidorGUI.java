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
 * Interfaz gráfica del servidor
 * 
 * @author grupo alef (Juan Sánchez)
 *
 */
public class ServidorGUI implements ReceptorActualizacionServidor {
	// Declara todos los componentes de la interfaz
	private JFrame frmVentana;			// ventana 
	private JPanel pnlGeneral;			// panel general
	private JPanel pnlBotones;			// panel de botones
	private JLabel lblTitulo;			// título
									
	private JTextArea taNotificaciones;		// área de notificaciones
	private JScrollPane spNotificaciones;	// panel de scroll de notificaciones
	private JButton btnIniciar;				// botón iniciar
	private JButton btnParar;				// botón parar

	private Servidor servidor;				// servidor
	private ServidorGUI gui;				// referencia al GUI (para clases internas anónimas)
	
	/**
	 * Constructor de clase. Crea todos los componentes y los añade al marco mediante 
	 * la combinación de BorderLayout (panel general) y GridBagLayout (paneles de las pestañas) 
	 */
	ServidorGUI() {
		gui = this;
		/*
		 * Crea el panel general
		 */
		pnlGeneral = new JPanel();
		pnlGeneral.setLayout(new BorderLayout()); 			// define su administrador de contenido 
		
		/* 
		 * Crea el rótulo del título y lo ubica en la zona
		 */
		lblTitulo = new JLabel("Servidor World Health Bank");
        lblTitulo.setBackground(new Color(255,204,0));				// color de fondo
        lblTitulo.setFont(new Font("Tahoma", 1, 14)); 				// fuente y tamaño
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);	// alineación
        lblTitulo.setOpaque(true);									// opacidad
		lblTitulo.setPreferredSize(new Dimension(500,25));			// dimensiones preferidas

		pnlGeneral.add(lblTitulo, BorderLayout.NORTH);				// añade el rótulo a la zona superior

		taNotificaciones = new JTextArea();							// área de texto notificaciones
		taNotificaciones.setLineWrap(true);							// para que parta las líneas que no caben en el control
		taNotificaciones.setEditable(false);						// no es editable por el usuario
        spNotificaciones = new JScrollPane(taNotificaciones);		// le añade un scroll
		spNotificaciones.setPreferredSize(new Dimension(100,140));	// dimensiones preferidas
		spNotificaciones.setMinimumSize(new Dimension(100,140));	// dimensiones mínimas
		pnlGeneral.add(spNotificaciones, BorderLayout.CENTER);

		
		pnlBotones = new JPanel(new GridLayout(1, 2, 10, 10));		// panel de los botones
        btnIniciar = new JButton("Iniciar servidor");				// botón iniciar servidor
        btnIniciar.setHorizontalTextPosition(SwingConstants.LEFT);

		btnIniciar.addActionListener( 								// escuchador del botón iniciar servidor
			new ActionListener() {									// clase ActionListener
				public void actionPerformed(ActionEvent e) {		// método actionPerformed
					
					if (servidor == null) {							// no se ha arrancado nunca, 
						servidor = new Servidor(gui);				// lo crea
					}
					servidor.arrancarServidor(); 					// ahora lo arranca
					System.out.println("Se ha solicitado el arranque del servidor");
					btnIniciar.setEnabled(false);					// deshabilita el botón de iniciar
					btnParar.setEnabled(true);						// habilita el botón de parar
				} // fin de método actioPerformed
			} // fin de clase interna anónima
		); // fin de llamada a addActionListener
		pnlBotones.add(btnIniciar);									// añade el botón al panel

		btnParar = new JButton("Parar servidor");					// botón parar servidor
        btnParar.setHorizontalTextPosition(SwingConstants.CENTER);
        btnParar.setEnabled(false);									// el botón está inhabilitado de entrada
        
		btnParar.addActionListener( 								// se le añade un escuchador
			new ActionListener() {									// nueva clase
				public void actionPerformed(ActionEvent e) {		// método actionPerformed
					if (servidor != null) {							// si el servidor existe
						servidor.pararServidor();					// lo para
					}
					btnIniciar.setEnabled(true);					// habilita el botón de arrancar
					btnParar.setEnabled(false);						// inhabilita el de parar
				} // fin de método actionPerformed
			} // fin de clase interna anónima
		); // fin de llamada a addActionListener
		pnlBotones.add(btnParar);									// añade el botón al panel

		pnlGeneral.add(pnlBotones, BorderLayout.SOUTH);				// añade el panel de botones al panel general 

		/*
		 * Crea el marco, define su panel de contenido y muestra todo
		 */
		frmVentana = new JFrame("Servidor WHB");					// marco de la ventana
		frmVentana.addWindowListener(								// listener de ventana para que se cierre la aplicación
				new WindowAdapter() {		 						// crea clase
				public void windowClosing(WindowEvent e) {			// al cerrar la ventana
					if (servidor != null) {							// si existe el servidor
						servidor.pararServidor();					// lo para
					}
					System.out.println("Fin de programa");
					System.exit(0);									// sale de la aplicación
				} // fin de método windowClosing
			} // fin de clase interna anónima
		); // fin de llamada a addWindowListener
		
		frmVentana.setSize(500,500);					// tamaño de la ventana
		frmVentana.setContentPane(pnlGeneral);			// asignación del contenido
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
	 * setter para área de notificaciones
	 * 
	 * @param texto a mostrar
	 */
	public void setNotificaciones(String texto) {
		taNotificaciones.setText(texto);
	}
	
	/**
	 * método sobreescrito del interface que actualiza la información eviada desde el servidor
	 * 
	 * @params servidor 
	 */
	@Override
	public void estadoServidorActualizado(Servidor servidor) {
		final String BR = (taNotificaciones.getText().equals("")) ? "" : "\n";					// por si hay que insertar un retorno de carro		
		if (servidor.servidorArrancado()) {														// si está arrancado
			taNotificaciones.setText(taNotificaciones.getText() + BR + "Servidor arrancado");
		} else {																				// ... y si no
			taNotificaciones.setText(taNotificaciones.getText() + BR + "Servidor detenido");				
		}
        taNotificaciones.setCaretPosition(taNotificaciones.getText().length());					// asegura de que se vea en el áre de texto el último mensaje
	}
}
