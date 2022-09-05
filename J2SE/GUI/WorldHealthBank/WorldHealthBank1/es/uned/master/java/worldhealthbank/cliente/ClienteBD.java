package es.uned.master.java.worldhealthbank.cliente;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.text.html.HTMLDocument.Iterator;

import es.uned.master.java.healthworldbank.comunicacion.Pregunta;
import es.uned.master.java.healthworldbank.comunicacion.Respuesta;
import es.uned.master.java.healthworldbank.comunicacion.TipoPeticion;
import es.uned.master.java.healthworldbank.datos.*;

// TODO: Auto-generated Javadoc
/**
 * The Class ClienteBD.
 */
public class ClienteBD {
	
	/** The applet. */
	private AppletWHB applet;
	
	/** The entrada. */
	private ObjectInputStream entrada = null;
	
	/** The salida. */
	private ObjectOutputStream salida = null;
	
	/** The servidor. */
	private Socket servidor = null;
	
	/** The socket cliente. */
	Socket socketCliente = null;

	/**
	 * Instantiates a new cliente bd.
	 * 
	 * @param applet
	 *            the applet
	 */
	public ClienteBD(AppletWHB applet) {
		this.applet = applet;
	}

	/**
	 * Obtener paises.
	 * 
	 * @param limite
	 *            the limite
	 * @param primerRegistro
	 *            the primer registro
	 */
	public void obtenerPaises(int limite, int primerRegistro) {
		try {			
			System.out.println("Obtencion de Paises");
			servidor = conectarAServidor();
			salida = new ObjectOutputStream(servidor.getOutputStream());
			entrada = new ObjectInputStream(servidor.getInputStream());
			salida.flush();
			// Enviamos al servidor un objeto pregunta con el limite y
			// el primer registro para poder obtener el resultado paginado correspondiente
			salida.writeObject(new Pregunta(TipoPeticion.PAISES, limite, primerRegistro));
			salida.flush();
			
			ArrayList<Registro> paises = null;
			String aviso = "";
			do {
				Respuesta leido = (Respuesta)entrada.readObject();
				if (leido.getTipoPeticion().equals("PAISES")) {
					paises = new ArrayList<Registro>(leido.getDatos());
				} else if (leido instanceof String) {
					aviso = (String) leido;
				}
			} while (!aviso.equals("FIN"));
			applet.setPaises(paises, null);
		} catch (IOException ie) {
			ie.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Obtener indicadores.
	 * 
	 * @param limite
	 *            the limite
	 * @param primerRegistro
	 *            the primer registro
	 */
	public void obtenerIndicadores(int limite, int primerRegistro) {
		try {			
			System.out.println("Obtencion de Indicadores");
			servidor = conectarAServidor();
			salida = new ObjectOutputStream(servidor.getOutputStream());
			entrada = new ObjectInputStream(servidor.getInputStream());
			salida.flush();
			// Enviamos al servidor un objeto pregunta con el limite y
			// el primer registro para poder obtener el resultado paginado correspondiente
			salida.writeObject(new Pregunta(TipoPeticion.INDICADORES, limite, primerRegistro));
			salida.flush();
			
			ArrayList<Registro> indicadores = null;
			String aviso = "";
			do {
				Respuesta leido = (Respuesta)entrada.readObject();
				//Object leido = entrada.readObject();
				if (leido.getTipoPeticion().equals("INDICADORES")) {
					indicadores = new ArrayList<Registro>(leido.getDatos());					
				} else if (leido instanceof String) {
					aviso = (String) leido;
				}
			} while (!aviso.equals("FIN"));
			applet.setIndicadores(indicadores, null);
		} catch (IOException ie) {
			// TODO: handle exception
			ie.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Obtener estadisticas.
	 * 
	 * @param codPais
	 *            the cod pais
	 * @param codIndicador
	 *            the cod indicador
	 */
	public void obtenerEstadisticas(String codPais, String codIndicador) {
		try {			
			System.out.println("Obtencion de las estadisticas");
			servidor = conectarAServidor();
			salida = new DataOutputStream(servidor.getOutputStream());
			entrada = new ObjectInputStream(servidor.getInputStream());
			salida.flush();
			// Enviamos al servidor un objeto pregunta con el limite y
			// el primer registro para poder obtener el resultado paginado correspondiente
			salida.writeObject(new Pregunta(TipoPeticion.ESTADISTICAS, codPais, codIndicador));
			salida.flush();
			
			ArrayList<Registro> estadisticas = null;
			String aviso = "";
			do {
				Respuesta leido = (Respuesta)entrada.readObject();
				if (leido.getTipoPeticion().equals("ESTADISTICAS")) {
					estadisticas = new ArrayList<Registro>(leido.getDatos());
				} else if (leido instanceof String) {
					aviso = (String) leido;
				}
			} while (!aviso.equals("FIN"));
			applet.setEstadisticas(estadisticas, null);
		} catch (IOException ie) {
			ie.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Obtener num paises.
	 */
	public void obtenerNumPaises() {
		try {			
			System.out.println("Obtencion de las estadisticas");
			servidor = conectarAServidor();
			salida = new ObjectOutputStream(servidor.getOutputStream());
			entrada = new ObjectInputStream(servidor.getInputStream());
			salida.flush();
			// Se le envia al servidor una pregunta con solo el tipo de peticion
			//ya que solo necesitamos conocer el número total de registros de paises
			salida.writeObject(new Pregunta(TipoPeticion.PAISES));
			salida.flush();

			String aviso = "";
			Integer numPaises = 0;
			do {
				Respuesta leido = (Respuesta)entrada.readObject();
				if (leido.getTotalRegistros() != 0) {
					numPaises = leido.getTotalRegistros();
				} else if (leido instanceof String) {
					aviso = (String) leido;
				}
			} while (!aviso.equals("FIN"));
			applet.setNumPaises(numPaises);
		} catch (IOException ie) {
			// TODO: handle exception
			ie.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
	/**
	 * Obtener num indicadores.
	 */
	public void obtenerNumIndicadores() {
		try {			
			System.out.println("Obtencion del numero de indicadores");
			servidor = conectarAServidor();
			salida = new ObjectOutputStream(servidor.getOutputStream());
			entrada = new ObjectInputStream(servidor.getInputStream());
			salida.flush();
			// Se le envia al servidor una pregunta con solo el tipo de peticion
			//ya que solo necesitamos conocer el número total de registros de paises
			salida.writeObject(new Pregunta(TipoPeticion.INDICADORES));
			salida.flush();

			String aviso = "";
			Integer numIndicadores = 0;
			do {
				Respuesta leido = (Respuesta)entrada.readObject();
				if (leido.getTotalRegistros() != 0) {
					numIndicadores = leido.getTotalRegistros();
				} else if (leido instanceof String) {
					aviso = (String) leido;
				}
			} while (!aviso.equals("FIN"));
			applet.setNumIndicadores(numIndicadores);
		} catch (IOException ie) {
			// TODO: handle exception
			ie.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	/**
	 * Conectar a servidor.
	 * 
	 * @return the socket
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private Socket conectarAServidor() throws IOException {
		// Abrir una nueva conexion por parte del cliente
		InetAddress direccion = null;
		

		socketCliente = new Socket(direccion, applet.getPuertoServidor());
		System.out.println("Cliente: " + socketCliente);
		return socketCliente;
	}

	
	/**
	 * Cerrar conexion.
	 */
	public void cerrarConexion() {
		try {
			try {
				
				salida.flush();
				// enviamos la cadena fin para que el servidor cierre la
				// conexion
				salida.writeUTF("FIN");
				salida.flush();
			}
			// procesar los problemas que pueden ocurrir al enviar el objeto
			catch (IOException IOE) {
				System.out.println("Error al escribir el objeto");
			}
			// Antes de cerrar la conexion enviamos las estadisticas de la
			// conexion del cliente al servidor
			System.out.println("Cerrando conexion cliente....");
			entrada.close();
			socketCliente.close();
		} catch (IOException e) {
			System.out.println("Se ha producido un error al intentar cerrar el flujo de datos");
		}
	}

	
	/**
	 * Obtener paginas.
	 * 
	 * @param peticion
	 *            the peticion
	 * @param limite
	 *            the limite
	 * @param primerRegistro
	 *            the primer registro
	 */
	/*public void obtenerPaginas(TipoPeticion peticion, int limite,
			int primerRegistro) {
		// Instancia pregunta
		Pregunta pregPagina = new Pregunta(peticion, limite, primerRegistro);
		salida.writeObject(pregPagina);// Enviamos la pregunta al servidor
		salida.flush();
	}*/


}
