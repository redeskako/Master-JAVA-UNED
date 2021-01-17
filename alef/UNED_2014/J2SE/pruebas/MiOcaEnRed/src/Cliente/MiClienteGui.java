package Cliente;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class MiClienteGui extends JFrame{

	private  JButton comenzar, terminar, tirarDado;
	private JLabel jlServidor, jlPuerto, jlMensaje;
	private JTextField tfServidor, tfPuerto;
	private boolean connected;
	private int defaultPort;
	private String defaultHost;
	JLabel jlDado;
	
	public MiClienteGui(String host, int port,int x, int y, int ancho, int alto) {
		super("Estación Cliente");
		defaultPort = port;
		defaultHost = host;
		
		this.setBounds(x, y, ancho, alto);
		
		// Se crean los componentes de la ventana.
		
		 jlServidor = new JLabel("Conectar a servidor :");
		 jlPuerto = new JLabel("Puerto de comunicaciones :");
		 jlDado = new JLabel("");
		 jlMensaje = new JLabel("Etiqueta de los mensajes");
		
		  tfServidor = new JTextField();
		  tfServidor.setText("localhost");
		  tfPuerto = new JTextField();
		  tfPuerto.setText("1500");
		 
		 
		 comenzar = new JButton ("Comenzar");
		 terminar = new JButton("Terminar");
		 tirarDado = new JButton ("Lanzar Dado");
		 
		 // Se crea el escuchador de eventos.
		 GestionEventos gb = new GestionEventos(this);
		 
		 // Se asocia  el escuchador de eventos a los objetos.
		
		  comenzar.addActionListener(gb);
		  terminar.addActionListener(gb);
		  tirarDado.addActionListener(gb);
	
		// Se elimina el gestor de contenidos por defecto.  
		  this.getContentPane().setLayout(null);
	    
		  // Se añaden las etiquetas a la ventana
		  
		  this.add(jlServidor);
		  jlServidor.setBounds(25,50,150,30);
		
		  this.add(jlPuerto);
		  jlPuerto.setBounds(25,80,200,30);
		  this.add(jlDado);
		  jlDado.setBounds(25,110,150,30);
		  
	     // Se añaden los cuadros de texto a la ventana.
		  
		  this.add(tfServidor);
		 
		  tfServidor.setBounds(150, 50, 70, 20);
		  this.add(tfPuerto);
		  
		  tfPuerto.setBounds(190, 80, 40, 20);
		  
		
		  
		//  Se añaden los botones a la ventana.
		  
		  this.add(comenzar);
		  comenzar.setBounds(500,50,100,20);
		  this.add(terminar);
		  terminar.setBounds(500,80,100,20);
		  terminar.setEnabled(false);
		  this.add(tirarDado);
		  tirarDado.setBounds(500,110,110,20);
		  tirarDado.setEnabled(false);
		
		  // Se añade la etiqueta que muestra los errores.
		  
		  this.add(jlMensaje);
		  jlMensaje.setBounds(50, 150, 300, 20);
		 
		 // jlMensaje.setVisible(false);
		  // Visualizamos la ventana
		   
		  this.setVisible(true);
	}

	public JTextField getTfServidor() {
		return tfServidor;
	}

	public JTextField getTfPuerto() {
		return tfPuerto;
	}

	public JButton getComenzar() {
		return comenzar;
	}

	public JButton getTerminar() {
		return terminar;
	}

	public JButton getTirarDado() {
		return tirarDado;
	}

	

	public void setJlMensaje(String str){
		jlMensaje.setText(str); 
	}
	
	public void visibilidadError(boolean estado){
		jlMensaje.setVisible(estado);
	}
	
	public void actualizarEstadoLanzamiento(boolean activar){
				  	tirarDado.setEnabled(activar);
			
	}
	
	public void actulizarEstadoComenzar(boolean estado){
		comenzar.setEnabled(estado);
	}
	
	public void actualizarEstadoTerminar(boolean estado){
		terminar.setEnabled(estado);
	}
	
	public void actualizarEstadoTirarDado(boolean estado){
		tirarDado.setEnabled(estado);
	}
}
