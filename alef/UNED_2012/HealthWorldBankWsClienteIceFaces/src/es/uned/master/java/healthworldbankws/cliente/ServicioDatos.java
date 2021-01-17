package es.uned.master.java.healthworldbankws.cliente;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.apache.axis2.AxisFault;
import org.jfree.data.xy.XYSeries;

import es.uned.master.java.healthworldbank.datos.*;
import es.uned.master.java.healthworldbankws.healthworldbankws.EstadisticaType;
import es.uned.master.java.healthworldbankws.healthworldbankws.HealthWorldBankWsStub;
import es.uned.master.java.healthworldbankws.healthworldbankws.IndicadorType;
import es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerEstadisticas;
import es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerEstadisticasResponse;
import es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerIndicadores;
import es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerIndicadoresResponse;
import es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerPaises;
import es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerPaisesResponse;
import es.uned.master.java.healthworldbankws.healthworldbankws.PaisType;

// TODO: Auto-generated Javadoc
/**
 * Clase que se encarga de la comunicaciÃ³n del cliente con el servidor
 * 
 * @author grupo alef (Paz RodrÃ­guez)
 * @author grupo alef (Marcos Bello)
 * @author grupo alef (Juan Sánchez)
 * @date 16/4/2012
 */
public class ServicioDatos {
	private static final String endpoint = "http://localhost:8080/HealthWorldBankWsServidor/services/HealthWorldBankWs/";
	private static final ServicioDatos INSTANCIA = new ServicioDatos();

	private ServicioDatos() {
		if (INSTANCIA != null) {
			throw new IllegalStateException("Ya está instanciado");
		}
	}

	public static ServicioDatos getInstancia() {
		return INSTANCIA;
	}

	/**
	 * Metodo para obtener los paises para rellenar la tabla de paises.
	 * 
	 * @param limite
	 * 
	 * @param primerRegistro
	 * 
	 */
	public ArrayList<Pais> obtenerPaises(int limite, int primerRegistro) {

		ObtenerPaises obtenerPaises = new ObtenerPaises();
		obtenerPaises.setPrimerRegistro(primerRegistro);
		obtenerPaises.setLimite(limite);
		try {
			HealthWorldBankWsStub stub = newStub();
			ObtenerPaisesResponse obtenerPaisesResponse = stub
					.obtenerPaises(obtenerPaises);

			// Comprobamos si ha habido algún error:
			String error = obtenerPaisesResponse.getError();
			if (error != null) {
				// //applet.mostrarError(error,"Error en servicio web: ");
				return null;
			}

			// Obtenemos los datos:
			ArrayList<Pais> paises = new ArrayList<Pais>();
			PaisType[] paisesType = obtenerPaisesResponse.getPais();
			for (PaisType paisType : paisesType) {
				paises.add(obtenerPais(paisType));
			}

			// Mostramos los datos:
			// applet.setPaises(paises, null);
			return paises;

		} catch (AxisFault e) {
			// applet.mostrarError(e.getMessage(),"Error en el sistema: ");
			e.printStackTrace();
		} catch (RemoteException e) {
			// applet.mostrarError(e.getMessage(),"Error en el sistema: ");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Metodo REST para obtener los paises para rellenar la tabla de paises.
	 * 
	 * @param limite
	 * 
	 * @param primerRegistro
	 * 
	 */
	public void obtenerPaisesREST(int limite, int primerRegistro) {

		HttpURLConnection conn;
		String xml = "";

		try {
			conn = get_connection(endpoint
					+ "ObtenerPaises?tipoPeticion=PAISES&limite=" + limite
					+ "&primerRegistro=" + primerRegistro, "GET");
			conn.connect();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			String next = null;
			while ((next = reader.readLine()) != null)
				xml += next;

			XMLInputFactory factory = XMLInputFactory.newInstance();
			XMLStreamReader XMLreader = factory
					.createXMLStreamReader(new ByteArrayInputStream(xml
							.getBytes()));
			ObtenerPaisesResponse obtenerPaisesResponse = ObtenerPaisesResponse.Factory
					.parse(XMLreader);

			// Obtenemos los datos:
			ArrayList<Pais> paises = new ArrayList<Pais>();
			PaisType[] paisesType = obtenerPaisesResponse.getPais();
			for (PaisType paisType : paisesType) {
				paises.add(obtenerPais(paisType));
			}

			// Mostramos los datos:
			// applet.setPaises(paises, null);

		} catch (XMLStreamException e) {
			// applet.mostrarError(e.getMessage(),"Error en el sistema: ");
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// applet.mostrarError(e.getMessage(),"Error en el sistema: ");
			e.printStackTrace();
		} catch (IOException e1) {
			// applet.mostrarError(e1.getMessage(),"Error en el sistema: ");
			e1.printStackTrace();
		} catch (Exception e) {
			// applet.mostrarError(e.getMessage(),"Error en el sistema: ");
			e.printStackTrace();
		}

	}

	/**
	 * Conecta con el servicio web REST
	 * 
	 * @param url_string
	 *            url de conexión
	 * @param verb
	 *            verbo (GET, PUT ...)
	 * @return la conexión
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	private HttpURLConnection get_connection(String url_string, String verb)
			throws MalformedURLException, IOException {
		HttpURLConnection conn = null;
		URL url = new URL(url_string);
		conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod(verb);
		return conn;
	}

	/**
	 * Metodo para obtener los indicadores para rellenar la tabla de
	 * indicadores.
	 * 
	 * @param limite
	 * 
	 * @param primerRegistro
	 * 
	 */
	public void obtenerIndicadores(int limite, int primerRegistro) {

		ObtenerIndicadores obtenerIndicadores = new ObtenerIndicadores();
		obtenerIndicadores.setPrimerRegistro(primerRegistro);
		obtenerIndicadores.setLimite(limite);
		try {
			HealthWorldBankWsStub stub = newStub();
			ObtenerIndicadoresResponse obtenerIndicadoresResponse = stub
					.obtenerIndicadores(obtenerIndicadores);

			// Comprobamos si ha habido algún error:
			String error = obtenerIndicadoresResponse.getError();
			if (error != null) {
				// applet.mostrarError(error,"Error en servicio web: ");
				return;
			}

			// Obtenemos los datos:
			ArrayList<Indicador> indicadores = new ArrayList<Indicador>();
			IndicadorType[] indicadoresType = obtenerIndicadoresResponse
					.getIndicador();
			for (IndicadorType indicadorType : indicadoresType) {
				indicadores.add(obtenerIndicador(indicadorType));
			}

			// Mostramos los datos:
			// applet.setIndicadores(indicadores, null);

		} catch (AxisFault e) {
			// applet.mostrarError(e.getMessage(),"Error en el sistema: ");
			e.printStackTrace();
		} catch (RemoteException e) {
			// applet.mostrarError(e.getMessage(),"Error en el sistema: ");
			e.printStackTrace();
		}
	}

	/**
	 * Metodo para obtener las estadisticas para rellenar la tabla de
	 * estadisticas.
	 * 
	 * @param codPais
	 * 
	 * @param codIndicador
	 * 
	 */
	public void obtenerEstadisticas(String codPais, String codIndicador,
			int limite, int primerRegistro) {

		// ArrayList<Estadistica> estadisticas = new ArrayList<Estadistica>();//
		XYSeries datos = new XYSeries("Estadisticas");//

		ObtenerEstadisticas obtenerEstadisticas = new ObtenerEstadisticas();
		obtenerEstadisticas.setPrimerRegistro(primerRegistro);
		obtenerEstadisticas.setLimite(limite);
		obtenerEstadisticas.setCodigoIndicador(codIndicador);
		obtenerEstadisticas.setCodigoPais(codPais);
		try {
			HealthWorldBankWsStub stub = newStub();
			ObtenerEstadisticasResponse obtenerEstadisticasResponse = stub
					.obtenerEstadisticas(obtenerEstadisticas);

			// Comprobamos si ha habido algún error:
			String error = obtenerEstadisticasResponse.getError();
			if (error != null) {
				// applet.mostrarError(error,"Error en servicio web: ");
				return;
			}

			// Obtenemos los datos:
			ArrayList<Estadistica> estadisticas = new ArrayList<Estadistica>();
			EstadisticaType[] estadisticasType = obtenerEstadisticasResponse
					.getEstadistica();
			for (EstadisticaType estadisticaType : estadisticasType) {
				estadisticas.add(obtenerEstadistica(estadisticaType));
				datos.add(estadisticaType.getAno(), estadisticaType.getDato());//
			}

			// Mostramos los datos:
			// applet.setEstadisticas(estadisticas, null);
			// applet.cambiarDatosGrafica(datos);//

		} catch (AxisFault e) {
			// applet.mostrarError(e.getMessage(),"Error en el sistema: ");
			e.printStackTrace();
		} catch (RemoteException e) {
			// applet.mostrarError(e.getMessage(),"Error en el sistema: ");
			e.printStackTrace();
		}
	}

	/**
	 * Metodo que obtiene el numero total de paises.
	 */
	public int obtenerNumPaises() {
		int numPaises=0;
		ObtenerPaises obtenerPaises = new ObtenerPaises();
		// En este caso nos da igual el primer registro y el límite
		obtenerPaises.setPrimerRegistro(1);
		obtenerPaises.setLimite(1);
		try {
			HealthWorldBankWsStub stub = newStub();
			ObtenerPaisesResponse obtenerPaisesResponse = stub
					.obtenerPaises(obtenerPaises);

			// Comprobamos si ha habido algún error:
			String error = obtenerPaisesResponse.getError();
			if (error != null) {
				// applet.mostrarError(error,"Error en servicio web: ");
				return 0;
			}

			// Obtenemos los datos:
			numPaises = obtenerPaisesResponse.getTotalRegistros();

			// Mostramos los datos:
			// applet.setNumPaises(numPaises);
		} catch (AxisFault e) {
			// applet.mostrarError(e.getMessage(),"Error en el sistema: ");
			e.printStackTrace();
		} catch (RemoteException e) {
			// applet.mostrarError(e.getMessage(),"Error en el sistema: ");
			e.printStackTrace();
		}
		return numPaises;
	}

	/**
	 * Metodo que obtiene el numero total de indicadores.
	 */
	public void obtenerNumIndicadores() {

		ObtenerIndicadores obtenerIndicadores = new ObtenerIndicadores();
		// En este caso nos da igual el primer registro y el límite
		obtenerIndicadores.setPrimerRegistro(1);
		obtenerIndicadores.setLimite(1);
		try {
			HealthWorldBankWsStub stub = newStub();
			ObtenerIndicadoresResponse obtenerIndicadoresResponse = stub
					.obtenerIndicadores(obtenerIndicadores);

			// Comprobamos si ha habido algún error:
			String error = obtenerIndicadoresResponse.getError();
			if (error != null) {
				// applet.mostrarError(error,"Error en servicio web: ");
				return;
			}

			// Obtenemos los datos:
			int numIndicadores = obtenerIndicadoresResponse.getTotalRegistros();

			// Mostramos los datos:
			// applet.setNumIndicadores(numIndicadores);

		} catch (AxisFault e) {
			// applet.mostrarError(e.getMessage(),"Error en el sistema: ");
			e.printStackTrace();
		} catch (RemoteException e) {
			// applet.mostrarError(e.getMessage(),"Error en el sistema: ");
			e.printStackTrace();
		}
	}

	/**
	 * Metodo que obtiene el numero total de estadisticas.
	 */
	public void obtenerNumResultados(String codPais, String codIndicador) {

		ObtenerEstadisticas obtenerEstadisticas = new ObtenerEstadisticas();
		// En este caso nos da igual el primer registro y el límite
		obtenerEstadisticas.setPrimerRegistro(1);
		obtenerEstadisticas.setLimite(1);
		obtenerEstadisticas.setCodigoIndicador(codIndicador);
		obtenerEstadisticas.setCodigoPais(codPais);
		try {
			HealthWorldBankWsStub stub = newStub();
			ObtenerEstadisticasResponse obtenerEstadisticasResponse = stub
					.obtenerEstadisticas(obtenerEstadisticas);

			// Comprobamos si ha habido algún error:
			String error = obtenerEstadisticasResponse.getError();
			if (error != null) {
				// applet.mostrarError(error,"Error en servicio web: ");
				return;
			}

			// Obtenemos los datos:
			int numResultados = obtenerEstadisticasResponse.getTotalRegistros();

			// Mostramos los datos:
			// applet.setNumResultados(numResultados);

		} catch (AxisFault e) {
			// applet.mostrarError(e.getMessage(),"Error en el sistema: ");
			e.printStackTrace();
		} catch (RemoteException e) {
			// applet.mostrarError(e.getMessage(),"Error en el sistema: ");
			e.printStackTrace();
		}
	}

	public static Pais obtenerPais(PaisType paisType) {
		Pais pais = null;
		pais = new Pais(paisType.getCodigo(), paisType.getNombre());
		return pais;
	}

	public static Indicador obtenerIndicador(IndicadorType indicadorType) {
		Indicador indicador = null;
		indicador = new Indicador(indicadorType.getCodigo(),
				indicadorType.getNombre(), indicadorType.getNota(),
				indicadorType.getOrganizacion());
		return indicador;
	}

	public static Estadistica obtenerEstadistica(EstadisticaType estadisticaType) {
		Estadistica estadistica = null;
		estadistica = new Estadistica(estadisticaType.getCodigoIndicador(),
				estadisticaType.getCodigoPais(), estadisticaType.getAno(),
				estadisticaType.getDato());
		return estadistica;
	}

	public static HealthWorldBankWsStub newStub() throws AxisFault {
		return new HealthWorldBankWsStub();
		// return new
		// HealthWorldBankWsStub("http://localhost:8080/HealthWorldBankWsServidor/services/HealthWorldBankWs/");
		// return new HealthWorldBankWsStub(configurationContext,
		// targetEndpoint, useSeparateListener);

	}
}
