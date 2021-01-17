package Cliente;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Cliente {

	private ObjectInputStream sInput;		// to read from the socket
	private ObjectOutputStream sOutput;		// to write on the socket
	private Socket socket;
	
	
	private MiClienteGui gui;
	
	public Cliente(MiClienteGui gui){
		  this.gui = gui;
	
	}
	
	public boolean iniciarComunicacion() {
		/*
		 * Los pasos a realizar por este método son
		 * 1. Recupera los valores del servidor y el puerto desde el GUI
		 * 2. Crea un objeto de la clase socket con el servidor y puerto recuperados.
		 * 3. Crea un objeto de tipo Stream para la comunicación de entrada.
		 * 4. Crea un objeto de tipo Stream para la comunicacion de salida.  
		 */
		
		int puerto;
		String servidor;
		boolean comunicacion = false;
		// Se recupera el servidor y el puerto desde la ventana
		
		servidor = gui.getTfServidor().getText().trim();
		puerto = Integer.parseInt(gui.getTfPuerto().getText().trim());
			
		// try to connect to the server
		
		try {
			socket = new Socket(servidor, puerto);
		
		} 
		// if it failed not much I can so
		catch(Exception ec) {
			mostrarMensaje ("Error connectiong to server:" + ec);
			return false;
		}
		comunicacion = true;
		String msg = "Connection accepted " + socket.getInetAddress() + ":" + socket.getPort();
		mostrarMensaje(msg);
		
	
		/* Creating both Data Stream */
		try
		{
			sInput  = new ObjectInputStream(socket.getInputStream());
			sOutput = new ObjectOutputStream(socket.getOutputStream());
		}
		catch (IOException eIO) {
			mostrarMensaje("Exception creating new Input/output Streams: " + eIO);
			return false;
		}

	/*	// creates the Thread to listen from the server 
		new ListenFromServer().start();
		// Send our username to the server this is the only message that we
		// will send as a String. All other messages will be ChatMessage objects
		try
		{
			sOutput.writeObject(username);
		}
		catch (IOException eIO) {
			mostrarMensaje("Exception doing login : " + eIO);
			disconnect();
			return false;
		}
		// success we inform the caller that it worked
		return true; */
		
		return comunicacion;
	}
	
	
	
	private void mostrarMensaje(String msg) {
		boolean mostrar = true;
		gui.setJlMensaje(msg);
		gui.visibilidadError(mostrar);
	}
}
