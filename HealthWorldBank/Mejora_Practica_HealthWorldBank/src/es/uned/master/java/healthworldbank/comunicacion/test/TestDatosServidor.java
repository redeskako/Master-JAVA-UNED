package es.uned.master.java.healthworldbank.comunicacion.test;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import es.uned.master.java.healthworldbank.comunicacion.Pregunta;
import es.uned.master.java.healthworldbank.comunicacion.Respuesta;
import es.uned.master.java.healthworldbank.comunicacion.TipoPeticion;
import es.uned.master.java.healthworldbank.datos.Estadistica;
import es.uned.master.java.healthworldbank.datos.Indicador;
import es.uned.master.java.healthworldbank.datos.Pais;
import es.uned.master.java.healthworldbank.datos.Registro;

/**
 * Servidor ligero que comprueba la comunicación con el servidor y la base de dato
 * utilizando las clases definidas para intercambio de información
 * 
 * @author grupo alef (Juan Sánchez)
 *
 */
public class TestDatosServidor {
	// Puerto del servidor. Por defecto 10088
	public static final int PUERTO = 10088;

	/**
	 * Constructor de clase
	 * Arranca el servidor y espera indifenidamente conexiones del cliente
	 */
	public TestDatosServidor() {
		System.out.println("Servidor iniciado");
		
		ServerSocket servidorSocket = null;
		Socket cliente = null;

		try {
			//Creamos nuestro servidor socket
			servidorSocket = new ServerSocket(PUERTO);
			
			while(true) {
				try {
					// aceptar conexiones de clientes
					cliente = servidorSocket.accept();

					//Obtenemos flujos de entrada y de salida de 'socket'
					ObjectInputStream objEntrada = new ObjectInputStream (cliente.getInputStream());
					ObjectOutputStream objSalida = new ObjectOutputStream (cliente.getOutputStream());

					//Leemos un mensaje que llega por el socket
					Pregunta preguntaEntrada = (Pregunta) objEntrada.readObject();
					
					System.out.println("Leída pregunta: " + preguntaEntrada.getTipoPeticion());

					// procesar pregunta
					Respuesta rsp = consultaBd(preguntaEntrada);

					// enviar respuesta
					objSalida.writeObject(rsp);
					
					
				} catch (SocketTimeoutException ioe) {
					System.out.println("Conexión caducada.");
				} catch (Exception e) { 
					System.out.println("Error en el servidor: ");
					e.printStackTrace();
				}
			}		

		} catch (Exception e) {
			System.out.println("Excepción:");
			e.printStackTrace();
		} finally {
			//Cerramos cliente 
			if ( cliente != null && !cliente.isClosed() ) {
				try {
					cliente.close();
					//cliente.close();
				} catch (IOException e) {
					System.out.println("Excepción al cerrar socket cliente:");
					e.printStackTrace();
				}
			}
			if ( servidorSocket != null && !servidorSocket.isClosed() ) {
				try {
					servidorSocket.close();
					//cliente.close();
				} catch (IOException e) {
					System.out.println("Excepción al cerrar socket servidor:");
					e.printStackTrace();
				}
			}
			System.out.println("Servidor cerrado.");
		}
	}
	
	/**
	 * Consulta la base de datos los datos solicitados por el cliente
	 * 
	 * @param pregEntrada pregunta leída del cliente
	 * @return respuesta a entregar al cliente
	 */
	private Respuesta consultaBd(Pregunta pregEntrada) {
		Respuesta rsp = null;
		String CLASE_CONEXION = "org.sqlite.JDBC";		// clase de conexión	
		String DRIVER_MANAGER = "jdbc:sqlite:Datos.db";	// driver para conectar a Datos.db (la crea si no existe)
		Connection conn = null;
		Statement consulta = null;
		ResultSet resultados = null, cuenta = null;
		ArrayList<Registro> reg = null;
		int totalRegistros = 0;
		TipoPeticion tp = pregEntrada.getTipoPeticion();
		String qry=null;
	
		try {
			Class.forName(CLASE_CONEXION);
			conn = DriverManager.getConnection(DRIVER_MANAGER);
	
			System.out.println("Consultando base de datos...");
			try {
				// Contar registros
				switch (tp) {
				case PAISES:
					consulta = conn.createStatement();
					System.out.println("Consultando: " + qry);
					qry = "SELECT COUNT(*) as contador FROM COUNTRY;";
					cuenta = consulta.executeQuery(qry);
					cuenta.next();
					totalRegistros = cuenta.getInt("contador");	
					System.out.println("Indicadores encontrados: " + totalRegistros);
					if (totalRegistros > 0) {		// continúa
						qry="SELECT COUNTRY_CODE, COUNTRY_NAME FROM COUNTRY ORDER BY COUNTRY_NAME;";
						System.out.println("Consultando: " + qry);
						resultados = consulta.executeQuery(qry);
						reg = new ArrayList<Registro>();
						while (resultados.next()) {
							reg.add(new Pais((String)resultados.getObject(1), (String)resultados.getObject(2)));
						}
						
						rsp = new Respuesta(TipoPeticion.PAISES, 0, 1, totalRegistros,reg); 
					} else {
						// error datos;
					}							
					break;
				case INDICADORES:
					consulta = conn.createStatement();
					System.out.println("Consultando: " + qry);
					qry = "SELECT COUNT(*) as contador FROM HEALTH_INDICATOR;";
					cuenta = consulta.executeQuery(qry);
					cuenta.next();
					totalRegistros = cuenta.getInt("contador");	
					System.out.println("Indicadores encontrados: " + totalRegistros);
					if (totalRegistros > 0) {		// continúa
						qry="SELECT INDICATOR_CODE, INDICATOR_NAME, SOURCE_NOTE, SOURCE_ORGANIZATION FROM HEALTH_INDICATOR ORDER BY INDICATOR_NAME;";
						System.out.println("Consultando: " + qry);
						resultados = consulta.executeQuery(qry);
						reg = new ArrayList<Registro>();
						while (resultados.next()) {
							reg.add(new Indicador((String)resultados.getObject(1), (String)resultados.getObject(2),(String)resultados.getObject(3), (String)resultados.getObject(4)));
						}
						
						rsp = new Respuesta(TipoPeticion.INDICADORES, 0, 1, totalRegistros,reg); 
					} else {	
								// error datos;
					}							
					break;
				case ESTADISTICAS:
					consulta = conn.createStatement();
					qry = "SELECT COUNT(*) as contador FROM DATA;";
					System.out.println("Consultando: " + qry);
					cuenta = consulta.executeQuery(qry);
					cuenta.next();
					totalRegistros = cuenta.getInt("contador");	
					System.out.println("Datos encontrados: " + totalRegistros);
					if (totalRegistros > 0) {		// continúa
						qry = "SELECT INDICATOR_CODE, COUNTRY_CODE, YEAR, PERCENTAGE FROM DATA " +
								"ORDER BY YEAR " +
								"LIMIT " + pregEntrada.getPrimerRegistro() + ", " + pregEntrada.getLimite() + ";";
						System.out.println("Consultando: " + qry);
						resultados = consulta.executeQuery(qry);
						reg = new ArrayList<Registro>();
						while (resultados.next()) {
							reg.add(new Estadistica((String)resultados.getObject(1), (String)resultados.getObject(2),(Integer)resultados.getObject(3), (Double)resultados.getObject(4)));
						}
						
						rsp = new Respuesta(TipoPeticion.ESTADISTICAS, 0, 1, totalRegistros,reg); 
					} else {	
								// error datos;
					}							
					break;
				
				}
			} catch (SQLException sqle) {
				System.out.println("Excepción al consultar datos: " + sqle);
			} finally {
				try {
					cuenta.close();
					resultados.close();
				} catch (SQLException e) {
					System.out.println("Excepción al cerrar el statement: " + e);
				} catch (Exception e) {
					
				}
			}
	
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("Fin de consulta");
		}	
		return rsp;
	}

	/**
	 * Punto de entrada a la aplicación
	 * 
	 * @param args no se usan
	 */
	public static void main(String[] args) {
		new TestDatosServidor();
	}

}
