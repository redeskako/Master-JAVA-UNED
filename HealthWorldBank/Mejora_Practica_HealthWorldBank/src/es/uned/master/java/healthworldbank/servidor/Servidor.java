/**
 * 
 */
package es.uned.master.java.healthworldbank.servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import es.uned.master.java.healthworldbank.comunicacion.Pregunta;
import es.uned.master.java.healthworldbank.comunicacion.Respuesta;
import es.uned.master.java.healthworldbank.comunicacion.TipoPeticion;
import es.uned.master.java.healthworldbank.datos.ErrorHWB;
import es.uned.master.java.healthworldbank.datos.Registro;
import es.uned.master.java.healthworldbank.infraestructura.DatosHealthWorldBank;

/**
 * Servidor.java
 * Clase encargada de gestionar un servidor socket que procesar� las Preguntas procedentes de las aplicaciones cliente y enviar� las Respuestas correspondientes.
 * Crear� un hilo de ejecuci�n que a su vez gestionar� un ServerSocket que escuchar� comunicaciones desde el puerto 'PUERTO'.
 * Este hilo a su vez puede crear m�s hilos, uno por cada conexi�n entrante que le llegue. 
 * Estos hilos se encargar�n de la comunicaci�n con cada una de las aplicaciones cliente individualmente.  
 * 
 * @author grupo alef (Jos� Torrecilla)
 * @date 17/4/2012
 */
public class Servidor {
	
	// Puerto del servidor. Por defecto 10088
	public static final int PUERTO = 10088;

	// N�mero m�ximo de conexiones simult�neas
	public static final int MAXIMO_CONEXIONES = 50;	//Pongo 50 por poner un n�mero alto

	// Timeout general para las conexiones socket en milisegundos. Si es 0 se entiende que no hay TIMEOUT
	public static final int TIMEOUT = 600000;	//Pongo un Timeout alto, 10 minutos
	
	//Definimos un timeout (en milisegundos) corto utilizado en algunas partes del c�digo
	//private static final int TIMEOUT_CORTO = 100;
	private static final int TIMEOUT_CORTO = 1000;

	//Objeto que implementa la interfaz ReceptorActualizacionServidor que recibir� 
	//un mensaje cada vez que se produzca una modificaci�n relevante en el servidor
	//En este caso (PracticaWorldHealthBank) cuando el servidor arranque o pare
	private ReceptorActualizacionServidor receptor;

	//Hilo encargado de escuchar las conexiones entrantes de la aplicaci�n cliente
	private HiloServidor hiloServidor = null;
		
	/**
	 * Constructor de clase. Crea el Servidor y almacena el 'receptor'
	 * 
	 *  @param receptor Objeto que implementa la interfaz ReceptorActualizacionServidor que recibir� el mensaje 
	 *  estadoServidorActualizada(Servidor servidor) cada vez que el estado del servidor sufra un cambio. 
	 *  Si se pasa como argumento 'null', simplemente estas notificaciones no se env�an 
	 */
	public Servidor(ReceptorActualizacionServidor receptor) {
		this.receptor = receptor;
	}

	/**
	 * M�todo que arranca el servidor.
	 * Si el servidor ya se encontraba arrancado anteriormente, �ste se para y se vuelve a arrancar. 
	 * De esta forma no es necesario para el servidor antes de invocar a este m�todo. 
	 */
	public void arrancarServidor() {
		//Paramos el servidor, si es que est� arrancado
		if (servidorArrancado()) {
			pararServidor();
		}
		
		//Creamos un nuevo HiloServidor y lo iniciamos
		hiloServidor = new HiloServidor("hiloServidor", this);
		hiloServidor.start();
		System.out.println("Servidor arrancado");
		
		//Notificamos actualizaci�n de estado
		this.notificarModificacionEstado();
	}

	/**
	 * M�todo que para el servidor.
	 * Este m�todo puede demorarse algunos segundos si hay problemas de comunicaci�n con alguna aplicaci�n cliente 
	 */
	public void pararServidor() {
		//Si el servidor no est� arrancado, no hacemos nada
		if (!servidorArrancado()) {
			return;
		}

		//Cancelamos el hiloServidor
		hiloServidor.cancelar();

		//Esperamos a que hiloServidor finalice
		try {
			hiloServidor.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//Notificamos actualizaci�n de estado
		this.notificarModificacionEstado();
	}

	/**
	 * M�todo que indica si el servidor est� arrancado o no.
	 * @return Si el servidor est� arrancado devuelve 'true', en caso contrario 'false' 
	 */
	public boolean servidorArrancado() {
		if (hiloServidor != null){
			return hiloServidor.isAlive();
		}
		return false;
	}
	
	/**
	 * M�todo privado que se invoca cada vez que hay un cambio en el estado del servidor
	 * Este m�todo crea una instancia de Notificador, que se encarga de notificar (en el hilo principal de la aplicaci�n) a 'receptor'
	 * de que ha habido un cambio en el estado del servidor 
	 */
	public void notificarModificacionEstado() {
		if (this.receptor != null) {
			//Creamos un Notificador
			Runnable notificarEnHiloPrincipal = new Notificador(this);
			
			System.out.println("Se va a notificar desde Servidor un cambio en el estado del servidor");
			
			//Hacemos que se ejecute en el hilo principal, ya que indirectamente provocar� que se actualice la interfaz de usuario
			SwingUtilities.invokeLater(notificarEnHiloPrincipal);
			
		}
	}


	/**
	 * Clase privada encargada de gestionar (en un hilo propio) un servidor socket que escuchar� en el puerto PUERTO.
	 * A su vez, crear� m�s hilos (instancias de HiloConexion) que se encargar�n de gestionar cada una de las conexiones socket individuales 
	 */
	private class HiloServidor extends Thread{

		//Hilos de las conexiones activas (quiz� ya cerradas) de este servidor 
		private volatile ArrayList<HiloConexion> conexiones = new ArrayList<HiloConexion>(MAXIMO_CONEXIONES);
		
		//Indica si el hilo se ha cancelado o no. Se comprueba de vez en cuando en el c�digo para finalizar el hilo
		private volatile boolean cancelado = false;

		//La instancia de Servidor 'padre', la que gestiona este hilo
		private Servidor servidor; 

		/**
		 * Constructor de la clase. Crea un hilo con el nombre nombreHilo indicado y 'servidor' como instancia de Servidor 'padre'
		 * @param nombreHilo Nombre del hilo. No se utiliza internamente, puede ser de utilidad para quien cree la instancia
		 * @param servidor Instancia de Servidor 'padre', la que 'gestiona' este HiloServidor
		 */		
		HiloServidor(String nombreHilo,Servidor servidor) {
			super(nombreHilo);
			this.servidor = servidor;
		}

		/**
		 * Cancela (finaliza) el hilo actual, parando el servidor. 
		 * Este m�todo puede demorarse (dependiendo del valor de TIMEOUT) si el servidor tiene alguna conexi�n activa con problemas de comunicaci�n.
		 */
		public void cancelar() {
			//En el m�todo run() se comprueba a menudo el valor de 'cancelado', y si es true se finaliza dicho m�todo
			System.out.println("Se va a cancelar el hiloServidor");
			cancelado = true;
		}

		/**
		 * M�todo principal de la clase (al extender Thread). Este m�todo crea un servidor socket en el puerto PUERTO y acepta hasta MAXIMO_CONEXIONES en dicho servidor.
		 * Por cada conexi�n aceptada se crea una instancia de HiloConexion (un nuevo hilo) que escucha las peticiones de dicho cliente
		 */
		public void run() {
			System.out.println("Se ha iniciado el HiloServidor");
			
			//Este ser� nuestro servidor socket
			ServerSocket servidorSocket = null;

			try {
				//Creamos nuestro servidor socket
				servidorSocket = new ServerSocket(PUERTO);
				
				//Definimos un timeout corto (en milisegundos) para este ServerSocket
				// De esta forma conseguimos que se compruebe a menudo si se ha cancelado el hilo (esto ocurre si cancelado == true)
				servidorSocket.setSoTimeout(TIMEOUT_CORTO);
				
				//Mientras no se haya cancelado el hilo seguimos escuchando en servidorSocket
				System.out.println("Comenzamos a escuchar nuevas conexiones en el HiloServidor");
				while(!cancelado) {
					try {
						//Creamos un nuevo HiloConexion con el Socket obtenido al aceptar la conexi�n entrante, lo arrancamos y lo a�adimos a nuestro ArrayList de conexiones
						HiloConexion nuevoHilo = new HiloConexion("Conexion"+conexiones.size(),servidorSocket.accept(),servidor);
						System.out.println("Hemos aceptado una nueva conexi�n en el HiloServidor");
						nuevoHilo.start();
						conexiones.add(nuevoHilo);
					} catch (SocketTimeoutException e) {
						//Esto significa que no hemos recibido una conexi�n entrante en 'timeoutCorto' milisegundos
						//Simplemente ignoro la excepci�n y sigo esperando nuevas conexiones
						System.out.println("TIMEOUT = " + e.toString());
					}
					
					/**
					*  Se a�aden unas trazas por consola del estado de las conexiones activas
					*  que se repiten cada segundo.
					*/

					System.out.println("numeroConexionesActivas = " + numeroConexionesActivas());
					System.out.println("Hilo Servidor = " + this.getName());
					System.out.println("Socket = " + servidorSocket.getInetAddress().getHostAddress() + ":" + servidorSocket.getLocalPort());
					System.out.println("....");
					for(HiloConexion h:conexiones) {
						System.out.println("Hilos conexion = " + h.getName() + h.getState());
					}
					
					//Si hay demasiadas conexiones activas, ponemos el servidor a "dormir" hasta que algunas conexiones actuales finalicen
					while (numeroConexionesActivas() >= MAXIMO_CONEXIONES) {
						try {
							Thread.sleep(TIMEOUT_CORTO);
						} catch (InterruptedException e) {
							//Hilo interrumpido. En principio no tengo nada que hacer aqu�, sigo como si nada...
						}
					}		
				}
	
			} catch (Exception e) {
				System.out.println("Se ha generado una Excepci�n en el m�todo run() de HiloServidor:");
				e.printStackTrace();
			} finally {

				//Cancelamos todas las conexiones que tenemos en este HiloServidor
				for(HiloConexion h:conexiones) {
					h.cancelar();
				}

				//Esperamos a que esas conexiones efectivamente acaben
				try {
					for(HiloConexion h:conexiones) {	
						h.join();
					} 
				} catch (InterruptedException e) {
					// Este hilo se ha interrumpido. Escribo un log y sigo cerrando cosas...
					System.out.println("Se ha generado una Excepci�n en el m�todo run() de HiloServidor al esperar el cierre de las conexiones previamente abiertas:");
					e.printStackTrace();

				}

				//Cerramos servidorSocket si a�n est� abierto
				if ( servidorSocket != null && !servidorSocket.isClosed() ) {
					try {
						servidorSocket.close();
					} catch (IOException e) {
						System.out.println("Se ha generado una Excepci�n en el m�todo run() de HiloServidor cerrando el servidor socket:");
						e.printStackTrace();
					}
				}
				System.out.println("Ha finalizado el HiloServidor");
			}


		}
		
		/**
		 * M�todo privado que calcula el n�mero de conexiones actualmente activas en el servidor
		 * @return N�mero de conexiones actualmente activas en el servidor
		 */
		private int numeroConexionesActivas() {
			//Vamos a contar el n�mero de conexiones activas que hay
			int resultado = 0;
			if (conexiones.size()>0) {
				for (HiloConexion h:conexiones) {
					if(h.isAlive()) {
						resultado++;
					}
				}
			}
			
			return resultado;
		}
	}


	/**
	 * Clase privada encargada de gestionar (en un hilo propio) un servidor socket que escuchar� en el puerto PUERTO.
	 */
	private class HiloConexion extends Thread {
		
		//Socket de la conexi�n. La variable m�s 'importante'
		private Socket socket;
		
		//Flujos de entrada y de salida del socket
		private ObjectInputStream entrada;
		private ObjectOutputStream salida;
		
		//Indica si el hilo se ha cancelado o no. Se comprueba de vez en cuando en el c�digo para finalizar el hilo
		private volatile boolean cancelado = false;
		

		/**
		 * Constructor de la clase. Crea un hilo con el nombre nombreHilo indicado y servidor como instancia de Servidor 'padre'
		 * @param nombreHilo Nombre del hilo. No se utiliza internamente, puede ser de utilidad para quien cree la instancia
		 * @param socket Socket de la conexi�n
		 * @param servidor Instancia de Servidor 'padre', la que 'gestiona' el HiloServidor que a su vez gestiona este HiloConexion
		 */		
		HiloConexion(String nombreHilo, Socket socket, Servidor servidor){
			super(nombreHilo);
			this.socket = socket;
			System.out.println("Se ha creado una nueva instancia de HiloConexion");
		}

		/**
		 * Cancela (finaliza) el hilo actual, cerrando el socket de comunicaci�n con la aplicaci�n cliente. 
		 * Este m�todo puede demorarse (dependiendo del valor de TIMEOUT) si la conexi�n tiene problemas de comunicaci�n.
		 */
		public void cancelar() {
			System.out.println("Se va a cancelar un HiloConexion");
			//En el m�todo run() se comprueba a menudo el valor de 'cancelado', y si es true se finaliza dicho m�todo
			this.cancelado = true;
		}

		/**
		 * M�todo principal de la clase (al extender Thread). Este m�todo escucha las peticiones que llegan desde el socket y responde a ellas.
		 * Cuando el cliente env�a el mensaje correspondiente, finaliza la comunicaci�n.
		 * Tambi�n la finaliza si el cliente no env�a peticiones durante TIMEOUT millisegundos
		 */
		public void run() {


			//Empezamos a escuchar peticiones
			try {
				System.out.println("Empezamos a ejecutar el m�todo run() de HiloConexion");
				
				//Definimos un timeout corto para el socket
				socket.setSoTimeout(TIMEOUT_CORTO);				

				//Obtenemos flujos de entrada y de salida de 'socket'
				entrada = new ObjectInputStream(socket.getInputStream());
				salida = new ObjectOutputStream (socket.getOutputStream());
				salida.flush();
				
				System.out.println("Conexion = " + this.getName());
				receptor.estadoServidorActualizado("\nConexion = " + this.getName());
				
				//Empezamos a leer preguntas
				Pregunta pregunta = leerPregunta();
				System.out.println("Le�da pregunta");
				
				if (!cancelado && pregunta != null && !TipoPeticion.FIN_COMUNICACION.equals(pregunta.getTipoPeticion())) {
					Respuesta respuesta = procesarPregunta(pregunta);
					this.enviar(respuesta);
					System.out.println("Enviada respuesta");
				} else {
					System.out.println("No se env�a respuesta porque se ha cancelado el hilo o la pregunta no admit�a respuesta");
				}
				
				//Hemos acordado que en cada comunicaci�n s�lo se va a hacer una pregunta, as� que no es necesario el bucle
				/*
				while (!cancelado && pregunta != null && !TipoPeticion.FIN_COMUNICACION.equals(pregunta.getTipoPeticion())) {
					//Mientras no se haya cancelado el HiloConexion, y el TipoPeticion obtenido de la Pregunta no sea FIN_COMUNICACION, seguimos procesando Preguntas 
					System.out.println("Le�da pregunta, se va a enviar respuesta");
					Respuesta respuesta = procesarPregunta(pregunta);
					this.enviar(respuesta);
					System.out.println("Enviada respuesta");
					
					//Leemos una nueva pregunta
					pregunta = leerPregunta();
				}
				*/

			} catch (Exception e) { 
				System.out.println("Se ha generado una Excepci�n en el m�todo run() de HiloConexion:");
				e.printStackTrace();
			} finally {
				
				//Se ha acordado que S� es necesario enviar el mensaje de FIN_COMUNICACION
				try {
					//Si a�n se puede, enviamos el mensaje de FIN_COMUNICACION
					
					if (socket != null && !socket.isClosed()) {
						Respuesta finComunicacion = new Respuesta(TipoPeticion.FIN_COMUNICACION,0,0,0,null);
						this.enviar(finComunicacion);
						System.out.println("Enviado mensaje al cliente para notificar conexi�n cerrada");
					}
				} catch (IOException e) {
					System.out.println("Se ha generado una Excepci�n al intentar enviar un mensaje de FIN_COMUNICACION en HiloConexion:");
					e.printStackTrace();
				}
				
				
				try {
					if (entrada != null) {
						entrada.close();
					}
				} catch (IOException e) {
					System.out.println("Se ha generado una Excepci�n al cerrar el flujo de entrada del socket en HiloConexion:");
					e.printStackTrace();
				}
				try {
					if (socket != null && !socket.isClosed()) {
						socket.close();	
						System.out.println("Se ha cerrado el socket en HiloConexion");
					}
				} catch (IOException e) {
					System.out.println("Se ha generado una Excepci�n al intentar cerrar el socket en HiloConexion:");
					e.printStackTrace();
				}

			}

		}
	
		
		/**
		 * M�todo privado que procesa una Pregunta y calcula la Respuesta correspondiente
		 * @param pregunta Pregunta a procesar
		 * @teturn Respuesta correspondiente calculada para la Pregunta 'pregunta'
		 */
		private Respuesta procesarPregunta(Pregunta pregunta) {
			
			DatosHealthWorldBank datosHWB = new DatosHealthWorldBank(pregunta.getTipoPeticion(), pregunta);
			
			Respuesta resp = null;
			try {
				
				resp =datosHWB.getObtenerDatos();
			
			} catch (SQLException e) {
				System.out.println("Se ha generado una Excepci�n SQL al intentar consultar a la BBDD:");
				e.printStackTrace();
				ArrayList<Registro> datos = new ArrayList<Registro>(1);
				datos.add(new ErrorHWB(e.getLocalizedMessage()));
				resp = new Respuesta(TipoPeticion.ERROR,0,1,1,datos);
			} catch (Exception e) {
				System.out.println("Se ha generado una Excepci�n al intentar consultar a la BBDD:");
				e.printStackTrace();
				ArrayList<Registro> datos = new ArrayList<Registro>(1);
				datos.add(new ErrorHWB(e.getLocalizedMessage()));
				resp = new Respuesta(TipoPeticion.ERROR,0,1,1,datos);
			}
			
			return resp;
		}
		
		
		/**
		 * M�todo privado que sirve para leer una nueva Pregunta del socket de comunicaci�n.
		 * Si pasan TIMEOUT milisegundos sin poder leer una pregunta, se cancela el HiloConexion actual
		 * @return Pregunta le�da en el socket. 'null' si se ha alcanzado el TIMEOUT.
		 * @throws ClassNotFoundException 
		 * @throws IOException
		 */
		private Pregunta leerPregunta() throws IOException, ClassNotFoundException {
			//Definimos el tiempo (en milisegundos) que llevamos sin recibir ninguna Pregunta.
			//Si llegamos a TIMEOUT, deber�amos cerrar la comunicaci�n
			int milisegundosSinComunicacion = 0;
			
			//En la variable'continuar' controlamos si hemos llegado al TIMEOUT.
			//Si el TIMEOUT est� definido como 0, entendemos que no hay TIMEOUT.
			boolean timeoutAlcanzado = false;
			
			//Empezamos a leer mensajes que lleguen por el socket
			Pregunta pregunta = null;
			boolean preguntaLeida = false;
			while (!cancelado && !preguntaLeida && !timeoutAlcanzado) {	
				//Mietras no hayamos le�do una pregunta y no hayamos llegado al TIMEOUT de espera, seguimos intentando leer
				try {
					pregunta = (Pregunta)entrada.readObject();
					preguntaLeida = true;	//Si hemos llegado hasta aqu�, es que hemos le�do la pregunta correctamente
					System.out.println("Se ha le�do satisfactoriamente una Pregunta");
					
					/**
					* Se a�aden trazas en la consola sobre la informacion de la pregunta que 
					* se ha recibido desde el cliente.
					*/
					System.out.println("Pregunta tipo peticion = " + pregunta.getTipoPeticion());
					System.out.println("Pregunta indicador = " + pregunta.getCodigoIndicador());
					System.out.println("Pregunta pais = " + pregunta.getCodigoPais());
					System.out.println("Pregunta limite = " + pregunta.getLimite());
					System.out.println("Pregunta registro = " + pregunta.getPrimerRegistro());
					
					/**
					* Se a�aden registros en el panel del servidorGUI sobre la informacion de la pregunta 
					* que se ha recibido desde el cliente.
					*/
					if ((pregunta.getLimite()==0)&&(pregunta.getPrimerRegistro()==0)) {
						receptor.estadoServidorActualizado("NUMERO DE RESISTROS");
					} else {
						receptor.estadoServidorActualizado("REPRESENTACION DE LOS REGISTROS");
					}
					receptor.estadoServidorActualizado("    Pregunta tipo peticion = " + pregunta.getTipoPeticion());
					receptor.estadoServidorActualizado("    Pregunta indicador = " + pregunta.getCodigoIndicador());
					receptor.estadoServidorActualizado("    Pregunta pais = " + pregunta.getCodigoPais());
					receptor.estadoServidorActualizado("    Pregunta limite = " + pregunta.getLimite());
					receptor.estadoServidorActualizado("    Pregunta registro = " + pregunta.getPrimerRegistro());
				} catch (SocketTimeoutException e) {
					//Apuntamos los milisegundos que llevamos si leer una pregunta nueva
					milisegundosSinComunicacion = milisegundosSinComunicacion + socket.getSoTimeout();
				}				
				
				//Comprobamos si hemos alcanzado el TIMEOUT
				if (TIMEOUT != 0 && milisegundosSinComunicacion > TIMEOUT) {
					timeoutAlcanzado = true;
					System.out.println("Se ha alcanzado el TIMEOUT esperando para leer una Pregunta");
				}

			}
			
			//Si 'timeoutAlcanzado' es 'true', debemos cancelar este HiloConexion
			if (timeoutAlcanzado) {
				System.out.println("Se va a cancelar el HiloConexion al haberse alcanzado el TIMEOUT esperando para leer una Pregunta");
				this.cancelar();
			}
			
			return pregunta;

		}
		
		/**
		 * M�todo privado que sirve para enviar una Respuesta mediante el socket a la aplicaci�n cliente.
		 * @param respuesta Respuesta que se env�a a la aplicaci�n cliente a trav�s del socket 
		 * @throws IOException
		 */
		private void enviar(Respuesta respuesta) throws IOException {
			salida.writeObject(respuesta);
			System.out.println("Se ha enviado una Respuesta en HiloConexion a trav�s del socket");
			
			/**
			* Se a�aden trazas en la consola sobre la informacion de la respuesta que 
			* se ha enviado al cliente.
			*/
			System.out.println("respuesta tipo peticion = " + respuesta.getTipoPeticion());
			System.out.println("respuesta limite = " + respuesta.getLimite());
			System.out.println("respuesta registro = " + respuesta.getPrimerRegistro());
			System.out.println("respuesta total registros = " + respuesta.getTotalRegistros());
			System.out.println("respuesta tipo pagina = " + respuesta.pagina());
			System.out.println("respuesta registro hasta = " + respuesta.registroHasta());
			
			/**
			* Se a�aden registros en el panel del servidorGUI sobre la informacion de la respuesta 
			* que se envia al cliente.
			*/
			receptor.estadoServidorActualizado("    ----");
			receptor.estadoServidorActualizado("    respuesta tipo peticion = " + respuesta.getTipoPeticion());
			receptor.estadoServidorActualizado("    respuesta limite = " + respuesta.getLimite());
			receptor.estadoServidorActualizado("    respuesta registro = " + respuesta.getPrimerRegistro());
			receptor.estadoServidorActualizado("    respuesta total registros = " + respuesta.getTotalRegistros());
			receptor.estadoServidorActualizado("    respuesta pagina = " + respuesta.pagina());
			receptor.estadoServidorActualizado("    respuesta registro hasta = " + respuesta.registroHasta());
			salida.flush();
		}

	}

	/**
	 * Clase privada encargada de notificar a 'receptor' que ha habido cambios en el estado del Servidor
	 * Es necesaria esta nueva clase (que implemente Runnable) para as� poder realizar esta notificaci�n en el hilo principal de la aplicaci�n.
	 * Si se invocara directamente desde alguna instancia de HiloServidor o HiloConexion a alg�n m�todo que actualizara la interfaz de usuario,
	 * podr�amos estar modificando la misma interfaz de usuario desde hilos distintos. Esto puede ser un problema... Por eso recurrimos a este 'truco' 
	 */	
	private class Notificador implements Runnable{

		// Instancia de Servidor que debe enviar la notificaci�n de que se ha actualizado el estado
		private Servidor padre;

		/**
		 * Constructor de la clase. Crea un Notificador con el 'padre' indicado
		 * @param padre Instancia de Servidor que debe enviar la notificaci�n de que se ha actuallizado el estado del servidor
		 */		
		Notificador(Servidor padre) {
			this.padre = padre;
		}

		/**
		 * M�todo principal de la clase (al implementar Runnable). Este m�todo �nicamente invoca a estadoServidorActualizado() de padre.receptor
		 */
		public void run() {
			if (padre.receptor != null) {
				System.out.println("Se va a notificar un cambio en el estado del servidor al receptor en la clase Servidor");
				padre.receptor.estadoServidorActualizado(padre);
			}
		}
	}
	
}
