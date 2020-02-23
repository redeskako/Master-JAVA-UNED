package es.uned.master.java.coleccion;
import java.util.*;

import es.uned.master.java.basico.*;

public class Kwic {
	private Set<TituloKwic> noclaves;
	private Map<TituloKwic,Set<String>> kwic;

	//Inicializo la estructura
	public Kwic(){
		this.noclaves= new TreeSet();
		this.kwic= new TreeMap();
	}

/**
 * *Esta párte se tratará exclusivamente las noclaves
 */
	public void computaNoClaves(String noclaves){
		StringTokenizer strk= new StringTokenizer(noclaves," ,");

		while (strk.hasMoreTokens()){
			this.noclaves.add(new TituloKwic(strk.nextToken()));
		}
	}

/*
 * Esta parte se tratará de kwic usando las noclaves como consulta
 */
	// Tu solo vales para cuando una palabra SEA INDICE
	// Se utiliza con el compunta que recibe un String
	private void computaIndice(TituloKwic palabra, String frase){
		//Necesariamente se añade al map.
		// Si esta -> Solo añado la frase en el Set
		// Si no está -> Además de la frase el índice
		Set<String> frases= new TreeSet();
		if (this.kwic.containsKey(palabra)){
			// Que lo contiene
			 //Cariño dame ese valor de la palabra
			frases= this.kwic.get(palabra);
		}
		frases.add(palabra.reemplaza(frase));
		//frases.add(TituloKwic.reemplaza(palabra, frase));

		// Añadiro al map como nuevo y machaco el que había.
		this.kwic.put(palabra, frases);

	}

	// Este es yupi.
	//Mëtodod que recibe una frase y se computa al kwic
	// Con mucha gracia y dos pares

	public void computaIndice(String frase){
		//Creo un Tokenizer para poder extraer palaba a palabra
		StringTokenizer strk= new StringTokenizer(frase," ,");

		while (strk.hasMoreTokens()){
			// Primer paso para extraer la palabra de la frase
			TituloKwic palabra= new TituloKwic(strk.nextToken());

			// Segundo Detectar si esa palabra es indice o no ->
			// Si la palabra es NOCLAVE no computa nada ...no se hace me voy a la bartola ...que estoy agusto
			// Dame una cerveza
			if (!(this.noclaves.contains(palabra))){
				// Tercer paso -solamente para las indice- incluirlo en el kwic
				// Ole ya llegao
				this.computaIndice(palabra, frase);
			}
		}
	}
		private String escribeNoClaves(){
			String str="Palabras no claves: ";
			Iterator<TituloKwic> it = this.noclaves.iterator();
			while (it.hasNext()){
				str+= it.next().toString()+", ";
			}
			return str;
		}
			private String escribeKwic(Set<String> s){
				String str="";
				Iterator<String> it= s.iterator();
				while (it.hasNext()){
					str+= "\t"+it.next()+"\n";
				}
				return str;
			}
		private String escribeKwic(){
			String str="--INDICE--\n";
			Iterator<Map.Entry<TituloKwic,Set<String>>> it= this.kwic.entrySet().iterator();

			while (it.hasNext()){
				Map.Entry<TituloKwic,Set<String>> mp = it.next();
				str+= mp.getKey()+"\n";
				str+= escribeKwic(mp.getValue());
			}
			return str;
		}
 // Método para imprimir
 public String toString(){
	 String str="";
	 str+= this.escribeNoClaves();
	 str+= this.escribeKwic();
	 return str;
 }
}














