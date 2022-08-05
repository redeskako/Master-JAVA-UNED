package org.listas;

import org.coordenadas.*;
//import java.lang.annotation.*;
public class PilaParAc implements IPila{
	// Declaración de las propiedades del objeto
	private Par [] lista; //Lista de pares que emulará una pila no acotada
	private int cabeza; // La cabeza que apunta a la lista
	public static final int NULO=-1;
	private final int TOPE;
	
	
	//private int hola=3;

		private Par [] inicializa(int tope){
			Par [] res= new Par[tope];
			for (int i=0;i<tope;i++){
				res[i]=null;
			}
			return res;
		}
	public PilaParAc(int tope){
		this.TOPE=tope;
		this.cabeza=PilaPar.NULO;
		this.lista=this.inicializa(this.TOPE);
	}
	public boolean esLlena(){
		return (this.cabeza==(this.TOPE-1));
	}
	/*
	 * Añade un Par a la pila acotada
	 */
/*	public void push(Par p){
		try{
			this.lista[cabeza+1]= p;
			this.cabeza++;
		}catch(IndexOutOfBoundsException e){
			throw (new PilaParException("Pila llena\t"));
		}
	}
*/	
	public void push(Par p) throws IndexOutOfBoundsException{
		this.lista[cabeza+1]= p;
		this.cabeza++;
	}
	/*
	 * Extrae un Par de la pila y lo elimina
	 */
	public Par pop(){
		return null;
	}
	public boolean esVacia(){
		return false;
	}
	public boolean equals(Object o){
		return false;
	}
	public String toString(){
		return "";
	}
	public void prueba(PilaPar prov){
		
	}
	
}
