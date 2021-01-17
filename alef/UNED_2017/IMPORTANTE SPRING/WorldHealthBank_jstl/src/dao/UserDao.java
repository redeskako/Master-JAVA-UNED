package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;

import dbConector.Conector;
import model.User;

public class UserDao {

    private static Connection connection;
    

    public UserDao() {
        connection = Conector.getConnection();
    }
    
    //Crear usuario
    public void crearteUser(User usuario){
    	try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into users(user,password) values (?, ? )");
            // Parameters start with 1
            preparedStatement.setString(1, usuario.getUser());
            //preparedStatement.setString(2, usuario.getPassword());

            preparedStatement.setString(2, DigestUtils.md5Hex(usuario.getPassword()));
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
   
    //Obtener todos los usuarios
    public List<User> getAllUser() {
        List<User> users = new ArrayList<User>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from users");
            while (rs.next()) {
                User user = new User();
                user.setUser(rs.getString("user"));
                user.setPassword(rs.getString("password"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }
    
    //Iniciar sesión: Comprueba si existe el usuario: (USER ó ADMIN) y si no existe (BAD_USER)
    public String getSession(User usuario){
    	
    	try{
    		
    		PreparedStatement user = connection.prepareStatement("select * from users where (user =? and password =?)");
    		 
    		
    		 user.setString(1, usuario.getUser());
    		 user.setString(2,  DigestUtils.md5Hex(usuario.getPassword()));
    		 ResultSet rs = user.executeQuery();
    		 
    		 
    		 if (rs.next()) {
                 usuario.setUser(rs.getString("user"));
                 usuario.setPassword(rs.getString("password"));
                 if(usuario.getUser().equals("admin")){
                	 return "ADMIN";
                 }
                 else{
                	 return "USER";
                 }
             }else{
         		return "BAD_USER";
            	 
             } 
    	
    	} catch (SQLException e) {
    		return "Error"+e;
        }
    }

       
}
