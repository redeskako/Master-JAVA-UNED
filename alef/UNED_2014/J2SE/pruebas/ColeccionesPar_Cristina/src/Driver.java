import java.util.*;

import es.uned.coordenadas.*;

public class Driver {

	public static void main(String[] args){
		
		//CONJUNTOS DE PAR:
		System.out.printf("\n\nCONJUNTOS DE PAR:\n");
		
		//Pares:
		Par p1 = new Par(3,5);
		Par p2 = new Par(7,20);
		Par p3 = new Par(0,50);
		Par p4 = new Par(-8,7);
		Par p5 = new Par (0,0);
		Par px = new Par (4,5);
		Par[] arrayPar = {p1, p2, p3, p4, p5};		
		
		//Se crean los conjuntos:
		TreeSet<Par> conjuntoTreePar = new TreeSet<Par>();
		System.out.printf("Esto es conjuntoTreePar vacío: %s \n", conjuntoTreePar.toString());
		HashSet<Par> conjuntoHashPar = new HashSet<Par>();
		System.out.printf("Esto es conjuntoHashPar vacío: %s \n\n", conjuntoHashPar.toString());
		
		//Se llenan los conjuntos con p1, p2, p3, p4, p5:
		for (int i=0; i<arrayPar.length; i++){
			conjuntoTreePar.add(arrayPar[i]);
			conjuntoHashPar.add(arrayPar[i]);
		}
		System.out.printf("Esto es conjuntoTreePar lleno: %s (ordena con compareTo)\n", conjuntoTreePar.toString());
		System.out.printf("Esto es conjuntoHashPar lleno: %s\n\n", conjuntoHashPar.toString());
		
		//Se intenta incluir un elemento repetido:
		conjuntoTreePar.add(p1);
		conjuntoHashPar.add(p1);
		System.out.printf("Intentando repetir un elemento en conjuntoTreePar: %s \n", conjuntoTreePar.toString());
		System.out.printf("Intentando repetir un elemento en conjuntoHashPar: %s \n\n", conjuntoHashPar.toString());
		
		//Vamos a imprimir los elementos separados:
		System.out.println("Estos son los elementos sueltos del conjuntoTreePar (mostrados con iterator):");
		Iterator iteradorConjuntoTree = conjuntoTreePar.iterator();
			while (iteradorConjuntoTree.hasNext()){
				System.out.println(iteradorConjuntoTree.next());
			}
		System.out.println("Estos son los elementos sueltos del conjuntoHashPar (mostrados con iterator):");
		Iterator iteradorConjuntoHash = conjuntoHashPar.iterator();
			while (iteradorConjuntoHash.hasNext()){
				Par p = (Par)iteradorConjuntoHash.next();
				System.out.printf("%s hashCode: %d\n", p, p.hashCode());
			}
				
		//Tamaño de los conjuntos:
		System.out.printf("\nTamaño del conjuntoTreePar (size): %d objetos \n\n", conjuntoTreePar.size());
		System.out.printf("\nTamaño del conjuntoHashPar (size): %d objetos \n\n", conjuntoHashPar.size());
		
		//Se crean otros TreeSet y HashSet para comparar (en elos vamos a eliminar un elemento):
		TreeSet<Par> copiaTree = new TreeSet<Par>();
		copiaTree = (TreeSet<Par>)conjuntoTreePar.clone(); //casting para que funcione
		copiaTree.remove(p3);
		System.out.printf("Esto es copiaTree (clone + remove): %s \n", copiaTree.toString());
		
		HashSet<Par>copiaHash = new HashSet<Par>();
		copiaHash = (HashSet)conjuntoHashPar.clone(); //casting para que funcione
		copiaHash.remove(p2);
		System.out.printf("Esto es copiaHash (clone + remove): %s \n\n", copiaHash.toString());
		
		//Comprobar si un TreeSet y un HashSet contiene un elemento o no:
		System.out.printf("¿Contiene (contains) conjuntoTreePar el elemento %s? %b \n", 
				p3.toString(), conjuntoTreePar.contains(p3));
		System.out.printf("¿Contiene (contains) copiaPar el elemento %s? %b \n\n", 
				p3.toString(), copiaTree.contains(p3));
		System.out.printf("¿Contiene (contains) conjuntoHashPar el elemento %s? %b \n", 
				p2.toString(), conjuntoHashPar.contains(p2));
		System.out.printf("¿Contiene (contains) copiaHash el elemento %s? %b \n\n", 
				p2.toString(), copiaHash.contains(p2));
		
		System.out.printf("COSAS DE TREESET:\n\n");
		
		//Primer elemento:
		System.out.printf("El elemento menor (first) es %s\n", conjuntoTreePar.first());
		//Último elemento:
		System.out.printf("El elemento mayor (last) es %s\n\n", conjuntoTreePar.last());

		//Buscar el menor de los elementos mayores a p3
		System.out.printf("ceiling: El menor de los elementos mayores o iguales a %s es %s. \n", 
				p3, conjuntoTreePar.ceiling(p3));
		System.out.printf("higher: El menor de los elementos mayores a %s es %s. \n", 
				p3, conjuntoTreePar.higher(p3));
		//Buscar el mayor de los elementos menores a p3
		System.out.printf("floor: El mayor de los elementos menores o iguales a %s es %s. \n", 
				p3, conjuntoTreePar.floor(p3));
		System.out.printf("lower: El mayor de los elementos menores a %s es %s. \n\n", 
				p3, conjuntoTreePar.lower(p3));
		
		//Porción mayores a px
		System.out.printf("tailSet: Porción de Set de los elementos mayores a %s es %s \n", 
				px, conjuntoTreePar.tailSet(px));
		//Porción entre p3 y px
		System.out.printf("subSet: Porción de Set de los elementos entre %s y %s es %s \n", 
				p5, px, conjuntoTreePar.subSet(p5, false, px, false));
		//Porción menores a px
		System.out.printf("headSet: Porción de Set de los elementos menores a %s es %s \n\n", 
				px, conjuntoTreePar.headSet(px));
		
		
		
		//Vamos a darle la vuelta a conjuntoTreePar en conjuntoAlreves:
		NavigableSet<Par> conjuntoAlreves = conjuntoTreePar.descendingSet();
		System.out.printf("Esto es conjuntoAlreves (descendingSet): %s \n\n", conjuntoAlreves.toString());
		
		
		//VACIAR CONJUNTO:
		System.out.printf("VACIANDO LOS CONJUNTOS:\n\n");
		
		//Eliminar primer y último elemento:
		System.out.printf("Extraer primer elemento de TreeSet (pollFirst): %s\n", conjuntoTreePar.pollFirst());
		System.out.printf("conjuntoTreePar: %s \n", conjuntoTreePar.toString());
		System.out.printf("Extraer último elemento de TreeSet (pollLast): %s\n", conjuntoTreePar.pollLast());
		System.out.printf("conjuntoTreePar: %s \n\n", conjuntoTreePar.toString());
		
		//Eliminar un elemento concreto:
		conjuntoTreePar.remove(p3);
		System.out.printf("Extraer el elemento %s de conjuntoTreePar: %s \n", p3.toString(), conjuntoTreePar.toString());
		conjuntoHashPar.remove(p3);
		System.out.printf("Extraer el elemento %s de conjuntoHashPar: %s \n\n", p3.toString(), conjuntoHashPar.toString());
		
		//Vaciar el conjunto:
		System.out.printf("¿Está vacío conjuntoTreePar (isEmpty)? %s \n", conjuntoTreePar.isEmpty());
		conjuntoTreePar.clear();
		System.out.printf("Esto es conjuntoTreePar después de vaciarlo (clear): %s \n", conjuntoTreePar.toString());
		System.out.printf("¿Está vacío conjuntoTreePar ahora (isEmpty)? %s \n\n", conjuntoTreePar.isEmpty());

/*		//CONJUNTOS DE PIXEL:
		System.out.println("CONJUNTOS DE PIXEL:");
		
		// Inicialización de objetos Pixel:
		Pixel pi1 = new Pixel("AZUL",1,-5);
		Pixel pi2 = new Pixel("AZUL",0,0);
		Pixel pi3 = new Pixel("VERDE",-8,-50);
		Pixel pi4 = new Pixel("AMARILLO",0,0);
		Pixel pi5 = new Pixel("BLANCO",5,0);
		
		// Se colocan en un array para poder crear los conjuntos más fácilmente
		Pixel[] arrayPixel = {pi1, pi2, pi3, pi4, pi5};
		
		// Se crea un TreeSet de elementos Pixel mediante el constructor TreeSet<>(Comparator<? super E> comparator).
		// En Comparator se define un método comapre() de dos elementos Pixel, que a su vez llama al método compareTo() de Pixel.
		Set<Pixel> conjuntoTreePixel = new TreeSet<Pixel>(new Comparator<Pixel>(){
			public int compare(Pixel pi1, Pixel pi2){
				// Se devuelve el comparador que se ha desarrollado en la clase Pixel (compareTo)
				return pi1.compareTo(pi2);
			}
		});
		
		// Se crea un HashSet de elementos Pixel.
		Set<Pixel> conjuntoHashPixel = new HashSet<>();
		
		// Se llenan ambos conjuntos (TreeSet y HashSet) con los elementos Pixel del arrayPixel.
		for (int i=0; i<arrayPixel.length; i++){
			conjuntoTreePixel.add(arrayPixel[i]);
			conjuntoHashPixel.add(arrayPixel[i]);
		}
		
		// Se muestran ambos conjuntos en consola.
		System.out.printf("Esto es conjuntoTreePixel: \n%s\n", conjuntoTreePixel.toString());
		System.out.printf("Esto es conjuntoHashPixel: \n%s\n", conjuntoHashPixel.toString());
*/

	}
}
