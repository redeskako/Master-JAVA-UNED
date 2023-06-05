package es.uned.master.java;

import java.util.*;
import java.sql.*;

public class Libro {
	private int id;
	private String libro;

	public Libro(int id, String libro){
		this.id = id;
		this.libro = libro;
	}

	public static Vector<Libro> consulta(String sql){
		Connection conn = null;
		Statement stm = null;
		Vector<Libro> books = new Vector<Libro>();
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://172.19.0.2:3306/libreria", "alef", "alef");
			//conn = DriverManager.getConnection("jdbc:mysql://localhost:32795/libreria", "alef", "alef");
			stm = conn.createStatement();
			ResultSet rst = stm.executeQuery(sql);
			while (rst.next()){
				Libro libro = new Libro(rst.getInt("id"), rst.getString("nombre"));
				books.add(libro);
			}
			stm.close();
		}catch(SQLException err){
			//throw new LibreriaException("Error consulta."+err);
			throw new LibreriaException("Error consulta.");
		}catch(Exception err){
			//throw new LibreriaException("Error indefinido."+err);
			throw new LibreriaException("Error en la base de datos.");
		}finally{
			if (conn!=null){
				try{
					conn.close();
				}catch(Exception e){}
			}
		}
		return books; 
	}

	public int getId() {
		return this.id;
	}
/*
	public void setId(int id) {
		this.id = id;
	}
*/
	public String getLibro() {
		return this.libro;
	}

	public void setLibro(String libro) {
		this.libro = libro;
	}

	public String toString(){
		return "[" + this.id + ", " + this.libro + "]";
	}

	public static Libro libro(int id){
		Vector<Libro> libros = Libro.consulta("SELECT * from Libro where (id=" + id + ")");
		Libro libro = null;
		if (libros.size()==1){
			libro = new Libro(libros.get(0).getId(),libros.get(0).getLibro());
		}else{
			throw new LibreriaException("No tengo ese libro");
		}
		return libro;
	}

	public static Libro libro(String nombre){
		Vector<Libro> lb = Libro.consulta("SELECT * from Libro where (nombre='" + nombre + "')");
		Libro libro = null;
		if (lb.size()==1){
			libro = new Libro(lb.get(0).getId(),lb.get(0).getLibro());
		}else{
			throw new LibreriaException("No tengo ese libro");
		}
		return libro;
	}

	public boolean equals(Object o){
		try{
			Libro libro = (Libro) o;
			return (this.id==libro.getId());
		}catch(ClassCastException err){
			//throw new LibreriaException ("No es una clase Libro"+err.toString());
			throw new LibreriaException ("No es una clase Libro");
		}
	}

	public int hashCode(){
		return new Integer(this.id).hashCode();
	}
}