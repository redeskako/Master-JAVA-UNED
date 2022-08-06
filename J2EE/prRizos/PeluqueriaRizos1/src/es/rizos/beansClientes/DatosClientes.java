package es.rizos.beansClientes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import es.rizos.librerias.ErrorRizosBd;

public class DatosClientes {

	private int id;
	private String dni;
	private int activo;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String direccion;
	private int codigopostal;
	private String localidad;
	private String provincia;
	private String telefono;
	private String observaciones;
	private String tipoUsuario;
	private String usuario;
	private String pass;


	public DatosClientes(){
		this.nombre=null;
		this.apellido1=null;
		this.apellido2=null;
		this.dni=null;
		this.direccion=null;
		this.codigopostal=0;
		this.localidad=null;
		this.provincia=null;
		this.telefono=null;
		this.observaciones=null;
		this.tipoUsuario=null;
		this.usuario=null;
		this.pass=null;
		this.activo=0;
	}
    Connection con = null;
    Statement st = null;

    public Statement abrirConexion() {
    	DataSource ds=null;
    	Connection con=null;
    	try{

    		Class.forName("com.mysql.jdbc.Driver").newInstance();
    		//con=  DriverManager.getConnection("jdbc:mysql://localhost/rizos_bd?user=usuario1&password=usuario1");
    		//con=DriverManager.getConnection("jdbc:mysql://localhost/Rizos_Bd","root","");
    		Context init= new InitialContext();
			Context envContext= (Context) init.lookup("java:comp/env");
			ds = (DataSource) envContext.lookup("jdbc/rizos_bd");
			con= ds.getConnection();
    		st= con.createStatement();
    		}catch(ClassCastException e){
    			throw new ErrorRizosBd("No está cargado el driver");
    		}catch(SQLException e){
    			throw new ErrorRizosBd("No puede conectar con la base de datos");
    		}catch (Exception e){
    			throw new ErrorRizosBd("Error Indefinido");

    		}
    		return st;
    }

    public void cerrarConexion() {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    public DatosClientes obtenerCliente(int id) {
        DatosClientes cliente = null;
        try {

            String sql = "select id,dni,nombre,apellido1,apellido2,direccion,codigopostal,localidad," +
            		"provincia,telefono,direccion,observaciones,tipoUsuario,usuario,pass from usuario where id='"+id+"';";
            st=this.abrirConexion();
            st.executeQuery(sql);
            ResultSet rs = st.getResultSet();
            if (rs.next()) {
                cliente = new DatosClientes();
                cliente.setId(rs.getInt("id"));
                cliente.setDni(rs.getString("dni"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido1(rs.getString("apellido1"));
                cliente.setApellido2(rs.getString("apellido2"));
                cliente.setDireccion(rs.getString("direccion"));
                cliente.setCodigopostal(rs.getInt("codigopostal"));
                cliente.setLocalidad(rs.getString("localidad"));
                cliente.setProvincia(rs.getString("provincia"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setDireccion(rs.getString("direccion"));
                cliente.setObservaciones(rs.getString("observaciones"));
                cliente.setTipoUsuario(rs.getString("tipoUsuario"));
                cliente.setUsuario(rs.getString("usuario"));
                cliente.setPass(rs.getString("pass"));

            }
            rs.close();
         	if (st!=null){
    			st.close();
    		}
         	this.cerrarConexion();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cliente;
    }
    public void editarCliente(DatosClientes cliente) {
        try {

        	String sql="update usuario set nombre='"+cliente.getNombre()+"'," +
        	" apellido1='"+cliente.getApellido1()+"', apellido2='"+cliente.getApellido2()+"'," +
        	" dni='"+cliente.getDni()+"', codigopostal='"+cliente.getCodigopostal()+"'," +
        	" localidad='"+cliente.getLocalidad()+"', provincia='"+cliente.getProvincia()+"'," +
        	" telefono='"+cliente.getTelefono()+"',direccion='"+cliente.direccion+"',observaciones='"+cliente.getObservaciones()+"'," +
     		" usuario='"+cliente.getUsuario()+"'," +
      		" pass='"+cliente.getPass()+"' where id='"+cliente.getId()+"'";

            st=this.abrirConexion();
            st.executeUpdate(sql);

        	if (st!=null){
				st.close();
			}
            this.cerrarConexion();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //metodo que devuelve "false" puedo insertar y "true" ya existe el cliente
   public boolean existeDNI(DatosClientes clientes){
	   boolean existeDNI=false;
	   try {
	        	String sql= "SELECT count(*) as Total FROM usuario where (dni='"+clientes.getDni()+"') OR (usuario='"+clientes.getUsuario()+"') OR (pass='"+clientes.getPass()+"')";
	            st=this.abrirConexion();
	            st.executeQuery(sql);
	            ResultSet rs = st.getResultSet();
	            rs.next();
	            existeDNI=(rs.getInt("Total")!=0); //si existe cliente en total se almacena un 1
	               									//devolvera true
	            System.out.println(existeDNI);
	            rs.close();
	        	if (st!=null){
					st.close();
				}
	            this.cerrarConexion();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	        return (existeDNI);

   }
    public void insertarCliente(DatosClientes cliente) {
      try{
        	String sql=
        		"insert into usuario (nombre, apellido1, apellido2, direccion,activo, dni, codigopostal, localidad, provincia, telefono,observaciones, tipoUsuario,usuario, pass) values ('"+cliente.getNombre()+"','"+cliente.getApellido1()+"','"+cliente.getApellido2()+"','"+cliente.getDireccion()+"','"+1+"','"+cliente.getDni()+"','"+cliente.getCodigopostal()+"','"+cliente.getLocalidad()+"','"+cliente.getProvincia()+"','"+cliente.getTelefono()+"','"+cliente.getObservaciones()+"','"+cliente.getTipoUsuario()+"','"+cliente.getUsuario()+"','"+cliente.getPass()+"')";

            st=this.abrirConexion();
            st.executeUpdate(sql);
        	if (st!=null){
				st.close();
			}
            this.cerrarConexion();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

	public int getCodigopostal() {
		return codigopostal;
	}

	public void setCodigopostal(int codigopostal) {
		this.codigopostal = codigopostal;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
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


	public int getActivo() {
		return activo;
	}

	public void setActivo(int activo) {
		this.activo = activo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
