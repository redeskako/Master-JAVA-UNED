package es.uned.master.java.oca.partida;

import es.uned.master.java.oca.comunicaciones.Comunicacion;
import es.uned.master.java.oca.comunicaciones.TipoComunicacion;
import es.uned.master.java.oca.excepciones.*;
import es.uned.master.java.oca.jugador.*;
import es.uned.master.java.oca.servidor.*;
import es.uned.master.java.oca.tablero.*;

import java.util.*; // importa clase java.util, usaremos Scanner, TreeSet, ...

public class Oca {
	// numero de jugadores que participan en el juego
	private int numeroJugadores; 
	
	// Servidor desde el que se va a gestionar la partida
	private Servidor servidor;
	
	// Conjunto de Jugadores en TreeSet para comprobar que no se repiten
	private Set<Jugador>tsJugador = new TreeSet<Jugador>();
	
	// Conjunto de Jugadores en ArrayList para poder desordenarlos
	private ArrayList<Jugador> jugadoresArray;

	// Boolean que permanece true mientras el juego está activo
	private boolean juegoTerminado;
	
	// Boolean partidaComenzada que inidica si se ha comenzado una partida
	private boolean partidaComenzada;
	
	// HashMap<Jugador, Casilla> que almacena en todo momento el conjunto de Jugadores y 
	// la Casilla en la que se encuentran.
	private HashMap<Jugador, Casilla> jugadorCasilla = new HashMap<Jugador, Casilla>();
	
	// Tablero con el que se van a realizar los cálculos
	private Tablero tablero;
	
	// Jugador activo en cada turno
	private Jugador jugActivo;
	
	/**
	 * Metodo construtor Oca para configurar la partida. Se reciben como parámetros el servidor y 
	 * el número de jugadores.
	 * Se inicializan otras variables de la Oca, con los valores correspondientes al inicio de una
	 * nueva partida.
	 * @param Número entero que se asigna al número de Jugadores
	 * @param Servidor desde el que se gestiona la partida
	 * @return null
	 * @throws null
	 */
	public Oca (int i, Servidor s) {
		this.numeroJugadores = i;		
		this.servidor = s;
		
		// Se inicializan los conjuntos vacíos
		this.tsJugador = new TreeSet<Jugador>();
		this.jugadoresArray = new ArrayList<Jugador>(numeroJugadores);
		this.jugadorCasilla = new HashMap<Jugador, Casilla>();
		
		// La partida aún no ha comenzado, ni ha terminado
		this.juegoTerminado = false;
		this.partidaComenzada = false;
		
		// Se inicia un nuevo tablero
		this.tablero = new Tablero(this.servidor, this);
		
		// Aún no hay jugador activo
		this.jugActivo = null;		
	}
	
	/**
	 * Incluye un jugador al TreeSet de Jugadores, que comprueba que no se repiten.
	 * @param Jugador que añadir al TreeSet
	 * @return null
	 * @throws null
	 */
	public void incluirJugador(Jugador j) {
		tsJugador.add(j);	
	} 
	
	/**
	 * Asigna el orden de los jugadores a partir del TreeSet de jugadores
	 * @param null
	 * @return String con la información del orden de los jugadores
	 * @throws null
	 */
	public void definirOrden () {
		// crea jArray en función del numero de jugadores
		ArrayList<Jugador> jArray = new ArrayList<Jugador>(numeroJugadores); 
		
		Iterator<Jugador> iter = tsJugador.iterator(); // itera en tsJugador
		
		// Añade a jArry los jugadores mientras estos existan 
		while (iter.hasNext()) {
			Jugador jug = iter.next();
			jArray.add(jug);
		} // fin while

		Collections.shuffle(jArray); // ordena aleatoriamente jArray
		
		setJugadoresArray(jArray); // establece jugadoresArray
		
		// Mensaje informativo
		String s = "Sois " + tsJugador.size() + " jugadores.\n"
				+ "El orden de los turnos será el siguiente:"; 
		
		for (int i=0; i<jugadoresArray.size(); i++){
			int orden = i+1;
			s = s + "\n     " + orden + "- " + jugadoresArray.get(i);
		}
		
		// Informa en su GUI
		servidor.infoPartidaGUI(s);
		
		// Envía Comunicacion a todos los Clientes sobre el orden
		Comunicacion comunicacionOrden = new Comunicacion(s, TipoComunicacion.INFO);
		servidor.enviarATodos(comunicacionOrden);
	} 
	/**
	 * Acciones a realizar durante el desarrollo de la partida:
	 * 1- Se sitúan a todos los Jugadores en la Casilla 1
	 * 2- Se da el turno al Jugador
	 * 3- Se analiza el primer turno turno
	 * @param null
	 * @return null
	 * @throws null
	 */
	public void iniciarOca() {
		
		// Se sitúan a todos los Jugadores en la Casilla 1
		Casilla c = new Casilla(1);
		for(int i=0; i<numeroJugadores; i++){
			jugadorCasilla.put(jugadoresArray.get(i), c);
		}

		// Se indica que ha comenzado la partida
		this.partidaComenzada = true;	
		
		// Se establece el Jugador activo
		this.jugActivo = this.jugadoresArray.get(0);
		
		// Se inicia el primer turno
		this.iniciarTurno();
	}
	/**
	 * Se inicia el turno:
	 * 1- Se comprueba si tiene castigos pendientes
	 * 2- Se informa a todos los jugadores del jugador que tiene el turno
	 * 3- Se envía comunicacion especial al jugador activo 
	 * @param null
	 * @return null
	 * @throws null
	 */
	public void iniciarTurno() {
		// Se detecta el Jugador que tiene el turno
		Jugador jug = this.jugActivo;
		
		//Comprobar si el jugador no tiene castigo de turno
		if(jug.getCastigo() == 0){
			
			// Informa en su GUI
			servidor.infoPartidaGUI("\n\nTurno del " + jug);
			
			// Se envía información del turno a todos: el jugador activo va a tirar el dado
			Comunicacion cTodos = new Comunicacion(jug + ", tira el dado", TipoComunicacion.INFO);
			servidor.enviarATodos(cTodos);
			// Se envía comunicacion especial al jugador activo
			Comunicacion cActivo = new Comunicacion("", TipoComunicacion.ES_TU_TURNO);
			servidor.enviarA(jug.getColor(), cActivo);
			
		} else {
			// Comprobar cuál es el castigo anterior:
			int castigo = jug.getCastigo();
			jug.setCastigo(castigo-1);
			
			// Informa al jugador de sus castigos
			if (jug.getCastigo() == 0) {
				
				// Informa en su GUI
				servidor.infoPartidaGUI("\n\n" + jug + ", no tiras el dado. "+ "En el siguiente turno tiras.");
				
				// Se envía información a todos: el jugador activo NO va a tirar el dado
				Comunicacion cTodos = new Comunicacion("\n\n" + jug + ", no tiras el dado. "
						+ "En el siguiente turno tiras.", TipoComunicacion.INFO);
				servidor.enviarATodos(cTodos);
			} else if (jug.getCastigo() == 1) {
				// Informa en su GUI
				servidor.infoPartidaGUI("\n\n" + jug + ", no tiras el dado. "+ "Te queda " + jug.getCastigo() 
						+ " turno de castigo.");
				// Se envía información a todos: el jugador activo NO va a tirar el dado
				Comunicacion cTodos = new Comunicacion("\n\n" + jug + ", no tiras el dado. "
						+ "Te queda " + jug.getCastigo() + " turno de castigo.", TipoComunicacion.INFO);
				servidor.enviarATodos(cTodos);
			} else {
				// Informa en su GUI
				servidor.infoPartidaGUI("\n\n" + jug + ", no tiras el dado. "+ "Te quedan " + jug.getCastigo() 
						+ " turnos de castigo.");
				// Se envía información a todos: el jugador activo NO va a tirar el dado
				Comunicacion cTodos = new Comunicacion("\n\n" + jug + ", no tiras el dado. "
						+ "Te quedan " + jug.getCastigo() + " turnos de castigo.", TipoComunicacion.INFO);
				servidor.enviarATodos(cTodos);
			}
			// Se pasa el turno sin haber jugado porque tenía castigos
			pasarTurno();
		}
	}
	/**
	 * Se analiza el turno, teniendo como dato el valor de la tirada del dado.
	 * @param El valor entero de la tirada del dado
	 * @return null
	 * @throws null
	 */
	public void analizarTurno(int d) {
		// Se selecciona jugador activo
		Jugador jug = this.jugActivo;
		// Valor de la tirada del dado
		int valor = d;
		// Informa en su GUI
		servidor.infoPartidaGUI("\n" + jug + ">>> has sacado un: " + valor);

		// Se envía información a todos:
		Comunicacion cTodos = new Comunicacion(jug + ">>> has sacado un: " + valor, TipoComunicacion.INFO);
		servidor.enviarATodos(cTodos);

		// Se comprueba en qué Casilla está:
		Casilla jugCasilla = jugadorCasilla.get(jug);

		// Se analiza la tirada en la clase Tablero. 
		// El resultado es una Casilla modificada.
		Casilla casillaResultado = tablero.analizarTirada(jug, jugCasilla, valor);

		// Se sustituye el valor (value) que corresponde al jugador activo (key): casillaResultado
		jugadorCasilla.put(jug, casillaResultado);

		// Si la casilla destino es la 63, se termina el juego
		if (casillaResultado.getPosicion() == 0){
			this.juegoTerminado = true;
		}

		// Si tiene que volver a tirar, repite turno:
		if (jug.getTiraOtraVez() == true){
			// finaliza el turno sin haber pasado el turno (sin cambiar el Jugador activo)
			this.finalizarTurno();
		} else {
			// Se envía comunicacion especial al jugador activo: fin del turno
			Comunicacion cActivo = new Comunicacion("", TipoComunicacion.NO_ES_TU_TURNO);
			servidor.enviarA(jug.getColor(), cActivo);

			// Se pasa el turno al siguiente jugador, comprobando antes que hay suficientes jugadores	
			this.comprobarJugadores();
		}
	}
	/**
	 * Antes de pasar el turno, se comprueba si quedan al menos dos jugadores.
	 * Si queda un jugador, se termina la partida.
	 * Si quedan al menos dos jugadores, se pasa el turno.
	 * @param null
	 * @return null
	 * @throws null
	 */
	public void comprobarJugadores(){
		if (jugadoresArray.size() < 2){
			// Si hay menos de 2 jugadores, se termina la partida.
			this.juegoTerminado = true;
			this.finalizarTurno();
		} else {
			pasarTurno();
		}
	}

	/**
	 * Se pasa el turno, teniendo en cuenta que debe pasar sólo por el número de jugadores del array.
	 * @param null
	 * @return null
	 * @throws null
	 */
	public void pasarTurno(){
		try{
			if (jugActivo == this.jugadoresArray.get(jugadoresArray.size()-1)){
				// Si es el último jugador del array: pasa al primero
				jugActivo = this.jugadoresArray.get(0);
			} else {
				// Si no es el último, pasa al siguiente
				int posicion = this.jugadoresArray.indexOf(jugActivo);
				jugActivo = this.jugadoresArray.get(posicion+1);
			}
		}catch (IndexOutOfBoundsException e){
			jugActivo = this.jugadoresArray.get(0);
		}
		this.finalizarTurno();
	}

	/**
	 * Se analiza el final del turno, comprobando si la partida continua o no.
	 * @param null
	 * @return null
	 * @throws null
	 */
	private void finalizarTurno(){
		// Comprueba si se ha terminado, si es cierto, se termina la partida
		if(juegoTerminado == true){
			finOca();
		} else {
			iniciarTurno();
		}
	}

	/**
	 * Finaliza la partida y pide al servidor que cierre todas las conexiones.
	 * @param null;
	 * @return null
	 * @throws null
	 */
	public void finOca() {		
		this.juegoTerminado = true;
		this.jugActivo = null;

		this.servidor.cerrarTodo();
	}
	/* GETTERS Y SETTERS */
	/**
	 * Obtiente la variable numeroJugadores
	 * @param null
	 * @return la variable numeroJugadores
	 * @throws null
	 */
	public int getNumeroJugadores() {
		return this.numeroJugadores;
	} // fin getNumeroJugadores

	/**
	 * Estabelece un valor para la variable numeroJugadores
	 * @param nuevo valor para la variable numeroJugadores
	 * @return null
	 * @throws null
	 */
	public void setNumeroJugadores(int numeroJugadores) {
		if (numeroJugadores<2 || numeroJugadores>6){
			throw new ClaseError("El valor del numeroJugadores tiene que estar comprendido entre 2 y 6."); 
		}else{
			this.numeroJugadores = numeroJugadores;
		}		
	}

	/**
	 * Obtiente la variable tsJugador
	 * @param null
	 * @return la variable tsJugador
	 * @throws null
	 */
	public Set<Jugador> getTsJugador() {
		return this.tsJugador;
	}

	/**
	 * Estabelece un valor para la variable tsJugador
	 * @param nuevo valor para la variable tsJugador
	 * @return null
	 * @throws null
	 */
	public void setTsJugador(Set<Jugador> s){
		this.tsJugador = s;
	}	

	/**
	 * Obtiente la variable jugadoresArray
	 * @param null
	 * @return la variable jugadoresArray
	 * @throws null
	 */
	public ArrayList<Jugador> getJugadoresArray() {
		return this.jugadoresArray;
	}

	/**
	 * Estabelece un valor para la variable jugadoresArray
	 * @param nuevo valor para la variable jugadoresArray
	 * @return null
	 * @throws null
	 */
	public void setJugadoresArray(ArrayList<Jugador> a){
		this.jugadoresArray = a;
	}	

	/**
	 * Estabelece un valor para la variable juegoTerminado
	 * @param booleano true si el juego ha terminado, false si no es así
	 * @return null
	 * @throws null
	 */
	public void setJuegoTerminado(boolean terminado){
		this.juegoTerminado = terminado;
	}

	/**
	 * Obtiene juegoTerminado
	 * @param null
	 * @return el valor de la variable juegoTerminado
	 * @throws null
	 */
	public boolean getJuegoTerminado(){
		return this.juegoTerminado;
	}

	/**
	 * Estabelece un valor para la variable partidaComenzada
	 * @param booleano para partidaComenzada
	 * @return null
	 * @throws null
	 */
	public void setPartidaComenzada(boolean b){
		this.partidaComenzada = b;
	}

	/**
	 * Obtiene partidaComenzada
	 * @param null
	 * @return el valor de la variable partidaComenzada
	 * @throws null
	 */
	public boolean getPartidaComenzada(){
		return this.partidaComenzada;
	}

	/**
	 * Obtiente la variable jugadorCasilla
	 * @param null
	 * @return la variable jugadorCasilla
	 * @throws null
	 */
	public HashMap<Jugador, Casilla> getJugadorCasilla() {
		return jugadorCasilla;
	}

	/**
	 * Establece nuevo valor para la variable jugadorCasilla
	 * @param nuevo valor para jugadorCasilla
	 * @return null
	 * @throws null
	 */
	public void setJugadorCasilla(HashMap<Jugador, Casilla> jugadorCasilla) {
		this.jugadorCasilla = jugadorCasilla;
	}

	/**
	 * Obtiene el valor de la variable jugActivo
	 * @param null
	 * @return el valor de la variable jugActivo
	 * @throws null
	 */
	public Jugador getJugActivo(){
		return jugActivo;
	}
}