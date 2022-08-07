package utiles;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import com.google.api.client.util.DateTime;

import beans.Video;
import beans.Canal;
import dao.CanalDao;
import dao.VideoDao;

public class  CrawlerCliente{

    public static void main(String[] args) throws InterruptedException {
    	//List que contine el ID de los videos ya alamacenados
    	List<String> videosBD= new ArrayList<String>();
    	//Objeto DAO para insertar los videos extraidos en la BD
    	VideoDao vd = new VideoDao();
    	//List que contiene los videos extraidos para ser insertados
    	List<Video> videosYTB = new ArrayList<Video>();
    	//Objeto DAO canales
    	CanalDao cd = new CanalDao();
    	//List con los canales Youtube para extraer video
    	List<Canal> canales = new ArrayList<Canal>(); 
    	// Creamos el objeto YoutubeCrawler para realizar las busquedas
         Crawler ytv = new  Crawler(); 
        ytv.setApiKey("");
	    ytv.setAppName("");
    	Calendar cal = Calendar.getInstance();
    	Date f1 = null;
    	Date f2 = null;
    	DateTime dt1 = null;
    	DateTime dt2 =null;    	
        int anioBusqueda =2007;        
        int mesBusqueda =0;    // 0=Enero ...11=Diciembre    
        int dia =1;
        // Definimos el año y mes actual de finalizacion de la busqueda
        int anioActual= Calendar.getInstance().get(Calendar.YEAR);
        int mesActual= Calendar.getInstance().get(Calendar.MONTH);
        //Abrimos la conexion a la BD para recuperar los canales, videos/canal
        //y realizar las insercciones de los videos recuperados
        vd.openConnection();
        
        //Recuperamos los canales a consultar de la BD local
        canales= cd.select();
        
        for (int i=0; i<canales.size();i++){
        	//cargamos los videos ya alamacenados en la BD para evitar duplicados
            videosBD=vd.select(canales.get(i).getId());
        	do {
                // Se realizan busquedas por intevalos mensuales, otras posiblidades 
             	// son semanales, quincenales... etc, dependiendo de la frequencia de
             	// carga de videos del canal y el número máximo de videos por busqueda=500
             	
             	 cal.set(anioBusqueda, mesBusqueda, dia);
                 f1=cal.getTime(); 
                 
                 //Incrementamos el mes de busqueda, en caso de sea el último
                 // del año se incrementa el año y el mes se pone a cero 
                 //Este valor será el intervalo final para la busqueda actual
                 //y el intervalo inicial para la siguiente busqueda
                 
                 if (mesBusqueda ==11){
                 	mesBusqueda=0;
        			anioBusqueda++;
        		    }else{
        		    	mesBusqueda++;
        		    }
                 cal.set(anioBusqueda, mesBusqueda,dia);
                 f2=cal.getTime();
                 dt1 = new DateTime(f1);
                 dt2 = new DateTime(f2);  
                 //obtenemos videos del Crawler
                 videosYTB =ytv.getVideos(canales.get(i).getId(),videosBD,dt1,dt2);
                 //si se han encontrado videos en el intervalo de fechas se insertan en la BD
                 if(videosYTB.size()>0){
                	 vd.insert(videosYTB); 
                 } 	 
                                   
       		   //ytv.getVideos("UC2ccm1GajfSujz7T18d7cKA", videosBD,dt1,dt2); //videos:5600 
                // ytv.getVideos("UCozj60ha6CLG1LsAmBFRE0g", videosBD,dt1,dt2); //videos:250            
               //  ytv.getVideos("UCCj956IF62FbT7Gouszaj9w", videosBD,dt1,dt2);//videos: 13897 		  
                 videosYTB.clear();
             }while (!((anioBusqueda== anioActual) & (mesBusqueda==mesActual)));  
        	//Vaciamos los videos del canal explorado para obtener los del siguiente
        	videosBD.clear(); 
        }
        vd.closeConnection();
        
    }
    }