package es.uned.master.java;

/*
 * @Class: Esta clase hace la suma........
 * @author: fulanito
 * @date: febreo 2020
 * 
 */
public class Suma {
	
	// Este es el sumando primero
	// Considero que debe inicializarse a 0
	int v1;
	
	//
	//
	int v2;
	
	// Documenteis!!!!
	// Explicación del método
	// @in
	// @out
	// @err
	// Otras fecha...
	public Suma() {
		this.v1 = 0;
		this.v2 = 0;
	}
	
	public Suma(int v1, int v2) {
		this.v1 = v1;
		this.v2 = v2;
	}
	
	public int suma () {
		return this.v1+this.v2;
	}
	
	public String toString() {
		return "Suma de los valores: "+this.suma();
	}
	
}
