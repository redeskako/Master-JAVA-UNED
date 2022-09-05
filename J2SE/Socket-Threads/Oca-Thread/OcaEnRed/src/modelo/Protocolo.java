/**
* 2014, Antonio Jesús Arquillos Álvarez
* Máster en Diseño y Desarrollo de Aplicaciones Web
* Universidad Nacional de Educación a Distancia
*/

package modelo;

public class Protocolo {
	// Define las constantes de mensajes de conexión y petición de acción
	
	// Mensaje de OK
	public static final String OK = "OK";
	// Mensaje de No OK
	public static final String KO = "KO";
	// Petición de nuevo jugador 
	public static final String NUEVO = "NUEVO";
	// Petición de jugador invitado
	public static final String INVITADO = "INVITADO";
	// Petición de comienzo de partida
	public static final String INICIO = "COMIENZAPARTIDA";
	// Petición de tirar dados
	public static final String TIRADA = "TIRADA";
	// El jugador se ha presentado al resto
	public static final String PRESENTADO = "PRESENTADO";
	
}
