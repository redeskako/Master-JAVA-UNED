

/**
 * HealthWorldBankWsTest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */
package es.uned.master.java.healthworldbankws.healthworldbankws;

/*
 *  HealthWorldBankWsTest Junit test case
 */

public class HealthWorldBankWsTest extends junit.framework.TestCase{


	/**
	 * Auto generated test method
	 */
	public  void testobtenerEstadisticas() throws java.lang.Exception{

		es.uned.master.java.healthworldbankws.healthworldbankws.HealthWorldBankWsStub stub =
				new es.uned.master.java.healthworldbankws.healthworldbankws.HealthWorldBankWsStub();//the default implementation should point to the right endpoint

		es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerEstadisticas obtenerEstadisticas18=
				(es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerEstadisticas)getTestObject(es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerEstadisticas.class);
		// Fill in the obtenerEstadisticas18 here
		obtenerEstadisticas18.setCodigoIndicador("SP.ADO.TFRT");
		obtenerEstadisticas18.setCodigoPais("AFG");
		obtenerEstadisticas18.setLimite(1);
		obtenerEstadisticas18.setPrimerRegistro(1);
		
		ObtenerEstadisticasResponse respuesta = stub.obtenerEstadisticas(obtenerEstadisticas18);
		System.out.println("Total registros: " + respuesta.getTotalRegistros());
		assertNotNull(respuesta);




	}

	/**
	 * Auto generated test method
	 */
	public  void testStartobtenerEstadisticas() throws java.lang.Exception{
		es.uned.master.java.healthworldbankws.healthworldbankws.HealthWorldBankWsStub stub = new es.uned.master.java.healthworldbankws.healthworldbankws.HealthWorldBankWsStub();
		es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerEstadisticas obtenerEstadisticas18=
				(es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerEstadisticas)getTestObject(es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerEstadisticas.class);
		// Fill in the obtenerEstadisticas18 here
		obtenerEstadisticas18.setCodigoIndicador("A");
		obtenerEstadisticas18.setCodigoPais("A");
		obtenerEstadisticas18.setLimite(1);
		obtenerEstadisticas18.setPrimerRegistro(1);

		stub.startobtenerEstadisticas(
				obtenerEstadisticas18,
				new tempCallbackN65548()
				);



	}

	private class tempCallbackN65548  extends es.uned.master.java.healthworldbankws.healthworldbankws.HealthWorldBankWsCallbackHandler{
		public tempCallbackN65548(){ super(null);}

		public void receiveResultobtenerEstadisticas(
				es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerEstadisticasResponse result
				) {

		}

		public void receiveErrorobtenerEstadisticas(java.lang.Exception e) {
			fail();
		}

	}

	/**
	 * Auto generated test method
	 */
	public  void testobtenerIndicadores() throws java.lang.Exception{

		es.uned.master.java.healthworldbankws.healthworldbankws.HealthWorldBankWsStub stub =
				new es.uned.master.java.healthworldbankws.healthworldbankws.HealthWorldBankWsStub();//the default implementation should point to the right endpoint

		es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerIndicadores obtenerIndicadores20=
				(es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerIndicadores)getTestObject(es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerIndicadores.class);
		// Fill in the obtenerIndicadores20 here
		obtenerIndicadores20.setLimite(1);
		obtenerIndicadores20.setPrimerRegistro(1);
		
		assertNotNull(stub.obtenerIndicadores(
				obtenerIndicadores20));




	}

	/**
	 * Auto generated test method
	 */
	public  void testStartobtenerIndicadores() throws java.lang.Exception{
		es.uned.master.java.healthworldbankws.healthworldbankws.HealthWorldBankWsStub stub = new es.uned.master.java.healthworldbankws.healthworldbankws.HealthWorldBankWsStub();
		es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerIndicadores obtenerIndicadores20=
				(es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerIndicadores)getTestObject(es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerIndicadores.class);
		// Fill in the obtenerIndicadores20 here
		obtenerIndicadores20.setLimite(1);
		obtenerIndicadores20.setPrimerRegistro(1);

		stub.startobtenerIndicadores(
				obtenerIndicadores20,
				new tempCallbackN65589()
				);



	}

	private class tempCallbackN65589  extends es.uned.master.java.healthworldbankws.healthworldbankws.HealthWorldBankWsCallbackHandler{
		public tempCallbackN65589(){ super(null);}

		public void receiveResultobtenerIndicadores(
				es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerIndicadoresResponse result
				) {

		}

		public void receiveErrorobtenerIndicadores(java.lang.Exception e) {
			fail();
		}

	}

	/**
	 * Auto generated test method
	 */
	public  void testobtenerPaises() throws java.lang.Exception{

		es.uned.master.java.healthworldbankws.healthworldbankws.HealthWorldBankWsStub stub =
				new es.uned.master.java.healthworldbankws.healthworldbankws.HealthWorldBankWsStub();//the default implementation should point to the right endpoint

		es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerPaises obtenerPaises22=
				(es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerPaises)getTestObject(es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerPaises.class);
		// Fill in the obtenerPaises22 here
		obtenerPaises22.setLimite(1);
		obtenerPaises22.setPrimerRegistro(1);
		
		assertNotNull(stub.obtenerPaises(
				obtenerPaises22));




	}

	/**
	 * Auto generated test method
	 */
	public  void testStartobtenerPaises() throws java.lang.Exception{
		es.uned.master.java.healthworldbankws.healthworldbankws.HealthWorldBankWsStub stub = new es.uned.master.java.healthworldbankws.healthworldbankws.HealthWorldBankWsStub();
		es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerPaises obtenerPaises22=
				(es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerPaises)getTestObject(es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerPaises.class);
		// Fill in the obtenerPaises22 here
		obtenerPaises22.setLimite(1);
		obtenerPaises22.setPrimerRegistro(1);

		stub.startobtenerPaises(
				obtenerPaises22,
				new tempCallbackN65630()
				);



	}

	private class tempCallbackN65630  extends es.uned.master.java.healthworldbankws.healthworldbankws.HealthWorldBankWsCallbackHandler{
		public tempCallbackN65630(){ super(null);}

		public void receiveResultobtenerPaises(
				es.uned.master.java.healthworldbankws.healthworldbankws.ObtenerPaisesResponse result
				) {

		}

		public void receiveErrorobtenerPaises(java.lang.Exception e) {
			fail();
		}

	}

	//Create an ADBBean and provide it as the test object
	public org.apache.axis2.databinding.ADBBean getTestObject(java.lang.Class type) throws java.lang.Exception{
		return (org.apache.axis2.databinding.ADBBean) type.newInstance();
	}




}
