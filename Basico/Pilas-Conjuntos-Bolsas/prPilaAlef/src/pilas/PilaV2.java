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
		this.pila = this.pila.push(p);
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
				public Pilav2(Par p) {
					this.valor = p;
					this.siguiente = null;
				}
				
				public boolean esVacia() {
					return this.valor == null;
				}
				
				public Pilav2 push(Par valor) {
					if (this.valor == null) {
						this.valor = valor;
						return this;
					}else{
						Pilav2 primero = new Pilav2(valor);
						primero.setSiguiente(this);
						return primero;
					}
				}
				
					private void setSiguiente(Pilav2 siguiente){
						this.siguiente = siguiente;
					}
				
				public Pilav2 pop() {
					return this.siguiente;
				}
				
				public Par cabeza() {
					return this.valor;
				}
					
					private Pilav2 siguiente(){
						return this.siguiente;
					}
				
				public String toString() {
					Pilav2 aux = this;
					String str="[";
					while ( (aux.cabeza() instanceof Par) && (aux.siguiente() instanceof Pilav2)) {
						str += aux.cabeza() + ", ";
						aux = aux.siguiente();
					}
					if (aux.cabeza() instanceof Par) {
						str += aux.cabeza();
					}
					str += "]";
					return str;
				}
	}
}