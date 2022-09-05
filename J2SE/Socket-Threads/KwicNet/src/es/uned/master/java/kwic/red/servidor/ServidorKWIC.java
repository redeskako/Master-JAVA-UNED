package es.uned.master.java.kwic.red.servidor;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JOptionPane;


import es.uned.master.java.kwic.controlador.KwicFormArea;

public class ServidorKWIC {

	//instancia clase DatosCliente
	private DatosCliente datosCliente;
	//area de texto estadisticas
	private KwicFormArea areaEstadisticas;
	//area de texto de los datos de conexion
	private KwicFormArea areaResultadoClientes;
	//instancia de la clase Estadistica
	protected Estadistica estadisticasServidor;
	//conexiones activas
	protected int conexionesActivas=0;
	//instancia de clase Socket
	private Socket conexion;
	//instancia de la clase ServerSocket
	private ServerSocket servidor;
	protected int conecta=0;
	//numero de hilos
	protected final int tamanoPool=5;
	protected ExecutorService poolHilos;

	
	
	//constructor
	public ServidorKWIC(DatosCliente datosCli, KwicFormArea estadisticas,
			KwicFormArea resultadoClientes)
	   {
		// Damos a los atributos el valor que se le pasa al cliente
				this.datosCliente = datosCli;
				// Obtenemos el area de estadisticas
				this.areaEstadisticas = estadisticas;
				// Obtenemos el area para escribir los datos de los clientes
				this.areaResultadoClientes = resultadoClientes;			
	      } 
	
	/**
	   * Funcion ejecutarServidor: M�todo que configura el servidor para que reciba conexiones y procese las conexiones 
	   * 
	   */
	public void ejecutarServidor() {
		try {			
				//Inicializamos las estadisticas
				estadisticasServidor=new Estadistica();
				//Inicializo el ThreadPool
				 inicializaThreadPool();
				try{
				servidor = new  ServerSocket(10008);
				}catch (BindException be){
					JOptionPane.showMessageDialog(null,  be.getMessage()+"\nEl servidor ya esta funcionando", "Alerta",JOptionPane.ERROR_MESSAGE);
					be.printStackTrace();
				}

			for (int i=0; i<5; i++){
								
				conexion = servidor.accept(); // permitir al servidor aceptar la conexión
				conecta++;				
				incrementaConexionesAtendidas();
	    		conexionesActivas++;
				Runnable nuevoCliente = new Hilo(this.datosCliente, this.areaEstadisticas, this.areaResultadoClientes, this, conexion); 
			    //Thread hilo = new Thread(nuevoCliente);			   
			    poolHilos.execute(nuevoCliente);			    
			    conexionesActivas--;
				
			} // fin de instrucción for

		} // fin del bloque try
			// procesar problemas con E/S
		catch (IOException excepcionES) {
			 JOptionPane.showMessageDialog(null,  excepcionES.getMessage()+"\nNo se ha podido establecer la conexion\nComprueba que se esta ejecutando el servidor", "Alerta",JOptionPane.ERROR_MESSAGE);
			excepcionES.printStackTrace();
		}

	} // fin del método ejecutarServidor
	
	/**
	   * Funcion:Devuelve las estadisticas actuales, garantiza acceso exclusivo
	   * @return Estadistica
	   */
	  public synchronized Estadistica getEstadisticas(){
		  return estadisticasServidor;
	  }

	  /**
	   * Funcion anadeDatosConexion:Anade datos de conexion a las estadisticas, garantiza acceso exclusivo
	   * 
	   */
	  public synchronized void anadeDatosConexion(DatosConexion datos){
		  estadisticasServidor.datosConexiones.add(datos);
	  }
	  
	  /**
	   * Funcion incrementaConexionesAtendidas:Anade conexiones atendidas a las estadisticas, garantiza acceso exclusivo
	   * 
	   */
	  protected void incrementaConexionesAtendidas(){
		  estadisticasServidor.conexionesAtendidas++;
	  }
	  /**
	   * Funcion inicializaThreadPool: Metodo que define un contenedor de 5 hilos
	   * 
	   */
	  private void inicializaThreadPool(){
		  System.out.println("****Inicializando Pool de Hilos a "+tamanoPool);
		  poolHilos=Executors.newFixedThreadPool(tamanoPool);
		 
	  }
		 
}
