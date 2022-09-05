package es.uned2013.parchis;


import es.uned2013.parchis.server.databaseDriver.insertaDatos;
import es.uned2013.parchis.server.gui.marcoEstadistico;

import java.awt.Toolkit;
import java.io.Serializable;
import java.net.SocketException;
import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
import java.rmi.UnmarshalException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import javax.swing.JFrame;

import es.uned2013.parchis.rmi.server.ParchisServer;
import es.uned2013.parchis.ui.MultipleUI;
import es.uned2013.parchis.ui.ParchisConsola;
import es.uned2013.parchis.ui.ParchisGUI;
import es.uned2013.parchis.ui.ParchisI18Keys;
import es.uned2013.parchis.ui.ParchisI18NUI;
import es.uned2013.parchis.ui.ParchisUI;
import es.uned2013.parchis.ui.ParchisUIMode;

/**
 * Clase principal del programa. Configura el juego y 
 * establece el valor de las variables principales
 * mediante un diálogo con el usuario a través de la
 * consola.
 * @author alef
 */

public class Parchis implements ParchisServer,Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Ficha> fichasJuego = new ArrayList<Ficha>(); //Fichas de la partida actual.
	private ArrayList<Jugador> jugadores = new ArrayList<Jugador>(); //Jugadores de la partida actual.
	private Jugador jugadorTurno = null; //Jugador en turno. Va cambiando rotativamente a lo largo de la partida.
	private Tablero tablero; //Tablero de la partida actual. 
	private ArrayList<Colores> coloresJuego = new ArrayList<Colores>(); //Colores de la partida actual.
	private insertaDatos dataBase = new insertaDatos();
	private String idPartida = "0";
	private marcoEstadistico marcoPanel;
	
	//Variables para el test de integración de JUnit
	/*Public y Protected para evitar generar métodos únicamente validos para las pruebas
	 *Si la prueba se encontrase en el mismo paquete no sería necesaria ninguna variable 'public'
	 *De esta manera queda más claro el código y la estructura del proyecto. 
	 */
	protected int jugadas; //Nº de jugadas en la partida actual.
	private static Modo modo = Modo.NORMAL; //Modo de ejecución NORMAL o PRUEBA
	public static int jugadaLimitePrueba = -1; //El juego se pararÃ¡ en el nÂº de jugada especificado. (-1) no se para
	protected final static Iterator<Integer> listaTestTirada = Arrays.asList(5,4,3,2,4,5,6,3,1, //Tiradas realizadas en la partida Test
			5,4,5,5,3,5,5,5,6,4,5,5,5,6,4,3,5,4,3,4,5,6,6,4,5,1,4,1/*37*/,4,4,1,3,
			6,4,4,4,5,6,3,4,6,3,5/*52*/,5,6,2,6,6,1,5,4,6,6,6,5,6,6,5,6,2,5,6,5,6,6,3
			,2,4,3,4/*79*/,6,6,5,6,5,6,5,6,6,6/*89*/,6,6,5,2,2,6,6,5,3,5,6,4,6,5/*103*/
			,2,6,4,2,6,6,4,6,6,2,3,5,6,1/*117*/,6,6,1,5,5,5,6,4,6,6,5,2,5,3).iterator();
	protected final static Iterator<Integer> listaTestEligeFicha = Arrays.asList(1,6,1,2,1,10,14, //Fichas seleccionadas en la partida Test
			3,1,7,13,1,5,11,14,14,14,8,9,13/*37*/,3,6,9,14,3,3,5,12,11,10,3,3,10,10,14/*52*/
			,6,6,11,11,11,14,1,1,5,6,13,13,3,3,1,9,16,16,16,3,1,6,10,14,14/*79*/
			,1,3,4,2,11,15,14/*89*/,3,4,2,6,9,16,16,16,13,2,2,10,10,14,15/*103*/
			,4,4,5,5,10,10,15,15,15,3,3,2,4,6,16,15/*117*/,2,2,2,2,5,5,10,9).iterator();
	
	// Variable de interfaz de usuario del parchís
	private static ParchisUI ui = null;
	// Variable que almacena el modo de interfaz de usuario
	private static ParchisUIMode uiMode = ParchisUIMode.GUI;
	// Modo de juego: LOCAL o ONLINE
	private static ModoJuego modoJuego = ModoJuego.LOCAL;
	// Lista de interfaces de jugadores conectados al servidor del parchís
	private BlockingQueue<ParchisUI> uiList = new ArrayBlockingQueue<ParchisUI>(4);
	
	private Registry registry; // Registro RMI
	
	// Variable booleana que indica si se está jugando una partida de parchis.
	private Boolean enJuego = false;

	public Parchis(){

		/**
		 * CONSTRUCTOR. Según la clase Driver proporcionada, sin argumentos.
		 **/
		
		// Inicializamos el registro de RMI
		try {
			registry = LocateRegistry.createRegistry(1099);
		} catch (RemoteException e) {
			System.err.println("ERROR:" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo para asignar el modo grafico.
	 */
	public void setUI(ParchisUIMode mode, Locale loc){
		ParchisUI inner = null;
		
		switch(mode){
			// interfaz con entrada/salida de datos por consola
			case CONSOLE:
				inner = new ParchisConsola(this); 
				uiMode = ParchisUIMode.CONSOLE;
				break;
			// interfaz con entrada/salida de datos en modo gráfico
			default:		
				inner = new ParchisGUI(loc, this, -1);
				uiMode = ParchisUIMode.GUI;
				break;
		}
		
		/* Si no hay información del contexto local del usuario
		 * se carga el idioma por defecto.
		 * Si la hubiera se busca un archivo 'properties' en el 
		 * idioma de su contexto local, si no estuviese disponible
		 * automáticamente se seleccionaría el idioma por defecto.
		 */
		if (loc == null) {
			ui = inner;
		}else{
			ui = new ParchisI18NUI(inner, loc, this, -1);
		}
	}
	
	/**
	 * Getter de ui. Devuelve la interfaz de comunicación
	 * en la que se desarrolla el programa. Y a través de
	 * ella se mostraran los datos a través de consola o
	 * de manera gráfica.
	 * @return ui (ParchisUI)
	 */
	public static ParchisUI getUI(){
		return ui;
	}
	
	/**
	 * Setter de uiMode. Establece el modo de interfaz de
	 * comunicación del parchís: CONSOLE o GUI
	 * @param uiMode (ParchisUIMode)
	 */
	public static void setUIMode(ParchisUIMode uiMode) {
		Parchis.uiMode = uiMode;
	}
	
	/**
	 * Getter de uiMode. Devuelve el modo de interfaz de
	 * comunicacion del parchís: CONSOLE o GUI
	 * @return uiMode (ParchisUIMode)
	 */
	public static ParchisUIMode getUIMode() {
		return uiMode;
	}
	
	/**
	 * Setter del modo de juego: ONLINE o LOCAL
	 * @param modoJuego (ModoJuego)
	 */
	public static void setModoJuego(ModoJuego modoJuego) {
		Parchis.modoJuego = modoJuego;
	}
	
	/**
	 * Getter del modo de juego: ONLINE o LOCAL
	 * @return modoJuego (ModoJuego)
	 */
	public static ModoJuego getModoJuego() {
		return modoJuego;
	}
	
	/** 
	 * Establece el número de jugadores del juego, inicializa tablero,lanza la primera 
	 * tirada y elige el primer jugador. Cuando este método finaliza, ya está 
	 * establecido el entorno para poder jugar con normalidad.
	 * @param jugs número de jugadores
	 * 
	 */
	public int numJugadores()  throws RemoteException{
		int jugs=0;
		
		// muestra el mensaje de cabecera dando la bienvenida
		ui.mostrarMensaje(ParchisI18Keys.BIENVENIDA);
		
		
		//obtiene el número de jugadores introducidos por consola
		jugs = ui.solicitarEntero(ParchisI18Keys.PREGUNTARNUMERO);
		
		/* Si el número de jugadores no es 2,3 o 4 informa al 
		 * usuario y le pide de nuevo el número de jugadores.*/
		 while ((jugs < 2) || (jugs > 4)){
			//vuelvo a pedir el número de jugadores, pues es incorrecto
			ui.mostrarMensaje(ParchisI18Keys.NUMEROINCORRECTO);
			jugs = ui.solicitarEntero(ParchisI18Keys.PREGUNTARNUMERO);
		}
		
		return jugs;		
	}
	
	
	/**
	 * Se encarga de llamar a los distintos procedimientos para 
	 * gestionar el juego general.
	 * @throws SocketException 
	 */
	@Override
	public void inicioJuego(int jugs) throws RemoteException, InterruptedException,
		ClassNotFoundException, SocketException {
		/* Selecciona un color para cada jugador y los almacena
		 * en la variable coloresJuego.
		 */
		
		// Borramos los arrays por si no estaban vacíos.
		
		
		enJuego = true; // Comienza la partida.
				
		this.coloresJuego.addAll(EnumSet.range(Colores.AMARILLO, Colores.values()[jugs - 1]));
	    
		 /*  
		 * Crea las fichas a partir de los colores o jugadores 
		 * en juego creando un identificador único para cada
		 * una de ellas. (1-AMARILLO,2-AMARILLO,3-AMARILLO,
		 * 4-AMARILLO,5-AZUL,6-AZUL,7-AZUL,8-AZUL,9-ROJO,10-ROJO,
		 * 11-ROJO,12-ROJO,13-VERDE,14-VERDE,15-VERDE,16-VERDE).
		 * y las almacena en el atributo fichasJuego.
		 */
		System.out.println("En el parchis-------------------------->>>>>>>>>>>>");
		for (int i = 0; i < (coloresJuego.size()); i++) {
			for (int j = (4 * i); j < ((4 * i) + 4); j++) {
				if (j == (4 * i)){ // La 1ª ficha de cada color en la casilla de salida
					this.fichasJuego.add(new Ficha(Colores.values()[i],j + 1,
							Colores.values()[i].getCasillaSalida(),
							Colores.values()[i].getCasillaSalida(),
							Colores.values()[i].getCasillaCasa(),
							Colores.values()[i].getCasillaFinal()));
					
				}
				else{ // ...el resto en casita.
					this.fichasJuego.add(new Ficha(Colores.values()[i],j + 1,
							Colores.values()[i].getCasillaCasa(),
							Colores.values()[i].getCasillaSalida(),
							Colores.values()[i].getCasillaCasa(),
							Colores.values()[i].getCasillaFinal()));
				}
			}
		}
			
		/*   
		 * Crea el tablero.
		 */
		this.tablero = new Tablero(fichasJuego);
		
		
		/*  
		 * Crea los jugadores y les pasa sus fichas, el nombre
		 * del jugador puede ser el del color de sus fichas.
		 * También les pasa una referencia al tablero de juego
		 * actual.
		 */
		for (int i = 0; i < coloresJuego.size(); i++) {
			this.jugadores.add(new Jugador(this.tablero, Colores.getColor(i).toString(),
					Colores.getColor(i), fichasJuego));
			// Si el modo de juego es ONLINE, asociamos a cada jugador su
			// UI correspondiente. Si es LOCAL, asociamos un único UI del 
			// parchís a todos los jugadores.
			if (modoJuego.equals(ModoJuego.ONLINE))
				this.jugadores.get(i).setUI((ParchisUI) uiList.toArray()[i]);
			else
				this.jugadores.get(i).setUI(ui);
		}
		
		// Si el modo de juego es ONLINE, asociamos los UIs de todos los 
		// jugadores al UI del parchís.
		if (modoJuego.equals(ModoJuego.ONLINE))
			ui = new MultipleUI(this);
		
		// Se realizan acciones previas sobre los controles del interfaz
		ui.inicioJuegoUI();
		
		// Si el modo de juego es LOCAL y el modo de interfaz es GUI,
		// llamamos una vez a mostrarTablero() para que se muestren
		// las fichas de los jugadores que participan en la partida
		if (modoJuego.equals(ModoJuego.LOCAL) && 
				uiMode.equals(ParchisUIMode.GUI))
			ui.mostrarTablero(tablero);
		// 1 añado condicion modo local prueba para obtener los tableros de todos los jugadores
		//if (modo.equals(Modo.PRUEBA)) 
			//ui = new MultipleUI(this);
		 
		try {
			if (modo.equals(Modo.NORMAL)) 
				// En modo normal se elige quién empieza a través de una tirada inicial
				elegirPrimero();
			else
			   // En Modo PRUEBA empieza siempre el jugador AMARILLO
			   jugadorTurno = jugadores.get(0);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			System.out.println("Problema con la conexión");
		}catch (UnmarshalException e) {
			// TODO Auto-generated catch block
			System.out.println("Error controlando el cierre de los clientes");
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ui.cambiarTurno(jugadorTurno.getColor());
		
		try{jugar();
		} catch (SocketException e1) {
			// TODO Auto-generated catch block
			System.out.println("Problema con la conexión");
		}catch (UnmarshalException e1) {
			// TODO Auto-generated catch block
			System.out.println("Error controlando el cierre de los clientes");
		}catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}// Comienza el juego*/
	}//end inicioJuego()
	
	/**
	 * Se encarga de llamar a los distintos procedimientos para 
	 * gestionar el juego general.
	 * @throws InterruptedException 
	 * @throws ClassNotFoundException 
	 * @throws SocketException 
	 *
	 */
	public void jugar()  throws RemoteException, InterruptedException, ClassNotFoundException, SocketException{
		
	   // Mientras no finalice el juego
		//realizamos la llamada para crear las tablas de la base de datos.
		dataBase.creaTablas();
		//creamos los datos de la partida actual.
		idPartida = dataBase.insertaPartidaNueva(jugadores.size());
		//lanzamos el entorno grafico del servidor
		int idTirada =0;
		try {
			marcoPanel = new marcoEstadistico(idPartida);
			//marcoPanel.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	    	Toolkit tk = Toolkit.getDefaultToolkit();  
	    	int xSize = ((int) tk.getScreenSize().getWidth());  
	    	int ySize = ((int) tk.getScreenSize().getHeight());  
	    	marcoPanel.setSize(xSize,ySize); 
	    	marcoPanel.setVisible( true ); // muestra el marco   
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		while (!(tablero.juegoFinalizado) &&
				(jugadas != Parchis.jugadaLimitePrueba)) { // para modo PRUEBA	
			jugadorTurno.tirarDado(); // Se tira el dado
			
			if (modo.equals(Modo.PRUEBA)) {jugadas++;
				Thread t = Thread.currentThread();
				System.out.println("Current Thread: " + t);
				try{
					Thread.sleep(1000);
				}catch(InterruptedException e){
					System.out.println("Hilo principal interrumpido");
				}// Solo en modo PRUEBA
			}
							
			do {
				//Solo hace falta mostar tablero en modo consola
				//por lo que se añade esta condicion
				if (uiMode.equals(ParchisUIMode.CONSOLE))//
					ui.mostrarTablero(tablero); // Se muestra la posiciÃ³n actual de todas las fichas.
				jugadorTurno.mover(); // Se mueve
				dataBase.insertaTirada(idPartida, Integer.toString(jugadorTurno.getColorId()), Integer.toString(idTirada), Integer.toString(jugadorTurno.getFichaTurno().getIdentificador()), Integer.toString(jugadorTurno.getIdCasillaAnterior()) ,Integer.toString(jugadorTurno.getFichaTurno().getCasillaActual()), Integer.toString(jugadorTurno.getTirada()));
				idTirada++;
			} while ((jugadorTurno.getMovimientoAdicional()) && (enJuego)); // Si se ha comido o ha llegado a la casilla final alguna ficha se mueve otra vez
			
		   // Si no ha finalizado el juego y el jugador actual no tiene que tirar otra vez...
			if (!(tablero.juegoFinalizado) && !(jugadorTurno.getTiroOtraVez())) {
				elegirSiguiente(); // ... Se pasa el turno al siguiente jugador	
			}	
		}
		
		/* Cuando el juego finaliza muestra un mensaje en consola
		 * indicando que el juego ha finalizado y el nombre del 
		 * jugador ganador.*/
		//finalizamos la partida	
		dataBase.finalizaPartida(idPartida);
		ui.mostrarMensajeString(ParchisI18Keys.JUGADORGANADOR, 
				jugadorTurno.getNombre());
		if (modoJuego.equals(ModoJuego.ONLINE))
			jugadorTurno.getUI().mostrarMensajeString(ParchisI18Keys.JUGADORGANADOR_ENTURNO,
					jugadorTurno.getNombre());

		// Ponemos la variable a false si la partida acaba normalemente.
		enJuego = false;
	}
	
	/**
	 * Elige al jugador que comienza la partida en función de 
	 * del valor de la tirada inicial.
	 * @throws InterruptedException 
	 */
	public void elegirPrimero() throws RemoteException, InterruptedException, SocketException {

		boolean valorRepetido = false; // indica si el valor inicial se repite para más de un jugador
		Jugador jugadorEmpieza; // Almacena el jugador que saca la máxima puntuación
		int tirada; // variable para asignar la tirada de cada jugador
		int maximaPuntuacion; // almacena la tirada más alta de todos los jugadores
		int[] jugadoresPorPuntos = new int[6]; // array de 6 posiciones que almacena 
		   // la cantidad de jugadores que han alcanzado cada puntuaciÃ³n del dado
		
		ui.mostrarMensaje(ParchisI18Keys.TIRADAINICIAL);
		
		do {
			// El bucle se repite mientras al menos 2 jugadores alcancen
			// la máxima puntuación de todas las tiradas del dado.
			valorRepetido = false;
			jugadorTurno = jugadores.get(0); // Por defecto, el AMARILLO
			jugadorEmpieza = jugadorTurno;
			maximaPuntuacion = 0;
			
			// Inicializa a 0 cada posición de 'jugadoresPorPuntos'
			Arrays.fill(jugadoresPorPuntos, 0);
				
			// Llama al método tirarDado() de cada jugador y muestra en consola el
			// resultado. En caso de que la puntuación que alcance un jugador
			// sea la máxima hasta el momento, se le da el turno y se almacena el
			// valor de su tirada en la variable 'maximaPuntuacion'. Por cada jugador,
			// se suma 1 a la posición del array 'jugadoresPorPuntos' correspondiente
			// a la puntuación que ha obtenido.
			for (Jugador jug: jugadores) {
				jugadorTurno = jug;
				ui.cambiarTurno(jugadorTurno.getColor());//
			
				jug.tirarDado(true);
				tirada = jug.getTirada();
				if (tirada > maximaPuntuacion) {
					jugadorEmpieza = jug;
					maximaPuntuacion = tirada;
				}
				jugadoresPorPuntos[tirada - 1]++;
			}
			
			// Si más de un jugador ha alcanzado la máxima puntuación obtenida
			// en las tiradas del dado
			if (jugadoresPorPuntos[maximaPuntuacion - 1] > 1) {
				ui.mostrarMensaje(ParchisI18Keys.VALORMASALTOREPETIDO);
				if (modoJuego.equals(ModoJuego.ONLINE))
					jugadorTurno.getUI().mostrarMensaje(ParchisI18Keys.VALORMASALTOREPETIDO);
						valorRepetido = true;
			}	
		}  while (valorRepetido);
		
		jugadorTurno = jugadorEmpieza;
		ui.mostrarMensajeString(ParchisI18Keys.EMPIEZAPARTIDA, 
				jugadorTurno.getNombre());
		if (modoJuego.equals(ModoJuego.ONLINE))
			jugadorTurno.getUI().mostrarMensajeString(ParchisI18Keys.EMPIEZAPARTIDA_ENTURNO, 
					jugadorTurno.getNombre());
	}
	
	/**
	 * Elige el jugador siguiente y se lo asigna a la variable 
	 * jugadorTurno, que serÃ¡ el siguiente jugador en turno.
	 * Si es el Ãºltimo del ArrayList, el turno pasarÃ¡ al primero.
	 * 
	 */
	public void elegirSiguiente()  throws RemoteException{
		ui.mostrarMensaje(ParchisI18Keys.NOMASMOVIMIENTOS);
		if (modoJuego.equals(ModoJuego.ONLINE))
			jugadorTurno.getUI().mostrarMensaje(ParchisI18Keys.NOMASMOVIMIENTOS);
		
		if(jugadores.indexOf(jugadorTurno)==(jugadores.size()-1)){ // Si estÃ¡ al final, vuelve al primer elemento de la lista
			jugadorTurno = jugadores.get(0);
		}
		else{ //Si no estÃ¡ al final de la lista, asigna el siguiente jugador a jugadorTurno
			jugadorTurno = jugadores.get(jugadores.indexOf(jugadorTurno) + 1);
		}
	
		ui.cambiarTurno(jugadorTurno.getColor());
	}
	
	
	/**
	 * Getter de jugadores. Devuelve el ArrayList de jugadores.
	 * @return jugadores (ArrayList<Jugador>)
	 */
	@Override
	public ArrayList<Jugador> getJugadores() throws RemoteException{
		return jugadores;
	}

	/**
	 * Getter de fichasJuego. Devuelve el ArrayList de fichasJuego.
	 * @return fichasJuego (ArrayList<Ficha>)
	 */
	public ArrayList<Ficha> getFichasJuego() {
		return fichasJuego;
	}
	
	/**
	 * Getter de modo. Indica si el programa se encuenta en 
	 * modo NORMAL o PRUEBA
	 * @return Modo.
	 */
	public static Modo getModo() {
		return modo;
	}

	/**
	 * Establece el modo en el que se ejecutarÃ¡ el programa: NORMAL o PRUEBA
	 * @param modo
	 */
	public static void setModo(Modo modo) {
		Parchis.modo = modo;
	}
	
	//aÃ±ado este mÃ©todo para poder comunicar con el interfaz
	@Override
	public int getJugadorTurno(){
		return jugadores.indexOf(jugadorTurno);
	}
	
	/**
	 * Getter del tablero de juego
	 * @return tablero (Tablero)
	 */
	@Override
	public Tablero getTablero() throws RemoteException {
		return tablero;
	}
	
	/**
	 * Getter de ficha. Devuelve la ficha de juego de la posicion 
	 * indicada en el parametro
	 * @return fichasJuego.get(num) (Ficha)
	 */
	@Override
	public Ficha getFicha(int num) throws RemoteException {
		return fichasJuego.get(num);
	}
	
	/**
	 * Getter que devuelve la lista de interfaces de usario
	 * conectadas al servidor del parchís.
	 * @return uiList (ArrayList<ParchisUI>)
	 */
	@Override
	public BlockingQueue<ParchisUI> getUIList() throws RemoteException {
		return uiList;
	}
	
	/**
	 * Setter de la variable 'enJuego' del parchís.
	 * @param enJuego (Boolean)
	 * @throws RemoteException
	 */
	@Override
	public void setEnJuego(Boolean enJuego) throws RemoteException {
		this.enJuego = enJuego;
	}
	
	/**
	 * Getter de la varible 'enJuego' del parchís.
	 * @return enJuego (Boolean)
	 * @throws RemoteException
	 */
	@Override
	public Boolean isEnJuego() throws RemoteException {
		return enJuego;
	}
	
	/**
	 * Añade el interfaz de usuario del nuevo jugador
	 * a la lista de UIs.
	 */
	@Override
	public void registrarJugador(ParchisUI ui) throws RemoteException {
		uiList.add(ui);
	}
	
	public void escucharPorJugadores(){
		try {
			String name = "ParchisServer";
	      
			ParchisServer stub = (ParchisServer) UnicastRemoteObject.exportObject(this, 0);
			registry.rebind(name, stub);
			ui.mostrarMensaje(ParchisI18Keys.REGISTROSERVIDOR);
		} catch (Exception e) {
			System.err.println("ParchisServer exception:");
			e.printStackTrace();
		}
	}
	/**
	 * Método para cerrar el servidor y eliminar el registro cuando se abandona
	 * la partida desde el servidor.
	 */
	public void finalizaEscucha(){
		try {
			// Elimina el registro, no aceptará más llamadas RMI
			UnicastRemoteObject.unexportObject(registry, true);
			System.out.println("Conexión cerrada");
		} catch (NoSuchObjectException e) {
			System.err.println("ERROR:" + e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public void abandonarPartida() throws RemoteException {
		// TODO Auto-generated method stub
		coloresJuego.clear();
		jugadorTurno = null;
		fichasJuego.clear();
		jugadores.clear();
		uiList.clear();
	}	
}