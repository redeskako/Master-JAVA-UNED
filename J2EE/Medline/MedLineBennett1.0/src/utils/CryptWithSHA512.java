/*
 * Esta clase se crea para el cifrado con el SHA512 pero los 
 * resultados son en codigo ascii y no nos permiten realizar
 * la comparación con el cifrado del algoritmo almacenado en
 * la bbdd por lo que tenemos que utilizar la clase
 * Codificador
 */

package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CryptWithSHA512 {
	private static MessageDigest md;
	//encriptación con sha512
	   public static String digest(String str, String alg) {
	        try {
	            //MessageDigest md = MessageDigest.getInstance(alg); 

	        	md = MessageDigest.getInstance(alg); 
	            // Indicamos el algoritmo a usar
	 
	            return new String(md.digest(str.getBytes()));
	            // 'Digerimos' el mensaje
	        } catch (NoSuchAlgorithmException e) {
	            System.out.println("el algoritmo " + alg + " no existe");
	            return null;
	        }
	    }
	   
	   public static void main(String args[]){
		   		   
		   String str2 = digest("carlos", "SHA-512");
		   System.out.println("Cifrado de paco22:" + str2);
		   String str3 = CryptWithSHA512.digest("carlos", "SHA-512");
		   System.out.println("Cifrado de paco3:" + str3);
	   }
	
}   

 
	   
	   
	   
	