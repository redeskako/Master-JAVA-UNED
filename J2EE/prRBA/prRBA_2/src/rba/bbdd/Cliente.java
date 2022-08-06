package rba.bbdd;


	import java.sql.*;

	import rba.*;
	import java.util.*;
	import java.lang.*;
import configuracion.RbaException;


	public class Cliente implements Comparable<Cliente>
	{
		public static final int INCREMENTO=10;
		private int codigo;
		private String nombre;
		private String apellido1;
		private String apellido2;
		private String telefono;
		private String correo;
		private String pass;
		private int cp;

		public Cliente(){
			this.codigo = 0;
			this.nombre = null;
			this.apellido1 = null;
			this.apellido2 = null;
			this.telefono = null;
			this.correo = null;
			this.pass = null;
			this.cp = 0;
		}

		public int compareTo(Cliente c){
			return this.correo.compareToIgnoreCase(c.getCorreo());
		}
		public int hashCode(){
			return this.correo.toUpperCase().hashCode();
		}
		public boolean equals(Object o){
			try{
				Cliente c= (Cliente) o;
				return this.correo.equalsIgnoreCase(c.correo);
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
		public String getNombre(){
			return this.nombre;
		}
		public void setNombre(String nom){
			this.nombre=nom;
		}
		public String getApellido1(){
			return this.apellido1;
		}
		public void setApellido1(String ape1){
			this.apellido1= ape1;
		}

		public String getApellido2(){
			return this.apellido2;
		}

		public void setApellido2(String ape2){
			this.apellido2 = ape2;
		}

		public void setTelefono(String telf){
			this.telefono = telf;
		}

		public String getTelefono(){
			return this.telefono;

		}
		public String getCorreo(){
			return this.correo;
		}

		public void setCorreo(String co){
			this.correo = co;
		}

		public String getPass(){
			return this.pass;
		}

		public void setPass(String password){
			this.pass = password;
		}

		public int getCp(){
			return this.cp;
		}

		public void setCp(int cp){
			this.cp = cp;
		}
		public String toString(){
			return "CLIENTE:["+this.codigo+","+this.nombre+" "+this.apellido1+" "+this.apellido2+"]";
		}
			public static Set<Cliente> ejecutaSQLCliente(ResultSet rst){
				Set<Cliente> sc= new TreeSet<Cliente>();
				try{
					while(rst.next()){
						Cliente c= new Cliente();
						c.setCodigo(rst.getInt("codigo"));
						c.setNombre(rst.getString("nombre"));
						c.setApellido1(rst.getString("apellido1"));
						c.setApellido2(rst.getString("apellido2"));
						c.setTelefono(rst.getString("telefono"));
						c.setCorreo(rst.getString("correo"));
						c.setPass(rst.getString("pass"));
						c.setCp(rst.getInt("cp"));
						sc.add(c);
					}
					rst.close();
				}catch(SQLException e){
					throw new RbaException("Problema con las bases de datos");
				}
				return sc;
			}
			public static Set<Cliente> ejecutaSQLCliente(String sql){
				Set<Cliente> sc=null;
				Connection conn=null;
				Statement stm= null;
				try{
					Class.forName("com.mysql.jdbc.Driver").newInstance();
					conn=  DriverManager.getConnection("jdbc:mysql://172.26.0.50/rba?user=usuario&password=usuario");
					stm= conn.createStatement();
					ResultSet rst= stm.executeQuery(sql);
					//Proceso el select
					sc= ejecutaSQLCliente(rst);
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
				return sc;
			}

			//INSERTA UN CLIENTE

				public static void InsertarCliente(String dni,String nombre,String apellido1,String apellido2,String telefono,String correo,String pass,int cp){

					Connection conn=null;
					Statement stm= null;

					try{
						Class.forName("com.mysql.jdbc.Driver").newInstance();
						conn=  DriverManager.getConnection("jdbc:mysql://172.26.0.50/rba?user=usuario&password=usuario");
						stm= conn.createStatement();
						ResultSet rst= stm.executeQuery("INSERT INTO CLIENTES (codigo,dni ,nombre ,apellido1 ,apellido2 ,telefono ,correo ,pass ,cp)VALUES (NULL ,'"+dni+"','"+nombre+"','"+apellido1+"','"+apellido2+"','"+telefono+"','"+correo+"',PASSWORD('"+pass+"'),"+cp+")");
						//Proceso el select
						rst.next();
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
				}

				// ACTUALIZA UN CLIENTE

				public static void ActualizarCliente(int codigo,String dni, String nombre,String apellido1,String apellido2,String telefono,String correo,String pass,int cp){

					Connection conn=null;
					Statement stm= null;

					try{
						Class.forName("com.mysql.jdbc.Driver").newInstance();
						conn=  DriverManager.getConnection("jdbc:mysql://172.26.0.50/rba?user=usuario&password=usuario");
						stm= conn.createStatement();
						ResultSet rst= stm.executeQuery("UPDATE CLIENTES SET nombre = '"+nombre+"',apellido1 = '"+apellido1+"',apellido2 = '"+apellido2+"',telefono = '"+telefono+"',correo = '"+correo+"',pass = PASSWORD('"+pass+"'),cp = '"+cp+"'WHERE codigo="+codigo+";");
						//Proceso el select
						rst.next();
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
				}

				//ELIMINAR UN CLIENTE


				public static void EliminarCliente( int codigo){

					Connection conn=null;
					Statement stm= null;

					try{
						Class.forName("com.mysql.jdbc.Driver").newInstance();
						conn=  DriverManager.getConnection("jdbc:mysql://172.26.0.50/rba?user=usuario&password=usuario");
						stm= conn.createStatement();
						final ResultSet rst= stm.executeQuery("DELETE FROM CLIENTES WHERE codigo="+codigo+";");
						//Proceso el select
						rst.next();
						conn.close();
					}catch(final ClassCastException e){
						throw new RbaException("No está cargado el driver");
					}catch(final SQLException e){
						throw new RbaException("No puede conectar con la base de datos");
					}catch (final Exception e){
						throw new RbaException("Error Indefinido");
					}finally{
						try{
							if (stm!=null){
								stm.close();
							}
							if (conn!=null){
								conn.close();
							}
						}catch(final Exception e){}
					}
				}

				//calcula el numero total de filas de la seleccion para ver el tema
				//del paginado

				public static int calculafilasClientes(){

					Connection conn=null;
					Statement stm= null;
					int total= 0;

					try{
						Class.forName("com.mysql.jdbc.Driver").newInstance();
						conn=  DriverManager.getConnection("jdbc:mysql://172.26.0.50/rba?user=usuario&password=usuario");
						stm= conn.createStatement();
						ResultSet rst= stm.executeQuery("SELECT COUNT(*) AS Total FROM CLIENTES");
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
			public static int calculaTotalPaginasC(){
				return calculafilasClientes()/INCREMENTO;
			}


		// esta funcion crea una select para listar todos los clientes parametrizados
		// por x clientes por paginas, con un numero inicial de cliente
		public static Set<Cliente> ejecutaListaCliente(int i){
			String sql="SELECT * FROM CLIENTES ORDER BY correo LIMIT 0,"+INCREMENTO;
			if (i>0){
				sql="SELECT * FROM CLIENTES ORDER BY correo LIMIT "+i*10+","+INCREMENTO;
			}
			return ejecutaSQLCliente(sql);
		}

		// funcion para crear select segun unos parametros
		// campo: con este parametro podemos elegir el campo de ordenacion y la opcion de busqueda
		// esta se activaria si en la caja de texto de busqueda hubiera algo que no sea blanco o null
		// texto: proviene de la caja de texto, que nos sirve para introducir los datos de busqueda
		// i: datos de inicio para paginar la seleccion

		public static Set<Cliente> ejecutaListaCampoCliente(int i, String campo, String texto){
				String sql="SELECT * FROM CLIENTES ORDER BY " + campo + " LIMIT 0,"+INCREMENTO;

				if (texto != ""){
					sql="SELECT * FROM CLIENTES where " + campo + " like '" + texto + "' order BY " + campo + " LIMIT 0,"+INCREMENTO;
					if (i>0){
						sql="SELECT * FROM CLIENTES where " + campo + " like '" + texto + "' ORDER BY" + campo + "LIMIT "+i*10+","+INCREMENTO;
					}

				}
				return ejecutaSQLCliente(sql);
			}

		// esta funcion crea una select para buscar un solo cliente

		public static Set<Cliente> ejecutaBusquedaCliente(int i){
			String sql="SELECT * FROM CLIENTES 	WHERE codigo = " + i;
			return ejecutaSQLCliente(sql);
			}
	}
