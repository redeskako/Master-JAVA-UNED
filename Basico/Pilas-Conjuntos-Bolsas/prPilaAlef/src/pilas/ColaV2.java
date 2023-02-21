package pilas;


public class ColaV2 {
	private Colav2 pila;

	public ColaV2(){
		this.pila = new Colav2();
	}

	public boolean esVacia(){
		return this.pila.esVacia();
	}

	public void push(Par p){
		this.pila = this.pila.push(p);
	}

	/*
	 * 
	 */
	public void pop() throws MiPilaException{
			this.pila = this.pila.pop();
	}

	public Par cabeza(){
		return this.pila.cabeza();
	}
	

	public String toString(){
		return pila.toString();
	}

			private class Colav2{
				private Par valor;
				private Colav2 siguiente;
				
				public Colav2() {
					this.valor = null;
					this.siguiente = null;
				}
				public Colav2(Par p) {
					this.valor = p;
					this.siguiente = null;
				}
				
				public boolean esVacia() {
					return this.valor == null;
				}
				
				public Colav2 push(Par valor) {
					
					if (this.valor == null) {
						// Si la pila está vacía
						this.valor = valor;
						// Asigno el valor al el primer elemento.
						// La Pila contiene 1 solo Par. NO hay siguiente.
						this.siguiente = null;
						// Asigno a null el siguiente.
						return this;
					}else{
						Colav2 primero = new Colav2(valor);
						primero.setSiguiente(this);
						return primero;
					}
				}
				
					private void setSiguiente(Colav2 siguiente){
						this.siguiente = siguiente;
					}
				
				/*
				 * Método pop: Extra la cabeza de la pila.
				 * @in ninguno
				 * @out Colav2 con el elemento extrado
				 * @err MiPilaException cuando la pila es vacia.
				 */
				public Colav2 pop() {
						// presupongo que la pila NO es vacia.
						if (this.siguiente instanceof Colav2) {
							// La pila tiene 2 o más elementos
							Colav2 aux = this; // Creo un temporal para limpiar el elemento que me sobra.
							this.setSiguiente(this.siguiente);
							aux.setSiguiente(null); 
						} else {
							if (this.valor instanceof Par) {
								// La pila tiene 1 elemento;
								this.valor = null;
								this.siguiente = null;
							} else {
								// La pila tiene 0  elemento;
								throw new MiPilaException("La pila está vacia, no se puede hacer pop");
							}
						}
						return this.siguiente;
				}
				
				public Par cabeza() {
					return this.valor;
				}
					
					private Colav2 siguiente(){
						return this.siguiente;
					}
				
				public String toString() {
					Colav2 aux = this;
					String str="[";
					while ( (aux.cabeza() instanceof Par) && (aux.siguiente() instanceof Colav2)) {
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