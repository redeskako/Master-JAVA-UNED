package es.csc.biblioteca.loans.dao;

//imc
import es.csc.biblioteca.customers.dao.CustomerDTO;
import es.csc.biblioteca.books.dao.BookDTO;

import es.csc.biblioteca.loans.dao.LoansDAOImpl;
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
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author mcfly--imc
 */
public class LoansDAOTest {
    private static Connection connection;
    
    public LoansDAOTest(){
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
        //imc
        final InputStream is = LoansDAOTest.class.getResourceAsStream(file);
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
    
   private static LoanDTO getPrestamo() {
       LoanDTO prestamo = null;
       SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
       
       try {
           prestamo = new LoanDTO();
         
           prestamo.setFechaInicio(new Date(formatter.parse("2000-01-01").getTime()));
           prestamo.setFechaFin(new Date(formatter.parse("2015-11-21").getTime()));
           
           //llamada al metodo costumer para introducir valores de prueba
           CustomerDTO custo_loan = new CustomerDTO();
           custo_loan.setNombre("Luis adfd ");
           custo_loan.setApellidos("Fernandez Najera");
           custo_loan.setDireccion("Pico Urbion DFS");
           custo_loan.setDni("56456454D");
           //custo_loan.setDni("11111111C");
           custo_loan.setFechaAlta(new Date(formatter.parse("2004-05-05").getTime()));
           custo_loan.setIdSocio(1);
           prestamo.setCustomer(custo_loan);
       
           
           //llamada al metodo book para introducir valores de prueba
           BookDTO book_loan = new BookDTO();
           book_loan.setAutor("Alfredo Ruiz Roldan");
           book_loan.setNombre("Ingenieria Informatica");
           book_loan.setTema("Informatica sdaf");
           book_loan.setIdLibro(1);//??????????????????????????
           prestamo.setBook(book_loan);
           
           //System.out.println(prestamo.toString());
           return prestamo;     
                             
       } catch (ParseException ex) {
       }
       return prestamo;
   }
    
   /**
    * Conexion LoansDAOTest
    * crea BBDD hsqldb
    * @throws Exception 
    */
   
   @BeforeClass
    public static void setUpClass() throws Exception {
        Class.forName("org.hsqldb.jdbc.JDBCDriver");
        LoansDAOTest.connection = getConnection();
        createDatabase();    
    } 

    /**
    * 
    * Deconexion BBDD hsqldb
    * @throws Exception 
    */
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
     * Test of insert method, of class LoansDAOImpl 
     * imc 14-03-2016
     */
    @Test
    public void testInsert() throws Exception {
        //resultado esperado
        //int expResult = 14;
        int expResult = 14;
        
        //Conexion Loan
        LoansDAOImpl instance = new LoansDAOImpl(connection);
        
        //comparativa de los resultados
        LoanDTO dto = getPrestamo();
        int result = instance.insert(dto);            
        assertEquals(expResult, result);
        
               
    }

    /**
     * Test of update method, of class LoansDAOImpl
     * imc 14-03-2016
     */
    @Test
    public void testUpdate() throws Exception {
        //id ultimo registro Loan 
        int idPrestamo = 13;
        //En BBDD hsqldb hay 10 registros de prestamos
               
        LoansDAOImpl instance = new LoansDAOImpl(connection);
        LoanDTO dto = getPrestamo();
        
        instance.update(idPrestamo,dto);
        System.out.println("Uno--el valor de idPrestamo es:" + dto.getIdPrestamo());
        System.out.println(dto);
        System.out.println("");
        
        
        dto.setIdPrestamo(idPrestamo);
        System.out.println("dos--dto.setIdPrestamo(idPrestamo) el valor de idPrestamo ahora es:" + dto.getIdPrestamo());
        System.out.println(dto);
        System.out.println("");
        
        
        
        /**Recupera de la base de datos el prestamo indicado por su clave primaria.
     * 
     * @param idPrestamo Clave primaria del prestamo.
     * @return Devuelve los datos del prestamo encontrado.
     * @throws LoansDAOException 
     * */
        LoanDTO expResult = instance.findByPrimaryKey(idPrestamo);
       
        System.out.println("tres---" + instance.findByPrimaryKey(idPrestamo).toString());
        //System.out.println("el valor de idprestamo es:" + expResult.getIdPrestamo());
        
        System.out.println(dto);
        System.out.println(expResult);
        
        assertEquals(dto, expResult);
    }

    /**
     * Test of delete method, of class LoansDAOImpl
     * imc 14-03-2016
     */
    @Test
    public void testDelete() throws Exception {
        int idPrestamo = 13;
        //int idPrestamo = 11;
        LoansDAOImpl instance = new LoansDAOImpl(connection);
        instance.delete(idPrestamo);
        LoanDTO result = instance.findByPrimaryKey(idPrestamo);
        assertNull(result);
    }

    /**
     * Test of findAll method, of class LoansDAOImpl
     * imc 14-03-2016
     */
    @Test
    public void testFindAll() throws Exception {
        LoansDAOImpl instance = new LoansDAOImpl(connection);
        List<LoanDTO> result = instance.findAll();
        assertTrue(!result.isEmpty());
    }

    /**
     * Test of findByPrimaryKey method, of class LoansDAOImpl
     * imc 14-03-2016
     */
    @Test
    public void testFindByPrimaryKey() throws Exception {
        int idPrestamo = 1;
        LoansDAOImpl instance = new LoansDAOImpl(connection);
        LoanDTO result = instance.findByPrimaryKey(idPrestamo);
        assertEquals(idPrestamo, result.getIdPrestamo());
    }

}

