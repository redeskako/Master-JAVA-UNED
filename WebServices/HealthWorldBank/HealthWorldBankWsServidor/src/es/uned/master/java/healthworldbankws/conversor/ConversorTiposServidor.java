/**
 * 
 */
package es.uned.master.java.healthworldbankws.conversor;

import es.uned.master.java.healthworldbank.comunicacion.*;
import es.uned.master.java.healthworldbank.datos.ErrorHWB;
import es.uned.master.java.healthworldbank.datos.Estadistica;
import es.uned.master.java.healthworldbank.datos.Indicador;
import es.uned.master.java.healthworldbank.datos.Pais;
import es.uned.master.java.healthworldbank.datos.Registro;
import es.uned.master.java.healthworldbankws.healthworldbankws.EstadisticaType;
import es.uned.master.java.healthworldbankws.healthworldbankws.IndicadorType;
import es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerEstadisticas;
import es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerEstadisticasResponse;
import es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerIndicadores;
import es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerIndicadoresResponse;
import es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerPaises;
import es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerPaisesResponse;
import es.uned.master.java.healthworldbankws.healthworldbankws.PaisType;

/**
 * @author José Torrecilla
 *
 */
public class ConversorTiposServidor {
	
	public static Pregunta obtenerPregunta(ObtenerPaises obtenerPaises) {
		
		return new Pregunta
				(TipoPeticion.PAISES,
				 obtenerPaises.getLimite(),
				 obtenerPaises.getPrimerRegistro());
	}
	
	public static Pregunta obtenerPregunta(ObtenerIndicadores obtenerIndicadores) {
		
		return new Pregunta
				(TipoPeticion.INDICADORES,
				 obtenerIndicadores.getLimite(),
				 obtenerIndicadores.getPrimerRegistro());
	}
	
	public static Pregunta obtenerPregunta(ObtenerEstadisticas obtenerEstadisticas) {
		
		return new Pregunta
				(TipoPeticion.ESTADISTICAS,
				 obtenerEstadisticas.getLimite(),
				 obtenerEstadisticas.getPrimerRegistro(),
				 obtenerEstadisticas.getCodigoPais(),
				 obtenerEstadisticas.getCodigoIndicador());
	}
	
	public static ObtenerPaisesResponse obtenerPaisesResponse(Respuesta respuesta) {
		
		ObtenerPaisesResponse obtenerPaisesResponse = new ObtenerPaisesResponse();
		obtenerPaisesResponse.setPrimerRegistro(respuesta.getPrimerRegistro());
		obtenerPaisesResponse.setLimite(respuesta.getLimite());
		obtenerPaisesResponse.setTotalRegistros(respuesta.getTotalRegistros());
		
		//Vemos si en la respuesta vienen Paises (si ha ido todo bien) o un error
		//También podrían venir cosas que no son países, en cuyo caso también sería un error
		
		if (respuesta.getTipoPeticion().equals(TipoPeticion.PAISES)) {
			//Estamos en el caso "bueno", así que informamos los países obtenidos
			for (Registro reg:respuesta.getDatos()) {
				Pais pais = (Pais)reg;
				obtenerPaisesResponse.addPais(ConversorTiposServidor.obtenerPaisType(pais));
			}
		} else if (respuesta.getTipoPeticion().equals(TipoPeticion.ERROR)) {
			//Estamos en el caso en que hemos obtenido un error al procesar la petición.
			String errorGlobal = "Errores en el servidor procesando petición:\n";
			for (Registro reg:respuesta.getDatos()) {
				ErrorHWB error = (ErrorHWB)reg;
				errorGlobal = errorGlobal + error.getDescripcion() + "\n";
			}
			obtenerPaisesResponse.setError(errorGlobal);
		} else {
			//Estamos en el caso en que estamos recibiendo una respuesta de un tipo que no esperamos.
			obtenerPaisesResponse.setError("Error en servidor: Recibida respuesta de tipo no esperado: " + respuesta.getTipoPeticion());
		}
		
		return obtenerPaisesResponse;
	}
	
	public static PaisType obtenerPaisType(Pais pais) {
		PaisType paisType = new PaisType();
		paisType.setCodigo(pais.getCodigo());
		paisType.setNombre(pais.getNombre());
		return paisType;
	}
	
	public static ObtenerIndicadoresResponse obtenerIndicadoresResponse(Respuesta respuesta) {
		
		ObtenerIndicadoresResponse obtenerIndicadoresResponse = new ObtenerIndicadoresResponse();
		obtenerIndicadoresResponse.setPrimerRegistro(respuesta.getPrimerRegistro());
		obtenerIndicadoresResponse.setLimite(respuesta.getLimite());
		obtenerIndicadoresResponse.setTotalRegistros(respuesta.getTotalRegistros());
		
		//Vemos si en la respuesta vienen indicadores (si ha ido todo bien) o un error
		//También podrían venir cosas que no son indicadores, en cuyo caso también sería un error
		
		if (respuesta.getTipoPeticion().equals(TipoPeticion.INDICADORES)) {
			//Estamos en el caso "bueno", así que informamos los indicadores obtenidos
			for (Registro reg:respuesta.getDatos()) {
				Indicador indicador = (Indicador)reg;
				obtenerIndicadoresResponse.addIndicador(ConversorTiposServidor.obtenerIndicadorType(indicador));
			}
		} else if (respuesta.getTipoPeticion().equals(TipoPeticion.ERROR)) {
			//Estamos en el caso en que hemos obtenido un error al procesar la petición.
			String errorGlobal = "Errores en el servidor procesando petición:\n";
			for (Registro reg:respuesta.getDatos()) {
				ErrorHWB error = (ErrorHWB)reg;
				errorGlobal = errorGlobal + error.getDescripcion() + "\n";
			}
			obtenerIndicadoresResponse.setError(errorGlobal);
		} else {
			//Estamos en el caso en que estamos recibiendo una respuesta de un tipo que no esperamos.
			obtenerIndicadoresResponse.setError("Error en servidor: Recibida respuesta de tipo no esperado: " + respuesta.getTipoPeticion());
		}
		
		return obtenerIndicadoresResponse;
	}
	
	public static IndicadorType obtenerIndicadorType(Indicador indicador) {
		IndicadorType indicadorType = new IndicadorType();
		indicadorType.setCodigo(indicador.getCodigo());
		indicadorType.setNombre(indicador.getNombre());
		indicadorType.setNota(indicador.getNota());
		indicadorType.setOrganizacion(indicador.getNota());
		return indicadorType;
	}	
	
	public static ObtenerEstadisticasResponse obtenerEstadisticasResponse(Respuesta respuesta) {
		
		ObtenerEstadisticasResponse obtenerEstadisticasResponse = new ObtenerEstadisticasResponse();
		obtenerEstadisticasResponse.setPrimerRegistro(respuesta.getPrimerRegistro());
		obtenerEstadisticasResponse.setLimite(respuesta.getLimite());
		obtenerEstadisticasResponse.setTotalRegistros(respuesta.getTotalRegistros());
		
		//Vemos si en la respuesta vienen estadísticas (si ha ido todo bien) o un error
		//También podrían venir cosas que no son estadísticas, en cuyo caso también sería un error
		
		if (respuesta.getTipoPeticion().equals(TipoPeticion.ESTADISTICAS)) {
			//Estamos en el caso "bueno", así que informamos las estadísticas obtenidas
			for (Registro reg:respuesta.getDatos()) {
				Estadistica estadistica = (Estadistica)reg;
				obtenerEstadisticasResponse.addEstadistica(ConversorTiposServidor.obtenerEstadisticaType(estadistica));
			}
		} else if (respuesta.getTipoPeticion().equals(TipoPeticion.ERROR)) {
			//Estamos en el caso en que hemos obtenido un error al procesar la petición.
			String errorGlobal = "Errores en el servidor procesando petición:\n";
			for (Registro reg:respuesta.getDatos()) {
				ErrorHWB error = (ErrorHWB)reg;
				errorGlobal = errorGlobal + error.getDescripcion() + "\n";
			}
			obtenerEstadisticasResponse.setError(errorGlobal);
		} else {
			//Estamos en el caso en que estamos recibiendo una respuesta de un tipo que no esperamos.
			obtenerEstadisticasResponse.setError("Error en servidor: Recibida respuesta de tipo no esperado: " + respuesta.getTipoPeticion());
		}
		
		return obtenerEstadisticasResponse;
	}
	
	public static EstadisticaType obtenerEstadisticaType(Estadistica estadistica) {
		EstadisticaType estadisticaType = new EstadisticaType();
		estadisticaType.setCodigoPais(estadistica.getCodigoPais());
		estadisticaType.setCodigoIndicador(estadistica.getCodigoIndicador());
		estadisticaType.setAno(estadistica.getAno());
		estadisticaType.setDato(estadistica.getDato());
		return estadisticaType;
	}	
}
