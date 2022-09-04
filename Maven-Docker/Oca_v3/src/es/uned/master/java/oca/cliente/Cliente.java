package es.uned.master.java.oca.cliente;

import java.awt.Color;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

import es.uned.master.java.oca.comunicaciones.*;


/**
 * Se define una clase Cliente en la que se desarrollan los métodos necesarios para definir un cliente
 * que se conecten al Servidor y permitan jugar al juego de la oca.
 * Se define la conexión Socket para las conexiones Cliente-Servidor.
 * La clase hereda de Thread para poder gestionar el socket en paralelo a la GUI.
 *
 * @author	Alef UNED 2014
 * @version	Oca 3.0
 * @fecha 	Mayo 2014
 */
public class Cliente extends Thread {
	// Canal de salida del socket
	private ObjectInputStream sInput;
	// Canal de entrada del socket
	private ObjectOutputStream sOutput;
	// Socket para conectar con servidor
	private Socket socket;

	// Objeto Comunicacion para enviar y recibir a través del socket
	private Comunicacion comunicacion;

	// Variable para controlar si el cliente sigue activado (fin=false) o se va
	// a detener (fin=true)
	private boolean fin;

	private boolean esperando;

	// Interfaz gráfica desde la que se gestiona el objeto Cliente
	private ClienteGui gui;
	// Temporizador para que se cancele el intento de conexión después de 2 segundos
		private Timer temporizador = new Timer();
		private Cliente esteCliente = this;

		private TimerTask tarea = new TimerTask() {
			@Override
			public void run(){
				if(esteCliente.sInput == null && esperando ){
					esteCliente.stop();
					esteCliente.gui.actualizarEstadoComenzar(true);
					esteCliente.gui.actualizarEstadoTirarDado(false);
					esteCliente.gui.actualizarEstadoTerminar(false);
					esteCliente.gui.jlMensaje.setForeground(new Color (255,0,0)); 
					esteCliente.gui.escribirError("Error en la conexión: el Servidor no está "
							+ "disponible en estos momentos (se ha excedido el tiempo de espera).");
					mostrarMensaje("\nServidor no disponible. Desconectando Cliente...");
					esteCliente.cerrarSocket();
					esteCliente.getGui().eliminarCliente();
				}
			} // fin run
			}; // fin TimerTask
	/**
	 * Método constructor: se asigna el parámetros a gui (interfaz gráfica ClienteGui)
	 * Se asigna false a la variable fin: el socket no está cerrado.
	 * @param ClienteGui, que tiene la GUI desde la que el usuario interactúa
	 * @return null
	 * @throws null
	 */
	public Cliente(ClienteGui gui) {
		// Se asigna el valor a CLienteGui
		this.gui = gui;
		// Se asigna false a la variable fin: el socket no está cerrado
		this.fin = false;
	}
    /**
     * Los pasos a realizar por este método son
     * 1. Recupera los valores del servidor y el puerto desde el GUI.
     * 2. Crea un objeto de la clase socket con el servidor y puerto recuperados.
     * 3. Crea un objeto de tipo Stream para la comunicación de entrada.
     * 4. Crea un objeto de tipo Stream para la comunicacion de salida.
     * 5. En ese momento, queda a la espera de recepción de objetos por el socket.
     * La clase lee permanentemente el inputStream y analiza el tipo de Comunicacion recibida.
	 * Indica las acciones a realizar según el tipo de Comunicacion.
	 * @param null
	 * @return null
	 * @throws Error en la conexión con Servidor
	 */
	public void run() {
		esperando = true;
		// 1. Recupera los valores del servidor y el puerto desde el GUI.
		int puerto;
		String servidor;
		servidor = gui.getTfServidor().getText().trim();
		puerto = Integer.parseInt(gui.getTfPuerto().getText().trim());
		try {
			// Se inicia el temporizador para que desconecte si tarda más de 2 seg en conectar
			this.temporizador.schedule(tarea, 2000);
			// 2. Crea un objeto de la clase socket con el servidor y puerto recuperados.
			socket = new Socket(servidor, puerto);
			// Se muestra en la GUI mensaje informativa
			String msg = "\nConexion Aceptada " + socket.getInetAddress() + ":"
					+ socket.getPort() + "\nIntentando establecer los canales de entrada y salida de datos...";
			mostrarMensaje(msg);
			// 3. Crea un objeto de tipo Stream para la comunicación de entrada.
			sInput = new ObjectInputStream(socket.getInputStream());
			// 4. Crea un objeto de tipo Stream para la comunicacion de salida.
			sOutput = new ObjectOutputStream(socket.getOutputStream());
			// Se activa el botón "Terminar" de la GUI Cliente
			gui.actualizarEstadoTerminar(true);
		} catch (IOException eIO) {
			esperando = false;gui.resetBotones();
			gui.escribirError("Error en la conexión: el Servidor no está conectado (IOException).");
			mostrarMensaje("\nServidor no disponible. Desconectando Cliente...");
			cerrarSocket();	
			this.stop();
		} catch (Exception ec) {
			esperando = false;gui.resetBotones();
			gui.escribirError("Error conectando con Servidor (Exception).");
			mostrarMensaje("\nServidor no disponible. Desconectando Cliente...");
			cerrarSocket();
			this.stop();
		}
		esperando = false;
		// 5. En ese momento, queda a la espera de recepción de objetos por el socket
		while (!fin) {
			try {
				// 5.1 Se lee el canal de entrada del socket y se hace un casting a Comunicacion
				comunicacion = (Comunicacion) sInput.readObject();
			} catch (IOException e2) {
				// Si se recibe un error en la entrada de datos, se finaliza el bucle
				break;
			} catch (ClassNotFoundException e) {
				// Si se recibe un error en la entrada de datos, se finaliza el bucle
				gui.escribirMensaje("No encontrada la clase");
				break;
			}
			// 5.2 Se obtiene el mensaje (String) de la Comunicacion
			String st = comunicacion.getMensaje().trim();
			// 5.3 Se realizan diferentes acciones en función del tipo de Comunicacion
			switch (comunicacion.getTipo()) {
			case INFO:
				// Si es INFO, se muestran en el área de texto de la GUI
				gui.escribirMensaje(st);
				break;
			case COLOR_JUGADOR:
				// Si es COLOR_JUGADOR, se muestra el color en la GUI del Cliente
				gui.escribirColor(st);
				break;
			case ES_TU_TURNO:
				// Si es ES_TU_TURNO, se activa el botón "Lanzar dado"
				gui.actualizarEstadoLanzamiento(true);
				break;
			case NO_ES_TU_TURNO:
				// Si es NO_ES_TU_TURNO, se desactiva el botón "Lanzar dado"
				gui.actualizarEstadoLanzamiento(false);
				break;
			case HAS_GANADO:
				// Si es HAS_GANADO, se realiza alguna acción especial en la GUI (sin desarrollar)
				break;
			case HAS_PERDIDO:
				// Si es HAS_PERDIDO, se realiza alguna acción especial en la GUI (sin desarrollar)
				break;
			case FINAL:
				// Se asigna true a la variable fin: el socket se cierra, se sale del bucle
				fin = true;
				// Se muestra mensaje informativo en la GUI
				gui.escribirMensaje("Desconectando del servidor...");
				// Se reinicia el estado inicial de los botones de la GUI
				gui.resetBotones();
				// Se desconecta el Cliente
				this.desconectar();
				break;
			default:
				// Si es de otro tipo, no se hace nada.
			}
		}
	}
    /**
     * Se muestra el contenido de un String en el área de texto de la GUI Cliente
	 * @param String para mostrar
	 * @return null
	 * @throws null
	 */
	public void mostrarMensaje(String msg) {
		// Llama al mensaje escribirMensaje de ClienteGui
		gui.escribirMensaje(msg);
	}
    /**
     * Antes de desconectar, se env�a a Servidor una Comunicacion TERMINAR.
	 * @param null
	 * @return null
	 * @throws null
	 */ 
	public void desconectar() {
		// Se asigna true a la variable fin: el socket se cierra, se sale del bucle
		this.fin = true;
		// Antes de desconectar, se envía a Servidor una Comunicacion TERMINAR.
		Comunicacion mensaje = new Comunicacion("", TipoComunicacion.TERMINAR);
		this.enviarMensaje(mensaje);
		this.cerrarSocket();
	}
    /**
     * Se cierra canal de Entrada, canal de Salida y Socket
	 * @param null
	 * @return null
	 * @throws Error al cerrar el canal de entrada, se dalida o el socket
	 */ 
	public void cerrarSocket(){
		try {
			// Se cierra el canal de entrada, si existe
			if (sInput != null)
				sInput.close();
			mostrarMensaje("\nEl input se ha cerrado correctamente.");
		} catch (Exception e) {
			gui.escribirError("Se ha Producido un Error en la Desconexión de Entrada...");
		}
		try {
			// Se cierra el canal de salida, si existe
			if (sOutput != null)
				sOutput.close();
			mostrarMensaje("El output se ha cerrado correctamente.");
		} catch (Exception err) {
			gui.escribirError("Se ha Producido un Error en la Desconexi�n de Salida...");
		}
		try {
			// Se cierra el socket, si existe
			if (socket != null)
				socket.close();
			mostrarMensaje("El socket se ha cerrado correctamente.");
		} catch (Exception e) {
			gui.escribirError("Se ha Producido un Error en la Desconexión del Socket...");
		}
		// Se elimina el cliente de manera definitiva. Si se vuelve a conectar, se creará uno nuevo
		gui.eliminarCliente();
		// Se reinicia el estado inicial de los botones de la GUI
		gui.resetBotones();
		// Se informa en la GUI del Cliente de que se ha desconectado correctamente
		mostrarMensaje(" <<< Cliente desconectado (cerrado socket) >>>");
	}
    /**
     * Método que envía al Servidor a través del socket un objeto de tipo Comunicacion
	 * @param Comunicacion que se va a enviar
	 * @return null
	 * @throws Error de escritura en el socket
	 */ 
	public void enviarMensaje(Comunicacion msg) {
		try {
			// Se envía la Comunicacion
			sOutput.writeObject(msg);
		} catch (IOException e) {
			gui.escribirError("Error de Escritura: " + e);
		}
	}
	/**
	 * Obtiene el valor de la variable gui
	 * @param null
	 * @return el valor de gui
	 * @throws null
	 */
	public ClienteGui getGui(){
		return this.gui;
	}
}