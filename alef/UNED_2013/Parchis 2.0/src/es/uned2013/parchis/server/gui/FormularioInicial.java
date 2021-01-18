package es.uned2013.parchis.server.gui;

import javax.swing.*;

import es.uned2013.parchis.ModoJuego;
import es.uned2013.parchis.Parchis;
import es.uned2013.parchis.ui.ParchisUIMode;
import es.uned2013.parchis.ui.ParchisUI;

import java.awt.FlowLayout;
import java.awt.event.*;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Locale;
import java.util.concurrent.BlockingQueue;

/**
 *  * Este es el formulario inicial que mostrará el servidor al abrir una partida nueva. 
	 * Pulsando estadisticas da la opcion de obtener estadisticas de partidas antiguas
	 * Pulsando nueva, generará una nueva partida donde se deberá crear la nueva partida y comenzar a jugarla.
	
 * @author Alef
 *
 */
public class FormularioInicial extends JFrame implements ActionListener, WindowListener{

	private static final long serialVersionUID = 1L;
	
	private JButton boton1, boton2, boton3;
	
	private Parchis parchis;
	
	public FormularioInicial() {
		addWindowListener(this);
		setLayout(null);
      boton1=new JButton("Nueva Partida");
      boton2=new JButton("Estadistica");
      boton3=new JButton("Abandonar");
      add(boton1);
      add(boton2);
      add(boton3);
      boton1.addActionListener(this);
      boton2.addActionListener(this);
      boton3.addActionListener(this);
      boton3.setEnabled(false);// Inhabilita "Abandonar" hasta que no comience el juego
   }
    
   /**
    * define el detector de eventos
    */
	public void actionPerformed(ActionEvent e) {
		ParchisUIMode uiMode = ParchisUIMode.GUI; // GUI o CONSOLE
		Locale loc = new Locale("es");
		
		Parchis.setModoJuego(ModoJuego.ONLINE); // ONLINE o LOCAL
   			 
   	if (e.getSource()==boton1) {
   		boton1.setEnabled(false);// Inhabilita "Nueva partida"
   		boton3.setEnabled(true);// Habilita "Abandonar"
   		// aquí deberia comenzar una nueva partida
   		parchis = new Parchis();
   		switch (Parchis.getModoJuego()) {
   			case ONLINE:
   				// Creamos el interfaz de usuario del parchis
   				// inicialmente en modo consola para que no se
   				// visualice un tablero si el modo de interfaz
   				// original es GUI.
   				parchis.setUI(ParchisUIMode.CONSOLE, loc);
   				// Retornamos al modo de interfaz original.
   				Parchis.setUIMode(uiMode);
   				try{
   					parchis.escucharPorJugadores();
   		 		}catch(Exception e1){
   		 			System.out.println("ERROR:" + e1.getMessage());
   		 			e1.printStackTrace();
   		 		}
   				break;
   			default:
   				// Creamos un interfaz de usario único
   				// para el parchís.
   				parchis.setUI(uiMode, loc);
   				if (uiMode.equals(ParchisUIMode.CONSOLE)) {
   					Thread t = new Thread() {
   						public void run() {		
   							try{
   	   						parchis.inicioJuego(parchis.numJugadores());
   	      		 		}catch(Exception e1){
   	      		 			System.out.println("ERROR:" + e1.getMessage());
   	      		 			e1.printStackTrace();
   	      		 		}
   						}
   					};
   					t.start();
   				}
   				break;
   		}
   	}
   	if (e.getSource()==boton2) {
   		// aquí abre la pantalla de estadisticas
   		FormularioCargaEstadisticas fCE = new FormularioCargaEstadisticas();
   		try {
   			fCE.FormularioCargaEstadisticas();
   		} catch (ClassNotFoundException e1) {
   			// TODO Auto-generated catch block
   			e1.printStackTrace();
   		} catch (SQLException e1) {
   			// TODO Auto-generated catch block
   			e1.printStackTrace();
   		}
   		this.setVisible(false);
   	}
   	if (e.getSource()==boton3) {
   		boton3.setEnabled(false); // Deshabilita "Abandonar"
   		boton1.setEnabled(true);// Habilita "Nueva partida"
   		 
			try {
				if (parchis.isEnJuego())
					Parchis.getUI().abandonarPartida(true, -1);
				else{
					BlockingQueue<ParchisUI> uiList = parchis.getUIList();
					int cont = uiList.size()-1;
					while (cont>=0)
					{
						((ParchisUI)uiList.toArray()[cont]).abandonarPartida(true, -1);
						cont--;
					}
				}
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				System.out.println("Error al bloquear a los clientes.");
				e1.printStackTrace();
			}
   		parchis.finalizaEscucha();// Cierra la conexión actual
   		System.exit(0);
   	}
   	 	
	}
    
	public static void main(String[] ar) {
		FormularioInicial formulario1=new FormularioInicial();
		formulario1.getContentPane().setLayout(new FlowLayout());
		formulario1.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		formulario1.setLocationRelativeTo(null);
		formulario1.setTitle("Parchis 2.0 Server");
		formulario1.pack();
		formulario1.setVisible(true);
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		try {
			if (parchis.isEnJuego())
				Parchis.getUI().abandonarPartida(true, -1);
			else{
				BlockingQueue<ParchisUI> uiList = parchis.getUIList();
				int cont = uiList.size()-1;
				while (cont>=0)
				{
					((ParchisUI)uiList.toArray()[cont]).abandonarPartida(true, -1);
					cont--;
				}
			}
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			System.out.println("Error al bloquear a los clientes.");
			e1.printStackTrace();
		}
		System.exit(0);
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
