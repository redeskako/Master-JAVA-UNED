package beans;

import java.util.ArrayList;
/**
 * 
 * @author Alef
 * Se crea la clase Search para los objetos que devuelve 
 *
 */


public class Search {
	
	private ArrayList<String> title;
	private ArrayList<String> URL;
	
	
	public ArrayList<String> getURL() {
		return URL;
	}


	public void setURL(ArrayList<String> uRL) {
		URL = uRL;
	}


	public ArrayList<String> getTitle() {
		return title;
	}


	public void setTitle(ArrayList<String> title) {
		this.title = title;
	}


	

}
