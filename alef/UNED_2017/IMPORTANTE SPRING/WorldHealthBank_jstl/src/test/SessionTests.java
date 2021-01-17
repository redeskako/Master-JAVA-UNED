package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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


import dao.DataDao;
import dao.UserDao;
import model.Data;
import model.User;

@RunWith(MockitoJUnitRunner.class)
public class SessionTests{

	
    
    
    @Mock
    private DataSource mockDataSource;
    @Mock
    private Connection mockConn;
    @Mock
    private PreparedStatement mockPreparedStmnt;
    @Mock
    private ResultSet mockResultSet;

    
    
    
    public SessionTests(){
    	
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
	public void testUserSession() throws SQLException{
		
		UserDao userDao = new UserDao();
		User user = new User("luis", "root");
		assert(userDao.getSession(user) == "USER");
		
     	
		
		
	}
	
	@Test
	public void testBadUser() throws SQLException{
		
		UserDao userDao = new UserDao();
		User user = new User("luis", "");
		assert(userDao.getSession(user) == "BAD_USER");
		
	}
	

	@Test
	public void testAdmin() throws SQLException{
		
		UserDao userDao = new UserDao();
		User user = new User("admin", "admin");
		assert(userDao.getSession(user) == "ADMIN");
	}
	
	
}






