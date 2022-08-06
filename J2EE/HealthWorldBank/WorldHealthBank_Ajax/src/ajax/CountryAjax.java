package ajax;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.CountryDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import model.Country;
import dbConector.Conector;


public class CountryAjax {

	 private Connection connection;
	 
	 private static final Logger log = LoggerFactory.getLogger(CountryDao.class);
	
	 public CountryAjax() {
	        connection = Conector.getConnection();
	        
	        
	    }
	
	
	//Obtener todos los países de bbdd en un array
	 
	 public List<Country> getAllCountry() {
	        List<Country> countries = new ArrayList<Country>();
	        try {
	            Statement statement = connection.createStatement();
	            ResultSet rs = statement.executeQuery("select * from country");
	            while (rs.next()) {
	                Country country = new Country();
	                country.setCountry_code(rs.getString("country_code"));
	                country.setCountry_name(rs.getString("country_name"));
	                countries.add(country);
	                System.out.println(country.toString());
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        
	        

	        return countries; 
	    }
	

	 
}
