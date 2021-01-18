package antonio.j2se.practica4bbdd.servidor.modelo;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import antonio.j2se.practica4bbdd.excepciones.ErrorComunicacionNegocioException;
import antonio.j2se.practica4bbdd.excepciones.SQLNegocioException;
import antonio.j2se.practica4bbdd.operaciones.OperacionesBBDD;
import antonio.j2se.practica4bbdd.servidor.vista.Servidor;

/**
 * Clase que representa una operacion de BBDD 
 * Mantiene referencias al socket,a la conexion de BBDD y al GUI
 * @author  Antonio Sánchez Antón
 * @since  1.0
 * @see java.lang.Runnable
 */
public class OperacionBBDD implements Runnable {
	protected Socket conexionSocket;
	protected Connection conexionBBDD;
	protected Servidor aplicacion;
	
	/**
	 * Constructor de OperacionBBDD
	 * @param conexionBBDD
	 * @param conexion
	 * @param aplicacion
	 */
	public OperacionBBDD(Connection conexionBBDD,Socket conexion,Servidor aplicacion){
		this.conexionSocket=conexion;
		this.conexionBBDD=conexionBBDD;
		this.aplicacion=aplicacion;
	}
	
	/**
	 * Metodo que se encarga de la gestion y tratamiento de las operaciones que se reciben
	 */
	public void run() {
	  DataInputStream entrada=null;
	  ObjectOutputStream salida=null;
	  try{	
        //Enviamos objetos
		  entrada=new DataInputStream(conexionSocket.getInputStream());
		  salida=new ObjectOutputStream(conexionSocket.getOutputStream());
          salida.flush();
		  //recibo la operacion
		  int operacion=entrada.readInt();
		  //recibo el numero de registros
		  int registros=entrada.readInt();
		  //recibo el offset
		  int offset=entrada.readInt();
		  OperacionesBBDD operacionRecibida=OperacionesBBDD.getOperacion(operacion);
		  this.aplicacion.setConsola("Operacion recibida: "+operacionRecibida+" registros: "+registros+" a partir del: "+offset);
		  String codigoPais=null;
		  String codigoIndicador=null;
		  int numPaises;
		  int numIndicadores;
		  switch (operacionRecibida) {
		    case ALLPAISES:
			    Collection<Pais> paises=obtenPaises(registros,offset,OperacionesBBDD.getSQLByOperacion(operacionRecibida));
			    enviaDatos(salida,paises.iterator());
			break;
		    case ALLINDICADORES:
			    Collection<Indicador> indicadores=obtenIndicadores(registros,offset,OperacionesBBDD.getSQLByOperacion(operacionRecibida));
			    enviaDatos(salida,indicadores.iterator());
			break;
		    case INDICADORESBYPAIS:
		    	codigoPais=entrada.readUTF();
		    	this.aplicacion.setConsola("Indicadores para pais "+codigoPais);
			    Collection<Indicador> indicadoresPais=obtenIndicadoresPais(registros,offset,OperacionesBBDD.getSQLByOperacion(operacionRecibida),codigoPais);
			    enviaDatos(salida,indicadoresPais.iterator());
			break;
		    case PAISESBYINDICADOR:
		    	codigoIndicador=entrada.readUTF();
		    	this.aplicacion.setConsola("Paises para Indicador "+codigoIndicador);
			    Collection<Pais> paisesIndicador=obtenPaisesIndicador(registros,offset,OperacionesBBDD.getSQLByOperacion(operacionRecibida),codigoIndicador);
			    enviaDatos(salida,paisesIndicador.iterator());
			break;
		    case ESTADISTICASBYINDICADORPAIS:
		    	codigoIndicador=entrada.readUTF();
		    	codigoPais=entrada.readUTF();
		    	this.aplicacion.setConsola("Estadisticas para indicador:"+codigoIndicador+ "y pais: "+codigoPais);
		    	Collection<Estadistica> estadisticas=obtenEstadisticasIndicadorPais(OperacionesBBDD.getSQLByOperacion(operacionRecibida),codigoIndicador,codigoPais);
			    enviaDatos(salida,estadisticas.iterator());
    	    break;	
		    case ALLPAISESCOUNT:
			    numPaises=obtenPaisesCount(OperacionesBBDD.getSQLByOperacion(operacionRecibida));
			    enviaDatos(salida,numPaises);
    		break;
		    case ALLINDICADORESCOUNT:
			    numIndicadores=obtenIndicadoresCount(OperacionesBBDD.getSQLByOperacion(operacionRecibida));
			    enviaDatos(salida,numIndicadores);
			break;
		    case INDICADORESBYPAISCOUNT:
		    	codigoPais=entrada.readUTF();
			    numIndicadores=obtenIndicadoresByPaisCount(OperacionesBBDD.getSQLByOperacion(operacionRecibida),codigoPais);
			    enviaDatos(salida,numIndicadores);
			break;
		    case PAISESBYINDICADORCOUNT:
		    	codigoIndicador=entrada.readUTF();
			    numPaises=obtenPaisesByIndicadorCount(OperacionesBBDD.getSQLByOperacion(operacionRecibida),codigoIndicador);
			    enviaDatos(salida,numPaises);
			break;
			
		 }
	  }catch(IOException ioe){
		  ioe.printStackTrace();
		  enviaError(salida,new ErrorComunicacionNegocioException(ioe));
	  }catch(ErrorComunicacionNegocioException ecne){
		  ecne.printStackTrace();
		  enviaError(salida,ecne);
	  }catch(SQLNegocioException sqle){
		  sqle.printStackTrace();
		  enviaError(salida,sqle);
	  }finally{
		  try {
			if(entrada!=null)  
			    entrada.close();
			if(salida!=null)
		        salida.close();
		  } catch (IOException e) {
				e.printStackTrace();
			} 
	  }
 	}
	
	
	/**
	 * Obtiene los Pais dentro de un limite y con un offset
	 * @param limite
	 * @param offset
	 * @param sql
	 * @return
	 * @throws SQLNegocioException
	 */
	protected Collection<Pais> obtenPaises(int limite,int offset,String sql)throws SQLNegocioException{
		ArrayList<Pais>retorno=new ArrayList<Pais>();
		PreparedStatement sentencia=null;
		try{
			sentencia=conexionBBDD.prepareStatement(sql);
			sentencia.setInt(1, limite);
			sentencia.setInt(2, offset);
			ResultSet resulset=sentencia.executeQuery();
			while (resulset.next()){
				String codigoPais=resulset.getString("COUNTRY_CODE");
				String descripcionPais=resulset.getString("COUNTRY_NAME");		
				Pais pais=new Pais(codigoPais,descripcionPais);
				retorno.add(pais);	
			}
			return retorno;
		}catch(SQLException sqle){
			sqle.printStackTrace();
			throw new SQLNegocioException("Ha habido un problema al obtener datos de paises de la BBDD.Consulte al Administrador,mas informacion en el log",sqle);
		}finally{
			try{
				if(sentencia!=null)
				    sentencia.close();
				if(conexionBBDD!=null)
				   conexionBBDD.close();
			}catch(SQLException sqle){
				sqle.printStackTrace();
				throw new SQLNegocioException(sqle);
			}
		}
	}
	
	/**
	 * Obtienene los indicadores dentro de un limite y con un offset
	 * @param limite
	 * @param offset
	 * @param sql
	 * @return
	 * @throws SQLNegocioException
	 */
	protected Collection<Indicador> obtenIndicadores(int limite,int offset,String sql)throws SQLNegocioException{
		ArrayList<Indicador>retorno=new ArrayList<Indicador>();
		PreparedStatement sentencia=null;
		try{
			sentencia=conexionBBDD.prepareStatement(sql);
			sentencia.setInt(1, limite);
			sentencia.setInt(2, offset);
			ResultSet resulset=sentencia.executeQuery();
			while (resulset.next()){
				String codigoIndic=resulset.getString("INDICATOR_CODE");
				String descripcionIndic=resulset.getString("INDICATOR_NAME");		
				Indicador indicador=new Indicador(codigoIndic,descripcionIndic);
				retorno.add(indicador);	
			}
			return retorno;
		}catch(SQLException sqle){
			sqle.printStackTrace();
			throw new SQLNegocioException("Ha habido un problema al obtener datos de indicadores de la BBDD.Consulte al Administrador,mas información en el log",sqle);
		}finally{
			try{
				if(sentencia!=null)
				    sentencia.close();
				if(conexionBBDD!=null)
				   conexionBBDD.close();
			}catch(SQLException sqle){
				sqle.printStackTrace();
				throw new SQLNegocioException(sqle);
			}
		}
	}
	

	/**
	 * Obtiene los indicadores por pais dentro de un limite y con un offset
	 * @param limite
	 * @param offset
	 * @param sql
	 * @param codPais
	 * @return
	 * @throws SQLNegocioException
	 */
	protected Collection<Indicador> obtenIndicadoresPais(int limite,int offset,String sql,String codPais)throws SQLNegocioException{
		ArrayList<Indicador>retorno=new ArrayList<Indicador>();
		PreparedStatement sentencia=null;
		try{
			sentencia=conexionBBDD.prepareStatement(sql);
			sentencia.setString(1, codPais);
			sentencia.setInt(2, limite);
			sentencia.setInt(3, offset);
			ResultSet resulset=sentencia.executeQuery();
			while (resulset.next()){
				String codigoIndic=resulset.getString("INDICATOR_CODE");
				String descripcionIndic=resulset.getString("INDICATOR_NAME");		
				Indicador indicador=new Indicador(codigoIndic,descripcionIndic);
				retorno.add(indicador);	
			}
			return retorno;
		}catch(SQLException sqle){
			sqle.printStackTrace();
			throw new SQLNegocioException("Ha habido un problema al obtener datos de indicadores por pais de la BBDD.Consulte al Administrador,mas informacion en el log",sqle);
		}finally{
			try{
				if(sentencia!=null)
				    sentencia.close();
				if(conexionBBDD!=null)
				   conexionBBDD.close();
			}catch(SQLException sqle){
				sqle.printStackTrace();
				throw new SQLNegocioException(sqle);
			}
		}
	}

	/**
	 * Obtiene los paises por indicador dentro de un limite y con un offset
	 * @param limite
	 * @param offset
	 * @param sql
	 * @param codIndicador
	 * @return
	 * @throws SQLNegocioException
	 */
	protected Collection<Pais> obtenPaisesIndicador(int limite,int offset,String sql,String codIndicador)throws SQLNegocioException{
		ArrayList<Pais>retorno=new ArrayList<Pais>();
		PreparedStatement sentencia=null;
		try{
			sentencia=conexionBBDD.prepareStatement(sql);
			sentencia.setString(1, codIndicador);
			sentencia.setInt(2, limite);
			sentencia.setInt(3, offset);
			ResultSet resulset=sentencia.executeQuery();
			while (resulset.next()){
				String codigoPais=resulset.getString("COUNTRY_CODE");
				String descripcionPais=resulset.getString("COUNTRY_NAME");		
				Pais pais=new Pais(codigoPais,descripcionPais);
				retorno.add(pais);	
			}
			return retorno;
		}catch(SQLException sqle){
			sqle.printStackTrace();
			throw new SQLNegocioException("Ha habido un problema al obtener datos de paises por indicador de la BBDD.Consulte al Administrador,mas informacion en el log",sqle);
		}finally{
			try{
				if(sentencia!=null)
				    sentencia.close();
				if(conexionBBDD!=null)
				   conexionBBDD.close();
			}catch(SQLException sqle){
				sqle.printStackTrace();
				throw new SQLNegocioException(sqle);
			}
		}
	}

    /**
     * Obtiene las estadisticas para un pais y un indicador
     * @param sql
     * @param codIndicador
     * @param codPais
     * @return
     * @throws SQLNegocioException
     */
	protected Collection<Estadistica> obtenEstadisticasIndicadorPais(String sql,String codIndicador,String codPais)throws SQLNegocioException{
		ArrayList<Estadistica>retorno=new ArrayList<Estadistica>();
		PreparedStatement sentencia=null;
		try{
			sentencia=conexionBBDD.prepareStatement(sql);
			sentencia.setString(1, codIndicador);
			sentencia.setString(2, codPais);
			ResultSet resulset=sentencia.executeQuery();
			while (resulset.next()){
				String codigoIndic=resulset.getString("INDICATOR_CODE");
				String codigoPais=resulset.getString("COUNTRY_CODE");
				int year=resulset.getInt("YEAR");
				float valor=resulset.getFloat("VALOR");
				Estadistica estadistica=new Estadistica(codigoPais,codigoIndic,year,valor);
				retorno.add(estadistica);	
			}
			return retorno;
		}catch(SQLException sqle){
			sqle.printStackTrace();
			throw new SQLNegocioException("Ha habido un problema al obtener datos de estadisticas de la BBDD.Consulte al Administrador,mas informacion en el log",sqle);
		}finally{
			try{
				if(sentencia!=null)
				    sentencia.close();
				if(conexionBBDD!=null)
				   conexionBBDD.close();
			}catch(SQLException sqle){
				sqle.printStackTrace();
				throw new SQLNegocioException(sqle);
			}
		}
	}	
	
	/**
	 * Obtiene el total de paises.Utilizado para mostrar al usuario el total de registros y de paginas
	 * @param sql
	 * @return
	 * @throws SQLNegocioException
	 */
	protected int obtenPaisesCount(String sql) throws SQLNegocioException{
		PreparedStatement sentencia=null;
		int numPaises=0;
		try{
			sentencia=conexionBBDD.prepareStatement(sql);
			ResultSet resulset=sentencia.executeQuery();
			while (resulset.next()){
				numPaises=resulset.getInt("CONTADOR");
			}
			return numPaises;
		}catch(SQLException sqle){
			sqle.printStackTrace();
			throw new SQLNegocioException("Ha habido un problema al obtener numero de paises de la BBDD.Consulte al Administrador,mas informacion en el log",sqle);
		}finally{
			try{
				if(sentencia!=null)
				    sentencia.close();
				if(conexionBBDD!=null)
				   conexionBBDD.close();
			}catch(SQLException sqle){
				sqle.printStackTrace();
				throw new SQLNegocioException(sqle);
			}
		}
		
	}

	
	/**
	 * Obtiene el total de indicadores.Utilizado para mostrar al usuario el total de registros y de paginas
	 * @param sql
	 * @return
	 * @throws SQLNegocioException
	 */
	protected int obtenIndicadoresCount(String sql) throws SQLNegocioException{
		PreparedStatement sentencia=null;
		int numIndicadores=0;
		try{
			sentencia=conexionBBDD.prepareStatement(sql);
			ResultSet resulset=sentencia.executeQuery();
			while (resulset.next()){
				numIndicadores=resulset.getInt("CONTADOR");
			}
			return numIndicadores;
		}catch(SQLException sqle){
			sqle.printStackTrace();
			throw new SQLNegocioException("Ha habido un problema al obtener numero de indicadores de la BBDD.Consulte al Administrador,mas informacion en el log",sqle);
		}finally{
			try{
				if(sentencia!=null)
				    sentencia.close();
				if(conexionBBDD!=null)
				   conexionBBDD.close();
			}catch(SQLException sqle){
				sqle.printStackTrace();
				throw new SQLNegocioException(sqle);
			}
		}
		
	}
	
	/**
	 * Obtiene el total de indicadores por pais.Utilizado para mostrar al usuario el total de registros y de paginas
	 * @param sql
	 * @param codPais
	 * @return
	 * @throws SQLNegocioException
	 */
	protected int obtenIndicadoresByPaisCount(String sql,String codPais) throws SQLNegocioException{
		PreparedStatement sentencia=null;
		int numIndicadores=0;
		try{
			sentencia=conexionBBDD.prepareStatement(sql);
			sentencia.setString(1, codPais);
			ResultSet resulset=sentencia.executeQuery();
			while (resulset.next()){
				numIndicadores=resulset.getInt("CONTADOR");
			}
			return numIndicadores;
		}catch(SQLException sqle){
			sqle.printStackTrace();
			throw new SQLNegocioException("Ha habido un problema al obtener numero de indicadores por pais de la BBDD.Consulte al Administrador,mas informacion en el log",sqle);
		}finally{
			try{
				if(sentencia!=null)
				    sentencia.close();
				if(conexionBBDD!=null)
				   conexionBBDD.close();
			}catch(SQLException sqle){
				sqle.printStackTrace();
				throw new SQLNegocioException(sqle);
			}
		}
		
	}

	/**
	 * Obtiene el total de paises por indicador.Utilizado para mostrar al usuario el total de registros y de paginas
	 * @param sql
	 * @param codIndicador
	 * @return
	 * @throws SQLNegocioException
	 */
	protected int obtenPaisesByIndicadorCount(String sql,String codIndicador) throws SQLNegocioException{
		PreparedStatement sentencia=null;
		int numPaises=0;
		try{
			sentencia=conexionBBDD.prepareStatement(sql);
			sentencia.setString(1, codIndicador);
			ResultSet resulset=sentencia.executeQuery();
			while (resulset.next()){
				numPaises=resulset.getInt("CONTADOR");
			}
			return numPaises;
		}catch(SQLException sqle){
			sqle.printStackTrace();
			throw new SQLNegocioException("Ha habido un problema al obtener numero de paises por indicador de la BBDD.Consulte al Administrador,mas informacion en el log",sqle);
		}finally{
			try{
				if(sentencia!=null)
				    sentencia.close();
				if(conexionBBDD!=null)
				   conexionBBDD.close();
			}catch(SQLException sqle){
				sqle.printStackTrace();
				throw new SQLNegocioException(sqle);
			}
		}
		
	}
	
	
	
	/**
	 * Gestion el envio de objetos al cliente
	 * @param salida
	 * @param datos
	 * @throws ErrorComunicacionNegocioException
	 */
	protected void enviaDatos(ObjectOutputStream salida,Iterator datos)throws ErrorComunicacionNegocioException{
	  try{	
			while(datos.hasNext()){
				salida.writeObject(datos.next());
			}
			salida.writeObject("FINDATOS");
	  }catch (IOException ioe){
		  ioe.printStackTrace();
		  throw new ErrorComunicacionNegocioException(ioe);
	  }
	}
	
	/**
	 * Gestion el envio de Integer al cliente
	 * @param salida
	 * @param datos
	 * @throws ErrorComunicacionNegocioException
	 */
	protected void enviaDatos(ObjectOutputStream salida,Integer dato)throws ErrorComunicacionNegocioException{
	  try{	
			salida.writeObject(dato);
			salida.writeObject("FINDATOS");
	  }catch (IOException ioe){
		  ioe.printStackTrace();
		  throw new ErrorComunicacionNegocioException(ioe);
	  }
	}
	
	/**
	 * Gestiona el envio de errores al cliente
	 * @param salida
	 * @param error
	 * @throws ErrorComunicacionNegocioException
	 */
	protected void enviaError(ObjectOutputStream salida,Exception error) {
	  try{	
		 salida.writeObject(error);    
		 salida.writeObject("FINDATOS");
	  }catch (IOException ioe){
		  ioe.printStackTrace();
		  
	  }
	}

}
