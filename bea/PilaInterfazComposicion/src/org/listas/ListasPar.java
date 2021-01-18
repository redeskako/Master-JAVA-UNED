package org.listas;

import org.coordenadas.Par;

public class ListasPar {
	private Par[] p;
	public final int TOPE; //si deseamos que todas las listas 
						   //tengan el mismo tamaño se declara static
		
	private Par [] inicializa(){
		Par [] p= new Par[this.TOPE];//array de pares a null
		for (int i=0;i<this.TOPE;i++){
			p[i]=null;
		}
		return p;
	}
	public ListasPar(){
		this.TOPE= 10;
		this.p= new Par[this.TOPE];
		this.p=this.inicializa();
	}
	public ListasPar(int tope){
		this.TOPE= tope;
		this.p= new Par[this.TOPE];
		this.p=this.inicializa();
	}
	
	
	public void anadir(Par x, int pos){

	}
	
	public void extraer( int pos){
		
	}
	
	public Par obtener(int pos){
		return null;
	}
	
	
	public boolean pertenece(Par x){
		return false;
	}
	
	
	public boolean equals(Object o){
		// 2 listas son iguales si lo son todos sus elementos
		//(sus elementos son pares)
		ListasPar l= (ListasPar) o;
		boolean esIgual=true;
		int i=0;
		
		if (this.p.length!=l.p.length){
			while ((esIgual) && (i<this.TOPE)){
				esIgual= (p[i].equals(l.obtener(i)));//P es un par por lo que este equals es el de los pares
				i++;
			}
			return esIgual;
		}
		else return false;
	}
}
