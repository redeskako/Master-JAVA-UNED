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


public class TipoServicio {

  private String tipoServicio;
  private double porcentaje;
  private int precio;
  private String color;
  
  public void TipoServicio(String tipoServicio, double porcentaje, int precio,String color){
	  this.tipoServicio=tipoServicio;
	  this.porcentaje=porcentaje;
	  this.precio=precio;
	  this.color=color;
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
    //calcula el numero total de citas de la base de datos
     public static int calculoTotalCitas(){
         Connection conn=abrirConexion();
         Statement stm= null;
         int totalcitas=0;
      try{
			stm= conn.createStatement();
         ResultSet rst=stm.executeQuery("select count(*) as totalcitas from citas");
     	rst.next();
			totalcitas= rst.getInt("totalcitas");
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
		System.out.print(totalcitas);
		return totalcitas;
		}       
     //====================================================================================
    //calcula el numero total de citas para un determinado tipoServicio
     public static int totalCitasTipoServicio(String tipoServicio){
         Connection conn=abrirConexion();
         Statement stm= null;
         int totaltiposerv=0;
      try{
			stm= conn.createStatement();
			String ingles=null;
			if(tipoServicio.equalsIgnoreCase("Corte")){
				ingles="Cut";
			}
			if(tipoServicio.equalsIgnoreCase("Tinte")){
				ingles="Dye";
			}
			if(tipoServicio.equalsIgnoreCase("Mechas")){
				ingles="Wicks";
			}
			if(tipoServicio.equalsIgnoreCase("Extensiones")){
				ingles="Extensions";
			}
			if(tipoServicio.equalsIgnoreCase("Alisado Japones")){
				ingles="Smoothed Japanese";
			}
			String sql="SELECT count(*) as totalcitas FROM citas where (tipoServicio='"+tipoServicio+"') or (tipoServicio='"+ingles+"')";
         ResultSet rst=stm.executeQuery(sql);
     	rst.next();
			totaltiposerv= rst.getInt("totalcitas");
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
	
		return totaltiposerv;
		}
     
    public double calculoPorcentaje(int totalcitas,int totalservicios){
    	double porcentaje=0;
         porcentaje=(totalservicios*100)/totalcitas;   	
    	return porcentaje;
    	
    }

	public String getTipoServicio() {
		return tipoServicio;
	}


	public void setTipoServicio(String tipoServicio) {
		this.tipoServicio = tipoServicio;
	}


	public double getPorcentaje() {
		return porcentaje;
	}


	public void setPorcentaje(double porcentaje) {
		this.porcentaje = porcentaje;
	}


	public int getPrecio() {
		return precio;
	}


	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

}
