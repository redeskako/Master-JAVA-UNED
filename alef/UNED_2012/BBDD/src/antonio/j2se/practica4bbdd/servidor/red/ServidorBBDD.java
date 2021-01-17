package antonio.j2se.practica4bbdd.servidor.red;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.security.AccessControlException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JOptionPane;

import org.apache.commons.dbcp.BasicDataSource;

import antonio.j2se.practica4bbdd.excepciones.ErrorComunicacionNegocioException;
import antonio.j2se.practica4bbdd.excepciones.ErrorConfiguracionNegocioException;
import antonio.j2se.practica4bbdd.excepciones.SQLNegocioException;
import antonio.j2se.practica4bbdd.servidor.modelo.OperacionBBDD;
import antonio.j2se.practica4bbdd.servidor.vista.Servidor;


/**
 * Clase que representa el servidorBBDD
 * Dispone de un pool de hilos para ir atendiendo las peticiones sin tener que rechazar ninguna
 * dispone de un pool de conexiones a BBDD
 * Se encarga de la comunicacion con el cliente
 * @author  Antonio Sánchez Antón
 * @since  1.0
 */
public class ServidorBBDD implements Runnable {
	  protected final int tamanoPoolHilos=5;
	  protected final int tamanoPoolBBDD=5;
	  protected int puerto=-99;
	  protected ExecutorService poolHilos;
	  protected BasicDataSource poolBBDD;
	  protected ServerSocket servidor;
	  protected Boolean atenderConexiones;
	  protected Servidor aplicacion;
	  protected Properties configuracionServidor;
	  	  
	  
	  /**
	   * Utilizado para cerrar el servidor
	   * Cambia el valor de la variable atenderConexiones provocando el cierre ordenado del servidor
	   */
	  public void noAtenderConexiones() {
		      dejarLog("Parando ServidorBBDD.....");
    		  atenderConexiones=Boolean.FALSE;
    		  try {
    			 if(servidor!=null)  
				     servidor.close();
			} catch (IOException e) {
				 //Connection Reset que se provoca al cerrar el socket
			}
    		  dejarLog("ServidorBBDD Parado");	  
		}
	  
	  
	  
	  /**
	   * Devuelve una conexion del Pool
	   * @return
	 * @throws SQLException 
	   */
	  public Connection getConexion() throws SQLException{
		  return poolBBDD.getConnection();
	  }
	  
	  /**
	   * Escribe en la consola de la aplicacion y en la salida estandard
	   * sincronizado para garantizar acceso exclusivo
	   * @param log
	   */
	  public synchronized void dejarLog(String log){
		  this.aplicacion.setConsola(log);
	  }
	  
	  /**
	   * DEvuelve el valor de la variable atenderConexiones
	   * @return
	   */
	  protected Boolean getAtenderConexiones(){
		  Boolean retorno;
		  synchronized (atenderConexiones) {
			  retorno=atenderConexiones;
		}
		  return retorno;
	  }
	  
	  /**
	   * Constructor
	   * @param app
	   */
	  public ServidorBBDD(Servidor app){
		try{   
		  this.aplicacion=app;
		  dejarLog("Inicializando ServidorBBDD.....");
		  cargarConfiguracion();
		  //Inicializo el Pool de Hilos
		  inicializaThreadPool();
		  //Inicializo el Pool de Conexiones a BBDD
		  inicializaBBDDPool();
		  dejarLog("ServidorBBDD Inicializado");
		}catch(SQLNegocioException sqle){
			sqle.printStackTrace();
			JOptionPane.showMessageDialog(aplicacion, sqle.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			System.exit(-1);
		}catch(ErrorConfiguracionNegocioException ecne){
		    ecne.printStackTrace();
		    JOptionPane.showMessageDialog(aplicacion, ecne.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
		    System.exit(-1);
	    }catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(aplicacion, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			System.exit(-1);
		}
		
	  }
	  
	  /**
	   * Indica si un puerto determinado esta o no en uso
	   * @param puerto
	   * @return
	   */
	 public static Boolean isPuertoEnUso(int puerto){
		 Boolean retorno=Boolean.TRUE; 
		 try{
			 ServerSocket serverPrueba=new ServerSocket(puerto);
			 retorno=Boolean.FALSE;
			 serverPrueba.close();
		  }catch(SocketException se){
			  //El puerto esta en uso
			  retorno=Boolean.TRUE;
		  }finally{
			  return retorno;
		  }
	  }
	  
	  /**
	   * Pone al servidor de BBDD a escuchar en el puerto indicado
	   * @param puerto
	   */
	  public void iniciarServidorBBDD(int puerto){
		      dejarLog("Arrancando ServidorBBDD");
			  this.puerto=puerto;
			  poolHilos.execute(this);
			  dejarLog("ServidorBBDD arrancado en puerto "+puerto+" y escuchando en su hilo");
	  }
	  
  
	  /**
	   * Inicializa el pool de hilos
	   */
	  private void inicializaThreadPool(){
		  dejarLog("****Inicializando Pool de Hilos a "+tamanoPoolHilos);
		  poolHilos=Executors.newFixedThreadPool(tamanoPoolHilos);
		  dejarLog("****Inicializado Pool de Hilos");
	  }
	  
	  /**
	   * Inicializa el pool de conexiones a BBDD
	 * @throws SQLNegocioException 
	   */
	  private void inicializaBBDDPool() throws SQLNegocioException{
		  dejarLog("****Inicializando Pool de Conexiones a BBDD a "+tamanoPoolBBDD);
		  try{
			  poolBBDD=new BasicDataSource();
			  poolBBDD.setInitialSize(tamanoPoolBBDD);
			  poolBBDD.setMaxActive(tamanoPoolBBDD);
			  poolBBDD.setMaxIdle(tamanoPoolBBDD);
			  poolBBDD.setDriverClassName("org.sqlite.JDBC");
			  poolBBDD.setUrl(getULRBBDD());
			  poolBBDD.setPoolPreparedStatements(Boolean.TRUE);
			  poolBBDD.setValidationQuery("SELECT count(1) from Pais");
		 }catch(Exception e){
			  e.printStackTrace();
			  throw new SQLNegocioException("Error al inicializar el Pool de Conexiones a BBDD.",e);
		  }
		  dejarLog("****Inicializado Pool de Conexiones");
	  }
	  
	  /**
	   * Libera los recursos(ServerSocket,Pool de Hilos y Pool de Conexiones a BBDD)
	 * @throws SQLNegocioException 
	   */
	  public void liberarRecursos() throws SQLNegocioException{
		  //cerramos el servidor y liberamos recursos
		   try {
			 if(servidor!=null)  
				 servidor.close();
			poolBBDD.close(); 
			poolHilos.shutdown();
		   } catch (IOException e) {
			   //Conection Reset que se provoca al cerrar el socket,error controlado
		   } catch (SQLException sql){
			   sql.printStackTrace();
			   throw new SQLNegocioException("Error al cerrar el Pool de Conexiones a BBDD.Revise configuracion de BBDD",sql);
		   }
 
	  }
	  
	  /**
	   * Metodo a implementar al ser un Runnable
	   */
	  public void run() {
			  try{
				//Inicializo el ServerSocket
				 servidor=new ServerSocket(this.puerto);
				 this.atenderConexiones=Boolean.TRUE;
				  while(getAtenderConexiones()){
					  Socket conexionSocket=servidor.accept();
					  //Al recibir una peticion esta se pasa a un hilo para ser atendica
	  	    	      Runnable peticion=new OperacionBBDD(getConexion(),conexionSocket,aplicacion);
	  	    	      poolHilos.execute(peticion);
				  }
			  }catch(IOException ioe){
				  //Parada del Servidor
				  //ioe.printStackTrace();
			 }catch (SQLException sqle) {
			 	sqle.printStackTrace();	
			 	JOptionPane.showMessageDialog(aplicacion, "Se ha produccido una error al obtener conexion contra la BBDD.Revise que la ubicacion de la BBDD es la correcta (D:/UNED/Master/Java/J2SE/PracticaBBDD/BBDD/practica4.db)","Error",JOptionPane.ERROR_MESSAGE);
			 	System.exit(-1);
			  }
			
		}



	public int getPuerto() {
		return puerto;
	}
	
    /**
     * Carga la configuracion del servidor
     * El archivo de configuracion se llama configuracionServidor.properties y debe ubicarse en la raiz del proyecto(nivel por encima del src/bin)
     * Configura la url de conexion a la BBDD
     * @throws ErrorConfiguracionNegocioException 
     */
	protected void cargarConfiguracion() throws ErrorConfiguracionNegocioException{
	  File archivoConfiguracion;
	  try{	
		  //archivoConfiguracion= new File(getCodeBase().getFile()).getParentFile();  
		  archivoConfiguracion=new File("."+System.getProperty("file.separator")+"configuracionServidor.properties");
		  configuracionServidor=new Properties();
		  configuracionServidor.load(new FileInputStream(archivoConfiguracion));
		//Comprobamos la correccion del la configuracion
		  getULRBBDD();
	  }catch(IOException ioe){
		  ioe.printStackTrace();
		  throw new ErrorConfiguracionNegocioException("Fichero de configuracion Servidor no encontrado.Revise su ubicacion",ioe);
	  }
	}

	/**
	 * Devuelve la url de BBDD configurada
	 * Utilizado por el servidor para establecer la conexiones a BBDD
	 * @return
	 * @throws ErrorConfiguracionNegocioException
	 */
	public String getULRBBDD() throws ErrorConfiguracionNegocioException{
			String url=configuracionServidor.getProperty("urlbbdd");
			 //Si es "" o contiene " " configuracion erronea
			if( url.trim().equals("") ||url.trim().contains(" ") ){
				throw new ErrorConfiguracionNegocioException("ULR de BBDD erroneamente configurada.Revise Configuracion(fichero configuracionServidor.properties)");	
			}else
				return url;
	}

     
}



	

