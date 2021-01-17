package Server;
import java.net.Socket;
import java.net.ServerSocket;
import java.io.*;
public class Server {

	 private int puerto;
	 private Socket conexion;
	 private ServerSocket servidor;
	 private ObjectOutputStream salida;
	 private ObjectInputStream entrada;
	 
	 public Server(int puerto){
		  this.puerto=puerto;
	 }

	 public void EsperarConexion() throws IOException{
		  conexion = servidor.accept();
		  conexion.getInetAddress().getHostName();
	 }
	 
	 public void ObtenerFlujos() throws IOException{
		 entrada = new ObjectInputStream(conexion.getInputStream() );
		 salida = new ObjectOutputStream( conexion.getOutputStream() );
	     salida.flush(); // vaciar buffer de salida para enviar información de encabezado
	 }
	 
	 
	 public void cerrarConexion () throws IOException{
		 salida.close();
         entrada.close();
         conexion.close();
	 }
	 
		   /*   1.- Crear un objeto ServerSocket.
		     *   2.- Esperar conexion.
		     *   3.- Obtener los flujos de entrada salida.
		     *   4.- Procesar conexión.
		     *   5.- Cerrar la conexion.
		     *   
		     *   
		    */
		 
		
		    
	
}
