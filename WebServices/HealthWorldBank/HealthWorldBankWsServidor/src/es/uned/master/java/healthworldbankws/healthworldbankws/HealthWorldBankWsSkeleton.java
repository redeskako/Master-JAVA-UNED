
/**
 * HealthWorldBankWsSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */
package es.uned.master.java.healthworldbankws.healthworldbankws;

import java.sql.SQLException;
import java.util.ArrayList;

import es.uned.master.java.healthworldbank.comunicacion.Pregunta;
import es.uned.master.java.healthworldbank.comunicacion.Respuesta;
import es.uned.master.java.healthworldbank.comunicacion.TipoPeticion;
import es.uned.master.java.healthworldbank.datos.ErrorHWB;
import es.uned.master.java.healthworldbank.datos.Registro;
import es.uned.master.java.healthworldbankws.infraestructura.DatosHealthWorldBank;
import es.uned.master.java.healthworldbankws.conversor.ConversorTiposServidor;

/**
 *  HealthWorldBankWsSkeleton java skeleton for the axisService
 */
public class HealthWorldBankWsSkeleton implements HealthWorldBankWsSkeletonInterface{


	/**
	 * Auto generated method signature
	 * 
	 * @param obtenerEstadisticas0 
	 * @return obtenerEstadisticasResponse1 
	 */

	public es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerEstadisticasResponse obtenerEstadisticas
	(
			es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerEstadisticas obtenerEstadisticas0
			)
	{

		Pregunta pregunta = ConversorTiposServidor.obtenerPregunta(obtenerEstadisticas0);
		DatosHealthWorldBank datosHWB = new DatosHealthWorldBank(pregunta.getTipoPeticion(), pregunta);
		Respuesta respuesta = null;
		try {
			respuesta = datosHWB.getObtenerDatos();
		} catch (SQLException e) {
			//Imprimimos la excepcion, y hacemos una respuesta que contenga el error
			e.printStackTrace();
			ArrayList<Registro> datos = new ArrayList<Registro>(1);
			datos.add(new ErrorHWB(e.getLocalizedMessage()));
			respuesta = new Respuesta(TipoPeticion.ERROR,0,1,1,datos);
		} catch (Exception e) {
			//Imprimimos la excepcion, y hacemos una respuesta que contenga el error
			e.printStackTrace();
			ArrayList<Registro> datos = new ArrayList<Registro>(1);
			datos.add(new ErrorHWB(e.getLocalizedMessage()));
			respuesta = new Respuesta(TipoPeticion.ERROR,0,1,1,datos);
		} 
		return ConversorTiposServidor.obtenerEstadisticasResponse(respuesta);
		//throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#obtenerEstadisticas");
	}


	/**
	 * Auto generated method signature
	 * 
	 * @param obtenerIndicadores2 
	 * @return obtenerIndicadoresResponse3 
	 */

	public es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerIndicadoresResponse obtenerIndicadores
	(
			es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerIndicadores obtenerIndicadores2
			)
	{
		Pregunta pregunta = ConversorTiposServidor.obtenerPregunta(obtenerIndicadores2);
		DatosHealthWorldBank datosHWB = new DatosHealthWorldBank(pregunta.getTipoPeticion(), pregunta);
		Respuesta respuesta = null;
		try {
			respuesta = datosHWB.getObtenerDatos();
		} catch (SQLException e) {
			//Imprimimos la excepcion, y hacemos una respuesta que contenga el error
			e.printStackTrace();
			ArrayList<Registro> datos = new ArrayList<Registro>(1);
			datos.add(new ErrorHWB(e.getLocalizedMessage()));
			respuesta = new Respuesta(TipoPeticion.ERROR,0,1,1,datos);
		} catch (Exception e) {
			//Imprimimos la excepcion, y hacemos una respuesta que contenga el error
			e.printStackTrace();
			ArrayList<Registro> datos = new ArrayList<Registro>(1);
			datos.add(new ErrorHWB(e.getLocalizedMessage()));
			respuesta = new Respuesta(TipoPeticion.ERROR,0,1,1,datos);
		} 
		return ConversorTiposServidor.obtenerIndicadoresResponse(respuesta);
		//throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#obtenerIndicadores");
	}


	/**
	 * Auto generated method signature
	 * 
	 * @param obtenerPaises4 
	 * @return obtenerPaisesResponse5 
	 */

	public es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerPaisesResponse obtenerPaises
	(
			es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerPaises obtenerPaises4
			)
	{
		Pregunta pregunta = ConversorTiposServidor.obtenerPregunta(obtenerPaises4);
		DatosHealthWorldBank datosHWB = new DatosHealthWorldBank(pregunta.getTipoPeticion(), pregunta);
		Respuesta respuesta = null;
		try {
			respuesta = datosHWB.getObtenerDatos();
		} catch (SQLException e) {
			//Imprimimos la excepcion, y hacemos una respuesta que contenga el error
			e.printStackTrace();
			ArrayList<Registro> datos = new ArrayList<Registro>(1);
			datos.add(new ErrorHWB(e.getLocalizedMessage()));
			respuesta = new Respuesta(TipoPeticion.ERROR,0,1,1,datos);
		} catch (Exception e) {
			//Imprimimos la excepcion, y hacemos una respuesta que contenga el error
			e.printStackTrace();
			ArrayList<Registro> datos = new ArrayList<Registro>(1);
			datos.add(new ErrorHWB(e.getLocalizedMessage()));
			respuesta = new Respuesta(TipoPeticion.ERROR,0,1,1,datos);
		} 
		return ConversorTiposServidor.obtenerPaisesResponse(respuesta);
		//throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#obtenerPaises");
	}

}
