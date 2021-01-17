package Servidor;

import java.net.*;
import java.io.*;


/**
 * Se define una clase ClienteThread para que el Servidor se comunique a través de conexiones Socket 
 * de manera simultánea con los diferentes Clientes.
 * 
 * @author	Alef UNED 2014
 * @version	Oca 3.0
 * @fecha 	Mayo 2014
 */
public class ClienteThread extends Thread{
    // Variables necesarias para establecer una conexión Socket.
	private Socket socket;
	private ObjectInputStream entrada;
	private ObjectOutputStream salida;
	
	// Identificador del Cliente: el color del jugador
	private Color id;
	
	// Mensaje que puede enviar/recibir
	private Comunicacion comunicacion;

    /**
	 * Método constructor: cada hilo se construye con un único parámetro: la conexión socket del Cliente.
	 * @param Socket del Cliente que se acaba de conectar
	 * @return null
	 * @throws null
	 */
    public ClienteThread (Socket s, Color c){
    	// Se asigna la conexión recibida como parámetro a la variable socket
    	this.socket = s;
    	// Se asigna el color como id de la conexión
    	this.id = c;
    	
    	// Canales de entrada y salida
    	// Se informa en la GUI:
    	Servidor.getServidorGUI().infoConexiones("Intentando establecer los canales de entrada y salida de datos");
    	// Se crean los canales:
    	try{
    		this.entrada = new ObjectInputStream(socket.getInputStream());
    		this.salida = new ObjectOutputStream(socket.getOutputStream());
    		Servidor.getServidorGUI().infoConexiones("El Jugador " + this.id + " se ha conectado al Servidor.");
    	} catch (IOException e) {
    		Servidor.getServidorGUI().infoConexiones("Se ha producido un error al establecer "
    				+ "los canales de entrada y salida de datos");
        }
    }

    
    
    
    
    
    
    
    public void cerrar(){
    	try{
	    	this.entrada.close();
	    	this.salida.close();
	    	this.socket.close();
    	} catch (IOException e){
    		Servidor.getServidorGUI().infoConexiones("Se ha producido un error al cerrar la conexión con el Cliente " + this.id);
    	}
    }

	public Color getColorId() {
		return id;
	}

}
