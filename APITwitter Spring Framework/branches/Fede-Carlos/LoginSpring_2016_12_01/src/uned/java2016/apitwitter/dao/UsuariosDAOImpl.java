package uned.java2016.apitwitter.dao;

import org.springframework.stereotype.Component;

import java.lang.StringBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

@Component("usuariosDAO")
public class UsuariosDAOImpl implements UsuariosDAO {

        private Connection conn;
        
    public UsuariosDAOImpl(){
    	System.out.println("Clase UsuariosDAOImpl inicializada!!");
    }


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


	@Override
	public void setConnection(Connection conn) {
		this.conn = conn;
		
	}
	





}
