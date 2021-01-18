package videoClub;
import java.util.*;
import videoClub.*;

public class StockPelicula {


	private Object ob;
	private String titulo;
	private int stock;
	private TreeSet<Pelicula> ListaStock;
	private int suma=0;
	
	//constructor
	public StockPelicula (){
		this.ListaStock=new TreeSet<Pelicula>();
	}
	
	public StockPelicula (Pelicula pelicula){
		this.ListaStock=new TreeSet<Pelicula>();
		this.ListaStock.add(pelicula) ;
	}

	
	//metodos get y set
	public Pelicula[] getListaStock() {
		Pelicula [] aux = (Pelicula [])this.ListaStock.toArray(); ; 
		return aux ;
	}

	//metodos añadir, listar y eliminar peliculas	
	public void InsertaPelicula (Pelicula pelicula) {
		this.ListaStock.add(pelicula);
	}

	public void EliminaPelicula (Pelicula pelicula) {
		this.ListaStock.remove(pelicula);
	}
	
	/*public Stock (suma) {
		
		for (Iterator it=ListaStock.iterator(); it.hasNext( ); ) {  
			
			if(it.equals(titulo.toString().equals(titulo.toString()))) {
			//if (pelicula.equals(it)){    //it.equals(pelicula.getTitulo())) {
				//la pelicula es la misma
				suma++;
			}
			else 
				//no es la misma continuamos
			    ob = it.next();
					
			}
			return suma;
		} */
	
	public Pelicula getPelicula(int pelicula) throws VideoClubException{
		Iterator it = this.ListaStock.iterator() ;
		Pelicula res = null ;
		boolean encontrada = false ;
		while ((!encontrada) && (it.hasNext())){
			res = (Pelicula) it.next() ;
			if (res.getId()==pelicula){
				encontrada = true ;
			}
			else{
				it.next() ;
			}			
		}
		if (encontrada) 
			return res ;
		else 
			throw new VideoClubException("Pelicula no encontrada") ;
		
	}
}	
	

