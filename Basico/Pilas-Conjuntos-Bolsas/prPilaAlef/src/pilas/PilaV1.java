package pilas;

import java.util.EmptyStackException;



public class PilaV1{
	//constante
	public static final int TOPE = 10;

	//para incrementar el array utilizo mult, que me indicará cual será su
	//nuevo tamaño:
	private Par [] pila;
	private int cabeza;

	//método constructor

	public PilaV1(){
		pila = new Par[TOPE] ;
		cabeza = -1;
	}

	public boolean esVacia(){
		return (cabeza == -1);
	}

	public void push(Par p){
		// incrementamos y asignamos
		this.cabeza++;
		if (this.cabeza < pila.length){
			this.pila[this.cabeza] = p;
		}else{
			Par [] pila1 = this.duplica();
			pila1[this.cabeza] = p;
			this.pila = pila1;
		}
	}

	/**
	 * Me creo un método duplica que me crea la nueva pila con el doble de
	 * tamaño
	 */

	private  Par[] duplica(){
		Par [] pila2 = new Par [this.pila.length*2];
		for (int i=0 ; i<(this.pila.length-1) ; i++){
			pila2[i] = pila[1];
		}
		return pila2;
	}
	
	public void pop(){
		if (!this.esVacia()){
			this.cabeza --;
		}else{
			throw new MiPilaException("La pila está vacía");
		}
	}

	public Par cabeza(){
		if (this.esVacia()){
			throw new MiPilaException("La pila no tiene elementos");
		}else{
			return pila[this.cabeza];
		}
	}
	
	public boolean equals(PilaV1 pila){
		boolean iguales = true;
		if ((this.cabeza==-1) && (pila.esVacia())){
			return iguales;
		}

		int i = this.cabeza;
		while (iguales && (!pila.esVacia()) && (i > -1)){
				iguales = this.pila[i].equals(pila.cabeza());
				pila.pop();
				i --;
		}
		if ((pila.esVacia()) && (i == -1)){
			return true;
		}else{
			return false;
		}
	}
	
	public String toString(){
		String res = "[";						// lo encierro entre corchetes
		for(int i=0 ; i<=this.cabeza ; i++){	//recorro el array de pila
			res += pila[i];
			if(i<this.cabeza){
				res += ", ";
			}
		}
		res += "]";
		return res;
	}
}