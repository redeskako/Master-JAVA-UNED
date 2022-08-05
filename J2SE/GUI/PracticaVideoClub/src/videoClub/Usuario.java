package videoClub;
import java.util.*;

public class Usuario implements Comparable, Comparator{
	//atributos de usuario
	private String nombre ;
	private String apellidos ;
	private String DNI ;
	private int id_cliente ;
	private Date  alta;
		
	
	public Usuario (String nombre, String apellidos,String DNI ,int id_cliente, Date alta){
		this.nombre = nombre ;
		this.apellidos = apellidos ;
		this.DNI = DNI ;
		this.id_cliente = id_cliente ;
		this.alta = alta ;
		
	}
	
	public Usuario(String nombre, String apellidos, String DNI){
		this.nombre = nombre ;
		this.apellidos = apellidos ;
		this.DNI = DNI ;
		this.id_cliente = 0 ;
		this.alta = new Date() ;
		
	}
	
	//metodos gets
	public String getNombre (){
		return this.nombre ;
	}
	
	public String getApellidos(){
		return this.apellidos ;
	}
	
	public String getDNI(){
		return this.DNI ;
	}
	
	public int getId_Cliente(){
		return this.id_cliente ;
	}
	
	public Date getalta(){
		return this.alta ;
	}
	
	//metodos sets	
	public void setNombre(String nombre){
		this.nombre = nombre ;
	}
	
	public void setApellidos(String apellidos){
		this.apellidos = apellidos ;
	}
	
	public void setDNI(String DNI){
		this.DNI = DNI ;
	}
			
	public void setId_cliente(int id){
		this.id_cliente = id ;
	}

	public void setAlta(Date alta){
		this.alta = alta ;
	}
	
	
	//otros metodos
	public String toString(){
		return "Usuario" + this.nombre + " " + this.apellidos + ", " + this.DNI + ", " + this.id_cliente + ", fecha alta: " + this.alta.toString() ;
	}
	
	public Usuario clone(){
		Usuario u = new Usuario(this.nombre, this.apellidos, this.DNI, this.id_cliente, this.alta);
		
		return u ;
	}
	
	//interfaz comparator , comparable
	
	public boolean equals (Object o) throws ClassCastException{
		Usuario u = (Usuario) o ;
		
		//dos usuarios son iguales cuando lo son su nombre, apellidos, dni, id_cliente
		return ((this.nombre==u.getNombre())&&
				(this.apellidos==u.getApellidos())
				&&(this.DNI== u.getDNI())&&
				(this.id_cliente == u.getId_Cliente())&&
				(this.alta==u.alta)) ;
	}
	
	
	public int compareTo(Object o) throws ClassCastException{
		Usuario u = (Usuario) o ;		
		//los usuarios los ordenamos por id_cliente
		return (this.id_cliente=u.id_cliente) ;
	}
	
	public int compare(Object o1, Object o2) throws ClassCastException{
		Usuario u1 = (Usuario) o1 ;
		Usuario u2 = (Usuario) o2 ;
		
		return u1.compareTo(u2) ;
	}
	

	
	
	

}
