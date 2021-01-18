package pilas;

public class Nodo {
	Par p;
	Nodo siguiente;
	Nodo(Par par){
		p = par;
		siguiente = null;
	}
	Nodo(Par par,Nodo sig){
		p = par;
		siguiente = sig;
	}
}
