package es.uned.master.java;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

class SumaTest {

	/*
	@Test
	void test() {
		fail("Not yet implemented");
	}
	*/
	
	@Test
	public void SumaTest() {
		Suma s1 = new Suma();
		Suma s2 = new Suma (3,-1);
		
		assertEquals(0, s1.suma());
		assertEquals(2, s2.suma());
	}

}