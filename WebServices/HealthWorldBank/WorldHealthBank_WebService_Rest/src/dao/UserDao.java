package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.codec.digest.DigestUtils;

import dbconector.Conector;
import model.User;

public class UserDao implements UserDaoI {

	private Connection connection;

    public UserDao() {
        connection = Conector.getConnection();
    }
	
	@Override
	public String gettoken(User usuario) {
	    	
		String token = "null";
		try{
			PreparedStatement user = connection
	    				 .prepareStatement("select * from users where (user =? and password =?)");
	    		
			user.setString(1, usuario.getUser());
	    	user.setString(2,  DigestUtils.md5Hex(usuario.getPassword()));
	    	ResultSet rs = user.executeQuery();
	    		 
	    	if (rs.next()) {
	    		usuario.setUser(rs.getString("user"));
	            usuario.setPassword(rs.getString("password"));
	            
	            token = usuario.getUser() + usuario.getPassword();
	            System.out.println("token = " + token);
	    	} else {
	    		token = "Usuario inexistente";
	        	System.out.println("token = " + token);
	        }
	        
	    	} catch (SQLException e) {
	    		return "Error"+e;
	        }
		
		return token;
	    }


}
