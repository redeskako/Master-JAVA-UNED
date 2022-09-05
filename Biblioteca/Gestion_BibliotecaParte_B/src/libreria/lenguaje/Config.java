/*
 *   CARLOS
 */
package libreria.lenguaje;

import java.util.*;
import java.io.*;

public class Config implements Serializable{
	public static final String OK = "OK";
	public static final String ERROR = "ERROR";
	public static final int PUERTO = 12345;
	private Locale local;
	private ResourceBundle rs;
	private int puerto;
	
	public Config (String local){
		this.local = new Locale(local);
		this.rs = ResourceBundle.getBundle("Idioma",this.local);
		this.puerto = Config.PUERTO;
	}
	public String traduce(String metaidioma){
		return this.rs.getString(metaidioma);
	}
	public void cambiaIdioma(String local){
		this.local = new Locale(local);
		this.rs = ResourceBundle.getBundle("Idioma",this.local);
	}
	public void puerto(int puerto){
		this.puerto = puerto;
	}
	public int puerto(){
		return this.puerto;
	}
	public String idioma(){
		if (this.local.getDisplayLanguage() == "español"){
			return "es";
		}else{
			return "en";
		}
	}
	public String locale(){
		return this.local.getDisplayLanguage();
	}
}