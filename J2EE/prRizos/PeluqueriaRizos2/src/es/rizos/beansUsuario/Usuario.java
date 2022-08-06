package es.rizos.beansUsuario;
import es.rizos.librerias.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Usuario {
	private String dni;
	private int id;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String direccion;
	private int codigopostal;
	private String localidad;
	private String provincia;
	private String telefono;
	private String activo;
	private String observaciones;
	private String tipoUsuario;
	private String usuario;
	private String password;


        public void Usuario(){

		this.dni=null;this.id=0;this.nombre=null;this.apellido1=null;this.apellido2=null;
		this.direccion=null;this.codigopostal=0;this.localidad=null;this.provincia=null;
		this.telefono=null;this.activo=null;this.observaciones=null;this.tipoUsuario=null;
		this.usuario=null;this.password=null;
	}
	public void Usuario(String dni,String nombre,String apellido1,String apellido2, String direccion,
	 int codigopostal,String localidad,String provincia,String telefono,String activo,String observaciones,
	 String tipoUsuario,String usuario,String password){

		this.dni=dni;this.nombre=nombre;this.apellido1=apellido1;this.apellido2=apellido2;
		this.direccion=direccion;this.codigopostal=codigopostal;this.localidad=localidad;
		this.provincia=provincia;this.telefono=telefono;this.activo=activo;this.observaciones=observaciones;
		this.tipoUsuario=tipoUsuario;this.usuario=usuario;this.password=password;

	}

	public String toString(){
		return "Usuario:["+this.dni+","+this.nombre+","+this.apellido1+","+this.apellido2+","+	this.direccion+","+this.codigopostal		+","+this.localidad+","+
		this.provincia+","+this.telefono+","+this.activo+","+this.observaciones+","+
		this.tipoUsuario+","+this.usuario+","+this.password+"]";
	}
	//=================================================================================================

	public static Connection abrirConexion(){
		Connection con=null;
		DataSource ds=null;
		try{
			//Class.forName("com.mysql.jdbc.Driver").newInstance();
			//con=DriverManager.getConnection("jdbc:mysql://localhost/rizos_bd?user=usuario1&password=usuario1");
			//con=DriverManager.getConnection("jdbc:mysql://localhost/Rizos_Bd","root","");
			Context init= new InitialContext();
			Context envContext= (Context) init.lookup("java:comp/env");
			ds = (DataSource) envContext.lookup("jdbc/rizos_bd");
			con= ds.getConnection();
		}catch(ClassCastException e){
			throw new ErrorRizosBd("No está cargado el driver");
		}catch (Exception e){
			throw new ErrorRizosBd("Error Indefinido");
		}
		return con;
	}
	//==============================================================================================
	public static boolean logarse(String usuario, String password){
		boolean logado=false;
		Connection con=null;
		Statement stm= null;
		try{
			con=abrirConexion();
			stm= con.createStatement();
            String sql="SELECT count(*) as Total FROM usuario where (usuario='"+usuario+"') AND (pass='"+password+"')";
			// el usuario y pass que utiliza el select si que se refiere a los usuarios de nuestra tabla
			//System.out.println("Valor rst:"+sql);
			ResultSet rst= stm.executeQuery(sql);
			rst.next();
			logado= (rst.getInt("Total")==1);
			con.close();
		}catch(ClassCastException e){
			throw new ErrorRizosBd("No está cargado el driver");
		}catch(SQLException e){
			throw new ErrorRizosBd("No puede conectar con la base de datos");
		}catch (Exception e){
			throw new ErrorRizosBd("Error Indefinido");
		// el finally siempre se ejecuta
		}finally{
			try{
				if (stm!=null){
					stm.close();
				}
				if (con!=null){
					con.close();
				}
			}catch(Exception e){}
		}
		return logado;
	}
	//=================================================================================================================
	public static boolean EsCliente(String usuario,String password){
		boolean esCliente=false;
		Connection conexion=abrirConexion();
                Statement stm= null;
		try{
			stm= conexion.createStatement();
            String sql="SELECT * FROM usuario where (usuario='"+usuario+"') AND (pass='"+password+"')";
            ResultSet rst= stm.executeQuery(sql);
            rst.next();
            String tipoUsuario=rst.getString(13);
            esCliente= tipoUsuario.equalsIgnoreCase("cliente");
            conexion.close();

		// el finally siempre se ejecuta
		}catch(SQLException e){
			throw new ErrorRizosBd("No puede conectar con la base de datos");
		}finally{
			try{
				if (stm!=null){
					stm.close();
				}
				if (conexion!=null){
					conexion.close();
				}
			}catch(Exception e){}
		}

		return esCliente;

	}
	//=========================================================================================================

	public static String buscadni(String usuario,String password){
		Connection conexion=abrirConexion();
        Statement stm= null;
        String dni="";
		try{
            stm= conexion.createStatement();
            String sql="SELECT dni FROM usuario where (usuario='"+usuario+"') AND (pass='"+password+"')";
            ResultSet rst= stm.executeQuery(sql);
            rst.next();
            dni=rst.getString(1);
            conexion.close();

		// el finally siempre se ejecuta
		}catch(SQLException e){
			throw new ErrorRizosBd("No puede conectar con la base de datos");
		}finally{
			try{
				if (stm!=null){
					stm.close();
				}
				if (conexion!=null){
					conexion.close();
				}
			}catch(Exception e){}
		}
		return dni;

	}
	//=========================================================================================================
	public static String idioma(String usuario,String password){
		Connection conexion=abrirConexion();
        Statement stm= null;
        String idioma="";
		try{
            stm= conexion.createStatement();
            String sql="SELECT locale FROM usuario where (usuario='"+usuario+"') AND (pass='"+password+"')";
            ResultSet rst= stm.executeQuery(sql);
            rst.next();
            idioma=rst.getString(1);
            conexion.close();

		// el finally siempre se ejecuta
		}catch(SQLException e){
			throw new ErrorRizosBd("No puede conectar con la base de datos");
		}finally{
			try{
				if (stm!=null){
					stm.close();
				}
				if (conexion!=null){
					conexion.close();
				}
			}catch(Exception e){}
		}
		
		return idioma;

	}


	//=========================================================================================================
	public static int buscaid(String dni){
		Connection conexion=abrirConexion();
                Statement stm= null;
                int id=0;
                try{
                    stm= conexion.createStatement();
                    String sql="SELECT id FROM usuario where dni='"+dni+"'";
                    ResultSet rst= stm.executeQuery(sql);
                    rst.next();
                    id=rst.getInt(1);
                    conexion.close();

		// el finally siempre se ejecuta
		}catch(SQLException e){
			throw new ErrorRizosBd("No puede conectar con la base de datos");
		}finally{
			try{
				if (stm!=null){
					stm.close();
				}
				if (conexion!=null){
					conexion.close();
				}
			}catch(Exception e){}
		}
		return id;

	}

	//==============================================================================================
	//==================================GETTERS AND SETTERS==========================================
	public String getDni() {
		return dni;
	}


	public void setDni(String dni) {
		this.dni = dni;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellido1() {
		return apellido1;
	}


	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}


	public String getApellido2() {
		return apellido2;
	}


	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public int getCodigopostal() {
		return codigopostal;
	}


	public void setCodigopostal(int codigopostal) {
		this.codigopostal = codigopostal;
	}

	public int getId(){
		return this.id;
	}
	public void seId(int id){
		this.id=id;
	}
	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}


	public String getProvincia() {
		return provincia;
	}


	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}


	public String getActivo() {
		return activo;
	}


	public void setActivo(String activo) {
		this.activo = activo;
	}


	public String getObservaciones() {
		return observaciones;
	}


	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}

}