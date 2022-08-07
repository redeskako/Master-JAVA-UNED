package utiles;


import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.util.DateTime;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Thumbnail;
//import com.google.api.services.youtube.model.Video;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import beans.Video;
 
public class  Crawler {
	/*
	 * Para poder trabajar con la API de Youtube es necesario
	 * Generar una apiKey y definir la app Name con la cuál trabajaremos
	 */
	private String apiKey;
	private String appName;
    
    private static final long NUMBER_OF_VIDEOS_RETURNED = 50;  
    private static YouTube youtube;
    
    public  Crawler() {
		 
	}    

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}
	
	/*
     * Método que realiza busquedas de videos en canales de Youtube, obteniendo:
     * -VideoID
     * -Titulo Video
     * -Descripción Video
     * -Fecha publicación
     * -Thumbnail
     * 
     * Parametros de entrada:
     * String idCanal: valor del canal del cúal obtendremos los videos
     * Vector<String> v: videos ya almacenados en  nuestra BD  del canal, para evitar duplicados 
     * DateTime t1: fecha inical de busqueda
     * DateTime t2: echa final de busqueda
     */

	public List<Video> getVideos(String idCanal,  List<String> v, DateTime t1, DateTime t2) {		 
		
		List<Video> videos = new ArrayList<Video>();

        try {       
            
            youtube = new YouTube.Builder(Auth.HTTP_TRANSPORT, Auth.JSON_FACTORY, new HttpRequestInitializer() {
                public void initialize(HttpRequest request) throws IOException {
                }
            }).setApplicationName(this.appName).build();
             
            
            YouTube.Search.List search = youtube.search().list("id,snippet");            
            String apiKey = this.apiKey;
            search.setKey(apiKey);
            //Establecemos el intervalo de fechas de busqueda
            search.setPublishedAfter(t1);
            search.setPublishedBefore(t2);
             
            // Establecemos el tipo de elemento a buscar: Video
            search.setType("video");            
            search.setChannelId(idCanal);
            //Establecemos los campos que se desean recuperar
            search.setFields("items(id/kind,id/videoId,snippet/title,snippet/description, "
            		+ "snippet/publishedAt,snippet/thumbnails/default/url),nextPageToken,pageInfo");
            search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);         
            
            // Establecemos el valor del token para la navegación de la paginas devueltas
            String nextToken = "";
            
            SearchListResponse searchResponse=null;
            List<SearchResult> searchResultList=null;
            
            do {
            	 search.setPageToken(nextToken);             	
                 searchResponse = search.execute();
                 searchResultList = searchResponse.getItems();                  
                 searchResponse.getItems();                
                for (int i=0;i<searchResultList.size();i++){
                	 SearchResult singleVideo = searchResultList.get(i);
                     ResourceId rId = singleVideo.getId();            
                     if (rId.getKind().equals("youtube#video")) {
                    	// Se descartan los videos ya alacenados en la BD                       
                    	 if (!v.contains(rId.getVideoId())){
                             Thumbnail thumbnail = singleVideo.getSnippet().getThumbnails().getDefault();                       
                             Video video2 = new Video();
                             video2.setIdCanal(idCanal);
                             video2.setId(rId.getVideoId());
                             video2.setTitulo(singleVideo.getSnippet().getTitle());
                             video2.setDescripcion(singleVideo.getSnippet().getDescription());
                             video2.setFechaPublicacion(singleVideo.getSnippet().getPublishedAt().toString());   
                             video2.setThumbnail(thumbnail.getUrl());
                             videos.add(video2);
                             v.add(rId.getVideoId());                          	 
                          }
                     }    
                }     
                nextToken = searchResponse.getNextPageToken();          
                 
                } while (nextToken != null);
                              
            } catch (GoogleJsonResponseException e) {
            System.err.println("There was a service error: " + e.getDetails().getCode() + " : "
                    + e.getDetails().getMessage());
            return null;
        } catch (IOException e) {
            System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage());
            return null;
        } catch (Throwable t) {
            t.printStackTrace();
            return null;
        }
        return videos;
    }
}
