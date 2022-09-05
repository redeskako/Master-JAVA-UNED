package antonio.j2ee.practica1Thinspo.channel.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import antonio.j2ee.practica1Thinspo.bbdd.modelo.ModeloDAO;
import antonio.j2ee.practica1Thinspo.excepciones.SQLNegocioException;
import antonio.j2ee.practica1Thinspo.usuario.modelo.Usuario;
import antonio.j2ee.practica1Thinspo.video.modelo.Agrupador;


/**
 * Clase que extiende antonio.j2ee.practica1Thinspo.bbdd.modelo.ModeloDAO
 * Implementa el patron DAO encapsulando el acceso a la tabla Channel
 * @author  Antonio Sánchez Antón
 * @since  1.0
 * @see antonio.j2ee.practica1Thinspo.bbdd.modelo.ModeloDAO
 */
public class ChannelDAO extends ModeloDAO {
	private static final String QUERYBUSQUEDA="SELECT * FROM CHANNEL ORDER BY username ASC LIMIT ? OFFSET ?";
	private static final String QUERYCOUNT="SELECT COUNT(1) AS CONTADOR FROM CHANNEL";
	private static final String QUERYBUSQUEDAPK="SELECT * FROM CHANNEL WHERE username=?";
	private static final String QUERYMASVIEWS="select username,channelviews from channel order by channelviews desc LIMIT 7 OFFSET 0";
	private static final String QUERYMASSUBS="select username,SUBSCRIBERSCOUNT from channel order by SUBSCRIBERSCOUNT desc LIMIT 5 OFFSET 0";
	protected ChannelDAO(){}
	
	/**
	 * Devuelve aquellos canales que cumplan el Filtro introducido ordenado por pk dentro del limite y con el offset dados
	 * @param filtro
	 * @param limite
	 * @param offset
	 * @return
	 * @throws SQLNegocioException
	 */
	public Collection<Channel> findChannels(int limite,int offset) throws SQLNegocioException{
	       ArrayList<Channel>channels=new ArrayList<Channel>();
		   PreparedStatement consulta=null;
	       Connection conexion=null;
	       ResultSet resulset=null;
	       try {
	              // Apertura de una conexión
	          conexion=getConnection();
              // Preparación de la consulta
              consulta=conexion.prepareStatement(QUERYBUSQUEDA);
              consulta.setInt(1, limite);
              consulta.setInt(2, offset);
		      resulset=consulta.executeQuery();
			  while (resulset.next()){
					String username=resulset.getString("USERNAME");
					long channelviews=resulset.getLong("CHANNELVIEWS");
					Date datefavoritevideosearched=resulset.getDate("DATEFAVORITEVIDEOSSEARCHED");
					Date datejoined=resulset.getDate("DATEJOINED");
					Date datefriendssearch=resulset.getDate("DATEFRIENDSSEARCHED");
					Date datesubscribedtosearched=resulset.getDate("DATESUBSCRIBEDTOSEARCHED");
					String location=resulset.getString("LOCATION");
					double scoresna=resulset.getDouble("SCORESNA");
					double scoresnanormalized=resulset.getDouble("SCORESNANORMALIZED");
					long subscriberscount=resulset.getLong("SUBSCRIBERSCOUNT");
					Date timecaptured=resulset.getDate("TIMECAPTURED");
					Date timefavcaptured=resulset.getDate("TIMEFAVCAPTURED");
					Date timescorecalculated=resulset.getDate("TIMESCORECALCULATED");
					Date timesubcaptured=resulset.getDate("TIMESUBCAPTURED");
					long uploadviews=resulset.getLong("UPLOADVIEWS");
					Channel canal=new Channel(username, channelviews,
							datefavoritevideosearched, datejoined,
							datefriendssearch, datesubscribedtosearched,
							location, scoresna, scoresnanormalized,
							subscriberscount, timecaptured, timefavcaptured,
							timescorecalculated, timesubcaptured,uploadviews);
					channels.add(canal);	
			 }
			 return channels;
			}catch(SQLException sqle){
				sqle.printStackTrace();
				throw new SQLNegocioException("Ha habido un problema al obtener datos de Channels de la BBDD.Consulte al Administrador,mas informacion en el log",sqle);
			}finally{
				try{
					if(consulta!=null)
					    consulta.close();
					if(conexion!=null)
					   conexion.close();
				}catch(SQLException sqle){
					sqle.printStackTrace();
					throw new SQLNegocioException(sqle);
				}
			}

	}
	
	/**
	 * Devuelve el total de Channles
	 * @return
	 * @throws SQLNegocioException
	 */
	public int countChannels() throws SQLNegocioException{
		   PreparedStatement consulta=null;
	       Connection conexion=null;
	       ResultSet resulset=null;
	       try {
	              // Apertura de una conexión
	          conexion=getConnection();
	              // Preparación de la consulta
	          consulta=conexion.prepareStatement(QUERYCOUNT);
	          resulset=consulta.executeQuery();
	          resulset.next();
	          return resulset.getInt("CONTADOR");
	       } catch(SQLException sqle){
				sqle.printStackTrace();
				throw new SQLNegocioException("Ha habido un problema al buscar canales en BBDD.Consulte al Administrador,mas informacion en el log",sqle);
			}finally{
				try{
					if(consulta!=null)
					    consulta.close();
					if(conexion!=null)
					   conexion.close();
				}catch(SQLException sqle){
					sqle.printStackTrace();
					throw new SQLNegocioException(sqle);
				}
			}
	}
	
	
	   /**
	    * Comprueba si un determinado canal existe
	    * @param username
	    * @return
	    * @throws SQLNegocioException
	    */
	   public Boolean findChannel(String username) throws SQLNegocioException{
		   PreparedStatement consulta=null;
	       Connection conexion=null;
	       ResultSet resulset=null;
	       Boolean retorno=Boolean.FALSE;
	       try {
	              // Apertura de una conexión
	          conexion=getConnection();
	              // Preparación de la consulta
	          consulta=conexion.prepareStatement(QUERYBUSQUEDAPK);
	          consulta.setString(1, username);
			  resulset=consulta.executeQuery();
			  if (resulset.next()){
				  retorno=Boolean.TRUE;
			  }
	          return retorno;
	       }catch(SQLException sqle){
				sqle.printStackTrace();
				throw new SQLNegocioException("Ha habido un problema al buscar Channel en BBDD.Consulte al Administrador,mas informacion en el log",sqle);
			}finally{
				try{
					if(consulta!=null)
					    consulta.close();
					if(conexion!=null)
					   conexion.close();
				}catch(SQLException sqle){
					sqle.printStackTrace();
					throw new SQLNegocioException(sqle);
				}
			}
	   }


	   /**
	    * Realiza la insercion de un nuevo canal
	    * @param canal
	    * @return
	    * @throws SQLNegocioException
	    */
	    public Channel create(Channel canal) throws SQLNegocioException{
	        Statement consulta=null;
	        Connection conexion=null;
	        StringBuffer sqlColumns=new StringBuffer();
	        StringBuffer sqlValues=new StringBuffer();
	        SimpleDateFormat sdfMySQL=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        try {
	           sqlColumns.append("INSERT INTO CHANNEL (");
	           sqlValues.append(" VALUES (");

	               // Apertura de una conexión
	           conexion=getConnection();
	               // Preparación de la consulta
	           consulta=conexion.createStatement();
	           sqlColumns.append("username");
	           sqlValues.append("'"+canal.getUsername()+"'");
	           sqlColumns.append(",channelviews");
	           sqlValues.append(","+canal.getChannelviews());
	           //Campo Opcional,puede llegar nulo
	           if(canal.getDatefavoritevideosearched()!=null){
	        	   sqlColumns.append(",datefavoritevideossearched");
	        	   sqlValues.append(","+sdfMySQL.format(canal.getDatefavoritevideosearched())+"'");
	           }
	         //Campo Opcional,puede llegar nulo
	           if(canal.getDatejoined()!=null){
	        	   sqlColumns.append(",datejoined");
	        	   sqlValues.append(",'"+sdfMySQL.format(canal.getDatejoined())+"'");
	           }
	         //Campo Opcional,puede llegar nulo
	           if(canal.getDatefriendssearch()!=null){
	        	   sqlColumns.append(",datefriendssearched");
	        	   sqlValues.append(",'"+sdfMySQL.format(canal.getDatefriendssearch())+"'");
	           }
	         //Campo Opcional,puede llegar nulo
	           if(canal.getDatesubscribedtosearched()!=null){
	        	   sqlColumns.append(",datesubscribedtosearched");
	        	   sqlValues.append(",'"+sdfMySQL.format(canal.getDatesubscribedtosearched())+"'");
	           }
	         //Campo Opcional,puede llegar nulo
	           if(canal.getLocation()!=null){
	        	   sqlColumns.append(",location");
	        	   sqlValues.append(",'"+canal.getLocation()+"'");
	           }
	           sqlColumns.append(",scoresna");
	           sqlValues.append(","+canal.getScoresna());
        	   sqlColumns.append(",scoresnanormalized");
        	   sqlValues.append(","+canal.getScoresnanormalized());
        	   sqlColumns.append(",subscriberscount");
        	   sqlValues.append(","+canal.getSubscriberscount());
  	         //Campo Opcional,puede llegar nulo
	           if(canal.getTimecaptured()!=null){
	        	   sqlColumns.append(",timecaptured");
	        	   sqlValues.append(",'"+sdfMySQL.format(canal.getTimecaptured())+"'");
	           }
		         //Campo Opcional,puede llegar nulo
	           if(canal.getTimefavcaptured()!=null){
	        	   sqlColumns.append(",timefavcaptured");
	        	   sqlValues.append(",'"+sdfMySQL.format(canal.getTimefavcaptured())+"'");
	           }
		         //Campo Opcional,puede llegar nulo
	           if(canal.getTimescorecalculated()!=null){
	        	   sqlColumns.append(",timescorecalculated");
	        	   sqlValues.append(",'"+sdfMySQL.format(canal.getTimescorecalculated())+"'");
	           }
		         //Campo Opcional,puede llegar nulo
	           if(canal.getTimesubcaptured()!=null){
	        	   sqlColumns.append(",timesubcaptured");
	        	   sqlValues.append(",'"+sdfMySQL.format(canal.getTimesubcaptured())+"'");
	           }
	           sqlColumns.append(",uploadviews");
	           sqlValues.append(","+canal.getUploadviews());
	          
	           sqlColumns.append(")");
	           sqlValues.append(")");
	           	               // Ejecución de la consulta
	           consulta.executeUpdate(sqlColumns.toString()+sqlValues.toString());
	           // Devolver el canal creado
	           return canal;
	        }catch(SQLException sqle) {
	        	System.err.println("Error al crear un nuevo canal");
	        	sqle.printStackTrace();
	        	throw new SQLNegocioException("Ha habido un problema al crear un canal.Consulte al Administrador,mas informacion en el log",sqle);
	        }
	        finally {
	           try {
	              if(consulta!=null) {
	                  consulta.close();
	              }
	              if(conexion!=null) {
	                 conexion.close();
	              }
	           }catch(Exception ex) {
	        	    System.err.println("Error cierre");
	        	    ex.printStackTrace();
	        	    throw new SQLNegocioException(ex);
	               }
	        }
	        
	    }
	    
	    /**
	     * Realiza la modificacion de un canal
	     * @param canal
	     * @return
	     * @throws SQLNegocioException
	     */
	    public Channel modificar(Channel canal) throws SQLNegocioException{
	        Statement consulta=null;
	        Connection conexion=null;
	        StringBuffer sql=new StringBuffer();
	        Boolean masDe1Campo=Boolean.FALSE;
	        SimpleDateFormat sdfMySQL=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        try {
	               // Apertura de una conexión
	           conexion=getConnection();
	               // Preparación de la consulta
	           consulta=conexion.createStatement();
	           sql.append("UPDATE CHANNEL SET ");

	           if(canal.getChannelviews()!=null){
	        	    if (masDe1Campo)
	        	    	sql.append(",channelviews="+canal.getChannelviews());	
	        	    else{
	        	    	masDe1Campo=Boolean.TRUE;
	        	    	sql.append("channelviews="+canal.getChannelviews());
	        	    }
	           }    
	           if(canal.getDatefavoritevideosearched()!=null) {
	        	    if (masDe1Campo)
	        	    	sql.append(",datefavoritevideossearched="+"'"+sdfMySQL.format(canal.getDatefavoritevideosearched())+"'");	
	        	    else{
	        	    	masDe1Campo=Boolean.TRUE;
	        	    	sql.append("datefavoritevideossearched="+"'"+sdfMySQL.format(canal.getDatefavoritevideosearched())+"'");
	        	    }
	           } 
	           if(canal.getDatejoined()!=null) {
	        	    if (masDe1Campo)
	        	    	sql.append(",datejoined="+"'"+sdfMySQL.format(canal.getDatejoined())+"'");	
	        	    else{
	        	    	masDe1Campo=Boolean.TRUE;
	        	    	sql.append("datejoined="+"'"+sdfMySQL.format(canal.getDatejoined())+"'");	
	        	    }
	           } 
	           if(canal.getDatefriendssearch()!=null) {
	        	    if (masDe1Campo)
	        	    	sql.append(",datefriendssearched="+"'"+sdfMySQL.format(canal.getDatefriendssearch())+"'");		
	        	    else{
	        	    	masDe1Campo=Boolean.TRUE;
	        	    	sql.append("datefriendssearched="+"'"+sdfMySQL.format(canal.getDatefriendssearch())+"'");	
	        	    }
	           } 
	           if(canal.getDatesubscribedtosearched()!=null) {
	        	    if (masDe1Campo)
	        	    	sql.append(",datesubscribedtosearched="+"'"+sdfMySQL.format(canal.getDatesubscribedtosearched())+"'");		
	        	    else{
	        	    	masDe1Campo=Boolean.TRUE;
	        	    	sql.append("datesubscribedtosearched="+"'"+sdfMySQL.format(canal.getDatesubscribedtosearched())+"'");	
	        	    }
	           } 	           
	           if(canal.getLocation()!=null){
	        	    if (masDe1Campo)
	        	    	sql.append(",location='"+canal.getLocation()+"'");	
	        	    else{
	        	    	masDe1Campo=Boolean.TRUE;
	        	    	sql.append("location='"+canal.getLocation()+"'");
	        	    }
	           }    
	           if(canal.getScoresna()!=null){
	        	    if (masDe1Campo)
	        	    	sql.append(",scoresna="+canal.getScoresna());	
	        	    else{
	        	    	masDe1Campo=Boolean.TRUE;
	        	    	sql.append("scoresna="+canal.getScoresna());
	        	    }
	           }    
	           if(canal.getScoresnanormalized()!=null){
	        	    if (masDe1Campo)
	        	    	sql.append(",scoresnanormalized="+canal.getScoresnanormalized());	
	        	    else{
	        	    	masDe1Campo=Boolean.TRUE;
	        	    	sql.append("scoresnanormalized="+canal.getScoresnanormalized());
	        	    }
	           }    
	           if(canal.getSubscriberscount()!=null){
	        	    if (masDe1Campo)
	        	    	sql.append(",subscriberscount="+canal.getSubscriberscount());	
	        	    else{
	        	    	masDe1Campo=Boolean.TRUE;
	        	    	sql.append("subscriberscount="+canal.getSubscriberscount());
	        	    }
	           }    
	           if(canal.getTimecaptured()!=null) {
	        	    if (masDe1Campo)
	        	    	sql.append(",timecaptured="+"'"+sdfMySQL.format(canal.getTimecaptured())+"'");
	        	    else{
	        	    	masDe1Campo=Boolean.TRUE;
	        	    	sql.append("timecaptured="+"'"+sdfMySQL.format(canal.getTimecaptured())+"'");
	        	    }
	           } 
	           if(canal.getTimefavcaptured()!=null) {
	        	    if (masDe1Campo)
	        	    	sql.append(",timefavcaptured="+"'"+sdfMySQL.format(canal.getTimefavcaptured())+"'");	
	        	    else{
	        	    	masDe1Campo=Boolean.TRUE;
	        	    	sql.append("timefavcaptured="+"'"+sdfMySQL.format(canal.getTimefavcaptured())+"'");
	        	    }
	           } 
	           if(canal.getTimescorecalculated()!=null) {
	        	    if (masDe1Campo)
	        	    	sql.append(",timescorecalculated="+"'"+sdfMySQL.format(canal.getTimescorecalculated())+"'");	
	        	    else{
	        	    	masDe1Campo=Boolean.TRUE;
	        	    	sql.append("timescorecalculated="+"'"+sdfMySQL.format(canal.getTimescorecalculated())+"'");
	        	    }
	           } 
	           if(canal.getTimesubcaptured()!=null) {
	        	    if (masDe1Campo)
	        	    	sql.append(",timesubcaptured="+"'"+sdfMySQL.format(canal.getTimesubcaptured())+"'");	
	        	    else{
	        	    	masDe1Campo=Boolean.TRUE;
	        	    	sql.append("timesubcaptured="+"'"+sdfMySQL.format(canal.getTimesubcaptured())+"'");	
	        	    }
	           } 
	           if(canal.getUploadviews()!=null){
	        	    if (masDe1Campo)
	        	    	sql.append(",uploadviews="+canal.getUploadviews());	
	        	    else{
	        	    	masDe1Campo=Boolean.TRUE;
	        	    	sql.append("uploadviews="+canal.getUploadviews());
	        	    }
	           }  
               sql.append(" WHERE username='"+canal.getUsername()+"'");
	               // Ejecución de la consulta
	           consulta.executeUpdate(sql.toString());
	           // Devolver el canal modificado
	           return canal;
	        }catch(SQLException sqle) {
	        	System.err.println("Error al modificar un nuevo canal");
	        	throw new SQLNegocioException("Ha habido un problema al modificar un canal.Consulte al Administrador,mas informacion en el log",sqle);
	        }
	        finally {
	           try {
	              if(consulta!=null) {
	                  consulta.close();
	              }
	              if(conexion!=null) {
	                 conexion.close();
	              }
	           }catch(Exception ex) {
	        	    System.err.println("Error cierre");
	        	    ex.printStackTrace();
	        	    throw new SQLNegocioException(ex);
	               }
	        }
	        
	    }
	    
	    /**
	     * Devuelve los datos que 7 Channels con mas Views para poderlos representar
	     * @return
	     * @throws SQLNegocioException
	     */
		public Collection <Agrupador> findChannelsMasViews()throws SQLNegocioException{
		       PreparedStatement consulta=null;
		       Connection conexion=null;
		       ResultSet resulset=null;
		       try{
				   Collection <Agrupador>retorno=new ArrayList<Agrupador>();
		           // Apertura de una conexión
		           conexion=getConnection();
		          // Preparación de la consulta
		           consulta=conexion.prepareStatement(QUERYMASVIEWS);
			       resulset=consulta.executeQuery();
				   while (resulset.next()){
					  String username=resulset.getString("USERNAME"); 
					  Long channelviews=resulset.getLong("CHANNELVIEWS");
					  retorno.add(new Agrupador("User: "+username+" Views: "+channelviews, channelviews));
				  }
				   return retorno;
		       }catch(SQLException sqle){
					sqle.printStackTrace();
					throw new SQLNegocioException("Ha habido un problema al obtener datos de Channels de la BBDD.Consulte al Administrador,mas informacion en el log",sqle);
				}finally{
					try{
						if(consulta!=null)
						    consulta.close();
						if(conexion!=null)
						   conexion.close();
					}catch(SQLException sqle){
						sqle.printStackTrace();
						throw new SQLNegocioException(sqle);
					}
				}
		}


		
	    /**
	     * Devuelve los datos que 5 Channels con mas Subscriptores para poderlos representar
	     * @return
	     * @throws SQLNegocioException
	     */
		public Collection <Agrupador> findChannelsMasSubscriptores()throws SQLNegocioException{
		       PreparedStatement consulta=null;
		       Connection conexion=null;
		       ResultSet resulset=null;
		       try{
				   Collection <Agrupador>retorno=new ArrayList<Agrupador>();
		           // Apertura de una conexión
		           conexion=getConnection();
		          // Preparación de la consulta
		           consulta=conexion.prepareStatement(QUERYMASSUBS);
			       resulset=consulta.executeQuery();
				   while (resulset.next()){
					  String username=resulset.getString("USERNAME"); 
					  long subscriberscount=resulset.getLong("SUBSCRIBERSCOUNT");
					  retorno.add(new Agrupador("User: "+username+" Subs: "+subscriberscount, subscriberscount));
				  }
				   return retorno;
		       }catch(SQLException sqle){
					sqle.printStackTrace();
					throw new SQLNegocioException("Ha habido un problema al obtener datos de Channels de la BBDD.Consulte al Administrador,mas informacion en el log",sqle);
				}finally{
					try{
						if(consulta!=null)
						    consulta.close();
						if(conexion!=null)
						   conexion.close();
					}catch(SQLException sqle){
						sqle.printStackTrace();
						throw new SQLNegocioException(sqle);
					}
				}
		}
		
}
