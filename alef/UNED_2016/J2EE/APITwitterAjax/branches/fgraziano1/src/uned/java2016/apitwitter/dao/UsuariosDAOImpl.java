package uned.java2016.apitwitter.dao;

import java.lang.StringBuilder;
import java.sql.Connection;
//import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class UsuariosDAOImpl implements UsuariosDAO {

        private Connection conn;
	    //private Statement stm;
        //private ResultSet rs = null;
        //private boolean conectado;

        //private StringBuilder sqltext = new StringBuilder();


	public UsuariosDAOImpl(Connection conn){
        
             this.conn = conn;
             
	}
	
	
	public String getRol(String usuario, String passwd){
		    
		Statement stm = null;
 		ResultSet rs = null;
		String salida= null;
		              
             try {
            	 stm = this.conn.createStatement();
            	 rs = stm.executeQuery("select rol from usuarios where user='" + usuario + "' and passwd='" + passwd + "';");
				if (rs.next()){
					salida=rs.getString("rol");
				}
				rs.close();
				stm.close();
				return(salida);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

             return("");
             
             
             
	}
	





}
