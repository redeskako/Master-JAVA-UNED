package es.uned.master.java.kwic.logica.coleccion;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

import es.uned.master.java.kwic.logica.modelo.TituloKwic;

public class KwicColeccion {
	
	private Set<TituloKwic> noclaves;
	private Map<TituloKwic, Set<String>> kwic;

	//Constructor Inicializo la estructura
	public KwicColeccion() {
		this.noclaves = new TreeSet();
		this.kwic = new TreeMap();
	}

	/***************************************************************************************************
	 *Funcion computaNoClaves:
	 *
	 * @param noclaves: de tipo string, contiene las palabras no clave
	 * Se guardan las palabras no claves separadas por espacio en blanco
	 */
	public void computaNoClaves(String noclaves) {
		System.out.println("******************************computaNoClaves");
		StringTokenizer strk = new StringTokenizer(noclaves, " ");

		while (strk.hasMoreTokens()) {
			this.noclaves.add(new TituloKwic(strk.nextToken()));
		}
		System.out.println("Procesando no claves:" + this.noclaves);
	}

	
	private void computaIndice(TituloKwic palabra, String frase) {

		//Creeamos un objeto TreeSet donde guardamos las palabras no claves 
		Set<String> frases = new TreeSet();
		if (this.kwic.containsKey(palabra)) {
 
			//En frases se guardan las palabras no clave
			frases = this.kwic.get(palabra);
		}
		//Remplazamos en la frase la palabra por ....
		frases.add(palabra.reemplaza(frase));
		// 

		// añado al map como nuevo y machaco el que habia
		this.kwic.put(palabra, frases);

	}												

	/***************************************************************************************************
	 *Funcion computaIndices:
	 *
	 * @param frase: de tipo String, contiene cada frase
	 * A partir de las frases obtenemos el correspondiente índice. 
	 */
	public void computaIndice(String frase) {
		System.out.println("******************************computaIndice");
		// Creamos un Tokenizer para poder extraer palaba a palabra
		StringTokenizer strk = new StringTokenizer(frase, " ");

		while (strk.hasMoreTokens()) {
			// Extraemos la palabra de la frase
			TituloKwic palabra = new TituloKwic(strk.nextToken());

			//Comprobamos si la palabra es no clave
			if (!(this.noclaves.contains(palabra))) {
				this.computaIndice(palabra, frase);
			}
		}
	}

	private String escribeNoClaves() {
		String str = "Palabras no claves: ";
		Iterator<TituloKwic> it = this.noclaves.iterator();
		while (it.hasNext()) {
			str += it.next().toString() + ", ";
		}
		str = str.substring(1, str.length()-2);
		System.out.println(str);
		return str;
	}

	private String escribeKwic(Set<String> s) {
		String str = "";
		Iterator<String> it = s.iterator();
		while (it.hasNext()) {
			str += "\t" + it.next() + "\n";
		}
		return str;
	}

	private String escribeKwic() {
		String str = "\n--Indice resultante--\n";
		Iterator<Map.Entry<TituloKwic, Set<String>>> it = this.kwic.entrySet()
				.iterator();

		while (it.hasNext()) {
			Map.Entry<TituloKwic, Set<String>> mp = it.next();
			str += mp.getKey() + "\n";
			str += escribeKwic(mp.getValue());
		}
		System.out.println(str);
		return str;
	}

	/***************************************************************************************************
	 *Funcion toString:
	 *
	 * @return String
	 * Construimos el resulta de Kwic 
	 */
	public String toString() {
		String str = "";
		str += this.escribeNoClaves();
		str += this.escribeKwic();
		return str;
	}
}
