package es.rizos.beansClientes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;


import es.rizos.librerias.ErrorRizosBd;

public class Clientes {
	private String dni;
	private int id;
	private String nombre;
	private String apellido1;
	private String apellido2;
	public static final int INCREMENTO=3;

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
    	try{

    		Class.forName("com.mysql.jdbc.Driver").newInstance();
    		con=  DriverManager.getConnection("jdbc:mysql://localhost/rizos_bd?user=usuario1&password=usuario1");
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
//inserta un elemento en la base de datos
   public void insertarCliente(Clientes cliente) {
        try {
           String sql = "insert into usuario (nombre, apellido1, apellido2, dni) " + "values ('"+cliente.getNombre()+"','"+cliente.getApellido1()+"','"+cliente.getApellido2()+"',"+cliente.getDni()+")";
            st.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
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
//obtiene un elemento "cliente" de la base de datos
    public Clientes obtenerCliente(int id) {
        Clientes cliente = null;
        try {
            String sql = "select id,nombre,apellido1,apellido2,dni from usuario where id='"+id+"';";
            st=this.abrirConexion();
            st.executeQuery(sql);
            ResultSet rs = st.getResultSet();
            if (rs.next()) {
                cliente = new Clientes();
                cliente.setId(rs.getInt("id"));
                cliente.setDni(rs.getString("dni"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido1(rs.getString("apellido1"));
                cliente.setApellido2(rs.getString("apellido2"));
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
//busca un elemento de la base de datos
    public ArrayList buscar(String campobusqueda, String textobusqueda) {
        ArrayList<Clientes> arrclientes = new ArrayList();
        try {
            String sql="select id,nombre,apellido1,apellido2,dni from usuario where "+campobusqueda+" like '%"+textobusqueda+"%' and activo='"+1+"';";
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
    public ArrayList ordenar(String campo) {
        ArrayList<Clientes> arrclientes = new ArrayList();
        try {

            String sql="select id,nombre,apellido1,apellido2,dni from usuario where activo='"+1+"' order by "+campo;
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
    public ArrayList ejecutaLista() {
        ArrayList<Clientes> arrclientes = new ArrayList();
        try {
        	String sql="select id,nombre,apellido1,apellido2,dni from usuario where activo='"+1+"' order by nombre";
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

   public void editarCliente(Clientes cliente) {
        try {
            String sql ="update usuario set nombre='"+cliente.getNombre()+"', apellido1='"+cliente.getApellido1()+"', apellido2='"+cliente.getApellido2()+"', dni='"+cliente.getDni()+"' where id='"+cliente.getId()+"'";
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

 public void eliminar(int idcliente){
     try {
         String sql ="delete from usuario where id='idcliente'";
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
	//=============================================
	public static int calculaTotalPaginas(){
		return calculafilas()/INCREMENTO;
	}

	//================================================================
		private static int calculafilas(){
			//Esto repite código, como hacemos para no repetirlo?????
			// Lo dejo como ejercicio!

			Connection conn=null;
			Statement stm= null;
			int total= 0;
			try{
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				conn=  DriverManager.getConnection("jdbc:mysql://localhost/rizos_bd?user=usuario1&password=usuario1");
				stm= conn.createStatement();
				ResultSet rst= stm.executeQuery("SELECT COUNT(*) AS Total FROM usuario");
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
