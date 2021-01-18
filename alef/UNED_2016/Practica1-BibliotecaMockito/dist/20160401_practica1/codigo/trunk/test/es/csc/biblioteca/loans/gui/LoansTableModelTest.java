package es.csc.biblioteca.loans.gui;

import es.csc.biblioteca.customers.dao.CustomerDTO;
import es.csc.biblioteca.customers.dao.CustomersDAOException;
import es.csc.biblioteca.customers.dao.ICustomersDAO;
import es.csc.biblioteca.books.dao.BookDTO;
import es.csc.biblioteca.books.dao.BooksDAOException;
import es.csc.biblioteca.books.dao.IBooksDAO;
import es.csc.biblioteca.loans.dao.LoanDTO;
import es.csc.biblioteca.loans.dao.LoansDAOException;
import es.csc.biblioteca.loans.dao.ILoansDAO;
import java.sql.Date;

import java.util.ArrayList;
import java.util.List;
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
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author David Atencia
 * @version 0.1
 */
@RunWith(MockitoJUnitRunner.class)  
public class LoansTableModelTest {

    private static ILoansDAO mockedLoansDAO;
    private static LoansTableModel instance;
    
    public LoansTableModelTest() {
    }
    
    @BeforeClass
    public static void setUpClass() throws LoansDAOException {
        // Create mock object of ILoansDAO
        mockedLoansDAO = mock(ILoansDAO.class);
        when(mockedLoansDAO.findAll()).thenReturn(new ArrayList<LoanDTO>());
        when(mockedLoansDAO.insert((LoanDTO) anyObject())).thenReturn(1);
        doNothing().when(mockedLoansDAO).delete(anyInt());
        when(mockedLoansDAO.findByPrimaryKey(anyInt())).thenReturn(null);
        doNothing().when(mockedLoansDAO).update(anyInt(), (LoanDTO) anyObject());
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        List<LoanDTO> al = new ArrayList<LoanDTO>();
                
        for(int i = 1; i <= 10; i++) {
            LoanDTO dto = new LoanDTO();
            CustomerDTO cust = new CustomerDTO();
            BookDTO book = new BookDTO();
            
            dto.setIdPrestamo(i);
            dto.setFechaInicio(new Date(Date.valueOf("2016-03-20").getTime()+i));
            dto.setFechaFin(new Date(Date.valueOf("2016-03-28").getTime()+i));
            
            cust.setIdSocio(i);
            cust.setNombre("Nombre_" + i);
            cust.setApellidos("Apellidos_" + i);
            cust.setDireccion("Dirección_" + i);
            cust.setDni("DNI_" + i);
            cust.setFechaAlta(new Date(Date.valueOf("2015-03-28").getTime()+i));
            
            book.setIdLibro(1);////????????
            book.setAutor("Autor"+i);
            book.setNombre("Título"+i);
            
            dto.setBook(book);
            dto.setCustomer(cust);
            al.add(dto);
        }
        // Create objet to test
        instance = new LoansTableModel(mockedLoansDAO, al);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getRowCount method, of class LoansTableModel.
     * @throws es.csc.biblioteca.loans.dao.LoansDAOException
     */
    @Test
    public void testGetRowCount() throws LoansDAOException {
        System.out.println("getRowCount");
        int expResult = 10;
        int result = instance.getRowCount();
        assertEquals(expResult, result);
    }

    /**
     * Test of getColumnCount method, of class LoansTableModel.
     */
    @Test
    public void testGetColumnCount() {
        System.out.println("getColumnCount");
        int expResult = 6;
        int result = instance.getColumnCount();
        assertEquals(expResult, result);
    }

//    /**
//     * Test of isCellEditable method, of class LoansTableModel.
//     */
//    @Test
//    public void testIsCellEditable() {
//        System.out.println("isCellEditable");
//        int rowIndex = 0;
//        int columnIndex = 0;
//        CustomersTableModel instance = null;
//        boolean expResult = false;
//        boolean result = instance.isCellEditable(rowIndex, columnIndex);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getColumnClass method, of class CustomersTableModel.
//     */
//    @Test
//    public void testGetColumnClass() {
//        System.out.println("getColumnClass");
//        int columnIndex = 0;
//        CustomersTableModel instance = null;
//        Class expResult = null;
//        Class result = instance.getColumnClass(columnIndex);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getColumnName method, of class CustomersTableModel.
//     */
//    @Test
//    public void testGetColumnName() {
//        System.out.println("getColumnName");
//        int column = 0;
//        CustomersTableModel instance = null;
//        String expResult = "";
//        String result = instance.getColumnName(column);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getValueAt method, of class CustomersTableModel.
//     */
//    @Test
//    public void testGetValueAt() {
//        System.out.println("getValueAt");
//        int rowIndex = 0;
//        int columnIndex = 0;
//        CustomersTableModel instance = null;
//        Object expResult = null;
//        Object result = instance.getValueAt(rowIndex, columnIndex);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setValueAt method, of class CustomersTableModel.
//     */
//    @Test
//    public void testSetValueAt() {
//        System.out.println("setValueAt");
//        Object aValue = null;
//        int rowIndex = 0;
//        int columnIndex = 0;
//        CustomersTableModel instance = null;
//        instance.setValueAt(aValue, rowIndex, columnIndex);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getCustomerAt method, of class CustomersTableModel.
//     */
//    @Test
//    public void testGetCustomerAt() {
//        System.out.println("getCustomerAt");
//        int rowIndex = 0;
//        CustomersTableModel instance = null;
//        CustomerDTO expResult = null;
//        CustomerDTO result = instance.getCustomerAt(rowIndex);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setCustomerAt method, of class CustomersTableModel.
//     */
//    @Test
//    public void testSetCustomerAt() {
//        System.out.println("setCustomerAt");
//        CustomerDTO dto = null;
//        int rowIndex = 0;
//        CustomersTableModel instance = null;
//        instance.setCustomerAt(dto, rowIndex);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of addLoan method, of class LoansTableModel.
     */
    @Test
    public void testAddLoan() throws LoansDAOException {
        System.out.println("addCustomer");
        
        LoanDTO dto = new LoanDTO();
        CustomerDTO cust = new CustomerDTO();
        BookDTO book = new BookDTO();
        
        cust.setIdSocio(1);
        cust.setApellidos("Pérez Domínguez");
        cust.setNombre("Raulito");
        cust.setDni("4004004D");
        
        book.setIdLibro(1);
        book.setAutor("Julio Verne");
        book.setNombre("La vuelta al mundo en 80 días");
        
        
        dto.setIdPrestamo(10);
        dto.setFechaInicio(new Date(Date.valueOf("2016-03-20").getTime()));
        dto.setFechaFin(new Date(Date.valueOf("2016-03-28").getTime()));
        
        instance.addLoan(dto);
        int expResult = 11;
        int result = instance.getRowCount();
        assertEquals(expResult, result);
        Mockito.verify(mockedLoansDAO, times(1)).insert(dto);
        System.out.println("Verified insert(customer) is called times");  
    }
    
    /**
     * Test of deleteSelectedLoans method, of class LoansTableModel.
     */
    @Test
    public void testDeleteSelectedLoans() throws LoansDAOException {
        System.out.println("deleteSelectedLoans");
        
        instance.setValueAt(Boolean.TRUE, 1, 0);
        instance.setValueAt(Boolean.TRUE, 3, 0);
        instance.setValueAt(Boolean.TRUE, 5, 0);
        
        instance.deleteSelectedLoans();
        
        int expResult = 7;
        int result = instance.getRowCount();
        assertEquals(expResult, result);
        
        Mockito.verify(mockedLoansDAO, times(3)).delete(anyInt());
        System.out.println("Verified delete(loan) is called 3 times"); 
    }

    /**
     * Test of removeLoan method, of class LoansTableModel.
     */
    @Test
    public void testRemoveLoan() {
        System.out.println("removeLoan");
        int rowIndex = 1;
        instance.removeLoan(rowIndex);
    }
    
//    /**
//     * Test of printLoans method, of class LoansTableModel.
//     */
//    @Test
//    public void testPrintLoans() {
//        System.out.println("printLoans");
//        LoansTableModel instance = null;
//        instance.printLoans();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of previewCustomers method, of class LoansTableModel.
//     */
//    @Test
//    public void testPreviewLoans() {
//        System.out.println("previewLoans");
//        LoansTableModel instance = null;
//        instance.previewLoans();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
}
