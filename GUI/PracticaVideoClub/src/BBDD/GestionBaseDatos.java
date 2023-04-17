package BBDD;

import java.sql.*;
//import java.util.*;
import videoClub.* ;

public class GestionBaseDatos {
	
	private Connection con;
	
	public GestionBaseDatos(){
		this.con = null;
	}
	
	public Connection GetConexion(){
		return this.con;
	}
	
	public void CargaDriver(){
		//Se carga el driver
		try{
		Class.forName("com.mysql.cj.jdbc.Driver");
		}catch (ClassNotFoundException e){
//			throw new VideoClubException("Error al cargar el driver");
		}
	}
	
	public void Conectar(){
		
		try{
			this.con = DriverManager.getConnection("jdbc:mysql://petardo/pi_bbdd?user=pi&password=pi");
			
		}catch(SQLException e){
			throw new VideoClubException("Error de conexiÃ³n a base de datos");
		}
		
		
	}
	
	public void Desconectar(){
		try{
			this.con.close();
		}catch(SQLException e){
			
		}finally{
		this.con = null;
		}
	}
	
	private void EjecutarSentencia(String s) throws SQLException{
		Statement st =null;
		st = this.con.createStatement();
		st.execute(s);		
	}
	
	
	
	private ResultSet Consultar( String s) throws SQLException{
		Statement st;
		ResultSet r = null;
		st = this.con.createStatement();
		r = st.executeQuery(s);
					
		return r;
	}
	
	
	public int insertarPelicula(String titulo )throws BBDDVideoClubException{
		ResultSet r;
		int res ;
		try{
			this.EjecutarSentencia("INSERT INTO Peliculas (Pelicula) VALUES ('"+titulo+"')");
			r = this.Consultar("SELECT MAX(idPelicula) FROM Peliculas WHERE Pelicula = '"+titulo+"'");
			r.next();
			res = r.getInt(1);
		}catch (SQLException e){
			throw new BBDDVideoClubException("Error insertando pelicula" + e) ;
		}
		return res;		
	}
	
	public StockPelicula obtenerPeliculas()throws BBDDVideoClubException{
		ResultSet r;
		StockPelicula res = null;
		try{
			r = this.Consultar("SELECT * FROM Peliculas ");
			if (!r.next()){
				throw new BBDDVideoClubException("Error obteniendo peliculas de la BBDD") ;
			}
			
			
			do{
				res.InsertaPelicula(new Pelicula(r.getString(2),r.getInt(1))) ;
			}while (r.next());
			
		}catch (SQLException e){
			throw new BBDDVideoClubException("Error obteniendo peliculas de la BBDD" + e) ;
		}
		
		return res ;
	}
	
	
	public ListaUsuario obtenerUsuarios()throws BBDDVideoClubException{
		ResultSet r;
		ListaUsuario res = new ListaUsuario();
		try{
			r = this.Consultar("SELECT * FROM Clientes ");
			if (!r.next()){
				throw new BBDDVideoClubException("Error obteniendo clientes de la BBDD") ;
			}
			
			
			do{
				res.InsertaUsuario(new Usuario ( r.getString(2), r.getString(3), r.getString(4),r.getInt(1) ,r.getDate(5))) ;
			}while (r.next());
			
		}catch (SQLException e){
			throw new BBDDVideoClubException("Error obteniendo peliculas de la BBDD" + e) ;
		}
		
		return res ;
	}
	
	public int insertarUsuario(String dni,String nombre, String apel, java.util.Date fecha) throws BBDDVideoClubException{
		ResultSet r;
		int res;
		try{
			this.EjecutarSentencia("INSERT INTO Clientes (DNI,Nombre,Apellidos,Alta) VALUES('"+dni+"','"+nombre+"','"+apel+"',"+fecha+")");
			r = this.Consultar("SELECT MAX(idCliente) FROM Clientes WHERE DNI = '"+dni+"'");
			r.next();
			res = r.getInt(1);
		}catch(SQLException e){
			throw new BBDDVideoClubException("Error insertando cliente. " + e) ;
			
		}
		return res;
	}
	
	public void insertarAlquiler(int idCliente, int idPeli, java.util.Date fecha)throws BBDDVideoClubException{
		try{
			this.EjecutarSentencia("INSERT INTO Alquiler (idCliente,idPelicula, FechaAlquiler) VALUES("+idCliente+","+idPeli+","+fecha+")");
		}catch(SQLException e){
			throw new BBDDVideoClubException("Error insertando pelicula" + e) ;
			
		}
		
	}
	
	public Admin_Prestamo  RellenarMapa()throws BBDDVideoClubException{
		ResultSet r,r1;
		Admin_Prestamo res= new Admin_Prestamo();
		
		try{
			r = this.Consultar("SELECT DISTINCT p.idCliente, c.DNI, c.Nombre, c.Apellidos, c.Alta FROM Prestamos p, Clientes c WHERE p.idCliente = c.idCliente");
			if (!r.next()){
				throw new BBDDVideoClubException("No existen prestamos.") ;
			}
			Usuario u ;
			
			do{
				
				u = new Usuario(r.getString(3),r.getString(4), r.getString(2), r.getInt(1),r.getDate(5));
				Prestamo prestamo = new Prestamo(u);
				//con el id obtenemos el cliente y su lista de peliculas
				r1 = this.Consultar("SELECT p.idPelicula, pe.Pelicula FROM Prestamos p, Peliculas pe WHERE p.idPelicula = pe.idPelicula AND p.idCliente = "+r.getInt(1)) ;
				if (!r1.next()){
					throw new BBDDVideoClubException("No existen peliculas en el prestamo.") ;
				}
				do{
					prestamo.añadirPelicula(new Pelicula(r1.getString(2), r1.getInt(1))) ;
				}while (r1.next());
				
				//ha terminado el bucle y tenemos el prestamo para el usuario
				res.insertarPrestamo(u, prestamo) ;				
			}while (!r.last());
			
			
			
		}catch (SQLException e){
			throw new BBDDVideoClubException("Error insertando pelicula" + e) ;
		}
		
		return res ;
	}
	
	public void eliminarCliente(int idCliente){
		try{
			this.EjecutarSentencia("DELETE Clientes WHERE idCliente = "+idCliente);
		}catch(SQLException e){
			throw new BBDDVideoClubException("Error al eliminar cliente. "+e);
		}
		
	}
	
	public void eliminarAlquiler(int idCliente, int idPelicula){
		try{
			this.EjecutarSentencia("DELETE Alquiler WHERE idCliente = "+idCliente+" and idPelicula = "+idPelicula);
		}catch(SQLException e){
			throw new BBDDVideoClubException("Error al elimimar prÃ©stamo. "+e);
		}
	}
	
	public void eliminarPelicula(int idPelicula){
		try{
			this.EjecutarSentencia("DELETE Peliculas WHERE idPelicula = "+idPelicula);
		}catch(SQLException e){
			throw new BBDDVideoClubException("Error al eliminar pelicula. "+e);
		}
		
	}
}