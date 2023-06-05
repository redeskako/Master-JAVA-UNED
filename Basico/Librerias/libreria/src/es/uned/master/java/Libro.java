package es.uned.master.java;

import java.util.*;
import java.sql.*;

public class Libro {
	private int id;
	private int id_autor = 0;
	private String libro;
	private String autor;

	// Constructor
	public Libro(int id, String libro){
		this.id = id;
		this.libro = libro;
	}

	public Libro(int id, String libro, int idautor, String autor){
		this(id, libro);
		this.id_autor = idautor;
		this.autor = autor;
	}

	// Conexion con la base de datos y ejecutar una instruccion sql que se le pasa
	// Devuelve un Vector de libros
	public static Vector<Libro> consulta(String sql){
		// Datos de conexion
		 String host = "172.19.0.2";
		 int puerto= 3306;
		 String bbdd ="autores";
		 String user = "alef";
		 String pass = "alef";

		// Datos de conexion con la base de datos
		Connection conn = null;
		Statement stm = null;

		Vector<Libro> books = new Vector<Libro>(); // Array de datos, de tipo Libro
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://" + 
												host + ":" +
												puerto + "/" +
												bbdd, 
												user, pass); // Conecto a la base de dato
			stm = conn.createStatement();
			ResultSet rst= stm.executeQuery(sql); // Ejecuto sentencia SQL

			// Recorro los datos que me ha devuelto la sentencia, y voy creando el array
			while (rst.next()){
				Libro libro = new Libro(rst.getInt("id"),rst.getString("Titulo")); // Saco el dato
				books.add(libro); // Inserto en el array
			}
			stm.close(); // Cierro la conexion con la base de datos
		}catch(SQLException err){
			//throw new LibreriaException("Error consulta."+err);
			throw new LibreriaException("Error consulta.");
		}catch(Exception err){
			//throw new LibreriaException("Error indefinido."+err);
			throw new LibreriaException("Error en la base de datos.");
		}finally{
			if (conn!=null){
				try{
					conn.close(); // Cierro la conexion
				}catch(Exception e){}
			}
		}
		return books; // Devuelvo en un array todos los libros de la base datos
	}

	// Insertar un libro en la base de datos
	public static void insertar(Libro nombre){
		// Datos de conexion
		// private donde_base_datos="localhost";
		 String host = "172.19.0.2";
		 int puerto= 3306;
		 String bbdd ="autores";
		 String user = "alef";
		 String pass = "alef";
		// String insercion = "false";
		// Datos de conexion con la base de datos
		Connection conn = null;
		Statement stm = null;
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://" + 
												host+ ":" +
												puerto + "/" +
												bbdd, 
												user, pass); // Conecto a la base de dato
			stm = conn.createStatement();
			String sql = "INSERT INTO libro (Titulo) VALUES ('" + nombre.getLibro() + "')";
			System.out.println("INSERT: " + sql);
			stm.executeUpdate(sql); // Ejecuto sentencia SQL
			stm.close(); // Cierro la conexion con la base de datos
		}catch(SQLException err){
			//throw new LibreriaException("Error consulta."+err);
			throw new LibreriaException("Error en la insercion.");
		}catch(Exception err){
			//throw new LibreriaException("Error indefinido."+err);
			throw new LibreriaException("Error en la base de datos.");
		}finally{
		  if (conn!=null){
			 try{
				conn.close(); // Cierro la conexion
			  }catch(Exception e){}
		  }
		}
	}

	// Devuelvo el identificador de un libro
	public int getId(){
		return id;
	}

	public void setId(int id){
		this.id = id;
	}

	// Devuelvo un libro
	public String getLibro(){
		return libro;
	}

	public void setLibro(String libro){
		this.libro = libro;
	}

	public String toString(){
		return "[" + this.id + ", " + this.libro + "]"; // Formato del array de libro
	}

	// Devuelvo la informacion de un libro, indicandole su id
	public static Vector<Libro> listaLibro(){
		Vector<Libro> libros = Libro.consulta("SELECT * from libro ORDER BY Titulo");
		return libros;
	}

	// Devuelvo la informacion de un libro, indicandole su nombre
	public static Libro libro(String nombre){
		Vector<Libro> libros = Libro.consulta("SELECT * from libro where (Titulo=" + nombre + ")");
		Libro libro = null;
		if (libros.size()==1){
			libro = new Libro(libros.get(0).getId(), libros.get(0).getLibro());
		}else{
			throw new LibreriaException("No tengo ese libro");
		}
		return libro;
	}

	// Para decir la igualdad de un libro
	public boolean equals(Object o){
		try{
			Libro libro = (Libro) o;
			return (this.id==libro.getId());
		}catch(ClassCastException err){
			//throw new LibreriaException ("No es una clase Libro" + err.toString());
			throw new LibreriaException ("No es una clase Libro");
		}
	}

	public int hashCode(){
		return new Integer(this.id).hashCode();
	}
}