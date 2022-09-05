package org.aprende.java.bbdd;
import java.sql.*;
import java.util.Date;

import org.aprende.java.*;

public class  Bbdd {
	private Connection conn;
	private boolean conectado;
	private Statement stm;
		
	public Bbdd(){		
		//no hace nada
	}	
	
	public boolean abrirConexion(){	
		this.conectado=false;
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            conn = DriverManager.getConnection("jdbc:mysql://petardo/disconformidad?user=alef&password=alef");
            stm=conn.createStatement();
            this.conectado=true;
        } catch (SQLException e) {
        	throw new DisconformidadException("Error al abrir la conexión: " + e.getMessage());
        } catch (ClassNotFoundException e){
        	throw new DisconformidadException("Error al abrir la conexión: " +e.getMessage());
        }finally{
        	return conectado; 
        }
        
	}
	public boolean estaConectado(){
		return this.conectado;
	}
	public void cerrarConexion(){
		try{
			this.stm.close();
			this.conn.close();			
		}catch (SQLException e){
			throw new DisconformidadException("Error al cerrar la conexión: " +e.getMessage());	        
		}finally{
			this.conn=null;
		}
	}
	public Usuarios listadoUsuarios(String sql){
		Usuarios lista = new Usuarios();
		ResultSet rs; 
		try{
			rs= stm.executeQuery(sql);				
			while (rs.next()){
				lista.add(new Usuario(rs.getInt("Id"),rs.getString("usuario"),rs.getString("clave")));
			}
			rs.close();
		}catch (SQLException e){
			lista = null;
			throw new DisconformidadException("Error al cerrar la conexion: " +e.getMessage());
		}catch(Exception e){
			lista = null;
			throw new DisconformidadException("Error indefinido. "+ e.getMessage());
		}
		finally{
			return lista;
		}
	}
	public Disconformidades listadoDisconformidades(String sql){
		Disconformidades lista = new Disconformidades();
		ResultSet rs; 
		try{
			rs= stm.executeQuery(sql);				
			while (rs.next()){
				//lista.add(new Disconformidad(rs.getInt("numero"),rs.getDate("fecha"),rs.getString("doc"),rs.getInt("servicio"),rs.getInt("usuario"),rs.getBoolean("devolucion"),rs.getString("motivo"),rs.getString("comentario")));
				// esta linea se quitara mas adelante
				
				lista.add(new Disconformidad(rs.getInt("numero"),rs.getString("fecha"),rs.getString("docs"),rs.getInt("servicio"),rs.getInt("idusuario"),rs.getBoolean("devolucion"),rs.getString("motivo"),rs.getString("comentario")));
			
			}
			rs.close();
		}catch (SQLException e){
			lista = null;
			throw new DisconformidadException("Error al cerrar la conexion: " +e.getMessage());
		}catch(Exception e){
			lista = null;
			throw new DisconformidadException("Error indefinido. "+ e.getMessage());
		}
		finally{
			return lista;
		}
	}	
	
	
	public void ejecutarDisconformidad (String comando) {
		try {
			stm.executeUpdate(comando);
		}catch(SQLException e) {
			throw new DisconformidadException("Error al operar(a�adir o eliminar) con una disconformidad. "+ e.getMessage());
		}
	}
	
}


