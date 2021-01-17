package es.uned2014.oca.cliente;

import javax.swing.*;

import java.awt.*;

/**
 * Se define una clase ClienteGui que hereda de JFrame en la que se desarrolla la interfaz 
 * gráfica para Cliente
 * 
 * @author	Alef UNED 2014
 * @version	Oca 3.0
 * @fecha 	Mayo 2014
 */

public class ClienteGui extends JFrame {

	// Botones
	private JButton comenzar, terminar, tirarDado;
	// Etiquetas
	private JLabel jlServidor, jlPuerto, jlColor;
	// Campos de texto
	private JTextField tfServidor, tfPuerto, tfColor;

	// Áreas de texto
	JScrollPane sb;
	JTextArea ta;
	
	// Etiquetas
	JLabel jlDado, jlMensaje;

	// Gestor de eventos para los action listeners
	private GestionEventos gb;
	
	/**
	 * Método constructor: recibe como parámetros las dimensiones. 
	 * @param enteros que definen las medidas de la ventana
	 * @return null
	 * @throws null
	 */
	public ClienteGui(int x, int y, int ancho, int alto) {
		// Se ejecuta el constructor del padre
		super("Estación Cliente");

		// Se establecen las medidas de la ventana
		this.setBounds(x, y, ancho, alto);

		// Se crean los componentes de la ventana.
		jlServidor = new JLabel("Conectar a servidor :");
		jlPuerto = new JLabel("Puerto de comunicaciones :");
		jlDado = new JLabel("");
		jlMensaje = new JLabel("");
		jlColor = new JLabel("El color de su ficha es :");

		tfServidor = new JTextField();
		tfServidor.setText("localhost");
		tfServidor.enable(false);
		tfPuerto = new JTextField();
		tfPuerto.setText("8888");
		tfPuerto.enable(false);
		tfColor = new JTextField();

		comenzar = new JButton("Comenzar");
		terminar = new JButton("Terminar");
		tirarDado = new JButton("Lanzar Dado");

		// Se crea el escuchador de eventos.
		gb = new GestionEventos(this);

		// Se asocia el escuchador de eventos a los objetos.
		comenzar.addActionListener(gb);
		terminar.addActionListener(gb);
		tirarDado.addActionListener(gb);

		// Se elimina el gestor de contenidos por defecto.
		this.getContentPane().setLayout(null);

		// Se añaden las etiquetas a la ventana
		this.add(jlServidor);
		jlServidor.setBounds(25, 50, 150, 30);

		this.add(jlPuerto);
		jlPuerto.setBounds(25, 80, 200, 30);
		
		this.add(jlDado);
		jlDado.setBounds(280, 110, 150, 30);

		this.add(jlColor);
		jlColor.setBounds(25, 110, 150, 30);

		// Se añaden los cuadros de texto a la ventana.
		this.add(tfServidor);
		tfServidor.setBounds(150, 50, 70, 20);
		
		this.add(tfPuerto);
		tfPuerto.setBounds(190, 80, 40, 20);

		this.add(tfColor);
		tfColor.setBounds(180, 110, 80, 20);

		// Se añaden los botones a la ventana.
		this.add(comenzar);
		comenzar.setBounds(500, 50, 100, 20);
		
		this.add(terminar);
		terminar.setBounds(500, 80, 100, 20);
		terminar.setEnabled(false);
		
		this.add(tirarDado);
		tirarDado.setBounds(500, 110, 110, 20);
		tirarDado.setEnabled(false);

		// Se añade la etiqueta que muestra los errores.
		this.add(jlMensaje);
		jlMensaje.setBounds(20, 420, 450, 20);
		jlMensaje.setVisible(true);
		jlMensaje.setForeground(new Color(255, 0, 0));
		jlMensaje.setText("");
		
		
		// Se añade el area de texto
		ta = new JTextArea("Bienvenido al Juego de la Oca...\n");
		ta.setBounds(20, 150, 600, 250);
		this.add(ta);
		
		sb = new JScrollPane(ta);
		sb.setViewportView(ta);
		sb.setBounds(20, 150, 600, 250);
		this.add(sb);

		ta.setEditable(false);
		ta.setVisible(true);
		
		// Visualizamos la ventana
		this.setVisible(true);
	}



	
	
	/**
	 * Obtiene el valor de la variable tfServidor (TexField Servidor)
	 * @param null
	 * @return valor de la variable tfServidor
	 * @throws null
	 */
	public JTextField getTfServidor() {
		return tfServidor;
	}
	
	/**
	 * Obtiene el valor de la variable tfPuerto (TexField Puerto)
	 * @param null
	 * @return valor de la variable tfPuerto
	 * @throws null
	 */
	public JTextField getTfPuerto() {
		return tfPuerto;
	}

	/**
	 * Obtiene el valor de la variable comenzar (botón Comenzar)
	 * @param null
	 * @return valor de la variable comenzar
	 * @throws null
	 */
	public JButton getComenzar() {
		return comenzar;
	}

	/**
	 * Obtiene el valor de la variable terminar (botón Terminar)
	 * @param null
	 * @return valor de la variable terminar
	 * @throws null
	 */
	public JButton getTerminar() {
		return terminar;
	}

	/**
	 * Obtiene el valor de la variable tirarDado (botón)
	 * @param null
	 * @return valor de la variable tirarDado
	 * @throws null
	 */
	public JButton getTirarDado() {
		return tirarDado;
	}
	

	
	
	
	/**
	 * Muestra un texto en la parte inferior de la GUI Cliente (error)
	 * @param String que se quiere mostrar
	 * @return null
	 * @throws null
	 */
	public void escribirError(String str) {
		jlMensaje.setForeground(new Color(255, 0, 0));
		jlMensaje.setText(str);
	}
	
	/**
	 * Establece si jlMensaje es visible o no.
	 * @param boolean
	 * @return null
	 * @throws null
	 */
	public void visibilidadError(boolean estado) {
		jlMensaje.setVisible(estado);
	}

	/**
	 * Establece si el botón tirarDado está activo o no.
	 * @param boolean
	 * @return null
	 * @throws null
	 */
	public void actualizarEstadoLanzamiento(boolean activar) {
		tirarDado.setEnabled(activar);
	}

	/**
	 * Establece si el botón Comenzar está activo o no.
	 * @param boolean
	 * @return null
	 * @throws null
	 */
	public void actualizarEstadoComenzar(boolean estado) {
		comenzar.setEnabled(estado);
	}

	/**
	 * Establece si el botón Terminar está activo o no.
	 * @param boolean
	 * @return null
	 * @throws null
	 */
	public void actualizarEstadoTerminar(boolean estado) {
		terminar.setEnabled(estado);
	}

	/**
	 * Establece si el botón TirarDado está activo o no.
	 * @param boolean
	 * @return null
	 * @throws null
	 */
	public void actualizarEstadoTirarDado(boolean estado) {
		tirarDado.setEnabled(estado);
	}

	/**
	 * Añade un String al final del texto que contiene el área de texto (ta)
	 * @param String que se quiere mostrar
	 * @return null
	 * @throws null
	 */
	public void escribirMensaje(String mensaje) {
		ta.setText(ta.getText() + mensaje + "\n");
		ta.setCaretPosition(ta.getText().length() - 1);
	}

	/**
	 * Dependiendo del color que reciba el Cliente, lo muestra en en text field
	 * (tfColor) y asigna color de fondo.
	 * Cambia el encabezado de la GUI, identificando el color del jugador.
	 * @param String del color
	 * @return null
	 * @throws null
	 */
	public void escribirColor(String color) {
		switch (color) {
		case "ROJO":
			tfColor.setBackground(new Color(255, 0, 0));
			tfColor.setForeground(new Color(255, 255, 255));
			super.setTitle("Jugador Rojo");
			break;
		case "AZUL":
			tfColor.setBackground(new Color(0, 0, 255));
			tfColor.setForeground(new Color(255, 255, 255));
			super.setTitle("Jugador Azul");
			break;
		case "AMARILLO":
			tfColor.setBackground(new Color(255, 255, 0));
			tfColor.setForeground(new Color(0, 0, 0));
			super.setTitle("Jugador Amarillo");
			break;
		case "VERDE":
			tfColor.setBackground(new Color(0, 153, 51));
			tfColor.setForeground(new Color(255, 255, 255));
			super.setTitle("Jugador Verde");
			break;
		case "NARANJA":
			tfColor.setBackground(new Color(255, 204, 0));
			tfColor.setForeground(new Color(0, 0, 0));
			super.setTitle("Jugador Naranja");
			break;
		case "VIOLETA":
			tfColor.setBackground(new Color(153, 0, 204));
			tfColor.setForeground(new Color(255, 255, 255));
			super.setTitle("Jugador Violeta");
			break;
		default:
			// Si se recibe otro color, se pone en blanco
			tfColor.setBackground(new Color(255, 255, 255));
			tfColor.setForeground(new Color(255, 255, 255));
			super.setTitle("Estación Cliente");
		}

		// Se hacen visibles los cambios
		tfColor.setText(color);
		tfColor.setVisible(true);
	}

	/**
	 * Los botones de la GUI vuelven a su estado inicial:
	 * - Comenzar: activado
	 * - Terminar: desactivado
	 * - Tirar el dado: desactivado
	 * - Color: blanco
	 * @param null
	 * @return null
	 * @throws null
	 */
	public void resetBotones() {
		actualizarEstadoComenzar(true);
		actualizarEstadoTirarDado(false);
		actualizarEstadoTerminar(false);
		escribirColor("");
	}

	/**
	 * Se elimina de manera definitiva un Cliente
	 * @param null
	 * @return null
	 * @throws null
	 */
	public void eliminarCliente() {
		this.gb.eliminarCliente();
	}
}
