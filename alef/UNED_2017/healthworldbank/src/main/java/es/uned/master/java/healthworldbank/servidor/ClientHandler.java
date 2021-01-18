package es.uned.master.java.healthworldbank.servidor;

import es.uned.master.java.healthworldbank.comunicacion.Pregunta;
import es.uned.master.java.healthworldbank.comunicacion.Respuesta;
import es.uned.master.java.healthworldbank.comunicacion.TipoPeticion;
import es.uned.master.java.healthworldbank.datos.ErrorHWB;
import es.uned.master.java.healthworldbank.datos.Registro;
import es.uned.master.java.healthworldbank.infraestructura.DatosHealthWorldBank;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;

/**
 * Maneja las peticiones del cliente. Se establece un time out para la conexión. En caso de superarse se cierra el socket.
 * El cliente debe de implmentar la forma de manejar el cierre del socket por parte del servidor.
 */
public class ClientHandler implements Runnable {

    /**
     * Time Out para el socket
     */
    public static final int TIMEOUT_HANDLER = 5000;

    /**
     * Socket de conexión con el cliente
     */
    private final Socket clientSocket;

    /**
     * Construtor
     * @param socket Socket de conexión con el cliente
     */
    public ClientHandler(Socket socket){
		this.clientSocket = socket;
	}


	public void run() {

	    try (ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream())){
            clientSocket.setSoTimeout(TIMEOUT_HANDLER);
            objectOutputStream.flush();
            try {
                Pregunta pregunta = (Pregunta) objectInputStream.readObject();
                Respuesta respuesta = procesarPregunta(pregunta);
                objectOutputStream.writeObject(respuesta);
                objectOutputStream.flush();
            } catch (SocketTimeoutException e){
                // do nothing
            } catch (ClassNotFoundException e){
                // do nothing
            }
        }catch(IOException e){
        	// Do nothing	
        } finally {
            try{
                if(clientSocket != null) clientSocket.close();
            } catch (IOException e){
                // do nothing
            }
        }
    }


    /**
     * Procesa la petición del cliente generando la respuesta
     * @param pregunta contenido de la petición del cliente
     * @return contenido de la respuesta al cliente
     */
    private Respuesta procesarPregunta(Pregunta pregunta) {
		
		DatosHealthWorldBank datosHWB = new DatosHealthWorldBank(pregunta.getTipoPeticion(), pregunta);
		Respuesta resp = null;
		try {	
			resp =datosHWB.getObtenerDatos();
		} catch (ServerRunningException e) {
			e.printStackTrace();
			ArrayList<Registro> datos = new ArrayList<Registro>(1);
			datos.add(new ErrorHWB(e.getLocalizedMessage()));
			resp = new Respuesta(TipoPeticion.ERROR, null,0,0,datos);
		}
		return resp;
	}

	
}


