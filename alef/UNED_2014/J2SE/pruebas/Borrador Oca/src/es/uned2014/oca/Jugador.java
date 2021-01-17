/* Aqui se definen el numero de jugadores que participaran, minimo 2, maximo 6
 * El atributo que identifica a los jugadores sera el color de su ficha, colores:
 * rojo, amarillo, naranja, verde, azul y violeta
 * 
 * Contendra los metodos para tirar el dado, mover la ficha y perder el turno
 */

package es.uned2014.oca;

public class Jugador {
	private boolean jugadorActivo; // si el jugador esta activo sera true, si no lo esta sera false
	private int turnoSinJuego; // variable para los turno sin participar de un jugador
	private int ordenTirada; // orden de cada jugador
	private String color; // color de la ficha del jugador
	
	// constructor de un jugador
	public Jugador( String color) {
		this.jugadorActivo = false; // por defecto esta inactivo
		this.turnoSinJuego = 0; // empieza sin penalizaciones
		this.ordenTirada = 0; // se definira con la tirada inicial
		this.color = color; // dependera el numero de jugadores
	} // fin constructor jugador
	
	// setters & getters de todo...
	
	// metodo para tirar el dado
	public int tirarDado() {
		return (int) Math.random() * 6 + 1;
	} // fin tirarDado

} // fin clase Jugador
