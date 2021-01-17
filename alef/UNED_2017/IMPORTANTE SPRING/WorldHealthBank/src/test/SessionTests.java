package test;

import org.mockito.Mock;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import junit.framework.TestCase;
import model.User;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import controller.AccesoWeb;
import dao.CountryDao;
import dao.DataDao;
import dao.HealthIndicatorDao;
import dao.UserDao;

@RunWith(MockitoJUnitRunner.class)
public class SessionTests{

	
	
	// Un usuario para pruebas
    private static final User USER = new User("Luis", "root");
    private static final User USER_ADMIN = new User("Luis", "root");
    private static final User BAD_USER = new User("Luis", "admin");
	

    private static UserDao userDao;
	
	@Before
    public void setUp() {
		
		userDao = mock(UserDao.class);
		
    }
	
	@Test
	public void test() throws Exception{
	
		//List<User> lista = userDao.getAllUser();
		//System.out.println(lista);
		assertEquals("USER", userDao.getSession(USER));
			  
	 
	 }
}






