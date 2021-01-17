package org.Otros;

import java.io.*;
import java.net.*;


public class MiIp {
	
	
	public String obtenerIp()
	{
		String ipPublica="";
		try
		{
		    URL whatismyip = new URL("http://checkip.amazonaws.com");
			BufferedReader in = new BufferedReader(new InputStreamReader(
		    whatismyip.openStream()));
			ipPublica = in.readLine(); 
		    in.close();

		   
		}
		catch (Exception e)
		{
			System.out.println("Error en Obtener IP Publica"+e.getMessage());
		}
		
		 return ipPublica;

	}
	


}
