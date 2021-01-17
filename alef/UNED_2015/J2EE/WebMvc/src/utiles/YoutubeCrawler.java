/*
 * Encapsula el acceso a los vídeos mediante la API de Youtube y
 * gestiona su inserción y recuperación de la BD.
 */

package utiles;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import beans.Canal;
import beans.Video;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Thumbnail;

import dao.CanalDao;
import dao.VideoDao;

public class YoutubeCrawler
{
	// Por imposición del API, el número máximo de videos por página es 50.
    private final long NUM_VIDEOS = 50;  

    // Propiedades por defecto.
	private String apiKey = "AIzaSyCXm0aYWahZvciDOkaQW_-Qrx2ULni3d10";
    private String appName = "maximal-ascent-94419";
    
    private YouTube youtube;
    
	/**
	 * Constructor.
	 */
	public YoutubeCrawler()
	{
	}
	
	/**
	 * Obtiene la clave de acceso a la API Youtube.
	 * @return clave de acceso.
	 */
	public String getApiKey()
	{
		return apiKey;
	}

	/**
	 * Ajusta la clave de accesso de la API Youtube.
	 * @param apiKey clave de acceso.
	 */
	public void setApiKey(String apiKey)
	{
		this.apiKey = apiKey;
	}

	/**
	 * Obtiene el nombre de la aplicación registrada para el acceso a la API Youtube.
	 * @return nombre de la aplicación.
	 */
	public String getAppName()
	{
		return appName;
	}

	/**
	 * Ajusta el nombre de la aplicación registrada para el acceso a la API Youtube.
	 * @param appName nombre de la aplicación.
	 */
	public void setAppName(String appName)
	{
		this.appName = appName;
	}

	/**
	 * Inserta los videos de un canal en la BD.
	 */
	public void insertaVideosEnBD(String sIdCanal)
	{
		VideoDao dao = new VideoDao();
		dao.openConnection();
		dao.insert(getVideosDeInternet(sIdCanal));
		dao.closeConnection();
	}
	
	/**
	 * Recupera los videos de un canal de la BD.
	 */
	public List<Video> recuperaVideosDeBD(String sIdCanal, int nNumPagina, int nNumVideosPorPagina)
	{
		VideoDao dao = new VideoDao();
		dao.openConnection();
		List<Video> lst = dao.select(sIdCanal, nNumPagina, nNumVideosPorPagina);
		dao.closeConnection();
		return lst;
	}

	/**
	 * Recupera un video de la BD.
	 * @param id identificador de video.
	 * @return video.
	 */
	public Video recuperaVideoDeBD(String id)
	{
		VideoDao dao = new VideoDao();
		dao.openConnection();
		Video video = dao.selectVideo(id);
		dao.closeConnection();
		return video;
	}

	/**
	 * Recupera los canales de la BD.
	 */
	public List<Canal> recuperaCanalesDeBD()
	{
		CanalDao dao = new CanalDao();
		dao.openConnection();
		List<Canal> lst = dao.select();
		dao.closeConnection();
		return lst;
	}
	
	/**
	 * Recupera un canal de la BD.
	 * @param id identificador de canal.
	 * @return canal.
	 */
	public Canal recuperaCanalDeBD(String id)
	{
		CanalDao dao = new CanalDao();
		dao.openConnection();
		Canal canal = dao.select(id);
		dao.closeConnection();
		return canal;
	}

	/**
	 * Obtiene el número de videos registrados en la BD de un determinado canal.
	 */
	public int getNumVideosCanal(String sIdCanal)
	{
		VideoDao dao = new VideoDao();
		dao.openConnection();
		int nNumVideos = dao.getNumVideos(sIdCanal);
		dao.closeConnection();
		return nNumVideos;
	}

	/**
	 * Obtiene la lista de videos de un canal mediante la API de Youtube.
	 * @param ID_CANAL identificador de canal.
	 * @return lista de objetos Video.
	 */
	private List<Video> getVideosDeInternet(String sIdCanal)
	{
        String nextToken;
        YouTube.Search.List search;
        SearchListResponse searchResponse;
        List<SearchResult> searchResultList;
        
        List<Video> lstVideos = new ArrayList<Video>();
		
        try
		{
        	// Inicializa el objeto youtube.
			youtube = new YouTube.Builder(new NetHttpTransport(), new JacksonFactory(), new HttpRequestInitializer()
			{
				public void initialize(HttpRequest request) throws IOException
				{
				}
			}).setApplicationName("maximal-ascent-94419").build();
         
			// Define la solicitud.
			search = youtube.search().list("id,snippet");            
			search.setKey(apiKey);
			search.setType("video");            
			search.setChannelId(sIdCanal);
			search.setFields("items(id/kind,id/videoId,snippet/title,snippet/description, snippet/publishedAt,snippet/thumbnails/default/url),nextPageToken,pageInfo");
			search.setMaxResults(NUM_VIDEOS);

			// Ejecuta la consulta por páginas.
			nextToken = "";
			while (nextToken != null)
			{
				// Incluye el token de la página en la solicitud.
				search.setPageToken(nextToken);

				// Ejecuta la consulta y obtiene los resultados.
				searchResponse = search.execute();
				searchResultList = searchResponse.getItems();

		        //System.out.println("Página: " + nContPag + ", token: " + nextToken + ", num_videos: " + searchResultList.size());

		        Iterator<SearchResult> it = searchResultList.iterator();
		        while (it.hasNext())
		        {
		            SearchResult singleVideo = it.next();
		            ResourceId rId = singleVideo.getId();            
		         
		            if (rId.getKind().equals("youtube#video"))
		            {
		            	Video video = new Video();
		            	
		            	Thumbnail thumbnail = singleVideo.getSnippet().getThumbnails().getDefault();
		            	
		                video.setId(rId.getVideoId());
		                video.setIdCanal(sIdCanal);
		                video.setTitulo(singleVideo.getSnippet().getTitle());
		                video.setDescripcion(singleVideo.getSnippet().getDescription());
		                video.setFechaPublicacion(getFecha(singleVideo.getSnippet().getPublishedAt().toString()));
		                video.setThumbnail(thumbnail.getUrl());

		                // Se descartan los videos repetidos.
                        if (!lstVideos.contains(video))
    		                lstVideos.add(video);
		            }
		        }

				// Siguiente página.
				nextToken = searchResponse.getNextPageToken();
			}			
			
			return lstVideos;
		}
		catch (GoogleJsonResponseException e)
		{
			System.err.println("There was a service error: " + e.getDetails().getCode() + " : " + e.getDetails().getMessage());
			return null;
		}
		catch (IOException e)
		{
			System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage());
			return null;
		}
		catch (Throwable t)
		{
			t.printStackTrace();
			return null;
		}		
	}		
	
	/**
	 * Convierte una fecha en formato Youtube a formato europeo.
	 * @param fechaYoutube fecha a convertir.
	 * @return fecha en formato europeo.
	 */
	public String getFecha(String fechaYoutube)
	{
		String sAno = fechaYoutube.substring(0, 4);
		String sMes = fechaYoutube.substring(5, 7);
		String sDia = fechaYoutube.substring(8, 10);
		String sHora = fechaYoutube.substring(11, 19);

		String sFecha = sDia + "/" + sMes + "/" + sAno + " " + sHora;

		return sFecha;
	}

	/**
	 * A partir de una lista de objetos Video maqueta sus datos para presentarlos en una página jsp.
	 * @param lst lista de videos.
	 * @return cadena con el código html a presentar.
	 */
	public String getListaVideos(List<Video> lst)
	{
		String sListaHtml = "";
		
        for (Video video : lst)
        {
        	sListaHtml += "<div class='video'>\n"; 
        	//sListaHtml += "<a href='https://www.youtube.com/watch?v=" + video.getId() + "'><img src='" + video.getThumbnail() + "'/></a>\n";
        	sListaHtml += "<a href='Controlador?operacion=muestra_video&id=" + video.getId() + "'><img src='" + video.getThumbnail() + "'/></a>\n";
        	sListaHtml += "<p><strong>Título:</strong> " + video.getTitulo() + ".<br/>\n";
        	sListaHtml += "<strong>Descripción:</strong> " + video.getDescripcion() + ".</br>\n";
        	sListaHtml += "<strong>Fecha de publicación:</strong> " + video.getFechaPublicacion() + ".\n";
        	sListaHtml += "</p></div>\n";
        }
        
        return sListaHtml;
	}
	
	/**
	 * A partir de una lista de objetos Canal maqueta sus datos para presentarlos en una página jsp.
	 * @param lst lista de canales.
	 * @return cadena con el código html a presentar.
	 */
	public String getListaCanales(List<Canal> lst)
	{
		String sListaHtml = "";
		
        for (Canal canal : lst)
        {
        	sListaHtml += "<div class='canal'>\n"; 
        	sListaHtml += "<p><a href='Controlador?operacion=lista_videos&canal=" + canal.getId() + "'>" + canal.getNombre() + "</a><br/>\n";
        	sListaHtml += "<strong>Nombre:</strong> " + canal.getNombre() + ".<br/>\n";
        	sListaHtml += "<strong>Descripción:</strong> " + canal.getDescripcion() + ".\n";
        	sListaHtml += "</p></div>\n";
        }

        return sListaHtml;
	}

	/**
	 * A partir de un objeto Video maqueta sus datos para presentarlos en una página jsp.
	 * @param video video.
	 * @return cadena con el código html a presentar.
	 */
	public String getDatosVideo(Video video)
	{	
		String sHtml = "";
		
    	sHtml += "<div>\n"; 
    	sHtml += "<p><strong>Título:</strong> " + video.getTitulo() + ".<br/>\n";
    	sHtml += "<strong>Descripción:</strong> " + video.getDescripcion() + ".</br>\n";
    	sHtml += "<strong>Fecha de publicación:</strong> " + video.getFechaPublicacion() + ".\n";
    	sHtml += "</div>\n";
    	
    	return sHtml;
	}

	/**
	 * Maqueta el índice de páginas para presentarlo en una página jsp.
	 * @param canal canal de vídeos a presentar.
	 * @param nPag página a mostrar.
	 * @param nNumVideosCanal número total de videos del canal.
	 * @return
	 */
	public String getIndice(String canal, int nPag, int nNumVideosCanal, int nNumVideosPagina)
	{
		String sIndiceHtml = "";
		
        int nPrimerVideo = ((nPag - 1) * nNumVideosPagina) + 1;
        int nUltimoVideo = Math.min(nPrimerVideo + nNumVideosPagina - 1, nNumVideosCanal);
        
        // Página anterior.
        sIndiceHtml += "<p>Página " + nPag + ". Mostrando videos del "+ nPrimerVideo + " al " + nUltimoVideo + " de un total de " + nNumVideosCanal + ".</p>\n";
        if (nPag != 1)
        	sIndiceHtml += "<div><a href='Controlador?operacion=lista_videos&canal=" + canal + "&pag=" + (nPag - 1) + "'>Anterior</a>&nbsp;&nbsp;";
        else
        	sIndiceHtml += "<div><span class='desactivado'>Anterior</span>&nbsp;&nbsp;";

        // Página siguiente.
        if (nPag <= Math.ceil(nNumVideosCanal / nNumVideosPagina))
        	sIndiceHtml += "<a href='Controlador?operacion=lista_videos&canal=" + canal + "&pag=" + (nPag + 1) + "'>Siguiente</a></div>";
        else
        	sIndiceHtml += "<span class='desactivado'>Siguiente</span></div>";

        return sIndiceHtml;
	}
}