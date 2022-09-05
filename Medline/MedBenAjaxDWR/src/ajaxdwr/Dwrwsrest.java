package ajaxdwr;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.jaxb.generated.Search;

public class Dwrwsrest {

	private Search results = null; // Resultados de la búsqueda

	public Dwrwsrest(){

	}

	public void busquedaRest(String busqueda, String catalogo, String palabra, 
			String campo, int limite){
		//Los espacios no son admitidos en una URL. Se eliminan, la búsqueda debe ser de una
		//palabra únicamente. 
		palabra = palabra.replaceAll(" ", "");

		//TODO Modificar URL en función de la instalación TOMCAT
		String urltxt = "http://localhost:8080/MedLineBennettAjax/rs/v1/";
		
		if (busqueda.equalsIgnoreCase("search"))
			urltxt += "search?catalogo=" + catalogo + "&palabra=" + palabra + 
				"&campo=" + campo;
		else
			urltxt +="startWith?catalogo=" + catalogo + "&palabra=" + palabra +
				"&limite=" + limite;

		try
		{
			URL url = new URL(urltxt);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "text/xml");

			System.out.println("getResponseCode = " + conn.getResponseCode());
			System.out.println("getResponseMessage = " + conn.getResponseMessage());

			if (conn.getResponseCode() != 200) 
			{
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			Search s = new Search();

			results = s.inicio(conn.getInputStream());

			conn.disconnect();

		} catch (MalformedURLException e) {
			System.out.println("Error al direccionar la búsqueda.");
			//e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error en la conexión.");
			System.out.println("Compruebe que el servidor está arrancado.");
			System.out.println("Compruebe que la URL apunta al puerto correcto.");
			//e.printStackTrace();
		}
	}
	
	/**
	 * Método que, en base a los resultados obtenidos del servidor WSREST en la
    * búsqueda básica, sobre la tabla 'HealthTopic' del XML de MedLine o sobre
    * la tabla 'Bennett' de la BD de MySQL, devuelve un array bidimensional de 
    * cadenas donde el primer elemento de cada fila es el valor del campo 
    * 'Title' u 'Organization', y el segundo es la URL asociada, correspondiendo
    * a los registros que contengan la palabra de búsqueda en el campo especificado.
	 * @param catalogo (String) -> 'MedLine' o 'Bennett'.
	 * @param palabra (String) -> Palabra de búsqueda.
	 * @param campo (String) -> Campo de búsqueda.
	 * @return res (String[][]) -> Array bidimensional de String:
	 * 								  res[x][0] = Valor de 'Title' u 'Organization'.
	 *                           res[x][0] = URL asociada al healthtopic u organización.
	 */
	public String[][] busquedaBasica(String catalogo, String palabra, String campo) {
		List<String> titulos = null, urls = null;
		String[][] res = null;
		int total;
		
		busquedaRest("search", catalogo, palabra, campo, 0);
		
		total = results.getTitle().size();
		titulos = results.getTitle();
		urls = results.getUrl();
		
		res = new String[total][2];
		
		for (int i = 0; i < total; i++) {
			res[i][0] = titulos.get(i);
			res[i][1] = urls.get(i);
		}
		
		return res;
	}
	
	/**
	 * Método que, en base a los resultados obtenidos del servidor WSREST en la
    * búsqueda predictiva, sobre el campo 'Title' de los HealthTopic del XML de 
    * MedLine o sobre el campo 'City' de la tabla 'Bennett' de la BD de MySQL, 
    * devuelve un array de cadenas con los valores no duplicados de los campos 
    * 'Title' o 'City' que comiencen por la palabra de búsqueda, y hasta un 
    * límite máximo especificado por el último párametro.
	 * @param catalogo (String) -> 'MedLine' o 'Bennett'.
	 * @param palabra (String) -> Palabra de búsqueda.
	 * @param limite (int) -> Máximo número de valores devueltos.
	 * @return res (String[]) -> Array con valores distintos 
	 * 						        del campo 'Title' o 'City'.
	 */
	public String[] busquedaPredictiva(String catalogo, String palabra, 
			int limite) {
		String[] res = null;
		String campo;
		int total;
		
		campo = (catalogo.equals("MedLine")) ? "Título" : "Ciudad";
		
		busquedaRest("startWith", catalogo, palabra, campo, limite);
		
		total = results.getTitle().size();
		res = results.getTitle().toArray((new String[total]));
		
		return res;
	}
}
