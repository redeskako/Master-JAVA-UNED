package org.driver;


import org.coordenadas.*;
import org.listas.*;
import java.util.*;
/**
 * @author carlosl.sanchez
 *
 */
public class Driver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Par p1;
		Par p2;
		Par p3;
		
		p1= new Par(3,5);
		p2= new Par(3,5);
		p3= new Par(40);
		
		System.out.println("¿P1 es igual a P2? "+(p1.equals(p2)));
		System.out.println("Valor P1:"+p1);
		System.out.println("Valor P2:"+p2);
		System.out.println("Valor P3:"+p3);
		PilaParAc prov1= new PilaParAc(3);
		//PilaParAc prov2= new PilaParAc(3);
		Random rnd= new Random();
		try{
			for (int i=0; i<=3; i++){
				prov1.push(new Par(rnd.nextInt(10),rnd.nextInt(10)));
			}
		}catch(IndexOutOfBoundsException e){
			System.out.println("Pila Llena.");
		}
		
		Set s= new TreeSet();
		for (int i=0; i<=30; i++){
			s.add(new Par(rnd.nextInt(10),rnd.nextInt(10)));
		}
		System.out.println("Mi conjunto favorito:");
		System.out.println(s.toString());
		p2.compare(p1, p3);
		System.out.println("p2 con p3="+p2.compare(p2, p3));
		System.out.println("Prueba");
		
		// Vector v= new Stack();
		Stack v= new Stack();
		
		System.out.println(v.empty());
		
		for (int i=0; i<=30; i++){
			v.add (new Par(rnd.nextInt(10),rnd.nextInt(10)));
		}
		System.out.println("Vector");
		System.out.println(v.toString());
		
		System.out.println(v.empty());
		
		PilaPar lapila  = new PilaPar();
		System.out.println(lapila.esVacia());
		//p2=lapila.pop();
		lapila.push(p1);
		System.out.println(lapila.esVacia());
		//p3=lapila.pop();
		System.out.println(p3.toString());
		lapila.push(p1);
		System.out.println(lapila.toString());
		
	}

}
