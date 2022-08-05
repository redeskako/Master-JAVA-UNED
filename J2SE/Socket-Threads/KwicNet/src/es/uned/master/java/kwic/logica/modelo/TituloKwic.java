package es.uned.master.java.kwic.logica.modelo;

import java.util.*;

public class TituloKwic implements Comparable<TituloKwic> {
	
	private String tk;
	//Constructor
	public TituloKwic(String str) {
		this.tk = str.toUpperCase();
	}
	/***************************************************************************************************
	 *Funcion equals
	 *
	 * @param o: de tipo Object 
	 * @return boolean
	 * Se sobrescribe la funcion equals, comparar si lo que se le pasa es una instancia/objeto de TituloKwic
	 */
	public boolean equals(Object o) {
		if (o instanceof TituloKwic) {
			TituloKwic tk = (TituloKwic) o;
			return this.tk.equals(tk.toString());
		} else {
			throw new KwicException("No es un TituloKwic");
		}
	}
	/***************************************************************************************************
	 *Funcion hasCode
	 * 
	 *  @return int
	 * Se sobreescribe la funcion hasCode(),devuelve el hash code del atributo del objeto TituloKwic.
	 * El hash code es un entero y será 0 cuando el string es vacio 
	 */
	public int hasCode() {
		return this.tk.hashCode();
	}
	/***************************************************************************************************
	 *Funcion compareTo
	 * 
	 * @param tk: de tipo Titulo<kwic
	 *  @return int 
	 * Esta funcion se sobrescribe,compara el atributo del objeto TituloKwic con el objeto que se le pasa por parametro. Devuelve un 0 si son iguales. 
	 */
	public int compareTo(TituloKwic tk) {
		return this.tk.compareToIgnoreCase(tk.toString().toUpperCase());
	}
	/***************************************************************************************************
	 *Funcion toString
	 * 
	 * @return int
	 * Transforma la cadena a mayusculas
	 */
	public String toString() {
		return this.tk.toUpperCase();
	}

	// Este metodo introduce una frase y un patrÃ³n y cambia el patron por los
	// caracteres '...'
	/***************************************************************************************************
	 *Funcion reemplaza
	 * 
	 * @param frase: de tipo String
	 *  @return String
	 * Remplazamos el indice por ... en la frase 
	 */

	public String reemplaza(String frase) {
		StringTokenizer strk = new StringTokenizer(frase, " ,");
		String resultado = "";
		while (strk.hasMoreTokens()) {
			String palabraAComparar = strk.nextToken();
			TituloKwic tk = new TituloKwic(palabraAComparar);
			if (this.tk.equals(tk.toString())) {
				resultado += "... ";
			} else {
				resultado += palabraAComparar + " ";
			}
		}
		return resultado;
	}
}
