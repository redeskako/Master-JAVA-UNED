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

public class CountryDao {

    private Connection connection;

    public CountryDao() {
        connection = Conector.getConnection();
    }

    //A�adir country
    public void addCountry(Country country) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into users(country_code,country_name) values (?, ? )");
            // Parameters start with 1
            preparedStatement.setString(1, country.getCountry_code());
            preparedStatement.setString(2, country.getCountry_name());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Borrar country
    public void deleteCountry(String code) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from country where country_code=?");
            // Parameters start with 1
            preparedStatement.setString(1, code);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Actualizar country
	public void updateCountry(Country country) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update country set country_name=?" +
                            "where country_code=?");
            // Parameters start with 1
            preparedStatement.setString(1, country.getCountry_name());
            preparedStatement.setString(2, country.getCountry_code());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	//Devuelve todos los country
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

    //Devuelve un pais
    public Country getCountryByCode(String code) {
    	Country country = new Country();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from country where country_code=?");
            preparedStatement.setString(1, country.getCountry_code());
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                country.setCountry_code(rs.getString("country_code"));
                country.setCountry_name(rs.getString("country_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return country;
    }

}
