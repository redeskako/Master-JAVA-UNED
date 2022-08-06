package es.rizos.beansCitas;


import java.sql.Connection;
import java.sql.Date ;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import es.rizos.librerias.*;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
public class Cita {
        private int codigoCita;
	    private String dni;
	    private Date fecha;
	    private String tipoServicio;
	    private String turno;
	    private String activo;
	    private static final int INCREMENTO=2;

	public void Cita() {
                this.codigoCita=0;this.dni=null;
                this.fecha=null;this.tipoServicio=null;this.turno=null;
                this.activo=null;
	}
        public void Cita(int codigoCita,String dni,Date fecha,String tipoServicio,String turno,String activo){
            this.codigoCita=codigoCita;this.dni=dni;this.fecha=fecha;this.tipoServicio=tipoServicio;this.turno=turno;this.activo=activo;
        }
        public String toString(){
		return "Cita:["+this.codigoCita+","+this.dni+","+this.fecha.toString()+","+this.tipoServicio+","+this.turno+","+this.activo+"]";
	}
        public int compareTo(Cita c){
                return this.dni.compareToIgnoreCase(c.getDni());
	}
	public int hashCode(){
		return this.dni.toUpperCase().hashCode();
	}
	public boolean equals(Object o){
		try{
			Cita c= (Cita) o;
			return (this.codigoCita==c.codigoCita);
		}catch(ClassCastException e){
			throw new ErrorRizosBd("Error de casting");
		}
	}
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
        //====================================================================================
        public static void editacita(int cod,String servicionuevo,String turnonuevo){
            Connection conexion=abrirConexion();
            Statement stm= null;
                try{
			stm= conexion.createStatement();
                        String sql =("UPDATE citas set tipoServicio='"+servicionuevo+"',turno='"+turnonuevo+"' WHERE codigocita='"+cod+"'");
                        int numfila=stm.executeUpdate(sql);
                        conexion.close();
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
        }
        //====================================================================================
    	//====================================================================================
        public static void limpiaCitas(Date fecha){
            Connection conexion=abrirConexion();
            Statement stm= null;
            try{
			stm= conexion.createStatement();
			String sql =("UPDATE citas set activo='0' WHERE fecha<='"+fecha+"'");
            int numfila=stm.executeUpdate(sql);
            conexion.close();
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
        }
        //====================================================================================
        public static void eliminacita(int cod){
            Connection conexion=abrirConexion();
            Statement stm= null;
            String dninuevo=("dninuevo");String servicio=("Servicionuevo");
                try{
			stm= conexion.createStatement();
                        String sql=("UPDATE citas set activo='"+0+"' WHERE codigocita='"+cod+"'");
                        //String sql=("DELETE FROM citas WHERE codigocita="+cod);
                        int numfila=stm.executeUpdate(sql);
                        conexion.close();
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
        }       	
         //====================================================================================
         public static void cambiaDniCitas(String dninuevo,String dniantiguo){
                    Connection conexion=abrirConexion();
                    Statement stm= null;
                    try{
                    	stm= conexion.createStatement();
                        String sql=("UPDATE citas set dni='"+dninuevo+"' WHERE dni='"+dniantiguo+"'");
                        int numfila=stm.executeUpdate(sql);
                        conexion.close();
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
               
         }          	
        //====================================================================================
       
        public static boolean JornadaNoEsCompleta(Date fecha){
    		boolean completa=false;
    		Statement stm= null;
    		Connection con=null;
    		try{
    			con=abrirConexion();
               //CADENA URI, NO COINCIDE CON LOS USUARIOS QUE TENEMOS EN NUESTRA BASE DE DATOS
    			stm= con.createStatement();
                String sql="SELECT count(*) as Total FROM citas where (fecha='"+fecha+"') AND (activo='"+1+"')";
    			ResultSet rst= stm.executeQuery(sql);
    			rst.next();
    			completa= (rst.getInt("Total")< 5);
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
    		return completa;
    	}
    	//=========================================================================================================
        public static boolean NoEstaTurnoReservado(Date fecha,String turno){
    		boolean reservado=false;
    		Statement stm= null;
    		Connection con=null;
    		try{
    			
    			con=abrirConexion();
               //CADENA URI, NO COINCIDE CON LOS USUARIOS QUE TENEMOS EN NUESTRA BASE DE DATOS
    			stm= con.createStatement();
                String sql="SELECT count(*) as Total FROM citas where (fecha='"+fecha+"') AND (activo='"+1+"') AND (turno='"+turno+"')";
    			ResultSet rst= stm.executeQuery(sql);
    			rst.next();
    			reservado= (rst.getInt("Total")==0);
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
    		return reservado;

        }
        //====================================================================================
        //====================================================================================
        public static void registracita(Cita cita){
              	Connection conexion=abrirConexion();
                Statement stm= null;
                try{
			stm= conexion.createStatement();
                        PreparedStatement sentencia=conexion.prepareStatement(
					"INSERT INTO "+
					" citas( "+
					" codigocita, "+
					" dni, "+
					" fecha, "+
					" tipoServicio, "+
                    " turno,"+
					" activo )"+
					"VALUES(?,?,?,?,?,?)"

                        );
                        sentencia.setInt(1,cita.getCodigoCita());
                        sentencia.setString(2,cita.getDni());
                        sentencia.setDate(3,cita.getFecha());
                        sentencia.setString(4,cita.getTipoServicio());
                        sentencia.setString(5,cita.getTurno());
                        sentencia.setInt(6,1);
                        sentencia.executeUpdate();



		// el finally siempre se ejecuta
		}catch(SQLException e){
			throw new ErrorRizosBd("No puede conectar con la base de datos");
               	}finally{
			try{
				if (stm!=null){
					stm.close();
		            conexion.close();
				}
				if (conexion!=null){
					conexion.close();
				}
			}catch(Exception e){}
		}

        }

        //====================================================================================
        public  Vector citasDelDia(java.sql.Date dia,String dni){
                Vector conjuntodecitas=new Vector();
                boolean esCliente=false;
                Connection conexion=abrirConexion();
                Statement stm= null;
                try{
                		stm= conexion.createStatement();
                        String sql="SELECT * FROM citas where (fecha='"+dia+"')AND (dni='"+dni+"') AND (activo='"+1+"')";
                        ResultSet rst= stm.executeQuery(sql);
                        conjuntodecitas=construyeCitas(rst);
		// el finally siempre se ejecuta
		}catch(SQLException e){
			throw new ErrorRizosBd("No puede conectar con la base de datos");
		}finally{
			try{
				if (stm!=null){
					stm.close();
					conexion.close();
				}
				if (conexion!=null){
					conexion.close();
				}

			}catch(Exception e){}
		}

            return conjuntodecitas;
        }
        //========================================================================================
        //====================================================================================
        public  Vector citasDelDia(java.sql.Date dia){
                Vector conjuntodecitas=new Vector();
                boolean esCliente=false;
                Connection conexion=abrirConexion();
                Statement stm= null;
                try{
                		stm= conexion.createStatement();
                        String sql="SELECT * FROM citas where (fecha='"+dia+"') AND (activo='"+1+"')";
                        ResultSet rst= stm.executeQuery(sql);
                        conjuntodecitas=construyeCitas(rst);
		// el finally siempre se ejecuSELECT COUNT(*) AS Total FROM citas where dni='00000000-D'ta
		}catch(SQLException e){
			throw new ErrorRizosBd("No puede conectar con la base de datos");
		}finally{
			try{
				if (stm!=null){
					stm.close();
					conexion.close();
				}
				if (conexion!=null){
					conexion.close();
				}

			}catch(Exception e){}
		}

            return conjuntodecitas;
        }
        //========================================================================================
   // devuelve todas las citas del usuario
        public  Vector citasUsuario(String dni,int i){
            Vector conjuntodecitas=new Vector();
            boolean esCliente=false;
            Connection conexion=abrirConexion();
            Statement stm= null;
            try{
		     stm= conexion.createStatement();
		 	String sql="SELECT * FROM citas where (dni='"+dni+"') LIMIT 0,"+INCREMENTO;
            if (i>0){
            	sql="SELECT * FROM citas where (dni='"+dni+"') LIMIT "+i*2+","+INCREMENTO;
            }

                    ResultSet rst= stm.executeQuery(sql);
                    conjuntodecitas=construyeCitas(rst);




	// el finally siempre se ejecuta
	}catch(SQLException e){
		throw new ErrorRizosBd("No puede conectar con la base de datos");
	}finally{
		try{
			if (stm!=null){
				stm.close();
				conexion.close();
			}
			if (conexion!=null){
				conexion.close();
			}

		}catch(Exception e){}
	}

        return conjuntodecitas;
    }
        public Vector construyeCitas(ResultSet rst){

            Vector conjuntodecitas=new Vector();//es temporal dentro de la ejecucion,
            try{
                        while(rst.next()){
                                Cita cita=new Cita();
                                cita.setCodigoCita(rst.getInt("codigocita"));
                                cita.setDni(rst.getString("dni"));
                                cita.setFecha(rst.getDate("fecha"));
                                cita.setTipoServicio(rst.getString("tipoServicio"));
                                cita.setTurno(rst.getString("turno"));
                                cita.setActivo(rst.getString("activo"));

                                conjuntodecitas.add(cita);
                        }
                        rst.close();
                        }catch(SQLException e){
				throw new ErrorRizosBd("Problema con las bases de datos");
		}
		return conjuntodecitas;

        }


    	//=============================================
    	public static int calculaTotalPaginas(String dni){
    		int contador=calculafilas(dni)/INCREMENTO;
    		if((calculafilas(dni)%(INCREMENTO))== 0){
    			contador=contador-1;
    		}
    		return contador;
    	}


    	//================================================================
    		private static int calculafilas(String dni){
    			//Esto repite código, como hacemos para no repetirlo?????
    			// Lo dejo como ejercicio!
    			Connection conn=null;
    			Statement stm= null;
    			int total= 0;
    			try{
    				conn=abrirConexion();
    				stm= conn.createStatement();
    				ResultSet rst= stm.executeQuery("SELECT COUNT(*) AS Total FROM citas where dni='"+dni+"'");
    				   				
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

        //======================================================================================


	public String getActivo() {
		return activo;
	}
	public void setActivo(String activo) {
		this.activo = activo;
	}
	public int getCodigoCita() {
		return codigoCita;
	}
	public void setCodigoCita(int codigoCita) {
		this.codigoCita = codigoCita;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getTipoServicio() {
		return tipoServicio;
	}
	public void setTipoServicio(String tipoServicio) {
		this.tipoServicio = tipoServicio;
	}
	public String getTurno() {
		return turno;
	}
	public void setTurno(String turno) {
		this.turno = turno;
	}


}