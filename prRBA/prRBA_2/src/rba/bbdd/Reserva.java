package rba.bbdd;


	import java.sql.*;
import java.sql.Date;

	import rba.*;
	import java.util.*;
	import java.lang.*;
import configuracion.RbaException;


	public class Reserva implements Comparable<Reserva>{
		public static final int INCREMENTO=10;
		private int codigo;
		private Date fecha;
		private String hora;
		private String turno;
		private int usuario;
		private int cliente;
		private int mesa;

		public Reserva(){
			this.codigo = 0;
			this.fecha = null;
			this.hora = null;
			this.turno = null;
			this.usuario = 0;
			this.cliente= 0;
			this.mesa = 0;

		}

		public int compareTo(Reserva r){
			return this.hora.compareToIgnoreCase(r.getHora());
		}
		public int hashCode(){
			return this.hora.toUpperCase().hashCode();
		}
		public boolean equals(Object o){
			try{
				Reserva r= (Reserva) o;
				return this.hora.equalsIgnoreCase(r.hora);
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
		public Date getFecha(){
			return this.fecha;
		}
		public void setFecha(Date f){
			this.fecha=f;
		}
		public String getHora(){
			return this.hora;
		}
		public void setHora(String h){
			this.hora= h;
		}

		public String getTurno(){
			return this.turno;
		}

		public void setTurno(String t){
			this.turno = t;
		}

		public void setUsuario(int u){
			this.usuario = u;
		}

		public int getUsuario(){
			return this.usuario;
		}

		public void setCliente(int cl){
			this.cliente = cl;
		}

		public int getCliente(){
			return this.cliente;
		}

		public void setMesa(int me){
			this.mesa = me;
		}

		public int getMesa(){
			return this.mesa;
		}


		public String toString(){
			return "RESERVA:["+this.codigo+","+this.fecha+" "+this.hora+" "+this.turno+" "+this.cliente+"]";
		}
			private static Set<Reserva> ejecutaSQLReserva(ResultSet rst){
				Set<Reserva> sr= new TreeSet<Reserva>();
				try{
					while(rst.next()){
						Reserva r= new Reserva();
						r.setCodigo(rst.getInt("codigo"));
						r.setFecha(rst.getDate("fecha"));
						r.setHora(rst.getString("hora"));
						r.setTurno(rst.getString("turno"));
						r.setUsuario(rst.getInt("usuario"));
						r.setCliente(rst.getInt("cliente"));
						r.setMesa(rst.getInt("mesa"));
						sr.add(r);
					}
					rst.close();
				}catch(SQLException e){
					throw new RbaException("Problema con las bases de datos");
				}
				return sr;
			}
			private static Set<Reserva> ejecutaSQLReserva(String sql){
				Set<Reserva> sr=null;
				Connection conn=null;
				Statement stm= null;
				try{
					Class.forName("rba.mysql.jdbc.Driver").newInstance();
					conn=  DriverManager.getConnection("jdbc:mysql://172.26.0.50/rba?user=usuario&password=usuario");
					stm= conn.createStatement();
					ResultSet rst= stm.executeQuery(sql);
					//Proceso el select
					sr= ejecutaSQLReserva(rst);
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
				return sr;
			}

			//INSERTA UNA RESERVA

				private static void InsertarCliente(Date fecha,String hora,String turno,int usuario,int cliente,int mesa){

					Connection conn=null;
					Statement stm= null;

					try{
						Class.forName("com.mysql.jdbc.Driver").newInstance();
						conn=  DriverManager.getConnection("jdbc:mysql://172.26.0.50/rba?user=usuario&password=usuario");
						stm= conn.createStatement();
						ResultSet rst= stm.executeQuery("INSERT INTO RESERVAS (codigo,fecha,hora,turno,usuario,cliente,mesa)VALUES (NULL ,'"+fecha+"','"+hora+"','"+turno+"','"+usuario+"','"+cliente+"','"+mesa+")");
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

				// ACTUALIZA LA RESERVA

				private static void ActualizarReserva(int codigo,Date fecha,String hora,String turno,int usuario,int cliente,int mesa){

					Connection conn=null;
					Statement stm= null;

					try{
						Class.forName("com.mysql.jdbc.Driver").newInstance();
						conn=  DriverManager.getConnection("jdbc:mysql://172.26.0.50/rba?user=usuario&password=usuario");
						stm= conn.createStatement();
						ResultSet rst= stm.executeQuery("UPDATE RESERVAS SET fecha = '"+fecha+"',hora = '"+hora+"',turno = '"+turno+"',usuario = '"+usuario+"',cliente = '"+cliente+"',mesa = '"+mesa+"'WHERE codigo="+codigo+";");
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

				//ELIMINAR UNA RESERVA

				private static void EliminarReserva(int codigo){

					Connection conn=null;
					Statement stm= null;

					try{
						Class.forName("com.mysql.jdbc.Driver").newInstance();
						conn=  DriverManager.getConnection("jdbc:mysql://172.26.0.50/rba?user=usuario&password=usuario");
						stm= conn.createStatement();
						ResultSet rst= stm.executeQuery("DELETE FROM RESERVA WHERE codigo="+codigo+";");
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

				//PARA EL LISTADO DE RESERVAS

				private static int calculafilasReservas(){

					Connection conn=null;
					Statement stm= null;
					int total= 0;

					try{
						Class.forName("com.mysql.jdbc.Driver").newInstance();
						conn=  DriverManager.getConnection("jdbc:mysql://172.26.0.50/rba?user=usuario&password=usuario");
						stm= conn.createStatement();
						ResultSet rst= stm.executeQuery("SELECT COUNT(*) AS Total FROM RESERVAS");
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
			public static int calculaTotalPaginasR(){
				return calculafilasReservas()/INCREMENTO;
			}
		public static Set<Reserva> ejecutaListaReserva(int i){
			String sql="SELECT * FROM RESERVAS ORDER BY hora LIMIT 0,"+INCREMENTO;
			if (i>0){
				sql="SELECT * FROM RESERVAS ORDER BY hora LIMIT "+i*10+","+INCREMENTO;
			}
			return ejecutaSQLReserva(sql);
		}

		public static Set<Reserva> ejecutaBusquedaReserva(int i){
			String sql="SELECT * FROM RESERVAS WHERE codigo= " + i;
			return ejecutaSQLReserva(sql);
			}


}