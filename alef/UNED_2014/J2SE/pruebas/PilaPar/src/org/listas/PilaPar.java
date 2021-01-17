package org.listas;

import org.coordenadas.*;

public class PilaPar {
	// Declaración de las propiedades del objeto
	private Par [] lista; //Lista de pares que emulará una pila no acotada
	private int cabeza; // La cabeza que apunta a la lista
	private static final int NULO=-1;
	private int TOPE=10;

		private Par [] inicializa(int tope){
			Par [] res= new Par[tope];
			for (int i=0;i<tope;i++){
				res[i]=null;
			}
			return res;
		}
	public PilaPar(){
		this.cabeza=PilaPar.NULO;
		this.lista=this.inicializa(this.TOPE);
	}
		/*
		 * Metodo privado que coge una lista llena y un par y la agranda con el par integrado
		 * Se agranda el doble de tamaño de la lista
		 */
		private Par[] llena(Par []lista, Par p){
			int cabezaant=this.TOPE;
			// crear un array de tope*2 y cambiar TOPE a TOPE*2
			Par[] res= this.inicializa(this.TOPE*2);
			this.TOPE= this.TOPE*2;
			// Asignar
			for(int i=0;i<cabezaant;i++){
				res[i]= lista[i];
			}
			res[cabezaant]= p;
			return res;
		}
	/*
	 * Añade un Par a la pila
	 */
	public void push(Par p){
		if (this.cabeza<this.TOPE){
			// Tengo caso de pila vacia
			// Tengo caso de pila con Pares pero que no está llena en mi array
			this.lista[cabeza+1]= p;
		}else{
			// El array está llenito.
			this.lista=llena(this.lista,p);
		}
		this.cabeza++;
	}
	/*
	 * Extrae un Par de la pila y lo elimina
	 */

	public boolean equals(Object o){
		PilaPar p = (PilaPar) o;
		boolean esIgual= true;
		if (!this.esVacia() && !p.esVacia()) {

			for (int i=cabeza;i==-1;i-- ){
				if (this.lista[i]!=p.pop()){
					esIgual=false;
					i=0;
				}
			}
		}
		return esIgual;
	}
	public Par pop(){
		Par p;
		if (this.esVacia()) {
			p=null;
		}else {
			p= this.lista[cabeza];
			this.lista[cabeza]=null; //eliminamos el valor de la posicion 
			this.cabeza--;
	
		}
		return p;			
	}
	public String toString(){
		String cadena;		
		if (this.esVacia()) {
			return "La pila esta vacia";
		}else{
			cadena = "[ " ;
			for (int i=0; i< this.cabeza;i++){
                                //this.lista[i] devuelve un par y hacemos uso del toString de la clase Par
				cadena = cadena + this.lista[i].toString() + " ";
			}
			cadena = cadena  + "]";
			return "";
		}		
	}

	public boolean esVacia(){
		return this.cabeza==PilaPar.NULO;
	}
}
