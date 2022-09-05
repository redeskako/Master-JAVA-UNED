package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dbConector.Conector;
import model.Country;
import model.Data;
import model.User;

public class DataDao {

    private Connection connection;

    public DataDao() {
        connection = Conector.getConnection();
    }
    
    public List<Data> getAllData() {
        List<Data> datas = new ArrayList<Data>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from data;");
            while (rs.next()) {
            	Data data = new Data();
            	data.setIndicador_code(rs.getString("indicador_code"));
            	data.setCountry_code(rs.getString("country_code"));
            	data.setYear(rs.getInt("year"));
            	data.setPercentage(rs.getFloat("percentage"));
            	datas.add(data);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return datas;
    }

    //Paginación de la tabla data
    /*
     * @param pag: pagina
     * @param numeroFilas: numero de filas por página.
     */
    public List<Data> getDataPagination(int pag, int numeroFilas) {
        List<Data> datas = new ArrayList<Data>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from data limit "+numeroFilas+" OFFSET "+pag*numeroFilas+";");
            while (rs.next()) {
            	Data data = new Data();
            	data.setIndicador_code(rs.getString("indicador_code"));
            	data.setCountry_code(rs.getString("country_code"));
            	data.setYear(rs.getInt("year"));
            	data.setPercentage(rs.getFloat("percentage"));
            	datas.add(data);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return datas;
    }
    public int getNumeroPaginas() {
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT COUNT(*) AS total FROM data;");
            rs.next();
            return rs.getInt("total");
            
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }

        
    }
    
    
    public List<Data> getDataIndicadorCountry(String indicadorCode, String countryCode) {
        List<Data> datas = new ArrayList<Data>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(("SELECT * FROM data WHERE country_code = '"+countryCode+"' AND indicador_code ='"+indicadorCode+"';"));
            while (rs.next()) {
            	Data data = new Data();
            	data.setIndicador_code(rs.getString("indicador_code"));
            	data.setCountry_code(rs.getString("country_code"));
            	data.setYear(rs.getInt("year"));
            	data.setPercentage(rs.getFloat("percentage"));
            	datas.add(data);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return datas;
    }
    
  //Borrar data
    public void deleteData(String indicador, String code, int year) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from data where indicador_code=? and country_code=? and year=?");
            preparedStatement.setString(1, indicador);
            preparedStatement.setString(2, code);
            preparedStatement.setInt(3, year);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
  //Obtener un data
    public Data geteOneData(String indicador, String code, int year) {
    	Data data = new Data();
	    if(indicador != null && code != null && year != 0){    
    		try {
	            PreparedStatement preparedStatement = connection
	                    .prepareStatement("SELECT * FROM data WHERE indicador_code=? and country_code=? and year=?");
	            // Parameters start with 1
	            preparedStatement.setString(1, indicador);
	            preparedStatement.setString(2, code);
	            preparedStatement.setInt(3, year);
	            ResultSet rs = preparedStatement.executeQuery();
	            
	            while (rs.next()) {
	            	data.setIndicador_code(rs.getString("indicador_code"));
	            	data.setCountry_code(rs.getString("country_code"));
	            	data.setYear(rs.getInt("year"));
	            	data.setPercentage(rs.getFloat("percentage"));
	            }
	            
	
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
    		return data;
	    }
	    return new Data("indicador", "country", 2017, 0);   
    }
    
  //Crear datos
    public void createData(Data data) {
        try {
        	
            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into data(country_code, indicador_code, year, percentage) values (?, ?, ?, ? )");
            
            
            preparedStatement.setString(1, data.getCountry_code());
            preparedStatement.setString(2, data.getIndicador_code());
            preparedStatement.setInt(3, data.getYear());
            preparedStatement.setFloat(4, data.getPercentage());
            preparedStatement.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    //Actualizar datos
    public void updateData(Data data) {
        try {
        	
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update data set percentage=?" +
                            "where indicador_code=? and country_code=? and year=?");

            preparedStatement.setFloat(1, data.getPercentage());
            preparedStatement.setString(2, data.getIndicador_code());
            preparedStatement.setString(3, data.getCountry_code());
            preparedStatement.setInt(4, data.getYear());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
}
