package es.csc.biblioteca.customers.dao;

import es.csc.biblioteca.customers.dao.CustomersDAOImpl;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author David Atencia
 * @version 0.1
 */
public class CustomersDAOTest {
    
    private static Connection connection;
    
    public CustomersDAOTest() {
    }
    
    /**
     * Establece una conexión con la base de datos de pruebas.
     * 
     * @return Devuelve una conexión con la base de datos de pruebas.
     * @throws SQLException 
     */
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:mem:.;shutdown=true", "SA", "");
    }
    
    /**
     * Crea la base de datos de pruebas.
     * 
     * @throws SQLException
     * @throws IOException 
     */
    private static void createDatabase() throws Exception {
        Statement statement = connection.createStatement();

        for (String sql : getSQLStatements("uned_creation_hsqldb.sql").split(";;")) {
            if (sql.length() > 0) {
                try {
                    statement.execute(sql);
                } catch (Exception e) {
                    System.err.println("Failed to execute SQL statement " + sql);
                    throw e;
                }
            }
        }
    }
    
    /**
     * Inserta los datos de pruebas en la base de datos.
     * 
     * @throws Exception 
     */
    private static void populateDatabase() throws Exception {
        Statement statement = connection.createStatement();
        for (String sql : getSQLStatements("uned_inserts_hsqldb.sql").split(";;")) {
            if (sql.length() > 0) {
                try {
                    statement.execute(sql);
                } catch (Exception e) {
                    System.err.println("Failed to execute SQL statement " + sql);
                    throw e;
                }
            }
        }
    }
    
    /**
     * Obtiene las sentencias SQL del fichero indicado.
     * 
     * @param file Nombre del fichero que contiene las sentencias SQL.
     * @return Devuelve una cadena que contiene las setencias SQL del fichero indicado.
     * @throws IOException 
     */
    private static String getSQLStatements(String file) throws IOException {
        String retval = "";
        final byte[] buf = new byte[4096];
        final InputStream is = CustomersDAOTest.class.getResourceAsStream(file);
        while (is.read(buf) > 0) {
            retval += new String(buf);
        }
        return retval.trim();
    }
    
    /**
     * Crea un socio para usar en las pruebas.
     * 
     * @return 
     */
    private static CustomerDTO getSocio() {
        CustomerDTO socio = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            socio = new CustomerDTO();
            socio.setNombre("David");
            socio.setApellidos("Atencia");
            socio.setDireccion("Nerja");
            socio.setDni("53155478V");
            socio.setFechaAlta(new Date(formatter.parse("2015-11-21").getTime()));
        } catch (ParseException ex) {
        }
        return socio;
    }
    
    @BeforeClass
    public static void setUpClass() throws Exception {
        Class.forName("org.hsqldb.jdbc.JDBCDriver");
        CustomersDAOTest.connection = getConnection();
        createDatabase();
    }
    
    @AfterClass
    public static void tearDownClass() throws Exception {
        Statement statement = connection.createStatement();
        statement.execute("SHUTDOWN");
    }
    
    @Before
    public void setUp() throws Exception {
        populateDatabase();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of insert method, of class CustomersDAOImpl.
     */
    @Test
    public void testInsert() throws Exception {
        int expResult = 11;
        CustomersDAOImpl instance = new CustomersDAOImpl(connection);
        CustomerDTO dto = getSocio();
        int result = instance.insert(dto);
        assertEquals(expResult, result);
    }

    /**
     * Test of update method, of class CustomersDAOImpl.
     */
    @Test
    public void testUpdate() throws Exception {
        int idSocio = 10;
        CustomersDAOImpl instance = new CustomersDAOImpl(connection);
        CustomerDTO dto = getSocio();
        instance.update(idSocio, dto);
        dto.setIdSocio(idSocio);
        CustomerDTO expResult = instance.findByPrimaryKey(idSocio);
        assertEquals(dto, expResult);
    }

    /**
     * Test of delete method, of class CustomersDAOImpl.
     */
    @Test
    public void testDelete() throws Exception {
        int idSocio = 10;
        CustomersDAOImpl instance = new CustomersDAOImpl(connection);
        instance.delete(idSocio);
        CustomerDTO result = instance.findByPrimaryKey(idSocio);
        assertNull(result);
    }

    /**
     * Test of findAll method, of class CustomersDAOImpl.
     */
    @Test
    public void testFindAll() throws Exception {
        CustomersDAOImpl instance = new CustomersDAOImpl(connection);
        List<CustomerDTO> result = instance.findAll();
        assertTrue(!result.isEmpty());
    }

    /**
     * Test of findByPrimaryKey method, of class CustomersDAOImpl.
     */
    @Test
    public void testFindByPrimaryKey() throws Exception {
        int idSocio = 1;
        CustomersDAOImpl instance = new CustomersDAOImpl(connection);
        CustomerDTO result = instance.findByPrimaryKey(idSocio);
        assertEquals(idSocio, result.getIdSocio());
    }

}
