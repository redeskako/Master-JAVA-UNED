package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;

import dbconector.Conector;
import model.Country;
import model.User;

public class CountryDao implements CountryDaoI {

    private Connection connection;

    public CountryDao() {
        connection = Conector.getConnection();
    }
    
    /* EJEMPLO ADD TIPO GET
	@Override
	//Añadir country
    public String addCountry(String a, String b) {
		System.out.println("a = " + a + ", b = " + b);
		Country pais = new Country(a,b);
		try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into country(country_code,country_name) values (?, ? )");
            // Parameters start with 1
            preparedStatement.setString(1, a);
            preparedStatement.setString(2, b);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    return "Pais creado: "+pais.toString();
    }*/
    
    @Override
    //Añadir country
    public String addCountry(String token, Country pais) {

    	int codigo_salida = 0;
    	String mensaje_salida = "Pais insertado correctamente: "+pais.toString();
    	String mensaje_error = null;
    	
    	if (existeUsuario(token)) {
    			try {
    				PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into country(country_code,country_name) values (?, ? )");
    				// Parameters start with 1
    				preparedStatement.setString(1, pais.getCountry_code());
    				preparedStatement.setString(2, pais.getCountry_name());
    				preparedStatement.executeUpdate();

    			} catch (SQLException e) {
    				e.printStackTrace();
    				mensaje_error = e.toString();
    				codigo_salida = e.getErrorCode();
    			}
        
    			if (codigo_salida != 0) {
    				mensaje_salida = "No se ha podido realizar la insercion del pais: "+pais.toString()+ "\nERROR: " + mensaje_error;
    			}
    	
    			System.out.println("token = " + token);
    	} else {
    		mensaje_salida = "Token incorrecto!!!";
    	}
    			return "RESULTADO: " + mensaje_salida; 
    }

	
	@Override
	//Borrar country
    public String deleteCountry(String token, String code) {
		
		int codigo_salida = 0;
    	String mensaje_salida = "Pais eliminado correctamente";
    	String mensaje_error = null;
    	
    	if (existeUsuario(token)) {
    	
    		if (existePaisPorCodigo(code)) {
    			
    			try {
    				PreparedStatement preparedStatement = connection.prepareStatement("delete from country where country_code=?");
    				// Parameters start with 1
    				preparedStatement.setString(1, code);
    				preparedStatement.executeUpdate();
            
    			} catch (SQLException e) {
    				e.printStackTrace();
    				mensaje_error = e.toString();
    				codigo_salida = e.getErrorCode();
    			}	
        
    			if (codigo_salida != 0) {
    				mensaje_salida = "No se ha podido realizar el borrado de "+code+ "\nERROR: " + mensaje_error;
    			}
    		} else {
    			mensaje_salida = "No se ha podido realizar el borrado de "+code+ "\nERROR: Code_name inexistente";
    		}
    	} else {
    		mensaje_salida = "Token incorrecto!!!";
    	}
    return "RESULTADO: " + mensaje_salida; 
    }

	

	@Override
    //Update country
    public String updateCountry(String token, Country pais) {

    	int codigo_salida = 0;
    	String mensaje_salida = "Pais actualizado correctamente: "+pais.toString();
    	String mensaje_error = null;
    	
    	if (existeUsuario(token)) {
    	
    		if (existePaisPorCodigo(pais.getCountry_code())) {
    			try {
    				PreparedStatement preparedStatement = connection
    					.prepareStatement("update country set country_name=?" +
    							"where country_code=?");
    				// Parameters start with 1
    				preparedStatement.setString(1, pais.getCountry_name());
    				preparedStatement.setString(2, pais.getCountry_code());
    				preparedStatement.executeUpdate();

    			} catch (SQLException e) {
    				e.printStackTrace();
    				mensaje_error = e.toString();
    				codigo_salida = e.getErrorCode();
    			}
        
    			if (codigo_salida != 0) {
    				mensaje_salida = "No se ha podido realizar la actualizacion del pais: "+pais.toString()+ "\nERROR: " + mensaje_error;
    			}		
    		} else {
    			mensaje_salida = "No se ha podido realizar el update de "+pais.getCountry_code()+ "\nERROR: Code_name inexistente";
    		}
    	} else {
    		mensaje_salida = "Token incorrecto!!!";
    	}	
    	return "RESULTADO: " + mensaje_salida; 
    }

	
	/* EJEMPLO UPDATE TIPO GET
    @Override
    //Actualizar country
	public String updateCountry(String a, String b) {
    	System.out.println("a = " + a + ", b = " + b);
		Country pais = new Country(a,b);
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update country set country_name=?" +
                            "where country_code=?");
            // Parameters start with 1
            preparedStatement.setString(1, pais.getCountry_name());
            preparedStatement.setString(2, pais.getCountry_code());
            preparedStatement.executeUpdate();
            

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Pais updated: "+pais.toString();
    }*/


	@Override
	//Devuelve un pais
    public String getCountryByCode(String token, String input) {
    	String country_name = "vacio";
    	String country_code = "vacio";
    	System.out.println("Country_code = " + input);
        
    	if (existeUsuario(token)) {
    	try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from country where country_code=?");
            preparedStatement.setString(1, input);
            ResultSet rs = preparedStatement.executeQuery();
            
            if (rs.next()) {
            	country_code = rs.getString(1);
            	country_name = rs.getString(2);
                System.out.println("Country_name = " + country_name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("token = " + token);
       
    	} else {
    		country_name = "Token incorrecto!!!";
    	}
		return country_name;
    }

	
	//Devuelve si un pais existe
    public Boolean existePaisPorCodigo(String code) {
 
    	boolean existe = false;
    	String country_name = "vacio";
    	String country_code = "vacio";
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from country where country_code=?");
            preparedStatement.setString(1, code);
            ResultSet rs = preparedStatement.executeQuery();
            
            if (rs.next()) {
            	country_code = rs.getString(1);
            	country_name = rs.getString(2);
            	existe = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        
		return existe;
    }
	
	
	

	@Override
	  //Paginación de la tabla data
    /*
     * @param pag: pagina
     * @param numeroFilas: numero de filas por página.
     */
    public List<Country> getCountryPagination(String token, int pag, int numeroFilas) {
    	List<Country> countries = new ArrayList<Country>();
        
    	if (existeUsuario(token)) {
    	try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM country LIMIT "+numeroFilas+" OFFSET "+pag*numeroFilas+";");
            while (rs.next()) {
            	 Country country = new Country();
                 country.setCountry_code(rs.getString("country_code"));
                 country.setCountry_name(rs.getString("country_name"));
                 countries.add(country);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    	} else {
    		Country country = new Country();
            country.setCountry_code("Token incorrecto!!!");
            country.setCountry_name("Token incorrecto!!!");
            countries.add(country);
    	}
        return countries;
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
	            
	            token = usuario.getUser() + "-" + usuario.getPassword();
	            System.out.println("token = " + token);
	    	} else {
	    		token = "Usuario/Password inexistente";
	        	System.out.println("token = " + token);
	        }
	        
	    	} catch (SQLException e) {
	    		return "Error"+e;
	        }
		
		return token;
	    }

	//Devuelve si un pais existe
    public Boolean existeUsuario(String token) {
 
    	boolean existe = false;
    	String user = "vacio";
    	String pass = "vacio";
    	pass = token.substring(token.indexOf("-")+1,token.length());
    	user = token.substring(0,token.indexOf("-"));
    	System.out.println("user = " + user + ", pass = " + pass);
        try {
        	PreparedStatement usuario = connection
   				 .prepareStatement("select * from users where (user =? and password =?)");
   		
        	usuario.setString(1, user);
        	usuario.setString(2,  pass);
        	ResultSet rs = usuario.executeQuery();
   		 
        	if (rs.next()) {
        		System.out.println("user = " + rs.getString("user"));
        		System.out.println("user = " + rs.getString("password"));
           
        		existe = true;
        	} else {
        		System.out.println("token = Usuario/Password inexistente");
        	}
       
        } catch (SQLException e) {
        	System.out.println("Error"+e);
        }
	
		return existe;
    }

	
}
