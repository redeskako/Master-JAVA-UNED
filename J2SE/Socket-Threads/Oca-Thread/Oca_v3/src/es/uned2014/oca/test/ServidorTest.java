package es.uned2014.oca.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import es.uned2014.oca.drivers.DriverServidor;
import es.uned2014.oca.servidor.*;
import es.uned2014.oca.excepciones.*;

public class ServidorTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}


	int i=10;
	DriverServidor ds = new DriverServidor();
	
	
	@Test(expected=ClaseError.class)
	public void testServidor() {

		Servidor testServidor=new Servidor(ds, i);
	
		
	}

}
