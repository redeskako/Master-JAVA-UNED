package es.uned.ficheros;

import java.io.*;
import java.util.*;

public class LeerFichero {
	private File archivo;
	private FileReader fr;
	private BufferedReader bfr;
	
		private void procesaLinea(String linea){
			StringTokenizer strk = new StringTokenizer (linea, " ");
			String line = "[";
			String word = "";
			
			while ((strk.hasMoreTokens())){
				line += strk.nextToken() + ", ";
			}
			line += ("]");
			System.out.println(line);
		}
	public LeerFichero(String file){
		String linea;
		try {
			//Apertura de fichero y creación de Buffer para agilizar la lectura
			this.archivo = new File(file);
			this.fr = new FileReader(this.archivo);
			this.bfr = new BufferedReader(this.fr);
			while ((linea = this.bfr.readLine()) != null){
				System.out.println("Linea capturada: " + linea);
				this.procesaLinea(linea);
			}
		}catch (FileNotFoundException ex){
			ex.printStackTrace();
		}catch (IOException ex){
			ex.printStackTrace();
		}catch (Exception ex){
			ex.printStackTrace();
		}finally{
			try{
				if (this.bfr != null){
					this.bfr.close();
				}
				if (this.fr != null){
					this.fr.close();
				}
			}catch(Exception ex){}
		}
	}
}