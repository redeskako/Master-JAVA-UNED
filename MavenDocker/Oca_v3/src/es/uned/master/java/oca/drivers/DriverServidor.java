package es.uned.master.java.oca.drivers;



import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import es.uned.master.java.oca.idioma.*;
import es.uned.master.java.oca.servidor.*;
/**
 * Driver de la parte de Servidor del juego de la oca versión 3.0. Hereda de la
 * clase JFrame para desarrollar la interfaz gráfica del Servidor. Implementa
 * ActionListener y WindowListener para gestionar los eventos que ocurran.
 *
 * @author Alef UNED 2014
 * @version Oca 3.0
 * @fecha Mayo 2014
 */
public class DriverServidor extends JFrame implements ActionListener,WindowListener {
	public static Config idioma;
	// El campo serialVersionUID es el número de versión de la clase.
	// Cuando el objeto es serializado lo primero que hace es escribir el
	// serialVersionUID.
	private static final long serialVersionUID = 1L;
	// se crea el boton para conectar y desconectar
	private JButton botonConectarDesconectar;
	// se crea los labels para mostrar texto
	private JLabel labelConexion, labelJugadores, labelIdioma;
	// se crea 2 combos, para el numero de jugadores y los idiomas
	private JComboBox listaJugadores, listaIdiomas;
	// se crea el area donde estará la información de la partida y las
	// conexiones
	private JTextArea infopart, infoconex;
	// se crea el servidor
	private Servidor servidor;
	// Numero de jugadores para las pruebas por defecto 3:
	private static int numJ = 3;
	// Booleano para comprobar si se ha pulsado el botón para
	// conectar/desconectar servidor
	private boolean servidorConectadoBoton;
	private String estadoBoton = "CN";
	// Método constructor del gui del servidor
	public DriverServidor() {
		super("Servidor");
		servidor = null;
		// se inicia el Servidor con el idioma Español
		idioma = new Config("es");
		// por defecto se cierra la ventana
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		// El titulo de la ventana
		this.setTitle(idioma.traduce("_SERVIDOR"));

		// Se crea el Panel superior donde estará el botón de comprobar
		// n Jugadores
		JPanel superior = new JPanel();
		labelJugadores = new JLabel(idioma.traduce("_NUMEROJUGDAORES"));
		superior.add(labelJugadores);
		add(superior, BorderLayout.NORTH);

		// Los opciones de jugadores jugadores de 2 a 6 en un array
		Integer[] numJugadores = { 2, 3, 4, 5, 6 };

		// Se crea el combo para el numero de jugadores
		listaJugadores = new JComboBox(numJugadores);
		listaJugadores.setSelectedIndex(1);
		listaJugadores.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					// una selección
					int i = (Integer) e.getItemSelectable()
							.getSelectedObjects()[0];
					numJ = i;
				}
			}
		});
		superior.add(listaJugadores);
		labelIdioma = new JLabel(idioma.traduce("_IDIOMA"));
		superior.add(labelIdioma);
		add(superior, BorderLayout.NORTH);

		// Los opciones de idioma en un array
		String[] idiomas = { idioma.traduce("_ES"), idioma.traduce("_EN") };
		// Se crea el combo para el los idiomas
		listaIdiomas = new JComboBox(idiomas);

		listaIdiomas.addActionListener(this);
		listaIdiomas.setActionCommand("CL");
		superior.add(listaIdiomas);

		// Se crea el Panel Inferior para la conexión y desconexión del servidor
		JPanel inferior = new JPanel(new FlowLayout());
		labelConexion = new JLabel(idioma.traduce("_CONEXIONSERVIDOR"));
		inferior.add(labelConexion);
		add(inferior, BorderLayout.SOUTH);

		// Para conectar y desconectar el servidor empezamos con conectar
		botonConectarDesconectar = new JButton(idioma.traduce("_CONECTAR"));
		botonConectarDesconectar.addActionListener(this);

		inferior.add(botonConectarDesconectar);

		// Se crea el panel central de la información de la partida
		JPanel center = new JPanel(new GridLayout(2, 1));
		add(center);

		// Se crea el área de información de la partida
		infopart = new JTextArea(80, 80);
		infopart.setEditable(false);
		infoPartida(idioma.traduce("_INFOPARTIDA") + " \n");

		center.add(new JScrollPane(infopart));

		// Se crea el área de la información de las conexiones con los clientes
		infoconex = new JTextArea(80, 80);
		infoconex.setEditable(false);
		infoConexiones(idioma.traduce("_INFOCONEXION") + " \n");
		center.add(new JScrollPane(infoconex));

		// Para que se haga visible
		addWindowListener(this);
		setSize(600, 600);
		setVisible(true);
	}
	/**
	 * Incluye al final del área de texto infopart un String que recibe como parámetro.
	 * Es información sobre el desarrollo de la partida.
	 * @param String para mostrar
	 * @return null
	 * @throws null
	 */
	public void infoPartida(String str) {
		infopart.append(str);
		infopart.setCaretPosition(infopart.getText().length() - 1);
	}
	/**
	 * Incluye al final del área de texto infoconex un String que recibe como parámetro.
	 * Es información sobre las conexiones socket.
	 * @param String para mostrar
	 * @return null
	 * @throws null
	 */
	public void infoConexiones(String str) {
		infoconex.append(str);
		infoconex.setCaretPosition(infoconex.getText().length() - 1);
	}
	/**
	 * Permite cambiar el idioma en el que se muestra la información de la GUI servidor.
	 * @param null
	 * @return null
	 * @throws null
	 */
	private void cambiaIdioma() {
		// Los cambios de idioma de los distintos labels, botones y textareas
		this.setTitle(idioma.traduce("_SERVIDOR"));
		if (this.estadoBoton == "CN") {
			botonConectarDesconectar.setText(idioma.traduce("_CONECTAR"));
		} else if (this.estadoBoton == "DC") {
			botonConectarDesconectar.setText(idioma.traduce("_DESCONECTAR"));
		}
		labelJugadores.setText(idioma.traduce("_NUMEROJUGDAORES"));
		labelIdioma.setText(idioma.traduce("_IDIOMA"));
		labelConexion.setText(idioma.traduce("_CONEXIONSERVIDOR"));
		infopart.setText(idioma.traduce("_INFOPARTIDA") + " \n");
		infoconex.setText(idioma.traduce("_INFOCONEXION") + " \n");
	}
	/**
	 * Permite cambiar el idioma en el que se muestra la información de la GUI servidor.
	 * @param String con el nuevo idioma
	 * @return null
	 * @throws null
	 */
	private void cambiaIdioma(String local) {
		idioma.cambiaIdioma(local);
		this.cambiaIdioma();
	}
	/**
	 * Cambiar el esta de boton, de conectado a desconectado y vv
	 * @param String con el nuevo idioma
	 * @return null
	 * @throws null
	 */ 
	private void cambioEstadoBoton() {
		if (this.estadoBoton == "CN") {
			this.estadoBoton = "DC";
		} else if (this.estadoBoton == "DC") {
			this.estadoBoton = "CN";
		}
	}
	/**
	 * El listener para captar cambio de idioma, desconectar y conectar del servidor.
	 * Muestra las acciones a realizar como efecto de los eventos.
	 * @param ActionEvent de la GUI del Servidor
	 * @return null
	 * @throws null
	 */ 
	public void actionPerformed(ActionEvent e) {
		String st = (String) e.getActionCommand();
		if (st.equalsIgnoreCase("CL")) {
			JComboBox cb = (JComboBox) e.getSource();
			int idiomaLocal = (Integer) cb.getSelectedIndex();
			if (idiomaLocal == 0) {
				this.cambiaIdioma("es");
			} else if (idiomaLocal == 1) {
				this.cambiaIdioma("en");
			}
		} else {
			// Si existe un servidor y se pulsa el botón: se cierra el servidor
			if (servidor != null) {
				// Variable que indica que el servidor se ha desconectado con el botón
				servidorConectadoBoton = false;
				// Si existe alguna conexión: se informa a todos los Clientes de
				// que deben cerrar sus conexiones
				if (this.servidor.getMapClientes().size() > 0) {
					// Como se obliga a cerrar el servidor desde el botón de
					// desconexión, se da por finalizado el juego
					this.servidor.getOca().setJuegoTerminado(true);
					// Se pide a Servidor que cierre todo
					this.servidor.cerrarTodo();
				}
				// Si no existe ninguna conexión, se cierra el ServerSocket
				else {
					// Como se obliga a cerrar el servidor desde el botón de
					// desconexión, se da por finalizado el juego
					this.servidor.getOca().setJuegoTerminado(true);
					// Se cierra el ServerSocket
					this.servidor.cerrarServidor();
				}
				botonConectarDesconectar.setText(idioma.traduce("_CONECTAR"));
				this.cambioEstadoBoton();
				listaIdiomas.setEnabled(true);
				listaJugadores.setEnabled(true);
			}
			// Si NO existe un servidor y se pulsa el botón, se inicia un
			// servidor
			else {
				// Se indica que se el servidor se ha conectado con el botón
				servidorConectadoBoton = true;
				// Se crea nuevo servidor
				servidor = new Servidor(this, numJ);
				if (servidor.getServidorConectado()) {
					// Si el servidor se ha creado correctamente, el botón pasa
					// a mostrar "Desconectar"
					botonConectarDesconectar.setText(idioma
							.traduce("_DESCONECTAR"));
					this.cambioEstadoBoton();
					listaIdiomas.setEnabled(false);
					listaJugadores.setEnabled(false);
				} else {
					// Si el servidor NO se ha creado correctamente, el botón
					// pasa a mostrar "Conectar"
					botonConectarDesconectar.setText(idioma
							.traduce("_CONECTAR"));
					this.cambioEstadoBoton();
					listaIdiomas.setEnabled(true);
					listaJugadores.setEnabled(true);
					this.servidor = null;
				}
			}
		}
	}
	/**
	 * Elimina de manera definitiva el servidor.
	 * @param null
	 * @return null
	 * @throws null
	 */
	public void eliminarServidor() {
		this.servidor = null;
	}
	/**
	 * Establece un nuevo texto para el botón botonConectarDesconectar.
	 * @param String para mostrar en botón
	 * @return null
	 * @throws null
	 */
	public void setTextoBoton(String s) {
		botonConectarDesconectar.setText(s);
	}
	/**
	 * Devuelve el valor de la variable servidorConectadoBoton, que indica si el
	 * servidor se ha conectado/desconectado por mendio del botón.
	 * @param null
	 * @return boolean
	 * @throws null
	 */
	public boolean getServidorConectadoBoton() {
		return this.servidorConectadoBoton;
	}
	/**
	 * Método main
	 */
	public static void main(String[] args) {
		new DriverServidor();
	}
	// Serie de método autogenerados
	public void windowActivated(WindowEvent arg0) {
	}
	public void windowClosed(WindowEvent arg0) {
	}
	public void windowClosing(WindowEvent arg0) {
	}
	public void windowDeactivated(WindowEvent arg0) {
	}
	public void windowDeiconified(WindowEvent arg0) {
	}
	public void windowIconified(WindowEvent arg0) {
	}
	public void windowOpened(WindowEvent arg0) {
	}
	/**
	 * Clase interna que hereda de Thread para gestionar el cambio de idioma.
	 *
	 * @author Alef UNED 2014
	 * @version Oca 3.0
	 * @fecha Mayo 2014
	 */
	class ServerRunning extends Thread {
		public void run() {
			servidor.iniciarServidor();
			botonConectarDesconectar.setText(idioma.traduce("_CONECTAR"));
			// listaJugadores.setEditable(true);
			infoConexiones("Server crashed\n");
			servidor = null;
		}
	}
}