/*
 * Class Test 
 * @author Francisco Yague
 */
package es.uned.master.java.healthworldbankws.cliente;

import java.rmi.RemoteException;
import org.apache.axis2.AxisFault;
import es.uned.master.java.healthworldbankws.healthworldbankws.HealthWorldBankWsStub;
import es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerEstadisticas;
import es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerEstadisticasResponse;
import es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerIndicadores;
import es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerIndicadoresResponse;
import es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerPaises;
import es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerPaisesResponse;

// TODO: Auto-generated Javadoc
/**
 * The Class Test.
 */
public class Test {

	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
	
		try {
			HealthWorldBankWsStub stub = newStub();
			int primerRegistro=1;
			int limite=1;
			String codIndicador="SP.ADO.TFRT";
			String codPais="AFG";
			
			//Test para paises
			ObtenerPaises obtenerPaises = new ObtenerPaises();
			obtenerPaises.setPrimerRegistro(primerRegistro);
			obtenerPaises.setLimite(limite);
			ObtenerPaisesResponse obtenerPaisesResponse = stub.obtenerPaises(obtenerPaises);
			System.out.println(obtenerPaisesResponse.getPais());
			//Test para Indicadores
			ObtenerIndicadores obtenerIndicadores = new ObtenerIndicadores();
			obtenerIndicadores.setPrimerRegistro(primerRegistro);
			obtenerIndicadores.setLimite(limite);
			ObtenerIndicadoresResponse obtenerIndicadoresResponse = stub.obtenerIndicadores(obtenerIndicadores);
			System.out.println(obtenerIndicadoresResponse.getIndicador());
			//Test para Estadisticas
			ObtenerEstadisticas obtenerEstadisticas = new ObtenerEstadisticas();
			obtenerEstadisticas.setPrimerRegistro(primerRegistro);
			obtenerEstadisticas.setLimite(limite);
			obtenerEstadisticas.setCodigoIndicador(codIndicador);
			obtenerEstadisticas.setCodigoPais(codPais);
			ObtenerEstadisticasResponse obtenerEstadisticasResponse = stub.obtenerEstadisticas(obtenerEstadisticas);
			System.out.println( obtenerEstadisticasResponse.getEstadistica());
			
		} catch (RemoteException excepcionDeInvocacion) {
			System.err.println(excepcionDeInvocacion.toString());
		}
	}
	

	/**
	 * New stub.
	 *
	 * @return the health world bank ws stub
	 * @throws AxisFault the axis fault
	 */
	private static HealthWorldBankWsStub newStub() throws AxisFault {
		return new HealthWorldBankWsStub();
		
	}

}
