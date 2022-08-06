package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*
 * Esta clase se crea para encriptar la constraseña con el algoritmo SHA-512 y que el resultado sea
 * igual al almacenado en la base de datos. Es decir que no se obtengan caracteres no alfabeticos 
 * que dan lugar a discordancias entre el valor almacenado en la bbdd y el obtenido con el 
 * del tipo ascii ºß_]F@yK:U	úà¤ã` ¯(úUÒ³(‰ò2å„?>–Ã^ÝWþ»ï"õo¯E?ŠÆþþeÿÇh¼€€
 * Con esto conseguimos que el valor de cifraso sea del tipo alfabetico
 *  ba1714df5f5d4640794b3a...96c35edd571cfebbef22f56faf459d8ac6fefe65ffc768bc808016
 */

public class Codificador {
		 
public static final String SHA512 = "SHA-512";
	 
	    public static String getEncoded(String texto, String algoritmo) throws NoSuchAlgorithmException {
	        MessageDigest md;
	        String output = "";
	        try {
	            md= MessageDigest.getInstance(algoritmo);
	            md.update(texto.getBytes());
	            byte[] mb = md.digest();
	            for (int i = 0; i < mb.length; i++) {
	                byte temp = mb[i];
	                String s = Integer.toHexString(new Byte(temp));
	                while (s.length() < 2) {
	                    s = "0" + s;
	                }
	                s = s.substring(s.length() - 2);
	                output += s;
	            }
	        } catch (NoSuchAlgorithmException nsae) {
	            nsae.printStackTrace();
	        }
	        System.out.println("SHA Output: "+ output);
	        return output;
	    }
	    
	    public static void main(String args[]) throws NoSuchAlgorithmException{
			   //String str = cryptWithMD5("alberto");
			   //System.out.println("Cifrado:"+str);
			   
			   String str2 = getEncoded("carlos", "SHA-512");
			   System.out.println("Cifrado de paco22:" + str2);
			   String str3 = CryptWithSHA512.digest("carlos", "SHA-512");
			   System.out.println("Cifrado de paco3:" + str3);
		   }
	    
}


