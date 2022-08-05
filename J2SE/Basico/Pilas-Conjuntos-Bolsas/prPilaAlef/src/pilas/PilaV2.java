package pilas;

import pilas.Par;
import pilas.PilaV2;
import pilas.Pilav2;

public class PilaV2 {
	private Pilav2 pila;

	public PilaV2(){
		 pila = new Pilav2();
	}

	public boolean esVacia(){
		return pila.esVacia();
	}

	public void push(Par p){
		pila.push(p);
	}

	public void pop(){
		pila.pop();
	}
	public Par cabeza(){
		return pila.cabeza();
	}
	public boolean equals(PilaV2 pila){
		return true;
	}

	public String toString(){
		return pila.toString();
	}

}

