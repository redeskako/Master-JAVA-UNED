package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CryptWithMD5 {
	   private static MessageDigest md;

	   public static String cryptWithMD5(String pass){
	    try {
	        md = MessageDigest.getInstance("MD5");
	        byte[] passBytes = pass.getBytes();
	        md.reset();
	        byte[] digested = md.digest(passBytes);
	        StringBuffer sb = new StringBuffer();
	        for(int i=0;i<digested.length;i++){
	            sb.append(Integer.toHexString((0xff & digested[i])| 0x100).substring(1,3));
	        }
	        return sb.toString();
	    } catch (NoSuchAlgorithmException ex) {
	       // Logger.getLogger(CryptWithMD5.class.getName()).log(Level.SEVERE, null, ex);
	    }
	        return null;


	   
	}
	   public static void main(String args[]){
		   String str = cryptWithMD5("alberto");
		   System.out.println("Cifrado de alberto:"+str);
		   
		   //String str2 = digest("paco", "SHA-512");
		   //System.out.println("Cifrado:" + str2);
	}
}
	   
	   
	   
	   
	   
	   
	   

	   
	   
	   
	