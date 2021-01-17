package predictions2;
import java.io.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
import java.util.Collections;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.*;
import java.io.ByteArrayOutputStream;
import java.beans.XMLEncoder;
import javax.servlet.ServletContext;

public class Predictions {
    private ConcurrentMap<Integer, Prediction>predictions;
    private ServletContext sctx;
    private AtomicInteger mapKey;
	
	public Predictions(){
		predictions = new ConcurrentHashMap<Integer, Prediction>();
		mapKey = new AtomicInteger();
	}

	public String  getPredictions() {
		if (getServletContext()==null) return null;
		
		if (predictions==null)populate();
		
		return toXML();
	}

	public void setPredictions(String ps) {
		
	}
	
	
	public int addPrediction(Prediction p){
		int id = mapKey.incrementAndGet();
		p.setId(id);
		predictions.put(id, p);
		return id;
	}
	
	
	
	
	
	
	private void populate(){
		
		 // Se define un fichero de texto para albergar los datos
				
		String filename ="/WEB_INF/data/predictions.db";
		
		// Se crea el stream de entrada 
		
		InputStream in=sctx.getResourceAsStream(filename); 
		
		if(in != null){
			try{
				InputStreamReader isr=new InputStreamReader(in);
				BufferedReader reader = new BufferedReader(isr);
				int i =0;
				String record = null;
			/*
			 * Se leen cadenas de caracteres desde la entrada. 
			 */
				while ((record = reader.readLine()) !=null){
			 /*
			  * La cadena 	leida se separa por medio del caracter !	
			  */
					String[] parts = record.split("!");
					
			/*
			 * Se crea el objeto P de la clase Prediction		
			 */
					Prediction p = new Prediction();
					
			/*
			 * 	Se actualizan los atributos del objeto P 	
			 */
					p.setWho(parts[0]);
					p.setWhat(parts[1]);
			/*
			 * Se añade el objeto creado al array 		
			 */
					addPrediction(p);
				}
			}catch(IOException e){
			
		   }
		}	
	}
		public void setServletContext(ServletContext sctx) {
		this.sctx = sctx;
    	}
		public ServletContext getServletContext() {
			return this.sctx ;
	    	}
		private String toXML(){
			
			String xml = null;
			try{
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				XMLEncoder encoder = new XMLEncoder(out);
				encoder.writeObject(predictions);
				encoder.close();
				xml=out.toString();
			}catch(Exception e){
				
			}
		   return xml;
		}
	
public ConcurrentMap<Integer, Prediction>getMap(){
	if (getServletContext() == null) return null;
	if (predictions.size()<1)populate();
	return this.predictions;
}
		
		
		
		
		
		
}
