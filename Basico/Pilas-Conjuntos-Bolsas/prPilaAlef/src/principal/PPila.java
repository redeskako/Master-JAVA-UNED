package principal;

import pilas.Par;
import pilas.PilaV1;
import pilas.PilaV2;
import pilas.Point;

public class PPila {
	public static void main (String [] arg){

 	System.out.println("Hola mundo");

    Point punto = new Point(2,2,2);

    System.out.println(punto.toString());

	Point punto2 = new Point(3,3,3);

	System.out.println(punto2.toString());

		Par p1= new Par (3,3);
		Par p2= new Par (4,3);
		Par p3= new Par (3,1);

		if (p1.equals(p2)){
			System.out.println("iguales");
		}else{
			System.out.println("NO iguales");
		}
		try{
			if (p1.equals(punto)){
				System.out.println("iguales");
			}else{
				System.out.println("NO iguales");
			}
		}catch(Exception e){
			System.out.println("Error en la comparación");
		}

		PilaV1 pila = new PilaV1();
		System.out.println(pila);
		pila.push(p1);
		pila.push(p2);
		pila.push(p3);
		System.out.println("Añadimos tres nodos");
		System.out.println(pila);

		
		PilaV2 pila2 = new PilaV2();
		pila2.push(p1);
		pila2.push(p2);
		pila2.push(p3);
		System.out.println(pila2);
		
		PilaV1 pila3 = pila.
		
		System.out.println("Las pilas son: " + pila.equals(pila));
	}

}

