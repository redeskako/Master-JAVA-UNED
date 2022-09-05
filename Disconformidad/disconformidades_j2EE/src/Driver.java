import org.aprende.java.bbdd.*;

import java.util.*;
public class Driver {

	public static void main(String[] args) {
		
		Disconformidad unaDisconformidad = new Disconformidad(); 
		ArrayList<Disconformidad> treeSetDisconformidades = new Disconformidad().listadoDisconformidades();
		System.out.println(treeSetDisconformidades.size());
		
		//ArrayList arrayListDisconformidades = new ArrayList<Disconformidad>();
		//arrayListDisconformidades.addAll(treeSetDisconformidades);
		
		//Iterator it= arrayListDisconformidades.iterator();		
		//while (it.hasNext()){
		//	unaDisconformidad = (Disconformidad)it.next();
		//	System.out.println(unaDisconformidad.numero());
		//}
		
		
		Iterator it= treeSetDisconformidades.iterator();		
		while (it.hasNext()){
			unaDisconformidad = (Disconformidad)it.next();
			System.out.println("ID: " + unaDisconformidad.numero());
			System.out.println("ID USUARIO: " + unaDisconformidad.usuario());
			//Usuario usu = new Usuarios().getUsuario(unaDisconformidad.usuario());
			//System.out.println("Nombre de usuario "+usu.nombre());	
		}
		
	}

}
