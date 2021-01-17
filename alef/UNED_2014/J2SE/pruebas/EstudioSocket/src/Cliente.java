import java.io.*;
import java.net.*;
public class Cliente {

	public static void main(String[] args) {
		/*
		 * Para definir la comunicación entre una  estación cliente y un servidor se necesita :
		 * 
		 * 1.- Un canal de comunicación entre la estación cliente  y el ordenador que ejerza funiciones de servidor. Clase Socket
		 * 2.- Un flujo de datos de entrada. Clase DataInputStream
		 * 3.- Un flujo de datos de salida.Clase OutputInputstream
		 * 
		 * Las tres cosas quedan definidas como atributos de la clase Cliente.  
		 */
		
		Socket cliente = null;
		InputStreamReader rd;
		BufferedReader entrada =null;
		DataOutputStream salida =null;
		
		// Se crean los objetos de las clases anteriormente citadas. 
		 
			
		try{
			cliente = new Socket("localhost",1500);
			salida = new DataOutputStream(cliente.getOutputStream());
			rd = new InputStreamReader(cliente.getInputStream()); 
			entrada = new BufferedReader(rd);
			// entrada = new DataInputStream(cliente.getInputStream());
	        
		//  Se monitoriza  que el nombre del servidor suministrado no sea válido.
			
		}catch(UnknownHostException excepcion){
			System.err.println("No encontrado el servidor localhost");
		}
	      //  Se monitoriza  que el servidor suministrado no se encuentre.
		catch(IOException excepcion){
		System.err.println("No se encuentra el servidor");
	}

	/*
	 * Si se ha podido construir el socket el streaming de entrada y el streaming de salida
	 * Se escribe sobre el streaming de salida la frase "Hola servidor".
	 * Se lee streaming de entrada al cliente. Es decir lo que el servidor manda al cliente. 
	 * Se cierran los streaming de entrada salida y  el socket de comunicaciones. 
	 */
		
	if (cliente !=null  && salida !=null  && entrada !=null){
		try{
			String linea_recibida;
			salida.writeBytes("¡Hola, servidor!");
			linea_recibida=entrada.readLine();
			System.out.println("Servidor: "+linea_recibida);
			salida.close();
			entrada.close();
			cliente.close();
		}catch(UnknownHostException excepcion){
			System.err.println("No encuentro el servidor");
		}
		catch(IOException excepcion){
			System.err.println("Error de entrada/salida");
		}
	}
			
}// fin main
} // fin Cliente