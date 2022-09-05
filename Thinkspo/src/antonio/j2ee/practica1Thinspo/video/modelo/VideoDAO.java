package antonio.j2ee.practica1Thinspo.video.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import antonio.j2ee.practica1Thinspo.bbdd.modelo.ModeloDAO;
import antonio.j2ee.practica1Thinspo.excepciones.SQLNegocioException;

/**
 * Clase que extiende antonio.j2ee.practica1Thinspo.bbdd.modelo.ModeloDAO
 * Implementa el patron DAO encapsulando el acceso a la tabla Video
 * @author  Antonio Sánchez Antón
 * @since  1.0
 * @see antonio.j2ee.practica1Thinspo.bbdd.modelo.ModeloDAO
 */
public class VideoDAO extends ModeloDAO {
	private static final String QUERYBUSQUEDA="SELECT * FROM VIDEO ORDER BY videoid ASC LIMIT ? OFFSET ?";
	private static final String QUERYCOUNT="SELECT COUNT(1) AS CONTADOR FROM VIDEO";
	private static final String QUERYCATEGORIAS="SELECT DISTINCT CATEGORIES FROM VIDEO ORDER BY CATEGORIES";
	private static final String QUERYVIDEOSWITHTAG="SELECT * FROM VIDEO WHERE CATEGORIES LIKE ?";
	private static final String QUERYUSUARIOSMASVISTOS="select uploader_username_oid ,sum(viewcount) as visionados from video group by uploader_username_oid having visionados>1 order by visionados desc LIMIT 5 OFFSET 0";
	private static final String QUERYVIDEOSMASLIKES="select videoid,uploader_username_oid,numlikes from video order by numlikes desc LIMIT 10 OFFSET 0";
	private static final String QUERYVIDEOSMASDISLIKES="select videoid,uploader_username_oid,numdislikes from video order by numdislikes desc LIMIT 10 OFFSET 0";
	
	//Tags cacheados para mejorar rendimiento al no haber actualizacion de videos
	//En caso de haberla habria que limpiar el cache para forzar su recarga
	private static Collection<Tag> cacheCategorias=null;
	
	protected VideoDAO() {}
	
	/**
	 * Devuelve Collection de Video formada por limmite Video a partir del offset
	 * @param limite
	 * @param offset
	 * @return
	 * @throws SQLNegocioException
	 */
	public Collection<Video> findVideos(int limite,int offset) throws SQLNegocioException{
	       ArrayList<Video>videos=new ArrayList<Video>();
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
				    String videoid=resulset.getString("VIDEOID");
				    float averageold=resulset.getFloat("AVERAGEOLD");
				    String categories=resulset.getString("CATEGORIES");
				    Date datecaptured=resulset.getDate("DATECAPTURED");
				    String description=resulset.getString("DESCRIPTION");
				    long duration=resulset.getLong("DURATION");
				    long favoritecount=resulset.getLong("FAVORITECOUNT");
				    int numdislikes=resulset.getInt("NUMDISLIKES");
				    int numlikes=resulset.getInt("NUMLIKES");
				    long numratersold=resulset.getLong("NUMRATERSOLD");
				    Date published=resulset.getDate("PUBLISHED");
				    double scoresnanormalized=resulset.getDouble("SCORESNANORMALIZED");
				    double scoresnatotal=resulset.getDouble("SCORESNATOTAL");
				    double scoresnavideo=resulset.getDouble("SCORESNAVIDEO");
				    Date timecaptured=resulset.getDate("TIMECAPTURED");
				    Date timecommentssearched=resulset.getDate("TIMECOMMENTSSEARCHED");
				    Date timescorecalculated=resulset.getDate("TIMESCORECALCULATED");
				    String title=resulset.getString("TITLE");
				    String uoloader_username_oid=resulset.getString("UPLOADER_USERNAME_OID");
				    long viewcount=resulset.getLong("VIEWCOUNT");
					Video video=new Video(videoid, averageold, categories,
							 datecaptured,description, duration,
							favoritecount, numdislikes, numlikes,
							numratersold, published, scoresnanormalized,
							scoresnatotal, scoresnavideo, timecaptured,
							timecommentssearched, timescorecalculated, title,
							uoloader_username_oid, viewcount);
					videos.add(video);	
			 }
			 return videos;
			}catch(SQLException sqle){
				sqle.printStackTrace();
				throw new SQLNegocioException("Ha habido un problema al obtener datos de Videos de la BBDD.Consulte al Administrador,mas informacion en el log",sqle);
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
	 * Devuelve el total de Video
	 * @return
	 * @throws SQLNegocioException
	 */
	public int countVideos() throws SQLNegocioException{
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
				throw new SQLNegocioException("Ha habido un problema al buscar videos en BBDD.Consulte al Administrador,mas informacion en el log",sqle);
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
	 * Devuelve el total de Tags
	 * @return
	 * @throws SQLNegocioException
	 */
	public Integer countTags() throws SQLNegocioException{
		if (cacheCategorias==null){
			cacheCategorias=obtenerTags();
		}
		return cacheCategorias.size();
	}
	
	
	/**
	 * Devuelve Collection de Tag formada por limmite Tag a partir del offset
	 * @param limite
	 * @param offset
	 * @return
	 * @throws SQLNegocioException
	 */
	public Collection<Tag> findTags(int limite,int offset) throws SQLNegocioException{
   	   if (cacheCategorias==null){
    		   cacheCategorias=obtenerTags();
   	   }
   	   //para evitar desbordar la lista
   	   if (limite>cacheCategorias.size())
	        return  ((List<Tag>)cacheCategorias).subList(offset, cacheCategorias.size());
   	   else
   		    return  ((List<Tag>)cacheCategorias).subList(offset, limite);
    }
	

	/**
	 * Devuelve la Collection de Video que contenga el Tag
	 * @param tag
	 * @return
	 * @throws SQLNegocioException
	 */
	public Collection<Video> findVideosByTag(String tag) throws SQLNegocioException{
	       ArrayList<Video>videos=new ArrayList<Video>();
		   PreparedStatement consulta=null;
	       Connection conexion=null;
	       ResultSet resulset=null;
	       try {
	              // Apertura de una conexión
	          conexion=getConnection();
                 // Preparación de la consulta
              consulta=conexion.prepareStatement(QUERYVIDEOSWITHTAG);
              consulta.setString(1, "%"+tag+"%");
              resulset=consulta.executeQuery();
			  while (resulset.next()){
				    String videoid=resulset.getString("VIDEOID");
				    float averageold=resulset.getFloat("AVERAGEOLD");
				    String categories=resulset.getString("CATEGORIES");
				    Date datecaptured=resulset.getDate("DATECAPTURED");
				    String description=resulset.getString("DESCRIPTION");
				    long duration=resulset.getLong("DURATION");
				    long favoritecount=resulset.getLong("FAVORITECOUNT");
				    int numdislikes=resulset.getInt("NUMDISLIKES");
				    int numlikes=resulset.getInt("NUMLIKES");
				    long numratersold=resulset.getLong("NUMRATERSOLD");
				    Date published=resulset.getDate("PUBLISHED");
				    double scoresnanormalized=resulset.getDouble("SCORESNANORMALIZED");
				    double scoresnatotal=resulset.getDouble("SCORESNATOTAL");
				    double scoresnavideo=resulset.getDouble("SCORESNAVIDEO");
				    Date timecaptured=resulset.getDate("TIMECAPTURED");
				    Date timecommentssearched=resulset.getDate("TIMECOMMENTSSEARCHED");
				    Date timescorecalculated=resulset.getDate("TIMESCORECALCULATED");
				    String title=resulset.getString("TITLE");
				    String uoloader_username_oid=resulset.getString("UPLOADER_USERNAME_OID");
				    long viewcount=resulset.getLong("VIEWCOUNT");
					Video video=new Video(videoid, averageold, categories,
							 datecaptured,description, duration,
							favoritecount, numdislikes, numlikes,
							numratersold, published, scoresnanormalized,
							scoresnatotal, scoresnavideo, timecaptured,
							timecommentssearched, timescorecalculated, title,
							uoloader_username_oid, viewcount);
					videos.add(video);	
			 }
			 System.out.println("Videos obtenidos para Tag:"+tag+" "+videos.size()); 
			 return videos;
			}catch(SQLException sqle){
				sqle.printStackTrace();
				throw new SQLNegocioException("Ha habido un problema al obtener datos de VideosNyTag de la BBDD.Consulte al Administrador,mas informacion en el log",sqle);
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
      * Devuelve una Collection de Agrupadores que contienen datos de los Usuarios mas vistos
      * @return
      * @throws SQLNegocioException
      */
	public Collection <Agrupador> findUsuariosMasVistos()throws SQLNegocioException{
       PreparedStatement consulta=null;
       Connection conexion=null;
       ResultSet resulset=null;
       try{
		   Collection retorno=new ArrayList<Agrupador>();
           // Apertura de una conexión
           conexion=getConnection();
          // Preparación de la consulta
           consulta=conexion.prepareStatement(QUERYUSUARIOSMASVISTOS);
	       resulset=consulta.executeQuery();
		   while (resulset.next()){
			  String uoloader_username_oid=resulset.getString("UPLOADER_USERNAME_OID"); 
			  Long visonados=resulset.getLong("VISIONADOS");
			  retorno.add(new Agrupador("User: "+uoloader_username_oid+"\nNum:"+visonados, visonados));
		  }
		   return retorno;
       }catch(SQLException sqle){
			sqle.printStackTrace();
			throw new SQLNegocioException("Ha habido un problema al obtener datos de Videos de la BBDD.Consulte al Administrador,mas informacion en el log",sqle);
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
     * Devuelve una Collection de Agrupadores que contienen datos de los Video mas gustan
     * @return
     * @throws SQLNegocioException
     */
	public Collection <Agrupador> findVideosMasLikes()throws SQLNegocioException{
	       PreparedStatement consulta=null;
	       Connection conexion=null;
	       ResultSet resulset=null;
	       try{
			   Collection <Agrupador>retorno=new ArrayList<Agrupador>();
	           // Apertura de una conexión
	           conexion=getConnection();
	          // Preparación de la consulta
	           consulta=conexion.prepareStatement(QUERYVIDEOSMASLIKES);
		       resulset=consulta.executeQuery();
			   while (resulset.next()){
				  String uoloader_username_oid=resulset.getString("UPLOADER_USERNAME_OID"); 
				  String videoId=resulset.getString("VIDEOID");
				  Long numlikes=resulset.getLong("NUMLIKES");
				  retorno.add(new Agrupador("User: "+uoloader_username_oid+"\nVideos:"+videoId+" \nNumero: "+numlikes, numlikes));
			  }
			   return retorno;
	       }catch(SQLException sqle){
				sqle.printStackTrace();
				throw new SQLNegocioException("Ha habido un problema al obtener datos de Videos de la BBDD.Consulte al Administrador,mas informacion en el log",sqle);
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
     * Devuelve una Collection de Agrupadores que contienen datos de los Video mas disgustan
     * @return
     * @throws SQLNegocioException
     */
	public Collection <Agrupador> findVideosMasDisLikes()throws SQLNegocioException{
	       PreparedStatement consulta=null;
	       Connection conexion=null;
	       ResultSet resulset=null;
	       try{
			   Collection <Agrupador>retorno=new ArrayList<Agrupador>();
	           // Apertura de una conexión
	           conexion=getConnection();
	          // Preparación de la consulta
	           consulta=conexion.prepareStatement(QUERYVIDEOSMASDISLIKES);
		       resulset=consulta.executeQuery();
			   while (resulset.next()){
				  String uoloader_username_oid=resulset.getString("UPLOADER_USERNAME_OID"); 
				  String videoId=resulset.getString("VIDEOID");
				  Long numdislikes=resulset.getLong("NUMDISLIKES");
				  retorno.add(new Agrupador("User: "+uoloader_username_oid+"\n-Videos:"+videoId+"\nNumero: "+numdislikes, numdislikes));
			  }
			   return retorno;
	       }catch(SQLException sqle){
				sqle.printStackTrace();
				throw new SQLNegocioException("Ha habido un problema al obtener datos de Videos de la BBDD.Consulte al Administrador,mas informacion en el log",sqle);
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
     * Devuelve una Collection con los diferentes Tag
     * @return
     * @throws SQLNegocioException
     */
	private Collection<Tag> obtenerTags() throws SQLNegocioException{
	       PreparedStatement consulta=null;
	       Connection conexion=null;
	       ResultSet resulset=null;
	       try{
			   Collection retorno=new ArrayList<Tag>();
	           // Apertura de una conexión
	           conexion=getConnection();
	          // Preparación de la consulta
	           consulta=conexion.prepareStatement(QUERYCATEGORIAS);
		       resulset=consulta.executeQuery();
			   while (resulset.next()){
				  String categories=resulset.getString("CATEGORIES");
				  retorno=VideoHelper.procesarCategoria(retorno, categories);
			  }
			   Collections.sort(((List)retorno),new ComparadorTags());
			   return retorno;
	       } catch(SQLException sqle){
				sqle.printStackTrace();
				throw new SQLNegocioException("Ha habido un problema al buscar las categorias de los videos en BBDD.Consulte al Administrador,mas informacion en el log",sqle);
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
