package es.uned.master.java.basico;
import java.util.*;

public class TituloKwic implements Comparable<TituloKwic>{
	private String tk;

	public TituloKwic(String str){
		this.tk= str.toUpperCase();
	}
	public boolean equals(Object o){
		if (o instanceof TituloKwic){
			TituloKwic tk= (TituloKwic) o;
			return this.tk.equals(tk.toString());
		}else{
			throw new KwicException("No es un TituloKwic");
		}
	}
	public int hasCode(){
		return this.tk.hashCode();
	}
	public int compareTo(TituloKwic tk){
		return this.tk.compareToIgnoreCase(tk.toString().toUpperCase());
	}
	public String toString(){
		return this.tk.toUpperCase();
	}
	//Este metodo introduce una frase y un patrón y cambia el patron por los caracteres '...'

	public String reemplaza(String frase){
		StringTokenizer strk= new StringTokenizer(frase," ,");
		String resultado="";
		while (strk.hasMoreTokens()){
			String palabraAComparar= strk.nextToken();
			TituloKwic tk= new TituloKwic(palabraAComparar);
			if (this.tk.equals(tk.toString())){
				resultado += "... ";
			}else{
				resultado += palabraAComparar+" ";
			}
		}
		return resultado;
	}


/*
	public static String reemplaza (TituloKwic patron, String frase){
		StringTokenizer strk= new StringTokenizer(frase," ,");
		String resultado="";
		while (strk.hasMoreTokens()){
			String palabraAComparar= strk.nextToken();
			TituloKwic kw= new TituloKwic(palabraAComparar);
			if (patron.equals(kw)){
				resultado += "... ";
			}else{
				resultado += palabraAComparar+" ";
			}
		}
		return resultado;
	}
*/
}