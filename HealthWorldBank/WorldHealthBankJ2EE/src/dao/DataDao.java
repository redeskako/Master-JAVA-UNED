package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dbConector.Conector;
import model.Data;
import model.User;

public class DataDao {

    private Connection connection;

    public DataDao() {
        connection = Conector.getConnection();
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


}
