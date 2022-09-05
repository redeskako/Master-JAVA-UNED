/*
 *   CARLOS LUIS SÁNCHEZ BOCANEGRA
                            DNI: 26017022C
             Expediente UNED: 55­04­00458
Domicilio: C/Cura Merino 2 2ºD 29011 Málaga

 */
package libreria.server;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.net.*;
import java.io.*;
import libreria.error.*;
import libreria.*;
import libreria.lenguaje.*;

public class CLibreriaServer extends Thread{
	public final static int INICIADO=1;
	public final static int PARADO=0;
	private long tInicio;
	private long tFinal;
	private String host;
	private String nombreHost;
	private Socket cliente;
	private int estado;
	
	public CLibreriaServer(Socket s){
		super();
		this.cliente=s;
		InetAddress direccion=s.getInetAddress();
		this.host= direccion.getHostAddress();
		this.nombreHost= direccion.getHostName();
		this.estado=CLibreriaServer.INICIADO;
	}
		private String dameValorLibro(String sql){
			String valores="";
			Connection con= null;
			Statement stm= null;
			try{
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				con= DriverManager.getConnection("jdbc:mysql://localhost/UNED?user=uned&password=uned");
				stm= con.createStatement();
				ResultSet rst= stm.executeQuery(sql);
				while (rst.next()){
					valores +=rst.getString("Nombre")+"|"+rst.getInt("IdLibro")+"|"+rst.getString("Autor")+"|"+rst.getString("Tema")+"|";
				}
				stm.close();
				con.close();
			}catch(SQLException e){
				throw new ErrorLibreriaException("_ERRORCONSULTA"+". "+e);
			}catch(Exception e){
				throw new ErrorLibreriaException("_ERRORNODEFINIDO"+". "+e);
			}finally{
				try{
					stm.close();
				}catch(Exception e){}				
				try{
					con.close();
				}catch(Exception e){}
			}			
			return valores;
		}
		private String dameValorSocio(String sql){
			String valores="";
			Connection con= null;
			Statement stm= null;
			try{
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				con= DriverManager.getConnection("jdbc:mysql://localhost/UNED?user=uned&password=uned");
				stm= con.createStatement();
				ResultSet rst= stm.executeQuery(sql);
				while (rst.next()){
					valores +=rst.getString("DNI")+"|"+rst.getInt("IdSocio")+"|"+rst.getString("Apellidos")+"|";
					valores += rst.getString("Nombre")+"|"+ rst.getString("Direccion")+"|";
					valores += rst.getDate("FechaAlta").toString()+"|";
				}
				stm.close();
				con.close();
			}catch(SQLException e){
				throw new ErrorLibreriaException("_ERRORCONSUNombreLTA"+". "+e);
			}catch(Exception e){
				throw new ErrorLibreriaException("_ERRORNODEFINIDO"+". "+e);
			}finally{
				try{
					stm.close();
				}catch(Exception e){}				
				try{
					con.close();
				}catch(Exception e){}
			}			
			return valores;
		}
		private String dameValorPrestamo(String sql){
			String valores="";
			Connection con= null;
			Statement stm= null;
			try{
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				con= DriverManager.getConnection("jdbc:mysql://localhost/UNED?user=uned&password=uned");
				stm= con.createStatement();
				ResultSet rst= stm.executeQuery(sql);
				while (rst.next()){
					valores +=rst.getInt("IdPrestamo")+"|"+rst.getString("IdSocio")+"|"+rst.getString("IdLibro")+"|";
					java.sql.Date fechaInicio= rst.getDate("FechaInicio");
					java.sql.Date fechaFin=null;
					try{
						fechaFin= rst.getDate("FechaFin");
					}catch(SQLException e){}
					valores += fechaInicio.toString()+"|"+ ((fechaFin==null)? "0000-00-00":fechaFin.toString())+"|";
				}
				stm.close();
				con.close();
			}catch(SQLException e){
				throw new ErrorLibreriaException("_ERRORCONSUNombreLTA"+". "+e);
			}catch(Exception e){
				throw new ErrorLibreriaException("_ERRORNODEFINIDO"+". "+e);
			}finally{
				try{
					stm.close();
				}catch(Exception e){}				
				try{
					con.close();
				}catch(Exception e){}
			}			
			return valores;
		}
		public void run(){
			this.tInicio= System.currentTimeMillis();
		try{
			InputStream is= this.cliente.getInputStream();
			OutputStream os= this.cliente.getOutputStream();
			ObjectInputStream entrada= new ObjectInputStream(is);
			
			String sql= (String) entrada.readObject();
			String obj= (String) entrada.readObject();
			String m="";
			if(obj.equalsIgnoreCase(CLibros.CLIBRO)){
				m= dameValorLibro(sql);
			}else if(obj.equalsIgnoreCase(CPrestamos.CPRESTAMO)){
				m= dameValorPrestamo(sql);
			}else{
				m= dameValorSocio(sql);
			}
			
			//Calculo los bytes a enviar primeramente
			ObjectOutputStream salida= new ObjectOutputStream(os);
			//Recepcion perfecta.
			salida.writeObject(Config.OK);
			salida.writeObject(m);
			
			entrada.close();
			this.estado=CLibreriaServer.PARADO;
			os.close();
			is.close();
		}catch(ClassNotFoundException e){
			throw new ErrorLibreriaException(""+e);
		}catch(IOException e){
			throw new ErrorLibreriaException(""+e);
		}finally{
			try{
				this.cliente.close();
				this.tFinal= System.currentTimeMillis();
			}catch(Exception e){}
		}
	}
	public long getTime(){
		return this.tFinal-this.tInicio;
	}
	public int estado(){
		return this.estado;
	}
	public String toString(){
		return ("Host:"+host+"\tNombre->"+nombreHost+"\n");
	}
}