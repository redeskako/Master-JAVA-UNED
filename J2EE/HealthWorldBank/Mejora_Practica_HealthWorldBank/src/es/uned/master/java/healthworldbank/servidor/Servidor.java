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
 * Clase encargada de gestionar un servidor socket que procesará las Preguntas procedentes de las aplicaciones cliente y enviará las Respuestas correspondientes.
 * Creará un hilo de ejecución que a su vez gestionará un ServerSocket que escuchará comunicaciones desde el puerto 'PUERTO'.
 * Este hilo a su vez puede crear más hilos, uno por cada conexión entrante que le llegue. 
 * Estos hilos se encargarán de la comunicación con cada una de las aplicaciones cliente individualmente.  
 * 
 * @author grupo alef (José Torrecilla)
 * @date 17/4/2012
 */
public class Servidor {
	
	// Puerto del servidor. Por defecto 10088
	public static final int PUERTO = 10088;

	// Número máximo de conexiones simultáneas
	public static final int MAXIMO_CONEXIONES = 50;	//Pongo 50 por poner un número alto

	// Timeout general para las conexiones socket en milisegundos. Si es 0 se entiende que no hay TIMEOUT
	public static final int TIMEOUT = 600000;	//Pongo un Timeout alto, 10 minutos
	
	//Definimos un timeout (en milisegundos) corto utilizado en algunas partes del código
	//private static final int TIMEOUT_CORTO = 100;
	private static final int TIMEOUT_CORTO = 1000;

	//Objeto que implementa la interfaz ReceptorActualizacionServidor que recibirá 
	//un mensaje cada vez que se produzca una modificación relevante en el servidor
	//En este caso (PracticaWorldHealthBank) cuando el servidor arranque o pare
	private ReceptorActualizacionServidor receptor;

	//Hilo encargado de escuchar las conexiones entrantes de la aplicación cliente
	private HiloServidor hiloServidor = null;
		
	/**
	 * Constructor de clase. Crea el Servidor y almacena el 'receptor'
	 * 
	 *  @param receptor Objeto que implementa la interfaz ReceptorActualizacionServidor que recibirá el mensaje 
	 *  estadoServidorActualizada(Servidor servidor) cada vez que el estado del servidor sufra un cambio. 
	 *  Si se pasa como argumento 'null', simplemente estas notificaciones no se envían 
	 */
	public Servidor(ReceptorActualizacionServidor receptor) {
		this.receptor = receptor;
	}

	/**
	 * Método que arranca el servidor.
	 * Si el servidor ya se encontraba arrancado anteriormente, éste se para y se vuelve a arrancar. 
	 * De esta forma no es necesario para el servidor antes de invocar a este método. 
	 */
	public void arrancarServidor() {
		//Paramos el servidor, si es que está arrancado
		if (servidorArrancado()) {
			pararServidor();
		}
		
		//Creamos un nuevo HiloServidor y lo iniciamos
		hiloServidor = new HiloServidor("hiloServidor", this);
		hiloServidor.start();
		System.out.println("Servidor arrancado");
		
		//Notificamos actualización de estado
		this.notificarModificacionEstado();
	}

	/**
	 * Método que para el servidor.
	 * Este método puede demorarse algunos segundos si hay problemas de comunicación con alguna aplicación cliente 
	 */
	public void pararServidor() {
		//Si el servidor no está arrancado, no hacemos nada
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
		
		//Notificamos actualización de estado
		this.notificarModificacionEstado();
	}

	/**
	 * Método que indica si el servidor está arrancado o no.
	 * @return Si el servidor está arrancado devuelve 'true', en caso contrario 'false' 
	 */
	public boolean servidorArrancado() {
		if (hiloServidor != null){
			return hiloServidor.isAlive();
		}
		return false;
	}
	
	/**
	 * Método privado que se invoca cada vez que hay un cambio en el estado del servidor
	 * Este método crea una instancia de Notificador, que se encarga de notificar (en el hilo principal de la aplicación) a 'receptor'
	 * de que ha habido un cambio en el estado del servidor 
	 */
	public void notificarModificacionEstado() {
		if (this.receptor != null) {
			//Creamos un Notificador
			Runnable notificarEnHiloPrincipal = new Notificador(this);
			
			System.out.println("Se va a notificar desde Servidor un cambio en el estado del servidor");
			
			//Hacemos que se ejecute en el hilo principal, ya que indirectamente provocará que se actualice la interfaz de usuario
			SwingUtilities.invokeLater(notificarEnHiloPrincipal);
			
		}
	}


	/**
	 * Clase privada encargada de gestionar (en un hilo propio) un servidor socket que escuchará en el puerto PUERTO.
	 * A su vez, creará más hilos (instancias de HiloConexion) que se encargarán de gestionar cada una de las conexiones socket individuales 
	 */
	private class HiloServidor extends Thread{

		//Hilos de las conexiones activas (quizá ya cerradas) de este servidor 
		private volatile ArrayList<HiloConexion> conexiones = new ArrayList<HiloConexion>(MAXIMO_CONEXIONES);
		
		//Indica si el hilo se ha cancelado o no. Se comprueba de vez en cuando en el código para finalizar el hilo
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
		 * Este método puede demorarse (dependiendo del valor de TIMEOUT) si el servidor tiene alguna conexión activa con problemas de comunicación.
		 */
		public void cancelar() {
			//En el método run() se comprueba a menudo el valor de 'cancelado', y si es true se finaliza dicho método
			System.out.println("Se va a cancelar el hiloServidor");
			cancelado = true;
		}

		/**
		 * Método principal de la clase (al extender Thread). Este método crea un servidor socket en el puerto PUERTO y acepta hasta MAXIMO_CONEXIONES en dicho servidor.
		 * Por cada conexión aceptada se crea una instancia de HiloConexion (un nuevo hilo) que escucha las peticiones de dicho cliente
		 */
		public void run() {
			System.out.println("Se ha iniciado el HiloServidor");
			
			//Este será nuestro servidor socket
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
						//Creamos un nuevo HiloConexion con el Socket obtenido al aceptar la conexión entrante, lo arrancamos y lo añadimos a nuestro ArrayList de conexiones
						HiloConexion nuevoHilo = new HiloConexion("Conexion"+conexiones.size(),servidorSocket.accept(),servidor);
						System.out.println("Hemos aceptado una nueva conexión en el HiloServidor");
						nuevoHilo.start();
						conexiones.add(nuevoHilo);
					} catch (SocketTimeoutException e) {
						//Esto significa que no hemos recibido una conexión entrante en 'timeoutCorto' milisegundos
						//Simplemente ignoro la excepción y sigo esperando nuevas conexiones
						System.out.println("TIMEOUT = " + e.toString());
					}
					
					/**
					*  Se añaden unas trazas por consola del estado de las conexiones activas
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
							//Hilo interrumpido. En principio no tengo nada que hacer aquí, sigo como si nada...
						}
					}		
				}
	
			} catch (Exception e) {
				System.out.println("Se ha generado una Excepción en el método run() de HiloServidor:");
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
					System.out.println("Se ha generado una Excepción en el método run() de HiloServidor al esperar el cierre de las conexiones previamente abiertas:");
					e.printStackTrace();

				}

				//Cerramos servidorSocket si aún está abierto
				if ( servidorSocket != null && !servidorSocket.isClosed() ) {
					try {
						servidorSocket.close();
					} catch (IOException e) {
						System.out.println("Se ha generado una Excepción en el método run() de HiloServidor cerrando el servidor socket:");
						e.printStackTrace();
					}
				}
				System.out.println("Ha finalizado el HiloServidor");
			}


		}
		
		/**
		 * Método privado que calcula el número de conexiones actualmente activas en el servidor
		 * @return Número de conexiones actualmente activas en el servidor
		 */
		private int numeroConexionesActivas() {
			//Vamos a contar el número de conexiones activas que hay
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
	 * Clase privada encargada de gestionar (en un hilo propio) un servidor socket que escuchará en el puerto PUERTO.
	 */
	private class HiloConexion extends Thread {
		
		//Socket de la conexión. La variable más 'importante'
		private Socket socket;
		
		//Flujos de entrada y de salida del socket
		private ObjectInputStream entrada;
		private ObjectOutputStream salida;
		
		//Indica si el hilo se ha cancelado o no. Se comprueba de vez en cuando en el código para finalizar el hilo
		private volatile boolean cancelado = false;
		

		/**
		 * Constructor de la clase. Crea un hilo con el nombre nombreHilo indicado y servidor como instancia de Servidor 'padre'
		 * @param nombreHilo Nombre del hilo. No se utiliza internamente, puede ser de utilidad para quien cree la instancia
		 * @param socket Socket de la conexión
		 * @param servidor Instancia de Servidor 'padre', la que 'gestiona' el HiloServidor que a su vez gestiona este HiloConexion
		 */		
		HiloConexion(String nombreHilo, Socket socket, Servidor servidor){
			super(nombreHilo);
			this.socket = socket;
			System.out.println("Se ha creado una nueva instancia de HiloConexion");
		}

		/**
		 * Cancela (finaliza) el hilo actual, cerrando el socket de comunicación con la aplicación cliente. 
		 * Este método puede demorarse (dependiendo del valor de TIMEOUT) si la conexión tiene problemas de comunicación.
		 */
		public void cancelar() {
			System.out.println("Se va a cancelar un HiloConexion");
			//En el método run() se comprueba a menudo el valor de 'cancelado', y si es true se finaliza dicho método
			this.cancelado = true;
		}

		/**
		 * Método principal de la clase (al extender Thread). Este método escucha las peticiones que llegan desde el socket y responde a ellas.
		 * Cuando el cliente envía el mensaje correspondiente, finaliza la comunicación.
		 * También la finaliza si el cliente no envía peticiones durante TIMEOUT millisegundos
		 */
		public void run() {


			//Empezamos a escuchar peticiones
			try {
				System.out.println("Empezamos a ejecutar el método run() de HiloConexion");
				
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
				System.out.println("Leída pregunta");
				
				if (!cancelado && pregunta != null && !TipoPeticion.FIN_COMUNICACION.equals(pregunta.getTipoPeticion())) {
					Respuesta respuesta = procesarPregunta(pregunta);
					this.enviar(respuesta);
					System.out.println("Enviada respuesta");
				} else {
					System.out.println("No se envía respuesta porque se ha cancelado el hilo o la pregunta no admitía respuesta");
				}
				
				//Hemos acordado que en cada comunicación sólo se va a hacer una pregunta, así que no es necesario el bucle
				/*
				while (!cancelado && pregunta != null && !TipoPeticion.FIN_COMUNICACION.equals(pregunta.getTipoPeticion())) {
					//Mientras no se haya cancelado el HiloConexion, y el TipoPeticion obtenido de la Pregunta no sea FIN_COMUNICACION, seguimos procesando Preguntas 
					System.out.println("Leída pregunta, se va a enviar respuesta");
					Respuesta respuesta = procesarPregunta(pregunta);
					this.enviar(respuesta);
					System.out.println("Enviada respuesta");
					
					//Leemos una nueva pregunta
					pregunta = leerPregunta();
				}
				*/

			} catch (Exception e) { 
				System.out.println("Se ha generado una Excepción en el método run() de HiloConexion:");
				e.printStackTrace();
			} finally {
				
				//Se ha acordado que SÍ es necesario enviar el mensaje de FIN_COMUNICACION
				try {
					//Si aún se puede, enviamos el mensaje de FIN_COMUNICACION
					
					if (socket != null && !socket.isClosed()) {
						Respuesta finComunicacion = new Respuesta(TipoPeticion.FIN_COMUNICACION,0,0,0,null);
						this.enviar(finComunicacion);
						System.out.println("Enviado mensaje al cliente para notificar conexión cerrada");
					}
				} catch (IOException e) {
					System.out.println("Se ha generado una Excepción al intentar enviar un mensaje de FIN_COMUNICACION en HiloConexion:");
					e.printStackTrace();
				}
				
				
				try {
					if (entrada != null) {
						entrada.close();
					}
				} catch (IOException e) {
					System.out.println("Se ha generado una Excepción al cerrar el flujo de entrada del socket en HiloConexion:");
					e.printStackTrace();
				}
				try {
					if (socket != null && !socket.isClosed()) {
						socket.close();	
						System.out.println("Se ha cerrado el socket en HiloConexion");
					}
				} catch (IOException e) {
					System.out.println("Se ha generado una Excepción al intentar cerrar el socket en HiloConexion:");
					e.printStackTrace();
				}

			}

		}
	
		
		/**
		 * Método privado que procesa una Pregunta y calcula la Respuesta correspondiente
		 * @param pregunta Pregunta a procesar
		 * @teturn Respuesta correspondiente calculada para la Pregunta 'pregunta'
		 */
		private Respuesta procesarPregunta(Pregunta pregunta) {
			
			DatosHealthWorldBank datosHWB = new DatosHealthWorldBank(pregunta.getTipoPeticion(), pregunta);
			
			Respuesta resp = null;
			try {
				
				resp =datosHWB.getObtenerDatos();
			
			} catch (SQLException e) {
				System.out.println("Se ha generado una Excepción SQL al intentar consultar a la BBDD:");
				e.printStackTrace();
				ArrayList<Registro> datos = new ArrayList<Registro>(1);
				datos.add(new ErrorHWB(e.getLocalizedMessage()));
				resp = new Respuesta(TipoPeticion.ERROR,0,1,1,datos);
			} catch (Exception e) {
				System.out.println("Se ha generado una Excepción al intentar consultar a la BBDD:");
				e.printStackTrace();
				ArrayList<Registro> datos = new ArrayList<Registro>(1);
				datos.add(new ErrorHWB(e.getLocalizedMessage()));
				resp = new Respuesta(TipoPeticion.ERROR,0,1,1,datos);
			}
			
			return resp;
		}
		
		
		/**
		 * Método privado que sirve para leer una nueva Pregunta del socket de comunicación.
		 * Si pasan TIMEOUT milisegundos sin poder leer una pregunta, se cancela el HiloConexion actual
		 * @return Pregunta leída en el socket. 'null' si se ha alcanzado el TIMEOUT.
		 * @throws ClassNotFoundException 
		 * @throws IOException
		 */
		private Pregunta leerPregunta() throws IOException, ClassNotFoundException {
			//Definimos el tiempo (en milisegundos) que llevamos sin recibir ninguna Pregunta.
			//Si llegamos a TIMEOUT, deberíamos cerrar la comunicación
			int milisegundosSinComunicacion = 0;
			
			//En la variable'continuar' controlamos si hemos llegado al TIMEOUT.
			//Si el TIMEOUT está definido como 0, entendemos que no hay TIMEOUT.
			boolean timeoutAlcanzado = false;
			
			//Empezamos a leer mensajes que lleguen por el socket
			Pregunta pregunta = null;
			boolean preguntaLeida = false;
			while (!cancelado && !preguntaLeida && !timeoutAlcanzado) {	
				//Mietras no hayamos leído una pregunta y no hayamos llegado al TIMEOUT de espera, seguimos intentando leer
				try {
					pregunta = (Pregunta)entrada.readObject();
					preguntaLeida = true;	//Si hemos llegado hasta aquí, es que hemos leído la pregunta correctamente
					System.out.println("Se ha leído satisfactoriamente una Pregunta");
					
					/**
					* Se añaden trazas en la consola sobre la informacion de la pregunta que 
					* se ha recibido desde el cliente.
					*/
					System.out.println("Pregunta tipo peticion = " + pregunta.getTipoPeticion());
					System.out.println("Pregunta indicador = " + pregunta.getCodigoIndicador());
					System.out.println("Pregunta pais = " + pregunta.getCodigoPais());
					System.out.println("Pregunta limite = " + pregunta.getLimite());
					System.out.println("Pregunta registro = " + pregunta.getPrimerRegistro());
					
					/**
					* Se añaden registros en el panel del servidorGUI sobre la informacion de la pregunta 
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
		 * Método privado que sirve para enviar una Respuesta mediante el socket a la aplicación cliente.
		 * @param respuesta Respuesta que se envía a la aplicación cliente a través del socket 
		 * @throws IOException
		 */
		private void enviar(Respuesta respuesta) throws IOException {
			salida.writeObject(respuesta);
			System.out.println("Se ha enviado una Respuesta en HiloConexion a través del socket");
			
			/**
			* Se añaden trazas en la consola sobre la informacion de la respuesta que 
			* se ha enviado al cliente.
			*/
			System.out.println("respuesta tipo peticion = " + respuesta.getTipoPeticion());
			System.out.println("respuesta limite = " + respuesta.getLimite());
			System.out.println("respuesta registro = " + respuesta.getPrimerRegistro());
			System.out.println("respuesta total registros = " + respuesta.getTotalRegistros());
			System.out.println("respuesta tipo pagina = " + respuesta.pagina());
			System.out.println("respuesta registro hasta = " + respuesta.registroHasta());
			
			/**
			* Se añaden registros en el panel del servidorGUI sobre la informacion de la respuesta 
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
	 * Es necesaria esta nueva clase (que implemente Runnable) para así poder realizar esta notificación en el hilo principal de la aplicación.
	 * Si se invocara directamente desde alguna instancia de HiloServidor o HiloConexion a algún método que actualizara la interfaz de usuario,
	 * podríamos estar modificando la misma interfaz de usuario desde hilos distintos. Esto puede ser un problema... Por eso recurrimos a este 'truco' 
	 */	
	private class Notificador implements Runnable{

		// Instancia de Servidor que debe enviar la notificación de que se ha actualizado el estado
		private Servidor padre;

		/**
		 * Constructor de la clase. Crea un Notificador con el 'padre' indicado
		 * @param padre Instancia de Servidor que debe enviar la notificación de que se ha actuallizado el estado del servidor
		 */		
		Notificador(Servidor padre) {
			this.padre = padre;
		}

		/**
		 * Método principal de la clase (al implementar Runnable). Este método únicamente invoca a estadoServidorActualizado() de padre.receptor
		 */
		public void run() {
			if (padre.receptor != null) {
				System.out.println("Se va a notificar un cambio en el estado del servidor al receptor en la clase Servidor");
				padre.receptor.estadoServidorActualizado(padre);
			}
		}
	}
	
}
