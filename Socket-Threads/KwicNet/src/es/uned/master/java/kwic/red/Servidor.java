package es.uned.master.java.kwic.red;


import java.io.*;
import java.net.*;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import es.uned.master.java.kwic.controlador.KwicFormArea;

public class Servidor {
	
	private DatosCliente datosCliente;
	private KwicFormArea areaEstadisticas;
	private KwicFormArea areaResultadoClientes;
	//String para imprimir los datos de conexiones de clientes
	private StringBuilder cliente;
	
	//Atributos para estadisticas
	private Date inicioSesion;
	private Date finSesion;
	
	private ObjectOutputStream salida;
    private ObjectInputStream entrada;
    private ServerSocket servidor;
    private Socket conexion;
    private int contador = 0;
	
	// configurar y ejecutar el servidor 
    public void ejecutarServidor()
    {
       // configurar servidor para que reciba conexiones; procesar las conexiones
       try {
 
          // Paso 1: crear un objeto ServerSocket.
          servidor = new ServerSocket(10008);          
 
          while ( contador < 6 ) {
 
             try {
                esperarConexion(); // Paso 2: esperar una conexión.
                obtenerFlujos();        // Paso 3: obtener flujos de entrada y salida.
                procesarConexion(); // Paso 4: procesar la conexión.
             }
 
             // procesar excepción EOFException cuando el cliente cierre la conexión 
             catch ( EOFException excepcionEOF ) {
                System.err.println( "El servidor terminó la conexión" );
             }
 
             finally {
                cerrarConexion();   // Paso 5: cerrar la conexión.               
             }
 
          } // fin de instrucción while
 
       } // fin del bloque try
 
       // procesar problemas con E/S
       catch ( IOException excepcionES ) {
          excepcionES.printStackTrace();
       }
 
    } // fin del método ejecutarServidor
 
    // esperar que la conexión llegue, después mostrar información de la conexión
    private void esperarConexion() throws IOException
    {
           	
       mostrarMensaje( "Esperando una conexión\n" );
       conexion = servidor.accept(); // permitir al servidor aceptar la conexión
     //Incrementamos el contador porque se hace una conexion nueva
       ++contador;       
       mostrarMensaje( "Conexión " + contador + " recibida de: " +
          conexion.getInetAddress().getHostName() );
       cliente.append("IP: "+conexion.getInetAddress().getHostName()+" Puerto: "+conexion.getPort()+" Inicio Sesión: "+inicioSesion.toString());       
    }
 
    // obtener flujos para enviar y recibir datos
    private void obtenerFlujos() throws IOException
    {
       // establecer flujo de salida para los objetos
       salida = new ObjectOutputStream( conexion.getOutputStream() );
       salida.flush(); // vaciar búfer de salida para enviar información de encabezado
 
       // establecer flujo de entrada para los objetos
       entrada = new ObjectInputStream( conexion.getInputStream() );
 
       mostrarMensaje( "\nSe recibieron los flujos de E/S\n" );
    }
 
    // procesar la conexión con el cliente
    // enviamos las cadenas de noClaves, frases y el resultado del kwic 
    private void procesarConexion() throws IOException
    {
       // enviar mensaje de conexión exitosa al cliente
       String mensaje = "Conexión exitosa";
       enviarMensaje( mensaje );
       //enviamos las cadenas en un objeto de la clase DatosCliente
       enviarDatos(datosCliente);
       
    
       do { // procesar los mensajes enviados por el cliente
 
          // leer el mensaje y mostrarlo en pantalla
          try {
             mensaje = ( String ) entrada.readObject();
             mostrarMensaje( "\n" + mensaje );
          }
 
          // atrapar problemas que pueden ocurrir al tratar de leer del cliente
          catch ( ClassNotFoundException excepcionClaseNoEncontrada ) {
             mostrarMensaje( "\nSe recibió un tipo de objeto desconocido" );
          }
 
       } while ( !mensaje.equals( "CLIENTE>>> TERMINAR" ) );
       //Al terminar la conexion con el cliente decrementamos el contador
       --contador;       
 
    } // fin del método procesarConexion
 
    // cerrar flujos y socket
    private void cerrarConexion() 
    {
       mostrarMensaje( "\nFinalizando la conexión\n" );
       
       try {
          salida.close();
          entrada.close();
          conexion.close();
       }
       catch( IOException excepcionES ) {
          excepcionES.printStackTrace();
       }
    }
 
    // enviar mensaje al cliente
    private void enviarMensaje( String mensaje )
    {
       // enviar objeto al cliente
       try {
          salida.writeObject( "SERVIDOR>>> " + mensaje );
          salida.flush();
          mostrarMensaje( "\nSERVIDOR>>> " + mensaje );
       }
 
       // procesar problemas que pueden ocurrir al enviar el objeto
       catch ( IOException excepcionES ) {
          //areaPantalla.append( "\nError al escribir objeto" );
       }
    }
    
    //enviar datos del servidor al cliente
    private void enviarDatos(DatosCliente datos){
    	// enviar objeto al cliente
        try {
           salida.writeObject( datos );
           salida.flush();
           mostrarMensaje( "\nSERVIDOR>>> Datos enviados" );
        }
  
        // procesar problemas que pueden ocurrir al enviar el objeto
        catch ( IOException excepcionES ) {
           //areaPantalla.append( "\nError al escribir objeto" );
        }
    }
 
    // método utilitario que es llamado desde otros subprocesos para manipular a
    // areaPantalla en el subproceso despachador de eventos
    private void mostrarMensaje( final String mensajeAMostrar )
    {
       // mostrar mensaje del subproceso de ejecución despachador de eventos
       SwingUtilities.invokeLater(
          new Runnable() {  // clase interna para asegurar que la GUI se actualice apropiadamente
 
             public void run() // actualiza areaPantalla
             {
               // areaPantalla.append( mensajeAMostrar );
                //areaPantalla.setCaretPosition( 
                //   areaPantalla.getText().length() );
             }
 
          }  // fin de la clase interna
 
       ); // fin de la llamada a SwingUtilities.invokeLater
    }
    
    //Constructor del servidor que recibe los Strings para pasarle al cliente
    public Servidor(DatosCliente datosCli, KwicFormArea estadisticas, KwicFormArea resultadoClientes){
    	
    	//Damos a los atributos el valor que se le pasa al cliente
    	this.datosCliente = datosCli;
    	//arrancamos el servidor;
    	ejecutarServidor();
    }

	
}
