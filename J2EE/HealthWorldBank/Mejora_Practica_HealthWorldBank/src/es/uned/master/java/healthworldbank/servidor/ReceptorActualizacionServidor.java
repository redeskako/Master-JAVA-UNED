/**
 * 
 */
package es.uned.master.java.healthworldbank.servidor;

/**
 * ReceptorActualizacionServidor.java
 * Esta interfaz define un único método:
 * public void estadoServidorActualizado(Servidor servidor);
 * Implementar este método asegura que el objeto está preparado para recibir notificaciones cada vez que el Servidor sufre un cambio significativo. 
 * En este caso (PracticaWorldHealthBank), únicamente se 'dispara' cuando el servidor se arranca o se para
 * 
 * @author grupo alef (José Torrecilla) 
 * @date 17/4/2012
 */
public interface ReceptorActualizacionServidor {
	public void estadoServidorActualizado(Servidor servidor);
	
	/**
	 * Se añaden estos métodos para poder enviar notificaciones a servidorGUI
	 */
	public void setNotificaciones(String texto);
	public void estadoServidorActualizado(String mensajeServer);
}
