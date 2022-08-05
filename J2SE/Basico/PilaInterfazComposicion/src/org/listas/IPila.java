package org.listas;
import org.coordenadas.Par;

public interface IPila {
	public boolean equals(Object o);
	public boolean esVacia();
	public void push(Par p);
	public Par pop();
	public String toString();
}
