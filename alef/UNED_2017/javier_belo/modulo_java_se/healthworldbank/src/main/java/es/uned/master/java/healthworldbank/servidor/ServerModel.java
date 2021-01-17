package es.uned.master.java.healthworldbank.servidor;

import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Modelo del servidor. Gestiona las peticiones al servidor creado un Thread por petición
 * con un máximo de peticiones gestionadas
 * @author jbelo
 */
public class ServerModel {

    /**
     * PUERTO en el que escucha el servidor
     */
    public static final int PORT = 5000;

    /**
     * Cantidad máxima de threads para gestión de peticiones del cliente
     */
    private static final int MAX_THREADS = 50;

    /**
     * Tiempo máximo de espera por el ThreadPool para cancelar los procesos en milesgundos
     */
    private static final int TIME_OUT_POOL = 10000;

    /**
     * Tiempo  máximo de escucha en milesegundos
     */
    private static final int TIMEOUT_SERVER = 5000;

    /**
     * Thread que gestiona la entrada de nuevas peticiones
     */
    private ServerThread serverThread = null;

    /**
     * Vista del servidor
     */
    private OnUpdateViewInterface onServerChangeListener;

    /**
     * Constructor
     * @param onServerChangeListener vista del servidor
     */
    public ServerModel(OnUpdateViewInterface onServerChangeListener) {
		this.onServerChangeListener = onServerChangeListener;
	}

    /**
     * Constructor
     */
    public ServerModel(){
		
	}

    /**
     * Arranca el servidor
     */
    public void startServer()  {
		if(serverThread != null && serverThread.isAlive()) return;
		serverThread = new ServerThread("server_thread");
		serverThread.start();
	}


    /**
     * Para el servidor
     */
    public void stopServer() {
		if (serverThread == null || !serverThread.isAlive()) return;
		onServerChangeListener.displayMessage("Cerrando Servidor...");
		serverThread.cancel();
	}

    /**
     * Thread encargado de la recepción de peticiones al servidor
     */
    private class ServerThread extends Thread{

        /**
         * Variable para detener el servidor
         */
        private boolean running = true;

        /**
         * ThreadPool
         */
        private final ThreadPoolExecutor threadPoolExecutor;

        /**
         * Constructor
         * @param threadName nombre asignado al Thread
         */
        public ServerThread(String threadName){
			super(threadName);
            threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(MAX_THREADS);
		}	
		
		public void run(){

			try (ServerSocket serverSocket = new ServerSocket(PORT)){
	            serverSocket.setSoTimeout(TIMEOUT_SERVER);
	            while (running) {
	                try {
						Socket socket = serverSocket.accept();
						ClientHandler ch = new ClientHandler(socket);
                        threadPoolExecutor.execute(ch);
					} catch (SocketTimeoutException e) {
						// do nothing
					}
	            }

	            try{
	                threadPoolExecutor.awaitTermination(TIME_OUT_POOL,TimeUnit.MILLISECONDS);
                }catch (InterruptedException e){
	                // do nothing
                }

                serverSocket.close();
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        } finally {
			    SwingUtilities.invokeLater(new MessageServerClosed());
            }
		}

        /**
         * cancela el Thread. El servidor se cierra.
         */
        public void cancel(){
			running = false;
		}


	}

    /**
     * Clase para comunicarse con la Vista desde el Thread del servidor configurando la Vista y
     * mostrando un mensaje al usuario
     */
    private class MessageServerClosed implements Runnable{
        @Override
        public void run() {
            onServerChangeListener.setStatus(false, "Servidor cerrado");
        }
    }



}
