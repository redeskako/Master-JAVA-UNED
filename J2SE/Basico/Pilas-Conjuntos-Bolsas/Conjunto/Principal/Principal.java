package Principal;
import java.util.*;
import clases.Par;




public class Principal {
	public static void main(String [] arg){

		//creamos un objeto TreeSet con la clase Parm que nos sirve para introducir los datos en una tabla de pares
		//que se llamara treeSetPar
		TreeSet<Par> treeSetPar;
		treeSetPar = new TreeSet<Par>();

		//introduccion de los valores, modo clase Par
		Par a1 = new Par(1,1);
		Par a2 = new Par(2,2);
		Par a3 = new Par(4,4);
		Par a4 = new Par(3,3);
		Par a5 = new Par(1,1);

		//añadimos los pares al objeto TreeSet
		//la clase TreeSet,  guardan objetos que no esten repetidos, y devuelve un false si se intenta.
		treeSetPar.add(a1);
		treeSetPar.add(a2);
		treeSetPar.add(a3);
		treeSetPar.add(a4);
		treeSetPar.add(a5);

		// a continuacion nos muestra listado de valores ordenados y no repetidos
		System.out.println(treeSetPar);

		



	}
}
