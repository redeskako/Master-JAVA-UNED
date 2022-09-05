package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.commons.codec.digest.DigestUtils;

import dbconector.Conector;
import model.Country;
import model.User;

public class Dao implements DaoI {

    private Connection connection;
    private static final Logger log = LoggerFactory.getLogger(Dao.class);

    public Dao() {
        connection = Conector.getConnection();
    }
    
	@Override
	//Añadir country
    public String addCountry(Country pais) {
		
    	int codigo_salida = 0;
    	String mensaje_salida = "Pais insertado correctamente: "+pais.toString();
    	String mensaje_error = null;
    	
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
        
    return "RESULTADO: " + mensaje_salida; 
    }

	
	@Override
	//Borrar country
    public String deleteCountry(String code) {
	
		int codigo_salida = 0;
    	String mensaje_salida = "Pais eliminado correctamente";
    	String mensaje_error = null;
    	
    	if (existePaisPorCodigo(code)) {
    		try {
    			PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from country where country_code=?");
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
    	return "RESULTADO: " + mensaje_salida;   
    }

	
    
    @Override
    //Actualizar country
	public String updateCountry(Country pais) {
    	
    	int codigo_salida = 0;
    	String mensaje_salida = "Pais actualizado correctamente: "+pais.toString();
    	String mensaje_error = null;
    	
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
    	return "RESULTADO: " + mensaje_salida; 
    }

	
	@Override
	//Devuelve un pais
    public String getCountryByCode(String code) {
    	Country country = new Country();
    	String country_name = "vacio";
    	String country_code;
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from country where country_code=?");
            preparedStatement.setString(1, code);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
            	country_code = rs.getString(1);
            	country_name = rs.getString(2);
                System.out.println("Country_name = " + country_name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        
		return country_name;
    }

	
	@Override
	 //Crear usuario
    public String crearteUser(User usuario){
		
    	int codigo_salida = 0;
    	String mensaje_salida = "Usuario creado correctamente: " + usuario.toString();
    	String mensaje_error = null;
    	
    	if (!existeUsuario(usuario.getUser())) {
    	try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into users(user,password) values (?, ? )");
            // Parameters start with 1
            preparedStatement.setString(1, usuario.getUser());
            //preparedStatement.setString(2, usuario.getPassword());

            preparedStatement.setString(2, DigestUtils.md5Hex(usuario.getPassword()));
            preparedStatement.executeUpdate();
            log.info("Usuario creado: "+usuario.toString());
            
        } catch (SQLException e) {
            e.printStackTrace();
            mensaje_error = e.toString();
			codigo_salida = e.getErrorCode();
        }
    	
    	if (codigo_salida != 0) {
			mensaje_salida = "No se ha podido realizar la creación del usuario: "+usuario.toString()+ "\nERROR: " + mensaje_error;
		}		
	} else {
		mensaje_salida = "No se ha podido realizar la creación de " + usuario.getUser() + "\nERROR: Usuario existente";
	}
	return "RESULTADO: " + mensaje_salida; 
	}

	
	//Devuelve si un pais existe
	private boolean existeUsuario(String user) {
		 
    	boolean existe = false;
    	String usuario = "vacio";
    	try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from users where user=?");
            preparedStatement.setString(1, user);
            ResultSet rs = preparedStatement.executeQuery();
            
            if (rs.next()) {
            	usuario = rs.getString(1);
            	existe = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

		return existe;
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
    public List<Country> getCountryPagination(int pag, int numeroFilas) {
    	List<Country> countries = new ArrayList<Country>();
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

        return countries;
    }
}
