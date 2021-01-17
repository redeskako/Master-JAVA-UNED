package ajax;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.UserDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import model.User;
import dbConector.Conector;

public class Prueba {

    private Connection connection;
    
    private static final Logger log = LoggerFactory.getLogger(UserDao.class);

    public Prueba() {
        connection = Conector.getConnection();
    }
    
//Devuelve un String vscío para verificar el comportamiento de DWR en un simple borrado de datos del jsp    
public String limpiar()  
{  
return "" ;  
} 

//Obtener todos los usuarios de bbdd en un String
public String getAllUser() {
    List<User> users = new ArrayList<User>();
    String listaUsuarios = "Usuarios actuales: ";
    try {
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select * from users");
        while (rs.next()) {
            User user = new User();
            user.setUser(rs.getString("user"));
            user.setPassword(rs.getString("password"));
            users.add(user);
            listaUsuarios = listaUsuarios + ", " + user.getUser();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return listaUsuarios;
}

//Obtener todos los usuarios como Array de Strings
public String[] getUserList() {
	
	String usuario = null;
    String[] users = null;
	ArrayList<String> ListUsers = new ArrayList<String>();
    try {
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select * from users");
        while (rs.next()) {
        	usuario = rs.getString("user");
            ListUsers.add(usuario);
            System.out.println("User = " + usuario);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    users = ListUsers.toArray(new String[0]);
    
    //verifica que la info a extraer es la correcta
    for (int i = 0; i < users.length; i++){
    	System.out.println("User array = " + users[i]);
    }
    
    return users;
}

}