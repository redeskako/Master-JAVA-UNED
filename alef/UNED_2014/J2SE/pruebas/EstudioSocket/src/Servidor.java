

import java.io.*;
import java.net.*;
public class Servidor {

			public static void main(String [] args){
			 ServerSocket mi_servicio = null;
			 String linea_recibida;
			 InputStreamReader rd;
			 BufferedReader  entrada;
			 PrintStream salida;
			 Socket socket_conectado= null;
			 try{
				  // Se crea el socket mi_servicio. 
				 mi_servicio = new ServerSocket(1500);
			 }
			 catch(IOException excepcion){
				 System.out.println(excepcion);
			 }
			 try{
				 // Se pone el socket creado a la escucha de peticiones
				 socket_conectado = mi_servicio.accept();
				 // Se lee el Streaming de entrada que se ha conectado al socket.   
				 rd = new InputStreamReader(socket_conectado.getInputStream());
				 entrada =new BufferedReader(rd);
				 // Se responde a la solicitud recibida por el servidor
				 salida = new PrintStream(socket_conectado.getOutputStream());
				 linea_recibida = entrada.readLine();
				 salida.println(linea_recibida);
				 salida.close();
				 entrada.close();
				 socket_conectado.close();
			 }catch(IOException excepcion){
				 System.out.println(excepcion);
			 }
		}
				
	 } 

