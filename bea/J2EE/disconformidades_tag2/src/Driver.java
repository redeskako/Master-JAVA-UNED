import org.aprende.java.bbdd.*;
import org.aprende.java.*;

import java.util.*;
public class Driver {

	public static void main(String[] args) {
		
		Usuario usu= new Usuario().validarUsuario("alef", "alef");
		System.out.println(usu.Id());
		
		Controlador c=new Controlador();
		
	}

}
