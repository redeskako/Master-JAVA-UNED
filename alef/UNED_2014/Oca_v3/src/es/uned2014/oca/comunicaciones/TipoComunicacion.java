package es.uned2014.oca.comunicaciones;

/**
 * Enumeración de los posibles tipos de comunicaciones que se van a enviar entre Cliente y Servidor.
 * @author	Alef UNED 2014
 * @version	Oca 3.0
 * @fecha 	Mayo 2014
 */
public enum TipoComunicacion {
	// Comunicaciones del Cliente al Servidor
	ALIAS, TIRADA_DADO,TERMINAR,
	// Comunicaciones del Servidor al Cliente
	INFO, COLOR_JUGADOR, ES_TU_TURNO, NO_ES_TU_TURNO, HAS_GANADO, HAS_PERDIDO, FINAL
}
