/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package comercio;

import comercio.error.ErrorComercioException;
import java.sql.*;
import java.util.*;

/**
 *
 * @author kako
 */
public class ComercioBBDD {
    private String bbdd;
    private String servidor;
    private String usuario;
    private String pass;

    @SuppressWarnings("deprecation")
	public ComercioBBDD(String servidor, String bbdd, String usuario, String pass){
        // TreeMap está ordenado, pero la variable es de tipo Map, solo podré ejecutar métodos de la clase Map
        // TreeMap implementa el interfaz Map, por eso me permite hacer new de la clase TreeMap
        this.servidor = servidor;
        this.bbdd = bbdd;
        this.usuario = usuario;
        this.pass = pass;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        } catch (Exception e) {
            //throw new ComercioException("Driver de BD no localizado"+e);
        }
    }

    public Map<Integer,Comercio> consultaComercios() {
        return this.consultaSQL("Select * from comercios order by co_id");
    }

    public void actualizaComercios(Map<Integer,Comercio> comercios) {
        this.actualizaSQL(comercios);
    }
    
    private Map<Integer,Comercio> consultaSQL(String sql) {
        // Defino la conexion Connection, e inicializo el map que asigno a una vble interna.
        Map<Integer,Comercio> comercios = new TreeMap<Integer, Comercio>();
        Connection con = null;
        Statement stm = null;
        ResultSet rst = null;

        try {
            con = DriverManager.getConnection("jdbc:mysql://" + this.servidor + "/" + this.bbdd, this.usuario, this.pass);
            // Establezco el Statement
            stm = con.createStatement();
            // Se utiliza prepareStatement para evitar en lo posible el ataque de inyección de SQL en las consultas
            // Le pido el ResultSet sobre el sql
            rst = stm.executeQuery(sql);

            // Mientras haya elementos
            while (rst.next()) {
              Comercio com = new Comercio(rst.getInt("co_id"), rst.getString("co_nombre"), rst.getString("co_pais"));
              comercios.put(new Integer(rst.getInt("co_id")), com);
            }
            // Cierro el resultset
            rst.close();
        } catch (SQLException e) {
            throw new ErrorComercioException("Error de consulta de los datos. "+e);
        } catch(Exception e) {
            throw new ErrorComercioException("Error no definido. "+e);
        } finally {
            try {
                // Cierre Statement
                stm.close();
            } catch(Exception e) {}
            try {
                // Cierre Connection
                con.close();
            } catch(Exception e) {}
        }
        return comercios;
    }

    private void actualizaSQL(Map<Integer,Comercio> comercios) {
        Connection con = null;
        Statement stm = null;
        String sql;

        try {
            con = DriverManager.getConnection("jdbc:mysql://" + this.servidor + "/" + this.bbdd, this.usuario, this.pass);
            // Establezco el Statement
            stm = con.createStatement();
            Iterator<Map.Entry<Integer,Comercio>> it = comercios.entrySet().iterator();
            while (it.hasNext()){
                Comercio comercio = it.next().getValue();
                if (comercio.get_co_id() < 0) {
                    // Nuevo comercio
                    sql = "INSERT INTO comercios (co_nombre, co_pais) VALUES (";
					sql+= "'"+comercio.get_co_name()+"','"+comercio.get_co_pais()+"')";
                } else {
                    // Actualizar comercio
                    sql = "UPDATE comercios SET co_nombre = '"+comercio.get_co_name()+"', ";
					sql+="co_pais = '"+comercio.get_co_pais();
					sql+="' where (co_id = "+comercio.get_co_id()+")";
                }
                stm.addBatch(sql);
            }
            int [] res = stm.executeBatch();
        } catch(SQLException e) {
            throw new ErrorComercioException("Error de actualización de los datos. "+e);
        } catch(Exception e) {
            throw new ErrorComercioException("Error no definido. "+e);
        } finally {
            try {
                stm.close();
            } catch(Exception e) {}
            try {
                con.close();
            } catch(Exception e) {}
        }
    }
}
