/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.csc.biblioteca.books.gui;

import es.csc.biblioteca.books.dao.BookDTO;
import es.csc.biblioteca.books.dao.BooksDAOException;
import es.csc.biblioteca.books.dao.IBooksDAO;
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
 * @author Ignacio
 */
@RunWith(MockitoJUnitRunner.class)  
public class BooksTableModelTest {
    
    private static IBooksDAO mockedBooksDAO;
    private static BooksTableModel instance;
    
    public BooksTableModelTest() {
    }
    
      @BeforeClass
    public static void setUpClass() throws BooksDAOException {
        // Create mock object of IBooksDAO
        mockedBooksDAO = mock(IBooksDAO.class);
        when(mockedBooksDAO.findAll()).thenReturn(new ArrayList<BookDTO>());
        when(mockedBooksDAO.insert((BookDTO) anyObject())).thenReturn(1);
        doNothing().when(mockedBooksDAO).delete(anyInt());
        when(mockedBooksDAO.findByPrimaryKey(anyInt())).thenReturn(null);
        doNothing().when(mockedBooksDAO).update(anyInt(), (BookDTO) anyObject());
    }
    
      @AfterClass
    public static void tearDownClass() {
    }
    
     @Before
    public void setUp() {
        List<BookDTO> al = new ArrayList<BookDTO>();
        for(int i = 1; i <= 10; i++) {
            BookDTO dto = new BookDTO();
            dto.setIdLibro(i);
            dto.setNombre("Nombre_" + i);
            dto.setAutor("Autor_" + i);
            dto.setTema("Tema" + i);
            al.add(dto);
        }
        // Create objet to test
        instance = new BooksTableModel(mockedBooksDAO, al);
    }
        @After
    public void tearDown() {
    }
 /**
     * Test of getRowCount method, of class BooksTableModel.
     * @throws es.csc.biblioteca.Books.dao.BooksDAOException
     */
    @Test
    public void testGetRowCount() throws BooksDAOException {
        System.out.println("getRowCount");
        int expResult = 10;
        int result = instance.getRowCount();
        assertEquals(expResult, result);
    }
     /**
     * Test of getColumnCount method, of class BooksTableModel.
     */
    @Test
    public void testGetColumnCount() {
        System.out.println("getColumnCount");
        int expResult = 5;
        int result = instance.getColumnCount();
        assertEquals(expResult, result);
    }
    //    /**
//     * Test of isCellEditable method, of class BooksTableModel.
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
//     * Test of getColumnClass method, of class BooksTableModel.
//     */
//    @Test
//    public void testGetColumnClass() {
//        System.out.println("getColumnClass");
//        int columnIndex = 0;
//        BooksTableModel instance = null;
//        Class expResult = null;
//        Class result = instance.getColumnClass(columnIndex);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getColumnName method, of class BooksTableModel.
//     */
//    @Test
//    public void testGetColumnName() {
//        System.out.println("getColumnName");
//        int column = 0;
//        BooksTableModel instance = null;
//        String expResult = "";
//        String result = instance.getColumnName(column);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getValueAt method, of class BooksTableModel.
//     */
//    @Test
//    public void testGetValueAt() {
//        System.out.println("getValueAt");
//        int rowIndex = 0;
//        int columnIndex = 0;
//        BooksTableModel instance = null;
//        Object expResult = null;
//        Object result = instance.getValueAt(rowIndex, columnIndex);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setValueAt method, of class BooksTableModel.
//     */
//    @Test
//    public void testSetValueAt() {
//        System.out.println("setValueAt");
//        Object aValue = null;
//        int rowIndex = 0;
//        int columnIndex = 0;
//        BooksTableModel instance = null;
//        instance.setValueAt(aValue, rowIndex, columnIndex);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getBooksAt method, of class BooksTableModel.
//     */
//    @Test
//    public void testGetBooksAt() {
//        System.out.println("getCustomerAt");
//        int rowIndex = 0;
//        BooksTableModel instance = null;
//        BookDTO expResult = null;
//        BooksDTO result = instance.getBookrAt(rowIndex);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setBookAt method, of class BooksTableModel.
//     */
//    @Test
//    public void testSetBookAt() {
//        System.out.println("setBookAt");
//        BookDTO dto = null;
//        int rowIndex = 0;
//        BooksTableModel instance = null;
//        instance.setBookAt(dto, rowIndex);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of addBook method, of class BooksTableModel.
     */
     @Test
    public void testAddBook() throws BooksDAOException {
        System.out.println("addCustomer");
        
        BookDTO dto = new BookDTO();
        dto.setIdLibro(99);
        dto.setNombre("Ignacio");
        dto.setAutor("Mockito_Prueba");
        
        instance.addBook(dto);
        int expResult = 11;
        int result = instance.getRowCount();
        assertEquals(expResult, result);
        Mockito.verify(mockedBooksDAO, times(1)).insert(dto);
        System.out.println("Verified insert(book) is called times");  
    }
     /**
     * Test of removeBook method, of class BooksTableModel.
     */
    @Test
    public void testRemovBook() {
        System.out.println("removeBook");
        int rowIndex = 1;
        instance.removeBook(rowIndex);
    }
/**
     * Test of deleteSelectedBooks method, of class BooksTableModel.
     */
    @Test
    public void testDeleteSelectedBooks() throws BooksDAOException {
        System.out.println("deleteSelectedBooks");
        
        instance.setValueAt(Boolean.TRUE, 1, 0);
        instance.setValueAt(Boolean.TRUE, 3, 0);
        instance.setValueAt(Boolean.TRUE, 5, 0);
        
        instance.deleteSelectedBooks();
        
        int expResult = 7;
        int result = instance.getRowCount();
        assertEquals(expResult, result);
        
        Mockito.verify(mockedBooksDAO, times(3)).delete(anyInt());
        System.out.println("Verified delete(customer) is called 3 times"); 
    }
}
