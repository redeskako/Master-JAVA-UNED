package videoClub;

import java.util.*;

public class Prestamo {
	//astributos
	
	private Usuario usuario;
	private ArrayList<Pelicula> listaPeliculas ;
	
	public Prestamo (Usuario usuario){
		this.usuario = usuario ;
		this.listaPeliculas = new ArrayList<Pelicula>() ;
	}
	
	public Prestamo (Usuario usuario, Pelicula pelicula){
		this.usuario = usuario ;
		this.listaPeliculas = new ArrayList<Pelicula>() ;
		this.listaPeliculas.add(pelicula) ;		
	}
	
	public Prestamo (Usuario usuario, ArrayList<Pelicula> lista){
		
		this.usuario = usuario ; 
		this.listaPeliculas = new ArrayList<Pelicula>(lista) ;
		
	}
	
	//metodos get	

	public Usuario getUsuario(){
		return this.usuario.clone() ;
	}
	
	public ArrayList<Pelicula> getPeliculas(){
		return (ArrayList<Pelicula>)this.listaPeliculas.clone() ;		
	}
	
	//metodos set
	public void setUsuario(Usuario usuario){
		this.usuario = usuario ;
	}
	
	
	
	//metodos generales
	public void añadirPelicula(Pelicula pelicula) throws VideoClubException{
		if (this.listaPeliculas.contains(pelicula))
			throw new VideoClubException("La pelicula está ya en el acutal prestamo") ;
		
		this.listaPeliculas.add(pelicula) ;
	}
	
	public void eliminarPelicula(Pelicula pelicula){
		if (this.listaPeliculas.contains(pelicula))
			throw new VideoClubException("La pelicula no estça en el prestamo") ;
		
		this.listaPeliculas.remove(pelicula) ;
	}
	
	public int numeroPeliculas(){
		return this.listaPeliculas.size() ;
	}
	
	public String toString(){
		return ("Usuario : " + this.usuario.toString() + ". Peliculas: " + this.listaPeliculas.toString() ) ;
	}
	
	
}
