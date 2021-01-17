package Servidor;

import java.net.*;
import java.io.*;
import java.util.*;


/**
 * Se define una clase Servidor en la que se desarrollan los métodos necesarios para definir un servidor
 * al que conectar los diferentes clientes y que ejecute la aplicación.
 * Se define la conexión ServerSocket para las conexiones Socket de los clientes.
 * 
 * @author	Alef UNED 2014
 * @version	Oca 3.0
 * @fecha 	Mayo 2014
 */

public class Servidor {
	// Variable DriverServidor que hace referencia al Driver del Servidor, que implementa GUI
	private static DriverServidor servidorGUI;
	// Variable de tipo colección (ArrayList) que representa el conjunto de conexiones de Clientes
	private ArrayList<ClienteThread> arrayClientes;
    // Variable de tipo entero que representa el puerto del Servidor para la conexión Cliente-Servidor
    private int puertoServidor;
    // Variable tipo booleano que será true mientras el Servidor esté conectado
    private boolean servidorConectado;
    
    // Variable de tipo entero que representa el número máximo de Clientes admitidos en cada partida
    private static int maxClientes;
    
    // Variable ServerSocket 
    ServerSocket socketServidor;

    
    // Conjunto de Colores posibles (id de los Clientes)
    Color[] colores = {Color.ROJO, Color.AZUL, Color.AMARILLO, Color.VERDE, Color.NARANJA, Color.VIOLETA};
    
    /**
	 * Método constructor: se asigna puertoServidor y servidorGUI. Se inicializa el ArrayList<ClienteThread>
	 * @param Valor entero que se asigna al puerto
	 * @param DriverServidor, que tiene la GUI desde la que el usuario interactúa con el Servidor
	 * @return null
	 * @throws null
	 */
    public Servidor(int i, DriverServidor ds){
    	this.puertoServidor = i;
    	this.servidorGUI = ds;
    	this.arrayClientes = new ArrayList<ClienteThread>();
    }
    
    /**
     * Se inicia la conexión ServerSocket y se queda a la espera de conexiones de Clientes.
     * @param null
     * @return null
     * @throws null
     */
    public void iniciarServidor(){
    	// Se pone a true el valor booleano que controla si el Servidor está conectado o no
    	this.servidorConectado = true;
    	
    	try{
        	// Se crea la conexión ServerSocket
        	socketServidor = new ServerSocket(puertoServidor);
        	
        	// Se espera a que haya conexiones de Clientes y se crean las conexiones
        	int contador = 0;
        	while (servidorConectado && arrayClientes.size() <= maxClientes){
        		servidorGUI.infoConexiones("Servidor a la espera de conexiones de clientes");
        		
        		// Se registra la conexión de un Cliente
        		Socket nuevoSocket = socketServidor.accept();

        		// Se construye un ClienteThread (hilo) con esa conexión
        		ClienteThread nuevoCliente = new ClienteThread(nuevoSocket, colores[contador]);
        		// Ejecuta el método run() del ClienteThread
        		nuevoCliente.start();
        		
        		// Se añade al array de conexiones con clientes
        		this.arrayClientes.add(nuevoCliente);
        	}
    	} catch (IOException e) {
    		servidorGUI.infoConexiones("Se ha producido un error al establecer conexiones Cliente-Servidor." + e);
        }

    }
    
    /**
     * Se cierra la conexión ServerSocket y las conexiones Socket con CLientes (ClienteThread).
     * @param null
     * @return null
     * @throws null
     */ 
	public void cerrarServidor(){
		servidorConectado = false;
		
		// Se cierra el ServerSocket
    	try{
    		socketServidor.close();
		} catch (IOException e) {
			servidorGUI.infoConexiones("Se ha producido un error al cerrar el Servidor." + e);
		}
    	
    	// Se cierran todas las conexiones con Clientes
    	for (int i=0; i<arrayClientes.size(); i++){
    		ClienteThread c = arrayClientes.get(i);
    		c.cerrar();
    	}
    }
    

    
    
    
    
    
    
    
    
    
    /** 
     * Método para obtener el contenido de la variable numeroClientes.
     * @param null
	 * @return Devuelve el numeroClientes
	 * @throws null
	 */
	public static int getmaxClientes() {
		return maxClientes;
	}

	/** 
	 * Método para establecer un nuevo valor a la variable numeroClientes
	 * @param valor entero para asignar a la variable numeroClientes
	 * @return null
	 * @throws null
	 */
	public static void setMaxClientes(int i) {
		maxClientes = i;
	}

    /** 
     * Método para obtener el contenido de la variable servidorGUI.
     * @param null
	 * @return Devuelve el servidorGUI
	 * @throws null
	 */
	public static DriverServidor getServidorGUI() {
		return servidorGUI;
	}
	
	
	
	
	
	
}
