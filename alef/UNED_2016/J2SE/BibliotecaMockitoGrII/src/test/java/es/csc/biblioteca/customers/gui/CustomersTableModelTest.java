package es.csc.biblioteca.customers.gui;

import es.csc.biblioteca.customers.dao.CustomerDTO;
import es.csc.biblioteca.customers.dao.CustomersDAOException;
import es.csc.biblioteca.customers.dao.ICustomersDAO;
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
public class CustomersTableModelTest {

    private static ICustomersDAO mockedCustomersDAO;
    private static CustomersTableModel instance;
    
    public CustomersTableModelTest() {
    }
    
    @BeforeClass
    public static void setUpClass() throws CustomersDAOException {
        // Create mock object of ICustomersDAO
        mockedCustomersDAO = mock(ICustomersDAO.class);
        when(mockedCustomersDAO.findAll()).thenReturn(new ArrayList<CustomerDTO>());
        when(mockedCustomersDAO.insert((CustomerDTO) anyObject())).thenReturn(1);
        doNothing().when(mockedCustomersDAO).delete(anyInt());
        when(mockedCustomersDAO.findByPrimaryKey(anyInt())).thenReturn(null);
        doNothing().when(mockedCustomersDAO).update(anyInt(), (CustomerDTO) anyObject());
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        List<CustomerDTO> al = new ArrayList<CustomerDTO>();
        for(int i = 1; i <= 10; i++) {
            CustomerDTO dto = new CustomerDTO();
            dto.setIdSocio(i);
            dto.setNombre("Nombre_" + i);
            dto.setApellidos("Apellidos_" + i);
            dto.setDireccion("Dirección_" + i);
            dto.setDni("DNI_" + i);
            dto.setFechaAlta(null);
            al.add(dto);
        }
        // Create objet to test
        instance = new CustomersTableModel(mockedCustomersDAO, al);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getRowCount method, of class CustomersTableModel.
     * @throws es.csc.biblioteca.customers.dao.CustomersDAOException
     */
    @Test
    public void testGetRowCount() throws CustomersDAOException {
        System.out.println("getRowCount");
        int expResult = 10;
        int result = instance.getRowCount();
        assertEquals(expResult, result);
    }

    /**
     * Test of getColumnCount method, of class CustomersTableModel.
     */
    @Test
    public void testGetColumnCount() {
        System.out.println("getColumnCount");
        int expResult = 7;
        int result = instance.getColumnCount();
        assertEquals(expResult, result);
    }

//    /**
//     * Test of isCellEditable method, of class CustomersTableModel.
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
     * Test of addCustomer method, of class CustomersTableModel.
     */
    @Test
    public void testAddCustomer() throws CustomersDAOException {
        System.out.println("addCustomer");
        
        CustomerDTO dto = new CustomerDTO();
        dto.setIdSocio(10);
        dto.setNombre("David");
        dto.setApellidos("Atencia");
        
        instance.addCustomer(dto);
        int expResult = 11;
        int result = instance.getRowCount();
        assertEquals(expResult, result);
        Mockito.verify(mockedCustomersDAO, times(1)).insert(dto);
        System.out.println("Verified insert(customer) is called times");  
    }

    /**
     * Test of removeCustomer method, of class CustomersTableModel.
     */
    @Test
    public void testRemoveCustomer() {
        System.out.println("removeCustomer");
        int rowIndex = 1;
        instance.removeCustomer(rowIndex);
    }

    /**
     * Test of deleteSelectedCustomers method, of class CustomersTableModel.
     */
    @Test
    public void testDeleteSelectedCustomers() throws CustomersDAOException {
        System.out.println("deleteSelectedCustomers");
        
        instance.setValueAt(Boolean.TRUE, 1, 0);
        instance.setValueAt(Boolean.TRUE, 3, 0);
        instance.setValueAt(Boolean.TRUE, 5, 0);
        
        instance.deleteSelectedCustomers();
        
        int expResult = 7;
        int result = instance.getRowCount();
        assertEquals(expResult, result);
        
        Mockito.verify(mockedCustomersDAO, times(3)).delete(anyInt());
        System.out.println("Verified delete(customer) is called 3 times"); 
    }

//    /**
//     * Test of printCustomers method, of class CustomersTableModel.
//     */
//    @Test
//    public void testPrintCustomers() {
//        System.out.println("printCustomers");
//        CustomersTableModel instance = null;
//        instance.printCustomers();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of previewCustomers method, of class CustomersTableModel.
//     */
//    @Test
//    public void testPreviewCustomers() {
//        System.out.println("previewCustomers");
//        CustomersTableModel instance = null;
//        instance.previewCustomers();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
}
