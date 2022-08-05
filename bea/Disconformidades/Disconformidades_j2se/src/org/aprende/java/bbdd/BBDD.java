package org.aprende.java.bbdd;
import java.sql.*;
import java.util.Date;

public class  BBDD {
	private Connection conn;
	private boolean conectado;
	private Statement stm;
		
	public BBDD(){		
		//no hace nada
	}	
	
	public boolean abrirConexion(){	
		this.conectado=false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://pi-/disconformidad?user=alef&password=alef");
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
				lista.add(new Disconformidad(rs.getInt("numero"),rs.getDate("fecha"),rs.getString("docs"),rs.getInt("servicio"),rs.getInt("idusuario"),rs.getBoolean("devolucion"),rs.getString("motivo"),rs.getString("comentario")));
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
			throw new DisconformidadException("Error al operar(anadir o eliminar) con una disconformidad. "+ e.getMessage());
		}
	}
	
}


