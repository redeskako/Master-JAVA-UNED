
package org.listas;

import org.coordenadas.*;
import java.util.*;

//import java.lang.annotation.*;
public class PilaPar implements IPila{

	
	private Stack pila;


	public PilaPar(){
		this.pila= new Stack();
		
	}
	public void push(Par p){
		this.pila.push (p);
		
	}
	/*
	 * Extrae un Par de la pila y lo elimina
	 */
	public Par pop(){
		try {
			return (Par) (this.pila.pop());
		}catch (EmptyStackException e ){
			throw (new PilaParException("Pila vacia\t"));
		}
		
	}
	
	public boolean esVacia(){
//		return (this.cabeza==PilaPar.NULO);
		return this.pila.empty();
		
	}
	public boolean equals(Object o){
		return this.pila.equals(o);
	}
	public String toString(){
		return this.pila.toString();
	}

}

	