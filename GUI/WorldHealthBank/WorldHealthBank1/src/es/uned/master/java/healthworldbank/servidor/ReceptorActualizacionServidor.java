/**
 * 
 */
package es.uned.master.java.healthworldbank.servidor;

/**
 * ReceptorActualizacionServidor.java
 * Esta interfaz define un �nico m�todo:
 * public void estadoServidorActualizado(Servidor servidor);
 * Implementar este m�todo asegura que el objeto est� preparado para recibir notificaciones cada vez que el Servidor sufre un cambio significativo. 
 * En este caso (PracticaWorldHealthBank), �nicamente se 'dispara' cuando el servidor se arranca o se para
 * 
 * @author grupo alef (Jos� Torrecilla) 
 * @date 17/4/2012
 */
public interface ReceptorActualizacionServidor {
	public void estadoServidorActualizado(Servidor servidor);
}
