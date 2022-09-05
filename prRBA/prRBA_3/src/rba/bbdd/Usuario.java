package rba.bbdd;

import java.sql.*;
import rba.*;
import java.util.*;

import javax.servlet.http.HttpSession;

import configuracion.RbaException;


public class Usuario implements Comparable<Usuario>{
	public static final int INCREMENTO=10;
	private int codigo;
	private String dni;
	private String password;
	private String nombre;

	public Usuario(){
		this.codigo=0;
		this.dni=null;
		this.password=null;
		this.nombre=null;

	}

	public int compareTo(Usuario u){
		return this.dni.compareToIgnoreCase(u.getDni());
	}
	public int hashCode(){
		return this.dni.toUpperCase().hashCode();
	}
	public boolean equals(Object o){
		try{
			Usuario u= (Usuario) o;
			return this.dni.equalsIgnoreCase(u.dni);
		}catch(ClassCastException e){
			throw new RbaException("Error de casting");
		}
	}
	public int getCodigo(){
		return this.codigo;
	}
	public void setCodigo(int c){
		this.codigo=c;
	}

	public String getDni(){
		return this.dni;
	}
	public void setDni(String dni){
		this.dni=dni;
	}
	public String getPassword(){
		return this.password;
	}
	public void setPassword(String password){
		this.password= password;
	}

	public String toString(){
		return "usuario:["+this.codigo+","+this.dni+","+this.password+"]";
	}
		private static Set<Usuario> ejecutaSQL(ResultSet rst){
			Set<Usuario> dnis= new TreeSet<Usuario>();
			try{
				while(rst.next()){
					Usuario u= new Usuario();
					u.setCodigo(rst.getInt("codigo"));
					u.setDni(rst.getString("dni"));
					u.setPassword(rst.getString("pass"));
					dnis.add(u);
				}
				rst.close();
			}catch(SQLException e){
				throw new RbaException("Problema con las bases de datos");
			}
			return dnis;
		}
		private static Set<Usuario> ejecutaSQL(String sql){
			Set<Usuario> dnis=null;
			Connection conn=null;
			Statement stm= null;
			try{
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				conn=  DriverManager.getConnection("jdbc:mysql://localhost/rba?user=usuario&password=usuario");
				stm= conn.createStatement();
				ResultSet rst= stm.executeQuery(sql);
				//Proceso el select
				dnis= ejecutaSQL(rst);
				conn.close();
			}catch(ClassCastException e){
				throw new RbaException("No está cargado el driver");
			}catch(SQLException e){
				throw new RbaException("No puede conectar con la base de datos");
			}catch (Exception e){
				throw new RbaException("Error Indefinido");
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
			return dnis;
		}
			private static int calculafilas(){

				Connection conn=null;
				Statement stm= null;
				int total= 0;
				//tenemos que crear el tablespace rba y las tablas
				try{
					Class.forName("com.mysql.jdbc.Driver").newInstance();
					conn=  DriverManager.getConnection("jdbc:mysql://localhost/rba?user=usuario&password=usuario");
					stm= conn.createStatement();
					ResultSet rst= stm.executeQuery("SELECT COUNT(*) AS Total FROM USUARIOS");
					//Proceso el select
					rst.next();
					total= rst.getInt("Total");
					conn.close();
				}catch(ClassCastException e){
					throw new RbaException("No está cargado el driver");
				}catch(SQLException e){
					throw new RbaException("No puede conectar con la base de datos");
				}catch (Exception e){
					throw new RbaException("Error Indefinido");
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
		public static int calculaTotalPaginas(){
			return calculafilas()/INCREMENTO;
		}
	public static Set<Usuario> ejecutaLista(int i){
		String sql="SELECT * FROM USUARIOS ORDER BY dni LIMIT 0,"+INCREMENTO;
		if (i>0){
			sql="SELECT * FROM USUARIOS ORDER BY dni LIMIT "+i*10+","+INCREMENTO;
		}
		return ejecutaSQL(sql);
	}
	public static boolean logarse(String dni, String password){
		boolean logado=false;
		Connection conn=null;
		Statement stm= null;
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn=  DriverManager.getConnection("jdbc:mysql://localhost/rba?user=usuario&password=usuario");
			stm= conn.createStatement();
			String sql="SELECT count(*) as Total FROM USUARIOS WHERE (dni='"+dni+"') AND (pass=PASSWORD('"+password+"'))";
			System.out.println("Valor rst:"+sql);
			ResultSet rst= stm.executeQuery(sql);
			rst.next();
			logado= (rst.getInt("Total")==1);
		
			conn.close();
		}catch(ClassCastException e){
			throw new RbaException("No está cargado el Driver");
		}catch(SQLException e){
			throw new RbaException("No puede conectar con la BBDD");
		}catch (Exception e){
			throw new RbaException("Error Indefinido");
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
		return logado;
	}
	public static String idioma(String dni, String password){
		String idioma="es_ES";
		Connection conn=null;
		Statement stm= null;
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn=  DriverManager.getConnection("jdbc:mysql://localhost/rba?user=usuario&password=usuario");
			stm= conn.createStatement();
			String sql="SELECT idioma FROM USUARIOS WHERE (dni='"+dni+"') AND (pass=PASSWORD('"+password+"'))";
			System.out.println("Valor rst:"+sql);
			ResultSet rst= stm.executeQuery(sql);
		
			rst.next();
			idioma= rst.getString("idioma");
		
			conn.close();
		}catch(ClassCastException e){
			throw new RbaException("No está cargado el Driver");
		}catch(SQLException e){
			throw new RbaException("No puede conectar con la BBDD");
		}catch (Exception e){
			throw new RbaException("Error Indefinido");
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
		return idioma;
	}
	public static Set<Usuario> ejecutaBusquedaUsuario(int i){
		String sql="SELECT * FROM USUARIOS WHERE codigo = " + i;
		return ejecutaSQL(sql);
		}



}