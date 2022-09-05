

import java.util.*;

import org.basedatos.ErrorException;
import org.basedatos.ListadoRecursos;
import org.basedatos.Recurso;


public class Driver {
public static void main(String[]args) throws ErrorException{
	//creo un recurso 
	Recurso recurso1 = new Recurso();
	// ArrayList<Recurso> treeSetRecursos = new Recurso().listadoRecursos();
	ListadoRecursos treeSetRecursos = new ListadoRecursos().listadoRecursos();
	
	System.out.println(treeSetRecursos.size());
	Iterator<Recurso> it = treeSetRecursos.iterator();
	while (it.hasNext());
		recurso1 = (Recurso)it.next();
		System.out.println("ID:" + recurso1.getIdRecurso());
		System.out.println("ID USUARIO:" + recurso1.getDescripcion());
}
}
