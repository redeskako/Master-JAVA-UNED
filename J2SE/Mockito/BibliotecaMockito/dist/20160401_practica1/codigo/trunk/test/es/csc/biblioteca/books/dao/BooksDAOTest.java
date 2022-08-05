/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.csc.biblioteca.books.dao;
import es.csc.biblioteca.books.dao.BooksDAOImpl;
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
 * @author Ignacio
 */
public class BooksDAOTest {
    private static Connection connection;
    
     public BooksDAOTest() {
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
        final InputStream is = BooksDAOTest.class.getResourceAsStream(file);
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
    private static BookDTO getIdLibro() {
        BookDTO libro = null;
        //SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            libro = new BookDTO();
            libro.setIdLibro(99);
            libro.setNombre("Ignacio");
            libro.setAutor("Mockito_Prueba");
            libro.setTema("Pruebas Mockito");
        } catch (Exception ex) {
        }
        return libro;
    }
    
    @BeforeClass
    public static void setUpClass() throws Exception {
        Class.forName("org.hsqldb.jdbc.JDBCDriver");
        BooksDAOTest.connection = getConnection();
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
     * Test of insert method, of class BooksDAOImpl.
     */
    @Test
    public void testInsert() throws Exception {
        int expResult = 11;
        BooksDAOImpl instance = new BooksDAOImpl(connection);
        BookDTO dto = getIdLibro();
        int result = instance.insert(dto);
        assertEquals(expResult, result);
    }

    /**
     * Test of update method, of class BooksDAOImpl.
     */
    @Test
    public void testUpdate() throws Exception {
        int idLibro = 99;
        BooksDAOImpl instance = new BooksDAOImpl(connection);
        BookDTO dto = getIdLibro();
        instance.update(idLibro, dto);
        dto.setIdLibro(idLibro);
        BookDTO expResult = instance.findByPrimaryKey(idLibro);
        assertEquals(dto, expResult);
    }

    /**
     * Test of delete method, of class BooksDAOImpl.
     */
    @Test
    public void testDelete() throws Exception {
        int idLibro = 99;
        BooksDAOImpl instance = new BooksDAOImpl(connection);
        instance.delete(idLibro);
        BookDTO result = instance.findByPrimaryKey(idLibro);
        assertNull(result);
    }

    /**
     * Test of findAll method, of class BooksDAOImpl.
     */
    @Test
    public void testFindAll() throws Exception {
        BooksDAOImpl instance = new BooksDAOImpl(connection);
        List<BookDTO> result = instance.findAll();
        assertTrue(!result.isEmpty());
    }

    /**
     * Test of findByPrimaryKey method, of class BooksDAOImpl.
     */
    @Test
    public void testFindByPrimaryKey() throws Exception {
        int IdLibro = 99;
        BooksDAOImpl instance = new BooksDAOImpl(connection);
        BookDTO result = instance.findByPrimaryKey(IdLibro);
        assertEquals(IdLibro, result.getIdLibro());
    }

}
