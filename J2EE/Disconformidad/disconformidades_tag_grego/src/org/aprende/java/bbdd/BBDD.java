package org.aprende.java.bbdd;
import java.sql.*;
import java.util.*;


public class  BBDD {
	private Connection conn;
	private boolean conectado;
	private Statement stm;
	private String servidor;
	private String base;
	private String usuario;
	private String password;

	public BBDD(){
		//no hace nada
		inicializa();
	}

	private void inicializa(){
		conn=null;
		servidor="";
		base="";
		usuario="";
		password="";
		conectado=false;

	}

		//public boolean abrirConexion(String servidor,String base, String usuario, String password){
		public boolean abrirConexion(){
		this.conectado=false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//conn = DriverManager.getConnection("jdbc:mysql://localhost/disconformidad?user=root&password=minene");
			conn = DriverManager.getConnection("jdbc:mysql://petardo/disconformidad?user=alef&password=alef");
			//conn = DriverManager.getConnection("jdbc:mysql://" + servidor + "/" + base + "?user=" + usuario +"&pasword="+password );
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
			throw new DisconformidadException("Error al cerrar la conexion: " +e.getMessage());
		}finally{
			inicializa();

		}
	}

	public Usuario getUsuario(String sql){
		Usuario u = null ;
		ResultSet rs;
		try{
			rs= stm.executeQuery(sql);
			if (rs.next()){
			u=new Usuario(rs.getInt("Id"),rs.getString("usuario"),rs.getString("clave"),rs.getInt("Gestion"));
			}
			rs.close();
		}catch (SQLException e){
			u = null;
			throw new DisconformidadException("Error al cerrar la conexion: " +e.getMessage());
		}catch(Exception e){
			u = null;
			throw new DisconformidadException("Error indefinido. "+ e.getMessage());
		}
		finally{
			return u;
		}

	}
	public Servicio getServicio(String sql){
		Servicio s = null ;
		ResultSet rs;
		try{
			rs= stm.executeQuery(sql);
			if (rs.next()){
			s=new Servicio(rs.getInt("Id"),rs.getString("servicio"));
			}
			rs.close();
		}catch (SQLException e){
			s = null;
			throw new DisconformidadException("Error al cerrar la conexion: " +e.getMessage());
		}catch(Exception e){
			s = null;
			throw new DisconformidadException("Error indefinido. "+ e.getMessage());
		}
		finally{
			return s;
		}

	}

	public Disconformidad getDisconformidad(String sql){
		ResultSet rs;
		Disconformidad dis=null;
		try{
			rs= stm.executeQuery(sql);
			if (rs.next()){
				dis=new Disconformidad(rs.getInt("numero"),rs.getDate("fecha"),rs.getString("docs"),rs.getInt("servicio"),rs.getInt("idusuario"),rs.getBoolean("devolucion"),rs.getString("motivo"),rs.getString("comentario"));
			}
			rs.close();
		}catch (SQLException e){
			dis = null;
			throw new DisconformidadException("Error al cerrar la conexion: " +e.getMessage());
		}catch(Exception e){
			dis = null;
			throw new DisconformidadException("Error indefinido. "+ e.getMessage());
		}
		finally{
			return dis;
		}
	}

	public ArrayList <Disconformidad> listadoDisconformidades(String sql){
		ArrayList <Disconformidad> lista = new ArrayList<Disconformidad>();
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




	///anteriores
	public String getNombreUsuario(String sql){
		String u = null ;
		ResultSet rs;
		try{
			rs= stm.executeQuery(sql);
			if (rs.next()){
				u=  rs.getString("usuario");
			}
			rs.close();
		}catch (SQLException e){
			u = null;
			throw new DisconformidadException("Error al cerrar la conexion: " +e.getMessage());
		}catch(Exception e){
			u = null;
			throw new DisconformidadException("Error indefinido. "+ e.getMessage());
		}
		finally{
			return u;
		}
	}

	public Servicios listadoServicios(String sql){
		Servicios lista = new Servicios();
		ResultSet rs;
		try{
			rs= stm.executeQuery(sql);
			while (rs.next()){
				lista.add(new Servicio(rs.getInt("Id"),rs.getString("Servicio")));
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

}


