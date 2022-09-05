//CLASE USUARIO
//Define cada usuario de la base de datos

package beans;
import java.util.*;

public class Usuario implements Comparable{

	private int id;
	private String usuario;
	private String clave;
	private int gestion; //1:administrador

	//constructor
	public Usuario(int id, String nombre, String clave,int gestion){
		this.id = id;
		this.usuario = nombre;
		this.clave = clave;
		this.gestion = gestion;
	}

	public Usuario(){
		this.id = 0;
		this.usuario = "";
		this.clave = "";
		this.gestion = 0;
	}

	public String clave(){
		return clave;
	}

	public void clave(String clave){
		this.clave = clave;
	}

	//get y set de las variables de clase
	public int Id(){
		return this.id;
	}

	public void Id(int id){
		this.id = id;
	}

	public String usuario(){
		return this.usuario;
	}

	public void usuario(String usu){
		this.usuario = usu;
	}

	//metodos
	public String toString(){
		return " (Usuario " + this.id + ":" + this.usuario + ":" + this.clave + ") ";
	}

	public int compareTo(Object o){
		Usuario u = (Usuario) o;
		return this.usuario.compareToIgnoreCase(u.usuario); //Ordenar por nombre
	}

	public boolean equals(Object o){
		Usuario u = (Usuario) o;
		return this.compareTo(u) == 0; //Dos usuarios son iguales si tienen el mismo nombre
	}

	public int getGestion(){
		return gestion;
	}

	public void setGestion(int gestion){
		this.gestion = gestion;
	}

	public String getNombreUsuario(int idusuario){
		String nombre = "";
		try{
			BBDD miBd = new BBDD();
			miBd.abrirConexion(); //No hacemos tratamiento de errores porque ya se ha hecho en el metodo abrirConexion
			nombre = miBd.getNombreUsuario("Select usuario from usuarios where id= ?", idusuario);
			miBd.cerrarConexion();//No hacemos tratamiento de errores porque ya se ha hecho en el metodo abrirConexion
		}catch(HealthtopicException e){
			System.out.println("Error en usuario " + e.getMessage());
		}catch(Exception e){
			System.out.println("Error desconocido" + e.getMessage());
		}
		return nombre;
	}

	public Usuario validarUsuario(String usuario, String pwd){
		Usuario usu = new Usuario();
		try{
			BBDD miBd = new BBDD();
			miBd.abrirConexion();
			usu = miBd.getUsuario("Select * from usuarios where usuario= ? and clave= ?", usuario, pwd);
			miBd.cerrarConexion();
		}catch(HealthtopicException e){
			System.out.println("Error en usuarioo " + e.getMessage());			
		}catch(Exception e){
			System.out.println("Error desconocido " + e.getMessage());
		}
		return usu;
	}
}