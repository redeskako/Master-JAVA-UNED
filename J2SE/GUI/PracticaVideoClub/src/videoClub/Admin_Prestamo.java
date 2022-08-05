package videoClub;
import java.util.*;

public class Admin_Prestamo {
	
	//atributos
	private TreeMap<Usuario,Prestamo> mapa; 
	
	
	//constructores
	public Admin_Prestamo(){
		mapa = new TreeMap<Usuario, Prestamo>() ;
	}
	
	public Admin_Prestamo(Usuario u){
		mapa = new TreeMap<Usuario, Prestamo>(u) ;
	}
	
	public Admin_Prestamo(Usuario u, Prestamo p){
		mapa = new TreeMap<Usuario, Prestamo>(u) ;
		mapa.put(u, p) ;		
	}
	
	 
	
	//metodos
	
	//para insertar nuevo prestamo o actualizar el prestamo existente. el prestamo
	//nuevo machaca el anterior
	public void insertarPrestamo(Usuario usuario, Prestamo prestamo) throws VideoClubException{
		try{					
			mapa.put(usuario, prestamo) ;
		}catch (ClassCastException e){
			throw new VideoClubException("Error de Casting." + e) ;
		}catch (NullPointerException e){
			throw new VideoClubException(e.toString()) ;
		}
	}
	
	//obtiene el prestamo referente a un usuario
	public Prestamo obtenerPrestamo(Usuario usuario)throws VideoClubException{
		try{
			return mapa.get(usuario) ;
		}catch (ClassCastException e){
			throw new VideoClubException("Error de Casting." + e) ;
		}catch (NullPointerException e){
			throw new VideoClubException(e.toString()) ;
		}
		
		
	}
	
	//elimina a un usuario y su prestamo
	public void eliminar(Usuario usuario){
		mapa.remove(usuario) ;
	}
	
	public boolean existeUsuario(Usuario usuario) throws VideoClubException{
		try{
			return mapa.containsKey(usuario) ;
		}catch (ClassCastException e){
			throw new VideoClubException("Error de Casting." + e) ;
		}catch (NullPointerException e){
			throw new VideoClubException(e.toString()) ;
		}
	}
	
	public Object[] listaUsuarios(){
		return mapa.keySet().toArray() ;
	}
	
	public void actualizarPrestamo(Usuario usuario, Prestamo prestamo)throws VideoClubException {
		try{
			this.insertarPrestamo(usuario, prestamo) ;
		}catch (VideoClubException e){
			throw e ;
		}
	}
	
	
	public String toString(){
		return mapa.toString() ;
	}
	
	

}
