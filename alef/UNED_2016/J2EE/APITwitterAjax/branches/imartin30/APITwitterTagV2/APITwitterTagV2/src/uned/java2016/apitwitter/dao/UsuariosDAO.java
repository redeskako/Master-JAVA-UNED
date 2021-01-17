package uned.java2016.apitwitter.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

public interface UsuariosDAO {

        //private Connection conn;
	//private Preparedstatement stm;


        //public UsuariosDAOImpl(Connection conn, statement stm);

        //public setConnection(Connection conexion);
	
	public String getRol(String usuario, String passwd);
	

}
