import org.aprende.java.*;
import org.aprende.java.bbdd.*;

public class Driver {

	
	public static void main(String[] args) {
		BBDD mibd= null;
		try{
			mibd=new BBDD();
			mibd.abrirConexion();
			//System.out.println("OK");
			System.out.println("¿Está conectado?"+ mibd.estaConectado());
			Usuarios lista = mibd.listadoUsuarios("select * from Usuarios");
			
			
		}catch(DisconformidadException e){
			System.out.println(e.getMessage());
		}
	}

}
