package es.uned.master.java.oca.idioma;

import java.util.*;

public class Config {
	Locale local;
	ResourceBundle rs;
	
	public Config (String local){
		this.local= new Locale(local);
		this.rs= ResourceBundle.getBundle("Idioma",this.local);
	}
	public String traduce(String metaidioma){
		return this.rs.getString(metaidioma);
	}
	public void cambiaIdioma(String local){
		this.local= new Locale(local);
		this.rs= ResourceBundle.getBundle("Idioma",this.local);
	}
	public String idioma(){
		if (this.local.getLanguage()=="español" || this.local.getLanguage()=="spanish"){
			return "es";
		}else{
			return "en";
		}
	}
	public String locale(){
		return this.local.getDisplayLanguage();
	}
}
