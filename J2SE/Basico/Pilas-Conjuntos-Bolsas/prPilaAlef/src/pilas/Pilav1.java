package pilas;

import java.util.EmptyStackException;



public class Pilav1 {
	//constante
	public static final int TOPE = 10;

	//para incrementar el array utilizo mult, que me indicará cual será su
	//nuevo tamaño:
	private int mult = 1;
	private Par [] pila ;
	private int cabeza ;

	//método constructor

	public Pilav1(){
		pila = new Par[TOPE] ;
		cabeza = -1 ;
	}

	public boolean esVacia(){
		return (cabeza == -1) ;
	}

	public void push(Par p){
		// incrementamos y asignamos
		if (cabeza <= pila.length-1){
			pila[++cabeza] = new Par (p.getX(),p.getY());
		}
		else{
			Par [] pila1 = this.duplica();
			this.cabeza++;
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
		for (int i=0 ; i<TOPE ; i++){
			pila2[i] = pila[1];
		}
		return pila2;
	}
	public void pop(){
		if(this.cabeza > -1){
			cabeza--;

		}
		else{
			throw new MiPilaException("La pila está vacía");
		}
	}
	public Par cabeza(){

		if (esVacia()){
			throw new MiPilaException("La pila no tiene elementos");
		}
		else{
			return pila[cabeza];
		}


	}
	public boolean equals(Pilav1 pila){
		boolean iguales = false;
		for(int i=0 ; i<=cabeza;++i){
			if ((this.pila[i].getX() == pila.cabeza().getX()) &&
			   (this.pila[i].getY() == pila.cabeza().getY())){
				iguales = true;
			}
		}
		return iguales;
	}

	public String toString(){
		String res = "["; // lo encierro entre corchetes
		for(int i=0 ; i<=this.cabeza ; i++){//recorro el array de pila
			res += pila[i];
			if(i<this.cabeza){
				res += ", ";
			}

		}
		res += "]";
		return res;
	}
}
