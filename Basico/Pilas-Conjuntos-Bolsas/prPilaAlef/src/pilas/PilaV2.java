package pilas;


public class PilaV2 {
	private Pilav2 pila;

	public PilaV2(){
		this.pila = new Pilav2();
	}

	public boolean esVacia(){
		return this.pila.esVacia();
	}

	public void push(Par p){
		this.pila.push(p);
	}

	public void pop(){
		this.pila = this.pila.pop();
	}

	public Par cabeza(){
		return this.pila.cabeza();
	}
	

	public String toString(){
		return pila.toString();
	}

			private class Pilav2{
				private Par valor;
				private Pilav2 siguiente;
				
				public Pilav2() {
					this.valor = null;
					this.siguiente = null;
				}
				
				public boolean esVacia() {
					return this.valor == null;
				}
				
				public void push(Par valor) {
					this.valor = valor;
					this.siguiente= new Pilav2();
				}
				
				public Pilav2 pop() {
					return this.siguiente;
				}
				
				public Par cabeza() {
					return this.valor;
				}
				
				public String toString() {
					Pilav2 aux = this;
					String str="[";
					while (aux.siguiente != null) {
						str += this.valor.intValue() + ", ";
						aux = aux.siguiente;
					}
					str += "]";
					return str;
				}
	}
}