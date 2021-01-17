package es.uned2013.parchis;

import java.io.Serializable;
import java.net.SocketException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.TreeMap;
import java.util.TreeSet;

import es.uned2013.parchis.ui.ParchisI18Keys;
import es.uned2013.parchis.ui.ParchisUI;
import es.uned2013.parchis.ui.ParchisUIMode;


/**
 * Controla las diferentes acciones de un participante 
 * en el juego, tirar el dado, elegir la ficha a mover,
 * mover el número de casillas correspondiente...e 
 * implementa todas las reglas de juego que tiene que
 * seguir un jugador.
 * @author alef
 *
 */
public class Jugador implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre; // Nombre del jugador, por defecto el de su color.
	private Colores color; // Color de sus fichas.
	private ArrayList<Ficha> fichas = new ArrayList<Ficha>(); // Fichas del jugador.
	private int tirada; // Valor del dado sacado en la última tirada.
	private boolean tiroOtraVez; // Controla cuando el jugador repite tirada en función de los seises sacados.
	private boolean muevoSiete; // 'true' cuando el jugador tiene todas las fichas fuera de la casilla casa.
	private boolean movimientoAdicional; // 'true' cuando se ha comido otra ficha o se ha ingresado en la casilla final
	private boolean movimientoFinalizado; // 'true' cuando no se pueden mover más fichas.
	private int seises; // Controla el nº de seises consecutivos en un turno.
	private Ficha segundoSeis; // Si sale un tercer seis esta ficha puede ser eliminada.
	private Tablero tablero; // Guarda la referencia al tablero de juego.
	private ParchisUI ui;  //UI del jugador.
	private Ficha fic = null; //ficha en turno
	private int CasillaAnterior = 0; //guarda la casilla anterior

	/**
	 * Constructor. Asigna los atributos tablero, nombre, color y 
	 * asocia las fichas de su color al jugador.
	 * @param tablero tablero del juego
	 * @param nombre nombre del jugador
	 * @param color color del jugador
	 * @param fichas fichas del jugador
	 */
	public Jugador(Tablero tablero, String nombre, Colores color, ArrayList<Ficha> fichas) {

		
		this.tablero = tablero;
		this.nombre = (nombre != ""? nombre: color.toString());
		this.color = color;
		
		/* Asignamos las fichas al jugador
		 * que corresponden a su color. 		
		 */
		for (int i = 0; i < fichas.size(); i++) 
			if (color.equals(fichas.get(i).getColor()))
				this.fichas.add(fichas.get(i));
		
		muevoSiete = false;
		seises = 0;
		segundoSeis = fichas.get(0);
		tiroOtraVez = false;
	}
	/**
	 * Getter de la variable nombre
	 * @return nombre nombre del jugador
	 */
	public String getNombre() {
		/* if (nombre == null)
			nombre = getColor().toString(); */
		return nombre;
	}
	
	/**
	 * Getter de la variable color
	 * @return color color del jugador
	 */
	public Colores getColor() {
		return color;
	}
	
	/**
	 * Getter de la variable color porId
	 * @return color color del jugador
	 */
	public int getColorId() {
		return getColor().getId();
	}
	
	/**
	 * Getter de la variable color porId
	 * @return color color del jugador
	 */
	public int getIdCasillaAnterior() {
		return CasillaAnterior;
	}
	
	/**
	 * Getter de la variable ficha en turno
	 * @return color color del jugador
	 */
	public Ficha getFichaTurno() {
		return fic;
	}
	
	
	/**
	 * Getter de la variable fichas
	 * @return fichas fichas del tablero
	 */
	public ArrayList<Ficha> getFichas() {
		return fichas;
	}
	
	/**
	 * Getter de la variable tirada
	 * @return tirada tirada de un jugador
	 */
	public int getTirada() {
		return tirada;
	}
	
	/**
	 * Setter de la variable color
	 * @param tirada asigna la tirada a la variable local tirada
	 */
	public void setTirada(int tirada) {
		this.tirada = tirada;
	}
	
	/**
	 * Getter de la variable movimientoAdicional
	 * @return movimientoAdicional true si existe movimiento adicional
	 */
	public boolean getMovimientoAdicional() {
		return movimientoAdicional;
	}
	
	/**
	 * Setter de la variable movimientoAdicional
	 * @param movimientoAdicional establece si hay movimientoAdicional
	 */
	public void setMovimientoAdicional(boolean movimientoAdicional) {
		this.movimientoAdicional = movimientoAdicional;
	}
	
	/**
	 * Getter de la variable tiroOtraVez
	 * @return tiroOtraVez true si el jugador tira otra vez
	 */
	public boolean getTiroOtraVez() {
		return tiroOtraVez;
	}
	
	/** Sobrecarga del método tirarDado sin parámetro que realiza
	 * internamente una llamada al método principal con esInicio = false
	 * para que, aparte de calcular un número aleatorio del 1 al 6, se 
	 * modifiquen las variables de instancia de Jugador que correspondan y 
	 * se compruebe si hay que devolver alguna ficha a la casilla de casa
	 * @return tirarDado(false); (int)
	 * @throws InterruptedException 
	 * @throws SocketException 
	 */
	public int tirarDado()  throws RemoteException, InterruptedException, SocketException{
		return tirarDado(false);
	}
	
	/**
	 * Devuelve un nº aleatorio entre 1 y 6 y lo asigna al 
	 * atributo tirada.
	 * @param esInicio indica si es la tirada de inicio
	 * @return tirada valor de la tirada
	 * 
	 * Si es un seis -> seises++
	 * Si 'seises'==3 
	 * y la ficha segunooSeis no ocupa unaa casilla pasillo
	 * tablero.fichaComida(segundoSeis);
	 * movimientoFinalizado==true;
	 * if muevoSiete==true -> tirada = 7
	 * @throws InterruptedException 
	 */
	public int tirarDado(boolean esInicio)  throws RemoteException, InterruptedException,  SocketException{

		Parchis.getUI().mostrarMensajeString(ParchisI18Keys.TIRADADO, nombre);
		
		switch(Parchis.getModo()) {
			case NORMAL:
				if (Parchis.getUIMode().equals(ParchisUIMode.GUI))
					getUI().solicitarCadena(ParchisI18Keys.CLICDADO);
				else
					getUI().solicitarCadena(ParchisI18Keys.PULSAENTER);
				Random azar = new Random();
				tirada = azar.nextInt(6) + 1;
				break;
			case PRUEBA:
				if (Parchis.listaTestTirada.hasNext())
					tirada = Parchis.listaTestTirada.next();
				break;
		}
		
		// En modo consola primero debe aparecer que hemos sacado un 6.
		if (Parchis.getUIMode().equals(ParchisUIMode.CONSOLE))
			Parchis.getUI().mostrarTirada(nombre, tirada);
		
		if (!esInicio) {
			movimientoFinalizado = false;
			movimientoAdicional = false;
			compruebaMuevoSiete();
			if ((tirada == 6) && muevoSiete)
				tirada = 7;
		}
		
		// En modo gráfico tenemos una representación para el 7.
		if (Parchis.getUIMode().equals(ParchisUIMode.GUI))
			Parchis.getUI().mostrarTirada(nombre, tirada);
		
		if (!esInicio) {
			if (tirada == 6 || tirada == 7) {
				seises++;
				if (seises == 3) {
					seises = 0;
					tiroOtraVez = false;
					movimientoFinalizado = true;
					
					Parchis.getUI().mostrarMensaje(ParchisI18Keys.TERCERSEIS);
					if (Parchis.getModoJuego().equals(ModoJuego.ONLINE))
						getUI().mostrarMensaje(ParchisI18Keys.TERCERSEIS);
					
					if (segundoSeis.getCasillaActual() < 
							color.getCasillaCasa()) {
						tablero.fichaComida(segundoSeis);
					}
					Parchis.getUI().mostrarMensaje(ParchisI18Keys.NOMOVNOTURNO);
					if (Parchis.getModoJuego().equals(ModoJuego.ONLINE))
						getUI().mostrarMensaje(ParchisI18Keys.NOMOVNOTURNO);
				}
				else{
					if (Parchis.getUIMode().equals(ParchisUIMode.CONSOLE) &&
							(tirada == 7)) {
						Parchis.getUI().mostrarMensaje(ParchisI18Keys.CUENTOSIETE);
						if (Parchis.getModoJuego().equals(ModoJuego.ONLINE))
							getUI().mostrarMensaje(ParchisI18Keys.CUENTOSIETE);
					}
					tiroOtraVez = true;
				}
			}
			else{
				tiroOtraVez = false;
				seises = 0;
			}
		}
			
		return tirada;
	}
	
	/**
	 * Gestiona el movimiento de fichas en función de las fichas movibles en cada momento
	 * y las decisiones del jugador. Las opciones se mostrarán en pantalla para que
	 * el jugador pueda elegir.
	 * Si el valor de 'tirada' fuese igual a 6 o 7 y el jugador
	 * tuviese alguna de sus fichas formando alguna barrera se
	 * informará al jugador de está circunstancia.
	 * 
	 * Se deberá comprobar si tiene barreras y en ese caso
	 * el jugador deberá elegir una ficha de alguna barrera
	 * para romperla.
	 * 
	 * Se moverá la ficha seleccionada la cantidad de casillas
	 * del atributo 'tirada'
	 */
	public void mover()  throws RemoteException{
		/*
		 * if movimientoFinalizado == false
		 * 
		 * Se informará al jugador sobre las fichas que puede mover
		 * (no tienen barreras o casilla final en su camino)
		 * haciendo la llamada a tablero.esMovible por cada
		 * una de sus fichas, teniendo en cuenta el valor de
		 * 'tirada'.
		 * 
		 * Si la tirada es 5, el jugador tiene alguna ficha en
		 * casa y 'esMovible' (no tiene dos fichas del mismo 
		 * color en la casilla salida) se colocará en la casilla
		 * salida del jugador la ficha con menor identificador.
		 * -> Tablero.sacaFicha(Ficha)
		 * Se informará de esta circunstancia al jugador.
		 * 
		 * Si el valor de 'tirada' fuese igual a 6 o 7 y el jugador
		 * tubiese alguna de sus fichas formando alguna barrera se
		 * informará al jugador de está circunstancia.
		 * 
		 * Se deberá comprobar si tiene barreras y en ese caso
		 * el jugador deberá elegir una ficha de alguna barrera
		 * para romperla.
		 * 
		 * Se moverá la ficha seleccionada la cantidad de casillas
		 * del atributo 'tirada', llamando a 'tablero.mueve'
		 */
		
		TreeMap<Integer, Ficha> fichasMovibles;
		TreeSet<Integer> barreras;
		
		Boolean fichaSacada = false;
		
		movimientoAdicional = false;
		
		if (!movimientoFinalizado) {
			fichasMovibles = calculaFichasMovibles();
			if (fichasMovibles.size() > 0) {
				if (tirada == 5) {
					// Si la tirada es 5 se busca si existe una ficha
					// movible en la casa del jugador, y la sacamos.
					// Controlamos con la variable "fichaSacada" que 
					// ya hemos realizado ese movimiento.
					fic = buscaFichaMovibleEnCasa(fichasMovibles);
					if (fic != null) {
						tablero.sacaFicha(this, fic);
						fichaSacada = true;
					}
				}
				if (tirada == 6 || tirada == 7) {
					// Si la tirada es 6 o 7, comprobamos si el jugador
					// tiene alguna barrera que se pueda romper formada por 2 
					// de sus fichas. En ese caso, actualizamos el map de fichas 
					// movibles con las fichas correspondientes solo a esas barreras.
					barreras = calculaBarreras(fichasMovibles);
					if (barreras.size() > 0) {
						fichasMovibles.clear();
						fichasMovibles = calculaFichasBarrera(barreras);
					}
				}
				if (!fichaSacada) {
					// Si no habíamos sacado ficha, entonces
					// deberemos realizar algún otro movimiento con 
					// otra ficha
					fic = seleccionaFicha(fichasMovibles);
					CasillaAnterior = fic.getCasillaActual();
					tablero.mueve(this, fic);
				}
				// Asignamos la nueva ficha seleccionada a "segundoSeis"
				segundoSeis = fic;
			}
		}
	}
	
	/** 
	 * Devuelve una cadena con el nombre y el color del jugador,
	 * así como los datos tabulados de sus fichas y las casillas que 
	 * ocupan actualmente.
	 * @return cad almacena nombre y color de jugador y datos de sus fichas y casillas
	 * No se utiliza pero se mantiene en previsión de cambios futuros.
	 */
	/*@Override
	public String toString() {
		String cad = "\nJugador: " + nombre + " Color: " + color;
		cad += "\nFicha Posición";
		cad += "\n----- --------";
		
		for (Ficha fic: fichas)
			cad += String.format("\n%3d   %5d",
					fic.getIdentificador(), fic.getCasillaActual());
		
		return cad;
	}*/
	
	/**
	 * Comprueba si no hay ninguna ficha del jugador en la casilla de 
	 * casa y, en ese caso asigna a la variable 'muevoSiete' el valor 
	 * 'true', incrementándose el valor del dado a 7 en caso de sacar 
	 * un 6 en la tirada.
	 */
	private void compruebaMuevoSiete() {
		if (tablero.getCasilla(color.getCasillaCasa()).getTieneFicha() == 0) 
			muevoSiete = true; 
		else
			muevoSiete = false;
	}
	
	/** 
	 * Recorre el array de fichas del jugador,
	 * y devuelve un map conteniendo solo las fichas que el jugador 
	 * puede mover junto con su identificador.
	 * @return fichasMovibles devuelve fichas que se pueden mover
	 */
	private TreeMap<Integer, Ficha> calculaFichasMovibles() {
		TreeMap<Integer, Ficha> fichasMovibles = new TreeMap<Integer, Ficha>();
		
		for (Ficha fic: fichas)
			if (tablero.esMovible(fic, tirada))
				fichasMovibles.put(fic.getIdentificador(), fic);
		
		return fichasMovibles;
	}
	
	/**
	 * Comprobando primero si el jugador tiene alguna ficha en la casilla 
	 * casa de su color, recorre el map de fichas movibles que se le pasa 
	 * como parámetro y devuelve la de menor identificador que se encuentre
	 * en la casilla casa. En caso de no encontrarla, devuelve null.
	 * @param fichasMovibles fichas con posibilidad de ser movidas
	 * @return fichaMovibleEnCasa ficha si existe en la casa
	 */
	private Ficha buscaFichaMovibleEnCasa(TreeMap<Integer, Ficha> fichasMovibles) {
		Ficha fichaMovibleEnCasa = null, fic;
		Casilla casillaCasa = tablero.getCasilla(color.getCasillaCasa());
		Iterator<Ficha> it = fichasMovibles.values().iterator();
		
		// Si la casilla casa del color del jugador tiene fichas
		if (casillaCasa.getTieneFicha() > 0) {
			// Recorre las fichas movibles del map
			while (it.hasNext()) {
				fic = it.next();
				// Si la ficha se encuentra en la casilla casa del
				// color del jugador (que es el color de la ficha)
				if (fic.getCasillaActual() == color.getCasillaCasa()) {
					fichaMovibleEnCasa = fic;
					break;
				}
			}
		}
		
		return fichaMovibleEnCasa;
	}
	
	/**
	 * Recorre el map de fichas movibles que se le pasa como parámetro y 
	 * devuelve un set de números de casilla, conteniendo aquellas 
	 * en las que 2 fichas forman una barrera que se puede romper.
	 * En caso de existir, se informa del hecho al jugador.
	 * @param fichasMovibles fichas de los jugadores a comprobar
	 * @return barreras números de casillas en las que hay barreras en caso de existir
	 */
	private TreeSet<Integer> calculaBarreras(TreeMap<Integer, Ficha> fichasMovibles)  throws RemoteException {
		
		TreeSet<Integer> barreras = new TreeSet<Integer>();
		Iterator<Ficha> it = fichasMovibles.values().iterator();
		Ficha fic;
		int idCasilla;
		Casilla cas;
		String cad = "";
		
		// Recorre las fichas del map
		while (it.hasNext()) {
			fic = it.next();
			// Obtiene la casilla en la que se encuentra la ficha
			idCasilla = fic.getCasillaActual();
			cas = tablero.getCasilla(idCasilla);
			if ((cas != null) && (idCasilla <= 68) && (cas.isEsBarrera()) && 
				(cas.getFicha1().getColor().equals(cas.getFicha2().getColor())) &&
				(!barreras.contains(idCasilla))) {
				// Si no es una casilla pasillo, existe una barrera,
				// el color de las dos fichas que contiene coincide,
				// y no se ha añadido al map de barreras de color,
				// añadimos el número de la casilla, tanto al set como
				// a la cadena que utilizaremos posteriormente para informar 
				// al jugador.
				if (barreras.size() > 0) cad += ", ";
				cad += idCasilla;
				barreras.add(idCasilla);
			}
		}
		
		if (barreras.size() > 0) {
			if (barreras.size() > 1)
				getUI().mostrarMensajeString(ParchisI18Keys.BARRERASPENDIENTES,cad);
			else
				getUI().mostrarMensajeString(ParchisI18Keys.BARRERAPENDIENTE,cad);
		}
		
		return barreras;
	}
	
	/**
	 * Recibe como parámetro un set con los números de casilla que contienen
	 * una barrera, y retorna un map con las fichas movibles que se encuentran 
	 * formando barrera en dichas casillas.
	 * @param barreras números de casillas en las que hay barreras en caso de existir
	 * @return fichasMovibles fichas que se encuentran formando una barrera
	 */
	private TreeMap<Integer, Ficha> calculaFichasBarrera(TreeSet<Integer> barreras) {
		TreeMap<Integer, Ficha> fichasMovibles = new TreeMap<Integer, Ficha>();
		Iterator<Integer> it = barreras.iterator();
		int numCasilla;
		Casilla cas;
		Ficha fic;
		
		// Recorre el set de números de casilla con barrera
		while (it.hasNext()) {
			numCasilla = it.next();
			// Se obtiene la casilla que contiene la barrera con su identificador
			cas = tablero.getCasilla(numCasilla);
			// Se obtiene la primera ficha que forma la barrera
			// y se añade a las fichas que son movibles.
			fic = cas.getFicha1();
			fichasMovibles.put(fic.getIdentificador(), fic);
			// Se obtiene la segunda ficha que forma la barrera
			// y se añade a las fichas que son movibles.
			fic = cas.getFicha2();
			fichasMovibles.put(fic.getIdentificador(), fic);
		}
		
		return fichasMovibles;
	}
	
	/**
	 * Recibe como parámetro un map de las fichas que se pueden mover, 
	 * se las muestra al jugador, y le solicita que introduzca una de ellas
	 * devolviendo la ficha que ha seleccionado
	 * @param fichasMovibles fichas que se pueden mover
	 * @return fic ficha movible seleccionada por el jugador
	 */
	private Ficha seleccionaFicha(TreeMap<Integer, Ficha> fichasMovibles)  throws RemoteException{
		Iterator<Ficha> it = fichasMovibles.values().iterator();
		Ficha fic = null;
		String cad = "";
		int cantFichas = fichasMovibles.size(), idFicha = 0, cont = 0;
		Boolean valido = false;
		
		if (cantFichas > 0) {
			// Si se puede mover alguna ficha,
			// se recorren las fichas movibles
			// y se muestran al jugador.
			cad = "";
			cont = 1;
			while (it.hasNext()) {
				cad += it.next().getIdentificador();
				if (cont < cantFichas) cad += ", ";
				cont++;
			}
			if (cantFichas > 1) 
				getUI().mostrarMensajeString(ParchisI18Keys.SOLOMOVERFICHASID, cad);
			else
				getUI().mostrarMensajeString(ParchisI18Keys.SOLOMOVERFICHA, cad);
						
			if (cantFichas > 1) { 
				// Si existe más de una ficha movible, se solicita al jugador
				// que introduzca una controlando que se trate de un 
				// identificador válido (entero válido que se encuentre entre 
				// los identificadores de fichas movibles).
				cont = 1;
				while ((!valido) && (cont < 100)) {
					switch(Parchis.getModo()) {
				   	case NORMAL:
				   		idFicha = getUI().solicitarEntero(ParchisI18Keys.INTRODUZCAIDFICHA);
							break;
						case PRUEBA:
							if (Parchis.listaTestEligeFicha.hasNext())
								idFicha = Parchis.listaTestEligeFicha.next();
							break;
					} 
					
					if (fichasMovibles.containsKey(idFicha)) {
						fic = fichasMovibles.get(idFicha);
						valido = true;
					}
					else{
						getUI().mostrarMensaje(ParchisI18Keys.ERRORVALORNOPOSIBLE);
					}
				}
				// Para evitar que se cuelgue el programa
				if (cont == 100) 
					fic = fichasMovibles.get(fichasMovibles.firstKey());
			}
			else {
				// Si solo se puede mover una ficha
				fic = fichasMovibles.get(fichasMovibles.firstKey());
			}
				
		}
		return fic;
	}
	public ParchisUI getUI() {
		return ui;
	}
	public void setUI(ParchisUI ui) {
		this.ui = ui;
	}
}
