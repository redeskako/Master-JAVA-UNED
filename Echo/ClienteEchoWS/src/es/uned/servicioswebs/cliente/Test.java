package es.uned.servicioswebs.cliente;

import java.rmi.RemoteException;

import org.apache.axis2.AxisFault;

import es.uned.servicioswebs.EchoServiceStub;

/**
 * <p>
 * Test.java <br/> Clase que prueba la invocacion a nuestro web service de echo
 * </p>
 * 
 * 
 * @author Ivan Garcia Puebla - www.autentia.com
 * @version 1.0
 */

public class Test {

	/**
	 * Metodo principal de la clase
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		/*
		 * Utilizamos el stub generado a partir del wsdl que logran establecer
		 * la conexion con el web service proveedor.
		 */
		EchoServiceStub customer = null;
		EchoServiceStub.Saludar request = null;
		EchoServiceStub.SaludarResponse response = null;

		try {

			// creamos el soporte y la peticion
			customer = new EchoServiceStub();
			request = new EchoServiceStub.Saludar();

			// establecemos el parametro de la invocacion
			request.setNombre("Eva");

			// invocamos al web service
			response = customer.saludar(request);

			// mostramos la respuesta
			System.out.println(response.get_return());

		} catch (RemoteException excepcionDeInvocacion) {
			System.err.println(excepcionDeInvocacion.toString());
		}

	}
}