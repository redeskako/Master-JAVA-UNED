package antonio.j2ee.practica1Thinspo.videosearch.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import antonio.j2ee.practica1Thinspo.bbdd.modelo.ModeloDAO;
import antonio.j2ee.practica1Thinspo.excepciones.SQLNegocioException;

/**
 * Clase que extiende antonio.j2ee.practica1Thinspo.bbdd.modelo.ModeloDAO
 * Implementa el patron DAO encapsulando el acceso a la tabla VideoSearch
 * @author  Antonio Sánchez Antón
 * @since  1.0
 * @see antonio.j2ee.practica1Thinspo.bbdd.modelo.ModeloDAO
 */
public class VideoSearchDAO extends ModeloDAO {
	private static final String QUERYBUSQUEDA="SELECT * FROM VIDEOSEARCH ORDER BY videosearch_id ASC LIMIT ? OFFSET ?";
	private static final String QUERYCOUNT="SELECT COUNT(1) AS CONTADOR FROM VIDEOSEARCH";
	
	protected VideoSearchDAO() {}
	
	/**
	 * Devuelve Collection de VideoSearch formada por limmite Video a partir del offset
	 * @param limite
	 * @param offset
	 * @return
	 * @throws SQLNegocioException
	 */
	public Collection<VideoSearch> findVideoSearch(int limite,int offset) throws SQLNegocioException{
	       ArrayList<VideoSearch>videos=new ArrayList<VideoSearch>();
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
				  long videosearchid=resulset.getLong("VIDEOSEARCH_ID");
				  String order=resulset.getString("ORDER"); 
				  int position=resulset.getInt("POSITION");
				  String query=resulset.getString("QUERY");
				  Date timeseached=resulset.getDate("TIMESEARCHED");
				  String video_videoid_oid=resulset.getString("VIDEO_VIDEOID_OID");   
				  VideoSearch video=new VideoSearch(videosearchid,order,position,
							query,timeseached,video_videoid_oid);
			      videos.add(video);	
			 }
			 return videos;
			}catch(SQLException sqle){
				sqle.printStackTrace();
				throw new SQLNegocioException("Ha habido un problema al obtener datos de VideoSearch de la BBDD.Consulte al Administrador,mas informacion en el log",sqle);
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
	 * Devuelve el total de VideoSearch
	 * @return
	 * @throws SQLNegocioException
	 */
	public int countVideoSearchs() throws SQLNegocioException{
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
				throw new SQLNegocioException("Ha habido un problema al buscar videoSearch en BBDD.Consulte al Administrador,mas informacion en el log",sqle);
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
