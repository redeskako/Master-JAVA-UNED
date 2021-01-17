package rba.bbdd;


	import java.sql.*;

	import rba.*;
	import java.util.*;
	import java.lang.*;
import configuracion.RbaException;


	public class Mesa {
		public static final int INCREMENTO=10;
		private int codigo;
		private int nsillas;

		public Mesa(){
			this.codigo = 0;
			this.nsillas = 0;
		}

		public int getCodigo(){
			return this.codigo;
		}
		public void setCodigo(int c){
			this.codigo = c;
		}
		public int getSillas(){
			return this.nsillas;
		}

		public void setSillas(int s){
			this.nsillas = s;
		}

		public String toString(){
			return "MESA:["+this.codigo+","+this.nsillas+"]";
		}
			private static Set<Mesa> ejecutaSQLMesa(ResultSet rst){
				Set<Mesa> sm= new TreeSet<Mesa>();
				try{
					while(rst.next()){
						Mesa m= new Mesa();
						m.setCodigo(rst.getInt("codigo"));
						m.setSillas(rst.getInt("numSillas"));

					}
					rst.close();
				}catch(SQLException e){
					throw new RbaException("Problema con las bases de datos");
				}
				return sm;
			}
			private static Set<Mesa> ejecutaSQLMesa(String sql){
				Set<Mesa> sm=null;
				Connection conn=null;
				Statement stm= null;
				try{
					Class.forName("rba.mysql.jdbc.Driver").newInstance();
					conn=  DriverManager.getConnection("jdbc:mysql://172.26.0.50/rba?user=usuario&password=usuario");
					stm= conn.createStatement();
					ResultSet rst= stm.executeQuery(sql);
					//Proceso el select
					sm= ejecutaSQLMesa(rst);
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
				return sm;
			}

			//INSERTA UNA MESA

				private static void InsertarMesa(int codigo,int ns){

					Connection conn=null;
					Statement stm= null;

					try{
						Class.forName("com.mysql.jdbc.Driver").newInstance();
						conn=  DriverManager.getConnection("jdbc:mysql://172.26.0.50/rba?user=usuario&password=usuario");
						stm= conn.createStatement();
						ResultSet rst= stm.executeQuery("INSERT INTO MESAS (codigo,numSillas)VALUES ('"+codigo+"','"+ns+"')");
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

				// ACTUALIZA UNA MESA

				private static void ActualizarMesa(int codigo,int ns){

					Connection conn=null;
					Statement stm= null;

					try{
						Class.forName("com.mysql.jdbc.Driver").newInstance();
						conn=  DriverManager.getConnection("jdbc:mysql://172.26.0.50/rba?user=usuario&password=usuario");
						stm= conn.createStatement();
						ResultSet rst= stm.executeQuery("UPDATE MESAS SET numSillas = '"+ns+"'WHERE codigo="+codigo+";");
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

				//ELIMINAR UNA MESA

				private static void EliminarMesa(int codigo){

					Connection conn=null;
					Statement stm= null;

					try{
						Class.forName("com.mysql.jdbc.Driver").newInstance();
						conn=  DriverManager.getConnection("jdbc:mysql://172.26.0.50/rba?user=usuario&password=usuario");
						stm= conn.createStatement();
						ResultSet rst= stm.executeQuery("DELETE FROM MESAS WHERE codigo="+codigo+";");
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

				//PARA EL LISTADO DE MESAS

				private static int calculafilasMesas(){

					Connection conn=null;
					Statement stm= null;
					int total= 0;

					try{
						Class.forName("com.mysql.jdbc.Driver").newInstance();
						conn=  DriverManager.getConnection("jdbc:mysql://172.26.0.50/rba?user=usuario&password=usuario");
						stm= conn.createStatement();
						ResultSet rst= stm.executeQuery("SELECT COUNT(*) AS Total FROM MESAS");
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
			public static int calculaTotalPaginasM(){
				return calculafilasMesas()/INCREMENTO;
			}
		//public static Set<Mesa> ejecutaListaMesa(int i){
		//	String sql="SELECT * FROM MESAS ORDER BY codigo LIMIT 0,"+INCREMENTO;
		//	if (i>0){
		//		sql="SELECT * FROM MESAS ORDER BY codigo LIMIT "+i*10+","+INCREMENTO;
		//	}
		//	return ejecutaSQLMesa(sql);
		//}


		// funcion para crear select segun unos parametros
		// campo: con este parametro podemos elegir el campo de ordenacion y la opcion de busqueda
		// esta se activaria si en la caja de texto de busqueda hubiera algo que no sea blanco o null
		// texto: proviene de la caja de texto, que nos sirve para introducir los datos de busqueda
		// i: datos de inicio para paginar la seleccion

		public static Set<Mesa> ejecutaListaCampoMesa(int i, String campo, String texto){
			String sql="SELECT * FROM MESAS ORDER BY" + campo + " LIMIT 0,"+INCREMENTO;

			if (texto != "") {

			    sql="SELECT * FROM MESAS where " + campo + " like '" + texto + " order BY " + campo + " LIMIT 0,"+INCREMENTO;
				if (i>0){
					sql="SELECT * FROM MESAS where " + campo + " like '" + texto + "' ORDER BY" + campo + "LIMIT "+i*10+","+INCREMENTO;
				}

			}
			return ejecutaSQLMesa(sql);
		}
		public static Set<Mesa> ejecutaBusquedaMesa(int i){
			String sql="SELECT * FROM MESA WHERE codigo= " + i;
			return ejecutaSQLMesa(sql);
			}
}