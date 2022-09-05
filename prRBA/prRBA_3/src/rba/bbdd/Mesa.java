package rba.bbdd;


	import java.sql.*;

	import rba.*;
	import java.util.*;
	import java.lang.*;
import configuracion.RbaException;


	public class Mesa implements Comparable{
		public static final int INCREMENTO=5;
		private int codigo;
		private int nsillas;

		public Mesa(){
			this.codigo = 0;
			this.nsillas = 0;

		}
		//para el javaBean
		public Mesa(int co,int ns){
			this.codigo = co;
			this.nsillas = ns;
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
						try{//controlo el caso de que no relleno el campo numero de sillas
							m.setSillas(rst.getInt("numSillas"));
						}catch(Exception e){
							m.setSillas(0);
						}

						sm.add(m);
					}
					//rst.close();
				}catch(SQLException e){
					throw new RbaException("Problema con las bases de datos");
				}catch(ClassCastException e){
					throw new RbaException("No está cargado el driver");
				}

				return sm;
			}


			private static Set<Mesa> ejecutaSQLMesa(String sql){
				Set<Mesa> sm=null;
				Connection conn=null;
				Statement stm= null;
				try{
					Class.forName("com.mysql.jdbc.Driver").newInstance();
					conn=  DriverManager.getConnection("jdbc:mysql://localhost/rba?user=usuario&password=usuario");
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

				public void InsertarMesa(int ns){
					String sql;
					Connection conn=null;
					Statement stm= null;

					try{
						Class.forName("com.mysql.jdbc.Driver").newInstance();
						conn=  DriverManager.getConnection("jdbc:mysql://localhost/rba","usuario","usuario");
						sql = "INSERT INTO MESAS (numSillas)VALUES ('"+ns+"')";
						//executeUpdate sirve para Insert, Update y delete
						PreparedStatement rst= conn.prepareStatement(sql);
						rst.executeUpdate();
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

				public void ActualizarMesa(int codigo,int ns){

					Connection conn=null;
					Statement stm= null;

					try{
						Class.forName("com.mysql.jdbc.Driver").newInstance();
						conn=  DriverManager.getConnection("jdbc:mysql://localhost/rba?user=usuario&password=usuario");
						stm= conn.createStatement();

						String sql = "UPDATE MESAS SET numSillas = '"+ns+"'WHERE codigo="+codigo+";";
						PreparedStatement rst= conn.prepareStatement(sql);
						rst.executeUpdate();
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

				public void EliminarMesa(int codigo){

					Connection conn=null;
					Statement stm= null;

					try{
						Class.forName("com.mysql.jdbc.Driver").newInstance();
						conn=  DriverManager.getConnection("jdbc:mysql://localhost/rba?user=usuario&password=usuario");
						stm= conn.createStatement();

						String sql = "DELETE FROM MESAS WHERE codigo="+codigo+";";
						PreparedStatement rst= conn.prepareStatement(sql);
						rst.executeUpdate();
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

				public static int calculafilasMesas(){

					Connection conn=null;
					Statement stm= null;
					int total= 0;

					try{
						Class.forName("com.mysql.jdbc.Driver").newInstance();
						conn=  DriverManager.getConnection("jdbc:mysql://localhost/rba?user=usuario&password=usuario");
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
		public static Set<Mesa> ejecutaListaMesa(int i){
			String sql="SELECT * FROM MESAS ORDER BY codigo LIMIT 0,"+INCREMENTO;
			if (i>0){
				sql="SELECT * FROM MESAS ORDER BY codigo LIMIT "+i*5+","+INCREMENTO;
			}
			return ejecutaSQLMesa(sql);
		}

		// funcion para crear select segun unos parametros
		// campo: con este parametro podemos elegir el campo de ordenacion y la opcion de busqueda
		// esta se activaria si en la caja de texto de busqueda hubiera algo que no sea blanco o null
		// texto: proviene de la caja de texto, que nos sirve para introducir los datos de busqueda
		// i: datos de inicio para paginar la seleccion

		public static Set<Mesa> ejecutaListaCampoMesa(int i, String campo, String texto){
			String sql="SELECT * FROM MESAS ORDER BY " + campo + " LIMIT 0,"+INCREMENTO;

			if (texto != "") {
			    sql="SELECT * FROM MESAS where " + campo + " like '%" + texto + "%' ORDER BY " + campo + " LIMIT 0,"+INCREMENTO;
				if (i>0){
					sql="SELECT * FROM MESAS where " + campo + " like '%" + texto + "%' ORDER BY" + campo + "LIMIT "+i*5+","+INCREMENTO;
				}
			}
			else{
					if (i>0){
						sql="SELECT * FROM MESAS ORDER BY " + campo + " LIMIT  "+i*5+","+INCREMENTO;
					}
				}
			return ejecutaSQLMesa(sql);
		}

		public static Set<Mesa> ejecutaBusquedaMesa(int i){
			String sql="SELECT * FROM MESAS WHERE codigo= " + i;
			return ejecutaSQLMesa(sql);
			}
		public int compareTo(Object o) {
			// TODO Auto-generated method stub
			return 1;
		}
		public static boolean estaOcupado(Integer Codigo){
			boolean estaOcupado=false;
			Connection conn=null;
			Statement stm= null;
			try{
				String sql="SELECT count(*) as Total FROM MESAS WHERE (codigo="+Codigo+")";
				
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				conn=  DriverManager.getConnection("jdbc:mysql://localhost/rba?user=usuario&password=usuario");
				stm= conn.createStatement();			
				
				ResultSet rst= stm.executeQuery(sql);
				rst.next();
				estaOcupado= (rst.getInt("Total")==1);
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
			return estaOcupado;
		}


}