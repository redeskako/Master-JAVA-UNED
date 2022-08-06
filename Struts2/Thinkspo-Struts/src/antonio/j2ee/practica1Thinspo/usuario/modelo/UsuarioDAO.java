package antonio.j2ee.practica1Thinspo.usuario.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import antonio.j2ee.practica1Thinspo.bbdd.modelo.ModeloDAO;
import antonio.j2ee.practica1Thinspo.excepciones.SQLNegocioException;


/**
 * Representa el DAO de Usuarios
 * Ofrece las operaciones para realizar el CRUD de usuarios
 * @author Antonio Sánchez 
 *
 */
public class UsuarioDAO extends ModeloDAO{
    private static final String QUERYCREACION="INSERT INTO USUARIO (login,password,nombre,apellido) VALUES(?,?,?,?)";
    private static final String QUERYELIMINACION="DELETE FROM USUARIO WHERE LOGIN=? AND PASSWORD=?";
    private static final String QUERYBUSQUEDA="SELECT * FROM USUARIO ORDER BY login ASC LIMIT ? OFFSET ? ";
    private static final String QUERYCOUNT="SELECT COUNT(1) AS CONTADOR FROM USUARIO";
    private static final String QUERYBUSQUEDALOGINPASS="SELECT * FROM USUARIO WHERE LOGIN=? AND PASSWORD=?";
    private static final String QUERYEXISTENCIALOGIN="SELECT COUNT(1) AS CONTADOR FROM USUARIO WHERE LOGIN=?";
    private static final String QUERYMODIFICACION="UPDATE USUARIO SET nombre=?,apellido=? WHERE login=? AND password=?";
        
    protected UsuarioDAO() {}
    
    
    /**
     * Realiza la insercion de un usuario en BBDD
     * @param usuario
     * @return
     * @throws SQLNegocioException
     */
    public Usuario create(Usuario usuario) throws SQLNegocioException{
        PreparedStatement consulta=null;
        Connection conexion=null;
        try {
               // Apertura de una conexión
           conexion=getConnection();
               // Preparación de la consulta
           consulta=conexion.prepareStatement(QUERYCREACION);
           consulta.setString(1, usuario.getLogin().toLowerCase());
           consulta.setString(2, usuario.getPassword().toLowerCase());
           consulta.setString(3, usuario.getNombre().toLowerCase());
           consulta.setString(4, usuario.getApellidos().toLowerCase());
               // Ejecución de la consulta
           consulta.executeUpdate();
           // Devolver el usuario creado
           return usuario;
        }catch(SQLException sqle) {
        	System.err.println("Error al crear un nuevo usuario");
        	sqle.printStackTrace();
        	throw new SQLNegocioException("Ha habido un problema al crear un usuario.Consulte al Administrador,mas informacion en el log",sqle);
        }
        finally {
           try {
              if(consulta!=null) {
                  consulta.close();
              }
              if(conexion!=null) {
                 conexion.close();
              }
           }catch(Exception ex) {
        	    System.err.println("Error cierre");
        	    ex.printStackTrace();
        	    throw new SQLNegocioException(ex);
               }
        }
        
    }
        

    /**
     * Realiza la modificacion de los datos del usuario
     * @param usuario
     * @return
     * @throws SQLNegocioException
     */
    public Usuario modificar(Usuario usuario) throws SQLNegocioException{
        PreparedStatement consulta=null;
        Connection conexion=null;
        try {
               // Apertura de una conexión
           conexion=getConnection();
               // Preparación de la consulta
           consulta=conexion.prepareStatement(QUERYMODIFICACION);
           consulta.setString(1, usuario.getNombre().toLowerCase());
           consulta.setString(2, usuario.getApellidos().toLowerCase());
           consulta.setString(3, usuario.getLogin().toLowerCase());
           consulta.setString(4, usuario.getPassword().toLowerCase());
               // Ejecución de la consulta
           consulta.executeUpdate();
           // Devolver el usuario creado
           return usuario;
        }catch(SQLException sqle) {
        	System.err.println("Error al modificar un usuario");
        	sqle.printStackTrace();
        	throw new SQLNegocioException("Ha habido un problema al modificar un usuario.Consulte al Administrador,mas informacion en el log",sqle);
        }
        finally {
           try {
              if(consulta!=null) {
                  consulta.close();
              }
              if(conexion!=null) {
                 conexion.close();
              }
           }catch(Exception ex) {
        	    System.err.println("Error cierre");
        	    ex.printStackTrace();
        	    throw new SQLNegocioException(ex);
               }
        }
        
    }
    
    
   /**
    * Devuelve una Collection de Usuarios
    * Utilizado para la carga del Grid de Usuarios 
    * @param limite
    * @param offset
    * @return
    * @throws SQLNegocioException
    */
   public Collection<Usuario> findUsuarios (int limite,int offset) throws SQLNegocioException{
       ArrayList<Usuario>usuarios=new ArrayList<Usuario>();
	   PreparedStatement consulta=null;
       Connection conexion=null;
       ResultSet resulset=null;
       try {
              // Apertura de una conexión
          conexion=getConnection();
              // Preparación de la consulta
          consulta=conexion.prepareStatement(QUERYBUSQUEDA);
          consulta.setInt(1, limite);
          consulta.setInt(2, offset);
		  resulset=consulta.executeQuery();
		  while (resulset.next()){
				String login=resulset.getString("LOGIN");
				String password=resulset.getString("PASSWORD");
				String nombre=resulset.getString("NOMBRE");
				String apellidos=resulset.getString("APELLIDO");
				Usuario user=new Usuario(login,password,nombre,apellidos);
				usuarios.add(user);	
		 }
		 return usuarios;
		}catch(SQLException sqle){
			sqle.printStackTrace();
			throw new SQLNegocioException("Ha habido un problema al obtener datos de usuarios de la BBDD.Consulte al Administrador,mas informacion en el log",sqle);
		}finally{
			try{
				if(consulta!=null)
				    consulta.close();
				if(conexion!=null)
				   conexion.close();
			}catch(SQLException sqle){
				sqle.printStackTrace();
				throw new SQLNegocioException(sqle);
			}
		}
	}
	
   
   /**
    * Indica si un usuario y password existen
    * Utilizado para permitir el acceso a la aplicacion
    * @param login
    * @param pass
    * @return
    * @throws SQLNegocioException
    */
   public Boolean findUsuario(String login,String pass) throws SQLNegocioException{
	   PreparedStatement consulta=null;
       Connection conexion=null;
       ResultSet resulset=null;
       Boolean retorno=Boolean.FALSE;
       try {
              // Apertura de una conexión
          conexion=getConnection();
              // Preparación de la consulta
          consulta=conexion.prepareStatement(QUERYBUSQUEDALOGINPASS);
          consulta.setString(1, login.toLowerCase());
          consulta.setString(2, pass.toLowerCase());
          resulset=consulta.executeQuery();
		  if (resulset.next()){
			  retorno=Boolean.TRUE;
		  }
          return retorno;
       }catch(SQLException sqle){
			sqle.printStackTrace();
			throw new SQLNegocioException("Ha habido un problema al buscar usuario en BBDD.Consulte al Administrador,mas informacion en el log",sqle);
		}finally{
			try{
				if(consulta!=null)
				    consulta.close();
				if(conexion!=null)
				   conexion.close();
			}catch(SQLException sqle){
				sqle.printStackTrace();
				throw new SQLNegocioException(sqle);
			}
		}
   }
    
   /**
    * Elimina un usuario con el login y pass indicado
    * @param login
    * @param pass
    * @throws SQLNegocioException
    */
   public void eliminar(String login,String pass) throws SQLNegocioException{
    	   PreparedStatement consulta=null;
           Connection conexion=null;
           ResultSet resulset=null;
           try {
                  // Apertura de una conexión
              conexion=getConnection();
                  // Preparación de la consulta
              consulta=conexion.prepareStatement(QUERYELIMINACION);
              consulta.setString(1, login.toLowerCase());
              consulta.setString(2, pass.toLowerCase());
    		  consulta.executeUpdate();
           }catch(SQLException sqle){
    			sqle.printStackTrace();
    			throw new SQLNegocioException("Ha habido un problema al eliminar usuario en BBDD.Consulte al Administrador,mas informacion en el log",sqle);
    		}finally{
    			try{
    				if(consulta!=null)
    				    consulta.close();
    				if(conexion!=null)
    				   conexion.close();
    			}catch(SQLException sqle){
    				sqle.printStackTrace();
    				throw new SQLNegocioException(sqle);
    			}
    		}
       
   }

   /**
    * Indica si un login existe(>0) o no existe (0)
    * @param login
    * @return
    * @throws SQLNegocioException
    */
	public int existeLogin(String login) throws SQLNegocioException{
		   PreparedStatement consulta=null;
	       Connection conexion=null;
	       ResultSet resulset=null;
	       try {
	              // Apertura de una conexión
	          conexion=getConnection();
	              // Preparación de la consulta
	          consulta=conexion.prepareStatement(QUERYEXISTENCIALOGIN);
	          consulta.setString(1, login.toLowerCase());
	          resulset=consulta.executeQuery();
	          resulset.next();
	          return resulset.getInt("CONTADOR");
	       } catch(SQLException sqle){
				sqle.printStackTrace();
				throw new SQLNegocioException("Ha habido un problema al buscar usuario en BBDD.Consulte al Administrador,mas informacion en el log",sqle);
			}finally{
				try{
					if(consulta!=null)
					    consulta.close();
					if(conexion!=null)
					   conexion.close();
				}catch(SQLException sqle){
					sqle.printStackTrace();
					throw new SQLNegocioException(sqle);
				}
			}
	}

    /**
     * Cuenta el numero de usuarios que hay registrados
     * @return
     * @throws SQLNegocioException
     */
	public int countUsuarios() throws SQLNegocioException{
		   PreparedStatement consulta=null;
	       Connection conexion=null;
	       ResultSet resulset=null;
	       try {
	              // Apertura de una conexión
	          conexion=getConnection();
	              // Preparación de la consulta
	          consulta=conexion.prepareStatement(QUERYCOUNT);
	          resulset=consulta.executeQuery();
	          resulset.next();
	          return resulset.getInt("CONTADOR");
	       } catch(SQLException sqle){
				sqle.printStackTrace();
				throw new SQLNegocioException("Ha habido un problema al buscar usuario en BBDD.Consulte al Administrador,mas informacion en el log",sqle);
			}finally{
				try{
					if(consulta!=null)
					    consulta.close();
					if(conexion!=null)
					   conexion.close();
				}catch(SQLException sqle){
					sqle.printStackTrace();
					throw new SQLNegocioException(sqle);
				}
			}
	}

}