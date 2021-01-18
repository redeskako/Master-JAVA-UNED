package es.uned2013.parchis.rmi.client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.BlockingQueue;

import es.uned2013.parchis.Colores;
import es.uned2013.parchis.rmi.server.ParchisServer;
import es.uned2013.parchis.ui.ParchisConsola;
import es.uned2013.parchis.ui.ParchisGUI;
import es.uned2013.parchis.ui.ParchisI18Keys;
import es.uned2013.parchis.ui.ParchisI18NUI;
import es.uned2013.parchis.ui.ParchisUI;
import es.uned2013.parchis.ui.ParchisUIMode;

public class ParchisClient {

	private ParchisUI ui = null;
	
	public static void main(String args[]){
		ParchisClient client = new ParchisClient();
		
		//Nos traemos el stub.
		client.registerClient();
	}
	
	private void registerClient(){
		
		ParchisServer server; // Servidor del parchís;
		Registry registry; // Registro de RMI.
		String name; // Nombre del servidor.
		ParchisUIMode uiMode = ParchisUIMode.GUI; // CONSOLE o GUI.
		Locale loc = new Locale("es"); // Idioma
		// Archivo para traducción de cadenas de texto.
		ResourceBundle rb = ResourceBundle.getBundle("Mensajes", loc); 
		BlockingQueue<ParchisUI> uiList; // Lista de UIs de clientes.
		int index; // Indice del nuevo cliente.
		
		try {
			name = "ParchisServer";
			registry = LocateRegistry.getRegistry();
			server = (ParchisServer) registry.lookup(name);
	      
			// Obtenemos el array de UIs y asignamos el
			// siguiente índice del jugador;
			uiList = server.getUIList();
			index = uiList.size();
			
			if ((index < 4) && !(server.isEnJuego())) {         
				// Inicializamos el interfaz de usuario
				this.setUI(uiMode, loc, server, index);
				ParchisUI stub = (ParchisUI) UnicastRemoteObject.exportObject(ui, 0);
	            
				// Añadimos el UI del jugador al servidor del parchís,
				// y volvemos a obtener la lista de UI's.
				server.registrarJugador(stub);
				uiList = server.getUIList();
				
				// Recorremos la lista de UIs
				for (int i = 0; i < uiList.size(); i++) {
					// Sólo en modo GUI actualizamos la visión del tablero para
					// todos los UIs, llamando a mostrarTablero(), al cual le
					// hemos asigando la función de crear y mostrar las fichas 
					// del nuevo jugador en cada interfaz del cliente. 
					// En el caso particular de GUI en modo ONLINE, no es 
					// necesario haber creado previamente el tablero, de 
					// manera que pasamos 'null' como parámetro.
					if (uiMode.equals(ParchisUIMode.GUI))
						((ParchisUI) uiList.toArray()[i]).mostrarTablero(null);
					// Si no es el índice del nuevo jugador,
					// mostramos un mensaje al resto informando
					// que se ha conectado.
					if (i != index)
						((ParchisUI) uiList.toArray()[i]).mostrarMensajeString(ParchisI18Keys.REGISTROCLIENTE, 
								Colores.getColor(index).toString());
				}
			}
			else {
				if (index >= 4)
					System.out.println(rb.getString(ParchisI18Keys.NUMEROINCORRECTO));
				else
					System.out.println(rb.getString(ParchisI18Keys.PARTIDAINICIADA));
			}
		} catch (Exception e) {
			System.err.println("ERROR:" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	//NOTA: Estos UI a null estan mal daran NullPointerException. Refactorizar.
	public void setUI(ParchisUIMode mode, Locale loc, ParchisServer server, 
			int index){
		ParchisUI inner = null;
		
		switch(mode){
			// interfaz con entrada/salida de datos por consola
			case CONSOLE:
				inner = new ParchisConsola(server); 
				break;
			// interfaz con entrada/salida de datos en modo gráfico
			case GUI:		
				inner = new ParchisGUI(loc, server, index);
				break;
		}
		
		/* Si no hay información del contexto local del usuario
		 * se carga el idioma por defecto.
		 * Si la hubiera se busca un archivo 'properties' en el 
		 * idioma de su contexto local, si no estuviese disponible
		 * automáticamente se seleccionaría el idioma por defecto.
		 */
		if (loc == null) {
			ui = inner;
		}else{
			ui = new ParchisI18NUI(inner, loc, server, index);
		}
	}
}
