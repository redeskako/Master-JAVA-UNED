package es.uned.master.java.kwic.red.cliente;

import java.io.*;
import java.net.*;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import es.uned.master.java.Interfaz;
import es.uned.master.java.kwic.red.servidor.DatosCliente;

/**
 * Clase que representa comunicacion del cliente con el servidor.  
 * 
 * @author grupo alef (Paz Rodriguez) 
 * @param <Intefaz>
 * @date 12/4/2012 
 */
public class ClienteBD{
	//private Interfaz interfaz;
	//private ObjectOutputStream salida;
	//private ObjectInputStream entrada;
	//private Socket socketCliente = null;
	//private Object datos_leidos;
	//private Date fechaInicio;
	//private int intentos_conexion=0;
	//private int conexiones_realizadas=0;
	private Interfaz applet;

	public ClienteBD(Interfaz applet){
		this.applet = applet;
	}
//Procedimiento que escribe en un objeto de tipo"DatosServidor" los dataos necesarios para enviarselos al servidor antes de cerrar una conexion
/*private DatosServidor actualizarDatosConexion(){		
		//System.out.println("intentos conexion: "+intentos_conexion+"Conexiones realizadas: "+conexiones_realizadas);
		return new DatosServidor(socketCliente.getInetAddress().getHostAddress(),socketCliente.getPort(),fechaInicio,new Date(),intentos_conexion,conexiones_realizadas);
	}*/
	
//Procedimiento que llama a diferentes mï¿½todos para realizar la comunicacion con el servidor y trata las posibles excepciones	
private void ejecutarCliente(){
	 // Conectarse al servidor, crear los flujos y procesar la conexion
	 try {
	   System.out.println("Ha entrado en el bucle ejecutarCliente");
	   conectarAServidor(); 			//Crear un socket para realizar la conexion
	   fechaInicio=new Date(); 		//Capturamos el momento del inicio de la conexion
	   System.out.println("Contenido de fechaInicio 1: "+fechaInicio);
	   crearFlujos();      			//Crear los flujos de entrada y salida
	   procesarConexion(); 			//Procesar la conexion
	   }
	 
	 // El servidor cierra la conexion
	 catch ( EOFException excepcionEOF ) {
	          System.out.println( "El Servidor ha terminado la conexion" );
	 }
	 
	 // Procesar los problemas que pueden ocurrir al comunicarse con el servidor
	 catch ( IOException IOE ) {
	    	  JOptionPane.showMessageDialog(null,  IOE.getMessage()+"\nNo se ha podido establecer la conexion\nComprueba que se esta ejecutando el servidor", "Alerta",JOptionPane.ERROR_MESSAGE);
	    	  IOE.printStackTrace();
	  }
}

/**
 * obtenerPaises: Procedimiento que obtiene los paises
 * @param limite
 * @param primerRegistro
 * 
 */
public void obtenerPaises(int limite, int primerRegistro){
	DataInputStream entrada = null;
	DataOutputStream salida = null;
	System.out.println("Obtencion de Paises");
	Socket servidor = conectarAServidor();
	salida = new DataOutputStream(servidor.getOutputStream());
	entrada = new DataInputStream(servidor.getInputStream());
	salida.flush();
	//Pendiente: Enviar al servidor los datos de todas las operaciones
	ArrayList <Pais>paises = new ArrayList<Pais>();
	String aviso="";
	do{
		Object leido = entrada.readObject();
		if(leido instanceof Pais){
			paises.add((Pais)leido);
		}else if(leido instanceof String){
			aviso =(String)leido;
		}
	}while(!aviso.equals("FIN"));
	applet.setPaises(paises, null);
}
/**
 * obtenerIndicadores: Procedimiento que obtiene los indicadores
 * @param limite
 * @param primerRegistro
 * 
 */
public void obtenerIndicadores(int limite, int primerRegistro){
	ObjectInputStream entrada = null;
	DataOutputStream salida = null;
	System.out.println("Obtencion de Indicadores");
	Socket servidor = conectarAServidor();
	salida = new DataOutputStream(servidor.getOutputStream());
	entrada = new ObjectInputStream(servidor.getInputStream());
	salida.flush();
	//Pendiente: Enviar al servidor los datos de todas las operaciones
	ArrayList <Indicador>indicadores = new ArrayList<Indicador>();
	String aviso="";
	do{
		Object leido = entrada.readObject();
		if(leido instanceof Indicador){
			paises.add((Indicador)leido);
		}else if(leido instanceof String){
			aviso =(String)leido;
		}
	}while(!aviso.equals("FIN"));
	applet.setIndicadores(indicadores, null);
}
/**
 * obtenerEstadisticas: Procedimiento que obtiene las estadisticas
 * @param codPais
 * @param codIndicador
 * 
 */
public void obtenerEstadisticas(String codPais, String codIndicador){
	ObjectInputStream entrada = null;
	DataOutputStream salida = null;
	System.out.println("Obtencion de las estadisticas");
	Socket servidor = conectarAServidor();
	salida = new DataOutputStream(servidor.getOutputStream());
	entrada = new ObjectInputStream(servidor.getInputStream());
	salida.flush();
	//Pendiente: Enviar al servidor los datos de todas las operaciones
	ArrayList <Estadistica>estadisticas = new ArrayList<Estadistica>();
	String aviso="";
	do{
		Object leido = entrada.readObject();
		if(leido instanceof Estadistica){
			estadisticas.add((Estadistica)leido);
		}else if(leido instanceof String){
			aviso =(String)leido;
		}
	}while(!aviso.equals("FIN"));
	applet.setEstadisticas(estadisticas, null);
}
/**
 * obtenerNumPaises: Procedimiento que obtiene el numero de paises totales
 * 
 */
public void obtenerNumPaises(){
	ObjectInputStream entrada = null;
	DataOutputStream salida = null;
	System.out.println("Obtencion de las estadisticas");
	Socket servidor = conectarAServidor();
	salida = new DataOutputStream(servidor.getOutputStream());
	entrada = new ObjectInputStream(servidor.getInputStream());
	salida.flush();
	//Pendiente: Enviar al servidor los datos de todas las operaciones
	
	String aviso="";
	Integer numPaises;
	do{
		Object leido = entrada.readObject();
		if(leido instanceof Integer){
			numPaises = (Integer)leido;
		}else if(leido instanceof String){
			aviso =(String)leido;
		}
	}while(!aviso.equals("FIN"));
	applet.setNumPaises(numPaises);
}

/**
 * obtenerNumIndicadores: Procedimiento que obtiene el numero de indicadores totales
 * 
 */
public void obtenerNumIndicadores(){
	ObjectInputStream entrada = null;
	DataOutputStream salida = null;
	System.out.println("Obtencion del numero de indicadores");
	Socket servidor = conectarAServidor();
	salida = new DataOutputStream(servidor.getOutputStream());
	entrada = new ObjectInputStream(servidor.getInputStream());
	salida.flush();
	//Pendiente: Enviar al servidor los datos de todas las operaciones
	
	String aviso="";
	Integer numIndicadores;
	do{
		Object leido = entrada.readObject();
		if(leido instanceof Integer){
			numIndicadores = (Integer)leido;
		}else if(leido instanceof String){
			aviso =(String)leido;
		}
	}while(!aviso.equals("FIN"));
	applet.setNumIndicadores(numIndicadores);
}


/**
 * conectarAServidor: Procedimiento que realiza la conexion con el servidor
 * 
 */
//Procedimiento que realiza la conexion con el servidor y cambia el estado de varios botones de la interfaz grafica
private Socket conectarAServidor() throws IOException{      
	//Abrir una nueva conexion por parte del cliente
	InetAddress direccion = null;
	Socket socketCliente = null;
	
	socketCliente = new Socket(direccion, applet.getPuertoServidor());
	System.out.println("Cliente: "+socketCliente);
	return  socketCliente;
	}

/**
 * crearFlujos: Procedimiento que establece los flujos de entrada/salida entre el cliente y el servidor
 * 
 */
private void crearFlujos() throws IOException {
	System.out.print("Envio de preguntas:");
	//Establecer flujo de salida de datos
	salida = new ObjectOutputStream( socketCliente.getOutputStream() );      
	salida.flush();
	//Establecer flujo de entrada de datos
	entrada = new ObjectInputStream( socketCliente.getInputStream());
	//Creamos Pregunta para Paises
	Pregunta pregPaises = new Pregunta(PAISES, null, null);
	//Creamos Pregunta para Indicadores
	Pregunta pregIndicadores = new Pregunta(INDICADORES, null, null);
	salida.writeObject(pregPaises);//Enviamos la pregunta de paises al servidor para poder recoger los datos
	salida.writeObject(pregIndicadores);//Enviamos la pregunta de indicadores al servidor para poder recoger los datos

}

/**
 * procesarConexion: Procedimiento que espera la recepcion de datos desde el servidor
 * Comprobamos que tipo de respuesta recibimos del servidor
 *
 */
private void procesarConexion() throws IOException {
	try {
		datos_leidos=entrada.readObject();
		System.out.println("Tipo de dato: "+datos_leidos.getClass());
		System.out.println("Datos recibidos: "+datos_leidos);
	    if (datos_leidos instanceof Respuesta){//
			System.out.println("Es un objeto de tipo Respuesta");
			if (Respuesta.getTipoPeticion().equals(PAISES)){
			   //Rellenamos el JTable de Paises
			}else if (Respuesta.getTipoPeticion().equals(INDICADORES)){
			   //Rellenamos el JTable de Indicador
			}else if (Respuesta.getTipoPeticion().equals(ESTADISTICAS)){
			   //Rellenamos el JTable de Estadisticas
			}
		}else if (datos_leidos instanceof String){//En caso de que se reciba un String comprobamos si es la palabra "fin"
			System.out.println("Es un String");//***********************
	        if(datos_leidos.equals("FIN")||datos_leidos.equals("CADUCA_CONEXION")){
				    System.out.println("Es la palabra FIN");//***********************
				    cancel(true);//Terminamos la ejecucion del programa cancelando el proceso pasando antes por el metodo void()
	        }
	   }
	}
	// Controlar los problemas que pueden ocurrir al leer del servidor
	catch ( ClassNotFoundException e) {
	      System.out.println("Se ha recibido un objeto de tipo desconocido");
	}
}
/**
 * cerrarConexion: Cierre de conexion
 *
 */	   
public void cerrarConexion(){
	try{
		try {
		    //salida.writeObject(actualizarDatosConexion());//Enviamos los datos sobre la conexion actual en un objeto del tipo DatosServidor
		    salida.flush();
		    //enviamos la cadena fin para que el servidor cierre la conexion
		    salida.writeObject("FIN");
		    salida.flush();
		    }
		 // procesar los problemas que pueden ocurrir al enviar el objeto
		 catch ( IOException IOE) {
		     System.out.println("Error al escribir el objeto");
		 }
		//Antes de cerrar la conexion enviamos las estadisticas de la conexion del cliente al servidor
		System.out.println("Cerrando conexion cliente....");
		entrada.close();
	    socketCliente.close();
	}catch (IOException e){
   		System.out.println("Se ha producido un error al intentar cerrar el flujo de datos");
   	}
}
/**
 * actualizar_datos: rocedimiento que actualiza los JTablet
 * @param aux: instancia de la clase Respuestta 
 */	
private void actualizar_datos(final Respuesta aux){
	/*Preguntar a HECTOR, que metodo tengo que llamar de la Interfaz para actualizar JTablet*/
	//interfaz.setPaises(aux.);
}

/**
 * obtenerPaginas: Procedimiento para navegar entre las páginas de Paises y Indicadores
 * @ param peticion: instancia de la clase TipoPeticion
 * @ param limite: numero máximo de registros que se indicaba en la Pregunta que debía contener la Respuesta
 * @ primerRegistro: posición del primer registro devuelto en la Respuesta
 */	
public void obtenerPaginas(TipoPeticion peticion, int limite, int primerRegistro){
	//Instancia pregunta
	Pregunta pregPagina = new Pregunta(peticion, limite, primerRegistro);
	salida.writeObject(pregPagina);//Enviamos la pregunta al servidor
	salida.flush();
}

//Procedimiento llamado cada vez que se pulsa el boton conectar o desconectar
@Override
protected Void doInBackground() throws Exception {
	System.out.println("Se ha pulsado el boton conectar");
	// Conectarse al servidory crear los flujos
	ejecutarCliente();
	while (!isCancelled()) {
		procesarConexion();
	}
	return null;
}
//Solo entre en el metodo done() cuando se cancela el subproceso bien desde la propia clase o desde la interfaz grafica
@Override
protected void  done(){
    cerrarConexion();
    System.out.println("Solo entra aqui al finalizar");
}
}


