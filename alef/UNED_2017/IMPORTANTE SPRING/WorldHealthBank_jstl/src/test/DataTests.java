package test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import org.mockito.Mock;
import org.mockito.internal.util.reflection.Fields;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

import controller.DataController;
import dao.DataDao;
import model.Data;

@RunWith(MockitoJUnitRunner.class)
public class DataTests{

	
	private DataDao dataDao = new DataDao();
	private Data data = new Data("indicador", "cod", 2017, 100);
    
    @Mock
    private DataSource mockDataSource;
    @Mock
    private Connection mockConn;
    @Mock
    private PreparedStatement mockPreparedStmnt;
    @Mock
    private ResultSet mockResultSet;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;

    
    
    
    public DataTests(){
    	
    }
    
    @BeforeClass
    public static void setUpClass() throws Exception {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    	
	@Before
	public void setup() throws SQLException {
        when(mockDataSource.getConnection()).thenReturn(mockConn);
        when(mockDataSource.getConnection(anyString(), anyString())).thenReturn(mockConn);
        doNothing().when(mockConn).commit();
        when(mockConn.prepareStatement(anyString(), anyInt())).thenReturn(mockPreparedStmnt);
        doNothing().when(mockPreparedStmnt).setString(anyInt(), anyString());
        when(mockPreparedStmnt.execute()).thenReturn(Boolean.TRUE);
        when(mockPreparedStmnt.getGeneratedKeys()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);
    }
	
	@After
    public void tearDown() {
    }
	
	@Test
	public void testCreateData() throws SQLException{
		
		
		
		
		int databaseSizeBeforeCreate = dataDao.getAllData().size();
		dataDao.createData(data);
		
		
		assert(dataDao.getAllData().size() == (databaseSizeBeforeCreate+1));
		
     	dataDao.deleteData(data.getIndicador_code(), data.getCountry_code(), data.getYear());
		
		
	}
	
	@Test
	public void testUpdateData() throws SQLException{
		
		
		dataDao.createData(data);
		
		data.setPercentage(0);
		dataDao.updateData(data);
		
		
		assert(dataDao.geteOneData(data.getIndicador_code(), data.getCountry_code(), data.getYear()).getPercentage() == 0);
		
		dataDao.deleteData(data.getIndicador_code(), data.getCountry_code(), data.getYear());
		
	}
	

	@Test
	public void testDeleteData() throws SQLException{
		
		
		
		
		dataDao.createData(data);
		dataDao.deleteData(data.getIndicador_code(), data.getCountry_code(), data.getYear());
	
		assert(dataDao.geteOneData(data.getIndicador_code(), data.getCountry_code(), data.getYear()).getIndicador_code() == null);
	}
	
	
	@Test
	public void testPageableData() throws SQLException{
		
			
		assert(dataDao.getDataPagination(0, 20).size() == 20);
	}
	
	
	
	//Tests de creación de datas utilizando Request
	
	@Test
	public void testCreateRequestData() throws SQLException, ServletException, IOException{
		
		int databaseSizeBeforeCreate = dataDao.getAllData().size();
		when(request.getParameter("indicador")).thenReturn(data.getIndicador_code());
		when(request.getParameter("country")).thenReturn(data.getCountry_code());
		when(request.getParameter("year")).thenReturn(String.valueOf(data.getYear()));
		when(request.getParameter("percentage")).thenReturn(String.valueOf(data.getPercentage()));
		
		DataController dataController = new DataController();
		dataController.doPost(request, response);
		
		assert(dataDao.getAllData().size() == (databaseSizeBeforeCreate+1));
		
		
		dataDao.deleteData(data.getIndicador_code(), data.getCountry_code(), data.getYear());
		 
	}
	
	@Test
	public void testUpdateRequestData() throws SQLException, ServletException, IOException{
		
		dataDao.createData(data);
		when(request.getParameter("indicador")).thenReturn(data.getIndicador_code());
		when(request.getParameter("country")).thenReturn(data.getCountry_code());
		when(request.getParameter("year")).thenReturn(String.valueOf(data.getYear()));
		when(request.getParameter("percentage")).thenReturn("50.0");
		
		DataController dataController = new DataController();
		dataController.doPost(request, response);
		
		assert(dataDao.geteOneData(data.getIndicador_code(), data.getCountry_code(), data.getYear()).getPercentage() == 50);
		
		dataDao.deleteData(data.getIndicador_code(), data.getCountry_code(), data.getYear());
		 
	}
	
	@Test
	public void testDeleteRequestData() throws SQLException, ServletException, IOException{
		
		dataDao.createData(data);
		when(request.getParameter("action")).thenReturn("delete");
		when(request.getParameter("Indicador")).thenReturn(data.getIndicador_code());
		when(request.getParameter("code")).thenReturn(data.getCountry_code());
		when(request.getParameter("year")).thenReturn(String.valueOf(data.getYear()));
		
		DataController dataController = new DataController();
		dataController.doGet(request, response);
		
		assert(dataDao.geteOneData(data.getIndicador_code(), data.getCountry_code(), data.getYear()).getIndicador_code() == null);
		
		 
	}
    
    
	
}






