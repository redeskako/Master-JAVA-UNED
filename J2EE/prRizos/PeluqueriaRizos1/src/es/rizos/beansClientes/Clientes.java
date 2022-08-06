package es.rizos.beansClientes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


import es.rizos.librerias.ErrorRizosBd;

public class Clientes {
	private String dni;
	private int id;
	private String nombre;
	private String apellido1;
	private String apellido2;
	public static final int INCREMENTO=5;

	public Clientes(){
		this.nombre=null;
		this.apellido1=null;
		this.apellido2=null;
		this.dni=null;
	}
    Connection con = null;
    Statement st = null;
// Abrir conexion con la base de datos
    public Statement abrirConexion() {
    	DataSource ds=null;
    	Connection con=null;
    	try{

    		//Class.forName("com.mysql.jdbc.Driver").newInstance();
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
//cierra la conexion con la base de datos
    public void cerrarConexion() {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

//elimina un elemento de la base de datos
    public void eliminarCliente(int id) {
        try {

            String sql = "update usuario set activo='"+0+"' where id='"+id+"';";
            st=this.abrirConexion();
            st.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

//busca un elemento de la base de datos
    public ArrayList buscar(String campobusqueda, String textobusqueda,int i) {
        ArrayList<Clientes> arrclientes = new ArrayList();
        try {
        	String sql="select id,nombre,apellido1,apellido2,dni from usuario where "+campobusqueda+" like '%"+textobusqueda+"%' and activo='"+1+"' LIMIT 0,"+INCREMENTO;
            if (i>0){
            	sql="select id,nombre,apellido1,apellido2,dni from usuario where "+campobusqueda+" like '%"+textobusqueda+"%' and activo='"+1+"' LIMIT "+i*5+","+INCREMENTO;
            }
            st=this.abrirConexion();
            st.executeQuery(sql);
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
            	Clientes cli=new Clientes();
            	cli.setId(rs.getInt("id"));
                cli.setNombre(rs.getString("nombre"));
                cli.setApellido1(rs.getString("apellido1"));
                cli.setApellido2(rs.getString("apellido2"));
                cli.setDni(rs.getString("dni"));
                arrclientes.add(cli);

            }
            rs.close();
         	if (st!=null){
    			st.close();
    		}
         	this.cerrarConexion();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }


        return arrclientes;
    }

//metodo que obtiene los elementos de la tabla ordenados seg�n "campo"
    public ArrayList<Clientes> ordenar(String campo,int i) {
        ArrayList<Clientes> arrclientes = new ArrayList<Clientes>();
        try {
        	String sql="select id,nombre,apellido1,apellido2,dni from usuario where activo='"+1+"' order by "+campo+" LIMIT 0,"+INCREMENTO;
            if (i>0){
            	sql="select id,nombre,apellido1,apellido2,dni from usuario where activo='"+1+"' order by "+campo+" LIMIT "+i*5+","+INCREMENTO;
            }
            st=this.abrirConexion();
            st.executeQuery(sql);
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
            	Clientes cli=new Clientes();
            	cli.setId(rs.getInt("id"));
                cli.setNombre(rs.getString("nombre"));
                cli.setApellido1(rs.getString("apellido1"));
                cli.setApellido2(rs.getString("apellido2"));
                cli.setDni(rs.getString("dni"));
                arrclientes.add(cli);

            }
            rs.close();
        	if (st!=null){
				st.close();
			}
            this.cerrarConexion();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return arrclientes;
    }

//metodo que obtiene una lista de "clientes"
    public ArrayList<Clientes> ejecutaLista(int i) {
        ArrayList<Clientes> arrclientes = new ArrayList<Clientes>();
        try {
        	 String sql="select id,nombre,apellido1,apellido2,dni from usuario where activo='"+1+"' order by nombre LIMIT 0,"+INCREMENTO;
             if (i>0){
            	 sql="select id,nombre,apellido1,apellido2,dni from usuario where activo='"+1+"' order by nombre LIMIT "+i*5+","+INCREMENTO;
             }
            st=this.abrirConexion();
            st.executeQuery(sql);
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
            	Clientes cli=new Clientes();
            	cli.setId(rs.getInt("id"));
                cli.setNombre(rs.getString("nombre"));
                cli.setApellido1(rs.getString("apellido1"));
                cli.setApellido2(rs.getString("apellido2"));
                cli.setDni(rs.getString("dni"));
                arrclientes.add(cli);

            }
            rs.close();
        	if (st!=null){
				st.close();
			}
            this.cerrarConexion();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return arrclientes;
    }


	//=============================================
	public static int calculaTotalPaginas(){
		int contador=calculafilas()/INCREMENTO;

		if((calculafilas()%(INCREMENTO))== 0){
			contador-=contador;
		}

		return contador;
	}


	//================================================================
		private static int calculafilas(){
			//Esto repite código, como hacemos para no repetirlo?????
			// Lo dejo como ejercicio!

			Connection conn=null;
			Statement stm= null;
			DataSource ds=null;
			int total= 0;
			try{
				//Class.forName("com.mysql.jdbc.Driver").newInstance();
				//conn=  DriverManager.getConnection("jdbc:mysql://localhost/rizos_bd?user=usuario1&password=usuario1");
				//conn=DriverManager.getConnection("jdbc:mysql://localhost/Rizos_Bd","root","");
				Context init= new InitialContext();
				Context envContext= (Context) init.lookup("java:comp/env");
				ds = (DataSource) envContext.lookup("jdbc/rizos_bd");
				conn= ds.getConnection();
	    		stm= conn.createStatement();
				ResultSet rst= stm.executeQuery("SELECT COUNT(*) AS Total FROM usuario where activo='"+1+"'");
				//Proceso el select
				rst.next();
				total= rst.getInt("Total");
				conn.close();
			}catch(ClassCastException e){
				throw new ErrorRizosBd("No está cargado el driver");
			}catch(SQLException e){
				throw new ErrorRizosBd("No puede conectar con la base de datos");
			}catch (Exception e){
				throw new ErrorRizosBd("Error Indefinido");
			}finally{
				try{
					if (stm!=null){
						stm.close();
					}
					if (conn!=null){
						conn.close();
					}
				}catch(Exception e){}
			}
			return total;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
