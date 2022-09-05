package es.uned2013.parchis.ui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.rmi.RemoteException;
import java.rmi.UnmarshalException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import es.uned2013.parchis.Casilla;
import es.uned2013.parchis.Colores;
import es.uned2013.parchis.Ficha;
import es.uned2013.parchis.ParchisClienteVista;
import es.uned2013.parchis.Tablero;
import es.uned2013.parchis.rmi.server.ParchisServer;

/**
 * Gestiona la interacción con los jugadores en el modo GUI
 * Al implementar de ParchisUI, por la propia estructura, es necesario que todos los métodos
 * estén definidos, incluso cuando no se utilicen.
 * Preparada para una posible ampliación futura.
 * 
 * Relacion entre ParchisGUI y ParchisClienteVista:
 * ParchisClienteVista que crea la interfaz gráfica del tablero
 * ParchisGUI que controla los eventos
 * PruebaParchisGUI que contiene el metodo main
 * La forma de relacionar las tres clases es mediante 
 * la declaración de un atributo ParchisGUI controlador en ParchisClienteVista 
 * el cual se inicializa pasandole como atributo el parámetro ParchisGUI this
 * A su vez en ParchisGUI se declara un atributo ParchisClienteVista vista
 * que se pasa como parametro al constructor
 */
public class ParchisGUI extends WindowAdapter implements ParchisUI, MouseListener {
	//cambio implements WindowListener por  extends WindowAdapter para implementar solo
	//el metodo windowClosing de la Intefaz WindowListener y no tener que implementar 
	//todos los metodos
	//

	private ResourceBundle rb = null;
	private ParchisServer parchis= null;
	private ParchisClienteVista vista;	//de la antigua clase ParchisClienteControlador 
	private int index; // Indice del cliente.
	private BlockingQueue<String> queue_tirarDado = new ArrayBlockingQueue<String>(1);
	private BlockingQueue<Integer> queue_pinchaFicha = new ArrayBlockingQueue<Integer>(1);
	private Thread tjuego = null; // Referencia al hilo del juego del parchís
	/**
	  * Constructor de la clase. Inicializa parchis
	  * @param parchis
	  */
	 public ParchisGUI(Locale loc, ParchisServer parchis, int index){ 
		 this.rb = ResourceBundle.getBundle("Mensajes", loc);
		 this.parchis = parchis;
		 this.index = index;
		 vista = new ParchisClienteVista(loc, this, index);
		 this.index = index;
	 }
	
	 /**
	  * Constructor sobrecargado de la clase. Inicializa la
	  * interfaz ParchisClienteVista
	  * Estaba en la antigua clase ParchisClienteControlador
	  */
	 public ParchisGUI(ParchisClienteVista vista) {
		 this.vista = vista;
	 }
		
	 
	 /*
	  * Todos los override en métodos en esta clase sobrecargan a los métodos de la clase
	  * ParchisUI.
	  */
	
	 /**
	  * Se encarga de mostrar el mensaje de entrada.
	  * @param mensaje Mensaje a mostrar
	  */
	@Override
	public void mostrarMensaje(String mensaje) throws RemoteException {
		
		vista.mostrarMensajes(mensaje);
	}
	
	/**
	  * Se encarga de mostrar el mensaje de entrada y una cadena de entrada aparte.
	  * Este método se crea por la necesidad de independizar el interfaz ParchisUI de la 
	  * presentación final. Independizar el mensaje a mostrar con la forma en que se presenta.
	  * @param mensaje Mensaje a mostrar
	  * @param cadena cadena de caracteres a añadir al mensaje
	  */
	@Override
	public void mostrarMensajeString(String mensaje, String cadena)  throws RemoteException{
		
		vista.mostrarMensajes(mensaje + " " + cadena + ".");
	}
	
	/**
	  * Se encarga de mostrar el mensaje de entrada y un entero aparte.
	  * Este método se crea por la necesidad de independizar el interfaz ParchisUI de la 
	  * presentación final. Independizar el mensaje a mostrar con la forma en que se presenta.
	  * @param mensaje Mensaje a mostrar
	  * @param numero entero a añadir al mensaje
	  */
	@Override
	public void mostrarMensajeInteger(String mensaje, int numero)  throws RemoteException{
		
		String mensajeCompuesto = String.format(mensaje + " " + numero + ".");
		vista.mostrarMensajes(mensajeCompuesto);
	}
	
	/**
	  * Gestiona la recepción de valores enteros por pantalla y posibles excepciones en la
	  * entrada.
	  * Se introduce para introducir el valor de la ficha
	  * @param mensaje Mensaje a mostrar para pedir valor a introducir
	  * @return entero resultado válido de la comprobación
	  */
	@Override
	public int solicitarEntero(String mensaje)  throws RemoteException {
	
		int resultado = 0;
		//mensaje que pide al jugador que pinche sobre la ficha
		vista.mostrarMensajes(mensaje);
		queue_pinchaFicha.clear();
		try {
			  resultado = queue_pinchaFicha.take();
	     }
	     catch (InterruptedException e) {
	     }
		
		return resultado;
	}
	
	/**
	  * Gestiona la recepción de cadenas por pantalla. Se utilizaría en el caso de solicitar
	  * el nombre de los jugadores por pantalla, por ejemplo.
	  * Tambien se puede utilizar para pedir que pulse sobre el dado
	  * @param mensaje Mensaje a leer del usuario
	  * @return String resultado válido de la comprobación.
	  * @throws InterruptedException 
	  */
	@Override
	public String solicitarCadena(String mensaje) throws RemoteException, InterruptedException{
		
		String resultado = "";
		//muestra el mensaje solicitando al jugador que pulse el dado
		vista.mostrarMensajes(mensaje);
		queue_tirarDado.clear();
		 try {
			  resultado = queue_tirarDado.take();
	     }
	     catch (InterruptedException e) {
	     }
		
		return resultado;
	}
	
	/**
	  * Muestra la tirada de cada jugador por pantalla.
	  * @param nombre Nombre del jugador
	  * @param tirada Valor de la tirada
	  */
	@Override 
	public void mostrarTirada(String nombre, int tirada)  throws RemoteException{
		vista.tirarDado(tirada);
	}
	
	/**
	  * Muestra la situación del tablero para todos los jugadores.
	  * En modo gráfico se utiliza para inicializar visualmente las fichas.
	  * @param tablero (Tablero)
	  */
	@Override
	public void mostrarTablero(Tablero tablero)  throws RemoteException {
		if (index >= 0)
			vista.inicializarFichas(parchis.getUIList().size());
		else
			vista.inicializarFichas(parchis.getJugadores().size());
	}
	
	/**
	  * Muestra la información de la ficha entrada.
	  * @param Ficha ficha a sacar por pantalla
	  */
	@Override
	public void mostrarFicha(Ficha ficha)  throws RemoteException {
		// TODO Auto-generated method stub
		//-----------------NO ES NECESARIO AL ESTAR EN MODO GRAFICO
		vista.moverFicha(vista.getlblFichas(), 
				(int)vista.getPosicionCasilla(parchis.getTablero().getCasilla(ficha.getCasillaActual()).getNumero()).get(0).getX(), 
				(int)vista.getPosicionCasilla(parchis.getTablero().getCasilla(ficha.getCasillaActual()).getNumero()).get(0).getY());
	}
	//NO
	/**
	 * Presenta información de cómo se mueve una ficha.
	 * @param Casilla_old destino
	 * @param Ficha ficha
	 */
	@Override
	public void moverFicha(Casilla destino, Ficha ficha)  throws RemoteException {
		vista.moverFicha(destino, ficha);
	}
	//FRANCISCO
	/**
	 * Presenta un mensaje informando de la eliminación de una ficha por otro jugador.
	 * @param Ficha ficha
	 */
	@Override
	public void comerFicha(Ficha ficha, Casilla destino)  throws RemoteException {
		vista.comerFicha(ficha, destino);
	}

	/**
	* Cambia el color de la etiqueta que identifica el color del jugador en turno.
	* @param color
	*/
	@Override
	public void cambiarTurno(Colores color) throws RemoteException {
			
		vista.cambiarTurno(color);
	}
	
	/**
	 * Gestiona las acciones previas a realizar sobre los controles del
	 * de la vista cuando comienza el juego del parchís.
	 * @throws RemoteException
	 */
	@Override
	public void inicioJuegoUI() throws RemoteException {
		vista.inicioJuegoVista();
	}
	
	/**
	 * Retorna el modo de interfaz (GUI)
	 * @return ParchisUIMode.GUI (ParchisUIMode)
	 * @throws RemoteException
	 */
	@Override
	public ParchisUIMode getUIMode() throws RemoteException {
		return ParchisUIMode.GUI;
	}
	
	
	/**
	 * Gestiona el abandono de la partida por parte del jugador asociado
	 * al GUI, finalizando la ejecución del juego, y recorriendo los UIs 
	 * del resto de jugadores para cerrar sus vistas.
	 */
	private void abandonarJugadorGUI() throws RemoteException, InterruptedException {
		Thread t = new Thread() {
			public void run() {		
				try {
					BlockingQueue<ParchisUI> uiList = parchis.getUIList();
					
					// Si estamos en modo ONLINE
					if (index != -1) {
						// Recorremos el listado de clientes conectados al servidor
						// del parchís.
						for (int i = 0; i < uiList.size(); i++) {
							// Por cada UI, llamamos a abandonarPartida(). 
							// El primer parámetro a 'false' indica que ha 
							// abandonado el jugador (no el servidor), y le 
							// pasamos el índice del cliente.
							((ParchisUI)uiList.toArray()[i]).
							abandonarPartida(false, index);
						}
					}
					// Si estamos en modo LOCAL
					else
						abandonarPartida(false, index);
					if (parchis.isEnJuego()) {
						// Indicamos que ha finalizado la partida
						parchis.setEnJuego(false);
						// Interrumpimos el hilo del parchís.
						if (tjuego!=null)
								tjuego.interrupt();
					}
					parchis.abandonarPartida();
				} catch (RemoteException e){
					System.out.println("ERROR:" + e.getMessage());
					e.printStackTrace();
				}
			}
		};
		t.start();
	}
	
	/**
	 * Implementa el tratamiento de los eventos de ratón
	 * sobre la vista del tablero del parchìs.
	 */
	public void mouseClicked(MouseEvent arg0)  {
		
		
		if (arg0.getComponent().getName().equals("lblDado")){
			if (vista.getConexion_servidor()){
				//se borra la cola bloqueante tirarDado
				queue_tirarDado.clear();
				//al pulsar el dado se añade a la cola queue_tirarDado la peticion	
				try {
					queue_tirarDado.put("botonPulsado");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		else if (arg0.getComponent().getName().equals("btnPartidaNueva")){
			if (vista.getBotonNuevo()){
			tjuego = new Thread() {
				public void run(){		
					try{
						parchis.inicioJuego(vista.getJugadores());
					}catch (Exception e){
						System.out.println("ERROR:" + e.getMessage());
						e.printStackTrace();
					}
				}
			};
			tjuego.start();
		}}
		else if (arg0.getComponent().getName().equals("btnPartidaOnline")) {
			if (vista.getBotonOnline()){
			tjuego = new Thread() {
				public void run(){		
					try{
						parchis.inicioJuego(parchis.getUIList().size());
					}catch (UnmarshalException e){
						System.out.println("Error controlando el cierre");
					}catch (NullPointerException e){
						System.out.println("Error controlando el cierre");
					}
					catch (Exception e){
						System.out.println("ERROR:" + e.getMessage());
						e.printStackTrace();
					}
				}
			};
			try {
				if (parchis.getUIList().size() < 2){
					vista.mostrarMensajes(rb.getString(ParchisI18Keys.NUMEROINCORRECTO));
				}else{
					tjuego.start();
				}
								
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				System.err.println("ERROR:" + e.getMessage());
				e.printStackTrace();
			}
		}}
		else if (arg0.getComponent().getName().equals("btnFinPartida")){
			if (vista.getBotonAbandonar()){
			try {
				// Gestionamos el abandono del jugador.
				abandonarJugadorGUI();
			} catch (Exception e) {
				System.err.println("ERROR:" + e.getMessage());
				e.printStackTrace();
			}
		}}
		else {
			for (int i = 0; i < 16; i++) {
				if (arg0.getComponent().getName().equals("ficha_" + (i + 1))){
					if (vista.getConexion_servidor()){
					queue_pinchaFicha.clear();	
					try {
						queue_pinchaFicha.put(parchis.getFicha(i).getIdentificador());
					} catch (RemoteException e) {
						e.printStackTrace();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}}
				}
			}
		}
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}
	
	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override 
	public void windowClosing(WindowEvent arg0) { 
		 if (vista.getBotonAbandonar()==false){
		    	System.exit(0);}
		else{
		try {
			// Gestionamos el abandono del jugador.
			abandonarJugadorGUI();
			//System.exit(0);
		} catch (Exception e) {
			System.err.println("ERROR:" + e.getMessage());
			e.printStackTrace();
		}
	} }
	
	@Override
	public void abandonarPartida(Boolean esServidor, int index) throws RemoteException {
		// TODO Auto-generated method stub
		// Si el juego está en marcha.
		vista.cerrarVista(esServidor, index);
	}
}
