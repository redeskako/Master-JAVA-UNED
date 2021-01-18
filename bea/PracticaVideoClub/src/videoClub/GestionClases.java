package videoClub;

import java.util.Date;

import BBDD.*;

public class GestionClases {
	private Admin_Prestamo mapa ;
	private StockPelicula listaPeliculas ;
	private ListaUsuario listaClientes ;
	private GestionBaseDatos bbdd ;
	
	
	public GestionClases(){
		bbdd = new GestionBaseDatos() ;
		
		//conectamos
		bbdd.CargaDriver() ;
		bbdd.Conectar() ;
		
		mapa = bbdd.RellenarMapa() ;
		listaPeliculas = bbdd.obtenerPeliculas() ;
		listaClientes = bbdd.obtenerUsuarios() ;
		
		bbdd.Desconectar() ;
	}
	
	
	public void insertarCliente(String nombre, String apellidos,String DNI){
		Date fecha ;
		fecha = new Date() ;
		
		int id_cliente = bbdd.insertarUsuario(DNI, nombre, apellidos, fecha) ;		
		
		Usuario u = new Usuario( nombre,  apellidos, DNI , id_cliente, fecha) ;
		this.listaClientes.InsertaUsuario(u) ;
	}
	
	public void insertarPelicula (String titulo){
		int id_pelicula = bbdd.insertarPelicula(titulo) ;
		this.listaPeliculas.InsertaPelicula(new Pelicula (titulo, id_pelicula)) ;
	}
	
	public void insertarPrestamo(int id_cliente, int id_pelicula){
		Usuario u = this.listaClientes.getUsuario(id_cliente) ;
		Prestamo p = this.mapa.obtenerPrestamo(u) ;
		Pelicula peli = this.listaPeliculas.getPelicula(id_pelicula) ;
		p.añadirPelicula(peli) ;
		this.mapa.actualizarPrestamo(u, p) ;
		
		//insertar prestamo en base de datos 
		//bbdd.insertar(u,peli) ;
	}
	
	public void borrarCliente(int id_cliente){
		Usuario u = this.listaClientes.getUsuario(id_cliente) ;
		this.listaClientes.EliminaUsuario(u) ;
		this.mapa.eliminar(u) ;
		
		//eliminar cliente base de datos
		//bbdd.eliminarcliente(u.getId())
	}
	
	public void borrarPelicula(int id){
		Pelicula p = this.listaPeliculas.getPelicula(id) ;
		this.listaPeliculas.EliminaPelicula(p) ;
		//suponemos que no está en prestamo
		
		//borrar pelicula de base de datos
		//bbdd.borrarPelicula(p)
	}
	
	public void borrarPrestamo(int id_cliente, int id_pelicula){
		Usuario u = this.listaClientes.getUsuario(id_cliente) ;
		Prestamo p = this.mapa.obtenerPrestamo(u) ;
		Pelicula peli = this.listaPeliculas.getPelicula(id_pelicula) ;
		p.eliminarPelicula(peli) ;
		
		//si el prestamo se keda vacio eliminamos al cliente
		if (p.numeroPeliculas()==0)
			this.mapa.eliminar(u) ;
		else
			this.mapa.actualizarPrestamo(u, p) ;
		
		//borrar pelicula de base de datos
		//bbdd.borrarPrestamo(id_cliente, id-pelicula)
		
	}
	

	
	

}
