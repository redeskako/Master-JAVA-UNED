package es.uned2014.oca.servidor;

import java.io.IOException;
import java.net.Socket;

import es.uned2014.oca.comunicaciones.Comunicacion;
import es.uned2014.oca.comunicaciones.TipoComunicacion;
import es.uned2014.oca.jugador.ColorJugador;

/**
 * Se define una clase ServidorEsperando para poder escuchar el ServerSocket de manera
 * permanente, mientras se realizan otras acciones.
 * @author	Alef UNED 2014
 * @version	Oca 3.0
 * @fecha 	Mayo 2014
 */

public class ServidorEsperando extends Thread{

	// Servidor desde el que se gestiona la aplicaci�n
	private Servidor servidor;
	// N�mero de jugadores
	private int num;
	
	/**
	 * M�todo constructor: se asignan los par�metros a servidor y num (que es n�mero de jugadores). 
	 * @param Servidor desde el que se gestiona la aplicaci�n
	 * @param Valor entero que se asigna al n�mero de jugadores
	 * @return null
	 * @throws null
	 */
	public ServidorEsperando(Servidor s, int i){
		this.servidor = s;
		this.num = i;
	}
	
	/**
	 * El servidor queda a la espera de conexiones de Clientes.
	 * @param null
	 * @return null
	 * @throws Error en la creaci�n de la conexi�n Servidor-Cliente
	*/
	public void run(){
		
		try{
			// Se espera a que haya conexiones de Clientes y se crean las conexiones
			// Mientras no hay suficientes jugadores, se espera a m�s conexiones
			while (servidor.getServidorConectado() && servidor.getMapClientes().size() < num){

				// Env�a Comunicacion a todos los Clientes
				String sEspera = "\nServidor a la espera de conexiones de clientes... (conexiones "
						+ "actuales: " + servidor.getMapClientes().size() + " de " + num + ")";
				Comunicacion comunicacionEspera = new Comunicacion(sEspera, TipoComunicacion.INFO);
				this.servidor.enviarATodos(comunicacionEspera);
				
				// Informa en su GUI
				this.servidor.infoConexionesGUI(sEspera);
				
				// Se registra la conexi�n de un Cliente
				Socket nuevoSocket = servidor.getSocketServidor().accept();

				// Se identifica el Color que va a identificar a esta conexi�n
				ColorJugador c = this.servidor.elegirColor();
						
				// Se construye un ClienteThread (hilo) con esa conexi�n 
				// (se aumenta contador para que asigne otro color a la siguiente conexi�n de Cliente)
				ClienteThread nuevoCliente = new ClienteThread(nuevoSocket, c, this.servidor );
				
				// Ejecuta el m�todo run() del ClienteThread
				nuevoCliente.start();
				
				// Se da lavor inical al cliente (COLOR_JUGADOR Y NO_ES_TU_TURNO)
				nuevoCliente.iniciarCliente();
        		
				// Se a�ade al map de conexiones con clientes
				this.servidor.getMapClientes().put(c, nuevoCliente);
			}
			
			// Env�a Comunicacion a todos los Clientes
			String s = "\nYa hay suficientes jugadores, va a comenzar la partida.\n";
			Comunicacion comunicacionOrden = new Comunicacion(s, TipoComunicacion.INFO);
			this.servidor.enviarATodos(comunicacionOrden);
			
			// Informa en su GUI
			this.servidor.infoConexionesGUI(s);
			
			// Empezar la partida
			this.servidor.jugarOca();
				
		} catch (IOException e) {
			if (this.servidor.getServidorConectado()){
				servidor.infoConexionesGUI("\nSe ha detenido la espera de conexiones Cliente-Servidor. ");
			}
			
		}
	}
}

