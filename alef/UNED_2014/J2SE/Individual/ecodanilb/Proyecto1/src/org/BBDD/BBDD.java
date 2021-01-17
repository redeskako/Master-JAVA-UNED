package org.BBDD;
import org.Otros.*;

import java.util.*;
import java.sql.*;
import java.util.*;


public class  BBDD {
	private Connection conn;
	private Statement stm;


	private String servidor;

	private String usuario;
	private String password;
		
	
	// ABRIMOS LA CONEXI�N
	
public void abrirConexion(){

	
		try{
			 Class.forName("com.mysql.jdbc.Driver"); // Carga el driver de conexion a mySQL
    	     this.conn = DriverManager.getConnection("jdbc:mysql://localhost/recursos_alpha?user=dani&password=dani");
    	     this.stm = conn.createStatement();

        } 
		catch (SQLException e){
        	throw new BBDDException("Error al abrir la Conexion: "+ e.getMessage());
      
      
        } 
		catch (Exception e){
      
        	throw new BBDDException("Error desconocido: "+e.getMessage());
        
        }

	}

// CERRAMOS LA CONEXION	


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
			throw new BBDDException("Error al Cerrar la Conexion: " + e.getMessage() );
		}
		}


	
	// HACEMOS CONSULTA SQL CON RECORDSET PARA VERIFICAR QUE EXISTE EL USUARIO
	
	public Usuario getUsuario(String sql){
		Usuario usuario = null;
		ResultSet rs;
		try{
			rs = this.stm.executeQuery(sql);//EJECUCI�N SETENCIA SQL
		  	if (rs.next()){
				usuario = new Usuario(rs.getString("NombreUsuario"), rs.getString("Contrasena"));//TOMAMOS LOS RESULTADOS DEVUELTOS
			}
			rs.close();
		}catch (SQLException e){
			throw new BBDDException("Error al cerrar la conexion: " + e.getMessage());
		}catch(Exception e){
			throw new BBDDException("Error indefinido. "+ e.getMessage());
		}finally{
			return usuario;
		}
	}
	
	// OBTENEMOS REGISTRO ID MEDIANTE CONSULTA LIMITADA CON PARAMETRO LIMIT
	
	
	public int obtenerId(int id)
	{
		 
		  

	  	  String stmsql="Select idRecurso from recursos limit " + (id-1) + ",1";

	  	  
	  	  int i=0;

	  	

	  	  try{
	
	  		abrirConexion();
	  		ResultSet rs;
	  		System.out.println("abierta conexion");
				 rs= this.stm.executeQuery(stmsql);

				 while(rs.next()){
	  			  i=rs.getInt("IdRecurso");
				 }

	  	  }catch(SQLException e){
	  		  throw new BBDDException ("Se ha producido Error Buscando IDRecursos" );
	  	  }
	  	  cerrarConexion();
	  	  return i;
	}
	
	//ALMACENAMOS EN UN ARRAYLIST LOS RESULTADOS DE CONSULTA SELECT
	
    public ArrayList <Recursos> listadoRecursos (String sql){
  	  String stmsql=sql;
  	  System.out.println("stmsql  "+sql);
  	  ResultSet rs;
  	  ArrayList <Recursos>ConjuntoRecursos =  new ArrayList  <Recursos>();


  	  try{
  		  
  		  rs= this.stm.executeQuery(stmsql);


	  		  		while(rs.next()){
	  			    ConjuntoRecursos.add(new Recursos(rs.getInt("IdRecurso"), rs.getString("Descripcion"))) ;
	  		  		}//fin while

  	  }catch(SQLException e){
  		  throw new BBDDException ("Se ha producido un error al recuperar los recursos" );
  	  }
  	  return ConjuntoRecursos;
  	     	 
   }
    
    
//ACTUALIZAMOS RECURSOS MEDIANTE EXECUTEUPDATE
    public void actualizarRecursos (String stmsql){
    	  
    	  abrirConexion();
    	  
    	  System.out.println("stmsql");

    	

    	  try{
    	
    		  this.stm.executeUpdate(stmsql);
    		  cerrarConexion();

    	  }catch(SQLException e){
    		  throw new BBDDException ("Se ha producido un error al Actualizar los recursos" );
    	  }

    	     	 
     }
    
   	
}
