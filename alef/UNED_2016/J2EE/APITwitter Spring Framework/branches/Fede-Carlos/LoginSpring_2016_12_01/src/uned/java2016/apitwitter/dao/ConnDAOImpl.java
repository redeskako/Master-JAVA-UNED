package uned.java2016.apitwitter.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.springframework.stereotype.Component;

@Component("connDAO")
public class ConnDAOImpl {
	
	private Connection conn;
	private boolean conectado;
	
	private void inicializa(){
		this.conn = null;
		this.conectado = false;
	}
	
	public ConnDAOImpl(){
		System.out.println("Clase ConnDAOImpl inicializada!!");
		inicializa();
	}
	
	@SuppressWarnings("finally")
	public boolean abrirConexion(){
		this.conectado=false;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			this.conn = DriverManager.getConnection("jdbc:mysql://localhost/apitwitter?user=uned&password=uned");
            this.conectado=true;
        } catch (SQLException e){
        	throw new APITwitterException("Error al abrir la conexión: " + e.getMessage());
        } catch (ClassNotFoundException e){
        	throw new APITwitterException("Error al abrir la conexión: " + e.getMessage());
        } catch (Exception e){
        	throw new APITwitterException("Error desconocido" + e.getMessage());
        }finally{
        	return conectado;
        }
	}

	public boolean estaConectado(){
		return this.conectado;
	}
	
	public void cerrarConexion(){
		try{
	///		if (this.stm != null){
	///    		this.stm.close();
	///		}
			if (this.conn != null){
				this.conn.close();
			}
		}catch (SQLException e){
			throw new APITwitterException("Error al cerrar la conexion: " + e.getMessage());
		}catch (Exception e){
			throw new APITwitterException("Error desconocido" + e.getMessage());
		}finally{
			inicializa();
		}
	}
	
	public Connection getConnection(){
		return this.conn;
	}
}
