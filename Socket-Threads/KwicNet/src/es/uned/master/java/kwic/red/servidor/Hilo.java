package es.uned.master.java.kwic.red.servidor;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;
import javax.swing.SwingUtilities;
import es.uned.master.java.kwic.controlador.KwicFormArea;
import es.uned.master.java.kwic.red.cliente.DatosServidor;

public class Hilo implements Runnable{
	
	//area de texto de las estadisticas
	private KwicFormArea areaEstadisticas;
	//area de texto de los datos conexion de clientes
	private KwicFormArea areaResultadoClientes;
	//flujo de salida
	private ObjectOutputStream salida;
	//flujo de entrada
	private ObjectInputStream entrada;
	//coleccion de datos conexion del cliente
	protected DatosConexion datosConexion;
	//instancia de la clase ServidorKWIC
	private ServidorKWIC servidor;
	//instancia clase Socket
	private Socket conexion;
	//instancia de la clase de datos cliente
	private DatosCliente datos;
	//instacia de la clase Object
    private Object datoslectura;
    private Boolean continuar;

    //Constructor
    public Hilo(DatosCliente DatosCli, KwicFormArea estadisticas, KwicFormArea resultadoCliente, ServidorKWIC servidor, Socket server) {
      this.conexion=server;
      this.datos=DatosCli;
      this.areaEstadisticas=estadisticas;
      this.areaResultadoClientes=resultadoCliente;
      this.servidor=servidor;
      //Guardamos datos de la conexion del cliente
      datosConexion=new DatosConexion();
      datosConexion.setDirIp(conexion.getInetAddress().getHostAddress());
      datosConexion.setPuerto(conexion.getPort());
    }
    
    /* Funcion run: Funcion que ejecuta los proceso del hilo. Inicializa los flujos de entrada y salida 
     * entre cliente y servidor y operar con los datos que envia el servidor hacia el cliente y recibe del mismo
	 *  
	 */
    public void run () {
    	datosConexion.setFecha_Inicio_Conexion(new Date(System.currentTimeMillis()));
    	try {
			try {
				System.out.println("Esperando una conexion");
				obtenerFlujos();
				procesarConexion();
			} catch (IOException e) {
				e.printStackTrace();
			} 			
		} finally {			
			cerrarConexion(); 
		}
    }
    
    /* Funcion obtenerFlujo: Obtiene los flujos para enviar y recibir datos
	 *  
	 */
    private void obtenerFlujos() throws IOException {
    	// establecer flujo de salida para los objetos
    	System.out.println("Establecemos flujos");
    	salida = new ObjectOutputStream(conexion.getOutputStream());
    	// vaciar bufer de salida para enviar informacion de encabezado
    	//establecer flujo de entrada para los objetos
    	salida.flush(); 
    	entrada = new ObjectInputStream(conexion.getInputStream());
    }

    /* Funcion procesarConexion: Funcion que envia los datos del resultado Kwic al cliente y este a su
     * vez envia datos de conexion al servidor
	 *  
	 */
    private void procesarConexion() throws IOException {
    	// enviar mensaje de conexion exitosa al cliente
    	System.out.println("Procesar los mensajes");
    	String mensaje = "Conexion exitosa";
    	// enviamos las cadenas en un objeto de la clase DatosCliente
    	enviarDatos(datos);
    	continuar=true;
    	do { // procesar los mensajes enviados por el cliente
    		// leer el mensaje y mostrarlo en pantalla
    		try {
    			datoslectura = entrada.readObject();
    			//si los datos son una instancia de datosservidor escribimos las estadisticas del cliente
    			if (datoslectura instanceof DatosServidor){
    				// anadimos los datos del cliente a resultadosCliente
    				System.out.println("Escribimos los datos del cliente");
    				mostrarMensaje((DatosServidor)datoslectura);    				
    			}else if(datoslectura instanceof String){
    					if (((String) datoslectura).equalsIgnoreCase("FIN")){
    						System.out.println("Salimos del bucle de lectura de datos del cliente");
    						continuar=false;
    					}//cierre if
    			}//cierre if-else
    		}// atrapar problemas que pueden ocurrir al tratar de leer del cliente
    		catch (ClassNotFoundException excepcionClaseNoEncontrada) {
    				System.out.println("Clase no encontrada");
    		}
    	} while (continuar);
    } 

    /* Funcion cerrarConexion: Cierra los flujos de entrada, salida y el socket.
     * Tambien muestra los datos estadisticos y aÒade los datos de conexion del cliente.
	 *  
	 */
    private void cerrarConexion() {
    	System.out.println("Finalizamos la conexion");
    	try {
    		salida.close();
    		entrada.close();
    		conexion.close();
    		datosConexion.setFecha_Fin_Conexion(new Date(System.currentTimeMillis()));    			
    		servidor.anadeDatosConexion(datosConexion);
    		//Obtenemos estadisticas
    		mostrarEstadisticas(servidor.getEstadisticas().toString());
    		} catch (IOException excepcionES) {
    			excepcionES.printStackTrace();
    		}
    }

    /* Funcion enviarDatos: Escribe objetos en el flujo de salida de datos.
	 *  
	 */
    private void enviarDatos(DatosCliente datos) {
    	// enviar objeto al cliente
    	try {
    		System.out.println("Enviamos datos");
    		salida.writeObject(datos);
    		salida.flush();
    		}catch (IOException excepcionES) {// procesar problemas que pueden ocurrir al enviar el objeto
    			System.out.println("Error al escribir objeto");
    		}
    }
	
    /* Funcion mostrarEstadisticas: MÈtodo que muestra los mensajes de las estadisticas en el area de texto del mismo
	 *  
	 */
    private void mostrarEstadisticas(final String mensajeAMostrar) {
    	// mostrar mensaje del subproceso de ejecuci√≥n despachador de eventos
    	SwingUtilities.invokeLater(new Runnable() { 
    		// clase interna para asegurar que la GUI se actualice apropiadamente
    		public void run(){ // actualiza areaPantalla
    					areaEstadisticas.setDisplayValue(mensajeAMostrar);
    					}

    	} // fin de la clase interna
    			); // fin de la llamada a SwingUtilities.invokeLater
    }
    
    /* Funcion mostrarMensaje: MÈtodo llamado desde otros subprocesos para manipular el area de resultado de cliente
	 *  
	 */
   private void mostrarMensaje(final DatosServidor mensajeAMostrar) {
   // mostrar mensaje del subproceso de ejecuci√≥n despachador de eventos
	   SwingUtilities.invokeLater(new Runnable() {
	  // clase interna para asegurar que la GUI se actualice apropiadamente
	 public void run(){ // actualiza areaPantalla
    				System.out.println("Actualizamos mostrar datos clientes");
    				System.out.println(mensajeAMostrar.toString());
    				String texto = areaResultadoClientes.getText();
    				texto = texto+mensajeAMostrar.toString()+"\n";
    				areaResultadoClientes.setDisplayValue(texto);
    				}
	   } // fin de la clase interna
			   ); // fin de la llamada a SwingUtilities.invokeLater
    }

}
