package es.uned.master.java.grajeGUI.capaGestion;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
public class GestionFechas {
	
   public  GestionFechas(){
		
	}

	public String getFechaHora() {	
	  Date fecha = new Date();
	  DateFormat fechaHora = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss" );
	  return fechaHora.format(fecha);
    }
	
}


