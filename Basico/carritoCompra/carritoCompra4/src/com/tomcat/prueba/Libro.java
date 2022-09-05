package com.tomcat.prueba;

import java.util.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;

public class Libro {
	private int id;
	private String libro;

	public Libro(){
		this.id=0;
		this.libro=null;
	}
	public Libro(int id, String libro){
		this.id=id;
		this.libro=libro;
	}
	public static Vector<Libro> consulta(String sql){
		Connection conn=null;
		Statement stm= null;
		DataSource ds=null;
		Vector<Libro> books= new Vector();
		try{
//			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Context init= new InitialContext();
			Context envContext= (Context) init.lookup("java:comp/env");
//			conn= DriverManager.getConnection("jdbc:mysql://pi-/libreria", "usuario", "usuario");
			ds = (DataSource) envContext.lookup("jdbc/libreria");
			conn= ds.getConnection();
			stm= conn.createStatement();
			ResultSet rst= stm.executeQuery(sql);
			while (rst.next()){
				Libro l= new Libro(rst.getInt("id"),rst.getString("nombre"));
				books.add(l);
			}
			stm.close();
		}catch(NameNotFoundException err){
			throw new LibreriaException("Error en JNDI"+err);
		}catch(SQLException err){
			//throw new LibreriaException("Error consulta."+err);
			throw new LibreriaException("Error consulta."+err);
		}catch(Exception err){
			//throw new LibreriaException("Error indefinido."+err);
			throw new LibreriaException("Error en la base de datos."+err);
		}finally{
			if (conn!=null){
				try{
					conn.close();
				}catch(Exception e){}
			}
		}
		return books;
	}
	public int insertarLibro(String nuevoLibro){
		Connection conn=null;
		PreparedStatement stm= null;
		DataSource ds=null;
		String sql= "INSERT INTO Libro (nombre) values (?)";
		int n=0;
		try{
//			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Context init= new InitialContext();
			Context envContext= (Context) init.lookup("java:comp/env");
//			conn= DriverManager.getConnection("jdbc:mysql://pi-/libreria", "usuario", "usuario");
			ds = (DataSource) envContext.lookup("jdbc/libreria");
			conn= ds.getConnection();
			synchronized (this){
				stm= conn.prepareStatement(sql);
				stm.setString(1, nuevoLibro);
				n= stm.executeUpdate();
			}
			stm.close();
		}catch(NameNotFoundException err){
			throw new LibreriaException("Error en JNDI"+err);
		}catch(SQLException err){
			//throw new LibreriaException("Error consulta."+err);
			throw new LibreriaException("Error consulta."+err);
		}catch(Exception err){
			//throw new LibreriaException("Error indefinido."+err);
			throw new LibreriaException("Error en la base de datos."+err);
		}finally{
			if (conn!=null){
				try{
					conn.close();
				}catch(Exception e){}
			}
		}
		return n;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLibro() {
		return libro;
	}

	public void setLibro(String libro) {
		this.libro = libro;
	}
	public String toString(){
		return "["+this.id+", "+this.libro+"]";
	}
	public static Libro libro(int id){
		Vector<Libro> lb= Libro.consulta("SELECT * from Libro where (id="+id+")");
		Libro libro= null;
		if (lb.size()==1){
			libro= new Libro(lb.get(0).getId(),lb.get(0).getLibro());
		}else{
			throw new LibreriaException("No tengo ese libro");
		}
		return libro;
	}
	public static Libro libro(String nombre){
		Vector<Libro> lb= Libro.consulta("SELECT * from Libro where (nombre="+nombre+")");
		Libro libro= null;
		if (lb.size()==1){
			libro= new Libro(lb.get(0).getId(),lb.get(0).getLibro());
		}else{
			throw new LibreriaException("No tengo ese libro");
		}
		return libro;
	}
	public boolean equals(Object o){
		try{
			Libro l= (Libro) o;
			return (this.id==l.getId());
		}catch(ClassCastException err){
			//throw new LibreriaException ("No es una clase Libro"+err.toString());
			throw new LibreriaException ("No es una clase Libro");
		}
	}
	public int hashCode(){
		return new Integer(this.id).hashCode();
	}
}
