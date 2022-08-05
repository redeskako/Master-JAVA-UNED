package es.uned.master.java.oca.tablero;

import es.uned.master.java.oca.comunicaciones.Comunicacion;
import es.uned.master.java.oca.comunicaciones.TipoComunicacion;
import es.uned.master.java.oca.excepciones.*;
import es.uned.master.java.oca.jugador.*;
import es.uned.master.java.oca.partida.*;
import es.uned.master.java.oca.servidor.Servidor;
import java.util.*;
/**
 * Se define una clase Tablero que representa el conjunto de Casillas necesarias para jugar a la Oca.
 * Su única variable es un conjunto ArrayList de Casillas.
 * Contiene ademas los métodos necesarios para describir las acciones que se llevan a cabo en el 
 * transcurso de la partida de la Oca.
 * @author	Alef UNED 2014
 * @version	Oca 2.0
 * @fecha 	Abril 2014
 */
public class Tablero {
	// Servidor desde el que se va a gestionar la partida
	private Servidor servidor;
	// Oca desde la que se va a gestionar la partida
	private Oca oca;
	// Variable de tipo colección (ArrayList) que representa un conjunto de objetos Casilla.
	private ArrayList<Casilla> arrayCasillas;
	/**
	 * Método constructor: se asigna a arrayCasillas un ArrayList de elementos Jugador de 63 de longitud,
	 * ya que en la Oca hay 63 casillas.
	 * @param null
	 * @return null
	 * @throws null
	 */
	public Tablero(Servidor s, Oca o){
		// Se establece el servidor que se va a utilizar
		this.servidor = s;
		this.oca = o;
		arrayCasillas = new ArrayList<Casilla>(63);
		// Mediante un bucle, se crean y añaden al ArrayList las 63 Casillas
		for(int i=1; i<=63; i++){
			// Se crea Casilla
			Casilla c = new Casilla(i);
			// Se añade la Casilla a la colección
			arrayCasillas.add(c);
		}
	}
	/**
	 * Obtiene el valor de la variable arrayCasillas
	 * @param null
	 * @return Se devuelve el valor de la variable arrayCasillas
	 * @throws null
	 */
	public ArrayList<Casilla> getArrayCasillas(){
		return this.arrayCasillas;
	}
	/**
	 * Se calcula la casilla destino, como resultado de sumar el valor de la tirada del dado a la
	 * casilla actual del jugador.
	 * Se comprueba si es la casilla final. Si es así, gana la partida y se termina la misma. Si no es as�,
	 * se analiza la casilla destino.
	 * @param Jugador activo, la Casilla en la que se encuentra, y la puntuación obtenida con los dados.
	 * @return Se devuelve el objeto Casilla resultante de la tirada.
	 * @throws Si la puntuación obtenida con los dados no está entre 1 y 6, se lanza ClaseError.
	 */
	public Casilla analizarTirada(Jugador j, Casilla c, int n){
		// Precondición: comprobar que el valor entero "n" está entre 1 y 6 (dado)
		if(n<1 || n>6){
			throw new ClaseError("Valor de la tirada del dado incorrecto.");
		} else {
			// Se calcula la posición de la casilla de destino con posición de la Casilla + tirada del dado:
			int posicionDestino = c.getPosicion() + n;
			// Se comprueba si hay rebote en la casilla 63, y se calcula la posicionDestino
			if(posicionDestino > 63){
				posicionDestino = 2*63 - posicionDestino;
			}
			// Una vez calculado el rebote (si lo hay), se crea la Casilla destino.
			Casilla casillaDestino = new Casilla(posicionDestino);
			// Se analiza la Casilla destino
			return analizarCasilla(j, casillaDestino);
		}
	}
	/**
	 * Se analiza la casilla destino, haciendo que se ejecuten las acciones necesarias dependiendo del tipo
	 * de casilla destino.
	 * @param Jugador activo y Casilla destino.
	 * @return Se devuelve el objeto Casilla resultante de la tirada.
	 * @throws null
	 */
	private Casilla analizarCasilla(Jugador j, Casilla casillaDestino){
		// Se ejecutan las acciones propias de cada tipo de Casilla, en función al tipo de Casilla.
		switch (casillaDestino.getTipo()){
		case OCA:
			return accionesOca(j, casillaDestino);
		case PUENTE:
			return accionesPuente(j, casillaDestino);
		case DADOS:
			return accionesDados(j, casillaDestino);
		case POSADA:
		case POZO:
			return accionesTurnos(j, casillaDestino, 2);
		case CARCEL:
			return accionesTurnos(j, casillaDestino, 3);
		case LABERINTO:
			return accionesLaberinto(j, casillaDestino);
		case CALAVERA:
			return accionesCalavera(j, casillaDestino);
		default:
			return accionesNormal(j, casillaDestino);
		}
	}
	/**
	 * Se definen las acciones necesarias cuando se cae en una casilla tipo OCA:
	 * 		1- Se comprueba si es la Oca 63: en ese caso se termina la partida
	 * 		2- Si no es la 63, se desplaza a la siguiente Oca y repite turno el jugador.
	 * @param 	Jugador activo y Casilla destino.
	 * @return 	Se devuelve el objeto Casilla resultante de la tirada.
	 * @throws 	null
	 */
	private Casilla accionesOca(Jugador j, Casilla casillaDestino){
		// Se comprueba si la casillaDestino es 63. En ese caso, ha ganado la partida.
		if (casillaDestino.getPosicion() == 63){
			// Se general el mensaje para el jugador
			String s =  " has caído en la casilla " + casillaDestino.getPosicion() + ": EL JARD�N DE LA OCA.";
			mensaje(j, s);
			// FINAL DE LA PARTIDA: Se envía información a todos
			String sFinal = "\n\n¡Ha ganado el " + j + "! \n¡Gracias por jugar a la Oca!\n*** FIN DEL JUEGO ***\n";
			Comunicacion cFinal = new Comunicacion(sFinal, TipoComunicacion.INFO);
			servidor.enviarATodos(cFinal);
			//  FINAL DE LA PARTIDA: Informa en su GUI
			servidor.infoPartidaGUI(sFinal);
			// Se cambia el valor de la variable juegoTerminado
			this.oca.setJuegoTerminado(true);
			// Se modifica la variable tiraOtraVez del Jugador j a true para que no pase el turno al siguiente jugador
			j.setTiraOtraVez(true);
			// El método devuelve la Casilla destino Casilla(0), reservada para cuando un jugador gana.
			return new Casilla(0);
		} else{
			// Se define un valor entero i que corresponde a la posici�n de la Casilla dentro del ArrayList
			int i = casillaDestino.getPosicion() - 1;
			// Bucle para buscar la siguiente casilla tipo OCA:
			while (arrayCasillas.get(i+1).getTipo() != TipoCasilla.OCA){
				i++;
			}
			// Se detecta la siguiente OCA
			Casilla nuevoDestino = arrayCasillas.get(i+1);
			// Se modifica la variable tiraOtraVez del Jugador j a true para que repita turno
			j.setTiraOtraVez(true);
			// Se general el mensaje para el jugador
			String s = " has caído en la casilla " + casillaDestino.getPosicion() + ": \nDe oca (" + casillaDestino.getPosicion() + ") a oca "
					+ "(" + nuevoDestino.getPosicion() + "), y tiro porque me toca.";
			mensaje(j, s);
			// El m�todo devuelve la Casilla del nuevo destino	
			return nuevoDestino;
		}
	}
	/**
	 * Se definen las acciones necesarias cuando se cae en una casilla tipo PUENTE:
	 * se desplaza a la otra Casilla tipo PUENTE y repite turno el jugador.
	 * @param 	Jugador activo y Casilla destino.
	 * @return 	Se devuelve el objeto Casilla resultante de la tirada.
	 * @throws 	null
	 */
	private Casilla accionesPuente(Jugador j, Casilla casillaDestino){
		// Bucle para buscar la otra casilla tipo PUENTE: se repite mientras NO sea puente o sea la casilla actual
		int i = 0;
		while (arrayCasillas.get(i).getTipo() != TipoCasilla.PUENTE || arrayCasillas.get(i).getPosicion() == casillaDestino.getPosicion()){
			i++;
		}
		// Se detecta el otro PUENTE
		Casilla nuevoDestino = arrayCasillas.get(i);
		// Se modifica la variable tiraOtraVez del Jugador j a true para que repita turno
		j.setTiraOtraVez(true);
		// Se general el mensaje para el jugador
		String s = " has caído en la casilla " + casillaDestino.getPosicion() + ": \nDe puente (" + casillaDestino.getPosicion() 
				+ ") a puente " + "(" + nuevoDestino.getPosicion() + "), y tiro porque me lleva la corriente.";
		mensaje(j, s);	
		// El método devuelve la Casilla del nuevo destino
		return nuevoDestino;
	}
	/**
	 * Se definen las acciones necesarias cuando se cae en una casilla tipo DADOS:
	 * se desplaza a la otra Casilla tipo DADOS y repite turno el jugador.
	 * @param 	Jugador activo y Casilla destino.
	 * @return 	Se devuelve el objeto Casilla resultante de la tirada.
	 * @throws 	null
	 */
	private Casilla accionesDados(Jugador j, Casilla casillaDestino){
		// Bucle para buscar la otra casilla tipo DADOS: se repite mientras NO sea puente o sea la casilla actual
		int i = 0;
		while (arrayCasillas.get(i).getTipo() != TipoCasilla.DADOS || arrayCasillas.get(i).getPosicion() == casillaDestino.getPosicion()){
			i++;
		}
		// Se detecta la otra Casilla tipo DADOS
		Casilla nuevoDestino = arrayCasillas.get(i);
		// Se modifica la variable tiraOtraVez del Jugador j a true para que repita turno
		j.setTiraOtraVez(true);
		// Se general el mensaje para el jugador
		String s = " has caído en la casilla " + casillaDestino.getPosicion() + ": \nDe dado (" + casillaDestino.getPosicion() 
				+ ") a dado " + "(" + nuevoDestino.getPosicion() + "), y tiro porque me ha tocado.";
		mensaje(j, s);
		// El método devuelve la Casilla del nuevo destino
		return nuevoDestino;
	}
	/**
	 * Se definen las acciones necesarias cuando se cae en una casilla tipo POSADA, POZO o CARCEL:
	 * Penalización con 2 o 3 turnos, dependiendo del tipo de Casilla.
	 * @param 	Jugador activo, Casilla destino y número de turnos penalizados.
	 * @return 	Se devuelve el objeto Casilla resultante de la tirada.
	 * @throws 	null
	 */
	private Casilla accionesTurnos(Jugador j, Casilla casillaDestino, int i){
		// Se añaden los turnos de castigo al jugador activo.
		j.setCastigo(i);
		// Se modifica la variable tiraOtraVez del Jugador j a false para que NO repita turno
		j.setTiraOtraVez(false);
		// Se general el mensaje para el jugador
		String s = " has caído en la casilla " + casillaDestino.getPosicion() + ": " + casillaDestino.getTipo() +
				"\nPierdes " + i + " turnos.";
		mensaje(j, s);
		// Se crea la Casilla y se devuelve
		return casillaDestino;
	}
	/**
	 * Se definen las acciones necesarias cuando se cae en una casilla tipo LABERINTO:
	 * Se desplaza a la Casilla 30.
	 * @param 	Jugador activo y Casilla destino.
	 * @return 	Se devuelve el objeto Casilla resultante de la tirada.
	 * @throws 	null
	 */	
	private Casilla accionesLaberinto(Jugador j, Casilla casillaDestino){
		Casilla nuevoDestino = arrayCasillas.get(29);
		// Se modifica la variable tiraOtraVez del Jugador j a false para que NO repita turno
		j.setTiraOtraVez(false);
		// Se general el mensaje para el jugador
		String s = " has caído en la casilla " + casillaDestino.getPosicion() + ": " + casillaDestino.getTipo() + 
				"\nDel laberinto al 30.";
		mensaje(j, s);
		// El método devuelve la Casilla del nuevo destino
		return nuevoDestino;
	}
	/**
	 * Se definen las acciones necesarias cuando se cae en una casilla tipo CALAVERA:
	 * Se desplaza a la Casilla 1.
	 * @param 	Jugador activo y Casilla destino.
	 * @return 	Se devuelve el objeto Casilla resultante de la tirada.
	 * @throws 	null
	 */
	private Casilla accionesCalavera(Jugador j, Casilla casillaDestino){
		Casilla nuevoDestino = arrayCasillas.get(0);
		// Se modifica la variable tiraOtraVez del Jugador j a false para que NO repita turno
		j.setTiraOtraVez(false);
		// Se general el mensaje para el jugador
		String s = " has caído en la casilla " + casillaDestino.getPosicion() + ": " + casillaDestino.getTipo() + 
				"\nVuelves a la casilla de inicio.";
		mensaje(j, s);
		// El método devuelve la Casilla del nuevo destino
		return nuevoDestino;
	}
	/**
	 * Se definen las acciones necesarias cuando se cae en una casilla tipo NORMAL.
	 * Simplemente, el turno termina sin acciones especiales.
	 * @param 	Jugador activo y Casilla destino.
	 * @return 	Se devuelve el objeto Casilla resultante de la tirada.
	 * @throws 	null
	 */
	private Casilla accionesNormal(Jugador j, Casilla casillaDestino){
		// Se modifica la variable tiraOtraVez del Jugador j a false para que NO repita turno
		j.setTiraOtraVez(false);
		// Se general el mensaje para el jugador
		String s = " has caído en la casilla " + casillaDestino.getPosicion() + ".";
		mensaje(j, s);
		// No hay acciones especiales. Se decuelve la Casilla
		return casillaDestino;
	}
	/**
	 * Método que envía por socket una Comunicación de tipo INFO a todos los Clientes.
	 * @param Jugador activo y la cadena de texto que se quiere mostrar en consola.
	 * @return null
	 * @throws null
	 */
	private void mensaje (Jugador j, String s){
		// Envía comunicación a todos los Clientes
		Comunicacion c = new Comunicacion("\n" + j + " >>>" + s, TipoComunicacion.INFO);
		servidor.enviarATodos(c);
		// Informa en su GUI
		servidor.infoPartidaGUI("\n" + j + " >>>" + s);
	}
	/**
	 * Método toString que devuelve una cadena de texto que representa al objeto Tablero.
	 * Este String tendrá el formato "Tablero: [1, tipo NORMAL][2, tipo NORMAL]..." .
	 * @param null
	 * @return String que representa un Tablero
	 * @throws null
	 */
	public String toString(){
		String s = "Tablero: \n";
		for (int i=0; i<63; i++){
			s += arrayCasillas.get(i).toString();
		}
		return s;
	}
}