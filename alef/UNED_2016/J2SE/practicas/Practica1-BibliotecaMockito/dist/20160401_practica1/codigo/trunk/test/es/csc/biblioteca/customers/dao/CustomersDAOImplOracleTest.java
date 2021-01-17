/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.csc.biblioteca.customers.dao;

import es.csc.biblioteca.jdbc.UtilsOracle;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
/**
 *
 * @author alber
 */

@RunWith(PowerMockRunner.class)
public class CustomersDAOImplOracleTest {
    
    private static Connection mockedConnection ;
    
    public CustomersDAOImplOracleTest() {
    }
    
    @BeforeClass
    public static void setUpClass() throws SQLException {
        mockedConnection = mock(Connection.class);
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of insert method, of class CustomersDAOImplOracle.
     * @throws java.lang.Exception
     */
    @Test
    @PrepareForTest(UtilsOracle.class)      
    public void testGeneraSecuencia() throws Exception {
        int expResult = 999;
        PowerMockito.mockStatic(UtilsOracle.class);
        Mockito.when(UtilsOracle.generaSecuencia(this.mockedConnection, "SEQ_SOCIO")).thenReturn(999);
        int result=UtilsOracle.generaSecuencia(this.mockedConnection,"SEQ_SOCIO");
        assertEquals(expResult, result);
        System.out.println("El resultado esperado es el correcto: "+ expResult);
    
        PowerMockito.verifyStatic(times(1));
        UtilsOracle.generaSecuencia(this.mockedConnection,"SEQ_SOCIO");
        System.out.println("Se accede una vez a traves del metodo UtilsOracle.generaSecuencia");
    }

    
    
}
