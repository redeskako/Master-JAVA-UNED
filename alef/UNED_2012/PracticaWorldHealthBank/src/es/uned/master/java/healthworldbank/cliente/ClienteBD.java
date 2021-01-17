package es.uned.master.java.healthworldbank.cliente;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import org.jfree.data.xy.XYSeries;

import es.uned.master.java.healthworldbank.comunicacion.Pregunta;
import es.uned.master.java.healthworldbank.comunicacion.Respuesta;
import es.uned.master.java.healthworldbank.comunicacion.TipoPeticion;
import es.uned.master.java.healthworldbank.datos.*;
import es.uned.master.java.healthworldbank.servidor.Servidor;

// TODO: Auto-generated Javadoc
/**
 * Clase que se encarga de la comunicación del cliente con el servidor 
 * 
 * @author grupo alef (Paz Rodríguez)
 * @author grupo alef (Marcos Bello)
 * @date 16/4/2012 
 */
public class ClienteBD {

	/** Instancia del applet. */
	private AppletWHB applet;

	/**
	 * Constructor de clase.
	 * 
	 * @param applet
	 *           
	 */
	public ClienteBD(AppletWHB applet) {
		this.applet = applet;
	}	
	
	/**
	 * Metodo para obtener los paises para rellenar la tabla de paises.
	 * 
	 * @param limite
	 *            
	 * @param primerRegistro
	 *            
	 */
	public void obtenerPaises(int limite, int primerRegistro) {
		try {
			System.out.println("Obtencion de Paises");
			Socket servidor = null;
			servidor = conectarAServidor(servidor);
			ObjectOutputStream salida = new ObjectOutputStream(servidor.getOutputStream());
			ObjectInputStream entrada = new ObjectInputStream(servidor.getInputStream());
			salida.flush();
			// Enviamos al servidor un objeto pregunta con el limite y
			// el primer registro para poder obtener el resultado paginado
			// correspondiente
			salida.writeObject(new Pregunta(TipoPeticion.PAISES, limite,primerRegistro));
			salida.flush();

			ArrayList<Pais> paises = new ArrayList<Pais>();
			String aviso = "";
			//Leemos los datos que nos mandan desde el servidor
			do {
				Respuesta leido = (Respuesta) entrada.readObject();
				if (leido.getTipoPeticion().equals(TipoPeticion.PAISES)) {
					List<Registro> registros = leido.getDatos();
					Iterator<Registro> iterador = registros.iterator();
					while (iterador.hasNext()) {
						Pais pais = (Pais) iterador.next();
						paises.add(pais);
					}					
				}else if(leido.getTipoPeticion().equals(TipoPeticion.ERROR)){
					List<Registro> registros = leido.getDatos();
					Iterator<Registro> iterador = registros.iterator();
					while (iterador.hasNext()) {
						ErrorHWB error = (ErrorHWB) iterador.next();
						applet.mostrarError(error.getDescripcion(),"Error en servidor: ");
					}						
				}else if (leido.getTipoPeticion().equals(TipoPeticion.FIN_COMUNICACION)) {
					aviso = "FIN";
				}
			} while (!aviso.equals("FIN"));
			//Cerramos la conexión y mostramos los datos 
			cerrarConexion(servidor, entrada, salida);
			applet.setPaises(paises, null);
		} catch (IOException ie) {
			applet.mostrarError(ie.getMessage(),"Error de conexión: ");
			ie.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			applet.mostrarError(e.getMessage(),"Error en el sistema: ");
			e.printStackTrace();
		}
	}

	/**
	 * Metodo para obtener los indicadores para rellenar la tabla de indicadores.
	 * 
	 * @param limite
	 *            
	 * @param primerRegistro
	 *            
	 */
	public void obtenerIndicadores(int limite, int primerRegistro) {
		try {
			System.out.println("Obtencion de Indicadores");
			Socket servidor = null;
			servidor = conectarAServidor(servidor);
			ObjectOutputStream salida = new ObjectOutputStream(servidor.getOutputStream());
			ObjectInputStream entrada = new ObjectInputStream(servidor.getInputStream());
			// Enviamos al servidor un objeto pregunta con el limite y
			// el primer registro para poder obtener el resultado paginado
			// correspondiente
			salida.writeObject(new Pregunta(TipoPeticion.INDICADORES, limite, primerRegistro));
			salida.flush();

			ArrayList<Indicador> indicadores = new ArrayList<Indicador>();
			String aviso = "";
			//Leemos los datos que nos mandan desde el servidor
			do {
				Respuesta leido = (Respuesta) entrada.readObject();
				
				if (leido.getTipoPeticion().equals(TipoPeticion.INDICADORES)) {
					List<Registro> registros = leido.getDatos();
					System.out.println("Indicadores: " + registros.toString());
					Iterator<Registro> iterador = registros.iterator();
					while (iterador.hasNext()) {
						Indicador indicador = (Indicador) iterador.next();
						indicadores.add(indicador);
					}					
				}else if(leido.getTipoPeticion().equals(TipoPeticion.ERROR)){
					List<Registro> registros = leido.getDatos();
					Iterator<Registro> iterador = registros.iterator();
					while (iterador.hasNext()) {
						ErrorHWB error = (ErrorHWB) iterador.next();
						applet.mostrarError(error.getDescripcion(),"Error en servidor: ");
					}						
				}else if (leido.getTipoPeticion().equals(TipoPeticion.FIN_COMUNICACION)) {
					aviso = "FIN";
				}
			} while (!aviso.equals("FIN"));
			//Cerramos la conexión y mostramos los datos 
			cerrarConexion(servidor, entrada, salida);
			applet.setIndicadores(indicadores, null);
		} catch (IOException ie) {
			// TODO: handle exception
			applet.mostrarError(ie.getMessage(),"Error de conexión: ");
			ie.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			applet.mostrarError(e.getMessage(),"Error en el sistema: ");
			e.printStackTrace();
		}
	}

	/**
	 * Metodo para obtener las estadisticas para rellenar la tabla de estadisticas.
	 * 
	 * @param codPais
	 *            
	 * @param codIndicador
	 *            
	 */
	public void obtenerEstadisticas(String codPais, String codIndicador,
			int limite, int primerRegistro) {
		try {
			System.out.println("Obtencion de las estadisticas");
			Socket servidor = null;
			servidor = conectarAServidor(servidor);
			ObjectOutputStream salida = new ObjectOutputStream(servidor.getOutputStream());
			ObjectInputStream entrada = new ObjectInputStream(servidor.getInputStream());
			salida.flush();
			// Enviamos al servidor un objeto pregunta con el limite y
			// el primer registro para poder obtener el resultado paginado
			// correspondiente
			System.out.println("Limite: " + limite + " Primer Registro: "+ primerRegistro + " Código Pais: " + codPais
					+ " Código Indicador: " + codIndicador);
			salida.writeObject(new Pregunta(TipoPeticion.ESTADISTICAS, limite, primerRegistro, codPais, codIndicador));
			System.out.println("Codigo pais: " + codPais+ " - Codigo indicador: " + codIndicador);
			salida.flush();

			ArrayList<Estadistica> estadisticas = new ArrayList<Estadistica>();
			String aviso = "";
			XYSeries datos = new XYSeries("Estadisticas");
			//Leemos los datos que nos mandan desde el servidor
			do {
				Respuesta leido = (Respuesta) entrada.readObject();
				if (leido.getTipoPeticion().equals(TipoPeticion.ESTADISTICAS)) {
					List<Registro> registros = leido.getDatos();
					System.out.println("ESTADISTICAS: " + registros.toString());
					Iterator<Registro> iterador = registros.iterator();
					while (iterador.hasNext()) {
						Estadistica estadistica = (Estadistica) iterador.next();
						estadisticas.add(estadistica);
						datos.add(estadistica.getAno(), estadistica.getDato());
					}					
				}else if(leido.getTipoPeticion().equals(TipoPeticion.ERROR)){
					List<Registro> registros = leido.getDatos();
					Iterator<Registro> iterador = registros.iterator();
					while (iterador.hasNext()) {
						ErrorHWB error = (ErrorHWB) iterador.next();
						applet.mostrarError(error.getDescripcion(),"Error en servidor: ");
					}						
				} else if (leido.getTipoPeticion().equals(TipoPeticion.FIN_COMUNICACION)) {
					aviso = "FIN";
				}
			} while (!aviso.equals("FIN"));
			//Cerramos la conexión y mostramos los datos 
			cerrarConexion(servidor, entrada, salida);
			applet.setEstadisticas(estadisticas, null);
			applet.cambiarDatosGrafica(datos);
		} catch (IOException ie) {
			applet.mostrarError(ie.getMessage(),"Error de conexión: ");
			ie.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			applet.mostrarError(e.getMessage(),"Error en el sistema: ");
			e.printStackTrace();
		}
	}

	/**
	 * Metodo que obtiene el numero total de paises.
	 */
	public void obtenerNumPaises() {
		try {
			System.out.println("Obtencion de las numero de paises");
			Socket servidor = null;
			servidor = conectarAServidor(servidor);
			ObjectOutputStream salida = new ObjectOutputStream(servidor.getOutputStream());
			ObjectInputStream entrada = new ObjectInputStream(servidor.getInputStream());
			salida.flush();
			// Se le envia al servidor una pregunta con solo el tipo de peticion
			// ya que solo necesitamos conocer el número total de registros de
			// paises
			salida.writeObject(new Pregunta(TipoPeticion.PAISES));
			salida.flush();

			String aviso = "";
			Integer numPaises = 0;
			//Leemos los datos que nos mandan desde el servidor
			do {
				Respuesta leido = (Respuesta) entrada.readObject();
				if (leido.getTotalRegistros() != 0) {
					numPaises = leido.getTotalRegistros();
				}else if(leido.getTipoPeticion().equals(TipoPeticion.ERROR)){
					List<Registro> registros = leido.getDatos();
					Iterator<Registro> iterador = registros.iterator();
					while (iterador.hasNext()) {
						ErrorHWB error = (ErrorHWB) iterador.next();
						applet.mostrarError(error.getDescripcion(),"Error en servidor: ");
					}						
				} else if (leido.getTipoPeticion().equals(TipoPeticion.FIN_COMUNICACION)) {
					aviso = "FIN";
				}
			} while (!aviso.equals("FIN"));
			//Cerramos la conexión y mostramos los datos
			cerrarConexion(servidor, entrada, salida);
			applet.setNumPaises(numPaises);
		} catch (EOFException eof) {
			applet.mostrarError(eof.getMessage(),"Error obteniendo datos: ");
			System.out.println("Excepcion: " + eof.getMessage());
			eof.printStackTrace();
		} catch (IOException ie) {
			// TODO: handle exception
			applet.mostrarError(ie.getMessage(),"Error de conexión: ");
			ie.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			applet.mostrarError(e.getMessage(),"Error en el sistema: ");
			e.printStackTrace();
		}
	}

	/**
	 * Metodo que obtiene el numero total de indicadores.
	 */
	public void obtenerNumIndicadores() {
		try {
			System.out.println("Obtencion del numero de indicadores");
			Socket servidor = null;
			servidor = conectarAServidor(servidor);
			ObjectOutputStream salida = new ObjectOutputStream(servidor.getOutputStream());
			ObjectInputStream entrada = new ObjectInputStream(servidor.getInputStream());
			salida.flush();
			// Se le envia al servidor una pregunta con solo el tipo de peticion
			// ya que solo necesitamos conocer el número total de registros de
			// paises
			salida.writeObject(new Pregunta(TipoPeticion.INDICADORES));
			salida.flush();

			String aviso = "";
			Integer numIndicadores = 0;
			//Leemos los datos que nos mandan desde el servidor
			do {
				Respuesta leido = (Respuesta) entrada.readObject();
				if (leido.getTotalRegistros() != 0) {
					numIndicadores = leido.getTotalRegistros();
				}else if(leido.getTipoPeticion().equals(TipoPeticion.ERROR)){
					List<Registro> registros = leido.getDatos();
					Iterator<Registro> iterador = registros.iterator();
					while (iterador.hasNext()) {
						ErrorHWB error = (ErrorHWB) iterador.next();
						applet.mostrarError(error.getDescripcion(),"Error en servidor: ");
					}						
				}else if (leido.getTipoPeticion().equals(TipoPeticion.FIN_COMUNICACION)) {
					aviso = "FIN";
				}
			} while (!aviso.equals("FIN"));
			//Cerramos la conexión y mostramos los datos
			cerrarConexion(servidor, entrada, salida);
			applet.setNumIndicadores(numIndicadores);
		} catch (EOFException eof) {
			applet.mostrarError(eof.getMessage(),"Error obteniendo datos: ");			
			System.out.println("Error EOF: " + eof.getMessage());
			eof.printStackTrace();
		} catch (IOException ie) {
			// TODO: handle exception
			applet.mostrarError(ie.getMessage(),"Error de conexión: ");
			ie.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			applet.mostrarError(e.getMessage(),"Error en el sistema: ");
			e.printStackTrace();
		}
	}

	/**
	 * Metodo que obtiene el numero total de estadisticas.
	 */
	public void obtenerNumResultados(String codPais, String codIndicador) {
		try {
			System.out.println("Obtencion del numero de resultados");
			Socket servidor = null;
			servidor = conectarAServidor(servidor);
			ObjectOutputStream salida = new ObjectOutputStream(servidor.getOutputStream());
			ObjectInputStream entrada = new ObjectInputStream(servidor.getInputStream());
			salida.flush();
			// Se le envia al servidor una pregunta con solo el tipo de peticion
			// ya que solo necesitamos conocer el número total de registros de
			// paises
			salida.writeObject(new Pregunta(TipoPeticion.ESTADISTICAS, codPais, codIndicador));
			salida.flush();

			String aviso = "";
			Integer numResultados = 0;
			//Leemos los datos que nos mandan desde el servidor
			do {
				Respuesta leido = (Respuesta) entrada.readObject();
				if (leido.getTotalRegistros() != 0) {
					numResultados = leido.getTotalRegistros();
				}else if(leido.getTipoPeticion().equals(TipoPeticion.ERROR)){
					List<Registro> registros = leido.getDatos();
					Iterator<Registro> iterador = registros.iterator();
					while (iterador.hasNext()) {
						ErrorHWB error = (ErrorHWB) iterador.next();
						applet.mostrarError(error.getDescripcion(),"Error en servidor: ");
					}						
				}else if (leido.getTipoPeticion().equals(TipoPeticion.FIN_COMUNICACION)) {
					aviso = "FIN";
				}
			} while (!aviso.equals("FIN"));
			//Cerramos la conexión y mostramos los datos
			cerrarConexion(servidor, entrada, salida);
			System.out.println("Total registros Resultados: " + numResultados);
			applet.setNumResultados(numResultados);
		} catch (EOFException eof) {
			applet.mostrarError(eof.getMessage(),"Error obteniendo datos: ");
			System.out.println("Error EOF: " + eof.getMessage());
			eof.printStackTrace();
		} catch (IOException ie) {
			// TODO: handle exception
			applet.mostrarError(ie.getMessage(),"Error de conexión: ");
			ie.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			applet.mostrarError(e.getMessage(),"Error en el sistema: ");
			e.printStackTrace();
		}
	}

	/**
	 * Método para conectar con el servidor.
	 * 
	 * @return the socket
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@SuppressWarnings({ "finally", "static-access" })
	private Socket conectarAServidor(Socket cliente) throws IOException {
		// Abrir una nueva conexion por parte del cliente
		// InetAddress direccion = 127.0.0.1;
		try {
			Servidor servidor = new Servidor(null);

			cliente = new Socket("127.0.0.1", servidor.PUERTO);
			System.out.println("Cliente: " + cliente);			
		} catch (IOException ex) {
			applet.mostrarError(ex.getMessage(),"Error de conexión: ");
			System.out.println("Error conexion: " + ex.getMessage());
		} finally {
			return cliente;
		}
	}
	
	/**
	 * Método para cerrar la conexión con el servidor.
	 */
	public void cerrarConexion(Socket servidor, ObjectInputStream entrada,
			ObjectOutputStream salida) {
		try {			
			System.out.println("Cerrando conexion cliente....");
			salida.close();
			entrada.close();
			servidor.close();
		} catch (IOException e) {
			applet.mostrarError(e.getMessage(),"Error de conexión: ");
			System.out.println("Se ha producido un error al intentar cerrar el flujo de datos");
		}
	}
}
