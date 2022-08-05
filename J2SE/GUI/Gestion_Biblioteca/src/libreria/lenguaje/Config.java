
/*
 *   CARLOS LUIS SÁNCHEZ BOCANEGRA
                            DNI: 26017022C
             Expediente UNED: 55­04­00458
Domicilio: C/Cura Merino 2 2ºD 29011 Málaga

 */
package libreria.lenguaje;

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
		if (this.local.getLanguage()=="espa�ol"){
			return "es";
		}else{
			return "en";
		}
	}
	public String locale(){
		return this.local.getDisplayLanguage();
	}
}
