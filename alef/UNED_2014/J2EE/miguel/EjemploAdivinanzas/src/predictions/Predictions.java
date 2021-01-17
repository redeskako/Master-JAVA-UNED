package predictions;
import java.io.*;
import java.io.ByteArrayOutputStream;
import java.beans.XMLEncoder;
import javax.servlet.ServletContext;

public class Predictions {

	private int n=32;
	private Prediction [ ] predictions;
	private ServletContext sctx;
	
	public Predictions(){
		
	}

	public String  getPredictions() {
		if (getServletContext()==null) return null;
		
		/*
		 * Si el array de adivinanzas (predictions) no ha sido inicializado
		 * entonces se ejecuta el metodo populate.
		 * El metodo populate lee desde un fichero de texto los datos suministrados, esto es,
		 * registros formados por las parejas adivino, adivinanza.
		 */
		if (predictions==null) populate();
		
		return toXML();
	}

	public void setPredictions(String ps) {
		
	}

	private void populate(){
		/*
		 * En este método se leen los datos desde un fichero de texto 
		 */
		
		
		 // Se define el fichero de texto que  alberga los datos.
				
		String filename ="/WEB_INF/data/predictions.db";
		
		// Se crea el stream de entrada 
		
		InputStream in=sctx.getResourceAsStream(filename); 
		
		
		if(in != null){
			try{
				InputStreamReader isr=new InputStreamReader(in);
				BufferedReader reader = new BufferedReader(isr);
				predictions = new Prediction[n];
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
					predictions[i++]=p;
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
	
}
