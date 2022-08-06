package antonio.j2ee.practica1Thinspo.video.modelo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.StringTokenizer;

/**
 * Clase de utilidades para gestion de Videos
 * Se encarga de procesar las categorias y de eliminar de estas caracteres no permitidos
 * @author  Antonio Sánchez Antón
 * @since  1.0
 */
public class VideoHelper {

	/**
	 * Procesa las categorias para formar una Collection de Tags
	 * 
	 * @param categorias
	 * @param categories
	 * @return
	 */
	public static Collection<Tag> procesarCategoria(Collection categorias,String categories){
		  StringTokenizer stcat=new StringTokenizer(categories, ",");
		  while (stcat.hasMoreTokens()){
			  String token=stcat.nextToken();
			   //si contiene categoria({http://gdata.youtube.com/schemas/2007/keywords.cat}) cogemos la misma detras de },el 2º token
			  if(token.contains("keywords.cat")){
				  StringTokenizer stTag=new StringTokenizer(token, "}");
				  stTag.nextToken();
				  String tag=procesaTag(stTag.nextToken().toUpperCase());
				  if(!tag.trim().equals("")){
					  Tag cat=new Tag(tag.trim(), tag.trim());
					  if(!categorias.contains(cat))
						   categorias.add(cat);
				  }	  
		      }
		  }
		  return categorias;
	}
	
	private static String procesaTag(String tag){
		  String palabra="";
		  char[] caracteres=tag.toCharArray();
		  for(int i=0;i<caracteres.length;i++){
			  if(!CaracteresEliminar.getInstancia().getCaracteres().contains(caracteres[i]+"")) 
				  palabra=palabra+caracteres[i];
		  }
		  return palabra;
	}	  
}

/**
 * Clase que define los String no permitidos en los Tags
 * 
 * @author  Antonio Sánchez Antón
 * @since  1.0
 */
class CaracteresEliminar {
	
	   protected Collection<String> caracteres;
	   private static CaracteresEliminar instancia;
	   
	   protected CaracteresEliminar(){
		   caracteres=new ArrayList<String>();
		   caracteres.add("]");
		   caracteres.add("[");
		   caracteres.add("'");
		   caracteres.add("\"");
		   caracteres.add("*");
		   caracteres.add("+");
		   caracteres.add("#");
		   caracteres.add("!");
		   caracteres.add("(");
		   caracteres.add(")");
		   caracteres.add("?");
		   caracteres.add(":");
		   caracteres.add(";");
		   caracteres.add(".");
		   caracteres.add("-");
		   caracteres.add("&");
		   caracteres.add("|");
		   caracteres.add("º");
		   caracteres.add("ª");
		   caracteres.add("=");
	   }
	   
	   public static synchronized CaracteresEliminar getInstancia(){
		   if(instancia==null){
			   instancia=new CaracteresEliminar();
		   }
		   return instancia;
	   }
	   
	   /**
	    * Devuelve la Collection de signos configurados
	    * Como mejora,esto se deberia parametrizar en un fichero
	    * @return signos
	    * @see java.util.Collection
	    */
	   public Collection<String> getCaracteres(){
		   return caracteres;
	   }
	   
	
}
