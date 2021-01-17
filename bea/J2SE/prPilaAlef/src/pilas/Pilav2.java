package pilas;

import pilas.Par;
import pilas.PilaV2;
import pilas.Pilav2;

public class Pilav2 {


	// Número de elementos que contiene la pila enlazada.
	private int numElementos;
	private Par cabeza;

	// primer nodo de la pila.

	private Nodo primero;

	private Nodo siguiente;

	/**
	 * Definimos una clase estática que representa un nodo de una pila.
	 * Cada nodo contiene el valor almacenado y una referencia a otro nodo.
	 */

	static class Nodo{
		//Dato que contiene el nodo
		protected Par p;

		//Referencia al nodo siguiente
		protected Nodo siguiente;

		/**
		 * Constructor que crea un nodo con el valor del parámetro y sin ninguna
		 * relación con otro nodo
		 * @return
		 */

		protected Nodo(Par par){

			p = par;
			siguiente = null;
		}

		/**
		 * Constructor que crea un nodo con el valor pasado como parámetro
		 * y unido a otro nodo (el siguiente)
		 *
		 * par --> valor que contiene el nodo.
		 * sig --> nodo con el que se relaciona.
		 */
		protected Nodo(Par par,Nodo sig){
			p = par;
			siguiente = sig;
		}

	}

    public Pilav2(){
    	numElementos = 0;
    	primero = null;
    }

	public boolean esVacia(){
		return numElementos == 0;
	}

	public void push(Par par){

		if(numElementos == 0){//si es el primer nodo a insertar
			Nodo nuevoNodo = new Nodo(par);
			primero = nuevoNodo;

		}
		else{//hay más elementos en la pila
			Nodo nuevoNodo = new Nodo(par,primero);
			primero = nuevoNodo;

		}
		cabeza = primero.p;
		numElementos++;
	}

	public void pop(){
		if (numElementos == 0){
			throw new MiPilaException("No hay elementos en la pila");
		}
		else{
			Nodo nuevoNodo = primero;
			primero = primero.siguiente;
			nuevoNodo = null;
		}
	}
	public int tamaño(){
		return numElementos;
	}
	/*public boolean equals(Nodo pila){
		return ();
	}*/
	public Par cabeza(){

		return cabeza;
	}

	public String toString(){
        Nodo aux = primero;
        String res = "[";
        while(aux != null){

            res += aux.p.toString();
            aux = aux.siguiente;
        }
        res += "]";
        return res;
    }

}

