package Paq_libreria;

import java.util.*;
import java.sql.*;

public class Libro {
	private int id;
	//private int id_autor=0;
	private String libro;

	// Constructor
	public Libro(int id, String libro){
		this.id=id;
		this.libro=libro;
		//this.id_autor=id_autor;
	}


	// Concexion con la base de datos y ejecutar una instruccion sql que se le pasa
	// Devuelve un Vector de libros
	public static Vector<Libro> consulta(String sql){

		// Datos de conexion
		// private donde_base_datos="localhost";
		 String donde_base_datos="pi-";
		 String user="autor";
		 String pass="autor";

		// Datos de conexion con la base de datos
		Connection conn=null;
		Statement stm= null;

		Vector<Libro> books= new Vector<Libro>(); // Array de datos, de tipo Libro

		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn= DriverManager.getConnection("jdbc:mysql://pi-/autores", "autor", "autor"); // Conecto a la base de dato
			stm= conn.createStatement();
			ResultSet rst= stm.executeQuery(sql); //Ejecuto sentencia SQL

			// Recorro los datos que me ha devuelto la sentencia, y voy creando el array
			while (rst.next()){
				Libro l= new Libro(rst.getInt("id"),rst.getString("libro")); // Saco el dato
				books.add(l); // Inserto en el array
			}

			stm.close(); // cierro la conexion con la base de datos

		}catch(SQLException err){
			//throw new LibreriaException("Error consulta."+err);
			throw new LibreriaException("Error consulta.");
		}catch(Exception err){
			//throw new LibreriaException("Error indefinido."+err);
			throw new LibreriaException("Error en la base de datos.");
		}finally{
			if (conn!=null){
				try{
					conn.close();// Cierro la conexion
				}catch(Exception e){}
			}
		}
		return books; // Devuelbo en un array todos los libros de la base datos
	}



//	 Insertar un libro en la base de datos
	public static void insertar(Libro nombre){
		// Datos de conexion
		// private donde_base_datos="localhost";
		 String donde_base_datos="pi-";
		 String user="autor";
		 String pass="autor";
		// String insercion="false";

		// Datos de conexion con la base de datos
		Connection conn=null;
		Statement stm= null;

		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn= DriverManager.getConnection("jdbc:mysql://pi-/autores", "autor", "autor"); // Conecto a la base de dato
			stm= conn.createStatement();
			stm.executeQuery("INSERT INTO libro (libro) VALUES ("+nombre.getLibro()+")"); //Ejecuto sentencia SQL

			stm.close(); // cierro la conexion con la base de datos

		}catch(SQLException err){
			//throw new LibreriaException("Error consulta."+err);
			throw new LibreriaException("Error insercion.");
		}catch(Exception err){
			//throw new LibreriaException("Error indefinido."+err);
			throw new LibreriaException("Error en la base de datos.");
		}finally{
		  if (conn!=null){
			 try{
				conn.close();// Cierro la conexion
			  }catch(Exception e){}

		  }
	}
	}



	// Devuelvo el identificador de un libro
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

  // Devuelvo un libro
	public String getLibro() {
		return libro;
	}

	public void setLibro(String libro) {
		this.libro = libro;
	}

	public String toString(){
		return "["+this.id+", "+this.libro+"]"; //Formato del array de libro
	}


	// Devuelvo la informacion de un libro, indicandole su id

	public static Vector<Libro> listaLibro()
	{
		Vector<Libro> libros=Libro.consulta("SELECT * from libro");
		return libros;
	}



//	 Devuelvo la informacion de un libro, indicandole su nombre
	public static Libro libro(String nombre){
		Vector<Libro> lb= Libro.consulta("SELECT * from libro where (libro="+nombre+")");
		Libro libro= null;
		if (lb.size()==1){
			libro= new Libro(lb.get(0).getId(),lb.get(0).getLibro());
		}else{
			throw new LibreriaException("No tengo ese libro");
		}
		return libro;
	}


	// Para decir la igualdad de un libro
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
