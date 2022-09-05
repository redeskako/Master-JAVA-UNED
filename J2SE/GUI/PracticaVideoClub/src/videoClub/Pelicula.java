/**Clase: Pelicula
 * Descripción: Clase cuyas instancias representan peliculas, una pelicula está
 * compuesta por un titulo , de tipo String, las instancias manejan una variable 
 * privada de tipo boolean, que representa el estado de la instancia, true significa
 * que la pelicula actualmente la tiene un cliente.
 * 
 * Constructor: Pelicula(String titulo)
 * Metodos get:  getTitulo(), getEstado()
 * Metodos set: settitulo(), setEstado()
 * 
 */
package videoClub;

import java.util.* ;

public class Pelicula implements Comparable{
	
	//atributos
	private String titulo ;
	private int idPelicula ;
	private boolean enPrestamo ;
	
	//constructores
	
	public Pelicula(String titulo, int id){
		this.titulo = titulo ;
		this.idPelicula = id ;
		this.enPrestamo = false ;
	}
		
	//METODOS DE ACCESO
	public String getTitulo(){
		return this.titulo ;
	}
	
	public int getId(){
		return this.idPelicula ;
	}
		
	public boolean getEstado(){
		return enPrestamo ;
	}
	
	
	public void setTitulo(String titulo){
		this.titulo = titulo ;		
	}
	
	public void setEstado(boolean prestamo){
		this.enPrestamo = prestamo ;
	}
	
	//interfaces comparable, compareTo
	public boolean equals (Object o) throws ClassCastException{
		Pelicula p = (Pelicula) o ;
	
		//dos peliculas son iguales cuando lo es su titulo
		return this.titulo.equals(p.getTitulo()) ;
	}
	
	public int compareTo(Object o) throws ClassCastException{
		Pelicula p = (Pelicula) o ;
		return this.titulo.compareTo(p.getTitulo());
		
	}
	
	public int compare(Object o1, Object o2) throws ClassCastException{
		Pelicula p1 = (Pelicula) o1 ; 
		Pelicula p2 = (Pelicula) o2 ;
		
		return p1.compareTo(p2) ;
	}
	
	
	public String toString(){
		String cadena = "Pelicula: " + this.titulo ;
		if (this.enPrestamo)
			cadena = cadena + " en prestamo." ;
		else
			cadena = cadena  + " en stock" ;
		
		return cadena ;
	}
	

}
