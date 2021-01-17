package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import utiles.YoutubeCrawler;

public class UsuarioDao
{		 
    public UsuarioDao()
    {	
    }

	public String getPassword(String userName) throws NamingException
	{
		Context envContext = null; 
		String password="nop";
        ResultSet rs;	       

        try
        {
        	envContext = new InitialContext();
        	Context initContext  = (Context)envContext.lookup("java:/comp/env");
            DataSource ds = (DataSource)initContext.lookup("jdbc/ytb");
            Connection con = ds.getConnection();
            
 	        Statement stmt = con.createStatement();
 			String sql = "select password from usuarios where user = '" + userName + "'" ;	             
 			rs = stmt.executeQuery(sql); 	             
            
 			while(rs.next())
 			{	            	    
 				password= rs.getString("password");
            }

 			rs.close();
            stmt.close();
            con.close();
        }
        catch (SQLException e)
        {
                e.printStackTrace();
        }

        return password;             
	}
}
