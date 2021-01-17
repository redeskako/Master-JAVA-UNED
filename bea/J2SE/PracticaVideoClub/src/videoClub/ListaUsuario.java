package videoClub;
import java.util.*;


public class ListaUsuario {	
	private TreeSet<Usuario> ListaIdUsuario;
	
	//constructor
	public ListaUsuario(){
		this.ListaIdUsuario=new TreeSet<Usuario>();
	}
	
	public ListaUsuario(Usuario usuario){
		this.ListaIdUsuario=new TreeSet<Usuario>();
		this.ListaIdUsuario.add(usuario) ;
	}


	//metodos añadir, listar y eliminar clientes
	public Usuario[] listarUsuario (){
		Usuario[] aux = (Usuario [])this.ListaIdUsuario.toArray() ; 
		return 	aux ;
	}
	
	public void InsertaUsuario (Usuario usuario) {
		this.ListaIdUsuario.add(usuario);
	}

	public void EliminaUsuario (Usuario usuario) {
		this.ListaIdUsuario.remove(usuario);
	}
	
	public Usuario getUsuario (int id){		
		boolean encontrado = false ; 
		Usuario res = null ;
		Iterator it = this.ListaIdUsuario.iterator() ;
		while ((it.hasNext())&&(!encontrado)){
			res = (Usuario)it.next() ;
			if (res.getId_Cliente()==id){
				encontrado = true ;				
			}
			else
				it.next() ;			
		}
		if (encontrado) 
			return res ;
		else 
			throw new VideoClubException("Usuario no encontrado") ;
		
		
		
	}
	
	
}
