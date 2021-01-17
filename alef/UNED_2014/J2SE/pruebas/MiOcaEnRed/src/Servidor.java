// Configurar un servidor que reciba una conexi贸n de un cliente, env铆e
// una cadena al cliente y cierre la conexi贸n.
// package es.uned.socket;

import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Servidor extends JFrame {
   private JTextField campoIntroducir;
   private JTextArea areaPantalla;
   private ObjectOutputStream salida;
   private ObjectInputStream entrada;
   private ServerSocket servidor;
   private Socket conexion;
   private int contador = 1;

   // configurar GUI
   public Servidor(){
	   /*
	    * El constructor de esta clase lo que hace es construir el servidor.
	    * 1.- Construye una ventana.
	    * 2.- Contruye los componetes de la ventana. Por componentes de la 
	    * ventana entiendo cuadros de texto, botones etc.
	    * 3.- Construye el escuchador de eventos que  pueden causar los componentes de  la ventana
	    * 4.- Se dota a la clase de funcionalidades. cada funcionalidad corresponde a un m閠odo. 
	    *  
	    */
      super( "Servidor" );

      Container contenedor = getContentPane();

      // crear campoIntroducir y registrar componente de escucha.
      
      campoIntroducir = new JTextField();
      campoIntroducir.setEditable( false );
      campoIntroducir.addActionListener(
         new ActionListener() {

            // enviar mensaje al cliente
            public void actionPerformed( ActionEvent evento ){
               enviarDatos( evento.getActionCommand() );
               campoIntroducir.setText( "" );
            }
         }  
      ); 

      contenedor.add( campoIntroducir, BorderLayout.NORTH );

      // crear areaPantalla
      areaPantalla = new JTextArea();
      contenedor.add( new JScrollPane( areaPantalla ), 
    		  			BorderLayout.CENTER );

      setSize( 300, 150 );
      setVisible( true );

   } // fin del constructor de Servidor

   // configurar y ejecutar el servidor 
   public void ejecutarServidor(){
      // configurar servidor para que reciba conexiones; procesar las conexiones
      try {
         // Paso 1: crear un objeto ServerSocket.
    	  
    	 /* 
    	  * En el constructor el 100, 縭epresenta el puerto de comunicaciones?. En la API aparece
    	  * identificado como backlog. 縀s lo mismo puerto que backlog?  
    	  * 
    	  */
         servidor = new ServerSocket( 12345, 100);

         while ( true ) {
            try {
               esperarConexion(); // Paso 2: esperar una conexi贸n.
               obtenerFlujos();        // Paso 3: obtener flujos de entrada y salida.
               procesarConexion(); // Paso 4: procesar la conexi贸n.
            }catch ( EOFException excepcionEOF ) {// procesar excepci贸n EOFException cuando el cliente cierre la conexi贸n 
               System.err.println( "El servidor termin贸 la conexi贸n" );
            }finally {
               cerrarConexion();   // Paso 5: cerrar la conexi贸n.
               ++contador;
            }

         } // fin de instrucci贸n while

      } // fin del bloque try

      // procesar problemas con E/S
      catch ( IOException excepcionES ) {
         excepcionES.printStackTrace();
      }

   } // fin del m茅todo ejecutarServidor

   // esperar que la conexi贸n llegue, despu茅s mostrar informaci贸n de la conexi贸n
   private void esperarConexion() throws IOException{
      mostrarMensaje( "Esperando una conexi贸n\n" );
      conexion = servidor.accept(); // permitir al servidor aceptar la conexion
      
      
      mostrarMensaje( "Conexi贸n " + contador + " recibida de:  " +
      // Se asocia la conexion a la direccion IP .
      // Para ello primero se recupera el nombre del ordenador 		
      // y despues se recupera la IP asociada a ese nombre. 
         conexion.getInetAddress().getHostName() );
   }

   // obtener flujos para enviar y recibir datos
   private void obtenerFlujos() throws IOException{
      // establecer flujo de salida para los objetos
      salida = new ObjectOutputStream( conexion.getOutputStream() );
      salida.flush(); // vaciar baffer de salida para enviar informaci贸n de encabezado

      // establecer flujo de entrada para los objetos
      entrada = new ObjectInputStream( conexion.getInputStream() );

      mostrarMensaje( "\nSe recibieron los flujos de E/S\n" );
   }

   // procesar la conexi贸n con el cliente
   private void procesarConexion() throws IOException{
      // enviar mensaje de conexi贸n exitosa al cliente
      String mensaje = "Conexi贸n exitosa";
      enviarDatos( mensaje );

      // habilitar campoIntroducir para que el usuario del servidor pueda enviar mensajes
      establecerCampoTextoEditable( true );

    
      /* 
    	 * Mientras que el servidor no reciba el mensaje "CLIENTE>>>TERMINAR"
    	 * Se lee la entrada del servidor (objeto entrada) y se muestra en pantalla.
    	 *  
    	 */ 
      
      do { // procesar los mensajes enviados por el cliente
    	  
    	         // leer el mensaje y mostrarlo en pantalla
      try {
            mensaje = ( String ) entrada.readObject();
            mostrarMensaje( "\n" + mensaje );
      }catch ( ClassNotFoundException excepcionClaseNoEncontrada ) { // atrapar problemas que pueden ocurrir al tratar de leer del cliente
            mostrarMensaje( "\nSe recibi贸 un tipo de objeto desconocido" );
        }

      } while ( !mensaje.equals( "CLIENTE>>> TERMINAR" ) );

   } // fin del m茅todo procesarConexion

   // cerrar flujos y socket
   private void cerrarConexion(){
      mostrarMensaje( "\nFinalizando la conexi贸n\n" );
      establecerCampoTextoEditable( false ); // deshabilitar campoIntroducir

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
   private void enviarDatos( String mensaje ){
      // enviar objeto al cliente
      try {
         salida.writeObject( "SERVIDOR>>> " + mensaje );
         salida.flush();
         mostrarMensaje( "\nSERVIDOR>>> " + mensaje );
      }

      // procesar problemas que pueden ocurrir al enviar el objeto
      catch ( IOException excepcionES ) {
         areaPantalla.append( "\nError al escribir objeto" );
      }
   }

   // m茅todo utilitario que es llamado desde otros subprocesos para manipular a
   // areaPantalla en el subproceso despachador de eventos
   private void mostrarMensaje( final String mensajeAMostrar ){
      // mostrar mensaje del subproceso de ejecuci贸n despachador de eventos
      SwingUtilities.invokeLater(
         new Runnable() {  // clase interna para asegurar que la GUI se actualice apropiadamente
            public void run(){ // actualiza areaPantalla          
               areaPantalla.append( mensajeAMostrar );
               areaPantalla.setCaretPosition( 
               areaPantalla.getText().length() );
            }

         }  // fin de la clase interna

      ); // fin de la llamada a SwingUtilities.invokeLater
   }

   // m茅todo utilitario que es llamado desde otros subprocesos para manipular a 
   // campoIntroducir en el subproceso despachador de eventos
   private void establecerCampoTextoEditable( final boolean editable ){
      // mostrar mensaje del subproceso de ejecuci贸n despachador de eventos
      SwingUtilities.invokeLater(
         new Runnable() {  // clase interna para asegurar que la GUI se actualice apropiadamente

            public void run(){  // establece la capacidad de modificar a campoIntroducir
               campoIntroducir.setEditable( editable );
            }

         }  // fin de la clase interna

      ); // fin de la llamada a SwingUtilities.invokeLater
   }

}  // fin de la clase Servidor