package antonio.j2ee.practica1Thinspo.channel.actions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

import antonio.j2ee.practica1Thinspo.channel.modelo.Channel;
import antonio.j2ee.practica1Thinspo.channel.modelo.ChannelDAOFactory;

import com.opensymphony.xwork2.ActionSupport;
/**
 * Action de Struts2 para dar soporte al CRUD de canales mediante upload de fichero csv
 * Ofrece el metono upload() que es el que se encarga de procesar el csv
 * @author  Antonio Sánchez Antón
 * @since  1.0
 * @see com.opensymphony.xwork2.ActionSupport
 */
public class UploadCanales extends ActionSupport {
	private static final long serialVersionUID = -7217814619376699838L;
    protected File archivo;
    protected String archivoFileName;
    protected String archivoContentType;
    

    /**
     * Realiza la carga y procesamiento del fichero csv(separador ;) de canales
     * Los campos Date deben venir con el formato "dd-MM-yyyy hh24:mm:ss"
     * Debe de estar en consonancia con la Tabla Channel,es decir aquellos campos obligatorios deben llegar siempre en el fichero
     * @return
     * @throws Exception
     */
    public String upload() throws Exception{
    	Long channelviews=null;
    	Date datefavoritevideosearched=null;
    	Date datejoined=null;
    	Date datefriendssearch=null;
    	Date datesubscribedtosearched=null;
    	String location=null;
    	Double scoresna=null;
    	Double scoresnanormalized=null;
    	Long subscriberscount=null;
    	Date timecaptured=null;
    	Date timefavcaptured=null;
    	Date timescorecalculated=null;
    	Date timesubcaptured=null;
    	Long uploadviews=null;
    	
    	if(archivoFileName!=null){
    		ServletContext contexto=ServletActionContext.getServletContext();
    		String directorioFicherosCanales=contexto.getRealPath("ficherosCanales");
    		File fichero=new File(directorioFicherosCanales,this.archivoFileName);
    		archivo.renameTo(fichero);
    	    //Formato de las fechas que llegaran en el csv
    		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    		//procesar el fichero csv de canales
    		BufferedReader br=new BufferedReader(new FileReader(fichero));
    		String fila;
    		while ((fila = br.readLine())!=null){
    			String[]tokens =getTokens(fila, ";");
    			String username=tokens[0];
    			if(!tokens[1].equals("")&&!tokens[1].equals(" "))
    			   channelviews=Long.parseLong(tokens[1]);
    			if(!tokens[2].equals("")&&!tokens[2].equals(" "))
    				datefavoritevideosearched=sdf.parse(tokens[2]);
    			if(!tokens[3].equals("")&&!tokens[3].equals(" "))
    				datejoined=sdf.parse(tokens[3]);
    			if(!tokens[4].equals("")&&!tokens[4].equals(" "))
    				datefriendssearch=sdf.parse(tokens[4]);
    			if(!tokens[5].equals("")&&!tokens[5].equals(" "))
    				datesubscribedtosearched=sdf.parse(tokens[5]);
    			if(!tokens[6].equals("")&&!tokens[6].equals(" "))
    				location=tokens[6];
    			if(!tokens[7].equals("")&&!tokens[7].equals(" "))
    				scoresna=Double.parseDouble(tokens[7]);
    			if(!tokens[8].equals("")&&!tokens[8].equals(" "))
    				scoresnanormalized=Double.parseDouble(tokens[8]);
    			if(!tokens[9].equals("")&&!tokens[9].equals(" "))
    				subscriberscount=Long.parseLong(tokens[9]);
    			if(!tokens[10].equals("")&&!tokens[10].equals(" "))
    				timecaptured=sdf.parse(tokens[10]);
    			if(!tokens[11].equals("")&&!tokens[11].equals(" "))
    				timefavcaptured=sdf.parse(tokens[11]);
    			if(!tokens[12].equals("")&&!tokens[12].equals(" "))
    				timescorecalculated=sdf.parse(tokens[12]);
    			if(!tokens[13].equals("")&&!tokens[13].equals(" "))
    				timesubcaptured=sdf.parse(tokens[13]);
    			if(!tokens[14].equals("")&&!tokens[14].equals(" "))
    				uploadviews=Long.parseLong(tokens[14]);
    			Channel canal=new Channel(username, channelviews, datefavoritevideosearched, datejoined, datefriendssearch, datesubscribedtosearched, location, scoresna, scoresnanormalized, subscriberscount, timecaptured, timefavcaptured, timescorecalculated, timesubcaptured, uploadviews);
    			//se comprueba si el canal existe (se modificaria) o no (se crearia)
    			if (ChannelDAOFactory.getInstancia().getChannelDAO().findChannel(canal.getUsername())) {
    				ChannelDAOFactory.getInstancia().getChannelDAO().modificar(canal);
    				addActionMessage("Modificado Canal para "+canal.getUsername());
    			}else{
    				ChannelDAOFactory.getInstancia().getChannelDAO().create(canal);
    				addActionMessage("Creado Canal para "+canal.getUsername());
    			}
    		}
    		return SUCCESS;	
    	}else{
    		addActionError("Fichero no tiene el formato adecuado.Revise que sea csv(application/vnd.ms-excel)");
    		return INPUT;
    	}
    	
    }

    //getters y setters
    
	public String getArchivoFileName() {
		return archivoFileName;
	}

	public void setArchivoFileName(String archivoFileName) {
		this.archivoFileName = archivoFileName;
	}

	public String getArchivoContentType() {
		return archivoContentType;
	}

    public String getRuta()  {
        return archivo.getAbsolutePath();
    }

	public void setArchivoContentType(String archivoContentType) {
		this.archivoContentType = archivoContentType;
	}


	public void setArchivo(File archivo) {
		this.archivo = archivo;
	}


	public File getArchivo() {
		return archivo;
	}
    
	  /// Fin getters y setters
	
	/**
	 * Devuelve los tokens de la linea incluidos los que no tienen valor ya que StringTokenizer no lo hace
	 * Utilizado para procesar el fichero csv
	 * Metodo base obtenido de http://stackoverflow.com/questions/2708591/java-stringtokenizer-empty-null-tokens
	 * @param line
	 * @param delim
	 * @return
	 */
	private String[] getTokens(String line, String delim){
		  String s = line;
		  int i = 0;

		  while (s.contains(delim)) {
		  s = s.substring(s.indexOf(delim) + delim.length());
		      i++;
		  }
		  
		  String token = null;
		  String remainder = null;
		  String[] tokens = new String[i+1];

		  for (int j = 0; j < i; j++) {
		    token = line.substring(0, line.indexOf(delim));
		    tokens[j] = token;
		    remainder = line.substring(line.indexOf(delim) + delim.length());
		    line = remainder;
	       }
            tokens[tokens.length-1]=remainder;
		  return tokens;
		 }
}
