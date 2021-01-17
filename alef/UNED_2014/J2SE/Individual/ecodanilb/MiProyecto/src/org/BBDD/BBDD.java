package org.BBDD;

import java.util.*;
import java.sql.*;


public class  BBDD {
	private Connection conn;
	private Statement stm;
	private boolean conectado;

	private String servidor;

	private String usuario;
	private String password;
		
	public BBDD(){		
		//no hace nada
		inicializa();
	}

	private void inicializa(){
		this.conn = null;
		this.servidor = "";

		this.usuario = "";
		this.password = "";
		this.conectado = false;
	}

		public boolean abrirConexion(){
		this.conectado=false;
		try{
			 Class.forName("com.mysql.jdbc.Driver"); // Carga el driver de conexion a mySQL
			 this.conn = DriverManager.getConnection("jdbc:mysql://localhost/Recursos?user=root");
			 this.stm = conn.createStatement();
             this.conectado=true;
        } catch (SQLException e){
        	throw new BBDDException("Error al abrir la Conexion: "+ e.getMessage());
      //  } catch (ClassNotFoundException e){
      //  	throw new BBDDException("Error al abrir la Conexion: " + e.getMessage());
        } catch (Exception e){
        	throw new BBDDException("Error desconocido: "+e.getMessage());
        }finally{
        	return conectado;
        }
	}

	public boolean estaConectado(){
		return this.conectado;
	}
	
	public void cerrarConexion(){
		try{
			if (this.stm != null){
				this.stm.close();
			}
			if (this.conn != null){
				this.conn.close();
			}
		}catch (SQLException e){
			throw new BBDDException("Error al Cerrar la Conexion: " + e.getMessage() );
		}catch (Exception e){
			throw new BBDDException("Error Desconocido: " + e.getMessage());
	
		}finally{
			inicializa();
		}
	}

	public Statement getStm() {
		return stm;
	}

	
	
	
	public Usuario getUsuario(String sql){
		Usuario u = null;
		ResultSet rs;
		try{
			rs = this.stm.executeQuery(sql);
		  	if (rs.next()){
				u = new Usuario(rs.getString("Nombre"), rs.getString("Clave"),rs.getBoolean("Tipo"));
			}
			rs.close();
		}catch (SQLException e){
			throw new BBDDException("Error al cerrar la conexion: " + e.getMessage());
		}catch(Exception e){
			throw new BBDDException("Error indefinido. "+ e.getMessage());
		}finally{
			return u;
		}
	}

	
	
	
   

}
