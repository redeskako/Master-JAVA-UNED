package es.uned.master.java.healthworldbank.comunicacion.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import es.uned.master.java.healthworldbank.comunicacion.Pregunta;
import es.uned.master.java.healthworldbank.comunicacion.Respuesta;
import es.uned.master.java.healthworldbank.comunicacion.TipoPeticion;
import es.uned.master.java.healthworldbank.datos.Estadistica;
import es.uned.master.java.healthworldbank.datos.Indicador;
import es.uned.master.java.healthworldbank.datos.Pais;
import es.uned.master.java.healthworldbank.datos.Registro;

/**
 * Cliente ligero que comprueba la comunicación con el servidor y la base de datos
 * utilizando las clases definidas para intercambio de información
 * 
 * Comprobación de errores mínima
 * 
 * @author grupo alef (Juan Sánchez)
 *
 */
public class TestDatosCliente {
	public static final int PUERTO = 10088;

	private InputStream entradaStream;
	private OutputStream salidaStream;
	private ObjectInputStream entradaObject;
	private ObjectOutputStream salidaObject;
	private Socket cliente;

		
	/**
	 * Envía al servidor una petición de un tipo determinado
	 * y procesa la respuesta
	 *  
	 * @param tipo de petición
	 */
	private void obtener(TipoPeticion tipo){
		try {
			cliente = new Socket("localhost",PUERTO);
			
			System.out.println("Abierto cliente: " + cliente);
			// obtenemos los flujos del cliente
			entradaStream = cliente.getInputStream();
			salidaStream = cliente.getOutputStream();
			
			// obtenemos los flujos de objetos
			salidaObject = new ObjectOutputStream (salidaStream);
			

			salidaObject.writeObject(new Pregunta(tipo, 10, 11));
			//cliente.setSoLinger(true, 1000);
			System.out.println("Enviado objeto Pregunta");
			entradaObject = new ObjectInputStream (entradaStream);
			Respuesta rsp; 
			rsp = (Respuesta) entradaObject.readObject();
			if (rsp == null) {
				System.out.println("El servidor no devolvió ningún dato");
			} else {
				switch (tipo) {
				case PAISES:
					muestraRespuestaPaises(rsp);
					break;
				case INDICADORES:
					muestraRespuestaIndicadores(rsp);
					break;
				case ESTADISTICAS:
					muestraRespuestaEstadisticas(rsp);
					break;
					
				}
			}
		} catch (SocketTimeoutException e) {
			//Esto significa que no hemos recibido una conexión entrante en 'timeoutCorto' milisegundos
			//Simplemente ignoro la excepción y sigo esperando nuevas conexiones
			System.err.println("SocketTimeoutException. " + e.getMessage());
		} catch (ClassNotFoundException cnf) {
			System.err.println("ClassNotFoundException. " + cnf.getMessage());
		} catch (IOException e) {
			System.err.println("IOException. " + e.getMessage());
		} finally {
			try {
				salidaObject.close();
				entradaObject.close();
				salidaStream.close();
				entradaStream.close();
			} catch (IOException e) {
				System.err.println("IOException. " + e.getMessage());
			}
		}
	}
	
	/** 
	 * Punto de entrada a la aplicación
	 * Crea instancia de clase y hace tres peticiones de tipos distintod
	 * @param args
	 */
	public static void main(String[] args) {
		TestDatosCliente test = new TestDatosCliente();
		test.obtener(TipoPeticion.PAISES);
		System.out.println("=============================");
		test.obtener(TipoPeticion.INDICADORES);
		System.out.println("=============================");
		test.obtener(TipoPeticion.ESTADISTICAS);
		System.out.println("=============================");
		System.out.println("Fin de TestDatosCliente");
	}

	/**
	 * Muestra en la consola información de países
	 * @param r respuesta del servidor
	 */
	private void muestraRespuestaPaises(Respuesta r) {
		System.out.println("Mostrando paises");
		System.out.println("================");
		for (Registro reg : r.getDatos()) {
			System.out.println(((Pais)reg).getCodigo() + " - " + 
					((Pais)reg).getNombre());
		}
	}
	
	/**
	 * Mustra en la consola información de indicadores
	 * @param r respuesta del servidor
	 */
	private void muestraRespuestaIndicadores(Respuesta r) {
		System.out.println("Mostrando indicadores");
		System.out.println("=====================");		
		for (Registro reg : r.getDatos()) {
			System.out.println(((Indicador)reg).getCodigo() + " - " + 
					((Indicador)reg).getNombre());
		}
	}

	/**
	 * Mustra en la consola información de estadísticas
	 * @param r respuesta del servidor
	 */
	private void muestraRespuestaEstadisticas(Respuesta r) {
		System.out.println("Mostrando datos");
		System.out.println("===============");		
		
		for (Registro reg : r.getDatos()) {
			System.out.println(((Estadistica)reg).getCodigoIndicador() + " - " + 
					((Estadistica)reg).getCodigoPais() + " - " + 
					((Estadistica)reg).getAno() + " - " +
					((Estadistica)reg).getDato());
		}
	}
	
}
