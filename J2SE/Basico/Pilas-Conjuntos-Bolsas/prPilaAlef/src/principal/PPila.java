package principal;

import pilas.Par;
import pilas.Pilav1;
import pilas.Pilav2;
import pilas.Point;

public class PPila {
	public static void main (String [] arg){

 	System.out.println("Hola mundo");

    Point punto = new Point(2,2,2);

    System.out.println(punto);

	Point punto2 = new Point(3,3,3);

	System.out.println(punto2.toString());

		Par p1= new Par(3,3);
		Par p2= new Par (4,3);
		Par p3= new Par(3,1);

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

		Pilav1 pila = new Pilav1();
		System.out.println(pila);
		pila.push(p1);
		pila.push(p2);
		pila.push(p3);
		System.out.println("Añadimos tres nodos");
		System.out.println(pila);
		pila.pop();
		System.out.println(pila);
		Pilav2 pila2 = new Pilav2();
		System.out.println(pila2);
		pila2.push(p1);
		System.out.println(pila2);
		pila2.push(p2);
		System.out.println(pila2);
		pila2.push(p3);
		System.out.println(pila2);


	}

}

