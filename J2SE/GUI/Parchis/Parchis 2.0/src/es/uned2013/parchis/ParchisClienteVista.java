package es.uned2013.parchis;

//import ParchisClienteVista;

import java.awt.Color;

import java.awt.Container;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Point;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
//import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EtchedBorder;

import es.uned2013.parchis.rmi.server.ParchisServer;
import es.uned2013.parchis.ui.ParchisGUI;
import es.uned2013.parchis.ui.ParchisI18Keys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * Clase que crea la vista gráfica del tablero de parchís.
 * @author alef
 */
public class ParchisClienteVista extends JFrame { //implements MouseListener{
	
	private static final long serialVersionUID = 1L;
	
	private static final String numJugs[] = {"2","3","4"}; 
	public static final String imgDados[] = {"dado1.png", "dado2.png", "dado3.png",
		"dado4.png", "dado5.png", "dado6.png", "dado7.png"};
	public static final String imgFichas[] = {"ficha_amarilla.png", "ficha_azul.png",
		"ficha_roja.png", "ficha_verde.png"};
	// Ruta donde se almacenan los archivos de imágenes (*.png)
	public static final String rutaImg = System.getProperty("user.dir") + 
			File.separator + "src" + File.separator + "es" + File.separator + "uned2013" + File.separator + "parchis" + File.separator + "img" + File.separator;
	// Ruta donde se almacenan los archivos de texto (*.txt) 
	private static final String rutaTxt = System.getProperty("user.dir") +
			File.separator + "src" + File.separator + "es" + File.separator + "uned2013" + File.separator + "parchis" + File.separator + "txt" + File.separator;
	// Ancho y alto en píxeles de cada una de las subdivisiones
	// resultantes de dividir el tablero en 20x20 celdas cuadradas
	// del mismo tamaño.
	private static final int TAM_CELDA = 35;
															
	// Map que asocia números de casilla a arrays de coordenadas con
	// 7 posiciones, de manera que cada posición determina dónde debe estar 
	// ubicada una ficha en esa casilla, dependiendo de, para casillas 
	// distintas de casa o final, si hay una o 2 fichas en esa casilla, y
	// si es la primera o la segunda ficha, y para casillas casa o final,
	// dependiendo de su identificador. Según el siguiente criterio:
	// posiciones.get(numCasilla).get(0) -> Coordenada de una única ficha en
	//                                      casilla distinta de casa o final.
	// posiciones.get(numCasilla).get(1) -> Coordenada de primera ficha de 2 en
	//                                      casilla distinta de casa o final.
	// posiciones.get(numCasilla).get(2) -> Coordenada de segunda ficha de 2 en
	//                                      casilla distinta de casa o final.
	// posiciones.get(numCasilla).get(3) -> Coordenada de primera ficha del
	//                                      jugador en casilla casa o final.
	// posiciones.get(numCasilla).get(4) -> Coordenada de la segunda ficha del
	//                                      jugador en casilla casa o final.
	// posiciones.get(numCasilla).get(5) -> Coordenada de la tercera ficha del
	//                                      jugador en casilla casa o final.
	// posiciones.get(numCasilla).get(6) -> Coordenada de la cuarta ficha del
	//                                      jugador en casilla casa o final
	HashMap<Integer,ArrayList<Point>> posiciones = new HashMap<Integer,ArrayList<Point>>();
	
	private ParchisGUI controlador; // Referencia al controlador.
	int index; // Indice del cliente.
	
	private ResourceBundle rb; // Fichero de idioma.
	
	//private JLabel fichaPrueba;
	//private ParchisClienteControlador controlador = new ParchisClienteControlador(this);
	
	private Container pane; // Contenedor de la vista.
	private JLabel dado; // Etiqueta con la imagen del dado.
	private JTextArea display; // Area de texto para los mensajes.
	private JLabel turnoColor; // Etiqueta con el color del jugador en turno.
	private JButton partida; // Botón "Nueva partida"
	private JButton online; // Botón "Partida en red"
	private JButton abandonar; // Botón "Abandonar"
	private JComboBox<String> cmbJugadores; //selecciona numero de Jugadores
	private ImageIcon fichas[] = new ImageIcon[4]; // Array con las imágenes de las fichas (4 colores).
	private JLabel lblFichas[] = new JLabel[16]; // Array con las etiquetas para representar las fichas.
	private Ficha ficha;//creo ficha para poder relacionar las fichas graficas con la fichas virtuales

	private boolean conexion_servidor = true;
	
	/**
	 * Constructor
	 */
	public ParchisClienteVista(Locale loc, ParchisGUI controlador, int index) {
		
		//Crea e inicializa la ventana.
		
		// Inicializamos el archivo de idioma
		rb = ResourceBundle.getBundle("Mensajes", loc); 
		
		// Título de la ventana
		if (index >= 0)
			super.setTitle(rb.getString(ParchisI18Keys.JUGADOR) + " " + 
					Colores.getColor(index).toString() + " online");
		else
			super.setTitle(rb.getString(ParchisI18Keys.NOMBREJUEGO) + " LOCAL");
		
		//Asociamos el controlador a la vista
		this.controlador = controlador;
		//Asignamos un índice de cliente
		this.index = index;
		
		addWindowListener(controlador);//Controlador de eventos del JFrame
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);//Delega en el listener la gestión del cierre de la aplicación
		setResizable(false);//Las posiciones de las casillas s�lo son v�lidas para este tama�o
		
		// Obtiene el panel principal.
		pane = getContentPane();
			
		cargaPosiciones();//Carga las posiciones de la ficha en el HashMap posiciones.
		
        
		/**
		 * Dado que no se pueden modificar las dimensiones de la ventana principal 
		 * se opta por un posicionamiento absoluto.
		 * Cuando el posicionamiento es absoluto la llamada a pack() haves que
		 * desaparezca la pantalla.
		 * En este tipo de posicionamiento el ZOrder es inverso, es decir el �ltimo
		 * componente a�adido al contenedor principla es el que est� m�s al fondo.
		 */
		pane.setLayout(null);
		  
		Insets insets = pane.getInsets(); // Devuelve el padding del objeto contenedor
		Dimension size;
		
		//Dado
		ImageIcon dado1 = new ImageIcon(rutaImg + imgDados[0]);
		dado = new JLabel(dado1);
		dado.addMouseListener(controlador);
		dado.setName("lblDado");
		  
		pane.add(dado);
		  
		insets = pane.getInsets();
		size = dado.getPreferredSize();
		dado.setBounds(755 + insets.left, 45 + insets.top, size.width, size.height);
		  
		//JLabel textoDado = new JLabel("Haz click sobre el dado para tirar");
		  
		//pane.add(textoDado);
		         
		//insets = pane.getInsets();
		//size = textoDado.getPreferredSize();
		//textoDado.setBounds(762 + insets.left, 255 + insets.top, size.width, size.height);
		  
		//Display
		display = new JTextArea(2, 10);
		//JLabel display = new JLabel("<html>&iexcl;&iexcl;&iexcl;Bienvendido al <br> parchis online!!!");
		//display.setHorizontalAlignment(SwingConstants.CENTER);
		//display.setVerticalAlignment(SwingConstants.CENTER);
		display.setEditable(false); // No permite editar el display
		display.setWrapStyleWord(true);// Evita dividir palabras al dividir entre líneas
		display.setLineWrap(true);// Divide la línea en varias si su longitud supera la del JTextArea
		display.append(rb.getString(ParchisI18Keys.BIENVENIDA));
		display.setBackground(Color.white);
		display.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		display.setOpaque(true);
		
		JScrollPane jpe = new JScrollPane(display);// Añado scroll (vertical) al display
		  
		pane.add(jpe);
		  
		insets = pane.getInsets();
		size = jpe.getPreferredSize();
		jpe.setBounds(755 + insets.left, 275 + insets.top, 200, 120);
		  
		//Turno
		JLabel turno = new JLabel(rb.getString(ParchisI18Keys.LBL_TURNO) + " ");
		  
		pane.add(turno);
		         
		insets = pane.getInsets();
		size = turno.getPreferredSize();
		turno.setBounds(802 + insets.left, 415 + insets.top, size.width, size.height);
		  
		turnoColor = new JLabel("");
		cambiarTurno(Colores.getColor(index));
		turnoColor.setBorder(BorderFactory.createLineBorder(Color.black));
		turnoColor.setOpaque(true);
		turnoColor.setVisible(true);
		  
		pane.add(turnoColor);
		         
		insets = pane.getInsets();
		size = turnoColor.getPreferredSize();
		turnoColor.setBounds(852 + insets.left, 412 + insets.top, 50, 20);
		  
		//Botones
		partida = new JButton(rb.getString(ParchisI18Keys.BTN_NUEVAPARTIDA));
		partida.addMouseListener(controlador);
		//partida.addActionListener(controlador);
		partida.setName("btnPartidaNueva");
		if (index >= 0) {
			// Si estamos en modo ONLINE, deshabilitamos el bóton.
			partida.setEnabled(false);
		}
		
		pane.add(partida);//TODO btnPartida, lblTurnoColor....
		  
		insets = pane.getInsets();
		size = partida.getPreferredSize();
		partida.setBounds(802 + insets.left, 480 + insets.top, 120, size.height);
		  
		online = new JButton(rb.getString(ParchisI18Keys.BTN_PARTIDAONLINE));
		online.addMouseListener(controlador);
		//online.addActionListener(controlador);
		online.setName("btnPartidaOnline");
		if (index == -1) {
			// Si estamos en modo LOCAL, deshabilitamos el botón.
			online.setEnabled(false);
		}
		pane.add(online); 
		  
		insets = pane.getInsets();
		size = online.getPreferredSize();
		online.setBounds(802 + insets.left, 540 + insets.top, 120, size.height);
		  
	   abandonar = new JButton(rb.getString(ParchisI18Keys.BTN_ABANDONAR));
		abandonar.addMouseListener(controlador);
		//abandonar.addActionListener(controlador);
	   abandonar.setName("btnFinPartida");
		  
		pane.add(abandonar);//TODO btnPartida, lblTurnoColor....
		  
		insets = pane.getInsets();
		size = abandonar.getPreferredSize();
		abandonar.setBounds(802 + insets.left, 600 + insets.top, 120, size.height);
		  
		//Jugadores
		JLabel jugadores = new JLabel(rb.getString(ParchisI18Keys.LBL_JUGADORES) + " ");
		  
		pane.add(jugadores);
		         
		insets = pane.getInsets();
		size = jugadores.getPreferredSize();
		jugadores.setBounds(802 + insets.left, 655 + insets.top, size.width, size.height);
		
		cmbJugadores = new JComboBox<String>(numJugs);
		cmbJugadores.setMaximumRowCount(4);
		if (index >= 0)
			// En modo ONLINE, deshabilitamos el ComboBox,
			// ya que el número de jugadores lo establecen
			// las conexiones al servidor.
			cmbJugadores.setEnabled(false);
		pane.add(cmbJugadores);
		  
		insets = pane.getInsets();
		size = cmbJugadores.getPreferredSize();
		cmbJugadores.setBounds(887 + insets.left, 650 + insets.top, size.width, size.height);
		
		// Se crean las fichas
		crearFichas();
		
		//Tablero
		ImageIcon parchis700 = new ImageIcon(rutaImg + "parchis700x700.png");
	   JLabel tablero = new JLabel(parchis700);
	   tablero.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		  
		pane.add(tablero);
		size = tablero.getPreferredSize();       
		tablero.setBounds(5 + insets.left, 7 + insets.top, size.width, size.height);
		  
		//Establece el tama�o y muestra la GUI.
		insets = getInsets();
		setSize(1000 + insets.left + insets.right, 750 + insets.top + insets.bottom);
		setVisible(true);
			
	}//end ParchisClienteVista()
	
	/** Carga posiciones en el Hashmap desde el fichero 
	 * de texto posiciones_tablero.txt
	 */
	private void cargaPosiciones(){
		
		Scanner fichero = null; // Variable para acceder al fichero.
		// Variable array para almacener las posiciones
		// del tablero asociadas a una casilla
		ArrayList<Point> posCasilla; 
		Point pos; // Posición sobre el tablero.
		int numCasilla, // Número de casilla.
			x, // Coordenada x del punto.
			y; // Coordenada y del punto.
		Insets insets = pane.getInsets(); // Anchura de los bordes del contenedor
		
		try {
			fichero = new Scanner(new File(rutaTxt + "posiciones_tablero.txt"));
			
			while (fichero.hasNext()) {
				numCasilla = fichero.nextInt();
				posCasilla = new ArrayList<Point>();
				for (int i = 1; i <= 7; i++) {
					x = (int) (fichero.nextFloat() * TAM_CELDA / 10) + 5 + insets.left;
					y = (int) (fichero.nextFloat() * TAM_CELDA / 10) + 7 + insets.top;
					pos = new Point(x, y);
					posCasilla.add(pos);
				}
				posiciones.put(numCasilla, posCasilla);
			}
		}
		catch (Exception e) {
			System.err.println("ERROR:" + e.getMessage());
			e.printStackTrace();
		}
		finally {
			if (fichero != null) fichero.close();
		}
		
	}//end cargaPosiciones()
	
	private void crearFichas() {
		String auxNombre;
		int numCasilla;
		
		//Inicializamos las imágenes de las fichas
		for (int i = 0; i < 4; i++)
			fichas[i] = new ImageIcon(rutaImg + imgFichas[i]);
		
		// Recorremos los índices de las 16 fichas
		for (int i = 0; i < 16; i++) {
			// Define un nombre diferente para cada ficha.
			auxNombre = "ficha_" + (i + 1);
			// Crea una nueva etiqueta con la imagen correspondiente.
			lblFichas[i] = new JLabel(fichas[i / 4]);
			// Se asigna el nombre a la etiqueta (para reconocer eventos).
			lblFichas[i].setName(auxNombre);
			// Colocamos las fichas en la casilla de casa de su color.
			numCasilla = Colores.getColor(i / 4).getCasillaCasa();
			lblFichas[i].setBounds(posiciones.get(numCasilla).get(3 + (i % 4)).x, 
					posiciones.get(numCasilla).get(3 + (i % 4)).y, 35, 35);
			// Se añade el listener para captar los clics de ratón.
			lblFichas[i].addMouseListener(controlador);
			// Se deshabilita la ficha y se hace invisible.
			lblFichas[i].setEnabled(false);
			lblFichas[i].setVisible(false);
			// Se añade la ficha al panel principal.
			pane.add(lblFichas[i]);
		}
	}
	
	public void inicializarFichas(int numJugs) {
		Boolean habilitar;
		int numCasilla;
		
		// Recorremos los índices de las fichas
		// que hay que comprobar en función del
		// número de jugadores conectados.
		for (int i = 0; i < (numJugs * 4); i++) {
			habilitar = false;
			if (((index + 1) == numJugs) || (index == -1))
				// Si es el último jugador conectado
				// o existe sólo una vista para jugar
				// en modo local, siempre se habilitarán
				// todas las fichas.
				habilitar = true;
			else if (((i / 4) + 1) == numJugs)
				// En caso contrario, sólo se crearán
				// las fichas del último jugador conectado
				habilitar = true;
				
			if (habilitar) {
				if ((i % 4) == 0) {
					// Si se trata de la primera ficha de cada color,
					// obtenemos el número de su casilla de salida.
					numCasilla = Colores.getColor(i / 4).getCasillaSalida();
					// Se coloca la ficha en la casilla de salida.
					lblFichas[i].setBounds(posiciones.get(numCasilla).get(0).x, 
							posiciones.get(numCasilla).get(0).y, 35, 35);
				}
				// Habilitamos la ficha y la visualizamos.
				lblFichas[i].setEnabled(true);
				lblFichas[i].setVisible(true);
			}
		}
	}
	
	/**
	 * Realiza acciones previas sobre algunos controles de la vista
	 * antes de que se inicie el juego.
	 */
	public void inicioJuegoVista() {
		// Deshabilitamos los botones 'partida', 'online',
		// y el ComboBox 'cmbJugadores' para que el usuario
		// no pueda pulsarlos ni interaccionar, al haberse
		// iniciado ya la partida (LOCAL o ONLINE).
		partida.setEnabled(false);
		online.setEnabled(false);
		cmbJugadores.setEnabled(false);
	}
	
	/**
	 * Getter de la etiqueta que contiene
	 * la imagen del dado.
	 * @return dado (JLabel)
	 */
	public JLabel getDado() {
		return dado;
	}
	
	/**
	 * Getter del número de jugadores que hay
	 * seleccionados en el ComboBox, como entero.
	 * @return cmbJugadores.getSelectendIndex() + 2 (int)
	 */
	public int getJugadores(){
		return cmbJugadores.getSelectedIndex() + 2;
	}
	
	/**
	 * Cambia la imagen del dado por la del número que se le pasa
	 * como parámetro.
	 * @param num número que ha sacado el jugador.
	 */
	public void tirarDado (int num){
		ImageIcon nuevoDado = new ImageIcon(rutaImg + ParchisClienteVista.imgDados[num-1]);
		getDado().setIcon(nuevoDado);
	}

	/**
	 * Cambia el color de turnoColor para indicar de quién es el turno
	 * @param color color que se va a mostrar.
	 */
	public void cambiarTurno (Colores color){
		Color color_asignar;
		switch(color)
		{
			case AMARILLO:
				color_asignar = Color.YELLOW;
				break;
			case AZUL:
				color_asignar = Color.BLUE;
				break;
			case ROJO:
				color_asignar = Color.RED;
				break;
			case VERDE:
				color_asignar = Color.GREEN;
				break;
			default:
				color_asignar = Color.GRAY;
				break;
		}
		
		turnoColor.setBackground(color_asignar);
	}
	
	/**
	 * Muestra un mensaje para el usuario remplazando el mensaje anterior
	 * @param mensaje mensaje que se va a mostrar
	 */
	public void mostrarMensajes(final String mensaje){
		SwingUtilities.invokeLater(
			new Runnable(){
				public void run(){ // Actualiza display
					display.append(mensaje + "\n");// Añade el mensaje al display
					display.setCaretPosition(display.getText().length());// Mueve el scroll a la parte final
				}// fin del método run
			}// fin de la clase anónima interna
		);// fin de la llamada SwingUtilities.InvokeLater
	}
	
	
	/**
	 * Este método mueva la ficha a la tantas casillas como indique el valor obtenida por el dado o 
	 * las posiciones correspondientes por comer ficha o entrar ficha en meta
	 * @param ficha
	 * @param x
	 * @param y
	 */
	public void moverFicha(JLabel ficha, int x, int y){
		ficha.setBounds(x, y, 35, 35);
	}
	

	/**
	 * Este método mueva la ficha a la tantas casillas como indique el valor obtenida por el dado o 
	 * las posiciones correspondientes por comer ficha o entrar ficha en meta
	 * @param ficha
	 * @param id identificador de la casilla
	 */
	public void moverFicha(JLabel ficha, int id){
		int x = posiciones.get(id).get(1).x;
		int y = posiciones.get(id).get(1).y;
		ficha.setBounds(x,y, 35, 35);
	}
	
	//metodo sobrecargado
	/**
	 * Metodo sobrecargado que solo se mueve si la ficha es movible.
		 * Este valor es un boolean que se pasa como parámetro
		 * @param ficha
		 * @param x
		 * @param y
		 * @param esMovible
		 */
	public void moverFicha(JLabel ficha, int x, int y, boolean esMovible){
			
			if (esMovible)
				moverFicha(ficha, x, y);
		}
	
	
	//metodo sobrecargado
	/**
	 * Metodo sobrecargado que solo se mueve si la ficha es movible.
	 * Este valor es un boolean que se pasa como parámetro
	 * @param ficha
	 * @param x
	 * @param y
	 * @param esMovible
	 */
	public void moverFicha(JLabel ficha, int id, boolean esMovible){
		//tengo que relacionar el metodo esmovible con moverFicha
		//esMovible recibe por parámetro una Ficha y un int correspondiente al avance 
		//devuelve un boolean que se utiliza como entrada para moverFicha
		if (esMovible)
			moverFicha(ficha, id);
	}
	
	/**
	 * Metodo que debuelve la etiqueta de la ficha
	 * @return
	 */
	public JLabel getlblFichas(){
		return lblFichas[this.getX()];
	}
	/**
	 * Metodo que static devuelve la etiqueta de la ficha
	 * @return
	 */
	public JLabel[] getLblFichas() {
		return lblFichas;
	}
	/**
	 * Metodo que establece la etiqueta para las fichas
	 * @param lblFichas
	 */
	public void setLblFichas(JLabel lblFichas[]) {
		this.lblFichas = lblFichas;
	}
	
	/**
	 * Se come una ficha del tablero. Es decir, la manda a su casa.
	 * Para ello, simplemente cambiamos la posición de la ficha.
	 * @param i identificador de la ficha (valor entre 0-16)
	 */
	public void comerFicha (Ficha ficha, Casilla destino){
		this.moverFicha(destino, ficha);
	}
	
	/**
	 * Método que obtener el atributo private
	 * Ficha ficha
	 * @return
	 */
	public Ficha getFicha() {
		return ficha;
	}
	/**
	 * Metodo para establecer el atributo Ficha ficha
	 * @param ficha
	 */
	public void setFicha(Ficha ficha) {
		this.ficha = ficha;
	}
	/**
	 * Metodo para obtener el Label de una ficha a partir de una ficha
	 * @param ficha
	 * @return JLabel ficha a mover
	 */
	public JLabel getFichaText(Ficha ficha){
		return getLblFichas()[ficha.getIdentificador()];
	}
	
	public void setDisplay(final String mensaje){
		SwingUtilities.invokeLater(
				new Runnable(){
					public void run(){ // Actualiza display
						display.append(mensaje);
					}// fin del método run
				}// fin de la clase anónima interna
		);// fin de la llamada SwingUtilities.InvokeLater
	}

		
	/**
	 * metodo para obtener la posición de una casilla
	 */
	public ArrayList<Point> getPosicionCasilla(int num){
		return posiciones.get(num);
	}
	
	/**
	 * Metodo sobrecargado que permite mover la ficha a la casilla de destino
	 * se situa en la casilla de destino en una posición centrada si está vacia
	 * o a la izquierda si el la primera ficha en llegar o a la derecha si es la 
	 * segunda ficha en llegar
	 * @param destino
	 * @param ficha
	 */
	public void moverFicha(Casilla destino, Ficha ficha) {
		// TODO Auto-generated method stub
		JLabel fichaJL = new JLabel();
		fichaJL = getLblFichas()[ficha.getIdentificador()-1];//obtiene la ficha JLabel desde el valor de ficha
		//switch
		int pos = 0; 
		
		if((destino.getNumero() > 0 & destino.getNumero() <=68) || 
			(destino.getNumero() > 100 & destino.getNumero() < 108)||
			(destino.getNumero() > 200 & destino.getNumero() < 208)||
			(destino.getNumero() > 300 & destino.getNumero() < 308)||
			(destino.getNumero() > 400 & destino.getNumero() < 408)){
			if(destino.getTieneFicha() == 1 )
				pos = 0;//si en casilla hay una unica ficha se coloca en el centro
				//System.out.println("Solo hay una ficha en la casilla " + 
				//destino.getNumero());
				
			
			if(destino.getTieneFicha() == 2){
				//si en la casilla hay dos fichas la que estaba inicialmente se coloca en pos 1
				pos = 1;
				int posicionX = (int)this.getPosicionCasilla(destino.getNumero()).get(pos).getX();
				int posicionY = (int)this.getPosicionCasilla(destino.getNumero()).get(pos).getY();
				JLabel fichaJL1 = new JLabel(); 
				fichaJL1 = getLblFichas()[destino.getFicha1().getIdentificador()-1];
				this.moverFicha(fichaJL1, posicionX, posicionY );
				//la que llega se coloca en posicion 2
				pos = 2;
			}
				//System.out.println("Hay dos fichas en la casilla " + 
				//destino.getNumero() );
		}else if (destino.getNumero() == 100 ||
				  destino.getNumero() == 200 ||
				  destino.getNumero() == 300 ||
				  destino.getNumero() == 400 ||
				  destino.getNumero() == 108 ||
				  destino.getNumero() == 208 ||
				  destino.getNumero() == 308 ||
				  destino.getNumero() == 408){
					if(destino.getTieneFicha()==1)
						//si se trata de una casilla casa o meta y no hay ficha
						//se escogen la coordenadas de la sexta posicion
						pos = 6;
						//System.out.println("Solo hay una ficha en la casilla " + 
						//destino.getNumero());
					if(destino.getTieneFicha()==2)
						//si hay una ficha se escogen las coordenadas de la quinta
						//posicion
						pos = 5;
						//System.out.println("Hay dos fichas en la casilla " + 
								//destino.getNumero());
					if(destino.getTieneFicha()==3)
						//si hay dos fichas se escogen las coordenadas de la cuarta
						//posicion
						pos = 4;
						//System.out.println("Hay tres fichas en la casilla " + 
							//	destino.getNumero());
					if(destino.getTieneFicha()==4)
						//si hay tres fichas se escogen las coordenadas de la tercera
						//posicion
						pos = 3;
						//System.out.println("Hay cuatro fichas en la casilla " + 
							//	destino.getNumero());
		}
		int posicionX = (int)this.getPosicionCasilla(destino.getNumero()).get(pos).getX();
		int posicionY = (int)this.getPosicionCasilla(destino.getNumero()).get(pos).getY();
		this.moverFicha(fichaJL, posicionX, posicionY );
	}

	public JTextArea getDisplay() {
		return display;
	}

	public void setDisplay(JTextArea display) {
		this.display = display;
	}

	public int setDisplay(int i) {
		// TODO Auto-generated method stub
		return i;
	}
	
	/**
	 * Gestiona el cierre de la vista por motivo de que un jugador
	 * o el servidor hayan finalizado la partida.
	 * @param esServidor (Boolean) -> Indica si es el servidor quien finaliza la partida.
	 * @param index (int) -> Indice del cliente que abandonado la partida.
	 */
	public void cerrarVista(Boolean esServidor, int index) {
		String mensaje = "";
		Boolean mostrarMensaje = false;
						
		if (esServidor) {
			// Si la finalización la ha ordenado el servidor.
			mensaje = rb.getString(ParchisI18Keys.ABANDONASERVIDOR);
			mostrarMensaje = true;
		}
		else if (index != this.index) {
			// Si ha abandonado un jugador distinto del de la vista,
			// primero mostramos un mensaje informativo
			mensaje = rb.getString(ParchisI18Keys.ABANDONACLIENTE) + " " +
					Colores.getColor(index).toString();
			mostrarMensaje = true;
		}
		else
		{
			mensaje = rb.getString(ParchisI18Keys.ABANDONAJUGADOR);
			mostrarMensaje = true;
		}
		conexion_servidor = false;
		if (mostrarMensaje){
			//JOptionPane.showMessageDialog(null, mensaje, 
			//rb.getString(ParchisI18Keys.FINDEPARTIDA),
			//JOptionPane.INFORMATION_MESSAGE);
			String mensaje2 = rb.getString(ParchisI18Keys.FINDEPARTIDA);
			this.mostrarMensajes(mensaje);
			this.mostrarMensajes(mensaje2);
		}
		// Se elimina la vista, y se cierra el UI.
		//this.setVisible(false);
		//this.dispose();
		//System.gc();
		abandonar.setEnabled(false);
		if (online.isEnabled())
			online.setEnabled(false);
	}
	
	
	
	//metodo main para prueba
	public static void main(String[] args) {
		Locale loc = new Locale("es");
		ParchisServer parchis = new Parchis();
		ParchisGUI controlador = new ParchisGUI(loc, parchis, -1);
		ParchisClienteVista vista2 = new ParchisClienteVista(loc, controlador, -1); 
	}

	public boolean getBotonAbandonar() {
		// TODO Auto-generated method stub
		return abandonar.isEnabled();
	}
	
	public boolean getBotonOnline() {
		// TODO Auto-generated method stub
		return online.isEnabled();
	}
	
	public boolean getBotonNuevo() {
		// TODO Auto-generated method stub
		return partida.isEnabled();
	}

	public boolean getConexion_servidor() {
		return this.conexion_servidor;
	}
}