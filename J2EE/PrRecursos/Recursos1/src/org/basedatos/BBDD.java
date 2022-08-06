package org.basedatos;



import java.sql.*;
import java.util.*;

import org.basedatos.ErrorException;
//Se crea la Base de Datos y la conexion
public class BBDD {
	
	private Connection conn;
	private Statement stm;
	private boolean conectado;
	
	@SuppressWarnings("unused")
	private String servidor;
	@SuppressWarnings("unused")
	private String base;
	@SuppressWarnings("unused")
	private String usuario;
	@SuppressWarnings("unused")
	private String password;

	
	public BBDD(){
		inicializa();
	}
	
	private void inicializa(){
		
		this.conn = null;
		this.servidor = "";
		this.base = "";
		this.usuario = "";
		this.password = "";
		this.conectado = false;
	}

	
	//para abrir la conexion con la base de datos
	public boolean abrirConexion() throws ErrorException{
		this.conectado = false;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			this.conn = DriverManager.getConnection("jdbc:mysql://localhost/recurso?user=alef&password=alef");
			this.stm = conn.createStatement();
			this.conectado = true;
		}catch (SQLException e){
			throw new ErrorException ("Error al abrir la conexion:" + e.getMessage());
		}catch (ClassNotFoundException e){
			throw new ErrorException ("Error al abrir la conexion:" + e.getMessage());
		}catch (Exception e){
			throw new ErrorException ("Error desconocido:" + e.getMessage());
		}
			return conectado;
	}
	//estado Conectado
 	public boolean estaConectado(){
 		return this.conectado;
 	}
	//Para Cerrar la conexion
 	public void cerrarConexion() throws ErrorException {
		try{
			if (this.stm !=null){
				this.stm.close();
			}
			if (this.conn !=null){
				this.conn.close();
			}
		}catch (SQLException e){
			throw new ErrorException("Error al cerrar la conexion:" + e.getMessage());
		}catch (Exception e){
			throw new ErrorException("Error desconocido" + e.getMessage());
		}finally {
			inicializa();
		}
	}
 	// Recoge el Id de Usuario y el Nombre 
 	public Usuario getUsuario (String sql) throws ErrorException{
 		Usuario u = null;
 		ResultSet rs;
 		try{
 			rs = this.stm.executeQuery(sql);
 			if (rs.next()){
 				u = new Usuario (rs.getInt("IdUsuario"), rs.getString("NombreUsuario"), rs.getString("Nombre"), rs.getString("Apellidos"), rs.getString("Contrasena"),rs.getInt("Rol"));
 			}
 			rs.close();
 		}catch (SQLException e){
			throw new ErrorException("Error al cerrar la conexion:" + e.getMessage());
		}catch (Exception e){
			throw new ErrorException("Error desconocido" + e.getMessage());
		}
			return u;  
		}
 	
 	
 	public String getNameUser (String sql) throws ErrorException{
 		String u = "";
 		ResultSet rs;
 		try{
 			rs = stm.executeQuery(sql);
 			if (rs.next()){
 				u = rs.getString("usuario");
 			}
 			rs.close();
 		}catch (SQLException e){
			throw new ErrorException("Error al cerrar la conexion:" + e.getMessage());
		}catch (Exception e){
			throw new ErrorException("Error desconocido" + e.getMessage());
		}
			return u;
	
 	}
 	// Saca el listado de Recursos
 	public ArrayList <Recurso> listadoRecursos (String sql) throws ErrorException{
 		ArrayList <Recurso> lista = new ArrayList<Recurso>();
 		ResultSet rs;
		try{
			rs = stm.executeQuery(sql);
			while (rs.next()){
				lista.add(new Recurso(rs.getInt("IdRecurso"),rs.getString("Descripcion")));
			}
			rs.close();
		}catch (SQLException e){
			lista = null;
			throw new ErrorException("Error al cerrar la conexion: " + e.getMessage());
		}catch(Exception e){
			lista = null;
			throw new ErrorException("Error indefinido. " + e.getMessage());
		}
			return lista;
	}

	public Recurso ejecutarRecurso (String comando) throws ErrorException{
		try{
			stm.executeUpdate(comando);
		}catch(SQLException e){
			throw new ErrorException("Error al operar(anadir o eliminar) con un recurso. " + e.getMessage());
		}
		return null;
	}
 }
 	
